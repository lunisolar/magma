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
 * Non-throwing functional interface (lambda) LDblUnaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 1): double a
 *
 * Co-domain: double
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LDblUnaryOperator extends DoubleUnaryOperator, MetaOperator, MetaInterface.NonThrowing, Codomain<aDouble>, Domain1<aDouble> { // NOSONAR

	String DESCRIPTION = "LDblUnaryOperator: double applyAsDbl(double a)";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LDblUnaryOperator interface should be discouraged.
	 */
	@Override
	@Deprecated
	default double applyAsDouble(double a) {
		return this.applyAsDbl(a);
	}

	// double applyAsDbl(double a) ;
	default double applyAsDbl(double a) {
		// return nestingApplyAsDbl(a);
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsDbl(double a)
	 */
	double applyAsDblX(double a) throws Throwable;

	default double tupleApplyAsDbl(LDblSingle args) {
		return applyAsDbl(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default double handlingApplyAsDbl(double a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LDblUnaryOperator handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> handlingApplyAsDbl(a, handling);
	}

	default double applyAsDbl(double a, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LDblUnaryOperator trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return a -> applyAsDbl(a, exF, newMessage, messageParams);
	}

	default double applyAsDbl(double a, @Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LDblUnaryOperator trying(@Nonnull ExWF<RuntimeException> exF) {
		return a -> applyAsDbl(a, exF);
	}

	default double applyAsDblThen(double a, @Nonnull LToDblFunction<Throwable> handler) {
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsDbl(e);
		}
	}

	default LDblUnaryOperator tryingThen(@Nonnull LToDblFunction<Throwable> handler) {
		return a -> applyAsDblThen(a, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default double nestingApplyAsDbl(double a) {
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default double shovingApplyAsDbl(double a) {
		try {
			return this.applyAsDblX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static double handlingApplyAsDbl(double a, LDblUnaryOperator func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsDbl(a, handling);
	}

	static double tryApplyAsDbl(double a, LDblUnaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsDbl(a);
	}

	static double tryApplyAsDbl(double a, LDblUnaryOperator func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.applyAsDbl(a, exF, newMessage, messageParams);
	}

	static double tryApplyAsDbl(double a, LDblUnaryOperator func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.applyAsDbl(a, exF);
	}

	static double tryApplyAsDblThen(double a, LDblUnaryOperator func, @Nonnull LToDblFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsDblThen(a, handler);
	}

	default double failSafeApplyAsDbl(double a, @Nonnull LDblUnaryOperator failSafe) {
		try {
			return applyAsDbl(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.applyAsDbl(a);
		}
	}

	static double failSafeApplyAsDbl(double a, LDblUnaryOperator func, @Nonnull LDblUnaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.applyAsDbl(a);
		} else {
			return func.failSafeApplyAsDbl(a, failSafe);
		}
	}

	static LDblUnaryOperator failSafe(LDblUnaryOperator func, @Nonnull LDblUnaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeApplyAsDbl(a, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default double nonNullApplyAsDbl(double a) {
		return applyAsDbl(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LDblUnaryOperator.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, double a, @Nonnull LDblUnaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.applyAsDbl(a);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.applyAsDbl(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, double a, @Nonnull LDblUnaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.applyAsDbl(a);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.applyAsDbl(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, double a, @Nonnull LDblUnaryOperator func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a, func);
	}

	/** Change function to consumer that ignores output. */
	public default LDblConsumer toConsumer() {
		return this::applyAsDbl;
	}

	/** Calls domain consumer before main function. */
	public default LDblUnaryOperator beforeDo(@Nonnull LDblConsumer before) {
		Null.nonNullArg(before, "before");
		return (double a) -> {
			before.accept(a);
			return applyAsDbl(a);
		};
	}

	/** Calls codomain consumer after main function. */
	public default LDblUnaryOperator afterDo(@Nonnull LDblConsumer after) {
		Null.nonNullArg(after, "after");
		return (double a) -> {
			final double retval = applyAsDbl(a);
			after.accept(retval);
			return retval;
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LDblSupplier capture(double a) {
		return () -> this.applyAsDbl(a);
	}

	/** Creates function that always returns the same value. */
	static LDblUnaryOperator constant(double r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LDblUnaryOperator dblUnaryOp(final @Nonnull LDblUnaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LDblUnaryOperator recursive(final @Nonnull LFunction<LDblUnaryOperator, LDblUnaryOperator> selfLambda) {
		final LDblUnaryOperatorSingle single = new LDblUnaryOperatorSingle();
		LDblUnaryOperator func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LDblUnaryOperatorSingle implements LSingle<LDblUnaryOperator>, LDblUnaryOperator {
		private LDblUnaryOperator target = null;

		@Override
		public double applyAsDblX(double a) throws Throwable {
			return target.applyAsDblX(a);
		}

		@Override
		public LDblUnaryOperator value() {
			return target;
		}
	}

	@Nonnull
	static LDblUnaryOperator dblUnaryOpThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LDblUnaryOperator dblUnaryOpThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce(message);
		};
	}

	static double call(double a, final @Nonnull LDblUnaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsDbl(a);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static LDblUnaryOperator wrap(final DoubleUnaryOperator other) {
		return other::applyAsDouble;
	}
	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as doNothing). */
	@Nonnull
	static LDblUnaryOperator safe() {
		return LDblUnaryOperator::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LDblUnaryOperator> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LDblUnaryOperator safe(final @Nullable LDblUnaryOperator other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LDblUnaryOperator> safeSupplier(final @Nullable LSupplier<LDblUnaryOperator> supplier) {
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
	default LDblUnaryOperator compose(@Nonnull final LDblUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsDbl(before.applyAsDbl(v));
	}

	public static LDblUnaryOperator composed(@Nonnull final LDblUnaryOperator before, LDblUnaryOperator after) {
		return after.compose(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LToDblFunction<V> dblUnaryOpCompose(@Nonnull final LToDblFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.applyAsDbl(before.applyAsDbl(v));
	}

	public static <V> LToDblFunction<V> composed(@Nonnull final LToDblFunction<? super V> before, LDblUnaryOperator after) {
		return after.dblUnaryOpCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LDblFunction<V> then(@Nonnull LDblFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.apply(this.applyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToByteFunction thenToByte(@Nonnull LDblToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsByte(this.applyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToSrtFunction thenToSrt(@Nonnull LDblToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsSrt(this.applyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToIntFunction thenToInt(@Nonnull LDblToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsInt(this.applyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToLongFunction thenToLong(@Nonnull LDblToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsLong(this.applyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToFltFunction thenToFlt(@Nonnull LDblToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsFlt(this.applyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblUnaryOperator thenToDbl(@Nonnull LDblUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsDbl(this.applyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblToCharFunction thenToChar(@Nonnull LDblToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsChar(this.applyAsDbl(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblPredicate thenToBool(@Nonnull LDblPredicate after) {
		Null.nonNullArg(after, "after");
		return a -> after.test(this.applyAsDbl(a));
	}

	// </editor-fold>

	/** Returns a function that always returns its input argument. */
	@Nonnull
	static LDblUnaryOperator identity() {
		return t -> t;
	}

	static double identity(double a) {
		return a;
	}

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LDblUnaryOperator) Operator */
	public static double doNothing(double a) {
		return Function4U.defaultDouble;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C0> void forEach(IndexedRead<C0, aDouble> ia, C0 source, LDblConsumer consumer) {
		int size = ia.size(source);
		LOiToDblFunction<Object> oiFunc0 = (LOiToDblFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			double a = oiFunc0.applyAsDbl(source, i);
			consumer.accept(this.applyAsDbl(a));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C0, I0> void iterate(SequentialRead<C0, I0, aDouble> sa, C0 source, LDblConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).apply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToDblFunction<Object> nextFunc0 = (LToDblFunction) sa.supplier();
		while (testFunc0.test(iterator0)) {
			double a = nextFunc0.applyAsDbl(iterator0);
			consumer.accept(this.applyAsDbl(a));
		}
	}

}
