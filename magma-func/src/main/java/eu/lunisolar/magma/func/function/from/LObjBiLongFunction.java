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
 * Non-throwing functional interface (lambda) LObjBiLongFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 3): T a1,long a2,long a3
 *
 * Co-domain: R
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LObjBiLongFunction<T, R> extends MetaFunction, MetaInterface.NonThrowing, Codomain<a<R>>, Domain3<a<T>, aLong, aLong> { // NOSONAR

	String DESCRIPTION = "LObjBiLongFunction: R apply(T a1,long a2,long a3)";

	@Nullable
	// R apply(T a1,long a2,long a3) ;
	default R apply(T a1, long a2, long a3) {
		// return nestingApply(a1,a2,a3);
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call apply(T a1,long a2,long a3)
	 */
	R applyX(T a1, long a2, long a3) throws Throwable;

	default R tupleApply(LObjBiLongTriple<T> args) {
		return apply(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions according to the instructions. */
	default R handlingApply(T a1, long a2, long a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LObjBiLongFunction<T, R> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> handlingApply(a1, a2, a3, handling);
	}

	default R apply(T a1, long a2, long a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default R apply(T a1, long a2, long a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default R apply(T a1, long a2, long a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default R apply(T a1, long a2, long a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LObjBiLongFunction<T, R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2, a3) -> apply(a1, a2, a3, factory, newMessage);
	}

	default LObjBiLongFunction<T, R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2, a3) -> apply(a1, a2, a3, factory, newMessage, param1);
	}

	default LObjBiLongFunction<T, R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2, a3) -> apply(a1, a2, a3, factory, newMessage, param1, param1);
	}

	default LObjBiLongFunction<T, R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2, a3) -> apply(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	default R apply(T a1, long a2, long a3, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LObjBiLongFunction<T, R> trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2, a3) -> apply(a1, a2, a3, factory);
	}

	default R applyThen(T a1, long a2, long a3, @Nonnull LFunction<Throwable, R> handler) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.apply(e);
		}
	}

	default LObjBiLongFunction<T, R> tryingThen(@Nonnull LFunction<Throwable, R> handler) {
		return (a1, a2, a3) -> applyThen(a1, a2, a3, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default R nestingApply(T a1, long a2, long a3) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default R shovingApply(T a1, long a2, long a3) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T, R> R shovingApply(T a1, long a2, long a3, LObjBiLongFunction<T, R> func) {
		Null.nonNullArg(func, "func");
		return func.shovingApply(a1, a2, a3);
	}

	static <T, R> R handlingApply(T a1, long a2, long a3, LObjBiLongFunction<T, R> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApply(a1, a2, a3, handling);
	}

	static <T, R> R tryApply(T a1, long a2, long a3, LObjBiLongFunction<T, R> func) {
		Null.nonNullArg(func, "func");
		return func.nestingApply(a1, a2, a3);
	}

	static <T, R> R tryApply(T a1, long a2, long a3, LObjBiLongFunction<T, R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, factory, newMessage);
	}

	static <T, R> R tryApply(T a1, long a2, long a3, LObjBiLongFunction<T, R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, factory, newMessage, param1);
	}

	static <T, R> R tryApply(T a1, long a2, long a3, LObjBiLongFunction<T, R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, factory, newMessage, param1, param2);
	}

	static <T, R> R tryApply(T a1, long a2, long a3, LObjBiLongFunction<T, R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	static <T, R> R tryApply(T a1, long a2, long a3, LObjBiLongFunction<T, R> func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, factory);
	}

	static <T, R> R tryApplyThen(T a1, long a2, long a3, LObjBiLongFunction<T, R> func, @Nonnull LFunction<Throwable, R> handler) {
		Null.nonNullArg(func, "func");
		return func.applyThen(a1, a2, a3, handler);
	}

	default R failSafeApply(T a1, long a2, long a3, @Nonnull LObjBiLongFunction<T, R> failSafe) {
		try {
			return apply(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.apply(a1, a2, a3);
		}
	}

	static <T, R> R failSafeApply(T a1, long a2, long a3, LObjBiLongFunction<T, R> func, @Nonnull LObjBiLongFunction<T, R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.apply(a1, a2, a3);
		} else {
			return func.failSafeApply(a1, a2, a3, failSafe);
		}
	}

	static <T, R> LObjBiLongFunction<T, R> failSafe(LObjBiLongFunction<T, R> func, @Nonnull LObjBiLongFunction<T, R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeApply(a1, a2, a3, func, failSafe);
	}

	LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullApply() method cannot be null (" + DESCRIPTION + ").";

	/** Function call that ensures the result is not null */
	@Nonnull
	default R nonNullApply(T a1, long a2, long a3) {
		return Null.nonNull(apply(a1, a2, a3), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LObjBiLongFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T, R> void fromTo(int min_i, int max_i, T a1, long a2, long a3, @Nonnull LObjBiLongFunction<T, R> func) {
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
	public static <T, R> void fromTill(int min_i, int max_i, T a1, long a2, long a3, @Nonnull LObjBiLongFunction<T, R> func) {
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
	public static <T, R> void times(int max_i, T a1, long a2, long a3, @Nonnull LObjBiLongFunction<T, R> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, a3, func);
	}

	/** Extract and apply function. */
	public static <R, M, K, V> R from(@Nonnull M container, LBiFunction<M, K, V> extractor, K key, long a2, long a3, @Nonnull LObjBiLongFunction<V, R> function) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.apply(value, a2, a3);
		}

		return null;
	}

	default LBiLongFunction<R> lShrink(@Nonnull LBiLongFunction<T> left) {
		Null.nonNullArg(left, "left");
		return (a2, a3) -> apply(left.apply(a2, a3), a2, a3);
	}

	default LBiLongFunction<R> lShrink_(T a1) {
		return (a2, a3) -> apply(a1, a2, a3);
	}

	public static <R, T> LBiLongFunction<R> lShrunken(@Nonnull LBiLongFunction<T> left, @Nonnull LObjBiLongFunction<T, R> func) {
		Null.nonNullArg(left, "left");
		Null.nonNullArg(func, "func");
		return func.lShrink(left);
	}

	public static <R, T> LBiLongFunction<R> lShrunken_(T a1, @Nonnull LObjBiLongFunction<T, R> func) {
		Null.nonNullArg(func, "func");
		return func.lShrink_(a1);
	}

	default LObjLongFunction<T, R> rShrink_(long a3) {
		return (a1, a2) -> apply(a1, a2, a3);
	}

	public static <T, R> LObjLongFunction<T, R> rShrunken_(long a3, @Nonnull LObjBiLongFunction<T, R> func) {
		Null.nonNullArg(func, "func");
		return func.rShrink_(a3);
	}

	/**  */
	public static <T, R> LObjBiLongFunction<T, R> uncurry(@Nonnull LFunction<T, LLongFunction<LLongFunction<R>>> func) {
		Null.nonNullArg(func, "func");
		return (T a1, long a2, long a3) -> func.apply(a1).apply(a2).apply(a3);
	}

	/** Cast that removes generics. */
	default LObjBiLongFunction untyped() {
		return this;
	}

	/** Cast that replace generics. */
	default <V2, V3> LObjBiLongFunction<V2, V3> cast() {
		return untyped();
	}

	/** Cast that replace generics. */
	public static <V2, V3> LObjBiLongFunction<V2, V3> cast(LObjBiLongFunction<?, ?> function) {
		return (LObjBiLongFunction) function;
	}

	/** Change function to consumer that ignores output. */
	default LObjBiLongConsumer<T> toConsumer() {
		return this::apply;
	}

	/** Calls domain consumer before main function. */
	default LObjBiLongFunction<T, R> beforeDo(@Nonnull LObjBiLongConsumer<T> before) {
		Null.nonNullArg(before, "before");
		return (T a1, long a2, long a3) -> {
			before.accept(a1, a2, a3);
			return apply(a1, a2, a3);
		};
	}

	/** Calls codomain consumer after main function. */
	default LObjBiLongFunction<T, R> afterDo(@Nonnull LConsumer<R> after) {
		Null.nonNullArg(after, "after");
		return (T a1, long a2, long a3) -> {
			final R retval = apply(a1, a2, a3);
			after.accept(retval);
			return retval;
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplier<R> capture(T a1, long a2, long a3) {
		return () -> this.apply(a1, a2, a3);
	}

	/** Creates function that always returns the same value. */
	static <T, R> LObjBiLongFunction<T, R> constant(R r) {
		return (a1, a2, a3) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T, R> LObjBiLongFunction<T, R> apply1st(@Nonnull LFunction<T, R> func) {
		return (a1, a2, a3) -> func.apply(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T, R> LObjBiLongFunction<T, R> apply2nd(@Nonnull LLongFunction<R> func) {
		return (a1, a2, a3) -> func.apply(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T, R> LObjBiLongFunction<T, R> apply3rd(@Nonnull LLongFunction<R> func) {
		return (a1, a2, a3) -> func.apply(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, R> LObjBiLongFunction<T, R> objBiLongFunc(final @Nonnull LObjBiLongFunction<T, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** A completely inconvenient method in case lambda expression and generic arguments are ambiguous for the compiler. */
	@Nonnull
	static <T, R> LObjBiLongFunction<T, R> objBiLongFunc(@Nullable Class<T> c1, @Nullable Class<R> c2, final @Nonnull LObjBiLongFunction<T, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	final class S<T, R> implements LObjBiLongFunction<T, R> {
		private LObjBiLongFunction<T, R> target = null;
		@Override
		public R applyX(T a1, long a2, long a3) throws Throwable {
			return target.applyX(a1, a2, a3);
		}
	}

	@Nonnull
	static <T, R> LObjBiLongFunction<T, R> recursive(final @Nonnull LFunction<LObjBiLongFunction<T, R>, LObjBiLongFunction<T, R>> selfLambda) {
		final S<T, R> single = new S();
		LObjBiLongFunction<T, R> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	public static <T, R> M<T, R> mementoOf(T a1, long a2, long a3, LObjBiLongFunction<T, R> function) {
		var initialValue = function.apply(a1, a2, a3);
		return initializedMementoOf(initialValue, function);
	}

	public static <T, R> M<T, R> initializedMementoOf(R initialValue, LObjBiLongFunction<T, R> function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static <T, R> M<T, R> deltaOf(T a1, long a2, long a3, LObjBiLongFunction<T, R> function, LBinaryOperator<R> deltaFunction) {
		var initialValue = function.apply(a1, a2, a3);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static <T, R> M<T, R> initializedDeltaOf(R initialValue, LObjBiLongFunction<T, R> function, LBinaryOperator<R> deltaFunction) {
		return memento(initialValue, deltaFunction.apply(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.apply(x1, x2));
	}

	public static <T, R> M<T, R> memento(R initialBaseValue, R initialValue, LObjBiLongFunction<T, R> baseFunction, LTernaryOperator<R> mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LObjBiLongFunction.M)
	 */
	@NotThreadSafe
	final class M<T, R> implements LObjBiLongFunction<T, R> {

		private final LObjBiLongFunction<T, R> baseFunction;
		private R lastBaseValue;
		private R lastValue;
		private final LTernaryOperator<R> mementoFunction;

		private M(R lastBaseValue, R lastValue, LObjBiLongFunction<T, R> baseFunction, LTernaryOperator<R> mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public R applyX(T a1, long a2, long a3) throws Throwable {
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
	static <T, R> LObjBiLongFunction<T, R> objBiLongFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T, R> LObjBiLongFunction<T, R> objBiLongFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, R> LObjBiLongFunction.LObj0Long2Long1Func<T, R> obj0Long2Long1Func(final @Nonnull LObjBiLongFunction.LObj0Long2Long1Func<T, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, R> LObjBiLongFunction.LLong1Obj0Long2Func<T, R> long1Obj0Long2Func(final @Nonnull LObjBiLongFunction.LLong1Obj0Long2Func<T, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, R> LObjBiLongFunction.LLong1Long2Obj0Func<T, R> long1Long2Obj0Func(final @Nonnull LObjBiLongFunction.LLong1Long2Obj0Func<T, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, R> LObjBiLongFunction.LLong2Obj0Long1Func<T, R> long2Obj0Long1Func(final @Nonnull LObjBiLongFunction.LLong2Obj0Long1Func<T, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, R> LObjBiLongFunction.LBiLong1Obj0Func<T, R> biLong1Obj0Func(final @Nonnull LObjBiLongFunction.LBiLong1Obj0Func<T, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T, R> R call(T a1, long a2, long a3, final @Nonnull LObjBiLongFunction<T, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.apply(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as doNothing). */
	@Nonnull
	static <T, R> LObjBiLongFunction<T, R> safe() {
		return LObjBiLongFunction::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T, R> LSupplier<LObjBiLongFunction<T, R>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T, R> LObjBiLongFunction<T, R> safe(final @Nullable LObjBiLongFunction<T, R> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T, R> LSupplier<LObjBiLongFunction<T, R>> safeSupplier(final @Nullable LSupplier<LObjBiLongFunction<T, R>> supplier) {
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
	default <V1> LObjBiLongFunction<V1, R> compose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LLongUnaryOperator before2, @Nonnull final LLongUnaryOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.apply(before1.apply(v1), before2.applyAsLong(v2), before3.applyAsLong(v3));
	}

	public static <V1, T, R> LObjBiLongFunction<V1, R> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LLongUnaryOperator before2, @Nonnull final LLongUnaryOperator before3, LObjBiLongFunction<T, R> after) {
		return after.compose(before1, before2, before3);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriFunction<V1, V2, V3, R> objBiLongFuncCompose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToLongFunction<? super V2> before2, @Nonnull final LToLongFunction<? super V3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.apply(before1.apply(v1), before2.applyAsLong(v2), before3.applyAsLong(v3));
	}

	public static <V1, V2, V3, T, R> LTriFunction<V1, V2, V3, R> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToLongFunction<? super V2> before2, @Nonnull final LToLongFunction<? super V3> before3,
			LObjBiLongFunction<T, R> after) {
		return after.objBiLongFuncCompose(before1, before2, before3);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LObjBiLongFunction<T, V> then(@Nonnull LFunction<? super R, ? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.apply(this.apply(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LObjBiLongConsumer<T> thenConsume(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.accept(this.apply(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LObjBiLongPredicate<T> thenToBool(@Nonnull LPredicate<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.test(this.apply(a1, a2, a3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LObjBiLongFunction<T, R> nonNullable() {
		return this::nonNullApply;
	}

	// <editor-fold desc="interface variants">

	/** Permutation of LObjBiLongFunction for method references. */
	@FunctionalInterface
	interface LObj0Long2Long1Func<T, R> extends LObjBiLongFunction<T, R> {

		/**
		 * Implement this, but call apply(T a1,long a2,long a3)
		 */
		default R applyX(T a1, long a2, long a3) {
			return this.applyObj0Long2Long1(a1, a3, a2);
		}

		@Nullable
		// R applyObj0Long2Long1(T a1,long a3,long a2) ;
		default R applyObj0Long2Long1(T a1, long a3, long a2) {
			// return nestingApplyObj0Long2Long1(a1,a3,a2);
			try {
				return this.applyObj0Long2Long1X(a1, a3, a2);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyObj0Long2Long1(T a1,long a3,long a2)
		 */
		R applyObj0Long2Long1X(T a1, long a3, long a2) throws Throwable;
	}

	/** Permutation of LObjBiLongFunction for method references. */
	@FunctionalInterface
	interface LLong1Obj0Long2Func<T, R> extends LObjBiLongFunction<T, R> {

		/**
		 * Implement this, but call applyObj0Long2Long1(T a1,long a3,long a2)
		 */
		default R applyX(T a1, long a2, long a3) {
			return this.applyLong1Obj0Long2(a2, a1, a3);
		}

		@Nullable
		// R applyLong1Obj0Long2(long a2,T a1,long a3) ;
		default R applyLong1Obj0Long2(long a2, T a1, long a3) {
			// return nestingApplyLong1Obj0Long2(a2,a1,a3);
			try {
				return this.applyLong1Obj0Long2X(a2, a1, a3);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyLong1Obj0Long2(long a2,T a1,long a3)
		 */
		R applyLong1Obj0Long2X(long a2, T a1, long a3) throws Throwable;
	}

	/** Permutation of LObjBiLongFunction for method references. */
	@FunctionalInterface
	interface LLong1Long2Obj0Func<T, R> extends LObjBiLongFunction<T, R> {

		/**
		 * Implement this, but call applyLong1Obj0Long2(long a2,T a1,long a3)
		 */
		default R applyX(T a1, long a2, long a3) {
			return this.applyLong1Long2Obj0(a2, a3, a1);
		}

		@Nullable
		// R applyLong1Long2Obj0(long a2,long a3,T a1) ;
		default R applyLong1Long2Obj0(long a2, long a3, T a1) {
			// return nestingApplyLong1Long2Obj0(a2,a3,a1);
			try {
				return this.applyLong1Long2Obj0X(a2, a3, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyLong1Long2Obj0(long a2,long a3,T a1)
		 */
		R applyLong1Long2Obj0X(long a2, long a3, T a1) throws Throwable;
	}

	/** Permutation of LObjBiLongFunction for method references. */
	@FunctionalInterface
	interface LLong2Obj0Long1Func<T, R> extends LObjBiLongFunction<T, R> {

		/**
		 * Implement this, but call applyLong1Long2Obj0(long a2,long a3,T a1)
		 */
		default R applyX(T a1, long a2, long a3) {
			return this.applyLong2Obj0Long1(a3, a1, a2);
		}

		@Nullable
		// R applyLong2Obj0Long1(long a3,T a1,long a2) ;
		default R applyLong2Obj0Long1(long a3, T a1, long a2) {
			// return nestingApplyLong2Obj0Long1(a3,a1,a2);
			try {
				return this.applyLong2Obj0Long1X(a3, a1, a2);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyLong2Obj0Long1(long a3,T a1,long a2)
		 */
		R applyLong2Obj0Long1X(long a3, T a1, long a2) throws Throwable;
	}

	/** Permutation of LObjBiLongFunction for method references. */
	@FunctionalInterface
	interface LBiLong1Obj0Func<T, R> extends LObjBiLongFunction<T, R> {

		/**
		 * Implement this, but call applyLong2Obj0Long1(long a3,T a1,long a2)
		 */
		default R applyX(T a1, long a2, long a3) {
			return this.applyBiLong1Obj0(a3, a2, a1);
		}

		@Nullable
		// R applyBiLong1Obj0(long a3,long a2,T a1) ;
		default R applyBiLong1Obj0(long a3, long a2, T a1) {
			// return nestingApplyBiLong1Obj0(a3,a2,a1);
			try {
				return this.applyBiLong1Obj0X(a3, a2, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyBiLong1Obj0(long a3,long a2,T a1)
		 */
		R applyBiLong1Obj0X(long a3, long a2, T a1) throws Throwable;
	}

	// </editor-fold>

	/** Does nothing (LObjBiLongFunction) Function */
	public static <T, R> R doNothing(T a1, long a2, long a3) {
		return (R) Function4U.defaultObject;
	}

	/** Does nothing (LObjBiLongFunction.LLong1Obj0Long2Func) Function */
	public static <T, R> R doNothing(long a2, T a1, long a3) {
		return (R) Function4U.defaultObject;
	}

	/** Does nothing (LObjBiLongFunction.LLong1Long2Obj0Func) Function */
	public static <T, R> R doNothing(long a2, long a3, T a1) {
		return (R) Function4U.defaultObject;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, C3> void forEach(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aLong> ia2, C2 source2, IndexedRead<C3, aLong> ia3, C3 source3, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToLongFunction<Object> oiFunc2 = (LOiToLongFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToLongFunction<Object> oiFunc3 = (LOiToLongFunction) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			T a1 = oiFunc1.apply(source1, i);
			long a2 = oiFunc2.applyAsLong(source2, i);
			long a3 = oiFunc3.applyAsLong(source3, i);
			consumer.accept(this.apply(a1, a2, a3));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, C3> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aLong> ia2, C2 source2, IndexedRead<C3, aLong> ia3, C3 source3, LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToLongFunction<Object> oiFunc2 = (LOiToLongFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToLongFunction<Object> oiFunc3 = (LOiToLongFunction) ia3.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			T a1 = nextFunc1.apply(iterator1);
			long a2 = oiFunc2.applyAsLong(source2, i);
			long a3 = oiFunc3.applyAsLong(source3, i);
			consumer.accept(this.apply(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2, C3> void iterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aLong> sa2, C2 source2, IndexedRead<C3, aLong> ia3, C3 source3, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToLongFunction<Object> nextFunc2 = (LToLongFunction) sa2.supplier();
		size = Integer.min(size, ia3.size(source3));
		LOiToLongFunction<Object> oiFunc3 = (LOiToLongFunction) ia3.getter();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			T a1 = oiFunc1.apply(source1, i);
			long a2 = nextFunc2.applyAsLong(iterator2);
			long a3 = oiFunc3.applyAsLong(source3, i);
			consumer.accept(this.apply(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, I2, C3> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aLong> sa2, C2 source2, IndexedRead<C3, aLong> ia3, C3 source3, LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToLongFunction<Object> nextFunc2 = (LToLongFunction) sa2.supplier();
		int size = ia3.size(source3);
		LOiToLongFunction<Object> oiFunc3 = (LOiToLongFunction) ia3.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && i < size) {
			T a1 = nextFunc1.apply(iterator1);
			long a2 = nextFunc2.applyAsLong(iterator2);
			long a3 = oiFunc3.applyAsLong(source3, i);
			consumer.accept(this.apply(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, C3, I3> void iterate(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aLong> ia2, C2 source2, SequentialRead<C3, I3, aLong> sa3, C3 source3, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToLongFunction<Object> oiFunc2 = (LOiToLongFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToLongFunction<Object> nextFunc3 = (LToLongFunction) sa3.supplier();
		int i = 0;
		while (i < size && testFunc3.test(iterator3)) {
			T a1 = oiFunc1.apply(source1, i);
			long a2 = oiFunc2.applyAsLong(source2, i);
			long a3 = nextFunc3.applyAsLong(iterator3);
			consumer.accept(this.apply(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, C3, I3> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aLong> ia2, C2 source2, SequentialRead<C3, I3, aLong> sa3, C3 source3, LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToLongFunction<Object> oiFunc2 = (LOiToLongFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToLongFunction<Object> nextFunc3 = (LToLongFunction) sa3.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size && testFunc3.test(iterator3)) {
			T a1 = nextFunc1.apply(iterator1);
			long a2 = oiFunc2.applyAsLong(source2, i);
			long a3 = nextFunc3.applyAsLong(iterator3);
			consumer.accept(this.apply(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2, C3, I3> void iterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aLong> sa2, C2 source2, SequentialRead<C3, I3, aLong> sa3, C3 source3, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToLongFunction<Object> nextFunc2 = (LToLongFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToLongFunction<Object> nextFunc3 = (LToLongFunction) sa3.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			T a1 = oiFunc1.apply(source1, i);
			long a2 = nextFunc2.applyAsLong(iterator2);
			long a3 = nextFunc3.applyAsLong(iterator3);
			consumer.accept(this.apply(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C1, I1, C2, I2, C3, I3> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aLong> sa2, C2 source2, SequentialRead<C3, I3, aLong> sa3, C3 source3, LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToLongFunction<Object> nextFunc2 = (LToLongFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToLongFunction<Object> nextFunc3 = (LToLongFunction) sa3.supplier();
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			T a1 = nextFunc1.apply(iterator1);
			long a2 = nextFunc2.applyAsLong(iterator2);
			long a3 = nextFunc3.applyAsLong(iterator3);
			consumer.accept(this.apply(a1, a2, a3));
		}
	}

}
