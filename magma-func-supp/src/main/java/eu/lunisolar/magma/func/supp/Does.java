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
public final class Does implements FluentSyntax {
	// <editor-fold desc="no instance">
	private Does() {
	}
	// </editor-fold>

	// <editor-fold desc="Object">

	// </editor-fold>

	// <editor-fold desc="`String`">

	/** Predicate: String <'%s'> must start with <'%s'>. Available in {@link P}, {@link Does}.*/
	public static boolean startWith(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return P.startWith(n, a1);
	}

	/** "Special" predicate: String <'%s'> must start with <'%s'>. Available in {@link P}, {@link Does}.*/
	public static @Nullable String startWithEx(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return P.startWithEx(n, a1);
	}
	/** Predicate: String <'%s'> must NOT start with <'%s'>. Available in {@link P}, {@link Does}.*/
	public static boolean notStartWith(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return P.notStartWith(n, a1);
	}

	/** "Special" predicate: String <'%s'> must NOT start with <'%s'>. Available in {@link P}, {@link Does}.*/
	public static @Nullable String notStartWithEx(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return P.notStartWithEx(n, a1);
	}

	/** Predicate: String <'%s'> must end with <'%s'>. Available in {@link P}, {@link Does}.*/
	public static boolean endWith(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return P.endWith(n, a1);
	}

	/** "Special" predicate: String <'%s'> must end with <'%s'>. Available in {@link P}, {@link Does}.*/
	public static @Nullable String endWithEx(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return P.endWithEx(n, a1);
	}
	/** Predicate: String <'%s'> must NOT end with <'%s'>. Available in {@link P}, {@link Does}.*/
	public static boolean notEndWith(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return P.notEndWith(n, a1);
	}

	/** "Special" predicate: String <'%s'> must NOT end with <'%s'>. Available in {@link P}, {@link Does}.*/
	public static @Nullable String notEndWithEx(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return P.notEndWithEx(n, a1);
	}

	/** Predicate: String <'%s'> must contain string <'%s'>. But does not. Available in {@link P}, {@link Does}.*/
	public static boolean contain(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return P.contain(n, a1);
	}

	/** "Special" predicate: String <'%s'> must contain string <'%s'>. But does not. Available in {@link P}, {@link Does}.*/
	public static @Nullable String containEx(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return P.containEx(n, a1);
	}
	/** Predicate: String <'%s'> must NOT contain string <'%s'>. But does not. Available in {@link P}, {@link Does}.*/
	public static boolean notContain(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return P.notContain(n, a1);
	}

	/** "Special" predicate: String <'%s'> must NOT contain string <'%s'>. But does not. Available in {@link P}, {@link Does}.*/
	public static @Nullable String notContainEx(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return P.notContainEx(n, a1);
	}

	// </editor-fold>

	// <editor-fold desc="and/or/xor">

	// </editor-fold>

	// <editor-fold desc="== <= >= < >">

	/** Predicate: <%s> must be equal to <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean equal(Object o1, Object o2) {
		return P.equal(o1, o2);
	}

	/** "Special" predicate: <%s> must be equal to <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static @Nullable String equalEx(Object o1, Object o2) {
		return P.equalEx(o1, o2);
	}
	/** Predicate: <%s> must NOT be equal to <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean notEqual(Object o1, Object o2) {
		return P.notEqual(o1, o2);
	}

	/** "Special" predicate: <%s> must NOT be equal to <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static @Nullable String notEqualEx(Object o1, Object o2) {
		return P.notEqualEx(o1, o2);
	}

	/** Predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean equal(byte n, byte a1) {
		return P.equal(n, a1);
	}

	/** "Special" predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static @Nullable String equalEx(byte n, byte a1) {
		return P.equalEx(n, a1);
	}
	/** Predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean notEqual(byte n, byte a1) {
		return P.notEqual(n, a1);
	}

	/** "Special" predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static @Nullable String notEqualEx(byte n, byte a1) {
		return P.notEqualEx(n, a1);
	}

	/** Predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean equal(short n, short a1) {
		return P.equal(n, a1);
	}

	/** "Special" predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static @Nullable String equalEx(short n, short a1) {
		return P.equalEx(n, a1);
	}
	/** Predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean notEqual(short n, short a1) {
		return P.notEqual(n, a1);
	}

	/** "Special" predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static @Nullable String notEqualEx(short n, short a1) {
		return P.notEqualEx(n, a1);
	}

	/** Predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean equal(int n, int a1) {
		return P.equal(n, a1);
	}

	/** "Special" predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static @Nullable String equalEx(int n, int a1) {
		return P.equalEx(n, a1);
	}
	/** Predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean notEqual(int n, int a1) {
		return P.notEqual(n, a1);
	}

	/** "Special" predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static @Nullable String notEqualEx(int n, int a1) {
		return P.notEqualEx(n, a1);
	}

	/** Predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean equal(long n, long a1) {
		return P.equal(n, a1);
	}

	/** "Special" predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static @Nullable String equalEx(long n, long a1) {
		return P.equalEx(n, a1);
	}
	/** Predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean notEqual(long n, long a1) {
		return P.notEqual(n, a1);
	}

	/** "Special" predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static @Nullable String notEqualEx(long n, long a1) {
		return P.notEqualEx(n, a1);
	}

	/** Predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean equal(float n, float a1) {
		return P.equal(n, a1);
	}

	/** "Special" predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static @Nullable String equalEx(float n, float a1) {
		return P.equalEx(n, a1);
	}
	/** Predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean notEqual(float n, float a1) {
		return P.notEqual(n, a1);
	}

	/** "Special" predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static @Nullable String notEqualEx(float n, float a1) {
		return P.notEqualEx(n, a1);
	}

	/** Predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean equal(double n, double a1) {
		return P.equal(n, a1);
	}

	/** "Special" predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static @Nullable String equalEx(double n, double a1) {
		return P.equalEx(n, a1);
	}
	/** Predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean notEqual(double n, double a1) {
		return P.notEqual(n, a1);
	}

	/** "Special" predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static @Nullable String notEqualEx(double n, double a1) {
		return P.notEqualEx(n, a1);
	}

	/** Predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean equal(char n, char a1) {
		return P.equal(n, a1);
	}

	/** "Special" predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static @Nullable String equalEx(char n, char a1) {
		return P.equalEx(n, a1);
	}
	/** Predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean notEqual(char n, char a1) {
		return P.notEqual(n, a1);
	}

	/** "Special" predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static @Nullable String notEqualEx(char n, char a1) {
		return P.notEqualEx(n, a1);
	}

	// </editor-fold>

	// <editor-fold desc="arrays">

	// </editor-fold>

	// <editor-fold desc="collections">

	/** Predicate: Collection <%s> must contain element <%s>. Available in {@link P}, {@link Does}.*/
	public static <T> boolean contain(@Nonnull Collection<T> collection, T element) {
		Null.nonNullArg(collection, "collection");
		return P.contain(collection, element);
	}

	/** "Special" predicate: Collection <%s> must contain element <%s>. Available in {@link P}, {@link Does}.*/
	public static <T> @Nullable String containEx(@Nonnull Collection<T> collection, T element) {
		Null.nonNullArg(collection, "collection");
		return P.containEx(collection, element);
	}
	/** Predicate: Collection <%s> must NOT contain element <%s>. Available in {@link P}, {@link Does}.*/
	public static <T> boolean notContain(@Nonnull Collection<T> collection, T element) {
		Null.nonNullArg(collection, "collection");
		return P.notContain(collection, element);
	}

	/** "Special" predicate: Collection <%s> must NOT contain element <%s>. Available in {@link P}, {@link Does}.*/
	public static <T> @Nullable String notContainEx(@Nonnull Collection<T> collection, T element) {
		Null.nonNullArg(collection, "collection");
		return P.notContainEx(collection, element);
	}

	/** Predicate: Collection <%s> must contain exactly elements in order: <%s>. Available in {@link P}, {@link Does}.*/
	public static <T> boolean containExactly(@Nonnull Collection<?> collection, Object... elementsInOrder) {
		Null.nonNullArg(collection, "collection");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return P.containExactly(collection, elementsInOrder);
	}

	/** "Special" predicate: Collection <%s> must contain exactly elements in order: <%s>. Available in {@link P}, {@link Does}.*/
	public static <T> @Nullable String containExactlyEx(@Nonnull Collection<?> collection, Object... elementsInOrder) {
		Null.nonNullArg(collection, "collection");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return P.containExactlyEx(collection, elementsInOrder);
	}
	/** Predicate: Collection <%s> must NOT contain exactly elements in order: <%s>. Available in {@link P}, {@link Does}.*/
	public static <T> boolean notContainExactly(@Nonnull Collection<?> collection, Object... elementsInOrder) {
		Null.nonNullArg(collection, "collection");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return P.notContainExactly(collection, elementsInOrder);
	}

	/** "Special" predicate: Collection <%s> must NOT contain exactly elements in order: <%s>. Available in {@link P}, {@link Does}.*/
	public static <T> @Nullable String notContainExactlyEx(@Nonnull Collection<?> collection, Object... elementsInOrder) {
		Null.nonNullArg(collection, "collection");
		Null.nonNullArg(elementsInOrder, "elementsInOrder");
		return P.notContainExactlyEx(collection, elementsInOrder);
	}

	/** Predicate: Map <%s> must contain key <%s>. Available in {@link P}, {@link Does}.*/
	public static <K> boolean containKey(@Nonnull Map<K, ?> map, K key) {
		Null.nonNullArg(map, "map");
		return P.containKey(map, key);
	}

	/** "Special" predicate: Map <%s> must contain key <%s>. Available in {@link P}, {@link Does}.*/
	public static <K> @Nullable String containKeyEx(@Nonnull Map<K, ?> map, K key) {
		Null.nonNullArg(map, "map");
		return P.containKeyEx(map, key);
	}
	/** Predicate: Map <%s> must NOT contain key <%s>. Available in {@link P}, {@link Does}.*/
	public static <K> boolean notContainKey(@Nonnull Map<K, ?> map, K key) {
		Null.nonNullArg(map, "map");
		return P.notContainKey(map, key);
	}

	/** "Special" predicate: Map <%s> must NOT contain key <%s>. Available in {@link P}, {@link Does}.*/
	public static <K> @Nullable String notContainKeyEx(@Nonnull Map<K, ?> map, K key) {
		Null.nonNullArg(map, "map");
		return P.notContainKeyEx(map, key);
	}

	/** Predicate: Map <%s> must contain keys <%s>. Available in {@link P}, {@link Does}.*/
	public static <K> boolean containKeys(@Nonnull Map<K, ?> map, K... keys) {
		Null.nonNullArg(map, "map");
		return P.containKeys(map, keys);
	}

	/** "Special" predicate: Map <%s> must contain keys <%s>. Available in {@link P}, {@link Does}.*/
	public static <K> @Nullable String containKeysEx(@Nonnull Map<K, ?> map, K... keys) {
		Null.nonNullArg(map, "map");
		return P.containKeysEx(map, keys);
	}
	/** Predicate: Map <%s> must NOT contain keys <%s>. Available in {@link P}, {@link Does}.*/
	public static <K> boolean notContainKeys(@Nonnull Map<K, ?> map, K... keys) {
		Null.nonNullArg(map, "map");
		return P.notContainKeys(map, keys);
	}

	/** "Special" predicate: Map <%s> must NOT contain keys <%s>. Available in {@link P}, {@link Does}.*/
	public static <K> @Nullable String notContainKeysEx(@Nonnull Map<K, ?> map, K... keys) {
		Null.nonNullArg(map, "map");
		return P.notContainKeysEx(map, keys);
	}

	/** Predicate: Map <%s> must contain any key from <%s>. Available in {@link P}, {@link Does}.*/
	public static <K> boolean containAnyKey(@Nonnull Map<K, ?> map, K... keys) {
		Null.nonNullArg(map, "map");
		return P.containAnyKey(map, keys);
	}

	/** "Special" predicate: Map <%s> must contain any key from <%s>. Available in {@link P}, {@link Does}.*/
	public static <K> @Nullable String containAnyKeyEx(@Nonnull Map<K, ?> map, K... keys) {
		Null.nonNullArg(map, "map");
		return P.containAnyKeyEx(map, keys);
	}

	// </editor-fold>

	// <editor-fold desc="object derivatives">

	// </editor-fold>

	// <editor-fold desc="Throwables">

	// </editor-fold>

	// <editor-fold desc="have">

	/** 'ToBool' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToBool(@Nonnull LPredicate<K> extractor, @Nonnull LLogicalOperator operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.apply(extractor.test(k));
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToBoolEx(@Nonnull LPredicate<K> extractor, @Nonnull LBoolFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.test(k));
	}

	/** 'ToBool' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToBool(@Nonnull LPredicate<K> extractor, boolean a2, @Nonnull LLogicalBinaryOperator operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.apply(extractor.test(k), a2);
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToBoolEx(@Nonnull LPredicate<K> extractor, boolean a2, @Nonnull LBiBoolFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.test(k), a2);
	}

	/** 'ToBool' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToBool(@Nonnull LPredicate<K> extractor, @Nonnull LLogicalBinaryOperator operator, boolean a2) {
		return haveToBool(extractor, a2, operator);
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToBoolEx(@Nonnull LPredicate<K> extractor, @Nonnull LBiBoolFunction<String> specialPredicate, boolean a2) {
		return haveToBoolEx(extractor, a2, specialPredicate);
	}

	/** 'ToBool' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToBool(@Nonnull LPredicate<K> extractor, boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.apply(extractor.test(k), a2, a3);
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToBoolEx(@Nonnull LPredicate<K> extractor, boolean a2, boolean a3, @Nonnull LTriBoolFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.test(k), a2, a3);
	}

	/** 'ToBool' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToBool(@Nonnull LPredicate<K> extractor, @Nonnull LLogicalTernaryOperator operator, boolean a2, boolean a3) {
		return haveToBool(extractor, a2, a3, operator);
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToBoolEx(@Nonnull LPredicate<K> extractor, @Nonnull LTriBoolFunction<String> specialPredicate, boolean a2, boolean a3) {
		return haveToBoolEx(extractor, a2, a3, specialPredicate);
	}

	/** 'ToBool' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToBoolInt(@Nonnull LPredicate<K> extractor, int v, @Nonnull LBoolIntPredicate operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.test(extractor.test(k), v);
	}

	/** 'ToBool' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToBoolInt(@Nonnull LPredicate<K> extractor, @Nonnull LBoolIntPredicate operator, int v) {
		return haveToBoolInt(extractor, v, operator);
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> haveToBool_(@Nonnull LPredicate<K> extractor, V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.testBoolObj(extractor.test(k), v);
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> haveToBoolEx_(@Nonnull LPredicate<K> extractor, V v, @Nonnull LObjBoolFunction.LBoolObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyBoolObj(extractor.test(k), v);
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> haveToBool_(@Nonnull LPredicate<K> extractor, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, V v) {
		return haveToBool_(extractor, v, operator);
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> haveToBoolEx_(@Nonnull LPredicate<K> extractor, @Nonnull LObjBoolFunction.LBoolObjFunc<? super V, String> specialPredicate, V v) {
		return haveToBoolEx_(extractor, v, specialPredicate);
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> haveToBoolWith(@Nonnull LPredicate<K> extractor, V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.test(with1, extractor.test(k));
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToBoolWithEx(@Nonnull LPredicate<K> extractor, V1 with1, @Nonnull LObjBoolFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.test(k));
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> haveToBoolWith(@Nonnull LPredicate<K> extractor, @Nonnull LObjBoolPredicate<? super V1> operator, V1 with1) {
		return haveToBoolWith(extractor, with1, operator);
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToBoolWithEx(@Nonnull LPredicate<K> extractor, @Nonnull LObjBoolFunction<? super V1, String> specialPredicate, V1 with1) {
		return haveToBoolWithEx(extractor, with1, specialPredicate);
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> haveToBoolWith(@Nonnull LPredicate<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.test(with1, with2, extractor.test(k));
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToBoolWithEx(@Nonnull LPredicate<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjBoolFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.test(k));
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> haveToBoolWith(@Nonnull LPredicate<K> extractor, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, V1 with1, V2 with2) {
		return haveToBoolWith(extractor, with1, with2, operator);
	}

	/**
	* 'ToBool' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToBoolWithEx(@Nonnull LPredicate<K> extractor, @Nonnull LBiObjBoolFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return haveToBoolWithEx(extractor, with1, with2, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, @Nonnull LPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k));
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveEx(@Nonnull LFunction<K, T> extractor, @Nonnull LFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k));
	}

	public static @Nonnull <K, T> LPredicate<K> uniHave(@Nonnull LFunction<K, T> extractor, T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> uniHaveEx(@Nonnull LFunction<K, T> extractor, T a2, @Nonnull LBiFunction<? super T, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2);
	}

	public static @Nonnull <K, T> LPredicate<K> uniHave(@Nonnull LFunction<K, T> extractor, @Nonnull LBiPredicate<? super T, ? super T> predicate, T a2) {
		return uniHave(extractor, a2, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> uniHaveEx(@Nonnull LFunction<K, T> extractor, @Nonnull LBiFunction<? super T, ? super T, String> specialPredicate, T a2) {
		return uniHaveEx(extractor, a2, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> uniHave(@Nonnull LFunction<K, T> extractor, T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2, a3);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> uniHaveEx(@Nonnull LFunction<K, T> extractor, T a2, T a3, @Nonnull LTriFunction<? super T, ? super T, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2, a3);
	}

	public static @Nonnull <K, T> LPredicate<K> uniHave(@Nonnull LFunction<K, T> extractor, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3) {
		return uniHave(extractor, a2, a3, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> uniHaveEx(@Nonnull LFunction<K, T> extractor, @Nonnull LTriFunction<? super T, ? super T, ? super T, String> specialPredicate, T a2, T a3) {
		return uniHaveEx(extractor, a2, a3, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> uniHave(@Nonnull LFunction<K, T> extractor, T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2, a3, a4);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> uniHaveEx(@Nonnull LFunction<K, T> extractor, T a2, T a3, T a4, @Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2, a3, a4);
	}

	public static @Nonnull <K, T> LPredicate<K> uniHave(@Nonnull LFunction<K, T> extractor, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4) {
		return uniHave(extractor, a2, a3, a4, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> uniHaveEx(@Nonnull LFunction<K, T> extractor, @Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, String> specialPredicate, T a2, T a3, T a4) {
		return uniHaveEx(extractor, a2, a3, a4, specialPredicate);
	}

	public static @Nonnull <K, T, V> LPredicate<K> haveA(@Nonnull LFunction<K, T> extractor, V[] a2, @Nonnull LBiPredicate<T, V[]> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T, V> LFunction<K, String> haveAEx(@Nonnull LFunction<K, T> extractor, V[] a2, @Nonnull LBiFunction<T, V[], String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2);
	}

	public static @Nonnull <K, T, V> LPredicate<K> haveA(@Nonnull LFunction<K, T> extractor, @Nonnull LBiPredicate<T, V[]> predicate, V... a2) {
		return haveA(extractor, a2, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T, V> LFunction<K, String> haveAEx(@Nonnull LFunction<K, T> extractor, @Nonnull LBiFunction<T, V[], String> specialPredicate, V... a2) {
		return haveAEx(extractor, a2, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> haveBool(@Nonnull LFunction<K, T> extractor, boolean v, @Nonnull LObjBoolPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveBoolEx(@Nonnull LFunction<K, T> extractor, boolean v, @Nonnull LObjBoolFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T> LPredicate<K> haveBool(@Nonnull LFunction<K, T> extractor, @Nonnull LObjBoolPredicate<? super T> predicate, boolean v) {
		return haveBool(extractor, v, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveBoolEx(@Nonnull LFunction<K, T> extractor, @Nonnull LObjBoolFunction<? super T, String> specialPredicate, boolean v) {
		return haveBoolEx(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> haveByte(@Nonnull LFunction<K, T> extractor, byte v, @Nonnull LObjBytePredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveByteEx(@Nonnull LFunction<K, T> extractor, byte v, @Nonnull LObjByteFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T> LPredicate<K> haveByte(@Nonnull LFunction<K, T> extractor, @Nonnull LObjBytePredicate<? super T> predicate, byte v) {
		return haveByte(extractor, v, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveByteEx(@Nonnull LFunction<K, T> extractor, @Nonnull LObjByteFunction<? super T, String> specialPredicate, byte v) {
		return haveByteEx(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> haveDbl(@Nonnull LFunction<K, T> extractor, double v, @Nonnull LObjDblPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveDblEx(@Nonnull LFunction<K, T> extractor, double v, @Nonnull LObjDblFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T> LPredicate<K> haveDbl(@Nonnull LFunction<K, T> extractor, @Nonnull LObjDblPredicate<? super T> predicate, double v) {
		return haveDbl(extractor, v, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveDblEx(@Nonnull LFunction<K, T> extractor, @Nonnull LObjDblFunction<? super T, String> specialPredicate, double v) {
		return haveDblEx(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> haveChar(@Nonnull LFunction<K, T> extractor, char v, @Nonnull LObjCharPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveCharEx(@Nonnull LFunction<K, T> extractor, char v, @Nonnull LObjCharFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T> LPredicate<K> haveChar(@Nonnull LFunction<K, T> extractor, @Nonnull LObjCharPredicate<? super T> predicate, char v) {
		return haveChar(extractor, v, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveCharEx(@Nonnull LFunction<K, T> extractor, @Nonnull LObjCharFunction<? super T, String> specialPredicate, char v) {
		return haveCharEx(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> haveSrt(@Nonnull LFunction<K, T> extractor, short v, @Nonnull LObjSrtPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveSrtEx(@Nonnull LFunction<K, T> extractor, short v, @Nonnull LObjSrtFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T> LPredicate<K> haveSrt(@Nonnull LFunction<K, T> extractor, @Nonnull LObjSrtPredicate<? super T> predicate, short v) {
		return haveSrt(extractor, v, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveSrtEx(@Nonnull LFunction<K, T> extractor, @Nonnull LObjSrtFunction<? super T, String> specialPredicate, short v) {
		return haveSrtEx(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> haveFlt(@Nonnull LFunction<K, T> extractor, float v, @Nonnull LObjFltPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveFltEx(@Nonnull LFunction<K, T> extractor, float v, @Nonnull LObjFltFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T> LPredicate<K> haveFlt(@Nonnull LFunction<K, T> extractor, @Nonnull LObjFltPredicate<? super T> predicate, float v) {
		return haveFlt(extractor, v, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveFltEx(@Nonnull LFunction<K, T> extractor, @Nonnull LObjFltFunction<? super T, String> specialPredicate, float v) {
		return haveFltEx(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> haveInt(@Nonnull LFunction<K, T> extractor, int v, @Nonnull LObjIntPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveIntEx(@Nonnull LFunction<K, T> extractor, int v, @Nonnull LOiFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T> LPredicate<K> haveInt(@Nonnull LFunction<K, T> extractor, @Nonnull LObjIntPredicate<? super T> predicate, int v) {
		return haveInt(extractor, v, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveIntEx(@Nonnull LFunction<K, T> extractor, @Nonnull LOiFunction<? super T, String> specialPredicate, int v) {
		return haveIntEx(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T> LPredicate<K> haveLong(@Nonnull LFunction<K, T> extractor, long v, @Nonnull LObjLongPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveLongEx(@Nonnull LFunction<K, T> extractor, long v, @Nonnull LObjLongFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T> LPredicate<K> haveLong(@Nonnull LFunction<K, T> extractor, @Nonnull LObjLongPredicate<? super T> predicate, long v) {
		return haveLong(extractor, v, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T> LFunction<K, String> haveLongEx(@Nonnull LFunction<K, T> extractor, @Nonnull LObjLongFunction<? super T, String> specialPredicate, long v) {
		return haveLongEx(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T, V> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, V v, @Nonnull LBiPredicate<? super T, ? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T, V> LFunction<K, String> haveEx(@Nonnull LFunction<K, T> extractor, V v, @Nonnull LBiFunction<? super T, ? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	public static @Nonnull <K, T, V> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, @Nonnull LBiPredicate<? super T, ? super V> predicate, V v) {
		return have(extractor, v, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T, V> LFunction<K, String> haveEx(@Nonnull LFunction<K, T> extractor, @Nonnull LBiFunction<? super T, ? super V, String> specialPredicate, V v) {
		return haveEx(extractor, v, specialPredicate);
	}

	public static @Nonnull <K, T, V2, V3> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2, a3);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T, V2, V3> LFunction<K, String> haveEx(@Nonnull LFunction<K, T> extractor, V2 a2, V3 a3, @Nonnull LTriFunction<? super T, ? super V2, ? super V3, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2, a3);
	}

	public static @Nonnull <K, T, V2, V3> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3) {
		return have(extractor, a2, a3, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T, V2, V3> LFunction<K, String> haveEx(@Nonnull LFunction<K, T> extractor, @Nonnull LTriFunction<? super T, ? super V2, ? super V3, String> specialPredicate, V2 a2, V3 a3) {
		return haveEx(extractor, a2, a3, specialPredicate);
	}

	public static @Nonnull <K, T, V2, V3, V4> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2, a3, a4);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T, V2, V3, V4> LFunction<K, String> haveEx(@Nonnull LFunction<K, T> extractor, V2 a2, V3 a3, V4 a4, @Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2, a3, a4);
	}

	public static @Nonnull <K, T, V2, V3, V4> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4) {
		return have(extractor, a2, a3, a4, predicate);
	}

	/** 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied). */
	public static @Nonnull <K, T, V2, V3, V4> LFunction<K, String> haveEx(@Nonnull LFunction<K, T> extractor, @Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, String> specialPredicate, V2 a2, V3 a3, V4 a4) {
		return haveEx(extractor, a2, a3, a4, specialPredicate);
	}

	/** 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s). */
	public static @Nonnull <K, T, V1> LPredicate<K> haveWith(@Nonnull LFunction<K, T> extractor, V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.apply(k));
	}

	/**
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, T, V1> LFunction<K, String> haveWithEx(@Nonnull LFunction<K, T> extractor, V1 with1, @Nonnull LBiFunction<? super V1, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.apply(k));
	}

	/** 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s). */
	public static @Nonnull <K, T, V1> LPredicate<K> haveWith(@Nonnull LFunction<K, T> extractor, @Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with1) {
		return haveWith(extractor, with1, predicate);
	}

	/**
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, T, V1> LFunction<K, String> haveWithEx(@Nonnull LFunction<K, T> extractor, @Nonnull LBiFunction<? super V1, ? super T, String> specialPredicate, V1 with1) {
		return haveWithEx(extractor, with1, specialPredicate);
	}

	/** 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s). */
	public static @Nonnull <K, T, V1, V2> LPredicate<K> haveWith(@Nonnull LFunction<K, T> extractor, V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.apply(k));
	}

	/**
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, T, V1, V2> LFunction<K, String> haveWithEx(@Nonnull LFunction<K, T> extractor, V1 with1, V2 with2, @Nonnull LTriFunction<? super V1, ? super V2, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.apply(k));
	}

	/** 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s). */
	public static @Nonnull <K, T, V1, V2> LPredicate<K> haveWith(@Nonnull LFunction<K, T> extractor, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, V1 with1, V2 with2) {
		return haveWith(extractor, with1, with2, predicate);
	}

	/**
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, T, V1, V2> LFunction<K, String> haveWithEx(@Nonnull LFunction<K, T> extractor, @Nonnull LTriFunction<? super V1, ? super V2, ? super T, String> specialPredicate, V1 with1, V2 with2) {
		return haveWithEx(extractor, with1, with2, specialPredicate);
	}

	/** 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s). */
	public static @Nonnull <K, T> LPredicate<K> uniHaveWith(@Nonnull LFunction<K, T> extractor, T with, @Nonnull LBiPredicate<? super T, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with, extractor.apply(k));
	}

	/**
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, T> LFunction<K, String> uniHaveWithEx(@Nonnull LFunction<K, T> extractor, T with, @Nonnull LBiFunction<? super T, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with, extractor.apply(k));
	}

	/** 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s). */
	public static @Nonnull <K, T> LPredicate<K> uniHaveWith(@Nonnull LFunction<K, T> extractor, @Nonnull LBiPredicate<? super T, ? super T> predicate, T with) {
		return uniHaveWith(extractor, with, predicate);
	}

	/**
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, T> LFunction<K, String> uniHaveWithEx(@Nonnull LFunction<K, T> extractor, @Nonnull LBiFunction<? super T, ? super T, String> specialPredicate, T with) {
		return uniHaveWithEx(extractor, with, specialPredicate);
	}

	/** 'ToByte' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToByte(@Nonnull LToByteFunction<K> extractor, @Nonnull LBytePredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsByte(k));
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToByteEx(@Nonnull LToByteFunction<K> extractor, @Nonnull LByteFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsByte(k));
	}

	/** 'ToByte' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToByte(@Nonnull LToByteFunction<K> extractor, byte a2, @Nonnull LBiBytePredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsByte(k), a2);
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToByteEx(@Nonnull LToByteFunction<K> extractor, byte a2, @Nonnull LBiByteFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsByte(k), a2);
	}

	/** 'ToByte' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToByte(@Nonnull LToByteFunction<K> extractor, @Nonnull LBiBytePredicate predicate, byte a2) {
		return haveToByte(extractor, a2, predicate);
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToByteEx(@Nonnull LToByteFunction<K> extractor, @Nonnull LBiByteFunction<String> specialPredicate, byte a2) {
		return haveToByteEx(extractor, a2, specialPredicate);
	}

	/** 'ToByte' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToByte(@Nonnull LToByteFunction<K> extractor, byte a2, byte a3, @Nonnull LTriBytePredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsByte(k), a2, a3);
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToByteEx(@Nonnull LToByteFunction<K> extractor, byte a2, byte a3, @Nonnull LTriByteFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsByte(k), a2, a3);
	}

	/** 'ToByte' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToByte(@Nonnull LToByteFunction<K> extractor, @Nonnull LTriBytePredicate predicate, byte a2, byte a3) {
		return haveToByte(extractor, a2, a3, predicate);
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToByteEx(@Nonnull LToByteFunction<K> extractor, @Nonnull LTriByteFunction<String> specialPredicate, byte a2, byte a3) {
		return haveToByteEx(extractor, a2, a3, specialPredicate);
	}

	/** 'ToByte' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToByteInt(@Nonnull LToByteFunction<K> extractor, int v, @Nonnull LByteIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsByte(k), v);
	}

	/** 'ToByte' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToByteInt(@Nonnull LToByteFunction<K> extractor, @Nonnull LByteIntPredicate predicate, int v) {
		return haveToByteInt(extractor, v, predicate);
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> haveToByte_(@Nonnull LToByteFunction<K> extractor, V v, @Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testByteObj(extractor.applyAsByte(k), v);
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> haveToByteEx_(@Nonnull LToByteFunction<K> extractor, V v, @Nonnull LObjByteFunction.LByteObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyByteObj(extractor.applyAsByte(k), v);
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> haveToByte_(@Nonnull LToByteFunction<K> extractor, @Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate, V v) {
		return haveToByte_(extractor, v, predicate);
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> haveToByteEx_(@Nonnull LToByteFunction<K> extractor, @Nonnull LObjByteFunction.LByteObjFunc<? super V, String> specialPredicate, V v) {
		return haveToByteEx_(extractor, v, specialPredicate);
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> haveToByteWith(@Nonnull LToByteFunction<K> extractor, V1 with1, @Nonnull LObjBytePredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsByte(k));
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToByteWithEx(@Nonnull LToByteFunction<K> extractor, V1 with1, @Nonnull LObjByteFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsByte(k));
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> haveToByteWith(@Nonnull LToByteFunction<K> extractor, @Nonnull LObjBytePredicate<? super V1> predicate, V1 with1) {
		return haveToByteWith(extractor, with1, predicate);
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToByteWithEx(@Nonnull LToByteFunction<K> extractor, @Nonnull LObjByteFunction<? super V1, String> specialPredicate, V1 with1) {
		return haveToByteWithEx(extractor, with1, specialPredicate);
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> haveToByteWith(@Nonnull LToByteFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjBytePredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsByte(k));
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToByteWithEx(@Nonnull LToByteFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjByteFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsByte(k));
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> haveToByteWith(@Nonnull LToByteFunction<K> extractor, @Nonnull LBiObjBytePredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return haveToByteWith(extractor, with1, with2, predicate);
	}

	/**
	* 'ToByte' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToByteWithEx(@Nonnull LToByteFunction<K> extractor, @Nonnull LBiObjByteFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return haveToByteWithEx(extractor, with1, with2, specialPredicate);
	}

	/** 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToDbl(@Nonnull LToDblFunction<K> extractor, @Nonnull LDblPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsDbl(k));
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToDblEx(@Nonnull LToDblFunction<K> extractor, @Nonnull LDblFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsDbl(k));
	}

	/** 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToDbl(@Nonnull LToDblFunction<K> extractor, double a2, @Nonnull LBiDblPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsDbl(k), a2);
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToDblEx(@Nonnull LToDblFunction<K> extractor, double a2, @Nonnull LBiDblFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsDbl(k), a2);
	}

	/** 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToDbl(@Nonnull LToDblFunction<K> extractor, @Nonnull LBiDblPredicate predicate, double a2) {
		return haveToDbl(extractor, a2, predicate);
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToDblEx(@Nonnull LToDblFunction<K> extractor, @Nonnull LBiDblFunction<String> specialPredicate, double a2) {
		return haveToDblEx(extractor, a2, specialPredicate);
	}

	/** 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToDbl(@Nonnull LToDblFunction<K> extractor, double a2, double a3, @Nonnull LTriDblPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsDbl(k), a2, a3);
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToDblEx(@Nonnull LToDblFunction<K> extractor, double a2, double a3, @Nonnull LTriDblFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsDbl(k), a2, a3);
	}

	/** 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToDbl(@Nonnull LToDblFunction<K> extractor, @Nonnull LTriDblPredicate predicate, double a2, double a3) {
		return haveToDbl(extractor, a2, a3, predicate);
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToDblEx(@Nonnull LToDblFunction<K> extractor, @Nonnull LTriDblFunction<String> specialPredicate, double a2, double a3) {
		return haveToDblEx(extractor, a2, a3, specialPredicate);
	}

	/** 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToDblInt(@Nonnull LToDblFunction<K> extractor, int v, @Nonnull LDblIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsDbl(k), v);
	}

	/** 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToDblInt(@Nonnull LToDblFunction<K> extractor, @Nonnull LDblIntPredicate predicate, int v) {
		return haveToDblInt(extractor, v, predicate);
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> haveToDbl_(@Nonnull LToDblFunction<K> extractor, V v, @Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testDblObj(extractor.applyAsDbl(k), v);
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> haveToDblEx_(@Nonnull LToDblFunction<K> extractor, V v, @Nonnull LObjDblFunction.LDblObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyDblObj(extractor.applyAsDbl(k), v);
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> haveToDbl_(@Nonnull LToDblFunction<K> extractor, @Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate, V v) {
		return haveToDbl_(extractor, v, predicate);
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> haveToDblEx_(@Nonnull LToDblFunction<K> extractor, @Nonnull LObjDblFunction.LDblObjFunc<? super V, String> specialPredicate, V v) {
		return haveToDblEx_(extractor, v, specialPredicate);
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> haveToDblWith(@Nonnull LToDblFunction<K> extractor, V1 with1, @Nonnull LObjDblPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsDbl(k));
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToDblWithEx(@Nonnull LToDblFunction<K> extractor, V1 with1, @Nonnull LObjDblFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsDbl(k));
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> haveToDblWith(@Nonnull LToDblFunction<K> extractor, @Nonnull LObjDblPredicate<? super V1> predicate, V1 with1) {
		return haveToDblWith(extractor, with1, predicate);
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToDblWithEx(@Nonnull LToDblFunction<K> extractor, @Nonnull LObjDblFunction<? super V1, String> specialPredicate, V1 with1) {
		return haveToDblWithEx(extractor, with1, specialPredicate);
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> haveToDblWith(@Nonnull LToDblFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjDblPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsDbl(k));
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToDblWithEx(@Nonnull LToDblFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjDblFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsDbl(k));
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> haveToDblWith(@Nonnull LToDblFunction<K> extractor, @Nonnull LBiObjDblPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return haveToDblWith(extractor, with1, with2, predicate);
	}

	/**
	* 'ToDbl' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToDblWithEx(@Nonnull LToDblFunction<K> extractor, @Nonnull LBiObjDblFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return haveToDblWithEx(extractor, with1, with2, specialPredicate);
	}

	/** 'ToChar' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToChar(@Nonnull LToCharFunction<K> extractor, @Nonnull LCharPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsChar(k));
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToCharEx(@Nonnull LToCharFunction<K> extractor, @Nonnull LCharFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsChar(k));
	}

	/** 'ToChar' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToChar(@Nonnull LToCharFunction<K> extractor, char a2, @Nonnull LBiCharPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsChar(k), a2);
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToCharEx(@Nonnull LToCharFunction<K> extractor, char a2, @Nonnull LBiCharFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsChar(k), a2);
	}

	/** 'ToChar' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToChar(@Nonnull LToCharFunction<K> extractor, @Nonnull LBiCharPredicate predicate, char a2) {
		return haveToChar(extractor, a2, predicate);
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToCharEx(@Nonnull LToCharFunction<K> extractor, @Nonnull LBiCharFunction<String> specialPredicate, char a2) {
		return haveToCharEx(extractor, a2, specialPredicate);
	}

	/** 'ToChar' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToChar(@Nonnull LToCharFunction<K> extractor, char a2, char a3, @Nonnull LTriCharPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsChar(k), a2, a3);
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToCharEx(@Nonnull LToCharFunction<K> extractor, char a2, char a3, @Nonnull LTriCharFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsChar(k), a2, a3);
	}

	/** 'ToChar' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToChar(@Nonnull LToCharFunction<K> extractor, @Nonnull LTriCharPredicate predicate, char a2, char a3) {
		return haveToChar(extractor, a2, a3, predicate);
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToCharEx(@Nonnull LToCharFunction<K> extractor, @Nonnull LTriCharFunction<String> specialPredicate, char a2, char a3) {
		return haveToCharEx(extractor, a2, a3, specialPredicate);
	}

	/** 'ToChar' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToCharInt(@Nonnull LToCharFunction<K> extractor, int v, @Nonnull LCharIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsChar(k), v);
	}

	/** 'ToChar' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToCharInt(@Nonnull LToCharFunction<K> extractor, @Nonnull LCharIntPredicate predicate, int v) {
		return haveToCharInt(extractor, v, predicate);
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> haveToChar_(@Nonnull LToCharFunction<K> extractor, V v, @Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testCharObj(extractor.applyAsChar(k), v);
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> haveToCharEx_(@Nonnull LToCharFunction<K> extractor, V v, @Nonnull LObjCharFunction.LCharObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyCharObj(extractor.applyAsChar(k), v);
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> haveToChar_(@Nonnull LToCharFunction<K> extractor, @Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate, V v) {
		return haveToChar_(extractor, v, predicate);
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> haveToCharEx_(@Nonnull LToCharFunction<K> extractor, @Nonnull LObjCharFunction.LCharObjFunc<? super V, String> specialPredicate, V v) {
		return haveToCharEx_(extractor, v, specialPredicate);
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> haveToCharWith(@Nonnull LToCharFunction<K> extractor, V1 with1, @Nonnull LObjCharPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsChar(k));
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToCharWithEx(@Nonnull LToCharFunction<K> extractor, V1 with1, @Nonnull LObjCharFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsChar(k));
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> haveToCharWith(@Nonnull LToCharFunction<K> extractor, @Nonnull LObjCharPredicate<? super V1> predicate, V1 with1) {
		return haveToCharWith(extractor, with1, predicate);
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToCharWithEx(@Nonnull LToCharFunction<K> extractor, @Nonnull LObjCharFunction<? super V1, String> specialPredicate, V1 with1) {
		return haveToCharWithEx(extractor, with1, specialPredicate);
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> haveToCharWith(@Nonnull LToCharFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjCharPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsChar(k));
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToCharWithEx(@Nonnull LToCharFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjCharFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsChar(k));
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> haveToCharWith(@Nonnull LToCharFunction<K> extractor, @Nonnull LBiObjCharPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return haveToCharWith(extractor, with1, with2, predicate);
	}

	/**
	* 'ToChar' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToCharWithEx(@Nonnull LToCharFunction<K> extractor, @Nonnull LBiObjCharFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return haveToCharWithEx(extractor, with1, with2, specialPredicate);
	}

	/** 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToSrt(@Nonnull LToSrtFunction<K> extractor, @Nonnull LSrtPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsSrt(k));
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToSrtEx(@Nonnull LToSrtFunction<K> extractor, @Nonnull LSrtFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsSrt(k));
	}

	/** 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToSrt(@Nonnull LToSrtFunction<K> extractor, short a2, @Nonnull LBiSrtPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsSrt(k), a2);
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToSrtEx(@Nonnull LToSrtFunction<K> extractor, short a2, @Nonnull LBiSrtFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsSrt(k), a2);
	}

	/** 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToSrt(@Nonnull LToSrtFunction<K> extractor, @Nonnull LBiSrtPredicate predicate, short a2) {
		return haveToSrt(extractor, a2, predicate);
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToSrtEx(@Nonnull LToSrtFunction<K> extractor, @Nonnull LBiSrtFunction<String> specialPredicate, short a2) {
		return haveToSrtEx(extractor, a2, specialPredicate);
	}

	/** 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToSrt(@Nonnull LToSrtFunction<K> extractor, short a2, short a3, @Nonnull LTriSrtPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsSrt(k), a2, a3);
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToSrtEx(@Nonnull LToSrtFunction<K> extractor, short a2, short a3, @Nonnull LTriSrtFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsSrt(k), a2, a3);
	}

	/** 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToSrt(@Nonnull LToSrtFunction<K> extractor, @Nonnull LTriSrtPredicate predicate, short a2, short a3) {
		return haveToSrt(extractor, a2, a3, predicate);
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToSrtEx(@Nonnull LToSrtFunction<K> extractor, @Nonnull LTriSrtFunction<String> specialPredicate, short a2, short a3) {
		return haveToSrtEx(extractor, a2, a3, specialPredicate);
	}

	/** 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToSrtInt(@Nonnull LToSrtFunction<K> extractor, int v, @Nonnull LSrtIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsSrt(k), v);
	}

	/** 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToSrtInt(@Nonnull LToSrtFunction<K> extractor, @Nonnull LSrtIntPredicate predicate, int v) {
		return haveToSrtInt(extractor, v, predicate);
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> haveToSrt_(@Nonnull LToSrtFunction<K> extractor, V v, @Nonnull LObjSrtPredicate.LSrtObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testSrtObj(extractor.applyAsSrt(k), v);
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> haveToSrtEx_(@Nonnull LToSrtFunction<K> extractor, V v, @Nonnull LObjSrtFunction.LSrtObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applySrtObj(extractor.applyAsSrt(k), v);
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> haveToSrt_(@Nonnull LToSrtFunction<K> extractor, @Nonnull LObjSrtPredicate.LSrtObjPred<? super V> predicate, V v) {
		return haveToSrt_(extractor, v, predicate);
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> haveToSrtEx_(@Nonnull LToSrtFunction<K> extractor, @Nonnull LObjSrtFunction.LSrtObjFunc<? super V, String> specialPredicate, V v) {
		return haveToSrtEx_(extractor, v, specialPredicate);
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> haveToSrtWith(@Nonnull LToSrtFunction<K> extractor, V1 with1, @Nonnull LObjSrtPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsSrt(k));
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToSrtWithEx(@Nonnull LToSrtFunction<K> extractor, V1 with1, @Nonnull LObjSrtFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsSrt(k));
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> haveToSrtWith(@Nonnull LToSrtFunction<K> extractor, @Nonnull LObjSrtPredicate<? super V1> predicate, V1 with1) {
		return haveToSrtWith(extractor, with1, predicate);
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToSrtWithEx(@Nonnull LToSrtFunction<K> extractor, @Nonnull LObjSrtFunction<? super V1, String> specialPredicate, V1 with1) {
		return haveToSrtWithEx(extractor, with1, specialPredicate);
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> haveToSrtWith(@Nonnull LToSrtFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjSrtPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsSrt(k));
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToSrtWithEx(@Nonnull LToSrtFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjSrtFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsSrt(k));
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> haveToSrtWith(@Nonnull LToSrtFunction<K> extractor, @Nonnull LBiObjSrtPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return haveToSrtWith(extractor, with1, with2, predicate);
	}

	/**
	* 'ToSrt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToSrtWithEx(@Nonnull LToSrtFunction<K> extractor, @Nonnull LBiObjSrtFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return haveToSrtWithEx(extractor, with1, with2, specialPredicate);
	}

	/** 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToFlt(@Nonnull LToFltFunction<K> extractor, @Nonnull LFltPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsFlt(k));
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToFltEx(@Nonnull LToFltFunction<K> extractor, @Nonnull LFltFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsFlt(k));
	}

	/** 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToFlt(@Nonnull LToFltFunction<K> extractor, float a2, @Nonnull LBiFltPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsFlt(k), a2);
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToFltEx(@Nonnull LToFltFunction<K> extractor, float a2, @Nonnull LBiFltFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsFlt(k), a2);
	}

	/** 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToFlt(@Nonnull LToFltFunction<K> extractor, @Nonnull LBiFltPredicate predicate, float a2) {
		return haveToFlt(extractor, a2, predicate);
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToFltEx(@Nonnull LToFltFunction<K> extractor, @Nonnull LBiFltFunction<String> specialPredicate, float a2) {
		return haveToFltEx(extractor, a2, specialPredicate);
	}

	/** 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToFlt(@Nonnull LToFltFunction<K> extractor, float a2, float a3, @Nonnull LTriFltPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsFlt(k), a2, a3);
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToFltEx(@Nonnull LToFltFunction<K> extractor, float a2, float a3, @Nonnull LTriFltFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsFlt(k), a2, a3);
	}

	/** 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToFlt(@Nonnull LToFltFunction<K> extractor, @Nonnull LTriFltPredicate predicate, float a2, float a3) {
		return haveToFlt(extractor, a2, a3, predicate);
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToFltEx(@Nonnull LToFltFunction<K> extractor, @Nonnull LTriFltFunction<String> specialPredicate, float a2, float a3) {
		return haveToFltEx(extractor, a2, a3, specialPredicate);
	}

	/** 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToFltInt(@Nonnull LToFltFunction<K> extractor, int v, @Nonnull LFltIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsFlt(k), v);
	}

	/** 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToFltInt(@Nonnull LToFltFunction<K> extractor, @Nonnull LFltIntPredicate predicate, int v) {
		return haveToFltInt(extractor, v, predicate);
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> haveToFlt_(@Nonnull LToFltFunction<K> extractor, V v, @Nonnull LObjFltPredicate.LFltObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testFltObj(extractor.applyAsFlt(k), v);
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> haveToFltEx_(@Nonnull LToFltFunction<K> extractor, V v, @Nonnull LObjFltFunction.LFltObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyFltObj(extractor.applyAsFlt(k), v);
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> haveToFlt_(@Nonnull LToFltFunction<K> extractor, @Nonnull LObjFltPredicate.LFltObjPred<? super V> predicate, V v) {
		return haveToFlt_(extractor, v, predicate);
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> haveToFltEx_(@Nonnull LToFltFunction<K> extractor, @Nonnull LObjFltFunction.LFltObjFunc<? super V, String> specialPredicate, V v) {
		return haveToFltEx_(extractor, v, specialPredicate);
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> haveToFltWith(@Nonnull LToFltFunction<K> extractor, V1 with1, @Nonnull LObjFltPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsFlt(k));
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToFltWithEx(@Nonnull LToFltFunction<K> extractor, V1 with1, @Nonnull LObjFltFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsFlt(k));
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> haveToFltWith(@Nonnull LToFltFunction<K> extractor, @Nonnull LObjFltPredicate<? super V1> predicate, V1 with1) {
		return haveToFltWith(extractor, with1, predicate);
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToFltWithEx(@Nonnull LToFltFunction<K> extractor, @Nonnull LObjFltFunction<? super V1, String> specialPredicate, V1 with1) {
		return haveToFltWithEx(extractor, with1, specialPredicate);
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> haveToFltWith(@Nonnull LToFltFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjFltPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsFlt(k));
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToFltWithEx(@Nonnull LToFltFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjFltFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsFlt(k));
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> haveToFltWith(@Nonnull LToFltFunction<K> extractor, @Nonnull LBiObjFltPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return haveToFltWith(extractor, with1, with2, predicate);
	}

	/**
	* 'ToFlt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToFltWithEx(@Nonnull LToFltFunction<K> extractor, @Nonnull LBiObjFltFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return haveToFltWithEx(extractor, with1, with2, specialPredicate);
	}

	/** 'ToInt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToInt(@Nonnull LToIntFunction<K> extractor, @Nonnull LIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsInt(k));
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToIntEx(@Nonnull LToIntFunction<K> extractor, @Nonnull LIntFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsInt(k));
	}

	/** 'ToInt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToInt(@Nonnull LToIntFunction<K> extractor, int a2, @Nonnull LBiIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsInt(k), a2);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToIntEx(@Nonnull LToIntFunction<K> extractor, int a2, @Nonnull LBiIntFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsInt(k), a2);
	}

	/** 'ToInt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToInt(@Nonnull LToIntFunction<K> extractor, @Nonnull LBiIntPredicate predicate, int a2) {
		return haveToInt(extractor, a2, predicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToIntEx(@Nonnull LToIntFunction<K> extractor, @Nonnull LBiIntFunction<String> specialPredicate, int a2) {
		return haveToIntEx(extractor, a2, specialPredicate);
	}

	/** 'ToInt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToInt(@Nonnull LToIntFunction<K> extractor, int a2, int a3, @Nonnull LTriIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsInt(k), a2, a3);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToIntEx(@Nonnull LToIntFunction<K> extractor, int a2, int a3, @Nonnull LTriIntFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsInt(k), a2, a3);
	}

	/** 'ToInt' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToInt(@Nonnull LToIntFunction<K> extractor, @Nonnull LTriIntPredicate predicate, int a2, int a3) {
		return haveToInt(extractor, a2, a3, predicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToIntEx(@Nonnull LToIntFunction<K> extractor, @Nonnull LTriIntFunction<String> specialPredicate, int a2, int a3) {
		return haveToIntEx(extractor, a2, a3, specialPredicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> haveToIntBool_(@Nonnull LToIntFunction<K> extractor, boolean v, @Nonnull LBoolIntPredicate.LIntBoolPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntBool(extractor.applyAsInt(k), v);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> haveToIntBool_(@Nonnull LToIntFunction<K> extractor, @Nonnull LBoolIntPredicate.LIntBoolPred predicate, boolean v) {
		return haveToIntBool_(extractor, v, predicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> haveToIntByte_(@Nonnull LToIntFunction<K> extractor, byte v, @Nonnull LByteIntPredicate.LIntBytePred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntByte(extractor.applyAsInt(k), v);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> haveToIntByte_(@Nonnull LToIntFunction<K> extractor, @Nonnull LByteIntPredicate.LIntBytePred predicate, byte v) {
		return haveToIntByte_(extractor, v, predicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> haveToIntDbl_(@Nonnull LToIntFunction<K> extractor, double v, @Nonnull LDblIntPredicate.LIntDblPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntDbl(extractor.applyAsInt(k), v);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> haveToIntDbl_(@Nonnull LToIntFunction<K> extractor, @Nonnull LDblIntPredicate.LIntDblPred predicate, double v) {
		return haveToIntDbl_(extractor, v, predicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> haveToIntChar_(@Nonnull LToIntFunction<K> extractor, char v, @Nonnull LCharIntPredicate.LIntCharPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntChar(extractor.applyAsInt(k), v);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> haveToIntChar_(@Nonnull LToIntFunction<K> extractor, @Nonnull LCharIntPredicate.LIntCharPred predicate, char v) {
		return haveToIntChar_(extractor, v, predicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> haveToIntSrt_(@Nonnull LToIntFunction<K> extractor, short v, @Nonnull LSrtIntPredicate.LIntSrtPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntSrt(extractor.applyAsInt(k), v);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> haveToIntSrt_(@Nonnull LToIntFunction<K> extractor, @Nonnull LSrtIntPredicate.LIntSrtPred predicate, short v) {
		return haveToIntSrt_(extractor, v, predicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> haveToIntFlt_(@Nonnull LToIntFunction<K> extractor, float v, @Nonnull LFltIntPredicate.LIntFltPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntFlt(extractor.applyAsInt(k), v);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> haveToIntFlt_(@Nonnull LToIntFunction<K> extractor, @Nonnull LFltIntPredicate.LIntFltPred predicate, float v) {
		return haveToIntFlt_(extractor, v, predicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> haveToIntLong_(@Nonnull LToIntFunction<K> extractor, long v, @Nonnull LLongIntPredicate.LIntLongPred predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntLong(extractor.applyAsInt(k), v);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K> LPredicate<K> haveToIntLong_(@Nonnull LToIntFunction<K> extractor, @Nonnull LLongIntPredicate.LIntLongPred predicate, long v) {
		return haveToIntLong_(extractor, v, predicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> haveToInt_(@Nonnull LToIntFunction<K> extractor, V v, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntObj(extractor.applyAsInt(k), v);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> haveToIntEx_(@Nonnull LToIntFunction<K> extractor, V v, @Nonnull LOiFunction.LIntObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyIntObj(extractor.applyAsInt(k), v);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> haveToInt_(@Nonnull LToIntFunction<K> extractor, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, V v) {
		return haveToInt_(extractor, v, predicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> haveToIntEx_(@Nonnull LToIntFunction<K> extractor, @Nonnull LOiFunction.LIntObjFunc<? super V, String> specialPredicate, V v) {
		return haveToIntEx_(extractor, v, specialPredicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> haveToIntWith(@Nonnull LToIntFunction<K> extractor, V1 with1, @Nonnull LObjIntPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsInt(k));
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToIntWithEx(@Nonnull LToIntFunction<K> extractor, V1 with1, @Nonnull LOiFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsInt(k));
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> haveToIntWith(@Nonnull LToIntFunction<K> extractor, @Nonnull LObjIntPredicate<? super V1> predicate, V1 with1) {
		return haveToIntWith(extractor, with1, predicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToIntWithEx(@Nonnull LToIntFunction<K> extractor, @Nonnull LOiFunction<? super V1, String> specialPredicate, V1 with1) {
		return haveToIntWithEx(extractor, with1, specialPredicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> haveToIntWith(@Nonnull LToIntFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsInt(k));
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToIntWithEx(@Nonnull LToIntFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjIntFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsInt(k));
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> haveToIntWith(@Nonnull LToIntFunction<K> extractor, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return haveToIntWith(extractor, with1, with2, predicate);
	}

	/**
	* 'ToInt' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToIntWithEx(@Nonnull LToIntFunction<K> extractor, @Nonnull LBiObjIntFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return haveToIntWithEx(extractor, with1, with2, specialPredicate);
	}

	/** 'ToLong' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToLong(@Nonnull LToLongFunction<K> extractor, @Nonnull LLongPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsLong(k));
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToLongEx(@Nonnull LToLongFunction<K> extractor, @Nonnull LLongFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsLong(k));
	}

	/** 'ToLong' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToLong(@Nonnull LToLongFunction<K> extractor, long a2, @Nonnull LBiLongPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsLong(k), a2);
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToLongEx(@Nonnull LToLongFunction<K> extractor, long a2, @Nonnull LBiLongFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsLong(k), a2);
	}

	/** 'ToLong' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToLong(@Nonnull LToLongFunction<K> extractor, @Nonnull LBiLongPredicate predicate, long a2) {
		return haveToLong(extractor, a2, predicate);
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToLongEx(@Nonnull LToLongFunction<K> extractor, @Nonnull LBiLongFunction<String> specialPredicate, long a2) {
		return haveToLongEx(extractor, a2, specialPredicate);
	}

	/** 'ToLong' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToLong(@Nonnull LToLongFunction<K> extractor, long a2, long a3, @Nonnull LTriLongPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsLong(k), a2, a3);
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToLongEx(@Nonnull LToLongFunction<K> extractor, long a2, long a3, @Nonnull LTriLongFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsLong(k), a2, a3);
	}

	/** 'ToLong' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToLong(@Nonnull LToLongFunction<K> extractor, @Nonnull LTriLongPredicate predicate, long a2, long a3) {
		return haveToLong(extractor, a2, a3, predicate);
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K> LFunction<K, String> haveToLongEx(@Nonnull LToLongFunction<K> extractor, @Nonnull LTriLongFunction<String> specialPredicate, long a2, long a3) {
		return haveToLongEx(extractor, a2, a3, specialPredicate);
	}

	/** 'ToLong' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToLongInt(@Nonnull LToLongFunction<K> extractor, int v, @Nonnull LLongIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsLong(k), v);
	}

	/** 'ToLong' - first, actual value will be converted to primitive type (contrary to the object). */
	public static @Nonnull <K> LPredicate<K> haveToLongInt(@Nonnull LToLongFunction<K> extractor, @Nonnull LLongIntPredicate predicate, int v) {
		return haveToLongInt(extractor, v, predicate);
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> haveToLong_(@Nonnull LToLongFunction<K> extractor, V v, @Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testLongObj(extractor.applyAsLong(k), v);
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> haveToLongEx_(@Nonnull LToLongFunction<K> extractor, V v, @Nonnull LObjLongFunction.LLongObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyLongObj(extractor.applyAsLong(k), v);
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LPredicate<K> haveToLong_(@Nonnull LToLongFunction<K> extractor, @Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, V v) {
		return haveToLong_(extractor, v, predicate);
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	* '_' - the predicate function is a variant or arguments represented by other function.
	*/
	public static @Nonnull <K, V> LFunction<K, String> haveToLongEx_(@Nonnull LToLongFunction<K> extractor, @Nonnull LObjLongFunction.LLongObjFunc<? super V, String> specialPredicate, V v) {
		return haveToLongEx_(extractor, v, specialPredicate);
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> haveToLongWith(@Nonnull LToLongFunction<K> extractor, V1 with1, @Nonnull LObjLongPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsLong(k));
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToLongWithEx(@Nonnull LToLongFunction<K> extractor, V1 with1, @Nonnull LObjLongFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsLong(k));
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1> LPredicate<K> haveToLongWith(@Nonnull LToLongFunction<K> extractor, @Nonnull LObjLongPredicate<? super V1> predicate, V1 with1) {
		return haveToLongWith(extractor, with1, predicate);
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1> LFunction<K, String> haveToLongWithEx(@Nonnull LToLongFunction<K> extractor, @Nonnull LObjLongFunction<? super V1, String> specialPredicate, V1 with1) {
		return haveToLongWithEx(extractor, with1, specialPredicate);
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> haveToLongWith(@Nonnull LToLongFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjLongPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsLong(k));
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToLongWithEx(@Nonnull LToLongFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjLongFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsLong(k));
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	*/
	public static @Nonnull <K, V1, V2> LPredicate<K> haveToLongWith(@Nonnull LToLongFunction<K> extractor, @Nonnull LBiObjLongPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return haveToLongWith(extractor, with1, with2, predicate);
	}

	/**
	* 'ToLong' - first, actual value will be converted to primitive type (contrary to the object).
	* 'With' - call of main predicate will be prefixed (contrary to affixed) with additional argument(s).
	* 'Ex' - main predicate returns strings with message telling what criteria is not satisfied (null if all conditions are satisfied).
	*/
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveToLongWithEx(@Nonnull LToLongFunction<K> extractor, @Nonnull LBiObjLongFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return haveToLongWithEx(extractor, with1, with2, specialPredicate);
	}

	// </editor-fold>

}
