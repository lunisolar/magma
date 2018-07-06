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
 * Non-throwing functional interface (lambda) LDblToFltFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): double a
 *
 * Co-domain: float
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LDblToFltFunction extends MetaFunction, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LDblToFltFunction: float doApplyAsFlt(double a)";

	// float doApplyAsFlt(double a) ;
	default float doApplyAsFlt(double a) {
		// return nestingDoApplyAsFlt(a);
		try {
			return this.doApplyAsFltX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doApplyAsFlt(double a)
	 */
	float doApplyAsFltX(double a) throws Throwable;

	default float tupleApplyAsFlt(LDblSingle args) {
		return doApplyAsFlt(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default float handlingDoApplyAsFlt(double a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyAsFltX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default float tryDoApplyAsFlt(double a, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyAsFltX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default float tryDoApplyAsFlt(double a, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyAsFltX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default float tryDoApplyAsFltThen(double a, @Nonnull LToFltFunction<Throwable> handler) {
		try {
			return this.doApplyAsFltX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApplyAsFlt(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default float nestingDoApplyAsFlt(double a) {
		try {
			return this.doApplyAsFltX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default float shovingDoApplyAsFlt(double a) {
		try {
			return this.doApplyAsFltX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static float handlingDoApplyAsFlt(double a, LDblToFltFunction func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApplyAsFlt(a, handling);
	}

	static float tryDoApplyAsFlt(double a, LDblToFltFunction func) {
		return tryDoApplyAsFlt(a, func, null);
	}

	static float tryDoApplyAsFlt(double a, LDblToFltFunction func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsFlt(a, exceptionFactory, newMessage, messageParams);
	}

	static float tryDoApplyAsFlt(double a, LDblToFltFunction func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsFlt(a, exceptionFactory);
	}

	static float tryDoApplyAsFltThen(double a, LDblToFltFunction func, @Nonnull LToFltFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsFltThen(a, handler);
	}

	default float failSafeDoApplyAsFlt(double a, @Nonnull LDblToFltFunction failSafe) {
		try {
			return doApplyAsFlt(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApplyAsFlt(a);
		}
	}

	static float failSafeDoApplyAsFlt(double a, LDblToFltFunction func, @Nonnull LDblToFltFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApplyAsFlt(a);
		} else {
			return func.failSafeDoApplyAsFlt(a, failSafe);
		}
	}

	static LDblToFltFunction failSafeDblToFltFunc(LDblToFltFunction func, @Nonnull LDblToFltFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeDoApplyAsFlt(a, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default float nonNullDoApplyAsFlt(double a) {
		return doApplyAsFlt(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LDblToFltFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, double a, LDblToFltFunction func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.doApplyAsFlt(a);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.doApplyAsFlt(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, double a, LDblToFltFunction func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i < max_i; i++) {
				func.doApplyAsFlt(a);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.doApplyAsFlt(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, double a, LDblToFltFunction func) {
		fromTill(0, max_i, a, func);
	}

	/** Captures arguments but delays the evaluation. */
	default LFltSupplier captureDblToFltFunc(double a) {
		return () -> this.doApplyAsFlt(a);
	}

	/** Creates function that always returns the same value. */
	static LDblToFltFunction constant(float r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LDblToFltFunction dblToFltFunc(final @Nonnull LDblToFltFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LDblToFltFunction recursive(final @Nonnull LFunction<LDblToFltFunction, LDblToFltFunction> selfLambda) {
		final LDblToFltFunctionSingle single = new LDblToFltFunctionSingle();
		LDblToFltFunction func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LDblToFltFunctionSingle implements LSingle<LDblToFltFunction>, LDblToFltFunction {
		private LDblToFltFunction target = null;

		@Override
		public float doApplyAsFltX(double a) throws Throwable {
			return target.doApplyAsFltX(a);
		}

		@Override
		public LDblToFltFunction value() {
			return target;
		}
	}

	@Nonnull
	static LDblToFltFunction dblToFltFuncThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LDblToFltFunction dblToFltFuncThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce(message);
		};
	}

	static float call(double a, final @Nonnull LDblToFltFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsFlt(a);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceFloat). */
	@Nonnull
	static LDblToFltFunction safe() {
		return LDblToFltFunction::produceFloat;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LDblToFltFunction> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LDblToFltFunction safe(final @Nullable LDblToFltFunction other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LDblToFltFunction> safeSupplier(final @Nullable LSupplier<LDblToFltFunction> supplier) {
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
	default LDblToFltFunction dblToFltFuncComposeDbl(@Nonnull final LDblUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApplyAsFlt(before.doApplyAsDbl(v));
	}

	public static LDblToFltFunction composedDbl(@Nonnull final LDblUnaryOperator before, LDblToFltFunction after) {
		return after.dblToFltFuncComposeDbl(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToFltFunction<V> dblToFltFuncCompose(@Nonnull final LToDblFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApplyAsFlt(before.doApplyAsDbl(v));
	}

	public static <V> LToFltFunction<V> composed(@Nonnull final LToDblFunction<? super V> before, LDblToFltFunction after) {
		return after.dblToFltFuncCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LDblFunction<V> then(@Nonnull LFltFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApply(this.doApplyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToByteFunction thenToByte(@Nonnull LFltToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsByte(this.doApplyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToSrtFunction thenToSrt(@Nonnull LFltToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsSrt(this.doApplyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToIntFunction thenToInt(@Nonnull LFltToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsInt(this.doApplyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToLongFunction thenToLong(@Nonnull LFltToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsLong(this.doApplyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToFltFunction thenToFlt(@Nonnull LFltUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsFlt(this.doApplyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblUnaryOperator thenToDbl(@Nonnull LFltToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsDbl(this.doApplyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToCharFunction thenToChar(@Nonnull LFltToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsChar(this.doApplyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblPredicate thenToBool(@Nonnull LFltPredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.doTest(this.doApplyAsFlt(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LDblToFltFunction nestingDblToFltFunc() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LDblToFltFunction shovingDblToFltFunc() {
		return this;
	}

	// </editor-fold>

	/** Does nothing (LDblToFltFunction) Function */
	public static float produceFloat(double a) {
		return Function4U.defaultFloat;
	}

	// MAP: FOR, [SourcePurpose{arg=double a, type=IA}, SourcePurpose{arg=LFltConsumer consumer, type=CONST}]
	default <C0> void forEach(IndexedRead<C0, aDouble> ia, C0 source, LFltConsumer consumer) {
		int size = ia.size(source);
		LOiToDblFunction<Object> oiFunc0 = (LOiToDblFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			double a = oiFunc0.doApplyAsDbl(source, i);
			consumer.doAccept(this.doApplyAsFlt(a));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=double a, type=SA}, SourcePurpose{arg=LFltConsumer consumer, type=CONST}]
	default <C0, I0> void iterate(SequentialRead<C0, I0, aDouble> sa, C0 source, LFltConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).doApply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToDblFunction<Object> nextFunc0 = (LToDblFunction) sa.getter();
		while (testFunc0.doTest(iterator0)) {
			double a = nextFunc0.doApplyAsDbl(iterator0);
			consumer.doAccept(this.doApplyAsFlt(a));
		}
	}

}
