/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2019 Lunisolar (http://lunisolar.eu/).
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

package eu.lunisolar.magma.func.function.to;

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
 * Non-throwing functional interface (lambda) LToDblBiFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 2): T1 a1,T2 a2
 *
 * Co-domain: double
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LToDblBiFunction<T1, T2> extends ToDoubleBiFunction<T1, T2>, MetaFunction, MetaInterface.NonThrowing, Codomain<aDouble>, Domain2<a<T1>, a<T2>> { // NOSONAR

	String DESCRIPTION = "LToDblBiFunction: double applyAsDbl(T1 a1,T2 a2)";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LToDblBiFunction interface should be discouraged.
	 */
	@Override
	@Deprecated
	default double applyAsDouble(T1 a1, T2 a2) {
		return this.applyAsDbl(a1, a2);
	}

	// double applyAsDbl(T1 a1,T2 a2) ;
	default double applyAsDbl(T1 a1, T2 a2) {
		// return nestingApplyAsDbl(a1,a2);
		try {
			return this.applyAsDblX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsDbl(T1 a1,T2 a2)
	 */
	double applyAsDblX(T1 a1, T2 a2) throws Throwable;

	default double tupleApplyAsDbl(LPair<T1, T2> args) {
		return applyAsDbl(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default double handlingApplyAsDbl(T1 a1, T2 a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsDblX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LToDblBiFunction<T1, T2> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2) -> handlingApplyAsDbl(a1, a2, handling);
	}

	default double applyAsDbl(T1 a1, T2 a2, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.applyAsDblX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LToDblBiFunction<T1, T2> trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return (a1, a2) -> applyAsDbl(a1, a2, exF, newMessage, messageParams);
	}

	default double applyAsDbl(T1 a1, T2 a2, @Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.applyAsDblX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LToDblBiFunction<T1, T2> trying(@Nonnull ExWF<RuntimeException> exF) {
		return (a1, a2) -> applyAsDbl(a1, a2, exF);
	}

	default double applyAsDblThen(T1 a1, T2 a2, @Nonnull LToDblFunction<Throwable> handler) {
		try {
			return this.applyAsDblX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsDbl(e);
		}
	}

	default LToDblBiFunction<T1, T2> tryingThen(@Nonnull LToDblFunction<Throwable> handler) {
		return (a1, a2) -> applyAsDblThen(a1, a2, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default double nestingApplyAsDbl(T1 a1, T2 a2) {
		try {
			return this.applyAsDblX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default double shovingApplyAsDbl(T1 a1, T2 a2) {
		try {
			return this.applyAsDblX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T1, T2> double handlingApplyAsDbl(T1 a1, T2 a2, LToDblBiFunction<T1, T2> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsDbl(a1, a2, handling);
	}

	static <T1, T2> double tryApplyAsDbl(T1 a1, T2 a2, LToDblBiFunction<T1, T2> func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsDbl(a1, a2);
	}

	static <T1, T2> double tryApplyAsDbl(T1 a1, T2 a2, LToDblBiFunction<T1, T2> func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.applyAsDbl(a1, a2, exF, newMessage, messageParams);
	}

	static <T1, T2> double tryApplyAsDbl(T1 a1, T2 a2, LToDblBiFunction<T1, T2> func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.applyAsDbl(a1, a2, exF);
	}

	static <T1, T2> double tryApplyAsDblThen(T1 a1, T2 a2, LToDblBiFunction<T1, T2> func, @Nonnull LToDblFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsDblThen(a1, a2, handler);
	}

	default double failSafeApplyAsDbl(T1 a1, T2 a2, @Nonnull LToDblBiFunction<T1, T2> failSafe) {
		try {
			return applyAsDbl(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.applyAsDbl(a1, a2);
		}
	}

	static <T1, T2> double failSafeApplyAsDbl(T1 a1, T2 a2, LToDblBiFunction<T1, T2> func, @Nonnull LToDblBiFunction<T1, T2> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.applyAsDbl(a1, a2);
		} else {
			return func.failSafeApplyAsDbl(a1, a2, failSafe);
		}
	}

	static <T1, T2> LToDblBiFunction<T1, T2> failSafe(LToDblBiFunction<T1, T2> func, @Nonnull LToDblBiFunction<T1, T2> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeApplyAsDbl(a1, a2, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default double nonNullApplyAsDbl(T1 a1, T2 a2) {
		return applyAsDbl(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LToDblBiFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void fromTo(int min_i, int max_i, T1 a1, T2 a2, @Nonnull LToDblBiFunction<T1, T2> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.applyAsDbl(a1, a2);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.applyAsDbl(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void fromTill(int min_i, int max_i, T1 a1, T2 a2, @Nonnull LToDblBiFunction<T1, T2> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.applyAsDbl(a1, a2);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.applyAsDbl(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void times(int max_i, T1 a1, T2 a2, @Nonnull LToDblBiFunction<T1, T2> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, func);
	}

	/** Extract and apply function. */
	public static <M, K, V, T2> double from(@Nonnull M container, LBiFunction<M, K, V> extractor, K key, T2 a2, @Nonnull LToDblBiFunction<V, T2> function, double orElse) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.applyAsDbl(value, a2);
		}

		return orElse;
	}

	public default LToDblFunction<T2> lShrink(@Nonnull LFunction<T2, T1> left) {
		Null.nonNullArg(left, "left");
		return a2 -> applyAsDbl(left.apply(a2), a2);
	}

	public default LToDblFunction<T2> lShrink_(T1 a1) {
		return a2 -> applyAsDbl(a1, a2);
	}

	public static <T2, T1> LToDblFunction<T2> lShrunken(@Nonnull LFunction<T2, T1> left, @Nonnull LToDblBiFunction<T1, T2> func) {
		Null.nonNullArg(left, "left");
		Null.nonNullArg(func, "func");
		return func.lShrink(left);
	}

	public static <T2, T1> LToDblFunction<T2> lShrunken_(T1 a1, @Nonnull LToDblBiFunction<T1, T2> func) {
		Null.nonNullArg(func, "func");
		return func.lShrink_(a1);
	}

	public default LToDblFunction<T1> rShrink(@Nonnull LFunction<T1, T2> right) {
		Null.nonNullArg(right, "right");
		return a1 -> applyAsDbl(a1, right.apply(a1));
	}

	public default LToDblFunction<T1> rShrink_(T2 a2) {
		return a1 -> applyAsDbl(a1, a2);
	}

	public static <T1, T2> LToDblFunction<T1> rShrunken(@Nonnull LFunction<T1, T2> right, @Nonnull LToDblBiFunction<T1, T2> func) {
		Null.nonNullArg(right, "right");
		Null.nonNullArg(func, "func");
		return func.rShrink(right);
	}

	public static <T1, T2> LToDblFunction<T1> rShrunken_(T2 a2, @Nonnull LToDblBiFunction<T1, T2> func) {
		Null.nonNullArg(func, "func");
		return func.rShrink_(a2);
	}

	/**  */
	public static <T1, T2> LToDblBiFunction<T1, T2> uncurry(@Nonnull LFunction<T1, LToDblFunction<T2>> func) {
		Null.nonNullArg(func, "func");
		return (T1 a1, T2 a2) -> func.apply(a1).applyAsDbl(a2);
	}

	/** Cast that removes generics. */
	public default LToDblBiFunction untyped() {
		return this;
	}

	/** Cast that replace generics. */
	public default <V2, V3> LToDblBiFunction<V2, V3> cast() {
		return untyped();
	}

	/** Cast that replace generics. */
	public static <V2, V3, T1, T2> LToDblBiFunction<V2, V3> cast(LToDblBiFunction<T1, T2> function) {
		return (LToDblBiFunction) function;
	}

	/** Change function to consumer that ignores output. */
	public default LBiConsumer<T1, T2> toConsumer() {
		return this::applyAsDbl;
	}

	/** Calls domain consumer before main function. */
	public default LToDblBiFunction<T1, T2> beforeDo(@Nonnull LBiConsumer<T1, T2> before) {
		Null.nonNullArg(before, "before");
		return (T1 a1, T2 a2) -> {
			before.accept(a1, a2);
			return applyAsDbl(a1, a2);
		};
	}

	/** Calls codomain consumer after main function. */
	public default LToDblBiFunction<T1, T2> afterDo(@Nonnull LDblConsumer after) {
		Null.nonNullArg(after, "after");
		return (T1 a1, T2 a2) -> {
			final double retval = applyAsDbl(a1, a2);
			after.accept(retval);
			return retval;
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LDblSupplier capture(T1 a1, T2 a2) {
		return () -> this.applyAsDbl(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static <T1, T2> LToDblBiFunction<T1, T2> constant(double r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T1, T2> LToDblBiFunction<T1, T2> apply1stAsDbl(@Nonnull LToDblFunction<T1> func) {
		return (a1, a2) -> func.applyAsDbl(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T1, T2> LToDblBiFunction<T1, T2> apply2ndAsDbl(@Nonnull LToDblFunction<T2> func) {
		return (a1, a2) -> func.applyAsDbl(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2> LToDblBiFunction<T1, T2> toDblBiFunc(final @Nonnull LToDblBiFunction<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** A completely inconvenient method in case lambda expression and generic arguments are ambiguous for the compiler. */
	@Nonnull
	static <T1, T2> LToDblBiFunction<T1, T2> toDblBiFunc(@Nullable Class<T1> c1, @Nullable Class<T2> c2, final @Nonnull LToDblBiFunction<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T1, T2> LToDblBiFunction<T1, T2> recursive(final @Nonnull LFunction<LToDblBiFunction<T1, T2>, LToDblBiFunction<T1, T2>> selfLambda) {
		final LToDblBiFunctionSingle<T1, T2> single = new LToDblBiFunctionSingle();
		LToDblBiFunction<T1, T2> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LToDblBiFunctionSingle<T1, T2> implements LToDblBiFunction<T1, T2> {
		private LToDblBiFunction<T1, T2> target = null;

		@Override
		public double applyAsDblX(T1 a1, T2 a2) throws Throwable {
			return target.applyAsDblX(a1, a2);
		}

	}

	@Nonnull
	static <T1, T2> LToDblBiFunction<T1, T2> toDblBiFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T1, T2> LToDblBiFunction<T1, T2> toDblBiFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1> LToDblBiFunction.LToDblObj1Obj0Func<T2, T1> toDblObj1Obj0Func(final @Nonnull LToDblBiFunction.LToDblObj1Obj0Func<T2, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T1, T2> double call(T1 a1, T2 a2, final @Nonnull LToDblBiFunction<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsDbl(a1, a2);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <T1, T2> LToDblBiFunction<T1, T2> wrap(final ToDoubleBiFunction<T1, T2> other) {
		return other::applyAsDouble;
	}
	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as doNothing). */
	@Nonnull
	static <T1, T2> LToDblBiFunction<T1, T2> safe() {
		return LToDblBiFunction::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T1, T2> LSupplier<LToDblBiFunction<T1, T2>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T1, T2> LToDblBiFunction<T1, T2> safe(final @Nullable LToDblBiFunction<T1, T2> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T1, T2> LSupplier<LToDblBiFunction<T1, T2>> safeSupplier(final @Nullable LSupplier<LToDblBiFunction<T1, T2>> supplier) {
		if (supplier == null) {
			return safeSupplier();
		} else {
			return supplier;
		}
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LToDblBiFunction<V1, V2> compose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.applyAsDbl(before1.apply(v1), before2.apply(v2));
	}

	public static <V1, V2, T1, T2> LToDblBiFunction<V1, V2> composed(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, LToDblBiFunction<T1, T2> after) {
		return after.compose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiFunction<T1, T2, V> then(@Nonnull LDblFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.apply(this.applyAsDbl(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToByteBiFunction<T1, T2> thenToByte(@Nonnull LDblToByteFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsByte(this.applyAsDbl(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToSrtBiFunction<T1, T2> thenToSrt(@Nonnull LDblToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsSrt(this.applyAsDbl(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToIntBiFunction<T1, T2> thenToInt(@Nonnull LDblToIntFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsInt(this.applyAsDbl(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToLongBiFunction<T1, T2> thenToLong(@Nonnull LDblToLongFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsLong(this.applyAsDbl(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToFltBiFunction<T1, T2> thenToFlt(@Nonnull LDblToFltFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsFlt(this.applyAsDbl(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToDblBiFunction<T1, T2> thenToDbl(@Nonnull LDblUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsDbl(this.applyAsDbl(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToCharBiFunction<T1, T2> thenToChar(@Nonnull LDblToCharFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsChar(this.applyAsDbl(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiPredicate<T1, T2> thenToBool(@Nonnull LDblPredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.test(this.applyAsDbl(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LToDblBiFunction for method references. */
	@FunctionalInterface
	interface LToDblObj1Obj0Func<T2, T1> extends LToDblBiFunction<T1, T2> {

		/**
		 * Implement this, but call applyAsDbl(T1 a1,T2 a2)
		 */
		default double applyAsDblX(T1 a1, T2 a2) {
			return this.applyAsDblObj1Obj0(a2, a1);
		}

		// double applyAsDblObj1Obj0(T2 a2,T1 a1) ;
		default double applyAsDblObj1Obj0(T2 a2, T1 a1) {
			// return nestingApplyAsDblObj1Obj0(a2,a1);
			try {
				return this.applyAsDblObj1Obj0X(a2, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyAsDblObj1Obj0(T2 a2,T1 a1)
		 */
		double applyAsDblObj1Obj0X(T2 a2, T1 a1) throws Throwable;
	}

	// </editor-fold>

	/** Does nothing (LToDblBiFunction) Function */
	public static <T1, T2> double doNothing(T1 a1, T2 a2) {
		return Function4U.defaultDouble;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2> void forEach(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, LDblConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = oiFunc2.apply(source2, i);
			consumer.accept(this.applyAsDbl(a1, a2));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, LDblConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = oiFunc2.apply(source2, i);
			consumer.accept(this.applyAsDbl(a1, a2));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2> void iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, LDblConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = nextFunc2.apply(iterator2);
			consumer.accept(this.applyAsDbl(a1, a2));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C1, I1, C2, I2> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, LDblConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2)) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = nextFunc2.apply(iterator2);
			consumer.accept(this.applyAsDbl(a1, a2));
		}
	}

}
