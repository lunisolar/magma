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
public interface LByteBinaryOperator extends MetaOperator, MetaInterface.NonThrowing, Codomain<aByte>, Domain2<aByte, aByte> { // NOSONAR

	String DESCRIPTION = "LByteBinaryOperator: byte applyAsByte(byte a1,byte a2)";

	// byte applyAsByte(byte a1,byte a2) ;
	default byte applyAsByte(byte a1, byte a2) {
		// return nestingApplyAsByte(a1,a2);
		try {
			return this.applyAsByteX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsByte(byte a1,byte a2)
	 */
	byte applyAsByteX(byte a1, byte a2) throws Throwable;

	default byte tupleApplyAsByte(LBytePair args) {
		return applyAsByte(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default byte handlingApplyAsByte(byte a1, byte a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsByteX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LByteBinaryOperator handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2) -> handlingApplyAsByte(a1, a2, handling);
	}

	default byte applyAsByte(byte a1, byte a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyAsByteX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default byte applyAsByte(byte a1, byte a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyAsByteX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default byte applyAsByte(byte a1, byte a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyAsByteX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default byte applyAsByte(byte a1, byte a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyAsByteX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LByteBinaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2) -> applyAsByte(a1, a2, factory, newMessage);
	}

	default LByteBinaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2) -> applyAsByte(a1, a2, factory, newMessage, param1);
	}

	default LByteBinaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2) -> applyAsByte(a1, a2, factory, newMessage, param1, param1);
	}

	default LByteBinaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2) -> applyAsByte(a1, a2, factory, newMessage, param1, param2, param3);
	}

	default byte applyAsByte(byte a1, byte a2, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyAsByteX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LByteBinaryOperator trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2) -> applyAsByte(a1, a2, factory);
	}

	default byte applyAsByteThen(byte a1, byte a2, @Nonnull LToByteFunction<Throwable> handler) {
		try {
			return this.applyAsByteX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsByte(e);
		}
	}

	default LByteBinaryOperator tryingThen(@Nonnull LToByteFunction<Throwable> handler) {
		return (a1, a2) -> applyAsByteThen(a1, a2, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default byte nestingApplyAsByte(byte a1, byte a2) {
		try {
			return this.applyAsByteX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default byte shovingApplyAsByte(byte a1, byte a2) {
		try {
			return this.applyAsByteX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static byte shovingApplyAsByte(byte a1, byte a2, LByteBinaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.shovingApplyAsByte(a1, a2);
	}

	static byte handlingApplyAsByte(byte a1, byte a2, LByteBinaryOperator func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsByte(a1, a2, handling);
	}

	static byte tryApplyAsByte(byte a1, byte a2, LByteBinaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsByte(a1, a2);
	}

	static byte tryApplyAsByte(byte a1, byte a2, LByteBinaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.applyAsByte(a1, a2, factory, newMessage);
	}

	static byte tryApplyAsByte(byte a1, byte a2, LByteBinaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.applyAsByte(a1, a2, factory, newMessage, param1);
	}

	static byte tryApplyAsByte(byte a1, byte a2, LByteBinaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.applyAsByte(a1, a2, factory, newMessage, param1, param2);
	}

	static byte tryApplyAsByte(byte a1, byte a2, LByteBinaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.applyAsByte(a1, a2, factory, newMessage, param1, param2, param3);
	}

	static byte tryApplyAsByte(byte a1, byte a2, LByteBinaryOperator func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.applyAsByte(a1, a2, factory);
	}

	static byte tryApplyAsByteThen(byte a1, byte a2, LByteBinaryOperator func, @Nonnull LToByteFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsByteThen(a1, a2, handler);
	}

	default byte failSafeApplyAsByte(byte a1, byte a2, @Nonnull LByteBinaryOperator failSafe) {
		try {
			return applyAsByte(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.applyAsByte(a1, a2);
		}
	}

	static byte failSafeApplyAsByte(byte a1, byte a2, LByteBinaryOperator func, @Nonnull LByteBinaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.applyAsByte(a1, a2);
		} else {
			return func.failSafeApplyAsByte(a1, a2, failSafe);
		}
	}

	static LByteBinaryOperator failSafe(LByteBinaryOperator func, @Nonnull LByteBinaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeApplyAsByte(a1, a2, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default byte nonNullApplyAsByte(byte a1, byte a2) {
		return applyAsByte(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LByteBinaryOperator.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, byte a1, byte a2, @Nonnull LByteBinaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.applyAsByte(a1, a2);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.applyAsByte(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, byte a1, byte a2, @Nonnull LByteBinaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.applyAsByte(a1, a2);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.applyAsByte(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, byte a1, byte a2, @Nonnull LByteBinaryOperator func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, func);
	}

	/**  */
	public static LByteBinaryOperator uncurry(@Nonnull LByteFunction<LByteUnaryOperator> func) {
		Null.nonNullArg(func, "func");
		return (byte a1, byte a2) -> func.apply(a1).applyAsByte(a2);
	}

	/** Change function to consumer that ignores output. */
	default LBiByteConsumer toConsumer() {
		return this::applyAsByte;
	}

	/** Calls domain consumer before main function. */
	default LByteBinaryOperator beforeDo(@Nonnull LBiByteConsumer before) {
		Null.nonNullArg(before, "before");
		return (byte a1, byte a2) -> {
			before.accept(a1, a2);
			return applyAsByte(a1, a2);
		};
	}

	/** Calls codomain consumer after main function. */
	default LByteBinaryOperator afterDo(@Nonnull LByteConsumer after) {
		Null.nonNullArg(after, "after");
		return (byte a1, byte a2) -> {
			final byte retval = applyAsByte(a1, a2);
			after.accept(retval);
			return retval;
		};
	}

	/** Creates function that always returns the same value. */
	static LByteBinaryOperator constant(byte r) {
		return (a1, a2) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LByteBinaryOperator byteBinaryOp(final @Nonnull LByteBinaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S implements LByteBinaryOperator {
		private LByteBinaryOperator target = null;
		@Override
		public byte applyAsByteX(byte a1, byte a2) throws Throwable {
			return target.applyAsByteX(a1, a2);
		}
	}

	@Nonnull
	static LByteBinaryOperator recursive(final @Nonnull LFunction<LByteBinaryOperator, LByteBinaryOperator> selfLambda) {
		final S single = new S();
		LByteBinaryOperator func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static M mementoOf(byte a1, byte a2, LByteBinaryOperator function) {
		var initialValue = function.applyAsByte(a1, a2);
		return initializedMementoOf(initialValue, function);
	}

	public static M initializedMementoOf(byte initialValue, LByteBinaryOperator function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static M deltaOf(byte a1, byte a2, LByteBinaryOperator function, LByteBinaryOperator deltaFunction) {
		var initialValue = function.applyAsByte(a1, a2);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static M deltaOf(byte a1, byte a2, LByteBinaryOperator function) {
		var initialValue = function.applyAsByte(a1, a2);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (byte) (x2 - x1));
	}

	public static M initializedDeltaOf(byte initialValue, LByteBinaryOperator function, LByteBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.applyAsByte(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.applyAsByte(x1, x2));
	}

	public static M memento(byte initialBaseValue, byte initialValue, LByteBinaryOperator baseFunction, LByteTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LByteBinaryOperator.M)
	 */
	@NotThreadSafe
	final class M implements LByteBinaryOperator {

		private final LByteBinaryOperator baseFunction;
		private byte lastBaseValue;
		private byte lastValue;
		private final LByteTernaryOperator mementoFunction;

		private M(byte lastBaseValue, byte lastValue, LByteBinaryOperator baseFunction, LByteTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public byte applyAsByteX(byte a1, byte a2) throws Throwable {
			byte x1 = lastBaseValue;
			byte x2 = lastBaseValue = baseFunction.applyAsByteX(a1, a2);

			return lastValue = mementoFunction.applyAsByteX(lastValue, x1, x2);
		}

		public byte currentApplyAsByte(byte a1, byte a2) {
			byte x1 = lastBaseValue;
			byte x2 = baseFunction.applyAsByte(a1, a2);

			return mementoFunction.applyAsByte(lastValue, x1, x2);
		}

		public byte lastValue() {
			return lastValue;
		};

		public byte lastBaseValue() {
			return lastBaseValue;
		};
	}

	// </editor-fold>

	@Nonnull
	static LByteBinaryOperator byteBinaryOpThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LByteBinaryOperator byteBinaryOpThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce(message);
		};
	}

	static byte call(byte a1, byte a2, final @Nonnull LByteBinaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsByte(a1, a2);
	}

	// <editor-fold desc="wrap">

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
	default LByteBinaryOperator compose(@Nonnull final LByteUnaryOperator before1, @Nonnull final LByteUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.applyAsByte(before1.applyAsByte(v1), before2.applyAsByte(v2));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LToByteBiFunction<V1, V2> unboxingCompose(@Nonnull final LToByteFunction<? super V1> before1, @Nonnull final LToByteFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.applyAsByte(before1.applyAsByte(v1), before2.applyAsByte(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiByteFunction<V> then(@Nonnull LByteFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.apply(this.applyAsByte(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteBinaryOperator thenToByte(@Nonnull LByteUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsByte(this.applyAsByte(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiBytePredicate thenToBool(@Nonnull LBytePredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.test(this.applyAsByte(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LByteBinaryOperator) Operator */
	public static byte doNothing(byte a1, byte a2) {
		return Function4U.defaultByte;
	}

}
