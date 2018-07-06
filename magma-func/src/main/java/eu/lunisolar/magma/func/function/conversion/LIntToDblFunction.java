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
 * Non-throwing functional interface (lambda) LIntToDblFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): int a
 *
 * Co-domain: double
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LIntToDblFunction extends IntToDoubleFunction, MetaFunction, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LIntToDblFunction: double doApplyAsDbl(int a)";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LIntToDblFunction interface should be discouraged.
	 */
	@Override
	@Deprecated
	default double applyAsDouble(int a) {
		return this.doApplyAsDbl(a);
	}

	// double doApplyAsDbl(int a) ;
	default double doApplyAsDbl(int a) {
		// return nestingDoApplyAsDbl(a);
		try {
			return this.doApplyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doApplyAsDbl(int a)
	 */
	double doApplyAsDblX(int a) throws Throwable;

	default double tupleApplyAsDbl(LIntSingle args) {
		return doApplyAsDbl(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default double handlingDoApplyAsDbl(int a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default double tryDoApplyAsDbl(int a, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default double tryDoApplyAsDbl(int a, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default double tryDoApplyAsDblThen(int a, @Nonnull LToDblFunction<Throwable> handler) {
		try {
			return this.doApplyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApplyAsDbl(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default double nestingDoApplyAsDbl(int a) {
		try {
			return this.doApplyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default double shovingDoApplyAsDbl(int a) {
		try {
			return this.doApplyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static double handlingDoApplyAsDbl(int a, LIntToDblFunction func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApplyAsDbl(a, handling);
	}

	static double tryDoApplyAsDbl(int a, LIntToDblFunction func) {
		return tryDoApplyAsDbl(a, func, null);
	}

	static double tryDoApplyAsDbl(int a, LIntToDblFunction func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsDbl(a, exceptionFactory, newMessage, messageParams);
	}

	static double tryDoApplyAsDbl(int a, LIntToDblFunction func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsDbl(a, exceptionFactory);
	}

	static double tryDoApplyAsDblThen(int a, LIntToDblFunction func, @Nonnull LToDblFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsDblThen(a, handler);
	}

	default double failSafeDoApplyAsDbl(int a, @Nonnull LIntToDblFunction failSafe) {
		try {
			return doApplyAsDbl(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApplyAsDbl(a);
		}
	}

	static double failSafeDoApplyAsDbl(int a, LIntToDblFunction func, @Nonnull LIntToDblFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApplyAsDbl(a);
		} else {
			return func.failSafeDoApplyAsDbl(a, failSafe);
		}
	}

	static LIntToDblFunction failSafeIntToDblFunc(LIntToDblFunction func, @Nonnull LIntToDblFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeDoApplyAsDbl(a, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default double nonNullDoApplyAsDbl(int a) {
		return doApplyAsDbl(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LIntToDblFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_a, int max_a, LIntToDblFunction func) {
		Null.nonNullArg(func, "func");
		if (min_a <= min_a) {
			for (int a = min_a; a <= max_a; a++) {
				func.doApplyAsDbl(a);
			}
		} else {
			for (int a = min_a; a >= max_a; a--) {
				func.doApplyAsDbl(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_a, int max_a, LIntToDblFunction func) {
		Null.nonNullArg(func, "func");
		if (min_a <= min_a) {
			for (int a = min_a; a < max_a; a++) {
				func.doApplyAsDbl(a);
			}
		} else {
			for (int a = min_a; a > max_a; a--) {
				func.doApplyAsDbl(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_a, LIntToDblFunction func) {
		fromTill(0, max_a, func);
	}

	/** Captures arguments but delays the evaluation. */
	default LDblSupplier captureIntToDblFunc(int a) {
		return () -> this.doApplyAsDbl(a);
	}

	/** Creates function that always returns the same value. */
	static LIntToDblFunction constant(double r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LIntToDblFunction intToDblFunc(final @Nonnull LIntToDblFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LIntToDblFunction recursive(final @Nonnull LFunction<LIntToDblFunction, LIntToDblFunction> selfLambda) {
		final LIntToDblFunctionSingle single = new LIntToDblFunctionSingle();
		LIntToDblFunction func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LIntToDblFunctionSingle implements LSingle<LIntToDblFunction>, LIntToDblFunction {
		private LIntToDblFunction target = null;

		@Override
		public double doApplyAsDblX(int a) throws Throwable {
			return target.doApplyAsDblX(a);
		}

		@Override
		public LIntToDblFunction value() {
			return target;
		}
	}

	@Nonnull
	static LIntToDblFunction intToDblFuncThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LIntToDblFunction intToDblFuncThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce(message);
		};
	}

	static double call(int a, final @Nonnull LIntToDblFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsDbl(a);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static LIntToDblFunction wrap(final IntToDoubleFunction other) {
		return other::applyAsDouble;
	}
	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceDouble). */
	@Nonnull
	static LIntToDblFunction safe() {
		return LIntToDblFunction::produceDouble;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LIntToDblFunction> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LIntToDblFunction safe(final @Nullable LIntToDblFunction other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LIntToDblFunction> safeSupplier(final @Nullable LSupplier<LIntToDblFunction> supplier) {
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
	default LIntToDblFunction intToDblFuncComposeInt(@Nonnull final LIntUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApplyAsDbl(before.doApplyAsInt(v));
	}

	public static LIntToDblFunction composedInt(@Nonnull final LIntUnaryOperator before, LIntToDblFunction after) {
		return after.intToDblFuncComposeInt(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToDblFunction<V> intToDblFuncCompose(@Nonnull final LToIntFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApplyAsDbl(before.doApplyAsInt(v));
	}

	public static <V> LToDblFunction<V> composed(@Nonnull final LToIntFunction<? super V> before, LIntToDblFunction after) {
		return after.intToDblFuncCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LIntFunction<V> then(@Nonnull LDblFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApply(this.doApplyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToByteFunction thenToByte(@Nonnull LDblToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsByte(this.doApplyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToSrtFunction thenToSrt(@Nonnull LDblToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsSrt(this.doApplyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntUnaryOperator thenToInt(@Nonnull LDblToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsInt(this.doApplyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToLongFunction thenToLong(@Nonnull LDblToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsLong(this.doApplyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToFltFunction thenToFlt(@Nonnull LDblToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsFlt(this.doApplyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToDblFunction thenToDbl(@Nonnull LDblUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsDbl(this.doApplyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToCharFunction thenToChar(@Nonnull LDblToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsChar(this.doApplyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntPredicate thenToBool(@Nonnull LDblPredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.doTest(this.doApplyAsDbl(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LIntToDblFunction nestingIntToDblFunc() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntToDblFunction shovingIntToDblFunc() {
		return this;
	}

	// </editor-fold>

	/** Does nothing (LIntToDblFunction) Function */
	public static double produceDouble(int a) {
		return Function4U.defaultDouble;
	}

	// MAP: FOR, [SourcePurpose{arg=int a, type=IA}, SourcePurpose{arg=LDblConsumer consumer, type=CONST}]
	default <C0> void forEach(IndexedRead<C0, aInt> ia, C0 source, LDblConsumer consumer) {
		int size = ia.size(source);
		LOiToIntFunction<Object> oiFunc0 = (LOiToIntFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			int a = oiFunc0.doApplyAsInt(source, i);
			consumer.doAccept(this.doApplyAsDbl(a));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=int a, type=SA}, SourcePurpose{arg=LDblConsumer consumer, type=CONST}]
	default <C0, I0> void iterate(SequentialRead<C0, I0, aInt> sa, C0 source, LDblConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).doApply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToIntFunction<Object> nextFunc0 = (LToIntFunction) sa.getter();
		while (testFunc0.doTest(iterator0)) {
			int a = nextFunc0.doApplyAsInt(iterator0);
			consumer.doAccept(this.doApplyAsDbl(a));
		}
	}

}
