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
 * Non-throwing functional interface (lambda) LDblToFltFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): double a
 *
 * Co-domain: float
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LDblToFltFunction extends MetaFunction, MetaInterface.NonThrowing, Codomain<aFloat>, Domain1<aDouble> { // NOSONAR

	String DESCRIPTION = "LDblToFltFunction: float applyAsFlt(double a)";

	// float applyAsFlt(double a) ;
	default float applyAsFlt(double a) {
		// return nestingApplyAsFlt(a);
		try {
			return this.applyAsFltX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsFlt(double a)
	 */
	float applyAsFltX(double a) throws Throwable;

	default float tupleApplyAsFlt(LDblSingle args) {
		return applyAsFlt(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default float handlingApplyAsFlt(double a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsFltX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LDblToFltFunction handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> handlingApplyAsFlt(a, handling);
	}

	default float applyAsFlt(double a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyAsFltX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default float applyAsFlt(double a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyAsFltX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default float applyAsFlt(double a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyAsFltX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default float applyAsFlt(double a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyAsFltX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LDblToFltFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return a -> applyAsFlt(a, factory, newMessage);
	}

	default LDblToFltFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return a -> applyAsFlt(a, factory, newMessage, param1);
	}

	default LDblToFltFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return a -> applyAsFlt(a, factory, newMessage, param1, param1);
	}

	default LDblToFltFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return a -> applyAsFlt(a, factory, newMessage, param1, param2, param3);
	}

	default float applyAsFlt(double a, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyAsFltX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LDblToFltFunction trying(@Nonnull ExWF<RuntimeException> factory) {
		return a -> applyAsFlt(a, factory);
	}

	default float applyAsFltThen(double a, @Nonnull LToFltFunction<Throwable> handler) {
		try {
			return this.applyAsFltX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsFlt(e);
		}
	}

	default LDblToFltFunction tryingThen(@Nonnull LToFltFunction<Throwable> handler) {
		return a -> applyAsFltThen(a, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default float nestingApplyAsFlt(double a) {
		try {
			return this.applyAsFltX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default float shovingApplyAsFlt(double a) {
		try {
			return this.applyAsFltX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static float shovingApplyAsFlt(double a, LDblToFltFunction func) {
		Null.nonNullArg(func, "func");
		return func.shovingApplyAsFlt(a);
	}

	static float handlingApplyAsFlt(double a, LDblToFltFunction func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsFlt(a, handling);
	}

	static float tryApplyAsFlt(double a, LDblToFltFunction func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsFlt(a);
	}

	static float tryApplyAsFlt(double a, LDblToFltFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.applyAsFlt(a, factory, newMessage);
	}

	static float tryApplyAsFlt(double a, LDblToFltFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.applyAsFlt(a, factory, newMessage, param1);
	}

	static float tryApplyAsFlt(double a, LDblToFltFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.applyAsFlt(a, factory, newMessage, param1, param2);
	}

	static float tryApplyAsFlt(double a, LDblToFltFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.applyAsFlt(a, factory, newMessage, param1, param2, param3);
	}

	static float tryApplyAsFlt(double a, LDblToFltFunction func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.applyAsFlt(a, factory);
	}

	static float tryApplyAsFltThen(double a, LDblToFltFunction func, @Nonnull LToFltFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsFltThen(a, handler);
	}

	default float failSafeApplyAsFlt(double a, @Nonnull LDblToFltFunction failSafe) {
		try {
			return applyAsFlt(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.applyAsFlt(a);
		}
	}

	static float failSafeApplyAsFlt(double a, LDblToFltFunction func, @Nonnull LDblToFltFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.applyAsFlt(a);
		} else {
			return func.failSafeApplyAsFlt(a, failSafe);
		}
	}

	static LDblToFltFunction failSafe(LDblToFltFunction func, @Nonnull LDblToFltFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeApplyAsFlt(a, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default float nonNullApplyAsFlt(double a) {
		return applyAsFlt(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LDblToFltFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, double a, @Nonnull LDblToFltFunction func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.applyAsFlt(a);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.applyAsFlt(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, double a, @Nonnull LDblToFltFunction func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.applyAsFlt(a);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.applyAsFlt(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, double a, @Nonnull LDblToFltFunction func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a, func);
	}

	/** Change function to consumer that ignores output. */
	default LDblConsumer toConsumer() {
		return this::applyAsFlt;
	}

	/** Calls domain consumer before main function. */
	default LDblToFltFunction beforeDo(@Nonnull LDblConsumer before) {
		Null.nonNullArg(before, "before");
		return (double a) -> {
			before.accept(a);
			return applyAsFlt(a);
		};
	}

	/** Calls codomain consumer after main function. */
	default LDblToFltFunction afterDo(@Nonnull LFltConsumer after) {
		Null.nonNullArg(after, "after");
		return (double a) -> {
			final float retval = applyAsFlt(a);
			after.accept(retval);
			return retval;
		};
	}

	/** Creates function that always returns the same value. */
	static LDblToFltFunction constant(float r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LDblToFltFunction dblToFltFunc(final @Nonnull LDblToFltFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S implements LDblToFltFunction {
		private LDblToFltFunction target = null;
		@Override
		public float applyAsFltX(double a) throws Throwable {
			return target.applyAsFltX(a);
		}
	}

	@Nonnull
	static LDblToFltFunction recursive(final @Nonnull LFunction<LDblToFltFunction, LDblToFltFunction> selfLambda) {
		final S single = new S();
		LDblToFltFunction func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static M mementoOf(double a, LDblToFltFunction function) {
		var initialValue = function.applyAsFlt(a);
		return initializedMementoOf(initialValue, function);
	}

	public static M initializedMementoOf(float initialValue, LDblToFltFunction function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static M deltaOf(double a, LDblToFltFunction function, LFltBinaryOperator deltaFunction) {
		var initialValue = function.applyAsFlt(a);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static M deltaOf(double a, LDblToFltFunction function) {
		var initialValue = function.applyAsFlt(a);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (x2 - x1));
	}

	public static M initializedDeltaOf(float initialValue, LDblToFltFunction function, LFltBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.applyAsFlt(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.applyAsFlt(x1, x2));
	}

	public static M memento(float initialBaseValue, float initialValue, LDblToFltFunction baseFunction, LFltTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LDblToFltFunction.M)
	 */
	@NotThreadSafe
	final class M implements LDblToFltFunction {

		private final LDblToFltFunction baseFunction;
		private float lastBaseValue;
		private float lastValue;
		private final LFltTernaryOperator mementoFunction;

		private M(float lastBaseValue, float lastValue, LDblToFltFunction baseFunction, LFltTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public float applyAsFltX(double a) throws Throwable {
			float x1 = lastBaseValue;
			float x2 = lastBaseValue = baseFunction.applyAsFltX(a);

			return lastValue = mementoFunction.applyAsFlt(lastValue, x1, x2);
		}

		public float lastValue() {
			return lastValue;
		};

		public float lastBaseValue() {
			return lastBaseValue;
		};
	}

	// </editor-fold>

	@Nonnull
	static LDblToFltFunction dblToFltFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LDblToFltFunction dblToFltFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce(message);
		};
	}

	static float call(double a, final @Nonnull LDblToFltFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsFlt(a);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LDblToFltFunction compose(@Nonnull final LDblUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsFlt(before.applyAsDbl(v));
	}

	public static LDblToFltFunction composed(@Nonnull final LDblUnaryOperator before, LDblToFltFunction after) {
		return after.compose(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToFltFunction<V> dblToFltFuncCompose(@Nonnull final LToDblFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsFlt(before.applyAsDbl(v));
	}

	public static <V> LToFltFunction<V> composed(@Nonnull final LToDblFunction<? super V> before, LDblToFltFunction after) {
		return after.dblToFltFuncCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LDblFunction<V> then(@Nonnull LFltFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.apply(this.applyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToByteFunction thenToByte(@Nonnull LFltToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsByte(this.applyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToSrtFunction thenToSrt(@Nonnull LFltToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsSrt(this.applyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToIntFunction thenToInt(@Nonnull LFltToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsInt(this.applyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToLongFunction thenToLong(@Nonnull LFltToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsLong(this.applyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToFltFunction thenToFlt(@Nonnull LFltUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsFlt(this.applyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblUnaryOperator thenToDbl(@Nonnull LFltToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsDbl(this.applyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToCharFunction thenToChar(@Nonnull LFltToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsChar(this.applyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblPredicate thenToBool(@Nonnull LFltPredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.test(this.applyAsFlt(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LDblToFltFunction) Function */
	public static float doNothing(double a) {
		return Function4U.defaultFloat;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C0> void forEach(IndexedRead<C0, aDouble> ia, C0 source, LFltConsumer consumer) {
		int size = ia.size(source);
		LOiToDblFunction<Object> oiFunc0 = (LOiToDblFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			double a = oiFunc0.applyAsDbl(source, i);
			consumer.accept(this.applyAsFlt(a));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C0, I0> void iterate(SequentialRead<C0, I0, aDouble> sa, C0 source, LFltConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).apply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToDblFunction<Object> nextFunc0 = (LToDblFunction) sa.supplier();
		while (testFunc0.test(iterator0)) {
			double a = nextFunc0.applyAsDbl(iterator0);
			consumer.accept(this.applyAsFlt(a));
		}
	}

}
