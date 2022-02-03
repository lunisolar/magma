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
public final class Has implements FluentSyntax {
	// <editor-fold desc="no instance">
	private Has() {
	}
	// </editor-fold>

	// <editor-fold desc="Object">

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

	// </editor-fold>

	// <editor-fold desc="and/or/xor">

	// </editor-fold>

	// <editor-fold desc="== <= >= < >">

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
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", Arrays.toString(array), i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull boolean[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthOtherThanEx(@Nonnull boolean[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", Arrays.toString(array), i);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean length(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthEx(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", Arrays.toString(array), i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthOtherThanEx(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", Arrays.toString(array), i);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean length(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthEx(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", Arrays.toString(array), i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthOtherThanEx(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", Arrays.toString(array), i);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean length(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthEx(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", Arrays.toString(array), i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthOtherThanEx(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", Arrays.toString(array), i);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean length(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthEx(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", Arrays.toString(array), i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthOtherThanEx(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", Arrays.toString(array), i);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean length(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthEx(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", Arrays.toString(array), i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthOtherThanEx(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", Arrays.toString(array), i);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean length(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthEx(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", Arrays.toString(array), i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthOtherThanEx(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", Arrays.toString(array), i);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean length(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthEx(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", Arrays.toString(array), i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthOtherThanEx(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", Arrays.toString(array), i);
	}

	/** Predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <T> boolean length(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return Array.getLength(array) == i;
	}

	/** "Special" predicate: Array <%s> must be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <T> String lengthEx(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", Arrays.toString(array), i);
	}

	/** Predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <T> boolean lengthOtherThan(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOT be of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <T> String lengthOtherThanEx(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOT be of size %s.", Arrays.toString(array), i);
	}

	// </editor-fold>

	// <editor-fold desc="collections">

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

	// </editor-fold>

	// <editor-fold desc="object derivatives">

	// </editor-fold>

	// <editor-fold desc="Throwables">

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

	/** Predicate: Exception <%s> must have suspended other exceptions. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean suspended(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return e.getSuppressed().length != 0;
	}

	/** "Special" predicate: Exception <%s> must have suspended other exceptions. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String suspendedEx(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return (e.getSuppressed().length != 0) ? null : String.format("Exception <%s> must have suspended other exceptions.", e);
	}

	/** Predicate: Exception <%s> must NOT have suspended other exceptions. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean noSuspended(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return !(e.getSuppressed().length != 0);
	}

	/** "Special" predicate: Exception <%s> must NOT have suspended other exceptions. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String noSuspendedEx(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return !(e.getSuppressed().length != 0) ? null : String.format("Exception <%s> must NOT have suspended other exceptions.", e);
	}

	/** Predicate: Exception <%s> must have message equal to <'%s>'. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean msgEqual(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return text.equals(e.getMessage());
	}

	/** "Special" predicate: Exception <%s> must have message equal to <'%s>'. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String msgEqualEx(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return (text.equals(e.getMessage())) ? null : String.format("Exception <%s> must have message equal to <'%s>'.", e, text);
	}

	/** Predicate: Exception <%s> must NOT have message equal to <'%s>'. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean msgNotEqual(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return !(text.equals(e.getMessage()));
	}

	/** "Special" predicate: Exception <%s> must NOT have message equal to <'%s>'. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String msgNotEqualEx(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return !(text.equals(e.getMessage())) ? null : String.format("Exception <%s> must NOT have message equal to <'%s>'.", e, text);
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

}
