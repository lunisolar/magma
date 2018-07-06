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
 * Non-throwing functional interface (lambda) LFltToSrtFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): float a
 *
 * Co-domain: short
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LFltToSrtFunction extends MetaFunction, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LFltToSrtFunction: short doApplyAsSrt(float a)";

	// short doApplyAsSrt(float a) ;
	default short doApplyAsSrt(float a) {
		// return nestingDoApplyAsSrt(a);
		try {
			return this.doApplyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doApplyAsSrt(float a)
	 */
	short doApplyAsSrtX(float a) throws Throwable;

	default short tupleApplyAsSrt(LFltSingle args) {
		return doApplyAsSrt(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default short handlingDoApplyAsSrt(float a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default short tryDoApplyAsSrt(float a, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default short tryDoApplyAsSrt(float a, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default short tryDoApplyAsSrtThen(float a, @Nonnull LToSrtFunction<Throwable> handler) {
		try {
			return this.doApplyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApplyAsSrt(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default short nestingDoApplyAsSrt(float a) {
		try {
			return this.doApplyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default short shovingDoApplyAsSrt(float a) {
		try {
			return this.doApplyAsSrtX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static short handlingDoApplyAsSrt(float a, LFltToSrtFunction func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApplyAsSrt(a, handling);
	}

	static short tryDoApplyAsSrt(float a, LFltToSrtFunction func) {
		return tryDoApplyAsSrt(a, func, null);
	}

	static short tryDoApplyAsSrt(float a, LFltToSrtFunction func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsSrt(a, exceptionFactory, newMessage, messageParams);
	}

	static short tryDoApplyAsSrt(float a, LFltToSrtFunction func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsSrt(a, exceptionFactory);
	}

	static short tryDoApplyAsSrtThen(float a, LFltToSrtFunction func, @Nonnull LToSrtFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsSrtThen(a, handler);
	}

	default short failSafeDoApplyAsSrt(float a, @Nonnull LFltToSrtFunction failSafe) {
		try {
			return doApplyAsSrt(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApplyAsSrt(a);
		}
	}

	static short failSafeDoApplyAsSrt(float a, LFltToSrtFunction func, @Nonnull LFltToSrtFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApplyAsSrt(a);
		} else {
			return func.failSafeDoApplyAsSrt(a, failSafe);
		}
	}

	static LFltToSrtFunction failSafeFltToSrtFunc(LFltToSrtFunction func, @Nonnull LFltToSrtFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeDoApplyAsSrt(a, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default short nonNullDoApplyAsSrt(float a) {
		return doApplyAsSrt(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LFltToSrtFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, float a, LFltToSrtFunction func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.doApplyAsSrt(a);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.doApplyAsSrt(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, float a, LFltToSrtFunction func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i < max_i; i++) {
				func.doApplyAsSrt(a);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.doApplyAsSrt(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, float a, LFltToSrtFunction func) {
		fromTill(0, max_i, a, func);
	}

	/** Captures arguments but delays the evaluation. */
	default LSrtSupplier captureFltToSrtFunc(float a) {
		return () -> this.doApplyAsSrt(a);
	}

	/** Creates function that always returns the same value. */
	static LFltToSrtFunction constant(short r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LFltToSrtFunction fltToSrtFunc(final @Nonnull LFltToSrtFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LFltToSrtFunction recursive(final @Nonnull LFunction<LFltToSrtFunction, LFltToSrtFunction> selfLambda) {
		final LFltToSrtFunctionSingle single = new LFltToSrtFunctionSingle();
		LFltToSrtFunction func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LFltToSrtFunctionSingle implements LSingle<LFltToSrtFunction>, LFltToSrtFunction {
		private LFltToSrtFunction target = null;

		@Override
		public short doApplyAsSrtX(float a) throws Throwable {
			return target.doApplyAsSrtX(a);
		}

		@Override
		public LFltToSrtFunction value() {
			return target;
		}
	}

	@Nonnull
	static LFltToSrtFunction fltToSrtFuncThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LFltToSrtFunction fltToSrtFuncThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce(message);
		};
	}

	static short call(float a, final @Nonnull LFltToSrtFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsSrt(a);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceShort). */
	@Nonnull
	static LFltToSrtFunction safe() {
		return LFltToSrtFunction::produceShort;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LFltToSrtFunction> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LFltToSrtFunction safe(final @Nullable LFltToSrtFunction other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LFltToSrtFunction> safeSupplier(final @Nullable LSupplier<LFltToSrtFunction> supplier) {
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
	default LFltToSrtFunction fltToSrtFuncComposeFlt(@Nonnull final LFltUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApplyAsSrt(before.doApplyAsFlt(v));
	}

	public static LFltToSrtFunction composedFlt(@Nonnull final LFltUnaryOperator before, LFltToSrtFunction after) {
		return after.fltToSrtFuncComposeFlt(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToSrtFunction<V> fltToSrtFuncCompose(@Nonnull final LToFltFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApplyAsSrt(before.doApplyAsFlt(v));
	}

	public static <V> LToSrtFunction<V> composed(@Nonnull final LToFltFunction<? super V> before, LFltToSrtFunction after) {
		return after.fltToSrtFuncCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LFltFunction<V> then(@Nonnull LSrtFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApply(this.doApplyAsSrt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltToByteFunction thenToByte(@Nonnull LSrtToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsByte(this.doApplyAsSrt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltToSrtFunction thenToSrt(@Nonnull LSrtUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsSrt(this.doApplyAsSrt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltToIntFunction thenToInt(@Nonnull LSrtToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsInt(this.doApplyAsSrt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltToLongFunction thenToLong(@Nonnull LSrtToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsLong(this.doApplyAsSrt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltUnaryOperator thenToFlt(@Nonnull LSrtToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsFlt(this.doApplyAsSrt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltToDblFunction thenToDbl(@Nonnull LSrtToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsDbl(this.doApplyAsSrt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltToCharFunction thenToChar(@Nonnull LSrtToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsChar(this.doApplyAsSrt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltPredicate thenToBool(@Nonnull LSrtPredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.doTest(this.doApplyAsSrt(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LFltToSrtFunction nestingFltToSrtFunc() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LFltToSrtFunction shovingFltToSrtFunc() {
		return this;
	}

	// </editor-fold>

	/** Does nothing (LFltToSrtFunction) Function */
	public static short produceShort(float a) {
		return Function4U.defaultShort;
	}

	// MAP: FOR, [SourcePurpose{arg=float a, type=IA}, SourcePurpose{arg=LSrtConsumer consumer, type=CONST}]
	default <C0> void forEach(IndexedRead<C0, aFloat> ia, C0 source, LSrtConsumer consumer) {
		int size = ia.size(source);
		LOiToFltFunction<Object> oiFunc0 = (LOiToFltFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			float a = oiFunc0.doApplyAsFlt(source, i);
			consumer.doAccept(this.doApplyAsSrt(a));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=float a, type=SA}, SourcePurpose{arg=LSrtConsumer consumer, type=CONST}]
	default <C0, I0> void iterate(SequentialRead<C0, I0, aFloat> sa, C0 source, LSrtConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).doApply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToFltFunction<Object> nextFunc0 = (LToFltFunction) sa.getter();
		while (testFunc0.doTest(iterator0)) {
			float a = nextFunc0.doApplyAsFlt(iterator0);
			consumer.doAccept(this.doApplyAsSrt(a));
		}
	}

}
