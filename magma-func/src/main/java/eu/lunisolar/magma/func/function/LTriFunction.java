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

package eu.lunisolar.magma.func.function;

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
 * Non-throwing functional interface (lambda) LTriFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 3): T1 a1,T2 a2,T3 a3
 *
 * Co-domain: R
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LTriFunction<T1, T2, T3, R> extends MetaFunction, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LTriFunction: R doApply(T1 a1,T2 a2,T3 a3)";

	@Nullable
	// R doApply(T1 a1,T2 a2,T3 a3) ;
	default R doApply(T1 a1, T2 a2, T3 a3) {
		// return nestingDoApply(a1,a2,a3);
		try {
			return this.doApplyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doApply(T1 a1,T2 a2,T3 a3)
	 */
	R doApplyX(T1 a1, T2 a2, T3 a3) throws Throwable;

	default R tupleApply(LTriple<T1, T2, T3> args) {
		return doApply(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions according to the instructions. */
	default R handlingDoApply(T1 a1, T2 a2, T3 a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default R tryDoApply(T1 a1, T2 a2, T3 a3, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default R tryDoApply(T1 a1, T2 a2, T3 a3, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default R tryDoApplyThen(T1 a1, T2 a2, T3 a3, @Nonnull LFunction<Throwable, R> handler) {
		try {
			return this.doApplyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApply(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default R nestingDoApply(T1 a1, T2 a2, T3 a3) {
		try {
			return this.doApplyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default R shovingDoApply(T1 a1, T2 a2, T3 a3) {
		try {
			return this.doApplyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T1, T2, T3, R> R handlingDoApply(T1 a1, T2 a2, T3 a3, LTriFunction<T1, T2, T3, R> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApply(a1, a2, a3, handling);
	}

	static <T1, T2, T3, R> R tryDoApply(T1 a1, T2 a2, T3 a3, LTriFunction<T1, T2, T3, R> func) {
		return tryDoApply(a1, a2, a3, func, null);
	}

	static <T1, T2, T3, R> R tryDoApply(T1 a1, T2 a2, T3 a3, LTriFunction<T1, T2, T3, R> func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApply(a1, a2, a3, exceptionFactory, newMessage, messageParams);
	}

	static <T1, T2, T3, R> R tryDoApply(T1 a1, T2 a2, T3 a3, LTriFunction<T1, T2, T3, R> func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApply(a1, a2, a3, exceptionFactory);
	}

	static <T1, T2, T3, R> R tryDoApplyThen(T1 a1, T2 a2, T3 a3, LTriFunction<T1, T2, T3, R> func, @Nonnull LFunction<Throwable, R> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyThen(a1, a2, a3, handler);
	}

	default R failSafeDoApply(T1 a1, T2 a2, T3 a3, @Nonnull LTriFunction<T1, T2, T3, R> failSafe) {
		try {
			return doApply(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApply(a1, a2, a3);
		}
	}

	static <T1, T2, T3, R> R failSafeDoApply(T1 a1, T2 a2, T3 a3, LTriFunction<T1, T2, T3, R> func, @Nonnull LTriFunction<T1, T2, T3, R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApply(a1, a2, a3);
		} else {
			return func.failSafeDoApply(a1, a2, a3, failSafe);
		}
	}

	static <T1, T2, T3, R> LTriFunction<T1, T2, T3, R> failSafeTriFunc(LTriFunction<T1, T2, T3, R> func, @Nonnull LTriFunction<T1, T2, T3, R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeDoApply(a1, a2, a3, func, failSafe);
	}

	LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullDoApply() method cannot be null (" + DESCRIPTION + ").";

	/** Function call that ensures the result is not null */
	@Nonnull
	default R nonNullDoApply(T1 a1, T2 a2, T3 a3) {
		return Null.requireNonNull(doApply(a1, a2, a3), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LTriFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, T3, R> void fromTo(int min_i, int max_i, T1 a1, T2 a2, T3 a3, LTriFunction<T1, T2, T3, R> func) {
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
	public static <T1, T2, T3, R> void fromTill(int min_i, int max_i, T1 a1, T2 a2, T3 a3, LTriFunction<T1, T2, T3, R> func) {
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
	public static <T1, T2, T3, R> void times(int max_i, T1 a1, T2 a2, T3 a3, LTriFunction<T1, T2, T3, R> func) {
		fromTill(0, max_i, a1, a2, a3, func);
	}

	public default LBiFunction<T2, T3, R> lShrink(LBiFunction<T2, T3, T1> left) {
		return (a2, a3) -> doApply(left.doApply(a2, a3), a2, a3);
	}

	public default LBiFunction<T2, T3, R> lShrinkc(T1 a1) {
		return (a2, a3) -> doApply(a1, a2, a3);
	}

	public static <T2, T3, R, T1> LBiFunction<T2, T3, R> lShrinked(LBiFunction<T2, T3, T1> left, LTriFunction<T1, T2, T3, R> func) {
		return func.lShrink(left);
	}

	public static <T2, T3, R, T1> LBiFunction<T2, T3, R> lShrinkedc(T1 a1, LTriFunction<T1, T2, T3, R> func) {
		return func.lShrinkc(a1);
	}

	public default LBiFunction<T1, T2, R> rShrink(LBiFunction<T1, T2, T3> right) {
		return (a1, a2) -> doApply(a1, a2, right.doApply(a1, a2));
	}

	public default LBiFunction<T1, T2, R> rShrinkc(T3 a3) {
		return (a1, a2) -> doApply(a1, a2, a3);
	}

	public static <T1, T2, R, T3> LBiFunction<T1, T2, R> rShrinked(LBiFunction<T1, T2, T3> right, LTriFunction<T1, T2, T3, R> func) {
		return func.rShrink(right);
	}

	public static <T1, T2, R, T3> LBiFunction<T1, T2, R> rShrinkedc(T3 a3, LTriFunction<T1, T2, T3, R> func) {
		return func.rShrinkc(a3);
	}

	/**  */
	public static <T1, T2, T3, R> LTriFunction<T1, T2, T3, R> uncurryTriFunc(LFunction<T1, LFunction<T2, LFunction<T3, R>>> func) {
		return (T1 a1, T2 a2, T3 a3) -> func.doApply(a1).doApply(a2).doApply(a3);
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplier<R> captureTriFunc(T1 a1, T2 a2, T3 a3) {
		return () -> this.doApply(a1, a2, a3);
	}

	/** Creates function that always returns the same value. */
	static <T1, T2, T3, R> LTriFunction<T1, T2, T3, R> constant(R r) {
		return (a1, a2, a3) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T1, T2, T3, R> LTriFunction<T1, T2, T3, R> apply1st(@Nonnull LFunction<T1, R> func) {
		return (a1, a2, a3) -> func.doApply(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T1, T2, T3, R> LTriFunction<T1, T2, T3, R> apply2nd(@Nonnull LFunction<T2, R> func) {
		return (a1, a2, a3) -> func.doApply(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T1, T2, T3, R> LTriFunction<T1, T2, T3, R> apply3rd(@Nonnull LFunction<T3, R> func) {
		return (a1, a2, a3) -> func.doApply(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, T3, R> LTriFunction<T1, T2, T3, R> triFunc(final @Nonnull LTriFunction<T1, T2, T3, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T1, T2, T3, R> LTriFunction<T1, T2, T3, R> recursive(final @Nonnull LFunction<LTriFunction<T1, T2, T3, R>, LTriFunction<T1, T2, T3, R>> selfLambda) {
		final LTriFunctionSingle<T1, T2, T3, R> single = new LTriFunctionSingle();
		LTriFunction<T1, T2, T3, R> func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LTriFunctionSingle<T1, T2, T3, R> implements LSingle<LTriFunction<T1, T2, T3, R>>, LTriFunction<T1, T2, T3, R> {
		private LTriFunction<T1, T2, T3, R> target = null;

		@Override
		public R doApplyX(T1 a1, T2 a2, T3 a3) throws Throwable {
			return target.doApplyX(a1, a2, a3);
		}

		@Override
		public LTriFunction<T1, T2, T3, R> value() {
			return target;
		}
	}

	@Nonnull
	static <T1, T2, T3, R> LTriFunction<T1, T2, T3, R> triFuncThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2, a3) -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static <T1, T2, T3, R> LTriFunction<T1, T2, T3, R> triFuncThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2, a3) -> {
			throw exceptionFactory.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T3, T2, R> LObjObj2Obj1Func<T1, T3, T2, R> objObj2Obj1Func(final @Nonnull LObjObj2Obj1Func<T1, T3, T2, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1, T3, R> LObj1BiObjFunc<T2, T1, T3, R> obj1BiObjFunc(final @Nonnull LObj1BiObjFunc<T2, T1, T3, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T3, T1, R> LObj1Obj2Obj0Func<T2, T3, T1, R> obj1Obj2Obj0Func(final @Nonnull LObj1Obj2Obj0Func<T2, T3, T1, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T3, T1, T2, R> LObj2Obj0Obj1Func<T3, T1, T2, R> obj2Obj0Obj1Func(final @Nonnull LObj2Obj0Obj1Func<T3, T1, T2, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T3, T2, T1, R> LBiObjObj0Func<T3, T2, T1, R> biObjObj0Func(final @Nonnull LBiObjObj0Func<T3, T2, T1, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T1, T2, T3, R> R call(T1 a1, T2 a2, T3 a3, final @Nonnull LTriFunction<T1, T2, T3, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApply(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produce). */
	@Nonnull
	static <T1, T2, T3, R> LTriFunction<T1, T2, T3, R> safe() {
		return LTriFunction::produce;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T1, T2, T3, R> LSupplier<LTriFunction<T1, T2, T3, R>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T1, T2, T3, R> LTriFunction<T1, T2, T3, R> safe(final @Nullable LTriFunction<T1, T2, T3, R> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T1, T2, T3, R> LSupplier<LTriFunction<T1, T2, T3, R>> safeSupplier(final @Nullable LSupplier<LTriFunction<T1, T2, T3, R>> supplier) {
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
	default <V1, V2, V3> LTriFunction<V1, V2, V3, R> triFuncCompose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LFunction<? super V3, ? extends T3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.doApply(before1.doApply(v1), before2.doApply(v2), before3.doApply(v3));
	}

	public static <V1, V2, V3, T1, T2, T3, R> LTriFunction<V1, V2, V3, R> composed(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2,
			@Nonnull final LFunction<? super V3, ? extends T3> before3, LTriFunction<T1, T2, T3, R> after) {
		return after.triFuncCompose(before1, before2, before3);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LTriFunction<T1, T2, T3, V> then(@Nonnull LFunction<? super R, ? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.doApply(this.doApply(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LTriConsumer<T1, T2, T3> thenConsume(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.doAccept(this.doApply(a1, a2, a3));
	}

	@Nonnull
	default LTriFunction<T1, T2, T3, R> before(@Nonnull LTriConsumer<? super T1, ? super T2, ? super T3> before) {
		Null.nonNullArg(before, "before");
		return (a1, a2, a3) -> {
			before.doAccept(a1, a2, a3);
			return this.doApply(a1, a2, a3);
		};
	}

	@Nonnull
	default LTriFunction<T1, T2, T3, R> after(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> {
			R result = this.doApply(a1, a2, a3);
			after.doAccept(result);
			return result;
		};
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToIntTriFunction<T1, T2, T3> thenToInt(@Nonnull LToIntFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.doApplyAsInt(this.doApply(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LTriPredicate<T1, T2, T3> thenToBool(@Nonnull LPredicate<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.doTest(this.doApply(a1, a2, a3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LTriFunction<T1, T2, T3, R> nestingTriFunc() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LTriFunction<T1, T2, T3, R> shovingTriFunc() {
		return this;
	}

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LTriFunction<T1, T2, T3, R> nonNullTriFunc() {
		return this::nonNullDoApply;
	}

	// <editor-fold desc="interface variants">

	/** Permutation of LTriFunction for method references. */
	@FunctionalInterface
	interface LObjObj2Obj1Func<T1, T3, T2, R> extends LTriFunction<T1, T2, T3, R> {
		@Nullable
		R doApplyObjObj2Obj1(T1 a1, T3 a3, T2 a2);

		@Override
		default R doApplyX(T1 a1, T2 a2, T3 a3) {
			return this.doApplyObjObj2Obj1(a1, a3, a2);
		}
	}

	/** Permutation of LTriFunction for method references. */
	@FunctionalInterface
	interface LObj1BiObjFunc<T2, T1, T3, R> extends LTriFunction<T1, T2, T3, R> {
		@Nullable
		R doApplyObj1BiObj(T2 a2, T1 a1, T3 a3);

		@Override
		default R doApplyX(T1 a1, T2 a2, T3 a3) {
			return this.doApplyObj1BiObj(a2, a1, a3);
		}
	}

	/** Permutation of LTriFunction for method references. */
	@FunctionalInterface
	interface LObj1Obj2Obj0Func<T2, T3, T1, R> extends LTriFunction<T1, T2, T3, R> {
		@Nullable
		R doApplyObj1Obj2Obj0(T2 a2, T3 a3, T1 a1);

		@Override
		default R doApplyX(T1 a1, T2 a2, T3 a3) {
			return this.doApplyObj1Obj2Obj0(a2, a3, a1);
		}
	}

	/** Permutation of LTriFunction for method references. */
	@FunctionalInterface
	interface LObj2Obj0Obj1Func<T3, T1, T2, R> extends LTriFunction<T1, T2, T3, R> {
		@Nullable
		R doApplyObj2Obj0Obj1(T3 a3, T1 a1, T2 a2);

		@Override
		default R doApplyX(T1 a1, T2 a2, T3 a3) {
			return this.doApplyObj2Obj0Obj1(a3, a1, a2);
		}
	}

	/** Permutation of LTriFunction for method references. */
	@FunctionalInterface
	interface LBiObjObj0Func<T3, T2, T1, R> extends LTriFunction<T1, T2, T3, R> {
		@Nullable
		R doApplyBiObjObj0(T3 a3, T2 a2, T1 a1);

		@Override
		default R doApplyX(T1 a1, T2 a2, T3 a3) {
			return this.doApplyBiObjObj0(a3, a2, a1);
		}
	}

	// </editor-fold>

	/** Does nothing (LTriFunction) Function */
	public static <T1, T2, T3, R> R produce(T1 a1, T2 a2, T3 a3) {
		return (R) Function4U.defaultObject;
	}

	// MAP: FOR, [SourcePurpose{arg=T1 a1, type=IA}, SourcePurpose{arg=T2 a2, type=IA}, SourcePurpose{arg=T3 a3, type=IA}, SourcePurpose{arg=LConsumer<? super
	// R> consumer, type=CONST}]
	default <C1, C2, C3> void forEach(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			T1 a1 = oiFunc1.doApply(source1, i);
			T2 a2 = oiFunc2.doApply(source2, i);
			T3 a3 = oiFunc3.doApply(source3, i);
			consumer.doAccept(this.doApply(a1, a2, a3));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T1 a1, type=SA}, SourcePurpose{arg=T2 a2, type=IA}, SourcePurpose{arg=T3 a3, type=IA}, SourcePurpose{arg=LConsumer<? super
	// R> consumer, type=CONST}]
	default <C1, I1, C2, C3> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size) {
			T1 a1 = nextFunc1.doApply(iterator1);
			T2 a2 = oiFunc2.doApply(source2, i);
			T3 a3 = oiFunc3.doApply(source3, i);
			consumer.doAccept(this.doApply(a1, a2, a3));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T1 a1, type=IA}, SourcePurpose{arg=T2 a2, type=SA}, SourcePurpose{arg=T3 a3, type=IA}, SourcePurpose{arg=LConsumer<? super
	// R> consumer, type=CONST}]
	default <C1, C2, I2, C3> void iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2)) {
			T1 a1 = oiFunc1.doApply(source1, i);
			T2 a2 = nextFunc2.doApply(iterator2);
			T3 a3 = oiFunc3.doApply(source3, i);
			consumer.doAccept(this.doApply(a1, a2, a3));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T1 a1, type=SA}, SourcePurpose{arg=T2 a2, type=SA}, SourcePurpose{arg=T3 a3, type=IA}, SourcePurpose{arg=LConsumer<? super
	// R> consumer, type=CONST}]
	default <C1, I1, C2, I2, C3> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.getter();
		int size = ia3.size(source3);
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2) && i < size) {
			T1 a1 = nextFunc1.doApply(iterator1);
			T2 a2 = nextFunc2.doApply(iterator2);
			T3 a3 = oiFunc3.doApply(source3, i);
			consumer.doAccept(this.doApply(a1, a2, a3));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T1 a1, type=IA}, SourcePurpose{arg=T2 a2, type=IA}, SourcePurpose{arg=T3 a3, type=SA}, SourcePurpose{arg=LConsumer<? super
	// R> consumer, type=CONST}]
	default <C1, C2, C3, I3> void iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.getter();
		int i = 0;
		while (i < size && testFunc3.doTest(iterator3)) {
			T1 a1 = oiFunc1.doApply(source1, i);
			T2 a2 = oiFunc2.doApply(source2, i);
			T3 a3 = nextFunc3.doApply(iterator3);
			consumer.doAccept(this.doApply(a1, a2, a3));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T1 a1, type=SA}, SourcePurpose{arg=T2 a2, type=IA}, SourcePurpose{arg=T3 a3, type=SA}, SourcePurpose{arg=LConsumer<? super
	// R> consumer, type=CONST}]
	default <C1, I1, C2, C3, I3> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size && testFunc3.doTest(iterator3)) {
			T1 a1 = nextFunc1.doApply(iterator1);
			T2 a2 = oiFunc2.doApply(source2, i);
			T3 a3 = nextFunc3.doApply(iterator3);
			consumer.doAccept(this.doApply(a1, a2, a3));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T1 a1, type=IA}, SourcePurpose{arg=T2 a2, type=SA}, SourcePurpose{arg=T3 a3, type=SA}, SourcePurpose{arg=LConsumer<? super
	// R> consumer, type=CONST}]
	default <C1, C2, I2, C3, I3> void iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2) && testFunc3.doTest(iterator3)) {
			T1 a1 = oiFunc1.doApply(source1, i);
			T2 a2 = nextFunc2.doApply(iterator2);
			T3 a3 = nextFunc3.doApply(iterator3);
			consumer.doAccept(this.doApply(a1, a2, a3));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T1 a1, type=SA}, SourcePurpose{arg=T2 a2, type=SA}, SourcePurpose{arg=T3 a3, type=SA}, SourcePurpose{arg=LConsumer<? super
	// R> consumer, type=CONST}]
	default <C1, I1, C2, I2, C3, I3> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.getter();
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2) && testFunc3.doTest(iterator3)) {
			T1 a1 = nextFunc1.doApply(iterator1);
			T2 a2 = nextFunc2.doApply(iterator2);
			T3 a3 = nextFunc3.doApply(iterator3);
			consumer.doAccept(this.doApply(a1, a2, a3));
		}
	}

}
