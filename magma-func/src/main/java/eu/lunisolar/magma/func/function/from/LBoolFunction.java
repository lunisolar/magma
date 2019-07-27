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
public interface LBoolFunction<R> extends MetaFunction, MetaInterface.NonThrowing, Codomain<a<R>>, Domain1<aBool> { // NOSONAR

	String DESCRIPTION = "LBoolFunction: R apply(boolean a)";

	@Nullable
	// R apply(boolean a) ;
	default R apply(boolean a) {
		// return nestingApply(a);
		try {
			return this.applyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call apply(boolean a)
	 */
	R applyX(boolean a) throws Throwable;

	default R tupleApply(LBoolSingle args) {
		return apply(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default R handlingApply(boolean a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LBoolFunction<R> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> handlingApply(a, handling);
	}

	default R apply(boolean a, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LBoolFunction<R> trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return a -> apply(a, exF, newMessage, messageParams);
	}

	default R apply(boolean a, @Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LBoolFunction<R> trying(@Nonnull ExWF<RuntimeException> exF) {
		return a -> apply(a, exF);
	}

	default R applyThen(boolean a, @Nonnull LFunction<Throwable, R> handler) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.apply(e);
		}
	}

	default LBoolFunction<R> tryingThen(@Nonnull LFunction<Throwable, R> handler) {
		return a -> applyThen(a, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default R nestingApply(boolean a) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default R shovingApply(boolean a) {
		try {
			return this.applyX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <R> R handlingApply(boolean a, LBoolFunction<R> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApply(a, handling);
	}

	static <R> R tryApply(boolean a, LBoolFunction<R> func) {
		Null.nonNullArg(func, "func");
		return func.nestingApply(a);
	}

	static <R> R tryApply(boolean a, LBoolFunction<R> func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.apply(a, exF, newMessage, messageParams);
	}

	static <R> R tryApply(boolean a, LBoolFunction<R> func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.apply(a, exF);
	}

	static <R> R tryApplyThen(boolean a, LBoolFunction<R> func, @Nonnull LFunction<Throwable, R> handler) {
		Null.nonNullArg(func, "func");
		return func.applyThen(a, handler);
	}

	default R failSafeApply(boolean a, @Nonnull LBoolFunction<R> failSafe) {
		try {
			return apply(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.apply(a);
		}
	}

	static <R> R failSafeApply(boolean a, LBoolFunction<R> func, @Nonnull LBoolFunction<R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.apply(a);
		} else {
			return func.failSafeApply(a, failSafe);
		}
	}

	static <R> LBoolFunction<R> failSafe(LBoolFunction<R> func, @Nonnull LBoolFunction<R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeApply(a, func, failSafe);
	}

	LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullApply() method cannot be null (" + DESCRIPTION + ").";

	/** Function call that ensures the result is not null */
	@Nonnull
	default R nonNullApply(boolean a) {
		return Null.requireNonNull(apply(a), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBoolFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <R> void fromTo(int min_i, int max_i, boolean a, LBoolFunction<R> func) {
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
	public static <R> void fromTill(int min_i, int max_i, boolean a, LBoolFunction<R> func) {
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
	public static <R> void times(int max_i, boolean a, LBoolFunction<R> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a, func);
	}

	/** Cast that removes generics. */
	public default LBoolFunction untyped() {
		return this;
	}

	/** Cast that replace generics. */
	public default <V2> LBoolFunction<V2> cast() {
		return untyped();
	}

	/** Cast that replace generics. */
	public static <V2, R> LBoolFunction<V2> cast(LBoolFunction<R> function) {
		return (LBoolFunction) function;
	}

	/** Change function to consumer that ignores output. */
	public default LBoolConsumer toConsumer() {
		return this::apply;
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplier<R> capture(boolean a) {
		return () -> this.apply(a);
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
		LBoolFunction<R> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LBoolFunctionSingle<R> implements LSingle<LBoolFunction<R>>, LBoolFunction<R> {
		private LBoolFunction<R> target = null;

		@Override
		public R applyX(boolean a) throws Throwable {
			return target.applyX(a);
		}

		@Override
		public LBoolFunction<R> value() {
			return target;
		}
	}

	@Nonnull
	static <R> LBoolFunction<R> boolFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <R> LBoolFunction<R> boolFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce(message);
		};
	}

	static <R> R call(boolean a, final @Nonnull LBoolFunction<R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.apply(a);
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
	default LBoolFunction<R> compose(@Nonnull final LLogicalOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.apply(before.apply(v));
	}

	public static <R> LBoolFunction<R> composed(@Nonnull final LLogicalOperator before, LBoolFunction<R> after) {
		return after.compose(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LFunction<V, R> boolFuncCompose(@Nonnull final LPredicate<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.apply(before.test(v));
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
		return a -> after.apply(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolConsumer thenConsume(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.accept(this.apply(a));
	}

	@Nonnull
	default LBoolFunction<R> before(@Nonnull LBoolConsumer before) {
		Null.nonNullArg(before, "before");
		return a -> {
			before.accept(a);
			return this.apply(a);
		};
	}

	@Nonnull
	default LBoolFunction<R> after(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> {
			R result = this.apply(a);
			after.accept(result);
			return result;
		};
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToByteFunction thenToByte(@Nonnull LToByteFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsByte(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToSrtFunction thenToSrt(@Nonnull LToSrtFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsSrt(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToIntFunction thenToInt(@Nonnull LToIntFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsInt(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToLongFunction thenToLong(@Nonnull LToLongFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsLong(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToFltFunction thenToFlt(@Nonnull LToFltFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsFlt(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToDblFunction thenToDbl(@Nonnull LToDblFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsDbl(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBoolToCharFunction thenToChar(@Nonnull LToCharFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsChar(this.apply(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLogicalOperator thenToBool(@Nonnull LPredicate<? super R> after) {
		Null.nonNullArg(after, "after");
		return a -> after.test(this.apply(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LBoolFunction<R> nonNullable() {
		return this::nonNullApply;
	}

	/** Does nothing (LBoolFunction) Function */
	public static <R> R produce(boolean a) {
		return (R) Function4U.defaultObject;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C0> void forEach(IndexedRead<C0, aBool> ia, C0 source, LConsumer<? super R> consumer) {
		int size = ia.size(source);
		LObjIntPredicate<Object> oiFunc0 = (LObjIntPredicate) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			boolean a = oiFunc0.test(source, i);
			consumer.accept(this.apply(a));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C0, I0> void iterate(SequentialRead<C0, I0, aBool> sa, C0 source, LConsumer<? super R> consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).apply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LPredicate<Object> nextFunc0 = (LPredicate) sa.supplier();
		while (testFunc0.test(iterator0)) {
			boolean a = nextFunc0.test(iterator0);
			consumer.accept(this.apply(a));
		}
	}

}
