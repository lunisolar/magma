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

package eu.lunisolar.magma.func.function.to;

import javax.annotation.Nonnull; // NOSONAR
import javax.annotation.Nullable; // NOSONAR
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
 * Non-throwing functional interface (lambda) LToLongFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): T a
 *
 * Co-domain: long
 *
 * Special case of function that corresponds to expressions like (iterator) -> Iterator::next
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LToLongFunction<T> extends ToLongFunction<T>, MetaFunction, MetaInterface.NonThrowing, OFunction<T, aLong> { // NOSONAR

	String DESCRIPTION = "LToLongFunction: long doApplyAsLong(T a)";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LToLongFunction interface should be discouraged.
	 */
	@Override
	@Deprecated
	default long applyAsLong(T a) {
		return this.doApplyAsLong(a);
	}

	// long doApplyAsLong(T a) ;
	default long doApplyAsLong(T a) {
		// return nestingDoApplyAsLong(a);
		try {
			return this.doApplyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doApplyAsLong(T a)
	 */
	long doApplyAsLongX(T a) throws Throwable;

	default long tupleApplyAsLong(LSingle<T> args) {
		return doApplyAsLong(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default long handlingDoApplyAsLong(T a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default long tryDoApplyAsLong(T a, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default long tryDoApplyAsLong(T a, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default long tryDoApplyAsLongThen(T a, @Nonnull LToLongFunction<Throwable> handler) {
		try {
			return this.doApplyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApplyAsLong(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default long nestingDoApplyAsLong(T a) {
		try {
			return this.doApplyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default long shovingDoApplyAsLong(T a) {
		try {
			return this.doApplyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> long handlingDoApplyAsLong(T a, LToLongFunction<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApplyAsLong(a, handling);
	}

	static <T> long tryDoApplyAsLong(T a, LToLongFunction<T> func) {
		return tryDoApplyAsLong(a, func, null);
	}

	static <T> long tryDoApplyAsLong(T a, LToLongFunction<T> func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsLong(a, exceptionFactory, newMessage, messageParams);
	}

	static <T> long tryDoApplyAsLong(T a, LToLongFunction<T> func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsLong(a, exceptionFactory);
	}

	static <T> long tryDoApplyAsLongThen(T a, LToLongFunction<T> func, @Nonnull LToLongFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsLongThen(a, handler);
	}

	default long failSafeDoApplyAsLong(T a, @Nonnull LToLongFunction<T> failSafe) {
		try {
			return doApplyAsLong(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApplyAsLong(a);
		}
	}

	static <T> long failSafeDoApplyAsLong(T a, LToLongFunction<T> func, @Nonnull LToLongFunction<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApplyAsLong(a);
		} else {
			return func.failSafeDoApplyAsLong(a, failSafe);
		}
	}

	static <T> LToLongFunction<T> failSafeToLongFunc(LToLongFunction<T> func, @Nonnull LToLongFunction<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeDoApplyAsLong(a, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default long nonNullDoApplyAsLong(T a) {
		return doApplyAsLong(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LToLongFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_i, int max_i, T a, LToLongFunction<T> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.doApplyAsLong(a);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.doApplyAsLong(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTill(int min_i, int max_i, T a, LToLongFunction<T> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i < max_i; i++) {
				func.doApplyAsLong(a);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.doApplyAsLong(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void times(int max_i, T a, LToLongFunction<T> func) {
		fromTill(0, max_i, a, func);
	}

	/** Captures arguments but delays the evaluation. */
	default LLongSupplier captureToLongFunc(T a) {
		return () -> this.doApplyAsLong(a);
	}

	/** Creates function that always returns the same value. */
	static <T> LToLongFunction<T> constant(long r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LToLongFunction<T> toLongFunc(final @Nonnull LToLongFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T> LToLongFunction<T> recursive(final @Nonnull LFunction<LToLongFunction<T>, LToLongFunction<T>> selfLambda) {
		final LToLongFunctionSingle<T> single = new LToLongFunctionSingle();
		LToLongFunction<T> func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LToLongFunctionSingle<T> implements LSingle<LToLongFunction<T>>, LToLongFunction<T> {
		private LToLongFunction<T> target = null;

		@Override
		public long doApplyAsLongX(T a) throws Throwable {
			return target.doApplyAsLongX(a);
		}

		@Override
		public LToLongFunction<T> value() {
			return target;
		}
	}

	@Nonnull
	static <T> LToLongFunction<T> toLongFuncThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static <T> LToLongFunction<T> toLongFuncThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce(message);
		};
	}

	static <T> long call(T a, final @Nonnull LToLongFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsLong(a);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <T> LToLongFunction<T> wrap(final ToLongFunction<T> other) {
		return other::applyAsLong;
	}
	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceLong). */
	@Nonnull
	static <T> LToLongFunction<T> safe() {
		return LToLongFunction::produceLong;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LToLongFunction<T>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T> LToLongFunction<T> safe(final @Nullable LToLongFunction<T> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LToLongFunction<T>> safeSupplier(final @Nullable LSupplier<LToLongFunction<T>> supplier) {
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
	default <V> LToLongFunction<V> toLongFuncCompose(@Nonnull final LFunction<? super V, ? extends T> before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApplyAsLong(before.doApply(v));
	}

	public static <V, T> LToLongFunction<V> composed(@Nonnull final LFunction<? super V, ? extends T> before, LToLongFunction<T> after) {
		return after.toLongFuncCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LFunction<T, V> then(@Nonnull LLongFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApply(this.doApplyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToByteFunction<T> thenToByte(@Nonnull LLongToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsByte(this.doApplyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToSrtFunction<T> thenToSrt(@Nonnull LLongToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsSrt(this.doApplyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToIntFunction<T> thenToInt(@Nonnull LLongToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsInt(this.doApplyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToLongFunction<T> thenToLong(@Nonnull LLongUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsLong(this.doApplyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToFltFunction<T> thenToFlt(@Nonnull LLongToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsFlt(this.doApplyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToDblFunction<T> thenToDbl(@Nonnull LLongToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsDbl(this.doApplyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToCharFunction<T> thenToChar(@Nonnull LLongToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsChar(this.doApplyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LPredicate<T> thenToBool(@Nonnull LLongPredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.doTest(this.doApplyAsLong(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LToLongFunction<T> nestingToLongFunc() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LToLongFunction<T> shovingToLongFunc() {
		return this;
	}

	// </editor-fold>

	/** Does nothing (LToLongFunction) Function */
	public static <T> long produceLong(T a) {
		return Function4U.defaultLong;
	}

	// MAP: FOR, [SourcePurpose{arg=T a, type=IA}, SourcePurpose{arg=LLongConsumer consumer, type=CONST}]
	default <C0> void forEach(IndexedRead<C0, a<T>> ia, C0 source, LLongConsumer consumer) {
		int size = ia.size(source);
		LOiFunction<Object, T> oiFunc0 = (LOiFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			T a = oiFunc0.doApply(source, i);
			consumer.doAccept(this.doApplyAsLong(a));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T a, type=SA}, SourcePurpose{arg=LLongConsumer consumer, type=CONST}]
	default <C0, I0> void iterate(SequentialRead<C0, I0, a<T>> sa, C0 source, LLongConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).doApply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LFunction<Object, T> nextFunc0 = (LFunction) sa.getter();
		while (testFunc0.doTest(iterator0)) {
			T a = nextFunc0.doApply(iterator0);
			consumer.doAccept(this.doApplyAsLong(a));
		}
	}

}
