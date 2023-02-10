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
 * Non-throwing functional interface (lambda) LToDblFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): T a
 *
 * Co-domain: double
 *
 * Special case of function that corresponds to expressions like (iterator) -> Iterator::next
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LToDblFunction<T> extends ToDoubleFunction<T>, MetaFunction, MetaInterface.NonThrowing, OFunction<T, aDouble>, Codomain<aDouble>, Domain1<a<T>> { // NOSONAR

	String DESCRIPTION = "LToDblFunction: double applyAsDbl(T a)";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LToDblFunction interface should be discouraged.
	 */
	@Override
	@Deprecated
	default double applyAsDouble(T a) {
		return this.applyAsDbl(a);
	}

	// double applyAsDbl(T a) ;
	default double applyAsDbl(T a) {
		// return nestingApplyAsDbl(a);
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsDbl(T a)
	 */
	double applyAsDblX(T a) throws Throwable;

	default double tupleApplyAsDbl(LSingle<T> args) {
		return applyAsDbl(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default double handlingApplyAsDbl(T a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LToDblFunction<T> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> handlingApplyAsDbl(a, handling);
	}

	default double applyAsDbl(T a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default double applyAsDbl(T a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default double applyAsDbl(T a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default double applyAsDbl(T a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LToDblFunction<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return a -> applyAsDbl(a, factory, newMessage);
	}

	default LToDblFunction<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return a -> applyAsDbl(a, factory, newMessage, param1);
	}

	default LToDblFunction<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return a -> applyAsDbl(a, factory, newMessage, param1, param1);
	}

	default LToDblFunction<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return a -> applyAsDbl(a, factory, newMessage, param1, param2, param3);
	}

	default double applyAsDbl(T a, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LToDblFunction<T> trying(@Nonnull ExWF<RuntimeException> factory) {
		return a -> applyAsDbl(a, factory);
	}

	default double applyAsDblThen(T a, @Nonnull LToDblFunction<Throwable> handler) {
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsDbl(e);
		}
	}

	default LToDblFunction<T> tryingThen(@Nonnull LToDblFunction<Throwable> handler) {
		return a -> applyAsDblThen(a, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default double nestingApplyAsDbl(T a) {
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default double shovingApplyAsDbl(T a) {
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> double shovingApplyAsDbl(T a, LToDblFunction<T> func) {
		Null.nonNullArg(func, "func");
		return func.shovingApplyAsDbl(a);
	}

	static <T> double handlingApplyAsDbl(T a, LToDblFunction<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsDbl(a, handling);
	}

	static <T> double tryApplyAsDbl(T a, LToDblFunction<T> func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsDbl(a);
	}

	static <T> double tryApplyAsDbl(T a, LToDblFunction<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.applyAsDbl(a, factory, newMessage);
	}

	static <T> double tryApplyAsDbl(T a, LToDblFunction<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.applyAsDbl(a, factory, newMessage, param1);
	}

	static <T> double tryApplyAsDbl(T a, LToDblFunction<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.applyAsDbl(a, factory, newMessage, param1, param2);
	}

	static <T> double tryApplyAsDbl(T a, LToDblFunction<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.applyAsDbl(a, factory, newMessage, param1, param2, param3);
	}

	static <T> double tryApplyAsDbl(T a, LToDblFunction<T> func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.applyAsDbl(a, factory);
	}

	static <T> double tryApplyAsDblThen(T a, LToDblFunction<T> func, @Nonnull LToDblFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsDblThen(a, handler);
	}

	default double failSafeApplyAsDbl(T a, @Nonnull LToDblFunction<T> failSafe) {
		try {
			return applyAsDbl(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.applyAsDbl(a);
		}
	}

	static <T> double failSafeApplyAsDbl(T a, LToDblFunction<T> func, @Nonnull LToDblFunction<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.applyAsDbl(a);
		} else {
			return func.failSafeApplyAsDbl(a, failSafe);
		}
	}

	static <T> LToDblFunction<T> failSafe(LToDblFunction<T> func, @Nonnull LToDblFunction<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeApplyAsDbl(a, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default double nonNullApplyAsDbl(T a) {
		return applyAsDbl(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LToDblFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_i, int max_i, T a, @Nonnull LToDblFunction<T> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.applyAsDbl(a);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.applyAsDbl(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTill(int min_i, int max_i, T a, @Nonnull LToDblFunction<T> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.applyAsDbl(a);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.applyAsDbl(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void times(int max_i, T a, @Nonnull LToDblFunction<T> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a, func);
	}

	/** Extract and apply function. */
	public static <M, K, V> double from(@Nonnull M container, LBiFunction<M, K, V> extractor, K key, @Nonnull LToDblFunction<V> function, double orElse) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.applyAsDbl(value);
		}

		return orElse;
	}

	/** Change function to consumer that ignores output. */
	default LConsumer<T> toConsumer() {
		return this::applyAsDbl;
	}

	/** Calls domain consumer before main function. */
	default LToDblFunction<T> beforeDo(@Nonnull LConsumer<T> before) {
		Null.nonNullArg(before, "before");
		return (T a) -> {
			before.accept(a);
			return applyAsDbl(a);
		};
	}

	/** Calls codomain consumer after main function. */
	default LToDblFunction<T> afterDo(@Nonnull LDblConsumer after) {
		Null.nonNullArg(after, "after");
		return (T a) -> {
			final double retval = applyAsDbl(a);
			after.accept(retval);
			return retval;
		};
	}

	/** Creates function that always returns the same value. */
	static <T> LToDblFunction<T> constant(double r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LToDblFunction<T> toDblFunc(final @Nonnull LToDblFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S<T> implements LToDblFunction<T> {
		private LToDblFunction<T> target = null;
		@Override
		public double applyAsDblX(T a) throws Throwable {
			return target.applyAsDblX(a);
		}
	}

	@Nonnull
	static <T> LToDblFunction<T> recursive(final @Nonnull LFunction<LToDblFunction<T>, LToDblFunction<T>> selfLambda) {
		final S<T> single = new S();
		LToDblFunction<T> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static <T> M<T> mementoOf(T a, LToDblFunction<T> function) {
		var initialValue = function.applyAsDbl(a);
		return initializedMementoOf(initialValue, function);
	}

	public static <T> M<T> initializedMementoOf(double initialValue, LToDblFunction<T> function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static <T> M<T> deltaOf(T a, LToDblFunction<T> function, LDblBinaryOperator deltaFunction) {
		var initialValue = function.applyAsDbl(a);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static <T> M<T> deltaOf(T a, LToDblFunction<T> function) {
		var initialValue = function.applyAsDbl(a);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (x2 - x1));
	}

	public static <T> M<T> initializedDeltaOf(double initialValue, LToDblFunction<T> function, LDblBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.applyAsDbl(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.applyAsDbl(x1, x2));
	}

	public static <T> M<T> memento(double initialBaseValue, double initialValue, LToDblFunction<T> baseFunction, LDblTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LToDblFunction.M)
	 */
	@NotThreadSafe
	final class M<T> implements LToDblFunction<T> {

		private final LToDblFunction<T> baseFunction;
		private double lastBaseValue;
		private double lastValue;
		private final LDblTernaryOperator mementoFunction;

		private M(double lastBaseValue, double lastValue, LToDblFunction<T> baseFunction, LDblTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public double applyAsDblX(T a) throws Throwable {
			double x1 = lastBaseValue;
			double x2 = lastBaseValue = baseFunction.applyAsDblX(a);

			return lastValue = mementoFunction.applyAsDblX(lastValue, x1, x2);
		}

		public double currentApplyAsDbl(T a) {
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
	static <T> LToDblFunction<T> toDblFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T> LToDblFunction<T> toDblFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce(message);
		};
	}

	static <T> double call(T a, final @Nonnull LToDblFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsDbl(a);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <T> LToDblFunction<T> wrap(final ToDoubleFunction<T> other) {
		return other::applyAsDouble;
	}
	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToDblFunction<V> compose(@Nonnull final LFunction<? super V, ? extends T> before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsDbl(before.apply(v));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LFunction<T, V> then(@Nonnull LDblFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.apply(this.applyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToByteFunction<T> thenToByte(@Nonnull LDblToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsByte(this.applyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToSrtFunction<T> thenToSrt(@Nonnull LDblToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsSrt(this.applyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToIntFunction<T> thenToInt(@Nonnull LDblToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsInt(this.applyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToLongFunction<T> thenToLong(@Nonnull LDblToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsLong(this.applyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToFltFunction<T> thenToFlt(@Nonnull LDblToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsFlt(this.applyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToDblFunction<T> thenToDbl(@Nonnull LDblUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsDbl(this.applyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToCharFunction<T> thenToChar(@Nonnull LDblToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsChar(this.applyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LPredicate<T> thenToBool(@Nonnull LDblPredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.test(this.applyAsDbl(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LToDblFunction) Function */
	public static <T> double doNothing(T a) {
		return Function4U.defaultDouble;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C0> void forEach(IndexedRead<C0, a<T>> ia, C0 source, LDblConsumer consumer) {
		int size = ia.size(source);
		LOiFunction<Object, T> oiFunc0 = (LOiFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			T a = oiFunc0.apply(source, i);
			consumer.accept(this.applyAsDbl(a));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C0, I0> void iterate(SequentialRead<C0, I0, a<T>> sa, C0 source, LDblConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).apply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LFunction<Object, T> nextFunc0 = (LFunction) sa.supplier();
		while (testFunc0.test(iterator0)) {
			T a = nextFunc0.apply(iterator0);
			consumer.accept(this.applyAsDbl(a));
		}
	}

}
