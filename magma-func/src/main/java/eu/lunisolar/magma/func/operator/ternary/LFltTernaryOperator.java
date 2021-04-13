/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2019 Lunisolar (http://lunisolar.eu/).
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
import java.util.function.*; // NOSONAR
import java.util.*; // NOSONAR
import java.lang.reflect.*;

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

	default float failSafeApplyAsFlt(float a1, float a2, float a3, @Nonnull LFltTernaryOperator failSafe) {
		try {
			return applyAsFlt(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.applyAsFlt(a1, a2, a3);
		}
	}

	static float failSafeApplyAsFlt(float a1, float a2, float a3, LFltTernaryOperator func, @Nonnull LFltTernaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.applyAsFlt(a1, a2, a3);
		} else {
			return func.failSafeApplyAsFlt(a1, a2, a3, failSafe);
		}
	}

	static LFltTernaryOperator failSafe(LFltTernaryOperator func, @Nonnull LFltTernaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeApplyAsFlt(a1, a2, a3, func, failSafe);
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

	default LFltBinaryOperator lShrink(@Nonnull LFltBinaryOperator left) {
		Null.nonNullArg(left, "left");
		return (a2, a3) -> applyAsFlt(left.applyAsFlt(a2, a3), a2, a3);
	}

	default LFltBinaryOperator lShrink_(float a1) {
		return (a2, a3) -> applyAsFlt(a1, a2, a3);
	}

	public static LFltBinaryOperator lShrunken(@Nonnull LFltBinaryOperator left, @Nonnull LFltTernaryOperator func) {
		Null.nonNullArg(left, "left");
		Null.nonNullArg(func, "func");
		return func.lShrink(left);
	}

	public static LFltBinaryOperator lShrunken_(float a1, @Nonnull LFltTernaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.lShrink_(a1);
	}

	default LFltBinaryOperator rShrink(@Nonnull LFltBinaryOperator right) {
		Null.nonNullArg(right, "right");
		return (a1, a2) -> applyAsFlt(a1, a2, right.applyAsFlt(a1, a2));
	}

	default LFltBinaryOperator rShrink_(float a3) {
		return (a1, a2) -> applyAsFlt(a1, a2, a3);
	}

	public static LFltBinaryOperator rShrunken(@Nonnull LFltBinaryOperator right, @Nonnull LFltTernaryOperator func) {
		Null.nonNullArg(right, "right");
		Null.nonNullArg(func, "func");
		return func.rShrink(right);
	}

	public static LFltBinaryOperator rShrunken_(float a3, @Nonnull LFltTernaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.rShrink_(a3);
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

	/** Captures arguments but delays the evaluation. */
	default LFltSupplier capture(float a1, float a2, float a3) {
		return () -> this.applyAsFlt(a1, a2, a3);
	}

	/** Creates function that always returns the same value. */
	static LFltTernaryOperator constant(float r) {
		return (a1, a2, a3) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static LFltTernaryOperator apply1stAsFlt(@Nonnull LFltUnaryOperator func) {
		return (a1, a2, a3) -> func.applyAsFlt(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static LFltTernaryOperator apply2ndAsFlt(@Nonnull LFltUnaryOperator func) {
		return (a1, a2, a3) -> func.applyAsFlt(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static LFltTernaryOperator apply3rdAsFlt(@Nonnull LFltUnaryOperator func) {
		return (a1, a2, a3) -> func.applyAsFlt(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LFltTernaryOperator fltTernaryOp(final @Nonnull LFltTernaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

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

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as doNothing). */
	@Nonnull
	static LFltTernaryOperator safe() {
		return LFltTernaryOperator::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LFltTernaryOperator> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LFltTernaryOperator safe(final @Nullable LFltTernaryOperator other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LFltTernaryOperator> safeSupplier(final @Nullable LSupplier<LFltTernaryOperator> supplier) {
		if (supplier == null) {
			return safeSupplier();
		} else {
			return supplier;
		}
	}

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

	public static LFltTernaryOperator composed(@Nonnull final LFltUnaryOperator before1, @Nonnull final LFltUnaryOperator before2, @Nonnull final LFltUnaryOperator before3, LFltTernaryOperator after) {
		return after.compose(before1, before2, before3);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

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

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, C3> void forEach(IndexedRead<C1, aFloat> ia1, C1 source1, IndexedRead<C2, aFloat> ia2, C2 source2, IndexedRead<C3, aFloat> ia3, C3 source3, LFltConsumer consumer) {
		int size = ia1.size(source1);
		LOiToFltFunction<Object> oiFunc1 = (LOiToFltFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToFltFunction<Object> oiFunc2 = (LOiToFltFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToFltFunction<Object> oiFunc3 = (LOiToFltFunction) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			float a1 = oiFunc1.applyAsFlt(source1, i);
			float a2 = oiFunc2.applyAsFlt(source2, i);
			float a3 = oiFunc3.applyAsFlt(source3, i);
			consumer.accept(this.applyAsFlt(a1, a2, a3));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, C3> void iterate(SequentialRead<C1, I1, aFloat> sa1, C1 source1, IndexedRead<C2, aFloat> ia2, C2 source2, IndexedRead<C3, aFloat> ia3, C3 source3, LFltConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToFltFunction<Object> nextFunc1 = (LToFltFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToFltFunction<Object> oiFunc2 = (LOiToFltFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToFltFunction<Object> oiFunc3 = (LOiToFltFunction) ia3.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			float a1 = nextFunc1.applyAsFlt(iterator1);
			float a2 = oiFunc2.applyAsFlt(source2, i);
			float a3 = oiFunc3.applyAsFlt(source3, i);
			consumer.accept(this.applyAsFlt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2, C3> void iterate(IndexedRead<C1, aFloat> ia1, C1 source1, SequentialRead<C2, I2, aFloat> sa2, C2 source2, IndexedRead<C3, aFloat> ia3, C3 source3, LFltConsumer consumer) {
		int size = ia1.size(source1);
		LOiToFltFunction<Object> oiFunc1 = (LOiToFltFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToFltFunction<Object> nextFunc2 = (LToFltFunction) sa2.supplier();
		size = Integer.min(size, ia3.size(source3));
		LOiToFltFunction<Object> oiFunc3 = (LOiToFltFunction) ia3.getter();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			float a1 = oiFunc1.applyAsFlt(source1, i);
			float a2 = nextFunc2.applyAsFlt(iterator2);
			float a3 = oiFunc3.applyAsFlt(source3, i);
			consumer.accept(this.applyAsFlt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, I2, C3> void iterate(SequentialRead<C1, I1, aFloat> sa1, C1 source1, SequentialRead<C2, I2, aFloat> sa2, C2 source2, IndexedRead<C3, aFloat> ia3, C3 source3, LFltConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToFltFunction<Object> nextFunc1 = (LToFltFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToFltFunction<Object> nextFunc2 = (LToFltFunction) sa2.supplier();
		int size = ia3.size(source3);
		LOiToFltFunction<Object> oiFunc3 = (LOiToFltFunction) ia3.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && i < size) {
			float a1 = nextFunc1.applyAsFlt(iterator1);
			float a2 = nextFunc2.applyAsFlt(iterator2);
			float a3 = oiFunc3.applyAsFlt(source3, i);
			consumer.accept(this.applyAsFlt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, C3, I3> void iterate(IndexedRead<C1, aFloat> ia1, C1 source1, IndexedRead<C2, aFloat> ia2, C2 source2, SequentialRead<C3, I3, aFloat> sa3, C3 source3, LFltConsumer consumer) {
		int size = ia1.size(source1);
		LOiToFltFunction<Object> oiFunc1 = (LOiToFltFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToFltFunction<Object> oiFunc2 = (LOiToFltFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToFltFunction<Object> nextFunc3 = (LToFltFunction) sa3.supplier();
		int i = 0;
		while (i < size && testFunc3.test(iterator3)) {
			float a1 = oiFunc1.applyAsFlt(source1, i);
			float a2 = oiFunc2.applyAsFlt(source2, i);
			float a3 = nextFunc3.applyAsFlt(iterator3);
			consumer.accept(this.applyAsFlt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, C3, I3> void iterate(SequentialRead<C1, I1, aFloat> sa1, C1 source1, IndexedRead<C2, aFloat> ia2, C2 source2, SequentialRead<C3, I3, aFloat> sa3, C3 source3, LFltConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToFltFunction<Object> nextFunc1 = (LToFltFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToFltFunction<Object> oiFunc2 = (LOiToFltFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToFltFunction<Object> nextFunc3 = (LToFltFunction) sa3.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size && testFunc3.test(iterator3)) {
			float a1 = nextFunc1.applyAsFlt(iterator1);
			float a2 = oiFunc2.applyAsFlt(source2, i);
			float a3 = nextFunc3.applyAsFlt(iterator3);
			consumer.accept(this.applyAsFlt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2, C3, I3> void iterate(IndexedRead<C1, aFloat> ia1, C1 source1, SequentialRead<C2, I2, aFloat> sa2, C2 source2, SequentialRead<C3, I3, aFloat> sa3, C3 source3, LFltConsumer consumer) {
		int size = ia1.size(source1);
		LOiToFltFunction<Object> oiFunc1 = (LOiToFltFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToFltFunction<Object> nextFunc2 = (LToFltFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToFltFunction<Object> nextFunc3 = (LToFltFunction) sa3.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			float a1 = oiFunc1.applyAsFlt(source1, i);
			float a2 = nextFunc2.applyAsFlt(iterator2);
			float a3 = nextFunc3.applyAsFlt(iterator3);
			consumer.accept(this.applyAsFlt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C1, I1, C2, I2, C3, I3> void iterate(SequentialRead<C1, I1, aFloat> sa1, C1 source1, SequentialRead<C2, I2, aFloat> sa2, C2 source2, SequentialRead<C3, I3, aFloat> sa3, C3 source3, LFltConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToFltFunction<Object> nextFunc1 = (LToFltFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToFltFunction<Object> nextFunc2 = (LToFltFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToFltFunction<Object> nextFunc3 = (LToFltFunction) sa3.supplier();
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			float a1 = nextFunc1.applyAsFlt(iterator1);
			float a2 = nextFunc2.applyAsFlt(iterator2);
			float a3 = nextFunc3.applyAsFlt(iterator3);
			consumer.accept(this.applyAsFlt(a1, a2, a3));
		}
	}

}
