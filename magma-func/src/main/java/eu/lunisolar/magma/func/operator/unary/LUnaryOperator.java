/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2019 Lunisolar (http://lunisolar.eu/).
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
public interface LUnaryOperator<T> extends UnaryOperator<T>, MetaOperator, MetaInterface.NonThrowing, OFunction<T, a<T>>, Codomain<a<T>>, Domain1<a<T>>, LFunction<T, T> { // NOSONAR

	String DESCRIPTION = "LUnaryOperator: T apply(T a)";

	default T tupleApply(LSingle<T> args) {
		return apply(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default T handlingApply(T a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LUnaryOperator<T> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> handlingApply(a, handling);
	}

	default T apply(T a, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LUnaryOperator<T> trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return a -> apply(a, exF, newMessage, messageParams);
	}

	default T apply(T a, @Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LUnaryOperator<T> trying(@Nonnull ExWF<RuntimeException> exF) {
		return a -> apply(a, exF);
	}

	default T applyThen(T a, @Nonnull LFunction<Throwable, T> handler) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.apply(e);
		}
	}

	default LUnaryOperator<T> tryingThen(@Nonnull LFunction<Throwable, T> handler) {
		return a -> applyThen(a, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default T nestingApply(T a) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default T shovingApply(T a) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> T handlingApply(T a, LUnaryOperator<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApply(a, handling);
	}

	static <T> T tryApply(T a, LUnaryOperator<T> func) {
		Null.nonNullArg(func, "func");
		return func.nestingApply(a);
	}

	static <T> T tryApply(T a, LUnaryOperator<T> func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.apply(a, exF, newMessage, messageParams);
	}

	static <T> T tryApply(T a, LUnaryOperator<T> func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.apply(a, exF);
	}

	static <T> T tryApplyThen(T a, LUnaryOperator<T> func, @Nonnull LFunction<Throwable, T> handler) {
		Null.nonNullArg(func, "func");
		return func.applyThen(a, handler);
	}

	default T failSafeApply(T a, @Nonnull LUnaryOperator<T> failSafe) {
		try {
			return apply(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.apply(a);
		}
	}

	static <T> T failSafeApply(T a, LUnaryOperator<T> func, @Nonnull LUnaryOperator<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.apply(a);
		} else {
			return func.failSafeApply(a, failSafe);
		}
	}

	static <T> LUnaryOperator<T> failSafe(LUnaryOperator<T> func, @Nonnull LUnaryOperator<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeApply(a, func, failSafe);
	}

	LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullApply() method cannot be null (" + DESCRIPTION + ").";

	/** Function call that ensures the result is not null */
	@Nonnull
	default T nonNullApply(T a) {
		return Null.nonNull(apply(a), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LUnaryOperator.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_i, int max_i, T a, @Nonnull LUnaryOperator<T> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.apply(a);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.apply(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTill(int min_i, int max_i, T a, @Nonnull LUnaryOperator<T> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.apply(a);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.apply(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void times(int max_i, T a, @Nonnull LUnaryOperator<T> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a, func);
	}

	/** Extract and apply function. */
	public static <T, M, K, V> T from(@Nonnull M container, LBiFunction<M, K, V> extractor, K key, @Nonnull LFunction<V, T> function) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.apply(value);
		}

		return null;
	}

	/** Cast that removes generics. */
	public default LUnaryOperator untyped() {
		return this;
	}

	/** Cast that replace generics. */
	public default LUnaryOperator cast() {
		return untyped();
	}

	/** Cast that replace generics. */
	public static <T> LUnaryOperator cast(LUnaryOperator<T> function) {
		return (LUnaryOperator) function;
	}

	/** Change function to consumer that ignores output. */
	public default LConsumer<T> toConsumer() {
		return this::apply;
	}

	/** Calls domain consumer before main function. */
	public default LUnaryOperator<T> beforeDo(@Nonnull LConsumer<T> before) {
		Null.nonNullArg(before, "before");
		return (T a) -> {
			before.accept(a);
			return apply(a);
		};
	}

	/** Calls codomain consumer after main function. */
	public default LUnaryOperator<T> afterDo(@Nonnull LConsumer<T> after) {
		Null.nonNullArg(after, "after");
		return (T a) -> {
			final T retval = apply(a);
			after.accept(retval);
			return retval;
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplier<T> capture(T a) {
		return () -> this.apply(a);
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

	/** A completely inconvenient method in case lambda expression and generic arguments are ambiguous for the compiler. */
	@Nonnull
	static <T> LUnaryOperator<T> unaryOp(@Nullable Class<T> c1, final @Nonnull LUnaryOperator<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T> LUnaryOperator<T> recursive(final @Nonnull LFunction<LUnaryOperator<T>, LUnaryOperator<T>> selfLambda) {
		final LUnaryOperatorSingle<T> single = new LUnaryOperatorSingle();
		LUnaryOperator<T> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LUnaryOperatorSingle<T> implements LSingle<LUnaryOperator<T>>, LUnaryOperator<T> {
		private LUnaryOperator<T> target = null;

		@Override
		public T applyX(T a) throws Throwable {
			return target.applyX(a);
		}

		@Override
		public LUnaryOperator<T> value() {
			return target;
		}
	}

	@Nonnull
	static <T> LUnaryOperator<T> unaryOpThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T> LUnaryOperator<T> unaryOpThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce(message);
		};
	}

	static <T> T call(T a, final @Nonnull LUnaryOperator<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.apply(a);
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
		return a -> after.apply(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToByteFunction<T> thenToByte(@Nonnull LToByteFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsByte(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToSrtFunction<T> thenToSrt(@Nonnull LToSrtFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsSrt(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToIntFunction<T> thenToInt(@Nonnull LToIntFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsInt(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToLongFunction<T> thenToLong(@Nonnull LToLongFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsLong(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToFltFunction<T> thenToFlt(@Nonnull LToFltFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsFlt(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToDblFunction<T> thenToDbl(@Nonnull LToDblFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsDbl(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToCharFunction<T> thenToChar(@Nonnull LToCharFunction<? super T> after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsChar(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LPredicate<T> thenToBool(@Nonnull LPredicate<? super T> after) {
		Null.nonNullArg(after, "after");
		return a -> after.test(this.apply(a));
	}

	// </editor-fold>

	/** Returns a function that always returns its input argument. */
	@Nonnull
	static <V> LUnaryOperator<V> identity() {
		return t -> t;
	}

	static <T> T identity(T a) {
		return a;
	}

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LUnaryOperator<T> nonNullable() {
		return this::nonNullApply;
	}

	/** Does nothing (LUnaryOperator) Operator */
	public static <T> T produce(T a) {
		return (T) Function4U.defaultObject;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C0> void forEach(IndexedRead<C0, a<T>> ia, C0 source, LConsumer<? super T> consumer) {
		int size = ia.size(source);
		LOiFunction<Object, T> oiFunc0 = (LOiFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			T a = oiFunc0.apply(source, i);
			consumer.accept(this.apply(a));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C0, I0> void iterate(SequentialRead<C0, I0, a<T>> sa, C0 source, LConsumer<? super T> consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).apply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LFunction<Object, T> nextFunc0 = (LFunction) sa.supplier();
		while (testFunc0.test(iterator0)) {
			T a = nextFunc0.apply(iterator0);
			consumer.accept(this.apply(a));
		}
	}

}
