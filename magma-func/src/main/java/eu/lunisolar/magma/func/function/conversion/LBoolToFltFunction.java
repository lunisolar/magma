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
 * Non-throwing functional interface (lambda) LBoolToFltFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): boolean a
 *
 * Co-domain: float
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBoolToFltFunction extends MetaFunction, MetaInterface.NonThrowing, Codomain<aFloat>, Domain1<aBool> { //NOSONAR

	String DESCRIPTION = "LBoolToFltFunction: float applyAsFlt(boolean a)";

	default float applyAsFlt(boolean a) {
		try {
			return this.applyAsFltX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsFlt(boolean a)
	 */
	float applyAsFltX(boolean a) throws Throwable;

	default float tupleApplyAsFlt(LBoolSingle args) {
		return applyAsFlt(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default float handlingApplyAsFlt(boolean a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsFltX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LBoolToFltFunction handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> handlingApplyAsFlt(a, handling);
	}

	default float applyAsFlt(boolean a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyAsFltX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default float applyAsFlt(boolean a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyAsFltX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default float applyAsFlt(boolean a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyAsFltX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default float applyAsFlt(boolean a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyAsFltX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LBoolToFltFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return a -> applyAsFlt(a, factory, newMessage);
	}

	default LBoolToFltFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return a -> applyAsFlt(a, factory, newMessage, param1);
	}

	default LBoolToFltFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return a -> applyAsFlt(a, factory, newMessage, param1, param1);
	}

	default LBoolToFltFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return a -> applyAsFlt(a, factory, newMessage, param1, param2, param3);
	}

	default float applyAsFlt(boolean a, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyAsFltX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LBoolToFltFunction trying(@Nonnull ExWF<RuntimeException> factory) {
		return a -> applyAsFlt(a, factory);
	}

	default float applyAsFltThen(boolean a, @Nonnull LToFltFunction<Throwable> handler) {
		try {
			return this.applyAsFltX(a);
		} catch (Throwable e) { //NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsFlt(e);
		}
	}

	default LBoolToFltFunction tryingThen(@Nonnull LToFltFunction<Throwable> handler) {
		return a -> applyAsFltThen(a, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default float nestingApplyAsFlt(boolean a) {
		try {
			return this.applyAsFltX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default float shovingApplyAsFlt(boolean a) {
		try {
			return this.applyAsFltX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static float shovingApplyAsFlt(boolean a, LBoolToFltFunction func) {
		Null.nonNullArg(func, "func");
		return func.shovingApplyAsFlt(a);
	}

	static float handlingApplyAsFlt(boolean a, LBoolToFltFunction func, HandlingInstructions<Throwable, RuntimeException> handling) { //<-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsFlt(a, handling);
	}

	static float tryApplyAsFlt(boolean a, LBoolToFltFunction func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsFlt(a);
	}

	static float tryApplyAsFlt(boolean a, LBoolToFltFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.applyAsFlt(a, factory, newMessage);
	}

	static float tryApplyAsFlt(boolean a, LBoolToFltFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.applyAsFlt(a, factory, newMessage, param1);
	}

	static float tryApplyAsFlt(boolean a, LBoolToFltFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.applyAsFlt(a, factory, newMessage, param1, param2);
	}

	static float tryApplyAsFlt(boolean a, LBoolToFltFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.applyAsFlt(a, factory, newMessage, param1, param2, param3);
	}

	static float tryApplyAsFlt(boolean a, LBoolToFltFunction func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.applyAsFlt(a, factory);
	}

	static float tryApplyAsFltThen(boolean a, LBoolToFltFunction func, @Nonnull LToFltFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsFltThen(a, handler);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default float nonNullApplyAsFlt(boolean a) {
		return applyAsFlt(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBoolToFltFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, boolean a, @Nonnull LBoolToFltFunction func) {
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
	public static void fromTill(int min_i, int max_i, boolean a, @Nonnull LBoolToFltFunction func) {
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
	public static void times(int max_i, boolean a, @Nonnull LBoolToFltFunction func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a, func);
	}

	/** Change function to one that ignores output. */
	default LBoolConsumer toConsumer() {
		return this::applyAsFlt;
	}

	/** Calls domain consumer before main function. */
	default LBoolToFltFunction beforeDo(@Nonnull LBoolConsumer before) {
		Null.nonNullArg(before, "before");
		return (boolean a) -> {
			before.accept(a);
			return applyAsFlt(a);
		};
	}

	/** Calls codomain consumer after main function. */
	default LBoolToFltFunction afterDo(@Nonnull LFltConsumer after) {
		Null.nonNullArg(after, "after");
		return (boolean a) -> {
			final float retval = applyAsFlt(a);
			after.accept(retval);
			return retval;
		};
	}

	/** Creates function that always returns the same value. */
	static LBoolToFltFunction constant(float r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LBoolToFltFunction boolToFltFunc(final @Nonnull LBoolToFltFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S implements LBoolToFltFunction {
		private LBoolToFltFunction target = null;
		@Override
		public float applyAsFltX(boolean a) throws Throwable {
			return target.applyAsFltX(a);
		}
	}

	@Nonnull
	static LBoolToFltFunction recursive(final @Nonnull LFunction<LBoolToFltFunction, LBoolToFltFunction> selfLambda) {
		final S single = new S();
		LBoolToFltFunction func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static M mementoOf(boolean a, LBoolToFltFunction function) {
		var initialValue = function.applyAsFlt(a);
		return initializedMementoOf(initialValue, function);
	}

	public static M initializedMementoOf(float initialValue, LBoolToFltFunction function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static M deltaOf(boolean a, LBoolToFltFunction function, LFltBinaryOperator deltaFunction) {
		var initialValue = function.applyAsFlt(a);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static M deltaOf(boolean a, LBoolToFltFunction function) {
		var initialValue = function.applyAsFlt(a);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (x2 - x1));
	}

	public static M initializedDeltaOf(float initialValue, LBoolToFltFunction function, LFltBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.applyAsFlt(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.applyAsFlt(x1, x2));
	}

	public static M memento(float initialBaseValue, float initialValue, LBoolToFltFunction baseFunction, LFltTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LBoolToFltFunction.M)
	 */
	@NotThreadSafe
	final class M implements LBoolToFltFunction {

		private final LBoolToFltFunction baseFunction;
		private float lastBaseValue;
		private float lastValue;
		private final LFltTernaryOperator mementoFunction;

		private M(float lastBaseValue, float lastValue, LBoolToFltFunction baseFunction, LFltTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public float applyAsFltX(boolean a) throws Throwable {
			float x1 = lastBaseValue;
			float x2 = lastBaseValue = baseFunction.applyAsFltX(a);

			return lastValue = mementoFunction.applyAsFltX(lastValue, x1, x2);
		}

		public float currentApplyAsFlt(boolean a) {
			float x1 = lastBaseValue;
			float x2 = baseFunction.applyAsFlt(a);

			return mementoFunction.applyAsFlt(lastValue, x1, x2);
		}

		public float lastValue() {
			return lastValue;
		}

		public float lastBaseValue() {
			return lastBaseValue;
		}

		public float currentBaseValue(boolean a) {
			return baseFunction.applyAsFlt(a);
		}
	}

	// </editor-fold>

	@Nonnull
	static LBoolToFltFunction boolToFltFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LBoolToFltFunction boolToFltFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce(message);
		};
	}

	static float call(boolean a, final @Nonnull LBoolToFltFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsFlt(a);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LBoolToFltFunction compose(@Nonnull final LLogicalOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsFlt(before.apply(v));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToFltFunction<V> unboxingCompose(@Nonnull final LPredicate<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsFlt(before.test(v));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBoolFunction<V> then(@Nonnull LFltFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.apply(this.applyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToByteFunction thenToByte(@Nonnull LFltToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsByte(this.applyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToSrtFunction thenToSrt(@Nonnull LFltToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsSrt(this.applyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToIntFunction thenToInt(@Nonnull LFltToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsInt(this.applyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToLongFunction thenToLong(@Nonnull LFltToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsLong(this.applyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToFltFunction thenToFlt(@Nonnull LFltUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsFlt(this.applyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToDblFunction thenToDbl(@Nonnull LFltToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsDbl(this.applyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToCharFunction thenToChar(@Nonnull LFltToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsChar(this.applyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLogicalOperator thenToBool(@Nonnull LFltPredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.test(this.applyAsFlt(a));
	}

	// </editor-fold>

	default LBoolToFltFunction shoving() {

		return new LBoolToFltFunction() {

			public float applyAsFlt(boolean a) {
				try {
					return this.applyAsFltX(a);
				} catch (Throwable e) { // NOSONAR
					Handling.handleErrors(e);
					throw Handling.throwIt(e);
				}
			}

			public float applyAsFltX(boolean a) throws Throwable {
				return LBoolToFltFunction.this.applyAsFltX(a);
			}

		};
	}

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LBoolToFltFunction) Function */
	public static float doNothing(boolean a) {
		return Function4U.defaultFloat;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C0> void forEach(IndexedRead<C0, aBool> ia, C0 source, LFltConsumer consumer) {
		int size = ia.size(source);
		var oiFunc0 = IA.boolGetter(ia);
		int i = 0;
		for (; i < size; i++) {
			boolean a = oiFunc0.test(source, i);
			consumer.accept(this.applyAsFlt(a));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C0, I0> void iterate(SequentialRead<C0, I0, aBool> sa, C0 source, LFltConsumer consumer) {
		var iterator0 = SA.adapter(sa).apply(source);
		var testFunc0 = SA.tester(sa);
		var nextFunc0 = SA.boolSupplier(sa);
		while (testFunc0.test(iterator0)) {
			boolean a = nextFunc0.test(iterator0);
			consumer.accept(this.applyAsFlt(a));
		}
	}

}
