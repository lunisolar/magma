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
 * Non-throwing functional interface (lambda) LTriLongFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 3): long a1,long a2,long a3
 *
 * Co-domain: R
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LTriLongFunction<R> extends MetaFunction, MetaInterface.NonThrowing, Codomain<a<R>>, Domain3<aLong, aLong, aLong> { //NOSONAR

	String DESCRIPTION = "LTriLongFunction: R apply(long a1,long a2,long a3)";

	@Nullable
	default R apply(long a1, long a2, long a3) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call apply(long a1,long a2,long a3)
	 */
	R applyX(long a1, long a2, long a3) throws Throwable;

	default R tupleApply(LLongTriple args) {
		return apply(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions according to the instructions. */
	default R handlingApply(long a1, long a2, long a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LTriLongFunction<R> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> handlingApply(a1, a2, a3, handling);
	}

	default R apply(long a1, long a2, long a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default R apply(long a1, long a2, long a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default R apply(long a1, long a2, long a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default R apply(long a1, long a2, long a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LTriLongFunction<R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2, a3) -> apply(a1, a2, a3, factory, newMessage);
	}

	default LTriLongFunction<R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2, a3) -> apply(a1, a2, a3, factory, newMessage, param1);
	}

	default LTriLongFunction<R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2, a3) -> apply(a1, a2, a3, factory, newMessage, param1, param1);
	}

	default LTriLongFunction<R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2, a3) -> apply(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	default R apply(long a1, long a2, long a3, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LTriLongFunction<R> trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2, a3) -> apply(a1, a2, a3, factory);
	}

	default R applyThen(long a1, long a2, long a3, @Nonnull LFunction<Throwable, R> handler) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			Handling.handleErrors(e);
			return handler.apply(e);
		}
	}

	default LTriLongFunction<R> tryingThen(@Nonnull LFunction<Throwable, R> handler) {
		return (a1, a2, a3) -> applyThen(a1, a2, a3, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default R nestingApply(long a1, long a2, long a3) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default R shovingApply(long a1, long a2, long a3) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <R> R shovingApply(long a1, long a2, long a3, LTriLongFunction<R> func) {
		Null.nonNullArg(func, "func");
		return func.shovingApply(a1, a2, a3);
	}

	static <R> R handlingApply(long a1, long a2, long a3, LTriLongFunction<R> func, HandlingInstructions<Throwable, RuntimeException> handling) { //<-
		Null.nonNullArg(func, "func");
		return func.handlingApply(a1, a2, a3, handling);
	}

	static <R> R tryApply(long a1, long a2, long a3, LTriLongFunction<R> func) {
		Null.nonNullArg(func, "func");
		return func.nestingApply(a1, a2, a3);
	}

	static <R> R tryApply(long a1, long a2, long a3, LTriLongFunction<R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, factory, newMessage);
	}

	static <R> R tryApply(long a1, long a2, long a3, LTriLongFunction<R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, factory, newMessage, param1);
	}

	static <R> R tryApply(long a1, long a2, long a3, LTriLongFunction<R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, factory, newMessage, param1, param2);
	}

	static <R> R tryApply(long a1, long a2, long a3, LTriLongFunction<R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	static <R> R tryApply(long a1, long a2, long a3, LTriLongFunction<R> func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, factory);
	}

	static <R> R tryApplyThen(long a1, long a2, long a3, LTriLongFunction<R> func, @Nonnull LFunction<Throwable, R> handler) {
		Null.nonNullArg(func, "func");
		return func.applyThen(a1, a2, a3, handler);
	}

	LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullApply() method cannot be null (" + DESCRIPTION + ").";

	/** Function call that ensures the result is not null */
	@Nonnull
	default R nonNullApply(long a1, long a2, long a3) {
		return Null.nonNull(apply(a1, a2, a3), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LTriLongFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <R> void fromTo(int min_i, int max_i, long a1, long a2, long a3, @Nonnull LTriLongFunction<R> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.apply(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.apply(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <R> void fromTill(int min_i, int max_i, long a1, long a2, long a3, @Nonnull LTriLongFunction<R> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.apply(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.apply(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <R> void times(int max_i, long a1, long a2, long a3, @Nonnull LTriLongFunction<R> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, a3, func);
	}

	default LBiLongFunction<R> _with(long a1) {
		return (a2, a3) -> apply(a1, a2, a3);
	}

	default LBiLongFunction<R> with(long a3) {
		return (a1, a2) -> apply(a1, a2, a3);
	}

	default LLongFunction<R> _with(long a1, long a2) {
		return a3 -> apply(a1, a2, a3);
	}

	default LLongFunction<R> with(long a2, long a3) {
		return a1 -> apply(a1, a2, a3);
	}

	/**  */
	public static <R> LTriLongFunction<R> uncurry(@Nonnull LLongFunction<LLongFunction<LLongFunction<R>>> func) {
		Null.nonNullArg(func, "func");
		return (long a1, long a2, long a3) -> func.apply(a1).apply(a2).apply(a3);
	}

	/** Change function to one that ignores output. */
	default LTriLongConsumer toConsumer() {
		return this::apply;
	}

	/** Calls domain consumer before main function. */
	default LTriLongFunction<R> beforeDo(@Nonnull LTriLongConsumer before) {
		Null.nonNullArg(before, "before");
		return (long a1, long a2, long a3) -> {
			before.accept(a1, a2, a3);
			return apply(a1, a2, a3);
		};
	}

	/** Calls codomain consumer after main function. */
	default LTriLongFunction<R> afterDo(@Nonnull LConsumer<R> after) {
		Null.nonNullArg(after, "after");
		return (long a1, long a2, long a3) -> {
			final R retval = apply(a1, a2, a3);
			after.accept(retval);
			return retval;
		};
	}

	/** Creates function that always returns the same value. */
	static <R> LTriLongFunction<R> constant(R r) {
		return (a1, a2, a3) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <R> LTriLongFunction<R> triLongFunc(final @Nonnull LTriLongFunction<R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S<R> implements LTriLongFunction<R> {
		private LTriLongFunction<R> target = null;
		@Override
		public R applyX(long a1, long a2, long a3) throws Throwable {
			return target.applyX(a1, a2, a3);
		}
	}

	@Nonnull
	static <R> LTriLongFunction<R> recursive(final @Nonnull LFunction<LTriLongFunction<R>, LTriLongFunction<R>> selfLambda) {
		final S<R> single = new S();
		LTriLongFunction<R> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static <R> M<R> mementoOf(long a1, long a2, long a3, LTriLongFunction<R> function) {
		var initialValue = function.apply(a1, a2, a3);
		return initializedMementoOf(initialValue, function);
	}

	public static <R> M<R> initializedMementoOf(R initialValue, LTriLongFunction<R> function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static <R> M<R> deltaOf(long a1, long a2, long a3, LTriLongFunction<R> function, LBinaryOperator<R> deltaFunction) {
		var initialValue = function.apply(a1, a2, a3);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static <R> M<R> initializedDeltaOf(R initialValue, LTriLongFunction<R> function, LBinaryOperator<R> deltaFunction) {
		return memento(initialValue, deltaFunction.apply(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.apply(x1, x2));
	}

	public static <R> M<R> memento(R initialBaseValue, R initialValue, LTriLongFunction<R> baseFunction, LTernaryOperator<R> mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LTriLongFunction.M)
	 */
	@NotThreadSafe
	final class M<R> implements LTriLongFunction<R> {

		private final LTriLongFunction<R> baseFunction;
		private R lastBaseValue;
		private R lastValue;
		private final LTernaryOperator<R> mementoFunction;

		private M(R lastBaseValue, R lastValue, LTriLongFunction<R> baseFunction, LTernaryOperator<R> mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public R applyX(long a1, long a2, long a3) throws Throwable {
			R x1 = lastBaseValue;
			R x2 = lastBaseValue = baseFunction.applyX(a1, a2, a3);

			return lastValue = mementoFunction.applyX(lastValue, x1, x2);
		}

		public R currentApply(long a1, long a2, long a3) {
			R x1 = lastBaseValue;
			R x2 = baseFunction.apply(a1, a2, a3);

			return mementoFunction.apply(lastValue, x1, x2);
		}

		public R lastValue() {
			return lastValue;
		}

		public R lastBaseValue() {
			return lastBaseValue;
		}

		public R currentBaseValue(long a1, long a2, long a3) {
			return baseFunction.apply(a1, a2, a3);
		}
	}

	// </editor-fold>

	@Nonnull
	static <R> LTriLongFunction<R> triLongFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <R> LTriLongFunction<R> triLongFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce(message);
		};
	}

	static <R> R call(long a1, long a2, long a3, final @Nonnull LTriLongFunction<R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.apply(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LTriLongFunction<R> compose(@Nonnull final LLongUnaryOperator before1, @Nonnull final LLongUnaryOperator before2, @Nonnull final LLongUnaryOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.apply(before1.applyAsLong(v1), before2.applyAsLong(v2), before3.applyAsLong(v3));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriFunction<V1, V2, V3, R> unboxingCompose(@Nonnull final LToLongFunction<? super V1> before1, @Nonnull final LToLongFunction<? super V2> before2, @Nonnull final LToLongFunction<? super V3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.apply(before1.applyAsLong(v1), before2.applyAsLong(v2), before3.applyAsLong(v3));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LTriLongFunction<V> then(@Nonnull LFunction<? super R, ? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.apply(this.apply(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LTriLongConsumer thenConsume(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.accept(this.apply(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongTernaryOperator thenToLong(@Nonnull LToLongFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.applyAsLong(this.apply(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LTriLongPredicate thenToBool(@Nonnull LPredicate<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.test(this.apply(a1, a2, a3));
	}

	// </editor-fold>

	default LTriLongFunction<R> shoving() {

		return new LTriLongFunction<R>() {

			@Nullable
			public R apply(long a1, long a2, long a3) {
				try {
					return this.applyX(a1, a2, a3);
				} catch (Throwable e) { // NOSONAR
					Handling.handleErrors(e);
					throw Handling.throwIt(e);
				}
			}

			public R applyX(long a1, long a2, long a3) throws Throwable {
				return LTriLongFunction.this.applyX(a1, a2, a3);
			}

		};
	}

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LTriLongFunction<R> nonNullable() {
		return this::nonNullApply;
	}

	/** Does nothing (LTriLongFunction) Function */
	public static <R> R doNothing(long a1, long a2, long a3) {
		return (R) Function4U.defaultObject;
	}

}
