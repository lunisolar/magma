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
public interface LSrtBinaryOperator extends MetaOperator, MetaInterface.NonThrowing, Codomain<aShort>, Domain2<aShort, aShort> { // NOSONAR

	String DESCRIPTION = "LSrtBinaryOperator: short applyAsSrt(short a1,short a2)";

	// short applyAsSrt(short a1,short a2) ;
	default short applyAsSrt(short a1, short a2) {
		// return nestingApplyAsSrt(a1,a2);
		try {
			return this.applyAsSrtX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsSrt(short a1,short a2)
	 */
	short applyAsSrtX(short a1, short a2) throws Throwable;

	default short tupleApplyAsSrt(LSrtPair args) {
		return applyAsSrt(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default short handlingApplyAsSrt(short a1, short a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsSrtX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LSrtBinaryOperator handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2) -> handlingApplyAsSrt(a1, a2, handling);
	}

	default short applyAsSrt(short a1, short a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyAsSrtX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default short applyAsSrt(short a1, short a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyAsSrtX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default short applyAsSrt(short a1, short a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyAsSrtX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default short applyAsSrt(short a1, short a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyAsSrtX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LSrtBinaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2) -> applyAsSrt(a1, a2, factory, newMessage);
	}

	default LSrtBinaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2) -> applyAsSrt(a1, a2, factory, newMessage, param1);
	}

	default LSrtBinaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2) -> applyAsSrt(a1, a2, factory, newMessage, param1, param1);
	}

	default LSrtBinaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2) -> applyAsSrt(a1, a2, factory, newMessage, param1, param2, param3);
	}

	default short applyAsSrt(short a1, short a2, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyAsSrtX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LSrtBinaryOperator trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2) -> applyAsSrt(a1, a2, factory);
	}

	default short applyAsSrtThen(short a1, short a2, @Nonnull LToSrtFunction<Throwable> handler) {
		try {
			return this.applyAsSrtX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsSrt(e);
		}
	}

	default LSrtBinaryOperator tryingThen(@Nonnull LToSrtFunction<Throwable> handler) {
		return (a1, a2) -> applyAsSrtThen(a1, a2, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default short nestingApplyAsSrt(short a1, short a2) {
		try {
			return this.applyAsSrtX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default short shovingApplyAsSrt(short a1, short a2) {
		try {
			return this.applyAsSrtX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static short shovingApplyAsSrt(short a1, short a2, LSrtBinaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.shovingApplyAsSrt(a1, a2);
	}

	static short handlingApplyAsSrt(short a1, short a2, LSrtBinaryOperator func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsSrt(a1, a2, handling);
	}

	static short tryApplyAsSrt(short a1, short a2, LSrtBinaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsSrt(a1, a2);
	}

	static short tryApplyAsSrt(short a1, short a2, LSrtBinaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrt(a1, a2, factory, newMessage);
	}

	static short tryApplyAsSrt(short a1, short a2, LSrtBinaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrt(a1, a2, factory, newMessage, param1);
	}

	static short tryApplyAsSrt(short a1, short a2, LSrtBinaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrt(a1, a2, factory, newMessage, param1, param2);
	}

	static short tryApplyAsSrt(short a1, short a2, LSrtBinaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrt(a1, a2, factory, newMessage, param1, param2, param3);
	}

	static short tryApplyAsSrt(short a1, short a2, LSrtBinaryOperator func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrt(a1, a2, factory);
	}

	static short tryApplyAsSrtThen(short a1, short a2, LSrtBinaryOperator func, @Nonnull LToSrtFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrtThen(a1, a2, handler);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default short nonNullApplyAsSrt(short a1, short a2) {
		return applyAsSrt(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LSrtBinaryOperator.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, short a1, short a2, @Nonnull LSrtBinaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.applyAsSrt(a1, a2);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.applyAsSrt(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, short a1, short a2, @Nonnull LSrtBinaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.applyAsSrt(a1, a2);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.applyAsSrt(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, short a1, short a2, @Nonnull LSrtBinaryOperator func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, func);
	}

	default LSrtUnaryOperator _with(short a1) {
		return a2 -> applyAsSrt(a1, a2);
	}

	default LSrtUnaryOperator with(short a2) {
		return a1 -> applyAsSrt(a1, a2);
	}

	/**  */
	public static LSrtBinaryOperator uncurry(@Nonnull LSrtFunction<LSrtUnaryOperator> func) {
		Null.nonNullArg(func, "func");
		return (short a1, short a2) -> func.apply(a1).applyAsSrt(a2);
	}

	/** Change function to one that ignores output. */
	default LBiSrtConsumer toConsumer() {
		return this::applyAsSrt;
	}

	/** Calls domain consumer before main function. */
	default LSrtBinaryOperator beforeDo(@Nonnull LBiSrtConsumer before) {
		Null.nonNullArg(before, "before");
		return (short a1, short a2) -> {
			before.accept(a1, a2);
			return applyAsSrt(a1, a2);
		};
	}

	/** Calls codomain consumer after main function. */
	default LSrtBinaryOperator afterDo(@Nonnull LSrtConsumer after) {
		Null.nonNullArg(after, "after");
		return (short a1, short a2) -> {
			final short retval = applyAsSrt(a1, a2);
			after.accept(retval);
			return retval;
		};
	}

	/** Creates function that always returns the same value. */
	static LSrtBinaryOperator constant(short r) {
		return (a1, a2) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LSrtBinaryOperator srtBinaryOp(final @Nonnull LSrtBinaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S implements LSrtBinaryOperator {
		private LSrtBinaryOperator target = null;
		@Override
		public short applyAsSrtX(short a1, short a2) throws Throwable {
			return target.applyAsSrtX(a1, a2);
		}
	}

	@Nonnull
	static LSrtBinaryOperator recursive(final @Nonnull LFunction<LSrtBinaryOperator, LSrtBinaryOperator> selfLambda) {
		final S single = new S();
		LSrtBinaryOperator func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static M mementoOf(short a1, short a2, LSrtBinaryOperator function) {
		var initialValue = function.applyAsSrt(a1, a2);
		return initializedMementoOf(initialValue, function);
	}

	public static M initializedMementoOf(short initialValue, LSrtBinaryOperator function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static M deltaOf(short a1, short a2, LSrtBinaryOperator function, LSrtBinaryOperator deltaFunction) {
		var initialValue = function.applyAsSrt(a1, a2);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static M deltaOf(short a1, short a2, LSrtBinaryOperator function) {
		var initialValue = function.applyAsSrt(a1, a2);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (short) (x2 - x1));
	}

	public static M initializedDeltaOf(short initialValue, LSrtBinaryOperator function, LSrtBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.applyAsSrt(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.applyAsSrt(x1, x2));
	}

	public static M memento(short initialBaseValue, short initialValue, LSrtBinaryOperator baseFunction, LSrtTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LSrtBinaryOperator.M)
	 */
	@NotThreadSafe
	final class M implements LSrtBinaryOperator {

		private final LSrtBinaryOperator baseFunction;
		private short lastBaseValue;
		private short lastValue;
		private final LSrtTernaryOperator mementoFunction;

		private M(short lastBaseValue, short lastValue, LSrtBinaryOperator baseFunction, LSrtTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public short applyAsSrtX(short a1, short a2) throws Throwable {
			short x1 = lastBaseValue;
			short x2 = lastBaseValue = baseFunction.applyAsSrtX(a1, a2);

			return lastValue = mementoFunction.applyAsSrtX(lastValue, x1, x2);
		}

		public short currentApplyAsSrt(short a1, short a2) {
			short x1 = lastBaseValue;
			short x2 = baseFunction.applyAsSrt(a1, a2);

			return mementoFunction.applyAsSrt(lastValue, x1, x2);
		}

		public short lastValue() {
			return lastValue;
		}

		public short lastBaseValue() {
			return lastBaseValue;
		}

		public short currentBaseValue(short a1, short a2) {
			return baseFunction.applyAsSrt(a1, a2);
		}
	}

	// </editor-fold>

	@Nonnull
	static LSrtBinaryOperator srtBinaryOpThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LSrtBinaryOperator srtBinaryOpThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce(message);
		};
	}

	static short call(short a1, short a2, final @Nonnull LSrtBinaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsSrt(a1, a2);
	}

	// <editor-fold desc="wrap">

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
	default LSrtBinaryOperator compose(@Nonnull final LSrtUnaryOperator before1, @Nonnull final LSrtUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.applyAsSrt(before1.applyAsSrt(v1), before2.applyAsSrt(v2));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LToSrtBiFunction<V1, V2> unboxingCompose(@Nonnull final LToSrtFunction<? super V1> before1, @Nonnull final LToSrtFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.applyAsSrt(before1.applyAsSrt(v1), before2.applyAsSrt(v2));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiSrtFunction<V> then(@Nonnull LSrtFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.apply(this.applyAsSrt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtBinaryOperator thenToSrt(@Nonnull LSrtUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsSrt(this.applyAsSrt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiSrtPredicate thenToBool(@Nonnull LSrtPredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.test(this.applyAsSrt(a1, a2));
	}

	// </editor-fold>

	default LSrtBinaryOperator shoving() {

		return new LSrtBinaryOperator() {

			public short applyAsSrt(short a1, short a2) {
				try {
					return this.applyAsSrtX(a1, a2);
				} catch (Throwable e) { // NOSONAR
					Handling.handleErrors(e);
					throw Handling.throwIt(e);
				}
			}

			public short applyAsSrtX(short a1, short a2) throws Throwable {
				return LSrtBinaryOperator.this.applyAsSrtX(a1, a2);
			}

		};
	}

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LSrtBinaryOperator) Operator */
	public static short doNothing(short a1, short a2) {
		return Function4U.defaultShort;
	}

}
