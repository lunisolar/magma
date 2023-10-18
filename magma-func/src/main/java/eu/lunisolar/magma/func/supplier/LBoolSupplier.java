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

package eu.lunisolar.magma.func.supplier;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
import javax.annotation.concurrent.NotThreadSafe; // NOSONAR
import java.util.Objects; // NOSONAR
import eu.lunisolar.magma.basics.*; // NOSONAR
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
 * Non-throwing functional interface (lambda) LBoolSupplier for Java 8.
 *
 * Type: supplier
 *
 * Domain (lvl: 0): none
 *
 * Co-domain: boolean
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBoolSupplier extends BooleanSupplier, MetaSupplier, MetaInterface.NonThrowing, Codomain<aBool>, Domain0 { // NOSONAR

	String DESCRIPTION = "LBoolSupplier: boolean getAsBool()";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LBoolSupplier interface should be discouraged.
	 */
	@Override
	@Deprecated
	default boolean getAsBoolean() {
		return this.getAsBool();
	}

	// boolean getAsBool() ;
	default boolean getAsBool() {
		// return nestingGetAsBool();
		try {
			return this.getAsBoolX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call getAsBool()
	 */
	boolean getAsBoolX() throws Throwable;

	default boolean tupleGetAsBool(LTuple.Void args) {
		return getAsBool();
	}

	/** Function call that handles exceptions according to the instructions. */
	default boolean handlingGetAsBool(HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.getAsBoolX();
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LBoolSupplier handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return () -> handlingGetAsBool(handling);
	}

	default boolean getAsBool(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.getAsBoolX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default boolean getAsBool(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.getAsBoolX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default boolean getAsBool(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.getAsBoolX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default boolean getAsBool(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.getAsBoolX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LBoolSupplier trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return () -> getAsBool(factory, newMessage);
	}

	default LBoolSupplier trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return () -> getAsBool(factory, newMessage, param1);
	}

	default LBoolSupplier trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return () -> getAsBool(factory, newMessage, param1, param1);
	}

	default LBoolSupplier trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return () -> getAsBool(factory, newMessage, param1, param2, param3);
	}

	default boolean getAsBool(@Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.getAsBoolX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LBoolSupplier trying(@Nonnull ExWF<RuntimeException> factory) {
		return () -> getAsBool(factory);
	}

	default boolean getAsBoolThen(@Nonnull LPredicate<Throwable> handler) {
		try {
			return this.getAsBoolX();
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.test(e);
		}
	}

	default LBoolSupplier tryingThen(@Nonnull LPredicate<Throwable> handler) {
		return () -> getAsBoolThen(handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingGetAsBool() {
		try {
			return this.getAsBoolX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default boolean shovingGetAsBool() {
		try {
			return this.getAsBoolX();
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static boolean shovingGetAsBool(LBoolSupplier func) {
		Null.nonNullArg(func, "func");
		return func.shovingGetAsBool();
	}

	static boolean handlingGetAsBool(LBoolSupplier func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingGetAsBool(handling);
	}

	static boolean tryGetAsBool(LBoolSupplier func) {
		Null.nonNullArg(func, "func");
		return func.nestingGetAsBool();
	}

	static boolean tryGetAsBool(LBoolSupplier func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.getAsBool(factory, newMessage);
	}

	static boolean tryGetAsBool(LBoolSupplier func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.getAsBool(factory, newMessage, param1);
	}

	static boolean tryGetAsBool(LBoolSupplier func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.getAsBool(factory, newMessage, param1, param2);
	}

	static boolean tryGetAsBool(LBoolSupplier func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.getAsBool(factory, newMessage, param1, param2, param3);
	}

	static boolean tryGetAsBool(LBoolSupplier func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.getAsBool(factory);
	}

	static boolean tryGetAsBoolThen(LBoolSupplier func, @Nonnull LPredicate<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.getAsBoolThen(handler);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullGetAsBool() {
		return getAsBool();
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBoolSupplier.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, @Nonnull LBoolSupplier func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.getAsBool();
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.getAsBool();
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, @Nonnull LBoolSupplier func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.getAsBool();
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.getAsBool();
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, @Nonnull LBoolSupplier func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, func);
	}

	/** Change function to one that ignores output. */
	default LAction toAction() {
		return this::getAsBool;
	}

	/** Calls codomain consumer after main function. */
	default LBoolSupplier afterDo(@Nonnull LBoolConsumer after) {
		Null.nonNullArg(after, "after");
		return () -> {
			final boolean retval = getAsBool();
			after.accept(retval);
			return retval;
		};
	}

	// <editor-fold desc="CallContext">

	static boolean nestingGetAsBool(@Nullable CallContext c1, @Nonnull LBoolSupplier function) {
		Null.nonNullArg(function, "function");
		try {
			return getAsBoolX(c1, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static boolean shovingGetAsBool(@Nullable CallContext c1, @Nonnull LBoolSupplier function) {
		Null.nonNullArg(function, "function");
		try {
			return getAsBoolX(c1, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static boolean getAsBoolX(@Nullable CallContext c1, @Nonnull LBoolSupplier function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContext.tryInit(last, c1);

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;
		Object retval = null;
		if (primary == null) {
			try {
				retval = function.shovingGetAsBool();
			} catch (Throwable e) {
				primary = e;
			}
		}

		primary = CallContext.tryFinish(primary, c1, s1);

		if (primary != null) {
			throw Handling.throwIt(primary);
		}
		return (boolean) retval;
	}

	static CompletableFuture<Boolean> asyncGetAsBool(@Nonnull AsyncCallContext async, @Nullable CallContext c1, @Nonnull LBoolSupplier function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<Boolean> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					var v = LBoolSupplier.getAsBoolX(c1, function);
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

	static boolean nestingGetAsBool(@Nullable CallContext c1, @Nullable CallContext c2, @Nonnull LBoolSupplier function) {
		Null.nonNullArg(function, "function");
		try {
			return getAsBoolX(c1, c2, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static boolean shovingGetAsBool(@Nullable CallContext c1, @Nullable CallContext c2, @Nonnull LBoolSupplier function) {
		Null.nonNullArg(function, "function");
		try {
			return getAsBoolX(c1, c2, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static boolean getAsBoolX(@Nullable CallContext c1, @Nullable CallContext c2, @Nonnull LBoolSupplier function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContext.tryInit(last, c1);
		final Object s2 = last = CallContext.tryInit(last, c2);

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;
		Object retval = null;
		if (primary == null) {
			try {
				retval = function.shovingGetAsBool();
			} catch (Throwable e) {
				primary = e;
			}
		}

		primary = CallContext.tryFinish(primary, c2, s2);
		primary = CallContext.tryFinish(primary, c1, s1);

		if (primary != null) {
			throw Handling.throwIt(primary);
		}
		return (boolean) retval;
	}

	static CompletableFuture<Boolean> asyncGetAsBool(@Nonnull AsyncCallContext async, @Nullable CallContext c1, @Nullable CallContext c2, @Nonnull LBoolSupplier function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<Boolean> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					var v = LBoolSupplier.getAsBoolX(c1, c2, function);
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

	static boolean nestingGetAsBool(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nonnull LBoolSupplier function) {
		Null.nonNullArg(function, "function");
		try {
			return getAsBoolX(c1, c2, c3, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static boolean shovingGetAsBool(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nonnull LBoolSupplier function) {
		Null.nonNullArg(function, "function");
		try {
			return getAsBoolX(c1, c2, c3, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static boolean getAsBoolX(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nonnull LBoolSupplier function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContext.tryInit(last, c1);
		final Object s2 = last = CallContext.tryInit(last, c2);
		final Object s3 = last = CallContext.tryInit(last, c3);

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;
		Object retval = null;
		if (primary == null) {
			try {
				retval = function.shovingGetAsBool();
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
		return (boolean) retval;
	}

	static CompletableFuture<Boolean> asyncGetAsBool(@Nonnull AsyncCallContext async, @Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nonnull LBoolSupplier function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<Boolean> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					var v = LBoolSupplier.getAsBoolX(c1, c2, c3, function);
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

	static boolean nestingGetAsBool(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, @Nonnull LBoolSupplier function) {
		Null.nonNullArg(function, "function");
		try {
			return getAsBoolX(c1, c2, c3, c4, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static boolean shovingGetAsBool(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, @Nonnull LBoolSupplier function) {
		Null.nonNullArg(function, "function");
		try {
			return getAsBoolX(c1, c2, c3, c4, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static boolean getAsBoolX(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, @Nonnull LBoolSupplier function) throws Throwable {
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
				retval = function.shovingGetAsBool();
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
		return (boolean) retval;
	}

	static CompletableFuture<Boolean> asyncGetAsBool(@Nonnull AsyncCallContext async, @Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, @Nonnull LBoolSupplier function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<Boolean> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					var v = LBoolSupplier.getAsBoolX(c1, c2, c3, c4, function);
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

	static CompletableFuture<Boolean> asyncGetAsBool(@Nonnull AsyncCallContext async, @Nonnull LBoolSupplier function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<Boolean> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					var v = function.getAsBoolX();
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
	static LBoolSupplier of(boolean r) {
		return () -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LBoolSupplier boolSup(final @Nonnull LBoolSupplier lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S implements LBoolSupplier {
		private LBoolSupplier target = null;
		@Override
		public boolean getAsBoolX() throws Throwable {
			return target.getAsBoolX();
		}
	}

	@Nonnull
	static LBoolSupplier recursive(final @Nonnull LFunction<LBoolSupplier, LBoolSupplier> selfLambda) {
		final S single = new S();
		LBoolSupplier func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static M mementoOf(LBoolSupplier function) {
		var initialValue = function.getAsBool();
		return initializedMementoOf(initialValue, function);
	}

	public static M initializedMementoOf(boolean initialValue, LBoolSupplier function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static M deltaOf(LBoolSupplier function, LLogicalBinaryOperator deltaFunction) {
		var initialValue = function.getAsBool();
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static M deltaOf(LBoolSupplier function) {
		var initialValue = function.getAsBool();
		return initializedDeltaOf(initialValue, function, (x1, x2) -> x1 != x2);
	}

	public static M initializedDeltaOf(boolean initialValue, LBoolSupplier function, LLogicalBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.apply(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.apply(x1, x2));
	}

	public static M memento(boolean initialBaseValue, boolean initialValue, LBoolSupplier baseFunction, LLogicalTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LBoolSupplier.M)
	 */
	@NotThreadSafe
	final class M implements LBoolSupplier {

		private final LBoolSupplier baseFunction;
		private boolean lastBaseValue;
		private boolean lastValue;
		private final LLogicalTernaryOperator mementoFunction;

		private M(boolean lastBaseValue, boolean lastValue, LBoolSupplier baseFunction, LLogicalTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public boolean getAsBoolX() throws Throwable {
			boolean x1 = lastBaseValue;
			boolean x2 = lastBaseValue = baseFunction.getAsBoolX();

			return lastValue = mementoFunction.applyX(lastValue, x1, x2);
		}

		public boolean currentGetAsBool() {
			boolean x1 = lastBaseValue;
			boolean x2 = baseFunction.getAsBool();

			return mementoFunction.apply(lastValue, x1, x2);
		}

		public boolean lastValue() {
			return lastValue;
		}

		public boolean lastBaseValue() {
			return lastBaseValue;
		}

		public boolean currentBaseValue() {
			return baseFunction.getAsBool();
		}
	}

	// </editor-fold>

	@Nonnull
	static LBoolSupplier boolSupThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return () -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LBoolSupplier boolSupThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return () -> {
			throw exF.produce(message);
		};
	}

	static boolean call(final @Nonnull LBoolSupplier lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.getAsBool();
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static LBoolSupplier wrap(final BooleanSupplier other) {
		return other::getAsBoolean;
	}
	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LSupplier<V> toSup(@Nonnull LBoolFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return () -> after.apply(this.getAsBool());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteSupplier toByteSup(@Nonnull LBoolToByteFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsByte(this.getAsBool());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtSupplier toSrtSup(@Nonnull LBoolToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsSrt(this.getAsBool());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntSupplier toIntSup(@Nonnull LBoolToIntFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsInt(this.getAsBool());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongSupplier toLongSup(@Nonnull LBoolToLongFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsLong(this.getAsBool());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltSupplier toFltSup(@Nonnull LBoolToFltFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsFlt(this.getAsBool());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblSupplier toDblSup(@Nonnull LBoolToDblFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsDbl(this.getAsBool());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharSupplier toCharSup(@Nonnull LBoolToCharFunction after) {
		Null.nonNullArg(after, "after");
		return () -> after.applyAsChar(this.getAsBool());
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolSupplier toBoolSup(@Nonnull LLogicalOperator after) {
		Null.nonNullArg(after, "after");
		return () -> after.apply(this.getAsBool());
	}

	// </editor-fold>

	default LBoolSupplier shoving() {

		return new LBoolSupplier() {

			public boolean getAsBool() {
				try {
					return this.getAsBoolX();
				} catch (Throwable e) { // NOSONAR
					Handling.handleErrors(e);
					throw Handling.throwIt(e);
				}
			}

			public boolean getAsBoolX() throws Throwable {
				return LBoolSupplier.this.getAsBoolX();
			}

		};
	}

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LBoolSupplier) Supplier */
	public static boolean doNothing() {
		return Function4U.defaultBoolean;
	}

}
