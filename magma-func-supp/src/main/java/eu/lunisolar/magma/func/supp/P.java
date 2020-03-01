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
public final class P implements FluentSyntax {
	// <editor-fold desc="no instance">
	private P() {
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

	public static final boolean allNull(@Nullable Object... objects) {
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

	// <editor-fold desc="`String`">

	public static boolean length(@Nonnull CharSequence s, int size) {
		Null.nonNullArg(s, "s");
		return size == s.length();
	}

	public static boolean ofLength(@Nonnull CharSequence s, int size) {
		Null.nonNullArg(s, "s");
		return size == s.length();
	}

	public static boolean startWith(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return n.startsWith(a1);
	}

	public static boolean endWith(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return n.endsWith(a1);
	}

	public static boolean contain(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return n.contains(a1);
	}

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

	public static boolean and(boolean operand1, boolean operand2) {
		return operand1 && operand2;
	}

	public static boolean or(boolean operand1, boolean operand2) {
		return operand1 || operand2;
	}

	public static boolean xor(boolean operand1, boolean operand2) {
		return operand1 ^ operand2;
	}

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

	protected static boolean privately_ofLength(Object array, int i) {
		return Array.getLength(array) == i;
	}

	public static boolean length(@Nonnull boolean[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.privately_ofLength(array, i);
	}

	public static boolean ofLength(@Nonnull boolean[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.privately_ofLength(array, i);
	}

	public static boolean empty(@Nonnull boolean[] array) {
		return ofLength(array, 0);
	}

	public static boolean nullOrEmpty(@Nullable boolean[] array) {
		return Null(array) || empty(array);
	}

	public static boolean singleton(@Nonnull boolean[] array) {
		return ofLength(array, 1);
	}

	public static boolean length(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.privately_ofLength(array, i);
	}

	public static boolean ofLength(@Nonnull byte[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.privately_ofLength(array, i);
	}

	public static boolean empty(@Nonnull byte[] array) {
		return ofLength(array, 0);
	}

	public static boolean nullOrEmpty(@Nullable byte[] array) {
		return Null(array) || empty(array);
	}

	public static boolean singleton(@Nonnull byte[] array) {
		return ofLength(array, 1);
	}

	public static boolean length(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.privately_ofLength(array, i);
	}

	public static boolean ofLength(@Nonnull double[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.privately_ofLength(array, i);
	}

	public static boolean empty(@Nonnull double[] array) {
		return ofLength(array, 0);
	}

	public static boolean nullOrEmpty(@Nullable double[] array) {
		return Null(array) || empty(array);
	}

	public static boolean singleton(@Nonnull double[] array) {
		return ofLength(array, 1);
	}

	public static boolean length(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.privately_ofLength(array, i);
	}

	public static boolean ofLength(@Nonnull char[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.privately_ofLength(array, i);
	}

	public static boolean empty(@Nonnull char[] array) {
		return ofLength(array, 0);
	}

	public static boolean nullOrEmpty(@Nullable char[] array) {
		return Null(array) || empty(array);
	}

	public static boolean singleton(@Nonnull char[] array) {
		return ofLength(array, 1);
	}

	public static boolean length(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.privately_ofLength(array, i);
	}

	public static boolean ofLength(@Nonnull short[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.privately_ofLength(array, i);
	}

	public static boolean empty(@Nonnull short[] array) {
		return ofLength(array, 0);
	}

	public static boolean nullOrEmpty(@Nullable short[] array) {
		return Null(array) || empty(array);
	}

	public static boolean singleton(@Nonnull short[] array) {
		return ofLength(array, 1);
	}

	public static boolean length(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.privately_ofLength(array, i);
	}

	public static boolean ofLength(@Nonnull float[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.privately_ofLength(array, i);
	}

	public static boolean empty(@Nonnull float[] array) {
		return ofLength(array, 0);
	}

	public static boolean nullOrEmpty(@Nullable float[] array) {
		return Null(array) || empty(array);
	}

	public static boolean singleton(@Nonnull float[] array) {
		return ofLength(array, 1);
	}

	public static boolean length(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.privately_ofLength(array, i);
	}

	public static boolean ofLength(@Nonnull int[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.privately_ofLength(array, i);
	}

	public static boolean empty(@Nonnull int[] array) {
		return ofLength(array, 0);
	}

	public static boolean nullOrEmpty(@Nullable int[] array) {
		return Null(array) || empty(array);
	}

	public static boolean singleton(@Nonnull int[] array) {
		return ofLength(array, 1);
	}

	public static boolean length(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.privately_ofLength(array, i);
	}

	public static boolean ofLength(@Nonnull long[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.privately_ofLength(array, i);
	}

	public static boolean empty(@Nonnull long[] array) {
		return ofLength(array, 0);
	}

	public static boolean nullOrEmpty(@Nullable long[] array) {
		return Null(array) || empty(array);
	}

	public static boolean singleton(@Nonnull long[] array) {
		return ofLength(array, 1);
	}

	public static <T> boolean length(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.privately_ofLength(array, i);
	}

	public static <T> boolean ofLength(@Nonnull T[] array, int i) {
		Null.nonNullArg(array, "array");
		return P.privately_ofLength(array, i);
	}

	public static <T> boolean empty(@Nonnull T[] array) {
		return ofLength(array, 0);
	}

	public static <T> boolean nullOrEmpty(@Nullable T[] array) {
		return Null(array) || empty(array);
	}

	public static <T> boolean singleton(@Nonnull T[] array) {
		return ofLength(array, 1);
	}

	// </editor-fold>

	// <editor-fold desc="collections">

	public static <T> boolean contain(@Nonnull Collection<T> collection, T element) {
		Null.nonNullArg(collection, "collection");
		return collection.contains(element);
	}

	public static <K> boolean containKey(@Nonnull Map<K, ?> map, K key) {
		Null.nonNullArg(map, "map");
		return map.containsKey(key);
	}

	public static <T> boolean size(@Nonnull Collection<T> collection, int i) {
		Null.nonNullArg(collection, "collection");
		return collection.size() == i;
	}

	public static <K, V> boolean size(@Nonnull Map<K, V> map, int i) {
		Null.nonNullArg(map, "map");
		return map.size() == i;
	}

	public static <T> boolean ofSize(@Nonnull Collection<T> collection, int i) {
		Null.nonNullArg(collection, "collection");
		return collection.size() == i;
	}

	public static <K, V> boolean ofSize(@Nonnull Map<K, V> map, int i) {
		Null.nonNullArg(map, "map");
		return map.size() == i;
	}

	public static <T> boolean partOf(T element, @Nonnull Collection<T> collection) {
		Null.nonNullArg(collection, "collection");
		return collection.contains(element);
	}

	public static <K> boolean aKeyIn(K key, @Nonnull Map<K, ?> map) {
		Null.nonNullArg(map, "map");
		return map.containsKey(key);
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

	// <editor-fold desc="object derivatives">

	public static <T> boolean instanceOf(T object, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return clazz.isInstance(object);
	}

	public static boolean assignableFrom(Class<?> specialization, Class<?> clazz) {
		Null.nonNullArg(clazz, "clazz");
		return clazz.isAssignableFrom(specialization);
	}

	// </editor-fold>

	// <editor-fold desc="Throwables">

	public static boolean runtime(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return instanceOf(e, RuntimeException.class);
	}

	public static boolean notRuntime(@Nonnull Throwable e) {
		Null.nonNullArg(e, "e");
		return !instanceOf(e, RuntimeException.class);
	}

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

	// <editor-fold desc="have/has">

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveBool(@Nonnull LPredicate<K> extractor, @Nonnull LLogicalOperator operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.apply(extractor.test(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasBool(@Nonnull LPredicate<K> extractor, @Nonnull LLogicalOperator operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.apply(extractor.test(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveBool(@Nonnull LPredicate<K> extractor, boolean a2, @Nonnull LLogicalBinaryOperator operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.apply(extractor.test(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveBool(@Nonnull LPredicate<K> extractor, @Nonnull LLogicalBinaryOperator operator, boolean a2) {
		return haveBool(extractor, a2, operator);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasBool(@Nonnull LPredicate<K> extractor, boolean a2, @Nonnull LLogicalBinaryOperator operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.apply(extractor.test(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasBool(@Nonnull LPredicate<K> extractor, @Nonnull LLogicalBinaryOperator operator, boolean a2) {
		return hasBool(extractor, a2, operator);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveBool(@Nonnull LPredicate<K> extractor, boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.apply(extractor.test(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveBool(@Nonnull LPredicate<K> extractor, @Nonnull LLogicalTernaryOperator operator, boolean a2, boolean a3) {
		return haveBool(extractor, a2, a3, operator);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasBool(@Nonnull LPredicate<K> extractor, boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.apply(extractor.test(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasBool(@Nonnull LPredicate<K> extractor, @Nonnull LLogicalTernaryOperator operator, boolean a2, boolean a3) {
		return hasBool(extractor, a2, a3, operator);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveBool$Int(@Nonnull LPredicate<K> extractor, int v, @Nonnull LBoolIntPredicate operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.test(extractor.test(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveBool$Int(@Nonnull LPredicate<K> extractor, @Nonnull LBoolIntPredicate operator, int v) {
		return haveBool$Int(extractor, v, operator);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasBool$Int(@Nonnull LPredicate<K> extractor, int v, @Nonnull LBoolIntPredicate operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.test(extractor.test(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasBool$Int(@Nonnull LPredicate<K> extractor, @Nonnull LBoolIntPredicate operator, int v) {
		return hasBool$Int(extractor, v, operator);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LPredicate<K> haveBool(@Nonnull LPredicate<K> extractor, V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.testBoolObj(extractor.test(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LPredicate<K> haveBool(@Nonnull LPredicate<K> extractor, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, V v) {
		return haveBool(extractor, v, operator);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LPredicate<K> hasBool(@Nonnull LPredicate<K> extractor, V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.testBoolObj(extractor.test(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LPredicate<K> hasBool(@Nonnull LPredicate<K> extractor, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, V v) {
		return hasBool(extractor, v, operator);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LPredicate<K> haveBool$WithBool(@Nonnull LPredicate<K> extractor, V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.test(with1, extractor.test(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LPredicate<K> haveBool$WithBool(@Nonnull LPredicate<K> extractor, @Nonnull LObjBoolPredicate<? super V1> operator, V1 with1) {
		return haveBool$WithBool(extractor, with1, operator);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LPredicate<K> hasBool$WithBool(@Nonnull LPredicate<K> extractor, V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.test(with1, extractor.test(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LPredicate<K> hasBool$WithBool(@Nonnull LPredicate<K> extractor, @Nonnull LObjBoolPredicate<? super V1> operator, V1 with1) {
		return hasBool$WithBool(extractor, with1, operator);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveBool$With(@Nonnull LPredicate<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.test(with1, with2, extractor.test(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveBool$With(@Nonnull LPredicate<K> extractor, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, V1 with1, V2 with2) {
		return haveBool$With(extractor, with1, with2, operator);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LPredicate<K> hasBool$With(@Nonnull LPredicate<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.test(with1, with2, extractor.test(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LPredicate<K> hasBool$With(@Nonnull LPredicate<K> extractor, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, V1 with1, V2 with2) {
		return hasBool$With(extractor, with1, with2, operator);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, @Nonnull LPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> has(@Nonnull LFunction<K, T> extractor, @Nonnull LPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> uniHave(@Nonnull LFunction<K, T> extractor, T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> uniHave(@Nonnull LFunction<K, T> extractor, @Nonnull LBiPredicate<? super T, ? super T> predicate, T a2) {
		return uniHave(extractor, a2, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> uniHas(@Nonnull LFunction<K, T> extractor, T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> uniHas(@Nonnull LFunction<K, T> extractor, @Nonnull LBiPredicate<? super T, ? super T> predicate, T a2) {
		return uniHas(extractor, a2, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> uniHave(@Nonnull LFunction<K, T> extractor, T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> uniHave(@Nonnull LFunction<K, T> extractor, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3) {
		return uniHave(extractor, a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> uniHas(@Nonnull LFunction<K, T> extractor, T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> uniHas(@Nonnull LFunction<K, T> extractor, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3) {
		return uniHas(extractor, a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> uniHave(@Nonnull LFunction<K, T> extractor, T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2, a3, a4);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> uniHave(@Nonnull LFunction<K, T> extractor, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4) {
		return uniHave(extractor, a2, a3, a4, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> uniHas(@Nonnull LFunction<K, T> extractor, T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2, a3, a4);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> uniHas(@Nonnull LFunction<K, T> extractor, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4) {
		return uniHas(extractor, a2, a3, a4, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> have$Bool(@Nonnull LFunction<K, T> extractor, boolean v, @Nonnull LObjBoolPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> have$Bool(@Nonnull LFunction<K, T> extractor, @Nonnull LObjBoolPredicate<? super T> predicate, boolean v) {
		return have$Bool(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> has$Bool(@Nonnull LFunction<K, T> extractor, boolean v, @Nonnull LObjBoolPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> has$Bool(@Nonnull LFunction<K, T> extractor, @Nonnull LObjBoolPredicate<? super T> predicate, boolean v) {
		return has$Bool(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> have$Byte(@Nonnull LFunction<K, T> extractor, byte v, @Nonnull LObjBytePredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> have$Byte(@Nonnull LFunction<K, T> extractor, @Nonnull LObjBytePredicate<? super T> predicate, byte v) {
		return have$Byte(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> has$Byte(@Nonnull LFunction<K, T> extractor, byte v, @Nonnull LObjBytePredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> has$Byte(@Nonnull LFunction<K, T> extractor, @Nonnull LObjBytePredicate<? super T> predicate, byte v) {
		return has$Byte(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> have$Dbl(@Nonnull LFunction<K, T> extractor, double v, @Nonnull LObjDblPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> have$Dbl(@Nonnull LFunction<K, T> extractor, @Nonnull LObjDblPredicate<? super T> predicate, double v) {
		return have$Dbl(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> has$Dbl(@Nonnull LFunction<K, T> extractor, double v, @Nonnull LObjDblPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> has$Dbl(@Nonnull LFunction<K, T> extractor, @Nonnull LObjDblPredicate<? super T> predicate, double v) {
		return has$Dbl(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> have$Char(@Nonnull LFunction<K, T> extractor, char v, @Nonnull LObjCharPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> have$Char(@Nonnull LFunction<K, T> extractor, @Nonnull LObjCharPredicate<? super T> predicate, char v) {
		return have$Char(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> has$Char(@Nonnull LFunction<K, T> extractor, char v, @Nonnull LObjCharPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> has$Char(@Nonnull LFunction<K, T> extractor, @Nonnull LObjCharPredicate<? super T> predicate, char v) {
		return has$Char(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> have$Srt(@Nonnull LFunction<K, T> extractor, short v, @Nonnull LObjSrtPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> have$Srt(@Nonnull LFunction<K, T> extractor, @Nonnull LObjSrtPredicate<? super T> predicate, short v) {
		return have$Srt(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> has$Srt(@Nonnull LFunction<K, T> extractor, short v, @Nonnull LObjSrtPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> has$Srt(@Nonnull LFunction<K, T> extractor, @Nonnull LObjSrtPredicate<? super T> predicate, short v) {
		return has$Srt(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> have$Flt(@Nonnull LFunction<K, T> extractor, float v, @Nonnull LObjFltPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> have$Flt(@Nonnull LFunction<K, T> extractor, @Nonnull LObjFltPredicate<? super T> predicate, float v) {
		return have$Flt(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> has$Flt(@Nonnull LFunction<K, T> extractor, float v, @Nonnull LObjFltPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> has$Flt(@Nonnull LFunction<K, T> extractor, @Nonnull LObjFltPredicate<? super T> predicate, float v) {
		return has$Flt(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> have$Int(@Nonnull LFunction<K, T> extractor, int v, @Nonnull LObjIntPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> have$Int(@Nonnull LFunction<K, T> extractor, @Nonnull LObjIntPredicate<? super T> predicate, int v) {
		return have$Int(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> has$Int(@Nonnull LFunction<K, T> extractor, int v, @Nonnull LObjIntPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> has$Int(@Nonnull LFunction<K, T> extractor, @Nonnull LObjIntPredicate<? super T> predicate, int v) {
		return has$Int(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> have$Long(@Nonnull LFunction<K, T> extractor, long v, @Nonnull LObjLongPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> have$Long(@Nonnull LFunction<K, T> extractor, @Nonnull LObjLongPredicate<? super T> predicate, long v) {
		return have$Long(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> has$Long(@Nonnull LFunction<K, T> extractor, long v, @Nonnull LObjLongPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> has$Long(@Nonnull LFunction<K, T> extractor, @Nonnull LObjLongPredicate<? super T> predicate, long v) {
		return has$Long(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T, V> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, V v, @Nonnull LBiPredicate<? super T, ? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T, V> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, @Nonnull LBiPredicate<? super T, ? super V> predicate, V v) {
		return have(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T, V> LPredicate<K> has(@Nonnull LFunction<K, T> extractor, V v, @Nonnull LBiPredicate<? super T, ? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T, V> LPredicate<K> has(@Nonnull LFunction<K, T> extractor, @Nonnull LBiPredicate<? super T, ? super V> predicate, V v) {
		return has(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T, V2, V3> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T, V2, V3> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3) {
		return have(extractor, a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T, V2, V3> LPredicate<K> has(@Nonnull LFunction<K, T> extractor, V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T, V2, V3> LPredicate<K> has(@Nonnull LFunction<K, T> extractor, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3) {
		return has(extractor, a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T, V2, V3, V4> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2, a3, a4);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T, V2, V3, V4> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4) {
		return have(extractor, a2, a3, a4, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T, V2, V3, V4> LPredicate<K> has(@Nonnull LFunction<K, T> extractor, V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2, a3, a4);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T, V2, V3, V4> LPredicate<K> has(@Nonnull LFunction<K, T> extractor, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4) {
		return has(extractor, a2, a3, a4, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T, V1> LPredicate<K> have$With(@Nonnull LFunction<K, T> extractor, V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.apply(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T, V1> LPredicate<K> have$With(@Nonnull LFunction<K, T> extractor, @Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with1) {
		return have$With(extractor, with1, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T, V1> LPredicate<K> has$With(@Nonnull LFunction<K, T> extractor, V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.apply(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T, V1> LPredicate<K> has$With(@Nonnull LFunction<K, T> extractor, @Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with1) {
		return has$With(extractor, with1, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T, V1, V2> LPredicate<K> have$With(@Nonnull LFunction<K, T> extractor, V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.apply(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T, V1, V2> LPredicate<K> have$With(@Nonnull LFunction<K, T> extractor, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, V1 with1, V2 with2) {
		return have$With(extractor, with1, with2, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T, V1, V2> LPredicate<K> has$With(@Nonnull LFunction<K, T> extractor, V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.apply(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T, V1, V2> LPredicate<K> has$With(@Nonnull LFunction<K, T> extractor, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, V1 with1, V2 with2) {
		return has$With(extractor, with1, with2, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> uniHave$With(@Nonnull LFunction<K, T> extractor, T with, @Nonnull LBiPredicate<? super T, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with, extractor.apply(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> uniHave$With(@Nonnull LFunction<K, T> extractor, @Nonnull LBiPredicate<? super T, ? super T> predicate, T with) {
		return uniHave$With(extractor, with, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> uniHas$With(@Nonnull LFunction<K, T> extractor, T with, @Nonnull LBiPredicate<? super T, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with, extractor.apply(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> uniHas$With(@Nonnull LFunction<K, T> extractor, @Nonnull LBiPredicate<? super T, ? super T> predicate, T with) {
		return uniHas$With(extractor, with, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveByte(@Nonnull LToByteFunction<K> extractor, @Nonnull LBytePredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsByte(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasByte(@Nonnull LToByteFunction<K> extractor, @Nonnull LBytePredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsByte(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveByte(@Nonnull LToByteFunction<K> extractor, byte a2, @Nonnull LBiBytePredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsByte(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveByte(@Nonnull LToByteFunction<K> extractor, @Nonnull LBiBytePredicate predicate, byte a2) {
		return haveByte(extractor, a2, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasByte(@Nonnull LToByteFunction<K> extractor, byte a2, @Nonnull LBiBytePredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsByte(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasByte(@Nonnull LToByteFunction<K> extractor, @Nonnull LBiBytePredicate predicate, byte a2) {
		return hasByte(extractor, a2, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveByte(@Nonnull LToByteFunction<K> extractor, byte a2, byte a3, @Nonnull LTriBytePredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsByte(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveByte(@Nonnull LToByteFunction<K> extractor, @Nonnull LTriBytePredicate predicate, byte a2, byte a3) {
		return haveByte(extractor, a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasByte(@Nonnull LToByteFunction<K> extractor, byte a2, byte a3, @Nonnull LTriBytePredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsByte(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasByte(@Nonnull LToByteFunction<K> extractor, @Nonnull LTriBytePredicate predicate, byte a2, byte a3) {
		return hasByte(extractor, a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveByte$Int(@Nonnull LToByteFunction<K> extractor, int v, @Nonnull LByteIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsByte(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveByte$Int(@Nonnull LToByteFunction<K> extractor, @Nonnull LByteIntPredicate predicate, int v) {
		return haveByte$Int(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasByte$Int(@Nonnull LToByteFunction<K> extractor, int v, @Nonnull LByteIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsByte(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasByte$Int(@Nonnull LToByteFunction<K> extractor, @Nonnull LByteIntPredicate predicate, int v) {
		return hasByte$Int(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LPredicate<K> haveByte(@Nonnull LToByteFunction<K> extractor, V v, @Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testByteObj(extractor.applyAsByte(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LPredicate<K> haveByte(@Nonnull LToByteFunction<K> extractor, @Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate, V v) {
		return haveByte(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LPredicate<K> hasByte(@Nonnull LToByteFunction<K> extractor, V v, @Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testByteObj(extractor.applyAsByte(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LPredicate<K> hasByte(@Nonnull LToByteFunction<K> extractor, @Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate, V v) {
		return hasByte(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LPredicate<K> haveByte$WithByte(@Nonnull LToByteFunction<K> extractor, V1 with1, @Nonnull LObjBytePredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsByte(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LPredicate<K> haveByte$WithByte(@Nonnull LToByteFunction<K> extractor, @Nonnull LObjBytePredicate<? super V1> predicate, V1 with1) {
		return haveByte$WithByte(extractor, with1, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LPredicate<K> hasByte$WithByte(@Nonnull LToByteFunction<K> extractor, V1 with1, @Nonnull LObjBytePredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsByte(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LPredicate<K> hasByte$WithByte(@Nonnull LToByteFunction<K> extractor, @Nonnull LObjBytePredicate<? super V1> predicate, V1 with1) {
		return hasByte$WithByte(extractor, with1, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveByte$With(@Nonnull LToByteFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjBytePredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsByte(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveByte$With(@Nonnull LToByteFunction<K> extractor, @Nonnull LBiObjBytePredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return haveByte$With(extractor, with1, with2, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LPredicate<K> hasByte$With(@Nonnull LToByteFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjBytePredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsByte(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LPredicate<K> hasByte$With(@Nonnull LToByteFunction<K> extractor, @Nonnull LBiObjBytePredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return hasByte$With(extractor, with1, with2, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveDbl(@Nonnull LToDblFunction<K> extractor, @Nonnull LDblPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsDbl(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasDbl(@Nonnull LToDblFunction<K> extractor, @Nonnull LDblPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsDbl(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveDbl(@Nonnull LToDblFunction<K> extractor, double a2, @Nonnull LBiDblPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsDbl(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveDbl(@Nonnull LToDblFunction<K> extractor, @Nonnull LBiDblPredicate predicate, double a2) {
		return haveDbl(extractor, a2, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasDbl(@Nonnull LToDblFunction<K> extractor, double a2, @Nonnull LBiDblPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsDbl(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasDbl(@Nonnull LToDblFunction<K> extractor, @Nonnull LBiDblPredicate predicate, double a2) {
		return hasDbl(extractor, a2, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveDbl(@Nonnull LToDblFunction<K> extractor, double a2, double a3, @Nonnull LTriDblPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsDbl(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveDbl(@Nonnull LToDblFunction<K> extractor, @Nonnull LTriDblPredicate predicate, double a2, double a3) {
		return haveDbl(extractor, a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasDbl(@Nonnull LToDblFunction<K> extractor, double a2, double a3, @Nonnull LTriDblPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsDbl(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasDbl(@Nonnull LToDblFunction<K> extractor, @Nonnull LTriDblPredicate predicate, double a2, double a3) {
		return hasDbl(extractor, a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveDbl$Int(@Nonnull LToDblFunction<K> extractor, int v, @Nonnull LDblIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsDbl(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveDbl$Int(@Nonnull LToDblFunction<K> extractor, @Nonnull LDblIntPredicate predicate, int v) {
		return haveDbl$Int(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasDbl$Int(@Nonnull LToDblFunction<K> extractor, int v, @Nonnull LDblIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsDbl(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasDbl$Int(@Nonnull LToDblFunction<K> extractor, @Nonnull LDblIntPredicate predicate, int v) {
		return hasDbl$Int(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LPredicate<K> haveDbl(@Nonnull LToDblFunction<K> extractor, V v, @Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testDblObj(extractor.applyAsDbl(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LPredicate<K> haveDbl(@Nonnull LToDblFunction<K> extractor, @Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate, V v) {
		return haveDbl(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LPredicate<K> hasDbl(@Nonnull LToDblFunction<K> extractor, V v, @Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testDblObj(extractor.applyAsDbl(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LPredicate<K> hasDbl(@Nonnull LToDblFunction<K> extractor, @Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate, V v) {
		return hasDbl(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LPredicate<K> haveDbl$WithDbl(@Nonnull LToDblFunction<K> extractor, V1 with1, @Nonnull LObjDblPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsDbl(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LPredicate<K> haveDbl$WithDbl(@Nonnull LToDblFunction<K> extractor, @Nonnull LObjDblPredicate<? super V1> predicate, V1 with1) {
		return haveDbl$WithDbl(extractor, with1, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LPredicate<K> hasDbl$WithDbl(@Nonnull LToDblFunction<K> extractor, V1 with1, @Nonnull LObjDblPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsDbl(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LPredicate<K> hasDbl$WithDbl(@Nonnull LToDblFunction<K> extractor, @Nonnull LObjDblPredicate<? super V1> predicate, V1 with1) {
		return hasDbl$WithDbl(extractor, with1, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveDbl$With(@Nonnull LToDblFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjDblPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsDbl(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveDbl$With(@Nonnull LToDblFunction<K> extractor, @Nonnull LBiObjDblPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return haveDbl$With(extractor, with1, with2, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LPredicate<K> hasDbl$With(@Nonnull LToDblFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjDblPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsDbl(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LPredicate<K> hasDbl$With(@Nonnull LToDblFunction<K> extractor, @Nonnull LBiObjDblPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return hasDbl$With(extractor, with1, with2, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveChar(@Nonnull LToCharFunction<K> extractor, @Nonnull LCharPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsChar(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasChar(@Nonnull LToCharFunction<K> extractor, @Nonnull LCharPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsChar(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveChar(@Nonnull LToCharFunction<K> extractor, char a2, @Nonnull LBiCharPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsChar(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveChar(@Nonnull LToCharFunction<K> extractor, @Nonnull LBiCharPredicate predicate, char a2) {
		return haveChar(extractor, a2, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasChar(@Nonnull LToCharFunction<K> extractor, char a2, @Nonnull LBiCharPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsChar(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasChar(@Nonnull LToCharFunction<K> extractor, @Nonnull LBiCharPredicate predicate, char a2) {
		return hasChar(extractor, a2, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveChar(@Nonnull LToCharFunction<K> extractor, char a2, char a3, @Nonnull LTriCharPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsChar(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveChar(@Nonnull LToCharFunction<K> extractor, @Nonnull LTriCharPredicate predicate, char a2, char a3) {
		return haveChar(extractor, a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasChar(@Nonnull LToCharFunction<K> extractor, char a2, char a3, @Nonnull LTriCharPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsChar(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasChar(@Nonnull LToCharFunction<K> extractor, @Nonnull LTriCharPredicate predicate, char a2, char a3) {
		return hasChar(extractor, a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveChar$Int(@Nonnull LToCharFunction<K> extractor, int v, @Nonnull LCharIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsChar(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveChar$Int(@Nonnull LToCharFunction<K> extractor, @Nonnull LCharIntPredicate predicate, int v) {
		return haveChar$Int(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasChar$Int(@Nonnull LToCharFunction<K> extractor, int v, @Nonnull LCharIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsChar(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasChar$Int(@Nonnull LToCharFunction<K> extractor, @Nonnull LCharIntPredicate predicate, int v) {
		return hasChar$Int(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LPredicate<K> haveChar(@Nonnull LToCharFunction<K> extractor, V v, @Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testCharObj(extractor.applyAsChar(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LPredicate<K> haveChar(@Nonnull LToCharFunction<K> extractor, @Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate, V v) {
		return haveChar(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LPredicate<K> hasChar(@Nonnull LToCharFunction<K> extractor, V v, @Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testCharObj(extractor.applyAsChar(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LPredicate<K> hasChar(@Nonnull LToCharFunction<K> extractor, @Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate, V v) {
		return hasChar(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LPredicate<K> haveChar$WithChar(@Nonnull LToCharFunction<K> extractor, V1 with1, @Nonnull LObjCharPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsChar(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LPredicate<K> haveChar$WithChar(@Nonnull LToCharFunction<K> extractor, @Nonnull LObjCharPredicate<? super V1> predicate, V1 with1) {
		return haveChar$WithChar(extractor, with1, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LPredicate<K> hasChar$WithChar(@Nonnull LToCharFunction<K> extractor, V1 with1, @Nonnull LObjCharPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsChar(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LPredicate<K> hasChar$WithChar(@Nonnull LToCharFunction<K> extractor, @Nonnull LObjCharPredicate<? super V1> predicate, V1 with1) {
		return hasChar$WithChar(extractor, with1, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveChar$With(@Nonnull LToCharFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjCharPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsChar(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveChar$With(@Nonnull LToCharFunction<K> extractor, @Nonnull LBiObjCharPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return haveChar$With(extractor, with1, with2, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LPredicate<K> hasChar$With(@Nonnull LToCharFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjCharPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsChar(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LPredicate<K> hasChar$With(@Nonnull LToCharFunction<K> extractor, @Nonnull LBiObjCharPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return hasChar$With(extractor, with1, with2, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveSrt(@Nonnull LToSrtFunction<K> extractor, @Nonnull LSrtPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsSrt(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasSrt(@Nonnull LToSrtFunction<K> extractor, @Nonnull LSrtPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsSrt(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveSrt(@Nonnull LToSrtFunction<K> extractor, short a2, @Nonnull LBiSrtPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsSrt(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveSrt(@Nonnull LToSrtFunction<K> extractor, @Nonnull LBiSrtPredicate predicate, short a2) {
		return haveSrt(extractor, a2, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasSrt(@Nonnull LToSrtFunction<K> extractor, short a2, @Nonnull LBiSrtPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsSrt(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasSrt(@Nonnull LToSrtFunction<K> extractor, @Nonnull LBiSrtPredicate predicate, short a2) {
		return hasSrt(extractor, a2, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveSrt(@Nonnull LToSrtFunction<K> extractor, short a2, short a3, @Nonnull LTriSrtPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsSrt(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveSrt(@Nonnull LToSrtFunction<K> extractor, @Nonnull LTriSrtPredicate predicate, short a2, short a3) {
		return haveSrt(extractor, a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasSrt(@Nonnull LToSrtFunction<K> extractor, short a2, short a3, @Nonnull LTriSrtPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsSrt(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasSrt(@Nonnull LToSrtFunction<K> extractor, @Nonnull LTriSrtPredicate predicate, short a2, short a3) {
		return hasSrt(extractor, a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveSrt$Int(@Nonnull LToSrtFunction<K> extractor, int v, @Nonnull LSrtIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsSrt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveSrt$Int(@Nonnull LToSrtFunction<K> extractor, @Nonnull LSrtIntPredicate predicate, int v) {
		return haveSrt$Int(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasSrt$Int(@Nonnull LToSrtFunction<K> extractor, int v, @Nonnull LSrtIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsSrt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasSrt$Int(@Nonnull LToSrtFunction<K> extractor, @Nonnull LSrtIntPredicate predicate, int v) {
		return hasSrt$Int(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LPredicate<K> haveSrt(@Nonnull LToSrtFunction<K> extractor, V v, @Nonnull LObjSrtPredicate.LSrtObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testSrtObj(extractor.applyAsSrt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LPredicate<K> haveSrt(@Nonnull LToSrtFunction<K> extractor, @Nonnull LObjSrtPredicate.LSrtObjPred<? super V> predicate, V v) {
		return haveSrt(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LPredicate<K> hasSrt(@Nonnull LToSrtFunction<K> extractor, V v, @Nonnull LObjSrtPredicate.LSrtObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testSrtObj(extractor.applyAsSrt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LPredicate<K> hasSrt(@Nonnull LToSrtFunction<K> extractor, @Nonnull LObjSrtPredicate.LSrtObjPred<? super V> predicate, V v) {
		return hasSrt(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LPredicate<K> haveSrt$WithSrt(@Nonnull LToSrtFunction<K> extractor, V1 with1, @Nonnull LObjSrtPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsSrt(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LPredicate<K> haveSrt$WithSrt(@Nonnull LToSrtFunction<K> extractor, @Nonnull LObjSrtPredicate<? super V1> predicate, V1 with1) {
		return haveSrt$WithSrt(extractor, with1, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LPredicate<K> hasSrt$WithSrt(@Nonnull LToSrtFunction<K> extractor, V1 with1, @Nonnull LObjSrtPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsSrt(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LPredicate<K> hasSrt$WithSrt(@Nonnull LToSrtFunction<K> extractor, @Nonnull LObjSrtPredicate<? super V1> predicate, V1 with1) {
		return hasSrt$WithSrt(extractor, with1, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveSrt$With(@Nonnull LToSrtFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjSrtPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsSrt(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveSrt$With(@Nonnull LToSrtFunction<K> extractor, @Nonnull LBiObjSrtPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return haveSrt$With(extractor, with1, with2, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LPredicate<K> hasSrt$With(@Nonnull LToSrtFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjSrtPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsSrt(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LPredicate<K> hasSrt$With(@Nonnull LToSrtFunction<K> extractor, @Nonnull LBiObjSrtPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return hasSrt$With(extractor, with1, with2, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveFlt(@Nonnull LToFltFunction<K> extractor, @Nonnull LFltPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsFlt(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasFlt(@Nonnull LToFltFunction<K> extractor, @Nonnull LFltPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsFlt(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveFlt(@Nonnull LToFltFunction<K> extractor, float a2, @Nonnull LBiFltPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsFlt(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveFlt(@Nonnull LToFltFunction<K> extractor, @Nonnull LBiFltPredicate predicate, float a2) {
		return haveFlt(extractor, a2, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasFlt(@Nonnull LToFltFunction<K> extractor, float a2, @Nonnull LBiFltPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsFlt(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasFlt(@Nonnull LToFltFunction<K> extractor, @Nonnull LBiFltPredicate predicate, float a2) {
		return hasFlt(extractor, a2, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveFlt(@Nonnull LToFltFunction<K> extractor, float a2, float a3, @Nonnull LTriFltPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsFlt(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveFlt(@Nonnull LToFltFunction<K> extractor, @Nonnull LTriFltPredicate predicate, float a2, float a3) {
		return haveFlt(extractor, a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasFlt(@Nonnull LToFltFunction<K> extractor, float a2, float a3, @Nonnull LTriFltPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsFlt(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasFlt(@Nonnull LToFltFunction<K> extractor, @Nonnull LTriFltPredicate predicate, float a2, float a3) {
		return hasFlt(extractor, a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveFlt$Int(@Nonnull LToFltFunction<K> extractor, int v, @Nonnull LFltIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsFlt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveFlt$Int(@Nonnull LToFltFunction<K> extractor, @Nonnull LFltIntPredicate predicate, int v) {
		return haveFlt$Int(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasFlt$Int(@Nonnull LToFltFunction<K> extractor, int v, @Nonnull LFltIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsFlt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasFlt$Int(@Nonnull LToFltFunction<K> extractor, @Nonnull LFltIntPredicate predicate, int v) {
		return hasFlt$Int(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LPredicate<K> haveFlt(@Nonnull LToFltFunction<K> extractor, V v, @Nonnull LObjFltPredicate.LFltObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testFltObj(extractor.applyAsFlt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LPredicate<K> haveFlt(@Nonnull LToFltFunction<K> extractor, @Nonnull LObjFltPredicate.LFltObjPred<? super V> predicate, V v) {
		return haveFlt(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LPredicate<K> hasFlt(@Nonnull LToFltFunction<K> extractor, V v, @Nonnull LObjFltPredicate.LFltObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testFltObj(extractor.applyAsFlt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LPredicate<K> hasFlt(@Nonnull LToFltFunction<K> extractor, @Nonnull LObjFltPredicate.LFltObjPred<? super V> predicate, V v) {
		return hasFlt(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LPredicate<K> haveFlt$WithFlt(@Nonnull LToFltFunction<K> extractor, V1 with1, @Nonnull LObjFltPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsFlt(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LPredicate<K> haveFlt$WithFlt(@Nonnull LToFltFunction<K> extractor, @Nonnull LObjFltPredicate<? super V1> predicate, V1 with1) {
		return haveFlt$WithFlt(extractor, with1, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LPredicate<K> hasFlt$WithFlt(@Nonnull LToFltFunction<K> extractor, V1 with1, @Nonnull LObjFltPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsFlt(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LPredicate<K> hasFlt$WithFlt(@Nonnull LToFltFunction<K> extractor, @Nonnull LObjFltPredicate<? super V1> predicate, V1 with1) {
		return hasFlt$WithFlt(extractor, with1, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveFlt$With(@Nonnull LToFltFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjFltPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsFlt(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveFlt$With(@Nonnull LToFltFunction<K> extractor, @Nonnull LBiObjFltPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return haveFlt$With(extractor, with1, with2, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LPredicate<K> hasFlt$With(@Nonnull LToFltFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjFltPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsFlt(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LPredicate<K> hasFlt$With(@Nonnull LToFltFunction<K> extractor, @Nonnull LBiObjFltPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return hasFlt$With(extractor, with1, with2, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveInt(@Nonnull LToIntFunction<K> extractor, @Nonnull LIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsInt(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasInt(@Nonnull LToIntFunction<K> extractor, @Nonnull LIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsInt(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveInt(@Nonnull LToIntFunction<K> extractor, int a2, @Nonnull LBiIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsInt(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveInt(@Nonnull LToIntFunction<K> extractor, @Nonnull LBiIntPredicate predicate, int a2) {
		return haveInt(extractor, a2, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasInt(@Nonnull LToIntFunction<K> extractor, int a2, @Nonnull LBiIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsInt(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasInt(@Nonnull LToIntFunction<K> extractor, @Nonnull LBiIntPredicate predicate, int a2) {
		return hasInt(extractor, a2, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveInt(@Nonnull LToIntFunction<K> extractor, int a2, int a3, @Nonnull LTriIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsInt(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveInt(@Nonnull LToIntFunction<K> extractor, @Nonnull LTriIntPredicate predicate, int a2, int a3) {
		return haveInt(extractor, a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasInt(@Nonnull LToIntFunction<K> extractor, int a2, int a3, @Nonnull LTriIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsInt(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasInt(@Nonnull LToIntFunction<K> extractor, @Nonnull LTriIntPredicate predicate, int a2, int a3) {
		return hasInt(extractor, a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveInt$Bool_(@Nonnull LToIntFunction<K> extractor, boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntBool(extractor.applyAsInt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveInt$Bool_(@Nonnull LToIntFunction<K> extractor, @Nonnull LBoolIntPredicate.LIntBoolPred predicate, boolean v) {
		return haveInt$Bool_(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasInt$Bool_(@Nonnull LToIntFunction<K> extractor, boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntBool(extractor.applyAsInt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasInt$Bool_(@Nonnull LToIntFunction<K> extractor, @Nonnull LBoolIntPredicate.LIntBoolPred predicate, boolean v) {
		return hasInt$Bool_(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveInt$Byte_(@Nonnull LToIntFunction<K> extractor, byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntByte(extractor.applyAsInt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveInt$Byte_(@Nonnull LToIntFunction<K> extractor, @Nonnull LByteIntPredicate.LIntBytePred predicate, byte v) {
		return haveInt$Byte_(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasInt$Byte_(@Nonnull LToIntFunction<K> extractor, byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntByte(extractor.applyAsInt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasInt$Byte_(@Nonnull LToIntFunction<K> extractor, @Nonnull LByteIntPredicate.LIntBytePred predicate, byte v) {
		return hasInt$Byte_(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveInt$Dbl_(@Nonnull LToIntFunction<K> extractor, double v, @Nonnull LDblIntPredicate.LIntDblPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntDbl(extractor.applyAsInt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveInt$Dbl_(@Nonnull LToIntFunction<K> extractor, @Nonnull LDblIntPredicate.LIntDblPred predicate, double v) {
		return haveInt$Dbl_(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasInt$Dbl_(@Nonnull LToIntFunction<K> extractor, double v, @Nonnull LDblIntPredicate.LIntDblPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntDbl(extractor.applyAsInt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasInt$Dbl_(@Nonnull LToIntFunction<K> extractor, @Nonnull LDblIntPredicate.LIntDblPred predicate, double v) {
		return hasInt$Dbl_(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveInt$Char_(@Nonnull LToIntFunction<K> extractor, char v, @Nonnull LCharIntPredicate.LIntCharPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntChar(extractor.applyAsInt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveInt$Char_(@Nonnull LToIntFunction<K> extractor, @Nonnull LCharIntPredicate.LIntCharPred predicate, char v) {
		return haveInt$Char_(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasInt$Char_(@Nonnull LToIntFunction<K> extractor, char v, @Nonnull LCharIntPredicate.LIntCharPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntChar(extractor.applyAsInt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasInt$Char_(@Nonnull LToIntFunction<K> extractor, @Nonnull LCharIntPredicate.LIntCharPred predicate, char v) {
		return hasInt$Char_(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveInt$Srt_(@Nonnull LToIntFunction<K> extractor, short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntSrt(extractor.applyAsInt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveInt$Srt_(@Nonnull LToIntFunction<K> extractor, @Nonnull LSrtIntPredicate.LIntSrtPred predicate, short v) {
		return haveInt$Srt_(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasInt$Srt_(@Nonnull LToIntFunction<K> extractor, short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntSrt(extractor.applyAsInt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasInt$Srt_(@Nonnull LToIntFunction<K> extractor, @Nonnull LSrtIntPredicate.LIntSrtPred predicate, short v) {
		return hasInt$Srt_(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveInt$Flt_(@Nonnull LToIntFunction<K> extractor, float v, @Nonnull LFltIntPredicate.LIntFltPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntFlt(extractor.applyAsInt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveInt$Flt_(@Nonnull LToIntFunction<K> extractor, @Nonnull LFltIntPredicate.LIntFltPred predicate, float v) {
		return haveInt$Flt_(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasInt$Flt_(@Nonnull LToIntFunction<K> extractor, float v, @Nonnull LFltIntPredicate.LIntFltPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntFlt(extractor.applyAsInt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasInt$Flt_(@Nonnull LToIntFunction<K> extractor, @Nonnull LFltIntPredicate.LIntFltPred predicate, float v) {
		return hasInt$Flt_(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveInt$Long_(@Nonnull LToIntFunction<K> extractor, long v, @Nonnull LLongIntPredicate.LIntLongPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntLong(extractor.applyAsInt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveInt$Long_(@Nonnull LToIntFunction<K> extractor, @Nonnull LLongIntPredicate.LIntLongPred predicate, long v) {
		return haveInt$Long_(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasInt$Long_(@Nonnull LToIntFunction<K> extractor, long v, @Nonnull LLongIntPredicate.LIntLongPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntLong(extractor.applyAsInt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasInt$Long_(@Nonnull LToIntFunction<K> extractor, @Nonnull LLongIntPredicate.LIntLongPred predicate, long v) {
		return hasInt$Long_(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LPredicate<K> haveInt(@Nonnull LToIntFunction<K> extractor, V v, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntObj(extractor.applyAsInt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LPredicate<K> haveInt(@Nonnull LToIntFunction<K> extractor, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, V v) {
		return haveInt(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LPredicate<K> hasInt(@Nonnull LToIntFunction<K> extractor, V v, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntObj(extractor.applyAsInt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LPredicate<K> hasInt(@Nonnull LToIntFunction<K> extractor, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, V v) {
		return hasInt(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LPredicate<K> haveInt$WithInt(@Nonnull LToIntFunction<K> extractor, V1 with1, @Nonnull LObjIntPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsInt(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LPredicate<K> haveInt$WithInt(@Nonnull LToIntFunction<K> extractor, @Nonnull LObjIntPredicate<? super V1> predicate, V1 with1) {
		return haveInt$WithInt(extractor, with1, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LPredicate<K> hasInt$WithInt(@Nonnull LToIntFunction<K> extractor, V1 with1, @Nonnull LObjIntPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsInt(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LPredicate<K> hasInt$WithInt(@Nonnull LToIntFunction<K> extractor, @Nonnull LObjIntPredicate<? super V1> predicate, V1 with1) {
		return hasInt$WithInt(extractor, with1, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveInt$With(@Nonnull LToIntFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsInt(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveInt$With(@Nonnull LToIntFunction<K> extractor, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return haveInt$With(extractor, with1, with2, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LPredicate<K> hasInt$With(@Nonnull LToIntFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsInt(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LPredicate<K> hasInt$With(@Nonnull LToIntFunction<K> extractor, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return hasInt$With(extractor, with1, with2, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveLong(@Nonnull LToLongFunction<K> extractor, @Nonnull LLongPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsLong(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasLong(@Nonnull LToLongFunction<K> extractor, @Nonnull LLongPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsLong(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveLong(@Nonnull LToLongFunction<K> extractor, long a2, @Nonnull LBiLongPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsLong(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveLong(@Nonnull LToLongFunction<K> extractor, @Nonnull LBiLongPredicate predicate, long a2) {
		return haveLong(extractor, a2, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasLong(@Nonnull LToLongFunction<K> extractor, long a2, @Nonnull LBiLongPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsLong(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasLong(@Nonnull LToLongFunction<K> extractor, @Nonnull LBiLongPredicate predicate, long a2) {
		return hasLong(extractor, a2, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveLong(@Nonnull LToLongFunction<K> extractor, long a2, long a3, @Nonnull LTriLongPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsLong(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveLong(@Nonnull LToLongFunction<K> extractor, @Nonnull LTriLongPredicate predicate, long a2, long a3) {
		return haveLong(extractor, a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasLong(@Nonnull LToLongFunction<K> extractor, long a2, long a3, @Nonnull LTriLongPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsLong(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasLong(@Nonnull LToLongFunction<K> extractor, @Nonnull LTriLongPredicate predicate, long a2, long a3) {
		return hasLong(extractor, a2, a3, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveLong$Int(@Nonnull LToLongFunction<K> extractor, int v, @Nonnull LLongIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsLong(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveLong$Int(@Nonnull LToLongFunction<K> extractor, @Nonnull LLongIntPredicate predicate, int v) {
		return haveLong$Int(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> hasLong$Int(@Nonnull LToLongFunction<K> extractor, int v, @Nonnull LLongIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsLong(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> hasLong$Int(@Nonnull LToLongFunction<K> extractor, @Nonnull LLongIntPredicate predicate, int v) {
		return hasLong$Int(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LPredicate<K> haveLong(@Nonnull LToLongFunction<K> extractor, V v, @Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testLongObj(extractor.applyAsLong(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LPredicate<K> haveLong(@Nonnull LToLongFunction<K> extractor, @Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, V v) {
		return haveLong(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LPredicate<K> hasLong(@Nonnull LToLongFunction<K> extractor, V v, @Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testLongObj(extractor.applyAsLong(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LPredicate<K> hasLong(@Nonnull LToLongFunction<K> extractor, @Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, V v) {
		return hasLong(extractor, v, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LPredicate<K> haveLong$WithLong(@Nonnull LToLongFunction<K> extractor, V1 with1, @Nonnull LObjLongPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsLong(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LPredicate<K> haveLong$WithLong(@Nonnull LToLongFunction<K> extractor, @Nonnull LObjLongPredicate<? super V1> predicate, V1 with1) {
		return haveLong$WithLong(extractor, with1, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LPredicate<K> hasLong$WithLong(@Nonnull LToLongFunction<K> extractor, V1 with1, @Nonnull LObjLongPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsLong(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LPredicate<K> hasLong$WithLong(@Nonnull LToLongFunction<K> extractor, @Nonnull LObjLongPredicate<? super V1> predicate, V1 with1) {
		return hasLong$WithLong(extractor, with1, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveLong$With(@Nonnull LToLongFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjLongPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsLong(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveLong$With(@Nonnull LToLongFunction<K> extractor, @Nonnull LBiObjLongPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return haveLong$With(extractor, with1, with2, predicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LPredicate<K> hasLong$With(@Nonnull LToLongFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjLongPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsLong(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LPredicate<K> hasLong$With(@Nonnull LToLongFunction<K> extractor, @Nonnull LBiObjLongPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return hasLong$With(extractor, with1, with2, predicate);
	}

	// </editor-fold>

}
