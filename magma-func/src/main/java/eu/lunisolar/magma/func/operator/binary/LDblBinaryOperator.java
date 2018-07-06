/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2016 Lunisolar (http://lunisolar.eu/).
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
public interface LDblBinaryOperator extends DoubleBinaryOperator, MetaOperator, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LDblBinaryOperator: double doApplyAsDbl(double a1,double a2)";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LDblBinaryOperator interface should be discouraged.
	 */
	@Override
	@Deprecated
	default double applyAsDouble(double a1, double a2) {
		return this.doApplyAsDbl(a1, a2);
	}

	// double doApplyAsDbl(double a1,double a2) ;
	default double doApplyAsDbl(double a1, double a2) {
		// return nestingDoApplyAsDbl(a1,a2);
		try {
			return this.doApplyAsDblX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doApplyAsDbl(double a1,double a2)
	 */
	double doApplyAsDblX(double a1, double a2) throws Throwable;

	default double tupleApplyAsDbl(LDblPair args) {
		return doApplyAsDbl(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default double handlingDoApplyAsDbl(double a1, double a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyAsDblX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default double tryDoApplyAsDbl(double a1, double a2, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyAsDblX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default double tryDoApplyAsDbl(double a1, double a2, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyAsDblX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default double tryDoApplyAsDblThen(double a1, double a2, @Nonnull LToDblFunction<Throwable> handler) {
		try {
			return this.doApplyAsDblX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApplyAsDbl(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default double nestingDoApplyAsDbl(double a1, double a2) {
		try {
			return this.doApplyAsDblX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default double shovingDoApplyAsDbl(double a1, double a2) {
		try {
			return this.doApplyAsDblX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static double handlingDoApplyAsDbl(double a1, double a2, LDblBinaryOperator func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApplyAsDbl(a1, a2, handling);
	}

	static double tryDoApplyAsDbl(double a1, double a2, LDblBinaryOperator func) {
		return tryDoApplyAsDbl(a1, a2, func, null);
	}

	static double tryDoApplyAsDbl(double a1, double a2, LDblBinaryOperator func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsDbl(a1, a2, exceptionFactory, newMessage, messageParams);
	}

	static double tryDoApplyAsDbl(double a1, double a2, LDblBinaryOperator func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsDbl(a1, a2, exceptionFactory);
	}

	static double tryDoApplyAsDblThen(double a1, double a2, LDblBinaryOperator func, @Nonnull LToDblFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsDblThen(a1, a2, handler);
	}

	default double failSafeDoApplyAsDbl(double a1, double a2, @Nonnull LDblBinaryOperator failSafe) {
		try {
			return doApplyAsDbl(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApplyAsDbl(a1, a2);
		}
	}

	static double failSafeDoApplyAsDbl(double a1, double a2, LDblBinaryOperator func, @Nonnull LDblBinaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApplyAsDbl(a1, a2);
		} else {
			return func.failSafeDoApplyAsDbl(a1, a2, failSafe);
		}
	}

	static LDblBinaryOperator failSafeDblBinaryOp(LDblBinaryOperator func, @Nonnull LDblBinaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeDoApplyAsDbl(a1, a2, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default double nonNullDoApplyAsDbl(double a1, double a2) {
		return doApplyAsDbl(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LDblBinaryOperator.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, double a1, double a2, LDblBinaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.doApplyAsDbl(a1, a2);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.doApplyAsDbl(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, double a1, double a2, LDblBinaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i < max_i; i++) {
				func.doApplyAsDbl(a1, a2);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.doApplyAsDbl(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, double a1, double a2, LDblBinaryOperator func) {
		fromTill(0, max_i, a1, a2, func);
	}

	public default LDblUnaryOperator lShrink(LDblUnaryOperator left) {
		return a2 -> doApplyAsDbl(left.doApplyAsDbl(a2), a2);
	}

	public default LDblUnaryOperator lShrinkc(double a1) {
		return a2 -> doApplyAsDbl(a1, a2);
	}

	public static LDblUnaryOperator lShrinked(LDblUnaryOperator left, LDblBinaryOperator func) {
		return func.lShrink(left);
	}

	public static LDblUnaryOperator lShrinkedc(double a1, LDblBinaryOperator func) {
		return func.lShrinkc(a1);
	}

	public default LDblUnaryOperator rShrink(LDblUnaryOperator right) {
		return a1 -> doApplyAsDbl(a1, right.doApplyAsDbl(a1));
	}

	public default LDblUnaryOperator rShrinkc(double a2) {
		return a1 -> doApplyAsDbl(a1, a2);
	}

	public static LDblUnaryOperator rShrinked(LDblUnaryOperator right, LDblBinaryOperator func) {
		return func.rShrink(right);
	}

	public static LDblUnaryOperator rShrinkedc(double a2, LDblBinaryOperator func) {
		return func.rShrinkc(a2);
	}

	/**  */
	public static LDblBinaryOperator uncurryDblBinaryOp(LDblFunction<LDblUnaryOperator> func) {
		return (double a1, double a2) -> func.doApply(a1).doApplyAsDbl(a2);
	}

	/** Captures arguments but delays the evaluation. */
	default LDblSupplier captureDblBinaryOp(double a1, double a2) {
		return () -> this.doApplyAsDbl(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static LDblBinaryOperator constant(double r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static LDblBinaryOperator apply1stAsDbl(@Nonnull LDblUnaryOperator func) {
		return (a1, a2) -> func.doApplyAsDbl(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static LDblBinaryOperator apply2ndAsDbl(@Nonnull LDblUnaryOperator func) {
		return (a1, a2) -> func.doApplyAsDbl(a2);
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
		LDblBinaryOperator func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LDblBinaryOperatorSingle implements LSingle<LDblBinaryOperator>, LDblBinaryOperator {
		private LDblBinaryOperator target = null;

		@Override
		public double doApplyAsDblX(double a1, double a2) throws Throwable {
			return target.doApplyAsDblX(a1, a2);
		}

		@Override
		public LDblBinaryOperator value() {
			return target;
		}
	}

	@Nonnull
	static LDblBinaryOperator dblBinaryOpThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LDblBinaryOperator dblBinaryOpThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce(message);
		};
	}

	static double call(double a1, double a2, final @Nonnull LDblBinaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsDbl(a1, a2);
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
	default LDblBinaryOperator dblBinaryOpComposeDbl(@Nonnull final LDblUnaryOperator before1, @Nonnull final LDblUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doApplyAsDbl(before1.doApplyAsDbl(v1), before2.doApplyAsDbl(v2));
	}

	public static LDblBinaryOperator composedDbl(@Nonnull final LDblUnaryOperator before1, @Nonnull final LDblUnaryOperator before2, LDblBinaryOperator after) {
		return after.dblBinaryOpComposeDbl(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LToDblBiFunction<V1, V2> dblBinaryOpCompose(@Nonnull final LToDblFunction<? super V1> before1, @Nonnull final LToDblFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doApplyAsDbl(before1.doApplyAsDbl(v1), before2.doApplyAsDbl(v2));
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
		return (a1, a2) -> after.doApply(this.doApplyAsDbl(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LDblBinaryOperator thenToDbl(@Nonnull LDblUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsDbl(this.doApplyAsDbl(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiDblPredicate thenToBool(@Nonnull LDblPredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doTest(this.doApplyAsDbl(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LDblBinaryOperator nestingDblBinaryOp() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LDblBinaryOperator shovingDblBinaryOp() {
		return this;
	}

	// </editor-fold>

	/** Does nothing (LDblBinaryOperator) Operator */
	public static double produceDouble(double a1, double a2) {
		return Function4U.defaultDouble;
	}

	// MAP: FOR, [SourcePurpose{arg=double a1, type=IA}, SourcePurpose{arg=double a2, type=IA}, SourcePurpose{arg=LDblConsumer consumer, type=CONST}]
	default <C1, C2> void forEach(IndexedRead<C1, aDouble> ia1, C1 source1, IndexedRead<C2, aDouble> ia2, C2 source2, LDblConsumer consumer) {
		int size = ia1.size(source1);
		LOiToDblFunction<Object> oiFunc1 = (LOiToDblFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToDblFunction<Object> oiFunc2 = (LOiToDblFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			double a1 = oiFunc1.doApplyAsDbl(source1, i);
			double a2 = oiFunc2.doApplyAsDbl(source2, i);
			consumer.doAccept(this.doApplyAsDbl(a1, a2));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=double a1, type=SA}, SourcePurpose{arg=double a2, type=IA}, SourcePurpose{arg=LDblConsumer consumer, type=CONST}]
	default <C1, I1, C2> void iterate(SequentialRead<C1, I1, aDouble> sa1, C1 source1, IndexedRead<C2, aDouble> ia2, C2 source2, LDblConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToDblFunction<Object> nextFunc1 = (LToDblFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiToDblFunction<Object> oiFunc2 = (LOiToDblFunction) ia2.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size) {
			double a1 = nextFunc1.doApplyAsDbl(iterator1);
			double a2 = oiFunc2.doApplyAsDbl(source2, i);
			consumer.doAccept(this.doApplyAsDbl(a1, a2));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=double a1, type=IA}, SourcePurpose{arg=double a2, type=SA}, SourcePurpose{arg=LDblConsumer consumer, type=CONST}]
	default <C1, C2, I2> void iterate(IndexedRead<C1, aDouble> ia1, C1 source1, SequentialRead<C2, I2, aDouble> sa2, C2 source2, LDblConsumer consumer) {
		int size = ia1.size(source1);
		LOiToDblFunction<Object> oiFunc1 = (LOiToDblFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToDblFunction<Object> nextFunc2 = (LToDblFunction) sa2.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2)) {
			double a1 = oiFunc1.doApplyAsDbl(source1, i);
			double a2 = nextFunc2.doApplyAsDbl(iterator2);
			consumer.doAccept(this.doApplyAsDbl(a1, a2));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=double a1, type=SA}, SourcePurpose{arg=double a2, type=SA}, SourcePurpose{arg=LDblConsumer consumer, type=CONST}]
	default <C1, I1, C2, I2> void iterate(SequentialRead<C1, I1, aDouble> sa1, C1 source1, SequentialRead<C2, I2, aDouble> sa2, C2 source2, LDblConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToDblFunction<Object> nextFunc1 = (LToDblFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToDblFunction<Object> nextFunc2 = (LToDblFunction) sa2.getter();
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2)) {
			double a1 = nextFunc1.doApplyAsDbl(iterator1);
			double a2 = nextFunc2.doApplyAsDbl(iterator2);
			consumer.doAccept(this.doApplyAsDbl(a1, a2));
		}
	}

}
