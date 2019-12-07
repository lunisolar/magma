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
public interface LLongFunction<R> extends LongFunction<R>, MetaFunction, MetaInterface.NonThrowing, Codomain<a<R>>, Domain1<aLong> { // NOSONAR

	String DESCRIPTION = "LLongFunction: R apply(long a)";

	@Nullable
	// R apply(long a) ;
	default R apply(long a) {
		// return nestingApply(a);
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
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LLongFunction<R> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> handlingApply(a, handling);
	}

	default R apply(long a, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LLongFunction<R> trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return a -> apply(a, exF, newMessage, messageParams);
	}

	default R apply(long a, @Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LLongFunction<R> trying(@Nonnull ExWF<RuntimeException> exF) {
		return a -> apply(a, exF);
	}

	default R applyThen(long a, @Nonnull LFunction<Throwable, R> handler) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { // NOSONAR
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
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <R> R handlingApply(long a, LLongFunction<R> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApply(a, handling);
	}

	static <R> R tryApply(long a, LLongFunction<R> func) {
		Null.nonNullArg(func, "func");
		return func.nestingApply(a);
	}

	static <R> R tryApply(long a, LLongFunction<R> func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.apply(a, exF, newMessage, messageParams);
	}

	static <R> R tryApply(long a, LLongFunction<R> func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.apply(a, exF);
	}

	static <R> R tryApplyThen(long a, LLongFunction<R> func, @Nonnull LFunction<Throwable, R> handler) {
		Null.nonNullArg(func, "func");
		return func.applyThen(a, handler);
	}

	default R failSafeApply(long a, @Nonnull LLongFunction<R> failSafe) {
		try {
			return apply(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.apply(a);
		}
	}

	static <R> R failSafeApply(long a, LLongFunction<R> func, @Nonnull LLongFunction<R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.apply(a);
		} else {
			return func.failSafeApply(a, failSafe);
		}
	}

	static <R> LLongFunction<R> failSafe(LLongFunction<R> func, @Nonnull LLongFunction<R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeApply(a, func, failSafe);
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

	/** Cast that removes generics. */
	public default LLongFunction untyped() {
		return this;
	}

	/** Cast that replace generics. */
	public default <V2> LLongFunction<V2> cast() {
		return untyped();
	}

	/** Cast that replace generics. */
	public static <V2, R> LLongFunction<V2> cast(LLongFunction<R> function) {
		return (LLongFunction) function;
	}

	/** Change function to consumer that ignores output. */
	public default LLongConsumer toConsumer() {
		return this::apply;
	}

	/** Calls domain consumer before main function. */
	public default LLongFunction<R> beforeDo(@Nonnull LLongConsumer before) {
		Null.nonNullArg(before, "before");
		return (long a) -> {
			before.accept(a);
			return apply(a);
		};
	}

	/** Calls codomain consumer after main function. */
	public default LLongFunction<R> afterDo(@Nonnull LConsumer<R> after) {
		Null.nonNullArg(after, "after");
		return (long a) -> {
			final R retval = apply(a);
			after.accept(retval);
			return retval;
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplier<R> capture(long a) {
		return () -> this.apply(a);
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

	/** A completely inconvenient method in case lambda expression and generic arguments are ambiguous for the compiler. */
	@Nonnull
	static <R> LLongFunction<R> longFunc(@Nullable Class<R> c1, final @Nonnull LLongFunction<R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <R> LLongFunction<R> recursive(final @Nonnull LFunction<LLongFunction<R>, LLongFunction<R>> selfLambda) {
		final LLongFunctionSingle<R> single = new LLongFunctionSingle();
		LLongFunction<R> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LLongFunctionSingle<R> implements LLongFunction<R> {
		private LLongFunction<R> target = null;

		@Override
		public R applyX(long a) throws Throwable {
			return target.applyX(a);
		}

	}

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

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as doNothing). */
	@Nonnull
	static <R> LLongFunction<R> safe() {
		return LLongFunction::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <R> LSupplier<LLongFunction<R>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <R> LLongFunction<R> safe(final @Nullable LLongFunction<R> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <R> LSupplier<LLongFunction<R>> safeSupplier(final @Nullable LSupplier<LLongFunction<R>> supplier) {
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
	default LLongFunction<R> compose(@Nonnull final LLongUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.apply(before.applyAsLong(v));
	}

	public static <R> LLongFunction<R> composed(@Nonnull final LLongUnaryOperator before, LLongFunction<R> after) {
		return after.compose(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LFunction<V, R> longFuncCompose(@Nonnull final LToLongFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.apply(before.applyAsLong(v));
	}

	public static <V, R> LFunction<V, R> composed(@Nonnull final LToLongFunction<? super V> before, LLongFunction<R> after) {
		return after.longFuncCompose(before);
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
		LOiToLongFunction<Object> oiFunc0 = (LOiToLongFunction) ia.getter();
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
		Object iterator0 = ((LFunction) sa.adapter()).apply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToLongFunction<Object> nextFunc0 = (LToLongFunction) sa.supplier();
		while (testFunc0.test(iterator0)) {
			long a = nextFunc0.applyAsLong(iterator0);
			consumer.accept(this.apply(a));
		}
	}

}
