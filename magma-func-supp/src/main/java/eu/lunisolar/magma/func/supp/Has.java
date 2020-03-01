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
public final class Has implements FluentSyntax {
	// <editor-fold desc="no instance">
	private Has() {
	}
	// </editor-fold>

	// <editor-fold desc="Object">

	// </editor-fold>

	// <editor-fold desc="`String`">

	public static boolean length(@Nonnull CharSequence s, int size) {
		Null.nonNullArg(s, "s");
		return size == s.length();
	}

	// </editor-fold>

	// <editor-fold desc="and/or/xor">

	// </editor-fold>

	// <editor-fold desc="arrays">

	public static boolean length(@Nonnull boolean[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.privately_ofLength(array, i);
	}

	public static boolean length(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.privately_ofLength(array, i);
	}

	public static boolean length(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.privately_ofLength(array, i);
	}

	public static boolean length(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.privately_ofLength(array, i);
	}

	public static boolean length(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.privately_ofLength(array, i);
	}

	public static boolean length(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.privately_ofLength(array, i);
	}

	public static boolean length(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.privately_ofLength(array, i);
	}

	public static boolean length(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.privately_ofLength(array, i);
	}

	public static <T> boolean length(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.privately_ofLength(array, i);
	}

	// </editor-fold>

	// <editor-fold desc="collections">

	public static <T> boolean size(@Nonnull Collection<T> collection, int i) {
		Null.nonNullArg(collection, "collection");
		return collection.size() == i;
	}

	public static <K, V> boolean size(@Nonnull Map<K, V> map, int i) {
		Null.nonNullArg(map, "map");
		return map.size() == i;
	}

	// </editor-fold>

	// <editor-fold desc="object derivatives">

	// </editor-fold>

	// <editor-fold desc="Throwables">

	public static boolean cause(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return e.getCause() != null;
	}

	public static boolean noCause(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return !cause(e);
	}

	public static boolean msgStartWith(@Nonnull Throwable e, @Nonnull String description) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(description, "description");
		return e.getMessage() != null && e.getMessage().startsWith(description);
	}

	public static boolean msgNotStartWith(@Nonnull Throwable e, @Nonnull String description) {
		return !msgStartWith(e, description);
	}

	public static boolean msgContain(@Nonnull Throwable e, @Nonnull String description) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(description, "description");
		return e.getMessage() != null && e.getMessage().contains(description);
	}

	public static boolean msgNotContain(@Nonnull Throwable e, @Nonnull String description) {
		return !msgContain(e, description);
	}

	public static boolean msgEndWith(@Nonnull Throwable e, @Nonnull String description) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(description, "description");
		return e.getMessage() != null && e.getMessage().endsWith(description);
	}

	public static boolean msgNotEndWith(@Nonnull Throwable e, @Nonnull String description) {
		return !msgEndWith(e, description);
	}

	public static boolean causeThat(@Nonnull Throwable e, @Nonnull LPredicate<Throwable> causePredicate) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(causePredicate, "causePredicate");
		return cause(e) && causePredicate.doApplyAsBoolean(e.getCause());
	}

	public static boolean rootCauseThat(@Nonnull Throwable e, @Nonnull LPredicate<Throwable> causePredicate) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(causePredicate, "causePredicate");
		return cause(e) && causePredicate.doApplyAsBoolean(X.getRootCause(e));
	}

	public static boolean suppressing(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return e.getSuppressed().length > 0;
	}

	// </editor-fold>

}
