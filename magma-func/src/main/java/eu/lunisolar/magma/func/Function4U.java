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

package eu.lunisolar.magma.func;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import java.util.Comparator; // NOSONAR
import java.util.Objects; // NOSONAR
import java.util.function.*;
import eu.lunisolar.magma.basics.exceptions.NestedException; //NOSONAR
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
 *  Methods that do not reference interface classes directly or are supplements for the JRE interfaces.
 */
@SuppressWarnings("unused")
public final class Function4U {

	public static final Object defaultObject = null;
	public static final byte defaultByte = 0;
	public static final byte defaultShort = 0;
	public static final int defaultInteger = 0;
	public static final long defaultLong = 0;
	public static final float defaultFloat = 0;
	public static final double defaultDouble = 0;
	public static final char defaultCharacter = 0;
	public static final boolean defaultBoolean = false;

	// <editor-fold desc="no-instance constructor">
	private Function4U() {
	}
	// </editor-fold>

	// <editor-fold desc="wrapper for lambda- JRE">

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static Runnable act(final Runnable lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static Runnable runnable(final Runnable lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T1, T2> BiConsumer biCons(final BiConsumer<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T1, T2> BiConsumer biConsumer(final BiConsumer<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> Consumer cons(final Consumer<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> Consumer consumer(final Consumer<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static DoubleConsumer dblCons(final DoubleConsumer lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static DoubleConsumer doubleConsumer(final DoubleConsumer lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static IntConsumer intCons(final IntConsumer lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static IntConsumer intConsumer(final IntConsumer lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static LongConsumer longCons(final LongConsumer lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static LongConsumer longConsumer(final LongConsumer lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> ObjDoubleConsumer objDblCons(final ObjDoubleConsumer<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> ObjDoubleConsumer objDoubleConsumer(final ObjDoubleConsumer<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> ObjIntConsumer objIntCons(final ObjIntConsumer<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> ObjIntConsumer objIntConsumer(final ObjIntConsumer<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> ObjLongConsumer objLongCons(final ObjLongConsumer<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> ObjLongConsumer objLongConsumer(final ObjLongConsumer<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> BinaryOperator binaryOp(final BinaryOperator<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> BinaryOperator binaryOperator(final BinaryOperator<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static DoubleBinaryOperator dblBinaryOp(final DoubleBinaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static DoubleBinaryOperator doubleBinaryOperator(final DoubleBinaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static DoubleUnaryOperator dblUnaryOp(final DoubleUnaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static DoubleUnaryOperator doubleUnaryOperator(final DoubleUnaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static IntBinaryOperator intBinaryOp(final IntBinaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static IntBinaryOperator intBinaryOperator(final IntBinaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static IntUnaryOperator intUnaryOp(final IntUnaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static IntUnaryOperator intUnaryOperator(final IntUnaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static LongBinaryOperator longBinaryOp(final LongBinaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static LongBinaryOperator longBinaryOperator(final LongBinaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static LongUnaryOperator longUnaryOp(final LongUnaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static LongUnaryOperator longUnaryOperator(final LongUnaryOperator lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> UnaryOperator unaryOp(final UnaryOperator<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> UnaryOperator unaryOperator(final UnaryOperator<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T1, T2, R> BiFunction biFunc(final BiFunction<T1, T2, R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T1, T2, R> BiFunction biFunction(final BiFunction<T1, T2, R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <R> DoubleFunction dblFunc(final DoubleFunction<R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <R> DoubleFunction doubleFunction(final DoubleFunction<R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static DoubleToIntFunction dblToIntFunc(final DoubleToIntFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static DoubleToIntFunction doubleToIntFunction(final DoubleToIntFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static DoubleToLongFunction dblToLongFunc(final DoubleToLongFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static DoubleToLongFunction doubleToLongFunction(final DoubleToLongFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T, R> Function func(final Function<T, R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T, R> Function function(final Function<T, R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <R> IntFunction intFunc(final IntFunction<R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <R> IntFunction intFunction(final IntFunction<R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static IntToDoubleFunction intToDblFunc(final IntToDoubleFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static IntToDoubleFunction intToDoubleFunction(final IntToDoubleFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static IntToLongFunction intToLongFunc(final IntToLongFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static IntToLongFunction intToLongFunction(final IntToLongFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <R> LongFunction longFunc(final LongFunction<R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <R> LongFunction longFunction(final LongFunction<R> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static LongToDoubleFunction longToDblFunc(final LongToDoubleFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static LongToDoubleFunction longToDoubleFunction(final LongToDoubleFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static LongToIntFunction longToIntFunc(final LongToIntFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static LongToIntFunction longToIntFunction(final LongToIntFunction lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T1, T2> ToDoubleBiFunction toDblBiFunc(final ToDoubleBiFunction<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T1, T2> ToDoubleBiFunction toDoubleBiFunction(final ToDoubleBiFunction<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> ToDoubleFunction toDblFunc(final ToDoubleFunction<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> ToDoubleFunction toDoubleFunction(final ToDoubleFunction<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T1, T2> ToIntBiFunction toIntBiFunc(final ToIntBiFunction<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T1, T2> ToIntBiFunction toIntBiFunction(final ToIntBiFunction<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> ToIntFunction toIntFunc(final ToIntFunction<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> ToIntFunction toIntFunction(final ToIntFunction<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T1, T2> ToLongBiFunction toLongBiFunc(final ToLongBiFunction<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T1, T2> ToLongBiFunction toLongBiFunction(final ToLongBiFunction<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> ToLongFunction toLongFunc(final ToLongFunction<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> ToLongFunction toLongFunction(final ToLongFunction<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T1, T2> BiPredicate biPred(final BiPredicate<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T1, T2> BiPredicate biPredicate(final BiPredicate<T1, T2> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static DoublePredicate dblPred(final DoublePredicate lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static DoublePredicate doublePredicate(final DoublePredicate lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static IntPredicate intPred(final IntPredicate lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static IntPredicate intPredicate(final IntPredicate lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static LongPredicate longPred(final LongPredicate lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static LongPredicate longPredicate(final LongPredicate lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> Predicate pred(final Predicate<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> Predicate predicate(final Predicate<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static BooleanSupplier boolSup(final BooleanSupplier lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static BooleanSupplier booleanSupplier(final BooleanSupplier lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static DoubleSupplier dblSup(final DoubleSupplier lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static DoubleSupplier doubleSupplier(final DoubleSupplier lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static IntSupplier intSup(final IntSupplier lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static IntSupplier intSupplier(final IntSupplier lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static LongSupplier longSup(final LongSupplier lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static LongSupplier longSupplier(final LongSupplier lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> Supplier sup(final Supplier<T> lambda) {
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler. */
	public static <T> Supplier supplier(final Supplier<T> lambda) {
		return lambda;
	}

	// </editor-fold>

}