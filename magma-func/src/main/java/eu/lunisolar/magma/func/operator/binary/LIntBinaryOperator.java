/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2023 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.func.operator.binary;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import javax.annotation.concurrent.NotThreadSafe; // NOSONAR
import java.util.Comparator; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.*; //NOSONAR
import eu.lunisolar.magma.basics.builder.*; // NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.aType.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.IA;
import eu.lunisolar.magma.func.SA;
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.func.tuple.*; // NOSONAR

import java.util.concurrent.*; // NOSONAR
import java.util.function.*; // NOSONAR
import java.util.*; // NOSONAR
import java.lang.reflect.*; // NOSONAR
import java.util.stream.Stream; // NOSONAR

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
 * Non-throwing functional interface (lambda) LIntBinaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 2): int a1,int a2
 *
 * Co-domain: int
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LIntBinaryOperator extends IntBinaryOperator, MetaOperator, MetaInterface.NonThrowing, Codomain<aInt>, Domain2<aInt, aInt> { //NOSONAR

	String DESCRIPTION = "LIntBinaryOperator: int applyAsInt(int a1,int a2)";

	default int applyAsInt(int a1, int a2) {
		try {
			return this.applyAsIntX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsInt(int a1,int a2)
	 */
	int applyAsIntX(int a1, int a2) throws Throwable;

	default int tupleApplyAsInt(LIntPair args) {
		return applyAsInt(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default int handlingApplyAsInt(int a1, int a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsIntX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LIntBinaryOperator handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2) -> handlingApplyAsInt(a1, a2, handling);
	}

	default int applyAsInt(int a1, int a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyAsIntX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default int applyAsInt(int a1, int a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyAsIntX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default int applyAsInt(int a1, int a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyAsIntX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default int applyAsInt(int a1, int a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyAsIntX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LIntBinaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2) -> applyAsInt(a1, a2, factory, newMessage);
	}

	default LIntBinaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2) -> applyAsInt(a1, a2, factory, newMessage, param1);
	}

	default LIntBinaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2) -> applyAsInt(a1, a2, factory, newMessage, param1, param1);
	}

	default LIntBinaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2) -> applyAsInt(a1, a2, factory, newMessage, param1, param2, param3);
	}

	default int applyAsInt(int a1, int a2, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyAsIntX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LIntBinaryOperator trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2) -> applyAsInt(a1, a2, factory);
	}

	default int applyAsIntThen(int a1, int a2, @Nonnull LToIntFunction<Throwable> handler) {
		try {
			return this.applyAsIntX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsInt(e);
		}
	}

	default LIntBinaryOperator tryingThen(@Nonnull LToIntFunction<Throwable> handler) {
		return (a1, a2) -> applyAsIntThen(a1, a2, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default int nestingApplyAsInt(int a1, int a2) {
		try {
			return this.applyAsIntX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default int shovingApplyAsInt(int a1, int a2) {
		try {
			return this.applyAsIntX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static int shovingApplyAsInt(int a1, int a2, LIntBinaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.shovingApplyAsInt(a1, a2);
	}

	static int handlingApplyAsInt(int a1, int a2, LIntBinaryOperator func, HandlingInstructions<Throwable, RuntimeException> handling) { //<-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsInt(a1, a2, handling);
	}

	static int tryApplyAsInt(int a1, int a2, LIntBinaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsInt(a1, a2);
	}

	static int tryApplyAsInt(int a1, int a2, LIntBinaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.applyAsInt(a1, a2, factory, newMessage);
	}

	static int tryApplyAsInt(int a1, int a2, LIntBinaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.applyAsInt(a1, a2, factory, newMessage, param1);
	}

	static int tryApplyAsInt(int a1, int a2, LIntBinaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.applyAsInt(a1, a2, factory, newMessage, param1, param2);
	}

	static int tryApplyAsInt(int a1, int a2, LIntBinaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.applyAsInt(a1, a2, factory, newMessage, param1, param2, param3);
	}

	static int tryApplyAsInt(int a1, int a2, LIntBinaryOperator func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.applyAsInt(a1, a2, factory);
	}

	static int tryApplyAsIntThen(int a1, int a2, LIntBinaryOperator func, @Nonnull LToIntFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsIntThen(a1, a2, handler);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default int nonNullApplyAsInt(int a1, int a2) {
		return applyAsInt(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LIntBinaryOperator.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, int a1, int a2, @Nonnull LIntBinaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.applyAsInt(a1, a2);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.applyAsInt(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, int a1, int a2, @Nonnull LIntBinaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.applyAsInt(a1, a2);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.applyAsInt(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, int a1, int a2, @Nonnull LIntBinaryOperator func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, func);
	}

	default LIntUnaryOperator _with(int a1) {
		return a2 -> applyAsInt(a1, a2);
	}

	default LIntUnaryOperator with(int a2) {
		return a1 -> applyAsInt(a1, a2);
	}

	/**  */
	public static LIntBinaryOperator uncurry(@Nonnull LIntFunction<LIntUnaryOperator> func) {
		Null.nonNullArg(func, "func");
		return (int a1, int a2) -> func.apply(a1).applyAsInt(a2);
	}

	/** Change function to one that ignores output. */
	default LBiIntConsumer toConsumer() {
		return this::applyAsInt;
	}

	/** Calls domain consumer before main function. */
	default LIntBinaryOperator beforeDo(@Nonnull LBiIntConsumer before) {
		Null.nonNullArg(before, "before");
		return (int a1, int a2) -> {
			before.accept(a1, a2);
			return applyAsInt(a1, a2);
		};
	}

	/** Calls codomain consumer after main function. */
	default LIntBinaryOperator afterDo(@Nonnull LIntConsumer after) {
		Null.nonNullArg(after, "after");
		return (int a1, int a2) -> {
			final int retval = applyAsInt(a1, a2);
			after.accept(retval);
			return retval;
		};
	}

	/** Creates function that always returns the same value. */
	static LIntBinaryOperator constant(int r) {
		return (a1, a2) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LIntBinaryOperator intBinaryOp(final @Nonnull LIntBinaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S implements LIntBinaryOperator {
		private LIntBinaryOperator target = null;
		@Override
		public int applyAsIntX(int a1, int a2) throws Throwable {
			return target.applyAsIntX(a1, a2);
		}
	}

	@Nonnull
	static LIntBinaryOperator recursive(final @Nonnull LFunction<LIntBinaryOperator, LIntBinaryOperator> selfLambda) {
		final S single = new S();
		LIntBinaryOperator func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static M mementoOf(int a1, int a2, LIntBinaryOperator function) {
		var initialValue = function.applyAsInt(a1, a2);
		return initializedMementoOf(initialValue, function);
	}

	public static M initializedMementoOf(int initialValue, LIntBinaryOperator function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static M deltaOf(int a1, int a2, LIntBinaryOperator function, LIntBinaryOperator deltaFunction) {
		var initialValue = function.applyAsInt(a1, a2);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static M deltaOf(int a1, int a2, LIntBinaryOperator function) {
		var initialValue = function.applyAsInt(a1, a2);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (x2 - x1));
	}

	public static M initializedDeltaOf(int initialValue, LIntBinaryOperator function, LIntBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.applyAsInt(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.applyAsInt(x1, x2));
	}

	public static M memento(int initialBaseValue, int initialValue, LIntBinaryOperator baseFunction, LIntTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LIntBinaryOperator.M)
	 */
	@NotThreadSafe
	final class M implements LIntBinaryOperator {

		private final LIntBinaryOperator baseFunction;
		private int lastBaseValue;
		private int lastValue;
		private final LIntTernaryOperator mementoFunction;

		private M(int lastBaseValue, int lastValue, LIntBinaryOperator baseFunction, LIntTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public int applyAsIntX(int a1, int a2) throws Throwable {
			int x1 = lastBaseValue;
			int x2 = lastBaseValue = baseFunction.applyAsIntX(a1, a2);

			return lastValue = mementoFunction.applyAsIntX(lastValue, x1, x2);
		}

		public int currentApplyAsInt(int a1, int a2) {
			int x1 = lastBaseValue;
			int x2 = baseFunction.applyAsInt(a1, a2);

			return mementoFunction.applyAsInt(lastValue, x1, x2);
		}

		public int lastValue() {
			return lastValue;
		}

		public int lastBaseValue() {
			return lastBaseValue;
		}

		public int currentBaseValue(int a1, int a2) {
			return baseFunction.applyAsInt(a1, a2);
		}
	}

	// </editor-fold>

	@Nonnull
	static LIntBinaryOperator intBinaryOpThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LIntBinaryOperator intBinaryOpThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce(message);
		};
	}

	static int call(int a1, int a2, final @Nonnull LIntBinaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsInt(a1, a2);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static LIntBinaryOperator wrap(final IntBinaryOperator other) {
		return other::applyAsInt;
	}
	// </editor-fold>

	/**
	 * Creates function that returns the lesser value according to the comparator.
	 * @see {@link java.util.function.BinaryOperator#minBy}
	 */
	@Nonnull
	static LIntBinaryOperator minBy(@Nonnull Comparator<Integer> comparator) {
		Null.nonNullArg(comparator, "comparator");
		return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
	}

	/**
	 * Creates function that returns the lesser value according to the comparator.
	 * @see {@link java.util.function.BinaryOperator#maxBy}
	 */
	@Nonnull
	static LIntBinaryOperator maxBy(@Nonnull Comparator<Integer> comparator) {
		Null.nonNullArg(comparator, "comparator");
		return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
	}

	/**
	 * Returns function that returns the lower value.
	 * @see {@link java.util.function.BinaryOperator#minBy}
	 */
	@Nonnull
	static LIntBinaryOperator min() {
		return Integer::min;
	}

	/**
	 * Returns function that returns the higher value.
	 * @see {@link java.util.function.BinaryOperator#maxBy}
	 */
	@Nonnull
	static LIntBinaryOperator max() {
		return Integer::max;
	}

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LIntBinaryOperator compose(@Nonnull final LIntUnaryOperator before1, @Nonnull final LIntUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.applyAsInt(before1.applyAsInt(v1), before2.applyAsInt(v2));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LToIntBiFunction<V1, V2> unboxingCompose(@Nonnull final LToIntFunction<? super V1> before1, @Nonnull final LToIntFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.applyAsInt(before1.applyAsInt(v1), before2.applyAsInt(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiIntFunction<V> then(@Nonnull LIntFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.apply(this.applyAsInt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntBinaryOperator thenToInt(@Nonnull LIntUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsInt(this.applyAsInt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiIntPredicate thenToBool(@Nonnull LIntPredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.test(this.applyAsInt(a1, a2));
	}

	// </editor-fold>

	default LIntBinaryOperator shoving() {

		return new LIntBinaryOperator() {

			public int applyAsInt(int a1, int a2) {
				try {
					return this.applyAsIntX(a1, a2);
				} catch (Throwable e) { // NOSONAR
					Handling.handleErrors(e);
					throw Handling.throwIt(e);
				}
			}

			public int applyAsIntX(int a1, int a2) throws Throwable {
				return LIntBinaryOperator.this.applyAsIntX(a1, a2);
			}

		};
	}

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LIntBinaryOperator) Operator */
	public static int doNothing(int a1, int a2) {
		return Function4U.defaultInteger;
	}

}
