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

package eu.lunisolar.magma.func.function.from;

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
 * Non-throwing functional interface (lambda) LLongFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): long a
 *
 * Co-domain: R
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LLongFunction<R> extends LongFunction<R>, MetaFunction, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LLongFunction: R doApply(long a)";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LLongFunction interface should be discouraged.
	 */
	@Override
	@Deprecated
	default R apply(long a) {
		return this.doApply(a);
	}

	@Nullable
	// R doApply(long a) ;
	default R doApply(long a) {
		// return nestingDoApply(a);
		try {
			return this.doApplyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doApply(long a)
	 */
	R doApplyX(long a) throws Throwable;

	default R tupleApply(LLongSingle args) {
		return doApply(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default R handlingDoApply(long a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default R tryDoApply(long a, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default R tryDoApply(long a, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default R tryDoApplyThen(long a, @Nonnull LFunction<Throwable, R> handler) {
		try {
			return this.doApplyX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApply(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default R nestingDoApply(long a) {
		try {
			return this.doApplyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default R shovingDoApply(long a) {
		try {
			return this.doApplyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <R> R handlingDoApply(long a, LLongFunction<R> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApply(a, handling);
	}

	static <R> R tryDoApply(long a, LLongFunction<R> func) {
		return tryDoApply(a, func, null);
	}

	static <R> R tryDoApply(long a, LLongFunction<R> func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApply(a, exceptionFactory, newMessage, messageParams);
	}

	static <R> R tryDoApply(long a, LLongFunction<R> func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApply(a, exceptionFactory);
	}

	static <R> R tryDoApplyThen(long a, LLongFunction<R> func, @Nonnull LFunction<Throwable, R> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyThen(a, handler);
	}

	default R failSafeDoApply(long a, @Nonnull LLongFunction<R> failSafe) {
		try {
			return doApply(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApply(a);
		}
	}

	static <R> R failSafeDoApply(long a, LLongFunction<R> func, @Nonnull LLongFunction<R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApply(a);
		} else {
			return func.failSafeDoApply(a, failSafe);
		}
	}

	static <R> LLongFunction<R> failSafeLongFunc(LLongFunction<R> func, @Nonnull LLongFunction<R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeDoApply(a, func, failSafe);
	}

	LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullDoApply() method cannot be null (" + DESCRIPTION + ").";

	/** Function call that ensures the result is not null */
	@Nonnull
	default R nonNullDoApply(long a) {
		return Null.requireNonNull(doApply(a), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LLongFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <R> void fromTo(long min_a, long max_a, LLongFunction<R> func) {
		Null.nonNullArg(func, "func");
		if (min_a <= min_a) {
			for (long a = min_a; a <= max_a; a++) {
				func.doApply(a);
			}
		} else {
			for (long a = min_a; a >= max_a; a--) {
				func.doApply(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <R> void fromTill(long min_a, long max_a, LLongFunction<R> func) {
		Null.nonNullArg(func, "func");
		if (min_a <= min_a) {
			for (long a = min_a; a < max_a; a++) {
				func.doApply(a);
			}
		} else {
			for (long a = min_a; a > max_a; a--) {
				func.doApply(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <R> void times(long max_a, LLongFunction<R> func) {
		fromTill(0, max_a, func);
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplier<R> captureLongFunc(long a) {
		return () -> this.doApply(a);
	}

	/** Creates function that always returns the same value. */
	static <R> LLongFunction<R> constant(R r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <R> LLongFunction<R> longFunc(final @Nonnull LLongFunction<R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <R> LLongFunction<R> recursive(final @Nonnull LFunction<LLongFunction<R>, LLongFunction<R>> selfLambda) {
		final LLongFunctionSingle<R> single = new LLongFunctionSingle();
		LLongFunction<R> func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LLongFunctionSingle<R> implements LSingle<LLongFunction<R>>, LLongFunction<R> {
		private LLongFunction<R> target = null;

		@Override
		public R doApplyX(long a) throws Throwable {
			return target.doApplyX(a);
		}

		@Override
		public LLongFunction<R> value() {
			return target;
		}
	}

	@Nonnull
	static <R> LLongFunction<R> longFuncThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static <R> LLongFunction<R> longFuncThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce(message);
		};
	}

	static <R> R call(long a, final @Nonnull LLongFunction<R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApply(a);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <R> LLongFunction<R> wrap(final LongFunction<R> other) {
		return other::apply;
	}
	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produce). */
	@Nonnull
	static <R> LLongFunction<R> safe() {
		return LLongFunction::produce;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <R> LSupplier<LLongFunction<R>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <R> LLongFunction<R> safe(final @Nullable LLongFunction<R> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <R> LSupplier<LLongFunction<R>> safeSupplier(final @Nullable LSupplier<LLongFunction<R>> supplier) {
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
	default LLongFunction<R> longFuncComposeLong(@Nonnull final LLongUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApply(before.doApplyAsLong(v));
	}

	public static <R> LLongFunction<R> composedLong(@Nonnull final LLongUnaryOperator before, LLongFunction<R> after) {
		return after.longFuncComposeLong(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LFunction<V, R> longFuncCompose(@Nonnull final LToLongFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApply(before.doApplyAsLong(v));
	}

	public static <V, R> LFunction<V, R> composed(@Nonnull final LToLongFunction<? super V> before, LLongFunction<R> after) {
		return after.longFuncCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LLongFunction<V> then(@Nonnull LFunction<? super R, ? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApply(this.doApply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongConsumer thenConsume(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doAccept(this.doApply(a));
	}

	@Nonnull
	default LLongFunction<R> before(@Nonnull LLongConsumer before) {
		Null.nonNullArg(before, "before");
		return a -> {
			before.doAccept(a);
			return this.doApply(a);
		};
	}

	@Nonnull
	default LLongFunction<R> after(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> {
			R result = this.doApply(a);
			after.doAccept(result);
			return result;
		};
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToByteFunction thenToByte(@Nonnull LToByteFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsByte(this.doApply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToSrtFunction thenToSrt(@Nonnull LToSrtFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsSrt(this.doApply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToIntFunction thenToInt(@Nonnull LToIntFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsInt(this.doApply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongUnaryOperator thenToLong(@Nonnull LToLongFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsLong(this.doApply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToFltFunction thenToFlt(@Nonnull LToFltFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsFlt(this.doApply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToDblFunction thenToDbl(@Nonnull LToDblFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsDbl(this.doApply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToCharFunction thenToChar(@Nonnull LToCharFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsChar(this.doApply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongPredicate thenToBool(@Nonnull LPredicate<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doTest(this.doApply(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LLongFunction<R> nestingLongFunc() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLongFunction<R> shovingLongFunc() {
		return this;
	}

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LLongFunction<R> nonNullLongFunc() {
		return this::nonNullDoApply;
	}

	/** Does nothing (LLongFunction) Function */
	public static <R> R produce(long a) {
		return (R) Function4U.defaultObject;
	}

	// MAP: FOR, [SourcePurpose{arg=long a, type=IA}, SourcePurpose{arg=LConsumer<? super R> consumer, type=CONST}]
	default <C0> void forEach(IndexedRead<C0, aLong> ia, C0 source, LConsumer<? super R> consumer) {
		int size = ia.size(source);
		LOiToLongFunction<Object> oiFunc0 = (LOiToLongFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			long a = oiFunc0.doApplyAsLong(source, i);
			consumer.doAccept(this.doApply(a));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=long a, type=SA}, SourcePurpose{arg=LConsumer<? super R> consumer, type=CONST}]
	default <C0, I0> void iterate(SequentialRead<C0, I0, aLong> sa, C0 source, LConsumer<? super R> consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).doApply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToLongFunction<Object> nextFunc0 = (LToLongFunction) sa.getter();
		while (testFunc0.doTest(iterator0)) {
			long a = nextFunc0.doApplyAsLong(iterator0);
			consumer.doAccept(this.doApply(a));
		}
	}

}
