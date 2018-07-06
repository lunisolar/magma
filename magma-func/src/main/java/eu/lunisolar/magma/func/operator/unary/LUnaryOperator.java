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
 * Non-throwing functional interface (lambda) LUnaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 1): T a
 *
 * Co-domain: T
 *
 * Special case of function that corresponds to expressions like (iterator) -> Iterator::next
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LUnaryOperator<T> extends UnaryOperator<T>, MetaOperator, MetaInterface.NonThrowing, OFunction<T, a<T>>, LFunction<T, T> { // NOSONAR

	String DESCRIPTION = "LUnaryOperator: T doApply(T a)";

	default T tupleApply(LSingle<T> args) {
		return doApply(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default T handlingDoApply(T a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default T tryDoApply(T a, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default T tryDoApply(T a, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default T tryDoApplyThen(T a, @Nonnull LFunction<Throwable, T> handler) {
		try {
			return this.doApplyX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApply(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default T nestingDoApply(T a) {
		try {
			return this.doApplyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default T shovingDoApply(T a) {
		try {
			return this.doApplyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> T handlingDoApply(T a, LUnaryOperator<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApply(a, handling);
	}

	static <T> T tryDoApply(T a, LUnaryOperator<T> func) {
		return tryDoApply(a, func, null);
	}

	static <T> T tryDoApply(T a, LUnaryOperator<T> func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApply(a, exceptionFactory, newMessage, messageParams);
	}

	static <T> T tryDoApply(T a, LUnaryOperator<T> func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApply(a, exceptionFactory);
	}

	static <T> T tryDoApplyThen(T a, LUnaryOperator<T> func, @Nonnull LFunction<Throwable, T> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyThen(a, handler);
	}

	default T failSafeDoApply(T a, @Nonnull LUnaryOperator<T> failSafe) {
		try {
			return doApply(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApply(a);
		}
	}

	static <T> T failSafeDoApply(T a, LUnaryOperator<T> func, @Nonnull LUnaryOperator<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApply(a);
		} else {
			return func.failSafeDoApply(a, failSafe);
		}
	}

	static <T> LUnaryOperator<T> failSafeUnaryOp(LUnaryOperator<T> func, @Nonnull LUnaryOperator<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeDoApply(a, func, failSafe);
	}

	LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullDoApply() method cannot be null (" + DESCRIPTION + ").";

	/** Function call that ensures the result is not null */
	@Nonnull
	default T nonNullDoApply(T a) {
		return Null.requireNonNull(doApply(a), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LUnaryOperator.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_i, int max_i, T a, LUnaryOperator<T> func) {
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
	public static <T> void fromTill(int min_i, int max_i, T a, LUnaryOperator<T> func) {
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
	public static <T> void times(int max_i, T a, LUnaryOperator<T> func) {
		fromTill(0, max_i, a, func);
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplier<T> captureUnaryOp(T a) {
		return () -> this.doApply(a);
	}

	/** Creates function that always returns the same value. */
	static <T> LUnaryOperator<T> constant(T r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LUnaryOperator<T> unaryOp(final @Nonnull LUnaryOperator<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T> LUnaryOperator<T> recursive(final @Nonnull LFunction<LUnaryOperator<T>, LUnaryOperator<T>> selfLambda) {
		final LUnaryOperatorSingle<T> single = new LUnaryOperatorSingle();
		LUnaryOperator<T> func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LUnaryOperatorSingle<T> implements LSingle<LUnaryOperator<T>>, LUnaryOperator<T> {
		private LUnaryOperator<T> target = null;

		@Override
		public T doApplyX(T a) throws Throwable {
			return target.doApplyX(a);
		}

		@Override
		public LUnaryOperator<T> value() {
			return target;
		}
	}

	@Nonnull
	static <T> LUnaryOperator<T> unaryOpThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static <T> LUnaryOperator<T> unaryOpThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return a -> {
			throw exceptionFactory.produce(message);
		};
	}

	static <T> T call(T a, final @Nonnull LUnaryOperator<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApply(a);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <T> LUnaryOperator<T> wrap(final UnaryOperator<T> other) {
		return other::apply;
	}
	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produce). */
	@Nonnull
	static <T> LUnaryOperator<T> safe() {
		return LUnaryOperator::produce;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LUnaryOperator<T>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T> LUnaryOperator<T> safe(final @Nullable LUnaryOperator<T> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LUnaryOperator<T>> safeSupplier(final @Nullable LSupplier<LUnaryOperator<T>> supplier) {
		if (supplier == null) {
			return safeSupplier();
		} else {
			return supplier;
		}
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LFunction<T, V> then(@Nonnull LFunction<? super T, ? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApply(this.doApply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToByteFunction<T> thenToByte(@Nonnull LToByteFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsByte(this.doApply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToSrtFunction<T> thenToSrt(@Nonnull LToSrtFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsSrt(this.doApply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToIntFunction<T> thenToInt(@Nonnull LToIntFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsInt(this.doApply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToLongFunction<T> thenToLong(@Nonnull LToLongFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsLong(this.doApply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToFltFunction<T> thenToFlt(@Nonnull LToFltFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsFlt(this.doApply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToDblFunction<T> thenToDbl(@Nonnull LToDblFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsDbl(this.doApply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToCharFunction<T> thenToChar(@Nonnull LToCharFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doApplyAsChar(this.doApply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LPredicate<T> thenToBool(@Nonnull LPredicate<? super T> after) {
		Null.nonNullArg(after, "after");
		return a -> after.doTest(this.doApply(a));
	}

	// </editor-fold>

	/** Returns a function that always returns its input argument. */
	@Nonnull
	static <V> LUnaryOperator<V> identity() {
		return t -> t;
	}

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LUnaryOperator<T> nestingUnaryOp() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LUnaryOperator<T> shovingUnaryOp() {
		return this;
	}

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LUnaryOperator<T> nonNullUnaryOp() {
		return this::nonNullDoApply;
	}

	/** Does nothing (LUnaryOperator) Operator */
	public static <T> T produce(T a) {
		return (T) Function4U.defaultObject;
	}

	// MAP: FOR, [SourcePurpose{arg=T a, type=IA}, SourcePurpose{arg=LConsumer<? super T> consumer, type=CONST}]
	default <C0> void forEach(IndexedRead<C0, a<T>> ia, C0 source, LConsumer<? super T> consumer) {
		int size = ia.size(source);
		LOiFunction<Object, T> oiFunc0 = (LOiFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			T a = oiFunc0.doApply(source, i);
			consumer.doAccept(this.doApply(a));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T a, type=SA}, SourcePurpose{arg=LConsumer<? super T> consumer, type=CONST}]
	default <C0, I0> void iterate(SequentialRead<C0, I0, a<T>> sa, C0 source, LConsumer<? super T> consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).doApply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LFunction<Object, T> nextFunc0 = (LFunction) sa.getter();
		while (testFunc0.doTest(iterator0)) {
			T a = nextFunc0.doApply(iterator0);
			consumer.doAccept(this.doApply(a));
		}
	}

}
