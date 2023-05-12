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
 * Non-throwing functional interface (lambda) LToSrtFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): T a
 *
 * Co-domain: short
 *
 * Special case of function that corresponds to expressions like (iterator) -> Iterator::next
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LToSrtFunction<T> extends MetaFunction, MetaInterface.NonThrowing, OFunction<T, aShort>, Codomain<aShort>, Domain1<a<T>> { // NOSONAR

	String DESCRIPTION = "LToSrtFunction: short applyAsSrt(T a)";

	// short applyAsSrt(T a) ;
	default short applyAsSrt(T a) {
		// return nestingApplyAsSrt(a);
		try {
			return this.applyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsSrt(T a)
	 */
	short applyAsSrtX(T a) throws Throwable;

	default short tupleApplyAsSrt(LSingle<T> args) {
		return applyAsSrt(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default short handlingApplyAsSrt(T a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LToSrtFunction<T> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> handlingApplyAsSrt(a, handling);
	}

	default short applyAsSrt(T a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default short applyAsSrt(T a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default short applyAsSrt(T a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default short applyAsSrt(T a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LToSrtFunction<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return a -> applyAsSrt(a, factory, newMessage);
	}

	default LToSrtFunction<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return a -> applyAsSrt(a, factory, newMessage, param1);
	}

	default LToSrtFunction<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return a -> applyAsSrt(a, factory, newMessage, param1, param1);
	}

	default LToSrtFunction<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return a -> applyAsSrt(a, factory, newMessage, param1, param2, param3);
	}

	default short applyAsSrt(T a, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LToSrtFunction<T> trying(@Nonnull ExWF<RuntimeException> factory) {
		return a -> applyAsSrt(a, factory);
	}

	default short applyAsSrtThen(T a, @Nonnull LToSrtFunction<Throwable> handler) {
		try {
			return this.applyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsSrt(e);
		}
	}

	default LToSrtFunction<T> tryingThen(@Nonnull LToSrtFunction<Throwable> handler) {
		return a -> applyAsSrtThen(a, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default short nestingApplyAsSrt(T a) {
		try {
			return this.applyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default short shovingApplyAsSrt(T a) {
		try {
			return this.applyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> short shovingApplyAsSrt(T a, LToSrtFunction<T> func) {
		Null.nonNullArg(func, "func");
		return func.shovingApplyAsSrt(a);
	}

	static <T> short handlingApplyAsSrt(T a, LToSrtFunction<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsSrt(a, handling);
	}

	static <T> short tryApplyAsSrt(T a, LToSrtFunction<T> func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsSrt(a);
	}

	static <T> short tryApplyAsSrt(T a, LToSrtFunction<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrt(a, factory, newMessage);
	}

	static <T> short tryApplyAsSrt(T a, LToSrtFunction<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrt(a, factory, newMessage, param1);
	}

	static <T> short tryApplyAsSrt(T a, LToSrtFunction<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrt(a, factory, newMessage, param1, param2);
	}

	static <T> short tryApplyAsSrt(T a, LToSrtFunction<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrt(a, factory, newMessage, param1, param2, param3);
	}

	static <T> short tryApplyAsSrt(T a, LToSrtFunction<T> func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrt(a, factory);
	}

	static <T> short tryApplyAsSrtThen(T a, LToSrtFunction<T> func, @Nonnull LToSrtFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrtThen(a, handler);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default short nonNullApplyAsSrt(T a) {
		return applyAsSrt(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LToSrtFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_i, int max_i, T a, @Nonnull LToSrtFunction<T> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.applyAsSrt(a);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.applyAsSrt(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTill(int min_i, int max_i, T a, @Nonnull LToSrtFunction<T> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.applyAsSrt(a);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.applyAsSrt(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void times(int max_i, T a, @Nonnull LToSrtFunction<T> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a, func);
	}

	/** Extract and apply function. */
	public static <M, K, V> short from(@Nonnull M container, LBiFunction<M, K, V> extractor, K key, @Nonnull LToSrtFunction<V> function, short orElse) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.applyAsSrt(value);
		}

		return orElse;
	}

	/** Change function to consumer that ignores output. */
	default LConsumer<T> toConsumer() {
		return this::applyAsSrt;
	}

	/** Calls domain consumer before main function. */
	default LToSrtFunction<T> beforeDo(@Nonnull LConsumer<T> before) {
		Null.nonNullArg(before, "before");
		return (T a) -> {
			before.accept(a);
			return applyAsSrt(a);
		};
	}

	/** Calls codomain consumer after main function. */
	default LToSrtFunction<T> afterDo(@Nonnull LSrtConsumer after) {
		Null.nonNullArg(after, "after");
		return (T a) -> {
			final short retval = applyAsSrt(a);
			after.accept(retval);
			return retval;
		};
	}

	/** Creates function that always returns the same value. */
	static <T> LToSrtFunction<T> constant(short r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LToSrtFunction<T> toSrtFunc(final @Nonnull LToSrtFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S<T> implements LToSrtFunction<T> {
		private LToSrtFunction<T> target = null;
		@Override
		public short applyAsSrtX(T a) throws Throwable {
			return target.applyAsSrtX(a);
		}
	}

	@Nonnull
	static <T> LToSrtFunction<T> recursive(final @Nonnull LFunction<LToSrtFunction<T>, LToSrtFunction<T>> selfLambda) {
		final S<T> single = new S();
		LToSrtFunction<T> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static <T> M<T> mementoOf(T a, LToSrtFunction<T> function) {
		var initialValue = function.applyAsSrt(a);
		return initializedMementoOf(initialValue, function);
	}

	public static <T> M<T> initializedMementoOf(short initialValue, LToSrtFunction<T> function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static <T> M<T> deltaOf(T a, LToSrtFunction<T> function, LSrtBinaryOperator deltaFunction) {
		var initialValue = function.applyAsSrt(a);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static <T> M<T> deltaOf(T a, LToSrtFunction<T> function) {
		var initialValue = function.applyAsSrt(a);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (short) (x2 - x1));
	}

	public static <T> M<T> initializedDeltaOf(short initialValue, LToSrtFunction<T> function, LSrtBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.applyAsSrt(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.applyAsSrt(x1, x2));
	}

	public static <T> M<T> memento(short initialBaseValue, short initialValue, LToSrtFunction<T> baseFunction, LSrtTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LToSrtFunction.M)
	 */
	@NotThreadSafe
	final class M<T> implements LToSrtFunction<T> {

		private final LToSrtFunction<T> baseFunction;
		private short lastBaseValue;
		private short lastValue;
		private final LSrtTernaryOperator mementoFunction;

		private M(short lastBaseValue, short lastValue, LToSrtFunction<T> baseFunction, LSrtTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public short applyAsSrtX(T a) throws Throwable {
			short x1 = lastBaseValue;
			short x2 = lastBaseValue = baseFunction.applyAsSrtX(a);

			return lastValue = mementoFunction.applyAsSrtX(lastValue, x1, x2);
		}

		public short currentApplyAsSrt(T a) {
			short x1 = lastBaseValue;
			short x2 = baseFunction.applyAsSrt(a);

			return mementoFunction.applyAsSrt(lastValue, x1, x2);
		}

		public short lastValue() {
			return lastValue;
		};

		public short lastBaseValue() {
			return lastBaseValue;
		};
	}

	// </editor-fold>

	@Nonnull
	static <T> LToSrtFunction<T> toSrtFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T> LToSrtFunction<T> toSrtFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce(message);
		};
	}

	static <T> short call(T a, final @Nonnull LToSrtFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsSrt(a);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToSrtFunction<V> compose(@Nonnull final LFunction<? super V, ? extends T> before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsSrt(before.apply(v));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LFunction<T, V> then(@Nonnull LSrtFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.apply(this.applyAsSrt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToByteFunction<T> thenToByte(@Nonnull LSrtToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsByte(this.applyAsSrt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToSrtFunction<T> thenToSrt(@Nonnull LSrtUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsSrt(this.applyAsSrt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToIntFunction<T> thenToInt(@Nonnull LSrtToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsInt(this.applyAsSrt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToLongFunction<T> thenToLong(@Nonnull LSrtToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsLong(this.applyAsSrt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToFltFunction<T> thenToFlt(@Nonnull LSrtToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsFlt(this.applyAsSrt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToDblFunction<T> thenToDbl(@Nonnull LSrtToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsDbl(this.applyAsSrt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToCharFunction<T> thenToChar(@Nonnull LSrtToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsChar(this.applyAsSrt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LPredicate<T> thenToBool(@Nonnull LSrtPredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.test(this.applyAsSrt(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LToSrtFunction) Function */
	public static <T> short doNothing(T a) {
		return Function4U.defaultShort;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C0> void forEach(IndexedRead<C0, a<T>> ia, C0 source, LSrtConsumer consumer) {
		int size = ia.size(source);
		LOiFunction<Object, T> oiFunc0 = (LOiFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			T a = oiFunc0.apply(source, i);
			consumer.accept(this.applyAsSrt(a));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C0, I0> void iterate(SequentialRead<C0, I0, a<T>> sa, C0 source, LSrtConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).apply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LFunction<Object, T> nextFunc0 = (LFunction) sa.supplier();
		while (testFunc0.test(iterator0)) {
			T a = nextFunc0.apply(iterator0);
			consumer.accept(this.applyAsSrt(a));
		}
	}

}
