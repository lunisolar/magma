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
 * Non-throwing functional interface (lambda) LLongToSrtFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): long a
 *
 * Co-domain: short
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LLongToSrtFunction extends MetaFunction, MetaInterface.NonThrowing, Codomain<aShort>, Domain1<aLong> { // NOSONAR

	String DESCRIPTION = "LLongToSrtFunction: short applyAsSrt(long a)";

	// short applyAsSrt(long a) ;
	default short applyAsSrt(long a) {
		// return nestingApplyAsSrt(a);
		try {
			return this.applyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsSrt(long a)
	 */
	short applyAsSrtX(long a) throws Throwable;

	default short tupleApplyAsSrt(LLongSingle args) {
		return applyAsSrt(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default short handlingApplyAsSrt(long a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LLongToSrtFunction handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> handlingApplyAsSrt(a, handling);
	}

	default short applyAsSrt(long a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default short applyAsSrt(long a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default short applyAsSrt(long a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default short applyAsSrt(long a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LLongToSrtFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return a -> applyAsSrt(a, factory, newMessage);
	}

	default LLongToSrtFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return a -> applyAsSrt(a, factory, newMessage, param1);
	}

	default LLongToSrtFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return a -> applyAsSrt(a, factory, newMessage, param1, param1);
	}

	default LLongToSrtFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return a -> applyAsSrt(a, factory, newMessage, param1, param2, param3);
	}

	default short applyAsSrt(long a, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LLongToSrtFunction trying(@Nonnull ExWF<RuntimeException> factory) {
		return a -> applyAsSrt(a, factory);
	}

	default short applyAsSrtThen(long a, @Nonnull LToSrtFunction<Throwable> handler) {
		try {
			return this.applyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsSrt(e);
		}
	}

	default LLongToSrtFunction tryingThen(@Nonnull LToSrtFunction<Throwable> handler) {
		return a -> applyAsSrtThen(a, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default short nestingApplyAsSrt(long a) {
		try {
			return this.applyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default short shovingApplyAsSrt(long a) {
		try {
			return this.applyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static short shovingApplyAsSrt(long a, LLongToSrtFunction func) {
		Null.nonNullArg(func, "func");
		return func.shovingApplyAsSrt(a);
	}

	static short handlingApplyAsSrt(long a, LLongToSrtFunction func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsSrt(a, handling);
	}

	static short tryApplyAsSrt(long a, LLongToSrtFunction func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsSrt(a);
	}

	static short tryApplyAsSrt(long a, LLongToSrtFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrt(a, factory, newMessage);
	}

	static short tryApplyAsSrt(long a, LLongToSrtFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrt(a, factory, newMessage, param1);
	}

	static short tryApplyAsSrt(long a, LLongToSrtFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrt(a, factory, newMessage, param1, param2);
	}

	static short tryApplyAsSrt(long a, LLongToSrtFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrt(a, factory, newMessage, param1, param2, param3);
	}

	static short tryApplyAsSrt(long a, LLongToSrtFunction func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrt(a, factory);
	}

	static short tryApplyAsSrtThen(long a, LLongToSrtFunction func, @Nonnull LToSrtFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrtThen(a, handler);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default short nonNullApplyAsSrt(long a) {
		return applyAsSrt(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LLongToSrtFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(long min_a, long max_a, @Nonnull LLongToSrtFunction func) {
		Null.nonNullArg(func, "func");
		if (min_a <= max_a) {
			for (long a = min_a; a <= max_a; a++) {
				func.applyAsSrt(a);
			}
		} else {
			for (long a = min_a; a >= max_a; a--) {
				func.applyAsSrt(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(long min_a, long max_a, @Nonnull LLongToSrtFunction func) {
		Null.nonNullArg(func, "func");
		if (min_a <= max_a) {
			for (long a = min_a; a < max_a; a++) {
				func.applyAsSrt(a);
			}
		} else {
			for (long a = min_a; a > max_a; a--) {
				func.applyAsSrt(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(long max_a, @Nonnull LLongToSrtFunction func) {
		if (max_a < 0)
			return;
		fromTill(0, max_a, func);
	}

	/** Change function to consumer that ignores output. */
	default LLongConsumer toConsumer() {
		return this::applyAsSrt;
	}

	/** Calls domain consumer before main function. */
	default LLongToSrtFunction beforeDo(@Nonnull LLongConsumer before) {
		Null.nonNullArg(before, "before");
		return (long a) -> {
			before.accept(a);
			return applyAsSrt(a);
		};
	}

	/** Calls codomain consumer after main function. */
	default LLongToSrtFunction afterDo(@Nonnull LSrtConsumer after) {
		Null.nonNullArg(after, "after");
		return (long a) -> {
			final short retval = applyAsSrt(a);
			after.accept(retval);
			return retval;
		};
	}

	/** Creates function that always returns the same value. */
	static LLongToSrtFunction constant(short r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LLongToSrtFunction longToSrtFunc(final @Nonnull LLongToSrtFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S implements LLongToSrtFunction {
		private LLongToSrtFunction target = null;
		@Override
		public short applyAsSrtX(long a) throws Throwable {
			return target.applyAsSrtX(a);
		}
	}

	@Nonnull
	static LLongToSrtFunction recursive(final @Nonnull LFunction<LLongToSrtFunction, LLongToSrtFunction> selfLambda) {
		final S single = new S();
		LLongToSrtFunction func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static M mementoOf(long a, LLongToSrtFunction function) {
		var initialValue = function.applyAsSrt(a);
		return initializedMementoOf(initialValue, function);
	}

	public static M initializedMementoOf(short initialValue, LLongToSrtFunction function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static M deltaOf(long a, LLongToSrtFunction function, LSrtBinaryOperator deltaFunction) {
		var initialValue = function.applyAsSrt(a);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static M deltaOf(long a, LLongToSrtFunction function) {
		var initialValue = function.applyAsSrt(a);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (short) (x2 - x1));
	}

	public static M initializedDeltaOf(short initialValue, LLongToSrtFunction function, LSrtBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.applyAsSrt(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.applyAsSrt(x1, x2));
	}

	public static M memento(short initialBaseValue, short initialValue, LLongToSrtFunction baseFunction, LSrtTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LLongToSrtFunction.M)
	 */
	@NotThreadSafe
	final class M implements LLongToSrtFunction {

		private final LLongToSrtFunction baseFunction;
		private short lastBaseValue;
		private short lastValue;
		private final LSrtTernaryOperator mementoFunction;

		private M(short lastBaseValue, short lastValue, LLongToSrtFunction baseFunction, LSrtTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public short applyAsSrtX(long a) throws Throwable {
			short x1 = lastBaseValue;
			short x2 = lastBaseValue = baseFunction.applyAsSrtX(a);

			return lastValue = mementoFunction.applyAsSrtX(lastValue, x1, x2);
		}

		public short currentApplyAsSrt(long a) {
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
	static LLongToSrtFunction longToSrtFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LLongToSrtFunction longToSrtFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce(message);
		};
	}

	static short call(long a, final @Nonnull LLongToSrtFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsSrt(a);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LLongToSrtFunction compose(@Nonnull final LLongUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsSrt(before.applyAsLong(v));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToSrtFunction<V> unboxingCompose(@Nonnull final LToLongFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsSrt(before.applyAsLong(v));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LLongFunction<V> then(@Nonnull LSrtFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.apply(this.applyAsSrt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToByteFunction thenToByte(@Nonnull LSrtToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsByte(this.applyAsSrt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToSrtFunction thenToSrt(@Nonnull LSrtUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsSrt(this.applyAsSrt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToIntFunction thenToInt(@Nonnull LSrtToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsInt(this.applyAsSrt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongUnaryOperator thenToLong(@Nonnull LSrtToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsLong(this.applyAsSrt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToFltFunction thenToFlt(@Nonnull LSrtToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsFlt(this.applyAsSrt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToDblFunction thenToDbl(@Nonnull LSrtToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsDbl(this.applyAsSrt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToCharFunction thenToChar(@Nonnull LSrtToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsChar(this.applyAsSrt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongPredicate thenToBool(@Nonnull LSrtPredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.test(this.applyAsSrt(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LLongToSrtFunction) Function */
	public static short doNothing(long a) {
		return Function4U.defaultShort;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C0> void forEach(IndexedRead<C0, aLong> ia, C0 source, LSrtConsumer consumer) {
		int size = ia.size(source);
		LOiToLongFunction<Object> oiFunc0 = (LOiToLongFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			long a = oiFunc0.applyAsLong(source, i);
			consumer.accept(this.applyAsSrt(a));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C0, I0> void iterate(SequentialRead<C0, I0, aLong> sa, C0 source, LSrtConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).apply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToLongFunction<Object> nextFunc0 = (LToLongFunction) sa.supplier();
		while (testFunc0.test(iterator0)) {
			long a = nextFunc0.applyAsLong(iterator0);
			consumer.accept(this.applyAsSrt(a));
		}
	}

}
