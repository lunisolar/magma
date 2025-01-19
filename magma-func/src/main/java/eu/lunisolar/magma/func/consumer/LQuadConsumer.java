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

package eu.lunisolar.magma.func.consumer;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import javax.annotation.concurrent.NotThreadSafe; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.*; //NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; // NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.aType.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.IA;
import eu.lunisolar.magma.func.SA;
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
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
 * Non-throwing functional interface (lambda) LQuadConsumer for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 4): T1 a1,T2 a2,T3 a3,T4 a4
 *
 * Co-domain: none
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LQuadConsumer<T1, T2, T3, T4> extends MetaConsumer, MetaInterface.NonThrowing, Codomain<aVoid>, Domain4<a<T1>, a<T2>, a<T3>, a<T4>> {

	String DESCRIPTION = "LQuadConsumer: void accept(T1 a1,T2 a2,T3 a3,T4 a4)";

	default void accept(T1 a1, T2 a2, T3 a3, T4 a4) {
		try {
			this.acceptX(a1, a2, a3, a4);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call accept(T1 a1,T2 a2,T3 a3,T4 a4)
	 */
	void acceptX(T1 a1, T2 a2, T3 a3, T4 a4) throws Throwable;

	default LTuple.Void tupleAccept(LQuad<T1, T2, T3, T4> args) {
		accept(args.first(), args.second(), args.third(), args.fourth());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions according to the instructions. */
	default void handlingAccept(T1 a1, T2 a2, T3 a3, T4 a4, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			this.acceptX(a1, a2, a3, a4);
		} catch (Throwable e) { //NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LQuadConsumer<T1, T2, T3, T4> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3, a4) -> handlingAccept(a1, a2, a3, a4, handling);
	}

	default void accept(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			this.acceptX(a1, a2, a3, a4);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default void accept(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			this.acceptX(a1, a2, a3, a4);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default void accept(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			this.acceptX(a1, a2, a3, a4);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default void accept(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			this.acceptX(a1, a2, a3, a4);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LQuadConsumer<T1, T2, T3, T4> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2, a3, a4) -> accept(a1, a2, a3, a4, factory, newMessage);
	}

	default LQuadConsumer<T1, T2, T3, T4> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2, a3, a4) -> accept(a1, a2, a3, a4, factory, newMessage, param1);
	}

	default LQuadConsumer<T1, T2, T3, T4> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2, a3, a4) -> accept(a1, a2, a3, a4, factory, newMessage, param1, param1);
	}

	default LQuadConsumer<T1, T2, T3, T4> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2, a3, a4) -> accept(a1, a2, a3, a4, factory, newMessage, param1, param2, param3);
	}

	default void accept(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull ExWF<RuntimeException> factory) {
		try {
			this.acceptX(a1, a2, a3, a4);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LQuadConsumer<T1, T2, T3, T4> trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2, a3, a4) -> accept(a1, a2, a3, a4, factory);
	}

	default void acceptThen(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LConsumer<Throwable> handler) {
		try {
			this.acceptX(a1, a2, a3, a4);
		} catch (Throwable e) { //NOSONAR
			Handling.handleErrors(e);
			handler.accept(e);
		}
	}

	default LQuadConsumer<T1, T2, T3, T4> tryingThen(@Nonnull LConsumer<Throwable> handler) {
		return (a1, a2, a3, a4) -> acceptThen(a1, a2, a3, a4, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingAccept(T1 a1, T2 a2, T3 a3, T4 a4) {
		try {
			this.acceptX(a1, a2, a3, a4);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default void shovingAccept(T1 a1, T2 a2, T3 a3, T4 a4) {
		try {
			this.acceptX(a1, a2, a3, a4);
		} catch (Throwable e) { //NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T1, T2, T3, T4> void shovingAccept(T1 a1, T2 a2, T3 a3, T4 a4, LQuadConsumer<T1, T2, T3, T4> func) {
		Null.nonNullArg(func, "func");
		func.shovingAccept(a1, a2, a3, a4);
	}

	static <T1, T2, T3, T4> void handlingAccept(T1 a1, T2 a2, T3 a3, T4 a4, LQuadConsumer<T1, T2, T3, T4> func, HandlingInstructions<Throwable, RuntimeException> handling) { //<-
		Null.nonNullArg(func, "func");
		func.handlingAccept(a1, a2, a3, a4, handling);
	}

	static <T1, T2, T3, T4> void tryAccept(T1 a1, T2 a2, T3 a3, T4 a4, LQuadConsumer<T1, T2, T3, T4> func) {
		Null.nonNullArg(func, "func");
		func.nestingAccept(a1, a2, a3, a4);
	}

	static <T1, T2, T3, T4> void tryAccept(T1 a1, T2 a2, T3 a3, T4 a4, LQuadConsumer<T1, T2, T3, T4> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, a4, factory, newMessage);
	}

	static <T1, T2, T3, T4> void tryAccept(T1 a1, T2 a2, T3 a3, T4 a4, LQuadConsumer<T1, T2, T3, T4> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, a4, factory, newMessage, param1);
	}

	static <T1, T2, T3, T4> void tryAccept(T1 a1, T2 a2, T3 a3, T4 a4, LQuadConsumer<T1, T2, T3, T4> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, a4, factory, newMessage, param1, param2);
	}

	static <T1, T2, T3, T4> void tryAccept(T1 a1, T2 a2, T3 a3, T4 a4, LQuadConsumer<T1, T2, T3, T4> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, a4, factory, newMessage, param1, param2, param3);
	}

	static <T1, T2, T3, T4> void tryAccept(T1 a1, T2 a2, T3 a3, T4 a4, LQuadConsumer<T1, T2, T3, T4> func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, a4, factory);
	}

	static <T1, T2, T3, T4> void tryAcceptThen(T1 a1, T2 a2, T3 a3, T4 a4, LQuadConsumer<T1, T2, T3, T4> func, @Nonnull LConsumer<Throwable> handler) {
		Null.nonNullArg(func, "func");
		func.acceptThen(a1, a2, a3, a4, handler);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LQuadConsumer.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, T3, T4> void fromTo(int min_i, int max_i, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadConsumer<T1, T2, T3, T4> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.accept(a1, a2, a3, a4);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.accept(a1, a2, a3, a4);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, T3, T4> void fromTill(int min_i, int max_i, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadConsumer<T1, T2, T3, T4> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.accept(a1, a2, a3, a4);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.accept(a1, a2, a3, a4);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, T3, T4> void times(int max_i, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadConsumer<T1, T2, T3, T4> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, a3, a4, func);
	}

	default LTriConsumer<T2, T3, T4> _with(T1 a1) {
		return (a2, a3, a4) -> accept(a1, a2, a3, a4);
	}

	default LTriConsumer<T1, T2, T3> with(T4 a4) {
		return (a1, a2, a3) -> accept(a1, a2, a3, a4);
	}

	default LBiConsumer<T3, T4> _with(T1 a1, T2 a2) {
		return (a3, a4) -> accept(a1, a2, a3, a4);
	}

	default LBiConsumer<T1, T2> with(T3 a3, T4 a4) {
		return (a1, a2) -> accept(a1, a2, a3, a4);
	}

	default LConsumer<T4> _with(T1 a1, T2 a2, T3 a3) {
		return a4 -> accept(a1, a2, a3, a4);
	}

	default LConsumer<T1> with(T2 a2, T3 a3, T4 a4) {
		return a1 -> accept(a1, a2, a3, a4);
	}

	/**  */
	public static <T1, T2, T3, T4> LQuadConsumer<T1, T2, T3, T4> uncurry(@Nonnull LFunction<T1, LFunction<T2, LFunction<T3, LConsumer<T4>>>> func) {
		Null.nonNullArg(func, "func");
		return (T1 a1, T2 a2, T3 a3, T4 a4) -> func.apply(a1).apply(a2).apply(a3).accept(a4);
	}

	/** Change function to one with codomain (always returning same value provided in argument). */
	default <T> LQuadFunction<T1, T2, T3, T4, T> returning(T value) {
		return (a1, a2, a3, a4) -> {
			LQuadConsumer.this.accept(a1, a2, a3, a4);
			return value;
		};
	}

	/** Calls domain consumer before main function. */
	default LQuadConsumer<T1, T2, T3, T4> beforeDo(@Nonnull LQuadConsumer<T1, T2, T3, T4> before) {
		Null.nonNullArg(before, "before");
		return (T1 a1, T2 a2, T3 a3, T4 a4) -> {
			before.accept(a1, a2, a3, a4);
			accept(a1, a2, a3, a4);
		};
	}

	//<editor-fold desc="CallContext">

	@Nonnull
	static <T1, T2, T3, T4> LQuadConsumer<T1, T2, T3, T4> quadCons(@Nullable CallContext c1, final @Nonnull LQuadConsumer<T1, T2, T3, T4> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.withCC(c1);
	}

	default @Nonnull LQuadConsumer<T1, T2, T3, T4> withCC(@Nullable CallContext c1) {
		return (a1, a2, a3, a4) -> LQuadConsumer.shovingAccept(c1, a1, a2, a3, a4, this);
	}

	static <T1, T2, T3, T4> void nestingAccept(@Nullable CallContext c1, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadConsumer<T1, T2, T3, T4> function) {
		Null.nonNullArg(function, "function");
		try {
			acceptX(c1, a1, a2, a3, a4, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static <T1, T2, T3, T4> void shovingAccept(@Nullable CallContext c1, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadConsumer<T1, T2, T3, T4> function) {
		Null.nonNullArg(function, "function");
		try {
			acceptX(c1, a1, a2, a3, a4, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static <T1, T2, T3, T4> void acceptX(@Nullable CallContext c1, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadConsumer<T1, T2, T3, T4> function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContexts.tryInit(last, c1); // try { c1?.start() ...

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;

		if (primary == null) {
			try {
				function.shovingAccept(a1, a2, a3, a4);
			} catch (Throwable e) {
				primary = e;
			}
		}

		primary = CallContexts.tryFinish(primary, c1, s1); // } finally { c1?.end(...) }

		if (primary != null) {
			throw Handling.throwIt(primary);
		}

	}

	static <T1, T2, T3, T4> CompletableFuture<Void> asyncAccept(@Nonnull AsyncCallContext async, @Nullable CallContext c1, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadConsumer<T1, T2, T3, T4> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<Void> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					LQuadConsumer.acceptX(c1, a1, a2, a3, a4, function);
					future.complete(null);
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

	static <T1, T2, T3, T4> void nestingAccept(@Nullable CallContext c1, @Nullable CallContext c2, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadConsumer<T1, T2, T3, T4> function) {
		Null.nonNullArg(function, "function");
		try {
			acceptX(c1, c2, a1, a2, a3, a4, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static <T1, T2, T3, T4> void shovingAccept(@Nullable CallContext c1, @Nullable CallContext c2, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadConsumer<T1, T2, T3, T4> function) {
		Null.nonNullArg(function, "function");
		try {
			acceptX(c1, c2, a1, a2, a3, a4, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static <T1, T2, T3, T4> void acceptX(@Nullable CallContext c1, @Nullable CallContext c2, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadConsumer<T1, T2, T3, T4> function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContexts.tryInit(last, c1); // try { c1?.start() ...
		final Object s2 = last = CallContexts.tryInit(last, c2); // try { c2?.start() ...

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;

		if (primary == null) {
			try {
				function.shovingAccept(a1, a2, a3, a4);
			} catch (Throwable e) {
				primary = e;
			}
		}

		primary = CallContexts.tryFinish(primary, c2, s2); // } finally { c2?.end(...) }
		primary = CallContexts.tryFinish(primary, c1, s1); // } finally { c1?.end(...) }

		if (primary != null) {
			throw Handling.throwIt(primary);
		}

	}

	static <T1, T2, T3, T4> CompletableFuture<Void> asyncAccept(@Nonnull AsyncCallContext async, @Nullable CallContext c1, @Nullable CallContext c2, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadConsumer<T1, T2, T3, T4> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<Void> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					LQuadConsumer.acceptX(c1, c2, a1, a2, a3, a4, function);
					future.complete(null);
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

	static <T1, T2, T3, T4> void nestingAccept(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadConsumer<T1, T2, T3, T4> function) {
		Null.nonNullArg(function, "function");
		try {
			acceptX(c1, c2, c3, a1, a2, a3, a4, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static <T1, T2, T3, T4> void shovingAccept(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadConsumer<T1, T2, T3, T4> function) {
		Null.nonNullArg(function, "function");
		try {
			acceptX(c1, c2, c3, a1, a2, a3, a4, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static <T1, T2, T3, T4> void acceptX(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadConsumer<T1, T2, T3, T4> function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContexts.tryInit(last, c1); // try { c1?.start() ...
		final Object s2 = last = CallContexts.tryInit(last, c2); // try { c2?.start() ...
		final Object s3 = last = CallContexts.tryInit(last, c3); // try { c3?.start() ...

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;

		if (primary == null) {
			try {
				function.shovingAccept(a1, a2, a3, a4);
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

	}

	static <T1, T2, T3, T4> CompletableFuture<Void> asyncAccept(@Nonnull AsyncCallContext async, @Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadConsumer<T1, T2, T3, T4> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<Void> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					LQuadConsumer.acceptX(c1, c2, c3, a1, a2, a3, a4, function);
					future.complete(null);
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

	static <T1, T2, T3, T4> void nestingAccept(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadConsumer<T1, T2, T3, T4> function) {
		Null.nonNullArg(function, "function");
		try {
			acceptX(c1, c2, c3, c4, a1, a2, a3, a4, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static <T1, T2, T3, T4> void shovingAccept(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadConsumer<T1, T2, T3, T4> function) {
		Null.nonNullArg(function, "function");
		try {
			acceptX(c1, c2, c3, c4, a1, a2, a3, a4, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static <T1, T2, T3, T4> void acceptX(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadConsumer<T1, T2, T3, T4> function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContexts.tryInit(last, c1); // try { c1?.start() ...
		final Object s2 = last = CallContexts.tryInit(last, c2); // try { c2?.start() ...
		final Object s3 = last = CallContexts.tryInit(last, c3); // try { c3?.start() ...
		final Object s4 = last = CallContexts.tryInit(last, c4); // try { c4?.start() ...

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;

		if (primary == null) {
			try {
				function.shovingAccept(a1, a2, a3, a4);
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

	}

	static <T1, T2, T3, T4> CompletableFuture<Void> asyncAccept(@Nonnull AsyncCallContext async, @Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, T1 a1, T2 a2, T3 a3, T4 a4,
			@Nonnull LQuadConsumer<T1, T2, T3, T4> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<Void> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					LQuadConsumer.acceptX(c1, c2, c3, c4, a1, a2, a3, a4, function);
					future.complete(null);
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

	static <T1, T2, T3, T4> CompletableFuture<Void> asyncAccept(@Nonnull AsyncCallContext async, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadConsumer<T1, T2, T3, T4> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<Void> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					function.acceptX(a1, a2, a3, a4);
					future.complete(null);
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

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, T3, T4> LQuadConsumer<T1, T2, T3, T4> quadCons(final @Nonnull LQuadConsumer<T1, T2, T3, T4> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T1, T2, T3, T4> LQuadConsumer<T1, T2, T3, T4> quadConsThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3, a4) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T1, T2, T3, T4> LQuadConsumer<T1, T2, T3, T4> quadConsThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3, a4) -> {
			throw exF.produce(message);
		};
	}

	static <T1, T2, T3, T4> void call(T1 a1, T2 a2, T3 a3, T4 a4, final @Nonnull LQuadConsumer<T1, T2, T3, T4> lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.accept(a1, a2, a3, a4);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3, V4> LQuadConsumer<V1, V2, V3, V4> compose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LFunction<? super V3, ? extends T3> before3,
			@Nonnull final LFunction<? super V4, ? extends T4> before4) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		Null.nonNullArg(before4, "before4");
		return (v1, v2, v3, v4) -> this.accept(before1.apply(v1), before2.apply(v2), before3.apply(v3), before4.apply(v4));
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two LQuadConsumer<T1,T2,T3,T4> together in a order. */
	@Nonnull
	default LQuadConsumer<T1, T2, T3, T4> andThen(@Nonnull LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3, a4) -> {
			this.accept(a1, a2, a3, a4);
			after.accept(a1, a2, a3, a4);
		};
	}

	// </editor-fold>

	default LQuadConsumer<T1, T2, T3, T4> shoving() {

		return new LQuadConsumer<T1, T2, T3, T4>() {

			public void accept(T1 a1, T2 a2, T3 a3, T4 a4) {
				try {
					this.acceptX(a1, a2, a3, a4);
				} catch (Throwable e) { // NOSONAR
					Handling.handleErrors(e);
					throw Handling.throwIt(e);
				}
			}

			public void acceptX(T1 a1, T2 a2, T3 a3, T4 a4) throws Throwable {
				LQuadConsumer.this.acceptX(a1, a2, a3, a4);
			}

		};
	}

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LQuadConsumer) */
	public static <T1, T2, T3, T4> void doNothing(T1 a1, T2 a2, T3 a3, T4 a4) {
		// NOSONAR
	}

	// <editor-fold desc="fluentUse">

	public static <T1, T2, T3, T4, R> R inlineAcceptR(R retval, T1 a1, T2 a2, T3 a3, T4 a4, LQuadConsumer<T1, T2, T3, T4> consumer) {
		consumer.accept(a1, a2, a3, a4);
		return retval;
	}

	public static <T1, T2, T3, T4> T1 inlineAccept(T1 a1, T2 a2, T3 a3, T4 a4, LQuadConsumer<T1, T2, T3, T4> consumer) {
		consumer.accept(a1, a2, a3, a4);
		return a1;
	}

	// </editor-fold>

}
