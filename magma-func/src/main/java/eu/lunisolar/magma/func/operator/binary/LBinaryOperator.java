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

package eu.lunisolar.magma.func.operator.binary;

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
 * Non-throwing functional interface (lambda) LBinaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 2): T a1,T a2
 *
 * Co-domain: T
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBinaryOperator<T> extends BinaryOperator<T>, MetaOperator, MetaInterface.NonThrowing, Codomain<a<T>>, Domain2<a<T>, a<T>>, LBiFunction<T, T, T> { // NOSONAR

	String DESCRIPTION = "LBinaryOperator: T apply(T a1,T a2)";

	default T tupleApply(LPair<T, T> args) {
		return apply(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default T handlingApply(T a1, T a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LBinaryOperator<T> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2) -> handlingApply(a1, a2, handling);
	}

	default T apply(T a1, T a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default T apply(T a1, T a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default T apply(T a1, T a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default T apply(T a1, T a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LBinaryOperator<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2) -> apply(a1, a2, factory, newMessage);
	}

	default LBinaryOperator<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2) -> apply(a1, a2, factory, newMessage, param1);
	}

	default LBinaryOperator<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2) -> apply(a1, a2, factory, newMessage, param1, param1);
	}

	default LBinaryOperator<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2) -> apply(a1, a2, factory, newMessage, param1, param2, param3);
	}

	default T apply(T a1, T a2, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LBinaryOperator<T> trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2) -> apply(a1, a2, factory);
	}

	default T applyThen(T a1, T a2, @Nonnull LFunction<Throwable, T> handler) {
		try {
			return this.applyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.apply(e);
		}
	}

	default LBinaryOperator<T> tryingThen(@Nonnull LFunction<Throwable, T> handler) {
		return (a1, a2) -> applyThen(a1, a2, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default T nestingApply(T a1, T a2) {
		try {
			return this.applyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default T shovingApply(T a1, T a2) {
		try {
			return this.applyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> T shovingApply(T a1, T a2, LBinaryOperator<T> func) {
		Null.nonNullArg(func, "func");
		return func.shovingApply(a1, a2);
	}

	static <T> T handlingApply(T a1, T a2, LBinaryOperator<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApply(a1, a2, handling);
	}

	static <T> T tryApply(T a1, T a2, LBinaryOperator<T> func) {
		Null.nonNullArg(func, "func");
		return func.nestingApply(a1, a2);
	}

	static <T> T tryApply(T a1, T a2, LBinaryOperator<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, factory, newMessage);
	}

	static <T> T tryApply(T a1, T a2, LBinaryOperator<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, factory, newMessage, param1);
	}

	static <T> T tryApply(T a1, T a2, LBinaryOperator<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, factory, newMessage, param1, param2);
	}

	static <T> T tryApply(T a1, T a2, LBinaryOperator<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, factory, newMessage, param1, param2, param3);
	}

	static <T> T tryApply(T a1, T a2, LBinaryOperator<T> func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, factory);
	}

	static <T> T tryApplyThen(T a1, T a2, LBinaryOperator<T> func, @Nonnull LFunction<Throwable, T> handler) {
		Null.nonNullArg(func, "func");
		return func.applyThen(a1, a2, handler);
	}

	LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullApply() method cannot be null (" + DESCRIPTION + ").";

	/** Function call that ensures the result is not null */
	@Nonnull
	default T nonNullApply(T a1, T a2) {
		return Null.nonNull(apply(a1, a2), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBinaryOperator.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_i, int max_i, T a1, T a2, @Nonnull LBinaryOperator<T> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.apply(a1, a2);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.apply(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTill(int min_i, int max_i, T a1, T a2, @Nonnull LBinaryOperator<T> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.apply(a1, a2);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.apply(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void times(int max_i, T a1, T a2, @Nonnull LBinaryOperator<T> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, func);
	}

	/** Extract and apply function. */
	public static <M, K, V, T> T from(@Nonnull M container, LBiFunction<M, K, V> extractor, K key, T a2, @Nonnull LBiFunction<V, T, T> function) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.apply(value, a2);
		}

		return null;
	}

	default LUnaryOperator<T> _with(T a1) {
		return a2 -> apply(a1, a2);
	}

	default LUnaryOperator<T> with(T a2) {
		return a1 -> apply(a1, a2);
	}

	/**  */
	public static <T> LBinaryOperator<T> uncurry(@Nonnull LFunction<T, LUnaryOperator<T>> func) {
		Null.nonNullArg(func, "func");
		return (T a1, T a2) -> func.apply(a1).apply(a2);
	}

	/** Change function to one that ignores output. */
	default LBiConsumer<T, T> toConsumer() {
		return this::apply;
	}

	/** Calls domain consumer before main function. */
	default LBinaryOperator<T> beforeDo(@Nonnull LBiConsumer<T, T> before) {
		Null.nonNullArg(before, "before");
		return (T a1, T a2) -> {
			before.accept(a1, a2);
			return apply(a1, a2);
		};
	}

	/** Calls codomain consumer after main function. */
	default LBinaryOperator<T> afterDo(@Nonnull LConsumer<T> after) {
		Null.nonNullArg(after, "after");
		return (T a1, T a2) -> {
			final T retval = apply(a1, a2);
			after.accept(retval);
			return retval;
		};
	}

	// <editor-fold desc="CallContext">

	static <T> T nestingApply(@Nullable CallContext c1, T a1, T a2, @Nonnull LBinaryOperator<T> function) {
		Null.nonNullArg(function, "function");
		try {
			return applyX(c1, a1, a2, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static <T> T shovingApply(@Nullable CallContext c1, T a1, T a2, @Nonnull LBinaryOperator<T> function) {
		Null.nonNullArg(function, "function");
		try {
			return applyX(c1, a1, a2, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static <T> T applyX(@Nullable CallContext c1, T a1, T a2, @Nonnull LBinaryOperator<T> function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContext.tryInit(last, c1);

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;
		Object retval = null;
		if (primary == null) {
			try {
				retval = function.shovingApply(a1, a2);
			} catch (Throwable e) {
				primary = e;
			}
		}

		primary = CallContext.tryFinish(primary, c1, s1);

		if (primary != null) {
			throw Handling.throwIt(primary);
		}
		return (T) retval;
	}

	static <T> CompletableFuture<T> asyncApply(@Nonnull AsyncCallContext async, @Nullable CallContext c1, T a1, T a2, @Nonnull LBinaryOperator<T> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<T> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					var v = LBinaryOperator.applyX(c1, a1, a2, function);
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

	static <T> T nestingApply(@Nullable CallContext c1, @Nullable CallContext c2, T a1, T a2, @Nonnull LBinaryOperator<T> function) {
		Null.nonNullArg(function, "function");
		try {
			return applyX(c1, c2, a1, a2, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static <T> T shovingApply(@Nullable CallContext c1, @Nullable CallContext c2, T a1, T a2, @Nonnull LBinaryOperator<T> function) {
		Null.nonNullArg(function, "function");
		try {
			return applyX(c1, c2, a1, a2, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static <T> T applyX(@Nullable CallContext c1, @Nullable CallContext c2, T a1, T a2, @Nonnull LBinaryOperator<T> function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContext.tryInit(last, c1);
		final Object s2 = last = CallContext.tryInit(last, c2);

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;
		Object retval = null;
		if (primary == null) {
			try {
				retval = function.shovingApply(a1, a2);
			} catch (Throwable e) {
				primary = e;
			}
		}

		primary = CallContext.tryFinish(primary, c2, s2);
		primary = CallContext.tryFinish(primary, c1, s1);

		if (primary != null) {
			throw Handling.throwIt(primary);
		}
		return (T) retval;
	}

	static <T> CompletableFuture<T> asyncApply(@Nonnull AsyncCallContext async, @Nullable CallContext c1, @Nullable CallContext c2, T a1, T a2, @Nonnull LBinaryOperator<T> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<T> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					var v = LBinaryOperator.applyX(c1, c2, a1, a2, function);
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

	static <T> T nestingApply(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, T a1, T a2, @Nonnull LBinaryOperator<T> function) {
		Null.nonNullArg(function, "function");
		try {
			return applyX(c1, c2, c3, a1, a2, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static <T> T shovingApply(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, T a1, T a2, @Nonnull LBinaryOperator<T> function) {
		Null.nonNullArg(function, "function");
		try {
			return applyX(c1, c2, c3, a1, a2, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static <T> T applyX(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, T a1, T a2, @Nonnull LBinaryOperator<T> function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContext.tryInit(last, c1);
		final Object s2 = last = CallContext.tryInit(last, c2);
		final Object s3 = last = CallContext.tryInit(last, c3);

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;
		Object retval = null;
		if (primary == null) {
			try {
				retval = function.shovingApply(a1, a2);
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
		return (T) retval;
	}

	static <T> CompletableFuture<T> asyncApply(@Nonnull AsyncCallContext async, @Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, T a1, T a2, @Nonnull LBinaryOperator<T> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<T> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					var v = LBinaryOperator.applyX(c1, c2, c3, a1, a2, function);
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

	static <T> T nestingApply(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, T a1, T a2, @Nonnull LBinaryOperator<T> function) {
		Null.nonNullArg(function, "function");
		try {
			return applyX(c1, c2, c3, c4, a1, a2, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static <T> T shovingApply(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, T a1, T a2, @Nonnull LBinaryOperator<T> function) {
		Null.nonNullArg(function, "function");
		try {
			return applyX(c1, c2, c3, c4, a1, a2, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static <T> T applyX(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, T a1, T a2, @Nonnull LBinaryOperator<T> function) throws Throwable {
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
				retval = function.shovingApply(a1, a2);
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
		return (T) retval;
	}

	static <T> CompletableFuture<T> asyncApply(@Nonnull AsyncCallContext async, @Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, T a1, T a2, @Nonnull LBinaryOperator<T> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<T> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					var v = LBinaryOperator.applyX(c1, c2, c3, c4, a1, a2, function);
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

	static <T> CompletableFuture<T> asyncApply(@Nonnull AsyncCallContext async, T a1, T a2, @Nonnull LBinaryOperator<T> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<T> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					var v = function.applyX(a1, a2);
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
	static <T> LBinaryOperator<T> constant(T r) {
		return (a1, a2) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LBinaryOperator<T> binaryOp(final @Nonnull LBinaryOperator<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S<T> implements LBinaryOperator<T> {
		private LBinaryOperator<T> target = null;
		@Override
		public T applyX(T a1, T a2) throws Throwable {
			return target.applyX(a1, a2);
		}
	}

	@Nonnull
	static <T> LBinaryOperator<T> recursive(final @Nonnull LFunction<LBinaryOperator<T>, LBinaryOperator<T>> selfLambda) {
		final S<T> single = new S();
		LBinaryOperator<T> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static <T> M<T> mementoOf(T a1, T a2, LBinaryOperator<T> function) {
		var initialValue = function.apply(a1, a2);
		return initializedMementoOf(initialValue, function);
	}

	public static <T> M<T> initializedMementoOf(T initialValue, LBinaryOperator<T> function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static <T> M<T> deltaOf(T a1, T a2, LBinaryOperator<T> function, LBinaryOperator<T> deltaFunction) {
		var initialValue = function.apply(a1, a2);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static <T> M<T> initializedDeltaOf(T initialValue, LBinaryOperator<T> function, LBinaryOperator<T> deltaFunction) {
		return memento(initialValue, deltaFunction.apply(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.apply(x1, x2));
	}

	public static <T> M<T> memento(T initialBaseValue, T initialValue, LBinaryOperator<T> baseFunction, LTernaryOperator<T> mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LBinaryOperator.M)
	 */
	@NotThreadSafe
	final class M<T> implements LBinaryOperator<T> {

		private final LBinaryOperator<T> baseFunction;
		private T lastBaseValue;
		private T lastValue;
		private final LTernaryOperator<T> mementoFunction;

		private M(T lastBaseValue, T lastValue, LBinaryOperator<T> baseFunction, LTernaryOperator<T> mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public T applyX(T a1, T a2) throws Throwable {
			T x1 = lastBaseValue;
			T x2 = lastBaseValue = baseFunction.applyX(a1, a2);

			return lastValue = mementoFunction.applyX(lastValue, x1, x2);
		}

		public T currentApply(T a1, T a2) {
			T x1 = lastBaseValue;
			T x2 = baseFunction.apply(a1, a2);

			return mementoFunction.apply(lastValue, x1, x2);
		}

		public T lastValue() {
			return lastValue;
		};

		public T lastBaseValue() {
			return lastBaseValue;
		};
	}

	// </editor-fold>

	@Nonnull
	static <T> LBinaryOperator<T> binaryOpThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T> LBinaryOperator<T> binaryOpThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce(message);
		};
	}

	static <T> T call(T a1, T a2, final @Nonnull LBinaryOperator<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.apply(a1, a2);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <T> LBinaryOperator<T> wrap(final BinaryOperator<T> other) {
		return other::apply;
	}
	// </editor-fold>

	/**
	 * Creates function that returns the lesser value according to the comparator.
	 * @see {@link java.util.function.BinaryOperator#minBy}
	 */
	@Nonnull
	static <T> LBinaryOperator<T> minBy(@Nonnull Comparator<? super T> comparator) {
		Null.nonNullArg(comparator, "comparator");
		return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
	}

	/**
	 * Creates function that returns the lesser value according to the comparator.
	 * @see {@link java.util.function.BinaryOperator#maxBy}
	 */
	@Nonnull
	static <T> LBinaryOperator<T> maxBy(@Nonnull Comparator<? super T> comparator) {
		Null.nonNullArg(comparator, "comparator");
		return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
	}

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiFunction<T, T, V> then(@Nonnull LFunction<? super T, ? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.apply(this.apply(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToByteBiFunction<T, T> thenToByte(@Nonnull LToByteFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsByte(this.apply(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToSrtBiFunction<T, T> thenToSrt(@Nonnull LToSrtFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsSrt(this.apply(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToIntBiFunction<T, T> thenToInt(@Nonnull LToIntFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsInt(this.apply(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToLongBiFunction<T, T> thenToLong(@Nonnull LToLongFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsLong(this.apply(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToFltBiFunction<T, T> thenToFlt(@Nonnull LToFltFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsFlt(this.apply(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToDblBiFunction<T, T> thenToDbl(@Nonnull LToDblFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsDbl(this.apply(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToCharBiFunction<T, T> thenToChar(@Nonnull LToCharFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsChar(this.apply(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiPredicate<T, T> thenToBool(@Nonnull LPredicate<? super T> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.test(this.apply(a1, a2));
	}

	// </editor-fold>

	default LBinaryOperator<T> shoving() {

		return new LBinaryOperator<T>() {

			@Nullable
			public T apply(T a1, T a2) {
				try {
					return this.applyX(a1, a2);
				} catch (Throwable e) { // NOSONAR
					Handling.handleErrors(e);
					throw Handling.throwIt(e);
				}
			}

			public T applyX(T a1, T a2) throws Throwable {
				return LBinaryOperator.this.applyX(a1, a2);
			}

		};
	}

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LBinaryOperator<T> nonNullable() {
		return this::nonNullApply;
	}

	/** Does nothing (LBinaryOperator) Operator */
	public static <T> T doNothing(T a1, T a2) {
		return (T) Function4U.defaultObject;
	}

}
