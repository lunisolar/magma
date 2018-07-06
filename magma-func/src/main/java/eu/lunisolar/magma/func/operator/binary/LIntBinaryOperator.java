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
 * Non-throwing functional interface (lambda) LIntBinaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 2): int a1,int a2
 *
 * Co-domain: int
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LIntBinaryOperator extends IntBinaryOperator, MetaOperator, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LIntBinaryOperator: int doApplyAsInt(int a1,int a2)";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LIntBinaryOperator interface should be discouraged.
	 */
	@Override
	@Deprecated
	default int applyAsInt(int a1, int a2) {
		return this.doApplyAsInt(a1, a2);
	}

	// int doApplyAsInt(int a1,int a2) ;
	default int doApplyAsInt(int a1, int a2) {
		// return nestingDoApplyAsInt(a1,a2);
		try {
			return this.doApplyAsIntX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doApplyAsInt(int a1,int a2)
	 */
	int doApplyAsIntX(int a1, int a2) throws Throwable;

	default int tupleApplyAsInt(LIntPair args) {
		return doApplyAsInt(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default int handlingDoApplyAsInt(int a1, int a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyAsIntX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default int tryDoApplyAsInt(int a1, int a2, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyAsIntX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default int tryDoApplyAsInt(int a1, int a2, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyAsIntX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default int tryDoApplyAsIntThen(int a1, int a2, @Nonnull LToIntFunction<Throwable> handler) {
		try {
			return this.doApplyAsIntX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApplyAsInt(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default int nestingDoApplyAsInt(int a1, int a2) {
		try {
			return this.doApplyAsIntX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default int shovingDoApplyAsInt(int a1, int a2) {
		try {
			return this.doApplyAsIntX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static int handlingDoApplyAsInt(int a1, int a2, LIntBinaryOperator func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApplyAsInt(a1, a2, handling);
	}

	static int tryDoApplyAsInt(int a1, int a2, LIntBinaryOperator func) {
		return tryDoApplyAsInt(a1, a2, func, null);
	}

	static int tryDoApplyAsInt(int a1, int a2, LIntBinaryOperator func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsInt(a1, a2, exceptionFactory, newMessage, messageParams);
	}

	static int tryDoApplyAsInt(int a1, int a2, LIntBinaryOperator func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsInt(a1, a2, exceptionFactory);
	}

	static int tryDoApplyAsIntThen(int a1, int a2, LIntBinaryOperator func, @Nonnull LToIntFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsIntThen(a1, a2, handler);
	}

	default int failSafeDoApplyAsInt(int a1, int a2, @Nonnull LIntBinaryOperator failSafe) {
		try {
			return doApplyAsInt(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApplyAsInt(a1, a2);
		}
	}

	static int failSafeDoApplyAsInt(int a1, int a2, LIntBinaryOperator func, @Nonnull LIntBinaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApplyAsInt(a1, a2);
		} else {
			return func.failSafeDoApplyAsInt(a1, a2, failSafe);
		}
	}

	static LIntBinaryOperator failSafeIntBinaryOp(LIntBinaryOperator func, @Nonnull LIntBinaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeDoApplyAsInt(a1, a2, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default int nonNullDoApplyAsInt(int a1, int a2) {
		return doApplyAsInt(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LIntBinaryOperator.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, int a1, int a2, LIntBinaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.doApplyAsInt(a1, a2);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.doApplyAsInt(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, int a1, int a2, LIntBinaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i < max_i; i++) {
				func.doApplyAsInt(a1, a2);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.doApplyAsInt(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, int a1, int a2, LIntBinaryOperator func) {
		fromTill(0, max_i, a1, a2, func);
	}

	public default LIntUnaryOperator lShrink(LIntUnaryOperator left) {
		return a2 -> doApplyAsInt(left.doApplyAsInt(a2), a2);
	}

	public default LIntUnaryOperator lShrinkc(int a1) {
		return a2 -> doApplyAsInt(a1, a2);
	}

	public static LIntUnaryOperator lShrinked(LIntUnaryOperator left, LIntBinaryOperator func) {
		return func.lShrink(left);
	}

	public static LIntUnaryOperator lShrinkedc(int a1, LIntBinaryOperator func) {
		return func.lShrinkc(a1);
	}

	public default LIntUnaryOperator rShrink(LIntUnaryOperator right) {
		return a1 -> doApplyAsInt(a1, right.doApplyAsInt(a1));
	}

	public default LIntUnaryOperator rShrinkc(int a2) {
		return a1 -> doApplyAsInt(a1, a2);
	}

	public static LIntUnaryOperator rShrinked(LIntUnaryOperator right, LIntBinaryOperator func) {
		return func.rShrink(right);
	}

	public static LIntUnaryOperator rShrinkedc(int a2, LIntBinaryOperator func) {
		return func.rShrinkc(a2);
	}

	/**  */
	public static LIntBinaryOperator uncurryIntBinaryOp(LIntFunction<LIntUnaryOperator> func) {
		return (int a1, int a2) -> func.doApply(a1).doApplyAsInt(a2);
	}

	/** Captures arguments but delays the evaluation. */
	default LIntSupplier captureIntBinaryOp(int a1, int a2) {
		return () -> this.doApplyAsInt(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static LIntBinaryOperator constant(int r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static LIntBinaryOperator apply1stAsInt(@Nonnull LIntUnaryOperator func) {
		return (a1, a2) -> func.doApplyAsInt(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static LIntBinaryOperator apply2ndAsInt(@Nonnull LIntUnaryOperator func) {
		return (a1, a2) -> func.doApplyAsInt(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LIntBinaryOperator intBinaryOp(final @Nonnull LIntBinaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LIntBinaryOperator recursive(final @Nonnull LFunction<LIntBinaryOperator, LIntBinaryOperator> selfLambda) {
		final LIntBinaryOperatorSingle single = new LIntBinaryOperatorSingle();
		LIntBinaryOperator func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LIntBinaryOperatorSingle implements LSingle<LIntBinaryOperator>, LIntBinaryOperator {
		private LIntBinaryOperator target = null;

		@Override
		public int doApplyAsIntX(int a1, int a2) throws Throwable {
			return target.doApplyAsIntX(a1, a2);
		}

		@Override
		public LIntBinaryOperator value() {
			return target;
		}
	}

	@Nonnull
	static LIntBinaryOperator intBinaryOpThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LIntBinaryOperator intBinaryOpThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce(message);
		};
	}

	static int call(int a1, int a2, final @Nonnull LIntBinaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsInt(a1, a2);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static LIntBinaryOperator wrap(final IntBinaryOperator other) {
		return other::applyAsInt;
	}
	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceInt). */
	@Nonnull
	static LIntBinaryOperator safe() {
		return LIntBinaryOperator::produceInt;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LIntBinaryOperator> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LIntBinaryOperator safe(final @Nullable LIntBinaryOperator other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LIntBinaryOperator> safeSupplier(final @Nullable LSupplier<LIntBinaryOperator> supplier) {
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
	static LIntBinaryOperator minBy(@Nonnull Comparator<Integer> comparator) {
		Null.nonNullArg(comparator, "comparator");
		return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
	}

	/**
	 * Creates function that returns the lesser value according to the comparator.
	 * @see {@link java.util.function.BinaryOperator#maxBy}
	 */
	@Nonnull
	static LIntBinaryOperator maxBy(@Nonnull Comparator<Integer> comparator) {
		Null.nonNullArg(comparator, "comparator");
		return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
	}

	/**
	 * Returns function that returns the lower value.
	 * @see {@link java.util.function.BinaryOperator#minBy}
	 */
	@Nonnull
	static LIntBinaryOperator min() {
		return Integer::min;
	}

	/**
	 * Returns function that returns the higher value.
	 * @see {@link java.util.function.BinaryOperator#maxBy}
	 */
	@Nonnull
	static LIntBinaryOperator max() {
		return Integer::max;
	}

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LIntBinaryOperator intBinaryOpComposeInt(@Nonnull final LIntUnaryOperator before1, @Nonnull final LIntUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doApplyAsInt(before1.doApplyAsInt(v1), before2.doApplyAsInt(v2));
	}

	public static LIntBinaryOperator composedInt(@Nonnull final LIntUnaryOperator before1, @Nonnull final LIntUnaryOperator before2, LIntBinaryOperator after) {
		return after.intBinaryOpComposeInt(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LToIntBiFunction<V1, V2> intBinaryOpCompose(@Nonnull final LToIntFunction<? super V1> before1, @Nonnull final LToIntFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doApplyAsInt(before1.doApplyAsInt(v1), before2.doApplyAsInt(v2));
	}

	public static <V1, V2> LToIntBiFunction<V1, V2> composed(@Nonnull final LToIntFunction<? super V1> before1, @Nonnull final LToIntFunction<? super V2> before2, LIntBinaryOperator after) {
		return after.intBinaryOpCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiIntFunction<V> then(@Nonnull LIntFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApply(this.doApplyAsInt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LIntBinaryOperator thenToInt(@Nonnull LIntUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsInt(this.doApplyAsInt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiIntPredicate thenToBool(@Nonnull LIntPredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doTest(this.doApplyAsInt(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LIntBinaryOperator nestingIntBinaryOp() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LIntBinaryOperator shovingIntBinaryOp() {
		return this;
	}

	// </editor-fold>

	/** Does nothing (LIntBinaryOperator) Operator */
	public static int produceInt(int a1, int a2) {
		return Function4U.defaultInteger;
	}

	// MAP: FOR, [SourcePurpose{arg=int a1, type=IA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=LIntConsumer consumer, type=CONST}]
	default <C1, C2> void forEach(IndexedRead<C1, aInt> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, LIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiToIntFunction<Object> oiFunc1 = (LOiToIntFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			int a1 = oiFunc1.doApplyAsInt(source1, i);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			consumer.doAccept(this.doApplyAsInt(a1, a2));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=int a1, type=SA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=LIntConsumer consumer, type=CONST}]
	default <C1, I1, C2> void iterate(SequentialRead<C1, I1, aInt> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, LIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToIntFunction<Object> nextFunc1 = (LToIntFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size) {
			int a1 = nextFunc1.doApplyAsInt(iterator1);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			consumer.doAccept(this.doApplyAsInt(a1, a2));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=int a1, type=IA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=LIntConsumer consumer, type=CONST}]
	default <C1, C2, I2> void iterate(IndexedRead<C1, aInt> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, LIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiToIntFunction<Object> oiFunc1 = (LOiToIntFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2)) {
			int a1 = oiFunc1.doApplyAsInt(source1, i);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			consumer.doAccept(this.doApplyAsInt(a1, a2));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=int a1, type=SA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=LIntConsumer consumer, type=CONST}]
	default <C1, I1, C2, I2> void iterate(SequentialRead<C1, I1, aInt> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, LIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToIntFunction<Object> nextFunc1 = (LToIntFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2)) {
			int a1 = nextFunc1.doApplyAsInt(iterator1);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			consumer.doAccept(this.doApplyAsInt(a1, a2));
		}
	}

}
