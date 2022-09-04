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
 * Non-throwing functional interface (lambda) LSrtTernaryOperator for Java 8.
 *
 * Type: operator
 *
 * Domain (lvl: 3): short a1,short a2,short a3
 *
 * Co-domain: short
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LSrtTernaryOperator extends MetaOperator, MetaInterface.NonThrowing, Codomain<aShort>, Domain3<aShort, aShort, aShort> { // NOSONAR

	String DESCRIPTION = "LSrtTernaryOperator: short applyAsSrt(short a1,short a2,short a3)";

	// short applyAsSrt(short a1,short a2,short a3) ;
	default short applyAsSrt(short a1, short a2, short a3) {
		// return nestingApplyAsSrt(a1,a2,a3);
		try {
			return this.applyAsSrtX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsSrt(short a1,short a2,short a3)
	 */
	short applyAsSrtX(short a1, short a2, short a3) throws Throwable;

	default short tupleApplyAsSrt(LSrtTriple args) {
		return applyAsSrt(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions according to the instructions. */
	default short handlingApplyAsSrt(short a1, short a2, short a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsSrtX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LSrtTernaryOperator handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> handlingApplyAsSrt(a1, a2, a3, handling);
	}

	default short applyAsSrt(short a1, short a2, short a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyAsSrtX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default short applyAsSrt(short a1, short a2, short a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyAsSrtX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default short applyAsSrt(short a1, short a2, short a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyAsSrtX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default short applyAsSrt(short a1, short a2, short a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyAsSrtX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LSrtTernaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2, a3) -> applyAsSrt(a1, a2, a3, factory, newMessage);
	}

	default LSrtTernaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2, a3) -> applyAsSrt(a1, a2, a3, factory, newMessage, param1);
	}

	default LSrtTernaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2, a3) -> applyAsSrt(a1, a2, a3, factory, newMessage, param1, param1);
	}

	default LSrtTernaryOperator trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2, a3) -> applyAsSrt(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	default short applyAsSrt(short a1, short a2, short a3, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyAsSrtX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LSrtTernaryOperator trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2, a3) -> applyAsSrt(a1, a2, a3, factory);
	}

	default short applyAsSrtThen(short a1, short a2, short a3, @Nonnull LToSrtFunction<Throwable> handler) {
		try {
			return this.applyAsSrtX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsSrt(e);
		}
	}

	default LSrtTernaryOperator tryingThen(@Nonnull LToSrtFunction<Throwable> handler) {
		return (a1, a2, a3) -> applyAsSrtThen(a1, a2, a3, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default short nestingApplyAsSrt(short a1, short a2, short a3) {
		try {
			return this.applyAsSrtX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default short shovingApplyAsSrt(short a1, short a2, short a3) {
		try {
			return this.applyAsSrtX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static short shovingApplyAsSrt(short a1, short a2, short a3, LSrtTernaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.shovingApplyAsSrt(a1, a2, a3);
	}

	static short handlingApplyAsSrt(short a1, short a2, short a3, LSrtTernaryOperator func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsSrt(a1, a2, a3, handling);
	}

	static short tryApplyAsSrt(short a1, short a2, short a3, LSrtTernaryOperator func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsSrt(a1, a2, a3);
	}

	static short tryApplyAsSrt(short a1, short a2, short a3, LSrtTernaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrt(a1, a2, a3, factory, newMessage);
	}

	static short tryApplyAsSrt(short a1, short a2, short a3, LSrtTernaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrt(a1, a2, a3, factory, newMessage, param1);
	}

	static short tryApplyAsSrt(short a1, short a2, short a3, LSrtTernaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrt(a1, a2, a3, factory, newMessage, param1, param2);
	}

	static short tryApplyAsSrt(short a1, short a2, short a3, LSrtTernaryOperator func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrt(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	static short tryApplyAsSrt(short a1, short a2, short a3, LSrtTernaryOperator func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrt(a1, a2, a3, factory);
	}

	static short tryApplyAsSrtThen(short a1, short a2, short a3, LSrtTernaryOperator func, @Nonnull LToSrtFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrtThen(a1, a2, a3, handler);
	}

	default short failSafeApplyAsSrt(short a1, short a2, short a3, @Nonnull LSrtTernaryOperator failSafe) {
		try {
			return applyAsSrt(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.applyAsSrt(a1, a2, a3);
		}
	}

	static short failSafeApplyAsSrt(short a1, short a2, short a3, LSrtTernaryOperator func, @Nonnull LSrtTernaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.applyAsSrt(a1, a2, a3);
		} else {
			return func.failSafeApplyAsSrt(a1, a2, a3, failSafe);
		}
	}

	static LSrtTernaryOperator failSafe(LSrtTernaryOperator func, @Nonnull LSrtTernaryOperator failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeApplyAsSrt(a1, a2, a3, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default short nonNullApplyAsSrt(short a1, short a2, short a3) {
		return applyAsSrt(a1, a2, a3);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LSrtTernaryOperator.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, short a1, short a2, short a3, @Nonnull LSrtTernaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.applyAsSrt(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.applyAsSrt(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_i, int max_i, short a1, short a2, short a3, @Nonnull LSrtTernaryOperator func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.applyAsSrt(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.applyAsSrt(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_i, short a1, short a2, short a3, @Nonnull LSrtTernaryOperator func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, a3, func);
	}

	/**  */
	public static LSrtTernaryOperator uncurry(@Nonnull LSrtFunction<LSrtFunction<LSrtUnaryOperator>> func) {
		Null.nonNullArg(func, "func");
		return (short a1, short a2, short a3) -> func.apply(a1).apply(a2).applyAsSrt(a3);
	}

	/** Change function to consumer that ignores output. */
	default LTriSrtConsumer toConsumer() {
		return this::applyAsSrt;
	}

	/** Calls domain consumer before main function. */
	default LSrtTernaryOperator beforeDo(@Nonnull LTriSrtConsumer before) {
		Null.nonNullArg(before, "before");
		return (short a1, short a2, short a3) -> {
			before.accept(a1, a2, a3);
			return applyAsSrt(a1, a2, a3);
		};
	}

	/** Calls codomain consumer after main function. */
	default LSrtTernaryOperator afterDo(@Nonnull LSrtConsumer after) {
		Null.nonNullArg(after, "after");
		return (short a1, short a2, short a3) -> {
			final short retval = applyAsSrt(a1, a2, a3);
			after.accept(retval);
			return retval;
		};
	}

	/** Creates function that always returns the same value. */
	static LSrtTernaryOperator constant(short r) {
		return (a1, a2, a3) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LSrtTernaryOperator srtTernaryOp(final @Nonnull LSrtTernaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S implements LSrtTernaryOperator {
		private LSrtTernaryOperator target = null;
		@Override
		public short applyAsSrtX(short a1, short a2, short a3) throws Throwable {
			return target.applyAsSrtX(a1, a2, a3);
		}
	}

	@Nonnull
	static LSrtTernaryOperator recursive(final @Nonnull LFunction<LSrtTernaryOperator, LSrtTernaryOperator> selfLambda) {
		final S single = new S();
		LSrtTernaryOperator func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static M mementoOf(short a1, short a2, short a3, LSrtTernaryOperator function) {
		var initialValue = function.applyAsSrt(a1, a2, a3);
		return initializedMementoOf(initialValue, function);
	}

	public static M initializedMementoOf(short initialValue, LSrtTernaryOperator function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static M deltaOf(short a1, short a2, short a3, LSrtTernaryOperator function, LSrtBinaryOperator deltaFunction) {
		var initialValue = function.applyAsSrt(a1, a2, a3);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static M deltaOf(short a1, short a2, short a3, LSrtTernaryOperator function) {
		var initialValue = function.applyAsSrt(a1, a2, a3);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (short) (x2 - x1));
	}

	public static M initializedDeltaOf(short initialValue, LSrtTernaryOperator function, LSrtBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.applyAsSrt(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.applyAsSrt(x1, x2));
	}

	public static M memento(short initialBaseValue, short initialValue, LSrtTernaryOperator baseFunction, LSrtTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LSrtTernaryOperator.M)
	 */
	@NotThreadSafe
	final class M implements LSrtTernaryOperator {

		private final LSrtTernaryOperator baseFunction;
		private short lastBaseValue;
		private short lastValue;
		private final LSrtTernaryOperator mementoFunction;

		private M(short lastBaseValue, short lastValue, LSrtTernaryOperator baseFunction, LSrtTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public short applyAsSrtX(short a1, short a2, short a3) throws Throwable {
			short x1 = lastBaseValue;
			short x2 = lastBaseValue = baseFunction.applyAsSrtX(a1, a2, a3);

			return lastValue = mementoFunction.applyAsSrt(lastValue, x1, x2);
		}

		public short lastValue() {
			return lastValue;
		};

		public short lastBaseValue() {
			return lastBaseValue;
		};
	}

	// </editor-fold>

	@Nonnull
	static LSrtTernaryOperator srtTernaryOpThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LSrtTernaryOperator srtTernaryOpThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce(message);
		};
	}

	static short call(short a1, short a2, short a3, final @Nonnull LSrtTernaryOperator lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsSrt(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LSrtTernaryOperator compose(@Nonnull final LSrtUnaryOperator before1, @Nonnull final LSrtUnaryOperator before2, @Nonnull final LSrtUnaryOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.applyAsSrt(before1.applyAsSrt(v1), before2.applyAsSrt(v2), before3.applyAsSrt(v3));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LTriSrtFunction<V> then(@Nonnull LSrtFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.apply(this.applyAsSrt(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtTernaryOperator thenToSrt(@Nonnull LSrtUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.applyAsSrt(this.applyAsSrt(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LTriSrtPredicate thenToBool(@Nonnull LSrtPredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.test(this.applyAsSrt(a1, a2, a3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Does nothing (LSrtTernaryOperator) Operator */
	public static short doNothing(short a1, short a2, short a3) {
		return Function4U.defaultShort;
	}

}
