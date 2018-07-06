/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2016 Lunisolar (http://lunisolar.eu/).
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
import java.util.function.*; // NOSONAR

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
public interface LAction extends Runnable, MetaAction, MetaInterface.NonThrowing {

	String DESCRIPTION = "LAction: void doExecute()";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LAction interface should be discouraged.
	 */
	@Override
	@Deprecated
	default void run() {
		this.doExecute();
	}

	// void doExecute() ;
	default void doExecute() {
		// nestingDoExecute();
		try {
			this.doExecuteX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doExecute()
	 */
	void doExecuteX() throws Throwable;

	default LTuple.Void tupleExecute(LTuple.Void args) {
		doExecute();
		return LTuple.Void.INSTANCE;
	}

	/** Function call that handles exceptions according to the instructions. */
	default void handlingDoExecute(HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			this.doExecuteX();
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default void tryDoExecute(@Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			this.doExecuteX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default void tryDoExecute(@Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			this.doExecuteX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default void tryDoExecuteThen(@Nonnull LConsumer<Throwable> handler) {
		try {
			this.doExecuteX();
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			handler.doAccept(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default void nestingDoExecute() {
		try {
			this.doExecuteX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default void shovingDoExecute() {
		try {
			this.doExecuteX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static void handlingDoExecute(LAction func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		func.handlingDoExecute(handling);
	}

	static void tryDoExecute(LAction func) {
		tryDoExecute(func, null);
	}

	static void tryDoExecute(LAction func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		func.tryDoExecute(exceptionFactory, newMessage, messageParams);
	}

	static void tryDoExecute(LAction func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		func.tryDoExecute(exceptionFactory);
	}

	static void tryDoExecuteThen(LAction func, @Nonnull LConsumer<Throwable> handler) {
		Null.nonNullArg(func, "func");
		func.tryDoExecuteThen(handler);
	}

	default void failSafeDoExecute(@Nonnull LAction failSafe) {
		try {
			doExecute();
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			failSafe.doExecute();
		}
	}

	static void failSafeDoExecute(LAction func, @Nonnull LAction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			failSafe.doExecute();
		} else {
			func.failSafeDoExecute(failSafe);
		}
	}

	static LAction failSafeAct(LAction func, @Nonnull LAction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return () -> failSafeDoExecute(func, failSafe);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LAction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, LAction func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.doExecute();
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.doExecute();
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, LAction func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i < max_i; i++) {
				func.doExecute();
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.doExecute();
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, LAction func) {
		fromTill(0, max_i, func);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LAction act(final @Nonnull LAction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LAction recursive(final @Nonnull LFunction<LAction, LAction> selfLambda) {
		final LActionSingle single = new LActionSingle();
		LAction func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LActionSingle implements LSingle<LAction>, LAction {
		private LAction target = null;

		@Override
		public void doExecuteX() throws Throwable {
			target.doExecuteX();
		}

		@Override
		public LAction value() {
			return target;
		}
	}

	@Nonnull
	static LAction actThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return () -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LAction actThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return () -> {
			throw exceptionFactory.produce(message);
		};
	}

	static void call(final @Nonnull LAction lambda) {
		Null.nonNullArg(lambda, "lambda");
		lambda.doExecute();
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static LAction wrap(final Runnable other) {
		return other::run;
	}
	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. */
	@Nonnull
	static LAction safe() {
		return LAction::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LAction> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LAction safe(final @Nullable LAction other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LAction> safeSupplier(final @Nullable LSupplier<LAction> supplier) {
		if (supplier == null) {
			return safeSupplier();
		} else {
			return supplier;
		}
	}

	// </editor-fold>

	// <editor-fold desc="andThen (consumer/action)">

	/** Combines two LAction together in a order. */
	@Nonnull
	default LAction andThen(@Nonnull LAction after) {
		Null.nonNullArg(after, "after");
		return () -> {
			this.doExecute();
			after.doExecute();
		};
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LAction nestingAct() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LAction shovingAct() {
		return this;
	}

	// </editor-fold>

	/** You can use this as a reference method whenever nothing should be done. */
	public static void doNothing() {
		// NOSONAR
	}

}
