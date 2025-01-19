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
 * Non-throwing functional interface (lambda) LOiToFltFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 2): T a1,int a2
 *
 * Co-domain: float
 *
 * Special case of function that corresponds to expressions like (list, index) -> List::get
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LOiToFltFunction<T> extends MetaFunction, MetaInterface.NonThrowing, OiFunction<T, aFloat>, Codomain<aFloat>, Domain2<a<T>, aInt> { //NOSONAR

	String DESCRIPTION = "LOiToFltFunction: float applyAsFlt(T a1,int a2)";

	default float applyAsFlt(T a1, int a2) {
		try {
			return this.applyAsFltX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsFlt(T a1,int a2)
	 */
	float applyAsFltX(T a1, int a2) throws Throwable;

	default float tupleApplyAsFlt(LObjIntPair<T> args) {
		return applyAsFlt(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default float handlingApplyAsFlt(T a1, int a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsFltX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LOiToFltFunction<T> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2) -> handlingApplyAsFlt(a1, a2, handling);
	}

	default float applyAsFlt(T a1, int a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyAsFltX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default float applyAsFlt(T a1, int a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyAsFltX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default float applyAsFlt(T a1, int a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyAsFltX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default float applyAsFlt(T a1, int a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyAsFltX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LOiToFltFunction<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2) -> applyAsFlt(a1, a2, factory, newMessage);
	}

	default LOiToFltFunction<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2) -> applyAsFlt(a1, a2, factory, newMessage, param1);
	}

	default LOiToFltFunction<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2) -> applyAsFlt(a1, a2, factory, newMessage, param1, param1);
	}

	default LOiToFltFunction<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2) -> applyAsFlt(a1, a2, factory, newMessage, param1, param2, param3);
	}

	default float applyAsFlt(T a1, int a2, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyAsFltX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LOiToFltFunction<T> trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2) -> applyAsFlt(a1, a2, factory);
	}

	default float applyAsFltThen(T a1, int a2, @Nonnull LToFltFunction<Throwable> handler) {
		try {
			return this.applyAsFltX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsFlt(e);
		}
	}

	default LOiToFltFunction<T> tryingThen(@Nonnull LToFltFunction<Throwable> handler) {
		return (a1, a2) -> applyAsFltThen(a1, a2, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default float nestingApplyAsFlt(T a1, int a2) {
		try {
			return this.applyAsFltX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default float shovingApplyAsFlt(T a1, int a2) {
		try {
			return this.applyAsFltX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> float shovingApplyAsFlt(T a1, int a2, LOiToFltFunction<T> func) {
		Null.nonNullArg(func, "func");
		return func.shovingApplyAsFlt(a1, a2);
	}

	static <T> float handlingApplyAsFlt(T a1, int a2, LOiToFltFunction<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { //<-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsFlt(a1, a2, handling);
	}

	static <T> float tryApplyAsFlt(T a1, int a2, LOiToFltFunction<T> func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsFlt(a1, a2);
	}

	static <T> float tryApplyAsFlt(T a1, int a2, LOiToFltFunction<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.applyAsFlt(a1, a2, factory, newMessage);
	}

	static <T> float tryApplyAsFlt(T a1, int a2, LOiToFltFunction<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.applyAsFlt(a1, a2, factory, newMessage, param1);
	}

	static <T> float tryApplyAsFlt(T a1, int a2, LOiToFltFunction<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.applyAsFlt(a1, a2, factory, newMessage, param1, param2);
	}

	static <T> float tryApplyAsFlt(T a1, int a2, LOiToFltFunction<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.applyAsFlt(a1, a2, factory, newMessage, param1, param2, param3);
	}

	static <T> float tryApplyAsFlt(T a1, int a2, LOiToFltFunction<T> func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.applyAsFlt(a1, a2, factory);
	}

	static <T> float tryApplyAsFltThen(T a1, int a2, LOiToFltFunction<T> func, @Nonnull LToFltFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsFltThen(a1, a2, handler);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default float nonNullApplyAsFlt(T a1, int a2) {
		return applyAsFlt(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LOiToFltFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_a2, int max_a2, T a1, @Nonnull LOiToFltFunction<T> func) {
		Null.nonNullArg(func, "func");
		if (min_a2 <= max_a2) {
			for (int a2 = min_a2; a2 <= max_a2; a2++) {
				func.applyAsFlt(a1, a2);
			}
		} else {
			for (int a2 = min_a2; a2 >= max_a2; a2--) {
				func.applyAsFlt(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTill(int min_a2, int max_a2, T a1, @Nonnull LOiToFltFunction<T> func) {
		Null.nonNullArg(func, "func");
		if (min_a2 <= max_a2) {
			for (int a2 = min_a2; a2 < max_a2; a2++) {
				func.applyAsFlt(a1, a2);
			}
		} else {
			for (int a2 = min_a2; a2 > max_a2; a2--) {
				func.applyAsFlt(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void times(int max_a2, T a1, @Nonnull LOiToFltFunction<T> func) {
		if (max_a2 < 0)
			return;
		fromTill(0, max_a2, a1, func);
	}

	/** Extract and apply function. */
	public static <M, K, V> float from(@Nonnull M container, LBiFunction<M, K, V> extractor, K key, int a2, @Nonnull LOiToFltFunction<V> function, float orElse) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.applyAsFlt(value, a2);
		}

		return orElse;
	}

	default LIntToFltFunction _with(T a1) {
		return a2 -> applyAsFlt(a1, a2);
	}

	default LToFltFunction<T> with(int a2) {
		return a1 -> applyAsFlt(a1, a2);
	}

	/**  */
	public static <T> LOiToFltFunction<T> uncurry(@Nonnull LFunction<T, LIntToFltFunction> func) {
		Null.nonNullArg(func, "func");
		return (T a1, int a2) -> func.apply(a1).applyAsFlt(a2);
	}

	/** Change function to one that ignores output. */
	default LObjIntConsumer<T> toConsumer() {
		return this::applyAsFlt;
	}

	/** Calls domain consumer before main function. */
	default LOiToFltFunction<T> beforeDo(@Nonnull LObjIntConsumer<T> before) {
		Null.nonNullArg(before, "before");
		return (T a1, int a2) -> {
			before.accept(a1, a2);
			return applyAsFlt(a1, a2);
		};
	}

	/** Calls codomain consumer after main function. */
	default LOiToFltFunction<T> afterDo(@Nonnull LFltConsumer after) {
		Null.nonNullArg(after, "after");
		return (T a1, int a2) -> {
			final float retval = applyAsFlt(a1, a2);
			after.accept(retval);
			return retval;
		};
	}

	/** Creates function that always returns the same value. */
	static <T> LOiToFltFunction<T> constant(float r) {
		return (a1, a2) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LOiToFltFunction<T> oiToFltFunc(final @Nonnull LOiToFltFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S<T> implements LOiToFltFunction<T> {
		private LOiToFltFunction<T> target = null;
		@Override
		public float applyAsFltX(T a1, int a2) throws Throwable {
			return target.applyAsFltX(a1, a2);
		}
	}

	@Nonnull
	static <T> LOiToFltFunction<T> recursive(final @Nonnull LFunction<LOiToFltFunction<T>, LOiToFltFunction<T>> selfLambda) {
		final S<T> single = new S();
		LOiToFltFunction<T> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static <T> M<T> mementoOf(T a1, int a2, LOiToFltFunction<T> function) {
		var initialValue = function.applyAsFlt(a1, a2);
		return initializedMementoOf(initialValue, function);
	}

	public static <T> M<T> initializedMementoOf(float initialValue, LOiToFltFunction<T> function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static <T> M<T> deltaOf(T a1, int a2, LOiToFltFunction<T> function, LFltBinaryOperator deltaFunction) {
		var initialValue = function.applyAsFlt(a1, a2);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static <T> M<T> deltaOf(T a1, int a2, LOiToFltFunction<T> function) {
		var initialValue = function.applyAsFlt(a1, a2);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (x2 - x1));
	}

	public static <T> M<T> initializedDeltaOf(float initialValue, LOiToFltFunction<T> function, LFltBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.applyAsFlt(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.applyAsFlt(x1, x2));
	}

	public static <T> M<T> memento(float initialBaseValue, float initialValue, LOiToFltFunction<T> baseFunction, LFltTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LOiToFltFunction.M)
	 */
	@NotThreadSafe
	final class M<T> implements LOiToFltFunction<T> {

		private final LOiToFltFunction<T> baseFunction;
		private float lastBaseValue;
		private float lastValue;
		private final LFltTernaryOperator mementoFunction;

		private M(float lastBaseValue, float lastValue, LOiToFltFunction<T> baseFunction, LFltTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public float applyAsFltX(T a1, int a2) throws Throwable {
			float x1 = lastBaseValue;
			float x2 = lastBaseValue = baseFunction.applyAsFltX(a1, a2);

			return lastValue = mementoFunction.applyAsFltX(lastValue, x1, x2);
		}

		public float currentApplyAsFlt(T a1, int a2) {
			float x1 = lastBaseValue;
			float x2 = baseFunction.applyAsFlt(a1, a2);

			return mementoFunction.applyAsFlt(lastValue, x1, x2);
		}

		public float lastValue() {
			return lastValue;
		}

		public float lastBaseValue() {
			return lastBaseValue;
		}

		public float currentBaseValue(T a1, int a2) {
			return baseFunction.applyAsFlt(a1, a2);
		}
	}

	// </editor-fold>

	@Nonnull
	static <T> LOiToFltFunction<T> oiToFltFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T> LOiToFltFunction<T> oiToFltFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce(message);
		};
	}

	static <T> float call(T a1, int a2, final @Nonnull LOiToFltFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsFlt(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1> LOiToFltFunction<V1> compose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.applyAsFlt(before1.apply(v1), before2.applyAsInt(v2));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LToFltBiFunction<V1, V2> unboxingCompose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.applyAsFlt(before1.apply(v1), before2.applyAsInt(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LOiFunction<T, V> then(@Nonnull LFltFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.apply(this.applyAsFlt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToByteFunction<T> thenToByte(@Nonnull LFltToByteFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsByte(this.applyAsFlt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToSrtFunction<T> thenToSrt(@Nonnull LFltToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsSrt(this.applyAsFlt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToIntFunction<T> thenToInt(@Nonnull LFltToIntFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsInt(this.applyAsFlt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToLongFunction<T> thenToLong(@Nonnull LFltToLongFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsLong(this.applyAsFlt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToFltFunction<T> thenToFlt(@Nonnull LFltUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsFlt(this.applyAsFlt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToDblFunction<T> thenToDbl(@Nonnull LFltToDblFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsDbl(this.applyAsFlt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToCharFunction<T> thenToChar(@Nonnull LFltToCharFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsChar(this.applyAsFlt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LObjIntPredicate<T> thenToBool(@Nonnull LFltPredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.test(this.applyAsFlt(a1, a2));
	}

	// </editor-fold>

	default LOiToFltFunction<T> shoving() {

		return new LOiToFltFunction<T>() {

			public float applyAsFlt(T a1, int a2) {
				try {
					return this.applyAsFltX(a1, a2);
				} catch (Throwable e) { // NOSONAR
					Handling.handleErrors(e);
					throw Handling.throwIt(e);
				}
			}

			public float applyAsFltX(T a1, int a2) throws Throwable {
				return LOiToFltFunction.this.applyAsFltX(a1, a2);
			}

		};
	}

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LOiToFltFunction) Function */
	public static <T> float doNothing(T a1, int a2) {
		return Function4U.defaultFloat;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2> void forEach(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, LFltConsumer consumer) {
		int size = ia1.size(source1);
		var oiFunc1 = IA.getter(ia1);
		size = Integer.min(size, ia2.size(source2));
		var oiFunc2 = IA.intGetter(ia2);
		int i = 0;
		for (; i < size; i++) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = oiFunc2.applyAsInt(source2, i);
			consumer.accept(this.applyAsFlt(a1, a2));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, LFltConsumer consumer) {
		var iterator1 = SA.adapter(sa1).apply(source1);
		var testFunc1 = SA.tester(sa1);
		var nextFunc1 = SA.supplier(sa1);
		int size = ia2.size(source2);
		var oiFunc2 = IA.intGetter(ia2);
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = oiFunc2.applyAsInt(source2, i);
			consumer.accept(this.applyAsFlt(a1, a2));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2> void iterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, LFltConsumer consumer) {
		int size = ia1.size(source1);
		var oiFunc1 = IA.getter(ia1);
		var iterator2 = SA.adapter(sa2).apply(source2);
		var testFunc2 = SA.tester(sa2);
		var nextFunc2 = SA.intSupplier(sa2);
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = nextFunc2.applyAsInt(iterator2);
			consumer.accept(this.applyAsFlt(a1, a2));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C1, I1, C2, I2> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, LFltConsumer consumer) {
		var iterator1 = SA.adapter(sa1).apply(source1);
		var testFunc1 = SA.tester(sa1);
		var nextFunc1 = SA.supplier(sa1);
		var iterator2 = SA.adapter(sa2).apply(source2);
		var testFunc2 = SA.tester(sa2);
		var nextFunc2 = SA.intSupplier(sa2);
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2)) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = nextFunc2.applyAsInt(iterator2);
			consumer.accept(this.applyAsFlt(a1, a2));
		}
	}

}
