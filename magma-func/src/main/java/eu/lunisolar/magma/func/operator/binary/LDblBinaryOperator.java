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

package eu.lunisolar.magma.func.operator.binary;

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

	default double applyAsDbl(double a1, double a2, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.applyAsDblX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LDblBinaryOperator trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return (a1, a2) -> applyAsDbl(a1, a2, exF, newMessage, messageParams);
	}

	default double applyAsDbl(double a1, double a2, @Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.applyAsDblX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LDblBinaryOperator trying(@Nonnull ExWF<RuntimeException> exF) {
		return (a1, a2) -> applyAsDbl(a1, a2, exF);
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

	static double handlingApplyAsDbl(double a1, double a2, LDblBinaryOperator func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsDbl(a1, a2, handling);
	}

	static double tryApplyAsDbl(double a1, double a2, LDblBinaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsDbl(a1, a2);
	}

	static double tryApplyAsDbl(double a1, double a2, LDblBinaryOperator func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.applyAsDbl(a1, a2, exF, newMessage, messageParams);
	}

	static double tryApplyAsDbl(double a1, double a2, LDblBinaryOperator func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.applyAsDbl(a1, a2, exF);
	}

	static double tryApplyAsDblThen(double a1, double a2, LDblBinaryOperator func, @Nonnull LToDblFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsDblThen(a1, a2, handler);
	}

	default double failSafeApplyAsDbl(double a1, double a2, @Nonnull LDblBinaryOperator failSafe) {
		try {
			return applyAsDbl(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.applyAsDbl(a1, a2);
		}
	}

	static double failSafeApplyAsDbl(double a1, double a2, LDblBinaryOperator func, @Nonnull LDblBinaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.applyAsDbl(a1, a2);
		} else {
			return func.failSafeApplyAsDbl(a1, a2, failSafe);
		}
	}

	static LDblBinaryOperator failSafe(LDblBinaryOperator func, @Nonnull LDblBinaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeApplyAsDbl(a1, a2, func, failSafe);
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

	public default LDblUnaryOperator lShrink(@Nonnull LDblUnaryOperator left) {
		Null.nonNullArg(left, "left");
		return a2 -> applyAsDbl(left.applyAsDbl(a2), a2);
	}

	public default LDblUnaryOperator lShrink_(double a1) {
		return a2 -> applyAsDbl(a1, a2);
	}

	public static LDblUnaryOperator lShrunken(@Nonnull LDblUnaryOperator left, @Nonnull LDblBinaryOperator func) {
		Null.nonNullArg(left, "left");
		Null.nonNullArg(func, "func");
		return func.lShrink(left);
	}

	public static LDblUnaryOperator lShrunken_(double a1, @Nonnull LDblBinaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.lShrink_(a1);
	}

	public default LDblUnaryOperator rShrink(@Nonnull LDblUnaryOperator right) {
		Null.nonNullArg(right, "right");
		return a1 -> applyAsDbl(a1, right.applyAsDbl(a1));
	}

	public default LDblUnaryOperator rShrink_(double a2) {
		return a1 -> applyAsDbl(a1, a2);
	}

	public static LDblUnaryOperator rShrunken(@Nonnull LDblUnaryOperator right, @Nonnull LDblBinaryOperator func) {
		Null.nonNullArg(right, "right");
		Null.nonNullArg(func, "func");
		return func.rShrink(right);
	}

	public static LDblUnaryOperator rShrunken_(double a2, @Nonnull LDblBinaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.rShrink_(a2);
	}

	/**  */
	public static LDblBinaryOperator uncurry(@Nonnull LDblFunction<LDblUnaryOperator> func) {
		Null.nonNullArg(func, "func");
		return (double a1, double a2) -> func.apply(a1).applyAsDbl(a2);
	}

	/** Change function to consumer that ignores output. */
	public default LBiDblConsumer toConsumer() {
		return this::applyAsDbl;
	}

	/** Calls domain consumer before main function. */
	public default LDblBinaryOperator beforeDo(@Nonnull LBiDblConsumer before) {
		Null.nonNullArg(before, "before");
		return (double a1, double a2) -> {
			before.accept(a1, a2);
			return applyAsDbl(a1, a2);
		};
	}

	/** Calls codomain consumer after main function. */
	public default LDblBinaryOperator afterDo(@Nonnull LDblConsumer after) {
		Null.nonNullArg(after, "after");
		return (double a1, double a2) -> {
			final double retval = applyAsDbl(a1, a2);
			after.accept(retval);
			return retval;
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LDblSupplier capture(double a1, double a2) {
		return () -> this.applyAsDbl(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static LDblBinaryOperator constant(double r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static LDblBinaryOperator apply1stAsDbl(@Nonnull LDblUnaryOperator func) {
		return (a1, a2) -> func.applyAsDbl(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static LDblBinaryOperator apply2ndAsDbl(@Nonnull LDblUnaryOperator func) {
		return (a1, a2) -> func.applyAsDbl(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LDblBinaryOperator dblBinaryOp(final @Nonnull LDblBinaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LDblBinaryOperator recursive(final @Nonnull LFunction<LDblBinaryOperator, LDblBinaryOperator> selfLambda) {
		final LDblBinaryOperatorSingle single = new LDblBinaryOperatorSingle();
		LDblBinaryOperator func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LDblBinaryOperatorSingle implements LSingle<LDblBinaryOperator>, LDblBinaryOperator {
		private LDblBinaryOperator target = null;

		@Override
		public double applyAsDblX(double a1, double a2) throws Throwable {
			return target.applyAsDblX(a1, a2);
		}

		@Override
		public LDblBinaryOperator value() {
			return target;
		}
	}

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

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceDouble). */
	@Nonnull
	static LDblBinaryOperator safe() {
		return LDblBinaryOperator::produceDouble;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LDblBinaryOperator> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LDblBinaryOperator safe(final @Nullable LDblBinaryOperator other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LDblBinaryOperator> safeSupplier(final @Nullable LSupplier<LDblBinaryOperator> supplier) {
		if (supplier == null) {
			return safeSupplier();
		} else {
			return supplier;
		}
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

	public static LDblBinaryOperator composed(@Nonnull final LDblUnaryOperator before1, @Nonnull final LDblUnaryOperator before2, LDblBinaryOperator after) {
		return after.compose(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LToDblBiFunction<V1, V2> dblBinaryOpCompose(@Nonnull final LToDblFunction<? super V1> before1, @Nonnull final LToDblFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.applyAsDbl(before1.applyAsDbl(v1), before2.applyAsDbl(v2));
	}

	public static <V1, V2> LToDblBiFunction<V1, V2> composed(@Nonnull final LToDblFunction<? super V1> before1, @Nonnull final LToDblFunction<? super V2> before2, LDblBinaryOperator after) {
		return after.dblBinaryOpCompose(before1, before2);
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
	public static double produceDouble(double a1, double a2) {
		return Function4U.defaultDouble;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2> void forEach(IndexedRead<C1, aDouble> ia1, C1 source1, IndexedRead<C2, aDouble> ia2, C2 source2, LDblConsumer consumer) {
		int size = ia1.size(source1);
		LOiToDblFunction<Object> oiFunc1 = (LOiToDblFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToDblFunction<Object> oiFunc2 = (LOiToDblFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			double a1 = oiFunc1.applyAsDbl(source1, i);
			double a2 = oiFunc2.applyAsDbl(source2, i);
			consumer.accept(this.applyAsDbl(a1, a2));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2> void iterate(SequentialRead<C1, I1, aDouble> sa1, C1 source1, IndexedRead<C2, aDouble> ia2, C2 source2, LDblConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToDblFunction<Object> nextFunc1 = (LToDblFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToDblFunction<Object> oiFunc2 = (LOiToDblFunction) ia2.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			double a1 = nextFunc1.applyAsDbl(iterator1);
			double a2 = oiFunc2.applyAsDbl(source2, i);
			consumer.accept(this.applyAsDbl(a1, a2));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2> void iterate(IndexedRead<C1, aDouble> ia1, C1 source1, SequentialRead<C2, I2, aDouble> sa2, C2 source2, LDblConsumer consumer) {
		int size = ia1.size(source1);
		LOiToDblFunction<Object> oiFunc1 = (LOiToDblFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToDblFunction<Object> nextFunc2 = (LToDblFunction) sa2.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			double a1 = oiFunc1.applyAsDbl(source1, i);
			double a2 = nextFunc2.applyAsDbl(iterator2);
			consumer.accept(this.applyAsDbl(a1, a2));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C1, I1, C2, I2> void iterate(SequentialRead<C1, I1, aDouble> sa1, C1 source1, SequentialRead<C2, I2, aDouble> sa2, C2 source2, LDblConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToDblFunction<Object> nextFunc1 = (LToDblFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToDblFunction<Object> nextFunc2 = (LToDblFunction) sa2.supplier();
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2)) {
			double a1 = nextFunc1.applyAsDbl(iterator1);
			double a2 = nextFunc2.applyAsDbl(iterator2);
			consumer.accept(this.applyAsDbl(a1, a2));
		}
	}

}
