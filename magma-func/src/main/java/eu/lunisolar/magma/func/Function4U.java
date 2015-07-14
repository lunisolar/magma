/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.func;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Comparator; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.exceptions.NestedException; //NOSONAR
import eu.lunisolar.magma.func.operator.unary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.binary.*; // NOSONAR
import eu.lunisolar.magma.func.operator.ternary.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.function.from.*; // NOSONAR
import eu.lunisolar.magma.func.function.to.*; // NOSONAR
import eu.lunisolar.magma.func.function.conversion.*; // NOSONAR
import eu.lunisolar.magma.func.predicate.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.bi.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.tri.*; // NOSONAR
import eu.lunisolar.magma.func.consumer.primitives.obj.*; // NOSONAR
import eu.lunisolar.magma.func.action.*; // NOSONAR

/**
 *
 */
public final class Function4U {

	// <editor-fold desc="no-instance constructor">
	private Function4U() {
	}
	// </editor-fold>

	/** You can use this as a reference method whenever nothing should be done. */
	public static <E> void doNothing() {
		// NOSONAR - there is something about 'doNothing' that common check rules do not cover ;)
	}

	/** You can use this as a reference method whenever nothing should be done. */
	public static <E> void doNothing(E element) {
		// NOSONAR - there is something about 'doNothing' that common check rules do not cover ;)
	}

	// <editor-fold desc="predicate::method">

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(T t) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(T t) {
		return false;
	}

	/** Returns TRUE. */
	public static <T1, T2> boolean alwaysTrue(T1 t1, T2 t2) {
		return true;
	}

	/** Returns FALSE. */
	public static <T1, T2> boolean alwaysFalse(T1 t1, T2 t2) {
		return false;
	}

	/** Returns TRUE. */
	public static <T1, T2, T3> boolean alwaysTrue(T1 t1, T2 t2, T3 t3) {
		return true;
	}

	/** Returns FALSE. */
	public static <T1, T2, T3> boolean alwaysFalse(T1 t1, T2 t2, T3 t3) {
		return false;
	}

	/** Returns TRUE. */
	public static boolean alwaysTrue(byte b) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(byte b) {
		return false;
	}

	/** Returns TRUE. */
	public static boolean alwaysTrue(short s) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(short s) {
		return false;
	}

	/** Returns TRUE. */
	public static boolean alwaysTrue(int i) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(int i) {
		return false;
	}

	/** Returns TRUE. */
	public static boolean alwaysTrue(long l) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(long l) {
		return false;
	}

	/** Returns TRUE. */
	public static boolean alwaysTrue(float f) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(float f) {
		return false;
	}

	/** Returns TRUE. */
	public static boolean alwaysTrue(double d) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(double d) {
		return false;
	}

	/** Returns TRUE. */
	public static boolean alwaysTrue(char c) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(char c) {
		return false;
	}

	/** Returns TRUE. */
	public static boolean alwaysTrue(byte b1, byte b2) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(byte b1, byte b2) {
		return false;
	}

	/** Returns TRUE. */
	public static boolean alwaysTrue(short s1, short s2) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(short s1, short s2) {
		return false;
	}

	/** Returns TRUE. */
	public static boolean alwaysTrue(int i1, int i2) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(int i1, int i2) {
		return false;
	}

	/** Returns TRUE. */
	public static boolean alwaysTrue(long l1, long l2) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(long l1, long l2) {
		return false;
	}

	/** Returns TRUE. */
	public static boolean alwaysTrue(float f1, float f2) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(float f1, float f2) {
		return false;
	}

	/** Returns TRUE. */
	public static boolean alwaysTrue(double d1, double d2) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(double d1, double d2) {
		return false;
	}

	/** Returns TRUE. */
	public static boolean alwaysTrue(char c1, char c2) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(char c1, char c2) {
		return false;
	}

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(T t, byte b) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(T t, byte b) {
		return false;
	}

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(T t, short s) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(T t, short s) {
		return false;
	}

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(T t, int i) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(T t, int i) {
		return false;
	}

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(T t, long l) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(T t, long l) {
		return false;
	}

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(T t, float f) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(T t, float f) {
		return false;
	}

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(T t, double d) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(T t, double d) {
		return false;
	}

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(T t, char c) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(T t, char c) {
		return false;
	}

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(T t, boolean b) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(T t, boolean b) {
		return false;
	}

	/** Returns TRUE. */
	public static <T1, T2> boolean alwaysTrue(T1 t1, T2 t2, byte b) {
		return true;
	}

	/** Returns FALSE. */
	public static <T1, T2> boolean alwaysFalse(T1 t1, T2 t2, byte b) {
		return false;
	}

	/** Returns TRUE. */
	public static <T1, T2> boolean alwaysTrue(T1 t1, T2 t2, short s) {
		return true;
	}

	/** Returns FALSE. */
	public static <T1, T2> boolean alwaysFalse(T1 t1, T2 t2, short s) {
		return false;
	}

	/** Returns TRUE. */
	public static <T1, T2> boolean alwaysTrue(T1 t1, T2 t2, int i) {
		return true;
	}

	/** Returns FALSE. */
	public static <T1, T2> boolean alwaysFalse(T1 t1, T2 t2, int i) {
		return false;
	}

	/** Returns TRUE. */
	public static <T1, T2> boolean alwaysTrue(T1 t1, T2 t2, long l) {
		return true;
	}

	/** Returns FALSE. */
	public static <T1, T2> boolean alwaysFalse(T1 t1, T2 t2, long l) {
		return false;
	}

	/** Returns TRUE. */
	public static <T1, T2> boolean alwaysTrue(T1 t1, T2 t2, float f) {
		return true;
	}

	/** Returns FALSE. */
	public static <T1, T2> boolean alwaysFalse(T1 t1, T2 t2, float f) {
		return false;
	}

	/** Returns TRUE. */
	public static <T1, T2> boolean alwaysTrue(T1 t1, T2 t2, double d) {
		return true;
	}

	/** Returns FALSE. */
	public static <T1, T2> boolean alwaysFalse(T1 t1, T2 t2, double d) {
		return false;
	}

	/** Returns TRUE. */
	public static <T1, T2> boolean alwaysTrue(T1 t1, T2 t2, char c) {
		return true;
	}

	/** Returns FALSE. */
	public static <T1, T2> boolean alwaysFalse(T1 t1, T2 t2, char c) {
		return false;
	}

	/** Returns TRUE. */
	public static <T1, T2> boolean alwaysTrue(T1 t1, T2 t2, boolean b) {
		return true;
	}

	/** Returns FALSE. */
	public static <T1, T2> boolean alwaysFalse(T1 t1, T2 t2, boolean b) {
		return false;
	}

	// </editor-fold>

	// <editor-fold desc="wrapper for lambda- JRE">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static <T> java.util.function.UnaryOperator l(final java.util.function.UnaryOperator<T> lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **unaryOperator** becomes ambiguous. */
	public static <T> java.util.function.UnaryOperator unaryOperator(final java.util.function.UnaryOperator<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static <T> java.util.function.BinaryOperator l(final java.util.function.BinaryOperator<T> lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **binaryOperator** becomes ambiguous. */
	public static <T> java.util.function.BinaryOperator binaryOperator(final java.util.function.BinaryOperator<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static java.util.function.IntUnaryOperator l(final java.util.function.IntUnaryOperator lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **intUnaryOperator** becomes ambiguous. */
	public static java.util.function.IntUnaryOperator intUnaryOperator(final java.util.function.IntUnaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static java.util.function.LongUnaryOperator l(final java.util.function.LongUnaryOperator lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **longUnaryOperator** becomes ambiguous. */
	public static java.util.function.LongUnaryOperator longUnaryOperator(final java.util.function.LongUnaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static java.util.function.DoubleUnaryOperator l(final java.util.function.DoubleUnaryOperator lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **doubleUnaryOperator** becomes ambiguous. */
	public static java.util.function.DoubleUnaryOperator doubleUnaryOperator(final java.util.function.DoubleUnaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static java.util.function.IntBinaryOperator l(final java.util.function.IntBinaryOperator lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **intBinaryOperator** becomes ambiguous. */
	public static java.util.function.IntBinaryOperator intBinaryOperator(final java.util.function.IntBinaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static java.util.function.LongBinaryOperator l(final java.util.function.LongBinaryOperator lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **longBinaryOperator** becomes ambiguous. */
	public static java.util.function.LongBinaryOperator longBinaryOperator(final java.util.function.LongBinaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static java.util.function.DoubleBinaryOperator l(final java.util.function.DoubleBinaryOperator lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **doubleBinaryOperator** becomes ambiguous. */
	public static java.util.function.DoubleBinaryOperator doubleBinaryOperator(final java.util.function.DoubleBinaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static <T, R> java.util.function.Function l(final java.util.function.Function<T, R> lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **function** becomes ambiguous. */
	public static <T, R> java.util.function.Function function(final java.util.function.Function<T, R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static <T1, T2, R> java.util.function.BiFunction l(final java.util.function.BiFunction<T1, T2, R> lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **biFunction** becomes ambiguous. */
	public static <T1, T2, R> java.util.function.BiFunction biFunction(final java.util.function.BiFunction<T1, T2, R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static <R> java.util.function.IntFunction l(final java.util.function.IntFunction<R> lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **intFunction** becomes ambiguous. */
	public static <R> java.util.function.IntFunction intFunction(final java.util.function.IntFunction<R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static <R> java.util.function.LongFunction l(final java.util.function.LongFunction<R> lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **longFunction** becomes ambiguous. */
	public static <R> java.util.function.LongFunction longFunction(final java.util.function.LongFunction<R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static <R> java.util.function.DoubleFunction l(final java.util.function.DoubleFunction<R> lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **doubleFunction** becomes ambiguous. */
	public static <R> java.util.function.DoubleFunction doubleFunction(final java.util.function.DoubleFunction<R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static <T> java.util.function.ToIntFunction l(final java.util.function.ToIntFunction<T> lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **toIntFunction** becomes ambiguous. */
	public static <T> java.util.function.ToIntFunction toIntFunction(final java.util.function.ToIntFunction<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static <T> java.util.function.ToLongFunction l(final java.util.function.ToLongFunction<T> lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **toLongFunction** becomes ambiguous. */
	public static <T> java.util.function.ToLongFunction toLongFunction(final java.util.function.ToLongFunction<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static <T> java.util.function.ToDoubleFunction l(final java.util.function.ToDoubleFunction<T> lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **toDoubleFunction** becomes ambiguous. */
	public static <T> java.util.function.ToDoubleFunction toDoubleFunction(final java.util.function.ToDoubleFunction<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static <T1, T2> java.util.function.ToIntBiFunction l(final java.util.function.ToIntBiFunction<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **toIntBiFunction** becomes ambiguous. */
	public static <T1, T2> java.util.function.ToIntBiFunction toIntBiFunction(final java.util.function.ToIntBiFunction<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static <T1, T2> java.util.function.ToLongBiFunction l(final java.util.function.ToLongBiFunction<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **toLongBiFunction** becomes ambiguous. */
	public static <T1, T2> java.util.function.ToLongBiFunction toLongBiFunction(final java.util.function.ToLongBiFunction<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static <T1, T2> java.util.function.ToDoubleBiFunction l(final java.util.function.ToDoubleBiFunction<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **toDoubleBiFunction** becomes ambiguous. */
	public static <T1, T2> java.util.function.ToDoubleBiFunction toDoubleBiFunction(final java.util.function.ToDoubleBiFunction<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static java.util.function.IntToLongFunction l(final java.util.function.IntToLongFunction lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **intToLongFunction** becomes ambiguous. */
	public static java.util.function.IntToLongFunction intToLongFunction(final java.util.function.IntToLongFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static java.util.function.IntToDoubleFunction l(final java.util.function.IntToDoubleFunction lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **intToDoubleFunction** becomes ambiguous. */
	public static java.util.function.IntToDoubleFunction intToDoubleFunction(final java.util.function.IntToDoubleFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static java.util.function.LongToIntFunction l(final java.util.function.LongToIntFunction lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **longToIntFunction** becomes ambiguous. */
	public static java.util.function.LongToIntFunction longToIntFunction(final java.util.function.LongToIntFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static java.util.function.LongToDoubleFunction l(final java.util.function.LongToDoubleFunction lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **longToDoubleFunction** becomes ambiguous. */
	public static java.util.function.LongToDoubleFunction longToDoubleFunction(final java.util.function.LongToDoubleFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static java.util.function.DoubleToIntFunction l(final java.util.function.DoubleToIntFunction lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **doubleToIntFunction** becomes ambiguous. */
	public static java.util.function.DoubleToIntFunction doubleToIntFunction(final java.util.function.DoubleToIntFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static java.util.function.DoubleToLongFunction l(final java.util.function.DoubleToLongFunction lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **doubleToLongFunction** becomes ambiguous. */
	public static java.util.function.DoubleToLongFunction doubleToLongFunction(final java.util.function.DoubleToLongFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static <T> java.util.function.Predicate l(final java.util.function.Predicate<T> lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **predicate** becomes ambiguous. */
	public static <T> java.util.function.Predicate predicate(final java.util.function.Predicate<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static <T1, T2> java.util.function.BiPredicate l(final java.util.function.BiPredicate<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **biPredicate** becomes ambiguous. */
	public static <T1, T2> java.util.function.BiPredicate biPredicate(final java.util.function.BiPredicate<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static java.util.function.IntPredicate l(final java.util.function.IntPredicate lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **intPredicate** becomes ambiguous. */
	public static java.util.function.IntPredicate intPredicate(final java.util.function.IntPredicate lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static java.util.function.LongPredicate l(final java.util.function.LongPredicate lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **longPredicate** becomes ambiguous. */
	public static java.util.function.LongPredicate longPredicate(final java.util.function.LongPredicate lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static java.util.function.DoublePredicate l(final java.util.function.DoublePredicate lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **doublePredicate** becomes ambiguous. */
	public static java.util.function.DoublePredicate doublePredicate(final java.util.function.DoublePredicate lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static <R> java.util.function.Supplier l(final java.util.function.Supplier<R> lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **supplier** becomes ambiguous. */
	public static <R> java.util.function.Supplier supplier(final java.util.function.Supplier<R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static java.util.function.IntSupplier l(final java.util.function.IntSupplier lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **intSupplier** becomes ambiguous. */
	public static java.util.function.IntSupplier intSupplier(final java.util.function.IntSupplier lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static java.util.function.LongSupplier l(final java.util.function.LongSupplier lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **longSupplier** becomes ambiguous. */
	public static java.util.function.LongSupplier longSupplier(final java.util.function.LongSupplier lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static java.util.function.DoubleSupplier l(final java.util.function.DoubleSupplier lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **doubleSupplier** becomes ambiguous. */
	public static java.util.function.DoubleSupplier doubleSupplier(final java.util.function.DoubleSupplier lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static java.util.function.BooleanSupplier l(final java.util.function.BooleanSupplier lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **booleanSupplier** becomes ambiguous. */
	public static java.util.function.BooleanSupplier booleanSupplier(final java.util.function.BooleanSupplier lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static <T> java.util.function.Consumer l(final java.util.function.Consumer<T> lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **consumer** becomes ambiguous. */
	public static <T> java.util.function.Consumer consumer(final java.util.function.Consumer<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static <T1, T2> java.util.function.BiConsumer l(final java.util.function.BiConsumer<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **biConsumer** becomes ambiguous. */
	public static <T1, T2> java.util.function.BiConsumer biConsumer(final java.util.function.BiConsumer<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static java.util.function.IntConsumer l(final java.util.function.IntConsumer lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **intConsumer** becomes ambiguous. */
	public static java.util.function.IntConsumer intConsumer(final java.util.function.IntConsumer lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static java.util.function.LongConsumer l(final java.util.function.LongConsumer lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **longConsumer** becomes ambiguous. */
	public static java.util.function.LongConsumer longConsumer(final java.util.function.LongConsumer lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static java.util.function.DoubleConsumer l(final java.util.function.DoubleConsumer lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **doubleConsumer** becomes ambiguous. */
	public static java.util.function.DoubleConsumer doubleConsumer(final java.util.function.DoubleConsumer lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static <T> java.util.function.ObjIntConsumer l(final java.util.function.ObjIntConsumer<T> lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **objIntConsumer** becomes ambiguous. */
	public static <T> java.util.function.ObjIntConsumer objIntConsumer(final java.util.function.ObjIntConsumer<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static <T> java.util.function.ObjLongConsumer l(final java.util.function.ObjLongConsumer<T> lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **objLongConsumer** becomes ambiguous. */
	public static <T> java.util.function.ObjLongConsumer objLongConsumer(final java.util.function.ObjLongConsumer<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static <T> java.util.function.ObjDoubleConsumer l(final java.util.function.ObjDoubleConsumer<T> lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **objDoubleConsumer** becomes ambiguous. */
	public static <T> java.util.function.ObjDoubleConsumer objDoubleConsumer(final java.util.function.ObjDoubleConsumer<T> lambda) {
		return lambda;
	}

	// </editor-fold>

}