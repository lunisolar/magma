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

package eu.lunisolar.magma.func.function.conversion;

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
 * Non-throwing functional interface (lambda) LCharToLongFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): char a
 *
 * Co-domain: long
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LCharToLongFunction extends MetaFunction, MetaInterface.NonThrowing, Codomain<aLong>, Domain1<aChar> { // NOSONAR

	String DESCRIPTION = "LCharToLongFunction: long applyAsLong(char a)";

	// long applyAsLong(char a) ;
	default long applyAsLong(char a) {
		// return nestingApplyAsLong(a);
		try {
			return this.applyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsLong(char a)
	 */
	long applyAsLongX(char a) throws Throwable;

	default long tupleApplyAsLong(LCharSingle args) {
		return applyAsLong(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default long handlingApplyAsLong(char a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LCharToLongFunction handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> handlingApplyAsLong(a, handling);
	}

	default long applyAsLong(char a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default long applyAsLong(char a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default long applyAsLong(char a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default long applyAsLong(char a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LCharToLongFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return a -> applyAsLong(a, factory, newMessage);
	}

	default LCharToLongFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return a -> applyAsLong(a, factory, newMessage, param1);
	}

	default LCharToLongFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return a -> applyAsLong(a, factory, newMessage, param1, param1);
	}

	default LCharToLongFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return a -> applyAsLong(a, factory, newMessage, param1, param2, param3);
	}

	default long applyAsLong(char a, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LCharToLongFunction trying(@Nonnull ExWF<RuntimeException> factory) {
		return a -> applyAsLong(a, factory);
	}

	default long applyAsLongThen(char a, @Nonnull LToLongFunction<Throwable> handler) {
		try {
			return this.applyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsLong(e);
		}
	}

	default LCharToLongFunction tryingThen(@Nonnull LToLongFunction<Throwable> handler) {
		return a -> applyAsLongThen(a, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default long nestingApplyAsLong(char a) {
		try {
			return this.applyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default long shovingApplyAsLong(char a) {
		try {
			return this.applyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static long shovingApplyAsLong(char a, LCharToLongFunction func) {
		Null.nonNullArg(func, "func");
		return func.shovingApplyAsLong(a);
	}

	static long handlingApplyAsLong(char a, LCharToLongFunction func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsLong(a, handling);
	}

	static long tryApplyAsLong(char a, LCharToLongFunction func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsLong(a);
	}

	static long tryApplyAsLong(char a, LCharToLongFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.applyAsLong(a, factory, newMessage);
	}

	static long tryApplyAsLong(char a, LCharToLongFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.applyAsLong(a, factory, newMessage, param1);
	}

	static long tryApplyAsLong(char a, LCharToLongFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.applyAsLong(a, factory, newMessage, param1, param2);
	}

	static long tryApplyAsLong(char a, LCharToLongFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.applyAsLong(a, factory, newMessage, param1, param2, param3);
	}

	static long tryApplyAsLong(char a, LCharToLongFunction func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.applyAsLong(a, factory);
	}

	static long tryApplyAsLongThen(char a, LCharToLongFunction func, @Nonnull LToLongFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsLongThen(a, handler);
	}

	default long failSafeApplyAsLong(char a, @Nonnull LCharToLongFunction failSafe) {
		try {
			return applyAsLong(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.applyAsLong(a);
		}
	}

	static long failSafeApplyAsLong(char a, LCharToLongFunction func, @Nonnull LCharToLongFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.applyAsLong(a);
		} else {
			return func.failSafeApplyAsLong(a, failSafe);
		}
	}

	static LCharToLongFunction failSafe(LCharToLongFunction func, @Nonnull LCharToLongFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeApplyAsLong(a, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default long nonNullApplyAsLong(char a) {
		return applyAsLong(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LCharToLongFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, char a, @Nonnull LCharToLongFunction func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.applyAsLong(a);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.applyAsLong(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, char a, @Nonnull LCharToLongFunction func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.applyAsLong(a);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.applyAsLong(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, char a, @Nonnull LCharToLongFunction func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a, func);
	}

	/** Change function to consumer that ignores output. */
	default LCharConsumer toConsumer() {
		return this::applyAsLong;
	}

	/** Calls domain consumer before main function. */
	default LCharToLongFunction beforeDo(@Nonnull LCharConsumer before) {
		Null.nonNullArg(before, "before");
		return (char a) -> {
			before.accept(a);
			return applyAsLong(a);
		};
	}

	/** Calls codomain consumer after main function. */
	default LCharToLongFunction afterDo(@Nonnull LLongConsumer after) {
		Null.nonNullArg(after, "after");
		return (char a) -> {
			final long retval = applyAsLong(a);
			after.accept(retval);
			return retval;
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LLongSupplier capture(char a) {
		return () -> this.applyAsLong(a);
	}

	/** Creates function that always returns the same value. */
	static LCharToLongFunction constant(long r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LCharToLongFunction charToLongFunc(final @Nonnull LCharToLongFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	final class S implements LCharToLongFunction {
		private LCharToLongFunction target = null;
		@Override
		public long applyAsLongX(char a) throws Throwable {
			return target.applyAsLongX(a);
		}
	}

	@Nonnull
	static LCharToLongFunction recursive(final @Nonnull LFunction<LCharToLongFunction, LCharToLongFunction> selfLambda) {
		final S single = new S();
		LCharToLongFunction func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	public static M mementoOf(char a, LCharToLongFunction function) {
		var initialValue = function.applyAsLong(a);
		return initializedMementoOf(initialValue, function);
	}

	public static M initializedMementoOf(long initialValue, LCharToLongFunction function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static M deltaOf(char a, LCharToLongFunction function, LLongBinaryOperator deltaFunction) {
		var initialValue = function.applyAsLong(a);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static M deltaOf(char a, LCharToLongFunction function) {
		var initialValue = function.applyAsLong(a);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (x2 - x1));
	}

	public static M initializedDeltaOf(long initialValue, LCharToLongFunction function, LLongBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.applyAsLong(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.applyAsLong(x1, x2));
	}

	public static M memento(long initialBaseValue, long initialValue, LCharToLongFunction baseFunction, LLongTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LCharToLongFunction.M)
	 */
	@NotThreadSafe
	final class M implements LCharToLongFunction {

		private final LCharToLongFunction baseFunction;
		private long lastBaseValue;
		private long lastValue;
		private final LLongTernaryOperator mementoFunction;

		private M(long lastBaseValue, long lastValue, LCharToLongFunction baseFunction, LLongTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public long applyAsLongX(char a) throws Throwable {
			long x1 = lastBaseValue;
			long x2 = lastBaseValue = baseFunction.applyAsLongX(a);

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
	static LCharToLongFunction charToLongFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LCharToLongFunction charToLongFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce(message);
		};
	}

	static long call(char a, final @Nonnull LCharToLongFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsLong(a);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LCharToLongFunction compose(@Nonnull final LCharUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsLong(before.applyAsChar(v));
	}

	public static LCharToLongFunction composed(@Nonnull final LCharUnaryOperator before, LCharToLongFunction after) {
		return after.compose(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToLongFunction<V> charToLongFuncCompose(@Nonnull final LToCharFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsLong(before.applyAsChar(v));
	}

	public static <V> LToLongFunction<V> composed(@Nonnull final LToCharFunction<? super V> before, LCharToLongFunction after) {
		return after.charToLongFuncCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LCharFunction<V> then(@Nonnull LLongFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.apply(this.applyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToByteFunction thenToByte(@Nonnull LLongToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsByte(this.applyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToSrtFunction thenToSrt(@Nonnull LLongToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsSrt(this.applyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToIntFunction thenToInt(@Nonnull LLongToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsInt(this.applyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToLongFunction thenToLong(@Nonnull LLongUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsLong(this.applyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToFltFunction thenToFlt(@Nonnull LLongToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsFlt(this.applyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToDblFunction thenToDbl(@Nonnull LLongToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsDbl(this.applyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharUnaryOperator thenToChar(@Nonnull LLongToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsChar(this.applyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharPredicate thenToBool(@Nonnull LLongPredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.test(this.applyAsLong(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LCharToLongFunction) Function */
	public static long doNothing(char a) {
		return Function4U.defaultLong;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C0> void forEach(IndexedRead<C0, aChar> ia, C0 source, LLongConsumer consumer) {
		int size = ia.size(source);
		LOiToCharFunction<Object> oiFunc0 = (LOiToCharFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			char a = oiFunc0.applyAsChar(source, i);
			consumer.accept(this.applyAsLong(a));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C0, I0> void iterate(SequentialRead<C0, I0, aChar> sa, C0 source, LLongConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).apply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToCharFunction<Object> nextFunc0 = (LToCharFunction) sa.supplier();
		while (testFunc0.test(iterator0)) {
			char a = nextFunc0.applyAsChar(iterator0);
			consumer.accept(this.applyAsLong(a));
		}
	}

}
