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

	/** Predicate: String <'%s'> must be <%d> characters long. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean length(@Nonnull CharSequence s, int size) {
		Null.nonNullArg(s, "s");
		return size == s.length();
	}

	/** "Special" predicate: String <'%s'> must be <%d> characters long. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String length$(@Nonnull CharSequence s, int size) {
		Null.nonNullArg(s, "s");
		return (size == s.length()) ? null : String.format("String <'%s'> must be <%d> characters long.", s, size);
	}

	/** Predicate: String <'%s'> must NOTbe <%d> characters long. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull CharSequence s, int size) {
		Null.nonNullArg(s, "s");
		return !(size == s.length());
	}

	/** "Special" predicate: String <'%s'> must NOTbe <%d> characters long. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthOtherThan$(@Nonnull CharSequence s, int size) {
		Null.nonNullArg(s, "s");
		return !(size == s.length()) ? null : String.format("String <'%s'> must NOTbe <%d> characters long.", s, size);
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
	public static String length$(@Nonnull boolean[] array, int i) {
		Null.nonNullArg(array, "array");
		return (Array.getLength(array) == i) ? null : String.format("Array <%s> must be of size %s.", array, i);
	}

	/** Predicate: Array <%s> must NOTbe of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull boolean[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOTbe of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthOtherThan$(@Nonnull boolean[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOTbe of size %s.", array, i);
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

	/** Predicate: Array <%s> must NOTbe of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOTbe of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthOtherThan$(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOTbe of size %s.", array, i);
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

	/** Predicate: Array <%s> must NOTbe of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOTbe of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthOtherThan$(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOTbe of size %s.", array, i);
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

	/** Predicate: Array <%s> must NOTbe of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOTbe of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthOtherThan$(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOTbe of size %s.", array, i);
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

	/** Predicate: Array <%s> must NOTbe of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOTbe of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthOtherThan$(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOTbe of size %s.", array, i);
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

	/** Predicate: Array <%s> must NOTbe of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOTbe of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthOtherThan$(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOTbe of size %s.", array, i);
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

	/** Predicate: Array <%s> must NOTbe of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOTbe of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthOtherThan$(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOTbe of size %s.", array, i);
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

	/** Predicate: Array <%s> must NOTbe of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean lengthOtherThan(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOTbe of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String lengthOtherThan$(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOTbe of size %s.", array, i);
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

	/** Predicate: Array <%s> must NOTbe of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <T> boolean lengthOtherThan(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i);
	}

	/** "Special" predicate: Array <%s> must NOTbe of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <T> String lengthOtherThan$(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return !(Array.getLength(array) == i) ? null : String.format("Array <%s> must NOTbe of size %s.", array, i);
	}

	// </editor-fold>

	// <editor-fold desc="collections">

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

	/** Predicate: Collection <%s> must NOTbe of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <T> boolean sizeOtherThan(@Nonnull Collection<T> collection, int i) {
		Null.nonNullArg(collection, "collection");
		return !(collection.size() == i);
	}

	/** "Special" predicate: Collection <%s> must NOTbe of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <T> String sizeOtherThan$(@Nonnull Collection<T> collection, int i) {
		Null.nonNullArg(collection, "collection");
		return !(collection.size() == i) ? null : String.format("Collection <%s> must NOTbe of size %s.", collection, i);
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

	/** Predicate: Map <%s> must NOTbe of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <K, V> boolean sizeOtherThan(@Nonnull Map<K, V> map, int i) {
		Null.nonNullArg(map, "map");
		return !(map.size() == i);
	}

	/** "Special" predicate: Map <%s> must NOTbe of size %s. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static <K, V> String sizeOtherThan$(@Nonnull Map<K, V> map, int i) {
		Null.nonNullArg(map, "map");
		return !(map.size() == i) ? null : String.format("Map <%s> must NOTbe of size %s.", map, i);
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
	public static String cause$(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return (e.getCause() != null) ? null : String.format("Exception <%s> must have cause.", e);
	}

	/** Predicate: Exception <%s> must NOThave cause. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean noCause(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return !(e.getCause() != null);
	}

	/** "Special" predicate: Exception <%s> must NOThave cause. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String noCause$(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return !(e.getCause() != null) ? null : String.format("Exception <%s> must NOThave cause.", e);
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

	/** Predicate: Exception <%s> must NOThave message starting with <'%s>'. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean msgNotStartWith(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return !(e.getMessage() != null && e.getMessage().startsWith(text));
	}

	/** "Special" predicate: Exception <%s> must NOThave message starting with <'%s>'. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String msgNotStartWith$(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return !(e.getMessage() != null && e.getMessage().startsWith(text)) ? null : String.format("Exception <%s> must NOThave message starting with <'%s>'.", e, text);
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

	/** Predicate: Exception <%s> must NOThave message containing <'%s>'. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean msgNotContain(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return !(e.getMessage() != null && e.getMessage().contains(text));
	}

	/** "Special" predicate: Exception <%s> must NOThave message containing <'%s>'. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String msgNotContain$(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return !(e.getMessage() != null && e.getMessage().contains(text)) ? null : String.format("Exception <%s> must NOThave message containing <'%s>'.", e, text);
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

	/** Predicate: Exception <%s> must NOThave message ending with <'%s>'. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static boolean msgNotEndWith(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return !(e.getMessage() != null && e.getMessage().endsWith(text));
	}

	/** "Special" predicate: Exception <%s> must NOThave message ending with <'%s>'. Available in {@link P}, {@link Has}, {@link Have}.*/
	public static String msgNotEndWith$(@Nonnull Throwable e, @Nonnull String text) {
		Null.nonNullArg(e, "e");
		Null.nonNullArg(text, "text");
		return !(e.getMessage() != null && e.getMessage().endsWith(text)) ? null : String.format("Exception <%s> must NOThave message ending with <'%s>'.", e, text);
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

	/** Predicate: Exception <%s> must NOThave suppressed other exceptions. Available in {@link P}, {@link Is}, {@link Has}, {@link Have}.*/
	public static boolean notSuppressing(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return !(e.getSuppressed().length > 0);
	}

	/** "Special" predicate: Exception <%s> must NOThave suppressed other exceptions. Available in {@link P}, {@link Is}, {@link Has}, {@link Have}.*/
	public static String notSuppressing$(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return !(e.getSuppressed().length > 0) ? null : String.format("Exception <%s> must NOThave suppressed other exceptions.", e);
	}

	// </editor-fold>

}
