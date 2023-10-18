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
 * Non-throwing functional interface (lambda) LToCharFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): T a
 *
 * Co-domain: char
 *
 * Special case of function that corresponds to expressions like (iterator) -> Iterator::next
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LToCharFunction<T> extends MetaFunction, MetaInterface.NonThrowing, OFunction<T, aChar>, Codomain<aChar>, Domain1<a<T>> { // NOSONAR

	String DESCRIPTION = "LToCharFunction: char applyAsChar(T a)";

	// char applyAsChar(T a) ;
	default char applyAsChar(T a) {
		// return nestingApplyAsChar(a);
		try {
			return this.applyAsCharX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsChar(T a)
	 */
	char applyAsCharX(T a) throws Throwable;

	default char tupleApplyAsChar(LSingle<T> args) {
		return applyAsChar(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default char handlingApplyAsChar(T a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsCharX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LToCharFunction<T> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> handlingApplyAsChar(a, handling);
	}

	default char applyAsChar(T a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyAsCharX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default char applyAsChar(T a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyAsCharX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default char applyAsChar(T a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyAsCharX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default char applyAsChar(T a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyAsCharX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LToCharFunction<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return a -> applyAsChar(a, factory, newMessage);
	}

	default LToCharFunction<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return a -> applyAsChar(a, factory, newMessage, param1);
	}

	default LToCharFunction<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return a -> applyAsChar(a, factory, newMessage, param1, param1);
	}

	default LToCharFunction<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return a -> applyAsChar(a, factory, newMessage, param1, param2, param3);
	}

	default char applyAsChar(T a, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyAsCharX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LToCharFunction<T> trying(@Nonnull ExWF<RuntimeException> factory) {
		return a -> applyAsChar(a, factory);
	}

	default char applyAsCharThen(T a, @Nonnull LToCharFunction<Throwable> handler) {
		try {
			return this.applyAsCharX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsChar(e);
		}
	}

	default LToCharFunction<T> tryingThen(@Nonnull LToCharFunction<Throwable> handler) {
		return a -> applyAsCharThen(a, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default char nestingApplyAsChar(T a) {
		try {
			return this.applyAsCharX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default char shovingApplyAsChar(T a) {
		try {
			return this.applyAsCharX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> char shovingApplyAsChar(T a, LToCharFunction<T> func) {
		Null.nonNullArg(func, "func");
		return func.shovingApplyAsChar(a);
	}

	static <T> char handlingApplyAsChar(T a, LToCharFunction<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsChar(a, handling);
	}

	static <T> char tryApplyAsChar(T a, LToCharFunction<T> func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsChar(a);
	}

	static <T> char tryApplyAsChar(T a, LToCharFunction<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.applyAsChar(a, factory, newMessage);
	}

	static <T> char tryApplyAsChar(T a, LToCharFunction<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.applyAsChar(a, factory, newMessage, param1);
	}

	static <T> char tryApplyAsChar(T a, LToCharFunction<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.applyAsChar(a, factory, newMessage, param1, param2);
	}

	static <T> char tryApplyAsChar(T a, LToCharFunction<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.applyAsChar(a, factory, newMessage, param1, param2, param3);
	}

	static <T> char tryApplyAsChar(T a, LToCharFunction<T> func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.applyAsChar(a, factory);
	}

	static <T> char tryApplyAsCharThen(T a, LToCharFunction<T> func, @Nonnull LToCharFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsCharThen(a, handler);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default char nonNullApplyAsChar(T a) {
		return applyAsChar(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LToCharFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_i, int max_i, T a, @Nonnull LToCharFunction<T> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.applyAsChar(a);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.applyAsChar(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTill(int min_i, int max_i, T a, @Nonnull LToCharFunction<T> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.applyAsChar(a);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.applyAsChar(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void times(int max_i, T a, @Nonnull LToCharFunction<T> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a, func);
	}

	/** Extract and apply function. */
	public static <M, K, V> char from(@Nonnull M container, LBiFunction<M, K, V> extractor, K key, @Nonnull LToCharFunction<V> function, char orElse) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.applyAsChar(value);
		}

		return orElse;
	}

	/** Change function to one that ignores output. */
	default LConsumer<T> toConsumer() {
		return this::applyAsChar;
	}

	/** Calls domain consumer before main function. */
	default LToCharFunction<T> beforeDo(@Nonnull LConsumer<T> before) {
		Null.nonNullArg(before, "before");
		return (T a) -> {
			before.accept(a);
			return applyAsChar(a);
		};
	}

	/** Calls codomain consumer after main function. */
	default LToCharFunction<T> afterDo(@Nonnull LCharConsumer after) {
		Null.nonNullArg(after, "after");
		return (T a) -> {
			final char retval = applyAsChar(a);
			after.accept(retval);
			return retval;
		};
	}

	/** Creates function that always returns the same value. */
	static <T> LToCharFunction<T> constant(char r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LToCharFunction<T> toCharFunc(final @Nonnull LToCharFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S<T> implements LToCharFunction<T> {
		private LToCharFunction<T> target = null;
		@Override
		public char applyAsCharX(T a) throws Throwable {
			return target.applyAsCharX(a);
		}
	}

	@Nonnull
	static <T> LToCharFunction<T> recursive(final @Nonnull LFunction<LToCharFunction<T>, LToCharFunction<T>> selfLambda) {
		final S<T> single = new S();
		LToCharFunction<T> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static <T> M<T> mementoOf(T a, LToCharFunction<T> function) {
		var initialValue = function.applyAsChar(a);
		return initializedMementoOf(initialValue, function);
	}

	public static <T> M<T> initializedMementoOf(char initialValue, LToCharFunction<T> function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static <T> M<T> deltaOf(T a, LToCharFunction<T> function, LCharBinaryOperator deltaFunction) {
		var initialValue = function.applyAsChar(a);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static <T> M<T> deltaOf(T a, LToCharFunction<T> function) {
		var initialValue = function.applyAsChar(a);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (char) (x2 - x1));
	}

	public static <T> M<T> initializedDeltaOf(char initialValue, LToCharFunction<T> function, LCharBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.applyAsChar(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.applyAsChar(x1, x2));
	}

	public static <T> M<T> memento(char initialBaseValue, char initialValue, LToCharFunction<T> baseFunction, LCharTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LToCharFunction.M)
	 */
	@NotThreadSafe
	final class M<T> implements LToCharFunction<T> {

		private final LToCharFunction<T> baseFunction;
		private char lastBaseValue;
		private char lastValue;
		private final LCharTernaryOperator mementoFunction;

		private M(char lastBaseValue, char lastValue, LToCharFunction<T> baseFunction, LCharTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public char applyAsCharX(T a) throws Throwable {
			char x1 = lastBaseValue;
			char x2 = lastBaseValue = baseFunction.applyAsCharX(a);

			return lastValue = mementoFunction.applyAsCharX(lastValue, x1, x2);
		}

		public char currentApplyAsChar(T a) {
			char x1 = lastBaseValue;
			char x2 = baseFunction.applyAsChar(a);

			return mementoFunction.applyAsChar(lastValue, x1, x2);
		}

		public char lastValue() {
			return lastValue;
		};

		public char lastBaseValue() {
			return lastBaseValue;
		};
	}

	// </editor-fold>

	@Nonnull
	static <T> LToCharFunction<T> toCharFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T> LToCharFunction<T> toCharFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce(message);
		};
	}

	static <T> char call(T a, final @Nonnull LToCharFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsChar(a);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToCharFunction<V> compose(@Nonnull final LFunction<? super V, ? extends T> before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsChar(before.apply(v));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LFunction<T, V> then(@Nonnull LCharFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.apply(this.applyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToByteFunction<T> thenToByte(@Nonnull LCharToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsByte(this.applyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToSrtFunction<T> thenToSrt(@Nonnull LCharToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsSrt(this.applyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToIntFunction<T> thenToInt(@Nonnull LCharToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsInt(this.applyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToLongFunction<T> thenToLong(@Nonnull LCharToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsLong(this.applyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToFltFunction<T> thenToFlt(@Nonnull LCharToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsFlt(this.applyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToDblFunction<T> thenToDbl(@Nonnull LCharToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsDbl(this.applyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToCharFunction<T> thenToChar(@Nonnull LCharUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsChar(this.applyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LPredicate<T> thenToBool(@Nonnull LCharPredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.test(this.applyAsChar(a));
	}

	// </editor-fold>

	default LToCharFunction<T> shoving() {

		return new LToCharFunction<T>() {

			public char applyAsChar(T a) {
				try {
					return this.applyAsCharX(a);
				} catch (Throwable e) { // NOSONAR
					Handling.handleErrors(e);
					throw Handling.throwIt(e);
				}
			}

			public char applyAsCharX(T a) throws Throwable {
				return LToCharFunction.this.applyAsCharX(a);
			}

		};
	}

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LToCharFunction) Function */
	public static <T> char doNothing(T a) {
		return Function4U.defaultCharacter;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C0> void forEach(IndexedRead<C0, a<T>> ia, C0 source, LCharConsumer consumer) {
		int size = ia.size(source);
		LOiFunction<Object, T> oiFunc0 = (LOiFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			T a = oiFunc0.apply(source, i);
			consumer.accept(this.applyAsChar(a));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C0, I0> void iterate(SequentialRead<C0, I0, a<T>> sa, C0 source, LCharConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).apply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LFunction<Object, T> nextFunc0 = (LFunction) sa.supplier();
		while (testFunc0.test(iterator0)) {
			T a = nextFunc0.apply(iterator0);
			consumer.accept(this.applyAsChar(a));
		}
	}

}
