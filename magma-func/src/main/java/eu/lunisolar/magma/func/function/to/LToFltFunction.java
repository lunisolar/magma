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

package eu.lunisolar.magma.func.function.to;

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
 * Non-throwing functional interface (lambda) LToFltFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): T a
 *
 * Co-domain: float
 *
 * Special case of function that corresponds to expressions like (iterator) -> Iterator::next
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LToFltFunction<T> extends MetaFunction, MetaInterface.NonThrowing, OFunction<T, aFloat> { // NOSONAR

	String DESCRIPTION = "LToFltFunction: float doApplyAsFlt(T a)";

	// float doApplyAsFlt(T a) ;
	default float doApplyAsFlt(T a) {
		// return nestingDoApplyAsFlt(a);
		try {
			return this.doApplyAsFltX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doApplyAsFlt(T a)
	 */
	float doApplyAsFltX(T a) throws Throwable;

	default float tupleApplyAsFlt(LSingle<T> args) {
		return doApplyAsFlt(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default float handlingDoApplyAsFlt(T a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyAsFltX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default float tryDoApplyAsFlt(T a, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyAsFltX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default float tryDoApplyAsFlt(T a, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyAsFltX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default float tryDoApplyAsFltThen(T a, @Nonnull LToFltFunction<Throwable> handler) {
		try {
			return this.doApplyAsFltX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApplyAsFlt(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default float nestingDoApplyAsFlt(T a) {
		try {
			return this.doApplyAsFltX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default float shovingDoApplyAsFlt(T a) {
		try {
			return this.doApplyAsFltX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> float handlingDoApplyAsFlt(T a, LToFltFunction<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApplyAsFlt(a, handling);
	}

	static <T> float tryDoApplyAsFlt(T a, LToFltFunction<T> func) {
		return tryDoApplyAsFlt(a, func, null);
	}

	static <T> float tryDoApplyAsFlt(T a, LToFltFunction<T> func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsFlt(a, exceptionFactory, newMessage, messageParams);
	}

	static <T> float tryDoApplyAsFlt(T a, LToFltFunction<T> func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsFlt(a, exceptionFactory);
	}

	static <T> float tryDoApplyAsFltThen(T a, LToFltFunction<T> func, @Nonnull LToFltFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsFltThen(a, handler);
	}

	default float failSafeDoApplyAsFlt(T a, @Nonnull LToFltFunction<T> failSafe) {
		try {
			return doApplyAsFlt(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApplyAsFlt(a);
		}
	}

	static <T> float failSafeDoApplyAsFlt(T a, LToFltFunction<T> func, @Nonnull LToFltFunction<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApplyAsFlt(a);
		} else {
			return func.failSafeDoApplyAsFlt(a, failSafe);
		}
	}

	static <T> LToFltFunction<T> failSafeToFltFunc(LToFltFunction<T> func, @Nonnull LToFltFunction<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeDoApplyAsFlt(a, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default float nonNullDoApplyAsFlt(T a) {
		return doApplyAsFlt(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LToFltFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_i, int max_i, T a, LToFltFunction<T> func) {
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
	public static <T> void fromTill(int min_i, int max_i, T a, LToFltFunction<T> func) {
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
	public static <T> void times(int max_i, T a, LToFltFunction<T> func) {
		fromTill(0, max_i, a, func);
	}

	/** Captures arguments but delays the evaluation. */
	default LFltSupplier captureToFltFunc(T a) {
		return () -> this.doApplyAsFlt(a);
	}

	/** Creates function that always returns the same value. */
	static <T> LToFltFunction<T> constant(float r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LToFltFunction<T> toFltFunc(final @Nonnull LToFltFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T> LToFltFunction<T> recursive(final @Nonnull LFunction<LToFltFunction<T>, LToFltFunction<T>> selfLambda) {
		final LToFltFunctionSingle<T> single = new LToFltFunctionSingle();
		LToFltFunction<T> func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LToFltFunctionSingle<T> implements LSingle<LToFltFunction<T>>, LToFltFunction<T> {
		private LToFltFunction<T> target = null;

		@Override
		public float doApplyAsFltX(T a) throws Throwable {
			return target.doApplyAsFltX(a);
		}

		@Override
		public LToFltFunction<T> value() {
			return target;
		}
	}

	@Nonnull
	static <T> LToFltFunction<T> toFltFuncThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static <T> LToFltFunction<T> toFltFuncThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce(message);
		};
	}

	static <T> float call(T a, final @Nonnull LToFltFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsFlt(a);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceFloat). */
	@Nonnull
	static <T> LToFltFunction<T> safe() {
		return LToFltFunction::produceFloat;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LToFltFunction<T>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T> LToFltFunction<T> safe(final @Nullable LToFltFunction<T> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LToFltFunction<T>> safeSupplier(final @Nullable LSupplier<LToFltFunction<T>> supplier) {
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
	default <V> LToFltFunction<V> toFltFuncCompose(@Nonnull final LFunction<? super V, ? extends T> before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApplyAsFlt(before.doApply(v));
	}

	public static <V, T> LToFltFunction<V> composed(@Nonnull final LFunction<? super V, ? extends T> before, LToFltFunction<T> after) {
		return after.toFltFuncCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LFunction<T, V> then(@Nonnull LFltFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApply(this.doApplyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToByteFunction<T> thenToByte(@Nonnull LFltToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsByte(this.doApplyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToSrtFunction<T> thenToSrt(@Nonnull LFltToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsSrt(this.doApplyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToIntFunction<T> thenToInt(@Nonnull LFltToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsInt(this.doApplyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToLongFunction<T> thenToLong(@Nonnull LFltToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsLong(this.doApplyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToFltFunction<T> thenToFlt(@Nonnull LFltUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsFlt(this.doApplyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToDblFunction<T> thenToDbl(@Nonnull LFltToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsDbl(this.doApplyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToCharFunction<T> thenToChar(@Nonnull LFltToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsChar(this.doApplyAsFlt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LPredicate<T> thenToBool(@Nonnull LFltPredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.doTest(this.doApplyAsFlt(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LToFltFunction<T> nestingToFltFunc() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LToFltFunction<T> shovingToFltFunc() {
		return this;
	}

	// </editor-fold>

	/** Does nothing (LToFltFunction) Function */
	public static <T> float produceFloat(T a) {
		return Function4U.defaultFloat;
	}

	// MAP: FOR, [SourcePurpose{arg=T a, type=IA}, SourcePurpose{arg=LFltConsumer consumer, type=CONST}]
	default <C0> void forEach(IndexedRead<C0, a<T>> ia, C0 source, LFltConsumer consumer) {
		int size = ia.size(source);
		LOiFunction<Object, T> oiFunc0 = (LOiFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			T a = oiFunc0.doApply(source, i);
			consumer.doAccept(this.doApplyAsFlt(a));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T a, type=SA}, SourcePurpose{arg=LFltConsumer consumer, type=CONST}]
	default <C0, I0> void iterate(SequentialRead<C0, I0, a<T>> sa, C0 source, LFltConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).doApply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LFunction<Object, T> nextFunc0 = (LFunction) sa.getter();
		while (testFunc0.doTest(iterator0)) {
			T a = nextFunc0.doApply(iterator0);
			consumer.doAccept(this.doApplyAsFlt(a));
		}
	}

}
