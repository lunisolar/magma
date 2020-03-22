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
		return n.startsWith(a1);
	}

	/** "Special" predicate: String <'%s'> must start with <'%s'>. Available in {@link P}, {@link Does}.*/
	public static String startWith$(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return (n.startsWith(a1)) ? null : String.format("String <'%s'> must start with <'%s'>.", n, a1);
	}

	/** Predicate: String <'%s'> must NOT start with <'%s'>. Available in {@link P}, {@link Does}.*/
	public static boolean notStartWith(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return !(n.startsWith(a1));
	}

	/** "Special" predicate: String <'%s'> must NOT start with <'%s'>. Available in {@link P}, {@link Does}.*/
	public static String notStartWith$(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return !(n.startsWith(a1)) ? null : String.format("String <'%s'> must NOT start with <'%s'>.", n, a1);
	}

	/** Predicate: String <'%s'> must end with <'%s'>. Available in {@link P}, {@link Does}.*/
	public static boolean endWith(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return n.endsWith(a1);
	}

	/** "Special" predicate: String <'%s'> must end with <'%s'>. Available in {@link P}, {@link Does}.*/
	public static String endWith$(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return (n.endsWith(a1)) ? null : String.format("String <'%s'> must end with <'%s'>.", n, a1);
	}

	/** Predicate: String <'%s'> must NOT end with <'%s'>. Available in {@link P}, {@link Does}.*/
	public static boolean notEndWith(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return !(n.endsWith(a1));
	}

	/** "Special" predicate: String <'%s'> must NOT end with <'%s'>. Available in {@link P}, {@link Does}.*/
	public static String notEndWith$(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return !(n.endsWith(a1)) ? null : String.format("String <'%s'> must NOT end with <'%s'>.", n, a1);
	}

	/** Predicate: String <'%s'> must contain string <'%s'>. But does not. Available in {@link P}, {@link Does}.*/
	public static boolean contain(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return n.contains(a1);
	}

	/** "Special" predicate: String <'%s'> must contain string <'%s'>. But does not. Available in {@link P}, {@link Does}.*/
	public static String contain$(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return (n.contains(a1)) ? null : String.format("String <'%s'> must contain string <'%s'>. But does not.", n, a1);
	}

	/** Predicate: String <'%s'> must NOT contain string <'%s'>. But does not. Available in {@link P}, {@link Does}.*/
	public static boolean notContain(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return !(n.contains(a1));
	}

	/** "Special" predicate: String <'%s'> must NOT contain string <'%s'>. But does not. Available in {@link P}, {@link Does}.*/
	public static String notContain$(@Nonnull String n, @Nonnull String a1) {
		Null.nonNullArg(n, "n");
		Null.nonNullArg(a1, "a1");
		return !(n.contains(a1)) ? null : String.format("String <'%s'> must NOT contain string <'%s'>. But does not.", n, a1);
	}

	// </editor-fold>

	// <editor-fold desc="and/or/xor">

	// </editor-fold>

	// <editor-fold desc="== <= >= < >">

	/** Predicate: <%s> must be equal to <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean equal(Object o, Object a1) {
		return Objects.equals(o, a1);
	}

	/** "Special" predicate: <%s> must be equal to <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String equal$(Object o, Object a1) {
		return (Objects.equals(o, a1)) ? null : String.format("<%s> must be equal to <%s>.", o, a1);
	}

	/** Predicate: <%s> must NOT be equal to <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean notEqual(Object o, Object a1) {
		return !(Objects.equals(o, a1));
	}

	/** "Special" predicate: <%s> must NOT be equal to <%s>. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String notEqual$(Object o, Object a1) {
		return !(Objects.equals(o, a1)) ? null : String.format("<%s> must NOT be equal to <%s>.", o, a1);
	}

	/** Predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean equal(byte n, byte a1) {
		return n == a1;
	}

	/** "Special" predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String equal$(byte n, byte a1) {
		return (n == a1) ? null : String.format("%s must be equal to %s.", n, a1);
	}

	/** Predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean notEqual(byte n, byte a1) {
		return n != a1;
	}

	/** "Special" predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String notEqual$(byte n, byte a1) {
		return (n != a1) ? null : String.format("%s must NOT be equal to %s.", n, a1);
	}

	/** Predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean equal(short n, short a1) {
		return n == a1;
	}

	/** "Special" predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String equal$(short n, short a1) {
		return (n == a1) ? null : String.format("%s must be equal to %s.", n, a1);
	}

	/** Predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean notEqual(short n, short a1) {
		return n != a1;
	}

	/** "Special" predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String notEqual$(short n, short a1) {
		return (n != a1) ? null : String.format("%s must NOT be equal to %s.", n, a1);
	}

	/** Predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean equal(int n, int a1) {
		return n == a1;
	}

	/** "Special" predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String equal$(int n, int a1) {
		return (n == a1) ? null : String.format("%s must be equal to %s.", n, a1);
	}

	/** Predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean notEqual(int n, int a1) {
		return n != a1;
	}

	/** "Special" predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String notEqual$(int n, int a1) {
		return (n != a1) ? null : String.format("%s must NOT be equal to %s.", n, a1);
	}

	/** Predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean equal(long n, long a1) {
		return n == a1;
	}

	/** "Special" predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String equal$(long n, long a1) {
		return (n == a1) ? null : String.format("%s must be equal to %s.", n, a1);
	}

	/** Predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean notEqual(long n, long a1) {
		return n != a1;
	}

	/** "Special" predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String notEqual$(long n, long a1) {
		return (n != a1) ? null : String.format("%s must NOT be equal to %s.", n, a1);
	}

	/** Predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean equal(float n, float a1) {
		return n == a1;
	}

	/** "Special" predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String equal$(float n, float a1) {
		return (n == a1) ? null : String.format("%s must be equal to %s.", n, a1);
	}

	/** Predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean notEqual(float n, float a1) {
		return n != a1;
	}

	/** "Special" predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String notEqual$(float n, float a1) {
		return (n != a1) ? null : String.format("%s must NOT be equal to %s.", n, a1);
	}

	/** Predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean equal(double n, double a1) {
		return n == a1;
	}

	/** "Special" predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String equal$(double n, double a1) {
		return (n == a1) ? null : String.format("%s must be equal to %s.", n, a1);
	}

	/** Predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean notEqual(double n, double a1) {
		return n != a1;
	}

	/** "Special" predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String notEqual$(double n, double a1) {
		return (n != a1) ? null : String.format("%s must NOT be equal to %s.", n, a1);
	}

	/** Predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean equal(char n, char a1) {
		return n == a1;
	}

	/** "Special" predicate: %s must be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String equal$(char n, char a1) {
		return (n == a1) ? null : String.format("%s must be equal to %s.", n, a1);
	}

	/** Predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static boolean notEqual(char n, char a1) {
		return n != a1;
	}

	/** "Special" predicate: %s must NOT be equal to %s. Available in {@link P}, {@link Is}, {@link Be}, {@link Does}.*/
	public static String notEqual$(char n, char a1) {
		return (n != a1) ? null : String.format("%s must NOT be equal to %s.", n, a1);
	}

	// </editor-fold>

	// <editor-fold desc="arrays">

	// </editor-fold>

	// <editor-fold desc="collections">

	/** Predicate: Collection <%s> must contain element <%s>. Available in {@link P}, {@link Does}.*/
	public static <T> boolean contain(@Nonnull Collection<T> collection, T element) {
		Null.nonNullArg(collection, "collection");
		return collection.contains(element);
	}

	/** "Special" predicate: Collection <%s> must contain element <%s>. Available in {@link P}, {@link Does}.*/
	public static <T> String contain$(@Nonnull Collection<T> collection, T element) {
		Null.nonNullArg(collection, "collection");
		return (collection.contains(element)) ? null : String.format("Collection <%s> must contain element <%s>.", collection, element);
	}

	/** Predicate: Collection <%s> must NOT contain element <%s>. Available in {@link P}, {@link Does}.*/
	public static <T> boolean notContain(@Nonnull Collection<T> collection, T element) {
		Null.nonNullArg(collection, "collection");
		return !(collection.contains(element));
	}

	/** "Special" predicate: Collection <%s> must NOT contain element <%s>. Available in {@link P}, {@link Does}.*/
	public static <T> String notContain$(@Nonnull Collection<T> collection, T element) {
		Null.nonNullArg(collection, "collection");
		return !(collection.contains(element)) ? null : String.format("Collection <%s> must NOT contain element <%s>.", collection, element);
	}

	/** Predicate: Map <%s> must contain key <%s>. Available in {@link P}, {@link Does}.*/
	public static <K> boolean containKey(@Nonnull Map<K, ?> map, K key) {
		Null.nonNullArg(map, "map");
		return map.containsKey(key);
	}

	/** "Special" predicate: Map <%s> must contain key <%s>. Available in {@link P}, {@link Does}.*/
	public static <K> String containKey$(@Nonnull Map<K, ?> map, K key) {
		Null.nonNullArg(map, "map");
		return (map.containsKey(key)) ? null : String.format("Map <%s> must contain key <%s>.", map, key);
	}

	/** Predicate: Map <%s> must NOT contain key <%s>. Available in {@link P}, {@link Does}.*/
	public static <K> boolean notContainKey(@Nonnull Map<K, ?> map, K key) {
		Null.nonNullArg(map, "map");
		return !(map.containsKey(key));
	}

	/** "Special" predicate: Map <%s> must NOT contain key <%s>. Available in {@link P}, {@link Does}.*/
	public static <K> String notContainKey$(@Nonnull Map<K, ?> map, K key) {
		Null.nonNullArg(map, "map");
		return !(map.containsKey(key)) ? null : String.format("Map <%s> must NOT contain key <%s>.", map, key);
	}

	// </editor-fold>

	// <editor-fold desc="object derivatives">

	// </editor-fold>

	// <editor-fold desc="Throwables">

	// </editor-fold>

	// <editor-fold desc="have">

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveBool(@Nonnull LPredicate<K> extractor, @Nonnull LLogicalOperator operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.apply(extractor.test(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> haveBool$(@Nonnull LPredicate<K> extractor, @Nonnull LBoolFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.test(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveBool(@Nonnull LPredicate<K> extractor, boolean a2, @Nonnull LLogicalBinaryOperator operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.apply(extractor.test(k), a2);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> haveBool$(@Nonnull LPredicate<K> extractor, boolean a2, @Nonnull LBiBoolFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.test(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveBool(@Nonnull LPredicate<K> extractor, @Nonnull LLogicalBinaryOperator operator, boolean a2) {
		return haveBool(extractor, a2, operator);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LFunction<K, String> haveBool$(@Nonnull LPredicate<K> extractor, @Nonnull LBiBoolFunction<String> specialPredicate, boolean a2) {
		return haveBool$(extractor, a2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveBool(@Nonnull LPredicate<K> extractor, boolean a2, boolean a3, @Nonnull LLogicalTernaryOperator operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.apply(extractor.test(k), a2, a3);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> haveBool$(@Nonnull LPredicate<K> extractor, boolean a2, boolean a3, @Nonnull LTriBoolFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.test(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveBool(@Nonnull LPredicate<K> extractor, @Nonnull LLogicalTernaryOperator operator, boolean a2, boolean a3) {
		return haveBool(extractor, a2, a3, operator);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LFunction<K, String> haveBool$(@Nonnull LPredicate<K> extractor, @Nonnull LTriBoolFunction<String> specialPredicate, boolean a2, boolean a3) {
		return haveBool$(extractor, a2, a3, specialPredicate);
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
	public static @Nonnull <K, V> LPredicate<K> haveBool(@Nonnull LPredicate<K> extractor, V v, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.testBoolObj(extractor.test(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LFunction<K, String> haveBool$(@Nonnull LPredicate<K> extractor, V v, @Nonnull LObjBoolFunction.LBoolObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyBoolObj(extractor.test(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LPredicate<K> haveBool(@Nonnull LPredicate<K> extractor, @Nonnull LObjBoolPredicate.LBoolObjPred<? super V> operator, V v) {
		return haveBool(extractor, v, operator);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LFunction<K, String> haveBool$(@Nonnull LPredicate<K> extractor, @Nonnull LObjBoolFunction.LBoolObjFunc<? super V, String> specialPredicate, V v) {
		return haveBool$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LPredicate<K> haveBool$WithBool(@Nonnull LPredicate<K> extractor, V1 with1, @Nonnull LObjBoolPredicate<? super V1> operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.test(with1, extractor.test(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LFunction<K, String> haveBool$WithBool$(@Nonnull LPredicate<K> extractor, V1 with1, @Nonnull LObjBoolFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.test(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LPredicate<K> haveBool$WithBool(@Nonnull LPredicate<K> extractor, @Nonnull LObjBoolPredicate<? super V1> operator, V1 with1) {
		return haveBool$WithBool(extractor, with1, operator);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LFunction<K, String> haveBool$WithBool$(@Nonnull LPredicate<K> extractor, @Nonnull LObjBoolFunction<? super V1, String> specialPredicate, V1 with1) {
		return haveBool$WithBool$(extractor, with1, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveBool$With(@Nonnull LPredicate<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(operator, "operator");
		return k -> operator.test(with1, with2, extractor.test(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveBool$With$(@Nonnull LPredicate<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjBoolFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.test(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveBool$With(@Nonnull LPredicate<K> extractor, @Nonnull LBiObjBoolPredicate<? super V1, ? super V2> operator, V1 with1, V2 with2) {
		return haveBool$With(extractor, with1, with2, operator);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveBool$With$(@Nonnull LPredicate<K> extractor, @Nonnull LBiObjBoolFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return haveBool$With$(extractor, with1, with2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, @Nonnull LPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LFunction<K, String> have$(@Nonnull LFunction<K, T> extractor, @Nonnull LFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> uniHave(@Nonnull LFunction<K, T> extractor, T a2, @Nonnull LBiPredicate<? super T, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LFunction<K, String> uniHave$(@Nonnull LFunction<K, T> extractor, T a2, @Nonnull LBiFunction<? super T, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> uniHave(@Nonnull LFunction<K, T> extractor, @Nonnull LBiPredicate<? super T, ? super T> predicate, T a2) {
		return uniHave(extractor, a2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LFunction<K, String> uniHave$(@Nonnull LFunction<K, T> extractor, @Nonnull LBiFunction<? super T, ? super T, String> specialPredicate, T a2) {
		return uniHave$(extractor, a2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> uniHave(@Nonnull LFunction<K, T> extractor, T a2, T a3, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2, a3);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LFunction<K, String> uniHave$(@Nonnull LFunction<K, T> extractor, T a2, T a3, @Nonnull LTriFunction<? super T, ? super T, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> uniHave(@Nonnull LFunction<K, T> extractor, @Nonnull LTriPredicate<? super T, ? super T, ? super T> predicate, T a2, T a3) {
		return uniHave(extractor, a2, a3, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LFunction<K, String> uniHave$(@Nonnull LFunction<K, T> extractor, @Nonnull LTriFunction<? super T, ? super T, ? super T, String> specialPredicate, T a2, T a3) {
		return uniHave$(extractor, a2, a3, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> uniHave(@Nonnull LFunction<K, T> extractor, T a2, T a3, T a4, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2, a3, a4);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LFunction<K, String> uniHave$(@Nonnull LFunction<K, T> extractor, T a2, T a3, T a4, @Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2, a3, a4);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> uniHave(@Nonnull LFunction<K, T> extractor, @Nonnull LQuadPredicate<? super T, ? super T, ? super T, ? super T> predicate, T a2, T a3, T a4) {
		return uniHave(extractor, a2, a3, a4, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LFunction<K, String> uniHave$(@Nonnull LFunction<K, T> extractor, @Nonnull LQuadFunction<? super T, ? super T, ? super T, ? super T, String> specialPredicate, T a2, T a3, T a4) {
		return uniHave$(extractor, a2, a3, a4, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> have$Bool(@Nonnull LFunction<K, T> extractor, boolean v, @Nonnull LObjBoolPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LFunction<K, String> have$Bool$(@Nonnull LFunction<K, T> extractor, boolean v, @Nonnull LObjBoolFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> have$Bool(@Nonnull LFunction<K, T> extractor, @Nonnull LObjBoolPredicate<? super T> predicate, boolean v) {
		return have$Bool(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LFunction<K, String> have$Bool$(@Nonnull LFunction<K, T> extractor, @Nonnull LObjBoolFunction<? super T, String> specialPredicate, boolean v) {
		return have$Bool$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> have$Byte(@Nonnull LFunction<K, T> extractor, byte v, @Nonnull LObjBytePredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LFunction<K, String> have$Byte$(@Nonnull LFunction<K, T> extractor, byte v, @Nonnull LObjByteFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> have$Byte(@Nonnull LFunction<K, T> extractor, @Nonnull LObjBytePredicate<? super T> predicate, byte v) {
		return have$Byte(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LFunction<K, String> have$Byte$(@Nonnull LFunction<K, T> extractor, @Nonnull LObjByteFunction<? super T, String> specialPredicate, byte v) {
		return have$Byte$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> have$Dbl(@Nonnull LFunction<K, T> extractor, double v, @Nonnull LObjDblPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LFunction<K, String> have$Dbl$(@Nonnull LFunction<K, T> extractor, double v, @Nonnull LObjDblFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> have$Dbl(@Nonnull LFunction<K, T> extractor, @Nonnull LObjDblPredicate<? super T> predicate, double v) {
		return have$Dbl(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LFunction<K, String> have$Dbl$(@Nonnull LFunction<K, T> extractor, @Nonnull LObjDblFunction<? super T, String> specialPredicate, double v) {
		return have$Dbl$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> have$Char(@Nonnull LFunction<K, T> extractor, char v, @Nonnull LObjCharPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LFunction<K, String> have$Char$(@Nonnull LFunction<K, T> extractor, char v, @Nonnull LObjCharFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> have$Char(@Nonnull LFunction<K, T> extractor, @Nonnull LObjCharPredicate<? super T> predicate, char v) {
		return have$Char(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LFunction<K, String> have$Char$(@Nonnull LFunction<K, T> extractor, @Nonnull LObjCharFunction<? super T, String> specialPredicate, char v) {
		return have$Char$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> have$Srt(@Nonnull LFunction<K, T> extractor, short v, @Nonnull LObjSrtPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LFunction<K, String> have$Srt$(@Nonnull LFunction<K, T> extractor, short v, @Nonnull LObjSrtFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> have$Srt(@Nonnull LFunction<K, T> extractor, @Nonnull LObjSrtPredicate<? super T> predicate, short v) {
		return have$Srt(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LFunction<K, String> have$Srt$(@Nonnull LFunction<K, T> extractor, @Nonnull LObjSrtFunction<? super T, String> specialPredicate, short v) {
		return have$Srt$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> have$Flt(@Nonnull LFunction<K, T> extractor, float v, @Nonnull LObjFltPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LFunction<K, String> have$Flt$(@Nonnull LFunction<K, T> extractor, float v, @Nonnull LObjFltFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> have$Flt(@Nonnull LFunction<K, T> extractor, @Nonnull LObjFltPredicate<? super T> predicate, float v) {
		return have$Flt(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LFunction<K, String> have$Flt$(@Nonnull LFunction<K, T> extractor, @Nonnull LObjFltFunction<? super T, String> specialPredicate, float v) {
		return have$Flt$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> have$Int(@Nonnull LFunction<K, T> extractor, int v, @Nonnull LObjIntPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LFunction<K, String> have$Int$(@Nonnull LFunction<K, T> extractor, int v, @Nonnull LOiFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> have$Int(@Nonnull LFunction<K, T> extractor, @Nonnull LObjIntPredicate<? super T> predicate, int v) {
		return have$Int(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LFunction<K, String> have$Int$(@Nonnull LFunction<K, T> extractor, @Nonnull LOiFunction<? super T, String> specialPredicate, int v) {
		return have$Int$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> have$Long(@Nonnull LFunction<K, T> extractor, long v, @Nonnull LObjLongPredicate<? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LFunction<K, String> have$Long$(@Nonnull LFunction<K, T> extractor, long v, @Nonnull LObjLongFunction<? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> have$Long(@Nonnull LFunction<K, T> extractor, @Nonnull LObjLongPredicate<? super T> predicate, long v) {
		return have$Long(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LFunction<K, String> have$Long$(@Nonnull LFunction<K, T> extractor, @Nonnull LObjLongFunction<? super T, String> specialPredicate, long v) {
		return have$Long$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T, V> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, V v, @Nonnull LBiPredicate<? super T, ? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T, V> LFunction<K, String> have$(@Nonnull LFunction<K, T> extractor, V v, @Nonnull LBiFunction<? super T, ? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T, V> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, @Nonnull LBiPredicate<? super T, ? super V> predicate, V v) {
		return have(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T, V> LFunction<K, String> have$(@Nonnull LFunction<K, T> extractor, @Nonnull LBiFunction<? super T, ? super V, String> specialPredicate, V v) {
		return have$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T, V2, V3> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, V2 a2, V3 a3, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2, a3);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T, V2, V3> LFunction<K, String> have$(@Nonnull LFunction<K, T> extractor, V2 a2, V3 a3, @Nonnull LTriFunction<? super T, ? super V2, ? super V3, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2, a3);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T, V2, V3> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, @Nonnull LTriPredicate<? super T, ? super V2, ? super V3> predicate, V2 a2, V3 a3) {
		return have(extractor, a2, a3, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T, V2, V3> LFunction<K, String> have$(@Nonnull LFunction<K, T> extractor, @Nonnull LTriFunction<? super T, ? super V2, ? super V3, String> specialPredicate, V2 a2, V3 a3) {
		return have$(extractor, a2, a3, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T, V2, V3, V4> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, V2 a2, V3 a3, V4 a4, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.apply(k), a2, a3, a4);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T, V2, V3, V4> LFunction<K, String> have$(@Nonnull LFunction<K, T> extractor, V2 a2, V3 a3, V4 a4, @Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.apply(k), a2, a3, a4);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T, V2, V3, V4> LPredicate<K> have(@Nonnull LFunction<K, T> extractor, @Nonnull LQuadPredicate<? super T, ? super V2, ? super V3, ? super V4> predicate, V2 a2, V3 a3, V4 a4) {
		return have(extractor, a2, a3, a4, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T, V2, V3, V4> LFunction<K, String> have$(@Nonnull LFunction<K, T> extractor, @Nonnull LQuadFunction<? super T, ? super V2, ? super V3, ? super V4, String> specialPredicate, V2 a2, V3 a3, V4 a4) {
		return have$(extractor, a2, a3, a4, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T, V1> LPredicate<K> have$With(@Nonnull LFunction<K, T> extractor, V1 with1, @Nonnull LBiPredicate<? super V1, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.apply(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T, V1> LFunction<K, String> have$With$(@Nonnull LFunction<K, T> extractor, V1 with1, @Nonnull LBiFunction<? super V1, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.apply(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T, V1> LPredicate<K> have$With(@Nonnull LFunction<K, T> extractor, @Nonnull LBiPredicate<? super V1, ? super T> predicate, V1 with1) {
		return have$With(extractor, with1, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T, V1> LFunction<K, String> have$With$(@Nonnull LFunction<K, T> extractor, @Nonnull LBiFunction<? super V1, ? super T, String> specialPredicate, V1 with1) {
		return have$With$(extractor, with1, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T, V1, V2> LPredicate<K> have$With(@Nonnull LFunction<K, T> extractor, V1 with1, V2 with2, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.apply(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T, V1, V2> LFunction<K, String> have$With$(@Nonnull LFunction<K, T> extractor, V1 with1, V2 with2, @Nonnull LTriFunction<? super V1, ? super V2, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.apply(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T, V1, V2> LPredicate<K> have$With(@Nonnull LFunction<K, T> extractor, @Nonnull LTriPredicate<? super V1, ? super V2, ? super T> predicate, V1 with1, V2 with2) {
		return have$With(extractor, with1, with2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T, V1, V2> LFunction<K, String> have$With$(@Nonnull LFunction<K, T> extractor, @Nonnull LTriFunction<? super V1, ? super V2, ? super T, String> specialPredicate, V1 with1, V2 with2) {
		return have$With$(extractor, with1, with2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LPredicate<K> uniHave$With(@Nonnull LFunction<K, T> extractor, T with, @Nonnull LBiPredicate<? super T, ? super T> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with, extractor.apply(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, T> LFunction<K, String> uniHave$With$(@Nonnull LFunction<K, T> extractor, T with, @Nonnull LBiFunction<? super T, ? super T, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with, extractor.apply(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LPredicate<K> uniHave$With(@Nonnull LFunction<K, T> extractor, @Nonnull LBiPredicate<? super T, ? super T> predicate, T with) {
		return uniHave$With(extractor, with, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, T> LFunction<K, String> uniHave$With$(@Nonnull LFunction<K, T> extractor, @Nonnull LBiFunction<? super T, ? super T, String> specialPredicate, T with) {
		return uniHave$With$(extractor, with, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveByte(@Nonnull LToByteFunction<K> extractor, @Nonnull LBytePredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsByte(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> haveByte$(@Nonnull LToByteFunction<K> extractor, @Nonnull LByteFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsByte(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveByte(@Nonnull LToByteFunction<K> extractor, byte a2, @Nonnull LBiBytePredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsByte(k), a2);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> haveByte$(@Nonnull LToByteFunction<K> extractor, byte a2, @Nonnull LBiByteFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsByte(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveByte(@Nonnull LToByteFunction<K> extractor, @Nonnull LBiBytePredicate predicate, byte a2) {
		return haveByte(extractor, a2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LFunction<K, String> haveByte$(@Nonnull LToByteFunction<K> extractor, @Nonnull LBiByteFunction<String> specialPredicate, byte a2) {
		return haveByte$(extractor, a2, specialPredicate);
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
	public static @Nonnull <K, V> LPredicate<K> haveByte(@Nonnull LToByteFunction<K> extractor, V v, @Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testByteObj(extractor.applyAsByte(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LFunction<K, String> haveByte$(@Nonnull LToByteFunction<K> extractor, V v, @Nonnull LObjByteFunction.LByteObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyByteObj(extractor.applyAsByte(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LPredicate<K> haveByte(@Nonnull LToByteFunction<K> extractor, @Nonnull LObjBytePredicate.LByteObjPred<? super V> predicate, V v) {
		return haveByte(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LFunction<K, String> haveByte$(@Nonnull LToByteFunction<K> extractor, @Nonnull LObjByteFunction.LByteObjFunc<? super V, String> specialPredicate, V v) {
		return haveByte$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LPredicate<K> haveByte$WithByte(@Nonnull LToByteFunction<K> extractor, V1 with1, @Nonnull LObjBytePredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsByte(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LFunction<K, String> haveByte$WithByte$(@Nonnull LToByteFunction<K> extractor, V1 with1, @Nonnull LObjByteFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsByte(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LPredicate<K> haveByte$WithByte(@Nonnull LToByteFunction<K> extractor, @Nonnull LObjBytePredicate<? super V1> predicate, V1 with1) {
		return haveByte$WithByte(extractor, with1, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LFunction<K, String> haveByte$WithByte$(@Nonnull LToByteFunction<K> extractor, @Nonnull LObjByteFunction<? super V1, String> specialPredicate, V1 with1) {
		return haveByte$WithByte$(extractor, with1, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveByte$With(@Nonnull LToByteFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjBytePredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsByte(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveByte$With$(@Nonnull LToByteFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjByteFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsByte(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveByte$With(@Nonnull LToByteFunction<K> extractor, @Nonnull LBiObjBytePredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return haveByte$With(extractor, with1, with2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveByte$With$(@Nonnull LToByteFunction<K> extractor, @Nonnull LBiObjByteFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return haveByte$With$(extractor, with1, with2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveDbl(@Nonnull LToDblFunction<K> extractor, @Nonnull LDblPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsDbl(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> haveDbl$(@Nonnull LToDblFunction<K> extractor, @Nonnull LDblFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsDbl(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveDbl(@Nonnull LToDblFunction<K> extractor, double a2, @Nonnull LBiDblPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsDbl(k), a2);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> haveDbl$(@Nonnull LToDblFunction<K> extractor, double a2, @Nonnull LBiDblFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsDbl(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveDbl(@Nonnull LToDblFunction<K> extractor, @Nonnull LBiDblPredicate predicate, double a2) {
		return haveDbl(extractor, a2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LFunction<K, String> haveDbl$(@Nonnull LToDblFunction<K> extractor, @Nonnull LBiDblFunction<String> specialPredicate, double a2) {
		return haveDbl$(extractor, a2, specialPredicate);
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
	public static @Nonnull <K, V> LPredicate<K> haveDbl(@Nonnull LToDblFunction<K> extractor, V v, @Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testDblObj(extractor.applyAsDbl(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LFunction<K, String> haveDbl$(@Nonnull LToDblFunction<K> extractor, V v, @Nonnull LObjDblFunction.LDblObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyDblObj(extractor.applyAsDbl(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LPredicate<K> haveDbl(@Nonnull LToDblFunction<K> extractor, @Nonnull LObjDblPredicate.LDblObjPred<? super V> predicate, V v) {
		return haveDbl(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LFunction<K, String> haveDbl$(@Nonnull LToDblFunction<K> extractor, @Nonnull LObjDblFunction.LDblObjFunc<? super V, String> specialPredicate, V v) {
		return haveDbl$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LPredicate<K> haveDbl$WithDbl(@Nonnull LToDblFunction<K> extractor, V1 with1, @Nonnull LObjDblPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsDbl(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LFunction<K, String> haveDbl$WithDbl$(@Nonnull LToDblFunction<K> extractor, V1 with1, @Nonnull LObjDblFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsDbl(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LPredicate<K> haveDbl$WithDbl(@Nonnull LToDblFunction<K> extractor, @Nonnull LObjDblPredicate<? super V1> predicate, V1 with1) {
		return haveDbl$WithDbl(extractor, with1, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LFunction<K, String> haveDbl$WithDbl$(@Nonnull LToDblFunction<K> extractor, @Nonnull LObjDblFunction<? super V1, String> specialPredicate, V1 with1) {
		return haveDbl$WithDbl$(extractor, with1, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveDbl$With(@Nonnull LToDblFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjDblPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsDbl(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveDbl$With$(@Nonnull LToDblFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjDblFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsDbl(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveDbl$With(@Nonnull LToDblFunction<K> extractor, @Nonnull LBiObjDblPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return haveDbl$With(extractor, with1, with2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveDbl$With$(@Nonnull LToDblFunction<K> extractor, @Nonnull LBiObjDblFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return haveDbl$With$(extractor, with1, with2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveChar(@Nonnull LToCharFunction<K> extractor, @Nonnull LCharPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsChar(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> haveChar$(@Nonnull LToCharFunction<K> extractor, @Nonnull LCharFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsChar(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveChar(@Nonnull LToCharFunction<K> extractor, char a2, @Nonnull LBiCharPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsChar(k), a2);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> haveChar$(@Nonnull LToCharFunction<K> extractor, char a2, @Nonnull LBiCharFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsChar(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveChar(@Nonnull LToCharFunction<K> extractor, @Nonnull LBiCharPredicate predicate, char a2) {
		return haveChar(extractor, a2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LFunction<K, String> haveChar$(@Nonnull LToCharFunction<K> extractor, @Nonnull LBiCharFunction<String> specialPredicate, char a2) {
		return haveChar$(extractor, a2, specialPredicate);
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
	public static @Nonnull <K, V> LPredicate<K> haveChar(@Nonnull LToCharFunction<K> extractor, V v, @Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testCharObj(extractor.applyAsChar(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LFunction<K, String> haveChar$(@Nonnull LToCharFunction<K> extractor, V v, @Nonnull LObjCharFunction.LCharObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyCharObj(extractor.applyAsChar(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LPredicate<K> haveChar(@Nonnull LToCharFunction<K> extractor, @Nonnull LObjCharPredicate.LCharObjPred<? super V> predicate, V v) {
		return haveChar(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LFunction<K, String> haveChar$(@Nonnull LToCharFunction<K> extractor, @Nonnull LObjCharFunction.LCharObjFunc<? super V, String> specialPredicate, V v) {
		return haveChar$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LPredicate<K> haveChar$WithChar(@Nonnull LToCharFunction<K> extractor, V1 with1, @Nonnull LObjCharPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsChar(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LFunction<K, String> haveChar$WithChar$(@Nonnull LToCharFunction<K> extractor, V1 with1, @Nonnull LObjCharFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsChar(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LPredicate<K> haveChar$WithChar(@Nonnull LToCharFunction<K> extractor, @Nonnull LObjCharPredicate<? super V1> predicate, V1 with1) {
		return haveChar$WithChar(extractor, with1, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LFunction<K, String> haveChar$WithChar$(@Nonnull LToCharFunction<K> extractor, @Nonnull LObjCharFunction<? super V1, String> specialPredicate, V1 with1) {
		return haveChar$WithChar$(extractor, with1, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveChar$With(@Nonnull LToCharFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjCharPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsChar(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveChar$With$(@Nonnull LToCharFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjCharFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsChar(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveChar$With(@Nonnull LToCharFunction<K> extractor, @Nonnull LBiObjCharPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return haveChar$With(extractor, with1, with2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveChar$With$(@Nonnull LToCharFunction<K> extractor, @Nonnull LBiObjCharFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return haveChar$With$(extractor, with1, with2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveSrt(@Nonnull LToSrtFunction<K> extractor, @Nonnull LSrtPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsSrt(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> haveSrt$(@Nonnull LToSrtFunction<K> extractor, @Nonnull LSrtFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsSrt(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveSrt(@Nonnull LToSrtFunction<K> extractor, short a2, @Nonnull LBiSrtPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsSrt(k), a2);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> haveSrt$(@Nonnull LToSrtFunction<K> extractor, short a2, @Nonnull LBiSrtFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsSrt(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveSrt(@Nonnull LToSrtFunction<K> extractor, @Nonnull LBiSrtPredicate predicate, short a2) {
		return haveSrt(extractor, a2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LFunction<K, String> haveSrt$(@Nonnull LToSrtFunction<K> extractor, @Nonnull LBiSrtFunction<String> specialPredicate, short a2) {
		return haveSrt$(extractor, a2, specialPredicate);
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
	public static @Nonnull <K, V> LPredicate<K> haveSrt(@Nonnull LToSrtFunction<K> extractor, V v, @Nonnull LObjSrtPredicate.LSrtObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testSrtObj(extractor.applyAsSrt(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LFunction<K, String> haveSrt$(@Nonnull LToSrtFunction<K> extractor, V v, @Nonnull LObjSrtFunction.LSrtObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applySrtObj(extractor.applyAsSrt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LPredicate<K> haveSrt(@Nonnull LToSrtFunction<K> extractor, @Nonnull LObjSrtPredicate.LSrtObjPred<? super V> predicate, V v) {
		return haveSrt(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LFunction<K, String> haveSrt$(@Nonnull LToSrtFunction<K> extractor, @Nonnull LObjSrtFunction.LSrtObjFunc<? super V, String> specialPredicate, V v) {
		return haveSrt$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LPredicate<K> haveSrt$WithSrt(@Nonnull LToSrtFunction<K> extractor, V1 with1, @Nonnull LObjSrtPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsSrt(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LFunction<K, String> haveSrt$WithSrt$(@Nonnull LToSrtFunction<K> extractor, V1 with1, @Nonnull LObjSrtFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsSrt(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LPredicate<K> haveSrt$WithSrt(@Nonnull LToSrtFunction<K> extractor, @Nonnull LObjSrtPredicate<? super V1> predicate, V1 with1) {
		return haveSrt$WithSrt(extractor, with1, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LFunction<K, String> haveSrt$WithSrt$(@Nonnull LToSrtFunction<K> extractor, @Nonnull LObjSrtFunction<? super V1, String> specialPredicate, V1 with1) {
		return haveSrt$WithSrt$(extractor, with1, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveSrt$With(@Nonnull LToSrtFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjSrtPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsSrt(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveSrt$With$(@Nonnull LToSrtFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjSrtFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsSrt(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveSrt$With(@Nonnull LToSrtFunction<K> extractor, @Nonnull LBiObjSrtPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return haveSrt$With(extractor, with1, with2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveSrt$With$(@Nonnull LToSrtFunction<K> extractor, @Nonnull LBiObjSrtFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return haveSrt$With$(extractor, with1, with2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveFlt(@Nonnull LToFltFunction<K> extractor, @Nonnull LFltPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsFlt(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> haveFlt$(@Nonnull LToFltFunction<K> extractor, @Nonnull LFltFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsFlt(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveFlt(@Nonnull LToFltFunction<K> extractor, float a2, @Nonnull LBiFltPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsFlt(k), a2);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> haveFlt$(@Nonnull LToFltFunction<K> extractor, float a2, @Nonnull LBiFltFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsFlt(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveFlt(@Nonnull LToFltFunction<K> extractor, @Nonnull LBiFltPredicate predicate, float a2) {
		return haveFlt(extractor, a2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LFunction<K, String> haveFlt$(@Nonnull LToFltFunction<K> extractor, @Nonnull LBiFltFunction<String> specialPredicate, float a2) {
		return haveFlt$(extractor, a2, specialPredicate);
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
	public static @Nonnull <K, V> LPredicate<K> haveFlt(@Nonnull LToFltFunction<K> extractor, V v, @Nonnull LObjFltPredicate.LFltObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testFltObj(extractor.applyAsFlt(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LFunction<K, String> haveFlt$(@Nonnull LToFltFunction<K> extractor, V v, @Nonnull LObjFltFunction.LFltObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyFltObj(extractor.applyAsFlt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LPredicate<K> haveFlt(@Nonnull LToFltFunction<K> extractor, @Nonnull LObjFltPredicate.LFltObjPred<? super V> predicate, V v) {
		return haveFlt(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LFunction<K, String> haveFlt$(@Nonnull LToFltFunction<K> extractor, @Nonnull LObjFltFunction.LFltObjFunc<? super V, String> specialPredicate, V v) {
		return haveFlt$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LPredicate<K> haveFlt$WithFlt(@Nonnull LToFltFunction<K> extractor, V1 with1, @Nonnull LObjFltPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsFlt(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LFunction<K, String> haveFlt$WithFlt$(@Nonnull LToFltFunction<K> extractor, V1 with1, @Nonnull LObjFltFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsFlt(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LPredicate<K> haveFlt$WithFlt(@Nonnull LToFltFunction<K> extractor, @Nonnull LObjFltPredicate<? super V1> predicate, V1 with1) {
		return haveFlt$WithFlt(extractor, with1, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LFunction<K, String> haveFlt$WithFlt$(@Nonnull LToFltFunction<K> extractor, @Nonnull LObjFltFunction<? super V1, String> specialPredicate, V1 with1) {
		return haveFlt$WithFlt$(extractor, with1, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveFlt$With(@Nonnull LToFltFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjFltPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsFlt(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveFlt$With$(@Nonnull LToFltFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjFltFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsFlt(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveFlt$With(@Nonnull LToFltFunction<K> extractor, @Nonnull LBiObjFltPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return haveFlt$With(extractor, with1, with2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveFlt$With$(@Nonnull LToFltFunction<K> extractor, @Nonnull LBiObjFltFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return haveFlt$With$(extractor, with1, with2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveInt(@Nonnull LToIntFunction<K> extractor, @Nonnull LIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsInt(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> haveInt$(@Nonnull LToIntFunction<K> extractor, @Nonnull LIntFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsInt(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveInt(@Nonnull LToIntFunction<K> extractor, int a2, @Nonnull LBiIntPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsInt(k), a2);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> haveInt$(@Nonnull LToIntFunction<K> extractor, int a2, @Nonnull LBiIntFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsInt(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveInt(@Nonnull LToIntFunction<K> extractor, @Nonnull LBiIntPredicate predicate, int a2) {
		return haveInt(extractor, a2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LFunction<K, String> haveInt$(@Nonnull LToIntFunction<K> extractor, @Nonnull LBiIntFunction<String> specialPredicate, int a2) {
		return haveInt$(extractor, a2, specialPredicate);
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
	public static @Nonnull <K, V> LPredicate<K> haveInt(@Nonnull LToIntFunction<K> extractor, V v, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testIntObj(extractor.applyAsInt(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LFunction<K, String> haveInt$(@Nonnull LToIntFunction<K> extractor, V v, @Nonnull LOiFunction.LIntObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyIntObj(extractor.applyAsInt(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LPredicate<K> haveInt(@Nonnull LToIntFunction<K> extractor, @Nonnull LObjIntPredicate.LIntObjPred<? super V> predicate, V v) {
		return haveInt(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LFunction<K, String> haveInt$(@Nonnull LToIntFunction<K> extractor, @Nonnull LOiFunction.LIntObjFunc<? super V, String> specialPredicate, V v) {
		return haveInt$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LPredicate<K> haveInt$WithInt(@Nonnull LToIntFunction<K> extractor, V1 with1, @Nonnull LObjIntPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsInt(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LFunction<K, String> haveInt$WithInt$(@Nonnull LToIntFunction<K> extractor, V1 with1, @Nonnull LOiFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsInt(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LPredicate<K> haveInt$WithInt(@Nonnull LToIntFunction<K> extractor, @Nonnull LObjIntPredicate<? super V1> predicate, V1 with1) {
		return haveInt$WithInt(extractor, with1, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LFunction<K, String> haveInt$WithInt$(@Nonnull LToIntFunction<K> extractor, @Nonnull LOiFunction<? super V1, String> specialPredicate, V1 with1) {
		return haveInt$WithInt$(extractor, with1, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveInt$With(@Nonnull LToIntFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsInt(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveInt$With$(@Nonnull LToIntFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjIntFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsInt(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveInt$With(@Nonnull LToIntFunction<K> extractor, @Nonnull LBiObjIntPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return haveInt$With(extractor, with1, with2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveInt$With$(@Nonnull LToIntFunction<K> extractor, @Nonnull LBiObjIntFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return haveInt$With$(extractor, with1, with2, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveLong(@Nonnull LToLongFunction<K> extractor, @Nonnull LLongPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsLong(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> haveLong$(@Nonnull LToLongFunction<K> extractor, @Nonnull LLongFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsLong(k));
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LPredicate<K> haveLong(@Nonnull LToLongFunction<K> extractor, long a2, @Nonnull LBiLongPredicate predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(extractor.applyAsLong(k), a2);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K> LFunction<K, String> haveLong$(@Nonnull LToLongFunction<K> extractor, long a2, @Nonnull LBiLongFunction<String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(extractor.applyAsLong(k), a2);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LPredicate<K> haveLong(@Nonnull LToLongFunction<K> extractor, @Nonnull LBiLongPredicate predicate, long a2) {
		return haveLong(extractor, a2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K> LFunction<K, String> haveLong$(@Nonnull LToLongFunction<K> extractor, @Nonnull LBiLongFunction<String> specialPredicate, long a2) {
		return haveLong$(extractor, a2, specialPredicate);
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
	public static @Nonnull <K, V> LPredicate<K> haveLong(@Nonnull LToLongFunction<K> extractor, V v, @Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.testLongObj(extractor.applyAsLong(k), v);
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V> LFunction<K, String> haveLong$(@Nonnull LToLongFunction<K> extractor, V v, @Nonnull LObjLongFunction.LLongObjFunc<? super V, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.applyLongObj(extractor.applyAsLong(k), v);
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LPredicate<K> haveLong(@Nonnull LToLongFunction<K> extractor, @Nonnull LObjLongPredicate.LLongObjPred<? super V> predicate, V v) {
		return haveLong(extractor, v, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V> LFunction<K, String> haveLong$(@Nonnull LToLongFunction<K> extractor, @Nonnull LObjLongFunction.LLongObjFunc<? super V, String> specialPredicate, V v) {
		return haveLong$(extractor, v, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LPredicate<K> haveLong$WithLong(@Nonnull LToLongFunction<K> extractor, V1 with1, @Nonnull LObjLongPredicate<? super V1> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, extractor.applyAsLong(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1> LFunction<K, String> haveLong$WithLong$(@Nonnull LToLongFunction<K> extractor, V1 with1, @Nonnull LObjLongFunction<? super V1, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, extractor.applyAsLong(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LPredicate<K> haveLong$WithLong(@Nonnull LToLongFunction<K> extractor, @Nonnull LObjLongPredicate<? super V1> predicate, V1 with1) {
		return haveLong$WithLong(extractor, with1, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1> LFunction<K, String> haveLong$WithLong$(@Nonnull LToLongFunction<K> extractor, @Nonnull LObjLongFunction<? super V1, String> specialPredicate, V1 with1) {
		return haveLong$WithLong$(extractor, with1, specialPredicate);
	}

	/** Variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveLong$With(@Nonnull LToLongFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjLongPredicate<? super V1, ? super V2> predicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(predicate, "predicate");
		return k -> predicate.test(with1, with2, extractor.applyAsLong(k));
	}

	/** "Special" variant 'method(..., (...) -> { ..long multiline definition.. })' */
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveLong$With$(@Nonnull LToLongFunction<K> extractor, V1 with1, V2 with2, @Nonnull LBiObjLongFunction<? super V1, ? super V2, String> specialPredicate) {
		Null.nonNullArg(extractor, "extractor");
		Null.nonNullArg(specialPredicate, "specialPredicate");
		return k -> specialPredicate.apply(with1, with2, extractor.applyAsLong(k));
	}

	/** Variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LPredicate<K> haveLong$With(@Nonnull LToLongFunction<K> extractor, @Nonnull LBiObjLongPredicate<? super V1, ? super V2> predicate, V1 with1, V2 with2) {
		return haveLong$With(extractor, with1, with2, predicate);
	}

	/** "Special" variant 'method(Is::equal, ...)' or 'method(Does::contain, ...)', etc.  */
	public static @Nonnull <K, V1, V2> LFunction<K, String> haveLong$With$(@Nonnull LToLongFunction<K> extractor, @Nonnull LBiObjLongFunction<? super V1, ? super V2, String> specialPredicate, V1 with1, V2 with2) {
		return haveLong$With$(extractor, with1, with2, specialPredicate);
	}

	// </editor-fold>

}
