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
 * Non-throwing functional interface (lambda) LLongToIntFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): long a
 *
 * Co-domain: int
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LLongToIntFunction extends LongToIntFunction, MetaFunction, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LLongToIntFunction: int doApplyAsInt(long a)";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LLongToIntFunction interface should be discouraged.
	 */
	@Override
	@Deprecated
	default int applyAsInt(long a) {
		return this.doApplyAsInt(a);
	}

	// int doApplyAsInt(long a) ;
	default int doApplyAsInt(long a) {
		// return nestingDoApplyAsInt(a);
		try {
			return this.doApplyAsIntX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doApplyAsInt(long a)
	 */
	int doApplyAsIntX(long a) throws Throwable;

	default int tupleApplyAsInt(LLongSingle args) {
		return doApplyAsInt(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default int handlingDoApplyAsInt(long a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyAsIntX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default int tryDoApplyAsInt(long a, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyAsIntX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default int tryDoApplyAsInt(long a, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyAsIntX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default int tryDoApplyAsIntThen(long a, @Nonnull LToIntFunction<Throwable> handler) {
		try {
			return this.doApplyAsIntX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApplyAsInt(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default int nestingDoApplyAsInt(long a) {
		try {
			return this.doApplyAsIntX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default int shovingDoApplyAsInt(long a) {
		try {
			return this.doApplyAsIntX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static int handlingDoApplyAsInt(long a, LLongToIntFunction func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApplyAsInt(a, handling);
	}

	static int tryDoApplyAsInt(long a, LLongToIntFunction func) {
		return tryDoApplyAsInt(a, func, null);
	}

	static int tryDoApplyAsInt(long a, LLongToIntFunction func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsInt(a, exceptionFactory, newMessage, messageParams);
	}

	static int tryDoApplyAsInt(long a, LLongToIntFunction func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsInt(a, exceptionFactory);
	}

	static int tryDoApplyAsIntThen(long a, LLongToIntFunction func, @Nonnull LToIntFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsIntThen(a, handler);
	}

	default int failSafeDoApplyAsInt(long a, @Nonnull LLongToIntFunction failSafe) {
		try {
			return doApplyAsInt(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApplyAsInt(a);
		}
	}

	static int failSafeDoApplyAsInt(long a, LLongToIntFunction func, @Nonnull LLongToIntFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApplyAsInt(a);
		} else {
			return func.failSafeDoApplyAsInt(a, failSafe);
		}
	}

	static LLongToIntFunction failSafeLongToIntFunc(LLongToIntFunction func, @Nonnull LLongToIntFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeDoApplyAsInt(a, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default int nonNullDoApplyAsInt(long a) {
		return doApplyAsInt(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LLongToIntFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(long min_a, long max_a, LLongToIntFunction func) {
		Null.nonNullArg(func, "func");
		if (min_a <= min_a) {
			for (long a = min_a; a <= max_a; a++) {
				func.doApplyAsInt(a);
			}
		} else {
			for (long a = min_a; a >= max_a; a--) {
				func.doApplyAsInt(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(long min_a, long max_a, LLongToIntFunction func) {
		Null.nonNullArg(func, "func");
		if (min_a <= min_a) {
			for (long a = min_a; a < max_a; a++) {
				func.doApplyAsInt(a);
			}
		} else {
			for (long a = min_a; a > max_a; a--) {
				func.doApplyAsInt(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(long max_a, LLongToIntFunction func) {
		fromTill(0, max_a, func);
	}

	/** Captures arguments but delays the evaluation. */
	default LIntSupplier captureLongToIntFunc(long a) {
		return () -> this.doApplyAsInt(a);
	}

	/** Creates function that always returns the same value. */
	static LLongToIntFunction constant(int r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LLongToIntFunction longToIntFunc(final @Nonnull LLongToIntFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LLongToIntFunction recursive(final @Nonnull LFunction<LLongToIntFunction, LLongToIntFunction> selfLambda) {
		final LLongToIntFunctionSingle single = new LLongToIntFunctionSingle();
		LLongToIntFunction func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LLongToIntFunctionSingle implements LSingle<LLongToIntFunction>, LLongToIntFunction {
		private LLongToIntFunction target = null;

		@Override
		public int doApplyAsIntX(long a) throws Throwable {
			return target.doApplyAsIntX(a);
		}

		@Override
		public LLongToIntFunction value() {
			return target;
		}
	}

	@Nonnull
	static LLongToIntFunction longToIntFuncThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LLongToIntFunction longToIntFuncThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce(message);
		};
	}

	static int call(long a, final @Nonnull LLongToIntFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsInt(a);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static LLongToIntFunction wrap(final LongToIntFunction other) {
		return other::applyAsInt;
	}
	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceInt). */
	@Nonnull
	static LLongToIntFunction safe() {
		return LLongToIntFunction::produceInt;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LLongToIntFunction> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LLongToIntFunction safe(final @Nullable LLongToIntFunction other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LLongToIntFunction> safeSupplier(final @Nullable LSupplier<LLongToIntFunction> supplier) {
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
	default LLongToIntFunction longToIntFuncComposeLong(@Nonnull final LLongUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApplyAsInt(before.doApplyAsLong(v));
	}

	public static LLongToIntFunction composedLong(@Nonnull final LLongUnaryOperator before, LLongToIntFunction after) {
		return after.longToIntFuncComposeLong(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToIntFunction<V> longToIntFuncCompose(@Nonnull final LToLongFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApplyAsInt(before.doApplyAsLong(v));
	}

	public static <V> LToIntFunction<V> composed(@Nonnull final LToLongFunction<? super V> before, LLongToIntFunction after) {
		return after.longToIntFuncCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LLongFunction<V> then(@Nonnull LIntFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApply(this.doApplyAsInt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToByteFunction thenToByte(@Nonnull LIntToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsByte(this.doApplyAsInt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToSrtFunction thenToSrt(@Nonnull LIntToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsSrt(this.doApplyAsInt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToIntFunction thenToInt(@Nonnull LIntUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsInt(this.doApplyAsInt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongUnaryOperator thenToLong(@Nonnull LIntToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsLong(this.doApplyAsInt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToFltFunction thenToFlt(@Nonnull LIntToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsFlt(this.doApplyAsInt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToDblFunction thenToDbl(@Nonnull LIntToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsDbl(this.doApplyAsInt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToCharFunction thenToChar(@Nonnull LIntToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsChar(this.doApplyAsInt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongPredicate thenToBool(@Nonnull LIntPredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.doTest(this.doApplyAsInt(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LLongToIntFunction nestingLongToIntFunc() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLongToIntFunction shovingLongToIntFunc() {
		return this;
	}

	// </editor-fold>

	/** Does nothing (LLongToIntFunction) Function */
	public static int produceInt(long a) {
		return Function4U.defaultInteger;
	}

	// MAP: FOR, [SourcePurpose{arg=long a, type=IA}, SourcePurpose{arg=LIntConsumer consumer, type=CONST}]
	default <C0> void forEach(IndexedRead<C0, aLong> ia, C0 source, LIntConsumer consumer) {
		int size = ia.size(source);
		LOiToLongFunction<Object> oiFunc0 = (LOiToLongFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			long a = oiFunc0.doApplyAsLong(source, i);
			consumer.doAccept(this.doApplyAsInt(a));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=long a, type=SA}, SourcePurpose{arg=LIntConsumer consumer, type=CONST}]
	default <C0, I0> void iterate(SequentialRead<C0, I0, aLong> sa, C0 source, LIntConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).doApply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToLongFunction<Object> nextFunc0 = (LToLongFunction) sa.getter();
		while (testFunc0.doTest(iterator0)) {
			long a = nextFunc0.doApplyAsLong(iterator0);
			consumer.doAccept(this.doApplyAsInt(a));
		}
	}

}
