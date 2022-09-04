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

	static <T1, T2, T3, T4, R> R shovingApply(T1 a1, T2 a2, T3 a3, T4 a4, LQuadFunction<T1, T2, T3, T4, R> func) {
		Null.nonNullArg(func, "func");
		return func.shovingApply(a1, a2, a3, a4);
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

	// <editor-fold desc="CallContext">

	static <T1, T2, T3, T4, R> R nestingApply(@Nullable CallContext c1, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadFunction<T1, T2, T3, T4, R> function) {
		Null.nonNullArg(function, "function");
		try {
			return applyX(c1, a1, a2, a3, a4, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static <T1, T2, T3, T4, R> R shovingApply(@Nullable CallContext c1, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadFunction<T1, T2, T3, T4, R> function) {
		Null.nonNullArg(function, "function");
		try {
			return applyX(c1, a1, a2, a3, a4, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static <T1, T2, T3, T4, R> R applyX(@Nullable CallContext c1, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadFunction<T1, T2, T3, T4, R> function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContext.tryInit(last, c1);

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;
		Object retval = null;
		if (primary == null) {
			try {
				retval = function.shovingApply(a1, a2, a3, a4);
			} catch (Throwable e) {
				primary = e;
			}
		}

		primary = CallContext.tryFinish(primary, c1, s1);

		if (primary != null) {
			throw Handling.throwIt(primary);
		}
		return (R) retval;
	}

	static <T1, T2, T3, T4, R> CompletableFuture<R> asyncApply(@Nonnull AsyncCallContext async, @Nullable CallContext c1, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadFunction<T1, T2, T3, T4, R> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<R> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					var v = LQuadFunction.applyX(c1, a1, a2, a3, a4, function);
					future.complete(v);
				} catch (Throwable e) {
					Handling.handleErrors(e);
					future.completeExceptionally(e);
				}
			});
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
		return future;
	}

	static <T1, T2, T3, T4, R> R nestingApply(@Nullable CallContext c1, @Nullable CallContext c2, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadFunction<T1, T2, T3, T4, R> function) {
		Null.nonNullArg(function, "function");
		try {
			return applyX(c1, c2, a1, a2, a3, a4, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static <T1, T2, T3, T4, R> R shovingApply(@Nullable CallContext c1, @Nullable CallContext c2, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadFunction<T1, T2, T3, T4, R> function) {
		Null.nonNullArg(function, "function");
		try {
			return applyX(c1, c2, a1, a2, a3, a4, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static <T1, T2, T3, T4, R> R applyX(@Nullable CallContext c1, @Nullable CallContext c2, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadFunction<T1, T2, T3, T4, R> function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContext.tryInit(last, c1);
		final Object s2 = last = CallContext.tryInit(last, c2);

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;
		Object retval = null;
		if (primary == null) {
			try {
				retval = function.shovingApply(a1, a2, a3, a4);
			} catch (Throwable e) {
				primary = e;
			}
		}

		primary = CallContext.tryFinish(primary, c2, s2);
		primary = CallContext.tryFinish(primary, c1, s1);

		if (primary != null) {
			throw Handling.throwIt(primary);
		}
		return (R) retval;
	}

	static <T1, T2, T3, T4, R> CompletableFuture<R> asyncApply(@Nonnull AsyncCallContext async, @Nullable CallContext c1, @Nullable CallContext c2, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadFunction<T1, T2, T3, T4, R> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<R> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					var v = LQuadFunction.applyX(c1, c2, a1, a2, a3, a4, function);
					future.complete(v);
				} catch (Throwable e) {
					Handling.handleErrors(e);
					future.completeExceptionally(e);
				}
			});
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
		return future;
	}

	static <T1, T2, T3, T4, R> R nestingApply(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadFunction<T1, T2, T3, T4, R> function) {
		Null.nonNullArg(function, "function");
		try {
			return applyX(c1, c2, c3, a1, a2, a3, a4, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static <T1, T2, T3, T4, R> R shovingApply(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadFunction<T1, T2, T3, T4, R> function) {
		Null.nonNullArg(function, "function");
		try {
			return applyX(c1, c2, c3, a1, a2, a3, a4, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static <T1, T2, T3, T4, R> R applyX(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadFunction<T1, T2, T3, T4, R> function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContext.tryInit(last, c1);
		final Object s2 = last = CallContext.tryInit(last, c2);
		final Object s3 = last = CallContext.tryInit(last, c3);

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;
		Object retval = null;
		if (primary == null) {
			try {
				retval = function.shovingApply(a1, a2, a3, a4);
			} catch (Throwable e) {
				primary = e;
			}
		}

		primary = CallContext.tryFinish(primary, c3, s3);
		primary = CallContext.tryFinish(primary, c2, s2);
		primary = CallContext.tryFinish(primary, c1, s1);

		if (primary != null) {
			throw Handling.throwIt(primary);
		}
		return (R) retval;
	}

	static <T1, T2, T3, T4, R> CompletableFuture<R> asyncApply(@Nonnull AsyncCallContext async, @Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadFunction<T1, T2, T3, T4, R> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<R> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					var v = LQuadFunction.applyX(c1, c2, c3, a1, a2, a3, a4, function);
					future.complete(v);
				} catch (Throwable e) {
					Handling.handleErrors(e);
					future.completeExceptionally(e);
				}
			});
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
		return future;
	}

	static <T1, T2, T3, T4, R> R nestingApply(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadFunction<T1, T2, T3, T4, R> function) {
		Null.nonNullArg(function, "function");
		try {
			return applyX(c1, c2, c3, c4, a1, a2, a3, a4, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static <T1, T2, T3, T4, R> R shovingApply(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadFunction<T1, T2, T3, T4, R> function) {
		Null.nonNullArg(function, "function");
		try {
			return applyX(c1, c2, c3, c4, a1, a2, a3, a4, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static <T1, T2, T3, T4, R> R applyX(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadFunction<T1, T2, T3, T4, R> function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContext.tryInit(last, c1);
		final Object s2 = last = CallContext.tryInit(last, c2);
		final Object s3 = last = CallContext.tryInit(last, c3);
		final Object s4 = last = CallContext.tryInit(last, c4);

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;
		Object retval = null;
		if (primary == null) {
			try {
				retval = function.shovingApply(a1, a2, a3, a4);
			} catch (Throwable e) {
				primary = e;
			}
		}

		primary = CallContext.tryFinish(primary, c4, s4);
		primary = CallContext.tryFinish(primary, c3, s3);
		primary = CallContext.tryFinish(primary, c2, s2);
		primary = CallContext.tryFinish(primary, c1, s1);

		if (primary != null) {
			throw Handling.throwIt(primary);
		}
		return (R) retval;
	}

	static <T1, T2, T3, T4, R> CompletableFuture<R> asyncApply(@Nonnull AsyncCallContext async, @Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, T1 a1, T2 a2, T3 a3, T4 a4,
			@Nonnull LQuadFunction<T1, T2, T3, T4, R> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<R> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					var v = LQuadFunction.applyX(c1, c2, c3, c4, a1, a2, a3, a4, function);
					future.complete(v);
				} catch (Throwable e) {
					Handling.handleErrors(e);
					future.completeExceptionally(e);
				}
			});
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
		return future;
	}

	static <T1, T2, T3, T4, R> CompletableFuture<R> asyncApply(@Nonnull AsyncCallContext async, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadFunction<T1, T2, T3, T4, R> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<R> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					var v = function.applyX(a1, a2, a3, a4);
					future.complete(v);
				} catch (Throwable e) {
					Handling.handleErrors(e);
					future.completeExceptionally(e);
				}
			});
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
		return future;
	}

	// </editor-fold>

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

}
