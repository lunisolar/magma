/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2022 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.func.operator.ternary;

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
 * Non-throwing functional interface (lambda) LDblTernaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 3): double a1,double a2,double a3
 *
 * Co-domain: double
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LDblTernaryOperator extends MetaOperator, MetaInterface.NonThrowing, Codomain<aDouble>, Domain3<aDouble, aDouble, aDouble> { // NOSONAR

	String DESCRIPTION = "LDblTernaryOperator: double applyAsDbl(double a1,double a2,double a3)";

	// double applyAsDbl(double a1,double a2,double a3) ;
	default double applyAsDbl(double a1, double a2, double a3) {
		// return nestingApplyAsDbl(a1,a2,a3);
		try {
			return this.applyAsDblX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsDbl(double a1,double a2,double a3)
	 */
	double applyAsDblX(double a1, double a2, double a3) throws Throwable;

	default double tupleApplyAsDbl(LDblTriple args) {
		return applyAsDbl(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions according to the instructions. */
	default double handlingApplyAsDbl(double a1, double a2, double a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsDblX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LDblTernaryOperator handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> handlingApplyAsDbl(a1, a2, a3, handling);
	}

	default double applyAsDbl(double a1, double a2, double a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyAsDblX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default double applyAsDbl(double a1, double a2, double a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyAsDblX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default double applyAsDbl(double a1, double a2, double a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyAsDblX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default double applyAsDbl(double a1, double a2, double a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyAsDblX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LDblTernaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2, a3) -> applyAsDbl(a1, a2, a3, factory, newMessage);
	}

	default LDblTernaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2, a3) -> applyAsDbl(a1, a2, a3, factory, newMessage, param1);
	}

	default LDblTernaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2, a3) -> applyAsDbl(a1, a2, a3, factory, newMessage, param1, param1);
	}

	default LDblTernaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2, a3) -> applyAsDbl(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	default double applyAsDbl(double a1, double a2, double a3, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyAsDblX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LDblTernaryOperator trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2, a3) -> applyAsDbl(a1, a2, a3, factory);
	}

	default double applyAsDblThen(double a1, double a2, double a3, @Nonnull LToDblFunction<Throwable> handler) {
		try {
			return this.applyAsDblX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsDbl(e);
		}
	}

	default LDblTernaryOperator tryingThen(@Nonnull LToDblFunction<Throwable> handler) {
		return (a1, a2, a3) -> applyAsDblThen(a1, a2, a3, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default double nestingApplyAsDbl(double a1, double a2, double a3) {
		try {
			return this.applyAsDblX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default double shovingApplyAsDbl(double a1, double a2, double a3) {
		try {
			return this.applyAsDblX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static double shovingApplyAsDbl(double a1, double a2, double a3, LDblTernaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.shovingApplyAsDbl(a1, a2, a3);
	}

	static double handlingApplyAsDbl(double a1, double a2, double a3, LDblTernaryOperator func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsDbl(a1, a2, a3, handling);
	}

	static double tryApplyAsDbl(double a1, double a2, double a3, LDblTernaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsDbl(a1, a2, a3);
	}

	static double tryApplyAsDbl(double a1, double a2, double a3, LDblTernaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.applyAsDbl(a1, a2, a3, factory, newMessage);
	}

	static double tryApplyAsDbl(double a1, double a2, double a3, LDblTernaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.applyAsDbl(a1, a2, a3, factory, newMessage, param1);
	}

	static double tryApplyAsDbl(double a1, double a2, double a3, LDblTernaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.applyAsDbl(a1, a2, a3, factory, newMessage, param1, param2);
	}

	static double tryApplyAsDbl(double a1, double a2, double a3, LDblTernaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.applyAsDbl(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	static double tryApplyAsDbl(double a1, double a2, double a3, LDblTernaryOperator func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.applyAsDbl(a1, a2, a3, factory);
	}

	static double tryApplyAsDblThen(double a1, double a2, double a3, LDblTernaryOperator func, @Nonnull LToDblFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsDblThen(a1, a2, a3, handler);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default double nonNullApplyAsDbl(double a1, double a2, double a3) {
		return applyAsDbl(a1, a2, a3);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LDblTernaryOperator.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, double a1, double a2, double a3, @Nonnull LDblTernaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.applyAsDbl(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.applyAsDbl(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, double a1, double a2, double a3, @Nonnull LDblTernaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.applyAsDbl(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.applyAsDbl(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, double a1, double a2, double a3, @Nonnull LDblTernaryOperator func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, a3, func);
	}

	/**  */
	public static LDblTernaryOperator uncurry(@Nonnull LDblFunction<LDblFunction<LDblUnaryOperator>> func) {
		Null.nonNullArg(func, "func");
		return (double a1, double a2, double a3) -> func.apply(a1).apply(a2).applyAsDbl(a3);
	}

	/** Change function to consumer that ignores output. */
	default LTriDblConsumer toConsumer() {
		return this::applyAsDbl;
	}

	/** Calls domain consumer before main function. */
	default LDblTernaryOperator beforeDo(@Nonnull LTriDblConsumer before) {
		Null.nonNullArg(before, "before");
		return (double a1, double a2, double a3) -> {
			before.accept(a1, a2, a3);
			return applyAsDbl(a1, a2, a3);
		};
	}

	/** Calls codomain consumer after main function. */
	default LDblTernaryOperator afterDo(@Nonnull LDblConsumer after) {
		Null.nonNullArg(after, "after");
		return (double a1, double a2, double a3) -> {
			final double retval = applyAsDbl(a1, a2, a3);
			after.accept(retval);
			return retval;
		};
	}

	/** Creates function that always returns the same value. */
	static LDblTernaryOperator constant(double r) {
		return (a1, a2, a3) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LDblTernaryOperator dblTernaryOp(final @Nonnull LDblTernaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S implements LDblTernaryOperator {
		private LDblTernaryOperator target = null;
		@Override
		public double applyAsDblX(double a1, double a2, double a3) throws Throwable {
			return target.applyAsDblX(a1, a2, a3);
		}
	}

	@Nonnull
	static LDblTernaryOperator recursive(final @Nonnull LFunction<LDblTernaryOperator, LDblTernaryOperator> selfLambda) {
		final S single = new S();
		LDblTernaryOperator func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static M mementoOf(double a1, double a2, double a3, LDblTernaryOperator function) {
		var initialValue = function.applyAsDbl(a1, a2, a3);
		return initializedMementoOf(initialValue, function);
	}

	public static M initializedMementoOf(double initialValue, LDblTernaryOperator function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static M deltaOf(double a1, double a2, double a3, LDblTernaryOperator function, LDblBinaryOperator deltaFunction) {
		var initialValue = function.applyAsDbl(a1, a2, a3);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static M deltaOf(double a1, double a2, double a3, LDblTernaryOperator function) {
		var initialValue = function.applyAsDbl(a1, a2, a3);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (x2 - x1));
	}

	public static M initializedDeltaOf(double initialValue, LDblTernaryOperator function, LDblBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.applyAsDbl(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.applyAsDbl(x1, x2));
	}

	public static M memento(double initialBaseValue, double initialValue, LDblTernaryOperator baseFunction, LDblTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LDblTernaryOperator.M)
	 */
	@NotThreadSafe
	final class M implements LDblTernaryOperator {

		private final LDblTernaryOperator baseFunction;
		private double lastBaseValue;
		private double lastValue;
		private final LDblTernaryOperator mementoFunction;

		private M(double lastBaseValue, double lastValue, LDblTernaryOperator baseFunction, LDblTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public double applyAsDblX(double a1, double a2, double a3) throws Throwable {
			double x1 = lastBaseValue;
			double x2 = lastBaseValue = baseFunction.applyAsDblX(a1, a2, a3);

			return lastValue = mementoFunction.applyAsDblX(lastValue, x1, x2);
		}

		public double currentApplyAsDbl(double a1, double a2, double a3) {
			double x1 = lastBaseValue;
			double x2 = baseFunction.applyAsDbl(a1, a2, a3);

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
	static LDblTernaryOperator dblTernaryOpThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LDblTernaryOperator dblTernaryOpThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce(message);
		};
	}

	static double call(double a1, double a2, double a3, final @Nonnull LDblTernaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsDbl(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LDblTernaryOperator compose(@Nonnull final LDblUnaryOperator before1, @Nonnull final LDblUnaryOperator before2, @Nonnull final LDblUnaryOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.applyAsDbl(before1.applyAsDbl(v1), before2.applyAsDbl(v2), before3.applyAsDbl(v3));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LTriDblFunction<V> then(@Nonnull LDblFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.apply(this.applyAsDbl(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblTernaryOperator thenToDbl(@Nonnull LDblUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.applyAsDbl(this.applyAsDbl(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LTriDblPredicate thenToBool(@Nonnull LDblPredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.test(this.applyAsDbl(a1, a2, a3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LDblTernaryOperator) Operator */
	public static double doNothing(double a1, double a2, double a3) {
		return Function4U.defaultDouble;
	}

}
