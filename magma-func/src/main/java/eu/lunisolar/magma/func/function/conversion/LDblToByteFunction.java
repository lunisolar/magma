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
 * Non-throwing functional interface (lambda) LDblToByteFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): double a
 *
 * Co-domain: byte
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LDblToByteFunction extends MetaFunction, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LDblToByteFunction: byte doApplyAsByte(double a)";

	// byte doApplyAsByte(double a) ;
	default byte doApplyAsByte(double a) {
		// return nestingDoApplyAsByte(a);
		try {
			return this.doApplyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doApplyAsByte(double a)
	 */
	byte doApplyAsByteX(double a) throws Throwable;

	default byte tupleApplyAsByte(LDblSingle args) {
		return doApplyAsByte(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default byte handlingDoApplyAsByte(double a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default byte tryDoApplyAsByte(double a, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default byte tryDoApplyAsByte(double a, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default byte tryDoApplyAsByteThen(double a, @Nonnull LToByteFunction<Throwable> handler) {
		try {
			return this.doApplyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApplyAsByte(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default byte nestingDoApplyAsByte(double a) {
		try {
			return this.doApplyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default byte shovingDoApplyAsByte(double a) {
		try {
			return this.doApplyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static byte handlingDoApplyAsByte(double a, LDblToByteFunction func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApplyAsByte(a, handling);
	}

	static byte tryDoApplyAsByte(double a, LDblToByteFunction func) {
		return tryDoApplyAsByte(a, func, null);
	}

	static byte tryDoApplyAsByte(double a, LDblToByteFunction func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsByte(a, exceptionFactory, newMessage, messageParams);
	}

	static byte tryDoApplyAsByte(double a, LDblToByteFunction func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsByte(a, exceptionFactory);
	}

	static byte tryDoApplyAsByteThen(double a, LDblToByteFunction func, @Nonnull LToByteFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsByteThen(a, handler);
	}

	default byte failSafeDoApplyAsByte(double a, @Nonnull LDblToByteFunction failSafe) {
		try {
			return doApplyAsByte(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApplyAsByte(a);
		}
	}

	static byte failSafeDoApplyAsByte(double a, LDblToByteFunction func, @Nonnull LDblToByteFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApplyAsByte(a);
		} else {
			return func.failSafeDoApplyAsByte(a, failSafe);
		}
	}

	static LDblToByteFunction failSafeDblToByteFunc(LDblToByteFunction func, @Nonnull LDblToByteFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeDoApplyAsByte(a, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default byte nonNullDoApplyAsByte(double a) {
		return doApplyAsByte(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LDblToByteFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, double a, LDblToByteFunction func) {
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
	public static void fromTill(int min_i, int max_i, double a, LDblToByteFunction func) {
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
	public static void times(int max_i, double a, LDblToByteFunction func) {
		fromTill(0, max_i, a, func);
	}

	/** Captures arguments but delays the evaluation. */
	default LByteSupplier captureDblToByteFunc(double a) {
		return () -> this.doApplyAsByte(a);
	}

	/** Creates function that always returns the same value. */
	static LDblToByteFunction constant(byte r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LDblToByteFunction dblToByteFunc(final @Nonnull LDblToByteFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LDblToByteFunction recursive(final @Nonnull LFunction<LDblToByteFunction, LDblToByteFunction> selfLambda) {
		final LDblToByteFunctionSingle single = new LDblToByteFunctionSingle();
		LDblToByteFunction func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LDblToByteFunctionSingle implements LSingle<LDblToByteFunction>, LDblToByteFunction {
		private LDblToByteFunction target = null;

		@Override
		public byte doApplyAsByteX(double a) throws Throwable {
			return target.doApplyAsByteX(a);
		}

		@Override
		public LDblToByteFunction value() {
			return target;
		}
	}

	@Nonnull
	static LDblToByteFunction dblToByteFuncThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LDblToByteFunction dblToByteFuncThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce(message);
		};
	}

	static byte call(double a, final @Nonnull LDblToByteFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsByte(a);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceByte). */
	@Nonnull
	static LDblToByteFunction safe() {
		return LDblToByteFunction::produceByte;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LDblToByteFunction> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LDblToByteFunction safe(final @Nullable LDblToByteFunction other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LDblToByteFunction> safeSupplier(final @Nullable LSupplier<LDblToByteFunction> supplier) {
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
	default LDblToByteFunction dblToByteFuncComposeDbl(@Nonnull final LDblUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApplyAsByte(before.doApplyAsDbl(v));
	}

	public static LDblToByteFunction composedDbl(@Nonnull final LDblUnaryOperator before, LDblToByteFunction after) {
		return after.dblToByteFuncComposeDbl(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToByteFunction<V> dblToByteFuncCompose(@Nonnull final LToDblFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApplyAsByte(before.doApplyAsDbl(v));
	}

	public static <V> LToByteFunction<V> composed(@Nonnull final LToDblFunction<? super V> before, LDblToByteFunction after) {
		return after.dblToByteFuncCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LDblFunction<V> then(@Nonnull LByteFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApply(this.doApplyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToByteFunction thenToByte(@Nonnull LByteUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsByte(this.doApplyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToSrtFunction thenToSrt(@Nonnull LByteToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsSrt(this.doApplyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToIntFunction thenToInt(@Nonnull LByteToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsInt(this.doApplyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToLongFunction thenToLong(@Nonnull LByteToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsLong(this.doApplyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToFltFunction thenToFlt(@Nonnull LByteToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsFlt(this.doApplyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblUnaryOperator thenToDbl(@Nonnull LByteToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsDbl(this.doApplyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToCharFunction thenToChar(@Nonnull LByteToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsChar(this.doApplyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblPredicate thenToBool(@Nonnull LBytePredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.doTest(this.doApplyAsByte(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LDblToByteFunction nestingDblToByteFunc() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LDblToByteFunction shovingDblToByteFunc() {
		return this;
	}

	// </editor-fold>

	/** Does nothing (LDblToByteFunction) Function */
	public static byte produceByte(double a) {
		return Function4U.defaultByte;
	}

	// MAP: FOR, [SourcePurpose{arg=double a, type=IA}, SourcePurpose{arg=LByteConsumer consumer, type=CONST}]
	default <C0> void forEach(IndexedRead<C0, aDouble> ia, C0 source, LByteConsumer consumer) {
		int size = ia.size(source);
		LOiToDblFunction<Object> oiFunc0 = (LOiToDblFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			double a = oiFunc0.doApplyAsDbl(source, i);
			consumer.doAccept(this.doApplyAsByte(a));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=double a, type=SA}, SourcePurpose{arg=LByteConsumer consumer, type=CONST}]
	default <C0, I0> void iterate(SequentialRead<C0, I0, aDouble> sa, C0 source, LByteConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).doApply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToDblFunction<Object> nextFunc0 = (LToDblFunction) sa.getter();
		while (testFunc0.doTest(iterator0)) {
			double a = nextFunc0.doApplyAsDbl(iterator0);
			consumer.doAccept(this.doApplyAsByte(a));
		}
	}

}
