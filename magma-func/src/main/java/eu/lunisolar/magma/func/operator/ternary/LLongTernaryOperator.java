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

package eu.lunisolar.magma.func.operator.ternary;

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
 * Non-throwing functional interface (lambda) LLongTernaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 3): long a1,long a2,long a3
 *
 * Co-domain: long
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LLongTernaryOperator extends MetaOperator, MetaInterface.NonThrowing, Codomain<aLong>, Domain3<aLong, aLong, aLong> { // NOSONAR

	String DESCRIPTION = "LLongTernaryOperator: long applyAsLong(long a1,long a2,long a3)";

	// long applyAsLong(long a1,long a2,long a3) ;
	default long applyAsLong(long a1, long a2, long a3) {
		// return nestingApplyAsLong(a1,a2,a3);
		try {
			return this.applyAsLongX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsLong(long a1,long a2,long a3)
	 */
	long applyAsLongX(long a1, long a2, long a3) throws Throwable;

	default long tupleApplyAsLong(LLongTriple args) {
		return applyAsLong(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions according to the instructions. */
	default long handlingApplyAsLong(long a1, long a2, long a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsLongX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LLongTernaryOperator handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> handlingApplyAsLong(a1, a2, a3, handling);
	}

	default long applyAsLong(long a1, long a2, long a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyAsLongX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default long applyAsLong(long a1, long a2, long a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyAsLongX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default long applyAsLong(long a1, long a2, long a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyAsLongX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default long applyAsLong(long a1, long a2, long a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyAsLongX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LLongTernaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2, a3) -> applyAsLong(a1, a2, a3, factory, newMessage);
	}

	default LLongTernaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2, a3) -> applyAsLong(a1, a2, a3, factory, newMessage, param1);
	}

	default LLongTernaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2, a3) -> applyAsLong(a1, a2, a3, factory, newMessage, param1, param1);
	}

	default LLongTernaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2, a3) -> applyAsLong(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	default long applyAsLong(long a1, long a2, long a3, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyAsLongX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LLongTernaryOperator trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2, a3) -> applyAsLong(a1, a2, a3, factory);
	}

	default long applyAsLongThen(long a1, long a2, long a3, @Nonnull LToLongFunction<Throwable> handler) {
		try {
			return this.applyAsLongX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsLong(e);
		}
	}

	default LLongTernaryOperator tryingThen(@Nonnull LToLongFunction<Throwable> handler) {
		return (a1, a2, a3) -> applyAsLongThen(a1, a2, a3, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default long nestingApplyAsLong(long a1, long a2, long a3) {
		try {
			return this.applyAsLongX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default long shovingApplyAsLong(long a1, long a2, long a3) {
		try {
			return this.applyAsLongX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static long shovingApplyAsLong(long a1, long a2, long a3, LLongTernaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.shovingApplyAsLong(a1, a2, a3);
	}

	static long handlingApplyAsLong(long a1, long a2, long a3, LLongTernaryOperator func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsLong(a1, a2, a3, handling);
	}

	static long tryApplyAsLong(long a1, long a2, long a3, LLongTernaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsLong(a1, a2, a3);
	}

	static long tryApplyAsLong(long a1, long a2, long a3, LLongTernaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.applyAsLong(a1, a2, a3, factory, newMessage);
	}

	static long tryApplyAsLong(long a1, long a2, long a3, LLongTernaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.applyAsLong(a1, a2, a3, factory, newMessage, param1);
	}

	static long tryApplyAsLong(long a1, long a2, long a3, LLongTernaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.applyAsLong(a1, a2, a3, factory, newMessage, param1, param2);
	}

	static long tryApplyAsLong(long a1, long a2, long a3, LLongTernaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.applyAsLong(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	static long tryApplyAsLong(long a1, long a2, long a3, LLongTernaryOperator func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.applyAsLong(a1, a2, a3, factory);
	}

	static long tryApplyAsLongThen(long a1, long a2, long a3, LLongTernaryOperator func, @Nonnull LToLongFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsLongThen(a1, a2, a3, handler);
	}

	default long failSafeApplyAsLong(long a1, long a2, long a3, @Nonnull LLongTernaryOperator failSafe) {
		try {
			return applyAsLong(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.applyAsLong(a1, a2, a3);
		}
	}

	static long failSafeApplyAsLong(long a1, long a2, long a3, LLongTernaryOperator func, @Nonnull LLongTernaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.applyAsLong(a1, a2, a3);
		} else {
			return func.failSafeApplyAsLong(a1, a2, a3, failSafe);
		}
	}

	static LLongTernaryOperator failSafe(LLongTernaryOperator func, @Nonnull LLongTernaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeApplyAsLong(a1, a2, a3, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default long nonNullApplyAsLong(long a1, long a2, long a3) {
		return applyAsLong(a1, a2, a3);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LLongTernaryOperator.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, long a1, long a2, long a3, @Nonnull LLongTernaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.applyAsLong(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.applyAsLong(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, long a1, long a2, long a3, @Nonnull LLongTernaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.applyAsLong(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.applyAsLong(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, long a1, long a2, long a3, @Nonnull LLongTernaryOperator func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, a3, func);
	}

	/**  */
	public static LLongTernaryOperator uncurry(@Nonnull LLongFunction<LLongFunction<LLongUnaryOperator>> func) {
		Null.nonNullArg(func, "func");
		return (long a1, long a2, long a3) -> func.apply(a1).apply(a2).applyAsLong(a3);
	}

	/** Change function to consumer that ignores output. */
	default LTriLongConsumer toConsumer() {
		return this::applyAsLong;
	}

	/** Calls domain consumer before main function. */
	default LLongTernaryOperator beforeDo(@Nonnull LTriLongConsumer before) {
		Null.nonNullArg(before, "before");
		return (long a1, long a2, long a3) -> {
			before.accept(a1, a2, a3);
			return applyAsLong(a1, a2, a3);
		};
	}

	/** Calls codomain consumer after main function. */
	default LLongTernaryOperator afterDo(@Nonnull LLongConsumer after) {
		Null.nonNullArg(after, "after");
		return (long a1, long a2, long a3) -> {
			final long retval = applyAsLong(a1, a2, a3);
			after.accept(retval);
			return retval;
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LLongSupplier capture(long a1, long a2, long a3) {
		return () -> this.applyAsLong(a1, a2, a3);
	}

	/** Creates function that always returns the same value. */
	static LLongTernaryOperator constant(long r) {
		return (a1, a2, a3) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static LLongTernaryOperator apply1stAsLong(@Nonnull LLongUnaryOperator func) {
		return (a1, a2, a3) -> func.applyAsLong(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static LLongTernaryOperator apply2ndAsLong(@Nonnull LLongUnaryOperator func) {
		return (a1, a2, a3) -> func.applyAsLong(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static LLongTernaryOperator apply3rdAsLong(@Nonnull LLongUnaryOperator func) {
		return (a1, a2, a3) -> func.applyAsLong(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LLongTernaryOperator longTernaryOp(final @Nonnull LLongTernaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	final class S implements LLongTernaryOperator {
		private LLongTernaryOperator target = null;
		@Override
		public long applyAsLongX(long a1, long a2, long a3) throws Throwable {
			return target.applyAsLongX(a1, a2, a3);
		}
	}

	@Nonnull
	static LLongTernaryOperator recursive(final @Nonnull LFunction<LLongTernaryOperator, LLongTernaryOperator> selfLambda) {
		final S single = new S();
		LLongTernaryOperator func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	public static M mementoOf(long a1, long a2, long a3, LLongTernaryOperator function) {
		var initialValue = function.applyAsLong(a1, a2, a3);
		return initializedMementoOf(initialValue, function);
	}

	public static M initializedMementoOf(long initialValue, LLongTernaryOperator function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static M deltaOf(long a1, long a2, long a3, LLongTernaryOperator function, LLongBinaryOperator deltaFunction) {
		var initialValue = function.applyAsLong(a1, a2, a3);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static M deltaOf(long a1, long a2, long a3, LLongTernaryOperator function) {
		var initialValue = function.applyAsLong(a1, a2, a3);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (x2 - x1));
	}

	public static M initializedDeltaOf(long initialValue, LLongTernaryOperator function, LLongBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.applyAsLong(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.applyAsLong(x1, x2));
	}

	public static M memento(long initialBaseValue, long initialValue, LLongTernaryOperator baseFunction, LLongTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LLongTernaryOperator.M)
	 */
	@NotThreadSafe
	final class M implements LLongTernaryOperator {

		private final LLongTernaryOperator baseFunction;
		private long lastBaseValue;
		private long lastValue;
		private final LLongTernaryOperator mementoFunction;

		private M(long lastBaseValue, long lastValue, LLongTernaryOperator baseFunction, LLongTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public long applyAsLongX(long a1, long a2, long a3) throws Throwable {
			long x1 = lastBaseValue;
			long x2 = lastBaseValue = baseFunction.applyAsLongX(a1, a2, a3);

			return lastValue = mementoFunction.applyAsLong(lastValue, x1, x2);
		}

		public long lastValue() {
			return lastValue;
		};

		public long lastBaseValue() {
			return lastBaseValue;
		};
	}

	@Nonnull
	static LLongTernaryOperator longTernaryOpThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LLongTernaryOperator longTernaryOpThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce(message);
		};
	}

	static long call(long a1, long a2, long a3, final @Nonnull LLongTernaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsLong(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LLongTernaryOperator compose(@Nonnull final LLongUnaryOperator before1, @Nonnull final LLongUnaryOperator before2, @Nonnull final LLongUnaryOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.applyAsLong(before1.applyAsLong(v1), before2.applyAsLong(v2), before3.applyAsLong(v3));
	}

	public static LLongTernaryOperator composed(@Nonnull final LLongUnaryOperator before1, @Nonnull final LLongUnaryOperator before2, @Nonnull final LLongUnaryOperator before3, LLongTernaryOperator after) {
		return after.compose(before1, before2, before3);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LTriLongFunction<V> then(@Nonnull LLongFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.apply(this.applyAsLong(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongTernaryOperator thenToLong(@Nonnull LLongUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.applyAsLong(this.applyAsLong(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LTriLongPredicate thenToBool(@Nonnull LLongPredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.test(this.applyAsLong(a1, a2, a3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LLongTernaryOperator) Operator */
	public static long doNothing(long a1, long a2, long a3) {
		return Function4U.defaultLong;
	}

}
