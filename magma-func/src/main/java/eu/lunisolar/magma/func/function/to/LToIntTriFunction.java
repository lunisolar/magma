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
 * Non-throwing functional interface (lambda) LToIntTriFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 3): T1 a1,T2 a2,T3 a3
 *
 * Co-domain: int
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LToIntTriFunction<T1, T2, T3> extends MetaFunction, MetaInterface.NonThrowing, Codomain<aInt>, Domain3<a<T1>, a<T2>, a<T3>> { // NOSONAR

	String DESCRIPTION = "LToIntTriFunction: int applyAsInt(T1 a1,T2 a2,T3 a3)";

	// int applyAsInt(T1 a1,T2 a2,T3 a3) ;
	default int applyAsInt(T1 a1, T2 a2, T3 a3) {
		// return nestingApplyAsInt(a1,a2,a3);
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsInt(T1 a1,T2 a2,T3 a3)
	 */
	int applyAsIntX(T1 a1, T2 a2, T3 a3) throws Throwable;

	default int tupleApplyAsInt(LTriple<T1, T2, T3> args) {
		return applyAsInt(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions according to the instructions. */
	default int handlingApplyAsInt(T1 a1, T2 a2, T3 a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LToIntTriFunction<T1, T2, T3> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> handlingApplyAsInt(a1, a2, a3, handling);
	}

	default int applyAsInt(T1 a1, T2 a2, T3 a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default int applyAsInt(T1 a1, T2 a2, T3 a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default int applyAsInt(T1 a1, T2 a2, T3 a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default int applyAsInt(T1 a1, T2 a2, T3 a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LToIntTriFunction<T1, T2, T3> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2, a3) -> applyAsInt(a1, a2, a3, factory, newMessage);
	}

	default LToIntTriFunction<T1, T2, T3> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2, a3) -> applyAsInt(a1, a2, a3, factory, newMessage, param1);
	}

	default LToIntTriFunction<T1, T2, T3> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2, a3) -> applyAsInt(a1, a2, a3, factory, newMessage, param1, param1);
	}

	default LToIntTriFunction<T1, T2, T3> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2, a3) -> applyAsInt(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	default int applyAsInt(T1 a1, T2 a2, T3 a3, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LToIntTriFunction<T1, T2, T3> trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2, a3) -> applyAsInt(a1, a2, a3, factory);
	}

	default int applyAsIntThen(T1 a1, T2 a2, T3 a3, @Nonnull LToIntFunction<Throwable> handler) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsInt(e);
		}
	}

	default LToIntTriFunction<T1, T2, T3> tryingThen(@Nonnull LToIntFunction<Throwable> handler) {
		return (a1, a2, a3) -> applyAsIntThen(a1, a2, a3, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default int nestingApplyAsInt(T1 a1, T2 a2, T3 a3) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default int shovingApplyAsInt(T1 a1, T2 a2, T3 a3) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T1, T2, T3> int shovingApplyAsInt(T1 a1, T2 a2, T3 a3, LToIntTriFunction<T1, T2, T3> func) {
		Null.nonNullArg(func, "func");
		return func.shovingApplyAsInt(a1, a2, a3);
	}

	static <T1, T2, T3> int handlingApplyAsInt(T1 a1, T2 a2, T3 a3, LToIntTriFunction<T1, T2, T3> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsInt(a1, a2, a3, handling);
	}

	static <T1, T2, T3> int tryApplyAsInt(T1 a1, T2 a2, T3 a3, LToIntTriFunction<T1, T2, T3> func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsInt(a1, a2, a3);
	}

	static <T1, T2, T3> int tryApplyAsInt(T1 a1, T2 a2, T3 a3, LToIntTriFunction<T1, T2, T3> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.applyAsInt(a1, a2, a3, factory, newMessage);
	}

	static <T1, T2, T3> int tryApplyAsInt(T1 a1, T2 a2, T3 a3, LToIntTriFunction<T1, T2, T3> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.applyAsInt(a1, a2, a3, factory, newMessage, param1);
	}

	static <T1, T2, T3> int tryApplyAsInt(T1 a1, T2 a2, T3 a3, LToIntTriFunction<T1, T2, T3> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.applyAsInt(a1, a2, a3, factory, newMessage, param1, param2);
	}

	static <T1, T2, T3> int tryApplyAsInt(T1 a1, T2 a2, T3 a3, LToIntTriFunction<T1, T2, T3> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.applyAsInt(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	static <T1, T2, T3> int tryApplyAsInt(T1 a1, T2 a2, T3 a3, LToIntTriFunction<T1, T2, T3> func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.applyAsInt(a1, a2, a3, factory);
	}

	static <T1, T2, T3> int tryApplyAsIntThen(T1 a1, T2 a2, T3 a3, LToIntTriFunction<T1, T2, T3> func, @Nonnull LToIntFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsIntThen(a1, a2, a3, handler);
	}

	default int failSafeApplyAsInt(T1 a1, T2 a2, T3 a3, @Nonnull LToIntTriFunction<T1, T2, T3> failSafe) {
		try {
			return applyAsInt(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.applyAsInt(a1, a2, a3);
		}
	}

	static <T1, T2, T3> int failSafeApplyAsInt(T1 a1, T2 a2, T3 a3, LToIntTriFunction<T1, T2, T3> func, @Nonnull LToIntTriFunction<T1, T2, T3> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.applyAsInt(a1, a2, a3);
		} else {
			return func.failSafeApplyAsInt(a1, a2, a3, failSafe);
		}
	}

	static <T1, T2, T3> LToIntTriFunction<T1, T2, T3> failSafe(LToIntTriFunction<T1, T2, T3> func, @Nonnull LToIntTriFunction<T1, T2, T3> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeApplyAsInt(a1, a2, a3, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default int nonNullApplyAsInt(T1 a1, T2 a2, T3 a3) {
		return applyAsInt(a1, a2, a3);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LToIntTriFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, T3> void fromTo(int min_i, int max_i, T1 a1, T2 a2, T3 a3, @Nonnull LToIntTriFunction<T1, T2, T3> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.applyAsInt(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.applyAsInt(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, T3> void fromTill(int min_i, int max_i, T1 a1, T2 a2, T3 a3, @Nonnull LToIntTriFunction<T1, T2, T3> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.applyAsInt(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.applyAsInt(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, T3> void times(int max_i, T1 a1, T2 a2, T3 a3, @Nonnull LToIntTriFunction<T1, T2, T3> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, a3, func);
	}

	/** Extract and apply function. */
	public static <M, K, V, T2, T3> int from(@Nonnull M container, LBiFunction<M, K, V> extractor, K key, T2 a2, T3 a3, @Nonnull LToIntTriFunction<V, T2, T3> function, int orElse) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.applyAsInt(value, a2, a3);
		}

		return orElse;
	}

	default LToIntBiFunction<T2, T3> lShrink(@Nonnull LBiFunction<T2, T3, T1> left) {
		Null.nonNullArg(left, "left");
		return (a2, a3) -> applyAsInt(left.apply(a2, a3), a2, a3);
	}

	default LToIntBiFunction<T2, T3> lShrink_(T1 a1) {
		return (a2, a3) -> applyAsInt(a1, a2, a3);
	}

	public static <T2, T3, T1> LToIntBiFunction<T2, T3> lShrunken(@Nonnull LBiFunction<T2, T3, T1> left, @Nonnull LToIntTriFunction<T1, T2, T3> func) {
		Null.nonNullArg(left, "left");
		Null.nonNullArg(func, "func");
		return func.lShrink(left);
	}

	public static <T2, T3, T1> LToIntBiFunction<T2, T3> lShrunken_(T1 a1, @Nonnull LToIntTriFunction<T1, T2, T3> func) {
		Null.nonNullArg(func, "func");
		return func.lShrink_(a1);
	}

	default LToIntBiFunction<T1, T2> rShrink(@Nonnull LBiFunction<T1, T2, T3> right) {
		Null.nonNullArg(right, "right");
		return (a1, a2) -> applyAsInt(a1, a2, right.apply(a1, a2));
	}

	default LToIntBiFunction<T1, T2> rShrink_(T3 a3) {
		return (a1, a2) -> applyAsInt(a1, a2, a3);
	}

	public static <T1, T2, T3> LToIntBiFunction<T1, T2> rShrunken(@Nonnull LBiFunction<T1, T2, T3> right, @Nonnull LToIntTriFunction<T1, T2, T3> func) {
		Null.nonNullArg(right, "right");
		Null.nonNullArg(func, "func");
		return func.rShrink(right);
	}

	public static <T1, T2, T3> LToIntBiFunction<T1, T2> rShrunken_(T3 a3, @Nonnull LToIntTriFunction<T1, T2, T3> func) {
		Null.nonNullArg(func, "func");
		return func.rShrink_(a3);
	}

	/**  */
	public static <T1, T2, T3> LToIntTriFunction<T1, T2, T3> uncurry(@Nonnull LFunction<T1, LFunction<T2, LToIntFunction<T3>>> func) {
		Null.nonNullArg(func, "func");
		return (T1 a1, T2 a2, T3 a3) -> func.apply(a1).apply(a2).applyAsInt(a3);
	}

	/** Cast that removes generics. */
	default LToIntTriFunction untyped() {
		return this;
	}

	/** Cast that replace generics. */
	default <V2, V3, V4> LToIntTriFunction<V2, V3, V4> cast() {
		return untyped();
	}

	/** Cast that replace generics. */
	public static <V2, V3, V4> LToIntTriFunction<V2, V3, V4> cast(LToIntTriFunction<?, ?, ?> function) {
		return (LToIntTriFunction) function;
	}

	/** Change function to consumer that ignores output. */
	default LTriConsumer<T1, T2, T3> toConsumer() {
		return this::applyAsInt;
	}

	/** Calls domain consumer before main function. */
	default LToIntTriFunction<T1, T2, T3> beforeDo(@Nonnull LTriConsumer<T1, T2, T3> before) {
		Null.nonNullArg(before, "before");
		return (T1 a1, T2 a2, T3 a3) -> {
			before.accept(a1, a2, a3);
			return applyAsInt(a1, a2, a3);
		};
	}

	/** Calls codomain consumer after main function. */
	default LToIntTriFunction<T1, T2, T3> afterDo(@Nonnull LIntConsumer after) {
		Null.nonNullArg(after, "after");
		return (T1 a1, T2 a2, T3 a3) -> {
			final int retval = applyAsInt(a1, a2, a3);
			after.accept(retval);
			return retval;
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LIntSupplier capture(T1 a1, T2 a2, T3 a3) {
		return () -> this.applyAsInt(a1, a2, a3);
	}

	/** Creates function that always returns the same value. */
	static <T1, T2, T3> LToIntTriFunction<T1, T2, T3> constant(int r) {
		return (a1, a2, a3) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T1, T2, T3> LToIntTriFunction<T1, T2, T3> apply1stAsInt(@Nonnull LToIntFunction<T1> func) {
		return (a1, a2, a3) -> func.applyAsInt(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T1, T2, T3> LToIntTriFunction<T1, T2, T3> apply2ndAsInt(@Nonnull LToIntFunction<T2> func) {
		return (a1, a2, a3) -> func.applyAsInt(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T1, T2, T3> LToIntTriFunction<T1, T2, T3> apply3rdAsInt(@Nonnull LToIntFunction<T3> func) {
		return (a1, a2, a3) -> func.applyAsInt(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, T3> LToIntTriFunction<T1, T2, T3> toIntTriFunc(final @Nonnull LToIntTriFunction<T1, T2, T3> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** A completely inconvenient method in case lambda expression and generic arguments are ambiguous for the compiler. */
	@Nonnull
	static <T1, T2, T3> LToIntTriFunction<T1, T2, T3> toIntTriFunc(@Nullable Class<T1> c1, @Nullable Class<T2> c2, @Nullable Class<T3> c3, final @Nonnull LToIntTriFunction<T1, T2, T3> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	final class S<T1, T2, T3> implements LToIntTriFunction<T1, T2, T3> {
		private LToIntTriFunction<T1, T2, T3> target = null;
		@Override
		public int applyAsIntX(T1 a1, T2 a2, T3 a3) throws Throwable {
			return target.applyAsIntX(a1, a2, a3);
		}
	}

	@Nonnull
	static <T1, T2, T3> LToIntTriFunction<T1, T2, T3> recursive(final @Nonnull LFunction<LToIntTriFunction<T1, T2, T3>, LToIntTriFunction<T1, T2, T3>> selfLambda) {
		final S<T1, T2, T3> single = new S();
		LToIntTriFunction<T1, T2, T3> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	public static <T1, T2, T3> M<T1, T2, T3> mementoOf(T1 a1, T2 a2, T3 a3, LToIntTriFunction<T1, T2, T3> function) {
		var initialValue = function.applyAsInt(a1, a2, a3);
		return initializedMementoOf(initialValue, function);
	}

	public static <T1, T2, T3> M<T1, T2, T3> initializedMementoOf(int initialValue, LToIntTriFunction<T1, T2, T3> function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static <T1, T2, T3> M<T1, T2, T3> deltaOf(T1 a1, T2 a2, T3 a3, LToIntTriFunction<T1, T2, T3> function, LIntBinaryOperator deltaFunction) {
		var initialValue = function.applyAsInt(a1, a2, a3);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static <T1, T2, T3> M<T1, T2, T3> deltaOf(T1 a1, T2 a2, T3 a3, LToIntTriFunction<T1, T2, T3> function) {
		var initialValue = function.applyAsInt(a1, a2, a3);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (x2 - x1));
	}

	public static <T1, T2, T3> M<T1, T2, T3> initializedDeltaOf(int initialValue, LToIntTriFunction<T1, T2, T3> function, LIntBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.applyAsInt(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.applyAsInt(x1, x2));
	}

	public static <T1, T2, T3> M<T1, T2, T3> memento(int initialBaseValue, int initialValue, LToIntTriFunction<T1, T2, T3> baseFunction, LIntTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LToIntTriFunction.M)
	 */
	@NotThreadSafe
	final class M<T1, T2, T3> implements LToIntTriFunction<T1, T2, T3> {

		private final LToIntTriFunction<T1, T2, T3> baseFunction;
		private int lastBaseValue;
		private int lastValue;
		private final LIntTernaryOperator mementoFunction;

		private M(int lastBaseValue, int lastValue, LToIntTriFunction<T1, T2, T3> baseFunction, LIntTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public int applyAsIntX(T1 a1, T2 a2, T3 a3) throws Throwable {
			int x1 = lastBaseValue;
			int x2 = lastBaseValue = baseFunction.applyAsIntX(a1, a2, a3);

			return lastValue = mementoFunction.applyAsInt(lastValue, x1, x2);
		}

		public int lastValue() {
			return lastValue;
		};

		public int lastBaseValue() {
			return lastBaseValue;
		};
	}

	@Nonnull
	static <T1, T2, T3> LToIntTriFunction<T1, T2, T3> toIntTriFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T1, T2, T3> LToIntTriFunction<T1, T2, T3> toIntTriFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T3, T2> LToIntTriFunction.LToIntObj0Obj2Obj1Func<T1, T3, T2> toIntObj0Obj2Obj1Func(final @Nonnull LToIntTriFunction.LToIntObj0Obj2Obj1Func<T1, T3, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1, T3> LToIntTriFunction.LToIntObj1BiObj2Func<T2, T1, T3> toIntObj1BiObj2Func(final @Nonnull LToIntTriFunction.LToIntObj1BiObj2Func<T2, T1, T3> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T3, T1> LToIntTriFunction.LToIntObj1Obj2Obj0Func<T2, T3, T1> toIntObj1Obj2Obj0Func(final @Nonnull LToIntTriFunction.LToIntObj1Obj2Obj0Func<T2, T3, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T3, T1, T2> LToIntTriFunction.LToIntObj2Obj0Obj1Func<T3, T1, T2> toIntObj2Obj0Obj1Func(final @Nonnull LToIntTriFunction.LToIntObj2Obj0Obj1Func<T3, T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T3, T2, T1> LToIntTriFunction.LToIntBiObj1Obj0Func<T3, T2, T1> toIntBiObj1Obj0Func(final @Nonnull LToIntTriFunction.LToIntBiObj1Obj0Func<T3, T2, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T1, T2, T3> int call(T1 a1, T2 a2, T3 a3, final @Nonnull LToIntTriFunction<T1, T2, T3> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsInt(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LToIntTriFunction<V1, V2, V3> compose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LFunction<? super V3, ? extends T3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.applyAsInt(before1.apply(v1), before2.apply(v2), before3.apply(v3));
	}

	public static <V1, V2, V3, T1, T2, T3> LToIntTriFunction<V1, V2, V3> composed(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2,
			@Nonnull final LFunction<? super V3, ? extends T3> before3, LToIntTriFunction<T1, T2, T3> after) {
		return after.compose(before1, before2, before3);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LTriFunction<T1, T2, T3, V> then(@Nonnull LIntFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.apply(this.applyAsInt(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToIntTriFunction<T1, T2, T3> thenToInt(@Nonnull LIntUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.applyAsInt(this.applyAsInt(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LTriPredicate<T1, T2, T3> thenToBool(@Nonnull LIntPredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.test(this.applyAsInt(a1, a2, a3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LToIntTriFunction for method references. */
	@FunctionalInterface
	interface LToIntObj0Obj2Obj1Func<T1, T3, T2> extends LToIntTriFunction<T1, T2, T3> {

		/**
		 * Implement this, but call applyAsInt(T1 a1,T2 a2,T3 a3)
		 */
		default int applyAsIntX(T1 a1, T2 a2, T3 a3) {
			return this.applyAsIntObj0Obj2Obj1(a1, a3, a2);
		}

		// int applyAsIntObj0Obj2Obj1(T1 a1,T3 a3,T2 a2) ;
		default int applyAsIntObj0Obj2Obj1(T1 a1, T3 a3, T2 a2) {
			// return nestingApplyAsIntObj0Obj2Obj1(a1,a3,a2);
			try {
				return this.applyAsIntObj0Obj2Obj1X(a1, a3, a2);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyAsIntObj0Obj2Obj1(T1 a1,T3 a3,T2 a2)
		 */
		int applyAsIntObj0Obj2Obj1X(T1 a1, T3 a3, T2 a2) throws Throwable;
	}

	/** Permutation of LToIntTriFunction for method references. */
	@FunctionalInterface
	interface LToIntObj1BiObj2Func<T2, T1, T3> extends LToIntTriFunction<T1, T2, T3> {

		/**
		 * Implement this, but call applyAsIntObj0Obj2Obj1(T1 a1,T3 a3,T2 a2)
		 */
		default int applyAsIntX(T1 a1, T2 a2, T3 a3) {
			return this.applyAsIntObj1BiObj2(a2, a1, a3);
		}

		// int applyAsIntObj1BiObj2(T2 a2,T1 a1,T3 a3) ;
		default int applyAsIntObj1BiObj2(T2 a2, T1 a1, T3 a3) {
			// return nestingApplyAsIntObj1BiObj2(a2,a1,a3);
			try {
				return this.applyAsIntObj1BiObj2X(a2, a1, a3);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyAsIntObj1BiObj2(T2 a2,T1 a1,T3 a3)
		 */
		int applyAsIntObj1BiObj2X(T2 a2, T1 a1, T3 a3) throws Throwable;
	}

	/** Permutation of LToIntTriFunction for method references. */
	@FunctionalInterface
	interface LToIntObj1Obj2Obj0Func<T2, T3, T1> extends LToIntTriFunction<T1, T2, T3> {

		/**
		 * Implement this, but call applyAsIntObj1BiObj2(T2 a2,T1 a1,T3 a3)
		 */
		default int applyAsIntX(T1 a1, T2 a2, T3 a3) {
			return this.applyAsIntObj1Obj2Obj0(a2, a3, a1);
		}

		// int applyAsIntObj1Obj2Obj0(T2 a2,T3 a3,T1 a1) ;
		default int applyAsIntObj1Obj2Obj0(T2 a2, T3 a3, T1 a1) {
			// return nestingApplyAsIntObj1Obj2Obj0(a2,a3,a1);
			try {
				return this.applyAsIntObj1Obj2Obj0X(a2, a3, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyAsIntObj1Obj2Obj0(T2 a2,T3 a3,T1 a1)
		 */
		int applyAsIntObj1Obj2Obj0X(T2 a2, T3 a3, T1 a1) throws Throwable;
	}

	/** Permutation of LToIntTriFunction for method references. */
	@FunctionalInterface
	interface LToIntObj2Obj0Obj1Func<T3, T1, T2> extends LToIntTriFunction<T1, T2, T3> {

		/**
		 * Implement this, but call applyAsIntObj1Obj2Obj0(T2 a2,T3 a3,T1 a1)
		 */
		default int applyAsIntX(T1 a1, T2 a2, T3 a3) {
			return this.applyAsIntObj2Obj0Obj1(a3, a1, a2);
		}

		// int applyAsIntObj2Obj0Obj1(T3 a3,T1 a1,T2 a2) ;
		default int applyAsIntObj2Obj0Obj1(T3 a3, T1 a1, T2 a2) {
			// return nestingApplyAsIntObj2Obj0Obj1(a3,a1,a2);
			try {
				return this.applyAsIntObj2Obj0Obj1X(a3, a1, a2);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyAsIntObj2Obj0Obj1(T3 a3,T1 a1,T2 a2)
		 */
		int applyAsIntObj2Obj0Obj1X(T3 a3, T1 a1, T2 a2) throws Throwable;
	}

	/** Permutation of LToIntTriFunction for method references. */
	@FunctionalInterface
	interface LToIntBiObj1Obj0Func<T3, T2, T1> extends LToIntTriFunction<T1, T2, T3> {

		/**
		 * Implement this, but call applyAsIntObj2Obj0Obj1(T3 a3,T1 a1,T2 a2)
		 */
		default int applyAsIntX(T1 a1, T2 a2, T3 a3) {
			return this.applyAsIntBiObj1Obj0(a3, a2, a1);
		}

		// int applyAsIntBiObj1Obj0(T3 a3,T2 a2,T1 a1) ;
		default int applyAsIntBiObj1Obj0(T3 a3, T2 a2, T1 a1) {
			// return nestingApplyAsIntBiObj1Obj0(a3,a2,a1);
			try {
				return this.applyAsIntBiObj1Obj0X(a3, a2, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyAsIntBiObj1Obj0(T3 a3,T2 a2,T1 a1)
		 */
		int applyAsIntBiObj1Obj0X(T3 a3, T2 a2, T1 a1) throws Throwable;
	}

	// </editor-fold>

	/** Does nothing (LToIntTriFunction) Function */
	public static <T1, T2, T3> int doNothing(T1 a1, T2 a2, T3 a3) {
		return Function4U.defaultInteger;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, C3> void forEach(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, LIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = oiFunc3.apply(source3, i);
			consumer.accept(this.applyAsInt(a1, a2, a3));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, C3> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, LIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = oiFunc3.apply(source3, i);
			consumer.accept(this.applyAsInt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2, C3> void iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, LIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = oiFunc3.apply(source3, i);
			consumer.accept(this.applyAsInt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, I2, C3> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, LIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		int size = ia3.size(source3);
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && i < size) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = oiFunc3.apply(source3, i);
			consumer.accept(this.applyAsInt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, C3, I3> void iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, LIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		int i = 0;
		while (i < size && testFunc3.test(iterator3)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = nextFunc3.apply(iterator3);
			consumer.accept(this.applyAsInt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, C3, I3> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, LIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size && testFunc3.test(iterator3)) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = nextFunc3.apply(iterator3);
			consumer.accept(this.applyAsInt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2, C3, I3> void iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, LIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = nextFunc3.apply(iterator3);
			consumer.accept(this.applyAsInt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C1, I1, C2, I2, C3, I3> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, LIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = nextFunc3.apply(iterator3);
			consumer.accept(this.applyAsInt(a1, a2, a3));
		}
	}

}
