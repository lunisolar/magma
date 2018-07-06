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
 * Non-throwing functional interface (lambda) LFltToByteFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): float a
 *
 * Co-domain: byte
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LFltToByteFunction extends MetaFunction, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LFltToByteFunction: byte doApplyAsByte(float a)";

	// byte doApplyAsByte(float a) ;
	default byte doApplyAsByte(float a) {
		// return nestingDoApplyAsByte(a);
		try {
			return this.doApplyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doApplyAsByte(float a)
	 */
	byte doApplyAsByteX(float a) throws Throwable;

	default byte tupleApplyAsByte(LFltSingle args) {
		return doApplyAsByte(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default byte handlingDoApplyAsByte(float a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default byte tryDoApplyAsByte(float a, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default byte tryDoApplyAsByte(float a, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default byte tryDoApplyAsByteThen(float a, @Nonnull LToByteFunction<Throwable> handler) {
		try {
			return this.doApplyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApplyAsByte(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default byte nestingDoApplyAsByte(float a) {
		try {
			return this.doApplyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default byte shovingDoApplyAsByte(float a) {
		try {
			return this.doApplyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static byte handlingDoApplyAsByte(float a, LFltToByteFunction func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApplyAsByte(a, handling);
	}

	static byte tryDoApplyAsByte(float a, LFltToByteFunction func) {
		return tryDoApplyAsByte(a, func, null);
	}

	static byte tryDoApplyAsByte(float a, LFltToByteFunction func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsByte(a, exceptionFactory, newMessage, messageParams);
	}

	static byte tryDoApplyAsByte(float a, LFltToByteFunction func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsByte(a, exceptionFactory);
	}

	static byte tryDoApplyAsByteThen(float a, LFltToByteFunction func, @Nonnull LToByteFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsByteThen(a, handler);
	}

	default byte failSafeDoApplyAsByte(float a, @Nonnull LFltToByteFunction failSafe) {
		try {
			return doApplyAsByte(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApplyAsByte(a);
		}
	}

	static byte failSafeDoApplyAsByte(float a, LFltToByteFunction func, @Nonnull LFltToByteFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApplyAsByte(a);
		} else {
			return func.failSafeDoApplyAsByte(a, failSafe);
		}
	}

	static LFltToByteFunction failSafeFltToByteFunc(LFltToByteFunction func, @Nonnull LFltToByteFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeDoApplyAsByte(a, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default byte nonNullDoApplyAsByte(float a) {
		return doApplyAsByte(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LFltToByteFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, float a, LFltToByteFunction func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.doApplyAsByte(a);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.doApplyAsByte(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, float a, LFltToByteFunction func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i < max_i; i++) {
				func.doApplyAsByte(a);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.doApplyAsByte(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, float a, LFltToByteFunction func) {
		fromTill(0, max_i, a, func);
	}

	/** Captures arguments but delays the evaluation. */
	default LByteSupplier captureFltToByteFunc(float a) {
		return () -> this.doApplyAsByte(a);
	}

	/** Creates function that always returns the same value. */
	static LFltToByteFunction constant(byte r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LFltToByteFunction fltToByteFunc(final @Nonnull LFltToByteFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LFltToByteFunction recursive(final @Nonnull LFunction<LFltToByteFunction, LFltToByteFunction> selfLambda) {
		final LFltToByteFunctionSingle single = new LFltToByteFunctionSingle();
		LFltToByteFunction func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LFltToByteFunctionSingle implements LSingle<LFltToByteFunction>, LFltToByteFunction {
		private LFltToByteFunction target = null;

		@Override
		public byte doApplyAsByteX(float a) throws Throwable {
			return target.doApplyAsByteX(a);
		}

		@Override
		public LFltToByteFunction value() {
			return target;
		}
	}

	@Nonnull
	static LFltToByteFunction fltToByteFuncThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LFltToByteFunction fltToByteFuncThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce(message);
		};
	}

	static byte call(float a, final @Nonnull LFltToByteFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsByte(a);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceByte). */
	@Nonnull
	static LFltToByteFunction safe() {
		return LFltToByteFunction::produceByte;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LFltToByteFunction> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LFltToByteFunction safe(final @Nullable LFltToByteFunction other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LFltToByteFunction> safeSupplier(final @Nullable LSupplier<LFltToByteFunction> supplier) {
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
	default LFltToByteFunction fltToByteFuncComposeFlt(@Nonnull final LFltUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApplyAsByte(before.doApplyAsFlt(v));
	}

	public static LFltToByteFunction composedFlt(@Nonnull final LFltUnaryOperator before, LFltToByteFunction after) {
		return after.fltToByteFuncComposeFlt(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToByteFunction<V> fltToByteFuncCompose(@Nonnull final LToFltFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApplyAsByte(before.doApplyAsFlt(v));
	}

	public static <V> LToByteFunction<V> composed(@Nonnull final LToFltFunction<? super V> before, LFltToByteFunction after) {
		return after.fltToByteFuncCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LFltFunction<V> then(@Nonnull LByteFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApply(this.doApplyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltToByteFunction thenToByte(@Nonnull LByteUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsByte(this.doApplyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltToSrtFunction thenToSrt(@Nonnull LByteToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsSrt(this.doApplyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltToIntFunction thenToInt(@Nonnull LByteToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsInt(this.doApplyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltToLongFunction thenToLong(@Nonnull LByteToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsLong(this.doApplyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltUnaryOperator thenToFlt(@Nonnull LByteToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsFlt(this.doApplyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltToDblFunction thenToDbl(@Nonnull LByteToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsDbl(this.doApplyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltToCharFunction thenToChar(@Nonnull LByteToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsChar(this.doApplyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltPredicate thenToBool(@Nonnull LBytePredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.doTest(this.doApplyAsByte(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LFltToByteFunction nestingFltToByteFunc() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LFltToByteFunction shovingFltToByteFunc() {
		return this;
	}

	// </editor-fold>

	/** Does nothing (LFltToByteFunction) Function */
	public static byte produceByte(float a) {
		return Function4U.defaultByte;
	}

	// MAP: FOR, [SourcePurpose{arg=float a, type=IA}, SourcePurpose{arg=LByteConsumer consumer, type=CONST}]
	default <C0> void forEach(IndexedRead<C0, aFloat> ia, C0 source, LByteConsumer consumer) {
		int size = ia.size(source);
		LOiToFltFunction<Object> oiFunc0 = (LOiToFltFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			float a = oiFunc0.doApplyAsFlt(source, i);
			consumer.doAccept(this.doApplyAsByte(a));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=float a, type=SA}, SourcePurpose{arg=LByteConsumer consumer, type=CONST}]
	default <C0, I0> void iterate(SequentialRead<C0, I0, aFloat> sa, C0 source, LByteConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).doApply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToFltFunction<Object> nextFunc0 = (LToFltFunction) sa.getter();
		while (testFunc0.doTest(iterator0)) {
			float a = nextFunc0.doApplyAsFlt(iterator0);
			consumer.doAccept(this.doApplyAsByte(a));
		}
	}

}
