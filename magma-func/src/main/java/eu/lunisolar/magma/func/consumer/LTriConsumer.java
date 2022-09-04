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
 * Non-throwing functional interface (lambda) LTriConsumer for Java 8.
 *
 * Type: consumer
 *
 * Domain (lvl: 3): T1 a1,T2 a2,T3 a3
 *
 * Co-domain: none
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LTriConsumer<T1, T2, T3> extends MetaConsumer, MetaInterface.NonThrowing, Codomain<aVoid>, Domain3<a<T1>, a<T2>, a<T3>> {

	String DESCRIPTION = "LTriConsumer: void accept(T1 a1,T2 a2,T3 a3)";

	// void accept(T1 a1,T2 a2,T3 a3) ;
	default void accept(T1 a1, T2 a2, T3 a3) {
		// nestingAccept(a1,a2,a3);
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call accept(T1 a1,T2 a2,T3 a3)
	 */
	void acceptX(T1 a1, T2 a2, T3 a3) throws Throwable;

	default LTuple.Void tupleAccept(LTriple<T1, T2, T3> args) {
		accept(args.first(), args.second(), args.third());
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions according to the instructions. */
	default void handlingAccept(T1 a1, T2 a2, T3 a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LTriConsumer<T1, T2, T3> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> handlingAccept(a1, a2, a3, handling);
	}

	default void accept(T1 a1, T2 a2, T3 a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default void accept(T1 a1, T2 a2, T3 a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default void accept(T1 a1, T2 a2, T3 a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default void accept(T1 a1, T2 a2, T3 a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LTriConsumer<T1, T2, T3> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2, a3) -> accept(a1, a2, a3, factory, newMessage);
	}

	default LTriConsumer<T1, T2, T3> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2, a3) -> accept(a1, a2, a3, factory, newMessage, param1);
	}

	default LTriConsumer<T1, T2, T3> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2, a3) -> accept(a1, a2, a3, factory, newMessage, param1, param1);
	}

	default LTriConsumer<T1, T2, T3> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2, a3) -> accept(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	default void accept(T1 a1, T2 a2, T3 a3, @Nonnull ExWF<RuntimeException> factory) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LTriConsumer<T1, T2, T3> trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2, a3) -> accept(a1, a2, a3, factory);
	}

	default void acceptThen(T1 a1, T2 a2, T3 a3, @Nonnull LConsumer<Throwable> handler) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			handler.accept(e);
		}
	}

	default LTriConsumer<T1, T2, T3> tryingThen(@Nonnull LConsumer<Throwable> handler) {
		return (a1, a2, a3) -> acceptThen(a1, a2, a3, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingAccept(T1 a1, T2 a2, T3 a3) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default void shovingAccept(T1 a1, T2 a2, T3 a3) {
		try {
			this.acceptX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T1, T2, T3> void shovingAccept(T1 a1, T2 a2, T3 a3, LTriConsumer<T1, T2, T3> func) {
		Null.nonNullArg(func, "func");
		func.shovingAccept(a1, a2, a3);
	}

	static <T1, T2, T3> void handlingAccept(T1 a1, T2 a2, T3 a3, LTriConsumer<T1, T2, T3> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		func.handlingAccept(a1, a2, a3, handling);
	}

	static <T1, T2, T3> void tryAccept(T1 a1, T2 a2, T3 a3, LTriConsumer<T1, T2, T3> func) {
		Null.nonNullArg(func, "func");
		func.nestingAccept(a1, a2, a3);
	}

	static <T1, T2, T3> void tryAccept(T1 a1, T2 a2, T3 a3, LTriConsumer<T1, T2, T3> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, factory, newMessage);
	}

	static <T1, T2, T3> void tryAccept(T1 a1, T2 a2, T3 a3, LTriConsumer<T1, T2, T3> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, factory, newMessage, param1);
	}

	static <T1, T2, T3> void tryAccept(T1 a1, T2 a2, T3 a3, LTriConsumer<T1, T2, T3> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, factory, newMessage, param1, param2);
	}

	static <T1, T2, T3> void tryAccept(T1 a1, T2 a2, T3 a3, LTriConsumer<T1, T2, T3> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	static <T1, T2, T3> void tryAccept(T1 a1, T2 a2, T3 a3, LTriConsumer<T1, T2, T3> func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		func.accept(a1, a2, a3, factory);
	}

	static <T1, T2, T3> void tryAcceptThen(T1 a1, T2 a2, T3 a3, LTriConsumer<T1, T2, T3> func, @Nonnull LConsumer<Throwable> handler) {
		Null.nonNullArg(func, "func");
		func.acceptThen(a1, a2, a3, handler);
	}

	default void failSafeAccept(T1 a1, T2 a2, T3 a3, @Nonnull LTriConsumer<T1, T2, T3> failSafe) {
		try {
			accept(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			failSafe.accept(a1, a2, a3);
		}
	}

	static <T1, T2, T3> void failSafeAccept(T1 a1, T2 a2, T3 a3, LTriConsumer<T1, T2, T3> func, @Nonnull LTriConsumer<T1, T2, T3> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			failSafe.accept(a1, a2, a3);
		} else {
			func.failSafeAccept(a1, a2, a3, failSafe);
		}
	}

	static <T1, T2, T3> LTriConsumer<T1, T2, T3> failSafe(LTriConsumer<T1, T2, T3> func, @Nonnull LTriConsumer<T1, T2, T3> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeAccept(a1, a2, a3, func, failSafe);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LTriConsumer.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, T3> void fromTo(int min_i, int max_i, T1 a1, T2 a2, T3 a3, @Nonnull LTriConsumer<T1, T2, T3> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.accept(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.accept(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, T3> void fromTill(int min_i, int max_i, T1 a1, T2 a2, T3 a3, @Nonnull LTriConsumer<T1, T2, T3> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.accept(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.accept(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, T3> void times(int max_i, T1 a1, T2 a2, T3 a3, @Nonnull LTriConsumer<T1, T2, T3> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, a3, func);
	}

	/**  */
	public static <T1, T2, T3> LTriConsumer<T1, T2, T3> uncurry(@Nonnull LFunction<T1, LFunction<T2, LConsumer<T3>>> func) {
		Null.nonNullArg(func, "func");
		return (T1 a1, T2 a2, T3 a3) -> func.apply(a1).apply(a2).accept(a3);
	}

	/** Change function to one with codomain (always returning same value provided in argument). */
	default <T> LTriFunction<T1, T2, T3, T> returning(T value) {
		return (a1, a2, a3) -> {
			LTriConsumer.this.accept(a1, a2, a3);
			return value;
		};
	}

	/** Calls domain consumer before main function. */
	default LTriConsumer<T1, T2, T3> beforeDo(@Nonnull LTriConsumer<T1, T2, T3> before) {
		Null.nonNullArg(before, "before");
		return (T1 a1, T2 a2, T3 a3) -> {
			before.accept(a1, a2, a3);
			accept(a1, a2, a3);
		};
	}

	// <editor-fold desc="CallContext">

	static <T1, T2, T3> void nestingAccept(@Nullable CallContext c1, T1 a1, T2 a2, T3 a3, @Nonnull LTriConsumer<T1, T2, T3> function) {
		Null.nonNullArg(function, "function");
		try {
			acceptX(c1, a1, a2, a3, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static <T1, T2, T3> void shovingAccept(@Nullable CallContext c1, T1 a1, T2 a2, T3 a3, @Nonnull LTriConsumer<T1, T2, T3> function) {
		Null.nonNullArg(function, "function");
		try {
			acceptX(c1, a1, a2, a3, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static <T1, T2, T3> void acceptX(@Nullable CallContext c1, T1 a1, T2 a2, T3 a3, @Nonnull LTriConsumer<T1, T2, T3> function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContext.tryInit(last, c1);

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;

		if (primary == null) {
			try {
				function.shovingAccept(a1, a2, a3);
			} catch (Throwable e) {
				primary = e;
			}
		}

		primary = CallContext.tryFinish(primary, c1, s1);

		if (primary != null) {
			throw Handling.throwIt(primary);
		}

	}

	static <T1, T2, T3> CompletableFuture<Void> asyncAccept(@Nonnull AsyncCallContext async, @Nullable CallContext c1, T1 a1, T2 a2, T3 a3, @Nonnull LTriConsumer<T1, T2, T3> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<Void> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					LTriConsumer.acceptX(c1, a1, a2, a3, function);
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

	static <T1, T2, T3> void nestingAccept(@Nullable CallContext c1, @Nullable CallContext c2, T1 a1, T2 a2, T3 a3, @Nonnull LTriConsumer<T1, T2, T3> function) {
		Null.nonNullArg(function, "function");
		try {
			acceptX(c1, c2, a1, a2, a3, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static <T1, T2, T3> void shovingAccept(@Nullable CallContext c1, @Nullable CallContext c2, T1 a1, T2 a2, T3 a3, @Nonnull LTriConsumer<T1, T2, T3> function) {
		Null.nonNullArg(function, "function");
		try {
			acceptX(c1, c2, a1, a2, a3, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static <T1, T2, T3> void acceptX(@Nullable CallContext c1, @Nullable CallContext c2, T1 a1, T2 a2, T3 a3, @Nonnull LTriConsumer<T1, T2, T3> function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContext.tryInit(last, c1);
		final Object s2 = last = CallContext.tryInit(last, c2);

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;

		if (primary == null) {
			try {
				function.shovingAccept(a1, a2, a3);
			} catch (Throwable e) {
				primary = e;
			}
		}

		primary = CallContext.tryFinish(primary, c2, s2);
		primary = CallContext.tryFinish(primary, c1, s1);

		if (primary != null) {
			throw Handling.throwIt(primary);
		}

	}

	static <T1, T2, T3> CompletableFuture<Void> asyncAccept(@Nonnull AsyncCallContext async, @Nullable CallContext c1, @Nullable CallContext c2, T1 a1, T2 a2, T3 a3, @Nonnull LTriConsumer<T1, T2, T3> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<Void> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					LTriConsumer.acceptX(c1, c2, a1, a2, a3, function);
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

	static <T1, T2, T3> void nestingAccept(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, T1 a1, T2 a2, T3 a3, @Nonnull LTriConsumer<T1, T2, T3> function) {
		Null.nonNullArg(function, "function");
		try {
			acceptX(c1, c2, c3, a1, a2, a3, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static <T1, T2, T3> void shovingAccept(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, T1 a1, T2 a2, T3 a3, @Nonnull LTriConsumer<T1, T2, T3> function) {
		Null.nonNullArg(function, "function");
		try {
			acceptX(c1, c2, c3, a1, a2, a3, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static <T1, T2, T3> void acceptX(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, T1 a1, T2 a2, T3 a3, @Nonnull LTriConsumer<T1, T2, T3> function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContext.tryInit(last, c1);
		final Object s2 = last = CallContext.tryInit(last, c2);
		final Object s3 = last = CallContext.tryInit(last, c3);

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;

		if (primary == null) {
			try {
				function.shovingAccept(a1, a2, a3);
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

	}

	static <T1, T2, T3> CompletableFuture<Void> asyncAccept(@Nonnull AsyncCallContext async, @Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, T1 a1, T2 a2, T3 a3, @Nonnull LTriConsumer<T1, T2, T3> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<Void> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					LTriConsumer.acceptX(c1, c2, c3, a1, a2, a3, function);
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

	static <T1, T2, T3> void nestingAccept(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, T1 a1, T2 a2, T3 a3, @Nonnull LTriConsumer<T1, T2, T3> function) {
		Null.nonNullArg(function, "function");
		try {
			acceptX(c1, c2, c3, c4, a1, a2, a3, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static <T1, T2, T3> void shovingAccept(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, T1 a1, T2 a2, T3 a3, @Nonnull LTriConsumer<T1, T2, T3> function) {
		Null.nonNullArg(function, "function");
		try {
			acceptX(c1, c2, c3, c4, a1, a2, a3, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static <T1, T2, T3> void acceptX(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, T1 a1, T2 a2, T3 a3, @Nonnull LTriConsumer<T1, T2, T3> function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContext.tryInit(last, c1);
		final Object s2 = last = CallContext.tryInit(last, c2);
		final Object s3 = last = CallContext.tryInit(last, c3);
		final Object s4 = last = CallContext.tryInit(last, c4);

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;

		if (primary == null) {
			try {
				function.shovingAccept(a1, a2, a3);
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

	}

	static <T1, T2, T3> CompletableFuture<Void> asyncAccept(@Nonnull AsyncCallContext async, @Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, T1 a1, T2 a2, T3 a3,
			@Nonnull LTriConsumer<T1, T2, T3> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<Void> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					LTriConsumer.acceptX(c1, c2, c3, c4, a1, a2, a3, function);
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

	static <T1, T2, T3> CompletableFuture<Void> asyncAccept(@Nonnull AsyncCallContext async, T1 a1, T2 a2, T3 a3, @Nonnull LTriConsumer<T1, T2, T3> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<Void> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					function.acceptX(a1, a2, a3);
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

	// </editor-fold>

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, T3> LTriConsumer<T1, T2, T3> triCons(final @Nonnull LTriConsumer<T1, T2, T3> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** A completely inconvenient method in case lambda expression and generic arguments are ambiguous for the compiler. */
	@Nonnull
	static <T1, T2, T3> LTriConsumer<T1, T2, T3> triCons(@Nullable Class<T1> c1, @Nullable Class<T2> c2, @Nullable Class<T3> c3, final @Nonnull LTriConsumer<T1, T2, T3> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S<T1, T2, T3> implements LTriConsumer<T1, T2, T3> {
		private LTriConsumer<T1, T2, T3> target = null;
		@Override
		public void acceptX(T1 a1, T2 a2, T3 a3) throws Throwable {
			target.acceptX(a1, a2, a3);
		}
	}

	@Nonnull
	static <T1, T2, T3> LTriConsumer<T1, T2, T3> recursive(final @Nonnull LFunction<LTriConsumer<T1, T2, T3>, LTriConsumer<T1, T2, T3>> selfLambda) {
		final S<T1, T2, T3> single = new S();
		LTriConsumer<T1, T2, T3> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	@Nonnull
	static <T1, T2, T3> LTriConsumer<T1, T2, T3> triConsThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T1, T2, T3> LTriConsumer<T1, T2, T3> triConsThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce(message);
		};
	}

	static <T1, T2, T3> void call(T1 a1, T2 a2, T3 a3, final @Nonnull LTriConsumer<T1, T2, T3> lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.accept(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriConsumer<V1, V2, V3> compose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LFunction<? super V3, ? extends T3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.accept(before1.apply(v1), before2.apply(v2), before3.apply(v3));
	}

	public static <V1, V2, V3, T1, T2, T3> LTriConsumer<V1, V2, V3> composed(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2,
			@Nonnull final LFunction<? super V3, ? extends T3> before3, LTriConsumer<T1, T2, T3> after) {
		return after.compose(before1, before2, before3);
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two LTriConsumer<T1,T2,T3> together in a order. */
	@Nonnull
	default LTriConsumer<T1, T2, T3> andThen(@Nonnull LTriConsumer<? super T1, ? super T2, ? super T3> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> {
			this.accept(a1, a2, a3);
			after.accept(a1, a2, a3);
		};
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LTriConsumer) */
	public static <T1, T2, T3> void doNothing(T1 a1, T2 a2, T3 a3) {
		// NOSONAR
	}

	// <editor-fold desc="fluentUse">

	public static <T1, T2, T3, R> R inlineAcceptR(R retval, T1 a1, T2 a2, T3 a3, LTriConsumer<T1, T2, T3> consumer) {
		consumer.accept(a1, a2, a3);
		return retval;
	}

	public static <T1, T2, T3> T1 inlineAccept(T1 a1, T2 a2, T3 a3, LTriConsumer<T1, T2, T3> consumer) {
		consumer.accept(a1, a2, a3);
		return a1;
	}

	// </editor-fold>

}
