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
 * Non-throwing functional interface (lambda) LToByteFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): T a
 *
 * Co-domain: byte
 *
 * Special case of function that corresponds to expressions like (iterator) -> Iterator::next
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LToByteFunction<T> extends MetaFunction, MetaInterface.NonThrowing, OFunction<T, aByte>, Codomain<aByte>, Domain1<a<T>> { // NOSONAR

	String DESCRIPTION = "LToByteFunction: byte applyAsByte(T a)";

	// byte applyAsByte(T a) ;
	default byte applyAsByte(T a) {
		// return nestingApplyAsByte(a);
		try {
			return this.applyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsByte(T a)
	 */
	byte applyAsByteX(T a) throws Throwable;

	default byte tupleApplyAsByte(LSingle<T> args) {
		return applyAsByte(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default byte handlingApplyAsByte(T a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LToByteFunction<T> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> handlingApplyAsByte(a, handling);
	}

	default byte applyAsByte(T a, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.applyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LToByteFunction<T> trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return a -> applyAsByte(a, exF, newMessage, messageParams);
	}

	default byte applyAsByte(T a, @Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.applyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LToByteFunction<T> trying(@Nonnull ExWF<RuntimeException> exF) {
		return a -> applyAsByte(a, exF);
	}

	default byte applyAsByteThen(T a, @Nonnull LToByteFunction<Throwable> handler) {
		try {
			return this.applyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsByte(e);
		}
	}

	default LToByteFunction<T> tryingThen(@Nonnull LToByteFunction<Throwable> handler) {
		return a -> applyAsByteThen(a, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default byte nestingApplyAsByte(T a) {
		try {
			return this.applyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default byte shovingApplyAsByte(T a) {
		try {
			return this.applyAsByteX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> byte handlingApplyAsByte(T a, LToByteFunction<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsByte(a, handling);
	}

	static <T> byte tryApplyAsByte(T a, LToByteFunction<T> func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsByte(a);
	}

	static <T> byte tryApplyAsByte(T a, LToByteFunction<T> func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.applyAsByte(a, exF, newMessage, messageParams);
	}

	static <T> byte tryApplyAsByte(T a, LToByteFunction<T> func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.applyAsByte(a, exF);
	}

	static <T> byte tryApplyAsByteThen(T a, LToByteFunction<T> func, @Nonnull LToByteFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsByteThen(a, handler);
	}

	default byte failSafeApplyAsByte(T a, @Nonnull LToByteFunction<T> failSafe) {
		try {
			return applyAsByte(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.applyAsByte(a);
		}
	}

	static <T> byte failSafeApplyAsByte(T a, LToByteFunction<T> func, @Nonnull LToByteFunction<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.applyAsByte(a);
		} else {
			return func.failSafeApplyAsByte(a, failSafe);
		}
	}

	static <T> LToByteFunction<T> failSafe(LToByteFunction<T> func, @Nonnull LToByteFunction<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeApplyAsByte(a, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default byte nonNullApplyAsByte(T a) {
		return applyAsByte(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LToByteFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_i, int max_i, T a, LToByteFunction<T> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.applyAsByte(a);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.applyAsByte(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTill(int min_i, int max_i, T a, LToByteFunction<T> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.applyAsByte(a);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.applyAsByte(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void times(int max_i, T a, LToByteFunction<T> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a, func);
	}

	/** Extract and apply function. */
	public static <M, K, V> byte from(M container, LBiFunction<M, K, V> extractor, K key, LToByteFunction<V> function, byte orElse) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.applyAsByte(value);
		}

		return orElse;
	}

	/** Cast that removes generics. */
	public default LToByteFunction untyped() {
		return this;
	}

	/** Cast that replace generics. */
	public default <V2> LToByteFunction<V2> cast() {
		return untyped();
	}

	/** Cast that replace generics. */
	public static <V2, T> LToByteFunction<V2> cast(LToByteFunction<T> function) {
		return (LToByteFunction) function;
	}

	/** Change function to consumer that ignores output. */
	public default LConsumer<T> toConsumer() {
		return this::applyAsByte;
	}

	/** Captures arguments but delays the evaluation. */
	default LByteSupplier capture(T a) {
		return () -> this.applyAsByte(a);
	}

	/** Creates function that always returns the same value. */
	static <T> LToByteFunction<T> constant(byte r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LToByteFunction<T> toByteFunc(final @Nonnull LToByteFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** A completely inconvenient method in case lambda expression and generic arguments are ambiguous for the compiler. */
	@Nonnull
	static <T> LToByteFunction<T> toByteFunc(Class<T> c1, final @Nonnull LToByteFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T> LToByteFunction<T> recursive(final @Nonnull LFunction<LToByteFunction<T>, LToByteFunction<T>> selfLambda) {
		final LToByteFunctionSingle<T> single = new LToByteFunctionSingle();
		LToByteFunction<T> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LToByteFunctionSingle<T> implements LSingle<LToByteFunction<T>>, LToByteFunction<T> {
		private LToByteFunction<T> target = null;

		@Override
		public byte applyAsByteX(T a) throws Throwable {
			return target.applyAsByteX(a);
		}

		@Override
		public LToByteFunction<T> value() {
			return target;
		}
	}

	@Nonnull
	static <T> LToByteFunction<T> toByteFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T> LToByteFunction<T> toByteFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce(message);
		};
	}

	static <T> byte call(T a, final @Nonnull LToByteFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsByte(a);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceByte). */
	@Nonnull
	static <T> LToByteFunction<T> safe() {
		return LToByteFunction::produceByte;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LToByteFunction<T>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T> LToByteFunction<T> safe(final @Nullable LToByteFunction<T> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LToByteFunction<T>> safeSupplier(final @Nullable LSupplier<LToByteFunction<T>> supplier) {
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
	default <V> LToByteFunction<V> compose(@Nonnull final LFunction<? super V, ? extends T> before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsByte(before.apply(v));
	}

	public static <V, T> LToByteFunction<V> composed(@Nonnull final LFunction<? super V, ? extends T> before, LToByteFunction<T> after) {
		return after.compose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LFunction<T, V> then(@Nonnull LByteFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.apply(this.applyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToByteFunction<T> thenToByte(@Nonnull LByteUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsByte(this.applyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToSrtFunction<T> thenToSrt(@Nonnull LByteToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsSrt(this.applyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToIntFunction<T> thenToInt(@Nonnull LByteToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsInt(this.applyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToLongFunction<T> thenToLong(@Nonnull LByteToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsLong(this.applyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToFltFunction<T> thenToFlt(@Nonnull LByteToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsFlt(this.applyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToDblFunction<T> thenToDbl(@Nonnull LByteToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsDbl(this.applyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToCharFunction<T> thenToChar(@Nonnull LByteToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsChar(this.applyAsByte(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LPredicate<T> thenToBool(@Nonnull LBytePredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.test(this.applyAsByte(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LToByteFunction) Function */
	public static <T> byte produceByte(T a) {
		return Function4U.defaultByte;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C0> void forEach(IndexedRead<C0, a<T>> ia, C0 source, LByteConsumer consumer) {
		int size = ia.size(source);
		LOiFunction<Object, T> oiFunc0 = (LOiFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			T a = oiFunc0.apply(source, i);
			consumer.accept(this.applyAsByte(a));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C0, I0> void iterate(SequentialRead<C0, I0, a<T>> sa, C0 source, LByteConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).apply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LFunction<Object, T> nextFunc0 = (LFunction) sa.supplier();
		while (testFunc0.test(iterator0)) {
			T a = nextFunc0.apply(iterator0);
			consumer.accept(this.applyAsByte(a));
		}
	}

}
