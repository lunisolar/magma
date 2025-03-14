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
 * Non-throwing functional interface (lambda) LByteTernaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 3): byte a1,byte a2,byte a3
 *
 * Co-domain: byte
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LByteTernaryOperator extends MetaOperator, MetaInterface.NonThrowing, Codomain<aByte>, Domain3<aByte, aByte, aByte> { //NOSONAR

	String DESCRIPTION = "LByteTernaryOperator: byte applyAsByte(byte a1,byte a2,byte a3)";

	default byte applyAsByte(byte a1, byte a2, byte a3) {
		try {
			return this.applyAsByteX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsByte(byte a1,byte a2,byte a3)
	 */
	byte applyAsByteX(byte a1, byte a2, byte a3) throws Throwable;

	default byte tupleApplyAsByte(LByteTriple args) {
		return applyAsByte(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions according to the instructions. */
	default byte handlingApplyAsByte(byte a1, byte a2, byte a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsByteX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LByteTernaryOperator handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> handlingApplyAsByte(a1, a2, a3, handling);
	}

	default byte applyAsByte(byte a1, byte a2, byte a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyAsByteX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default byte applyAsByte(byte a1, byte a2, byte a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyAsByteX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default byte applyAsByte(byte a1, byte a2, byte a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyAsByteX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default byte applyAsByte(byte a1, byte a2, byte a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyAsByteX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LByteTernaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2, a3) -> applyAsByte(a1, a2, a3, factory, newMessage);
	}

	default LByteTernaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2, a3) -> applyAsByte(a1, a2, a3, factory, newMessage, param1);
	}

	default LByteTernaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2, a3) -> applyAsByte(a1, a2, a3, factory, newMessage, param1, param1);
	}

	default LByteTernaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2, a3) -> applyAsByte(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	default byte applyAsByte(byte a1, byte a2, byte a3, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyAsByteX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LByteTernaryOperator trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2, a3) -> applyAsByte(a1, a2, a3, factory);
	}

	default byte applyAsByteThen(byte a1, byte a2, byte a3, @Nonnull LToByteFunction<Throwable> handler) {
		try {
			return this.applyAsByteX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsByte(e);
		}
	}

	default LByteTernaryOperator tryingThen(@Nonnull LToByteFunction<Throwable> handler) {
		return (a1, a2, a3) -> applyAsByteThen(a1, a2, a3, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default byte nestingApplyAsByte(byte a1, byte a2, byte a3) {
		try {
			return this.applyAsByteX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default byte shovingApplyAsByte(byte a1, byte a2, byte a3) {
		try {
			return this.applyAsByteX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static byte shovingApplyAsByte(byte a1, byte a2, byte a3, LByteTernaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.shovingApplyAsByte(a1, a2, a3);
	}

	static byte handlingApplyAsByte(byte a1, byte a2, byte a3, LByteTernaryOperator func, HandlingInstructions<Throwable, RuntimeException> handling) { //<-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsByte(a1, a2, a3, handling);
	}

	static byte tryApplyAsByte(byte a1, byte a2, byte a3, LByteTernaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsByte(a1, a2, a3);
	}

	static byte tryApplyAsByte(byte a1, byte a2, byte a3, LByteTernaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.applyAsByte(a1, a2, a3, factory, newMessage);
	}

	static byte tryApplyAsByte(byte a1, byte a2, byte a3, LByteTernaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.applyAsByte(a1, a2, a3, factory, newMessage, param1);
	}

	static byte tryApplyAsByte(byte a1, byte a2, byte a3, LByteTernaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.applyAsByte(a1, a2, a3, factory, newMessage, param1, param2);
	}

	static byte tryApplyAsByte(byte a1, byte a2, byte a3, LByteTernaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.applyAsByte(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	static byte tryApplyAsByte(byte a1, byte a2, byte a3, LByteTernaryOperator func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.applyAsByte(a1, a2, a3, factory);
	}

	static byte tryApplyAsByteThen(byte a1, byte a2, byte a3, LByteTernaryOperator func, @Nonnull LToByteFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsByteThen(a1, a2, a3, handler);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default byte nonNullApplyAsByte(byte a1, byte a2, byte a3) {
		return applyAsByte(a1, a2, a3);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LByteTernaryOperator.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, byte a1, byte a2, byte a3, @Nonnull LByteTernaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.applyAsByte(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.applyAsByte(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, byte a1, byte a2, byte a3, @Nonnull LByteTernaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.applyAsByte(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.applyAsByte(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, byte a1, byte a2, byte a3, @Nonnull LByteTernaryOperator func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, a3, func);
	}

	default LByteBinaryOperator _with(byte a1) {
		return (a2, a3) -> applyAsByte(a1, a2, a3);
	}

	default LByteBinaryOperator with(byte a3) {
		return (a1, a2) -> applyAsByte(a1, a2, a3);
	}

	default LByteUnaryOperator _with(byte a1, byte a2) {
		return a3 -> applyAsByte(a1, a2, a3);
	}

	default LByteUnaryOperator with(byte a2, byte a3) {
		return a1 -> applyAsByte(a1, a2, a3);
	}

	/**  */
	public static LByteTernaryOperator uncurry(@Nonnull LByteFunction<LByteFunction<LByteUnaryOperator>> func) {
		Null.nonNullArg(func, "func");
		return (byte a1, byte a2, byte a3) -> func.apply(a1).apply(a2).applyAsByte(a3);
	}

	/** Change function to one that ignores output. */
	default LTriByteConsumer toConsumer() {
		return this::applyAsByte;
	}

	/** Calls domain consumer before main function. */
	default LByteTernaryOperator beforeDo(@Nonnull LTriByteConsumer before) {
		Null.nonNullArg(before, "before");
		return (byte a1, byte a2, byte a3) -> {
			before.accept(a1, a2, a3);
			return applyAsByte(a1, a2, a3);
		};
	}

	/** Calls codomain consumer after main function. */
	default LByteTernaryOperator afterDo(@Nonnull LByteConsumer after) {
		Null.nonNullArg(after, "after");
		return (byte a1, byte a2, byte a3) -> {
			final byte retval = applyAsByte(a1, a2, a3);
			after.accept(retval);
			return retval;
		};
	}

	/** Creates function that always returns the same value. */
	static LByteTernaryOperator constant(byte r) {
		return (a1, a2, a3) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LByteTernaryOperator byteTernaryOp(final @Nonnull LByteTernaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S implements LByteTernaryOperator {
		private LByteTernaryOperator target = null;
		@Override
		public byte applyAsByteX(byte a1, byte a2, byte a3) throws Throwable {
			return target.applyAsByteX(a1, a2, a3);
		}
	}

	@Nonnull
	static LByteTernaryOperator recursive(final @Nonnull LFunction<LByteTernaryOperator, LByteTernaryOperator> selfLambda) {
		final S single = new S();
		LByteTernaryOperator func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static M mementoOf(byte a1, byte a2, byte a3, LByteTernaryOperator function) {
		var initialValue = function.applyAsByte(a1, a2, a3);
		return initializedMementoOf(initialValue, function);
	}

	public static M initializedMementoOf(byte initialValue, LByteTernaryOperator function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static M deltaOf(byte a1, byte a2, byte a3, LByteTernaryOperator function, LByteBinaryOperator deltaFunction) {
		var initialValue = function.applyAsByte(a1, a2, a3);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static M deltaOf(byte a1, byte a2, byte a3, LByteTernaryOperator function) {
		var initialValue = function.applyAsByte(a1, a2, a3);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (byte) (x2 - x1));
	}

	public static M initializedDeltaOf(byte initialValue, LByteTernaryOperator function, LByteBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.applyAsByte(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.applyAsByte(x1, x2));
	}

	public static M memento(byte initialBaseValue, byte initialValue, LByteTernaryOperator baseFunction, LByteTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LByteTernaryOperator.M)
	 */
	@NotThreadSafe
	final class M implements LByteTernaryOperator {

		private final LByteTernaryOperator baseFunction;
		private byte lastBaseValue;
		private byte lastValue;
		private final LByteTernaryOperator mementoFunction;

		private M(byte lastBaseValue, byte lastValue, LByteTernaryOperator baseFunction, LByteTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public byte applyAsByteX(byte a1, byte a2, byte a3) throws Throwable {
			byte x1 = lastBaseValue;
			byte x2 = lastBaseValue = baseFunction.applyAsByteX(a1, a2, a3);

			return lastValue = mementoFunction.applyAsByteX(lastValue, x1, x2);
		}

		public byte currentApplyAsByte(byte a1, byte a2, byte a3) {
			byte x1 = lastBaseValue;
			byte x2 = baseFunction.applyAsByte(a1, a2, a3);

			return mementoFunction.applyAsByte(lastValue, x1, x2);
		}

		public byte lastValue() {
			return lastValue;
		}

		public byte lastBaseValue() {
			return lastBaseValue;
		}

		public byte currentBaseValue(byte a1, byte a2, byte a3) {
			return baseFunction.applyAsByte(a1, a2, a3);
		}
	}

	// </editor-fold>

	@Nonnull
	static LByteTernaryOperator byteTernaryOpThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LByteTernaryOperator byteTernaryOpThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce(message);
		};
	}

	static byte call(byte a1, byte a2, byte a3, final @Nonnull LByteTernaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsByte(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LByteTernaryOperator compose(@Nonnull final LByteUnaryOperator before1, @Nonnull final LByteUnaryOperator before2, @Nonnull final LByteUnaryOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.applyAsByte(before1.applyAsByte(v1), before2.applyAsByte(v2), before3.applyAsByte(v3));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LTriByteFunction<V> then(@Nonnull LByteFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.apply(this.applyAsByte(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteTernaryOperator thenToByte(@Nonnull LByteUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.applyAsByte(this.applyAsByte(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LTriBytePredicate thenToBool(@Nonnull LBytePredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.test(this.applyAsByte(a1, a2, a3));
	}

	// </editor-fold>

	default LByteTernaryOperator shoving() {

		return new LByteTernaryOperator() {

			public byte applyAsByte(byte a1, byte a2, byte a3) {
				try {
					return this.applyAsByteX(a1, a2, a3);
				} catch (Throwable e) { // NOSONAR
					Handling.handleErrors(e);
					throw Handling.throwIt(e);
				}
			}

			public byte applyAsByteX(byte a1, byte a2, byte a3) throws Throwable {
				return LByteTernaryOperator.this.applyAsByteX(a1, a2, a3);
			}

		};
	}

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LByteTernaryOperator) Operator */
	public static byte doNothing(byte a1, byte a2, byte a3) {
		return Function4U.defaultByte;
	}

}
