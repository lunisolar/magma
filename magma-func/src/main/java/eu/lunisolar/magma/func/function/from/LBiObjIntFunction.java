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

package eu.lunisolar.magma.func.function.from;

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
 * Non-throwing functional interface (lambda) LBiObjIntFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 3): T1 a1,T2 a2,int a3
 *
 * Co-domain: R
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiObjIntFunction<T1, T2, R> extends MetaFunction, MetaInterface.NonThrowing, Codomain<a<R>>, Domain3<a<T1>, a<T2>, aInt> { // NOSONAR

	String DESCRIPTION = "LBiObjIntFunction: R apply(T1 a1,T2 a2,int a3)";

	@Nullable
	// R apply(T1 a1,T2 a2,int a3) ;
	default R apply(T1 a1, T2 a2, int a3) {
		// return nestingApply(a1,a2,a3);
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call apply(T1 a1,T2 a2,int a3)
	 */
	R applyX(T1 a1, T2 a2, int a3) throws Throwable;

	default R tupleApply(LBiObjIntTriple<T1, T2> args) {
		return apply(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions according to the instructions. */
	default R handlingApply(T1 a1, T2 a2, int a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LBiObjIntFunction<T1, T2, R> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> handlingApply(a1, a2, a3, handling);
	}

	default R apply(T1 a1, T2 a2, int a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default R apply(T1 a1, T2 a2, int a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default R apply(T1 a1, T2 a2, int a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default R apply(T1 a1, T2 a2, int a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LBiObjIntFunction<T1, T2, R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2, a3) -> apply(a1, a2, a3, factory, newMessage);
	}

	default LBiObjIntFunction<T1, T2, R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2, a3) -> apply(a1, a2, a3, factory, newMessage, param1);
	}

	default LBiObjIntFunction<T1, T2, R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2, a3) -> apply(a1, a2, a3, factory, newMessage, param1, param1);
	}

	default LBiObjIntFunction<T1, T2, R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2, a3) -> apply(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	default R apply(T1 a1, T2 a2, int a3, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LBiObjIntFunction<T1, T2, R> trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2, a3) -> apply(a1, a2, a3, factory);
	}

	default R applyThen(T1 a1, T2 a2, int a3, @Nonnull LFunction<Throwable, R> handler) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.apply(e);
		}
	}

	default LBiObjIntFunction<T1, T2, R> tryingThen(@Nonnull LFunction<Throwable, R> handler) {
		return (a1, a2, a3) -> applyThen(a1, a2, a3, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default R nestingApply(T1 a1, T2 a2, int a3) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default R shovingApply(T1 a1, T2 a2, int a3) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T1, T2, R> R shovingApply(T1 a1, T2 a2, int a3, LBiObjIntFunction<T1, T2, R> func) {
		Null.nonNullArg(func, "func");
		return func.shovingApply(a1, a2, a3);
	}

	static <T1, T2, R> R handlingApply(T1 a1, T2 a2, int a3, LBiObjIntFunction<T1, T2, R> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApply(a1, a2, a3, handling);
	}

	static <T1, T2, R> R tryApply(T1 a1, T2 a2, int a3, LBiObjIntFunction<T1, T2, R> func) {
		Null.nonNullArg(func, "func");
		return func.nestingApply(a1, a2, a3);
	}

	static <T1, T2, R> R tryApply(T1 a1, T2 a2, int a3, LBiObjIntFunction<T1, T2, R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, factory, newMessage);
	}

	static <T1, T2, R> R tryApply(T1 a1, T2 a2, int a3, LBiObjIntFunction<T1, T2, R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, factory, newMessage, param1);
	}

	static <T1, T2, R> R tryApply(T1 a1, T2 a2, int a3, LBiObjIntFunction<T1, T2, R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, factory, newMessage, param1, param2);
	}

	static <T1, T2, R> R tryApply(T1 a1, T2 a2, int a3, LBiObjIntFunction<T1, T2, R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	static <T1, T2, R> R tryApply(T1 a1, T2 a2, int a3, LBiObjIntFunction<T1, T2, R> func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, factory);
	}

	static <T1, T2, R> R tryApplyThen(T1 a1, T2 a2, int a3, LBiObjIntFunction<T1, T2, R> func, @Nonnull LFunction<Throwable, R> handler) {
		Null.nonNullArg(func, "func");
		return func.applyThen(a1, a2, a3, handler);
	}

	LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullApply() method cannot be null (" + DESCRIPTION + ").";

	/** Function call that ensures the result is not null */
	@Nonnull
	default R nonNullApply(T1 a1, T2 a2, int a3) {
		return Null.nonNull(apply(a1, a2, a3), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiObjIntFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, R> void fromTo(int min_a3, int max_a3, T1 a1, T2 a2, @Nonnull LBiObjIntFunction<T1, T2, R> func) {
		Null.nonNullArg(func, "func");
		if (min_a3 <= max_a3) {
			for (int a3 = min_a3; a3 <= max_a3; a3++) {
				func.apply(a1, a2, a3);
			}
		} else {
			for (int a3 = min_a3; a3 >= max_a3; a3--) {
				func.apply(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, R> void fromTill(int min_a3, int max_a3, T1 a1, T2 a2, @Nonnull LBiObjIntFunction<T1, T2, R> func) {
		Null.nonNullArg(func, "func");
		if (min_a3 <= max_a3) {
			for (int a3 = min_a3; a3 < max_a3; a3++) {
				func.apply(a1, a2, a3);
			}
		} else {
			for (int a3 = min_a3; a3 > max_a3; a3--) {
				func.apply(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, R> void times(int max_a3, T1 a1, T2 a2, @Nonnull LBiObjIntFunction<T1, T2, R> func) {
		if (max_a3 < 0)
			return;
		fromTill(0, max_a3, a1, a2, func);
	}

	/** Extract and apply function. */
	public static <M, K, V, T2, R> R from(@Nonnull M container, LBiFunction<M, K, V> extractor, K key, T2 a2, int a3, @Nonnull LBiObjIntFunction<V, T2, R> function) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.apply(value, a2, a3);
		}

		return null;
	}

	/**  */
	public static <T1, T2, R> LBiObjIntFunction<T1, T2, R> uncurry(@Nonnull LFunction<T1, LFunction<T2, LIntFunction<R>>> func) {
		Null.nonNullArg(func, "func");
		return (T1 a1, T2 a2, int a3) -> func.apply(a1).apply(a2).apply(a3);
	}

	/** Change function to consumer that ignores output. */
	default LBiObjIntConsumer<T1, T2> toConsumer() {
		return this::apply;
	}

	/** Calls domain consumer before main function. */
	default LBiObjIntFunction<T1, T2, R> beforeDo(@Nonnull LBiObjIntConsumer<T1, T2> before) {
		Null.nonNullArg(before, "before");
		return (T1 a1, T2 a2, int a3) -> {
			before.accept(a1, a2, a3);
			return apply(a1, a2, a3);
		};
	}

	/** Calls codomain consumer after main function. */
	default LBiObjIntFunction<T1, T2, R> afterDo(@Nonnull LConsumer<R> after) {
		Null.nonNullArg(after, "after");
		return (T1 a1, T2 a2, int a3) -> {
			final R retval = apply(a1, a2, a3);
			after.accept(retval);
			return retval;
		};
	}

	/** Creates function that always returns the same value. */
	static <T1, T2, R> LBiObjIntFunction<T1, T2, R> constant(R r) {
		return (a1, a2, a3) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, R> LBiObjIntFunction<T1, T2, R> biObjIntFunc(final @Nonnull LBiObjIntFunction<T1, T2, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S<T1, T2, R> implements LBiObjIntFunction<T1, T2, R> {
		private LBiObjIntFunction<T1, T2, R> target = null;
		@Override
		public R applyX(T1 a1, T2 a2, int a3) throws Throwable {
			return target.applyX(a1, a2, a3);
		}
	}

	@Nonnull
	static <T1, T2, R> LBiObjIntFunction<T1, T2, R> recursive(final @Nonnull LFunction<LBiObjIntFunction<T1, T2, R>, LBiObjIntFunction<T1, T2, R>> selfLambda) {
		final S<T1, T2, R> single = new S();
		LBiObjIntFunction<T1, T2, R> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static <T1, T2, R> M<T1, T2, R> mementoOf(T1 a1, T2 a2, int a3, LBiObjIntFunction<T1, T2, R> function) {
		var initialValue = function.apply(a1, a2, a3);
		return initializedMementoOf(initialValue, function);
	}

	public static <T1, T2, R> M<T1, T2, R> initializedMementoOf(R initialValue, LBiObjIntFunction<T1, T2, R> function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static <T1, T2, R> M<T1, T2, R> deltaOf(T1 a1, T2 a2, int a3, LBiObjIntFunction<T1, T2, R> function, LBinaryOperator<R> deltaFunction) {
		var initialValue = function.apply(a1, a2, a3);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static <T1, T2, R> M<T1, T2, R> initializedDeltaOf(R initialValue, LBiObjIntFunction<T1, T2, R> function, LBinaryOperator<R> deltaFunction) {
		return memento(initialValue, deltaFunction.apply(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.apply(x1, x2));
	}

	public static <T1, T2, R> M<T1, T2, R> memento(R initialBaseValue, R initialValue, LBiObjIntFunction<T1, T2, R> baseFunction, LTernaryOperator<R> mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LBiObjIntFunction.M)
	 */
	@NotThreadSafe
	final class M<T1, T2, R> implements LBiObjIntFunction<T1, T2, R> {

		private final LBiObjIntFunction<T1, T2, R> baseFunction;
		private R lastBaseValue;
		private R lastValue;
		private final LTernaryOperator<R> mementoFunction;

		private M(R lastBaseValue, R lastValue, LBiObjIntFunction<T1, T2, R> baseFunction, LTernaryOperator<R> mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public R applyX(T1 a1, T2 a2, int a3) throws Throwable {
			R x1 = lastBaseValue;
			R x2 = lastBaseValue = baseFunction.applyX(a1, a2, a3);

			return lastValue = mementoFunction.applyX(lastValue, x1, x2);
		}

		public R currentApply(T1 a1, T2 a2, int a3) {
			R x1 = lastBaseValue;
			R x2 = baseFunction.apply(a1, a2, a3);

			return mementoFunction.apply(lastValue, x1, x2);
		}

		public R lastValue() {
			return lastValue;
		};

		public R lastBaseValue() {
			return lastBaseValue;
		};
	}

	// </editor-fold>

	@Nonnull
	static <T1, T2, R> LBiObjIntFunction<T1, T2, R> biObjIntFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T1, T2, R> LBiObjIntFunction<T1, T2, R> biObjIntFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce(message);
		};
	}

	static <T1, T2, R> R call(T1 a1, T2 a2, int a3, final @Nonnull LBiObjIntFunction<T1, T2, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.apply(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiObjIntFunction<V1, V2, R> compose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LIntUnaryOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.apply(before1.apply(v1), before2.apply(v2), before3.applyAsInt(v3));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriFunction<V1, V2, V3, R> unboxingCompose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LToIntFunction<? super V3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.apply(before1.apply(v1), before2.apply(v2), before3.applyAsInt(v3));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiObjIntFunction<T1, T2, V> then(@Nonnull LFunction<? super R, ? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.apply(this.apply(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiObjIntConsumer<T1, T2> thenConsume(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.accept(this.apply(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiObjIntPredicate<T1, T2> thenToBool(@Nonnull LPredicate<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.test(this.apply(a1, a2, a3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LBiObjIntFunction<T1, T2, R> nonNullable() {
		return this::nonNullApply;
	}

	/** Does nothing (LBiObjIntFunction) Function */
	public static <T1, T2, R> R doNothing(T1 a1, T2 a2, int a3) {
		return (R) Function4U.defaultObject;
	}

}
