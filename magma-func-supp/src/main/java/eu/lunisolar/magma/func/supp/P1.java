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
 * - when({@link Is}::equal, 4)
 *
 * This class contains some predicates that take - and ignore - additional argument(s) that are intended only for building a message.
 *
 * @see {@link P}, {@link Is}, {@link Does}, {@link Be}, {@link Are}
 */
public final class P1 implements FluentSyntax {
	// <editor-fold desc="no instance">
	private P1() {
	}
	// </editor-fold>

	// <editor-fold desc="Object">

	/** Predicate: Object <%s> must be the same as <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1> boolean same(@Nullable Object n, @Nullable Object other, MP1 msgParamOnly) {
		return P.same(n, other);
	}

	/** "Special" predicate: Object <%s> must be the same as <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1> String sameEx(@Nullable Object n, @Nullable Object other, MP1 msgParamOnly) {
		return P.same(n, other) ? null : String.format("Object <%s> must be the same as <%s>.", n, other);
	}

	/** Predicate: Object <%s> must NOT be the same as <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1> boolean notSame(@Nullable Object n, @Nullable Object other, MP1 msgParamOnly) {
		return P.notSame(n, other);
	}

	/** "Special" predicate: Object <%s> must NOT be the same as <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1> String notSameEx(@Nullable Object n, @Nullable Object other, MP1 msgParamOnly) {
		return P.notSame(n, other) ? null : String.format("Object <%s> must NOT be the same as <%s>.", n, other);
	}

	/** Predicate: Reference must be null, currently is pointing to <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1> boolean Null(@Nullable Object n, MP1 msgParamOnly) {
		return P.Null(n);
	}

	/** "Special" predicate: Reference must be null, currently is pointing to <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1> String NullEx(@Nullable Object n, MP1 msgParamOnly) {
		return P.Null(n) ? null : String.format("Reference must be null, currently is pointing to <%s>.", n);
	}

	/** Predicate: Reference must NOT be null, currently is pointing to <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1> boolean notNull(@Nullable Object n, MP1 msgParamOnly) {
		return P.notNull(n);
	}

	/** "Special" predicate: Reference must NOT be null, currently is pointing to <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1> String notNullEx(@Nullable Object n, MP1 msgParamOnly) {
		return P.notNull(n) ? null : String.format("Reference must NOT be null, currently is pointing to <%s>.", n);
	}

	// </editor-fold>

	// <editor-fold desc="`String`">

	/** Predicate: String <'%s'> must be empty. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1> boolean empty(@Nonnull String n, MP1 msgParamOnly) {
		Null.nonNullArg(n, "n");
		return P.empty(n);
	}

	/** "Special" predicate: String <'%s'> must be empty. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1> String emptyEx(@Nonnull String n, MP1 msgParamOnly) {
		Null.nonNullArg(n, "n");
		return P.empty(n) ? null : String.format("String <'%s'> must be empty.", n);
	}

	/** Predicate: String <'%s'> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1> boolean notEmpty(@Nonnull String n, MP1 msgParamOnly) {
		Null.nonNullArg(n, "n");
		return P.notEmpty(n);
	}

	/** "Special" predicate: String <'%s'> must NOT be empty. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1> String notEmptyEx(@Nonnull String n, MP1 msgParamOnly) {
		Null.nonNullArg(n, "n");
		return P.notEmpty(n) ? null : String.format("String <'%s'> must NOT be empty.", n);
	}

	/** Predicate: String <'%s'> must be blank (empty or consisting of only white characters). Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1> boolean blank(@Nonnull String n, MP1 msgParamOnly) {
		Null.nonNullArg(n, "n");
		return P.blank(n);
	}

	/** "Special" predicate: String <'%s'> must be blank (empty or consisting of only white characters). Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1> String blankEx(@Nonnull String n, MP1 msgParamOnly) {
		Null.nonNullArg(n, "n");
		return P.blank(n) ? null : String.format("String <'%s'> must be blank (empty or consisting of only white characters).", n);
	}

	/** Predicate: String <'%s'> must NOT be blank (empty or consisting of only white characters). Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1> boolean notBlank(@Nonnull String n, MP1 msgParamOnly) {
		Null.nonNullArg(n, "n");
		return P.notBlank(n);
	}

	/** "Special" predicate: String <'%s'> must NOT be blank (empty or consisting of only white characters). Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1> String notBlankEx(@Nonnull String n, MP1 msgParamOnly) {
		Null.nonNullArg(n, "n");
		return P.notBlank(n) ? null : String.format("String <'%s'> must NOT be blank (empty or consisting of only white characters).", n);
	}

	/** Predicate: String <'%s'> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1> boolean nullOrEmpty(@Nonnull String n, MP1 msgParamOnly) {
		return P.nullOrEmpty(n);
	}

	/** "Special" predicate: String <'%s'> must be null or empty. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1> String nullOrEmptyEx(@Nonnull String n, MP1 msgParamOnly) {
		return P.nullOrEmpty(n) ? null : String.format("String <'%s'> must be null or empty.", n);
	}

	/** Predicate: String <'%s'> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1> boolean notNullNorEmpty(@Nonnull String n, MP1 msgParamOnly) {
		return P.notNullNorEmpty(n);
	}

	/** "Special" predicate: String <'%s'> must NOT be null or empty. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1> String notNullNorEmptyEx(@Nonnull String n, MP1 msgParamOnly) {
		return P.notNullNorEmpty(n) ? null : String.format("String <'%s'> must NOT be null or empty.", n);
	}

	/** Predicate: String <'%s'> must be null or blank. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1> boolean nullOrBlank(@Nonnull String n, MP1 msgParamOnly) {
		return P.nullOrBlank(n);
	}

	/** "Special" predicate: String <'%s'> must be null or blank. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1> String nullOrBlankEx(@Nonnull String n, MP1 msgParamOnly) {
		return P.nullOrBlank(n) ? null : String.format("String <'%s'> must be null or blank.", n);
	}

	/** Predicate: String <'%s'> must NOT be null or blank. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1> boolean notNullNorBlank(@Nonnull String n, MP1 msgParamOnly) {
		return P.notNullNorBlank(n);
	}

	/** "Special" predicate: String <'%s'> must NOT be null or blank. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1> String notNullNorBlankEx(@Nonnull String n, MP1 msgParamOnly) {
		return P.notNullNorBlank(n) ? null : String.format("String <'%s'> must NOT be null or blank.", n);
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
	public static <MP1> boolean instanceOf(Object object, Class<?> clazz, MP1 msgParamOnly) {
		Null.nonNullArg(clazz, "clazz");
		return P.instanceOf(object, clazz);
	}

	/** "Special" predicate: Object <%s> of class <%s> must be instance of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1> String instanceOfEx(Object object, Class<?> clazz, MP1 msgParamOnly) {
		Null.nonNullArg(clazz, "clazz");
		return P.instanceOf(object, clazz) ? null : String.format("Object <%s> of class <%s> must be instance of <%s>.", object, object != null ? object.getClass() : null, clazz);
	}

	/** Predicate: Object <%s> of class <%s> must NOT be instance of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1> boolean notInstanceOf(Object object, Class<?> clazz, MP1 msgParamOnly) {
		Null.nonNullArg(clazz, "clazz");
		return P.notInstanceOf(object, clazz);
	}

	/** "Special" predicate: Object <%s> of class <%s> must NOT be instance of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1> String notInstanceOfEx(Object object, Class<?> clazz, MP1 msgParamOnly) {
		Null.nonNullArg(clazz, "clazz");
		return P.notInstanceOf(object, clazz) ? null : String.format("Object <%s> of class <%s> must NOT be instance of <%s>.", object, object != null ? object.getClass() : null, clazz);
	}

	/** Predicate: Object <%s> of class <%s> must be exactly instance of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1> boolean exactlyInstanceOf(Object object, Class<?> clazz, MP1 msgParamOnly) {
		Null.nonNullArg(clazz, "clazz");
		return P.exactlyInstanceOf(object, clazz);
	}

	/** "Special" predicate: Object <%s> of class <%s> must be exactly instance of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1> String exactlyInstanceOfEx(Object object, Class<?> clazz, MP1 msgParamOnly) {
		Null.nonNullArg(clazz, "clazz");
		return P.exactlyInstanceOf(object, clazz) ? null : String.format("Object <%s> of class <%s> must be exactly instance of <%s>.", object, object != null ? object.getClass() : null, clazz);
	}

	/** Predicate: Object <%s> of class <%s> must NOT be exactly instance of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1> boolean notExactlyInstanceOf(Object object, Class<?> clazz, MP1 msgParamOnly) {
		Null.nonNullArg(clazz, "clazz");
		return P.notExactlyInstanceOf(object, clazz);
	}

	/** "Special" predicate: Object <%s> of class <%s> must NOT be exactly instance of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1> String notExactlyInstanceOfEx(Object object, Class<?> clazz, MP1 msgParamOnly) {
		Null.nonNullArg(clazz, "clazz");
		return P.notExactlyInstanceOf(object, clazz) ? null : String.format("Object <%s> of class <%s> must NOT be exactly instance of <%s>.", object, object != null ? object.getClass() : null, clazz);
	}

	/** Predicate: Class <%s> of class <%s> must ---NOT-- be specialization of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1> boolean assignableFrom(Class<?> specialization, Class<?> clazz, MP1 msgParamOnly) {
		Null.nonNullArg(clazz, "clazz");
		return P.assignableFrom(specialization, clazz);
	}

	/** "Special" predicate: Class <%s> of class <%s> must ---NOT-- be specialization of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1> String assignableFromEx(Class<?> specialization, Class<?> clazz, MP1 msgParamOnly) {
		Null.nonNullArg(clazz, "clazz");
		return P.assignableFrom(specialization, clazz) ? null : String.format("Class <%s> of class <%s> must ---NOT-- be specialization of <%s>.", specialization, specialization != null ? specialization.getClass() : null, clazz);
	}

	/** Predicate: Class <%s> of class <%s> must ---NOT-- be specialization of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1> boolean notAssignableFrom(Class<?> specialization, Class<?> clazz, MP1 msgParamOnly) {
		Null.nonNullArg(clazz, "clazz");
		return P.notAssignableFrom(specialization, clazz);
	}

	/** "Special" predicate: Class <%s> of class <%s> must ---NOT-- be specialization of <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link P1}, {@link P2}.*/
	public static <MP1> String notAssignableFromEx(Class<?> specialization, Class<?> clazz, MP1 msgParamOnly) {
		Null.nonNullArg(clazz, "clazz");
		return P.notAssignableFrom(specialization, clazz) ? null : String.format("Class <%s> of class <%s> must ---NOT-- be specialization of <%s>.", specialization, specialization != null ? specialization.getClass() : null, clazz);
	}

	// </editor-fold>

	// <editor-fold desc="Throwables">

	// </editor-fold>

}
