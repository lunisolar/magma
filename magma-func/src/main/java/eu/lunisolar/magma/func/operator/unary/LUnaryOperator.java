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

package eu.lunisolar.magma.func.operator.unary;

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
 * Non-throwing functional interface (lambda) LUnaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 1): T a
 *
 * Co-domain: T
 *
 * Special case of function that corresponds to expressions like (iterator) -> Iterator::next
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LUnaryOperator<T> extends UnaryOperator<T>, MetaOperator, MetaInterface.NonThrowing, OFunction<T, a<T>>, Codomain<a<T>>, Domain1<a<T>>, LFunction<T, T> { //NOSONAR

	String DESCRIPTION = "LUnaryOperator: T apply(T a)";

	default T tupleApply(LSingle<T> args) {
		return apply(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default T handlingApply(T a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LUnaryOperator<T> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> handlingApply(a, handling);
	}

	default T apply(T a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default T apply(T a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default T apply(T a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default T apply(T a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LUnaryOperator<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return a -> apply(a, factory, newMessage);
	}

	default LUnaryOperator<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return a -> apply(a, factory, newMessage, param1);
	}

	default LUnaryOperator<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return a -> apply(a, factory, newMessage, param1, param1);
	}

	default LUnaryOperator<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return a -> apply(a, factory, newMessage, param1, param2, param3);
	}

	default T apply(T a, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LUnaryOperator<T> trying(@Nonnull ExWF<RuntimeException> factory) {
		return a -> apply(a, factory);
	}

	default T applyThen(T a, @Nonnull LFunction<Throwable, T> handler) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { //NOSONAR
			Handling.handleErrors(e);
			return handler.apply(e);
		}
	}

	default LUnaryOperator<T> tryingThen(@Nonnull LFunction<Throwable, T> handler) {
		return a -> applyThen(a, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default T nestingApply(T a) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default T shovingApply(T a) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> T shovingApply(T a, LUnaryOperator<T> func) {
		Null.nonNullArg(func, "func");
		return func.shovingApply(a);
	}

	static <T> T handlingApply(T a, LUnaryOperator<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { //<-
		Null.nonNullArg(func, "func");
		return func.handlingApply(a, handling);
	}

	static <T> T tryApply(T a, LUnaryOperator<T> func) {
		Null.nonNullArg(func, "func");
		return func.nestingApply(a);
	}

	static <T> T tryApply(T a, LUnaryOperator<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.apply(a, factory, newMessage);
	}

	static <T> T tryApply(T a, LUnaryOperator<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.apply(a, factory, newMessage, param1);
	}

	static <T> T tryApply(T a, LUnaryOperator<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.apply(a, factory, newMessage, param1, param2);
	}

	static <T> T tryApply(T a, LUnaryOperator<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.apply(a, factory, newMessage, param1, param2, param3);
	}

	static <T> T tryApply(T a, LUnaryOperator<T> func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.apply(a, factory);
	}

	static <T> T tryApplyThen(T a, LUnaryOperator<T> func, @Nonnull LFunction<Throwable, T> handler) {
		Null.nonNullArg(func, "func");
		return func.applyThen(a, handler);
	}

	LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullApply() method cannot be null (" + DESCRIPTION + ").";

	/** Function call that ensures the result is not null */
	@Nonnull
	default T nonNullApply(T a) {
		return Null.nonNull(apply(a), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LUnaryOperator.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_i, int max_i, T a, @Nonnull LUnaryOperator<T> func) {
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
	public static <T> void fromTill(int min_i, int max_i, T a, @Nonnull LUnaryOperator<T> func) {
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
	public static <T> void times(int max_i, T a, @Nonnull LUnaryOperator<T> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a, func);
	}

	/** Extract and apply function. */
	public static <M, K, V, T> T from(@Nonnull M container, LBiFunction<M, K, V> extractor, K key, @Nonnull LFunction<V, T> function) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.apply(value);
		}

		return null;
	}

	/** Change function to one that ignores output. */
	default LConsumer<T> toConsumer() {
		return this::apply;
	}

	/** Calls domain consumer before main function. */
	default LUnaryOperator<T> beforeDo(@Nonnull LConsumer<T> before) {
		Null.nonNullArg(before, "before");
		return (T a) -> {
			before.accept(a);
			return apply(a);
		};
	}

	/** Calls codomain consumer after main function. */
	default LUnaryOperator<T> afterDo(@Nonnull LConsumer<T> after) {
		Null.nonNullArg(after, "after");
		return (T a) -> {
			final T retval = apply(a);
			after.accept(retval);
			return retval;
		};
	}

	//<editor-fold desc="CallContext">

	@Nonnull
	static <T> LUnaryOperator<T> unaryOp(@Nullable CallContext c1, final @Nonnull LUnaryOperator<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.withCC(c1);
	}

	default @Nonnull LUnaryOperator<T> withCC(@Nullable CallContext c1) {
		return a -> LUnaryOperator.shovingApply(c1, a, this);
	}

	static <T> T nestingApply(@Nullable CallContext c1, T a, @Nonnull LUnaryOperator<T> function) {
		Null.nonNullArg(function, "function");
		try {
			return applyX(c1, a, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static <T> T shovingApply(@Nullable CallContext c1, T a, @Nonnull LUnaryOperator<T> function) {
		Null.nonNullArg(function, "function");
		try {
			return applyX(c1, a, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static <T> T applyX(@Nullable CallContext c1, T a, @Nonnull LUnaryOperator<T> function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContexts.tryInit(last, c1); // try { c1?.start() ...

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;
		Object retval = null;
		if (primary == null) {
			try {
				retval = function.shovingApply(a);
			} catch (Throwable e) {
				primary = e;
			}
		}

		primary = CallContexts.tryFinish(primary, c1, s1); // } finally { c1?.end(...) }

		if (primary != null) {
			throw Handling.throwIt(primary);
		}
		return (T) retval;
	}

	static <T> CompletableFuture<T> asyncApply(@Nonnull AsyncCallContext async, @Nullable CallContext c1, T a, @Nonnull LUnaryOperator<T> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<T> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					var v = LUnaryOperator.applyX(c1, a, function);
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

	static <T> T nestingApply(@Nullable CallContext c1, @Nullable CallContext c2, T a, @Nonnull LUnaryOperator<T> function) {
		Null.nonNullArg(function, "function");
		try {
			return applyX(c1, c2, a, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static <T> T shovingApply(@Nullable CallContext c1, @Nullable CallContext c2, T a, @Nonnull LUnaryOperator<T> function) {
		Null.nonNullArg(function, "function");
		try {
			return applyX(c1, c2, a, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static <T> T applyX(@Nullable CallContext c1, @Nullable CallContext c2, T a, @Nonnull LUnaryOperator<T> function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContexts.tryInit(last, c1); // try { c1?.start() ...
		final Object s2 = last = CallContexts.tryInit(last, c2); // try { c2?.start() ...

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;
		Object retval = null;
		if (primary == null) {
			try {
				retval = function.shovingApply(a);
			} catch (Throwable e) {
				primary = e;
			}
		}

		primary = CallContexts.tryFinish(primary, c2, s2); // } finally { c2?.end(...) }
		primary = CallContexts.tryFinish(primary, c1, s1); // } finally { c1?.end(...) }

		if (primary != null) {
			throw Handling.throwIt(primary);
		}
		return (T) retval;
	}

	static <T> CompletableFuture<T> asyncApply(@Nonnull AsyncCallContext async, @Nullable CallContext c1, @Nullable CallContext c2, T a, @Nonnull LUnaryOperator<T> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<T> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					var v = LUnaryOperator.applyX(c1, c2, a, function);
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

	static <T> T nestingApply(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, T a, @Nonnull LUnaryOperator<T> function) {
		Null.nonNullArg(function, "function");
		try {
			return applyX(c1, c2, c3, a, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static <T> T shovingApply(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, T a, @Nonnull LUnaryOperator<T> function) {
		Null.nonNullArg(function, "function");
		try {
			return applyX(c1, c2, c3, a, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static <T> T applyX(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, T a, @Nonnull LUnaryOperator<T> function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContexts.tryInit(last, c1); // try { c1?.start() ...
		final Object s2 = last = CallContexts.tryInit(last, c2); // try { c2?.start() ...
		final Object s3 = last = CallContexts.tryInit(last, c3); // try { c3?.start() ...

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;
		Object retval = null;
		if (primary == null) {
			try {
				retval = function.shovingApply(a);
			} catch (Throwable e) {
				primary = e;
			}
		}

		primary = CallContexts.tryFinish(primary, c3, s3); // } finally { c3?.end(...) }
		primary = CallContexts.tryFinish(primary, c2, s2); // } finally { c2?.end(...) }
		primary = CallContexts.tryFinish(primary, c1, s1); // } finally { c1?.end(...) }

		if (primary != null) {
			throw Handling.throwIt(primary);
		}
		return (T) retval;
	}

	static <T> CompletableFuture<T> asyncApply(@Nonnull AsyncCallContext async, @Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, T a, @Nonnull LUnaryOperator<T> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<T> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					var v = LUnaryOperator.applyX(c1, c2, c3, a, function);
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

	static <T> T nestingApply(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, T a, @Nonnull LUnaryOperator<T> function) {
		Null.nonNullArg(function, "function");
		try {
			return applyX(c1, c2, c3, c4, a, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static <T> T shovingApply(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, T a, @Nonnull LUnaryOperator<T> function) {
		Null.nonNullArg(function, "function");
		try {
			return applyX(c1, c2, c3, c4, a, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static <T> T applyX(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, T a, @Nonnull LUnaryOperator<T> function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContexts.tryInit(last, c1); // try { c1?.start() ...
		final Object s2 = last = CallContexts.tryInit(last, c2); // try { c2?.start() ...
		final Object s3 = last = CallContexts.tryInit(last, c3); // try { c3?.start() ...
		final Object s4 = last = CallContexts.tryInit(last, c4); // try { c4?.start() ...

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;
		Object retval = null;
		if (primary == null) {
			try {
				retval = function.shovingApply(a);
			} catch (Throwable e) {
				primary = e;
			}
		}

		primary = CallContexts.tryFinish(primary, c4, s4); // } finally { c4?.end(...) }
		primary = CallContexts.tryFinish(primary, c3, s3); // } finally { c3?.end(...) }
		primary = CallContexts.tryFinish(primary, c2, s2); // } finally { c2?.end(...) }
		primary = CallContexts.tryFinish(primary, c1, s1); // } finally { c1?.end(...) }

		if (primary != null) {
			throw Handling.throwIt(primary);
		}
		return (T) retval;
	}

	static <T> CompletableFuture<T> asyncApply(@Nonnull AsyncCallContext async, @Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, T a, @Nonnull LUnaryOperator<T> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<T> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					var v = LUnaryOperator.applyX(c1, c2, c3, c4, a, function);
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

	static <T> CompletableFuture<T> asyncApply(@Nonnull AsyncCallContext async, T a, @Nonnull LUnaryOperator<T> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<T> future = new CompletableFuture<>();
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

	//</editor-fold>

	/** Creates function that always returns the same value. */
	static <T> LUnaryOperator<T> constant(T r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LUnaryOperator<T> unaryOp(final @Nonnull LUnaryOperator<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S<T> implements LUnaryOperator<T> {
		private LUnaryOperator<T> target = null;
		@Override
		public T applyX(T a) throws Throwable {
			return target.applyX(a);
		}
	}

	@Nonnull
	static <T> LUnaryOperator<T> recursive(final @Nonnull LFunction<LUnaryOperator<T>, LUnaryOperator<T>> selfLambda) {
		final S<T> single = new S();
		LUnaryOperator<T> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static <T> M<T> mementoOf(T a, LUnaryOperator<T> function) {
		var initialValue = function.apply(a);
		return initializedMementoOf(initialValue, function);
	}

	public static <T> M<T> initializedMementoOf(T initialValue, LUnaryOperator<T> function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static <T> M<T> deltaOf(T a, LUnaryOperator<T> function, LBinaryOperator<T> deltaFunction) {
		var initialValue = function.apply(a);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static <T> M<T> initializedDeltaOf(T initialValue, LUnaryOperator<T> function, LBinaryOperator<T> deltaFunction) {
		return memento(initialValue, deltaFunction.apply(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.apply(x1, x2));
	}

	public static <T> M<T> memento(T initialBaseValue, T initialValue, LUnaryOperator<T> baseFunction, LTernaryOperator<T> mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LUnaryOperator.M)
	 */
	@NotThreadSafe
	final class M<T> implements LUnaryOperator<T> {

		private final LUnaryOperator<T> baseFunction;
		private T lastBaseValue;
		private T lastValue;
		private final LTernaryOperator<T> mementoFunction;

		private M(T lastBaseValue, T lastValue, LUnaryOperator<T> baseFunction, LTernaryOperator<T> mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public T applyX(T a) throws Throwable {
			T x1 = lastBaseValue;
			T x2 = lastBaseValue = baseFunction.applyX(a);

			return lastValue = mementoFunction.applyX(lastValue, x1, x2);
		}

		public T currentApply(T a) {
			T x1 = lastBaseValue;
			T x2 = baseFunction.apply(a);

			return mementoFunction.apply(lastValue, x1, x2);
		}

		public T lastValue() {
			return lastValue;
		}

		public T lastBaseValue() {
			return lastBaseValue;
		}

		public T currentBaseValue(T a) {
			return baseFunction.apply(a);
		}
	}

	// </editor-fold>

	@Nonnull
	static <T> LUnaryOperator<T> unaryOpThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T> LUnaryOperator<T> unaryOpThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce(message);
		};
	}

	static <T> T call(T a, final @Nonnull LUnaryOperator<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.apply(a);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <T> LUnaryOperator<T> wrap(final UnaryOperator<T> other) {
		return other::apply;
	}
	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LFunction<T, V> then(@Nonnull LFunction<? super T, ? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.apply(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToByteFunction<T> thenToByte(@Nonnull LToByteFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsByte(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToSrtFunction<T> thenToSrt(@Nonnull LToSrtFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsSrt(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToIntFunction<T> thenToInt(@Nonnull LToIntFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsInt(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToLongFunction<T> thenToLong(@Nonnull LToLongFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsLong(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToFltFunction<T> thenToFlt(@Nonnull LToFltFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsFlt(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToDblFunction<T> thenToDbl(@Nonnull LToDblFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsDbl(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToCharFunction<T> thenToChar(@Nonnull LToCharFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsChar(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LPredicate<T> thenToBool(@Nonnull LPredicate<? super T> after) {
		Null.nonNullArg(after, "after");
		return a -> after.test(this.apply(a));
	}

	// </editor-fold>

	/** Returns a function that always returns its input argument. */
	@Nonnull
	static <V> LUnaryOperator<V> identity() {
		return t -> t;
	}

	static <T> T identity(T a) {
		return a;
	}

	default LUnaryOperator<T> shoving() {

		return new LUnaryOperator<T>() {

			@Nullable
			public T apply(T a) {
				try {
					return this.applyX(a);
				} catch (Throwable e) { // NOSONAR
					Handling.handleErrors(e);
					throw Handling.throwIt(e);
				}
			}

			public T applyX(T a) throws Throwable {
				return LUnaryOperator.this.applyX(a);
			}

		};
	}

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LUnaryOperator<T> nonNullable() {
		return this::nonNullApply;
	}

	/** Does nothing (LUnaryOperator) Operator */
	public static <T> T doNothing(T a) {
		return (T) Function4U.defaultObject;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C0> void forEach(IndexedRead<C0, a<T>> ia, C0 source, LConsumer<? super T> consumer) {
		int size = ia.size(source);
		var oiFunc0 = IA.getter(ia);
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
	default <C0, I0> void iterate(SequentialRead<C0, I0, a<T>> sa, C0 source, LConsumer<? super T> consumer) {
		var iterator0 = SA.adapter(sa).apply(source);
		var testFunc0 = SA.tester(sa);
		var nextFunc0 = SA.supplier(sa);
		while (testFunc0.test(iterator0)) {
			T a = nextFunc0.apply(iterator0);
			consumer.accept(this.apply(a));
		}
	}

}
