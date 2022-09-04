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
 * Non-throwing functional interface (lambda) LFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): T a
 *
 * Co-domain: R
 *
 * Special case of function that corresponds to expressions like (iterator) -> Iterator::next
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LFunction<T, R> extends Function<T, R>, MetaFunction, MetaInterface.NonThrowing, OFunction<T, a<R>>, Codomain<a<R>>, Domain1<a<T>> { // NOSONAR

	String DESCRIPTION = "LFunction: R apply(T a)";

	@Nullable
	// R apply(T a) ;
	default R apply(T a) {
		// return nestingApply(a);
		try {
			return this.applyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call apply(T a)
	 */
	R applyX(T a) throws Throwable;

	default R tupleApply(LSingle<T> args) {
		return apply(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default R handlingApply(T a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LFunction<T, R> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> handlingApply(a, handling);
	}

	default R apply(T a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default R apply(T a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default R apply(T a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default R apply(T a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LFunction<T, R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return a -> apply(a, factory, newMessage);
	}

	default LFunction<T, R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return a -> apply(a, factory, newMessage, param1);
	}

	default LFunction<T, R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return a -> apply(a, factory, newMessage, param1, param1);
	}

	default LFunction<T, R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return a -> apply(a, factory, newMessage, param1, param2, param3);
	}

	default R apply(T a, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LFunction<T, R> trying(@Nonnull ExWF<RuntimeException> factory) {
		return a -> apply(a, factory);
	}

	default R applyThen(T a, @Nonnull LFunction<Throwable, R> handler) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.apply(e);
		}
	}

	default LFunction<T, R> tryingThen(@Nonnull LFunction<Throwable, R> handler) {
		return a -> applyThen(a, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default R nestingApply(T a) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default R shovingApply(T a) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T, R> R shovingApply(T a, LFunction<T, R> func) {
		Null.nonNullArg(func, "func");
		return func.shovingApply(a);
	}

	static <T, R> R handlingApply(T a, LFunction<T, R> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApply(a, handling);
	}

	static <T, R> R tryApply(T a, LFunction<T, R> func) {
		Null.nonNullArg(func, "func");
		return func.nestingApply(a);
	}

	static <T, R> R tryApply(T a, LFunction<T, R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.apply(a, factory, newMessage);
	}

	static <T, R> R tryApply(T a, LFunction<T, R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.apply(a, factory, newMessage, param1);
	}

	static <T, R> R tryApply(T a, LFunction<T, R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.apply(a, factory, newMessage, param1, param2);
	}

	static <T, R> R tryApply(T a, LFunction<T, R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.apply(a, factory, newMessage, param1, param2, param3);
	}

	static <T, R> R tryApply(T a, LFunction<T, R> func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.apply(a, factory);
	}

	static <T, R> R tryApplyThen(T a, LFunction<T, R> func, @Nonnull LFunction<Throwable, R> handler) {
		Null.nonNullArg(func, "func");
		return func.applyThen(a, handler);
	}

	default R failSafeApply(T a, @Nonnull LFunction<T, R> failSafe) {
		try {
			return apply(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.apply(a);
		}
	}

	static <T, R> R failSafeApply(T a, LFunction<T, R> func, @Nonnull LFunction<T, R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.apply(a);
		} else {
			return func.failSafeApply(a, failSafe);
		}
	}

	static <T, R> LFunction<T, R> failSafe(LFunction<T, R> func, @Nonnull LFunction<T, R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeApply(a, func, failSafe);
	}

	LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullApply() method cannot be null (" + DESCRIPTION + ").";

	/** Function call that ensures the result is not null */
	@Nonnull
	default R nonNullApply(T a) {
		return Null.nonNull(apply(a), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T, R> void fromTo(int min_i, int max_i, T a, @Nonnull LFunction<T, R> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.apply(a);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.apply(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T, R> void fromTill(int min_i, int max_i, T a, @Nonnull LFunction<T, R> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.apply(a);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.apply(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T, R> void times(int max_i, T a, @Nonnull LFunction<T, R> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a, func);
	}

	/** Extract and apply function. */
	public static <R, M, K, V> R from(@Nonnull M container, LBiFunction<M, K, V> extractor, K key, @Nonnull LFunction<V, R> function) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.apply(value);
		}

		return null;
	}

	/** Change function to consumer that ignores output. */
	default LConsumer<T> toConsumer() {
		return this::apply;
	}

	/** Calls domain consumer before main function. */
	default LFunction<T, R> beforeDo(@Nonnull LConsumer<T> before) {
		Null.nonNullArg(before, "before");
		return (T a) -> {
			before.accept(a);
			return apply(a);
		};
	}

	/** Calls codomain consumer after main function. */
	default LFunction<T, R> afterDo(@Nonnull LConsumer<R> after) {
		Null.nonNullArg(after, "after");
		return (T a) -> {
			final R retval = apply(a);
			after.accept(retval);
			return retval;
		};
	}

	// <editor-fold desc="CallContext">

	static <T, R> R nestingApply(@Nullable CallContext c1, T a, @Nonnull LFunction<T, R> function) {
		Null.nonNullArg(function, "function");
		try {
			return applyX(c1, a, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static <T, R> R shovingApply(@Nullable CallContext c1, T a, @Nonnull LFunction<T, R> function) {
		Null.nonNullArg(function, "function");
		try {
			return applyX(c1, a, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static <T, R> R applyX(@Nullable CallContext c1, T a, @Nonnull LFunction<T, R> function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContext.tryInit(last, c1);

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;
		Object retval = null;
		if (primary == null) {
			try {
				retval = function.shovingApply(a);
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

	static <T, R> CompletableFuture<R> asyncApply(@Nonnull AsyncCallContext async, @Nullable CallContext c1, T a, @Nonnull LFunction<T, R> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<R> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					var v = LFunction.applyX(c1, a, function);
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

	static <T, R> R nestingApply(@Nullable CallContext c1, @Nullable CallContext c2, T a, @Nonnull LFunction<T, R> function) {
		Null.nonNullArg(function, "function");
		try {
			return applyX(c1, c2, a, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static <T, R> R shovingApply(@Nullable CallContext c1, @Nullable CallContext c2, T a, @Nonnull LFunction<T, R> function) {
		Null.nonNullArg(function, "function");
		try {
			return applyX(c1, c2, a, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static <T, R> R applyX(@Nullable CallContext c1, @Nullable CallContext c2, T a, @Nonnull LFunction<T, R> function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContext.tryInit(last, c1);
		final Object s2 = last = CallContext.tryInit(last, c2);

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;
		Object retval = null;
		if (primary == null) {
			try {
				retval = function.shovingApply(a);
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

	static <T, R> CompletableFuture<R> asyncApply(@Nonnull AsyncCallContext async, @Nullable CallContext c1, @Nullable CallContext c2, T a, @Nonnull LFunction<T, R> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<R> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					var v = LFunction.applyX(c1, c2, a, function);
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

	static <T, R> R nestingApply(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, T a, @Nonnull LFunction<T, R> function) {
		Null.nonNullArg(function, "function");
		try {
			return applyX(c1, c2, c3, a, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static <T, R> R shovingApply(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, T a, @Nonnull LFunction<T, R> function) {
		Null.nonNullArg(function, "function");
		try {
			return applyX(c1, c2, c3, a, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static <T, R> R applyX(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, T a, @Nonnull LFunction<T, R> function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContext.tryInit(last, c1);
		final Object s2 = last = CallContext.tryInit(last, c2);
		final Object s3 = last = CallContext.tryInit(last, c3);

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;
		Object retval = null;
		if (primary == null) {
			try {
				retval = function.shovingApply(a);
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

	static <T, R> CompletableFuture<R> asyncApply(@Nonnull AsyncCallContext async, @Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, T a, @Nonnull LFunction<T, R> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<R> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					var v = LFunction.applyX(c1, c2, c3, a, function);
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

	static <T, R> R nestingApply(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, T a, @Nonnull LFunction<T, R> function) {
		Null.nonNullArg(function, "function");
		try {
			return applyX(c1, c2, c3, c4, a, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static <T, R> R shovingApply(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, T a, @Nonnull LFunction<T, R> function) {
		Null.nonNullArg(function, "function");
		try {
			return applyX(c1, c2, c3, c4, a, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static <T, R> R applyX(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, T a, @Nonnull LFunction<T, R> function) throws Throwable {
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
				retval = function.shovingApply(a);
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

	static <T, R> CompletableFuture<R> asyncApply(@Nonnull AsyncCallContext async, @Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, T a, @Nonnull LFunction<T, R> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<R> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					var v = LFunction.applyX(c1, c2, c3, c4, a, function);
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

	static <T, R> CompletableFuture<R> asyncApply(@Nonnull AsyncCallContext async, T a, @Nonnull LFunction<T, R> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<R> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					var v = function.applyX(a);
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

	/** Creates function that always returns the same value. */
	static <T, R> LFunction<T, R> constant(R r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, R> LFunction<T, R> func(final @Nonnull LFunction<T, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** A completely inconvenient method in case lambda expression and generic arguments are ambiguous for the compiler. */
	@Nonnull
	static <T, R> LFunction<T, R> func(@Nullable Class<T> c1, @Nullable Class<R> c2, final @Nonnull LFunction<T, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	final class S<T, R> implements LFunction<T, R> {
		private LFunction<T, R> target = null;
		@Override
		public R applyX(T a) throws Throwable {
			return target.applyX(a);
		}
	}

	@Nonnull
	static <T, R> LFunction<T, R> recursive(final @Nonnull LFunction<LFunction<T, R>, LFunction<T, R>> selfLambda) {
		final S<T, R> single = new S();
		LFunction<T, R> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	public static <T, R> M<T, R> mementoOf(T a, LFunction<T, R> function) {
		var initialValue = function.apply(a);
		return initializedMementoOf(initialValue, function);
	}

	public static <T, R> M<T, R> initializedMementoOf(R initialValue, LFunction<T, R> function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static <T, R> M<T, R> deltaOf(T a, LFunction<T, R> function, LBinaryOperator<R> deltaFunction) {
		var initialValue = function.apply(a);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static <T, R> M<T, R> initializedDeltaOf(R initialValue, LFunction<T, R> function, LBinaryOperator<R> deltaFunction) {
		return memento(initialValue, deltaFunction.apply(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.apply(x1, x2));
	}

	public static <T, R> M<T, R> memento(R initialBaseValue, R initialValue, LFunction<T, R> baseFunction, LTernaryOperator<R> mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LFunction.M)
	 */
	@NotThreadSafe
	final class M<T, R> implements LFunction<T, R> {

		private final LFunction<T, R> baseFunction;
		private R lastBaseValue;
		private R lastValue;
		private final LTernaryOperator<R> mementoFunction;

		private M(R lastBaseValue, R lastValue, LFunction<T, R> baseFunction, LTernaryOperator<R> mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public R applyX(T a) throws Throwable {
			R x1 = lastBaseValue;
			R x2 = lastBaseValue = baseFunction.applyX(a);

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
	static <T, R> LFunction<T, R> funcThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T, R> LFunction<T, R> funcThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce(message);
		};
	}

	static <T, R> R call(T a, final @Nonnull LFunction<T, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.apply(a);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <T, R> LFunction<T, R> wrap(final Function<T, R> other) {
		return other::apply;
	}
	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LFunction<V, R> compose(@Nonnull final LFunction<? super V, ? extends T> before) {
		Null.nonNullArg(before, "before");
		return v -> this.apply(before.apply(v));
	}

	public static <V, T, R> LFunction<V, R> composed(@Nonnull final LFunction<? super V, ? extends T> before, LFunction<T, R> after) {
		return after.compose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LFunction<T, V> then(@Nonnull LFunction<? super R, ? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.apply(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LConsumer<T> thenConsume(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.accept(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToByteFunction<T> thenToByte(@Nonnull LToByteFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsByte(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToSrtFunction<T> thenToSrt(@Nonnull LToSrtFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsSrt(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToIntFunction<T> thenToInt(@Nonnull LToIntFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsInt(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToLongFunction<T> thenToLong(@Nonnull LToLongFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsLong(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToFltFunction<T> thenToFlt(@Nonnull LToFltFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsFlt(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToDblFunction<T> thenToDbl(@Nonnull LToDblFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsDbl(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToCharFunction<T> thenToChar(@Nonnull LToCharFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsChar(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LPredicate<T> thenToBool(@Nonnull LPredicate<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.test(this.apply(a));
	}

	// </editor-fold>

	/** Returns a function that always returns its input argument. */
	@Nonnull
	static <V> LFunction<V, V> identity() {
		return t -> t;
	}

	static <R> R identity(R a) {
		return a;
	}

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LFunction<T, R> nonNullable() {
		return this::nonNullApply;
	}

	/** Does nothing (LFunction) Function */
	public static <T, R> R doNothing(T a) {
		return (R) Function4U.defaultObject;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C0> void forEach(IndexedRead<C0, a<T>> ia, C0 source, LConsumer<? super R> consumer) {
		int size = ia.size(source);
		LOiFunction<Object, T> oiFunc0 = (LOiFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			T a = oiFunc0.apply(source, i);
			consumer.accept(this.apply(a));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C0, I0> void iterate(SequentialRead<C0, I0, a<T>> sa, C0 source, LConsumer<? super R> consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).apply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LFunction<Object, T> nextFunc0 = (LFunction) sa.supplier();
		while (testFunc0.test(iterator0)) {
			T a = nextFunc0.apply(iterator0);
			consumer.accept(this.apply(a));
		}
	}

}
