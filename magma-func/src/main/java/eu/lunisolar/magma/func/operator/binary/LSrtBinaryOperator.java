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
 * Non-throwing functional interface (lambda) LSrtBinaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 2): short a1,short a2
 *
 * Co-domain: short
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LSrtBinaryOperator extends MetaOperator, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LSrtBinaryOperator: short doApplyAsSrt(short a1,short a2)";

	// short doApplyAsSrt(short a1,short a2) ;
	default short doApplyAsSrt(short a1, short a2) {
		// return nestingDoApplyAsSrt(a1,a2);
		try {
			return this.doApplyAsSrtX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doApplyAsSrt(short a1,short a2)
	 */
	short doApplyAsSrtX(short a1, short a2) throws Throwable;

	default short tupleApplyAsSrt(LSrtPair args) {
		return doApplyAsSrt(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default short handlingDoApplyAsSrt(short a1, short a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyAsSrtX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default short tryDoApplyAsSrt(short a1, short a2, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyAsSrtX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default short tryDoApplyAsSrt(short a1, short a2, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyAsSrtX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default short tryDoApplyAsSrtThen(short a1, short a2, @Nonnull LToSrtFunction<Throwable> handler) {
		try {
			return this.doApplyAsSrtX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApplyAsSrt(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default short nestingDoApplyAsSrt(short a1, short a2) {
		try {
			return this.doApplyAsSrtX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default short shovingDoApplyAsSrt(short a1, short a2) {
		try {
			return this.doApplyAsSrtX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static short handlingDoApplyAsSrt(short a1, short a2, LSrtBinaryOperator func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApplyAsSrt(a1, a2, handling);
	}

	static short tryDoApplyAsSrt(short a1, short a2, LSrtBinaryOperator func) {
		return tryDoApplyAsSrt(a1, a2, func, null);
	}

	static short tryDoApplyAsSrt(short a1, short a2, LSrtBinaryOperator func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsSrt(a1, a2, exceptionFactory, newMessage, messageParams);
	}

	static short tryDoApplyAsSrt(short a1, short a2, LSrtBinaryOperator func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsSrt(a1, a2, exceptionFactory);
	}

	static short tryDoApplyAsSrtThen(short a1, short a2, LSrtBinaryOperator func, @Nonnull LToSrtFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsSrtThen(a1, a2, handler);
	}

	default short failSafeDoApplyAsSrt(short a1, short a2, @Nonnull LSrtBinaryOperator failSafe) {
		try {
			return doApplyAsSrt(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApplyAsSrt(a1, a2);
		}
	}

	static short failSafeDoApplyAsSrt(short a1, short a2, LSrtBinaryOperator func, @Nonnull LSrtBinaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApplyAsSrt(a1, a2);
		} else {
			return func.failSafeDoApplyAsSrt(a1, a2, failSafe);
		}
	}

	static LSrtBinaryOperator failSafeSrtBinaryOp(LSrtBinaryOperator func, @Nonnull LSrtBinaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeDoApplyAsSrt(a1, a2, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default short nonNullDoApplyAsSrt(short a1, short a2) {
		return doApplyAsSrt(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LSrtBinaryOperator.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, short a1, short a2, LSrtBinaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.doApplyAsSrt(a1, a2);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.doApplyAsSrt(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, short a1, short a2, LSrtBinaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i < max_i; i++) {
				func.doApplyAsSrt(a1, a2);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.doApplyAsSrt(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, short a1, short a2, LSrtBinaryOperator func) {
		fromTill(0, max_i, a1, a2, func);
	}

	public default LSrtUnaryOperator lShrink(LSrtUnaryOperator left) {
		return a2 -> doApplyAsSrt(left.doApplyAsSrt(a2), a2);
	}

	public default LSrtUnaryOperator lShrinkc(short a1) {
		return a2 -> doApplyAsSrt(a1, a2);
	}

	public static LSrtUnaryOperator lShrinked(LSrtUnaryOperator left, LSrtBinaryOperator func) {
		return func.lShrink(left);
	}

	public static LSrtUnaryOperator lShrinkedc(short a1, LSrtBinaryOperator func) {
		return func.lShrinkc(a1);
	}

	public default LSrtUnaryOperator rShrink(LSrtUnaryOperator right) {
		return a1 -> doApplyAsSrt(a1, right.doApplyAsSrt(a1));
	}

	public default LSrtUnaryOperator rShrinkc(short a2) {
		return a1 -> doApplyAsSrt(a1, a2);
	}

	public static LSrtUnaryOperator rShrinked(LSrtUnaryOperator right, LSrtBinaryOperator func) {
		return func.rShrink(right);
	}

	public static LSrtUnaryOperator rShrinkedc(short a2, LSrtBinaryOperator func) {
		return func.rShrinkc(a2);
	}

	/**  */
	public static LSrtBinaryOperator uncurrySrtBinaryOp(LSrtFunction<LSrtUnaryOperator> func) {
		return (short a1, short a2) -> func.doApply(a1).doApplyAsSrt(a2);
	}

	/** Captures arguments but delays the evaluation. */
	default LSrtSupplier captureSrtBinaryOp(short a1, short a2) {
		return () -> this.doApplyAsSrt(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static LSrtBinaryOperator constant(short r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static LSrtBinaryOperator apply1stAsSrt(@Nonnull LSrtUnaryOperator func) {
		return (a1, a2) -> func.doApplyAsSrt(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static LSrtBinaryOperator apply2ndAsSrt(@Nonnull LSrtUnaryOperator func) {
		return (a1, a2) -> func.doApplyAsSrt(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LSrtBinaryOperator srtBinaryOp(final @Nonnull LSrtBinaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LSrtBinaryOperator recursive(final @Nonnull LFunction<LSrtBinaryOperator, LSrtBinaryOperator> selfLambda) {
		final LSrtBinaryOperatorSingle single = new LSrtBinaryOperatorSingle();
		LSrtBinaryOperator func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LSrtBinaryOperatorSingle implements LSingle<LSrtBinaryOperator>, LSrtBinaryOperator {
		private LSrtBinaryOperator target = null;

		@Override
		public short doApplyAsSrtX(short a1, short a2) throws Throwable {
			return target.doApplyAsSrtX(a1, a2);
		}

		@Override
		public LSrtBinaryOperator value() {
			return target;
		}
	}

	@Nonnull
	static LSrtBinaryOperator srtBinaryOpThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LSrtBinaryOperator srtBinaryOpThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce(message);
		};
	}

	static short call(short a1, short a2, final @Nonnull LSrtBinaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsSrt(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceShort). */
	@Nonnull
	static LSrtBinaryOperator safe() {
		return LSrtBinaryOperator::produceShort;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LSrtBinaryOperator> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LSrtBinaryOperator safe(final @Nullable LSrtBinaryOperator other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LSrtBinaryOperator> safeSupplier(final @Nullable LSupplier<LSrtBinaryOperator> supplier) {
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
	static LSrtBinaryOperator minBy(@Nonnull Comparator<Short> comparator) {
		Null.nonNullArg(comparator, "comparator");
		return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
	}

	/**
	 * Creates function that returns the lesser value according to the comparator.
	 * @see {@link java.util.function.BinaryOperator#maxBy}
	 */
	@Nonnull
	static LSrtBinaryOperator maxBy(@Nonnull Comparator<Short> comparator) {
		Null.nonNullArg(comparator, "comparator");
		return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
	}

	/**
	 * Returns function that returns the lower value.
	 * @see {@link java.util.function.BinaryOperator#minBy}
	 */
	@Nonnull
	static LSrtBinaryOperator min() {
		return (a, b) -> (a <= b) ? a : b;
	}

	/**
	 * Returns function that returns the higher value.
	 * @see {@link java.util.function.BinaryOperator#maxBy}
	 */
	@Nonnull
	static LSrtBinaryOperator max() {
		return (a, b) -> (a >= b) ? a : b;
	}

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LSrtBinaryOperator srtBinaryOpComposeSrt(@Nonnull final LSrtUnaryOperator before1, @Nonnull final LSrtUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doApplyAsSrt(before1.doApplyAsSrt(v1), before2.doApplyAsSrt(v2));
	}

	public static LSrtBinaryOperator composedSrt(@Nonnull final LSrtUnaryOperator before1, @Nonnull final LSrtUnaryOperator before2, LSrtBinaryOperator after) {
		return after.srtBinaryOpComposeSrt(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LToSrtBiFunction<V1, V2> srtBinaryOpCompose(@Nonnull final LToSrtFunction<? super V1> before1, @Nonnull final LToSrtFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doApplyAsSrt(before1.doApplyAsSrt(v1), before2.doApplyAsSrt(v2));
	}

	public static <V1, V2> LToSrtBiFunction<V1, V2> composed(@Nonnull final LToSrtFunction<? super V1> before1, @Nonnull final LToSrtFunction<? super V2> before2, LSrtBinaryOperator after) {
		return after.srtBinaryOpCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiSrtFunction<V> then(@Nonnull LSrtFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApply(this.doApplyAsSrt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtBinaryOperator thenToSrt(@Nonnull LSrtUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsSrt(this.doApplyAsSrt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiSrtPredicate thenToBool(@Nonnull LSrtPredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doTest(this.doApplyAsSrt(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LSrtBinaryOperator nestingSrtBinaryOp() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LSrtBinaryOperator shovingSrtBinaryOp() {
		return this;
	}

	// </editor-fold>

	/** Does nothing (LSrtBinaryOperator) Operator */
	public static short produceShort(short a1, short a2) {
		return Function4U.defaultShort;
	}

	// MAP: FOR, [SourcePurpose{arg=short a1, type=IA}, SourcePurpose{arg=short a2, type=IA}, SourcePurpose{arg=LSrtConsumer consumer, type=CONST}]
	default <C1, C2> void forEach(IndexedRead<C1, aShort> ia1, C1 source1, IndexedRead<C2, aShort> ia2, C2 source2, LSrtConsumer consumer) {
		int size = ia1.size(source1);
		LOiToSrtFunction<Object> oiFunc1 = (LOiToSrtFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToSrtFunction<Object> oiFunc2 = (LOiToSrtFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			short a1 = oiFunc1.doApplyAsSrt(source1, i);
			short a2 = oiFunc2.doApplyAsSrt(source2, i);
			consumer.doAccept(this.doApplyAsSrt(a1, a2));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=short a1, type=SA}, SourcePurpose{arg=short a2, type=IA}, SourcePurpose{arg=LSrtConsumer consumer, type=CONST}]
	default <C1, I1, C2> void iterate(SequentialRead<C1, I1, aShort> sa1, C1 source1, IndexedRead<C2, aShort> ia2, C2 source2, LSrtConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToSrtFunction<Object> nextFunc1 = (LToSrtFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiToSrtFunction<Object> oiFunc2 = (LOiToSrtFunction) ia2.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size) {
			short a1 = nextFunc1.doApplyAsSrt(iterator1);
			short a2 = oiFunc2.doApplyAsSrt(source2, i);
			consumer.doAccept(this.doApplyAsSrt(a1, a2));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=short a1, type=IA}, SourcePurpose{arg=short a2, type=SA}, SourcePurpose{arg=LSrtConsumer consumer, type=CONST}]
	default <C1, C2, I2> void iterate(IndexedRead<C1, aShort> ia1, C1 source1, SequentialRead<C2, I2, aShort> sa2, C2 source2, LSrtConsumer consumer) {
		int size = ia1.size(source1);
		LOiToSrtFunction<Object> oiFunc1 = (LOiToSrtFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToSrtFunction<Object> nextFunc2 = (LToSrtFunction) sa2.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2)) {
			short a1 = oiFunc1.doApplyAsSrt(source1, i);
			short a2 = nextFunc2.doApplyAsSrt(iterator2);
			consumer.doAccept(this.doApplyAsSrt(a1, a2));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=short a1, type=SA}, SourcePurpose{arg=short a2, type=SA}, SourcePurpose{arg=LSrtConsumer consumer, type=CONST}]
	default <C1, I1, C2, I2> void iterate(SequentialRead<C1, I1, aShort> sa1, C1 source1, SequentialRead<C2, I2, aShort> sa2, C2 source2, LSrtConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToSrtFunction<Object> nextFunc1 = (LToSrtFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToSrtFunction<Object> nextFunc2 = (LToSrtFunction) sa2.getter();
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2)) {
			short a1 = nextFunc1.doApplyAsSrt(iterator1);
			short a2 = nextFunc2.doApplyAsSrt(iterator2);
			consumer.doAccept(this.doApplyAsSrt(a1, a2));
		}
	}

}
