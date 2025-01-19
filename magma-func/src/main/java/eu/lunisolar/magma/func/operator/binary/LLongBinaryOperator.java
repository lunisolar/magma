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
 * Non-throwing functional interface (lambda) LLongBinaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 2): long a1,long a2
 *
 * Co-domain: long
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LLongBinaryOperator extends LongBinaryOperator, MetaOperator, MetaInterface.NonThrowing, Codomain<aLong>, Domain2<aLong, aLong> { //NOSONAR

	String DESCRIPTION = "LLongBinaryOperator: long applyAsLong(long a1,long a2)";

	default long applyAsLong(long a1, long a2) {
		try {
			return this.applyAsLongX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsLong(long a1,long a2)
	 */
	long applyAsLongX(long a1, long a2) throws Throwable;

	default long tupleApplyAsLong(LLongPair args) {
		return applyAsLong(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default long handlingApplyAsLong(long a1, long a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsLongX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LLongBinaryOperator handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2) -> handlingApplyAsLong(a1, a2, handling);
	}

	default long applyAsLong(long a1, long a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyAsLongX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default long applyAsLong(long a1, long a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyAsLongX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default long applyAsLong(long a1, long a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyAsLongX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default long applyAsLong(long a1, long a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyAsLongX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LLongBinaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2) -> applyAsLong(a1, a2, factory, newMessage);
	}

	default LLongBinaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2) -> applyAsLong(a1, a2, factory, newMessage, param1);
	}

	default LLongBinaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2) -> applyAsLong(a1, a2, factory, newMessage, param1, param1);
	}

	default LLongBinaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2) -> applyAsLong(a1, a2, factory, newMessage, param1, param2, param3);
	}

	default long applyAsLong(long a1, long a2, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyAsLongX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LLongBinaryOperator trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2) -> applyAsLong(a1, a2, factory);
	}

	default long applyAsLongThen(long a1, long a2, @Nonnull LToLongFunction<Throwable> handler) {
		try {
			return this.applyAsLongX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsLong(e);
		}
	}

	default LLongBinaryOperator tryingThen(@Nonnull LToLongFunction<Throwable> handler) {
		return (a1, a2) -> applyAsLongThen(a1, a2, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default long nestingApplyAsLong(long a1, long a2) {
		try {
			return this.applyAsLongX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default long shovingApplyAsLong(long a1, long a2) {
		try {
			return this.applyAsLongX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static long shovingApplyAsLong(long a1, long a2, LLongBinaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.shovingApplyAsLong(a1, a2);
	}

	static long handlingApplyAsLong(long a1, long a2, LLongBinaryOperator func, HandlingInstructions<Throwable, RuntimeException> handling) { //<-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsLong(a1, a2, handling);
	}

	static long tryApplyAsLong(long a1, long a2, LLongBinaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsLong(a1, a2);
	}

	static long tryApplyAsLong(long a1, long a2, LLongBinaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.applyAsLong(a1, a2, factory, newMessage);
	}

	static long tryApplyAsLong(long a1, long a2, LLongBinaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.applyAsLong(a1, a2, factory, newMessage, param1);
	}

	static long tryApplyAsLong(long a1, long a2, LLongBinaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.applyAsLong(a1, a2, factory, newMessage, param1, param2);
	}

	static long tryApplyAsLong(long a1, long a2, LLongBinaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.applyAsLong(a1, a2, factory, newMessage, param1, param2, param3);
	}

	static long tryApplyAsLong(long a1, long a2, LLongBinaryOperator func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.applyAsLong(a1, a2, factory);
	}

	static long tryApplyAsLongThen(long a1, long a2, LLongBinaryOperator func, @Nonnull LToLongFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsLongThen(a1, a2, handler);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default long nonNullApplyAsLong(long a1, long a2) {
		return applyAsLong(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LLongBinaryOperator.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, long a1, long a2, @Nonnull LLongBinaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.applyAsLong(a1, a2);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.applyAsLong(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, long a1, long a2, @Nonnull LLongBinaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.applyAsLong(a1, a2);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.applyAsLong(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, long a1, long a2, @Nonnull LLongBinaryOperator func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, func);
	}

	default LLongUnaryOperator _with(long a1) {
		return a2 -> applyAsLong(a1, a2);
	}

	default LLongUnaryOperator with(long a2) {
		return a1 -> applyAsLong(a1, a2);
	}

	/**  */
	public static LLongBinaryOperator uncurry(@Nonnull LLongFunction<LLongUnaryOperator> func) {
		Null.nonNullArg(func, "func");
		return (long a1, long a2) -> func.apply(a1).applyAsLong(a2);
	}

	/** Change function to one that ignores output. */
	default LBiLongConsumer toConsumer() {
		return this::applyAsLong;
	}

	/** Calls domain consumer before main function. */
	default LLongBinaryOperator beforeDo(@Nonnull LBiLongConsumer before) {
		Null.nonNullArg(before, "before");
		return (long a1, long a2) -> {
			before.accept(a1, a2);
			return applyAsLong(a1, a2);
		};
	}

	/** Calls codomain consumer after main function. */
	default LLongBinaryOperator afterDo(@Nonnull LLongConsumer after) {
		Null.nonNullArg(after, "after");
		return (long a1, long a2) -> {
			final long retval = applyAsLong(a1, a2);
			after.accept(retval);
			return retval;
		};
	}

	/** Creates function that always returns the same value. */
	static LLongBinaryOperator constant(long r) {
		return (a1, a2) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LLongBinaryOperator longBinaryOp(final @Nonnull LLongBinaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S implements LLongBinaryOperator {
		private LLongBinaryOperator target = null;
		@Override
		public long applyAsLongX(long a1, long a2) throws Throwable {
			return target.applyAsLongX(a1, a2);
		}
	}

	@Nonnull
	static LLongBinaryOperator recursive(final @Nonnull LFunction<LLongBinaryOperator, LLongBinaryOperator> selfLambda) {
		final S single = new S();
		LLongBinaryOperator func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static M mementoOf(long a1, long a2, LLongBinaryOperator function) {
		var initialValue = function.applyAsLong(a1, a2);
		return initializedMementoOf(initialValue, function);
	}

	public static M initializedMementoOf(long initialValue, LLongBinaryOperator function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static M deltaOf(long a1, long a2, LLongBinaryOperator function, LLongBinaryOperator deltaFunction) {
		var initialValue = function.applyAsLong(a1, a2);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static M deltaOf(long a1, long a2, LLongBinaryOperator function) {
		var initialValue = function.applyAsLong(a1, a2);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (x2 - x1));
	}

	public static M initializedDeltaOf(long initialValue, LLongBinaryOperator function, LLongBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.applyAsLong(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.applyAsLong(x1, x2));
	}

	public static M memento(long initialBaseValue, long initialValue, LLongBinaryOperator baseFunction, LLongTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LLongBinaryOperator.M)
	 */
	@NotThreadSafe
	final class M implements LLongBinaryOperator {

		private final LLongBinaryOperator baseFunction;
		private long lastBaseValue;
		private long lastValue;
		private final LLongTernaryOperator mementoFunction;

		private M(long lastBaseValue, long lastValue, LLongBinaryOperator baseFunction, LLongTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public long applyAsLongX(long a1, long a2) throws Throwable {
			long x1 = lastBaseValue;
			long x2 = lastBaseValue = baseFunction.applyAsLongX(a1, a2);

			return lastValue = mementoFunction.applyAsLongX(lastValue, x1, x2);
		}

		public long currentApplyAsLong(long a1, long a2) {
			long x1 = lastBaseValue;
			long x2 = baseFunction.applyAsLong(a1, a2);

			return mementoFunction.applyAsLong(lastValue, x1, x2);
		}

		public long lastValue() {
			return lastValue;
		}

		public long lastBaseValue() {
			return lastBaseValue;
		}

		public long currentBaseValue(long a1, long a2) {
			return baseFunction.applyAsLong(a1, a2);
		}
	}

	// </editor-fold>

	@Nonnull
	static LLongBinaryOperator longBinaryOpThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LLongBinaryOperator longBinaryOpThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce(message);
		};
	}

	static long call(long a1, long a2, final @Nonnull LLongBinaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsLong(a1, a2);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static LLongBinaryOperator wrap(final LongBinaryOperator other) {
		return other::applyAsLong;
	}
	// </editor-fold>

	/**
	 * Creates function that returns the lesser value according to the comparator.
	 * @see {@link java.util.function.BinaryOperator#minBy}
	 */
	@Nonnull
	static LLongBinaryOperator minBy(@Nonnull Comparator<Long> comparator) {
		Null.nonNullArg(comparator, "comparator");
		return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
	}

	/**
	 * Creates function that returns the lesser value according to the comparator.
	 * @see {@link java.util.function.BinaryOperator#maxBy}
	 */
	@Nonnull
	static LLongBinaryOperator maxBy(@Nonnull Comparator<Long> comparator) {
		Null.nonNullArg(comparator, "comparator");
		return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
	}

	/**
	 * Returns function that returns the lower value.
	 * @see {@link java.util.function.BinaryOperator#minBy}
	 */
	@Nonnull
	static LLongBinaryOperator min() {
		return Long::min;
	}

	/**
	 * Returns function that returns the higher value.
	 * @see {@link java.util.function.BinaryOperator#maxBy}
	 */
	@Nonnull
	static LLongBinaryOperator max() {
		return Long::max;
	}

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LLongBinaryOperator compose(@Nonnull final LLongUnaryOperator before1, @Nonnull final LLongUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.applyAsLong(before1.applyAsLong(v1), before2.applyAsLong(v2));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LToLongBiFunction<V1, V2> unboxingCompose(@Nonnull final LToLongFunction<? super V1> before1, @Nonnull final LToLongFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.applyAsLong(before1.applyAsLong(v1), before2.applyAsLong(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiLongFunction<V> then(@Nonnull LLongFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.apply(this.applyAsLong(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongBinaryOperator thenToLong(@Nonnull LLongUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsLong(this.applyAsLong(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiLongPredicate thenToBool(@Nonnull LLongPredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.test(this.applyAsLong(a1, a2));
	}

	// </editor-fold>

	default LLongBinaryOperator shoving() {

		return new LLongBinaryOperator() {

			public long applyAsLong(long a1, long a2) {
				try {
					return this.applyAsLongX(a1, a2);
				} catch (Throwable e) { // NOSONAR
					Handling.handleErrors(e);
					throw Handling.throwIt(e);
				}
			}

			public long applyAsLongX(long a1, long a2) throws Throwable {
				return LLongBinaryOperator.this.applyAsLongX(a1, a2);
			}

		};
	}

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LLongBinaryOperator) Operator */
	public static long doNothing(long a1, long a2) {
		return Function4U.defaultLong;
	}

}
