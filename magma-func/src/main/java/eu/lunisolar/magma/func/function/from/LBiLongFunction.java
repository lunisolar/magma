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
 * Non-throwing functional interface (lambda) LBiLongFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 2): long a1,long a2
 *
 * Co-domain: R
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiLongFunction<R> extends MetaFunction, MetaInterface.NonThrowing, Codomain<a<R>>, Domain2<aLong, aLong> { // NOSONAR

	String DESCRIPTION = "LBiLongFunction: R apply(long a1,long a2)";

	@Nullable
	// R apply(long a1,long a2) ;
	default R apply(long a1, long a2) {
		// return nestingApply(a1,a2);
		try {
			return this.applyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call apply(long a1,long a2)
	 */
	R applyX(long a1, long a2) throws Throwable;

	default R tupleApply(LLongPair args) {
		return apply(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default R handlingApply(long a1, long a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LBiLongFunction<R> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2) -> handlingApply(a1, a2, handling);
	}

	default R apply(long a1, long a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default R apply(long a1, long a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default R apply(long a1, long a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default R apply(long a1, long a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LBiLongFunction<R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2) -> apply(a1, a2, factory, newMessage);
	}

	default LBiLongFunction<R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2) -> apply(a1, a2, factory, newMessage, param1);
	}

	default LBiLongFunction<R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2) -> apply(a1, a2, factory, newMessage, param1, param1);
	}

	default LBiLongFunction<R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2) -> apply(a1, a2, factory, newMessage, param1, param2, param3);
	}

	default R apply(long a1, long a2, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LBiLongFunction<R> trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2) -> apply(a1, a2, factory);
	}

	default R applyThen(long a1, long a2, @Nonnull LFunction<Throwable, R> handler) {
		try {
			return this.applyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.apply(e);
		}
	}

	default LBiLongFunction<R> tryingThen(@Nonnull LFunction<Throwable, R> handler) {
		return (a1, a2) -> applyThen(a1, a2, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default R nestingApply(long a1, long a2) {
		try {
			return this.applyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default R shovingApply(long a1, long a2) {
		try {
			return this.applyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <R> R shovingApply(long a1, long a2, LBiLongFunction<R> func) {
		Null.nonNullArg(func, "func");
		return func.shovingApply(a1, a2);
	}

	static <R> R handlingApply(long a1, long a2, LBiLongFunction<R> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApply(a1, a2, handling);
	}

	static <R> R tryApply(long a1, long a2, LBiLongFunction<R> func) {
		Null.nonNullArg(func, "func");
		return func.nestingApply(a1, a2);
	}

	static <R> R tryApply(long a1, long a2, LBiLongFunction<R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, factory, newMessage);
	}

	static <R> R tryApply(long a1, long a2, LBiLongFunction<R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, factory, newMessage, param1);
	}

	static <R> R tryApply(long a1, long a2, LBiLongFunction<R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, factory, newMessage, param1, param2);
	}

	static <R> R tryApply(long a1, long a2, LBiLongFunction<R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, factory, newMessage, param1, param2, param3);
	}

	static <R> R tryApply(long a1, long a2, LBiLongFunction<R> func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, factory);
	}

	static <R> R tryApplyThen(long a1, long a2, LBiLongFunction<R> func, @Nonnull LFunction<Throwable, R> handler) {
		Null.nonNullArg(func, "func");
		return func.applyThen(a1, a2, handler);
	}

	default R failSafeApply(long a1, long a2, @Nonnull LBiLongFunction<R> failSafe) {
		try {
			return apply(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.apply(a1, a2);
		}
	}

	static <R> R failSafeApply(long a1, long a2, LBiLongFunction<R> func, @Nonnull LBiLongFunction<R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.apply(a1, a2);
		} else {
			return func.failSafeApply(a1, a2, failSafe);
		}
	}

	static <R> LBiLongFunction<R> failSafe(LBiLongFunction<R> func, @Nonnull LBiLongFunction<R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeApply(a1, a2, func, failSafe);
	}

	LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullApply() method cannot be null (" + DESCRIPTION + ").";

	/** Function call that ensures the result is not null */
	@Nonnull
	default R nonNullApply(long a1, long a2) {
		return Null.nonNull(apply(a1, a2), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiLongFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <R> void fromTo(int min_i, int max_i, long a1, long a2, @Nonnull LBiLongFunction<R> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.apply(a1, a2);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.apply(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <R> void fromTill(int min_i, int max_i, long a1, long a2, @Nonnull LBiLongFunction<R> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.apply(a1, a2);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.apply(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <R> void times(int max_i, long a1, long a2, @Nonnull LBiLongFunction<R> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, func);
	}

	default LLongFunction<R> lShrink(@Nonnull LLongUnaryOperator left) {
		Null.nonNullArg(left, "left");
		return a2 -> apply(left.applyAsLong(a2), a2);
	}

	default LLongFunction<R> lShrink_(long a1) {
		return a2 -> apply(a1, a2);
	}

	public static <R> LLongFunction<R> lShrunken(@Nonnull LLongUnaryOperator left, @Nonnull LBiLongFunction<R> func) {
		Null.nonNullArg(left, "left");
		Null.nonNullArg(func, "func");
		return func.lShrink(left);
	}

	public static <R> LLongFunction<R> lShrunken_(long a1, @Nonnull LBiLongFunction<R> func) {
		Null.nonNullArg(func, "func");
		return func.lShrink_(a1);
	}

	default LLongFunction<R> rShrink(@Nonnull LLongUnaryOperator right) {
		Null.nonNullArg(right, "right");
		return a1 -> apply(a1, right.applyAsLong(a1));
	}

	default LLongFunction<R> rShrink_(long a2) {
		return a1 -> apply(a1, a2);
	}

	public static <R> LLongFunction<R> rShrunken(@Nonnull LLongUnaryOperator right, @Nonnull LBiLongFunction<R> func) {
		Null.nonNullArg(right, "right");
		Null.nonNullArg(func, "func");
		return func.rShrink(right);
	}

	public static <R> LLongFunction<R> rShrunken_(long a2, @Nonnull LBiLongFunction<R> func) {
		Null.nonNullArg(func, "func");
		return func.rShrink_(a2);
	}

	/**  */
	public static <R> LBiLongFunction<R> uncurry(@Nonnull LLongFunction<LLongFunction<R>> func) {
		Null.nonNullArg(func, "func");
		return (long a1, long a2) -> func.apply(a1).apply(a2);
	}

	/** Cast that removes generics. */
	default LBiLongFunction untyped() {
		return this;
	}

	/** Cast that replace generics. */
	default <V2> LBiLongFunction<V2> cast() {
		return untyped();
	}

	/** Cast that replace generics. */
	public static <V2> LBiLongFunction<V2> cast(LBiLongFunction<?> function) {
		return (LBiLongFunction) function;
	}

	/** Change function to consumer that ignores output. */
	default LBiLongConsumer toConsumer() {
		return this::apply;
	}

	/** Calls domain consumer before main function. */
	default LBiLongFunction<R> beforeDo(@Nonnull LBiLongConsumer before) {
		Null.nonNullArg(before, "before");
		return (long a1, long a2) -> {
			before.accept(a1, a2);
			return apply(a1, a2);
		};
	}

	/** Calls codomain consumer after main function. */
	default LBiLongFunction<R> afterDo(@Nonnull LConsumer<R> after) {
		Null.nonNullArg(after, "after");
		return (long a1, long a2) -> {
			final R retval = apply(a1, a2);
			after.accept(retval);
			return retval;
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplier<R> capture(long a1, long a2) {
		return () -> this.apply(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static <R> LBiLongFunction<R> constant(R r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <R> LBiLongFunction<R> apply1st(@Nonnull LLongFunction<R> func) {
		return (a1, a2) -> func.apply(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <R> LBiLongFunction<R> apply2nd(@Nonnull LLongFunction<R> func) {
		return (a1, a2) -> func.apply(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <R> LBiLongFunction<R> biLongFunc(final @Nonnull LBiLongFunction<R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** A completely inconvenient method in case lambda expression and generic arguments are ambiguous for the compiler. */
	@Nonnull
	static <R> LBiLongFunction<R> biLongFunc(@Nullable Class<R> c1, final @Nonnull LBiLongFunction<R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	final class S<R> implements LBiLongFunction<R> {
		private LBiLongFunction<R> target = null;
		@Override
		public R applyX(long a1, long a2) throws Throwable {
			return target.applyX(a1, a2);
		}
	}

	@Nonnull
	static <R> LBiLongFunction<R> recursive(final @Nonnull LFunction<LBiLongFunction<R>, LBiLongFunction<R>> selfLambda) {
		final S<R> single = new S();
		LBiLongFunction<R> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	public static <R> M<R> mementoOf(long a1, long a2, LBiLongFunction<R> function) {
		var initialValue = function.apply(a1, a2);
		return initializedMementoOf(initialValue, function);
	}

	public static <R> M<R> initializedMementoOf(R initialValue, LBiLongFunction<R> function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static <R> M<R> deltaOf(long a1, long a2, LBiLongFunction<R> function, LBinaryOperator<R> deltaFunction) {
		var initialValue = function.apply(a1, a2);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static <R> M<R> initializedDeltaOf(R initialValue, LBiLongFunction<R> function, LBinaryOperator<R> deltaFunction) {
		return memento(initialValue, deltaFunction.apply(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.apply(x1, x2));
	}

	public static <R> M<R> memento(R initialBaseValue, R initialValue, LBiLongFunction<R> baseFunction, LTernaryOperator<R> mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LBiLongFunction.M)
	 */
	@NotThreadSafe
	final class M<R> implements LBiLongFunction<R> {

		private final LBiLongFunction<R> baseFunction;
		private R lastBaseValue;
		private R lastValue;
		private final LTernaryOperator<R> mementoFunction;

		private M(R lastBaseValue, R lastValue, LBiLongFunction<R> baseFunction, LTernaryOperator<R> mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public R applyX(long a1, long a2) throws Throwable {
			R x1 = lastBaseValue;
			R x2 = lastBaseValue = baseFunction.applyX(a1, a2);

			return lastValue = mementoFunction.apply(lastValue, x1, x2);
		}

		public R lastValue() {
			return lastValue;
		};

		public R lastBaseValue() {
			return lastBaseValue;
		};
	}

	@Nonnull
	static <R> LBiLongFunction<R> biLongFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <R> LBiLongFunction<R> biLongFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <R> LBiLongFunction.LLong1Long0Func<R> long1Long0Func(final @Nonnull LBiLongFunction.LLong1Long0Func<R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <R> R call(long a1, long a2, final @Nonnull LBiLongFunction<R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.apply(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LBiLongFunction<R> compose(@Nonnull final LLongUnaryOperator before1, @Nonnull final LLongUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.apply(before1.applyAsLong(v1), before2.applyAsLong(v2));
	}

	public static <R> LBiLongFunction<R> composed(@Nonnull final LLongUnaryOperator before1, @Nonnull final LLongUnaryOperator before2, LBiLongFunction<R> after) {
		return after.compose(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiFunction<V1, V2, R> biLongFuncCompose(@Nonnull final LToLongFunction<? super V1> before1, @Nonnull final LToLongFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.apply(before1.applyAsLong(v1), before2.applyAsLong(v2));
	}

	public static <V1, V2, R> LBiFunction<V1, V2, R> composed(@Nonnull final LToLongFunction<? super V1> before1, @Nonnull final LToLongFunction<? super V2> before2, LBiLongFunction<R> after) {
		return after.biLongFuncCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiLongFunction<V> then(@Nonnull LFunction<? super R, ? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.apply(this.apply(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiLongConsumer thenConsume(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.accept(this.apply(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongBinaryOperator thenToLong(@Nonnull LToLongFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsLong(this.apply(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiLongPredicate thenToBool(@Nonnull LPredicate<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.test(this.apply(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LBiLongFunction<R> nonNullable() {
		return this::nonNullApply;
	}

	// <editor-fold desc="interface variants">

	/** Permutation of LBiLongFunction for method references. */
	@FunctionalInterface
	interface LLong1Long0Func<R> extends LBiLongFunction<R> {

		/**
		 * Implement this, but call apply(long a1,long a2)
		 */
		default R applyX(long a1, long a2) {
			return this.applyLong1Long0(a2, a1);
		}

		@Nullable
		// R applyLong1Long0(long a2,long a1) ;
		default R applyLong1Long0(long a2, long a1) {
			// return nestingApplyLong1Long0(a2,a1);
			try {
				return this.applyLong1Long0X(a2, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyLong1Long0(long a2,long a1)
		 */
		R applyLong1Long0X(long a2, long a1) throws Throwable;
	}

	// </editor-fold>

	/** Does nothing (LBiLongFunction) Function */
	public static <R> R doNothing(long a1, long a2) {
		return (R) Function4U.defaultObject;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2> void forEach(IndexedRead<C1, aLong> ia1, C1 source1, IndexedRead<C2, aLong> ia2, C2 source2, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LOiToLongFunction<Object> oiFunc1 = (LOiToLongFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToLongFunction<Object> oiFunc2 = (LOiToLongFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			long a1 = oiFunc1.applyAsLong(source1, i);
			long a2 = oiFunc2.applyAsLong(source2, i);
			consumer.accept(this.apply(a1, a2));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2> void iterate(SequentialRead<C1, I1, aLong> sa1, C1 source1, IndexedRead<C2, aLong> ia2, C2 source2, LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToLongFunction<Object> nextFunc1 = (LToLongFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToLongFunction<Object> oiFunc2 = (LOiToLongFunction) ia2.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			long a1 = nextFunc1.applyAsLong(iterator1);
			long a2 = oiFunc2.applyAsLong(source2, i);
			consumer.accept(this.apply(a1, a2));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2> void iterate(IndexedRead<C1, aLong> ia1, C1 source1, SequentialRead<C2, I2, aLong> sa2, C2 source2, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LOiToLongFunction<Object> oiFunc1 = (LOiToLongFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToLongFunction<Object> nextFunc2 = (LToLongFunction) sa2.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			long a1 = oiFunc1.applyAsLong(source1, i);
			long a2 = nextFunc2.applyAsLong(iterator2);
			consumer.accept(this.apply(a1, a2));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C1, I1, C2, I2> void iterate(SequentialRead<C1, I1, aLong> sa1, C1 source1, SequentialRead<C2, I2, aLong> sa2, C2 source2, LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToLongFunction<Object> nextFunc1 = (LToLongFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToLongFunction<Object> nextFunc2 = (LToLongFunction) sa2.supplier();
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2)) {
			long a1 = nextFunc1.applyAsLong(iterator1);
			long a2 = nextFunc2.applyAsLong(iterator2);
			consumer.accept(this.apply(a1, a2));
		}
	}

}
