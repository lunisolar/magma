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
 * Non-throwing functional interface (lambda) LByteBinaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 2): byte a1,byte a2
 *
 * Co-domain: byte
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LByteBinaryOperator extends MetaOperator, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LByteBinaryOperator: byte doApplyAsByte(byte a1,byte a2)";

	// byte doApplyAsByte(byte a1,byte a2) ;
	default byte doApplyAsByte(byte a1, byte a2) {
		// return nestingDoApplyAsByte(a1,a2);
		try {
			return this.doApplyAsByteX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doApplyAsByte(byte a1,byte a2)
	 */
	byte doApplyAsByteX(byte a1, byte a2) throws Throwable;

	default byte tupleApplyAsByte(LBytePair args) {
		return doApplyAsByte(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default byte handlingDoApplyAsByte(byte a1, byte a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyAsByteX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default byte tryDoApplyAsByte(byte a1, byte a2, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyAsByteX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default byte tryDoApplyAsByte(byte a1, byte a2, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyAsByteX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default byte tryDoApplyAsByteThen(byte a1, byte a2, @Nonnull LToByteFunction<Throwable> handler) {
		try {
			return this.doApplyAsByteX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApplyAsByte(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default byte nestingDoApplyAsByte(byte a1, byte a2) {
		try {
			return this.doApplyAsByteX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default byte shovingDoApplyAsByte(byte a1, byte a2) {
		try {
			return this.doApplyAsByteX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static byte handlingDoApplyAsByte(byte a1, byte a2, LByteBinaryOperator func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApplyAsByte(a1, a2, handling);
	}

	static byte tryDoApplyAsByte(byte a1, byte a2, LByteBinaryOperator func) {
		return tryDoApplyAsByte(a1, a2, func, null);
	}

	static byte tryDoApplyAsByte(byte a1, byte a2, LByteBinaryOperator func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsByte(a1, a2, exceptionFactory, newMessage, messageParams);
	}

	static byte tryDoApplyAsByte(byte a1, byte a2, LByteBinaryOperator func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsByte(a1, a2, exceptionFactory);
	}

	static byte tryDoApplyAsByteThen(byte a1, byte a2, LByteBinaryOperator func, @Nonnull LToByteFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsByteThen(a1, a2, handler);
	}

	default byte failSafeDoApplyAsByte(byte a1, byte a2, @Nonnull LByteBinaryOperator failSafe) {
		try {
			return doApplyAsByte(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApplyAsByte(a1, a2);
		}
	}

	static byte failSafeDoApplyAsByte(byte a1, byte a2, LByteBinaryOperator func, @Nonnull LByteBinaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApplyAsByte(a1, a2);
		} else {
			return func.failSafeDoApplyAsByte(a1, a2, failSafe);
		}
	}

	static LByteBinaryOperator failSafeByteBinaryOp(LByteBinaryOperator func, @Nonnull LByteBinaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeDoApplyAsByte(a1, a2, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default byte nonNullDoApplyAsByte(byte a1, byte a2) {
		return doApplyAsByte(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LByteBinaryOperator.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, byte a1, byte a2, LByteBinaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.doApplyAsByte(a1, a2);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.doApplyAsByte(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, byte a1, byte a2, LByteBinaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i < max_i; i++) {
				func.doApplyAsByte(a1, a2);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.doApplyAsByte(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, byte a1, byte a2, LByteBinaryOperator func) {
		fromTill(0, max_i, a1, a2, func);
	}

	public default LByteUnaryOperator lShrink(LByteUnaryOperator left) {
		return a2 -> doApplyAsByte(left.doApplyAsByte(a2), a2);
	}

	public default LByteUnaryOperator lShrinkc(byte a1) {
		return a2 -> doApplyAsByte(a1, a2);
	}

	public static LByteUnaryOperator lShrinked(LByteUnaryOperator left, LByteBinaryOperator func) {
		return func.lShrink(left);
	}

	public static LByteUnaryOperator lShrinkedc(byte a1, LByteBinaryOperator func) {
		return func.lShrinkc(a1);
	}

	public default LByteUnaryOperator rShrink(LByteUnaryOperator right) {
		return a1 -> doApplyAsByte(a1, right.doApplyAsByte(a1));
	}

	public default LByteUnaryOperator rShrinkc(byte a2) {
		return a1 -> doApplyAsByte(a1, a2);
	}

	public static LByteUnaryOperator rShrinked(LByteUnaryOperator right, LByteBinaryOperator func) {
		return func.rShrink(right);
	}

	public static LByteUnaryOperator rShrinkedc(byte a2, LByteBinaryOperator func) {
		return func.rShrinkc(a2);
	}

	/**  */
	public static LByteBinaryOperator uncurryByteBinaryOp(LByteFunction<LByteUnaryOperator> func) {
		return (byte a1, byte a2) -> func.doApply(a1).doApplyAsByte(a2);
	}

	/** Captures arguments but delays the evaluation. */
	default LByteSupplier captureByteBinaryOp(byte a1, byte a2) {
		return () -> this.doApplyAsByte(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static LByteBinaryOperator constant(byte r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static LByteBinaryOperator apply1stAsByte(@Nonnull LByteUnaryOperator func) {
		return (a1, a2) -> func.doApplyAsByte(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static LByteBinaryOperator apply2ndAsByte(@Nonnull LByteUnaryOperator func) {
		return (a1, a2) -> func.doApplyAsByte(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LByteBinaryOperator byteBinaryOp(final @Nonnull LByteBinaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LByteBinaryOperator recursive(final @Nonnull LFunction<LByteBinaryOperator, LByteBinaryOperator> selfLambda) {
		final LByteBinaryOperatorSingle single = new LByteBinaryOperatorSingle();
		LByteBinaryOperator func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LByteBinaryOperatorSingle implements LSingle<LByteBinaryOperator>, LByteBinaryOperator {
		private LByteBinaryOperator target = null;

		@Override
		public byte doApplyAsByteX(byte a1, byte a2) throws Throwable {
			return target.doApplyAsByteX(a1, a2);
		}

		@Override
		public LByteBinaryOperator value() {
			return target;
		}
	}

	@Nonnull
	static LByteBinaryOperator byteBinaryOpThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static LByteBinaryOperator byteBinaryOpThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce(message);
		};
	}

	static byte call(byte a1, byte a2, final @Nonnull LByteBinaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsByte(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceByte). */
	@Nonnull
	static LByteBinaryOperator safe() {
		return LByteBinaryOperator::produceByte;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LByteBinaryOperator> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LByteBinaryOperator safe(final @Nullable LByteBinaryOperator other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LByteBinaryOperator> safeSupplier(final @Nullable LSupplier<LByteBinaryOperator> supplier) {
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
	static LByteBinaryOperator minBy(@Nonnull Comparator<Byte> comparator) {
		Null.nonNullArg(comparator, "comparator");
		return (a, b) -> comparator.compare(a, b) <= 0 ? a : b;
	}

	/**
	 * Creates function that returns the lesser value according to the comparator.
	 * @see {@link java.util.function.BinaryOperator#maxBy}
	 */
	@Nonnull
	static LByteBinaryOperator maxBy(@Nonnull Comparator<Byte> comparator) {
		Null.nonNullArg(comparator, "comparator");
		return (a, b) -> comparator.compare(a, b) >= 0 ? a : b;
	}

	/**
	 * Returns function that returns the lower value.
	 * @see {@link java.util.function.BinaryOperator#minBy}
	 */
	@Nonnull
	static LByteBinaryOperator min() {
		return (a, b) -> (a <= b) ? a : b;
	}

	/**
	 * Returns function that returns the higher value.
	 * @see {@link java.util.function.BinaryOperator#maxBy}
	 */
	@Nonnull
	static LByteBinaryOperator max() {
		return (a, b) -> (a >= b) ? a : b;
	}

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LByteBinaryOperator byteBinaryOpComposeByte(@Nonnull final LByteUnaryOperator before1, @Nonnull final LByteUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doApplyAsByte(before1.doApplyAsByte(v1), before2.doApplyAsByte(v2));
	}

	public static LByteBinaryOperator composedByte(@Nonnull final LByteUnaryOperator before1, @Nonnull final LByteUnaryOperator before2, LByteBinaryOperator after) {
		return after.byteBinaryOpComposeByte(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LToByteBiFunction<V1, V2> byteBinaryOpCompose(@Nonnull final LToByteFunction<? super V1> before1, @Nonnull final LToByteFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doApplyAsByte(before1.doApplyAsByte(v1), before2.doApplyAsByte(v2));
	}

	public static <V1, V2> LToByteBiFunction<V1, V2> composed(@Nonnull final LToByteFunction<? super V1> before1, @Nonnull final LToByteFunction<? super V2> before2, LByteBinaryOperator after) {
		return after.byteBinaryOpCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiByteFunction<V> then(@Nonnull LByteFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApply(this.doApplyAsByte(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteBinaryOperator thenToByte(@Nonnull LByteUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsByte(this.doApplyAsByte(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiBytePredicate thenToBool(@Nonnull LBytePredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doTest(this.doApplyAsByte(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LByteBinaryOperator nestingByteBinaryOp() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LByteBinaryOperator shovingByteBinaryOp() {
		return this;
	}

	// </editor-fold>

	/** Does nothing (LByteBinaryOperator) Operator */
	public static byte produceByte(byte a1, byte a2) {
		return Function4U.defaultByte;
	}

	// MAP: FOR, [SourcePurpose{arg=byte a1, type=IA}, SourcePurpose{arg=byte a2, type=IA}, SourcePurpose{arg=LByteConsumer consumer, type=CONST}]
	default <C1, C2> void forEach(IndexedRead<C1, aByte> ia1, C1 source1, IndexedRead<C2, aByte> ia2, C2 source2, LByteConsumer consumer) {
		int size = ia1.size(source1);
		LOiToByteFunction<Object> oiFunc1 = (LOiToByteFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToByteFunction<Object> oiFunc2 = (LOiToByteFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			byte a1 = oiFunc1.doApplyAsByte(source1, i);
			byte a2 = oiFunc2.doApplyAsByte(source2, i);
			consumer.doAccept(this.doApplyAsByte(a1, a2));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=byte a1, type=SA}, SourcePurpose{arg=byte a2, type=IA}, SourcePurpose{arg=LByteConsumer consumer, type=CONST}]
	default <C1, I1, C2> void iterate(SequentialRead<C1, I1, aByte> sa1, C1 source1, IndexedRead<C2, aByte> ia2, C2 source2, LByteConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToByteFunction<Object> nextFunc1 = (LToByteFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiToByteFunction<Object> oiFunc2 = (LOiToByteFunction) ia2.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size) {
			byte a1 = nextFunc1.doApplyAsByte(iterator1);
			byte a2 = oiFunc2.doApplyAsByte(source2, i);
			consumer.doAccept(this.doApplyAsByte(a1, a2));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=byte a1, type=IA}, SourcePurpose{arg=byte a2, type=SA}, SourcePurpose{arg=LByteConsumer consumer, type=CONST}]
	default <C1, C2, I2> void iterate(IndexedRead<C1, aByte> ia1, C1 source1, SequentialRead<C2, I2, aByte> sa2, C2 source2, LByteConsumer consumer) {
		int size = ia1.size(source1);
		LOiToByteFunction<Object> oiFunc1 = (LOiToByteFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToByteFunction<Object> nextFunc2 = (LToByteFunction) sa2.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2)) {
			byte a1 = oiFunc1.doApplyAsByte(source1, i);
			byte a2 = nextFunc2.doApplyAsByte(iterator2);
			consumer.doAccept(this.doApplyAsByte(a1, a2));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=byte a1, type=SA}, SourcePurpose{arg=byte a2, type=SA}, SourcePurpose{arg=LByteConsumer consumer, type=CONST}]
	default <C1, I1, C2, I2> void iterate(SequentialRead<C1, I1, aByte> sa1, C1 source1, SequentialRead<C2, I2, aByte> sa2, C2 source2, LByteConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToByteFunction<Object> nextFunc1 = (LToByteFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToByteFunction<Object> nextFunc2 = (LToByteFunction) sa2.getter();
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2)) {
			byte a1 = nextFunc1.doApplyAsByte(iterator1);
			byte a2 = nextFunc2.doApplyAsByte(iterator2);
			consumer.doAccept(this.doApplyAsByte(a1, a2));
		}
	}

}
