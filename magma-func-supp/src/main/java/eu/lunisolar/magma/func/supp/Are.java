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
@MethodReferences
public class Are implements FluentSyntax {

	// <editor-fold desc="no instance">
	private Are() {
	}
	// </editor-fold>

	// <editor-fold desc="Object">

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

	/** Predicate: String <'%s'> must be <%d> characters long. Available in {@link P}, {@link Is}, {@link Be}, {@link Are}.*/
	public static boolean ofLength(@Nonnull CharSequence s, int size) {
		Null.nonNullArg(s, "s");
		return P.ofLength(s, size);
	}

	/** "Special" predicate: String <'%s'> must be <%d> characters long. Available in {@link P}, {@link Is}, {@link Be}, {@link Are}.*/
	public static @Nullable String ofLengthEx(@Nonnull CharSequence s, int size) {
		Null.nonNullArg(s, "s");
		return P.ofLengthEx(s, size);
	}
	/** Predicate: String <'%s'> must NOT be <%d> characters long. Available in {@link P}, {@link Is}, {@link Be}, {@link Are}.*/
	public static boolean notOfLength(@Nonnull CharSequence s, int size) {
		Null.nonNullArg(s, "s");
		return P.notOfLength(s, size);
	}

	/** "Special" predicate: String <'%s'> must NOT be <%d> characters long. Available in {@link P}, {@link Is}, {@link Be}, {@link Are}.*/
	public static @Nullable String notOfLengthEx(@Nonnull CharSequence s, int size) {
		Null.nonNullArg(s, "s");
		return P.notOfLengthEx(s, size);
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

	// </editor-fold>

	// <editor-fold desc="Throwables">

	// </editor-fold>

	// <editor-fold desc="Value (e.g. Opt )">

	// </editor-fold>

}
