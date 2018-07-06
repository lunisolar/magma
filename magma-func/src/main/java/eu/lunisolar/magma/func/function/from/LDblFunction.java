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
 * Non-throwing functional interface (lambda) LDblFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): double a
 *
 * Co-domain: R
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LDblFunction<R> extends DoubleFunction<R>, MetaFunction, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LDblFunction: R doApply(double a)";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LDblFunction interface should be discouraged.
	 */
	@Override
	@Deprecated
	default R apply(double a) {
		return this.doApply(a);
	}

	@Nullable
	// R doApply(double a) ;
	default R doApply(double a) {
		// return nestingDoApply(a);
		try {
			return this.doApplyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doApply(double a)
	 */
	R doApplyX(double a) throws Throwable;

	default R tupleApply(LDblSingle args) {
		return doApply(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default R handlingDoApply(double a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default R tryDoApply(double a, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default R tryDoApply(double a, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default R tryDoApplyThen(double a, @Nonnull LFunction<Throwable, R> handler) {
		try {
			return this.doApplyX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApply(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default R nestingDoApply(double a) {
		try {
			return this.doApplyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default R shovingDoApply(double a) {
		try {
			return this.doApplyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <R> R handlingDoApply(double a, LDblFunction<R> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApply(a, handling);
	}

	static <R> R tryDoApply(double a, LDblFunction<R> func) {
		return tryDoApply(a, func, null);
	}

	static <R> R tryDoApply(double a, LDblFunction<R> func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApply(a, exceptionFactory, newMessage, messageParams);
	}

	static <R> R tryDoApply(double a, LDblFunction<R> func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApply(a, exceptionFactory);
	}

	static <R> R tryDoApplyThen(double a, LDblFunction<R> func, @Nonnull LFunction<Throwable, R> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyThen(a, handler);
	}

	default R failSafeDoApply(double a, @Nonnull LDblFunction<R> failSafe) {
		try {
			return doApply(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApply(a);
		}
	}

	static <R> R failSafeDoApply(double a, LDblFunction<R> func, @Nonnull LDblFunction<R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApply(a);
		} else {
			return func.failSafeDoApply(a, failSafe);
		}
	}

	static <R> LDblFunction<R> failSafeDblFunc(LDblFunction<R> func, @Nonnull LDblFunction<R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeDoApply(a, func, failSafe);
	}

	LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullDoApply() method cannot be null (" + DESCRIPTION + ").";

	/** Function call that ensures the result is not null */
	@Nonnull
	default R nonNullDoApply(double a) {
		return Null.requireNonNull(doApply(a), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LDblFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <R> void fromTo(int min_i, int max_i, double a, LDblFunction<R> func) {
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
	public static <R> void fromTill(int min_i, int max_i, double a, LDblFunction<R> func) {
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
	public static <R> void times(int max_i, double a, LDblFunction<R> func) {
		fromTill(0, max_i, a, func);
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplier<R> captureDblFunc(double a) {
		return () -> this.doApply(a);
	}

	/** Creates function that always returns the same value. */
	static <R> LDblFunction<R> constant(R r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <R> LDblFunction<R> dblFunc(final @Nonnull LDblFunction<R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <R> LDblFunction<R> recursive(final @Nonnull LFunction<LDblFunction<R>, LDblFunction<R>> selfLambda) {
		final LDblFunctionSingle<R> single = new LDblFunctionSingle();
		LDblFunction<R> func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LDblFunctionSingle<R> implements LSingle<LDblFunction<R>>, LDblFunction<R> {
		private LDblFunction<R> target = null;

		@Override
		public R doApplyX(double a) throws Throwable {
			return target.doApplyX(a);
		}

		@Override
		public LDblFunction<R> value() {
			return target;
		}
	}

	@Nonnull
	static <R> LDblFunction<R> dblFuncThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static <R> LDblFunction<R> dblFuncThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce(message);
		};
	}

	static <R> R call(double a, final @Nonnull LDblFunction<R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApply(a);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <R> LDblFunction<R> wrap(final DoubleFunction<R> other) {
		return other::apply;
	}
	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produce). */
	@Nonnull
	static <R> LDblFunction<R> safe() {
		return LDblFunction::produce;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <R> LSupplier<LDblFunction<R>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <R> LDblFunction<R> safe(final @Nullable LDblFunction<R> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <R> LSupplier<LDblFunction<R>> safeSupplier(final @Nullable LSupplier<LDblFunction<R>> supplier) {
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
	default LDblFunction<R> dblFuncComposeDbl(@Nonnull final LDblUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApply(before.doApplyAsDbl(v));
	}

	public static <R> LDblFunction<R> composedDbl(@Nonnull final LDblUnaryOperator before, LDblFunction<R> after) {
		return after.dblFuncComposeDbl(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LFunction<V, R> dblFuncCompose(@Nonnull final LToDblFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApply(before.doApplyAsDbl(v));
	}

	public static <V, R> LFunction<V, R> composed(@Nonnull final LToDblFunction<? super V> before, LDblFunction<R> after) {
		return after.dblFuncCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LDblFunction<V> then(@Nonnull LFunction<? super R, ? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApply(this.doApply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblConsumer thenConsume(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doAccept(this.doApply(a));
	}

	@Nonnull
	default LDblFunction<R> before(@Nonnull LDblConsumer before) {
		Null.nonNullArg(before, "before");
		return a -> {
			before.doAccept(a);
			return this.doApply(a);
		};
	}

	@Nonnull
	default LDblFunction<R> after(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> {
			R result = this.doApply(a);
			after.doAccept(result);
			return result;
		};
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToByteFunction thenToByte(@Nonnull LToByteFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsByte(this.doApply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToSrtFunction thenToSrt(@Nonnull LToSrtFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsSrt(this.doApply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToIntFunction thenToInt(@Nonnull LToIntFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsInt(this.doApply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToLongFunction thenToLong(@Nonnull LToLongFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsLong(this.doApply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToFltFunction thenToFlt(@Nonnull LToFltFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsFlt(this.doApply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblUnaryOperator thenToDbl(@Nonnull LToDblFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsDbl(this.doApply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToCharFunction thenToChar(@Nonnull LToCharFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsChar(this.doApply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblPredicate thenToBool(@Nonnull LPredicate<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doTest(this.doApply(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LDblFunction<R> nestingDblFunc() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LDblFunction<R> shovingDblFunc() {
		return this;
	}

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LDblFunction<R> nonNullDblFunc() {
		return this::nonNullDoApply;
	}

	/** Does nothing (LDblFunction) Function */
	public static <R> R produce(double a) {
		return (R) Function4U.defaultObject;
	}

	// MAP: FOR, [SourcePurpose{arg=double a, type=IA}, SourcePurpose{arg=LConsumer<? super R> consumer, type=CONST}]
	default <C0> void forEach(IndexedRead<C0, aDouble> ia, C0 source, LConsumer<? super R> consumer) {
		int size = ia.size(source);
		LOiToDblFunction<Object> oiFunc0 = (LOiToDblFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			double a = oiFunc0.doApplyAsDbl(source, i);
			consumer.doAccept(this.doApply(a));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=double a, type=SA}, SourcePurpose{arg=LConsumer<? super R> consumer, type=CONST}]
	default <C0, I0> void iterate(SequentialRead<C0, I0, aDouble> sa, C0 source, LConsumer<? super R> consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).doApply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToDblFunction<Object> nextFunc0 = (LToDblFunction) sa.getter();
		while (testFunc0.doTest(iterator0)) {
			double a = nextFunc0.doApplyAsDbl(iterator0);
			consumer.doAccept(this.doApply(a));
		}
	}

}
