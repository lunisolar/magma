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

package eu.lunisolar.magma.func.function;

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
 * Non-throwing functional interface (lambda) LQuadFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 4): T1 a1,T2 a2,T3 a3,T4 a4
 *
 * Co-domain: R
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LQuadFunction<T1, T2, T3, T4, R> extends MetaFunction, MetaInterface.NonThrowing, Codomain<a<R>>, Domain4<a<T1>, a<T2>, a<T3>, a<T4>> { // NOSONAR

	String DESCRIPTION = "LQuadFunction: R apply(T1 a1,T2 a2,T3 a3,T4 a4)";

	@Nullable
	// R apply(T1 a1,T2 a2,T3 a3,T4 a4) ;
	default R apply(T1 a1, T2 a2, T3 a3, T4 a4) {
		// return nestingApply(a1,a2,a3,a4);
		try {
			return this.applyX(a1, a2, a3, a4);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call apply(T1 a1,T2 a2,T3 a3,T4 a4)
	 */
	R applyX(T1 a1, T2 a2, T3 a3, T4 a4) throws Throwable;

	default R tupleApply(LQuad<T1, T2, T3, T4> args) {
		return apply(args.first(), args.second(), args.third(), args.fourth());
	}

	/** Function call that handles exceptions according to the instructions. */
	default R handlingApply(T1 a1, T2 a2, T3 a3, T4 a4, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyX(a1, a2, a3, a4);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LQuadFunction<T1, T2, T3, T4, R> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3, a4) -> handlingApply(a1, a2, a3, a4, handling);
	}

	default R apply(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyX(a1, a2, a3, a4);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default R apply(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyX(a1, a2, a3, a4);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default R apply(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyX(a1, a2, a3, a4);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default R apply(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyX(a1, a2, a3, a4);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LQuadFunction<T1, T2, T3, T4, R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2, a3, a4) -> apply(a1, a2, a3, a4, factory, newMessage);
	}

	default LQuadFunction<T1, T2, T3, T4, R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2, a3, a4) -> apply(a1, a2, a3, a4, factory, newMessage, param1);
	}

	default LQuadFunction<T1, T2, T3, T4, R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2, a3, a4) -> apply(a1, a2, a3, a4, factory, newMessage, param1, param1);
	}

	default LQuadFunction<T1, T2, T3, T4, R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2, a3, a4) -> apply(a1, a2, a3, a4, factory, newMessage, param1, param2, param3);
	}

	default R apply(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyX(a1, a2, a3, a4);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LQuadFunction<T1, T2, T3, T4, R> trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2, a3, a4) -> apply(a1, a2, a3, a4, factory);
	}

	default R applyThen(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LFunction<Throwable, R> handler) {
		try {
			return this.applyX(a1, a2, a3, a4);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.apply(e);
		}
	}

	default LQuadFunction<T1, T2, T3, T4, R> tryingThen(@Nonnull LFunction<Throwable, R> handler) {
		return (a1, a2, a3, a4) -> applyThen(a1, a2, a3, a4, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default R nestingApply(T1 a1, T2 a2, T3 a3, T4 a4) {
		try {
			return this.applyX(a1, a2, a3, a4);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default R shovingApply(T1 a1, T2 a2, T3 a3, T4 a4) {
		try {
			return this.applyX(a1, a2, a3, a4);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T1, T2, T3, T4, R> R handlingApply(T1 a1, T2 a2, T3 a3, T4 a4, LQuadFunction<T1, T2, T3, T4, R> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApply(a1, a2, a3, a4, handling);
	}

	static <T1, T2, T3, T4, R> R tryApply(T1 a1, T2 a2, T3 a3, T4 a4, LQuadFunction<T1, T2, T3, T4, R> func) {
		Null.nonNullArg(func, "func");
		return func.nestingApply(a1, a2, a3, a4);
	}

	static <T1, T2, T3, T4, R> R tryApply(T1 a1, T2 a2, T3 a3, T4 a4, LQuadFunction<T1, T2, T3, T4, R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, a4, factory, newMessage);
	}

	static <T1, T2, T3, T4, R> R tryApply(T1 a1, T2 a2, T3 a3, T4 a4, LQuadFunction<T1, T2, T3, T4, R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, a4, factory, newMessage, param1);
	}

	static <T1, T2, T3, T4, R> R tryApply(T1 a1, T2 a2, T3 a3, T4 a4, LQuadFunction<T1, T2, T3, T4, R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, a4, factory, newMessage, param1, param2);
	}

	static <T1, T2, T3, T4, R> R tryApply(T1 a1, T2 a2, T3 a3, T4 a4, LQuadFunction<T1, T2, T3, T4, R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, a4, factory, newMessage, param1, param2, param3);
	}

	static <T1, T2, T3, T4, R> R tryApply(T1 a1, T2 a2, T3 a3, T4 a4, LQuadFunction<T1, T2, T3, T4, R> func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, a4, factory);
	}

	static <T1, T2, T3, T4, R> R tryApplyThen(T1 a1, T2 a2, T3 a3, T4 a4, LQuadFunction<T1, T2, T3, T4, R> func, @Nonnull LFunction<Throwable, R> handler) {
		Null.nonNullArg(func, "func");
		return func.applyThen(a1, a2, a3, a4, handler);
	}

	default R failSafeApply(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadFunction<T1, T2, T3, T4, R> failSafe) {
		try {
			return apply(a1, a2, a3, a4);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.apply(a1, a2, a3, a4);
		}
	}

	static <T1, T2, T3, T4, R> R failSafeApply(T1 a1, T2 a2, T3 a3, T4 a4, LQuadFunction<T1, T2, T3, T4, R> func, @Nonnull LQuadFunction<T1, T2, T3, T4, R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.apply(a1, a2, a3, a4);
		} else {
			return func.failSafeApply(a1, a2, a3, a4, failSafe);
		}
	}

	static <T1, T2, T3, T4, R> LQuadFunction<T1, T2, T3, T4, R> failSafe(LQuadFunction<T1, T2, T3, T4, R> func, @Nonnull LQuadFunction<T1, T2, T3, T4, R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3, a4) -> failSafeApply(a1, a2, a3, a4, func, failSafe);
	}

	LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullApply() method cannot be null (" + DESCRIPTION + ").";

	/** Function call that ensures the result is not null */
	@Nonnull
	default R nonNullApply(T1 a1, T2 a2, T3 a3, T4 a4) {
		return Null.nonNull(apply(a1, a2, a3, a4), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LQuadFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, T3, T4, R> void fromTo(int min_i, int max_i, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadFunction<T1, T2, T3, T4, R> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.apply(a1, a2, a3, a4);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.apply(a1, a2, a3, a4);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, T3, T4, R> void fromTill(int min_i, int max_i, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadFunction<T1, T2, T3, T4, R> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.apply(a1, a2, a3, a4);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.apply(a1, a2, a3, a4);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, T3, T4, R> void times(int max_i, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadFunction<T1, T2, T3, T4, R> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, a3, a4, func);
	}

	/** Extract and apply function. */
	public static <R, M, K, V, T2, T3, T4> R from(@Nonnull M container, LBiFunction<M, K, V> extractor, K key, T2 a2, T3 a3, T4 a4, @Nonnull LQuadFunction<V, T2, T3, T4, R> function) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.apply(value, a2, a3, a4);
		}

		return null;
	}

	default LTriFunction<T2, T3, T4, R> lShrink(@Nonnull LTriFunction<T2, T3, T4, T1> left) {
		Null.nonNullArg(left, "left");
		return (a2, a3, a4) -> apply(left.apply(a2, a3, a4), a2, a3, a4);
	}

	default LTriFunction<T2, T3, T4, R> lShrink_(T1 a1) {
		return (a2, a3, a4) -> apply(a1, a2, a3, a4);
	}

	public static <T2, T3, T4, R, T1> LTriFunction<T2, T3, T4, R> lShrunken(@Nonnull LTriFunction<T2, T3, T4, T1> left, @Nonnull LQuadFunction<T1, T2, T3, T4, R> func) {
		Null.nonNullArg(left, "left");
		Null.nonNullArg(func, "func");
		return func.lShrink(left);
	}

	public static <T2, T3, T4, R, T1> LTriFunction<T2, T3, T4, R> lShrunken_(T1 a1, @Nonnull LQuadFunction<T1, T2, T3, T4, R> func) {
		Null.nonNullArg(func, "func");
		return func.lShrink_(a1);
	}

	default LTriFunction<T1, T2, T3, R> rShrink(@Nonnull LTriFunction<T1, T2, T3, T4> right) {
		Null.nonNullArg(right, "right");
		return (a1, a2, a3) -> apply(a1, a2, a3, right.apply(a1, a2, a3));
	}

	default LTriFunction<T1, T2, T3, R> rShrink_(T4 a4) {
		return (a1, a2, a3) -> apply(a1, a2, a3, a4);
	}

	public static <T1, T2, T3, R, T4> LTriFunction<T1, T2, T3, R> rShrunken(@Nonnull LTriFunction<T1, T2, T3, T4> right, @Nonnull LQuadFunction<T1, T2, T3, T4, R> func) {
		Null.nonNullArg(right, "right");
		Null.nonNullArg(func, "func");
		return func.rShrink(right);
	}

	public static <T1, T2, T3, R, T4> LTriFunction<T1, T2, T3, R> rShrunken_(T4 a4, @Nonnull LQuadFunction<T1, T2, T3, T4, R> func) {
		Null.nonNullArg(func, "func");
		return func.rShrink_(a4);
	}

	/**  */
	public static <T1, T2, T3, T4, R> LQuadFunction<T1, T2, T3, T4, R> uncurry(@Nonnull LFunction<T1, LFunction<T2, LFunction<T3, LFunction<T4, R>>>> func) {
		Null.nonNullArg(func, "func");
		return (T1 a1, T2 a2, T3 a3, T4 a4) -> func.apply(a1).apply(a2).apply(a3).apply(a4);
	}

	/** Cast that removes generics. */
	default LQuadFunction untyped() {
		return this;
	}

	/** Cast that replace generics. */
	default <V2, V3, V4, V5, V6> LQuadFunction<V2, V3, V4, V5, V6> cast() {
		return untyped();
	}

	/** Cast that replace generics. */
	public static <V2, V3, V4, V5, V6> LQuadFunction<V2, V3, V4, V5, V6> cast(LQuadFunction<?, ?, ?, ?, ?> function) {
		return (LQuadFunction) function;
	}

	/** Change function to consumer that ignores output. */
	default LQuadConsumer<T1, T2, T3, T4> toConsumer() {
		return this::apply;
	}

	/** Calls domain consumer before main function. */
	default LQuadFunction<T1, T2, T3, T4, R> beforeDo(@Nonnull LQuadConsumer<T1, T2, T3, T4> before) {
		Null.nonNullArg(before, "before");
		return (T1 a1, T2 a2, T3 a3, T4 a4) -> {
			before.accept(a1, a2, a3, a4);
			return apply(a1, a2, a3, a4);
		};
	}

	/** Calls codomain consumer after main function. */
	default LQuadFunction<T1, T2, T3, T4, R> afterDo(@Nonnull LConsumer<R> after) {
		Null.nonNullArg(after, "after");
		return (T1 a1, T2 a2, T3 a3, T4 a4) -> {
			final R retval = apply(a1, a2, a3, a4);
			after.accept(retval);
			return retval;
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplier<R> capture(T1 a1, T2 a2, T3 a3, T4 a4) {
		return () -> this.apply(a1, a2, a3, a4);
	}

	/** Creates function that always returns the same value. */
	static <T1, T2, T3, T4, R> LQuadFunction<T1, T2, T3, T4, R> constant(R r) {
		return (a1, a2, a3, a4) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T1, T2, T3, T4, R> LQuadFunction<T1, T2, T3, T4, R> apply1st(@Nonnull LFunction<T1, R> func) {
		return (a1, a2, a3, a4) -> func.apply(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T1, T2, T3, T4, R> LQuadFunction<T1, T2, T3, T4, R> apply2nd(@Nonnull LFunction<T2, R> func) {
		return (a1, a2, a3, a4) -> func.apply(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T1, T2, T3, T4, R> LQuadFunction<T1, T2, T3, T4, R> apply3rd(@Nonnull LFunction<T3, R> func) {
		return (a1, a2, a3, a4) -> func.apply(a3);
	}

	/** Captures single parameter function into this interface where only 4th parameter will be used. */
	@Nonnull
	static <T1, T2, T3, T4, R> LQuadFunction<T1, T2, T3, T4, R> apply4th(@Nonnull LFunction<T4, R> func) {
		return (a1, a2, a3, a4) -> func.apply(a4);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, T3, T4, R> LQuadFunction<T1, T2, T3, T4, R> quadFunc(final @Nonnull LQuadFunction<T1, T2, T3, T4, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** A completely inconvenient method in case lambda expression and generic arguments are ambiguous for the compiler. */
	@Nonnull
	static <T1, T2, T3, T4, R> LQuadFunction<T1, T2, T3, T4, R> quadFunc(@Nullable Class<T1> c1, @Nullable Class<T2> c2, @Nullable Class<T3> c3, @Nullable Class<T4> c4, @Nullable Class<R> c5, final @Nonnull LQuadFunction<T1, T2, T3, T4, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	final class S<T1, T2, T3, T4, R> implements LQuadFunction<T1, T2, T3, T4, R> {
		private LQuadFunction<T1, T2, T3, T4, R> target = null;
		@Override
		public R applyX(T1 a1, T2 a2, T3 a3, T4 a4) throws Throwable {
			return target.applyX(a1, a2, a3, a4);
		}
	}

	@Nonnull
	static <T1, T2, T3, T4, R> LQuadFunction<T1, T2, T3, T4, R> recursive(final @Nonnull LFunction<LQuadFunction<T1, T2, T3, T4, R>, LQuadFunction<T1, T2, T3, T4, R>> selfLambda) {
		final S<T1, T2, T3, T4, R> single = new S();
		LQuadFunction<T1, T2, T3, T4, R> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	public static <T1, T2, T3, T4, R> M<T1, T2, T3, T4, R> mementoOf(T1 a1, T2 a2, T3 a3, T4 a4, LQuadFunction<T1, T2, T3, T4, R> function) {
		var initialValue = function.apply(a1, a2, a3, a4);
		return initializedMementoOf(initialValue, function);
	}

	public static <T1, T2, T3, T4, R> M<T1, T2, T3, T4, R> initializedMementoOf(R initialValue, LQuadFunction<T1, T2, T3, T4, R> function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static <T1, T2, T3, T4, R> M<T1, T2, T3, T4, R> deltaOf(T1 a1, T2 a2, T3 a3, T4 a4, LQuadFunction<T1, T2, T3, T4, R> function, LBinaryOperator<R> deltaFunction) {
		var initialValue = function.apply(a1, a2, a3, a4);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static <T1, T2, T3, T4, R> M<T1, T2, T3, T4, R> initializedDeltaOf(R initialValue, LQuadFunction<T1, T2, T3, T4, R> function, LBinaryOperator<R> deltaFunction) {
		return memento(initialValue, deltaFunction.apply(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.apply(x1, x2));
	}

	public static <T1, T2, T3, T4, R> M<T1, T2, T3, T4, R> memento(R initialBaseValue, R initialValue, LQuadFunction<T1, T2, T3, T4, R> baseFunction, LTernaryOperator<R> mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LQuadFunction.M)
	 */
	@NotThreadSafe
	final class M<T1, T2, T3, T4, R> implements LQuadFunction<T1, T2, T3, T4, R> {

		private final LQuadFunction<T1, T2, T3, T4, R> baseFunction;
		private R lastBaseValue;
		private R lastValue;
		private final LTernaryOperator<R> mementoFunction;

		private M(R lastBaseValue, R lastValue, LQuadFunction<T1, T2, T3, T4, R> baseFunction, LTernaryOperator<R> mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public R applyX(T1 a1, T2 a2, T3 a3, T4 a4) throws Throwable {
			R x1 = lastBaseValue;
			R x2 = lastBaseValue = baseFunction.applyX(a1, a2, a3, a4);

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
	static <T1, T2, T3, T4, R> LQuadFunction<T1, T2, T3, T4, R> quadFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3, a4) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T1, T2, T3, T4, R> LQuadFunction<T1, T2, T3, T4, R> quadFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3, a4) -> {
			throw exF.produce(message);
		};
	}

	static <T1, T2, T3, T4, R> R call(T1 a1, T2 a2, T3 a3, T4 a4, final @Nonnull LQuadFunction<T1, T2, T3, T4, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.apply(a1, a2, a3, a4);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as doNothing). */
	@Nonnull
	static <T1, T2, T3, T4, R> LQuadFunction<T1, T2, T3, T4, R> safe() {
		return LQuadFunction::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T1, T2, T3, T4, R> LSupplier<LQuadFunction<T1, T2, T3, T4, R>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T1, T2, T3, T4, R> LQuadFunction<T1, T2, T3, T4, R> safe(final @Nullable LQuadFunction<T1, T2, T3, T4, R> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T1, T2, T3, T4, R> LSupplier<LQuadFunction<T1, T2, T3, T4, R>> safeSupplier(final @Nullable LSupplier<LQuadFunction<T1, T2, T3, T4, R>> supplier) {
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
	default <V1, V2, V3, V4> LQuadFunction<V1, V2, V3, V4, R> compose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LFunction<? super V3, ? extends T3> before3,
			@Nonnull final LFunction<? super V4, ? extends T4> before4) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		Null.nonNullArg(before4, "before4");
		return (v1, v2, v3, v4) -> this.apply(before1.apply(v1), before2.apply(v2), before3.apply(v3), before4.apply(v4));
	}

	public static <V1, V2, V3, V4, T1, T2, T3, T4, R> LQuadFunction<V1, V2, V3, V4, R> composed(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2,
			@Nonnull final LFunction<? super V3, ? extends T3> before3, @Nonnull final LFunction<? super V4, ? extends T4> before4, LQuadFunction<T1, T2, T3, T4, R> after) {
		return after.compose(before1, before2, before3, before4);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LQuadFunction<T1, T2, T3, T4, V> then(@Nonnull LFunction<? super R, ? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3, a4) -> after.apply(this.apply(a1, a2, a3, a4));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LQuadConsumer<T1, T2, T3, T4> thenConsume(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3, a4) -> after.accept(this.apply(a1, a2, a3, a4));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LQuadFunction<T1, T2, T3, T4, R> nonNullable() {
		return this::nonNullApply;
	}

	/** Does nothing (LQuadFunction) Function */
	public static <T1, T2, T3, T4, R> R doNothing(T1 a1, T2 a2, T3 a3, T4 a4) {
		return (R) Function4U.defaultObject;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, C3, C4> void forEach(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		int i = 0;
		for (; i < size; i++) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = oiFunc4.apply(source4, i);
			consumer.accept(this.apply(a1, a2, a3, a4));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, C3, C4> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4, LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = oiFunc4.apply(source4, i);
			consumer.accept(this.apply(a1, a2, a3, a4));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2, C3, C4> void iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = oiFunc4.apply(source4, i);
			consumer.accept(this.apply(a1, a2, a3, a4));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, I2, C3, C4> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4, LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		int size = ia3.size(source3);
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && i < size) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = oiFunc4.apply(source4, i);
			consumer.accept(this.apply(a1, a2, a3, a4));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, C3, I3, C4> void iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		int i = 0;
		while (i < size && testFunc3.test(iterator3)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = oiFunc4.apply(source4, i);
			consumer.accept(this.apply(a1, a2, a3, a4));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, C3, I3, C4> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4, LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size && testFunc3.test(iterator3)) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = oiFunc4.apply(source4, i);
			consumer.accept(this.apply(a1, a2, a3, a4));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2, C3, I3, C4> void iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		int i = 0;
		while (i < size && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = oiFunc4.apply(source4, i);
			consumer.accept(this.apply(a1, a2, a3, a4));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, I2, C3, I3, C4> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4,
			LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		int size = ia4.size(source4);
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && testFunc3.test(iterator3) && i < size) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = oiFunc4.apply(source4, i);
			consumer.accept(this.apply(a1, a2, a3, a4));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, C3, C4, I4> void iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4, C4 source4, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		int i = 0;
		while (i < size && testFunc4.test(iterator4)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = nextFunc4.apply(iterator4);
			consumer.accept(this.apply(a1, a2, a3, a4));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, C3, C4, I4> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4, C4 source4, LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size && testFunc4.test(iterator4)) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = nextFunc4.apply(iterator4);
			consumer.accept(this.apply(a1, a2, a3, a4));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2, C3, C4, I4> void iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4, C4 source4, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2) && testFunc4.test(iterator4)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = nextFunc4.apply(iterator4);
			consumer.accept(this.apply(a1, a2, a3, a4));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, I2, C3, C4, I4> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4, C4 source4,
			LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		int size = ia3.size(source3);
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && i < size && testFunc4.test(iterator4)) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = nextFunc4.apply(iterator4);
			consumer.accept(this.apply(a1, a2, a3, a4));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, C3, I3, C4, I4> void iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4, C4 source4, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		int i = 0;
		while (i < size && testFunc3.test(iterator3) && testFunc4.test(iterator4)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = nextFunc4.apply(iterator4);
			consumer.accept(this.apply(a1, a2, a3, a4));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, C3, I3, C4, I4> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4, C4 source4,
			LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size && testFunc3.test(iterator3) && testFunc4.test(iterator4)) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = nextFunc4.apply(iterator4);
			consumer.accept(this.apply(a1, a2, a3, a4));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2, C3, I3, C4, I4> void iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4, C4 source4,
			LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2) && testFunc3.test(iterator3) && testFunc4.test(iterator4)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = nextFunc4.apply(iterator4);
			consumer.accept(this.apply(a1, a2, a3, a4));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C1, I1, C2, I2, C3, I3, C4, I4> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4, C4 source4,
			LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && testFunc3.test(iterator3) && testFunc4.test(iterator4)) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = nextFunc4.apply(iterator4);
			consumer.accept(this.apply(a1, a2, a3, a4));
		}
	}

}
