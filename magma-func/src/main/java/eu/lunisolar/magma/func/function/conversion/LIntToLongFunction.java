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

package eu.lunisolar.magma.func.function.conversion;

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
 * Non-throwing functional interface (lambda) LIntToLongFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 1): int a
 *
 * Co-domain: long
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LIntToLongFunction extends IntToLongFunction, MetaFunction, MetaInterface.NonThrowing, Codomain<aLong>, Domain1<aInt> { // NOSONAR

	String DESCRIPTION = "LIntToLongFunction: long applyAsLong(int a)";

	// long applyAsLong(int a) ;
	default long applyAsLong(int a) {
		// return nestingApplyAsLong(a);
		try {
			return this.applyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsLong(int a)
	 */
	long applyAsLongX(int a) throws Throwable;

	default long tupleApplyAsLong(LIntSingle args) {
		return applyAsLong(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default long handlingApplyAsLong(int a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LIntToLongFunction handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> handlingApplyAsLong(a, handling);
	}

	default long applyAsLong(int a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default long applyAsLong(int a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default long applyAsLong(int a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default long applyAsLong(int a, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LIntToLongFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return a -> applyAsLong(a, factory, newMessage);
	}

	default LIntToLongFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return a -> applyAsLong(a, factory, newMessage, param1);
	}

	default LIntToLongFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return a -> applyAsLong(a, factory, newMessage, param1, param1);
	}

	default LIntToLongFunction trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return a -> applyAsLong(a, factory, newMessage, param1, param2, param3);
	}

	default long applyAsLong(int a, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LIntToLongFunction trying(@Nonnull ExWF<RuntimeException> factory) {
		return a -> applyAsLong(a, factory);
	}

	default long applyAsLongThen(int a, @Nonnull LToLongFunction<Throwable> handler) {
		try {
			return this.applyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsLong(e);
		}
	}

	default LIntToLongFunction tryingThen(@Nonnull LToLongFunction<Throwable> handler) {
		return a -> applyAsLongThen(a, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default long nestingApplyAsLong(int a) {
		try {
			return this.applyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default long shovingApplyAsLong(int a) {
		try {
			return this.applyAsLongX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static long shovingApplyAsLong(int a, LIntToLongFunction func) {
		Null.nonNullArg(func, "func");
		return func.shovingApplyAsLong(a);
	}

	static long handlingApplyAsLong(int a, LIntToLongFunction func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsLong(a, handling);
	}

	static long tryApplyAsLong(int a, LIntToLongFunction func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsLong(a);
	}

	static long tryApplyAsLong(int a, LIntToLongFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.applyAsLong(a, factory, newMessage);
	}

	static long tryApplyAsLong(int a, LIntToLongFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.applyAsLong(a, factory, newMessage, param1);
	}

	static long tryApplyAsLong(int a, LIntToLongFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.applyAsLong(a, factory, newMessage, param1, param2);
	}

	static long tryApplyAsLong(int a, LIntToLongFunction func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.applyAsLong(a, factory, newMessage, param1, param2, param3);
	}

	static long tryApplyAsLong(int a, LIntToLongFunction func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.applyAsLong(a, factory);
	}

	static long tryApplyAsLongThen(int a, LIntToLongFunction func, @Nonnull LToLongFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsLongThen(a, handler);
	}

	default long failSafeApplyAsLong(int a, @Nonnull LIntToLongFunction failSafe) {
		try {
			return applyAsLong(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.applyAsLong(a);
		}
	}

	static long failSafeApplyAsLong(int a, LIntToLongFunction func, @Nonnull LIntToLongFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.applyAsLong(a);
		} else {
			return func.failSafeApplyAsLong(a, failSafe);
		}
	}

	static LIntToLongFunction failSafe(LIntToLongFunction func, @Nonnull LIntToLongFunction failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeApplyAsLong(a, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default long nonNullApplyAsLong(int a) {
		return applyAsLong(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LIntToLongFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_a, int max_a, @Nonnull LIntToLongFunction func) {
		Null.nonNullArg(func, "func");
		if (min_a <= max_a) {
			for (int a = min_a; a <= max_a; a++) {
				func.applyAsLong(a);
			}
		} else {
			for (int a = min_a; a >= max_a; a--) {
				func.applyAsLong(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_a, int max_a, @Nonnull LIntToLongFunction func) {
		Null.nonNullArg(func, "func");
		if (min_a <= max_a) {
			for (int a = min_a; a < max_a; a++) {
				func.applyAsLong(a);
			}
		} else {
			for (int a = min_a; a > max_a; a--) {
				func.applyAsLong(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_a, @Nonnull LIntToLongFunction func) {
		if (max_a < 0)
			return;
		fromTill(0, max_a, func);
	}

	/** Change function to consumer that ignores output. */
	default LIntConsumer toConsumer() {
		return this::applyAsLong;
	}

	/** Calls domain consumer before main function. */
	default LIntToLongFunction beforeDo(@Nonnull LIntConsumer before) {
		Null.nonNullArg(before, "before");
		return (int a) -> {
			before.accept(a);
			return applyAsLong(a);
		};
	}

	/** Calls codomain consumer after main function. */
	default LIntToLongFunction afterDo(@Nonnull LLongConsumer after) {
		Null.nonNullArg(after, "after");
		return (int a) -> {
			final long retval = applyAsLong(a);
			after.accept(retval);
			return retval;
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LLongSupplier capture(int a) {
		return () -> this.applyAsLong(a);
	}

	/** Creates function that always returns the same value. */
	static LIntToLongFunction constant(long r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LIntToLongFunction intToLongFunc(final @Nonnull LIntToLongFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	final class S implements LIntToLongFunction {
		private LIntToLongFunction target = null;
		@Override
		public long applyAsLongX(int a) throws Throwable {
			return target.applyAsLongX(a);
		}
	}

	@Nonnull
	static LIntToLongFunction recursive(final @Nonnull LFunction<LIntToLongFunction, LIntToLongFunction> selfLambda) {
		final S single = new S();
		LIntToLongFunction func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	public static M mementoOf(int a, LIntToLongFunction function) {
		var initialValue = function.applyAsLong(a);
		return initializedMementoOf(initialValue, function);
	}

	public static M initializedMementoOf(long initialValue, LIntToLongFunction function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static M deltaOf(int a, LIntToLongFunction function, LLongBinaryOperator deltaFunction) {
		var initialValue = function.applyAsLong(a);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static M deltaOf(int a, LIntToLongFunction function) {
		var initialValue = function.applyAsLong(a);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (x2 - x1));
	}

	public static M initializedDeltaOf(long initialValue, LIntToLongFunction function, LLongBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.applyAsLong(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.applyAsLong(x1, x2));
	}

	public static M memento(long initialBaseValue, long initialValue, LIntToLongFunction baseFunction, LLongTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LIntToLongFunction.M)
	 */
	@NotThreadSafe
	final class M implements LIntToLongFunction {

		private final LIntToLongFunction baseFunction;
		private long lastBaseValue;
		private long lastValue;
		private final LLongTernaryOperator mementoFunction;

		private M(long lastBaseValue, long lastValue, LIntToLongFunction baseFunction, LLongTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public long applyAsLongX(int a) throws Throwable {
			long x1 = lastBaseValue;
			long x2 = lastBaseValue = baseFunction.applyAsLongX(a);

			return lastValue = mementoFunction.applyAsLong(lastValue, x1, x2);
		}

		public long lastValue() {
			return lastValue;
		};

		public long lastBaseValue() {
			return lastBaseValue;
		};
	}

	@Nonnull
	static LIntToLongFunction intToLongFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LIntToLongFunction intToLongFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce(message);
		};
	}

	static long call(int a, final @Nonnull LIntToLongFunction lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsLong(a);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static LIntToLongFunction wrap(final IntToLongFunction other) {
		return other::applyAsLong;
	}
	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as doNothing). */
	@Nonnull
	static LIntToLongFunction safe() {
		return LIntToLongFunction::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LIntToLongFunction> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LIntToLongFunction safe(final @Nullable LIntToLongFunction other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LIntToLongFunction> safeSupplier(final @Nullable LSupplier<LIntToLongFunction> supplier) {
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
	default LIntToLongFunction compose(@Nonnull final LIntUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsLong(before.applyAsInt(v));
	}

	public static LIntToLongFunction composed(@Nonnull final LIntUnaryOperator before, LIntToLongFunction after) {
		return after.compose(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToLongFunction<V> intToLongFuncCompose(@Nonnull final LToIntFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsLong(before.applyAsInt(v));
	}

	public static <V> LToLongFunction<V> composed(@Nonnull final LToIntFunction<? super V> before, LIntToLongFunction after) {
		return after.intToLongFuncCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LIntFunction<V> then(@Nonnull LLongFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.apply(this.applyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToByteFunction thenToByte(@Nonnull LLongToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsByte(this.applyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToSrtFunction thenToSrt(@Nonnull LLongToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsSrt(this.applyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntUnaryOperator thenToInt(@Nonnull LLongToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsInt(this.applyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToLongFunction thenToLong(@Nonnull LLongUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsLong(this.applyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToFltFunction thenToFlt(@Nonnull LLongToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsFlt(this.applyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToDblFunction thenToDbl(@Nonnull LLongToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsDbl(this.applyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToCharFunction thenToChar(@Nonnull LLongToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsChar(this.applyAsLong(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntPredicate thenToBool(@Nonnull LLongPredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.test(this.applyAsLong(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LIntToLongFunction) Function */
	public static long doNothing(int a) {
		return Function4U.defaultLong;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C0> void forEach(IndexedRead<C0, aInt> ia, C0 source, LLongConsumer consumer) {
		int size = ia.size(source);
		LOiToIntFunction<Object> oiFunc0 = (LOiToIntFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			int a = oiFunc0.applyAsInt(source, i);
			consumer.accept(this.applyAsLong(a));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C0, I0> void iterate(SequentialRead<C0, I0, aInt> sa, C0 source, LLongConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).apply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToIntFunction<Object> nextFunc0 = (LToIntFunction) sa.supplier();
		while (testFunc0.test(iterator0)) {
			int a = nextFunc0.applyAsInt(iterator0);
			consumer.accept(this.applyAsLong(a));
		}
	}

}
