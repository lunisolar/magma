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
 * Non-throwing functional interface (lambda) LLongBinaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 2): long a1,long a2
 *
 * Co-domain: long
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LLongBinaryOperator extends LongBinaryOperator, MetaOperator, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LLongBinaryOperator: long doApplyAsLong(long a1,long a2)";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LLongBinaryOperator interface should be discouraged.
	 */
	@Override
	@Deprecated
	default long applyAsLong(long a1, long a2) {
		return this.doApplyAsLong(a1, a2);
	}

	// long doApplyAsLong(long a1,long a2) ;
	default long doApplyAsLong(long a1, long a2) {
		// return nestingDoApplyAsLong(a1,a2);
		try {
			return this.doApplyAsLongX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doApplyAsLong(long a1,long a2)
	 */
	long doApplyAsLongX(long a1, long a2) throws Throwable;

	default long tupleApplyAsLong(LLongPair args) {
		return doApplyAsLong(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default long handlingDoApplyAsLong(long a1, long a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyAsLongX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default long tryDoApplyAsLong(long a1, long a2, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyAsLongX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default long tryDoApplyAsLong(long a1, long a2, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyAsLongX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default long tryDoApplyAsLongThen(long a1, long a2, @Nonnull LToLongFunction<Throwable> handler) {
		try {
			return this.doApplyAsLongX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApplyAsLong(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default long nestingDoApplyAsLong(long a1, long a2) {
		try {
			return this.doApplyAsLongX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default long shovingDoApplyAsLong(long a1, long a2) {
		try {
			return this.doApplyAsLongX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static long handlingDoApplyAsLong(long a1, long a2, LLongBinaryOperator func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApplyAsLong(a1, a2, handling);
	}

	static long tryDoApplyAsLong(long a1, long a2, LLongBinaryOperator func) {
		return tryDoApplyAsLong(a1, a2, func, null);
	}

	static long tryDoApplyAsLong(long a1, long a2, LLongBinaryOperator func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsLong(a1, a2, exceptionFactory, newMessage, messageParams);
	}

	static long tryDoApplyAsLong(long a1, long a2, LLongBinaryOperator func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsLong(a1, a2, exceptionFactory);
	}

	static long tryDoApplyAsLongThen(long a1, long a2, LLongBinaryOperator func, @Nonnull LToLongFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsLongThen(a1, a2, handler);
	}

	default long failSafeDoApplyAsLong(long a1, long a2, @Nonnull LLongBinaryOperator failSafe) {
		try {
			return doApplyAsLong(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApplyAsLong(a1, a2);
		}
	}

	static long failSafeDoApplyAsLong(long a1, long a2, LLongBinaryOperator func, @Nonnull LLongBinaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApplyAsLong(a1, a2);
		} else {
			return func.failSafeDoApplyAsLong(a1, a2, failSafe);
		}
	}

	static LLongBinaryOperator failSafeLongBinaryOp(LLongBinaryOperator func, @Nonnull LLongBinaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeDoApplyAsLong(a1, a2, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default long nonNullDoApplyAsLong(long a1, long a2) {
		return doApplyAsLong(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LLongBinaryOperator.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, long a1, long a2, LLongBinaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.doApplyAsLong(a1, a2);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.doApplyAsLong(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, long a1, long a2, LLongBinaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i < max_i; i++) {
				func.doApplyAsLong(a1, a2);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.doApplyAsLong(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, long a1, long a2, LLongBinaryOperator func) {
		fromTill(0, max_i, a1, a2, func);
	}

	public default LLongUnaryOperator lShrink(LLongUnaryOperator left) {
		return a2 -> doApplyAsLong(left.doApplyAsLong(a2), a2);
	}

	public default LLongUnaryOperator lShrinkc(long a1) {
		return a2 -> doApplyAsLong(a1, a2);
	}

	public static LLongUnaryOperator lShrinked(LLongUnaryOperator left, LLongBinaryOperator func) {
		return func.lShrink(left);
	}

	public static LLongUnaryOperator lShrinkedc(long a1, LLongBinaryOperator func) {
		return func.lShrinkc(a1);
	}

	public default LLongUnaryOperator rShrink(LLongUnaryOperator right) {
		return a1 -> doApplyAsLong(a1, right.doApplyAsLong(a1));
	}

	public default LLongUnaryOperator rShrinkc(long a2) {
		return a1 -> doApplyAsLong(a1, a2);
	}

	public static LLongUnaryOperator rShrinked(LLongUnaryOperator right, LLongBinaryOperator func) {
		return func.rShrink(right);
	}

	public static LLongUnaryOperator rShrinkedc(long a2, LLongBinaryOperator func) {
		return func.rShrinkc(a2);
	}

	/**  */
	public static LLongBinaryOperator uncurryLongBinaryOp(LLongFunction<LLongUnaryOperator> func) {
		return (long a1, long a2) -> func.doApply(a1).doApplyAsLong(a2);
	}

	/** Captures arguments but delays the evaluation. */
	default LLongSupplier captureLongBinaryOp(long a1, long a2) {
		return () -> this.doApplyAsLong(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static LLongBinaryOperator constant(long r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static LLongBinaryOperator apply1stAsLong(@Nonnull LLongUnaryOperator func) {
		return (a1, a2) -> func.doApplyAsLong(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static LLongBinaryOperator apply2ndAsLong(@Nonnull LLongUnaryOperator func) {
		return (a1, a2) -> func.doApplyAsLong(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LLongBinaryOperator longBinaryOp(final @Nonnull LLongBinaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LLongBinaryOperator recursive(final @Nonnull LFunction<LLongBinaryOperator, LLongBinaryOperator> selfLambda) {
		final LLongBinaryOperatorSingle single = new LLongBinaryOperatorSingle();
		LLongBinaryOperator func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LLongBinaryOperatorSingle implements LSingle<LLongBinaryOperator>, LLongBinaryOperator {
		private LLongBinaryOperator target = null;

		@Override
		public long doApplyAsLongX(long a1, long a2) throws Throwable {
			return target.doApplyAsLongX(a1, a2);
		}

		@Override
		public LLongBinaryOperator value() {
			return target;
		}
	}

	@Nonnull
	static LLongBinaryOperator longBinaryOpThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LLongBinaryOperator longBinaryOpThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce(message);
		};
	}

	static long call(long a1, long a2, final @Nonnull LLongBinaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsLong(a1, a2);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static LLongBinaryOperator wrap(final LongBinaryOperator other) {
		return other::applyAsLong;
	}
	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceLong). */
	@Nonnull
	static LLongBinaryOperator safe() {
		return LLongBinaryOperator::produceLong;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LLongBinaryOperator> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LLongBinaryOperator safe(final @Nullable LLongBinaryOperator other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LLongBinaryOperator> safeSupplier(final @Nullable LSupplier<LLongBinaryOperator> supplier) {
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
	static LLongBinaryOperator minBy(@Nonnull Comparator<Long> comparator) {
		Null.nonNullArg(comparator, "comparator");
		return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
	}

	/**
	 * Creates function that returns the lesser value according to the comparator.
	 * @see {@link java.util.function.BinaryOperator#maxBy}
	 */
	@Nonnull
	static LLongBinaryOperator maxBy(@Nonnull Comparator<Long> comparator) {
		Null.nonNullArg(comparator, "comparator");
		return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
	}

	/**
	 * Returns function that returns the lower value.
	 * @see {@link java.util.function.BinaryOperator#minBy}
	 */
	@Nonnull
	static LLongBinaryOperator min() {
		return Long::min;
	}

	/**
	 * Returns function that returns the higher value.
	 * @see {@link java.util.function.BinaryOperator#maxBy}
	 */
	@Nonnull
	static LLongBinaryOperator max() {
		return Long::max;
	}

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LLongBinaryOperator longBinaryOpComposeLong(@Nonnull final LLongUnaryOperator before1, @Nonnull final LLongUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doApplyAsLong(before1.doApplyAsLong(v1), before2.doApplyAsLong(v2));
	}

	public static LLongBinaryOperator composedLong(@Nonnull final LLongUnaryOperator before1, @Nonnull final LLongUnaryOperator before2, LLongBinaryOperator after) {
		return after.longBinaryOpComposeLong(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LToLongBiFunction<V1, V2> longBinaryOpCompose(@Nonnull final LToLongFunction<? super V1> before1, @Nonnull final LToLongFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doApplyAsLong(before1.doApplyAsLong(v1), before2.doApplyAsLong(v2));
	}

	public static <V1, V2> LToLongBiFunction<V1, V2> composed(@Nonnull final LToLongFunction<? super V1> before1, @Nonnull final LToLongFunction<? super V2> before2, LLongBinaryOperator after) {
		return after.longBinaryOpCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiLongFunction<V> then(@Nonnull LLongFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApply(this.doApplyAsLong(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LLongBinaryOperator thenToLong(@Nonnull LLongUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsLong(this.doApplyAsLong(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiLongPredicate thenToBool(@Nonnull LLongPredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doTest(this.doApplyAsLong(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LLongBinaryOperator nestingLongBinaryOp() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LLongBinaryOperator shovingLongBinaryOp() {
		return this;
	}

	// </editor-fold>

	/** Does nothing (LLongBinaryOperator) Operator */
	public static long produceLong(long a1, long a2) {
		return Function4U.defaultLong;
	}

	// MAP: FOR, [SourcePurpose{arg=long a1, type=IA}, SourcePurpose{arg=long a2, type=IA}, SourcePurpose{arg=LLongConsumer consumer, type=CONST}]
	default <C1, C2> void forEach(IndexedRead<C1, aLong> ia1, C1 source1, IndexedRead<C2, aLong> ia2, C2 source2, LLongConsumer consumer) {
		int size = ia1.size(source1);
		LOiToLongFunction<Object> oiFunc1 = (LOiToLongFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToLongFunction<Object> oiFunc2 = (LOiToLongFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			long a1 = oiFunc1.doApplyAsLong(source1, i);
			long a2 = oiFunc2.doApplyAsLong(source2, i);
			consumer.doAccept(this.doApplyAsLong(a1, a2));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=long a1, type=SA}, SourcePurpose{arg=long a2, type=IA}, SourcePurpose{arg=LLongConsumer consumer, type=CONST}]
	default <C1, I1, C2> void iterate(SequentialRead<C1, I1, aLong> sa1, C1 source1, IndexedRead<C2, aLong> ia2, C2 source2, LLongConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToLongFunction<Object> nextFunc1 = (LToLongFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiToLongFunction<Object> oiFunc2 = (LOiToLongFunction) ia2.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size) {
			long a1 = nextFunc1.doApplyAsLong(iterator1);
			long a2 = oiFunc2.doApplyAsLong(source2, i);
			consumer.doAccept(this.doApplyAsLong(a1, a2));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=long a1, type=IA}, SourcePurpose{arg=long a2, type=SA}, SourcePurpose{arg=LLongConsumer consumer, type=CONST}]
	default <C1, C2, I2> void iterate(IndexedRead<C1, aLong> ia1, C1 source1, SequentialRead<C2, I2, aLong> sa2, C2 source2, LLongConsumer consumer) {
		int size = ia1.size(source1);
		LOiToLongFunction<Object> oiFunc1 = (LOiToLongFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToLongFunction<Object> nextFunc2 = (LToLongFunction) sa2.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2)) {
			long a1 = oiFunc1.doApplyAsLong(source1, i);
			long a2 = nextFunc2.doApplyAsLong(iterator2);
			consumer.doAccept(this.doApplyAsLong(a1, a2));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=long a1, type=SA}, SourcePurpose{arg=long a2, type=SA}, SourcePurpose{arg=LLongConsumer consumer, type=CONST}]
	default <C1, I1, C2, I2> void iterate(SequentialRead<C1, I1, aLong> sa1, C1 source1, SequentialRead<C2, I2, aLong> sa2, C2 source2, LLongConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToLongFunction<Object> nextFunc1 = (LToLongFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToLongFunction<Object> nextFunc2 = (LToLongFunction) sa2.getter();
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2)) {
			long a1 = nextFunc1.doApplyAsLong(iterator1);
			long a2 = nextFunc2.doApplyAsLong(iterator2);
			consumer.doAccept(this.doApplyAsLong(a1, a2));
		}
	}

}
