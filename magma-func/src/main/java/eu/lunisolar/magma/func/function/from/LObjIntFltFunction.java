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
 * Non-throwing functional interface (lambda) LObjIntFltFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 3): T a1,int a2,float a3
 *
 * Co-domain: R
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LObjIntFltFunction<T, R> extends MetaFunction, MetaInterface.NonThrowing, Codomain<a<R>>, Domain3<a<T>, aInt, aFloat> { // NOSONAR

	String DESCRIPTION = "LObjIntFltFunction: R apply(T a1,int a2,float a3)";

	@Nullable
	// R apply(T a1,int a2,float a3) ;
	default R apply(T a1, int a2, float a3) {
		// return nestingApply(a1,a2,a3);
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call apply(T a1,int a2,float a3)
	 */
	R applyX(T a1, int a2, float a3) throws Throwable;

	default R tupleApply(LObjIntFltTriple<T> args) {
		return apply(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions according to the instructions. */
	default R handlingApply(T a1, int a2, float a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LObjIntFltFunction<T, R> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> handlingApply(a1, a2, a3, handling);
	}

	default R apply(T a1, int a2, float a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default R apply(T a1, int a2, float a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default R apply(T a1, int a2, float a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default R apply(T a1, int a2, float a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LObjIntFltFunction<T, R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2, a3) -> apply(a1, a2, a3, factory, newMessage);
	}

	default LObjIntFltFunction<T, R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2, a3) -> apply(a1, a2, a3, factory, newMessage, param1);
	}

	default LObjIntFltFunction<T, R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2, a3) -> apply(a1, a2, a3, factory, newMessage, param1, param1);
	}

	default LObjIntFltFunction<T, R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2, a3) -> apply(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	default R apply(T a1, int a2, float a3, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LObjIntFltFunction<T, R> trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2, a3) -> apply(a1, a2, a3, factory);
	}

	default R applyThen(T a1, int a2, float a3, @Nonnull LFunction<Throwable, R> handler) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.apply(e);
		}
	}

	default LObjIntFltFunction<T, R> tryingThen(@Nonnull LFunction<Throwable, R> handler) {
		return (a1, a2, a3) -> applyThen(a1, a2, a3, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default R nestingApply(T a1, int a2, float a3) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default R shovingApply(T a1, int a2, float a3) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T, R> R shovingApply(T a1, int a2, float a3, LObjIntFltFunction<T, R> func) {
		Null.nonNullArg(func, "func");
		return func.shovingApply(a1, a2, a3);
	}

	static <T, R> R handlingApply(T a1, int a2, float a3, LObjIntFltFunction<T, R> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApply(a1, a2, a3, handling);
	}

	static <T, R> R tryApply(T a1, int a2, float a3, LObjIntFltFunction<T, R> func) {
		Null.nonNullArg(func, "func");
		return func.nestingApply(a1, a2, a3);
	}

	static <T, R> R tryApply(T a1, int a2, float a3, LObjIntFltFunction<T, R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, factory, newMessage);
	}

	static <T, R> R tryApply(T a1, int a2, float a3, LObjIntFltFunction<T, R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, factory, newMessage, param1);
	}

	static <T, R> R tryApply(T a1, int a2, float a3, LObjIntFltFunction<T, R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, factory, newMessage, param1, param2);
	}

	static <T, R> R tryApply(T a1, int a2, float a3, LObjIntFltFunction<T, R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	static <T, R> R tryApply(T a1, int a2, float a3, LObjIntFltFunction<T, R> func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, factory);
	}

	static <T, R> R tryApplyThen(T a1, int a2, float a3, LObjIntFltFunction<T, R> func, @Nonnull LFunction<Throwable, R> handler) {
		Null.nonNullArg(func, "func");
		return func.applyThen(a1, a2, a3, handler);
	}

	default R failSafeApply(T a1, int a2, float a3, @Nonnull LObjIntFltFunction<T, R> failSafe) {
		try {
			return apply(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.apply(a1, a2, a3);
		}
	}

	static <T, R> R failSafeApply(T a1, int a2, float a3, LObjIntFltFunction<T, R> func, @Nonnull LObjIntFltFunction<T, R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.apply(a1, a2, a3);
		} else {
			return func.failSafeApply(a1, a2, a3, failSafe);
		}
	}

	static <T, R> LObjIntFltFunction<T, R> failSafe(LObjIntFltFunction<T, R> func, @Nonnull LObjIntFltFunction<T, R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeApply(a1, a2, a3, func, failSafe);
	}

	LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullApply() method cannot be null (" + DESCRIPTION + ").";

	/** Function call that ensures the result is not null */
	@Nonnull
	default R nonNullApply(T a1, int a2, float a3) {
		return Null.nonNull(apply(a1, a2, a3), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LObjIntFltFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T, R> void fromTo(int min_a2, int max_a2, T a1, float a3, @Nonnull LObjIntFltFunction<T, R> func) {
		Null.nonNullArg(func, "func");
		if (min_a2 <= max_a2) {
			for (int a2 = min_a2; a2 <= max_a2; a2++) {
				func.apply(a1, a2, a3);
			}
		} else {
			for (int a2 = min_a2; a2 >= max_a2; a2--) {
				func.apply(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T, R> void fromTill(int min_a2, int max_a2, T a1, float a3, @Nonnull LObjIntFltFunction<T, R> func) {
		Null.nonNullArg(func, "func");
		if (min_a2 <= max_a2) {
			for (int a2 = min_a2; a2 < max_a2; a2++) {
				func.apply(a1, a2, a3);
			}
		} else {
			for (int a2 = min_a2; a2 > max_a2; a2--) {
				func.apply(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T, R> void times(int max_a2, T a1, float a3, @Nonnull LObjIntFltFunction<T, R> func) {
		if (max_a2 < 0)
			return;
		fromTill(0, max_a2, a1, a3, func);
	}

	/** Extract and apply function. */
	public static <R, M, K, V> R from(@Nonnull M container, LBiFunction<M, K, V> extractor, K key, int a2, float a3, @Nonnull LObjIntFltFunction<V, R> function) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.apply(value, a2, a3);
		}

		return null;
	}

	default LOiFunction<T, R> rShrink(@Nonnull LOiToFltFunction<T> right) {
		Null.nonNullArg(right, "right");
		return (a1, a2) -> apply(a1, a2, right.applyAsFlt(a1, a2));
	}

	default LOiFunction<T, R> rShrink_(float a3) {
		return (a1, a2) -> apply(a1, a2, a3);
	}

	public static <T, R> LOiFunction<T, R> rShrunken(@Nonnull LOiToFltFunction<T> right, @Nonnull LObjIntFltFunction<T, R> func) {
		Null.nonNullArg(right, "right");
		Null.nonNullArg(func, "func");
		return func.rShrink(right);
	}

	public static <T, R> LOiFunction<T, R> rShrunken_(float a3, @Nonnull LObjIntFltFunction<T, R> func) {
		Null.nonNullArg(func, "func");
		return func.rShrink_(a3);
	}

	/**  */
	public static <T, R> LObjIntFltFunction<T, R> uncurry(@Nonnull LFunction<T, LIntFunction<LFltFunction<R>>> func) {
		Null.nonNullArg(func, "func");
		return (T a1, int a2, float a3) -> func.apply(a1).apply(a2).apply(a3);
	}

	/** Cast that removes generics. */
	default LObjIntFltFunction untyped() {
		return this;
	}

	/** Cast that replace generics. */
	default <V2, V3> LObjIntFltFunction<V2, V3> cast() {
		return untyped();
	}

	/** Cast that replace generics. */
	public static <V2, V3> LObjIntFltFunction<V2, V3> cast(LObjIntFltFunction<?, ?> function) {
		return (LObjIntFltFunction) function;
	}

	/** Change function to consumer that ignores output. */
	default LTieFltConsumer<T> toConsumer() {
		return this::apply;
	}

	/** Calls domain consumer before main function. */
	default LObjIntFltFunction<T, R> beforeDo(@Nonnull LTieFltConsumer<T> before) {
		Null.nonNullArg(before, "before");
		return (T a1, int a2, float a3) -> {
			before.accept(a1, a2, a3);
			return apply(a1, a2, a3);
		};
	}

	/** Calls codomain consumer after main function. */
	default LObjIntFltFunction<T, R> afterDo(@Nonnull LConsumer<R> after) {
		Null.nonNullArg(after, "after");
		return (T a1, int a2, float a3) -> {
			final R retval = apply(a1, a2, a3);
			after.accept(retval);
			return retval;
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplier<R> capture(T a1, int a2, float a3) {
		return () -> this.apply(a1, a2, a3);
	}

	/** Creates function that always returns the same value. */
	static <T, R> LObjIntFltFunction<T, R> constant(R r) {
		return (a1, a2, a3) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T, R> LObjIntFltFunction<T, R> apply1st(@Nonnull LFunction<T, R> func) {
		return (a1, a2, a3) -> func.apply(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T, R> LObjIntFltFunction<T, R> apply2nd(@Nonnull LIntFunction<R> func) {
		return (a1, a2, a3) -> func.apply(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T, R> LObjIntFltFunction<T, R> apply3rd(@Nonnull LFltFunction<R> func) {
		return (a1, a2, a3) -> func.apply(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, R> LObjIntFltFunction<T, R> objIntFltFunc(final @Nonnull LObjIntFltFunction<T, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** A completely inconvenient method in case lambda expression and generic arguments are ambiguous for the compiler. */
	@Nonnull
	static <T, R> LObjIntFltFunction<T, R> objIntFltFunc(@Nullable Class<T> c1, @Nullable Class<R> c2, final @Nonnull LObjIntFltFunction<T, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	final class S<T, R> implements LObjIntFltFunction<T, R> {
		private LObjIntFltFunction<T, R> target = null;
		@Override
		public R applyX(T a1, int a2, float a3) throws Throwable {
			return target.applyX(a1, a2, a3);
		}
	}

	@Nonnull
	static <T, R> LObjIntFltFunction<T, R> recursive(final @Nonnull LFunction<LObjIntFltFunction<T, R>, LObjIntFltFunction<T, R>> selfLambda) {
		final S<T, R> single = new S();
		LObjIntFltFunction<T, R> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	public static <T, R> M<T, R> mementoOf(T a1, int a2, float a3, LObjIntFltFunction<T, R> function) {
		var initialValue = function.apply(a1, a2, a3);
		return initializedMementoOf(initialValue, function);
	}

	public static <T, R> M<T, R> initializedMementoOf(R initialValue, LObjIntFltFunction<T, R> function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static <T, R> M<T, R> deltaOf(T a1, int a2, float a3, LObjIntFltFunction<T, R> function, LBinaryOperator<R> deltaFunction) {
		var initialValue = function.apply(a1, a2, a3);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static <T, R> M<T, R> initializedDeltaOf(R initialValue, LObjIntFltFunction<T, R> function, LBinaryOperator<R> deltaFunction) {
		return memento(initialValue, deltaFunction.apply(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.apply(x1, x2));
	}

	public static <T, R> M<T, R> memento(R initialBaseValue, R initialValue, LObjIntFltFunction<T, R> baseFunction, LTernaryOperator<R> mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LObjIntFltFunction.M)
	 */
	@NotThreadSafe
	final class M<T, R> implements LObjIntFltFunction<T, R> {

		private final LObjIntFltFunction<T, R> baseFunction;
		private R lastBaseValue;
		private R lastValue;
		private final LTernaryOperator<R> mementoFunction;

		private M(R lastBaseValue, R lastValue, LObjIntFltFunction<T, R> baseFunction, LTernaryOperator<R> mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public R applyX(T a1, int a2, float a3) throws Throwable {
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
	static <T, R> LObjIntFltFunction<T, R> objIntFltFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T, R> LObjIntFltFunction<T, R> objIntFltFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, R> LObjIntFltFunction.LObjFltIntFunc<T, R> objFltIntFunc(final @Nonnull LObjIntFltFunction.LObjFltIntFunc<T, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, R> LObjIntFltFunction.LIntObjFltFunc<T, R> intObjFltFunc(final @Nonnull LObjIntFltFunction.LIntObjFltFunc<T, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, R> LObjIntFltFunction.LIntFltObjFunc<T, R> intFltObjFunc(final @Nonnull LObjIntFltFunction.LIntFltObjFunc<T, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, R> LObjIntFltFunction.LFltObjIntFunc<T, R> fltObjIntFunc(final @Nonnull LObjIntFltFunction.LFltObjIntFunc<T, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, R> LObjIntFltFunction.LFltIntObjFunc<T, R> fltIntObjFunc(final @Nonnull LObjIntFltFunction.LFltIntObjFunc<T, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T, R> R call(T a1, int a2, float a3, final @Nonnull LObjIntFltFunction<T, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.apply(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as doNothing). */
	@Nonnull
	static <T, R> LObjIntFltFunction<T, R> safe() {
		return LObjIntFltFunction::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T, R> LSupplier<LObjIntFltFunction<T, R>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T, R> LObjIntFltFunction<T, R> safe(final @Nullable LObjIntFltFunction<T, R> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T, R> LSupplier<LObjIntFltFunction<T, R>> safeSupplier(final @Nullable LSupplier<LObjIntFltFunction<T, R>> supplier) {
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
	default <V1> LObjIntFltFunction<V1, R> compose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2, @Nonnull final LFltUnaryOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.apply(before1.apply(v1), before2.applyAsInt(v2), before3.applyAsFlt(v3));
	}

	public static <V1, T, R> LObjIntFltFunction<V1, R> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2, @Nonnull final LFltUnaryOperator before3, LObjIntFltFunction<T, R> after) {
		return after.compose(before1, before2, before3);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriFunction<V1, V2, V3, R> objIntFltFuncCompose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2, @Nonnull final LToFltFunction<? super V3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.apply(before1.apply(v1), before2.applyAsInt(v2), before3.applyAsFlt(v3));
	}

	public static <V1, V2, V3, T, R> LTriFunction<V1, V2, V3, R> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2, @Nonnull final LToFltFunction<? super V3> before3,
			LObjIntFltFunction<T, R> after) {
		return after.objIntFltFuncCompose(before1, before2, before3);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LObjIntFltFunction<T, V> then(@Nonnull LFunction<? super R, ? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.apply(this.apply(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LTieFltConsumer<T> thenConsume(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.accept(this.apply(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LTieFltFunction<T> thenToInt(@Nonnull LToIntFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.applyAsInt(this.apply(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LObjIntFltPredicate<T> thenToBool(@Nonnull LPredicate<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.test(this.apply(a1, a2, a3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LObjIntFltFunction<T, R> nonNullable() {
		return this::nonNullApply;
	}

	// <editor-fold desc="interface variants">

	/** Permutation of LObjIntFltFunction for method references. */
	@FunctionalInterface
	interface LObjFltIntFunc<T, R> extends LObjIntFltFunction<T, R> {

		/**
		 * Implement this, but call apply(T a1,int a2,float a3)
		 */
		default R applyX(T a1, int a2, float a3) {
			return this.applyObjFltInt(a1, a3, a2);
		}

		@Nullable
		// R applyObjFltInt(T a1,float a3,int a2) ;
		default R applyObjFltInt(T a1, float a3, int a2) {
			// return nestingApplyObjFltInt(a1,a3,a2);
			try {
				return this.applyObjFltIntX(a1, a3, a2);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyObjFltInt(T a1,float a3,int a2)
		 */
		R applyObjFltIntX(T a1, float a3, int a2) throws Throwable;
	}

	/** Permutation of LObjIntFltFunction for method references. */
	@FunctionalInterface
	interface LIntObjFltFunc<T, R> extends LObjIntFltFunction<T, R> {

		/**
		 * Implement this, but call applyObjFltInt(T a1,float a3,int a2)
		 */
		default R applyX(T a1, int a2, float a3) {
			return this.applyIntObjFlt(a2, a1, a3);
		}

		@Nullable
		// R applyIntObjFlt(int a2,T a1,float a3) ;
		default R applyIntObjFlt(int a2, T a1, float a3) {
			// return nestingApplyIntObjFlt(a2,a1,a3);
			try {
				return this.applyIntObjFltX(a2, a1, a3);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyIntObjFlt(int a2,T a1,float a3)
		 */
		R applyIntObjFltX(int a2, T a1, float a3) throws Throwable;
	}

	/** Permutation of LObjIntFltFunction for method references. */
	@FunctionalInterface
	interface LIntFltObjFunc<T, R> extends LObjIntFltFunction<T, R> {

		/**
		 * Implement this, but call applyIntObjFlt(int a2,T a1,float a3)
		 */
		default R applyX(T a1, int a2, float a3) {
			return this.applyIntFltObj(a2, a3, a1);
		}

		@Nullable
		// R applyIntFltObj(int a2,float a3,T a1) ;
		default R applyIntFltObj(int a2, float a3, T a1) {
			// return nestingApplyIntFltObj(a2,a3,a1);
			try {
				return this.applyIntFltObjX(a2, a3, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyIntFltObj(int a2,float a3,T a1)
		 */
		R applyIntFltObjX(int a2, float a3, T a1) throws Throwable;
	}

	/** Permutation of LObjIntFltFunction for method references. */
	@FunctionalInterface
	interface LFltObjIntFunc<T, R> extends LObjIntFltFunction<T, R> {

		/**
		 * Implement this, but call applyIntFltObj(int a2,float a3,T a1)
		 */
		default R applyX(T a1, int a2, float a3) {
			return this.applyFltObjInt(a3, a1, a2);
		}

		@Nullable
		// R applyFltObjInt(float a3,T a1,int a2) ;
		default R applyFltObjInt(float a3, T a1, int a2) {
			// return nestingApplyFltObjInt(a3,a1,a2);
			try {
				return this.applyFltObjIntX(a3, a1, a2);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyFltObjInt(float a3,T a1,int a2)
		 */
		R applyFltObjIntX(float a3, T a1, int a2) throws Throwable;
	}

	/** Permutation of LObjIntFltFunction for method references. */
	@FunctionalInterface
	interface LFltIntObjFunc<T, R> extends LObjIntFltFunction<T, R> {

		/**
		 * Implement this, but call applyFltObjInt(float a3,T a1,int a2)
		 */
		default R applyX(T a1, int a2, float a3) {
			return this.applyFltIntObj(a3, a2, a1);
		}

		@Nullable
		// R applyFltIntObj(float a3,int a2,T a1) ;
		default R applyFltIntObj(float a3, int a2, T a1) {
			// return nestingApplyFltIntObj(a3,a2,a1);
			try {
				return this.applyFltIntObjX(a3, a2, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyFltIntObj(float a3,int a2,T a1)
		 */
		R applyFltIntObjX(float a3, int a2, T a1) throws Throwable;
	}

	// </editor-fold>

	/** Does nothing (LObjIntFltFunction) Function */
	public static <T, R> R doNothing(T a1, int a2, float a3) {
		return (R) Function4U.defaultObject;
	}

	/** Does nothing (LObjIntFltFunction.LObjFltIntFunc) Function */
	public static <T, R> R doNothing(T a1, float a3, int a2) {
		return (R) Function4U.defaultObject;
	}

	/** Does nothing (LObjIntFltFunction.LIntObjFltFunc) Function */
	public static <T, R> R doNothing(int a2, T a1, float a3) {
		return (R) Function4U.defaultObject;
	}

	/** Does nothing (LObjIntFltFunction.LIntFltObjFunc) Function */
	public static <T, R> R doNothing(int a2, float a3, T a1) {
		return (R) Function4U.defaultObject;
	}

	/** Does nothing (LObjIntFltFunction.LFltObjIntFunc) Function */
	public static <T, R> R doNothing(float a3, T a1, int a2) {
		return (R) Function4U.defaultObject;
	}

	/** Does nothing (LObjIntFltFunction.LFltIntObjFunc) Function */
	public static <T, R> R doNothing(float a3, int a2, T a1) {
		return (R) Function4U.defaultObject;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, C3> void forEach(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, IndexedRead<C3, aFloat> ia3, C3 source3, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToFltFunction<Object> oiFunc3 = (LOiToFltFunction) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = oiFunc2.applyAsInt(source2, i);
			float a3 = oiFunc3.applyAsFlt(source3, i);
			consumer.accept(this.apply(a1, a2, a3));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, C3> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, IndexedRead<C3, aFloat> ia3, C3 source3, LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToFltFunction<Object> oiFunc3 = (LOiToFltFunction) ia3.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = oiFunc2.applyAsInt(source2, i);
			float a3 = oiFunc3.applyAsFlt(source3, i);
			consumer.accept(this.apply(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2, C3> void iterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, IndexedRead<C3, aFloat> ia3, C3 source3, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		size = Integer.min(size, ia3.size(source3));
		LOiToFltFunction<Object> oiFunc3 = (LOiToFltFunction) ia3.getter();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = nextFunc2.applyAsInt(iterator2);
			float a3 = oiFunc3.applyAsFlt(source3, i);
			consumer.accept(this.apply(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, I2, C3> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, IndexedRead<C3, aFloat> ia3, C3 source3, LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		int size = ia3.size(source3);
		LOiToFltFunction<Object> oiFunc3 = (LOiToFltFunction) ia3.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && i < size) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = nextFunc2.applyAsInt(iterator2);
			float a3 = oiFunc3.applyAsFlt(source3, i);
			consumer.accept(this.apply(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, C3, I3> void iterate(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, SequentialRead<C3, I3, aFloat> sa3, C3 source3, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToFltFunction<Object> nextFunc3 = (LToFltFunction) sa3.supplier();
		int i = 0;
		while (i < size && testFunc3.test(iterator3)) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = oiFunc2.applyAsInt(source2, i);
			float a3 = nextFunc3.applyAsFlt(iterator3);
			consumer.accept(this.apply(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, C3, I3> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, SequentialRead<C3, I3, aFloat> sa3, C3 source3, LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToFltFunction<Object> nextFunc3 = (LToFltFunction) sa3.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size && testFunc3.test(iterator3)) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = oiFunc2.applyAsInt(source2, i);
			float a3 = nextFunc3.applyAsFlt(iterator3);
			consumer.accept(this.apply(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2, C3, I3> void iterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, SequentialRead<C3, I3, aFloat> sa3, C3 source3, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToFltFunction<Object> nextFunc3 = (LToFltFunction) sa3.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = nextFunc2.applyAsInt(iterator2);
			float a3 = nextFunc3.applyAsFlt(iterator3);
			consumer.accept(this.apply(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C1, I1, C2, I2, C3, I3> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, SequentialRead<C3, I3, aFloat> sa3, C3 source3, LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToFltFunction<Object> nextFunc3 = (LToFltFunction) sa3.supplier();
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = nextFunc2.applyAsInt(iterator2);
			float a3 = nextFunc3.applyAsFlt(iterator3);
			consumer.accept(this.apply(a1, a2, a3));
		}
	}

}
