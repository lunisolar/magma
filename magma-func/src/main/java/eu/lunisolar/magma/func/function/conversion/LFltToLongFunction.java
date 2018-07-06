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

package eu.lunisolar.magma.func.function.conversion;

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
 * Non-throwing functional interface (lambda) LFltToLongFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): float a
 *
 * Co-domain: long
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LFltToLongFunction extends MetaFunction, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LFltToLongFunction: long doApplyAsLong(float a)";

	// long doApplyAsLong(float a) ;
	default long doApplyAsLong(float a) {
		// return nestingDoApplyAsLong(a);
		try {
			return this.doApplyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doApplyAsLong(float a)
	 */
	long doApplyAsLongX(float a) throws Throwable;

	default long tupleApplyAsLong(LFltSingle args) {
		return doApplyAsLong(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default long handlingDoApplyAsLong(float a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default long tryDoApplyAsLong(float a, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default long tryDoApplyAsLong(float a, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default long tryDoApplyAsLongThen(float a, @Nonnull LToLongFunction<Throwable> handler) {
		try {
			return this.doApplyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApplyAsLong(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default long nestingDoApplyAsLong(float a) {
		try {
			return this.doApplyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default long shovingDoApplyAsLong(float a) {
		try {
			return this.doApplyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static long handlingDoApplyAsLong(float a, LFltToLongFunction func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApplyAsLong(a, handling);
	}

	static long tryDoApplyAsLong(float a, LFltToLongFunction func) {
		return tryDoApplyAsLong(a, func, null);
	}

	static long tryDoApplyAsLong(float a, LFltToLongFunction func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsLong(a, exceptionFactory, newMessage, messageParams);
	}

	static long tryDoApplyAsLong(float a, LFltToLongFunction func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsLong(a, exceptionFactory);
	}

	static long tryDoApplyAsLongThen(float a, LFltToLongFunction func, @Nonnull LToLongFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsLongThen(a, handler);
	}

	default long failSafeDoApplyAsLong(float a, @Nonnull LFltToLongFunction failSafe) {
		try {
			return doApplyAsLong(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApplyAsLong(a);
		}
	}

	static long failSafeDoApplyAsLong(float a, LFltToLongFunction func, @Nonnull LFltToLongFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApplyAsLong(a);
		} else {
			return func.failSafeDoApplyAsLong(a, failSafe);
		}
	}

	static LFltToLongFunction failSafeFltToLongFunc(LFltToLongFunction func, @Nonnull LFltToLongFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeDoApplyAsLong(a, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default long nonNullDoApplyAsLong(float a) {
		return doApplyAsLong(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LFltToLongFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, float a, LFltToLongFunction func) {
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
	public static void fromTill(int min_i, int max_i, float a, LFltToLongFunction func) {
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
	public static void times(int max_i, float a, LFltToLongFunction func) {
		fromTill(0, max_i, a, func);
	}

	/** Captures arguments but delays the evaluation. */
	default LLongSupplier captureFltToLongFunc(float a) {
		return () -> this.doApplyAsLong(a);
	}

	/** Creates function that always returns the same value. */
	static LFltToLongFunction constant(long r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LFltToLongFunction fltToLongFunc(final @Nonnull LFltToLongFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LFltToLongFunction recursive(final @Nonnull LFunction<LFltToLongFunction, LFltToLongFunction> selfLambda) {
		final LFltToLongFunctionSingle single = new LFltToLongFunctionSingle();
		LFltToLongFunction func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LFltToLongFunctionSingle implements LSingle<LFltToLongFunction>, LFltToLongFunction {
		private LFltToLongFunction target = null;

		@Override
		public long doApplyAsLongX(float a) throws Throwable {
			return target.doApplyAsLongX(a);
		}

		@Override
		public LFltToLongFunction value() {
			return target;
		}
	}

	@Nonnull
	static LFltToLongFunction fltToLongFuncThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LFltToLongFunction fltToLongFuncThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce(message);
		};
	}

	static long call(float a, final @Nonnull LFltToLongFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsLong(a);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceLong). */
	@Nonnull
	static LFltToLongFunction safe() {
		return LFltToLongFunction::produceLong;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LFltToLongFunction> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LFltToLongFunction safe(final @Nullable LFltToLongFunction other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LFltToLongFunction> safeSupplier(final @Nullable LSupplier<LFltToLongFunction> supplier) {
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
	default LFltToLongFunction fltToLongFuncComposeFlt(@Nonnull final LFltUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApplyAsLong(before.doApplyAsFlt(v));
	}

	public static LFltToLongFunction composedFlt(@Nonnull final LFltUnaryOperator before, LFltToLongFunction after) {
		return after.fltToLongFuncComposeFlt(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToLongFunction<V> fltToLongFuncCompose(@Nonnull final LToFltFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApplyAsLong(before.doApplyAsFlt(v));
	}

	public static <V> LToLongFunction<V> composed(@Nonnull final LToFltFunction<? super V> before, LFltToLongFunction after) {
		return after.fltToLongFuncCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LFltFunction<V> then(@Nonnull LLongFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApply(this.doApplyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltToByteFunction thenToByte(@Nonnull LLongToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsByte(this.doApplyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltToSrtFunction thenToSrt(@Nonnull LLongToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsSrt(this.doApplyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltToIntFunction thenToInt(@Nonnull LLongToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsInt(this.doApplyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltToLongFunction thenToLong(@Nonnull LLongUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsLong(this.doApplyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltUnaryOperator thenToFlt(@Nonnull LLongToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsFlt(this.doApplyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltToDblFunction thenToDbl(@Nonnull LLongToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsDbl(this.doApplyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltToCharFunction thenToChar(@Nonnull LLongToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsChar(this.doApplyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltPredicate thenToBool(@Nonnull LLongPredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.doTest(this.doApplyAsLong(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LFltToLongFunction nestingFltToLongFunc() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LFltToLongFunction shovingFltToLongFunc() {
		return this;
	}

	// </editor-fold>

	/** Does nothing (LFltToLongFunction) Function */
	public static long produceLong(float a) {
		return Function4U.defaultLong;
	}

	// MAP: FOR, [SourcePurpose{arg=float a, type=IA}, SourcePurpose{arg=LLongConsumer consumer, type=CONST}]
	default <C0> void forEach(IndexedRead<C0, aFloat> ia, C0 source, LLongConsumer consumer) {
		int size = ia.size(source);
		LOiToFltFunction<Object> oiFunc0 = (LOiToFltFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			float a = oiFunc0.doApplyAsFlt(source, i);
			consumer.doAccept(this.doApplyAsLong(a));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=float a, type=SA}, SourcePurpose{arg=LLongConsumer consumer, type=CONST}]
	default <C0, I0> void iterate(SequentialRead<C0, I0, aFloat> sa, C0 source, LLongConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).doApply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToFltFunction<Object> nextFunc0 = (LToFltFunction) sa.getter();
		while (testFunc0.doTest(iterator0)) {
			float a = nextFunc0.doApplyAsFlt(iterator0);
			consumer.doAccept(this.doApplyAsLong(a));
		}
	}

}
