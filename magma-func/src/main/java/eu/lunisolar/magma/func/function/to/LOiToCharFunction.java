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
 * Non-throwing functional interface (lambda) LOiToCharFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 2): T a1,int a2
 *
 * Co-domain: char
 *
 * Special case of function that corresponds to expressions like (list, index) -> List::get
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LOiToCharFunction<T> extends MetaFunction, MetaInterface.NonThrowing, OiFunction<T, aChar>, Codomain<aChar>, Domain2<a<T>, aInt> { // NOSONAR

	String DESCRIPTION = "LOiToCharFunction: char applyAsChar(T a1,int a2)";

	// char applyAsChar(T a1,int a2) ;
	default char applyAsChar(T a1, int a2) {
		// return nestingApplyAsChar(a1,a2);
		try {
			return this.applyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsChar(T a1,int a2)
	 */
	char applyAsCharX(T a1, int a2) throws Throwable;

	default char tupleApplyAsChar(LObjIntPair<T> args) {
		return applyAsChar(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default char handlingApplyAsChar(T a1, int a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LOiToCharFunction<T> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2) -> handlingApplyAsChar(a1, a2, handling);
	}

	default char applyAsChar(T a1, int a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default char applyAsChar(T a1, int a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default char applyAsChar(T a1, int a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default char applyAsChar(T a1, int a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LOiToCharFunction<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2) -> applyAsChar(a1, a2, factory, newMessage);
	}

	default LOiToCharFunction<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2) -> applyAsChar(a1, a2, factory, newMessage, param1);
	}

	default LOiToCharFunction<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2) -> applyAsChar(a1, a2, factory, newMessage, param1, param1);
	}

	default LOiToCharFunction<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2) -> applyAsChar(a1, a2, factory, newMessage, param1, param2, param3);
	}

	default char applyAsChar(T a1, int a2, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LOiToCharFunction<T> trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2) -> applyAsChar(a1, a2, factory);
	}

	default char applyAsCharThen(T a1, int a2, @Nonnull LToCharFunction<Throwable> handler) {
		try {
			return this.applyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsChar(e);
		}
	}

	default LOiToCharFunction<T> tryingThen(@Nonnull LToCharFunction<Throwable> handler) {
		return (a1, a2) -> applyAsCharThen(a1, a2, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default char nestingApplyAsChar(T a1, int a2) {
		try {
			return this.applyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default char shovingApplyAsChar(T a1, int a2) {
		try {
			return this.applyAsCharX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> char shovingApplyAsChar(T a1, int a2, LOiToCharFunction<T> func) {
		Null.nonNullArg(func, "func");
		return func.shovingApplyAsChar(a1, a2);
	}

	static <T> char handlingApplyAsChar(T a1, int a2, LOiToCharFunction<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsChar(a1, a2, handling);
	}

	static <T> char tryApplyAsChar(T a1, int a2, LOiToCharFunction<T> func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsChar(a1, a2);
	}

	static <T> char tryApplyAsChar(T a1, int a2, LOiToCharFunction<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.applyAsChar(a1, a2, factory, newMessage);
	}

	static <T> char tryApplyAsChar(T a1, int a2, LOiToCharFunction<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.applyAsChar(a1, a2, factory, newMessage, param1);
	}

	static <T> char tryApplyAsChar(T a1, int a2, LOiToCharFunction<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.applyAsChar(a1, a2, factory, newMessage, param1, param2);
	}

	static <T> char tryApplyAsChar(T a1, int a2, LOiToCharFunction<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.applyAsChar(a1, a2, factory, newMessage, param1, param2, param3);
	}

	static <T> char tryApplyAsChar(T a1, int a2, LOiToCharFunction<T> func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.applyAsChar(a1, a2, factory);
	}

	static <T> char tryApplyAsCharThen(T a1, int a2, LOiToCharFunction<T> func, @Nonnull LToCharFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsCharThen(a1, a2, handler);
	}

	default char failSafeApplyAsChar(T a1, int a2, @Nonnull LOiToCharFunction<T> failSafe) {
		try {
			return applyAsChar(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.applyAsChar(a1, a2);
		}
	}

	static <T> char failSafeApplyAsChar(T a1, int a2, LOiToCharFunction<T> func, @Nonnull LOiToCharFunction<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.applyAsChar(a1, a2);
		} else {
			return func.failSafeApplyAsChar(a1, a2, failSafe);
		}
	}

	static <T> LOiToCharFunction<T> failSafe(LOiToCharFunction<T> func, @Nonnull LOiToCharFunction<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeApplyAsChar(a1, a2, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default char nonNullApplyAsChar(T a1, int a2) {
		return applyAsChar(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LOiToCharFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_a2, int max_a2, T a1, @Nonnull LOiToCharFunction<T> func) {
		Null.nonNullArg(func, "func");
		if (min_a2 <= max_a2) {
			for (int a2 = min_a2; a2 <= max_a2; a2++) {
				func.applyAsChar(a1, a2);
			}
		} else {
			for (int a2 = min_a2; a2 >= max_a2; a2--) {
				func.applyAsChar(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTill(int min_a2, int max_a2, T a1, @Nonnull LOiToCharFunction<T> func) {
		Null.nonNullArg(func, "func");
		if (min_a2 <= max_a2) {
			for (int a2 = min_a2; a2 < max_a2; a2++) {
				func.applyAsChar(a1, a2);
			}
		} else {
			for (int a2 = min_a2; a2 > max_a2; a2--) {
				func.applyAsChar(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void times(int max_a2, T a1, @Nonnull LOiToCharFunction<T> func) {
		if (max_a2 < 0)
			return;
		fromTill(0, max_a2, a1, func);
	}

	/** Extract and apply function. */
	public static <M, K, V> char from(@Nonnull M container, LBiFunction<M, K, V> extractor, K key, int a2, @Nonnull LOiToCharFunction<V> function, char orElse) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.applyAsChar(value, a2);
		}

		return orElse;
	}

	/**  */
	public static <T> LOiToCharFunction<T> uncurry(@Nonnull LFunction<T, LIntToCharFunction> func) {
		Null.nonNullArg(func, "func");
		return (T a1, int a2) -> func.apply(a1).applyAsChar(a2);
	}

	/** Change function to consumer that ignores output. */
	default LObjIntConsumer<T> toConsumer() {
		return this::applyAsChar;
	}

	/** Calls domain consumer before main function. */
	default LOiToCharFunction<T> beforeDo(@Nonnull LObjIntConsumer<T> before) {
		Null.nonNullArg(before, "before");
		return (T a1, int a2) -> {
			before.accept(a1, a2);
			return applyAsChar(a1, a2);
		};
	}

	/** Calls codomain consumer after main function. */
	default LOiToCharFunction<T> afterDo(@Nonnull LCharConsumer after) {
		Null.nonNullArg(after, "after");
		return (T a1, int a2) -> {
			final char retval = applyAsChar(a1, a2);
			after.accept(retval);
			return retval;
		};
	}

	/** Creates function that always returns the same value. */
	static <T> LOiToCharFunction<T> constant(char r) {
		return (a1, a2) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LOiToCharFunction<T> oiToCharFunc(final @Nonnull LOiToCharFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** A completely inconvenient method in case lambda expression and generic arguments are ambiguous for the compiler. */
	@Nonnull
	static <T> LOiToCharFunction<T> oiToCharFunc(@Nullable Class<T> c1, final @Nonnull LOiToCharFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	final class S<T> implements LOiToCharFunction<T> {
		private LOiToCharFunction<T> target = null;
		@Override
		public char applyAsCharX(T a1, int a2) throws Throwable {
			return target.applyAsCharX(a1, a2);
		}
	}

	@Nonnull
	static <T> LOiToCharFunction<T> recursive(final @Nonnull LFunction<LOiToCharFunction<T>, LOiToCharFunction<T>> selfLambda) {
		final S<T> single = new S();
		LOiToCharFunction<T> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	public static <T> M<T> mementoOf(T a1, int a2, LOiToCharFunction<T> function) {
		var initialValue = function.applyAsChar(a1, a2);
		return initializedMementoOf(initialValue, function);
	}

	public static <T> M<T> initializedMementoOf(char initialValue, LOiToCharFunction<T> function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static <T> M<T> deltaOf(T a1, int a2, LOiToCharFunction<T> function, LCharBinaryOperator deltaFunction) {
		var initialValue = function.applyAsChar(a1, a2);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static <T> M<T> deltaOf(T a1, int a2, LOiToCharFunction<T> function) {
		var initialValue = function.applyAsChar(a1, a2);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (char) (x2 - x1));
	}

	public static <T> M<T> initializedDeltaOf(char initialValue, LOiToCharFunction<T> function, LCharBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.applyAsChar(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.applyAsChar(x1, x2));
	}

	public static <T> M<T> memento(char initialBaseValue, char initialValue, LOiToCharFunction<T> baseFunction, LCharTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LOiToCharFunction.M)
	 */
	@NotThreadSafe
	final class M<T> implements LOiToCharFunction<T> {

		private final LOiToCharFunction<T> baseFunction;
		private char lastBaseValue;
		private char lastValue;
		private final LCharTernaryOperator mementoFunction;

		private M(char lastBaseValue, char lastValue, LOiToCharFunction<T> baseFunction, LCharTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public char applyAsCharX(T a1, int a2) throws Throwable {
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
	static <T> LOiToCharFunction<T> oiToCharFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T> LOiToCharFunction<T> oiToCharFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LOiToCharFunction.LIntObjToCharFunc<T> intObjToCharFunc(final @Nonnull LOiToCharFunction.LIntObjToCharFunc<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T> char call(T a1, int a2, final @Nonnull LOiToCharFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsChar(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1> LOiToCharFunction<V1> compose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.applyAsChar(before1.apply(v1), before2.applyAsInt(v2));
	}

	public static <V1, T> LOiToCharFunction<V1> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2, LOiToCharFunction<T> after) {
		return after.compose(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LToCharBiFunction<V1, V2> oiToCharFuncCompose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.applyAsChar(before1.apply(v1), before2.applyAsInt(v2));
	}

	public static <V1, V2, T> LToCharBiFunction<V1, V2> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2, LOiToCharFunction<T> after) {
		return after.oiToCharFuncCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LOiFunction<T, V> then(@Nonnull LCharFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.apply(this.applyAsChar(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToByteFunction<T> thenToByte(@Nonnull LCharToByteFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsByte(this.applyAsChar(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToSrtFunction<T> thenToSrt(@Nonnull LCharToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsSrt(this.applyAsChar(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToIntFunction<T> thenToInt(@Nonnull LCharToIntFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsInt(this.applyAsChar(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToLongFunction<T> thenToLong(@Nonnull LCharToLongFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsLong(this.applyAsChar(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToFltFunction<T> thenToFlt(@Nonnull LCharToFltFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsFlt(this.applyAsChar(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToDblFunction<T> thenToDbl(@Nonnull LCharToDblFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsDbl(this.applyAsChar(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToCharFunction<T> thenToChar(@Nonnull LCharUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsChar(this.applyAsChar(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LObjIntPredicate<T> thenToBool(@Nonnull LCharPredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.test(this.applyAsChar(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LOiToCharFunction for method references. */
	@FunctionalInterface
	interface LIntObjToCharFunc<T> extends LOiToCharFunction<T> {

		/**
		 * Implement this, but call applyAsChar(T a1,int a2)
		 */
		default char applyAsCharX(T a1, int a2) {
			return this.applyAsCharIntObj(a2, a1);
		}

		// char applyAsCharIntObj(int a2,T a1) ;
		default char applyAsCharIntObj(int a2, T a1) {
			// return nestingApplyAsCharIntObj(a2,a1);
			try {
				return this.applyAsCharIntObjX(a2, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyAsCharIntObj(int a2,T a1)
		 */
		char applyAsCharIntObjX(int a2, T a1) throws Throwable;
	}

	// </editor-fold>

	/** Does nothing (LOiToCharFunction) Function */
	public static <T> char doNothing(T a1, int a2) {
		return Function4U.defaultCharacter;
	}

	/** Does nothing (LOiToCharFunction.LIntObjToCharFunc) Function */
	public static <T> char doNothing(int a2, T a1) {
		return Function4U.defaultCharacter;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2> void forEach(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, LCharConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = oiFunc2.applyAsInt(source2, i);
			consumer.accept(this.applyAsChar(a1, a2));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, LCharConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = oiFunc2.applyAsInt(source2, i);
			consumer.accept(this.applyAsChar(a1, a2));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2> void iterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, LCharConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = nextFunc2.applyAsInt(iterator2);
			consumer.accept(this.applyAsChar(a1, a2));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C1, I1, C2, I2> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, LCharConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2)) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = nextFunc2.applyAsInt(iterator2);
			consumer.accept(this.applyAsChar(a1, a2));
		}
	}

}
