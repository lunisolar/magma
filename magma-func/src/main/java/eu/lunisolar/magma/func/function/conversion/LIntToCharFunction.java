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
 * Non-throwing functional interface (lambda) LIntToCharFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): int a
 *
 * Co-domain: char
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LIntToCharFunction extends MetaFunction, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LIntToCharFunction: char doApplyAsChar(int a)";

	// char doApplyAsChar(int a) ;
	default char doApplyAsChar(int a) {
		// return nestingDoApplyAsChar(a);
		try {
			return this.doApplyAsCharX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doApplyAsChar(int a)
	 */
	char doApplyAsCharX(int a) throws Throwable;

	default char tupleApplyAsChar(LIntSingle args) {
		return doApplyAsChar(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default char handlingDoApplyAsChar(int a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyAsCharX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default char tryDoApplyAsChar(int a, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyAsCharX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default char tryDoApplyAsChar(int a, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyAsCharX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default char tryDoApplyAsCharThen(int a, @Nonnull LToCharFunction<Throwable> handler) {
		try {
			return this.doApplyAsCharX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApplyAsChar(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default char nestingDoApplyAsChar(int a) {
		try {
			return this.doApplyAsCharX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default char shovingDoApplyAsChar(int a) {
		try {
			return this.doApplyAsCharX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static char handlingDoApplyAsChar(int a, LIntToCharFunction func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApplyAsChar(a, handling);
	}

	static char tryDoApplyAsChar(int a, LIntToCharFunction func) {
		return tryDoApplyAsChar(a, func, null);
	}

	static char tryDoApplyAsChar(int a, LIntToCharFunction func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsChar(a, exceptionFactory, newMessage, messageParams);
	}

	static char tryDoApplyAsChar(int a, LIntToCharFunction func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsChar(a, exceptionFactory);
	}

	static char tryDoApplyAsCharThen(int a, LIntToCharFunction func, @Nonnull LToCharFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsCharThen(a, handler);
	}

	default char failSafeDoApplyAsChar(int a, @Nonnull LIntToCharFunction failSafe) {
		try {
			return doApplyAsChar(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApplyAsChar(a);
		}
	}

	static char failSafeDoApplyAsChar(int a, LIntToCharFunction func, @Nonnull LIntToCharFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApplyAsChar(a);
		} else {
			return func.failSafeDoApplyAsChar(a, failSafe);
		}
	}

	static LIntToCharFunction failSafeIntToCharFunc(LIntToCharFunction func, @Nonnull LIntToCharFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeDoApplyAsChar(a, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default char nonNullDoApplyAsChar(int a) {
		return doApplyAsChar(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LIntToCharFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_a, int max_a, LIntToCharFunction func) {
		Null.nonNullArg(func, "func");
		if (min_a <= min_a) {
			for (int a = min_a; a <= max_a; a++) {
				func.doApplyAsChar(a);
			}
		} else {
			for (int a = min_a; a >= max_a; a--) {
				func.doApplyAsChar(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_a, int max_a, LIntToCharFunction func) {
		Null.nonNullArg(func, "func");
		if (min_a <= min_a) {
			for (int a = min_a; a < max_a; a++) {
				func.doApplyAsChar(a);
			}
		} else {
			for (int a = min_a; a > max_a; a--) {
				func.doApplyAsChar(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_a, LIntToCharFunction func) {
		fromTill(0, max_a, func);
	}

	/** Captures arguments but delays the evaluation. */
	default LCharSupplier captureIntToCharFunc(int a) {
		return () -> this.doApplyAsChar(a);
	}

	/** Creates function that always returns the same value. */
	static LIntToCharFunction constant(char r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LIntToCharFunction intToCharFunc(final @Nonnull LIntToCharFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LIntToCharFunction recursive(final @Nonnull LFunction<LIntToCharFunction, LIntToCharFunction> selfLambda) {
		final LIntToCharFunctionSingle single = new LIntToCharFunctionSingle();
		LIntToCharFunction func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LIntToCharFunctionSingle implements LSingle<LIntToCharFunction>, LIntToCharFunction {
		private LIntToCharFunction target = null;

		@Override
		public char doApplyAsCharX(int a) throws Throwable {
			return target.doApplyAsCharX(a);
		}

		@Override
		public LIntToCharFunction value() {
			return target;
		}
	}

	@Nonnull
	static LIntToCharFunction intToCharFuncThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LIntToCharFunction intToCharFuncThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce(message);
		};
	}

	static char call(int a, final @Nonnull LIntToCharFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsChar(a);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceChar). */
	@Nonnull
	static LIntToCharFunction safe() {
		return LIntToCharFunction::produceChar;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LIntToCharFunction> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LIntToCharFunction safe(final @Nullable LIntToCharFunction other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LIntToCharFunction> safeSupplier(final @Nullable LSupplier<LIntToCharFunction> supplier) {
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
	default LIntToCharFunction intToCharFuncComposeInt(@Nonnull final LIntUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApplyAsChar(before.doApplyAsInt(v));
	}

	public static LIntToCharFunction composedInt(@Nonnull final LIntUnaryOperator before, LIntToCharFunction after) {
		return after.intToCharFuncComposeInt(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToCharFunction<V> intToCharFuncCompose(@Nonnull final LToIntFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApplyAsChar(before.doApplyAsInt(v));
	}

	public static <V> LToCharFunction<V> composed(@Nonnull final LToIntFunction<? super V> before, LIntToCharFunction after) {
		return after.intToCharFuncCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LIntFunction<V> then(@Nonnull LCharFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApply(this.doApplyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToByteFunction thenToByte(@Nonnull LCharToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsByte(this.doApplyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToSrtFunction thenToSrt(@Nonnull LCharToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsSrt(this.doApplyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntUnaryOperator thenToInt(@Nonnull LCharToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsInt(this.doApplyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToLongFunction thenToLong(@Nonnull LCharToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsLong(this.doApplyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToFltFunction thenToFlt(@Nonnull LCharToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsFlt(this.doApplyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToDblFunction thenToDbl(@Nonnull LCharToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsDbl(this.doApplyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToCharFunction thenToChar(@Nonnull LCharUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsChar(this.doApplyAsChar(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntPredicate thenToBool(@Nonnull LCharPredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.doTest(this.doApplyAsChar(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LIntToCharFunction nestingIntToCharFunc() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntToCharFunction shovingIntToCharFunc() {
		return this;
	}

	// </editor-fold>

	/** Does nothing (LIntToCharFunction) Function */
	public static char produceChar(int a) {
		return Function4U.defaultCharacter;
	}

	// MAP: FOR, [SourcePurpose{arg=int a, type=IA}, SourcePurpose{arg=LCharConsumer consumer, type=CONST}]
	default <C0> void forEach(IndexedRead<C0, aInt> ia, C0 source, LCharConsumer consumer) {
		int size = ia.size(source);
		LOiToIntFunction<Object> oiFunc0 = (LOiToIntFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			int a = oiFunc0.doApplyAsInt(source, i);
			consumer.doAccept(this.doApplyAsChar(a));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=int a, type=SA}, SourcePurpose{arg=LCharConsumer consumer, type=CONST}]
	default <C0, I0> void iterate(SequentialRead<C0, I0, aInt> sa, C0 source, LCharConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).doApply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToIntFunction<Object> nextFunc0 = (LToIntFunction) sa.getter();
		while (testFunc0.doTest(iterator0)) {
			int a = nextFunc0.doApplyAsInt(iterator0);
			consumer.doAccept(this.doApplyAsChar(a));
		}
	}

}
