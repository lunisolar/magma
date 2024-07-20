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

package eu.lunisolar.magma.func.action;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import javax.annotation.concurrent.NotThreadSafe; // NOSONAR
import java.util.Objects;// NOSONAR
import java.util.function.Predicate; //NOSONAR
import eu.lunisolar.magma.basics.*; //NOSONAR
import eu.lunisolar.magma.basics.exceptions.*; // NOSONAR
import eu.lunisolar.magma.func.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.aType.*;
import eu.lunisolar.magma.basics.meta.functional.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.type.*; // NOSONAR
import eu.lunisolar.magma.basics.meta.functional.domain.*; // NOSONAR
import eu.lunisolar.magma.func.IA;
import eu.lunisolar.magma.func.SA;
import eu.lunisolar.magma.func.consumer.*; // NOSONAR
import eu.lunisolar.magma.func.function.*; // NOSONAR
import eu.lunisolar.magma.func.supplier.*; // NOSONAR
import eu.lunisolar.magma.func.tuple.*; // NOSONAR
import java.util.concurrent.*; // NOSONAR
import java.util.function.*; // NOSONAR
import java.util.*; // NOSONAR
import java.lang.reflect.*; // NOSONAR

/**
 * LAction is a replacement for Runnable.
 *
 * - offers default methods
 * - do not rise warnings about Runnable being call directly
 * - two versions (throwing and non-throwing) have conversion methods that mirrors each other)
 *
 * Non-throwing functional interface (lambda) LAction for Java 8.
 *
 * Type: action
 *
 * Domain (lvl: 0): none
 *
 * Co-domain: none
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LAction extends Runnable, MetaAction, MetaInterface.NonThrowing, Codomain<aVoid>, Domain0 {

	String DESCRIPTION = "LAction: void execute()";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * Calling this method via LAction interface should be discouraged.
	 */
	@Override
	default void run() {
		this.execute();
	}

	// void execute() ;
	default void execute() {
		try {
			this.executeX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call execute()
	 */
	void executeX() throws Throwable;

	default LTuple.Void tupleExecute(LTuple.Void args) {
		execute();
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions according to the instructions. */
	default void handlingExecute(HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			this.executeX();
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LAction handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return () -> handlingExecute(handling);
	}

	default void execute(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			this.executeX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default void execute(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			this.executeX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default void execute(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			this.executeX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default void execute(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			this.executeX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LAction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return () -> execute(factory, newMessage);
	}

	default LAction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return () -> execute(factory, newMessage, param1);
	}

	default LAction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return () -> execute(factory, newMessage, param1, param1);
	}

	default LAction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return () -> execute(factory, newMessage, param1, param2, param3);
	}

	default void execute(@Nonnull ExWF<RuntimeException> factory) {
		try {
			this.executeX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LAction trying(@Nonnull ExWF<RuntimeException> factory) {
		return () -> execute(factory);
	}

	default void executeThen(@Nonnull LConsumer<Throwable> handler) {
		try {
			this.executeX();
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			handler.accept(e);
		}
	}

	default LAction tryingThen(@Nonnull LConsumer<Throwable> handler) {
		return () -> executeThen(handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingExecute() {
		try {
			this.executeX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default void shovingExecute() {
		try {
			this.executeX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static void shovingExecute(LAction func) {
		Null.nonNullArg(func, "func");
		func.shovingExecute();
	}

	static void handlingExecute(LAction func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		func.handlingExecute(handling);
	}

	static void tryExecute(LAction func) {
		Null.nonNullArg(func, "func");
		func.nestingExecute();
	}

	static void tryExecute(LAction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		func.execute(factory, newMessage);
	}

	static void tryExecute(LAction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		func.execute(factory, newMessage, param1);
	}

	static void tryExecute(LAction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		func.execute(factory, newMessage, param1, param2);
	}

	static void tryExecute(LAction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		func.execute(factory, newMessage, param1, param2, param3);
	}

	static void tryExecute(LAction func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		func.execute(factory);
	}

	static void tryExecuteThen(LAction func, @Nonnull LConsumer<Throwable> handler) {
		Null.nonNullArg(func, "func");
		func.executeThen(handler);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LAction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, @Nonnull LAction func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.execute();
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.execute();
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, @Nonnull LAction func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.execute();
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.execute();
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, @Nonnull LAction func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, func);
	}

	/** Change function to one with codomain (always returning same value provided in argument). */
	default <T> LSupplier<T> returning(T value) {
		return () -> {
			LAction.this.execute();
			return value;
		};
	}

	// <editor-fold desc="CallContext">

	@Nonnull
	static LAction act(@Nullable CallContext c1, final @Nonnull LAction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.withCC(c1);
	}

	default @Nonnull LAction withCC(@Nullable CallContext c1) {
		return () -> LAction.shovingExecute(c1, this);
	}

	static void nestingExecute(@Nullable CallContext c1, @Nonnull LAction function) {
		Null.nonNullArg(function, "function");
		try {
			executeX(c1, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static void shovingExecute(@Nullable CallContext c1, @Nonnull LAction function) {
		Null.nonNullArg(function, "function");
		try {
			executeX(c1, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static void executeX(@Nullable CallContext c1, @Nonnull LAction function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContexts.tryInit(last, c1); // try { c1?.start() ...

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;

		if (primary == null) {
			try {
				function.shovingExecute();
			} catch (Throwable e) {
				primary = e;
			}
		}

		primary = CallContexts.tryFinish(primary, c1, s1); // } finally { c1?.end(...) }

		if (primary != null) {
			throw Handling.throwIt(primary);
		}

	}

	static CompletableFuture<Void> asyncExecute(@Nonnull AsyncCallContext async, @Nullable CallContext c1, @Nonnull LAction function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<Void> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					LAction.executeX(c1, function);
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

	static void nestingExecute(@Nullable CallContext c1, @Nullable CallContext c2, @Nonnull LAction function) {
		Null.nonNullArg(function, "function");
		try {
			executeX(c1, c2, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static void shovingExecute(@Nullable CallContext c1, @Nullable CallContext c2, @Nonnull LAction function) {
		Null.nonNullArg(function, "function");
		try {
			executeX(c1, c2, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static void executeX(@Nullable CallContext c1, @Nullable CallContext c2, @Nonnull LAction function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContexts.tryInit(last, c1); // try { c1?.start() ...
		final Object s2 = last = CallContexts.tryInit(last, c2); // try { c2?.start() ...

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;

		if (primary == null) {
			try {
				function.shovingExecute();
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

	static CompletableFuture<Void> asyncExecute(@Nonnull AsyncCallContext async, @Nullable CallContext c1, @Nullable CallContext c2, @Nonnull LAction function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<Void> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					LAction.executeX(c1, c2, function);
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

	static void nestingExecute(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nonnull LAction function) {
		Null.nonNullArg(function, "function");
		try {
			executeX(c1, c2, c3, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static void shovingExecute(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nonnull LAction function) {
		Null.nonNullArg(function, "function");
		try {
			executeX(c1, c2, c3, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static void executeX(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nonnull LAction function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContexts.tryInit(last, c1); // try { c1?.start() ...
		final Object s2 = last = CallContexts.tryInit(last, c2); // try { c2?.start() ...
		final Object s3 = last = CallContexts.tryInit(last, c3); // try { c3?.start() ...

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;

		if (primary == null) {
			try {
				function.shovingExecute();
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

	static CompletableFuture<Void> asyncExecute(@Nonnull AsyncCallContext async, @Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nonnull LAction function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<Void> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					LAction.executeX(c1, c2, c3, function);
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

	static void nestingExecute(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, @Nonnull LAction function) {
		Null.nonNullArg(function, "function");
		try {
			executeX(c1, c2, c3, c4, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static void shovingExecute(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, @Nonnull LAction function) {
		Null.nonNullArg(function, "function");
		try {
			executeX(c1, c2, c3, c4, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static void executeX(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, @Nonnull LAction function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContexts.tryInit(last, c1); // try { c1?.start() ...
		final Object s2 = last = CallContexts.tryInit(last, c2); // try { c2?.start() ...
		final Object s3 = last = CallContexts.tryInit(last, c3); // try { c3?.start() ...
		final Object s4 = last = CallContexts.tryInit(last, c4); // try { c4?.start() ...

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;

		if (primary == null) {
			try {
				function.shovingExecute();
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

	static CompletableFuture<Void> asyncExecute(@Nonnull AsyncCallContext async, @Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, @Nonnull LAction function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<Void> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					LAction.executeX(c1, c2, c3, c4, function);
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

	static CompletableFuture<Void> asyncExecute(@Nonnull AsyncCallContext async, @Nonnull LAction function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<Void> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					function.executeX();
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
	static LAction act(final @Nonnull LAction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S implements LAction {
		private LAction target = null;
		@Override
		public void executeX() throws Throwable {
			target.executeX();
		}
	}

	@Nonnull
	static LAction recursive(final @Nonnull LFunction<LAction, LAction> selfLambda) {
		final S single = new S();
		LAction func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	@Nonnull
	static LAction actThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return () -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LAction actThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return () -> {
			throw exF.produce(message);
		};
	}

	static void call(final @Nonnull LAction lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.execute();
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static LAction wrap(final Runnable other) {
		return other::run;
	}
	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two LAction together in a order. */
	@Nonnull
	default LAction andThen(@Nonnull LAction after) {
		Null.nonNullArg(after, "after");
		return () -> {
			this.execute();
			after.execute();
		};
	}

	// </editor-fold>

	default LAction shoving() {

		return new LAction() {

			public void execute() {
				try {
					this.executeX();
				} catch (Throwable e) { // NOSONAR
					Handling.handleErrors(e);
					throw Handling.throwIt(e);
				}
			}

			public void executeX() throws Throwable {
				LAction.this.executeX();
			}

		};
	}

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** You can use this as a reference method whenever nothing should be done. */
	public static void doNothing() {
		// NOSONAR
	}

}
