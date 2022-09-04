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
 * Non-throwing functional interface (lambda) LObjIntLongFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 3): T a1,int a2,long a3
 *
 * Co-domain: R
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LObjIntLongFunction<T, R> extends MetaFunction, MetaInterface.NonThrowing, Codomain<a<R>>, Domain3<a<T>, aInt, aLong> { // NOSONAR

	String DESCRIPTION = "LObjIntLongFunction: R apply(T a1,int a2,long a3)";

	@Nullable
	// R apply(T a1,int a2,long a3) ;
	default R apply(T a1, int a2, long a3) {
		// return nestingApply(a1,a2,a3);
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call apply(T a1,int a2,long a3)
	 */
	R applyX(T a1, int a2, long a3) throws Throwable;

	default R tupleApply(LObjIntLongTriple<T> args) {
		return apply(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions according to the instructions. */
	default R handlingApply(T a1, int a2, long a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LObjIntLongFunction<T, R> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> handlingApply(a1, a2, a3, handling);
	}

	default R apply(T a1, int a2, long a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default R apply(T a1, int a2, long a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default R apply(T a1, int a2, long a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default R apply(T a1, int a2, long a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LObjIntLongFunction<T, R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2, a3) -> apply(a1, a2, a3, factory, newMessage);
	}

	default LObjIntLongFunction<T, R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2, a3) -> apply(a1, a2, a3, factory, newMessage, param1);
	}

	default LObjIntLongFunction<T, R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2, a3) -> apply(a1, a2, a3, factory, newMessage, param1, param1);
	}

	default LObjIntLongFunction<T, R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2, a3) -> apply(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	default R apply(T a1, int a2, long a3, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LObjIntLongFunction<T, R> trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2, a3) -> apply(a1, a2, a3, factory);
	}

	default R applyThen(T a1, int a2, long a3, @Nonnull LFunction<Throwable, R> handler) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.apply(e);
		}
	}

	default LObjIntLongFunction<T, R> tryingThen(@Nonnull LFunction<Throwable, R> handler) {
		return (a1, a2, a3) -> applyThen(a1, a2, a3, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default R nestingApply(T a1, int a2, long a3) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default R shovingApply(T a1, int a2, long a3) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T, R> R shovingApply(T a1, int a2, long a3, LObjIntLongFunction<T, R> func) {
		Null.nonNullArg(func, "func");
		return func.shovingApply(a1, a2, a3);
	}

	static <T, R> R handlingApply(T a1, int a2, long a3, LObjIntLongFunction<T, R> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApply(a1, a2, a3, handling);
	}

	static <T, R> R tryApply(T a1, int a2, long a3, LObjIntLongFunction<T, R> func) {
		Null.nonNullArg(func, "func");
		return func.nestingApply(a1, a2, a3);
	}

	static <T, R> R tryApply(T a1, int a2, long a3, LObjIntLongFunction<T, R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, factory, newMessage);
	}

	static <T, R> R tryApply(T a1, int a2, long a3, LObjIntLongFunction<T, R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, factory, newMessage, param1);
	}

	static <T, R> R tryApply(T a1, int a2, long a3, LObjIntLongFunction<T, R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, factory, newMessage, param1, param2);
	}

	static <T, R> R tryApply(T a1, int a2, long a3, LObjIntLongFunction<T, R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	static <T, R> R tryApply(T a1, int a2, long a3, LObjIntLongFunction<T, R> func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, factory);
	}

	static <T, R> R tryApplyThen(T a1, int a2, long a3, LObjIntLongFunction<T, R> func, @Nonnull LFunction<Throwable, R> handler) {
		Null.nonNullArg(func, "func");
		return func.applyThen(a1, a2, a3, handler);
	}

	default R failSafeApply(T a1, int a2, long a3, @Nonnull LObjIntLongFunction<T, R> failSafe) {
		try {
			return apply(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.apply(a1, a2, a3);
		}
	}

	static <T, R> R failSafeApply(T a1, int a2, long a3, LObjIntLongFunction<T, R> func, @Nonnull LObjIntLongFunction<T, R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.apply(a1, a2, a3);
		} else {
			return func.failSafeApply(a1, a2, a3, failSafe);
		}
	}

	static <T, R> LObjIntLongFunction<T, R> failSafe(LObjIntLongFunction<T, R> func, @Nonnull LObjIntLongFunction<T, R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeApply(a1, a2, a3, func, failSafe);
	}

	LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullApply() method cannot be null (" + DESCRIPTION + ").";

	/** Function call that ensures the result is not null */
	@Nonnull
	default R nonNullApply(T a1, int a2, long a3) {
		return Null.nonNull(apply(a1, a2, a3), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LObjIntLongFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T, R> void fromTo(int min_i, int max_i, T a1, int a2, long a3, @Nonnull LObjIntLongFunction<T, R> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.apply(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.apply(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T, R> void fromTill(int min_i, int max_i, T a1, int a2, long a3, @Nonnull LObjIntLongFunction<T, R> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.apply(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.apply(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T, R> void times(int max_i, T a1, int a2, long a3, @Nonnull LObjIntLongFunction<T, R> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, a3, func);
	}

	/** Extract and apply function. */
	public static <R, M, K, V> R from(@Nonnull M container, LBiFunction<M, K, V> extractor, K key, int a2, long a3, @Nonnull LObjIntLongFunction<V, R> function) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.apply(value, a2, a3);
		}

		return null;
	}

	default LOiFunction<T, R> rShrink(@Nonnull LOiToLongFunction<T> right) {
		Null.nonNullArg(right, "right");
		return (a1, a2) -> apply(a1, a2, right.applyAsLong(a1, a2));
	}

	default LOiFunction<T, R> rShrink_(long a3) {
		return (a1, a2) -> apply(a1, a2, a3);
	}

	public static <T, R> LOiFunction<T, R> rShrunken(@Nonnull LOiToLongFunction<T> right, @Nonnull LObjIntLongFunction<T, R> func) {
		Null.nonNullArg(right, "right");
		Null.nonNullArg(func, "func");
		return func.rShrink(right);
	}

	public static <T, R> LOiFunction<T, R> rShrunken_(long a3, @Nonnull LObjIntLongFunction<T, R> func) {
		Null.nonNullArg(func, "func");
		return func.rShrink_(a3);
	}

	/**  */
	public static <T, R> LObjIntLongFunction<T, R> uncurry(@Nonnull LFunction<T, LIntFunction<LLongFunction<R>>> func) {
		Null.nonNullArg(func, "func");
		return (T a1, int a2, long a3) -> func.apply(a1).apply(a2).apply(a3);
	}

	/** Cast that removes generics. */
	default LObjIntLongFunction untyped() {
		return this;
	}

	/** Cast that replace generics. */
	default <V2, V3> LObjIntLongFunction<V2, V3> cast() {
		return untyped();
	}

	/** Cast that replace generics. */
	public static <V2, V3> LObjIntLongFunction<V2, V3> cast(LObjIntLongFunction<?, ?> function) {
		return (LObjIntLongFunction) function;
	}

	/** Change function to consumer that ignores output. */
	default LTieLongConsumer<T> toConsumer() {
		return this::apply;
	}

	/** Calls domain consumer before main function. */
	default LObjIntLongFunction<T, R> beforeDo(@Nonnull LTieLongConsumer<T> before) {
		Null.nonNullArg(before, "before");
		return (T a1, int a2, long a3) -> {
			before.accept(a1, a2, a3);
			return apply(a1, a2, a3);
		};
	}

	/** Calls codomain consumer after main function. */
	default LObjIntLongFunction<T, R> afterDo(@Nonnull LConsumer<R> after) {
		Null.nonNullArg(after, "after");
		return (T a1, int a2, long a3) -> {
			final R retval = apply(a1, a2, a3);
			after.accept(retval);
			return retval;
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplier<R> capture(T a1, int a2, long a3) {
		return () -> this.apply(a1, a2, a3);
	}

	/** Creates function that always returns the same value. */
	static <T, R> LObjIntLongFunction<T, R> constant(R r) {
		return (a1, a2, a3) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T, R> LObjIntLongFunction<T, R> apply1st(@Nonnull LFunction<T, R> func) {
		return (a1, a2, a3) -> func.apply(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T, R> LObjIntLongFunction<T, R> apply2nd(@Nonnull LIntFunction<R> func) {
		return (a1, a2, a3) -> func.apply(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T, R> LObjIntLongFunction<T, R> apply3rd(@Nonnull LLongFunction<R> func) {
		return (a1, a2, a3) -> func.apply(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, R> LObjIntLongFunction<T, R> objIntLongFunc(final @Nonnull LObjIntLongFunction<T, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** A completely inconvenient method in case lambda expression and generic arguments are ambiguous for the compiler. */
	@Nonnull
	static <T, R> LObjIntLongFunction<T, R> objIntLongFunc(@Nullable Class<T> c1, @Nullable Class<R> c2, final @Nonnull LObjIntLongFunction<T, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	final class S<T, R> implements LObjIntLongFunction<T, R> {
		private LObjIntLongFunction<T, R> target = null;
		@Override
		public R applyX(T a1, int a2, long a3) throws Throwable {
			return target.applyX(a1, a2, a3);
		}
	}

	@Nonnull
	static <T, R> LObjIntLongFunction<T, R> recursive(final @Nonnull LFunction<LObjIntLongFunction<T, R>, LObjIntLongFunction<T, R>> selfLambda) {
		final S<T, R> single = new S();
		LObjIntLongFunction<T, R> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	public static <T, R> M<T, R> mementoOf(T a1, int a2, long a3, LObjIntLongFunction<T, R> function) {
		var initialValue = function.apply(a1, a2, a3);
		return initializedMementoOf(initialValue, function);
	}

	public static <T, R> M<T, R> initializedMementoOf(R initialValue, LObjIntLongFunction<T, R> function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static <T, R> M<T, R> deltaOf(T a1, int a2, long a3, LObjIntLongFunction<T, R> function, LBinaryOperator<R> deltaFunction) {
		var initialValue = function.apply(a1, a2, a3);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static <T, R> M<T, R> initializedDeltaOf(R initialValue, LObjIntLongFunction<T, R> function, LBinaryOperator<R> deltaFunction) {
		return memento(initialValue, deltaFunction.apply(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.apply(x1, x2));
	}

	public static <T, R> M<T, R> memento(R initialBaseValue, R initialValue, LObjIntLongFunction<T, R> baseFunction, LTernaryOperator<R> mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LObjIntLongFunction.M)
	 */
	@NotThreadSafe
	final class M<T, R> implements LObjIntLongFunction<T, R> {

		private final LObjIntLongFunction<T, R> baseFunction;
		private R lastBaseValue;
		private R lastValue;
		private final LTernaryOperator<R> mementoFunction;

		private M(R lastBaseValue, R lastValue, LObjIntLongFunction<T, R> baseFunction, LTernaryOperator<R> mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public R applyX(T a1, int a2, long a3) throws Throwable {
			R x1 = lastBaseValue;
			R x2 = lastBaseValue = baseFunction.applyX(a1, a2, a3);

			return lastValue = mementoFunction.apply(lastValue, x1, x2);
		}

		public R lastValue() {
			return lastValue;
		};

		public R lastBaseValue() {
			return lastBaseValue;
		};
	}

	@Nonnull
	static <T, R> LObjIntLongFunction<T, R> objIntLongFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T, R> LObjIntLongFunction<T, R> objIntLongFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, R> LObjIntLongFunction.LObjLongIntFunc<T, R> objLongIntFunc(final @Nonnull LObjIntLongFunction.LObjLongIntFunc<T, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, R> LObjIntLongFunction.LIntObjLongFunc<T, R> intObjLongFunc(final @Nonnull LObjIntLongFunction.LIntObjLongFunc<T, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, R> LObjIntLongFunction.LIntLongObjFunc<T, R> intLongObjFunc(final @Nonnull LObjIntLongFunction.LIntLongObjFunc<T, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, R> LObjIntLongFunction.LLongObjIntFunc<T, R> longObjIntFunc(final @Nonnull LObjIntLongFunction.LLongObjIntFunc<T, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, R> LObjIntLongFunction.LLongIntObjFunc<T, R> longIntObjFunc(final @Nonnull LObjIntLongFunction.LLongIntObjFunc<T, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T, R> R call(T a1, int a2, long a3, final @Nonnull LObjIntLongFunction<T, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.apply(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1> LObjIntLongFunction<V1, R> compose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2, @Nonnull final LLongUnaryOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.apply(before1.apply(v1), before2.applyAsInt(v2), before3.applyAsLong(v3));
	}

	public static <V1, T, R> LObjIntLongFunction<V1, R> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2, @Nonnull final LLongUnaryOperator before3, LObjIntLongFunction<T, R> after) {
		return after.compose(before1, before2, before3);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriFunction<V1, V2, V3, R> objIntLongFuncCompose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2, @Nonnull final LToLongFunction<? super V3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.apply(before1.apply(v1), before2.applyAsInt(v2), before3.applyAsLong(v3));
	}

	public static <V1, V2, V3, T, R> LTriFunction<V1, V2, V3, R> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2, @Nonnull final LToLongFunction<? super V3> before3,
			LObjIntLongFunction<T, R> after) {
		return after.objIntLongFuncCompose(before1, before2, before3);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LObjIntLongFunction<T, V> then(@Nonnull LFunction<? super R, ? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.apply(this.apply(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LTieLongConsumer<T> thenConsume(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.accept(this.apply(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LTieLongFunction<T> thenToInt(@Nonnull LToIntFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.applyAsInt(this.apply(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LObjIntLongPredicate<T> thenToBool(@Nonnull LPredicate<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.test(this.apply(a1, a2, a3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LObjIntLongFunction<T, R> nonNullable() {
		return this::nonNullApply;
	}

	// <editor-fold desc="interface variants">

	/** Permutation of LObjIntLongFunction for method references. */
	@FunctionalInterface
	interface LObjLongIntFunc<T, R> extends LObjIntLongFunction<T, R> {

		/**
		 * Implement this, but call apply(T a1,int a2,long a3)
		 */
		default R applyX(T a1, int a2, long a3) {
			return this.applyObjLongInt(a1, a3, a2);
		}

		@Nullable
		// R applyObjLongInt(T a1,long a3,int a2) ;
		default R applyObjLongInt(T a1, long a3, int a2) {
			// return nestingApplyObjLongInt(a1,a3,a2);
			try {
				return this.applyObjLongIntX(a1, a3, a2);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyObjLongInt(T a1,long a3,int a2)
		 */
		R applyObjLongIntX(T a1, long a3, int a2) throws Throwable;
	}

	/** Permutation of LObjIntLongFunction for method references. */
	@FunctionalInterface
	interface LIntObjLongFunc<T, R> extends LObjIntLongFunction<T, R> {

		/**
		 * Implement this, but call applyObjLongInt(T a1,long a3,int a2)
		 */
		default R applyX(T a1, int a2, long a3) {
			return this.applyIntObjLong(a2, a1, a3);
		}

		@Nullable
		// R applyIntObjLong(int a2,T a1,long a3) ;
		default R applyIntObjLong(int a2, T a1, long a3) {
			// return nestingApplyIntObjLong(a2,a1,a3);
			try {
				return this.applyIntObjLongX(a2, a1, a3);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyIntObjLong(int a2,T a1,long a3)
		 */
		R applyIntObjLongX(int a2, T a1, long a3) throws Throwable;
	}

	/** Permutation of LObjIntLongFunction for method references. */
	@FunctionalInterface
	interface LIntLongObjFunc<T, R> extends LObjIntLongFunction<T, R> {

		/**
		 * Implement this, but call applyIntObjLong(int a2,T a1,long a3)
		 */
		default R applyX(T a1, int a2, long a3) {
			return this.applyIntLongObj(a2, a3, a1);
		}

		@Nullable
		// R applyIntLongObj(int a2,long a3,T a1) ;
		default R applyIntLongObj(int a2, long a3, T a1) {
			// return nestingApplyIntLongObj(a2,a3,a1);
			try {
				return this.applyIntLongObjX(a2, a3, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyIntLongObj(int a2,long a3,T a1)
		 */
		R applyIntLongObjX(int a2, long a3, T a1) throws Throwable;
	}

	/** Permutation of LObjIntLongFunction for method references. */
	@FunctionalInterface
	interface LLongObjIntFunc<T, R> extends LObjIntLongFunction<T, R> {

		/**
		 * Implement this, but call applyIntLongObj(int a2,long a3,T a1)
		 */
		default R applyX(T a1, int a2, long a3) {
			return this.applyLongObjInt(a3, a1, a2);
		}

		@Nullable
		// R applyLongObjInt(long a3,T a1,int a2) ;
		default R applyLongObjInt(long a3, T a1, int a2) {
			// return nestingApplyLongObjInt(a3,a1,a2);
			try {
				return this.applyLongObjIntX(a3, a1, a2);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyLongObjInt(long a3,T a1,int a2)
		 */
		R applyLongObjIntX(long a3, T a1, int a2) throws Throwable;
	}

	/** Permutation of LObjIntLongFunction for method references. */
	@FunctionalInterface
	interface LLongIntObjFunc<T, R> extends LObjIntLongFunction<T, R> {

		/**
		 * Implement this, but call applyLongObjInt(long a3,T a1,int a2)
		 */
		default R applyX(T a1, int a2, long a3) {
			return this.applyLongIntObj(a3, a2, a1);
		}

		@Nullable
		// R applyLongIntObj(long a3,int a2,T a1) ;
		default R applyLongIntObj(long a3, int a2, T a1) {
			// return nestingApplyLongIntObj(a3,a2,a1);
			try {
				return this.applyLongIntObjX(a3, a2, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyLongIntObj(long a3,int a2,T a1)
		 */
		R applyLongIntObjX(long a3, int a2, T a1) throws Throwable;
	}

	// </editor-fold>

	/** Does nothing (LObjIntLongFunction) Function */
	public static <T, R> R doNothing(T a1, int a2, long a3) {
		return (R) Function4U.defaultObject;
	}

	/** Does nothing (LObjIntLongFunction.LObjLongIntFunc) Function */
	public static <T, R> R doNothing(T a1, long a3, int a2) {
		return (R) Function4U.defaultObject;
	}

	/** Does nothing (LObjIntLongFunction.LIntObjLongFunc) Function */
	public static <T, R> R doNothing(int a2, T a1, long a3) {
		return (R) Function4U.defaultObject;
	}

	/** Does nothing (LObjIntLongFunction.LIntLongObjFunc) Function */
	public static <T, R> R doNothing(int a2, long a3, T a1) {
		return (R) Function4U.defaultObject;
	}

	/** Does nothing (LObjIntLongFunction.LLongObjIntFunc) Function */
	public static <T, R> R doNothing(long a3, T a1, int a2) {
		return (R) Function4U.defaultObject;
	}

	/** Does nothing (LObjIntLongFunction.LLongIntObjFunc) Function */
	public static <T, R> R doNothing(long a3, int a2, T a1) {
		return (R) Function4U.defaultObject;
	}

}
