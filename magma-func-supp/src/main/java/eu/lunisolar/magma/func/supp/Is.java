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
import eu.lunisolar.magma.basics.fluent.FluentSyntax;

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
 * @see {@link P}, {@link Is}, {@link Does}, {@link Be}
 */
public final class Is implements FluentSyntax {
	// <editor-fold desc="no instance">
	private Is() {
	}
	// </editor-fold>

	// <editor-fold desc="Object">

	public static boolean same(@Nullable Object n, @Nullable Object other) {
		return n == other;
	}

	public static boolean Null(@Nullable Object n) {
		return n == null;
	}

	public static boolean notNull(@Nullable Object n) {
		return n != null;
	}

	public static final boolean noneNull(@Nullable Object... objects) {
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

	public static final boolean anyNull(@Nullable Object... objects) {
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

	// </editor-fold>

	// <editor-fold desc="String">

	public static boolean empty(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return n.isEmpty();
	}

	public static boolean blank(@Nonnull String n) {
		Null.nonNullArg(n, "n");
		return n.isBlank();
	}

	public static boolean nullOrEmpty(@Nullable String n) {
		return Null(n) || empty(n);
	}

	public static boolean nullOrBlank(@Nullable String n) {
		return Null(n) || blank(n);
	}

	// </editor-fold>

	// <editor-fold desc="and/or/xor">

	// </editor-fold>

	// <editor-fold desc="== <= >= < >">

	public static boolean equal(Object o, Object a1) {
		return Objects.equals(o, a1);
	}

	public static boolean notEqual(Object o, Object a1) {
		return !equal(o, a1);
	}

	public static boolean equal(byte o, byte a1) {
		return o == a1;
	}

	public static boolean notEqual(byte o, byte a1) {
		return o != a1;
	}

	/** Example use: throwIfNot((byte)0, P::gt, (byte)1, X::arg, "Argument n must be: m > %s", (byte)1); */
	public static boolean gt(byte n, byte a1) {
		return n > a1;
	}

	/** Example use: throwIfNot((byte)0, P::lt, (byte)1, X::arg, "Argument n must be: m < %s", (byte)1); */
	public static boolean lt(byte n, byte a1) {
		return n < a1;
	}

	/** Example use: throwIfNot((byte)0, P::gtEq, (byte)1, X::arg, "Argument n must be: m >= %s", (byte)1); */
	public static boolean gtEq(byte n, byte a1) {
		return n >= a1;
	}

	/** Example use: throwIfNot((byte)0, P::ltEq, (byte)1, X::arg, "Argument n must be: m <= %s", (byte)1); */
	public static boolean ltEq(byte n, byte a1) {
		return n <= a1;
	}

	public static boolean equal(short o, short a1) {
		return o == a1;
	}

	public static boolean notEqual(short o, short a1) {
		return o != a1;
	}

	/** Example use: throwIfNot((short)0, P::gt, (short)1, X::arg, "Argument n must be: m > %s", (short)1); */
	public static boolean gt(short n, short a1) {
		return n > a1;
	}

	/** Example use: throwIfNot((short)0, P::lt, (short)1, X::arg, "Argument n must be: m < %s", (short)1); */
	public static boolean lt(short n, short a1) {
		return n < a1;
	}

	/** Example use: throwIfNot((short)0, P::gtEq, (short)1, X::arg, "Argument n must be: m >= %s", (short)1); */
	public static boolean gtEq(short n, short a1) {
		return n >= a1;
	}

	/** Example use: throwIfNot((short)0, P::ltEq, (short)1, X::arg, "Argument n must be: m <= %s", (short)1); */
	public static boolean ltEq(short n, short a1) {
		return n <= a1;
	}

	public static boolean equal(int o, int a1) {
		return o == a1;
	}

	public static boolean notEqual(int o, int a1) {
		return o != a1;
	}

	/** Example use: throwIfNot(0, P::gt, 1, X::arg, "Argument n must be: m > %s", 1); */
	public static boolean gt(int n, int a1) {
		return n > a1;
	}

	/** Example use: throwIfNot(0, P::lt, 1, X::arg, "Argument n must be: m < %s", 1); */
	public static boolean lt(int n, int a1) {
		return n < a1;
	}

	/** Example use: throwIfNot(0, P::gtEq, 1, X::arg, "Argument n must be: m >= %s", 1); */
	public static boolean gtEq(int n, int a1) {
		return n >= a1;
	}

	/** Example use: throwIfNot(0, P::ltEq, 1, X::arg, "Argument n must be: m <= %s", 1); */
	public static boolean ltEq(int n, int a1) {
		return n <= a1;
	}

	public static boolean equal(long o, long a1) {
		return o == a1;
	}

	public static boolean notEqual(long o, long a1) {
		return o != a1;
	}

	/** Example use: throwIfNot(0L, P::gt, 1L, X::arg, "Argument n must be: m > %s", 1L); */
	public static boolean gt(long n, long a1) {
		return n > a1;
	}

	/** Example use: throwIfNot(0L, P::lt, 1L, X::arg, "Argument n must be: m < %s", 1L); */
	public static boolean lt(long n, long a1) {
		return n < a1;
	}

	/** Example use: throwIfNot(0L, P::gtEq, 1L, X::arg, "Argument n must be: m >= %s", 1L); */
	public static boolean gtEq(long n, long a1) {
		return n >= a1;
	}

	/** Example use: throwIfNot(0L, P::ltEq, 1L, X::arg, "Argument n must be: m <= %s", 1L); */
	public static boolean ltEq(long n, long a1) {
		return n <= a1;
	}

	public static boolean equal(float o, float a1) {
		return o == a1;
	}

	public static boolean notEqual(float o, float a1) {
		return o != a1;
	}

	/** Example use: throwIfNot(0f, P::gt, 1f, X::arg, "Argument n must be: m > %s", 1f); */
	public static boolean gt(float n, float a1) {
		return n > a1;
	}

	/** Example use: throwIfNot(0f, P::lt, 1f, X::arg, "Argument n must be: m < %s", 1f); */
	public static boolean lt(float n, float a1) {
		return n < a1;
	}

	/** Example use: throwIfNot(0f, P::gtEq, 1f, X::arg, "Argument n must be: m >= %s", 1f); */
	public static boolean gtEq(float n, float a1) {
		return n >= a1;
	}

	/** Example use: throwIfNot(0f, P::ltEq, 1f, X::arg, "Argument n must be: m <= %s", 1f); */
	public static boolean ltEq(float n, float a1) {
		return n <= a1;
	}

	public static boolean equal(double o, double a1) {
		return o == a1;
	}

	public static boolean notEqual(double o, double a1) {
		return o != a1;
	}

	/** Example use: throwIfNot(0d, P::gt, 1d, X::arg, "Argument n must be: m > %s", 1d); */
	public static boolean gt(double n, double a1) {
		return n > a1;
	}

	/** Example use: throwIfNot(0d, P::lt, 1d, X::arg, "Argument n must be: m < %s", 1d); */
	public static boolean lt(double n, double a1) {
		return n < a1;
	}

	/** Example use: throwIfNot(0d, P::gtEq, 1d, X::arg, "Argument n must be: m >= %s", 1d); */
	public static boolean gtEq(double n, double a1) {
		return n >= a1;
	}

	/** Example use: throwIfNot(0d, P::ltEq, 1d, X::arg, "Argument n must be: m <= %s", 1d); */
	public static boolean ltEq(double n, double a1) {
		return n <= a1;
	}

	public static boolean equal(char o, char a1) {
		return o == a1;
	}

	public static boolean notEqual(char o, char a1) {
		return o != a1;
	}

	/** Example use: throwIfNot('\u0000', P::gt, '\u0001', X::arg, "Argument n must be: m > %s", '\u0001'); */
	public static boolean gt(char n, char a1) {
		return n > a1;
	}

	/** Example use: throwIfNot('\u0000', P::lt, '\u0001', X::arg, "Argument n must be: m < %s", '\u0001'); */
	public static boolean lt(char n, char a1) {
		return n < a1;
	}

	/** Example use: throwIfNot('\u0000', P::gtEq, '\u0001', X::arg, "Argument n must be: m >= %s", '\u0001'); */
	public static boolean gtEq(char n, char a1) {
		return n >= a1;
	}

	/** Example use: throwIfNot('\u0000', P::ltEq, '\u0001', X::arg, "Argument n must be: m <= %s", '\u0001'); */
	public static boolean ltEq(char n, char a1) {
		return n <= a1;
	}

	/** Example use: throwIfNot((byte)0, P::between, (byte)0, (byte)1, X::arg, "Argument n must be: a1 < n < a2", (byte)0, (byte)1); */
	public static boolean between(byte n, byte a1, byte a2) {
		return a1 < n && n < a2;
	}

	/** Example use: throwIfNot((byte)0, P::inRange, (byte)0, (byte)1, X::arg, "Argument n must be: a1<= n <= a2", (byte)0, (byte)1); */
	public static boolean inRange(byte n, byte a1, byte a2) {
		return a1 <= n && n <= a2;
	}

	/** Example use: throwIfNot((short)0, P::between, (short)0, (short)1, X::arg, "Argument n must be: a1 < n < a2", (short)0, (short)1); */
	public static boolean between(short n, short a1, short a2) {
		return a1 < n && n < a2;
	}

	/** Example use: throwIfNot((short)0, P::inRange, (short)0, (short)1, X::arg, "Argument n must be: a1<= n <= a2", (short)0, (short)1); */
	public static boolean inRange(short n, short a1, short a2) {
		return a1 <= n && n <= a2;
	}

	/** Example use: throwIfNot(0, P::between, 0, 1, X::arg, "Argument n must be: a1 < n < a2", 0, 1); */
	public static boolean between(int n, int a1, int a2) {
		return a1 < n && n < a2;
	}

	/** Example use: throwIfNot(0, P::inRange, 0, 1, X::arg, "Argument n must be: a1<= n <= a2", 0, 1); */
	public static boolean inRange(int n, int a1, int a2) {
		return a1 <= n && n <= a2;
	}

	/** Example use: throwIfNot(0L, P::between, 0L, 1L, X::arg, "Argument n must be: a1 < n < a2", 0L, 1L); */
	public static boolean between(long n, long a1, long a2) {
		return a1 < n && n < a2;
	}

	/** Example use: throwIfNot(0L, P::inRange, 0L, 1L, X::arg, "Argument n must be: a1<= n <= a2", 0L, 1L); */
	public static boolean inRange(long n, long a1, long a2) {
		return a1 <= n && n <= a2;
	}

	/** Example use: throwIfNot(0f, P::between, 0f, 1f, X::arg, "Argument n must be: a1 < n < a2", 0f, 1f); */
	public static boolean between(float n, float a1, float a2) {
		return a1 < n && n < a2;
	}

	/** Example use: throwIfNot(0f, P::inRange, 0f, 1f, X::arg, "Argument n must be: a1<= n <= a2", 0f, 1f); */
	public static boolean inRange(float n, float a1, float a2) {
		return a1 <= n && n <= a2;
	}

	/** Example use: throwIfNot(0d, P::between, 0d, 1d, X::arg, "Argument n must be: a1 < n < a2", 0d, 1d); */
	public static boolean between(double n, double a1, double a2) {
		return a1 < n && n < a2;
	}

	/** Example use: throwIfNot(0d, P::inRange, 0d, 1d, X::arg, "Argument n must be: a1<= n <= a2", 0d, 1d); */
	public static boolean inRange(double n, double a1, double a2) {
		return a1 <= n && n <= a2;
	}

	/** Example use: throwIfNot('\u0000', P::between, '\u0000', '\u0001', X::arg, "Argument n must be: a1 < n < a2", '\u0000', '\u0001'); */
	public static boolean between(char n, char a1, char a2) {
		return a1 < n && n < a2;
	}

	/** Example use: throwIfNot('\u0000', P::inRange, '\u0000', '\u0001', X::arg, "Argument n must be: a1<= n <= a2", '\u0000', '\u0001'); */
	public static boolean inRange(char n, char a1, char a2) {
		return a1 <= n && n <= a2;
	}

	public static boolean True(boolean a1) {
		return LLogicalOperator.isTrue(a1);
	}

	public static boolean False(boolean a1) {
		return LLogicalOperator.isFalse(a1);
	}

	// </editor-fold>

	// <editor-fold desc="arrays">
	private static boolean privately_ofSize(Object array, int i) {
		return Array.getLength(array) == i;
	}

	public static boolean ofSize(@Nonnull boolean[] array, int i) {
		Null.nonNullArg(array, "array");
		return privately_ofSize(array, i);
	}

	public static boolean empty(@Nonnull boolean[] array) {
		return ofSize(array, 0);
	}

	public static boolean nullOrEmpty(@Nullable boolean[] array) {
		return Null(array) || empty(array);
	}

	public static boolean singleton(@Nonnull boolean[] array) {
		return ofSize(array, 1);
	}

	public static boolean ofSize(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return privately_ofSize(array, i);
	}

	public static boolean empty(@Nonnull byte[] array) {
		return ofSize(array, 0);
	}

	public static boolean nullOrEmpty(@Nullable byte[] array) {
		return Null(array) || empty(array);
	}

	public static boolean singleton(@Nonnull byte[] array) {
		return ofSize(array, 1);
	}

	public static boolean ofSize(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return privately_ofSize(array, i);
	}

	public static boolean empty(@Nonnull double[] array) {
		return ofSize(array, 0);
	}

	public static boolean nullOrEmpty(@Nullable double[] array) {
		return Null(array) || empty(array);
	}

	public static boolean singleton(@Nonnull double[] array) {
		return ofSize(array, 1);
	}

	public static boolean ofSize(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return privately_ofSize(array, i);
	}

	public static boolean empty(@Nonnull char[] array) {
		return ofSize(array, 0);
	}

	public static boolean nullOrEmpty(@Nullable char[] array) {
		return Null(array) || empty(array);
	}

	public static boolean singleton(@Nonnull char[] array) {
		return ofSize(array, 1);
	}

	public static boolean ofSize(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return privately_ofSize(array, i);
	}

	public static boolean empty(@Nonnull short[] array) {
		return ofSize(array, 0);
	}

	public static boolean nullOrEmpty(@Nullable short[] array) {
		return Null(array) || empty(array);
	}

	public static boolean singleton(@Nonnull short[] array) {
		return ofSize(array, 1);
	}

	public static boolean ofSize(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return privately_ofSize(array, i);
	}

	public static boolean empty(@Nonnull float[] array) {
		return ofSize(array, 0);
	}

	public static boolean nullOrEmpty(@Nullable float[] array) {
		return Null(array) || empty(array);
	}

	public static boolean singleton(@Nonnull float[] array) {
		return ofSize(array, 1);
	}

	public static boolean ofSize(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return privately_ofSize(array, i);
	}

	public static boolean empty(@Nonnull int[] array) {
		return ofSize(array, 0);
	}

	public static boolean nullOrEmpty(@Nullable int[] array) {
		return Null(array) || empty(array);
	}

	public static boolean singleton(@Nonnull int[] array) {
		return ofSize(array, 1);
	}

	public static boolean ofSize(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return privately_ofSize(array, i);
	}

	public static boolean empty(@Nonnull long[] array) {
		return ofSize(array, 0);
	}

	public static boolean nullOrEmpty(@Nullable long[] array) {
		return Null(array) || empty(array);
	}

	public static boolean singleton(@Nonnull long[] array) {
		return ofSize(array, 1);
	}

	public static <T> boolean ofSize(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return privately_ofSize(array, i);
	}

	public static <T> boolean empty(@Nonnull T[] array) {
		return ofSize(array, 0);
	}

	public static <T> boolean nullOrEmpty(@Nullable T[] array) {
		return Null(array) || empty(array);
	}

	public static <T> boolean singleton(@Nonnull T[] array) {
		return ofSize(array, 1);
	}

	// </editor-fold>

	// <editor-fold desc="collections">

	public static <T> boolean partOf(T element, @Nonnull Collection<T> collection) {
		Null.nonNullArg(collection, "collection");
		return collection.contains(element);
	}

	public static <K> boolean aKeyIn(K key, @Nonnull Map<K, ?> map) {
		Null.nonNullArg(map, "map");
		return map.containsKey(key);
	}

	public static <T> boolean ofSize(@Nonnull Collection<T> collection, int i) {
		Null.nonNullArg(collection, "collection");
		return collection.size() == i;
	}

	public static <T> boolean empty(@Nonnull Collection<T> collection) {
		return ofSize(collection, 0);
	}

	public static <T> boolean nullOrEmpty(@Nullable Collection<T> collection) {
		return Null(collection) || empty(collection);
	}

	public static <T> boolean singleton(@Nonnull Collection<T> collection) {
		return ofSize(collection, 1);
	}

	public static <K, V> boolean ofSize(@Nonnull Map<K, V> map, int i) {
		Null.nonNullArg(map, "map");
		return map.size() == i;
	}

	public static <K, V> boolean empty(@Nonnull Map<K, V> map) {
		return ofSize(map, 0);
	}

	public static <K, V> boolean nullOrEmpty(@Nullable Map<K, V> map) {
		return Null(map) || empty(map);
	}

	public static <K, V> boolean singleton(@Nonnull Map<K, V> map) {
		return ofSize(map, 1);
	}

	// </editor-fold>

	public static <T> boolean instanceOf(T object, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return clazz.isInstance(object);
	}

	public static boolean assignableFrom(Class<?> specialization, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return clazz.isAssignableFrom(specialization);
	}

	// </editor-fold>

}
