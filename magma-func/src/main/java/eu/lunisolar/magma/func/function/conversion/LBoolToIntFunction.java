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
 * Non-throwing functional interface (lambda) LBoolToIntFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): boolean a
 *
 * Co-domain: int
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBoolToIntFunction extends MetaFunction, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LBoolToIntFunction: int doApplyAsInt(boolean a)";

	// int doApplyAsInt(boolean a) ;
	default int doApplyAsInt(boolean a) {
		// return nestingDoApplyAsInt(a);
		try {
			return this.doApplyAsIntX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doApplyAsInt(boolean a)
	 */
	int doApplyAsIntX(boolean a) throws Throwable;

	default int tupleApplyAsInt(LBoolSingle args) {
		return doApplyAsInt(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default int handlingDoApplyAsInt(boolean a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyAsIntX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default int tryDoApplyAsInt(boolean a, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyAsIntX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default int tryDoApplyAsInt(boolean a, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyAsIntX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default int tryDoApplyAsIntThen(boolean a, @Nonnull LToIntFunction<Throwable> handler) {
		try {
			return this.doApplyAsIntX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApplyAsInt(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default int nestingDoApplyAsInt(boolean a) {
		try {
			return this.doApplyAsIntX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default int shovingDoApplyAsInt(boolean a) {
		try {
			return this.doApplyAsIntX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static int handlingDoApplyAsInt(boolean a, LBoolToIntFunction func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApplyAsInt(a, handling);
	}

	static int tryDoApplyAsInt(boolean a, LBoolToIntFunction func) {
		return tryDoApplyAsInt(a, func, null);
	}

	static int tryDoApplyAsInt(boolean a, LBoolToIntFunction func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsInt(a, exceptionFactory, newMessage, messageParams);
	}

	static int tryDoApplyAsInt(boolean a, LBoolToIntFunction func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsInt(a, exceptionFactory);
	}

	static int tryDoApplyAsIntThen(boolean a, LBoolToIntFunction func, @Nonnull LToIntFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsIntThen(a, handler);
	}

	default int failSafeDoApplyAsInt(boolean a, @Nonnull LBoolToIntFunction failSafe) {
		try {
			return doApplyAsInt(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApplyAsInt(a);
		}
	}

	static int failSafeDoApplyAsInt(boolean a, LBoolToIntFunction func, @Nonnull LBoolToIntFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApplyAsInt(a);
		} else {
			return func.failSafeDoApplyAsInt(a, failSafe);
		}
	}

	static LBoolToIntFunction failSafeBoolToIntFunc(LBoolToIntFunction func, @Nonnull LBoolToIntFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeDoApplyAsInt(a, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default int nonNullDoApplyAsInt(boolean a) {
		return doApplyAsInt(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBoolToIntFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, boolean a, LBoolToIntFunction func) {
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
	public static void fromTill(int min_i, int max_i, boolean a, LBoolToIntFunction func) {
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
	public static void times(int max_i, boolean a, LBoolToIntFunction func) {
		fromTill(0, max_i, a, func);
	}

	/** Captures arguments but delays the evaluation. */
	default LIntSupplier captureBoolToIntFunc(boolean a) {
		return () -> this.doApplyAsInt(a);
	}

	/** Creates function that always returns the same value. */
	static LBoolToIntFunction constant(int r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LBoolToIntFunction boolToIntFunc(final @Nonnull LBoolToIntFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LBoolToIntFunction recursive(final @Nonnull LFunction<LBoolToIntFunction, LBoolToIntFunction> selfLambda) {
		final LBoolToIntFunctionSingle single = new LBoolToIntFunctionSingle();
		LBoolToIntFunction func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LBoolToIntFunctionSingle implements LSingle<LBoolToIntFunction>, LBoolToIntFunction {
		private LBoolToIntFunction target = null;

		@Override
		public int doApplyAsIntX(boolean a) throws Throwable {
			return target.doApplyAsIntX(a);
		}

		@Override
		public LBoolToIntFunction value() {
			return target;
		}
	}

	@Nonnull
	static LBoolToIntFunction boolToIntFuncThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LBoolToIntFunction boolToIntFuncThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce(message);
		};
	}

	static int call(boolean a, final @Nonnull LBoolToIntFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsInt(a);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceInt). */
	@Nonnull
	static LBoolToIntFunction safe() {
		return LBoolToIntFunction::produceInt;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LBoolToIntFunction> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LBoolToIntFunction safe(final @Nullable LBoolToIntFunction other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LBoolToIntFunction> safeSupplier(final @Nullable LSupplier<LBoolToIntFunction> supplier) {
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
	default LBoolToIntFunction boolToIntFuncComposeBool(@Nonnull final LLogicalOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApplyAsInt(before.doApply(v));
	}

	public static LBoolToIntFunction composedBool(@Nonnull final LLogicalOperator before, LBoolToIntFunction after) {
		return after.boolToIntFuncComposeBool(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToIntFunction<V> boolToIntFuncCompose(@Nonnull final LPredicate<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApplyAsInt(before.doTest(v));
	}

	public static <V> LToIntFunction<V> composed(@Nonnull final LPredicate<? super V> before, LBoolToIntFunction after) {
		return after.boolToIntFuncCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBoolFunction<V> then(@Nonnull LIntFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApply(this.doApplyAsInt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToByteFunction thenToByte(@Nonnull LIntToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsByte(this.doApplyAsInt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToSrtFunction thenToSrt(@Nonnull LIntToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsSrt(this.doApplyAsInt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToIntFunction thenToInt(@Nonnull LIntUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsInt(this.doApplyAsInt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToLongFunction thenToLong(@Nonnull LIntToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsLong(this.doApplyAsInt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToFltFunction thenToFlt(@Nonnull LIntToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsFlt(this.doApplyAsInt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToDblFunction thenToDbl(@Nonnull LIntToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsDbl(this.doApplyAsInt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToCharFunction thenToChar(@Nonnull LIntToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsChar(this.doApplyAsInt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLogicalOperator thenToBool(@Nonnull LIntPredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.doTest(this.doApplyAsInt(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBoolToIntFunction nestingBoolToIntFunc() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBoolToIntFunction shovingBoolToIntFunc() {
		return this;
	}

	// </editor-fold>

	/** Does nothing (LBoolToIntFunction) Function */
	public static int produceInt(boolean a) {
		return Function4U.defaultInteger;
	}

	// MAP: FOR, [SourcePurpose{arg=boolean a, type=IA}, SourcePurpose{arg=LIntConsumer consumer, type=CONST}]
	default <C0> void forEach(IndexedRead<C0, aBool> ia, C0 source, LIntConsumer consumer) {
		int size = ia.size(source);
		LObjIntPredicate<Object> oiFunc0 = (LObjIntPredicate) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			boolean a = oiFunc0.doTest(source, i);
			consumer.doAccept(this.doApplyAsInt(a));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=boolean a, type=SA}, SourcePurpose{arg=LIntConsumer consumer, type=CONST}]
	default <C0, I0> void iterate(SequentialRead<C0, I0, aBool> sa, C0 source, LIntConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).doApply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LPredicate<Object> nextFunc0 = (LPredicate) sa.getter();
		while (testFunc0.doTest(iterator0)) {
			boolean a = nextFunc0.doTest(iterator0);
			consumer.doAccept(this.doApplyAsInt(a));
		}
	}

}
