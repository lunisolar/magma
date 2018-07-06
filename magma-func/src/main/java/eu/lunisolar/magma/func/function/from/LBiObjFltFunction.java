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
 * Non-throwing functional interface (lambda) LBiObjFltFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 3): T1 a1,T2 a2,float a3
 *
 * Co-domain: R
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiObjFltFunction<T1, T2, R> extends MetaFunction, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LBiObjFltFunction: R doApply(T1 a1,T2 a2,float a3)";

	@Nullable
	// R doApply(T1 a1,T2 a2,float a3) ;
	default R doApply(T1 a1, T2 a2, float a3) {
		// return nestingDoApply(a1,a2,a3);
		try {
			return this.doApplyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doApply(T1 a1,T2 a2,float a3)
	 */
	R doApplyX(T1 a1, T2 a2, float a3) throws Throwable;

	default R tupleApply(LBiObjFltTriple<T1, T2> args) {
		return doApply(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions according to the instructions. */
	default R handlingDoApply(T1 a1, T2 a2, float a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default R tryDoApply(T1 a1, T2 a2, float a3, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default R tryDoApply(T1 a1, T2 a2, float a3, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default R tryDoApplyThen(T1 a1, T2 a2, float a3, @Nonnull LFunction<Throwable, R> handler) {
		try {
			return this.doApplyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApply(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default R nestingDoApply(T1 a1, T2 a2, float a3) {
		try {
			return this.doApplyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default R shovingDoApply(T1 a1, T2 a2, float a3) {
		try {
			return this.doApplyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T1, T2, R> R handlingDoApply(T1 a1, T2 a2, float a3, LBiObjFltFunction<T1, T2, R> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApply(a1, a2, a3, handling);
	}

	static <T1, T2, R> R tryDoApply(T1 a1, T2 a2, float a3, LBiObjFltFunction<T1, T2, R> func) {
		return tryDoApply(a1, a2, a3, func, null);
	}

	static <T1, T2, R> R tryDoApply(T1 a1, T2 a2, float a3, LBiObjFltFunction<T1, T2, R> func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApply(a1, a2, a3, exceptionFactory, newMessage, messageParams);
	}

	static <T1, T2, R> R tryDoApply(T1 a1, T2 a2, float a3, LBiObjFltFunction<T1, T2, R> func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApply(a1, a2, a3, exceptionFactory);
	}

	static <T1, T2, R> R tryDoApplyThen(T1 a1, T2 a2, float a3, LBiObjFltFunction<T1, T2, R> func, @Nonnull LFunction<Throwable, R> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyThen(a1, a2, a3, handler);
	}

	default R failSafeDoApply(T1 a1, T2 a2, float a3, @Nonnull LBiObjFltFunction<T1, T2, R> failSafe) {
		try {
			return doApply(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApply(a1, a2, a3);
		}
	}

	static <T1, T2, R> R failSafeDoApply(T1 a1, T2 a2, float a3, LBiObjFltFunction<T1, T2, R> func, @Nonnull LBiObjFltFunction<T1, T2, R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApply(a1, a2, a3);
		} else {
			return func.failSafeDoApply(a1, a2, a3, failSafe);
		}
	}

	static <T1, T2, R> LBiObjFltFunction<T1, T2, R> failSafeBiObjFltFunc(LBiObjFltFunction<T1, T2, R> func, @Nonnull LBiObjFltFunction<T1, T2, R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeDoApply(a1, a2, a3, func, failSafe);
	}

	LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullDoApply() method cannot be null (" + DESCRIPTION + ").";

	/** Function call that ensures the result is not null */
	@Nonnull
	default R nonNullDoApply(T1 a1, T2 a2, float a3) {
		return Null.requireNonNull(doApply(a1, a2, a3), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiObjFltFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, R> void fromTo(int min_i, int max_i, T1 a1, T2 a2, float a3, LBiObjFltFunction<T1, T2, R> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.doApply(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.doApply(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, R> void fromTill(int min_i, int max_i, T1 a1, T2 a2, float a3, LBiObjFltFunction<T1, T2, R> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i < max_i; i++) {
				func.doApply(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.doApply(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, R> void times(int max_i, T1 a1, T2 a2, float a3, LBiObjFltFunction<T1, T2, R> func) {
		fromTill(0, max_i, a1, a2, a3, func);
	}

	public default LObjFltFunction<T2, R> lShrink(LObjFltFunction<T2, T1> left) {
		return (a2, a3) -> doApply(left.doApply(a2, a3), a2, a3);
	}

	public default LObjFltFunction<T2, R> lShrinkc(T1 a1) {
		return (a2, a3) -> doApply(a1, a2, a3);
	}

	public static <T2, R, T1> LObjFltFunction<T2, R> lShrinked(LObjFltFunction<T2, T1> left, LBiObjFltFunction<T1, T2, R> func) {
		return func.lShrink(left);
	}

	public static <T2, R, T1> LObjFltFunction<T2, R> lShrinkedc(T1 a1, LBiObjFltFunction<T1, T2, R> func) {
		return func.lShrinkc(a1);
	}

	public default LBiFunction<T1, T2, R> rShrink(LToFltBiFunction<T1, T2> right) {
		return (a1, a2) -> doApply(a1, a2, right.doApplyAsFlt(a1, a2));
	}

	public default LBiFunction<T1, T2, R> rShrinkc(float a3) {
		return (a1, a2) -> doApply(a1, a2, a3);
	}

	public static <T1, T2, R> LBiFunction<T1, T2, R> rShrinked(LToFltBiFunction<T1, T2> right, LBiObjFltFunction<T1, T2, R> func) {
		return func.rShrink(right);
	}

	public static <T1, T2, R> LBiFunction<T1, T2, R> rShrinkedc(float a3, LBiObjFltFunction<T1, T2, R> func) {
		return func.rShrinkc(a3);
	}

	/**  */
	public static <T1, T2, R> LBiObjFltFunction<T1, T2, R> uncurryBiObjFltFunc(LFunction<T1, LFunction<T2, LFltFunction<R>>> func) {
		return (T1 a1, T2 a2, float a3) -> func.doApply(a1).doApply(a2).doApply(a3);
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplier<R> captureBiObjFltFunc(T1 a1, T2 a2, float a3) {
		return () -> this.doApply(a1, a2, a3);
	}

	/** Creates function that always returns the same value. */
	static <T1, T2, R> LBiObjFltFunction<T1, T2, R> constant(R r) {
		return (a1, a2, a3) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T1, T2, R> LBiObjFltFunction<T1, T2, R> apply1st(@Nonnull LFunction<T1, R> func) {
		return (a1, a2, a3) -> func.doApply(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T1, T2, R> LBiObjFltFunction<T1, T2, R> apply2nd(@Nonnull LFunction<T2, R> func) {
		return (a1, a2, a3) -> func.doApply(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T1, T2, R> LBiObjFltFunction<T1, T2, R> apply3rd(@Nonnull LFltFunction<R> func) {
		return (a1, a2, a3) -> func.doApply(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, R> LBiObjFltFunction<T1, T2, R> biObjFltFunc(final @Nonnull LBiObjFltFunction<T1, T2, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T1, T2, R> LBiObjFltFunction<T1, T2, R> recursive(final @Nonnull LFunction<LBiObjFltFunction<T1, T2, R>, LBiObjFltFunction<T1, T2, R>> selfLambda) {
		final LBiObjFltFunctionSingle<T1, T2, R> single = new LBiObjFltFunctionSingle();
		LBiObjFltFunction<T1, T2, R> func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LBiObjFltFunctionSingle<T1, T2, R> implements LSingle<LBiObjFltFunction<T1, T2, R>>, LBiObjFltFunction<T1, T2, R> {
		private LBiObjFltFunction<T1, T2, R> target = null;

		@Override
		public R doApplyX(T1 a1, T2 a2, float a3) throws Throwable {
			return target.doApplyX(a1, a2, a3);
		}

		@Override
		public LBiObjFltFunction<T1, T2, R> value() {
			return target;
		}
	}

	@Nonnull
	static <T1, T2, R> LBiObjFltFunction<T1, T2, R> biObjFltFuncThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2, a3) -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static <T1, T2, R> LBiObjFltFunction<T1, T2, R> biObjFltFuncThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2, a3) -> {
			throw exceptionFactory.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, R> LObjFltObj1Func<T1, T2, R> objFltObj1Func(final @Nonnull LObjFltObj1Func<T1, T2, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1, R> LObj1Obj0FltFunc<T2, T1, R> obj1Obj0FltFunc(final @Nonnull LObj1Obj0FltFunc<T2, T1, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1, R> LObj1FltObj0Func<T2, T1, R> obj1FltObj0Func(final @Nonnull LObj1FltObj0Func<T2, T1, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, R> LFltObj0Obj1Func<T1, T2, R> fltObj0Obj1Func(final @Nonnull LFltObj0Obj1Func<T1, T2, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1, R> LFltObjObj0Func<T2, T1, R> fltObjObj0Func(final @Nonnull LFltObjObj0Func<T2, T1, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T1, T2, R> R call(T1 a1, T2 a2, float a3, final @Nonnull LBiObjFltFunction<T1, T2, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApply(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produce). */
	@Nonnull
	static <T1, T2, R> LBiObjFltFunction<T1, T2, R> safe() {
		return LBiObjFltFunction::produce;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T1, T2, R> LSupplier<LBiObjFltFunction<T1, T2, R>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T1, T2, R> LBiObjFltFunction<T1, T2, R> safe(final @Nullable LBiObjFltFunction<T1, T2, R> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T1, T2, R> LSupplier<LBiObjFltFunction<T1, T2, R>> safeSupplier(final @Nullable LSupplier<LBiObjFltFunction<T1, T2, R>> supplier) {
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
	default <V1, V2> LBiObjFltFunction<V1, V2, R> biObjFltFuncComposeFlt(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LFltUnaryOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.doApply(before1.doApply(v1), before2.doApply(v2), before3.doApplyAsFlt(v3));
	}

	public static <V1, V2, T1, T2, R> LBiObjFltFunction<V1, V2, R> composedFlt(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LFltUnaryOperator before3,
			LBiObjFltFunction<T1, T2, R> after) {
		return after.biObjFltFuncComposeFlt(before1, before2, before3);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriFunction<V1, V2, V3, R> biObjFltFuncCompose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LToFltFunction<? super V3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.doApply(before1.doApply(v1), before2.doApply(v2), before3.doApplyAsFlt(v3));
	}

	public static <V1, V2, V3, T1, T2, R> LTriFunction<V1, V2, V3, R> composed(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LToFltFunction<? super V3> before3,
			LBiObjFltFunction<T1, T2, R> after) {
		return after.biObjFltFuncCompose(before1, before2, before3);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiObjFltFunction<T1, T2, V> then(@Nonnull LFunction<? super R, ? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.doApply(this.doApply(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiObjFltConsumer<T1, T2> thenConsume(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.doAccept(this.doApply(a1, a2, a3));
	}

	@Nonnull
	default LBiObjFltFunction<T1, T2, R> before(@Nonnull LBiObjFltConsumer<? super T1, ? super T2> before) {
		Null.nonNullArg(before, "before");
		return (a1, a2, a3) -> {
			before.doAccept(a1, a2, a3);
			return this.doApply(a1, a2, a3);
		};
	}

	@Nonnull
	default LBiObjFltFunction<T1, T2, R> after(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> {
			R result = this.doApply(a1, a2, a3);
			after.doAccept(result);
			return result;
		};
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiObjFltPredicate<T1, T2> thenToBool(@Nonnull LPredicate<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.doTest(this.doApply(a1, a2, a3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBiObjFltFunction<T1, T2, R> nestingBiObjFltFunc() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiObjFltFunction<T1, T2, R> shovingBiObjFltFunc() {
		return this;
	}

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LBiObjFltFunction<T1, T2, R> nonNullBiObjFltFunc() {
		return this::nonNullDoApply;
	}

	// <editor-fold desc="interface variants">

	/** Permutation of LBiObjFltFunction for method references. */
	@FunctionalInterface
	interface LObjFltObj1Func<T1, T2, R> extends LBiObjFltFunction<T1, T2, R> {
		@Nullable
		R doApplyObjFltObj1(T1 a1, float a3, T2 a2);

		@Override
		default R doApplyX(T1 a1, T2 a2, float a3) {
			return this.doApplyObjFltObj1(a1, a3, a2);
		}
	}

	/** Permutation of LBiObjFltFunction for method references. */
	@FunctionalInterface
	interface LObj1Obj0FltFunc<T2, T1, R> extends LBiObjFltFunction<T1, T2, R> {
		@Nullable
		R doApplyObj1Obj0Flt(T2 a2, T1 a1, float a3);

		@Override
		default R doApplyX(T1 a1, T2 a2, float a3) {
			return this.doApplyObj1Obj0Flt(a2, a1, a3);
		}
	}

	/** Permutation of LBiObjFltFunction for method references. */
	@FunctionalInterface
	interface LObj1FltObj0Func<T2, T1, R> extends LBiObjFltFunction<T1, T2, R> {
		@Nullable
		R doApplyObj1FltObj0(T2 a2, float a3, T1 a1);

		@Override
		default R doApplyX(T1 a1, T2 a2, float a3) {
			return this.doApplyObj1FltObj0(a2, a3, a1);
		}
	}

	/** Permutation of LBiObjFltFunction for method references. */
	@FunctionalInterface
	interface LFltObj0Obj1Func<T1, T2, R> extends LBiObjFltFunction<T1, T2, R> {
		@Nullable
		R doApplyFltObj0Obj1(float a3, T1 a1, T2 a2);

		@Override
		default R doApplyX(T1 a1, T2 a2, float a3) {
			return this.doApplyFltObj0Obj1(a3, a1, a2);
		}
	}

	/** Permutation of LBiObjFltFunction for method references. */
	@FunctionalInterface
	interface LFltObjObj0Func<T2, T1, R> extends LBiObjFltFunction<T1, T2, R> {
		@Nullable
		R doApplyFltObjObj0(float a3, T2 a2, T1 a1);

		@Override
		default R doApplyX(T1 a1, T2 a2, float a3) {
			return this.doApplyFltObjObj0(a3, a2, a1);
		}
	}

	// </editor-fold>

	/** Does nothing (LBiObjFltFunction) Function */
	public static <T1, T2, R> R produce(T1 a1, T2 a2, float a3) {
		return (R) Function4U.defaultObject;
	}

	/** Does nothing (LBiObjFltFunction.LObjFltObj1Func) Function */
	public static <T1, T2, R> R produce(T1 a1, float a3, T2 a2) {
		return (R) Function4U.defaultObject;
	}

	/** Does nothing (LBiObjFltFunction.LFltObj0Obj1Func) Function */
	public static <T1, T2, R> R produce(float a3, T1 a1, T2 a2) {
		return (R) Function4U.defaultObject;
	}

	// MAP: FOR, [SourcePurpose{arg=T1 a1, type=IA}, SourcePurpose{arg=T2 a2, type=IA}, SourcePurpose{arg=float a3, type=IA}, SourcePurpose{arg=LConsumer<?
	// super R> consumer, type=CONST}]
	default <C1, C2, C3> void forEach(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, aFloat> ia3, C3 source3, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToFltFunction<Object> oiFunc3 = (LOiToFltFunction) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			T1 a1 = oiFunc1.doApply(source1, i);
			T2 a2 = oiFunc2.doApply(source2, i);
			float a3 = oiFunc3.doApplyAsFlt(source3, i);
			consumer.doAccept(this.doApply(a1, a2, a3));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T1 a1, type=SA}, SourcePurpose{arg=T2 a2, type=IA}, SourcePurpose{arg=float a3, type=IA}, SourcePurpose{arg=LConsumer<?
	// super R> consumer, type=CONST}]
	default <C1, I1, C2, C3> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, aFloat> ia3, C3 source3, LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToFltFunction<Object> oiFunc3 = (LOiToFltFunction) ia3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size) {
			T1 a1 = nextFunc1.doApply(iterator1);
			T2 a2 = oiFunc2.doApply(source2, i);
			float a3 = oiFunc3.doApplyAsFlt(source3, i);
			consumer.doAccept(this.doApply(a1, a2, a3));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T1 a1, type=IA}, SourcePurpose{arg=T2 a2, type=SA}, SourcePurpose{arg=float a3, type=IA}, SourcePurpose{arg=LConsumer<?
	// super R> consumer, type=CONST}]
	default <C1, C2, I2, C3> void iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, aFloat> ia3, C3 source3, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToFltFunction<Object> oiFunc3 = (LOiToFltFunction) ia3.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2)) {
			T1 a1 = oiFunc1.doApply(source1, i);
			T2 a2 = nextFunc2.doApply(iterator2);
			float a3 = oiFunc3.doApplyAsFlt(source3, i);
			consumer.doAccept(this.doApply(a1, a2, a3));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T1 a1, type=SA}, SourcePurpose{arg=T2 a2, type=SA}, SourcePurpose{arg=float a3, type=IA}, SourcePurpose{arg=LConsumer<?
	// super R> consumer, type=CONST}]
	default <C1, I1, C2, I2, C3> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, aFloat> ia3, C3 source3, LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.getter();
		int size = ia3.size(source3);
		LOiToFltFunction<Object> oiFunc3 = (LOiToFltFunction) ia3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2) && i < size) {
			T1 a1 = nextFunc1.doApply(iterator1);
			T2 a2 = nextFunc2.doApply(iterator2);
			float a3 = oiFunc3.doApplyAsFlt(source3, i);
			consumer.doAccept(this.doApply(a1, a2, a3));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T1 a1, type=IA}, SourcePurpose{arg=T2 a2, type=IA}, SourcePurpose{arg=float a3, type=SA}, SourcePurpose{arg=LConsumer<?
	// super R> consumer, type=CONST}]
	default <C1, C2, C3, I3> void iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, aFloat> sa3, C3 source3, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToFltFunction<Object> nextFunc3 = (LToFltFunction) sa3.getter();
		int i = 0;
		while (i < size && testFunc3.doTest(iterator3)) {
			T1 a1 = oiFunc1.doApply(source1, i);
			T2 a2 = oiFunc2.doApply(source2, i);
			float a3 = nextFunc3.doApplyAsFlt(iterator3);
			consumer.doAccept(this.doApply(a1, a2, a3));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T1 a1, type=SA}, SourcePurpose{arg=T2 a2, type=IA}, SourcePurpose{arg=float a3, type=SA}, SourcePurpose{arg=LConsumer<?
	// super R> consumer, type=CONST}]
	default <C1, I1, C2, C3, I3> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, aFloat> sa3, C3 source3, LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToFltFunction<Object> nextFunc3 = (LToFltFunction) sa3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size && testFunc3.doTest(iterator3)) {
			T1 a1 = nextFunc1.doApply(iterator1);
			T2 a2 = oiFunc2.doApply(source2, i);
			float a3 = nextFunc3.doApplyAsFlt(iterator3);
			consumer.doAccept(this.doApply(a1, a2, a3));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T1 a1, type=IA}, SourcePurpose{arg=T2 a2, type=SA}, SourcePurpose{arg=float a3, type=SA}, SourcePurpose{arg=LConsumer<?
	// super R> consumer, type=CONST}]
	default <C1, C2, I2, C3, I3> void iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, aFloat> sa3, C3 source3, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToFltFunction<Object> nextFunc3 = (LToFltFunction) sa3.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2) && testFunc3.doTest(iterator3)) {
			T1 a1 = oiFunc1.doApply(source1, i);
			T2 a2 = nextFunc2.doApply(iterator2);
			float a3 = nextFunc3.doApplyAsFlt(iterator3);
			consumer.doAccept(this.doApply(a1, a2, a3));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T1 a1, type=SA}, SourcePurpose{arg=T2 a2, type=SA}, SourcePurpose{arg=float a3, type=SA}, SourcePurpose{arg=LConsumer<?
	// super R> consumer, type=CONST}]
	default <C1, I1, C2, I2, C3, I3> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, aFloat> sa3, C3 source3, LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToFltFunction<Object> nextFunc3 = (LToFltFunction) sa3.getter();
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2) && testFunc3.doTest(iterator3)) {
			T1 a1 = nextFunc1.doApply(iterator1);
			T2 a2 = nextFunc2.doApply(iterator2);
			float a3 = nextFunc3.doApplyAsFlt(iterator3);
			consumer.doAccept(this.doApply(a1, a2, a3));
		}
	}

}
