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

package eu.lunisolar.magma.func.operator.binary;

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
 * Non-throwing functional interface (lambda) LFltBinaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 2): float a1,float a2
 *
 * Co-domain: float
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LFltBinaryOperator extends MetaOperator, MetaInterface.NonThrowing, Codomain<aFloat>, Domain2<aFloat, aFloat> { // NOSONAR

	String DESCRIPTION = "LFltBinaryOperator: float applyAsFlt(float a1,float a2)";

	// float applyAsFlt(float a1,float a2) ;
	default float applyAsFlt(float a1, float a2) {
		// return nestingApplyAsFlt(a1,a2);
		try {
			return this.applyAsFltX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsFlt(float a1,float a2)
	 */
	float applyAsFltX(float a1, float a2) throws Throwable;

	default float tupleApplyAsFlt(LFltPair args) {
		return applyAsFlt(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default float handlingApplyAsFlt(float a1, float a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsFltX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LFltBinaryOperator handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2) -> handlingApplyAsFlt(a1, a2, handling);
	}

	default float applyAsFlt(float a1, float a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyAsFltX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default float applyAsFlt(float a1, float a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyAsFltX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default float applyAsFlt(float a1, float a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyAsFltX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default float applyAsFlt(float a1, float a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyAsFltX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LFltBinaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2) -> applyAsFlt(a1, a2, factory, newMessage);
	}

	default LFltBinaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2) -> applyAsFlt(a1, a2, factory, newMessage, param1);
	}

	default LFltBinaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2) -> applyAsFlt(a1, a2, factory, newMessage, param1, param1);
	}

	default LFltBinaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2) -> applyAsFlt(a1, a2, factory, newMessage, param1, param2, param3);
	}

	default float applyAsFlt(float a1, float a2, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyAsFltX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LFltBinaryOperator trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2) -> applyAsFlt(a1, a2, factory);
	}

	default float applyAsFltThen(float a1, float a2, @Nonnull LToFltFunction<Throwable> handler) {
		try {
			return this.applyAsFltX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsFlt(e);
		}
	}

	default LFltBinaryOperator tryingThen(@Nonnull LToFltFunction<Throwable> handler) {
		return (a1, a2) -> applyAsFltThen(a1, a2, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default float nestingApplyAsFlt(float a1, float a2) {
		try {
			return this.applyAsFltX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default float shovingApplyAsFlt(float a1, float a2) {
		try {
			return this.applyAsFltX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static float shovingApplyAsFlt(float a1, float a2, LFltBinaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.shovingApplyAsFlt(a1, a2);
	}

	static float handlingApplyAsFlt(float a1, float a2, LFltBinaryOperator func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsFlt(a1, a2, handling);
	}

	static float tryApplyAsFlt(float a1, float a2, LFltBinaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsFlt(a1, a2);
	}

	static float tryApplyAsFlt(float a1, float a2, LFltBinaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.applyAsFlt(a1, a2, factory, newMessage);
	}

	static float tryApplyAsFlt(float a1, float a2, LFltBinaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.applyAsFlt(a1, a2, factory, newMessage, param1);
	}

	static float tryApplyAsFlt(float a1, float a2, LFltBinaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.applyAsFlt(a1, a2, factory, newMessage, param1, param2);
	}

	static float tryApplyAsFlt(float a1, float a2, LFltBinaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.applyAsFlt(a1, a2, factory, newMessage, param1, param2, param3);
	}

	static float tryApplyAsFlt(float a1, float a2, LFltBinaryOperator func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.applyAsFlt(a1, a2, factory);
	}

	static float tryApplyAsFltThen(float a1, float a2, LFltBinaryOperator func, @Nonnull LToFltFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsFltThen(a1, a2, handler);
	}

	default float failSafeApplyAsFlt(float a1, float a2, @Nonnull LFltBinaryOperator failSafe) {
		try {
			return applyAsFlt(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.applyAsFlt(a1, a2);
		}
	}

	static float failSafeApplyAsFlt(float a1, float a2, LFltBinaryOperator func, @Nonnull LFltBinaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.applyAsFlt(a1, a2);
		} else {
			return func.failSafeApplyAsFlt(a1, a2, failSafe);
		}
	}

	static LFltBinaryOperator failSafe(LFltBinaryOperator func, @Nonnull LFltBinaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeApplyAsFlt(a1, a2, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default float nonNullApplyAsFlt(float a1, float a2) {
		return applyAsFlt(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LFltBinaryOperator.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, float a1, float a2, @Nonnull LFltBinaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.applyAsFlt(a1, a2);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.applyAsFlt(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, float a1, float a2, @Nonnull LFltBinaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.applyAsFlt(a1, a2);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.applyAsFlt(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, float a1, float a2, @Nonnull LFltBinaryOperator func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, func);
	}

	/**  */
	public static LFltBinaryOperator uncurry(@Nonnull LFltFunction<LFltUnaryOperator> func) {
		Null.nonNullArg(func, "func");
		return (float a1, float a2) -> func.apply(a1).applyAsFlt(a2);
	}

	/** Change function to consumer that ignores output. */
	default LBiFltConsumer toConsumer() {
		return this::applyAsFlt;
	}

	/** Calls domain consumer before main function. */
	default LFltBinaryOperator beforeDo(@Nonnull LBiFltConsumer before) {
		Null.nonNullArg(before, "before");
		return (float a1, float a2) -> {
			before.accept(a1, a2);
			return applyAsFlt(a1, a2);
		};
	}

	/** Calls codomain consumer after main function. */
	default LFltBinaryOperator afterDo(@Nonnull LFltConsumer after) {
		Null.nonNullArg(after, "after");
		return (float a1, float a2) -> {
			final float retval = applyAsFlt(a1, a2);
			after.accept(retval);
			return retval;
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LFltSupplier capture(float a1, float a2) {
		return () -> this.applyAsFlt(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static LFltBinaryOperator constant(float r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static LFltBinaryOperator apply1stAsFlt(@Nonnull LFltUnaryOperator func) {
		return (a1, a2) -> func.applyAsFlt(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static LFltBinaryOperator apply2ndAsFlt(@Nonnull LFltUnaryOperator func) {
		return (a1, a2) -> func.applyAsFlt(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LFltBinaryOperator fltBinaryOp(final @Nonnull LFltBinaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	final class S implements LFltBinaryOperator {
		private LFltBinaryOperator target = null;
		@Override
		public float applyAsFltX(float a1, float a2) throws Throwable {
			return target.applyAsFltX(a1, a2);
		}
	}

	@Nonnull
	static LFltBinaryOperator recursive(final @Nonnull LFunction<LFltBinaryOperator, LFltBinaryOperator> selfLambda) {
		final S single = new S();
		LFltBinaryOperator func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	public static M mementoOf(float a1, float a2, LFltBinaryOperator function) {
		var initialValue = function.applyAsFlt(a1, a2);
		return initializedMementoOf(initialValue, function);
	}

	public static M initializedMementoOf(float initialValue, LFltBinaryOperator function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static M deltaOf(float a1, float a2, LFltBinaryOperator function, LFltBinaryOperator deltaFunction) {
		var initialValue = function.applyAsFlt(a1, a2);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static M deltaOf(float a1, float a2, LFltBinaryOperator function) {
		var initialValue = function.applyAsFlt(a1, a2);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (x2 - x1));
	}

	public static M initializedDeltaOf(float initialValue, LFltBinaryOperator function, LFltBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.applyAsFlt(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.applyAsFlt(x1, x2));
	}

	public static M memento(float initialBaseValue, float initialValue, LFltBinaryOperator baseFunction, LFltTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LFltBinaryOperator.M)
	 */
	@NotThreadSafe
	final class M implements LFltBinaryOperator {

		private final LFltBinaryOperator baseFunction;
		private float lastBaseValue;
		private float lastValue;
		private final LFltTernaryOperator mementoFunction;

		private M(float lastBaseValue, float lastValue, LFltBinaryOperator baseFunction, LFltTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public float applyAsFltX(float a1, float a2) throws Throwable {
			float x1 = lastBaseValue;
			float x2 = lastBaseValue = baseFunction.applyAsFltX(a1, a2);

			return lastValue = mementoFunction.applyAsFlt(lastValue, x1, x2);
		}

		public float lastValue() {
			return lastValue;
		};

		public float lastBaseValue() {
			return lastBaseValue;
		};
	}

	@Nonnull
	static LFltBinaryOperator fltBinaryOpThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LFltBinaryOperator fltBinaryOpThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce(message);
		};
	}

	static float call(float a1, float a2, final @Nonnull LFltBinaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsFlt(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	/**
	 * Creates function that returns the lesser value according to the comparator.
	 * @see {@link java.util.function.BinaryOperator#minBy}
	 */
	@Nonnull
	static LFltBinaryOperator minBy(@Nonnull Comparator<Float> comparator) {
		Null.nonNullArg(comparator, "comparator");
		return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
	}

	/**
	 * Creates function that returns the lesser value according to the comparator.
	 * @see {@link java.util.function.BinaryOperator#maxBy}
	 */
	@Nonnull
	static LFltBinaryOperator maxBy(@Nonnull Comparator<Float> comparator) {
		Null.nonNullArg(comparator, "comparator");
		return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
	}

	/**
	 * Returns function that returns the lower value.
	 * @see {@link java.util.function.BinaryOperator#minBy}
	 */
	@Nonnull
	static LFltBinaryOperator min() {
		return Float::min;
	}

	/**
	 * Returns function that returns the higher value.
	 * @see {@link java.util.function.BinaryOperator#maxBy}
	 */
	@Nonnull
	static LFltBinaryOperator max() {
		return Float::max;
	}

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LFltBinaryOperator compose(@Nonnull final LFltUnaryOperator before1, @Nonnull final LFltUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.applyAsFlt(before1.applyAsFlt(v1), before2.applyAsFlt(v2));
	}

	public static LFltBinaryOperator composed(@Nonnull final LFltUnaryOperator before1, @Nonnull final LFltUnaryOperator before2, LFltBinaryOperator after) {
		return after.compose(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LToFltBiFunction<V1, V2> fltBinaryOpCompose(@Nonnull final LToFltFunction<? super V1> before1, @Nonnull final LToFltFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.applyAsFlt(before1.applyAsFlt(v1), before2.applyAsFlt(v2));
	}

	public static <V1, V2> LToFltBiFunction<V1, V2> composed(@Nonnull final LToFltFunction<? super V1> before1, @Nonnull final LToFltFunction<? super V2> before2, LFltBinaryOperator after) {
		return after.fltBinaryOpCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiFltFunction<V> then(@Nonnull LFltFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.apply(this.applyAsFlt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltBinaryOperator thenToFlt(@Nonnull LFltUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsFlt(this.applyAsFlt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiFltPredicate thenToBool(@Nonnull LFltPredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.test(this.applyAsFlt(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LFltBinaryOperator) Operator */
	public static float doNothing(float a1, float a2) {
		return Function4U.defaultFloat;
	}

}
