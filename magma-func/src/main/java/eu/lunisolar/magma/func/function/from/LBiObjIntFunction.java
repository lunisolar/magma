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

package eu.lunisolar.magma.func.function.from;

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
 * Non-throwing functional interface (lambda) LBiObjIntFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 3): T1 a1,T2 a2,int a3
 *
 * Co-domain: R
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiObjIntFunction<T1, T2, R> extends MetaFunction, MetaInterface.NonThrowing, Codomain<a<R>>, Domain3<a<T1>, a<T2>, aInt> { // NOSONAR

	String DESCRIPTION = "LBiObjIntFunction: R apply(T1 a1,T2 a2,int a3)";

	@Nullable
	// R apply(T1 a1,T2 a2,int a3) ;
	default R apply(T1 a1, T2 a2, int a3) {
		// return nestingApply(a1,a2,a3);
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call apply(T1 a1,T2 a2,int a3)
	 */
	R applyX(T1 a1, T2 a2, int a3) throws Throwable;

	default R tupleApply(LBiObjIntTriple<T1, T2> args) {
		return apply(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions according to the instructions. */
	default R handlingApply(T1 a1, T2 a2, int a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LBiObjIntFunction<T1, T2, R> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> handlingApply(a1, a2, a3, handling);
	}

	default R apply(T1 a1, T2 a2, int a3, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LBiObjIntFunction<T1, T2, R> trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return (a1, a2, a3) -> apply(a1, a2, a3, exF, newMessage, messageParams);
	}

	default R apply(T1 a1, T2 a2, int a3, @Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LBiObjIntFunction<T1, T2, R> trying(@Nonnull ExWF<RuntimeException> exF) {
		return (a1, a2, a3) -> apply(a1, a2, a3, exF);
	}

	default R applyThen(T1 a1, T2 a2, int a3, @Nonnull LFunction<Throwable, R> handler) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.apply(e);
		}
	}

	default LBiObjIntFunction<T1, T2, R> tryingThen(@Nonnull LFunction<Throwable, R> handler) {
		return (a1, a2, a3) -> applyThen(a1, a2, a3, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default R nestingApply(T1 a1, T2 a2, int a3) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default R shovingApply(T1 a1, T2 a2, int a3) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T1, T2, R> R handlingApply(T1 a1, T2 a2, int a3, LBiObjIntFunction<T1, T2, R> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApply(a1, a2, a3, handling);
	}

	static <T1, T2, R> R tryApply(T1 a1, T2 a2, int a3, LBiObjIntFunction<T1, T2, R> func) {
		Null.nonNullArg(func, "func");
		return func.nestingApply(a1, a2, a3);
	}

	static <T1, T2, R> R tryApply(T1 a1, T2 a2, int a3, LBiObjIntFunction<T1, T2, R> func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, exF, newMessage, messageParams);
	}

	static <T1, T2, R> R tryApply(T1 a1, T2 a2, int a3, LBiObjIntFunction<T1, T2, R> func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, exF);
	}

	static <T1, T2, R> R tryApplyThen(T1 a1, T2 a2, int a3, LBiObjIntFunction<T1, T2, R> func, @Nonnull LFunction<Throwable, R> handler) {
		Null.nonNullArg(func, "func");
		return func.applyThen(a1, a2, a3, handler);
	}

	default R failSafeApply(T1 a1, T2 a2, int a3, @Nonnull LBiObjIntFunction<T1, T2, R> failSafe) {
		try {
			return apply(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.apply(a1, a2, a3);
		}
	}

	static <T1, T2, R> R failSafeApply(T1 a1, T2 a2, int a3, LBiObjIntFunction<T1, T2, R> func, @Nonnull LBiObjIntFunction<T1, T2, R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.apply(a1, a2, a3);
		} else {
			return func.failSafeApply(a1, a2, a3, failSafe);
		}
	}

	static <T1, T2, R> LBiObjIntFunction<T1, T2, R> failSafe(LBiObjIntFunction<T1, T2, R> func, @Nonnull LBiObjIntFunction<T1, T2, R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeApply(a1, a2, a3, func, failSafe);
	}

	LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullApply() method cannot be null (" + DESCRIPTION + ").";

	/** Function call that ensures the result is not null */
	@Nonnull
	default R nonNullApply(T1 a1, T2 a2, int a3) {
		return Null.nonNull(apply(a1, a2, a3), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiObjIntFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, R> void fromTo(int min_a3, int max_a3, T1 a1, T2 a2, @Nonnull LBiObjIntFunction<T1, T2, R> func) {
		Null.nonNullArg(func, "func");
		if (min_a3 <= max_a3) {
			for (int a3 = min_a3; a3 <= max_a3; a3++) {
				func.apply(a1, a2, a3);
			}
		} else {
			for (int a3 = min_a3; a3 >= max_a3; a3--) {
				func.apply(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, R> void fromTill(int min_a3, int max_a3, T1 a1, T2 a2, @Nonnull LBiObjIntFunction<T1, T2, R> func) {
		Null.nonNullArg(func, "func");
		if (min_a3 <= max_a3) {
			for (int a3 = min_a3; a3 < max_a3; a3++) {
				func.apply(a1, a2, a3);
			}
		} else {
			for (int a3 = min_a3; a3 > max_a3; a3--) {
				func.apply(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, R> void times(int max_a3, T1 a1, T2 a2, @Nonnull LBiObjIntFunction<T1, T2, R> func) {
		if (max_a3 < 0)
			return;
		fromTill(0, max_a3, a1, a2, func);
	}

	/** Extract and apply function. */
	public static <R, M, K, V, T2> R from(@Nonnull M container, LBiFunction<M, K, V> extractor, K key, T2 a2, int a3, @Nonnull LBiObjIntFunction<V, T2, R> function) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.apply(value, a2, a3);
		}

		return null;
	}

	public default LOiFunction<T2, R> lShrink(@Nonnull LOiFunction<T2, T1> left) {
		Null.nonNullArg(left, "left");
		return (a2, a3) -> apply(left.apply(a2, a3), a2, a3);
	}

	public default LOiFunction<T2, R> lShrink_(T1 a1) {
		return (a2, a3) -> apply(a1, a2, a3);
	}

	public static <T2, R, T1> LOiFunction<T2, R> lShrunken(@Nonnull LOiFunction<T2, T1> left, @Nonnull LBiObjIntFunction<T1, T2, R> func) {
		Null.nonNullArg(left, "left");
		Null.nonNullArg(func, "func");
		return func.lShrink(left);
	}

	public static <T2, R, T1> LOiFunction<T2, R> lShrunken_(T1 a1, @Nonnull LBiObjIntFunction<T1, T2, R> func) {
		Null.nonNullArg(func, "func");
		return func.lShrink_(a1);
	}

	public default LBiFunction<T1, T2, R> rShrink(@Nonnull LToIntBiFunction<T1, T2> right) {
		Null.nonNullArg(right, "right");
		return (a1, a2) -> apply(a1, a2, right.applyAsInt(a1, a2));
	}

	public default LBiFunction<T1, T2, R> rShrink_(int a3) {
		return (a1, a2) -> apply(a1, a2, a3);
	}

	public static <T1, T2, R> LBiFunction<T1, T2, R> rShrunken(@Nonnull LToIntBiFunction<T1, T2> right, @Nonnull LBiObjIntFunction<T1, T2, R> func) {
		Null.nonNullArg(right, "right");
		Null.nonNullArg(func, "func");
		return func.rShrink(right);
	}

	public static <T1, T2, R> LBiFunction<T1, T2, R> rShrunken_(int a3, @Nonnull LBiObjIntFunction<T1, T2, R> func) {
		Null.nonNullArg(func, "func");
		return func.rShrink_(a3);
	}

	/**  */
	public static <T1, T2, R> LBiObjIntFunction<T1, T2, R> uncurry(@Nonnull LFunction<T1, LFunction<T2, LIntFunction<R>>> func) {
		Null.nonNullArg(func, "func");
		return (T1 a1, T2 a2, int a3) -> func.apply(a1).apply(a2).apply(a3);
	}

	/** Cast that removes generics. */
	public default LBiObjIntFunction untyped() {
		return this;
	}

	/** Cast that replace generics. */
	public default <V2, V3, V4> LBiObjIntFunction<V2, V3, V4> cast() {
		return untyped();
	}

	/** Cast that replace generics. */
	public static <V2, V3, V4, T1, T2, R> LBiObjIntFunction<V2, V3, V4> cast(LBiObjIntFunction<T1, T2, R> function) {
		return (LBiObjIntFunction) function;
	}

	/** Change function to consumer that ignores output. */
	public default LBiObjIntConsumer<T1, T2> toConsumer() {
		return this::apply;
	}

	/** Calls domain consumer before main function. */
	public default LBiObjIntFunction<T1, T2, R> beforeDo(@Nonnull LBiObjIntConsumer<T1, T2> before) {
		Null.nonNullArg(before, "before");
		return (T1 a1, T2 a2, int a3) -> {
			before.accept(a1, a2, a3);
			return apply(a1, a2, a3);
		};
	}

	/** Calls codomain consumer after main function. */
	public default LBiObjIntFunction<T1, T2, R> afterDo(@Nonnull LConsumer<R> after) {
		Null.nonNullArg(after, "after");
		return (T1 a1, T2 a2, int a3) -> {
			final R retval = apply(a1, a2, a3);
			after.accept(retval);
			return retval;
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplier<R> capture(T1 a1, T2 a2, int a3) {
		return () -> this.apply(a1, a2, a3);
	}

	/** Creates function that always returns the same value. */
	static <T1, T2, R> LBiObjIntFunction<T1, T2, R> constant(R r) {
		return (a1, a2, a3) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T1, T2, R> LBiObjIntFunction<T1, T2, R> apply1st(@Nonnull LFunction<T1, R> func) {
		return (a1, a2, a3) -> func.apply(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T1, T2, R> LBiObjIntFunction<T1, T2, R> apply2nd(@Nonnull LFunction<T2, R> func) {
		return (a1, a2, a3) -> func.apply(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T1, T2, R> LBiObjIntFunction<T1, T2, R> apply3rd(@Nonnull LIntFunction<R> func) {
		return (a1, a2, a3) -> func.apply(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, R> LBiObjIntFunction<T1, T2, R> biObjIntFunc(final @Nonnull LBiObjIntFunction<T1, T2, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** A completely inconvenient method in case lambda expression and generic arguments are ambiguous for the compiler. */
	@Nonnull
	static <T1, T2, R> LBiObjIntFunction<T1, T2, R> biObjIntFunc(@Nullable Class<T1> c1, @Nullable Class<T2> c2, @Nullable Class<R> c3, final @Nonnull LBiObjIntFunction<T1, T2, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T1, T2, R> LBiObjIntFunction<T1, T2, R> recursive(final @Nonnull LFunction<LBiObjIntFunction<T1, T2, R>, LBiObjIntFunction<T1, T2, R>> selfLambda) {
		final LBiObjIntFunctionSingle<T1, T2, R> single = new LBiObjIntFunctionSingle();
		LBiObjIntFunction<T1, T2, R> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LBiObjIntFunctionSingle<T1, T2, R> implements LSingle<LBiObjIntFunction<T1, T2, R>>, LBiObjIntFunction<T1, T2, R> {
		private LBiObjIntFunction<T1, T2, R> target = null;

		@Override
		public R applyX(T1 a1, T2 a2, int a3) throws Throwable {
			return target.applyX(a1, a2, a3);
		}

		@Override
		public LBiObjIntFunction<T1, T2, R> value() {
			return target;
		}
	}

	@Nonnull
	static <T1, T2, R> LBiObjIntFunction<T1, T2, R> biObjIntFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T1, T2, R> LBiObjIntFunction<T1, T2, R> biObjIntFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, R> LBiObjIntFunction.LObj0Int2Obj1Func<T1, T2, R> obj0Int2Obj1Func(final @Nonnull LBiObjIntFunction.LObj0Int2Obj1Func<T1, T2, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1, R> LBiObjIntFunction.LObj1Obj0Int2Func<T2, T1, R> obj1Obj0Int2Func(final @Nonnull LBiObjIntFunction.LObj1Obj0Int2Func<T2, T1, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1, R> LBiObjIntFunction.LObj1Int2Obj0Func<T2, T1, R> obj1Int2Obj0Func(final @Nonnull LBiObjIntFunction.LObj1Int2Obj0Func<T2, T1, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, R> LBiObjIntFunction.LInt2Obj0Obj1Func<T1, T2, R> int2Obj0Obj1Func(final @Nonnull LBiObjIntFunction.LInt2Obj0Obj1Func<T1, T2, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1, R> LBiObjIntFunction.LInt2Obj1Obj0Func<T2, T1, R> int2Obj1Obj0Func(final @Nonnull LBiObjIntFunction.LInt2Obj1Obj0Func<T2, T1, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T1, T2, R> R call(T1 a1, T2 a2, int a3, final @Nonnull LBiObjIntFunction<T1, T2, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.apply(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produce). */
	@Nonnull
	static <T1, T2, R> LBiObjIntFunction<T1, T2, R> safe() {
		return LBiObjIntFunction::produce;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T1, T2, R> LSupplier<LBiObjIntFunction<T1, T2, R>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T1, T2, R> LBiObjIntFunction<T1, T2, R> safe(final @Nullable LBiObjIntFunction<T1, T2, R> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T1, T2, R> LSupplier<LBiObjIntFunction<T1, T2, R>> safeSupplier(final @Nullable LSupplier<LBiObjIntFunction<T1, T2, R>> supplier) {
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
	default <V1, V2> LBiObjIntFunction<V1, V2, R> compose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LIntUnaryOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.apply(before1.apply(v1), before2.apply(v2), before3.applyAsInt(v3));
	}

	public static <V1, V2, T1, T2, R> LBiObjIntFunction<V1, V2, R> composed(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LIntUnaryOperator before3,
			LBiObjIntFunction<T1, T2, R> after) {
		return after.compose(before1, before2, before3);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriFunction<V1, V2, V3, R> biObjIntFuncCompose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LToIntFunction<? super V3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.apply(before1.apply(v1), before2.apply(v2), before3.applyAsInt(v3));
	}

	public static <V1, V2, V3, T1, T2, R> LTriFunction<V1, V2, V3, R> composed(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LToIntFunction<? super V3> before3,
			LBiObjIntFunction<T1, T2, R> after) {
		return after.biObjIntFuncCompose(before1, before2, before3);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiObjIntFunction<T1, T2, V> then(@Nonnull LFunction<? super R, ? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.apply(this.apply(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiObjIntConsumer<T1, T2> thenConsume(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.accept(this.apply(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiObjIntPredicate<T1, T2> thenToBool(@Nonnull LPredicate<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.test(this.apply(a1, a2, a3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LBiObjIntFunction<T1, T2, R> nonNullable() {
		return this::nonNullApply;
	}

	// <editor-fold desc="interface variants">

	/** Permutation of LBiObjIntFunction for method references. */
	@FunctionalInterface
	interface LObj0Int2Obj1Func<T1, T2, R> extends LBiObjIntFunction<T1, T2, R> {

		/**
		 * Implement this, but call apply(T1 a1,T2 a2,int a3)
		 */
		default R applyX(T1 a1, T2 a2, int a3) {
			return this.applyObj0Int2Obj1(a1, a3, a2);
		}

		@Nullable
		// R applyObj0Int2Obj1(T1 a1,int a3,T2 a2) ;
		default R applyObj0Int2Obj1(T1 a1, int a3, T2 a2) {
			// return nestingApplyObj0Int2Obj1(a1,a3,a2);
			try {
				return this.applyObj0Int2Obj1X(a1, a3, a2);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyObj0Int2Obj1(T1 a1,int a3,T2 a2)
		 */
		R applyObj0Int2Obj1X(T1 a1, int a3, T2 a2) throws Throwable;
	}

	/** Permutation of LBiObjIntFunction for method references. */
	@FunctionalInterface
	interface LObj1Obj0Int2Func<T2, T1, R> extends LBiObjIntFunction<T1, T2, R> {

		/**
		 * Implement this, but call applyObj0Int2Obj1(T1 a1,int a3,T2 a2)
		 */
		default R applyX(T1 a1, T2 a2, int a3) {
			return this.applyObj1Obj0Int2(a2, a1, a3);
		}

		@Nullable
		// R applyObj1Obj0Int2(T2 a2,T1 a1,int a3) ;
		default R applyObj1Obj0Int2(T2 a2, T1 a1, int a3) {
			// return nestingApplyObj1Obj0Int2(a2,a1,a3);
			try {
				return this.applyObj1Obj0Int2X(a2, a1, a3);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyObj1Obj0Int2(T2 a2,T1 a1,int a3)
		 */
		R applyObj1Obj0Int2X(T2 a2, T1 a1, int a3) throws Throwable;
	}

	/** Permutation of LBiObjIntFunction for method references. */
	@FunctionalInterface
	interface LObj1Int2Obj0Func<T2, T1, R> extends LBiObjIntFunction<T1, T2, R> {

		/**
		 * Implement this, but call applyObj1Obj0Int2(T2 a2,T1 a1,int a3)
		 */
		default R applyX(T1 a1, T2 a2, int a3) {
			return this.applyObj1Int2Obj0(a2, a3, a1);
		}

		@Nullable
		// R applyObj1Int2Obj0(T2 a2,int a3,T1 a1) ;
		default R applyObj1Int2Obj0(T2 a2, int a3, T1 a1) {
			// return nestingApplyObj1Int2Obj0(a2,a3,a1);
			try {
				return this.applyObj1Int2Obj0X(a2, a3, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyObj1Int2Obj0(T2 a2,int a3,T1 a1)
		 */
		R applyObj1Int2Obj0X(T2 a2, int a3, T1 a1) throws Throwable;
	}

	/** Permutation of LBiObjIntFunction for method references. */
	@FunctionalInterface
	interface LInt2Obj0Obj1Func<T1, T2, R> extends LBiObjIntFunction<T1, T2, R> {

		/**
		 * Implement this, but call applyObj1Int2Obj0(T2 a2,int a3,T1 a1)
		 */
		default R applyX(T1 a1, T2 a2, int a3) {
			return this.applyInt2Obj0Obj1(a3, a1, a2);
		}

		@Nullable
		// R applyInt2Obj0Obj1(int a3,T1 a1,T2 a2) ;
		default R applyInt2Obj0Obj1(int a3, T1 a1, T2 a2) {
			// return nestingApplyInt2Obj0Obj1(a3,a1,a2);
			try {
				return this.applyInt2Obj0Obj1X(a3, a1, a2);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyInt2Obj0Obj1(int a3,T1 a1,T2 a2)
		 */
		R applyInt2Obj0Obj1X(int a3, T1 a1, T2 a2) throws Throwable;
	}

	/** Permutation of LBiObjIntFunction for method references. */
	@FunctionalInterface
	interface LInt2Obj1Obj0Func<T2, T1, R> extends LBiObjIntFunction<T1, T2, R> {

		/**
		 * Implement this, but call applyInt2Obj0Obj1(int a3,T1 a1,T2 a2)
		 */
		default R applyX(T1 a1, T2 a2, int a3) {
			return this.applyInt2Obj1Obj0(a3, a2, a1);
		}

		@Nullable
		// R applyInt2Obj1Obj0(int a3,T2 a2,T1 a1) ;
		default R applyInt2Obj1Obj0(int a3, T2 a2, T1 a1) {
			// return nestingApplyInt2Obj1Obj0(a3,a2,a1);
			try {
				return this.applyInt2Obj1Obj0X(a3, a2, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyInt2Obj1Obj0(int a3,T2 a2,T1 a1)
		 */
		R applyInt2Obj1Obj0X(int a3, T2 a2, T1 a1) throws Throwable;
	}

	// </editor-fold>

	/** Does nothing (LBiObjIntFunction) Function */
	public static <T1, T2, R> R produce(T1 a1, T2 a2, int a3) {
		return (R) Function4U.defaultObject;
	}

	/** Does nothing (LBiObjIntFunction.LObj0Int2Obj1Func) Function */
	public static <T1, T2, R> R produce(T1 a1, int a3, T2 a2) {
		return (R) Function4U.defaultObject;
	}

	/** Does nothing (LBiObjIntFunction.LInt2Obj0Obj1Func) Function */
	public static <T1, T2, R> R produce(int a3, T1 a1, T2 a2) {
		return (R) Function4U.defaultObject;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, C3> void forEach(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, aInt> ia3, C3 source3, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToIntFunction<Object> oiFunc3 = (LOiToIntFunction) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = oiFunc2.apply(source2, i);
			int a3 = oiFunc3.applyAsInt(source3, i);
			consumer.accept(this.apply(a1, a2, a3));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, C3> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, aInt> ia3, C3 source3, LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToIntFunction<Object> oiFunc3 = (LOiToIntFunction) ia3.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = oiFunc2.apply(source2, i);
			int a3 = oiFunc3.applyAsInt(source3, i);
			consumer.accept(this.apply(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2, C3> void iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, aInt> ia3, C3 source3, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		size = Integer.min(size, ia3.size(source3));
		LOiToIntFunction<Object> oiFunc3 = (LOiToIntFunction) ia3.getter();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = nextFunc2.apply(iterator2);
			int a3 = oiFunc3.applyAsInt(source3, i);
			consumer.accept(this.apply(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, I2, C3> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, aInt> ia3, C3 source3, LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		int size = ia3.size(source3);
		LOiToIntFunction<Object> oiFunc3 = (LOiToIntFunction) ia3.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && i < size) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = nextFunc2.apply(iterator2);
			int a3 = oiFunc3.applyAsInt(source3, i);
			consumer.accept(this.apply(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, C3, I3> void iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, aInt> sa3, C3 source3, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToIntFunction<Object> nextFunc3 = (LToIntFunction) sa3.supplier();
		int i = 0;
		while (i < size && testFunc3.test(iterator3)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = oiFunc2.apply(source2, i);
			int a3 = nextFunc3.applyAsInt(iterator3);
			consumer.accept(this.apply(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, C3, I3> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, aInt> sa3, C3 source3, LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToIntFunction<Object> nextFunc3 = (LToIntFunction) sa3.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size && testFunc3.test(iterator3)) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = oiFunc2.apply(source2, i);
			int a3 = nextFunc3.applyAsInt(iterator3);
			consumer.accept(this.apply(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2, C3, I3> void iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, aInt> sa3, C3 source3, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToIntFunction<Object> nextFunc3 = (LToIntFunction) sa3.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = nextFunc2.apply(iterator2);
			int a3 = nextFunc3.applyAsInt(iterator3);
			consumer.accept(this.apply(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C1, I1, C2, I2, C3, I3> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, aInt> sa3, C3 source3, LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToIntFunction<Object> nextFunc3 = (LToIntFunction) sa3.supplier();
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = nextFunc2.apply(iterator2);
			int a3 = nextFunc3.applyAsInt(iterator3);
			consumer.accept(this.apply(a1, a2, a3));
		}
	}

}
