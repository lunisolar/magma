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
 * Non-throwing functional interface (lambda) LFltToLongFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): float a
 *
 * Co-domain: long
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LFltToLongFunction extends MetaFunction, MetaInterface.NonThrowing, Codomain<aLong>, Domain1<aFloat> { //NOSONAR

	String DESCRIPTION = "LFltToLongFunction: long applyAsLong(float a)";

	default long applyAsLong(float a) {
		try {
			return this.applyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsLong(float a)
	 */
	long applyAsLongX(float a) throws Throwable;

	default long tupleApplyAsLong(LFltSingle args) {
		return applyAsLong(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default long handlingApplyAsLong(float a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsLongX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LFltToLongFunction handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> handlingApplyAsLong(a, handling);
	}

	default long applyAsLong(float a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyAsLongX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default long applyAsLong(float a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyAsLongX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default long applyAsLong(float a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyAsLongX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default long applyAsLong(float a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyAsLongX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LFltToLongFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return a -> applyAsLong(a, factory, newMessage);
	}

	default LFltToLongFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return a -> applyAsLong(a, factory, newMessage, param1);
	}

	default LFltToLongFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return a -> applyAsLong(a, factory, newMessage, param1, param1);
	}

	default LFltToLongFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return a -> applyAsLong(a, factory, newMessage, param1, param2, param3);
	}

	default long applyAsLong(float a, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyAsLongX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LFltToLongFunction trying(@Nonnull ExWF<RuntimeException> factory) {
		return a -> applyAsLong(a, factory);
	}

	default long applyAsLongThen(float a, @Nonnull LToLongFunction<Throwable> handler) {
		try {
			return this.applyAsLongX(a);
		} catch (Throwable e) { //NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsLong(e);
		}
	}

	default LFltToLongFunction tryingThen(@Nonnull LToLongFunction<Throwable> handler) {
		return a -> applyAsLongThen(a, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default long nestingApplyAsLong(float a) {
		try {
			return this.applyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default long shovingApplyAsLong(float a) {
		try {
			return this.applyAsLongX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static long shovingApplyAsLong(float a, LFltToLongFunction func) {
		Null.nonNullArg(func, "func");
		return func.shovingApplyAsLong(a);
	}

	static long handlingApplyAsLong(float a, LFltToLongFunction func, HandlingInstructions<Throwable, RuntimeException> handling) { //<-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsLong(a, handling);
	}

	static long tryApplyAsLong(float a, LFltToLongFunction func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsLong(a);
	}

	static long tryApplyAsLong(float a, LFltToLongFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.applyAsLong(a, factory, newMessage);
	}

	static long tryApplyAsLong(float a, LFltToLongFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.applyAsLong(a, factory, newMessage, param1);
	}

	static long tryApplyAsLong(float a, LFltToLongFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.applyAsLong(a, factory, newMessage, param1, param2);
	}

	static long tryApplyAsLong(float a, LFltToLongFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.applyAsLong(a, factory, newMessage, param1, param2, param3);
	}

	static long tryApplyAsLong(float a, LFltToLongFunction func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.applyAsLong(a, factory);
	}

	static long tryApplyAsLongThen(float a, LFltToLongFunction func, @Nonnull LToLongFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsLongThen(a, handler);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default long nonNullApplyAsLong(float a) {
		return applyAsLong(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LFltToLongFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, float a, @Nonnull LFltToLongFunction func) {
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
	public static void fromTill(int min_i, int max_i, float a, @Nonnull LFltToLongFunction func) {
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
	public static void times(int max_i, float a, @Nonnull LFltToLongFunction func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a, func);
	}

	/** Change function to one that ignores output. */
	default LFltConsumer toConsumer() {
		return this::applyAsLong;
	}

	/** Calls domain consumer before main function. */
	default LFltToLongFunction beforeDo(@Nonnull LFltConsumer before) {
		Null.nonNullArg(before, "before");
		return (float a) -> {
			before.accept(a);
			return applyAsLong(a);
		};
	}

	/** Calls codomain consumer after main function. */
	default LFltToLongFunction afterDo(@Nonnull LLongConsumer after) {
		Null.nonNullArg(after, "after");
		return (float a) -> {
			final long retval = applyAsLong(a);
			after.accept(retval);
			return retval;
		};
	}

	/** Creates function that always returns the same value. */
	static LFltToLongFunction constant(long r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LFltToLongFunction fltToLongFunc(final @Nonnull LFltToLongFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S implements LFltToLongFunction {
		private LFltToLongFunction target = null;
		@Override
		public long applyAsLongX(float a) throws Throwable {
			return target.applyAsLongX(a);
		}
	}

	@Nonnull
	static LFltToLongFunction recursive(final @Nonnull LFunction<LFltToLongFunction, LFltToLongFunction> selfLambda) {
		final S single = new S();
		LFltToLongFunction func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static M mementoOf(float a, LFltToLongFunction function) {
		var initialValue = function.applyAsLong(a);
		return initializedMementoOf(initialValue, function);
	}

	public static M initializedMementoOf(long initialValue, LFltToLongFunction function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static M deltaOf(float a, LFltToLongFunction function, LLongBinaryOperator deltaFunction) {
		var initialValue = function.applyAsLong(a);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static M deltaOf(float a, LFltToLongFunction function) {
		var initialValue = function.applyAsLong(a);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (x2 - x1));
	}

	public static M initializedDeltaOf(long initialValue, LFltToLongFunction function, LLongBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.applyAsLong(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.applyAsLong(x1, x2));
	}

	public static M memento(long initialBaseValue, long initialValue, LFltToLongFunction baseFunction, LLongTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LFltToLongFunction.M)
	 */
	@NotThreadSafe
	final class M implements LFltToLongFunction {

		private final LFltToLongFunction baseFunction;
		private long lastBaseValue;
		private long lastValue;
		private final LLongTernaryOperator mementoFunction;

		private M(long lastBaseValue, long lastValue, LFltToLongFunction baseFunction, LLongTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public long applyAsLongX(float a) throws Throwable {
			long x1 = lastBaseValue;
			long x2 = lastBaseValue = baseFunction.applyAsLongX(a);

			return lastValue = mementoFunction.applyAsLongX(lastValue, x1, x2);
		}

		public long currentApplyAsLong(float a) {
			long x1 = lastBaseValue;
			long x2 = baseFunction.applyAsLong(a);

			return mementoFunction.applyAsLong(lastValue, x1, x2);
		}

		public long lastValue() {
			return lastValue;
		}

		public long lastBaseValue() {
			return lastBaseValue;
		}

		public long currentBaseValue(float a) {
			return baseFunction.applyAsLong(a);
		}
	}

	// </editor-fold>

	@Nonnull
	static LFltToLongFunction fltToLongFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LFltToLongFunction fltToLongFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce(message);
		};
	}

	static long call(float a, final @Nonnull LFltToLongFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsLong(a);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LFltToLongFunction compose(@Nonnull final LFltUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsLong(before.applyAsFlt(v));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToLongFunction<V> unboxingCompose(@Nonnull final LToFltFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsLong(before.applyAsFlt(v));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LFltFunction<V> then(@Nonnull LLongFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.apply(this.applyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltToByteFunction thenToByte(@Nonnull LLongToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsByte(this.applyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltToSrtFunction thenToSrt(@Nonnull LLongToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsSrt(this.applyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltToIntFunction thenToInt(@Nonnull LLongToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsInt(this.applyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltToLongFunction thenToLong(@Nonnull LLongUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsLong(this.applyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltUnaryOperator thenToFlt(@Nonnull LLongToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsFlt(this.applyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltToDblFunction thenToDbl(@Nonnull LLongToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsDbl(this.applyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltToCharFunction thenToChar(@Nonnull LLongToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsChar(this.applyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltPredicate thenToBool(@Nonnull LLongPredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.test(this.applyAsLong(a));
	}

	// </editor-fold>

	default LFltToLongFunction shoving() {

		return new LFltToLongFunction() {

			public long applyAsLong(float a) {
				try {
					return this.applyAsLongX(a);
				} catch (Throwable e) { // NOSONAR
					Handling.handleErrors(e);
					throw Handling.throwIt(e);
				}
			}

			public long applyAsLongX(float a) throws Throwable {
				return LFltToLongFunction.this.applyAsLongX(a);
			}

		};
	}

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LFltToLongFunction) Function */
	public static long doNothing(float a) {
		return Function4U.defaultLong;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C0> void forEach(IndexedRead<C0, aFloat> ia, C0 source, LLongConsumer consumer) {
		int size = ia.size(source);
		var oiFunc0 = IA.fltGetter(ia);
		int i = 0;
		for (; i < size; i++) {
			float a = oiFunc0.applyAsFlt(source, i);
			consumer.accept(this.applyAsLong(a));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C0, I0> void iterate(SequentialRead<C0, I0, aFloat> sa, C0 source, LLongConsumer consumer) {
		var iterator0 = SA.adapter(sa).apply(source);
		var testFunc0 = SA.tester(sa);
		var nextFunc0 = SA.fltSupplier(sa);
		while (testFunc0.test(iterator0)) {
			float a = nextFunc0.applyAsFlt(iterator0);
			consumer.accept(this.applyAsLong(a));
		}
	}

}
