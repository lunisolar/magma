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
 * Non-throwing functional interface (lambda) LToIntBiFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 2): T1 a1,T2 a2
 *
 * Co-domain: int
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LToIntBiFunction<T1, T2> extends ToIntBiFunction<T1, T2>, MetaFunction, MetaInterface.NonThrowing, Codomain<aInt>, Domain2<a<T1>, a<T2>> { //NOSONAR

	String DESCRIPTION = "LToIntBiFunction: int applyAsInt(T1 a1,T2 a2)";

	default int applyAsInt(T1 a1, T2 a2) {
		try {
			return this.applyAsIntX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsInt(T1 a1,T2 a2)
	 */
	int applyAsIntX(T1 a1, T2 a2) throws Throwable;

	default int tupleApplyAsInt(LPair<T1, T2> args) {
		return applyAsInt(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default int handlingApplyAsInt(T1 a1, T2 a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsIntX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LToIntBiFunction<T1, T2> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2) -> handlingApplyAsInt(a1, a2, handling);
	}

	default int applyAsInt(T1 a1, T2 a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyAsIntX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default int applyAsInt(T1 a1, T2 a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyAsIntX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default int applyAsInt(T1 a1, T2 a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyAsIntX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default int applyAsInt(T1 a1, T2 a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyAsIntX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LToIntBiFunction<T1, T2> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2) -> applyAsInt(a1, a2, factory, newMessage);
	}

	default LToIntBiFunction<T1, T2> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2) -> applyAsInt(a1, a2, factory, newMessage, param1);
	}

	default LToIntBiFunction<T1, T2> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2) -> applyAsInt(a1, a2, factory, newMessage, param1, param1);
	}

	default LToIntBiFunction<T1, T2> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2) -> applyAsInt(a1, a2, factory, newMessage, param1, param2, param3);
	}

	default int applyAsInt(T1 a1, T2 a2, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyAsIntX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LToIntBiFunction<T1, T2> trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2) -> applyAsInt(a1, a2, factory);
	}

	default int applyAsIntThen(T1 a1, T2 a2, @Nonnull LToIntFunction<Throwable> handler) {
		try {
			return this.applyAsIntX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsInt(e);
		}
	}

	default LToIntBiFunction<T1, T2> tryingThen(@Nonnull LToIntFunction<Throwable> handler) {
		return (a1, a2) -> applyAsIntThen(a1, a2, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default int nestingApplyAsInt(T1 a1, T2 a2) {
		try {
			return this.applyAsIntX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default int shovingApplyAsInt(T1 a1, T2 a2) {
		try {
			return this.applyAsIntX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T1, T2> int shovingApplyAsInt(T1 a1, T2 a2, LToIntBiFunction<T1, T2> func) {
		Null.nonNullArg(func, "func");
		return func.shovingApplyAsInt(a1, a2);
	}

	static <T1, T2> int handlingApplyAsInt(T1 a1, T2 a2, LToIntBiFunction<T1, T2> func, HandlingInstructions<Throwable, RuntimeException> handling) { //<-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsInt(a1, a2, handling);
	}

	static <T1, T2> int tryApplyAsInt(T1 a1, T2 a2, LToIntBiFunction<T1, T2> func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsInt(a1, a2);
	}

	static <T1, T2> int tryApplyAsInt(T1 a1, T2 a2, LToIntBiFunction<T1, T2> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.applyAsInt(a1, a2, factory, newMessage);
	}

	static <T1, T2> int tryApplyAsInt(T1 a1, T2 a2, LToIntBiFunction<T1, T2> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.applyAsInt(a1, a2, factory, newMessage, param1);
	}

	static <T1, T2> int tryApplyAsInt(T1 a1, T2 a2, LToIntBiFunction<T1, T2> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.applyAsInt(a1, a2, factory, newMessage, param1, param2);
	}

	static <T1, T2> int tryApplyAsInt(T1 a1, T2 a2, LToIntBiFunction<T1, T2> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.applyAsInt(a1, a2, factory, newMessage, param1, param2, param3);
	}

	static <T1, T2> int tryApplyAsInt(T1 a1, T2 a2, LToIntBiFunction<T1, T2> func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.applyAsInt(a1, a2, factory);
	}

	static <T1, T2> int tryApplyAsIntThen(T1 a1, T2 a2, LToIntBiFunction<T1, T2> func, @Nonnull LToIntFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsIntThen(a1, a2, handler);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default int nonNullApplyAsInt(T1 a1, T2 a2) {
		return applyAsInt(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LToIntBiFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void fromTo(int min_i, int max_i, T1 a1, T2 a2, @Nonnull LToIntBiFunction<T1, T2> func) {
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
	public static <T1, T2> void fromTill(int min_i, int max_i, T1 a1, T2 a2, @Nonnull LToIntBiFunction<T1, T2> func) {
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
	public static <T1, T2> void times(int max_i, T1 a1, T2 a2, @Nonnull LToIntBiFunction<T1, T2> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, func);
	}

	/** Extract and apply function. */
	public static <M, K, V, T2> int from(@Nonnull M container, LBiFunction<M, K, V> extractor, K key, T2 a2, @Nonnull LToIntBiFunction<V, T2> function, int orElse) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.applyAsInt(value, a2);
		}

		return orElse;
	}

	default LToIntFunction<T2> _with(T1 a1) {
		return a2 -> applyAsInt(a1, a2);
	}

	default LToIntFunction<T1> with(T2 a2) {
		return a1 -> applyAsInt(a1, a2);
	}

	/**  */
	public static <T1, T2> LToIntBiFunction<T1, T2> uncurry(@Nonnull LFunction<T1, LToIntFunction<T2>> func) {
		Null.nonNullArg(func, "func");
		return (T1 a1, T2 a2) -> func.apply(a1).applyAsInt(a2);
	}

	/** Change function to one that ignores output. */
	default LBiConsumer<T1, T2> toConsumer() {
		return this::applyAsInt;
	}

	/** Calls domain consumer before main function. */
	default LToIntBiFunction<T1, T2> beforeDo(@Nonnull LBiConsumer<T1, T2> before) {
		Null.nonNullArg(before, "before");
		return (T1 a1, T2 a2) -> {
			before.accept(a1, a2);
			return applyAsInt(a1, a2);
		};
	}

	/** Calls codomain consumer after main function. */
	default LToIntBiFunction<T1, T2> afterDo(@Nonnull LIntConsumer after) {
		Null.nonNullArg(after, "after");
		return (T1 a1, T2 a2) -> {
			final int retval = applyAsInt(a1, a2);
			after.accept(retval);
			return retval;
		};
	}

	/** Creates function that always returns the same value. */
	static <T1, T2> LToIntBiFunction<T1, T2> constant(int r) {
		return (a1, a2) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2> LToIntBiFunction<T1, T2> toIntBiFunc(final @Nonnull LToIntBiFunction<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S<T1, T2> implements LToIntBiFunction<T1, T2> {
		private LToIntBiFunction<T1, T2> target = null;
		@Override
		public int applyAsIntX(T1 a1, T2 a2) throws Throwable {
			return target.applyAsIntX(a1, a2);
		}
	}

	@Nonnull
	static <T1, T2> LToIntBiFunction<T1, T2> recursive(final @Nonnull LFunction<LToIntBiFunction<T1, T2>, LToIntBiFunction<T1, T2>> selfLambda) {
		final S<T1, T2> single = new S();
		LToIntBiFunction<T1, T2> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static <T1, T2> M<T1, T2> mementoOf(T1 a1, T2 a2, LToIntBiFunction<T1, T2> function) {
		var initialValue = function.applyAsInt(a1, a2);
		return initializedMementoOf(initialValue, function);
	}

	public static <T1, T2> M<T1, T2> initializedMementoOf(int initialValue, LToIntBiFunction<T1, T2> function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static <T1, T2> M<T1, T2> deltaOf(T1 a1, T2 a2, LToIntBiFunction<T1, T2> function, LIntBinaryOperator deltaFunction) {
		var initialValue = function.applyAsInt(a1, a2);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static <T1, T2> M<T1, T2> deltaOf(T1 a1, T2 a2, LToIntBiFunction<T1, T2> function) {
		var initialValue = function.applyAsInt(a1, a2);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (x2 - x1));
	}

	public static <T1, T2> M<T1, T2> initializedDeltaOf(int initialValue, LToIntBiFunction<T1, T2> function, LIntBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.applyAsInt(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.applyAsInt(x1, x2));
	}

	public static <T1, T2> M<T1, T2> memento(int initialBaseValue, int initialValue, LToIntBiFunction<T1, T2> baseFunction, LIntTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LToIntBiFunction.M)
	 */
	@NotThreadSafe
	final class M<T1, T2> implements LToIntBiFunction<T1, T2> {

		private final LToIntBiFunction<T1, T2> baseFunction;
		private int lastBaseValue;
		private int lastValue;
		private final LIntTernaryOperator mementoFunction;

		private M(int lastBaseValue, int lastValue, LToIntBiFunction<T1, T2> baseFunction, LIntTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public int applyAsIntX(T1 a1, T2 a2) throws Throwable {
			int x1 = lastBaseValue;
			int x2 = lastBaseValue = baseFunction.applyAsIntX(a1, a2);

			return lastValue = mementoFunction.applyAsIntX(lastValue, x1, x2);
		}

		public int currentApplyAsInt(T1 a1, T2 a2) {
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

		public int currentBaseValue(T1 a1, T2 a2) {
			return baseFunction.applyAsInt(a1, a2);
		}
	}

	// </editor-fold>

	@Nonnull
	static <T1, T2> LToIntBiFunction<T1, T2> toIntBiFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T1, T2> LToIntBiFunction<T1, T2> toIntBiFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce(message);
		};
	}

	static <T1, T2> int call(T1 a1, T2 a2, final @Nonnull LToIntBiFunction<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsInt(a1, a2);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <T1, T2> LToIntBiFunction<T1, T2> wrap(final ToIntBiFunction<T1, T2> other) {
		return other::applyAsInt;
	}
	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LToIntBiFunction<V1, V2> compose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.applyAsInt(before1.apply(v1), before2.apply(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiFunction<T1, T2, V> then(@Nonnull LIntFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.apply(this.applyAsInt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToByteBiFunction<T1, T2> thenToByte(@Nonnull LIntToByteFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsByte(this.applyAsInt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToSrtBiFunction<T1, T2> thenToSrt(@Nonnull LIntToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsSrt(this.applyAsInt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToIntBiFunction<T1, T2> thenToInt(@Nonnull LIntUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsInt(this.applyAsInt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToLongBiFunction<T1, T2> thenToLong(@Nonnull LIntToLongFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsLong(this.applyAsInt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToFltBiFunction<T1, T2> thenToFlt(@Nonnull LIntToFltFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsFlt(this.applyAsInt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToDblBiFunction<T1, T2> thenToDbl(@Nonnull LIntToDblFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsDbl(this.applyAsInt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToCharBiFunction<T1, T2> thenToChar(@Nonnull LIntToCharFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsChar(this.applyAsInt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiPredicate<T1, T2> thenToBool(@Nonnull LIntPredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.test(this.applyAsInt(a1, a2));
	}

	// </editor-fold>

	default LToIntBiFunction<T1, T2> shoving() {

		return new LToIntBiFunction<T1, T2>() {

			public int applyAsInt(T1 a1, T2 a2) {
				try {
					return this.applyAsIntX(a1, a2);
				} catch (Throwable e) { // NOSONAR
					Handling.handleErrors(e);
					throw Handling.throwIt(e);
				}
			}

			public int applyAsIntX(T1 a1, T2 a2) throws Throwable {
				return LToIntBiFunction.this.applyAsIntX(a1, a2);
			}

		};
	}

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LToIntBiFunction) Function */
	public static <T1, T2> int doNothing(T1 a1, T2 a2) {
		return Function4U.defaultInteger;
	}

}
