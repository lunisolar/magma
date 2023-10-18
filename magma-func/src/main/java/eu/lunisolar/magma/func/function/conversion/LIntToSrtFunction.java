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
 * Non-throwing functional interface (lambda) LIntToSrtFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): int a
 *
 * Co-domain: short
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LIntToSrtFunction extends MetaFunction, MetaInterface.NonThrowing, Codomain<aShort>, Domain1<aInt> { // NOSONAR

	String DESCRIPTION = "LIntToSrtFunction: short applyAsSrt(int a)";

	// short applyAsSrt(int a) ;
	default short applyAsSrt(int a) {
		// return nestingApplyAsSrt(a);
		try {
			return this.applyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsSrt(int a)
	 */
	short applyAsSrtX(int a) throws Throwable;

	default short tupleApplyAsSrt(LIntSingle args) {
		return applyAsSrt(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default short handlingApplyAsSrt(int a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LIntToSrtFunction handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> handlingApplyAsSrt(a, handling);
	}

	default short applyAsSrt(int a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default short applyAsSrt(int a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default short applyAsSrt(int a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default short applyAsSrt(int a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LIntToSrtFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return a -> applyAsSrt(a, factory, newMessage);
	}

	default LIntToSrtFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return a -> applyAsSrt(a, factory, newMessage, param1);
	}

	default LIntToSrtFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return a -> applyAsSrt(a, factory, newMessage, param1, param1);
	}

	default LIntToSrtFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return a -> applyAsSrt(a, factory, newMessage, param1, param2, param3);
	}

	default short applyAsSrt(int a, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LIntToSrtFunction trying(@Nonnull ExWF<RuntimeException> factory) {
		return a -> applyAsSrt(a, factory);
	}

	default short applyAsSrtThen(int a, @Nonnull LToSrtFunction<Throwable> handler) {
		try {
			return this.applyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsSrt(e);
		}
	}

	default LIntToSrtFunction tryingThen(@Nonnull LToSrtFunction<Throwable> handler) {
		return a -> applyAsSrtThen(a, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default short nestingApplyAsSrt(int a) {
		try {
			return this.applyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default short shovingApplyAsSrt(int a) {
		try {
			return this.applyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static short shovingApplyAsSrt(int a, LIntToSrtFunction func) {
		Null.nonNullArg(func, "func");
		return func.shovingApplyAsSrt(a);
	}

	static short handlingApplyAsSrt(int a, LIntToSrtFunction func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsSrt(a, handling);
	}

	static short tryApplyAsSrt(int a, LIntToSrtFunction func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsSrt(a);
	}

	static short tryApplyAsSrt(int a, LIntToSrtFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrt(a, factory, newMessage);
	}

	static short tryApplyAsSrt(int a, LIntToSrtFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrt(a, factory, newMessage, param1);
	}

	static short tryApplyAsSrt(int a, LIntToSrtFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrt(a, factory, newMessage, param1, param2);
	}

	static short tryApplyAsSrt(int a, LIntToSrtFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrt(a, factory, newMessage, param1, param2, param3);
	}

	static short tryApplyAsSrt(int a, LIntToSrtFunction func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrt(a, factory);
	}

	static short tryApplyAsSrtThen(int a, LIntToSrtFunction func, @Nonnull LToSrtFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrtThen(a, handler);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default short nonNullApplyAsSrt(int a) {
		return applyAsSrt(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LIntToSrtFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_a, int max_a, @Nonnull LIntToSrtFunction func) {
		Null.nonNullArg(func, "func");
		if (min_a <= max_a) {
			for (int a = min_a; a <= max_a; a++) {
				func.applyAsSrt(a);
			}
		} else {
			for (int a = min_a; a >= max_a; a--) {
				func.applyAsSrt(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_a, int max_a, @Nonnull LIntToSrtFunction func) {
		Null.nonNullArg(func, "func");
		if (min_a <= max_a) {
			for (int a = min_a; a < max_a; a++) {
				func.applyAsSrt(a);
			}
		} else {
			for (int a = min_a; a > max_a; a--) {
				func.applyAsSrt(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_a, @Nonnull LIntToSrtFunction func) {
		if (max_a < 0)
			return;
		fromTill(0, max_a, func);
	}

	/** Change function to one that ignores output. */
	default LIntConsumer toConsumer() {
		return this::applyAsSrt;
	}

	/** Calls domain consumer before main function. */
	default LIntToSrtFunction beforeDo(@Nonnull LIntConsumer before) {
		Null.nonNullArg(before, "before");
		return (int a) -> {
			before.accept(a);
			return applyAsSrt(a);
		};
	}

	/** Calls codomain consumer after main function. */
	default LIntToSrtFunction afterDo(@Nonnull LSrtConsumer after) {
		Null.nonNullArg(after, "after");
		return (int a) -> {
			final short retval = applyAsSrt(a);
			after.accept(retval);
			return retval;
		};
	}

	/** Creates function that always returns the same value. */
	static LIntToSrtFunction constant(short r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LIntToSrtFunction intToSrtFunc(final @Nonnull LIntToSrtFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S implements LIntToSrtFunction {
		private LIntToSrtFunction target = null;
		@Override
		public short applyAsSrtX(int a) throws Throwable {
			return target.applyAsSrtX(a);
		}
	}

	@Nonnull
	static LIntToSrtFunction recursive(final @Nonnull LFunction<LIntToSrtFunction, LIntToSrtFunction> selfLambda) {
		final S single = new S();
		LIntToSrtFunction func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static M mementoOf(int a, LIntToSrtFunction function) {
		var initialValue = function.applyAsSrt(a);
		return initializedMementoOf(initialValue, function);
	}

	public static M initializedMementoOf(short initialValue, LIntToSrtFunction function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static M deltaOf(int a, LIntToSrtFunction function, LSrtBinaryOperator deltaFunction) {
		var initialValue = function.applyAsSrt(a);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static M deltaOf(int a, LIntToSrtFunction function) {
		var initialValue = function.applyAsSrt(a);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (short) (x2 - x1));
	}

	public static M initializedDeltaOf(short initialValue, LIntToSrtFunction function, LSrtBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.applyAsSrt(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.applyAsSrt(x1, x2));
	}

	public static M memento(short initialBaseValue, short initialValue, LIntToSrtFunction baseFunction, LSrtTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LIntToSrtFunction.M)
	 */
	@NotThreadSafe
	final class M implements LIntToSrtFunction {

		private final LIntToSrtFunction baseFunction;
		private short lastBaseValue;
		private short lastValue;
		private final LSrtTernaryOperator mementoFunction;

		private M(short lastBaseValue, short lastValue, LIntToSrtFunction baseFunction, LSrtTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public short applyAsSrtX(int a) throws Throwable {
			short x1 = lastBaseValue;
			short x2 = lastBaseValue = baseFunction.applyAsSrtX(a);

			return lastValue = mementoFunction.applyAsSrtX(lastValue, x1, x2);
		}

		public short currentApplyAsSrt(int a) {
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
	static LIntToSrtFunction intToSrtFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LIntToSrtFunction intToSrtFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce(message);
		};
	}

	static short call(int a, final @Nonnull LIntToSrtFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsSrt(a);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LIntToSrtFunction compose(@Nonnull final LIntUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsSrt(before.applyAsInt(v));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToSrtFunction<V> unboxingCompose(@Nonnull final LToIntFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsSrt(before.applyAsInt(v));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LIntFunction<V> then(@Nonnull LSrtFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.apply(this.applyAsSrt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToByteFunction thenToByte(@Nonnull LSrtToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsByte(this.applyAsSrt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToSrtFunction thenToSrt(@Nonnull LSrtUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsSrt(this.applyAsSrt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntUnaryOperator thenToInt(@Nonnull LSrtToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsInt(this.applyAsSrt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToLongFunction thenToLong(@Nonnull LSrtToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsLong(this.applyAsSrt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToFltFunction thenToFlt(@Nonnull LSrtToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsFlt(this.applyAsSrt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToDblFunction thenToDbl(@Nonnull LSrtToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsDbl(this.applyAsSrt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToCharFunction thenToChar(@Nonnull LSrtToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsChar(this.applyAsSrt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntPredicate thenToBool(@Nonnull LSrtPredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.test(this.applyAsSrt(a));
	}

	// </editor-fold>

	default LIntToSrtFunction shoving() {

		return new LIntToSrtFunction() {

			public short applyAsSrt(int a) {
				try {
					return this.applyAsSrtX(a);
				} catch (Throwable e) { // NOSONAR
					Handling.handleErrors(e);
					throw Handling.throwIt(e);
				}
			}

			public short applyAsSrtX(int a) throws Throwable {
				return LIntToSrtFunction.this.applyAsSrtX(a);
			}

		};
	}

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LIntToSrtFunction) Function */
	public static short doNothing(int a) {
		return Function4U.defaultShort;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C0> void forEach(IndexedRead<C0, aInt> ia, C0 source, LSrtConsumer consumer) {
		int size = ia.size(source);
		LOiToIntFunction<Object> oiFunc0 = (LOiToIntFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			int a = oiFunc0.applyAsInt(source, i);
			consumer.accept(this.applyAsSrt(a));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C0, I0> void iterate(SequentialRead<C0, I0, aInt> sa, C0 source, LSrtConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).apply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToIntFunction<Object> nextFunc0 = (LToIntFunction) sa.supplier();
		while (testFunc0.test(iterator0)) {
			int a = nextFunc0.applyAsInt(iterator0);
			consumer.accept(this.applyAsSrt(a));
		}
	}

}
