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

package eu.lunisolar.magma.func.function.from;

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
 * Non-throwing functional interface (lambda) LTriSrtFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 3): short a1,short a2,short a3
 *
 * Co-domain: R
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LTriSrtFunction<R> extends MetaFunction, MetaInterface.NonThrowing, Codomain<a<R>>, Domain3<aShort, aShort, aShort> { // NOSONAR

	String DESCRIPTION = "LTriSrtFunction: R apply(short a1,short a2,short a3)";

	@Nullable
	// R apply(short a1,short a2,short a3) ;
	default R apply(short a1, short a2, short a3) {
		// return nestingApply(a1,a2,a3);
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call apply(short a1,short a2,short a3)
	 */
	R applyX(short a1, short a2, short a3) throws Throwable;

	default R tupleApply(LSrtTriple args) {
		return apply(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions according to the instructions. */
	default R handlingApply(short a1, short a2, short a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LTriSrtFunction<R> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> handlingApply(a1, a2, a3, handling);
	}

	default R apply(short a1, short a2, short a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default R apply(short a1, short a2, short a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default R apply(short a1, short a2, short a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default R apply(short a1, short a2, short a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LTriSrtFunction<R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2, a3) -> apply(a1, a2, a3, factory, newMessage);
	}

	default LTriSrtFunction<R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2, a3) -> apply(a1, a2, a3, factory, newMessage, param1);
	}

	default LTriSrtFunction<R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2, a3) -> apply(a1, a2, a3, factory, newMessage, param1, param1);
	}

	default LTriSrtFunction<R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2, a3) -> apply(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	default R apply(short a1, short a2, short a3, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LTriSrtFunction<R> trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2, a3) -> apply(a1, a2, a3, factory);
	}

	default R applyThen(short a1, short a2, short a3, @Nonnull LFunction<Throwable, R> handler) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.apply(e);
		}
	}

	default LTriSrtFunction<R> tryingThen(@Nonnull LFunction<Throwable, R> handler) {
		return (a1, a2, a3) -> applyThen(a1, a2, a3, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default R nestingApply(short a1, short a2, short a3) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default R shovingApply(short a1, short a2, short a3) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <R> R shovingApply(short a1, short a2, short a3, LTriSrtFunction<R> func) {
		Null.nonNullArg(func, "func");
		return func.shovingApply(a1, a2, a3);
	}

	static <R> R handlingApply(short a1, short a2, short a3, LTriSrtFunction<R> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApply(a1, a2, a3, handling);
	}

	static <R> R tryApply(short a1, short a2, short a3, LTriSrtFunction<R> func) {
		Null.nonNullArg(func, "func");
		return func.nestingApply(a1, a2, a3);
	}

	static <R> R tryApply(short a1, short a2, short a3, LTriSrtFunction<R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, factory, newMessage);
	}

	static <R> R tryApply(short a1, short a2, short a3, LTriSrtFunction<R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, factory, newMessage, param1);
	}

	static <R> R tryApply(short a1, short a2, short a3, LTriSrtFunction<R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, factory, newMessage, param1, param2);
	}

	static <R> R tryApply(short a1, short a2, short a3, LTriSrtFunction<R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	static <R> R tryApply(short a1, short a2, short a3, LTriSrtFunction<R> func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, factory);
	}

	static <R> R tryApplyThen(short a1, short a2, short a3, LTriSrtFunction<R> func, @Nonnull LFunction<Throwable, R> handler) {
		Null.nonNullArg(func, "func");
		return func.applyThen(a1, a2, a3, handler);
	}

	default R failSafeApply(short a1, short a2, short a3, @Nonnull LTriSrtFunction<R> failSafe) {
		try {
			return apply(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.apply(a1, a2, a3);
		}
	}

	static <R> R failSafeApply(short a1, short a2, short a3, LTriSrtFunction<R> func, @Nonnull LTriSrtFunction<R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.apply(a1, a2, a3);
		} else {
			return func.failSafeApply(a1, a2, a3, failSafe);
		}
	}

	static <R> LTriSrtFunction<R> failSafe(LTriSrtFunction<R> func, @Nonnull LTriSrtFunction<R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeApply(a1, a2, a3, func, failSafe);
	}

	LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullApply() method cannot be null (" + DESCRIPTION + ").";

	/** Function call that ensures the result is not null */
	@Nonnull
	default R nonNullApply(short a1, short a2, short a3) {
		return Null.nonNull(apply(a1, a2, a3), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LTriSrtFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <R> void fromTo(int min_i, int max_i, short a1, short a2, short a3, @Nonnull LTriSrtFunction<R> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.apply(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.apply(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <R> void fromTill(int min_i, int max_i, short a1, short a2, short a3, @Nonnull LTriSrtFunction<R> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.apply(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.apply(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <R> void times(int max_i, short a1, short a2, short a3, @Nonnull LTriSrtFunction<R> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, a3, func);
	}

	/**  */
	public static <R> LTriSrtFunction<R> uncurry(@Nonnull LSrtFunction<LSrtFunction<LSrtFunction<R>>> func) {
		Null.nonNullArg(func, "func");
		return (short a1, short a2, short a3) -> func.apply(a1).apply(a2).apply(a3);
	}

	/** Change function to consumer that ignores output. */
	default LTriSrtConsumer toConsumer() {
		return this::apply;
	}

	/** Calls domain consumer before main function. */
	default LTriSrtFunction<R> beforeDo(@Nonnull LTriSrtConsumer before) {
		Null.nonNullArg(before, "before");
		return (short a1, short a2, short a3) -> {
			before.accept(a1, a2, a3);
			return apply(a1, a2, a3);
		};
	}

	/** Calls codomain consumer after main function. */
	default LTriSrtFunction<R> afterDo(@Nonnull LConsumer<R> after) {
		Null.nonNullArg(after, "after");
		return (short a1, short a2, short a3) -> {
			final R retval = apply(a1, a2, a3);
			after.accept(retval);
			return retval;
		};
	}

	/** Creates function that always returns the same value. */
	static <R> LTriSrtFunction<R> constant(R r) {
		return (a1, a2, a3) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <R> LTriSrtFunction<R> triSrtFunc(final @Nonnull LTriSrtFunction<R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S<R> implements LTriSrtFunction<R> {
		private LTriSrtFunction<R> target = null;
		@Override
		public R applyX(short a1, short a2, short a3) throws Throwable {
			return target.applyX(a1, a2, a3);
		}
	}

	@Nonnull
	static <R> LTriSrtFunction<R> recursive(final @Nonnull LFunction<LTriSrtFunction<R>, LTriSrtFunction<R>> selfLambda) {
		final S<R> single = new S();
		LTriSrtFunction<R> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static <R> M<R> mementoOf(short a1, short a2, short a3, LTriSrtFunction<R> function) {
		var initialValue = function.apply(a1, a2, a3);
		return initializedMementoOf(initialValue, function);
	}

	public static <R> M<R> initializedMementoOf(R initialValue, LTriSrtFunction<R> function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static <R> M<R> deltaOf(short a1, short a2, short a3, LTriSrtFunction<R> function, LBinaryOperator<R> deltaFunction) {
		var initialValue = function.apply(a1, a2, a3);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static <R> M<R> initializedDeltaOf(R initialValue, LTriSrtFunction<R> function, LBinaryOperator<R> deltaFunction) {
		return memento(initialValue, deltaFunction.apply(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.apply(x1, x2));
	}

	public static <R> M<R> memento(R initialBaseValue, R initialValue, LTriSrtFunction<R> baseFunction, LTernaryOperator<R> mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LTriSrtFunction.M)
	 */
	@NotThreadSafe
	final class M<R> implements LTriSrtFunction<R> {

		private final LTriSrtFunction<R> baseFunction;
		private R lastBaseValue;
		private R lastValue;
		private final LTernaryOperator<R> mementoFunction;

		private M(R lastBaseValue, R lastValue, LTriSrtFunction<R> baseFunction, LTernaryOperator<R> mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public R applyX(short a1, short a2, short a3) throws Throwable {
			R x1 = lastBaseValue;
			R x2 = lastBaseValue = baseFunction.applyX(a1, a2, a3);

			return lastValue = mementoFunction.applyX(lastValue, x1, x2);
		}

		public R currentApply(short a1, short a2, short a3) {
			R x1 = lastBaseValue;
			R x2 = baseFunction.apply(a1, a2, a3);

			return mementoFunction.apply(lastValue, x1, x2);
		}

		public R lastValue() {
			return lastValue;
		};

		public R lastBaseValue() {
			return lastBaseValue;
		};
	}

	// </editor-fold>

	@Nonnull
	static <R> LTriSrtFunction<R> triSrtFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <R> LTriSrtFunction<R> triSrtFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce(message);
		};
	}

	static <R> R call(short a1, short a2, short a3, final @Nonnull LTriSrtFunction<R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.apply(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LTriSrtFunction<R> compose(@Nonnull final LSrtUnaryOperator before1, @Nonnull final LSrtUnaryOperator before2, @Nonnull final LSrtUnaryOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.apply(before1.applyAsSrt(v1), before2.applyAsSrt(v2), before3.applyAsSrt(v3));
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriFunction<V1, V2, V3, R> unboxingCompose(@Nonnull final LToSrtFunction<? super V1> before1, @Nonnull final LToSrtFunction<? super V2> before2, @Nonnull final LToSrtFunction<? super V3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.apply(before1.applyAsSrt(v1), before2.applyAsSrt(v2), before3.applyAsSrt(v3));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LTriSrtFunction<V> then(@Nonnull LFunction<? super R, ? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.apply(this.apply(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LTriSrtConsumer thenConsume(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.accept(this.apply(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtTernaryOperator thenToSrt(@Nonnull LToSrtFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.applyAsSrt(this.apply(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LTriSrtPredicate thenToBool(@Nonnull LPredicate<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.test(this.apply(a1, a2, a3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LTriSrtFunction<R> nonNullable() {
		return this::nonNullApply;
	}

	/** Does nothing (LTriSrtFunction) Function */
	public static <R> R doNothing(short a1, short a2, short a3) {
		return (R) Function4U.defaultObject;
	}

}
