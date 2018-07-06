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

package eu.lunisolar.magma.func.operator.unary;

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
 * Non-throwing functional interface (lambda) LFltUnaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 1): float a
 *
 * Co-domain: float
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LFltUnaryOperator extends MetaOperator, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LFltUnaryOperator: float doApplyAsFlt(float a)";

	// float doApplyAsFlt(float a) ;
	default float doApplyAsFlt(float a) {
		// return nestingDoApplyAsFlt(a);
		try {
			return this.doApplyAsFltX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doApplyAsFlt(float a)
	 */
	float doApplyAsFltX(float a) throws Throwable;

	default float tupleApplyAsFlt(LFltSingle args) {
		return doApplyAsFlt(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default float handlingDoApplyAsFlt(float a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyAsFltX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default float tryDoApplyAsFlt(float a, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyAsFltX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default float tryDoApplyAsFlt(float a, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyAsFltX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default float tryDoApplyAsFltThen(float a, @Nonnull LToFltFunction<Throwable> handler) {
		try {
			return this.doApplyAsFltX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApplyAsFlt(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default float nestingDoApplyAsFlt(float a) {
		try {
			return this.doApplyAsFltX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default float shovingDoApplyAsFlt(float a) {
		try {
			return this.doApplyAsFltX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static float handlingDoApplyAsFlt(float a, LFltUnaryOperator func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApplyAsFlt(a, handling);
	}

	static float tryDoApplyAsFlt(float a, LFltUnaryOperator func) {
		return tryDoApplyAsFlt(a, func, null);
	}

	static float tryDoApplyAsFlt(float a, LFltUnaryOperator func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsFlt(a, exceptionFactory, newMessage, messageParams);
	}

	static float tryDoApplyAsFlt(float a, LFltUnaryOperator func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsFlt(a, exceptionFactory);
	}

	static float tryDoApplyAsFltThen(float a, LFltUnaryOperator func, @Nonnull LToFltFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsFltThen(a, handler);
	}

	default float failSafeDoApplyAsFlt(float a, @Nonnull LFltUnaryOperator failSafe) {
		try {
			return doApplyAsFlt(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApplyAsFlt(a);
		}
	}

	static float failSafeDoApplyAsFlt(float a, LFltUnaryOperator func, @Nonnull LFltUnaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApplyAsFlt(a);
		} else {
			return func.failSafeDoApplyAsFlt(a, failSafe);
		}
	}

	static LFltUnaryOperator failSafeFltUnaryOp(LFltUnaryOperator func, @Nonnull LFltUnaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeDoApplyAsFlt(a, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default float nonNullDoApplyAsFlt(float a) {
		return doApplyAsFlt(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LFltUnaryOperator.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, float a, LFltUnaryOperator func) {
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
	public static void fromTill(int min_i, int max_i, float a, LFltUnaryOperator func) {
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
	public static void times(int max_i, float a, LFltUnaryOperator func) {
		fromTill(0, max_i, a, func);
	}

	/** Captures arguments but delays the evaluation. */
	default LFltSupplier captureFltUnaryOp(float a) {
		return () -> this.doApplyAsFlt(a);
	}

	/** Creates function that always returns the same value. */
	static LFltUnaryOperator constant(float r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LFltUnaryOperator fltUnaryOp(final @Nonnull LFltUnaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LFltUnaryOperator recursive(final @Nonnull LFunction<LFltUnaryOperator, LFltUnaryOperator> selfLambda) {
		final LFltUnaryOperatorSingle single = new LFltUnaryOperatorSingle();
		LFltUnaryOperator func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LFltUnaryOperatorSingle implements LSingle<LFltUnaryOperator>, LFltUnaryOperator {
		private LFltUnaryOperator target = null;

		@Override
		public float doApplyAsFltX(float a) throws Throwable {
			return target.doApplyAsFltX(a);
		}

		@Override
		public LFltUnaryOperator value() {
			return target;
		}
	}

	@Nonnull
	static LFltUnaryOperator fltUnaryOpThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LFltUnaryOperator fltUnaryOpThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce(message);
		};
	}

	static float call(float a, final @Nonnull LFltUnaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsFlt(a);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceFloat). */
	@Nonnull
	static LFltUnaryOperator safe() {
		return LFltUnaryOperator::produceFloat;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LFltUnaryOperator> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LFltUnaryOperator safe(final @Nullable LFltUnaryOperator other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LFltUnaryOperator> safeSupplier(final @Nullable LSupplier<LFltUnaryOperator> supplier) {
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
	default LFltUnaryOperator fltUnaryOpComposeFlt(@Nonnull final LFltUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApplyAsFlt(before.doApplyAsFlt(v));
	}

	public static LFltUnaryOperator composedFlt(@Nonnull final LFltUnaryOperator before, LFltUnaryOperator after) {
		return after.fltUnaryOpComposeFlt(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToFltFunction<V> fltUnaryOpCompose(@Nonnull final LToFltFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApplyAsFlt(before.doApplyAsFlt(v));
	}

	public static <V> LToFltFunction<V> composed(@Nonnull final LToFltFunction<? super V> before, LFltUnaryOperator after) {
		return after.fltUnaryOpCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LFltFunction<V> then(@Nonnull LFltFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApply(this.doApplyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltToByteFunction thenToByte(@Nonnull LFltToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsByte(this.doApplyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltToSrtFunction thenToSrt(@Nonnull LFltToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsSrt(this.doApplyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltToIntFunction thenToInt(@Nonnull LFltToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsInt(this.doApplyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltToLongFunction thenToLong(@Nonnull LFltToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsLong(this.doApplyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltUnaryOperator thenToFlt(@Nonnull LFltUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsFlt(this.doApplyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltToDblFunction thenToDbl(@Nonnull LFltToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsDbl(this.doApplyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltToCharFunction thenToChar(@Nonnull LFltToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsChar(this.doApplyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LFltPredicate thenToBool(@Nonnull LFltPredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.doTest(this.doApplyAsFlt(a));
	}

	// </editor-fold>

	/** Returns a function that always returns its input argument. */
	@Nonnull
	static LFltUnaryOperator identity() {
		return t -> t;
	}

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LFltUnaryOperator nestingFltUnaryOp() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LFltUnaryOperator shovingFltUnaryOp() {
		return this;
	}

	// </editor-fold>

	/** Does nothing (LFltUnaryOperator) Operator */
	public static float produceFloat(float a) {
		return Function4U.defaultFloat;
	}

	// MAP: FOR, [SourcePurpose{arg=float a, type=IA}, SourcePurpose{arg=LFltConsumer consumer, type=CONST}]
	default <C0> void forEach(IndexedRead<C0, aFloat> ia, C0 source, LFltConsumer consumer) {
		int size = ia.size(source);
		LOiToFltFunction<Object> oiFunc0 = (LOiToFltFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			float a = oiFunc0.doApplyAsFlt(source, i);
			consumer.doAccept(this.doApplyAsFlt(a));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=float a, type=SA}, SourcePurpose{arg=LFltConsumer consumer, type=CONST}]
	default <C0, I0> void iterate(SequentialRead<C0, I0, aFloat> sa, C0 source, LFltConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).doApply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToFltFunction<Object> nextFunc0 = (LToFltFunction) sa.getter();
		while (testFunc0.doTest(iterator0)) {
			float a = nextFunc0.doApplyAsFlt(iterator0);
			consumer.doAccept(this.doApplyAsFlt(a));
		}
	}

}
