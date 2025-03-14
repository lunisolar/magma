/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2023 Lunisolar (http://lunisolar.eu/).
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
import javax.annotation.concurrent.NotThreadSafe; // NOSONAR
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

import java.util.concurrent.*; // NOSONAR
import java.util.function.*; // NOSONAR
import java.util.*; // NOSONAR
import java.lang.reflect.*; // NOSONAR
import java.util.stream.Stream; // NOSONAR

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
 * Non-throwing functional interface (lambda) LLongFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): long a
 *
 * Co-domain: R
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LLongFunction<R> extends LongFunction<R>, MetaFunction, MetaInterface.NonThrowing, Codomain<a<R>>, Domain1<aLong> { //NOSONAR

	String DESCRIPTION = "LLongFunction: R apply(long a)";

	@Nullable
	default R apply(long a) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call apply(long a)
	 */
	R applyX(long a) throws Throwable;

	default R tupleApply(LLongSingle args) {
		return apply(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default R handlingApply(long a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LLongFunction<R> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> handlingApply(a, handling);
	}

	default R apply(long a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default R apply(long a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default R apply(long a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default R apply(long a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LLongFunction<R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return a -> apply(a, factory, newMessage);
	}

	default LLongFunction<R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return a -> apply(a, factory, newMessage, param1);
	}

	default LLongFunction<R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return a -> apply(a, factory, newMessage, param1, param1);
	}

	default LLongFunction<R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return a -> apply(a, factory, newMessage, param1, param2, param3);
	}

	default R apply(long a, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LLongFunction<R> trying(@Nonnull ExWF<RuntimeException> factory) {
		return a -> apply(a, factory);
	}

	default R applyThen(long a, @Nonnull LFunction<Throwable, R> handler) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { //NOSONAR
			Handling.handleErrors(e);
			return handler.apply(e);
		}
	}

	default LLongFunction<R> tryingThen(@Nonnull LFunction<Throwable, R> handler) {
		return a -> applyThen(a, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default R nestingApply(long a) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default R shovingApply(long a) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { //NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <R> R shovingApply(long a, LLongFunction<R> func) {
		Null.nonNullArg(func, "func");
		return func.shovingApply(a);
	}

	static <R> R handlingApply(long a, LLongFunction<R> func, HandlingInstructions<Throwable, RuntimeException> handling) { //<-
		Null.nonNullArg(func, "func");
		return func.handlingApply(a, handling);
	}

	static <R> R tryApply(long a, LLongFunction<R> func) {
		Null.nonNullArg(func, "func");
		return func.nestingApply(a);
	}

	static <R> R tryApply(long a, LLongFunction<R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.apply(a, factory, newMessage);
	}

	static <R> R tryApply(long a, LLongFunction<R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.apply(a, factory, newMessage, param1);
	}

	static <R> R tryApply(long a, LLongFunction<R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.apply(a, factory, newMessage, param1, param2);
	}

	static <R> R tryApply(long a, LLongFunction<R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.apply(a, factory, newMessage, param1, param2, param3);
	}

	static <R> R tryApply(long a, LLongFunction<R> func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.apply(a, factory);
	}

	static <R> R tryApplyThen(long a, LLongFunction<R> func, @Nonnull LFunction<Throwable, R> handler) {
		Null.nonNullArg(func, "func");
		return func.applyThen(a, handler);
	}

	LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullApply() method cannot be null (" + DESCRIPTION + ").";

	/** Function call that ensures the result is not null */
	@Nonnull
	default R nonNullApply(long a) {
		return Null.nonNull(apply(a), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LLongFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <R> void fromTo(long min_a, long max_a, @Nonnull LLongFunction<R> func) {
		Null.nonNullArg(func, "func");
		if (min_a <= max_a) {
			for (long a = min_a; a <= max_a; a++) {
				func.apply(a);
			}
		} else {
			for (long a = min_a; a >= max_a; a--) {
				func.apply(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <R> void fromTill(long min_a, long max_a, @Nonnull LLongFunction<R> func) {
		Null.nonNullArg(func, "func");
		if (min_a <= max_a) {
			for (long a = min_a; a < max_a; a++) {
				func.apply(a);
			}
		} else {
			for (long a = min_a; a > max_a; a--) {
				func.apply(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <R> void times(long max_a, @Nonnull LLongFunction<R> func) {
		if (max_a < 0)
			return;
		fromTill(0, max_a, func);
	}

	/** Change function to one that ignores output. */
	default LLongConsumer toConsumer() {
		return this::apply;
	}

	/** Calls domain consumer before main function. */
	default LLongFunction<R> beforeDo(@Nonnull LLongConsumer before) {
		Null.nonNullArg(before, "before");
		return (long a) -> {
			before.accept(a);
			return apply(a);
		};
	}

	/** Calls codomain consumer after main function. */
	default LLongFunction<R> afterDo(@Nonnull LConsumer<R> after) {
		Null.nonNullArg(after, "after");
		return (long a) -> {
			final R retval = apply(a);
			after.accept(retval);
			return retval;
		};
	}

	/** Creates function that always returns the same value. */
	static <R> LLongFunction<R> constant(R r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <R> LLongFunction<R> longFunc(final @Nonnull LLongFunction<R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S<R> implements LLongFunction<R> {
		private LLongFunction<R> target = null;
		@Override
		public R applyX(long a) throws Throwable {
			return target.applyX(a);
		}
	}

	@Nonnull
	static <R> LLongFunction<R> recursive(final @Nonnull LFunction<LLongFunction<R>, LLongFunction<R>> selfLambda) {
		final S<R> single = new S();
		LLongFunction<R> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static <R> M<R> mementoOf(long a, LLongFunction<R> function) {
		var initialValue = function.apply(a);
		return initializedMementoOf(initialValue, function);
	}

	public static <R> M<R> initializedMementoOf(R initialValue, LLongFunction<R> function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static <R> M<R> deltaOf(long a, LLongFunction<R> function, LBinaryOperator<R> deltaFunction) {
		var initialValue = function.apply(a);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static <R> M<R> initializedDeltaOf(R initialValue, LLongFunction<R> function, LBinaryOperator<R> deltaFunction) {
		return memento(initialValue, deltaFunction.apply(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.apply(x1, x2));
	}

	public static <R> M<R> memento(R initialBaseValue, R initialValue, LLongFunction<R> baseFunction, LTernaryOperator<R> mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LLongFunction.M)
	 */
	@NotThreadSafe
	final class M<R> implements LLongFunction<R> {

		private final LLongFunction<R> baseFunction;
		private R lastBaseValue;
		private R lastValue;
		private final LTernaryOperator<R> mementoFunction;

		private M(R lastBaseValue, R lastValue, LLongFunction<R> baseFunction, LTernaryOperator<R> mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public R applyX(long a) throws Throwable {
			R x1 = lastBaseValue;
			R x2 = lastBaseValue = baseFunction.applyX(a);

			return lastValue = mementoFunction.applyX(lastValue, x1, x2);
		}

		public R currentApply(long a) {
			R x1 = lastBaseValue;
			R x2 = baseFunction.apply(a);

			return mementoFunction.apply(lastValue, x1, x2);
		}

		public R lastValue() {
			return lastValue;
		}

		public R lastBaseValue() {
			return lastBaseValue;
		}

		public R currentBaseValue(long a) {
			return baseFunction.apply(a);
		}
	}

	// </editor-fold>

	@Nonnull
	static <R> LLongFunction<R> longFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <R> LLongFunction<R> longFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce(message);
		};
	}

	static <R> R call(long a, final @Nonnull LLongFunction<R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.apply(a);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <R> LLongFunction<R> wrap(final LongFunction<R> other) {
		return other::apply;
	}
	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LLongFunction<R> compose(@Nonnull final LLongUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.apply(before.applyAsLong(v));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LFunction<V, R> unboxingCompose(@Nonnull final LToLongFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.apply(before.applyAsLong(v));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LLongFunction<V> then(@Nonnull LFunction<? super R, ? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.apply(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongConsumer thenConsume(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.accept(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToByteFunction thenToByte(@Nonnull LToByteFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsByte(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToSrtFunction thenToSrt(@Nonnull LToSrtFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsSrt(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToIntFunction thenToInt(@Nonnull LToIntFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsInt(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongUnaryOperator thenToLong(@Nonnull LToLongFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsLong(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToFltFunction thenToFlt(@Nonnull LToFltFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsFlt(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToDblFunction thenToDbl(@Nonnull LToDblFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsDbl(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongToCharFunction thenToChar(@Nonnull LToCharFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsChar(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongPredicate thenToBool(@Nonnull LPredicate<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.test(this.apply(a));
	}

	// </editor-fold>

	default LLongFunction<R> shoving() {

		return new LLongFunction<R>() {

			@Nullable
			public R apply(long a) {
				try {
					return this.applyX(a);
				} catch (Throwable e) { // NOSONAR
					Handling.handleErrors(e);
					throw Handling.throwIt(e);
				}
			}

			public R applyX(long a) throws Throwable {
				return LLongFunction.this.applyX(a);
			}

		};
	}

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LLongFunction<R> nonNullable() {
		return this::nonNullApply;
	}

	/** Does nothing (LLongFunction) Function */
	public static <R> R doNothing(long a) {
		return (R) Function4U.defaultObject;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C0> void forEach(IndexedRead<C0, aLong> ia, C0 source, LConsumer<? super R> consumer) {
		int size = ia.size(source);
		var oiFunc0 = IA.longGetter(ia);
		int i = 0;
		for (; i < size; i++) {
			long a = oiFunc0.applyAsLong(source, i);
			consumer.accept(this.apply(a));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C0, I0> void iterate(SequentialRead<C0, I0, aLong> sa, C0 source, LConsumer<? super R> consumer) {
		var iterator0 = SA.adapter(sa).apply(source);
		var testFunc0 = SA.tester(sa);
		var nextFunc0 = SA.longSupplier(sa);
		while (testFunc0.test(iterator0)) {
			long a = nextFunc0.applyAsLong(iterator0);
			consumer.accept(this.apply(a));
		}
	}

}
