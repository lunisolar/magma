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
 * Non-throwing functional interface (lambda) LByteToDblFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): byte a
 *
 * Co-domain: double
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LByteToDblFunction extends MetaFunction, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LByteToDblFunction: double doApplyAsDbl(byte a)";

	// double doApplyAsDbl(byte a) ;
	default double doApplyAsDbl(byte a) {
		// return nestingDoApplyAsDbl(a);
		try {
			return this.doApplyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doApplyAsDbl(byte a)
	 */
	double doApplyAsDblX(byte a) throws Throwable;

	default double tupleApplyAsDbl(LByteSingle args) {
		return doApplyAsDbl(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default double handlingDoApplyAsDbl(byte a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default double tryDoApplyAsDbl(byte a, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default double tryDoApplyAsDbl(byte a, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default double tryDoApplyAsDblThen(byte a, @Nonnull LToDblFunction<Throwable> handler) {
		try {
			return this.doApplyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApplyAsDbl(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default double nestingDoApplyAsDbl(byte a) {
		try {
			return this.doApplyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default double shovingDoApplyAsDbl(byte a) {
		try {
			return this.doApplyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static double handlingDoApplyAsDbl(byte a, LByteToDblFunction func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApplyAsDbl(a, handling);
	}

	static double tryDoApplyAsDbl(byte a, LByteToDblFunction func) {
		return tryDoApplyAsDbl(a, func, null);
	}

	static double tryDoApplyAsDbl(byte a, LByteToDblFunction func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsDbl(a, exceptionFactory, newMessage, messageParams);
	}

	static double tryDoApplyAsDbl(byte a, LByteToDblFunction func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsDbl(a, exceptionFactory);
	}

	static double tryDoApplyAsDblThen(byte a, LByteToDblFunction func, @Nonnull LToDblFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsDblThen(a, handler);
	}

	default double failSafeDoApplyAsDbl(byte a, @Nonnull LByteToDblFunction failSafe) {
		try {
			return doApplyAsDbl(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApplyAsDbl(a);
		}
	}

	static double failSafeDoApplyAsDbl(byte a, LByteToDblFunction func, @Nonnull LByteToDblFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApplyAsDbl(a);
		} else {
			return func.failSafeDoApplyAsDbl(a, failSafe);
		}
	}

	static LByteToDblFunction failSafeByteToDblFunc(LByteToDblFunction func, @Nonnull LByteToDblFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeDoApplyAsDbl(a, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default double nonNullDoApplyAsDbl(byte a) {
		return doApplyAsDbl(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LByteToDblFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, byte a, LByteToDblFunction func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.doApplyAsDbl(a);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.doApplyAsDbl(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, byte a, LByteToDblFunction func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i < max_i; i++) {
				func.doApplyAsDbl(a);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.doApplyAsDbl(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, byte a, LByteToDblFunction func) {
		fromTill(0, max_i, a, func);
	}

	/** Captures arguments but delays the evaluation. */
	default LDblSupplier captureByteToDblFunc(byte a) {
		return () -> this.doApplyAsDbl(a);
	}

	/** Creates function that always returns the same value. */
	static LByteToDblFunction constant(double r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LByteToDblFunction byteToDblFunc(final @Nonnull LByteToDblFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LByteToDblFunction recursive(final @Nonnull LFunction<LByteToDblFunction, LByteToDblFunction> selfLambda) {
		final LByteToDblFunctionSingle single = new LByteToDblFunctionSingle();
		LByteToDblFunction func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LByteToDblFunctionSingle implements LSingle<LByteToDblFunction>, LByteToDblFunction {
		private LByteToDblFunction target = null;

		@Override
		public double doApplyAsDblX(byte a) throws Throwable {
			return target.doApplyAsDblX(a);
		}

		@Override
		public LByteToDblFunction value() {
			return target;
		}
	}

	@Nonnull
	static LByteToDblFunction byteToDblFuncThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LByteToDblFunction byteToDblFuncThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce(message);
		};
	}

	static double call(byte a, final @Nonnull LByteToDblFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsDbl(a);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceDouble). */
	@Nonnull
	static LByteToDblFunction safe() {
		return LByteToDblFunction::produceDouble;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LByteToDblFunction> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LByteToDblFunction safe(final @Nullable LByteToDblFunction other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LByteToDblFunction> safeSupplier(final @Nullable LSupplier<LByteToDblFunction> supplier) {
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
	default LByteToDblFunction byteToDblFuncComposeByte(@Nonnull final LByteUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApplyAsDbl(before.doApplyAsByte(v));
	}

	public static LByteToDblFunction composedByte(@Nonnull final LByteUnaryOperator before, LByteToDblFunction after) {
		return after.byteToDblFuncComposeByte(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToDblFunction<V> byteToDblFuncCompose(@Nonnull final LToByteFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApplyAsDbl(before.doApplyAsByte(v));
	}

	public static <V> LToDblFunction<V> composed(@Nonnull final LToByteFunction<? super V> before, LByteToDblFunction after) {
		return after.byteToDblFuncCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LByteFunction<V> then(@Nonnull LDblFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApply(this.doApplyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteUnaryOperator thenToByte(@Nonnull LDblToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsByte(this.doApplyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToSrtFunction thenToSrt(@Nonnull LDblToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsSrt(this.doApplyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToIntFunction thenToInt(@Nonnull LDblToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsInt(this.doApplyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToLongFunction thenToLong(@Nonnull LDblToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsLong(this.doApplyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToFltFunction thenToFlt(@Nonnull LDblToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsFlt(this.doApplyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToDblFunction thenToDbl(@Nonnull LDblUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsDbl(this.doApplyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToCharFunction thenToChar(@Nonnull LDblToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsChar(this.doApplyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBytePredicate thenToBool(@Nonnull LDblPredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.doTest(this.doApplyAsDbl(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LByteToDblFunction nestingByteToDblFunc() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LByteToDblFunction shovingByteToDblFunc() {
		return this;
	}

	// </editor-fold>

	/** Does nothing (LByteToDblFunction) Function */
	public static double produceDouble(byte a) {
		return Function4U.defaultDouble;
	}

	// MAP: FOR, [SourcePurpose{arg=byte a, type=IA}, SourcePurpose{arg=LDblConsumer consumer, type=CONST}]
	default <C0> void forEach(IndexedRead<C0, aByte> ia, C0 source, LDblConsumer consumer) {
		int size = ia.size(source);
		LOiToByteFunction<Object> oiFunc0 = (LOiToByteFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			byte a = oiFunc0.doApplyAsByte(source, i);
			consumer.doAccept(this.doApplyAsDbl(a));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=byte a, type=SA}, SourcePurpose{arg=LDblConsumer consumer, type=CONST}]
	default <C0, I0> void iterate(SequentialRead<C0, I0, aByte> sa, C0 source, LDblConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).doApply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToByteFunction<Object> nextFunc0 = (LToByteFunction) sa.getter();
		while (testFunc0.doTest(iterator0)) {
			byte a = nextFunc0.doApplyAsByte(iterator0);
			consumer.doAccept(this.doApplyAsDbl(a));
		}
	}

}
