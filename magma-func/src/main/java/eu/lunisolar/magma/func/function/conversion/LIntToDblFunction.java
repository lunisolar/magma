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
 * Non-throwing functional interface (lambda) LIntToDblFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): int a
 *
 * Co-domain: double
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LIntToDblFunction extends IntToDoubleFunction, MetaFunction, MetaInterface.NonThrowing, Codomain<aDouble>, Domain1<aInt> { // NOSONAR

	String DESCRIPTION = "LIntToDblFunction: double applyAsDbl(int a)";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LIntToDblFunction interface should be discouraged.
	 */
	@Override
	@Deprecated
	default double applyAsDouble(int a) {
		return this.applyAsDbl(a);
	}

	// double applyAsDbl(int a) ;
	default double applyAsDbl(int a) {
		// return nestingApplyAsDbl(a);
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsDbl(int a)
	 */
	double applyAsDblX(int a) throws Throwable;

	default double tupleApplyAsDbl(LIntSingle args) {
		return applyAsDbl(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default double handlingApplyAsDbl(int a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LIntToDblFunction handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> handlingApplyAsDbl(a, handling);
	}

	default double applyAsDbl(int a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default double applyAsDbl(int a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default double applyAsDbl(int a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default double applyAsDbl(int a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LIntToDblFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return a -> applyAsDbl(a, factory, newMessage);
	}

	default LIntToDblFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return a -> applyAsDbl(a, factory, newMessage, param1);
	}

	default LIntToDblFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return a -> applyAsDbl(a, factory, newMessage, param1, param1);
	}

	default LIntToDblFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return a -> applyAsDbl(a, factory, newMessage, param1, param2, param3);
	}

	default double applyAsDbl(int a, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LIntToDblFunction trying(@Nonnull ExWF<RuntimeException> factory) {
		return a -> applyAsDbl(a, factory);
	}

	default double applyAsDblThen(int a, @Nonnull LToDblFunction<Throwable> handler) {
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsDbl(e);
		}
	}

	default LIntToDblFunction tryingThen(@Nonnull LToDblFunction<Throwable> handler) {
		return a -> applyAsDblThen(a, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default double nestingApplyAsDbl(int a) {
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default double shovingApplyAsDbl(int a) {
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static double shovingApplyAsDbl(int a, LIntToDblFunction func) {
		Null.nonNullArg(func, "func");
		return func.shovingApplyAsDbl(a);
	}

	static double handlingApplyAsDbl(int a, LIntToDblFunction func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsDbl(a, handling);
	}

	static double tryApplyAsDbl(int a, LIntToDblFunction func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsDbl(a);
	}

	static double tryApplyAsDbl(int a, LIntToDblFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.applyAsDbl(a, factory, newMessage);
	}

	static double tryApplyAsDbl(int a, LIntToDblFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.applyAsDbl(a, factory, newMessage, param1);
	}

	static double tryApplyAsDbl(int a, LIntToDblFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.applyAsDbl(a, factory, newMessage, param1, param2);
	}

	static double tryApplyAsDbl(int a, LIntToDblFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.applyAsDbl(a, factory, newMessage, param1, param2, param3);
	}

	static double tryApplyAsDbl(int a, LIntToDblFunction func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.applyAsDbl(a, factory);
	}

	static double tryApplyAsDblThen(int a, LIntToDblFunction func, @Nonnull LToDblFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsDblThen(a, handler);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default double nonNullApplyAsDbl(int a) {
		return applyAsDbl(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LIntToDblFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_a, int max_a, @Nonnull LIntToDblFunction func) {
		Null.nonNullArg(func, "func");
		if (min_a <= max_a) {
			for (int a = min_a; a <= max_a; a++) {
				func.applyAsDbl(a);
			}
		} else {
			for (int a = min_a; a >= max_a; a--) {
				func.applyAsDbl(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_a, int max_a, @Nonnull LIntToDblFunction func) {
		Null.nonNullArg(func, "func");
		if (min_a <= max_a) {
			for (int a = min_a; a < max_a; a++) {
				func.applyAsDbl(a);
			}
		} else {
			for (int a = min_a; a > max_a; a--) {
				func.applyAsDbl(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_a, @Nonnull LIntToDblFunction func) {
		if (max_a < 0)
			return;
		fromTill(0, max_a, func);
	}

	/** Change function to one that ignores output. */
	default LIntConsumer toConsumer() {
		return this::applyAsDbl;
	}

	/** Calls domain consumer before main function. */
	default LIntToDblFunction beforeDo(@Nonnull LIntConsumer before) {
		Null.nonNullArg(before, "before");
		return (int a) -> {
			before.accept(a);
			return applyAsDbl(a);
		};
	}

	/** Calls codomain consumer after main function. */
	default LIntToDblFunction afterDo(@Nonnull LDblConsumer after) {
		Null.nonNullArg(after, "after");
		return (int a) -> {
			final double retval = applyAsDbl(a);
			after.accept(retval);
			return retval;
		};
	}

	/** Creates function that always returns the same value. */
	static LIntToDblFunction constant(double r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LIntToDblFunction intToDblFunc(final @Nonnull LIntToDblFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S implements LIntToDblFunction {
		private LIntToDblFunction target = null;
		@Override
		public double applyAsDblX(int a) throws Throwable {
			return target.applyAsDblX(a);
		}
	}

	@Nonnull
	static LIntToDblFunction recursive(final @Nonnull LFunction<LIntToDblFunction, LIntToDblFunction> selfLambda) {
		final S single = new S();
		LIntToDblFunction func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static M mementoOf(int a, LIntToDblFunction function) {
		var initialValue = function.applyAsDbl(a);
		return initializedMementoOf(initialValue, function);
	}

	public static M initializedMementoOf(double initialValue, LIntToDblFunction function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static M deltaOf(int a, LIntToDblFunction function, LDblBinaryOperator deltaFunction) {
		var initialValue = function.applyAsDbl(a);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static M deltaOf(int a, LIntToDblFunction function) {
		var initialValue = function.applyAsDbl(a);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (x2 - x1));
	}

	public static M initializedDeltaOf(double initialValue, LIntToDblFunction function, LDblBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.applyAsDbl(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.applyAsDbl(x1, x2));
	}

	public static M memento(double initialBaseValue, double initialValue, LIntToDblFunction baseFunction, LDblTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LIntToDblFunction.M)
	 */
	@NotThreadSafe
	final class M implements LIntToDblFunction {

		private final LIntToDblFunction baseFunction;
		private double lastBaseValue;
		private double lastValue;
		private final LDblTernaryOperator mementoFunction;

		private M(double lastBaseValue, double lastValue, LIntToDblFunction baseFunction, LDblTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public double applyAsDblX(int a) throws Throwable {
			double x1 = lastBaseValue;
			double x2 = lastBaseValue = baseFunction.applyAsDblX(a);

			return lastValue = mementoFunction.applyAsDblX(lastValue, x1, x2);
		}

		public double currentApplyAsDbl(int a) {
			double x1 = lastBaseValue;
			double x2 = baseFunction.applyAsDbl(a);

			return mementoFunction.applyAsDbl(lastValue, x1, x2);
		}

		public double lastValue() {
			return lastValue;
		};

		public double lastBaseValue() {
			return lastBaseValue;
		};
	}

	// </editor-fold>

	@Nonnull
	static LIntToDblFunction intToDblFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LIntToDblFunction intToDblFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce(message);
		};
	}

	static double call(int a, final @Nonnull LIntToDblFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsDbl(a);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static LIntToDblFunction wrap(final IntToDoubleFunction other) {
		return other::applyAsDouble;
	}
	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LIntToDblFunction compose(@Nonnull final LIntUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsDbl(before.applyAsInt(v));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToDblFunction<V> unboxingCompose(@Nonnull final LToIntFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsDbl(before.applyAsInt(v));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LIntFunction<V> then(@Nonnull LDblFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.apply(this.applyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToByteFunction thenToByte(@Nonnull LDblToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsByte(this.applyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToSrtFunction thenToSrt(@Nonnull LDblToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsSrt(this.applyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntUnaryOperator thenToInt(@Nonnull LDblToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsInt(this.applyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToLongFunction thenToLong(@Nonnull LDblToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsLong(this.applyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToFltFunction thenToFlt(@Nonnull LDblToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsFlt(this.applyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToDblFunction thenToDbl(@Nonnull LDblUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsDbl(this.applyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToCharFunction thenToChar(@Nonnull LDblToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsChar(this.applyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntPredicate thenToBool(@Nonnull LDblPredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.test(this.applyAsDbl(a));
	}

	// </editor-fold>

	default LIntToDblFunction shoving() {

		return new LIntToDblFunction() {

			public double applyAsDbl(int a) {
				try {
					return this.applyAsDblX(a);
				} catch (Throwable e) { // NOSONAR
					Handling.handleErrors(e);
					throw Handling.throwIt(e);
				}
			}

			public double applyAsDblX(int a) throws Throwable {
				return LIntToDblFunction.this.applyAsDblX(a);
			}

		};
	}

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LIntToDblFunction) Function */
	public static double doNothing(int a) {
		return Function4U.defaultDouble;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C0> void forEach(IndexedRead<C0, aInt> ia, C0 source, LDblConsumer consumer) {
		int size = ia.size(source);
		LOiToIntFunction<Object> oiFunc0 = (LOiToIntFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			int a = oiFunc0.applyAsInt(source, i);
			consumer.accept(this.applyAsDbl(a));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C0, I0> void iterate(SequentialRead<C0, I0, aInt> sa, C0 source, LDblConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).apply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToIntFunction<Object> nextFunc0 = (LToIntFunction) sa.supplier();
		while (testFunc0.test(iterator0)) {
			int a = nextFunc0.applyAsInt(iterator0);
			consumer.accept(this.applyAsDbl(a));
		}
	}

}
