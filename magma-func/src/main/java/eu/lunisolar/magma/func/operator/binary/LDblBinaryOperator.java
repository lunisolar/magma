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

package eu.lunisolar.magma.func.operator.binary;

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
 * Non-throwing functional interface (lambda) LDblBinaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 2): double a1,double a2
 *
 * Co-domain: double
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LDblBinaryOperator extends DoubleBinaryOperator, MetaOperator, MetaInterface.NonThrowing, Codomain<aDouble>, Domain2<aDouble, aDouble> { // NOSONAR

	String DESCRIPTION = "LDblBinaryOperator: double applyAsDbl(double a1,double a2)";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LDblBinaryOperator interface should be discouraged.
	 */
	@Override
	@Deprecated
	default double applyAsDouble(double a1, double a2) {
		return this.applyAsDbl(a1, a2);
	}

	// double applyAsDbl(double a1,double a2) ;
	default double applyAsDbl(double a1, double a2) {
		// return nestingApplyAsDbl(a1,a2);
		try {
			return this.applyAsDblX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsDbl(double a1,double a2)
	 */
	double applyAsDblX(double a1, double a2) throws Throwable;

	default double tupleApplyAsDbl(LDblPair args) {
		return applyAsDbl(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default double handlingApplyAsDbl(double a1, double a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsDblX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LDblBinaryOperator handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2) -> handlingApplyAsDbl(a1, a2, handling);
	}

	default double applyAsDbl(double a1, double a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyAsDblX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default double applyAsDbl(double a1, double a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyAsDblX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default double applyAsDbl(double a1, double a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyAsDblX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default double applyAsDbl(double a1, double a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyAsDblX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LDblBinaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2) -> applyAsDbl(a1, a2, factory, newMessage);
	}

	default LDblBinaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2) -> applyAsDbl(a1, a2, factory, newMessage, param1);
	}

	default LDblBinaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2) -> applyAsDbl(a1, a2, factory, newMessage, param1, param1);
	}

	default LDblBinaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2) -> applyAsDbl(a1, a2, factory, newMessage, param1, param2, param3);
	}

	default double applyAsDbl(double a1, double a2, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyAsDblX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LDblBinaryOperator trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2) -> applyAsDbl(a1, a2, factory);
	}

	default double applyAsDblThen(double a1, double a2, @Nonnull LToDblFunction<Throwable> handler) {
		try {
			return this.applyAsDblX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsDbl(e);
		}
	}

	default LDblBinaryOperator tryingThen(@Nonnull LToDblFunction<Throwable> handler) {
		return (a1, a2) -> applyAsDblThen(a1, a2, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default double nestingApplyAsDbl(double a1, double a2) {
		try {
			return this.applyAsDblX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default double shovingApplyAsDbl(double a1, double a2) {
		try {
			return this.applyAsDblX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static double shovingApplyAsDbl(double a1, double a2, LDblBinaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.shovingApplyAsDbl(a1, a2);
	}

	static double handlingApplyAsDbl(double a1, double a2, LDblBinaryOperator func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsDbl(a1, a2, handling);
	}

	static double tryApplyAsDbl(double a1, double a2, LDblBinaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsDbl(a1, a2);
	}

	static double tryApplyAsDbl(double a1, double a2, LDblBinaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.applyAsDbl(a1, a2, factory, newMessage);
	}

	static double tryApplyAsDbl(double a1, double a2, LDblBinaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.applyAsDbl(a1, a2, factory, newMessage, param1);
	}

	static double tryApplyAsDbl(double a1, double a2, LDblBinaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.applyAsDbl(a1, a2, factory, newMessage, param1, param2);
	}

	static double tryApplyAsDbl(double a1, double a2, LDblBinaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.applyAsDbl(a1, a2, factory, newMessage, param1, param2, param3);
	}

	static double tryApplyAsDbl(double a1, double a2, LDblBinaryOperator func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.applyAsDbl(a1, a2, factory);
	}

	static double tryApplyAsDblThen(double a1, double a2, LDblBinaryOperator func, @Nonnull LToDblFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsDblThen(a1, a2, handler);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default double nonNullApplyAsDbl(double a1, double a2) {
		return applyAsDbl(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LDblBinaryOperator.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, double a1, double a2, @Nonnull LDblBinaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.applyAsDbl(a1, a2);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.applyAsDbl(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, double a1, double a2, @Nonnull LDblBinaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.applyAsDbl(a1, a2);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.applyAsDbl(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, double a1, double a2, @Nonnull LDblBinaryOperator func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, func);
	}

	/**  */
	public static LDblBinaryOperator uncurry(@Nonnull LDblFunction<LDblUnaryOperator> func) {
		Null.nonNullArg(func, "func");
		return (double a1, double a2) -> func.apply(a1).applyAsDbl(a2);
	}

	/** Change function to consumer that ignores output. */
	default LBiDblConsumer toConsumer() {
		return this::applyAsDbl;
	}

	/** Calls domain consumer before main function. */
	default LDblBinaryOperator beforeDo(@Nonnull LBiDblConsumer before) {
		Null.nonNullArg(before, "before");
		return (double a1, double a2) -> {
			before.accept(a1, a2);
			return applyAsDbl(a1, a2);
		};
	}

	/** Calls codomain consumer after main function. */
	default LDblBinaryOperator afterDo(@Nonnull LDblConsumer after) {
		Null.nonNullArg(after, "after");
		return (double a1, double a2) -> {
			final double retval = applyAsDbl(a1, a2);
			after.accept(retval);
			return retval;
		};
	}

	/** Creates function that always returns the same value. */
	static LDblBinaryOperator constant(double r) {
		return (a1, a2) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LDblBinaryOperator dblBinaryOp(final @Nonnull LDblBinaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S implements LDblBinaryOperator {
		private LDblBinaryOperator target = null;
		@Override
		public double applyAsDblX(double a1, double a2) throws Throwable {
			return target.applyAsDblX(a1, a2);
		}
	}

	@Nonnull
	static LDblBinaryOperator recursive(final @Nonnull LFunction<LDblBinaryOperator, LDblBinaryOperator> selfLambda) {
		final S single = new S();
		LDblBinaryOperator func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static M mementoOf(double a1, double a2, LDblBinaryOperator function) {
		var initialValue = function.applyAsDbl(a1, a2);
		return initializedMementoOf(initialValue, function);
	}

	public static M initializedMementoOf(double initialValue, LDblBinaryOperator function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static M deltaOf(double a1, double a2, LDblBinaryOperator function, LDblBinaryOperator deltaFunction) {
		var initialValue = function.applyAsDbl(a1, a2);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static M deltaOf(double a1, double a2, LDblBinaryOperator function) {
		var initialValue = function.applyAsDbl(a1, a2);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (x2 - x1));
	}

	public static M initializedDeltaOf(double initialValue, LDblBinaryOperator function, LDblBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.applyAsDbl(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.applyAsDbl(x1, x2));
	}

	public static M memento(double initialBaseValue, double initialValue, LDblBinaryOperator baseFunction, LDblTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LDblBinaryOperator.M)
	 */
	@NotThreadSafe
	final class M implements LDblBinaryOperator {

		private final LDblBinaryOperator baseFunction;
		private double lastBaseValue;
		private double lastValue;
		private final LDblTernaryOperator mementoFunction;

		private M(double lastBaseValue, double lastValue, LDblBinaryOperator baseFunction, LDblTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public double applyAsDblX(double a1, double a2) throws Throwable {
			double x1 = lastBaseValue;
			double x2 = lastBaseValue = baseFunction.applyAsDblX(a1, a2);

			return lastValue = mementoFunction.applyAsDblX(lastValue, x1, x2);
		}

		public double currentApplyAsDbl(double a1, double a2) {
			double x1 = lastBaseValue;
			double x2 = baseFunction.applyAsDbl(a1, a2);

			return mementoFunction.applyAsDbl(lastValue, x1, x2);
		}

		public double lastValue() {
			return lastValue;
		};

		public double lastBaseValue() {
			return lastBaseValue;
		};
	}

	// </editor-fold>

	@Nonnull
	static LDblBinaryOperator dblBinaryOpThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LDblBinaryOperator dblBinaryOpThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce(message);
		};
	}

	static double call(double a1, double a2, final @Nonnull LDblBinaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsDbl(a1, a2);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static LDblBinaryOperator wrap(final DoubleBinaryOperator other) {
		return other::applyAsDouble;
	}
	// </editor-fold>

	/**
	 * Creates function that returns the lesser value according to the comparator.
	 * @see {@link java.util.function.BinaryOperator#minBy}
	 */
	@Nonnull
	static LDblBinaryOperator minBy(@Nonnull Comparator<Double> comparator) {
		Null.nonNullArg(comparator, "comparator");
		return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
	}

	/**
	 * Creates function that returns the lesser value according to the comparator.
	 * @see {@link java.util.function.BinaryOperator#maxBy}
	 */
	@Nonnull
	static LDblBinaryOperator maxBy(@Nonnull Comparator<Double> comparator) {
		Null.nonNullArg(comparator, "comparator");
		return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
	}

	/**
	 * Returns function that returns the lower value.
	 * @see {@link java.util.function.BinaryOperator#minBy}
	 */
	@Nonnull
	static LDblBinaryOperator min() {
		return Double::min;
	}

	/**
	 * Returns function that returns the higher value.
	 * @see {@link java.util.function.BinaryOperator#maxBy}
	 */
	@Nonnull
	static LDblBinaryOperator max() {
		return Double::max;
	}

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LDblBinaryOperator compose(@Nonnull final LDblUnaryOperator before1, @Nonnull final LDblUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.applyAsDbl(before1.applyAsDbl(v1), before2.applyAsDbl(v2));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LToDblBiFunction<V1, V2> unboxingCompose(@Nonnull final LToDblFunction<? super V1> before1, @Nonnull final LToDblFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.applyAsDbl(before1.applyAsDbl(v1), before2.applyAsDbl(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiDblFunction<V> then(@Nonnull LDblFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.apply(this.applyAsDbl(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblBinaryOperator thenToDbl(@Nonnull LDblUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsDbl(this.applyAsDbl(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiDblPredicate thenToBool(@Nonnull LDblPredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.test(this.applyAsDbl(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LDblBinaryOperator) Operator */
	public static double doNothing(double a1, double a2) {
		return Function4U.defaultDouble;
	}

}
