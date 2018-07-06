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
 * Non-throwing functional interface (lambda) LBiFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 2): T1 a1,T2 a2
 *
 * Co-domain: R
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiFunction<T1, T2, R> extends BiFunction<T1, T2, R>, MetaFunction, MetaInterface.NonThrowing { // NOSONAR

	String DESCRIPTION = "LBiFunction: R doApply(T1 a1,T2 a2)";

	/**
	 * Default implementation for JRE method that calls exception nesting method.
	 * @deprecated Calling this method via LBiFunction interface should be discouraged.
	 */
	@Override
	@Deprecated
	default R apply(T1 a1, T2 a2) {
		return this.doApply(a1, a2);
	}

	@Nullable
	// R doApply(T1 a1,T2 a2) ;
	default R doApply(T1 a1, T2 a2) {
		// return nestingDoApply(a1,a2);
		try {
			return this.doApplyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doApply(T1 a1,T2 a2)
	 */
	R doApplyX(T1 a1, T2 a2) throws Throwable;

	default R tupleApply(LPair<T1, T2> args) {
		return doApply(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default R handlingDoApply(T1 a1, T2 a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default R tryDoApply(T1 a1, T2 a2, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default R tryDoApply(T1 a1, T2 a2, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default R tryDoApplyThen(T1 a1, T2 a2, @Nonnull LFunction<Throwable, R> handler) {
		try {
			return this.doApplyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApply(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default R nestingDoApply(T1 a1, T2 a2) {
		try {
			return this.doApplyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default R shovingDoApply(T1 a1, T2 a2) {
		try {
			return this.doApplyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T1, T2, R> R handlingDoApply(T1 a1, T2 a2, LBiFunction<T1, T2, R> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApply(a1, a2, handling);
	}

	static <T1, T2, R> R tryDoApply(T1 a1, T2 a2, LBiFunction<T1, T2, R> func) {
		return tryDoApply(a1, a2, func, null);
	}

	static <T1, T2, R> R tryDoApply(T1 a1, T2 a2, LBiFunction<T1, T2, R> func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApply(a1, a2, exceptionFactory, newMessage, messageParams);
	}

	static <T1, T2, R> R tryDoApply(T1 a1, T2 a2, LBiFunction<T1, T2, R> func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApply(a1, a2, exceptionFactory);
	}

	static <T1, T2, R> R tryDoApplyThen(T1 a1, T2 a2, LBiFunction<T1, T2, R> func, @Nonnull LFunction<Throwable, R> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyThen(a1, a2, handler);
	}

	default R failSafeDoApply(T1 a1, T2 a2, @Nonnull LBiFunction<T1, T2, R> failSafe) {
		try {
			return doApply(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApply(a1, a2);
		}
	}

	static <T1, T2, R> R failSafeDoApply(T1 a1, T2 a2, LBiFunction<T1, T2, R> func, @Nonnull LBiFunction<T1, T2, R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApply(a1, a2);
		} else {
			return func.failSafeDoApply(a1, a2, failSafe);
		}
	}

	static <T1, T2, R> LBiFunction<T1, T2, R> failSafeBiFunc(LBiFunction<T1, T2, R> func, @Nonnull LBiFunction<T1, T2, R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeDoApply(a1, a2, func, failSafe);
	}

	LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullDoApply() method cannot be null (" + DESCRIPTION + ").";

	/** Function call that ensures the result is not null */
	@Nonnull
	default R nonNullDoApply(T1 a1, T2 a2) {
		return Null.requireNonNull(doApply(a1, a2), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, R> void fromTo(int min_i, int max_i, T1 a1, T2 a2, LBiFunction<T1, T2, R> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.doApply(a1, a2);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.doApply(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, R> void fromTill(int min_i, int max_i, T1 a1, T2 a2, LBiFunction<T1, T2, R> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i < max_i; i++) {
				func.doApply(a1, a2);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.doApply(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, R> void times(int max_i, T1 a1, T2 a2, LBiFunction<T1, T2, R> func) {
		fromTill(0, max_i, a1, a2, func);
	}

	public default LFunction<T2, R> lShrink(LFunction<T2, T1> left) {
		return a2 -> doApply(left.doApply(a2), a2);
	}

	public default LFunction<T2, R> lShrinkc(T1 a1) {
		return a2 -> doApply(a1, a2);
	}

	public static <T2, R, T1> LFunction<T2, R> lShrinked(LFunction<T2, T1> left, LBiFunction<T1, T2, R> func) {
		return func.lShrink(left);
	}

	public static <T2, R, T1> LFunction<T2, R> lShrinkedc(T1 a1, LBiFunction<T1, T2, R> func) {
		return func.lShrinkc(a1);
	}

	public default LFunction<T1, R> rShrink(LFunction<T1, T2> right) {
		return a1 -> doApply(a1, right.doApply(a1));
	}

	public default LFunction<T1, R> rShrinkc(T2 a2) {
		return a1 -> doApply(a1, a2);
	}

	public static <T1, R, T2> LFunction<T1, R> rShrinked(LFunction<T1, T2> right, LBiFunction<T1, T2, R> func) {
		return func.rShrink(right);
	}

	public static <T1, R, T2> LFunction<T1, R> rShrinkedc(T2 a2, LBiFunction<T1, T2, R> func) {
		return func.rShrinkc(a2);
	}

	/**  */
	public static <T1, T2, R> LBiFunction<T1, T2, R> uncurryBiFunc(LFunction<T1, LFunction<T2, R>> func) {
		return (T1 a1, T2 a2) -> func.doApply(a1).doApply(a2);
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplier<R> captureBiFunc(T1 a1, T2 a2) {
		return () -> this.doApply(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static <T1, T2, R> LBiFunction<T1, T2, R> constant(R r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T1, T2, R> LBiFunction<T1, T2, R> apply1st(@Nonnull LFunction<T1, R> func) {
		return (a1, a2) -> func.doApply(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T1, T2, R> LBiFunction<T1, T2, R> apply2nd(@Nonnull LFunction<T2, R> func) {
		return (a1, a2) -> func.doApply(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, R> LBiFunction<T1, T2, R> biFunc(final @Nonnull LBiFunction<T1, T2, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T1, T2, R> LBiFunction<T1, T2, R> recursive(final @Nonnull LFunction<LBiFunction<T1, T2, R>, LBiFunction<T1, T2, R>> selfLambda) {
		final LBiFunctionSingle<T1, T2, R> single = new LBiFunctionSingle();
		LBiFunction<T1, T2, R> func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LBiFunctionSingle<T1, T2, R> implements LSingle<LBiFunction<T1, T2, R>>, LBiFunction<T1, T2, R> {
		private LBiFunction<T1, T2, R> target = null;

		@Override
		public R doApplyX(T1 a1, T2 a2) throws Throwable {
			return target.doApplyX(a1, a2);
		}

		@Override
		public LBiFunction<T1, T2, R> value() {
			return target;
		}
	}

	@Nonnull
	static <T1, T2, R> LBiFunction<T1, T2, R> biFuncThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static <T1, T2, R> LBiFunction<T1, T2, R> biFuncThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2) -> {
			throw exceptionFactory.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1, R> LObj1Obj0Func<T2, T1, R> obj1Obj0Func(final @Nonnull LObj1Obj0Func<T2, T1, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T1, T2, R> R call(T1 a1, T2 a2, final @Nonnull LBiFunction<T1, T2, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApply(a1, a2);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <T1, T2, R> LBiFunction<T1, T2, R> wrap(final BiFunction<T1, T2, R> other) {
		return other::apply;
	}
	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produce). */
	@Nonnull
	static <T1, T2, R> LBiFunction<T1, T2, R> safe() {
		return LBiFunction::produce;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T1, T2, R> LSupplier<LBiFunction<T1, T2, R>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T1, T2, R> LBiFunction<T1, T2, R> safe(final @Nullable LBiFunction<T1, T2, R> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T1, T2, R> LSupplier<LBiFunction<T1, T2, R>> safeSupplier(final @Nullable LSupplier<LBiFunction<T1, T2, R>> supplier) {
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
	default <V1, V2> LBiFunction<V1, V2, R> biFuncCompose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.doApply(before1.doApply(v1), before2.doApply(v2));
	}

	public static <V1, V2, T1, T2, R> LBiFunction<V1, V2, R> composed(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, LBiFunction<T1, T2, R> after) {
		return after.biFuncCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiFunction<T1, T2, V> then(@Nonnull LFunction<? super R, ? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApply(this.doApply(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiConsumer<T1, T2> thenConsume(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doAccept(this.doApply(a1, a2));
	}

	@Nonnull
	default LBiFunction<T1, T2, R> before(@Nonnull LBiConsumer<? super T1, ? super T2> before) {
		Null.nonNullArg(before, "before");
		return (a1, a2) -> {
			before.doAccept(a1, a2);
			return this.doApply(a1, a2);
		};
	}

	@Nonnull
	default LBiFunction<T1, T2, R> after(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> {
			R result = this.doApply(a1, a2);
			after.doAccept(result);
			return result;
		};
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToByteBiFunction<T1, T2> thenToByte(@Nonnull LToByteFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsByte(this.doApply(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToSrtBiFunction<T1, T2> thenToSrt(@Nonnull LToSrtFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsSrt(this.doApply(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToIntBiFunction<T1, T2> thenToInt(@Nonnull LToIntFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsInt(this.doApply(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToLongBiFunction<T1, T2> thenToLong(@Nonnull LToLongFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsLong(this.doApply(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToFltBiFunction<T1, T2> thenToFlt(@Nonnull LToFltFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsFlt(this.doApply(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToDblBiFunction<T1, T2> thenToDbl(@Nonnull LToDblFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsDbl(this.doApply(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToCharBiFunction<T1, T2> thenToChar(@Nonnull LToCharFunction<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doApplyAsChar(this.doApply(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiPredicate<T1, T2> thenToBool(@Nonnull LPredicate<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.doTest(this.doApply(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LBiFunction<T1, T2, R> nestingBiFunc() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LBiFunction<T1, T2, R> shovingBiFunc() {
		return this;
	}

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LBiFunction<T1, T2, R> nonNullBiFunc() {
		return this::nonNullDoApply;
	}

	// <editor-fold desc="interface variants">

	/** Permutation of LBiFunction for method references. */
	@FunctionalInterface
	interface LObj1Obj0Func<T2, T1, R> extends LBiFunction<T1, T2, R> {
		@Nullable
		R doApplyObj1Obj0(T2 a2, T1 a1);

		@Override
		default R doApplyX(T1 a1, T2 a2) {
			return this.doApplyObj1Obj0(a2, a1);
		}
	}

	// </editor-fold>

	/** Does nothing (LBiFunction) Function */
	public static <T1, T2, R> R produce(T1 a1, T2 a2) {
		return (R) Function4U.defaultObject;
	}

	// MAP: FOR, [SourcePurpose{arg=T1 a1, type=IA}, SourcePurpose{arg=T2 a2, type=IA}, SourcePurpose{arg=LConsumer<? super R> consumer, type=CONST}]
	default <C1, C2> void forEach(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			T1 a1 = oiFunc1.doApply(source1, i);
			T2 a2 = oiFunc2.doApply(source2, i);
			consumer.doAccept(this.doApply(a1, a2));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T1 a1, type=SA}, SourcePurpose{arg=T2 a2, type=IA}, SourcePurpose{arg=LConsumer<? super R> consumer, type=CONST}]
	default <C1, I1, C2> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size) {
			T1 a1 = nextFunc1.doApply(iterator1);
			T2 a2 = oiFunc2.doApply(source2, i);
			consumer.doAccept(this.doApply(a1, a2));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T1 a1, type=IA}, SourcePurpose{arg=T2 a2, type=SA}, SourcePurpose{arg=LConsumer<? super R> consumer, type=CONST}]
	default <C1, C2, I2> void iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2)) {
			T1 a1 = oiFunc1.doApply(source1, i);
			T2 a2 = nextFunc2.doApply(iterator2);
			consumer.doAccept(this.doApply(a1, a2));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T1 a1, type=SA}, SourcePurpose{arg=T2 a2, type=SA}, SourcePurpose{arg=LConsumer<? super R> consumer, type=CONST}]
	default <C1, I1, C2, I2> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.getter();
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2)) {
			T1 a1 = nextFunc1.doApply(iterator1);
			T2 a2 = nextFunc2.doApply(iterator2);
			consumer.doAccept(this.doApply(a1, a2));
		}
	}

}
