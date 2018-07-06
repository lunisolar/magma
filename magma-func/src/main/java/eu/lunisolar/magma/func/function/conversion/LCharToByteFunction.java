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
 * Non-throwing functional interface (lambda) LCharToByteFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): char a
 *
 * Co-domain: byte
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LCharToByteFunction extends MetaFunction, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LCharToByteFunction: byte doApplyAsByte(char a)";

	// byte doApplyAsByte(char a) ;
	default byte doApplyAsByte(char a) {
		// return nestingDoApplyAsByte(a);
		try {
			return this.doApplyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doApplyAsByte(char a)
	 */
	byte doApplyAsByteX(char a) throws Throwable;

	default byte tupleApplyAsByte(LCharSingle args) {
		return doApplyAsByte(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default byte handlingDoApplyAsByte(char a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default byte tryDoApplyAsByte(char a, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default byte tryDoApplyAsByte(char a, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default byte tryDoApplyAsByteThen(char a, @Nonnull LToByteFunction<Throwable> handler) {
		try {
			return this.doApplyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApplyAsByte(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default byte nestingDoApplyAsByte(char a) {
		try {
			return this.doApplyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default byte shovingDoApplyAsByte(char a) {
		try {
			return this.doApplyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static byte handlingDoApplyAsByte(char a, LCharToByteFunction func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApplyAsByte(a, handling);
	}

	static byte tryDoApplyAsByte(char a, LCharToByteFunction func) {
		return tryDoApplyAsByte(a, func, null);
	}

	static byte tryDoApplyAsByte(char a, LCharToByteFunction func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsByte(a, exceptionFactory, newMessage, messageParams);
	}

	static byte tryDoApplyAsByte(char a, LCharToByteFunction func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsByte(a, exceptionFactory);
	}

	static byte tryDoApplyAsByteThen(char a, LCharToByteFunction func, @Nonnull LToByteFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsByteThen(a, handler);
	}

	default byte failSafeDoApplyAsByte(char a, @Nonnull LCharToByteFunction failSafe) {
		try {
			return doApplyAsByte(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApplyAsByte(a);
		}
	}

	static byte failSafeDoApplyAsByte(char a, LCharToByteFunction func, @Nonnull LCharToByteFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApplyAsByte(a);
		} else {
			return func.failSafeDoApplyAsByte(a, failSafe);
		}
	}

	static LCharToByteFunction failSafeCharToByteFunc(LCharToByteFunction func, @Nonnull LCharToByteFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeDoApplyAsByte(a, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default byte nonNullDoApplyAsByte(char a) {
		return doApplyAsByte(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LCharToByteFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, char a, LCharToByteFunction func) {
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
	public static void fromTill(int min_i, int max_i, char a, LCharToByteFunction func) {
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
	public static void times(int max_i, char a, LCharToByteFunction func) {
		fromTill(0, max_i, a, func);
	}

	/** Captures arguments but delays the evaluation. */
	default LByteSupplier captureCharToByteFunc(char a) {
		return () -> this.doApplyAsByte(a);
	}

	/** Creates function that always returns the same value. */
	static LCharToByteFunction constant(byte r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LCharToByteFunction charToByteFunc(final @Nonnull LCharToByteFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LCharToByteFunction recursive(final @Nonnull LFunction<LCharToByteFunction, LCharToByteFunction> selfLambda) {
		final LCharToByteFunctionSingle single = new LCharToByteFunctionSingle();
		LCharToByteFunction func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LCharToByteFunctionSingle implements LSingle<LCharToByteFunction>, LCharToByteFunction {
		private LCharToByteFunction target = null;

		@Override
		public byte doApplyAsByteX(char a) throws Throwable {
			return target.doApplyAsByteX(a);
		}

		@Override
		public LCharToByteFunction value() {
			return target;
		}
	}

	@Nonnull
	static LCharToByteFunction charToByteFuncThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LCharToByteFunction charToByteFuncThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce(message);
		};
	}

	static byte call(char a, final @Nonnull LCharToByteFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsByte(a);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceByte). */
	@Nonnull
	static LCharToByteFunction safe() {
		return LCharToByteFunction::produceByte;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LCharToByteFunction> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LCharToByteFunction safe(final @Nullable LCharToByteFunction other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LCharToByteFunction> safeSupplier(final @Nullable LSupplier<LCharToByteFunction> supplier) {
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
	default LCharToByteFunction charToByteFuncComposeChar(@Nonnull final LCharUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApplyAsByte(before.doApplyAsChar(v));
	}

	public static LCharToByteFunction composedChar(@Nonnull final LCharUnaryOperator before, LCharToByteFunction after) {
		return after.charToByteFuncComposeChar(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToByteFunction<V> charToByteFuncCompose(@Nonnull final LToCharFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApplyAsByte(before.doApplyAsChar(v));
	}

	public static <V> LToByteFunction<V> composed(@Nonnull final LToCharFunction<? super V> before, LCharToByteFunction after) {
		return after.charToByteFuncCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LCharFunction<V> then(@Nonnull LByteFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApply(this.doApplyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToByteFunction thenToByte(@Nonnull LByteUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsByte(this.doApplyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToSrtFunction thenToSrt(@Nonnull LByteToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsSrt(this.doApplyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToIntFunction thenToInt(@Nonnull LByteToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsInt(this.doApplyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToLongFunction thenToLong(@Nonnull LByteToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsLong(this.doApplyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToFltFunction thenToFlt(@Nonnull LByteToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsFlt(this.doApplyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToDblFunction thenToDbl(@Nonnull LByteToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsDbl(this.doApplyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharUnaryOperator thenToChar(@Nonnull LByteToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsChar(this.doApplyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharPredicate thenToBool(@Nonnull LBytePredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.doTest(this.doApplyAsByte(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LCharToByteFunction nestingCharToByteFunc() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LCharToByteFunction shovingCharToByteFunc() {
		return this;
	}

	// </editor-fold>

	/** Does nothing (LCharToByteFunction) Function */
	public static byte produceByte(char a) {
		return Function4U.defaultByte;
	}

	// MAP: FOR, [SourcePurpose{arg=char a, type=IA}, SourcePurpose{arg=LByteConsumer consumer, type=CONST}]
	default <C0> void forEach(IndexedRead<C0, aChar> ia, C0 source, LByteConsumer consumer) {
		int size = ia.size(source);
		LOiToCharFunction<Object> oiFunc0 = (LOiToCharFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			char a = oiFunc0.doApplyAsChar(source, i);
			consumer.doAccept(this.doApplyAsByte(a));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=char a, type=SA}, SourcePurpose{arg=LByteConsumer consumer, type=CONST}]
	default <C0, I0> void iterate(SequentialRead<C0, I0, aChar> sa, C0 source, LByteConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).doApply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToCharFunction<Object> nextFunc0 = (LToCharFunction) sa.getter();
		while (testFunc0.doTest(iterator0)) {
			char a = nextFunc0.doApplyAsChar(iterator0);
			consumer.doAccept(this.doApplyAsByte(a));
		}
	}

}
