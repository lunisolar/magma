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
 * Non-throwing functional interface (lambda) LIntUnaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 1): int a
 *
 * Co-domain: int
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LIntUnaryOperator extends IntUnaryOperator, MetaOperator, MetaInterface.NonThrowing, Codomain<aInt>, Domain1<aInt> { // NOSONAR

	String DESCRIPTION = "LIntUnaryOperator: int applyAsInt(int a)";

	// int applyAsInt(int a) ;
	default int applyAsInt(int a) {
		// return nestingApplyAsInt(a);
		try {
			return this.applyAsIntX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsInt(int a)
	 */
	int applyAsIntX(int a) throws Throwable;

	default int tupleApplyAsInt(LIntSingle args) {
		return applyAsInt(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default int handlingApplyAsInt(int a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsIntX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LIntUnaryOperator handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> handlingApplyAsInt(a, handling);
	}

	default int applyAsInt(int a, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.applyAsIntX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LIntUnaryOperator trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return a -> applyAsInt(a, exF, newMessage, messageParams);
	}

	default int applyAsInt(int a, @Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.applyAsIntX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LIntUnaryOperator trying(@Nonnull ExWF<RuntimeException> exF) {
		return a -> applyAsInt(a, exF);
	}

	default int applyAsIntThen(int a, @Nonnull LToIntFunction<Throwable> handler) {
		try {
			return this.applyAsIntX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsInt(e);
		}
	}

	default LIntUnaryOperator tryingThen(@Nonnull LToIntFunction<Throwable> handler) {
		return a -> applyAsIntThen(a, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default int nestingApplyAsInt(int a) {
		try {
			return this.applyAsIntX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default int shovingApplyAsInt(int a) {
		try {
			return this.applyAsIntX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static int handlingApplyAsInt(int a, LIntUnaryOperator func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsInt(a, handling);
	}

	static int tryApplyAsInt(int a, LIntUnaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsInt(a);
	}

	static int tryApplyAsInt(int a, LIntUnaryOperator func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.applyAsInt(a, exF, newMessage, messageParams);
	}

	static int tryApplyAsInt(int a, LIntUnaryOperator func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.applyAsInt(a, exF);
	}

	static int tryApplyAsIntThen(int a, LIntUnaryOperator func, @Nonnull LToIntFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsIntThen(a, handler);
	}

	default int failSafeApplyAsInt(int a, @Nonnull LIntUnaryOperator failSafe) {
		try {
			return applyAsInt(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.applyAsInt(a);
		}
	}

	static int failSafeApplyAsInt(int a, LIntUnaryOperator func, @Nonnull LIntUnaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.applyAsInt(a);
		} else {
			return func.failSafeApplyAsInt(a, failSafe);
		}
	}

	static LIntUnaryOperator failSafe(LIntUnaryOperator func, @Nonnull LIntUnaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeApplyAsInt(a, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default int nonNullApplyAsInt(int a) {
		return applyAsInt(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LIntUnaryOperator.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_a, int max_a, @Nonnull LIntUnaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_a <= max_a) {
			for (int a = min_a; a <= max_a; a++) {
				func.applyAsInt(a);
			}
		} else {
			for (int a = min_a; a >= max_a; a--) {
				func.applyAsInt(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_a, int max_a, @Nonnull LIntUnaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_a <= max_a) {
			for (int a = min_a; a < max_a; a++) {
				func.applyAsInt(a);
			}
		} else {
			for (int a = min_a; a > max_a; a--) {
				func.applyAsInt(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_a, @Nonnull LIntUnaryOperator func) {
		if (max_a < 0)
			return;
		fromTill(0, max_a, func);
	}

	/** Change function to consumer that ignores output. */
	public default LIntConsumer toConsumer() {
		return this::applyAsInt;
	}

	/** Calls domain consumer before main function. */
	public default LIntUnaryOperator beforeDo(@Nonnull LIntConsumer before) {
		Null.nonNullArg(before, "before");
		return (int a) -> {
			before.accept(a);
			return applyAsInt(a);
		};
	}

	/** Calls codomain consumer after main function. */
	public default LIntUnaryOperator afterDo(@Nonnull LIntConsumer after) {
		Null.nonNullArg(after, "after");
		return (int a) -> {
			final int retval = applyAsInt(a);
			after.accept(retval);
			return retval;
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LIntSupplier capture(int a) {
		return () -> this.applyAsInt(a);
	}

	/** Creates function that always returns the same value. */
	static LIntUnaryOperator constant(int r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LIntUnaryOperator intUnaryOp(final @Nonnull LIntUnaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LIntUnaryOperator recursive(final @Nonnull LFunction<LIntUnaryOperator, LIntUnaryOperator> selfLambda) {
		final LIntUnaryOperatorSingle single = new LIntUnaryOperatorSingle();
		LIntUnaryOperator func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LIntUnaryOperatorSingle implements LSingle<LIntUnaryOperator>, LIntUnaryOperator {
		private LIntUnaryOperator target = null;

		@Override
		public int applyAsIntX(int a) throws Throwable {
			return target.applyAsIntX(a);
		}

		@Override
		public LIntUnaryOperator value() {
			return target;
		}
	}

	@Nonnull
	static LIntUnaryOperator intUnaryOpThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LIntUnaryOperator intUnaryOpThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce(message);
		};
	}

	static int call(int a, final @Nonnull LIntUnaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsInt(a);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static LIntUnaryOperator wrap(final IntUnaryOperator other) {
		return other::applyAsInt;
	}
	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceInt). */
	@Nonnull
	static LIntUnaryOperator safe() {
		return LIntUnaryOperator::produceInt;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LIntUnaryOperator> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LIntUnaryOperator safe(final @Nullable LIntUnaryOperator other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LIntUnaryOperator> safeSupplier(final @Nullable LSupplier<LIntUnaryOperator> supplier) {
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
	default LIntUnaryOperator compose(@Nonnull final LIntUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsInt(before.applyAsInt(v));
	}

	public static LIntUnaryOperator composed(@Nonnull final LIntUnaryOperator before, LIntUnaryOperator after) {
		return after.compose(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToIntFunction<V> intUnaryOpCompose(@Nonnull final LToIntFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsInt(before.applyAsInt(v));
	}

	public static <V> LToIntFunction<V> composed(@Nonnull final LToIntFunction<? super V> before, LIntUnaryOperator after) {
		return after.intUnaryOpCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LIntFunction<V> then(@Nonnull LIntFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.apply(this.applyAsInt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToByteFunction thenToByte(@Nonnull LIntToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsByte(this.applyAsInt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToSrtFunction thenToSrt(@Nonnull LIntToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsSrt(this.applyAsInt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntUnaryOperator thenToInt(@Nonnull LIntUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsInt(this.applyAsInt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToLongFunction thenToLong(@Nonnull LIntToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsLong(this.applyAsInt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToFltFunction thenToFlt(@Nonnull LIntToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsFlt(this.applyAsInt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToDblFunction thenToDbl(@Nonnull LIntToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsDbl(this.applyAsInt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntToCharFunction thenToChar(@Nonnull LIntToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsChar(this.applyAsInt(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntPredicate thenToBool(@Nonnull LIntPredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.test(this.applyAsInt(a));
	}

	// </editor-fold>

	/** Returns a function that always returns its input argument. */
	@Nonnull
	static LIntUnaryOperator identity() {
		return t -> t;
	}

	static int identity(int a) {
		return a;
	}

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LIntUnaryOperator) Operator */
	public static int produceInt(int a) {
		return Function4U.defaultInteger;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C0> void forEach(IndexedRead<C0, aInt> ia, C0 source, LIntConsumer consumer) {
		int size = ia.size(source);
		LOiToIntFunction<Object> oiFunc0 = (LOiToIntFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			int a = oiFunc0.applyAsInt(source, i);
			consumer.accept(this.applyAsInt(a));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C0, I0> void iterate(SequentialRead<C0, I0, aInt> sa, C0 source, LIntConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).apply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToIntFunction<Object> nextFunc0 = (LToIntFunction) sa.supplier();
		while (testFunc0.test(iterator0)) {
			int a = nextFunc0.applyAsInt(iterator0);
			consumer.accept(this.applyAsInt(a));
		}
	}

}
