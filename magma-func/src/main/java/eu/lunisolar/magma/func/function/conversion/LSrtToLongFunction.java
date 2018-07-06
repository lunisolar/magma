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
 * Non-throwing functional interface (lambda) LSrtToLongFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): short a
 *
 * Co-domain: long
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LSrtToLongFunction extends MetaFunction, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LSrtToLongFunction: long doApplyAsLong(short a)";

	// long doApplyAsLong(short a) ;
	default long doApplyAsLong(short a) {
		// return nestingDoApplyAsLong(a);
		try {
			return this.doApplyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doApplyAsLong(short a)
	 */
	long doApplyAsLongX(short a) throws Throwable;

	default long tupleApplyAsLong(LSrtSingle args) {
		return doApplyAsLong(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default long handlingDoApplyAsLong(short a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default long tryDoApplyAsLong(short a, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default long tryDoApplyAsLong(short a, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default long tryDoApplyAsLongThen(short a, @Nonnull LToLongFunction<Throwable> handler) {
		try {
			return this.doApplyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApplyAsLong(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default long nestingDoApplyAsLong(short a) {
		try {
			return this.doApplyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default long shovingDoApplyAsLong(short a) {
		try {
			return this.doApplyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static long handlingDoApplyAsLong(short a, LSrtToLongFunction func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApplyAsLong(a, handling);
	}

	static long tryDoApplyAsLong(short a, LSrtToLongFunction func) {
		return tryDoApplyAsLong(a, func, null);
	}

	static long tryDoApplyAsLong(short a, LSrtToLongFunction func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsLong(a, exceptionFactory, newMessage, messageParams);
	}

	static long tryDoApplyAsLong(short a, LSrtToLongFunction func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsLong(a, exceptionFactory);
	}

	static long tryDoApplyAsLongThen(short a, LSrtToLongFunction func, @Nonnull LToLongFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsLongThen(a, handler);
	}

	default long failSafeDoApplyAsLong(short a, @Nonnull LSrtToLongFunction failSafe) {
		try {
			return doApplyAsLong(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApplyAsLong(a);
		}
	}

	static long failSafeDoApplyAsLong(short a, LSrtToLongFunction func, @Nonnull LSrtToLongFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApplyAsLong(a);
		} else {
			return func.failSafeDoApplyAsLong(a, failSafe);
		}
	}

	static LSrtToLongFunction failSafeSrtToLongFunc(LSrtToLongFunction func, @Nonnull LSrtToLongFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeDoApplyAsLong(a, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default long nonNullDoApplyAsLong(short a) {
		return doApplyAsLong(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LSrtToLongFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, short a, LSrtToLongFunction func) {
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
	public static void fromTill(int min_i, int max_i, short a, LSrtToLongFunction func) {
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
	public static void times(int max_i, short a, LSrtToLongFunction func) {
		fromTill(0, max_i, a, func);
	}

	/** Captures arguments but delays the evaluation. */
	default LLongSupplier captureSrtToLongFunc(short a) {
		return () -> this.doApplyAsLong(a);
	}

	/** Creates function that always returns the same value. */
	static LSrtToLongFunction constant(long r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LSrtToLongFunction srtToLongFunc(final @Nonnull LSrtToLongFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LSrtToLongFunction recursive(final @Nonnull LFunction<LSrtToLongFunction, LSrtToLongFunction> selfLambda) {
		final LSrtToLongFunctionSingle single = new LSrtToLongFunctionSingle();
		LSrtToLongFunction func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LSrtToLongFunctionSingle implements LSingle<LSrtToLongFunction>, LSrtToLongFunction {
		private LSrtToLongFunction target = null;

		@Override
		public long doApplyAsLongX(short a) throws Throwable {
			return target.doApplyAsLongX(a);
		}

		@Override
		public LSrtToLongFunction value() {
			return target;
		}
	}

	@Nonnull
	static LSrtToLongFunction srtToLongFuncThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LSrtToLongFunction srtToLongFuncThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce(message);
		};
	}

	static long call(short a, final @Nonnull LSrtToLongFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsLong(a);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceLong). */
	@Nonnull
	static LSrtToLongFunction safe() {
		return LSrtToLongFunction::produceLong;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LSrtToLongFunction> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LSrtToLongFunction safe(final @Nullable LSrtToLongFunction other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LSrtToLongFunction> safeSupplier(final @Nullable LSupplier<LSrtToLongFunction> supplier) {
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
	default LSrtToLongFunction srtToLongFuncComposeSrt(@Nonnull final LSrtUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApplyAsLong(before.doApplyAsSrt(v));
	}

	public static LSrtToLongFunction composedSrt(@Nonnull final LSrtUnaryOperator before, LSrtToLongFunction after) {
		return after.srtToLongFuncComposeSrt(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToLongFunction<V> srtToLongFuncCompose(@Nonnull final LToSrtFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApplyAsLong(before.doApplyAsSrt(v));
	}

	public static <V> LToLongFunction<V> composed(@Nonnull final LToSrtFunction<? super V> before, LSrtToLongFunction after) {
		return after.srtToLongFuncCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LSrtFunction<V> then(@Nonnull LLongFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApply(this.doApplyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtToByteFunction thenToByte(@Nonnull LLongToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsByte(this.doApplyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtUnaryOperator thenToSrt(@Nonnull LLongToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsSrt(this.doApplyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtToIntFunction thenToInt(@Nonnull LLongToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsInt(this.doApplyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtToLongFunction thenToLong(@Nonnull LLongUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsLong(this.doApplyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtToFltFunction thenToFlt(@Nonnull LLongToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsFlt(this.doApplyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtToDblFunction thenToDbl(@Nonnull LLongToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsDbl(this.doApplyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtToCharFunction thenToChar(@Nonnull LLongToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsChar(this.doApplyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtPredicate thenToBool(@Nonnull LLongPredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.doTest(this.doApplyAsLong(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LSrtToLongFunction nestingSrtToLongFunc() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LSrtToLongFunction shovingSrtToLongFunc() {
		return this;
	}

	// </editor-fold>

	/** Does nothing (LSrtToLongFunction) Function */
	public static long produceLong(short a) {
		return Function4U.defaultLong;
	}

	// MAP: FOR, [SourcePurpose{arg=short a, type=IA}, SourcePurpose{arg=LLongConsumer consumer, type=CONST}]
	default <C0> void forEach(IndexedRead<C0, aShort> ia, C0 source, LLongConsumer consumer) {
		int size = ia.size(source);
		LOiToSrtFunction<Object> oiFunc0 = (LOiToSrtFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			short a = oiFunc0.doApplyAsSrt(source, i);
			consumer.doAccept(this.doApplyAsLong(a));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=short a, type=SA}, SourcePurpose{arg=LLongConsumer consumer, type=CONST}]
	default <C0, I0> void iterate(SequentialRead<C0, I0, aShort> sa, C0 source, LLongConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).doApply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToSrtFunction<Object> nextFunc0 = (LToSrtFunction) sa.getter();
		while (testFunc0.doTest(iterator0)) {
			short a = nextFunc0.doApplyAsSrt(iterator0);
			consumer.doAccept(this.doApplyAsLong(a));
		}
	}

}
