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

package eu.lunisolar.magma.func.function.to;

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
 * Non-throwing functional interface (lambda) LToDblBiFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 2): T1 a1,T2 a2
 *
 * Co-domain: double
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LToDblBiFunction<T1, T2> extends ToDoubleBiFunction<T1, T2>, MetaFunction, MetaInterface.NonThrowing, Codomain<aDouble>, Domain2<a<T1>, a<T2>> { //NOSONAR

	String DESCRIPTION = "LToDblBiFunction: double applyAsDbl(T1 a1,T2 a2)";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * Calling this method via LToDblBiFunction interface should be discouraged.
	 */
	@Override
	default double applyAsDouble(T1 a1, T2 a2) {
		return this.applyAsDbl(a1, a2);
	}

	default double applyAsDbl(T1 a1, T2 a2) {
		try {
			return this.applyAsDblX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsDbl(T1 a1,T2 a2)
	 */
	double applyAsDblX(T1 a1, T2 a2) throws Throwable;

	default double tupleApplyAsDbl(LPair<T1, T2> args) {
		return applyAsDbl(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default double handlingApplyAsDbl(T1 a1, T2 a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsDblX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LToDblBiFunction<T1, T2> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2) -> handlingApplyAsDbl(a1, a2, handling);
	}

	default double applyAsDbl(T1 a1, T2 a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyAsDblX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default double applyAsDbl(T1 a1, T2 a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyAsDblX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default double applyAsDbl(T1 a1, T2 a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyAsDblX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default double applyAsDbl(T1 a1, T2 a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyAsDblX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LToDblBiFunction<T1, T2> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2) -> applyAsDbl(a1, a2, factory, newMessage);
	}

	default LToDblBiFunction<T1, T2> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2) -> applyAsDbl(a1, a2, factory, newMessage, param1);
	}

	default LToDblBiFunction<T1, T2> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2) -> applyAsDbl(a1, a2, factory, newMessage, param1, param1);
	}

	default LToDblBiFunction<T1, T2> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2) -> applyAsDbl(a1, a2, factory, newMessage, param1, param2, param3);
	}

	default double applyAsDbl(T1 a1, T2 a2, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyAsDblX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LToDblBiFunction<T1, T2> trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2) -> applyAsDbl(a1, a2, factory);
	}

	default double applyAsDblThen(T1 a1, T2 a2, @Nonnull LToDblFunction<Throwable> handler) {
		try {
			return this.applyAsDblX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsDbl(e);
		}
	}

	default LToDblBiFunction<T1, T2> tryingThen(@Nonnull LToDblFunction<Throwable> handler) {
		return (a1, a2) -> applyAsDblThen(a1, a2, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default double nestingApplyAsDbl(T1 a1, T2 a2) {
		try {
			return this.applyAsDblX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default double shovingApplyAsDbl(T1 a1, T2 a2) {
		try {
			return this.applyAsDblX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T1, T2> double shovingApplyAsDbl(T1 a1, T2 a2, LToDblBiFunction<T1, T2> func) {
		Null.nonNullArg(func, "func");
		return func.shovingApplyAsDbl(a1, a2);
	}

	static <T1, T2> double handlingApplyAsDbl(T1 a1, T2 a2, LToDblBiFunction<T1, T2> func, HandlingInstructions<Throwable, RuntimeException> handling) { //<-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsDbl(a1, a2, handling);
	}

	static <T1, T2> double tryApplyAsDbl(T1 a1, T2 a2, LToDblBiFunction<T1, T2> func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsDbl(a1, a2);
	}

	static <T1, T2> double tryApplyAsDbl(T1 a1, T2 a2, LToDblBiFunction<T1, T2> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.applyAsDbl(a1, a2, factory, newMessage);
	}

	static <T1, T2> double tryApplyAsDbl(T1 a1, T2 a2, LToDblBiFunction<T1, T2> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.applyAsDbl(a1, a2, factory, newMessage, param1);
	}

	static <T1, T2> double tryApplyAsDbl(T1 a1, T2 a2, LToDblBiFunction<T1, T2> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.applyAsDbl(a1, a2, factory, newMessage, param1, param2);
	}

	static <T1, T2> double tryApplyAsDbl(T1 a1, T2 a2, LToDblBiFunction<T1, T2> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.applyAsDbl(a1, a2, factory, newMessage, param1, param2, param3);
	}

	static <T1, T2> double tryApplyAsDbl(T1 a1, T2 a2, LToDblBiFunction<T1, T2> func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.applyAsDbl(a1, a2, factory);
	}

	static <T1, T2> double tryApplyAsDblThen(T1 a1, T2 a2, LToDblBiFunction<T1, T2> func, @Nonnull LToDblFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsDblThen(a1, a2, handler);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default double nonNullApplyAsDbl(T1 a1, T2 a2) {
		return applyAsDbl(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LToDblBiFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void fromTo(int min_i, int max_i, T1 a1, T2 a2, @Nonnull LToDblBiFunction<T1, T2> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.applyAsDbl(a1, a2);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.applyAsDbl(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void fromTill(int min_i, int max_i, T1 a1, T2 a2, @Nonnull LToDblBiFunction<T1, T2> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.applyAsDbl(a1, a2);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.applyAsDbl(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void times(int max_i, T1 a1, T2 a2, @Nonnull LToDblBiFunction<T1, T2> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, func);
	}

	/** Extract and apply function. */
	public static <M, K, V, T2> double from(@Nonnull M container, LBiFunction<M, K, V> extractor, K key, T2 a2, @Nonnull LToDblBiFunction<V, T2> function, double orElse) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.applyAsDbl(value, a2);
		}

		return orElse;
	}

	default LToDblFunction<T2> _with(T1 a1) {
		return a2 -> applyAsDbl(a1, a2);
	}

	default LToDblFunction<T1> with(T2 a2) {
		return a1 -> applyAsDbl(a1, a2);
	}

	/**  */
	public static <T1, T2> LToDblBiFunction<T1, T2> uncurry(@Nonnull LFunction<T1, LToDblFunction<T2>> func) {
		Null.nonNullArg(func, "func");
		return (T1 a1, T2 a2) -> func.apply(a1).applyAsDbl(a2);
	}

	/** Change function to one that ignores output. */
	default LBiConsumer<T1, T2> toConsumer() {
		return this::applyAsDbl;
	}

	/** Calls domain consumer before main function. */
	default LToDblBiFunction<T1, T2> beforeDo(@Nonnull LBiConsumer<T1, T2> before) {
		Null.nonNullArg(before, "before");
		return (T1 a1, T2 a2) -> {
			before.accept(a1, a2);
			return applyAsDbl(a1, a2);
		};
	}

	/** Calls codomain consumer after main function. */
	default LToDblBiFunction<T1, T2> afterDo(@Nonnull LDblConsumer after) {
		Null.nonNullArg(after, "after");
		return (T1 a1, T2 a2) -> {
			final double retval = applyAsDbl(a1, a2);
			after.accept(retval);
			return retval;
		};
	}

	/** Creates function that always returns the same value. */
	static <T1, T2> LToDblBiFunction<T1, T2> constant(double r) {
		return (a1, a2) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2> LToDblBiFunction<T1, T2> toDblBiFunc(final @Nonnull LToDblBiFunction<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S<T1, T2> implements LToDblBiFunction<T1, T2> {
		private LToDblBiFunction<T1, T2> target = null;
		@Override
		public double applyAsDblX(T1 a1, T2 a2) throws Throwable {
			return target.applyAsDblX(a1, a2);
		}
	}

	@Nonnull
	static <T1, T2> LToDblBiFunction<T1, T2> recursive(final @Nonnull LFunction<LToDblBiFunction<T1, T2>, LToDblBiFunction<T1, T2>> selfLambda) {
		final S<T1, T2> single = new S();
		LToDblBiFunction<T1, T2> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static <T1, T2> M<T1, T2> mementoOf(T1 a1, T2 a2, LToDblBiFunction<T1, T2> function) {
		var initialValue = function.applyAsDbl(a1, a2);
		return initializedMementoOf(initialValue, function);
	}

	public static <T1, T2> M<T1, T2> initializedMementoOf(double initialValue, LToDblBiFunction<T1, T2> function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static <T1, T2> M<T1, T2> deltaOf(T1 a1, T2 a2, LToDblBiFunction<T1, T2> function, LDblBinaryOperator deltaFunction) {
		var initialValue = function.applyAsDbl(a1, a2);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static <T1, T2> M<T1, T2> deltaOf(T1 a1, T2 a2, LToDblBiFunction<T1, T2> function) {
		var initialValue = function.applyAsDbl(a1, a2);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (x2 - x1));
	}

	public static <T1, T2> M<T1, T2> initializedDeltaOf(double initialValue, LToDblBiFunction<T1, T2> function, LDblBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.applyAsDbl(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.applyAsDbl(x1, x2));
	}

	public static <T1, T2> M<T1, T2> memento(double initialBaseValue, double initialValue, LToDblBiFunction<T1, T2> baseFunction, LDblTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LToDblBiFunction.M)
	 */
	@NotThreadSafe
	final class M<T1, T2> implements LToDblBiFunction<T1, T2> {

		private final LToDblBiFunction<T1, T2> baseFunction;
		private double lastBaseValue;
		private double lastValue;
		private final LDblTernaryOperator mementoFunction;

		private M(double lastBaseValue, double lastValue, LToDblBiFunction<T1, T2> baseFunction, LDblTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public double applyAsDblX(T1 a1, T2 a2) throws Throwable {
			double x1 = lastBaseValue;
			double x2 = lastBaseValue = baseFunction.applyAsDblX(a1, a2);

			return lastValue = mementoFunction.applyAsDblX(lastValue, x1, x2);
		}

		public double currentApplyAsDbl(T1 a1, T2 a2) {
			double x1 = lastBaseValue;
			double x2 = baseFunction.applyAsDbl(a1, a2);

			return mementoFunction.applyAsDbl(lastValue, x1, x2);
		}

		public double lastValue() {
			return lastValue;
		}

		public double lastBaseValue() {
			return lastBaseValue;
		}

		public double currentBaseValue(T1 a1, T2 a2) {
			return baseFunction.applyAsDbl(a1, a2);
		}
	}

	// </editor-fold>

	@Nonnull
	static <T1, T2> LToDblBiFunction<T1, T2> toDblBiFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T1, T2> LToDblBiFunction<T1, T2> toDblBiFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce(message);
		};
	}

	static <T1, T2> double call(T1 a1, T2 a2, final @Nonnull LToDblBiFunction<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsDbl(a1, a2);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <T1, T2> LToDblBiFunction<T1, T2> wrap(final ToDoubleBiFunction<T1, T2> other) {
		return other::applyAsDouble;
	}
	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LToDblBiFunction<V1, V2> compose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.applyAsDbl(before1.apply(v1), before2.apply(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiFunction<T1, T2, V> then(@Nonnull LDblFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.apply(this.applyAsDbl(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToByteBiFunction<T1, T2> thenToByte(@Nonnull LDblToByteFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsByte(this.applyAsDbl(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToSrtBiFunction<T1, T2> thenToSrt(@Nonnull LDblToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsSrt(this.applyAsDbl(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToIntBiFunction<T1, T2> thenToInt(@Nonnull LDblToIntFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsInt(this.applyAsDbl(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToLongBiFunction<T1, T2> thenToLong(@Nonnull LDblToLongFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsLong(this.applyAsDbl(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToFltBiFunction<T1, T2> thenToFlt(@Nonnull LDblToFltFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsFlt(this.applyAsDbl(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToDblBiFunction<T1, T2> thenToDbl(@Nonnull LDblUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsDbl(this.applyAsDbl(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToCharBiFunction<T1, T2> thenToChar(@Nonnull LDblToCharFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsChar(this.applyAsDbl(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiPredicate<T1, T2> thenToBool(@Nonnull LDblPredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.test(this.applyAsDbl(a1, a2));
	}

	// </editor-fold>

	default LToDblBiFunction<T1, T2> shoving() {

		return new LToDblBiFunction<T1, T2>() {

			public double applyAsDbl(T1 a1, T2 a2) {
				try {
					return this.applyAsDblX(a1, a2);
				} catch (Throwable e) { // NOSONAR
					Handling.handleErrors(e);
					throw Handling.throwIt(e);
				}
			}

			public double applyAsDblX(T1 a1, T2 a2) throws Throwable {
				return LToDblBiFunction.this.applyAsDblX(a1, a2);
			}

		};
	}

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LToDblBiFunction) Function */
	public static <T1, T2> double doNothing(T1 a1, T2 a2) {
		return Function4U.defaultDouble;
	}

}
