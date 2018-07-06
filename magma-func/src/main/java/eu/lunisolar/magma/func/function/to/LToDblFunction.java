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
 * Non-throwing functional interface (lambda) LToDblFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): T a
 *
 * Co-domain: double
 *
 * Special case of function that corresponds to expressions like (iterator) -> Iterator::next
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LToDblFunction<T> extends ToDoubleFunction<T>, MetaFunction, MetaInterface.NonThrowing, OFunction<T, aDouble> { // NOSONAR

	String DESCRIPTION = "LToDblFunction: double doApplyAsDbl(T a)";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LToDblFunction interface should be discouraged.
	 */
	@Override
	@Deprecated
	default double applyAsDouble(T a) {
		return this.doApplyAsDbl(a);
	}

	// double doApplyAsDbl(T a) ;
	default double doApplyAsDbl(T a) {
		// return nestingDoApplyAsDbl(a);
		try {
			return this.doApplyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doApplyAsDbl(T a)
	 */
	double doApplyAsDblX(T a) throws Throwable;

	default double tupleApplyAsDbl(LSingle<T> args) {
		return doApplyAsDbl(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default double handlingDoApplyAsDbl(T a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default double tryDoApplyAsDbl(T a, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default double tryDoApplyAsDbl(T a, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default double tryDoApplyAsDblThen(T a, @Nonnull LToDblFunction<Throwable> handler) {
		try {
			return this.doApplyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApplyAsDbl(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default double nestingDoApplyAsDbl(T a) {
		try {
			return this.doApplyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default double shovingDoApplyAsDbl(T a) {
		try {
			return this.doApplyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> double handlingDoApplyAsDbl(T a, LToDblFunction<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApplyAsDbl(a, handling);
	}

	static <T> double tryDoApplyAsDbl(T a, LToDblFunction<T> func) {
		return tryDoApplyAsDbl(a, func, null);
	}

	static <T> double tryDoApplyAsDbl(T a, LToDblFunction<T> func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsDbl(a, exceptionFactory, newMessage, messageParams);
	}

	static <T> double tryDoApplyAsDbl(T a, LToDblFunction<T> func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsDbl(a, exceptionFactory);
	}

	static <T> double tryDoApplyAsDblThen(T a, LToDblFunction<T> func, @Nonnull LToDblFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsDblThen(a, handler);
	}

	default double failSafeDoApplyAsDbl(T a, @Nonnull LToDblFunction<T> failSafe) {
		try {
			return doApplyAsDbl(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApplyAsDbl(a);
		}
	}

	static <T> double failSafeDoApplyAsDbl(T a, LToDblFunction<T> func, @Nonnull LToDblFunction<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApplyAsDbl(a);
		} else {
			return func.failSafeDoApplyAsDbl(a, failSafe);
		}
	}

	static <T> LToDblFunction<T> failSafeToDblFunc(LToDblFunction<T> func, @Nonnull LToDblFunction<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeDoApplyAsDbl(a, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default double nonNullDoApplyAsDbl(T a) {
		return doApplyAsDbl(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LToDblFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_i, int max_i, T a, LToDblFunction<T> func) {
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
	public static <T> void fromTill(int min_i, int max_i, T a, LToDblFunction<T> func) {
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
	public static <T> void times(int max_i, T a, LToDblFunction<T> func) {
		fromTill(0, max_i, a, func);
	}

	/** Captures arguments but delays the evaluation. */
	default LDblSupplier captureToDblFunc(T a) {
		return () -> this.doApplyAsDbl(a);
	}

	/** Creates function that always returns the same value. */
	static <T> LToDblFunction<T> constant(double r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LToDblFunction<T> toDblFunc(final @Nonnull LToDblFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T> LToDblFunction<T> recursive(final @Nonnull LFunction<LToDblFunction<T>, LToDblFunction<T>> selfLambda) {
		final LToDblFunctionSingle<T> single = new LToDblFunctionSingle();
		LToDblFunction<T> func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LToDblFunctionSingle<T> implements LSingle<LToDblFunction<T>>, LToDblFunction<T> {
		private LToDblFunction<T> target = null;

		@Override
		public double doApplyAsDblX(T a) throws Throwable {
			return target.doApplyAsDblX(a);
		}

		@Override
		public LToDblFunction<T> value() {
			return target;
		}
	}

	@Nonnull
	static <T> LToDblFunction<T> toDblFuncThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static <T> LToDblFunction<T> toDblFuncThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce(message);
		};
	}

	static <T> double call(T a, final @Nonnull LToDblFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsDbl(a);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <T> LToDblFunction<T> wrap(final ToDoubleFunction<T> other) {
		return other::applyAsDouble;
	}
	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceDouble). */
	@Nonnull
	static <T> LToDblFunction<T> safe() {
		return LToDblFunction::produceDouble;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LToDblFunction<T>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T> LToDblFunction<T> safe(final @Nullable LToDblFunction<T> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LToDblFunction<T>> safeSupplier(final @Nullable LSupplier<LToDblFunction<T>> supplier) {
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
	default <V> LToDblFunction<V> toDblFuncCompose(@Nonnull final LFunction<? super V, ? extends T> before) {
		Null.nonNullArg(before, "before");
		return v -> this.doApplyAsDbl(before.doApply(v));
	}

	public static <V, T> LToDblFunction<V> composed(@Nonnull final LFunction<? super V, ? extends T> before, LToDblFunction<T> after) {
		return after.toDblFuncCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LFunction<T, V> then(@Nonnull LDblFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApply(this.doApplyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToByteFunction<T> thenToByte(@Nonnull LDblToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsByte(this.doApplyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToSrtFunction<T> thenToSrt(@Nonnull LDblToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsSrt(this.doApplyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToIntFunction<T> thenToInt(@Nonnull LDblToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsInt(this.doApplyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToLongFunction<T> thenToLong(@Nonnull LDblToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsLong(this.doApplyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToFltFunction<T> thenToFlt(@Nonnull LDblToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsFlt(this.doApplyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToDblFunction<T> thenToDbl(@Nonnull LDblUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsDbl(this.doApplyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToCharFunction<T> thenToChar(@Nonnull LDblToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsChar(this.doApplyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LPredicate<T> thenToBool(@Nonnull LDblPredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.doTest(this.doApplyAsDbl(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LToDblFunction<T> nestingToDblFunc() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LToDblFunction<T> shovingToDblFunc() {
		return this;
	}

	// </editor-fold>

	/** Does nothing (LToDblFunction) Function */
	public static <T> double produceDouble(T a) {
		return Function4U.defaultDouble;
	}

	// MAP: FOR, [SourcePurpose{arg=T a, type=IA}, SourcePurpose{arg=LDblConsumer consumer, type=CONST}]
	default <C0> void forEach(IndexedRead<C0, a<T>> ia, C0 source, LDblConsumer consumer) {
		int size = ia.size(source);
		LOiFunction<Object, T> oiFunc0 = (LOiFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			T a = oiFunc0.doApply(source, i);
			consumer.doAccept(this.doApplyAsDbl(a));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T a, type=SA}, SourcePurpose{arg=LDblConsumer consumer, type=CONST}]
	default <C0, I0> void iterate(SequentialRead<C0, I0, a<T>> sa, C0 source, LDblConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).doApply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LFunction<Object, T> nextFunc0 = (LFunction) sa.getter();
		while (testFunc0.doTest(iterator0)) {
			T a = nextFunc0.doApply(iterator0);
			consumer.doAccept(this.doApplyAsDbl(a));
		}
	}

}
