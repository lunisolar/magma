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
 * Non-throwing functional interface (lambda) LSrtToIntFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): short a
 *
 * Co-domain: int
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LSrtToIntFunction extends MetaFunction, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LSrtToIntFunction: int doApplyAsInt(short a)";

	// int doApplyAsInt(short a) ;
	default int doApplyAsInt(short a) {
		// return nestingDoApplyAsInt(a);
		try {
			return this.doApplyAsIntX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doApplyAsInt(short a)
	 */
	int doApplyAsIntX(short a) throws Throwable;

	default int tupleApplyAsInt(LSrtSingle args) {
		return doApplyAsInt(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default int handlingDoApplyAsInt(short a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyAsIntX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default int tryDoApplyAsInt(short a, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyAsIntX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default int tryDoApplyAsInt(short a, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyAsIntX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default int tryDoApplyAsIntThen(short a, @Nonnull LToIntFunction<Throwable> handler) {
		try {
			return this.doApplyAsIntX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApplyAsInt(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default int nestingDoApplyAsInt(short a) {
		try {
			return this.doApplyAsIntX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default int shovingDoApplyAsInt(short a) {
		try {
			return this.doApplyAsIntX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static int handlingDoApplyAsInt(short a, LSrtToIntFunction func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApplyAsInt(a, handling);
	}

	static int tryDoApplyAsInt(short a, LSrtToIntFunction func) {
		return tryDoApplyAsInt(a, func, null);
	}

	static int tryDoApplyAsInt(short a, LSrtToIntFunction func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsInt(a, exceptionFactory, newMessage, messageParams);
	}

	static int tryDoApplyAsInt(short a, LSrtToIntFunction func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsInt(a, exceptionFactory);
	}

	static int tryDoApplyAsIntThen(short a, LSrtToIntFunction func, @Nonnull LToIntFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsIntThen(a, handler);
	}

	default int failSafeDoApplyAsInt(short a, @Nonnull LSrtToIntFunction failSafe) {
		try {
			return doApplyAsInt(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApplyAsInt(a);
		}
	}

	static int failSafeDoApplyAsInt(short a, LSrtToIntFunction func, @Nonnull LSrtToIntFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApplyAsInt(a);
		} else {
			return func.failSafeDoApplyAsInt(a, failSafe);
		}
	}

	static LSrtToIntFunction failSafeSrtToIntFunc(LSrtToIntFunction func, @Nonnull LSrtToIntFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeDoApplyAsInt(a, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default int nonNullDoApplyAsInt(short a) {
		return doApplyAsInt(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LSrtToIntFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, short a, LSrtToIntFunction func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.doApplyAsInt(a);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.doApplyAsInt(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, short a, LSrtToIntFunction func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i < max_i; i++) {
				func.doApplyAsInt(a);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.doApplyAsInt(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, short a, LSrtToIntFunction func) {
		fromTill(0, max_i, a, func);
	}

	/** Captures arguments but delays the evaluation. */
	default LIntSupplier captureSrtToIntFunc(short a) {
		return () -> this.doApplyAsInt(a);
	}

	/** Creates function that always returns the same value. */
	static LSrtToIntFunction constant(int r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LSrtToIntFunction srtToIntFunc(final @Nonnull LSrtToIntFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LSrtToIntFunction recursive(final @Nonnull LFunction<LSrtToIntFunction, LSrtToIntFunction> selfLambda) {
		final LSrtToIntFunctionSingle single = new LSrtToIntFunctionSingle();
		LSrtToIntFunction func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LSrtToIntFunctionSingle implements LSingle<LSrtToIntFunction>, LSrtToIntFunction {
		private LSrtToIntFunction target = null;

		@Override
		public int doApplyAsIntX(short a) throws Throwable {
			return target.doApplyAsIntX(a);
		}

		@Override
		public LSrtToIntFunction value() {
			return target;
		}
	}

	@Nonnull
	static LSrtToIntFunction srtToIntFuncThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LSrtToIntFunction srtToIntFuncThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce(message);
		};
	}

	static int call(short a, final @Nonnull LSrtToIntFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsInt(a);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceInt). */
	@Nonnull
	static LSrtToIntFunction safe() {
		return LSrtToIntFunction::produceInt;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LSrtToIntFunction> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LSrtToIntFunction safe(final @Nullable LSrtToIntFunction other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LSrtToIntFunction> safeSupplier(final @Nullable LSupplier<LSrtToIntFunction> supplier) {
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
	default LSrtToIntFunction srtToIntFuncComposeSrt(@Nonnull final LSrtUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApplyAsInt(before.doApplyAsSrt(v));
	}

	public static LSrtToIntFunction composedSrt(@Nonnull final LSrtUnaryOperator before, LSrtToIntFunction after) {
		return after.srtToIntFuncComposeSrt(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToIntFunction<V> srtToIntFuncCompose(@Nonnull final LToSrtFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApplyAsInt(before.doApplyAsSrt(v));
	}

	public static <V> LToIntFunction<V> composed(@Nonnull final LToSrtFunction<? super V> before, LSrtToIntFunction after) {
		return after.srtToIntFuncCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LSrtFunction<V> then(@Nonnull LIntFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApply(this.doApplyAsInt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtToByteFunction thenToByte(@Nonnull LIntToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsByte(this.doApplyAsInt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtUnaryOperator thenToSrt(@Nonnull LIntToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsSrt(this.doApplyAsInt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtToIntFunction thenToInt(@Nonnull LIntUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsInt(this.doApplyAsInt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtToLongFunction thenToLong(@Nonnull LIntToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsLong(this.doApplyAsInt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtToFltFunction thenToFlt(@Nonnull LIntToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsFlt(this.doApplyAsInt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtToDblFunction thenToDbl(@Nonnull LIntToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsDbl(this.doApplyAsInt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtToCharFunction thenToChar(@Nonnull LIntToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsChar(this.doApplyAsInt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtPredicate thenToBool(@Nonnull LIntPredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.doTest(this.doApplyAsInt(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LSrtToIntFunction nestingSrtToIntFunc() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LSrtToIntFunction shovingSrtToIntFunc() {
		return this;
	}

	// </editor-fold>

	/** Does nothing (LSrtToIntFunction) Function */
	public static int produceInt(short a) {
		return Function4U.defaultInteger;
	}

	// MAP: FOR, [SourcePurpose{arg=short a, type=IA}, SourcePurpose{arg=LIntConsumer consumer, type=CONST}]
	default <C0> void forEach(IndexedRead<C0, aShort> ia, C0 source, LIntConsumer consumer) {
		int size = ia.size(source);
		LOiToSrtFunction<Object> oiFunc0 = (LOiToSrtFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			short a = oiFunc0.doApplyAsSrt(source, i);
			consumer.doAccept(this.doApplyAsInt(a));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=short a, type=SA}, SourcePurpose{arg=LIntConsumer consumer, type=CONST}]
	default <C0, I0> void iterate(SequentialRead<C0, I0, aShort> sa, C0 source, LIntConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).doApply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToSrtFunction<Object> nextFunc0 = (LToSrtFunction) sa.getter();
		while (testFunc0.doTest(iterator0)) {
			short a = nextFunc0.doApplyAsSrt(iterator0);
			consumer.doAccept(this.doApplyAsInt(a));
		}
	}

}
