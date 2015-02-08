/*
 * (C) Copyright 2015 Lunisolar (http://lunisolar.eu/).
 *
 * This file is part of "lunisolar-magma".
 *
 * "lunisolar-magma" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

package eu.lunisolar.magma.func;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Comparator; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.NestedException; //NOSONAR
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
@SuppressWarnings("UnusedDeclaration")
public final class Function4U {

	public static final String VALIDATION_MESSAGE_OTHER = "Argument [other] cannot be null.";
	public static final String VALIDATION_MESSAGE_AFTER = "Argument [after] cannot be null.";
	public static final String VALIDATION_MESSAGE_BEFORE1 = "Argument [before1] cannot be null.";
	public static final String VALIDATION_MESSAGE_BEFORE2 = "Argument [before2] cannot be null.";
	public static final String VALIDATION_MESSAGE_BEFORE3 = "Argument [before3] cannot be null.";
	public static final String VALIDATION_MESSAGE_COMPARATOR = "Argument [comparator] cannot be null.";
	public static final String VALIDATION_MESSAGE_EXCEPTION = "Argument [exception] cannot be null.";
	public static final String VALIDATION_MESSAGE_EXCEPTIONPREDICATE = "Argument [exceptionPredicate] cannot be null.";
	public static final String VALIDATION_MESSAGE_HANDLER = "Argument [handler] cannot be null.";
	public static final String VALIDATION_MESSAGE_ANYIMPLEMENTATION = "Argument [anyImplementation] cannot be null.";
	public static final String VALIDATION_MESSAGE_SEMATHOR = "Argument [semathor] cannot be null.";

	// <editor-fold desc="no-instance constructor">
	private Function4U() {
	}
	// </editor-fold>

	/** You can use this as a reference method whenever nothing should be done. */
	public static final <E> void doNothing() {
		// NOSONAR - there is something about 'doNothing' that common check rules do not cover ;)
	}

	/** You can use this as a reference method whenever nothing should be done. */
	public static final <E> void doNothing(E element) {
		// NOSONAR - there is something about 'doNothing' that common check rules do not cover ;)
	}

	// <editor-fold desc="predicate::method">

	/** Returns TRUE. */
	public static final <T> boolean alwaysTrue(T t) {
		return true;
	}

	/** Returns FALSE. */
	public static final <T> boolean alwaysFalse(T t) {
		return false;
	}

	/** Returns TRUE. */
	public static final <T1, T2> boolean alwaysTrue(T1 t1, T2 t2) {
		return true;
	}

	/** Returns FALSE. */
	public static final <T1, T2> boolean alwaysFalse(T1 t1, T2 t2) {
		return false;
	}

	/** Returns TRUE. */
	public static final <T1, T2, T3> boolean alwaysTrue(T1 t1, T2 t2, T3 t3) {
		return true;
	}

	/** Returns FALSE. */
	public static final <T1, T2, T3> boolean alwaysFalse(T1 t1, T2 t2, T3 t3) {
		return false;
	}

	/** Returns TRUE. */
	public static final boolean alwaysTrue(byte b) {
		return true;
	}

	/** Returns FALSE. */
	public static final boolean alwaysFalse(byte b) {
		return false;
	}

	/** Returns TRUE. */
	public static final boolean alwaysTrue(short s) {
		return true;
	}

	/** Returns FALSE. */
	public static final boolean alwaysFalse(short s) {
		return false;
	}

	/** Returns TRUE. */
	public static final boolean alwaysTrue(int i) {
		return true;
	}

	/** Returns FALSE. */
	public static final boolean alwaysFalse(int i) {
		return false;
	}

	/** Returns TRUE. */
	public static final boolean alwaysTrue(long l) {
		return true;
	}

	/** Returns FALSE. */
	public static final boolean alwaysFalse(long l) {
		return false;
	}

	/** Returns TRUE. */
	public static final boolean alwaysTrue(float f) {
		return true;
	}

	/** Returns FALSE. */
	public static final boolean alwaysFalse(float f) {
		return false;
	}

	/** Returns TRUE. */
	public static final boolean alwaysTrue(double d) {
		return true;
	}

	/** Returns FALSE. */
	public static final boolean alwaysFalse(double d) {
		return false;
	}

	/** Returns TRUE. */
	public static final boolean alwaysTrue(char c) {
		return true;
	}

	/** Returns FALSE. */
	public static final boolean alwaysFalse(char c) {
		return false;
	}

	/** Returns TRUE. */
	public static final boolean alwaysTrue(byte b1, byte b2) {
		return true;
	}

	/** Returns FALSE. */
	public static final boolean alwaysFalse(byte b1, byte b2) {
		return false;
	}

	/** Returns TRUE. */
	public static final boolean alwaysTrue(short s1, short s2) {
		return true;
	}

	/** Returns FALSE. */
	public static final boolean alwaysFalse(short s1, short s2) {
		return false;
	}

	/** Returns TRUE. */
	public static final boolean alwaysTrue(int i1, int i2) {
		return true;
	}

	/** Returns FALSE. */
	public static final boolean alwaysFalse(int i1, int i2) {
		return false;
	}

	/** Returns TRUE. */
	public static final boolean alwaysTrue(long l1, long l2) {
		return true;
	}

	/** Returns FALSE. */
	public static final boolean alwaysFalse(long l1, long l2) {
		return false;
	}

	/** Returns TRUE. */
	public static final boolean alwaysTrue(float f1, float f2) {
		return true;
	}

	/** Returns FALSE. */
	public static final boolean alwaysFalse(float f1, float f2) {
		return false;
	}

	/** Returns TRUE. */
	public static final boolean alwaysTrue(double d1, double d2) {
		return true;
	}

	/** Returns FALSE. */
	public static final boolean alwaysFalse(double d1, double d2) {
		return false;
	}

	/** Returns TRUE. */
	public static final boolean alwaysTrue(char c1, char c2) {
		return true;
	}

	/** Returns FALSE. */
	public static final boolean alwaysFalse(char c1, char c2) {
		return false;
	}

	/** Returns TRUE. */
	public static final <T> boolean alwaysTrue(T t, byte b) {
		return true;
	}

	/** Returns FALSE. */
	public static final <T> boolean alwaysFalse(T t, byte b) {
		return false;
	}

	/** Returns TRUE. */
	public static final <T> boolean alwaysTrue(T t, short s) {
		return true;
	}

	/** Returns FALSE. */
	public static final <T> boolean alwaysFalse(T t, short s) {
		return false;
	}

	/** Returns TRUE. */
	public static final <T> boolean alwaysTrue(T t, int i) {
		return true;
	}

	/** Returns FALSE. */
	public static final <T> boolean alwaysFalse(T t, int i) {
		return false;
	}

	/** Returns TRUE. */
	public static final <T> boolean alwaysTrue(T t, long l) {
		return true;
	}

	/** Returns FALSE. */
	public static final <T> boolean alwaysFalse(T t, long l) {
		return false;
	}

	/** Returns TRUE. */
	public static final <T> boolean alwaysTrue(T t, float f) {
		return true;
	}

	/** Returns FALSE. */
	public static final <T> boolean alwaysFalse(T t, float f) {
		return false;
	}

	/** Returns TRUE. */
	public static final <T> boolean alwaysTrue(T t, double d) {
		return true;
	}

	/** Returns FALSE. */
	public static final <T> boolean alwaysFalse(T t, double d) {
		return false;
	}

	/** Returns TRUE. */
	public static final <T> boolean alwaysTrue(T t, char c) {
		return true;
	}

	/** Returns FALSE. */
	public static final <T> boolean alwaysFalse(T t, char c) {
		return false;
	}

	/** Returns TRUE. */
	public static final <T> boolean alwaysTrue(T t, boolean b) {
		return true;
	}

	/** Returns FALSE. */
	public static final <T> boolean alwaysFalse(T t, boolean b) {
		return false;
	}

	/** Returns TRUE. */
	public static final <T1, T2> boolean alwaysTrue(T1 t1, T2 t2, byte b) {
		return true;
	}

	/** Returns FALSE. */
	public static final <T1, T2> boolean alwaysFalse(T1 t1, T2 t2, byte b) {
		return false;
	}

	/** Returns TRUE. */
	public static final <T1, T2> boolean alwaysTrue(T1 t1, T2 t2, short s) {
		return true;
	}

	/** Returns FALSE. */
	public static final <T1, T2> boolean alwaysFalse(T1 t1, T2 t2, short s) {
		return false;
	}

	/** Returns TRUE. */
	public static final <T1, T2> boolean alwaysTrue(T1 t1, T2 t2, int i) {
		return true;
	}

	/** Returns FALSE. */
	public static final <T1, T2> boolean alwaysFalse(T1 t1, T2 t2, int i) {
		return false;
	}

	/** Returns TRUE. */
	public static final <T1, T2> boolean alwaysTrue(T1 t1, T2 t2, long l) {
		return true;
	}

	/** Returns FALSE. */
	public static final <T1, T2> boolean alwaysFalse(T1 t1, T2 t2, long l) {
		return false;
	}

	/** Returns TRUE. */
	public static final <T1, T2> boolean alwaysTrue(T1 t1, T2 t2, float f) {
		return true;
	}

	/** Returns FALSE. */
	public static final <T1, T2> boolean alwaysFalse(T1 t1, T2 t2, float f) {
		return false;
	}

	/** Returns TRUE. */
	public static final <T1, T2> boolean alwaysTrue(T1 t1, T2 t2, double d) {
		return true;
	}

	/** Returns FALSE. */
	public static final <T1, T2> boolean alwaysFalse(T1 t1, T2 t2, double d) {
		return false;
	}

	/** Returns TRUE. */
	public static final <T1, T2> boolean alwaysTrue(T1 t1, T2 t2, char c) {
		return true;
	}

	/** Returns FALSE. */
	public static final <T1, T2> boolean alwaysFalse(T1 t1, T2 t2, char c) {
		return false;
	}

	/** Returns TRUE. */
	public static final <T1, T2> boolean alwaysTrue(T1 t1, T2 t2, boolean b) {
		return true;
	}

	/** Returns FALSE. */
	public static final <T1, T2> boolean alwaysFalse(T1 t1, T2 t2, boolean b) {
		return false;
	}

	// </editor-fold>

	// <editor-fold desc="wrapper for lambda- JRE">
	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static final <T> java.util.function.UnaryOperator l(final java.util.function.UnaryOperator<T> lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **l** becomes ambiguous. */
	public static final <T> java.util.function.UnaryOperator unaryOperator(final java.util.function.UnaryOperator<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static final <T> java.util.function.BinaryOperator l(final java.util.function.BinaryOperator<T> lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **l** becomes ambiguous. */
	public static final <T> java.util.function.BinaryOperator binaryOperator(final java.util.function.BinaryOperator<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static final java.util.function.IntUnaryOperator l(final java.util.function.IntUnaryOperator lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **l** becomes ambiguous. */
	public static final java.util.function.IntUnaryOperator intUnaryOperator(final java.util.function.IntUnaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static final java.util.function.LongUnaryOperator l(final java.util.function.LongUnaryOperator lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **l** becomes ambiguous. */
	public static final java.util.function.LongUnaryOperator longUnaryOperator(final java.util.function.LongUnaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static final java.util.function.DoubleUnaryOperator l(final java.util.function.DoubleUnaryOperator lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **l** becomes ambiguous. */
	public static final java.util.function.DoubleUnaryOperator doubleUnaryOperator(final java.util.function.DoubleUnaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static final java.util.function.IntBinaryOperator l(final java.util.function.IntBinaryOperator lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **l** becomes ambiguous. */
	public static final java.util.function.IntBinaryOperator intBinaryOperator(final java.util.function.IntBinaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static final java.util.function.LongBinaryOperator l(final java.util.function.LongBinaryOperator lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **l** becomes ambiguous. */
	public static final java.util.function.LongBinaryOperator longBinaryOperator(final java.util.function.LongBinaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static final java.util.function.DoubleBinaryOperator l(final java.util.function.DoubleBinaryOperator lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **l** becomes ambiguous. */
	public static final java.util.function.DoubleBinaryOperator doubleBinaryOperator(final java.util.function.DoubleBinaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static final <T, R> java.util.function.Function l(final java.util.function.Function<T, R> lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **l** becomes ambiguous. */
	public static final <T, R> java.util.function.Function function(final java.util.function.Function<T, R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static final <T1, T2, R> java.util.function.BiFunction l(final java.util.function.BiFunction<T1, T2, R> lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **l** becomes ambiguous. */
	public static final <T1, T2, R> java.util.function.BiFunction biFunction(final java.util.function.BiFunction<T1, T2, R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static final <R> java.util.function.IntFunction l(final java.util.function.IntFunction<R> lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **l** becomes ambiguous. */
	public static final <R> java.util.function.IntFunction intFunction(final java.util.function.IntFunction<R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static final <R> java.util.function.LongFunction l(final java.util.function.LongFunction<R> lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **l** becomes ambiguous. */
	public static final <R> java.util.function.LongFunction longFunction(final java.util.function.LongFunction<R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static final <R> java.util.function.DoubleFunction l(final java.util.function.DoubleFunction<R> lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **l** becomes ambiguous. */
	public static final <R> java.util.function.DoubleFunction doubleFunction(final java.util.function.DoubleFunction<R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static final <T> java.util.function.ToIntFunction l(final java.util.function.ToIntFunction<T> lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **l** becomes ambiguous. */
	public static final <T> java.util.function.ToIntFunction toIntFunction(final java.util.function.ToIntFunction<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static final <T> java.util.function.ToLongFunction l(final java.util.function.ToLongFunction<T> lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **l** becomes ambiguous. */
	public static final <T> java.util.function.ToLongFunction toLongFunction(final java.util.function.ToLongFunction<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static final <T> java.util.function.ToDoubleFunction l(final java.util.function.ToDoubleFunction<T> lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **l** becomes ambiguous. */
	public static final <T> java.util.function.ToDoubleFunction toDoubleFunction(final java.util.function.ToDoubleFunction<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static final <T1, T2> java.util.function.ToIntBiFunction l(final java.util.function.ToIntBiFunction<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **l** becomes ambiguous. */
	public static final <T1, T2> java.util.function.ToIntBiFunction toIntBiFunction(final java.util.function.ToIntBiFunction<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static final <T1, T2> java.util.function.ToLongBiFunction l(final java.util.function.ToLongBiFunction<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **l** becomes ambiguous. */
	public static final <T1, T2> java.util.function.ToLongBiFunction toLongBiFunction(final java.util.function.ToLongBiFunction<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static final <T1, T2> java.util.function.ToDoubleBiFunction l(final java.util.function.ToDoubleBiFunction<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **l** becomes ambiguous. */
	public static final <T1, T2> java.util.function.ToDoubleBiFunction toDoubleBiFunction(final java.util.function.ToDoubleBiFunction<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static final java.util.function.IntToLongFunction l(final java.util.function.IntToLongFunction lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **l** becomes ambiguous. */
	public static final java.util.function.IntToLongFunction intToLongFunction(final java.util.function.IntToLongFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static final java.util.function.IntToDoubleFunction l(final java.util.function.IntToDoubleFunction lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **l** becomes ambiguous. */
	public static final java.util.function.IntToDoubleFunction intToDoubleFunction(final java.util.function.IntToDoubleFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static final java.util.function.LongToIntFunction l(final java.util.function.LongToIntFunction lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **l** becomes ambiguous. */
	public static final java.util.function.LongToIntFunction longToIntFunction(final java.util.function.LongToIntFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static final java.util.function.LongToDoubleFunction l(final java.util.function.LongToDoubleFunction lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **l** becomes ambiguous. */
	public static final java.util.function.LongToDoubleFunction longToDoubleFunction(final java.util.function.LongToDoubleFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static final java.util.function.DoubleToIntFunction l(final java.util.function.DoubleToIntFunction lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **l** becomes ambiguous. */
	public static final java.util.function.DoubleToIntFunction doubleToIntFunction(final java.util.function.DoubleToIntFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static final java.util.function.DoubleToLongFunction l(final java.util.function.DoubleToLongFunction lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **l** becomes ambiguous. */
	public static final java.util.function.DoubleToLongFunction doubleToLongFunction(final java.util.function.DoubleToLongFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static final <T> java.util.function.Predicate l(final java.util.function.Predicate<T> lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **l** becomes ambiguous. */
	public static final <T> java.util.function.Predicate predicate(final java.util.function.Predicate<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static final <T1, T2> java.util.function.BiPredicate l(final java.util.function.BiPredicate<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **l** becomes ambiguous. */
	public static final <T1, T2> java.util.function.BiPredicate biPredicate(final java.util.function.BiPredicate<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static final java.util.function.IntPredicate l(final java.util.function.IntPredicate lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **l** becomes ambiguous. */
	public static final java.util.function.IntPredicate intPredicate(final java.util.function.IntPredicate lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static final java.util.function.LongPredicate l(final java.util.function.LongPredicate lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **l** becomes ambiguous. */
	public static final java.util.function.LongPredicate longPredicate(final java.util.function.LongPredicate lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static final java.util.function.DoublePredicate l(final java.util.function.DoublePredicate lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **l** becomes ambiguous. */
	public static final java.util.function.DoublePredicate doublePredicate(final java.util.function.DoublePredicate lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static final <R> java.util.function.Supplier l(final java.util.function.Supplier<R> lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **l** becomes ambiguous. */
	public static final <R> java.util.function.Supplier supplier(final java.util.function.Supplier<R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static final java.util.function.IntSupplier l(final java.util.function.IntSupplier lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **l** becomes ambiguous. */
	public static final java.util.function.IntSupplier intSupplier(final java.util.function.IntSupplier lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static final java.util.function.LongSupplier l(final java.util.function.LongSupplier lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **l** becomes ambiguous. */
	public static final java.util.function.LongSupplier longSupplier(final java.util.function.LongSupplier lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static final java.util.function.DoubleSupplier l(final java.util.function.DoubleSupplier lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **l** becomes ambiguous. */
	public static final java.util.function.DoubleSupplier doubleSupplier(final java.util.function.DoubleSupplier lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods axceptiong different interfaces). */
	public static final java.util.function.BooleanSupplier l(final java.util.function.BooleanSupplier lambda) {
		return lambda;
	}

	/** Convenient method for lambda exression that throws exception (e.g. RuntimeException). In such case compilator do not have a return type info and **l** becomes ambiguous. */
	public static final java.util.function.BooleanSupplier booleanSupplier(final java.util.function.BooleanSupplier lambda) {
		return lambda;
	}

	// </editor-fold>

}
