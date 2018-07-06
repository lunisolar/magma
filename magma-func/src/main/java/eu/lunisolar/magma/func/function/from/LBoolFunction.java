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

package eu.lunisolar.magma.func.function.from;

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
 * Non-throwing functional interface (lambda) LBoolFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): boolean a
 *
 * Co-domain: R
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBoolFunction<R> extends MetaFunction, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LBoolFunction: R doApply(boolean a)";

	@Nullable
	// R doApply(boolean a) ;
	default R doApply(boolean a) {
		// return nestingDoApply(a);
		try {
			return this.doApplyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doApply(boolean a)
	 */
	R doApplyX(boolean a) throws Throwable;

	default R tupleApply(LBoolSingle args) {
		return doApply(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default R handlingDoApply(boolean a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default R tryDoApply(boolean a, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default R tryDoApply(boolean a, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default R tryDoApplyThen(boolean a, @Nonnull LFunction<Throwable, R> handler) {
		try {
			return this.doApplyX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApply(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default R nestingDoApply(boolean a) {
		try {
			return this.doApplyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default R shovingDoApply(boolean a) {
		try {
			return this.doApplyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <R> R handlingDoApply(boolean a, LBoolFunction<R> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApply(a, handling);
	}

	static <R> R tryDoApply(boolean a, LBoolFunction<R> func) {
		return tryDoApply(a, func, null);
	}

	static <R> R tryDoApply(boolean a, LBoolFunction<R> func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApply(a, exceptionFactory, newMessage, messageParams);
	}

	static <R> R tryDoApply(boolean a, LBoolFunction<R> func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApply(a, exceptionFactory);
	}

	static <R> R tryDoApplyThen(boolean a, LBoolFunction<R> func, @Nonnull LFunction<Throwable, R> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyThen(a, handler);
	}

	default R failSafeDoApply(boolean a, @Nonnull LBoolFunction<R> failSafe) {
		try {
			return doApply(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApply(a);
		}
	}

	static <R> R failSafeDoApply(boolean a, LBoolFunction<R> func, @Nonnull LBoolFunction<R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApply(a);
		} else {
			return func.failSafeDoApply(a, failSafe);
		}
	}

	static <R> LBoolFunction<R> failSafeBoolFunc(LBoolFunction<R> func, @Nonnull LBoolFunction<R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeDoApply(a, func, failSafe);
	}

	LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullDoApply() method cannot be null (" + DESCRIPTION + ").";

	/** Function call that ensures the result is not null */
	@Nonnull
	default R nonNullDoApply(boolean a) {
		return Null.requireNonNull(doApply(a), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBoolFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <R> void fromTo(int min_i, int max_i, boolean a, LBoolFunction<R> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.doApply(a);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.doApply(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <R> void fromTill(int min_i, int max_i, boolean a, LBoolFunction<R> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i < max_i; i++) {
				func.doApply(a);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.doApply(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <R> void times(int max_i, boolean a, LBoolFunction<R> func) {
		fromTill(0, max_i, a, func);
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplier<R> captureBoolFunc(boolean a) {
		return () -> this.doApply(a);
	}

	/** Creates function that always returns the same value. */
	static <R> LBoolFunction<R> constant(R r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <R> LBoolFunction<R> boolFunc(final @Nonnull LBoolFunction<R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <R> LBoolFunction<R> recursive(final @Nonnull LFunction<LBoolFunction<R>, LBoolFunction<R>> selfLambda) {
		final LBoolFunctionSingle<R> single = new LBoolFunctionSingle();
		LBoolFunction<R> func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LBoolFunctionSingle<R> implements LSingle<LBoolFunction<R>>, LBoolFunction<R> {
		private LBoolFunction<R> target = null;

		@Override
		public R doApplyX(boolean a) throws Throwable {
			return target.doApplyX(a);
		}

		@Override
		public LBoolFunction<R> value() {
			return target;
		}
	}

	@Nonnull
	static <R> LBoolFunction<R> boolFuncThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static <R> LBoolFunction<R> boolFuncThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce(message);
		};
	}

	static <R> R call(boolean a, final @Nonnull LBoolFunction<R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApply(a);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produce). */
	@Nonnull
	static <R> LBoolFunction<R> safe() {
		return LBoolFunction::produce;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <R> LSupplier<LBoolFunction<R>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <R> LBoolFunction<R> safe(final @Nullable LBoolFunction<R> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <R> LSupplier<LBoolFunction<R>> safeSupplier(final @Nullable LSupplier<LBoolFunction<R>> supplier) {
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
	default LBoolFunction<R> boolFuncComposeBool(@Nonnull final LLogicalOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApply(before.doApply(v));
	}

	public static <R> LBoolFunction<R> composedBool(@Nonnull final LLogicalOperator before, LBoolFunction<R> after) {
		return after.boolFuncComposeBool(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LFunction<V, R> boolFuncCompose(@Nonnull final LPredicate<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApply(before.doTest(v));
	}

	public static <V, R> LFunction<V, R> composed(@Nonnull final LPredicate<? super V> before, LBoolFunction<R> after) {
		return after.boolFuncCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBoolFunction<V> then(@Nonnull LFunction<? super R, ? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApply(this.doApply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolConsumer thenConsume(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doAccept(this.doApply(a));
	}

	@Nonnull
	default LBoolFunction<R> before(@Nonnull LBoolConsumer before) {
		Null.nonNullArg(before, "before");
		return a -> {
			before.doAccept(a);
			return this.doApply(a);
		};
	}

	@Nonnull
	default LBoolFunction<R> after(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> {
			R result = this.doApply(a);
			after.doAccept(result);
			return result;
		};
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToByteFunction thenToByte(@Nonnull LToByteFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsByte(this.doApply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToSrtFunction thenToSrt(@Nonnull LToSrtFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsSrt(this.doApply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToIntFunction thenToInt(@Nonnull LToIntFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsInt(this.doApply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToLongFunction thenToLong(@Nonnull LToLongFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsLong(this.doApply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToFltFunction thenToFlt(@Nonnull LToFltFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsFlt(this.doApply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToDblFunction thenToDbl(@Nonnull LToDblFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsDbl(this.doApply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToCharFunction thenToChar(@Nonnull LToCharFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsChar(this.doApply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLogicalOperator thenToBool(@Nonnull LPredicate<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doTest(this.doApply(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBoolFunction<R> nestingBoolFunc() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBoolFunction<R> shovingBoolFunc() {
		return this;
	}

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LBoolFunction<R> nonNullBoolFunc() {
		return this::nonNullDoApply;
	}

	/** Does nothing (LBoolFunction) Function */
	public static <R> R produce(boolean a) {
		return (R) Function4U.defaultObject;
	}

	// MAP: FOR, [SourcePurpose{arg=boolean a, type=IA}, SourcePurpose{arg=LConsumer<? super R> consumer, type=CONST}]
	default <C0> void forEach(IndexedRead<C0, aBool> ia, C0 source, LConsumer<? super R> consumer) {
		int size = ia.size(source);
		LObjIntPredicate<Object> oiFunc0 = (LObjIntPredicate) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			boolean a = oiFunc0.doTest(source, i);
			consumer.doAccept(this.doApply(a));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=boolean a, type=SA}, SourcePurpose{arg=LConsumer<? super R> consumer, type=CONST}]
	default <C0, I0> void iterate(SequentialRead<C0, I0, aBool> sa, C0 source, LConsumer<? super R> consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).doApply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LPredicate<Object> nextFunc0 = (LPredicate) sa.getter();
		while (testFunc0.doTest(iterator0)) {
			boolean a = nextFunc0.doTest(iterator0);
			consumer.doAccept(this.doApply(a));
		}
	}

}
