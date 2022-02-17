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
 * - when({@link Is}::equal, 4) *
 * @see {@link P}, {@link Is}, {@link Does}, {@link Be}, {@link Are}
 */
public final class Have implements FluentSyntax {
	// <editor-fold desc="no instance">
	private Have() {
	}
	// </editor-fold>

	// <editor-fold desc="Object">

	// </editor-fold>

	// <editor-fold desc="`String`">

	/** Predicate: String <'%s'> must be <%d> characters long. Available in {@link P}, {@link Has}, {@link Have}, {@link Is}, {@link Be}.*/
	public static boolean length(@Nonnull CharSequence s, int size) {
		Null.nonNullArg(s, "s");
		return P.length(s, size);
	}

	/** "Special" predicate: String <'%s'> must be <%d> characters long. Available in {@link P}, {@link Has}, {@link Have}, {@link Is}, {@link Be}.*/
	public static @Nullable String lengthEx(@Nonnull CharSequence s, int size) {
		Null.nonNullArg(s, "s");
		return P.lengthEx(s, size);
	}
	/** Predicate: String <'%s'> must NOT be <%d> characters long. Available in {@link P}, {@link Has}, {@link Have}, {@link Is}, {@link Be}.*/
	public static boolean lengthOtherThan(@Nonnull CharSequence s, int size) {
		Null.nonNullArg(s, "s");
		return P.lengthOtherThan(s, size);
	}

	/** "Special" predicate: String <'%s'> must NOT be <%d> characters long. Available in {@link P}, {@link Has}, {@link Have}, {@link Is}, {@link Be}.*/
	public static @Nullable String lengthOtherThanEx(@Nonnull CharSequence s, int size) {
		Null.nonNullArg(s, "s");
		return P.lengthOtherThanEx(s, size);
	}

	// </editor-fold>

	// <editor-fold desc="and/or/xor">

	// </editor-fold>

	// <editor-fold desc="== <= >= < >">

	// </editor-fold>

	// <editor-fold desc="arrays">

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean length(@Nonnull boolean[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.length(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static @Nullable String lengthEx(@Nonnull boolean[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.lengthEx(array, i);
	}
	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull boolean[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.lengthOtherThan(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static @Nullable String lengthOtherThanEx(@Nonnull boolean[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.lengthOtherThanEx(array, i);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean length(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.length(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static @Nullable String lengthEx(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.lengthEx(array, i);
	}
	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.lengthOtherThan(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static @Nullable String lengthOtherThanEx(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.lengthOtherThanEx(array, i);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean length(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.length(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static @Nullable String lengthEx(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.lengthEx(array, i);
	}
	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.lengthOtherThan(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static @Nullable String lengthOtherThanEx(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.lengthOtherThanEx(array, i);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean length(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.length(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static @Nullable String lengthEx(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.lengthEx(array, i);
	}
	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.lengthOtherThan(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static @Nullable String lengthOtherThanEx(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.lengthOtherThanEx(array, i);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean length(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.length(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static @Nullable String lengthEx(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.lengthEx(array, i);
	}
	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.lengthOtherThan(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static @Nullable String lengthOtherThanEx(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.lengthOtherThanEx(array, i);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean length(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.length(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static @Nullable String lengthEx(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.lengthEx(array, i);
	}
	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.lengthOtherThan(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static @Nullable String lengthOtherThanEx(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.lengthOtherThanEx(array, i);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean length(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.length(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static @Nullable String lengthEx(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.lengthEx(array, i);
	}
	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.lengthOtherThan(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static @Nullable String lengthOtherThanEx(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.lengthOtherThanEx(array, i);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean length(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.length(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static @Nullable String lengthEx(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.lengthEx(array, i);
	}
	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.lengthOtherThan(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static @Nullable String lengthOtherThanEx(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.lengthOtherThanEx(array, i);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <T> boolean length(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.length(array, i);
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <T> @Nullable String lengthEx(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.lengthEx(array, i);
	}
	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <T> boolean lengthOtherThan(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.lengthOtherThan(array, i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <T> @Nullable String lengthOtherThanEx(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.lengthOtherThanEx(array, i);
	}

	// </editor-fold>

	// <editor-fold desc="collections">

	/** Predicate: Collection <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <T> boolean size(@Nonnull Collection<T> collection, int i) {
		Null.nonNullArg(collection, "collection");
		return P.size(collection, i);
	}

	/** "Special" predicate: Collection <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <T> @Nullable String sizeEx(@Nonnull Collection<T> collection, int i) {
		Null.nonNullArg(collection, "collection");
		return P.sizeEx(collection, i);
	}
	/** Predicate: Collection <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <T> boolean sizeOtherThan(@Nonnull Collection<T> collection, int i) {
		Null.nonNullArg(collection, "collection");
		return P.sizeOtherThan(collection, i);
	}

	/** "Special" predicate: Collection <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <T> @Nullable String sizeOtherThanEx(@Nonnull Collection<T> collection, int i) {
		Null.nonNullArg(collection, "collection");
		return P.sizeOtherThanEx(collection, i);
	}

	/** Predicate: Map <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <K, V> boolean size(@Nonnull Map<K, V> map, int i) {
		Null.nonNullArg(map, "map");
		return P.size(map, i);
	}

	/** "Special" predicate: Map <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <K, V> @Nullable String sizeEx(@Nonnull Map<K, V> map, int i) {
		Null.nonNullArg(map, "map");
		return P.sizeEx(map, i);
	}
	/** Predicate: Map <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <K, V> boolean sizeOtherThan(@Nonnull Map<K, V> map, int i) {
		Null.nonNullArg(map, "map");
		return P.sizeOtherThan(map, i);
	}

	/** "Special" predicate: Map <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <K, V> @Nullable String sizeOtherThanEx(@Nonnull Map<K, V> map, int i) {
		Null.nonNullArg(map, "map");
		return P.sizeOtherThanEx(map, i);
	}

	// </editor-fold>

	// <editor-fold desc="object derivatives">

	// </editor-fold>

	// <editor-fold desc="Throwables">

	/** Predicate: Exception <%s> must have cause. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean cause(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return P.cause(e);
	}

	/** "Special" predicate: Exception <%s> must have cause. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static @Nullable String causeEx(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return P.causeEx(e);
	}
	/** Predicate: Exception <%s> must NOT have cause. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean noCause(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return P.noCause(e);
	}

	/** "Special" predicate: Exception <%s> must NOT have cause. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static @Nullable String noCauseEx(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return P.noCauseEx(e);
	}

	/** Predicate: Exception <%s> must have suspended other exceptions. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean suspended(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return P.suspended(e);
	}

	/** "Special" predicate: Exception <%s> must have suspended other exceptions. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static @Nullable String suspendedEx(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return P.suspendedEx(e);
	}
	/** Predicate: Exception <%s> must NOT have suspended other exceptions. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean noSuspended(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return P.noSuspended(e);
	}

	/** "Special" predicate: Exception <%s> must NOT have suspended other exceptions. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static @Nullable String noSuspendedEx(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return P.noSuspendedEx(e);
	}

	/** Predicate: Exception <%s> must have message equal to <'%s>'. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean msgEqual(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return P.msgEqual(e, text);
	}

	/** "Special" predicate: Exception <%s> must have message equal to <'%s>'. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static @Nullable String msgEqualEx(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return P.msgEqualEx(e, text);
	}
	/** Predicate: Exception <%s> must NOT have message equal to <'%s>'. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean msgNotEqual(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return P.msgNotEqual(e, text);
	}

	/** "Special" predicate: Exception <%s> must NOT have message equal to <'%s>'. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static @Nullable String msgNotEqualEx(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return P.msgNotEqualEx(e, text);
	}

	/** Predicate: Exception <%s> must have message starting with <'%s>'. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean msgStartWith(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return P.msgStartWith(e, text);
	}

	/** "Special" predicate: Exception <%s> must have message starting with <'%s>'. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static @Nullable String msgStartWithEx(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return P.msgStartWithEx(e, text);
	}
	/** Predicate: Exception <%s> must NOT have message starting with <'%s>'. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean msgNotStartWith(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return P.msgNotStartWith(e, text);
	}

	/** "Special" predicate: Exception <%s> must NOT have message starting with <'%s>'. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static @Nullable String msgNotStartWithEx(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return P.msgNotStartWithEx(e, text);
	}

	/** Predicate: Exception <%s> must have message containing <'%s>'. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean msgContain(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return P.msgContain(e, text);
	}

	/** "Special" predicate: Exception <%s> must have message containing <'%s>'. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static @Nullable String msgContainEx(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return P.msgContainEx(e, text);
	}
	/** Predicate: Exception <%s> must NOT have message containing <'%s>'. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean msgNotContain(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return P.msgNotContain(e, text);
	}

	/** "Special" predicate: Exception <%s> must NOT have message containing <'%s>'. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static @Nullable String msgNotContainEx(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return P.msgNotContainEx(e, text);
	}

	/** Predicate: Exception <%s> must have message ending with <'%s>'. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean msgEndWith(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return P.msgEndWith(e, text);
	}

	/** "Special" predicate: Exception <%s> must have message ending with <'%s>'. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static @Nullable String msgEndWithEx(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return P.msgEndWithEx(e, text);
	}
	/** Predicate: Exception <%s> must NOT have message ending with <'%s>'. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean msgNotEndWith(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return P.msgNotEndWith(e, text);
	}

	/** "Special" predicate: Exception <%s> must NOT have message ending with <'%s>'. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static @Nullable String msgNotEndWithEx(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return P.msgNotEndWithEx(e, text);
	}

	/** Predicate: Exception <%s> must have suppressed other exceptions. Available in {@link P}, {@link Is}, {@link Has}, {@link Have}.*/
	public static boolean suppressing(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return P.suppressing(e);
	}

	/** "Special" predicate: Exception <%s> must have suppressed other exceptions. Available in {@link P}, {@link Is}, {@link Has}, {@link Have}.*/
	public static @Nullable String suppressingEx(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return P.suppressingEx(e);
	}
	/** Predicate: Exception <%s> must NOT have suppressed other exceptions. Available in {@link P}, {@link Is}, {@link Has}, {@link Have}.*/
	public static boolean notSuppressing(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return P.notSuppressing(e);
	}

	/** "Special" predicate: Exception <%s> must NOT have suppressed other exceptions. Available in {@link P}, {@link Is}, {@link Has}, {@link Have}.*/
	public static @Nullable String notSuppressingEx(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return P.notSuppressingEx(e);
	}

	// </editor-fold>

}
