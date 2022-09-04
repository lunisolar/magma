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
 * Non-throwing functional interface (lambda) LToCharBiFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 2): T1 a1,T2 a2
 *
 * Co-domain: char
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LToCharBiFunction<T1, T2> extends MetaFunction, MetaInterface.NonThrowing, Codomain<aChar>, Domain2<a<T1>, a<T2>> { // NOSONAR

	String DESCRIPTION = "LToCharBiFunction: char applyAsChar(T1 a1,T2 a2)";

	// char applyAsChar(T1 a1,T2 a2) ;
	default char applyAsChar(T1 a1, T2 a2) {
		// return nestingApplyAsChar(a1,a2);
		try {
			return this.applyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsChar(T1 a1,T2 a2)
	 */
	char applyAsCharX(T1 a1, T2 a2) throws Throwable;

	default char tupleApplyAsChar(LPair<T1, T2> args) {
		return applyAsChar(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default char handlingApplyAsChar(T1 a1, T2 a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LToCharBiFunction<T1, T2> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2) -> handlingApplyAsChar(a1, a2, handling);
	}

	default char applyAsChar(T1 a1, T2 a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default char applyAsChar(T1 a1, T2 a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default char applyAsChar(T1 a1, T2 a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default char applyAsChar(T1 a1, T2 a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LToCharBiFunction<T1, T2> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2) -> applyAsChar(a1, a2, factory, newMessage);
	}

	default LToCharBiFunction<T1, T2> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2) -> applyAsChar(a1, a2, factory, newMessage, param1);
	}

	default LToCharBiFunction<T1, T2> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2) -> applyAsChar(a1, a2, factory, newMessage, param1, param1);
	}

	default LToCharBiFunction<T1, T2> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2) -> applyAsChar(a1, a2, factory, newMessage, param1, param2, param3);
	}

	default char applyAsChar(T1 a1, T2 a2, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LToCharBiFunction<T1, T2> trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2) -> applyAsChar(a1, a2, factory);
	}

	default char applyAsCharThen(T1 a1, T2 a2, @Nonnull LToCharFunction<Throwable> handler) {
		try {
			return this.applyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsChar(e);
		}
	}

	default LToCharBiFunction<T1, T2> tryingThen(@Nonnull LToCharFunction<Throwable> handler) {
		return (a1, a2) -> applyAsCharThen(a1, a2, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default char nestingApplyAsChar(T1 a1, T2 a2) {
		try {
			return this.applyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default char shovingApplyAsChar(T1 a1, T2 a2) {
		try {
			return this.applyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T1, T2> char shovingApplyAsChar(T1 a1, T2 a2, LToCharBiFunction<T1, T2> func) {
		Null.nonNullArg(func, "func");
		return func.shovingApplyAsChar(a1, a2);
	}

	static <T1, T2> char handlingApplyAsChar(T1 a1, T2 a2, LToCharBiFunction<T1, T2> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsChar(a1, a2, handling);
	}

	static <T1, T2> char tryApplyAsChar(T1 a1, T2 a2, LToCharBiFunction<T1, T2> func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsChar(a1, a2);
	}

	static <T1, T2> char tryApplyAsChar(T1 a1, T2 a2, LToCharBiFunction<T1, T2> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.applyAsChar(a1, a2, factory, newMessage);
	}

	static <T1, T2> char tryApplyAsChar(T1 a1, T2 a2, LToCharBiFunction<T1, T2> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.applyAsChar(a1, a2, factory, newMessage, param1);
	}

	static <T1, T2> char tryApplyAsChar(T1 a1, T2 a2, LToCharBiFunction<T1, T2> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.applyAsChar(a1, a2, factory, newMessage, param1, param2);
	}

	static <T1, T2> char tryApplyAsChar(T1 a1, T2 a2, LToCharBiFunction<T1, T2> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.applyAsChar(a1, a2, factory, newMessage, param1, param2, param3);
	}

	static <T1, T2> char tryApplyAsChar(T1 a1, T2 a2, LToCharBiFunction<T1, T2> func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.applyAsChar(a1, a2, factory);
	}

	static <T1, T2> char tryApplyAsCharThen(T1 a1, T2 a2, LToCharBiFunction<T1, T2> func, @Nonnull LToCharFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsCharThen(a1, a2, handler);
	}

	default char failSafeApplyAsChar(T1 a1, T2 a2, @Nonnull LToCharBiFunction<T1, T2> failSafe) {
		try {
			return applyAsChar(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.applyAsChar(a1, a2);
		}
	}

	static <T1, T2> char failSafeApplyAsChar(T1 a1, T2 a2, LToCharBiFunction<T1, T2> func, @Nonnull LToCharBiFunction<T1, T2> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.applyAsChar(a1, a2);
		} else {
			return func.failSafeApplyAsChar(a1, a2, failSafe);
		}
	}

	static <T1, T2> LToCharBiFunction<T1, T2> failSafe(LToCharBiFunction<T1, T2> func, @Nonnull LToCharBiFunction<T1, T2> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeApplyAsChar(a1, a2, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default char nonNullApplyAsChar(T1 a1, T2 a2) {
		return applyAsChar(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LToCharBiFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void fromTo(int min_i, int max_i, T1 a1, T2 a2, @Nonnull LToCharBiFunction<T1, T2> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.applyAsChar(a1, a2);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.applyAsChar(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void fromTill(int min_i, int max_i, T1 a1, T2 a2, @Nonnull LToCharBiFunction<T1, T2> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.applyAsChar(a1, a2);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.applyAsChar(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void times(int max_i, T1 a1, T2 a2, @Nonnull LToCharBiFunction<T1, T2> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, func);
	}

	/** Extract and apply function. */
	public static <M, K, V, T2> char from(@Nonnull M container, LBiFunction<M, K, V> extractor, K key, T2 a2, @Nonnull LToCharBiFunction<V, T2> function, char orElse) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.applyAsChar(value, a2);
		}

		return orElse;
	}

	/**  */
	public static <T1, T2> LToCharBiFunction<T1, T2> uncurry(@Nonnull LFunction<T1, LToCharFunction<T2>> func) {
		Null.nonNullArg(func, "func");
		return (T1 a1, T2 a2) -> func.apply(a1).applyAsChar(a2);
	}

	/** Change function to consumer that ignores output. */
	default LBiConsumer<T1, T2> toConsumer() {
		return this::applyAsChar;
	}

	/** Calls domain consumer before main function. */
	default LToCharBiFunction<T1, T2> beforeDo(@Nonnull LBiConsumer<T1, T2> before) {
		Null.nonNullArg(before, "before");
		return (T1 a1, T2 a2) -> {
			before.accept(a1, a2);
			return applyAsChar(a1, a2);
		};
	}

	/** Calls codomain consumer after main function. */
	default LToCharBiFunction<T1, T2> afterDo(@Nonnull LCharConsumer after) {
		Null.nonNullArg(after, "after");
		return (T1 a1, T2 a2) -> {
			final char retval = applyAsChar(a1, a2);
			after.accept(retval);
			return retval;
		};
	}

	/** Creates function that always returns the same value. */
	static <T1, T2> LToCharBiFunction<T1, T2> constant(char r) {
		return (a1, a2) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2> LToCharBiFunction<T1, T2> toCharBiFunc(final @Nonnull LToCharBiFunction<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** A completely inconvenient method in case lambda expression and generic arguments are ambiguous for the compiler. */
	@Nonnull
	static <T1, T2> LToCharBiFunction<T1, T2> toCharBiFunc(@Nullable Class<T1> c1, @Nullable Class<T2> c2, final @Nonnull LToCharBiFunction<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	final class S<T1, T2> implements LToCharBiFunction<T1, T2> {
		private LToCharBiFunction<T1, T2> target = null;
		@Override
		public char applyAsCharX(T1 a1, T2 a2) throws Throwable {
			return target.applyAsCharX(a1, a2);
		}
	}

	@Nonnull
	static <T1, T2> LToCharBiFunction<T1, T2> recursive(final @Nonnull LFunction<LToCharBiFunction<T1, T2>, LToCharBiFunction<T1, T2>> selfLambda) {
		final S<T1, T2> single = new S();
		LToCharBiFunction<T1, T2> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	public static <T1, T2> M<T1, T2> mementoOf(T1 a1, T2 a2, LToCharBiFunction<T1, T2> function) {
		var initialValue = function.applyAsChar(a1, a2);
		return initializedMementoOf(initialValue, function);
	}

	public static <T1, T2> M<T1, T2> initializedMementoOf(char initialValue, LToCharBiFunction<T1, T2> function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static <T1, T2> M<T1, T2> deltaOf(T1 a1, T2 a2, LToCharBiFunction<T1, T2> function, LCharBinaryOperator deltaFunction) {
		var initialValue = function.applyAsChar(a1, a2);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static <T1, T2> M<T1, T2> deltaOf(T1 a1, T2 a2, LToCharBiFunction<T1, T2> function) {
		var initialValue = function.applyAsChar(a1, a2);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (char) (x2 - x1));
	}

	public static <T1, T2> M<T1, T2> initializedDeltaOf(char initialValue, LToCharBiFunction<T1, T2> function, LCharBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.applyAsChar(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.applyAsChar(x1, x2));
	}

	public static <T1, T2> M<T1, T2> memento(char initialBaseValue, char initialValue, LToCharBiFunction<T1, T2> baseFunction, LCharTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LToCharBiFunction.M)
	 */
	@NotThreadSafe
	final class M<T1, T2> implements LToCharBiFunction<T1, T2> {

		private final LToCharBiFunction<T1, T2> baseFunction;
		private char lastBaseValue;
		private char lastValue;
		private final LCharTernaryOperator mementoFunction;

		private M(char lastBaseValue, char lastValue, LToCharBiFunction<T1, T2> baseFunction, LCharTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public char applyAsCharX(T1 a1, T2 a2) throws Throwable {
			char x1 = lastBaseValue;
			char x2 = lastBaseValue = baseFunction.applyAsCharX(a1, a2);

			return lastValue = mementoFunction.applyAsChar(lastValue, x1, x2);
		}

		public char lastValue() {
			return lastValue;
		};

		public char lastBaseValue() {
			return lastBaseValue;
		};
	}

	@Nonnull
	static <T1, T2> LToCharBiFunction<T1, T2> toCharBiFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T1, T2> LToCharBiFunction<T1, T2> toCharBiFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1> LToCharBiFunction.LToCharObj1Obj0Func<T2, T1> toCharObj1Obj0Func(final @Nonnull LToCharBiFunction.LToCharObj1Obj0Func<T2, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T1, T2> char call(T1 a1, T2 a2, final @Nonnull LToCharBiFunction<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsChar(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LToCharBiFunction<V1, V2> compose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.applyAsChar(before1.apply(v1), before2.apply(v2));
	}

	public static <V1, V2, T1, T2> LToCharBiFunction<V1, V2> composed(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, LToCharBiFunction<T1, T2> after) {
		return after.compose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiFunction<T1, T2, V> then(@Nonnull LCharFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.apply(this.applyAsChar(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToByteBiFunction<T1, T2> thenToByte(@Nonnull LCharToByteFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsByte(this.applyAsChar(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToSrtBiFunction<T1, T2> thenToSrt(@Nonnull LCharToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsSrt(this.applyAsChar(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToIntBiFunction<T1, T2> thenToInt(@Nonnull LCharToIntFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsInt(this.applyAsChar(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToLongBiFunction<T1, T2> thenToLong(@Nonnull LCharToLongFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsLong(this.applyAsChar(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToFltBiFunction<T1, T2> thenToFlt(@Nonnull LCharToFltFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsFlt(this.applyAsChar(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToDblBiFunction<T1, T2> thenToDbl(@Nonnull LCharToDblFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsDbl(this.applyAsChar(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToCharBiFunction<T1, T2> thenToChar(@Nonnull LCharUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsChar(this.applyAsChar(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiPredicate<T1, T2> thenToBool(@Nonnull LCharPredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.test(this.applyAsChar(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LToCharBiFunction for method references. */
	@FunctionalInterface
	interface LToCharObj1Obj0Func<T2, T1> extends LToCharBiFunction<T1, T2> {

		/**
		 * Implement this, but call applyAsChar(T1 a1,T2 a2)
		 */
		default char applyAsCharX(T1 a1, T2 a2) {
			return this.applyAsCharObj1Obj0(a2, a1);
		}

		// char applyAsCharObj1Obj0(T2 a2,T1 a1) ;
		default char applyAsCharObj1Obj0(T2 a2, T1 a1) {
			// return nestingApplyAsCharObj1Obj0(a2,a1);
			try {
				return this.applyAsCharObj1Obj0X(a2, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyAsCharObj1Obj0(T2 a2,T1 a1)
		 */
		char applyAsCharObj1Obj0X(T2 a2, T1 a1) throws Throwable;
	}

	// </editor-fold>

	/** Does nothing (LToCharBiFunction) Function */
	public static <T1, T2> char doNothing(T1 a1, T2 a2) {
		return Function4U.defaultCharacter;
	}

}
