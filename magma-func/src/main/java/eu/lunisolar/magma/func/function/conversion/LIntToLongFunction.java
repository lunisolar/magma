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
 * Non-throwing functional interface (lambda) LIntToLongFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): int a
 *
 * Co-domain: long
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LIntToLongFunction extends IntToLongFunction, MetaFunction, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LIntToLongFunction: long doApplyAsLong(int a)";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LIntToLongFunction interface should be discouraged.
	 */
	@Override
	@Deprecated
	default long applyAsLong(int a) {
		return this.doApplyAsLong(a);
	}

	// long doApplyAsLong(int a) ;
	default long doApplyAsLong(int a) {
		// return nestingDoApplyAsLong(a);
		try {
			return this.doApplyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doApplyAsLong(int a)
	 */
	long doApplyAsLongX(int a) throws Throwable;

	default long tupleApplyAsLong(LIntSingle args) {
		return doApplyAsLong(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default long handlingDoApplyAsLong(int a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default long tryDoApplyAsLong(int a, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default long tryDoApplyAsLong(int a, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default long tryDoApplyAsLongThen(int a, @Nonnull LToLongFunction<Throwable> handler) {
		try {
			return this.doApplyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApplyAsLong(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default long nestingDoApplyAsLong(int a) {
		try {
			return this.doApplyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default long shovingDoApplyAsLong(int a) {
		try {
			return this.doApplyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static long handlingDoApplyAsLong(int a, LIntToLongFunction func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApplyAsLong(a, handling);
	}

	static long tryDoApplyAsLong(int a, LIntToLongFunction func) {
		return tryDoApplyAsLong(a, func, null);
	}

	static long tryDoApplyAsLong(int a, LIntToLongFunction func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsLong(a, exceptionFactory, newMessage, messageParams);
	}

	static long tryDoApplyAsLong(int a, LIntToLongFunction func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsLong(a, exceptionFactory);
	}

	static long tryDoApplyAsLongThen(int a, LIntToLongFunction func, @Nonnull LToLongFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsLongThen(a, handler);
	}

	default long failSafeDoApplyAsLong(int a, @Nonnull LIntToLongFunction failSafe) {
		try {
			return doApplyAsLong(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApplyAsLong(a);
		}
	}

	static long failSafeDoApplyAsLong(int a, LIntToLongFunction func, @Nonnull LIntToLongFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApplyAsLong(a);
		} else {
			return func.failSafeDoApplyAsLong(a, failSafe);
		}
	}

	static LIntToLongFunction failSafeIntToLongFunc(LIntToLongFunction func, @Nonnull LIntToLongFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeDoApplyAsLong(a, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default long nonNullDoApplyAsLong(int a) {
		return doApplyAsLong(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LIntToLongFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_a, int max_a, LIntToLongFunction func) {
		Null.nonNullArg(func, "func");
		if (min_a <= min_a) {
			for (int a = min_a; a <= max_a; a++) {
				func.doApplyAsLong(a);
			}
		} else {
			for (int a = min_a; a >= max_a; a--) {
				func.doApplyAsLong(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_a, int max_a, LIntToLongFunction func) {
		Null.nonNullArg(func, "func");
		if (min_a <= min_a) {
			for (int a = min_a; a < max_a; a++) {
				func.doApplyAsLong(a);
			}
		} else {
			for (int a = min_a; a > max_a; a--) {
				func.doApplyAsLong(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_a, LIntToLongFunction func) {
		fromTill(0, max_a, func);
	}

	/** Captures arguments but delays the evaluation. */
	default LLongSupplier captureIntToLongFunc(int a) {
		return () -> this.doApplyAsLong(a);
	}

	/** Creates function that always returns the same value. */
	static LIntToLongFunction constant(long r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LIntToLongFunction intToLongFunc(final @Nonnull LIntToLongFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LIntToLongFunction recursive(final @Nonnull LFunction<LIntToLongFunction, LIntToLongFunction> selfLambda) {
		final LIntToLongFunctionSingle single = new LIntToLongFunctionSingle();
		LIntToLongFunction func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LIntToLongFunctionSingle implements LSingle<LIntToLongFunction>, LIntToLongFunction {
		private LIntToLongFunction target = null;

		@Override
		public long doApplyAsLongX(int a) throws Throwable {
			return target.doApplyAsLongX(a);
		}

		@Override
		public LIntToLongFunction value() {
			return target;
		}
	}

	@Nonnull
	static LIntToLongFunction intToLongFuncThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LIntToLongFunction intToLongFuncThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce(message);
		};
	}

	static long call(int a, final @Nonnull LIntToLongFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsLong(a);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static LIntToLongFunction wrap(final IntToLongFunction other) {
		return other::applyAsLong;
	}
	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceLong). */
	@Nonnull
	static LIntToLongFunction safe() {
		return LIntToLongFunction::produceLong;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LIntToLongFunction> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LIntToLongFunction safe(final @Nullable LIntToLongFunction other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LIntToLongFunction> safeSupplier(final @Nullable LSupplier<LIntToLongFunction> supplier) {
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
	default LIntToLongFunction intToLongFuncComposeInt(@Nonnull final LIntUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApplyAsLong(before.doApplyAsInt(v));
	}

	public static LIntToLongFunction composedInt(@Nonnull final LIntUnaryOperator before, LIntToLongFunction after) {
		return after.intToLongFuncComposeInt(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToLongFunction<V> intToLongFuncCompose(@Nonnull final LToIntFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApplyAsLong(before.doApplyAsInt(v));
	}

	public static <V> LToLongFunction<V> composed(@Nonnull final LToIntFunction<? super V> before, LIntToLongFunction after) {
		return after.intToLongFuncCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LIntFunction<V> then(@Nonnull LLongFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApply(this.doApplyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToByteFunction thenToByte(@Nonnull LLongToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsByte(this.doApplyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToSrtFunction thenToSrt(@Nonnull LLongToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsSrt(this.doApplyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntUnaryOperator thenToInt(@Nonnull LLongToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsInt(this.doApplyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToLongFunction thenToLong(@Nonnull LLongUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsLong(this.doApplyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToFltFunction thenToFlt(@Nonnull LLongToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsFlt(this.doApplyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToDblFunction thenToDbl(@Nonnull LLongToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsDbl(this.doApplyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToCharFunction thenToChar(@Nonnull LLongToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsChar(this.doApplyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntPredicate thenToBool(@Nonnull LLongPredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.doTest(this.doApplyAsLong(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LIntToLongFunction nestingIntToLongFunc() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntToLongFunction shovingIntToLongFunc() {
		return this;
	}

	// </editor-fold>

	/** Does nothing (LIntToLongFunction) Function */
	public static long produceLong(int a) {
		return Function4U.defaultLong;
	}

	// MAP: FOR, [SourcePurpose{arg=int a, type=IA}, SourcePurpose{arg=LLongConsumer consumer, type=CONST}]
	default <C0> void forEach(IndexedRead<C0, aInt> ia, C0 source, LLongConsumer consumer) {
		int size = ia.size(source);
		LOiToIntFunction<Object> oiFunc0 = (LOiToIntFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			int a = oiFunc0.doApplyAsInt(source, i);
			consumer.doAccept(this.doApplyAsLong(a));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=int a, type=SA}, SourcePurpose{arg=LLongConsumer consumer, type=CONST}]
	default <C0, I0> void iterate(SequentialRead<C0, I0, aInt> sa, C0 source, LLongConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).doApply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToIntFunction<Object> nextFunc0 = (LToIntFunction) sa.getter();
		while (testFunc0.doTest(iterator0)) {
			int a = nextFunc0.doApplyAsInt(iterator0);
			consumer.doAccept(this.doApplyAsLong(a));
		}
	}

}
