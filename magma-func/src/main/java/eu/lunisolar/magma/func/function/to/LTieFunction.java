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
 * Non-throwing functional interface (lambda) LTieFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 3): T1 a1,int a2,T2 a3
 *
 * Co-domain: int
 *
 * Special case of function that corresponds to TIE consumer with return integer value.
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LTieFunction<T1, T2> extends MetaFunction, MetaInterface.NonThrowing, TieFunction<T1, a<T2>>, Codomain<aInt>, Domain3<a<T1>, aInt, a<T2>> { //NOSONAR

	String DESCRIPTION = "LTieFunction: int applyAsInt(T1 a1,int a2,T2 a3)";

	default int applyAsInt(T1 a1, int a2, T2 a3) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsInt(T1 a1,int a2,T2 a3)
	 */
	int applyAsIntX(T1 a1, int a2, T2 a3) throws Throwable;

	default int tupleApplyAsInt(LObjIntObjTriple<T1, T2> args) {
		return applyAsInt(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions according to the instructions. */
	default int handlingApplyAsInt(T1 a1, int a2, T2 a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LTieFunction<T1, T2> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> handlingApplyAsInt(a1, a2, a3, handling);
	}

	default int applyAsInt(T1 a1, int a2, T2 a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default int applyAsInt(T1 a1, int a2, T2 a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default int applyAsInt(T1 a1, int a2, T2 a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default int applyAsInt(T1 a1, int a2, T2 a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LTieFunction<T1, T2> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2, a3) -> applyAsInt(a1, a2, a3, factory, newMessage);
	}

	default LTieFunction<T1, T2> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2, a3) -> applyAsInt(a1, a2, a3, factory, newMessage, param1);
	}

	default LTieFunction<T1, T2> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2, a3) -> applyAsInt(a1, a2, a3, factory, newMessage, param1, param1);
	}

	default LTieFunction<T1, T2> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2, a3) -> applyAsInt(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	default int applyAsInt(T1 a1, int a2, T2 a3, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LTieFunction<T1, T2> trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2, a3) -> applyAsInt(a1, a2, a3, factory);
	}

	default int applyAsIntThen(T1 a1, int a2, T2 a3, @Nonnull LToIntFunction<Throwable> handler) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsInt(e);
		}
	}

	default LTieFunction<T1, T2> tryingThen(@Nonnull LToIntFunction<Throwable> handler) {
		return (a1, a2, a3) -> applyAsIntThen(a1, a2, a3, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default int nestingApplyAsInt(T1 a1, int a2, T2 a3) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default int shovingApplyAsInt(T1 a1, int a2, T2 a3) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T1, T2> int shovingApplyAsInt(T1 a1, int a2, T2 a3, LTieFunction<T1, T2> func) {
		Null.nonNullArg(func, "func");
		return func.shovingApplyAsInt(a1, a2, a3);
	}

	static <T1, T2> int handlingApplyAsInt(T1 a1, int a2, T2 a3, LTieFunction<T1, T2> func, HandlingInstructions<Throwable, RuntimeException> handling) { //<-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsInt(a1, a2, a3, handling);
	}

	static <T1, T2> int tryApplyAsInt(T1 a1, int a2, T2 a3, LTieFunction<T1, T2> func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsInt(a1, a2, a3);
	}

	static <T1, T2> int tryApplyAsInt(T1 a1, int a2, T2 a3, LTieFunction<T1, T2> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.applyAsInt(a1, a2, a3, factory, newMessage);
	}

	static <T1, T2> int tryApplyAsInt(T1 a1, int a2, T2 a3, LTieFunction<T1, T2> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.applyAsInt(a1, a2, a3, factory, newMessage, param1);
	}

	static <T1, T2> int tryApplyAsInt(T1 a1, int a2, T2 a3, LTieFunction<T1, T2> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.applyAsInt(a1, a2, a3, factory, newMessage, param1, param2);
	}

	static <T1, T2> int tryApplyAsInt(T1 a1, int a2, T2 a3, LTieFunction<T1, T2> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.applyAsInt(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	static <T1, T2> int tryApplyAsInt(T1 a1, int a2, T2 a3, LTieFunction<T1, T2> func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.applyAsInt(a1, a2, a3, factory);
	}

	static <T1, T2> int tryApplyAsIntThen(T1 a1, int a2, T2 a3, LTieFunction<T1, T2> func, @Nonnull LToIntFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsIntThen(a1, a2, a3, handler);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default int nonNullApplyAsInt(T1 a1, int a2, T2 a3) {
		return applyAsInt(a1, a2, a3);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LTieFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void fromTo(int min_a2, int max_a2, T1 a1, T2 a3, @Nonnull LTieFunction<T1, T2> func) {
		Null.nonNullArg(func, "func");
		if (min_a2 <= max_a2) {
			for (int a2 = min_a2; a2 <= max_a2; a2++) {
				func.applyAsInt(a1, a2, a3);
			}
		} else {
			for (int a2 = min_a2; a2 >= max_a2; a2--) {
				func.applyAsInt(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void fromTill(int min_a2, int max_a2, T1 a1, T2 a3, @Nonnull LTieFunction<T1, T2> func) {
		Null.nonNullArg(func, "func");
		if (min_a2 <= max_a2) {
			for (int a2 = min_a2; a2 < max_a2; a2++) {
				func.applyAsInt(a1, a2, a3);
			}
		} else {
			for (int a2 = min_a2; a2 > max_a2; a2--) {
				func.applyAsInt(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void times(int max_a2, T1 a1, T2 a3, @Nonnull LTieFunction<T1, T2> func) {
		if (max_a2 < 0)
			return;
		fromTill(0, max_a2, a1, a3, func);
	}

	/** Extract and apply function. */
	public static <M, K, V, T2> int from(@Nonnull M container, LBiFunction<M, K, V> extractor, K key, int a2, T2 a3, @Nonnull LTieFunction<V, T2> function, int orElse) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.applyAsInt(value, a2, a3);
		}

		return orElse;
	}

	default LOiToIntFunction<T1> with(T2 a3) {
		return (a1, a2) -> applyAsInt(a1, a2, a3);
	}

	default LToIntFunction<T2> _with(T1 a1, int a2) {
		return a3 -> applyAsInt(a1, a2, a3);
	}

	default LToIntFunction<T1> with(int a2, T2 a3) {
		return a1 -> applyAsInt(a1, a2, a3);
	}

	/**  */
	public static <T1, T2> LTieFunction<T1, T2> uncurry(@Nonnull LFunction<T1, LIntFunction<LToIntFunction<T2>>> func) {
		Null.nonNullArg(func, "func");
		return (T1 a1, int a2, T2 a3) -> func.apply(a1).apply(a2).applyAsInt(a3);
	}

	/** Change function to one that ignores output. */
	default LTieConsumer<T1, T2> toConsumer() {
		return this::applyAsInt;
	}

	/** Calls domain consumer before main function. */
	default LTieFunction<T1, T2> beforeDo(@Nonnull LTieConsumer<T1, T2> before) {
		Null.nonNullArg(before, "before");
		return (T1 a1, int a2, T2 a3) -> {
			before.accept(a1, a2, a3);
			return applyAsInt(a1, a2, a3);
		};
	}

	/** Calls codomain consumer after main function. */
	default LTieFunction<T1, T2> afterDo(@Nonnull LIntConsumer after) {
		Null.nonNullArg(after, "after");
		return (T1 a1, int a2, T2 a3) -> {
			final int retval = applyAsInt(a1, a2, a3);
			after.accept(retval);
			return retval;
		};
	}

	/** Creates function that always returns the same value. */
	static <T1, T2> LTieFunction<T1, T2> constant(int r) {
		return (a1, a2, a3) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2> LTieFunction<T1, T2> tieFunc(final @Nonnull LTieFunction<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S<T1, T2> implements LTieFunction<T1, T2> {
		private LTieFunction<T1, T2> target = null;
		@Override
		public int applyAsIntX(T1 a1, int a2, T2 a3) throws Throwable {
			return target.applyAsIntX(a1, a2, a3);
		}
	}

	@Nonnull
	static <T1, T2> LTieFunction<T1, T2> recursive(final @Nonnull LFunction<LTieFunction<T1, T2>, LTieFunction<T1, T2>> selfLambda) {
		final S<T1, T2> single = new S();
		LTieFunction<T1, T2> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static <T1, T2> M<T1, T2> mementoOf(T1 a1, int a2, T2 a3, LTieFunction<T1, T2> function) {
		var initialValue = function.applyAsInt(a1, a2, a3);
		return initializedMementoOf(initialValue, function);
	}

	public static <T1, T2> M<T1, T2> initializedMementoOf(int initialValue, LTieFunction<T1, T2> function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static <T1, T2> M<T1, T2> deltaOf(T1 a1, int a2, T2 a3, LTieFunction<T1, T2> function, LIntBinaryOperator deltaFunction) {
		var initialValue = function.applyAsInt(a1, a2, a3);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static <T1, T2> M<T1, T2> deltaOf(T1 a1, int a2, T2 a3, LTieFunction<T1, T2> function) {
		var initialValue = function.applyAsInt(a1, a2, a3);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (x2 - x1));
	}

	public static <T1, T2> M<T1, T2> initializedDeltaOf(int initialValue, LTieFunction<T1, T2> function, LIntBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.applyAsInt(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.applyAsInt(x1, x2));
	}

	public static <T1, T2> M<T1, T2> memento(int initialBaseValue, int initialValue, LTieFunction<T1, T2> baseFunction, LIntTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LTieFunction.M)
	 */
	@NotThreadSafe
	final class M<T1, T2> implements LTieFunction<T1, T2> {

		private final LTieFunction<T1, T2> baseFunction;
		private int lastBaseValue;
		private int lastValue;
		private final LIntTernaryOperator mementoFunction;

		private M(int lastBaseValue, int lastValue, LTieFunction<T1, T2> baseFunction, LIntTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public int applyAsIntX(T1 a1, int a2, T2 a3) throws Throwable {
			int x1 = lastBaseValue;
			int x2 = lastBaseValue = baseFunction.applyAsIntX(a1, a2, a3);

			return lastValue = mementoFunction.applyAsIntX(lastValue, x1, x2);
		}

		public int currentApplyAsInt(T1 a1, int a2, T2 a3) {
			int x1 = lastBaseValue;
			int x2 = baseFunction.applyAsInt(a1, a2, a3);

			return mementoFunction.applyAsInt(lastValue, x1, x2);
		}

		public int lastValue() {
			return lastValue;
		}

		public int lastBaseValue() {
			return lastBaseValue;
		}

		public int currentBaseValue(T1 a1, int a2, T2 a3) {
			return baseFunction.applyAsInt(a1, a2, a3);
		}
	}

	// </editor-fold>

	@Nonnull
	static <T1, T2> LTieFunction<T1, T2> tieFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T1, T2> LTieFunction<T1, T2> tieFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce(message);
		};
	}

	static <T1, T2> int call(T1 a1, int a2, T2 a3, final @Nonnull LTieFunction<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsInt(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V3> LTieFunction<V1, V3> compose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LIntUnaryOperator before2, @Nonnull final LFunction<? super V3, ? extends T2> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.applyAsInt(before1.apply(v1), before2.applyAsInt(v2), before3.apply(v3));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LToIntTriFunction<V1, V2, V3> unboxingCompose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LToIntFunction<? super V2> before2, @Nonnull final LFunction<? super V3, ? extends T2> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.applyAsInt(before1.apply(v1), before2.applyAsInt(v2), before3.apply(v3));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LObjIntObjFunction<T1, T2, V> then(@Nonnull LIntFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.apply(this.applyAsInt(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LTieFunction<T1, T2> thenToInt(@Nonnull LIntUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.applyAsInt(this.applyAsInt(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LObjIntObjPredicate<T1, T2> thenToBool(@Nonnull LIntPredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.test(this.applyAsInt(a1, a2, a3));
	}

	// </editor-fold>

	default LTieFunction<T1, T2> shoving() {

		return new LTieFunction<T1, T2>() {

			public int applyAsInt(T1 a1, int a2, T2 a3) {
				try {
					return this.applyAsIntX(a1, a2, a3);
				} catch (Throwable e) { // NOSONAR
					Handling.handleErrors(e);
					throw Handling.throwIt(e);
				}
			}

			public int applyAsIntX(T1 a1, int a2, T2 a3) throws Throwable {
				return LTieFunction.this.applyAsIntX(a1, a2, a3);
			}

		};
	}

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LTieFunction) Function */
	public static <T1, T2> int doNothing(T1 a1, int a2, T2 a3) {
		return Function4U.defaultInteger;
	}

	/** ***ITERATION:    TIE_CONSUMER_GEN:  FOR, [SourcePurpose{arg=int sStart, type=CONST}, SourcePurpose{arg=int sEnd, type=CONST}, SourcePurpose{arg=int tStart, type=CONST}, SourcePurpose{arg=T1 trg1, type=CONST}, SourcePurpose{arg=T2 a3, type=TIE_SOURCE}, SourcePurpose{arg=T2 a3, type=TIE_GEN_SUPPLIER}] */
	default <SRC> int genericTieForEach(int sStart, int sEnd, int tStart, T1 trg1, SRC src3, OiFunction<SRC, a<T2>> srcAcc3) {
		return tieForEach(sStart, sEnd, tStart, trg1, src3, (LOiFunction<SRC, T2>) srcAcc3, this);

	}

	/** ***ITERATION:    TARGETED_INDEXED_FOR_EACH:  FOR, [SourcePurpose{arg=T1 trg1, type=CONST}, SourcePurpose{arg=T2 a3, type=IA}, SourcePurpose{arg=LTieFunction<? super T1,? super T2> consumer, type=CONST}] */
	public static <T1, C3, T2> T1 tiForEach(T1 trg1, IndexedRead<C3, a<T2>> ia3, C3 source3, LTieFunction<? super T1, ? super T2> consumer) {

		tieForEach(trg1, ia3, source3, consumer);

		return trg1;

	}

	/** ***ITERATION:    TARGETED_INDEXED_FOR_EACH_NEW:  FOR, [SourcePurpose{arg=T1 trg1, type=SIZE_FACTORY}, SourcePurpose{arg=T2 a3, type=IA}, SourcePurpose{arg=LTieFunction<? super T1,? super T2> consumer, type=CONST}] */
	public static <T1, C3, T2> T1 ntiForEach(LIntFunction<T1> trgFactory1, IndexedRead<C3, a<T2>> ia3, C3 source3, LTieFunction<? super T1, ? super T2> consumer) {
		int size = ia3.size(source3);
		T1 trg1 = trgFactory1.apply(size);
		tieForEach(0, size, 0, trg1, source3, ia3.getter(), consumer);
		return trg1;
	}

	/** ***ITERATION:    TIE_CONSUMER_SHORT:  FOR, [SourcePurpose{arg=T1 trg1, type=CONST}, SourcePurpose{arg=T2 a3, type=IA}, SourcePurpose{arg=LTieFunction<? super T1,? super T2> consumer, type=CONST}] */
	public static <T1, C3, T2> int tieForEach(T1 trg1, IndexedRead<C3, a<T2>> ia3, C3 source3, LTieFunction<? super T1, ? super T2> consumer) {
		int size = ia3.size(source3);
		return tieForEach(0, size, 0, trg1, source3, ia3.getter(), consumer);
	}

	/** ***ITERATION:    TIE_CONSUMER:  FOR, [SourcePurpose{arg=int sStart, type=CONST}, SourcePurpose{arg=int sEnd, type=CONST}, SourcePurpose{arg=int tStart, type=CONST}, SourcePurpose{arg=T1 trg1, type=CONST}, SourcePurpose{arg=T2 a3, type=TIE_SOURCE}, SourcePurpose{arg=T2 a3, type=TIE_SUPPLIER}, SourcePurpose{arg=LTieFunction<? super T1,? super T2> consumer, type=CONST}] */
	public static <T1, SRC, T2> int tieForEach(int sStart, int sEnd, int tStart, T1 trg1, SRC src3, LOiFunction<SRC, T2> srcAcc3, LTieFunction<? super T1, ? super T2> consumer) {
		int tIndex = tStart;
		for (int sIndex = sStart; sIndex < sEnd; sIndex++) {
			T2 a3 = srcAcc3.apply(src3, sIndex);
			tIndex += consumer.applyAsInt(trg1, tIndex, a3);
		}
		return tIndex - tStart;

	}

	/** ***ITERATION:    TIE_CONSUMER2_GEN:  FOR, [SourcePurpose{arg=int sStart, type=CONST}, SourcePurpose{arg=int tStart, type=CONST}, SourcePurpose{arg=T1 trg1, type=CONST}, SourcePurpose{arg=T2 a3, type=TIE_SOURCE}, SourcePurpose{arg=T2 a3, type=TE_GEN_PREDICATE}, SourcePurpose{arg=T2 a3, type=TE_GEN_SUPPLIER}] */
	default <SRC> int genericTieForEach(int sStart, int tStart, T1 trg1, SRC src3, OFunction<SRC, aBool> srcTest3, OFunction<SRC, a<T2>> srcAcc3) {
		return tieForEach(sStart, tStart, trg1, src3, (LPredicate<SRC>) srcTest3, (LFunction<SRC, T2>) srcAcc3, this);

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with TIE: 'target', index, element). First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns increment count based on consumer function
	*/
	public static <T1, SRC, T2> int tieForEach(int sStart, int tStart, T1 trg1, SRC src3, LPredicate<SRC> srcTest3, LFunction<SRC, T2> srcAcc3, LTieFunction<? super T1, ? super T2> consumer) {
		int tIndex = tStart;
		for (; srcTest3.test(src3); tIndex++) {
			T2 a3 = srcAcc3.apply(src3);
			tIndex += consumer.applyAsInt(trg1, tIndex, a3);
		}
		return tIndex - sStart;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with TIE: 'target', index, element). First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns increment count based on consumer function
	*/
	public static <T1, C3, I3, T2> int tieIterate(T1 trg1, SequentialRead<C3, I3, a<T2>> sa3, C3 source3, LTieFunction<? super T1, ? super T2> consumer) {
		LFunction<C3, I3> toIntermediate = sa3.adapter();
		return tieForEach(0, 0, trg1, toIntermediate.apply(source3), sa3.tester(), sa3.supplier(), consumer);
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with TIE: 'target', index, element). First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns 'target' object
	*/
	public static <T1, C3, I3, T2> T1 tiIterate(T1 trg1, SequentialRead<C3, I3, a<T2>> sa3, C3 source3, LTieFunction<? super T1, ? super T2> consumer) {

		tieIterate(trg1, sa3, source3, consumer);

		return trg1;

	}

	/** ***ITERATION:    TARGETED_INDEXED_ITERATE_NEW:  WHILE, [SourcePurpose{arg=T1 trg1, type=SUPPLIER}, SourcePurpose{arg=T2 a3, type=SA}, SourcePurpose{arg=LTieFunction<? super T1,? super T2> consumer, type=CONST}] */
	public static <T1, C3, I3, T2> T1 ntiIterate(LSupplier<T1> source1, SequentialRead<C3, I3, a<T2>> sa3, C3 source3, LTieFunction<? super T1, ? super T2> consumer) {
		T1 trg1 = source1.get();
		tieIterate(trg1, sa3, source3, consumer);

		return trg1;

	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, C3> void forEach(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, IndexedRead<C3, a<T2>> ia3, C3 source3, LIntConsumer consumer) {
		int size = ia1.size(source1);
		var oiFunc1 = IA.getter(ia1);
		size = Integer.min(size, ia2.size(source2));
		var oiFunc2 = IA.intGetter(ia2);
		size = Integer.min(size, ia3.size(source3));
		var oiFunc3 = IA.getter(ia3);
		int i = 0;
		for (; i < size; i++) {
			T1 a1 = oiFunc1.apply(source1, i);
			int a2 = oiFunc2.applyAsInt(source2, i);
			T2 a3 = oiFunc3.apply(source3, i);
			consumer.accept(this.applyAsInt(a1, a2, a3));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, C3> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, IndexedRead<C3, a<T2>> ia3, C3 source3, LIntConsumer consumer) {
		var iterator1 = SA.adapter(sa1).apply(source1);
		var testFunc1 = SA.tester(sa1);
		var nextFunc1 = SA.supplier(sa1);
		int size = ia2.size(source2);
		var oiFunc2 = IA.intGetter(ia2);
		size = Integer.min(size, ia3.size(source3));
		var oiFunc3 = IA.getter(ia3);
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			T1 a1 = nextFunc1.apply(iterator1);
			int a2 = oiFunc2.applyAsInt(source2, i);
			T2 a3 = oiFunc3.apply(source3, i);
			consumer.accept(this.applyAsInt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2, C3> void iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, IndexedRead<C3, a<T2>> ia3, C3 source3, LIntConsumer consumer) {
		int size = ia1.size(source1);
		var oiFunc1 = IA.getter(ia1);
		var iterator2 = SA.adapter(sa2).apply(source2);
		var testFunc2 = SA.tester(sa2);
		var nextFunc2 = SA.intSupplier(sa2);
		size = Integer.min(size, ia3.size(source3));
		var oiFunc3 = IA.getter(ia3);
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			T1 a1 = oiFunc1.apply(source1, i);
			int a2 = nextFunc2.applyAsInt(iterator2);
			T2 a3 = oiFunc3.apply(source3, i);
			consumer.accept(this.applyAsInt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, I2, C3> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, IndexedRead<C3, a<T2>> ia3, C3 source3, LIntConsumer consumer) {
		var iterator1 = SA.adapter(sa1).apply(source1);
		var testFunc1 = SA.tester(sa1);
		var nextFunc1 = SA.supplier(sa1);
		var iterator2 = SA.adapter(sa2).apply(source2);
		var testFunc2 = SA.tester(sa2);
		var nextFunc2 = SA.intSupplier(sa2);
		int size = ia3.size(source3);
		var oiFunc3 = IA.getter(ia3);
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && i < size) {
			T1 a1 = nextFunc1.apply(iterator1);
			int a2 = nextFunc2.applyAsInt(iterator2);
			T2 a3 = oiFunc3.apply(source3, i);
			consumer.accept(this.applyAsInt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, C3, I3> void iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, SequentialRead<C3, I3, a<T2>> sa3, C3 source3, LIntConsumer consumer) {
		int size = ia1.size(source1);
		var oiFunc1 = IA.getter(ia1);
		size = Integer.min(size, ia2.size(source2));
		var oiFunc2 = IA.intGetter(ia2);
		var iterator3 = SA.adapter(sa3).apply(source3);
		var testFunc3 = SA.tester(sa3);
		var nextFunc3 = SA.supplier(sa3);
		int i = 0;
		while (i < size && testFunc3.test(iterator3)) {
			T1 a1 = oiFunc1.apply(source1, i);
			int a2 = oiFunc2.applyAsInt(source2, i);
			T2 a3 = nextFunc3.apply(iterator3);
			consumer.accept(this.applyAsInt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, C3, I3> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, SequentialRead<C3, I3, a<T2>> sa3, C3 source3, LIntConsumer consumer) {
		var iterator1 = SA.adapter(sa1).apply(source1);
		var testFunc1 = SA.tester(sa1);
		var nextFunc1 = SA.supplier(sa1);
		int size = ia2.size(source2);
		var oiFunc2 = IA.intGetter(ia2);
		var iterator3 = SA.adapter(sa3).apply(source3);
		var testFunc3 = SA.tester(sa3);
		var nextFunc3 = SA.supplier(sa3);
		int i = 0;
		while (testFunc1.test(iterator1) && i < size && testFunc3.test(iterator3)) {
			T1 a1 = nextFunc1.apply(iterator1);
			int a2 = oiFunc2.applyAsInt(source2, i);
			T2 a3 = nextFunc3.apply(iterator3);
			consumer.accept(this.applyAsInt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2, C3, I3> void iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, SequentialRead<C3, I3, a<T2>> sa3, C3 source3, LIntConsumer consumer) {
		int size = ia1.size(source1);
		var oiFunc1 = IA.getter(ia1);
		var iterator2 = SA.adapter(sa2).apply(source2);
		var testFunc2 = SA.tester(sa2);
		var nextFunc2 = SA.intSupplier(sa2);
		var iterator3 = SA.adapter(sa3).apply(source3);
		var testFunc3 = SA.tester(sa3);
		var nextFunc3 = SA.supplier(sa3);
		int i = 0;
		while (i < size && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			T1 a1 = oiFunc1.apply(source1, i);
			int a2 = nextFunc2.applyAsInt(iterator2);
			T2 a3 = nextFunc3.apply(iterator3);
			consumer.accept(this.applyAsInt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C1, I1, C2, I2, C3, I3> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, SequentialRead<C3, I3, a<T2>> sa3, C3 source3, LIntConsumer consumer) {
		var iterator1 = SA.adapter(sa1).apply(source1);
		var testFunc1 = SA.tester(sa1);
		var nextFunc1 = SA.supplier(sa1);
		var iterator2 = SA.adapter(sa2).apply(source2);
		var testFunc2 = SA.tester(sa2);
		var nextFunc2 = SA.intSupplier(sa2);
		var iterator3 = SA.adapter(sa3).apply(source3);
		var testFunc3 = SA.tester(sa3);
		var nextFunc3 = SA.supplier(sa3);
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			T1 a1 = nextFunc1.apply(iterator1);
			int a2 = nextFunc2.applyAsInt(iterator2);
			T2 a3 = nextFunc3.apply(iterator3);
			consumer.accept(this.applyAsInt(a1, a2, a3));
		}
	}

}
