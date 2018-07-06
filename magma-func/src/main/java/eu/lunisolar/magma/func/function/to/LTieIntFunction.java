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
 * Non-throwing functional interface (lambda) LTieIntFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 3): T a1,int a2,int a3
 *
 * Co-domain: int
 *
 * Special case of function that corresponds to TIE consumer with return integer value.
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LTieIntFunction<T> extends MetaFunction, MetaInterface.NonThrowing, TieFunction<T, aInt> { // NOSONAR

	String DESCRIPTION = "LTieIntFunction: int doApplyAsInt(T a1,int a2,int a3)";

	// int doApplyAsInt(T a1,int a2,int a3) ;
	default int doApplyAsInt(T a1, int a2, int a3) {
		// return nestingDoApplyAsInt(a1,a2,a3);
		try {
			return this.doApplyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doApplyAsInt(T a1,int a2,int a3)
	 */
	int doApplyAsIntX(T a1, int a2, int a3) throws Throwable;

	default int tupleApplyAsInt(LObjBiIntTriple<T> args) {
		return doApplyAsInt(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions according to the instructions. */
	default int handlingDoApplyAsInt(T a1, int a2, int a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default int tryDoApplyAsInt(T a1, int a2, int a3, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default int tryDoApplyAsInt(T a1, int a2, int a3, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default int tryDoApplyAsIntThen(T a1, int a2, int a3, @Nonnull LToIntFunction<Throwable> handler) {
		try {
			return this.doApplyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApplyAsInt(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default int nestingDoApplyAsInt(T a1, int a2, int a3) {
		try {
			return this.doApplyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default int shovingDoApplyAsInt(T a1, int a2, int a3) {
		try {
			return this.doApplyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> int handlingDoApplyAsInt(T a1, int a2, int a3, LTieIntFunction<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApplyAsInt(a1, a2, a3, handling);
	}

	static <T> int tryDoApplyAsInt(T a1, int a2, int a3, LTieIntFunction<T> func) {
		return tryDoApplyAsInt(a1, a2, a3, func, null);
	}

	static <T> int tryDoApplyAsInt(T a1, int a2, int a3, LTieIntFunction<T> func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsInt(a1, a2, a3, exceptionFactory, newMessage, messageParams);
	}

	static <T> int tryDoApplyAsInt(T a1, int a2, int a3, LTieIntFunction<T> func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsInt(a1, a2, a3, exceptionFactory);
	}

	static <T> int tryDoApplyAsIntThen(T a1, int a2, int a3, LTieIntFunction<T> func, @Nonnull LToIntFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsIntThen(a1, a2, a3, handler);
	}

	default int failSafeDoApplyAsInt(T a1, int a2, int a3, @Nonnull LTieIntFunction<T> failSafe) {
		try {
			return doApplyAsInt(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApplyAsInt(a1, a2, a3);
		}
	}

	static <T> int failSafeDoApplyAsInt(T a1, int a2, int a3, LTieIntFunction<T> func, @Nonnull LTieIntFunction<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApplyAsInt(a1, a2, a3);
		} else {
			return func.failSafeDoApplyAsInt(a1, a2, a3, failSafe);
		}
	}

	static <T> LTieIntFunction<T> failSafeTieIntFunc(LTieIntFunction<T> func, @Nonnull LTieIntFunction<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeDoApplyAsInt(a1, a2, a3, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default int nonNullDoApplyAsInt(T a1, int a2, int a3) {
		return doApplyAsInt(a1, a2, a3);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LTieIntFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_i, int max_i, T a1, int a2, int a3, LTieIntFunction<T> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.doApplyAsInt(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.doApplyAsInt(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTill(int min_i, int max_i, T a1, int a2, int a3, LTieIntFunction<T> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= min_i) {
			for (int i = min_i; i < max_i; i++) {
				func.doApplyAsInt(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.doApplyAsInt(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void times(int max_i, T a1, int a2, int a3, LTieIntFunction<T> func) {
		fromTill(0, max_i, a1, a2, a3, func);
	}

	public default LIntBinaryOperator lShrink(LBiIntFunction<T> left) {
		return (a2, a3) -> doApplyAsInt(left.doApply(a2, a3), a2, a3);
	}

	public default LIntBinaryOperator lShrinkc(T a1) {
		return (a2, a3) -> doApplyAsInt(a1, a2, a3);
	}

	public static <T> LIntBinaryOperator lShrinked(LBiIntFunction<T> left, LTieIntFunction<T> func) {
		return func.lShrink(left);
	}

	public static <T> LIntBinaryOperator lShrinkedc(T a1, LTieIntFunction<T> func) {
		return func.lShrinkc(a1);
	}

	public default LOiToIntFunction<T> rShrink(LOiToIntFunction<T> right) {
		return (a1, a2) -> doApplyAsInt(a1, a2, right.doApplyAsInt(a1, a2));
	}

	public default LOiToIntFunction<T> rShrinkc(int a3) {
		return (a1, a2) -> doApplyAsInt(a1, a2, a3);
	}

	public static <T> LOiToIntFunction<T> rShrinked(LOiToIntFunction<T> right, LTieIntFunction<T> func) {
		return func.rShrink(right);
	}

	public static <T> LOiToIntFunction<T> rShrinkedc(int a3, LTieIntFunction<T> func) {
		return func.rShrinkc(a3);
	}

	/**  */
	public static <T> LTieIntFunction<T> uncurryTieIntFunc(LFunction<T, LIntFunction<LIntUnaryOperator>> func) {
		return (T a1, int a2, int a3) -> func.doApply(a1).doApply(a2).doApplyAsInt(a3);
	}

	/** Captures arguments but delays the evaluation. */
	default LIntSupplier captureTieIntFunc(T a1, int a2, int a3) {
		return () -> this.doApplyAsInt(a1, a2, a3);
	}

	/** Creates function that always returns the same value. */
	static <T> LTieIntFunction<T> constant(int r) {
		return (a1, a2, a3) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T> LTieIntFunction<T> apply1stAsInt(@Nonnull LToIntFunction<T> func) {
		return (a1, a2, a3) -> func.doApplyAsInt(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T> LTieIntFunction<T> apply2ndAsInt(@Nonnull LIntUnaryOperator func) {
		return (a1, a2, a3) -> func.doApplyAsInt(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T> LTieIntFunction<T> apply3rdAsInt(@Nonnull LIntUnaryOperator func) {
		return (a1, a2, a3) -> func.doApplyAsInt(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LTieIntFunction<T> tieIntFunc(final @Nonnull LTieIntFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T> LTieIntFunction<T> recursive(final @Nonnull LFunction<LTieIntFunction<T>, LTieIntFunction<T>> selfLambda) {
		final LTieIntFunctionSingle<T> single = new LTieIntFunctionSingle();
		LTieIntFunction<T> func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LTieIntFunctionSingle<T> implements LSingle<LTieIntFunction<T>>, LTieIntFunction<T> {
		private LTieIntFunction<T> target = null;

		@Override
		public int doApplyAsIntX(T a1, int a2, int a3) throws Throwable {
			return target.doApplyAsIntX(a1, a2, a3);
		}

		@Override
		public LTieIntFunction<T> value() {
			return target;
		}
	}

	@Nonnull
	static <T> LTieIntFunction<T> tieIntFuncThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2, a3) -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static <T> LTieIntFunction<T> tieIntFuncThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2, a3) -> {
			throw exceptionFactory.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LObjInt2Int1ToIntFunc<T> objInt2Int1ToIntFunc(final @Nonnull LObjInt2Int1ToIntFunc<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LInt1ObjIntToIntFunc<T> int1ObjIntToIntFunc(final @Nonnull LInt1ObjIntToIntFunc<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LInt1Int2ObjToIntFunc<T> int1Int2ObjToIntFunc(final @Nonnull LInt1Int2ObjToIntFunc<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LInt2ObjInt1ToIntFunc<T> int2ObjInt1ToIntFunc(final @Nonnull LInt2ObjInt1ToIntFunc<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LBiIntObjToIntFunc<T> biIntObjToIntFunc(final @Nonnull LBiIntObjToIntFunc<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T> int call(T a1, int a2, int a3, final @Nonnull LTieIntFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsInt(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceInt). */
	@Nonnull
	static <T> LTieIntFunction<T> safe() {
		return LTieIntFunction::produceInt;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LTieIntFunction<T>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T> LTieIntFunction<T> safe(final @Nullable LTieIntFunction<T> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LTieIntFunction<T>> safeSupplier(final @Nullable LSupplier<LTieIntFunction<T>> supplier) {
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
	default <V1> LTieIntFunction<V1> tieIntFuncComposeInt(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2, @Nonnull final LIntUnaryOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.doApplyAsInt(before1.doApply(v1), before2.doApplyAsInt(v2), before3.doApplyAsInt(v3));
	}

	public static <V1, T> LTieIntFunction<V1> composedInt(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2, @Nonnull final LIntUnaryOperator before3, LTieIntFunction<T> after) {
		return after.tieIntFuncComposeInt(before1, before2, before3);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LToIntTriFunction<V1, V2, V3> tieIntFuncCompose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2, @Nonnull final LToIntFunction<? super V3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.doApplyAsInt(before1.doApply(v1), before2.doApplyAsInt(v2), before3.doApplyAsInt(v3));
	}

	public static <V1, V2, V3, T> LToIntTriFunction<V1, V2, V3> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2, @Nonnull final LToIntFunction<? super V3> before3,
			LTieIntFunction<T> after) {
		return after.tieIntFuncCompose(before1, before2, before3);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LObjBiIntFunction<T, V> then(@Nonnull LIntFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.doApply(this.doApplyAsInt(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LTieIntFunction<T> thenToInt(@Nonnull LIntUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.doApplyAsInt(this.doApplyAsInt(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LObjBiIntPredicate<T> thenToBool(@Nonnull LIntPredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.doTest(this.doApplyAsInt(a1, a2, a3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LTieIntFunction<T> nestingTieIntFunc() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LTieIntFunction<T> shovingTieIntFunc() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LTieIntFunction for method references. */
	@FunctionalInterface
	interface LObjInt2Int1ToIntFunc<T> extends LTieIntFunction<T> {

		int doApplyAsIntObjInt2Int1(T a1, int a3, int a2);

		@Override
		default int doApplyAsIntX(T a1, int a2, int a3) {
			return this.doApplyAsIntObjInt2Int1(a1, a3, a2);
		}
	}

	/** Permutation of LTieIntFunction for method references. */
	@FunctionalInterface
	interface LInt1ObjIntToIntFunc<T> extends LTieIntFunction<T> {

		int doApplyAsIntInt1ObjInt(int a2, T a1, int a3);

		@Override
		default int doApplyAsIntX(T a1, int a2, int a3) {
			return this.doApplyAsIntInt1ObjInt(a2, a1, a3);
		}
	}

	/** Permutation of LTieIntFunction for method references. */
	@FunctionalInterface
	interface LInt1Int2ObjToIntFunc<T> extends LTieIntFunction<T> {

		int doApplyAsIntInt1Int2Obj(int a2, int a3, T a1);

		@Override
		default int doApplyAsIntX(T a1, int a2, int a3) {
			return this.doApplyAsIntInt1Int2Obj(a2, a3, a1);
		}
	}

	/** Permutation of LTieIntFunction for method references. */
	@FunctionalInterface
	interface LInt2ObjInt1ToIntFunc<T> extends LTieIntFunction<T> {

		int doApplyAsIntInt2ObjInt1(int a3, T a1, int a2);

		@Override
		default int doApplyAsIntX(T a1, int a2, int a3) {
			return this.doApplyAsIntInt2ObjInt1(a3, a1, a2);
		}
	}

	/** Permutation of LTieIntFunction for method references. */
	@FunctionalInterface
	interface LBiIntObjToIntFunc<T> extends LTieIntFunction<T> {

		int doApplyAsIntBiIntObj(int a3, int a2, T a1);

		@Override
		default int doApplyAsIntX(T a1, int a2, int a3) {
			return this.doApplyAsIntBiIntObj(a3, a2, a1);
		}
	}

	// </editor-fold>

	/** Does nothing (LTieIntFunction) Function */
	public static <T> int produceInt(T a1, int a2, int a3) {
		return Function4U.defaultInteger;
	}

	/** Does nothing (LTieIntFunction.LInt1ObjIntToIntFunc) Function */
	public static <T> int produceInt(int a2, T a1, int a3) {
		return Function4U.defaultInteger;
	}

	/** Does nothing (LTieIntFunction.LInt1Int2ObjToIntFunc) Function */
	public static <T> int produceInt(int a2, int a3, T a1) {
		return Function4U.defaultInteger;
	}

	// TIE_CONSUMER_GEN: FOR, [SourcePurpose{arg=int sStart, type=CONST}, SourcePurpose{arg=int sEnd, type=CONST}, SourcePurpose{arg=int tStart, type=CONST},
	// SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=int a3, type=TIE_SOURCE}, SourcePurpose{arg=int a3, type=TIE_GEN_SUPPLIER}]
	default <SRC> int genericTieForEach(int sStart, int sEnd, int tStart, T trg1, SRC src3, OiFunction<SRC, aInt> srcAcc3) {
		return tieForEach(sStart, sEnd, tStart, trg1, src3, (LOiToIntFunction<SRC>) srcAcc3, this);

	}

	// TARGETED_INDEXED_FOR_EACH: FOR, [SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=int a3, type=IA}, SourcePurpose{arg=LTieIntFunction<? super T>
	// consumer, type=CONST}]
	public static <T, C3> T tiForEach(T trg1, IndexedRead<C3, aInt> ia3, C3 source3, LTieIntFunction<? super T> consumer) {

		tieForEach(trg1, ia3, source3, consumer);

		return trg1;

	}

	// TARGETED_INDEXED_FOR_EACH_NEW: FOR, [SourcePurpose{arg=T trg1, type=SIZE_FACTORY}, SourcePurpose{arg=int a3, type=IA},
	// SourcePurpose{arg=LTieIntFunction<? super T> consumer, type=CONST}]
	public static <T, C3> T ntiForEach(LIntFunction<T> trgFactory1, IndexedRead<C3, aInt> ia3, C3 source3, LTieIntFunction<? super T> consumer) {
		int size = ia3.size(source3);
		T trg1 = trgFactory1.doApply(size);
		tieForEach(0, size, 0, trg1, source3, ia3.getter(), consumer);
		return trg1;
	}

	// TIE_CONSUMER_SHORT: FOR, [SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=int a3, type=IA}, SourcePurpose{arg=LTieIntFunction<? super T>
	// consumer, type=CONST}]
	public static <T, C3> int tieForEach(T trg1, IndexedRead<C3, aInt> ia3, C3 source3, LTieIntFunction<? super T> consumer) {
		int size = ia3.size(source3);
		return tieForEach(0, size, 0, trg1, source3, ia3.getter(), consumer);
	}

	// TIE_CONSUMER: FOR, [SourcePurpose{arg=int sStart, type=CONST}, SourcePurpose{arg=int sEnd, type=CONST}, SourcePurpose{arg=int tStart, type=CONST},
	// SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=int a3, type=TIE_SOURCE}, SourcePurpose{arg=int a3, type=TIE_SUPPLIER},
	// SourcePurpose{arg=LTieIntFunction<? super T> consumer, type=CONST}]
	public static <T, SRC> int tieForEach(int sStart, int sEnd, int tStart, T trg1, SRC src3, LOiToIntFunction<SRC> srcAcc3, LTieIntFunction<? super T> consumer) {
		int tIndex = tStart;
		for (int sIndex = sStart; sIndex < sEnd; sIndex++) {
			int a3 = srcAcc3.doApplyAsInt(src3, sIndex);
			tIndex += consumer.doApplyAsInt(trg1, tIndex, a3);
		}
		return tIndex - tStart;

	}

	// TIE_CONSUMER2: FOR, [SourcePurpose{arg=int sStart, type=CONST}, SourcePurpose{arg=int tStart, type=CONST}, SourcePurpose{arg=T trg1, type=CONST},
	// SourcePurpose{arg=int a3, type=TIE_SOURCE}, SourcePurpose{arg=int a3, type=TE_PREDICATE}, SourcePurpose{arg=int a3, type=TE_SUPPLIER},
	// SourcePurpose{arg=LTieIntFunction<? super T> consumer, type=CONST}]
	public static <T, SRC> int tieForEach(int sStart, int tStart, T trg1, SRC src3, LPredicate<SRC> srcTest3, LToIntFunction<SRC> srcAcc3, LTieIntFunction<? super T> consumer) {
		int tIndex = tStart;
		for (; srcTest3.doTest(src3); tIndex++) {
			int a3 = srcAcc3.doApplyAsInt(src3);
			tIndex += consumer.doApplyAsInt(trg1, tIndex, a3);
		}
		return tIndex - sStart;

	}

	// TIE_CONSUMER2_SHORT: WHILE, [SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=int a3, type=SA}, SourcePurpose{arg=LTieIntFunction<? super T>
	// consumer, type=CONST}]
	public static <T, C3, I3> int tieIterate(T trg1, SequentialRead<C3, I3, aInt> sa3, C3 source3, LTieIntFunction<? super T> consumer) {
		LFunction<C3, I3> toIntermediate = sa3.adapter();
		return tieForEach(0, 0, trg1, toIntermediate.doApply(source3), sa3.tester(), sa3.getter(), consumer);
	}

	// TARGETED_INDEXED_ITERATE: WHILE, [SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=int a3, type=SA}, SourcePurpose{arg=LTieIntFunction<? super T>
	// consumer, type=CONST}]
	public static <T, C3, I3> T tiIterate(T trg1, SequentialRead<C3, I3, aInt> sa3, C3 source3, LTieIntFunction<? super T> consumer) {

		tieIterate(trg1, sa3, source3, consumer);

		return trg1;

	}

	// TARGETED_INDEXED_ITERATE_NEW: WHILE, [SourcePurpose{arg=T trg1, type=SUPPLIER}, SourcePurpose{arg=int a3, type=SA}, SourcePurpose{arg=LTieIntFunction<?
	// super T> consumer, type=CONST}]
	public static <T, C3, I3> T ntiIterate(LSupplier<T> source1, SequentialRead<C3, I3, aInt> sa3, C3 source3, LTieIntFunction<? super T> consumer) {
		T trg1 = source1.doGet();
		tieIterate(trg1, sa3, source3, consumer);

		return trg1;

	}

	// MAP: FOR, [SourcePurpose{arg=T a1, type=IA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=int a3, type=IA}, SourcePurpose{arg=LIntConsumer
	// consumer, type=CONST}]
	default <C1, C2, C3> void forEach(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, IndexedRead<C3, aInt> ia3, C3 source3, LIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToIntFunction<Object> oiFunc3 = (LOiToIntFunction) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			T a1 = oiFunc1.doApply(source1, i);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			int a3 = oiFunc3.doApplyAsInt(source3, i);
			consumer.doAccept(this.doApplyAsInt(a1, a2, a3));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T a1, type=SA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=int a3, type=IA}, SourcePurpose{arg=LIntConsumer
	// consumer, type=CONST}]
	default <C1, I1, C2, C3> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, IndexedRead<C3, aInt> ia3, C3 source3, LIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToIntFunction<Object> oiFunc3 = (LOiToIntFunction) ia3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size) {
			T a1 = nextFunc1.doApply(iterator1);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			int a3 = oiFunc3.doApplyAsInt(source3, i);
			consumer.doAccept(this.doApplyAsInt(a1, a2, a3));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T a1, type=IA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=int a3, type=IA}, SourcePurpose{arg=LIntConsumer
	// consumer, type=CONST}]
	default <C1, C2, I2, C3> void iterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, IndexedRead<C3, aInt> ia3, C3 source3, LIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToIntFunction<Object> oiFunc3 = (LOiToIntFunction) ia3.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2)) {
			T a1 = oiFunc1.doApply(source1, i);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			int a3 = oiFunc3.doApplyAsInt(source3, i);
			consumer.doAccept(this.doApplyAsInt(a1, a2, a3));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T a1, type=SA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=int a3, type=IA}, SourcePurpose{arg=LIntConsumer
	// consumer, type=CONST}]
	default <C1, I1, C2, I2, C3> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, IndexedRead<C3, aInt> ia3, C3 source3, LIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		int size = ia3.size(source3);
		LOiToIntFunction<Object> oiFunc3 = (LOiToIntFunction) ia3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2) && i < size) {
			T a1 = nextFunc1.doApply(iterator1);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			int a3 = oiFunc3.doApplyAsInt(source3, i);
			consumer.doAccept(this.doApplyAsInt(a1, a2, a3));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T a1, type=IA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=int a3, type=SA}, SourcePurpose{arg=LIntConsumer
	// consumer, type=CONST}]
	default <C1, C2, C3, I3> void iterate(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, SequentialRead<C3, I3, aInt> sa3, C3 source3, LIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToIntFunction<Object> nextFunc3 = (LToIntFunction) sa3.getter();
		int i = 0;
		while (i < size && testFunc3.doTest(iterator3)) {
			T a1 = oiFunc1.doApply(source1, i);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			int a3 = nextFunc3.doApplyAsInt(iterator3);
			consumer.doAccept(this.doApplyAsInt(a1, a2, a3));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T a1, type=SA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=int a3, type=SA}, SourcePurpose{arg=LIntConsumer
	// consumer, type=CONST}]
	default <C1, I1, C2, C3, I3> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, SequentialRead<C3, I3, aInt> sa3, C3 source3, LIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToIntFunction<Object> nextFunc3 = (LToIntFunction) sa3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size && testFunc3.doTest(iterator3)) {
			T a1 = nextFunc1.doApply(iterator1);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			int a3 = nextFunc3.doApplyAsInt(iterator3);
			consumer.doAccept(this.doApplyAsInt(a1, a2, a3));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T a1, type=IA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=int a3, type=SA}, SourcePurpose{arg=LIntConsumer
	// consumer, type=CONST}]
	default <C1, C2, I2, C3, I3> void iterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, SequentialRead<C3, I3, aInt> sa3, C3 source3, LIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToIntFunction<Object> nextFunc3 = (LToIntFunction) sa3.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2) && testFunc3.doTest(iterator3)) {
			T a1 = oiFunc1.doApply(source1, i);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			int a3 = nextFunc3.doApplyAsInt(iterator3);
			consumer.doAccept(this.doApplyAsInt(a1, a2, a3));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T a1, type=SA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=int a3, type=SA}, SourcePurpose{arg=LIntConsumer
	// consumer, type=CONST}]
	default <C1, I1, C2, I2, C3, I3> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, SequentialRead<C3, I3, aInt> sa3, C3 source3, LIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToIntFunction<Object> nextFunc3 = (LToIntFunction) sa3.getter();
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2) && testFunc3.doTest(iterator3)) {
			T a1 = nextFunc1.doApply(iterator1);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			int a3 = nextFunc3.doApplyAsInt(iterator3);
			consumer.doAccept(this.doApplyAsInt(a1, a2, a3));
		}
	}

}
