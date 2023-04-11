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

package eu.lunisolar.magma.func.operator.ternary;

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
 * Non-throwing functional interface (lambda) LFltTernaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 3): float a1,float a2,float a3
 *
 * Co-domain: float
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LFltTernaryOperator extends MetaOperator, MetaInterface.NonThrowing, Codomain<aFloat>, Domain3<aFloat, aFloat, aFloat> { // NOSONAR

	String DESCRIPTION = "LFltTernaryOperator: float applyAsFlt(float a1,float a2,float a3)";

	// float applyAsFlt(float a1,float a2,float a3) ;
	default float applyAsFlt(float a1, float a2, float a3) {
		// return nestingApplyAsFlt(a1,a2,a3);
		try {
			return this.applyAsFltX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsFlt(float a1,float a2,float a3)
	 */
	float applyAsFltX(float a1, float a2, float a3) throws Throwable;

	default float tupleApplyAsFlt(LFltTriple args) {
		return applyAsFlt(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions according to the instructions. */
	default float handlingApplyAsFlt(float a1, float a2, float a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsFltX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LFltTernaryOperator handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> handlingApplyAsFlt(a1, a2, a3, handling);
	}

	default float applyAsFlt(float a1, float a2, float a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyAsFltX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default float applyAsFlt(float a1, float a2, float a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyAsFltX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default float applyAsFlt(float a1, float a2, float a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyAsFltX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default float applyAsFlt(float a1, float a2, float a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyAsFltX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LFltTernaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2, a3) -> applyAsFlt(a1, a2, a3, factory, newMessage);
	}

	default LFltTernaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2, a3) -> applyAsFlt(a1, a2, a3, factory, newMessage, param1);
	}

	default LFltTernaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2, a3) -> applyAsFlt(a1, a2, a3, factory, newMessage, param1, param1);
	}

	default LFltTernaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2, a3) -> applyAsFlt(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	default float applyAsFlt(float a1, float a2, float a3, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyAsFltX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LFltTernaryOperator trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2, a3) -> applyAsFlt(a1, a2, a3, factory);
	}

	default float applyAsFltThen(float a1, float a2, float a3, @Nonnull LToFltFunction<Throwable> handler) {
		try {
			return this.applyAsFltX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsFlt(e);
		}
	}

	default LFltTernaryOperator tryingThen(@Nonnull LToFltFunction<Throwable> handler) {
		return (a1, a2, a3) -> applyAsFltThen(a1, a2, a3, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default float nestingApplyAsFlt(float a1, float a2, float a3) {
		try {
			return this.applyAsFltX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default float shovingApplyAsFlt(float a1, float a2, float a3) {
		try {
			return this.applyAsFltX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static float shovingApplyAsFlt(float a1, float a2, float a3, LFltTernaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.shovingApplyAsFlt(a1, a2, a3);
	}

	static float handlingApplyAsFlt(float a1, float a2, float a3, LFltTernaryOperator func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsFlt(a1, a2, a3, handling);
	}

	static float tryApplyAsFlt(float a1, float a2, float a3, LFltTernaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsFlt(a1, a2, a3);
	}

	static float tryApplyAsFlt(float a1, float a2, float a3, LFltTernaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.applyAsFlt(a1, a2, a3, factory, newMessage);
	}

	static float tryApplyAsFlt(float a1, float a2, float a3, LFltTernaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.applyAsFlt(a1, a2, a3, factory, newMessage, param1);
	}

	static float tryApplyAsFlt(float a1, float a2, float a3, LFltTernaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.applyAsFlt(a1, a2, a3, factory, newMessage, param1, param2);
	}

	static float tryApplyAsFlt(float a1, float a2, float a3, LFltTernaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.applyAsFlt(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	static float tryApplyAsFlt(float a1, float a2, float a3, LFltTernaryOperator func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.applyAsFlt(a1, a2, a3, factory);
	}

	static float tryApplyAsFltThen(float a1, float a2, float a3, LFltTernaryOperator func, @Nonnull LToFltFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsFltThen(a1, a2, a3, handler);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default float nonNullApplyAsFlt(float a1, float a2, float a3) {
		return applyAsFlt(a1, a2, a3);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LFltTernaryOperator.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, float a1, float a2, float a3, @Nonnull LFltTernaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.applyAsFlt(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.applyAsFlt(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, float a1, float a2, float a3, @Nonnull LFltTernaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.applyAsFlt(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.applyAsFlt(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, float a1, float a2, float a3, @Nonnull LFltTernaryOperator func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, a3, func);
	}

	/**  */
	public static LFltTernaryOperator uncurry(@Nonnull LFltFunction<LFltFunction<LFltUnaryOperator>> func) {
		Null.nonNullArg(func, "func");
		return (float a1, float a2, float a3) -> func.apply(a1).apply(a2).applyAsFlt(a3);
	}

	/** Change function to consumer that ignores output. */
	default LTriFltConsumer toConsumer() {
		return this::applyAsFlt;
	}

	/** Calls domain consumer before main function. */
	default LFltTernaryOperator beforeDo(@Nonnull LTriFltConsumer before) {
		Null.nonNullArg(before, "before");
		return (float a1, float a2, float a3) -> {
			before.accept(a1, a2, a3);
			return applyAsFlt(a1, a2, a3);
		};
	}

	/** Calls codomain consumer after main function. */
	default LFltTernaryOperator afterDo(@Nonnull LFltConsumer after) {
		Null.nonNullArg(after, "after");
		return (float a1, float a2, float a3) -> {
			final float retval = applyAsFlt(a1, a2, a3);
			after.accept(retval);
			return retval;
		};
	}

	/** Creates function that always returns the same value. */
	static LFltTernaryOperator constant(float r) {
		return (a1, a2, a3) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LFltTernaryOperator fltTernaryOp(final @Nonnull LFltTernaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S implements LFltTernaryOperator {
		private LFltTernaryOperator target = null;
		@Override
		public float applyAsFltX(float a1, float a2, float a3) throws Throwable {
			return target.applyAsFltX(a1, a2, a3);
		}
	}

	@Nonnull
	static LFltTernaryOperator recursive(final @Nonnull LFunction<LFltTernaryOperator, LFltTernaryOperator> selfLambda) {
		final S single = new S();
		LFltTernaryOperator func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static M mementoOf(float a1, float a2, float a3, LFltTernaryOperator function) {
		var initialValue = function.applyAsFlt(a1, a2, a3);
		return initializedMementoOf(initialValue, function);
	}

	public static M initializedMementoOf(float initialValue, LFltTernaryOperator function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static M deltaOf(float a1, float a2, float a3, LFltTernaryOperator function, LFltBinaryOperator deltaFunction) {
		var initialValue = function.applyAsFlt(a1, a2, a3);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static M deltaOf(float a1, float a2, float a3, LFltTernaryOperator function) {
		var initialValue = function.applyAsFlt(a1, a2, a3);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (x2 - x1));
	}

	public static M initializedDeltaOf(float initialValue, LFltTernaryOperator function, LFltBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.applyAsFlt(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.applyAsFlt(x1, x2));
	}

	public static M memento(float initialBaseValue, float initialValue, LFltTernaryOperator baseFunction, LFltTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LFltTernaryOperator.M)
	 */
	@NotThreadSafe
	final class M implements LFltTernaryOperator {

		private final LFltTernaryOperator baseFunction;
		private float lastBaseValue;
		private float lastValue;
		private final LFltTernaryOperator mementoFunction;

		private M(float lastBaseValue, float lastValue, LFltTernaryOperator baseFunction, LFltTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public float applyAsFltX(float a1, float a2, float a3) throws Throwable {
			float x1 = lastBaseValue;
			float x2 = lastBaseValue = baseFunction.applyAsFltX(a1, a2, a3);

			return lastValue = mementoFunction.applyAsFltX(lastValue, x1, x2);
		}

		public float currentApplyAsFlt(float a1, float a2, float a3) {
			float x1 = lastBaseValue;
			float x2 = baseFunction.applyAsFlt(a1, a2, a3);

			return mementoFunction.applyAsFlt(lastValue, x1, x2);
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
	static LFltTernaryOperator fltTernaryOpThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LFltTernaryOperator fltTernaryOpThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce(message);
		};
	}

	static float call(float a1, float a2, float a3, final @Nonnull LFltTernaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsFlt(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LFltTernaryOperator compose(@Nonnull final LFltUnaryOperator before1, @Nonnull final LFltUnaryOperator before2, @Nonnull final LFltUnaryOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.applyAsFlt(before1.applyAsFlt(v1), before2.applyAsFlt(v2), before3.applyAsFlt(v3));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LTriFltFunction<V> then(@Nonnull LFltFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.apply(this.applyAsFlt(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltTernaryOperator thenToFlt(@Nonnull LFltUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.applyAsFlt(this.applyAsFlt(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LTriFltPredicate thenToBool(@Nonnull LFltPredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.test(this.applyAsFlt(a1, a2, a3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LFltTernaryOperator) Operator */
	public static float doNothing(float a1, float a2, float a3) {
		return Function4U.defaultFloat;
	}

}
