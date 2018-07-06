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
 * Non-throwing functional interface (lambda) LTieCharFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 3): T a1,int a2,char a3
 *
 * Co-domain: int
 *
 * Special case of function that corresponds to TIE consumer with return integer value.
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LTieCharFunction<T> extends MetaFunction, MetaInterface.NonThrowing, TieFunction<T, aChar> { // NOSONAR

	String DESCRIPTION = "LTieCharFunction: int doApplyAsInt(T a1,int a2,char a3)";

	// int doApplyAsInt(T a1,int a2,char a3) ;
	default int doApplyAsInt(T a1, int a2, char a3) {
		// return nestingDoApplyAsInt(a1,a2,a3);
		try {
			return this.doApplyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call doApplyAsInt(T a1,int a2,char a3)
	 */
	int doApplyAsIntX(T a1, int a2, char a3) throws Throwable;

	default int tupleApplyAsInt(LObjIntCharTriple<T> args) {
		return doApplyAsInt(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions according to the instructions. */
	default int handlingDoApplyAsInt(T a1, int a2, char a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.doApplyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default int tryDoApplyAsInt(T a1, int a2, char a3, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.doApplyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory, newMessage, messageParams);
		}
	}

	default int tryDoApplyAsInt(T a1, int a2, char a3, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		try {
			return this.doApplyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exceptionFactory);
		}
	}

	default int tryDoApplyAsIntThen(T a1, int a2, char a3, @Nonnull LToIntFunction<Throwable> handler) {
		try {
			return this.doApplyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.doApplyAsInt(e);
		}
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default int nestingDoApplyAsInt(T a1, int a2, char a3) {
		try {
			return this.doApplyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default int shovingDoApplyAsInt(T a1, int a2, char a3) {
		try {
			return this.doApplyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> int handlingDoApplyAsInt(T a1, int a2, char a3, LTieCharFunction<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingDoApplyAsInt(a1, a2, a3, handling);
	}

	static <T> int tryDoApplyAsInt(T a1, int a2, char a3, LTieCharFunction<T> func) {
		return tryDoApplyAsInt(a1, a2, a3, func, null);
	}

	static <T> int tryDoApplyAsInt(T a1, int a2, char a3, LTieCharFunction<T> func, @Nonnull ExceptionWrapWithMessageFactory<RuntimeException> exceptionFactory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsInt(a1, a2, a3, exceptionFactory, newMessage, messageParams);
	}

	static <T> int tryDoApplyAsInt(T a1, int a2, char a3, LTieCharFunction<T> func, @Nonnull ExceptionWrapFactory<RuntimeException> exceptionFactory) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsInt(a1, a2, a3, exceptionFactory);
	}

	static <T> int tryDoApplyAsIntThen(T a1, int a2, char a3, LTieCharFunction<T> func, @Nonnull LToIntFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.tryDoApplyAsIntThen(a1, a2, a3, handler);
	}

	default int failSafeDoApplyAsInt(T a1, int a2, char a3, @Nonnull LTieCharFunction<T> failSafe) {
		try {
			return doApplyAsInt(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.doApplyAsInt(a1, a2, a3);
		}
	}

	static <T> int failSafeDoApplyAsInt(T a1, int a2, char a3, LTieCharFunction<T> func, @Nonnull LTieCharFunction<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.doApplyAsInt(a1, a2, a3);
		} else {
			return func.failSafeDoApplyAsInt(a1, a2, a3, failSafe);
		}
	}

	static <T> LTieCharFunction<T> failSafeTieCharFunc(LTieCharFunction<T> func, @Nonnull LTieCharFunction<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeDoApplyAsInt(a1, a2, a3, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default int nonNullDoApplyAsInt(T a1, int a2, char a3) {
		return doApplyAsInt(a1, a2, a3);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LTieCharFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_a2, int max_a2, T a1, char a3, LTieCharFunction<T> func) {
		Null.nonNullArg(func, "func");
		if (min_a2 <= min_a2) {
			for (int a2 = min_a2; a2 <= max_a2; a2++) {
				func.doApplyAsInt(a1, a2, a3);
			}
		} else {
			for (int a2 = min_a2; a2 >= max_a2; a2--) {
				func.doApplyAsInt(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTill(int min_a2, int max_a2, T a1, char a3, LTieCharFunction<T> func) {
		Null.nonNullArg(func, "func");
		if (min_a2 <= min_a2) {
			for (int a2 = min_a2; a2 < max_a2; a2++) {
				func.doApplyAsInt(a1, a2, a3);
			}
		} else {
			for (int a2 = min_a2; a2 > max_a2; a2--) {
				func.doApplyAsInt(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void times(int max_a2, T a1, char a3, LTieCharFunction<T> func) {
		fromTill(0, max_a2, a1, a3, func);
	}

	/**  */
	public static <T> LTieCharFunction<T> uncurryTieCharFunc(LFunction<T, LIntFunction<LCharToIntFunction>> func) {
		return (T a1, int a2, char a3) -> func.doApply(a1).doApply(a2).doApplyAsInt(a3);
	}

	/** Captures arguments but delays the evaluation. */
	default LIntSupplier captureTieCharFunc(T a1, int a2, char a3) {
		return () -> this.doApplyAsInt(a1, a2, a3);
	}

	/** Creates function that always returns the same value. */
	static <T> LTieCharFunction<T> constant(int r) {
		return (a1, a2, a3) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T> LTieCharFunction<T> apply1stAsInt(@Nonnull LToIntFunction<T> func) {
		return (a1, a2, a3) -> func.doApplyAsInt(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T> LTieCharFunction<T> apply2ndAsInt(@Nonnull LIntUnaryOperator func) {
		return (a1, a2, a3) -> func.doApplyAsInt(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T> LTieCharFunction<T> apply3rdAsInt(@Nonnull LCharToIntFunction func) {
		return (a1, a2, a3) -> func.doApplyAsInt(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LTieCharFunction<T> tieCharFunc(final @Nonnull LTieCharFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T> LTieCharFunction<T> recursive(final @Nonnull LFunction<LTieCharFunction<T>, LTieCharFunction<T>> selfLambda) {
		final LTieCharFunctionSingle<T> single = new LTieCharFunctionSingle();
		LTieCharFunction<T> func = selfLambda.doApply(single);
		single.target = func;
		return func;
	}

	final class LTieCharFunctionSingle<T> implements LSingle<LTieCharFunction<T>>, LTieCharFunction<T> {
		private LTieCharFunction<T> target = null;

		@Override
		public int doApplyAsIntX(T a1, int a2, char a3) throws Throwable {
			return target.doApplyAsIntX(a1, a2, a3);
		}

		@Override
		public LTieCharFunction<T> value() {
			return target;
		}
	}

	@Nonnull
	static <T> LTieCharFunction<T> tieCharFuncThrowing(final @Nonnull ExceptionFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2, a3) -> {
			throw exceptionFactory.produce();
		};
	}

	@Nonnull
	static <T> LTieCharFunction<T> tieCharFuncThrowing(final String message, final @Nonnull ExceptionWithMessageFactory<Throwable> exceptionFactory) {
		Null.nonNullArg(exceptionFactory, "exceptionFactory");
		return (a1, a2, a3) -> {
			throw exceptionFactory.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LObjCharIntToIntFunc<T> objCharIntToIntFunc(final @Nonnull LObjCharIntToIntFunc<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LIntObjCharToIntFunc<T> intObjCharToIntFunc(final @Nonnull LIntObjCharToIntFunc<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LIntCharObjToIntFunc<T> intCharObjToIntFunc(final @Nonnull LIntCharObjToIntFunc<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LCharObjIntToIntFunc<T> charObjIntToIntFunc(final @Nonnull LCharObjIntToIntFunc<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LCharIntObjToIntFunc<T> charIntObjToIntFunc(final @Nonnull LCharIntObjToIntFunc<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T> int call(T a1, int a2, char a3, final @Nonnull LTieCharFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.doApplyAsInt(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceInt). */
	@Nonnull
	static <T> LTieCharFunction<T> safe() {
		return LTieCharFunction::produceInt;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LTieCharFunction<T>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T> LTieCharFunction<T> safe(final @Nullable LTieCharFunction<T> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LTieCharFunction<T>> safeSupplier(final @Nullable LSupplier<LTieCharFunction<T>> supplier) {
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
	default <V1> LTieCharFunction<V1> tieCharFuncComposeIntChar(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2, @Nonnull final LCharUnaryOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.doApplyAsInt(before1.doApply(v1), before2.doApplyAsInt(v2), before3.doApplyAsChar(v3));
	}

	public static <V1, T> LTieCharFunction<V1> composedIntChar(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2, @Nonnull final LCharUnaryOperator before3, LTieCharFunction<T> after) {
		return after.tieCharFuncComposeIntChar(before1, before2, before3);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LToIntTriFunction<V1, V2, V3> tieCharFuncCompose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2, @Nonnull final LToCharFunction<? super V3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.doApplyAsInt(before1.doApply(v1), before2.doApplyAsInt(v2), before3.doApplyAsChar(v3));
	}

	public static <V1, V2, V3, T> LToIntTriFunction<V1, V2, V3> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2, @Nonnull final LToCharFunction<? super V3> before3,
			LTieCharFunction<T> after) {
		return after.tieCharFuncCompose(before1, before2, before3);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LObjIntCharFunction<T, V> then(@Nonnull LIntFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.doApply(this.doApplyAsInt(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LTieCharFunction<T> thenToInt(@Nonnull LIntUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.doApplyAsInt(this.doApplyAsInt(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LObjIntCharPredicate<T> thenToBool(@Nonnull LIntPredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.doTest(this.doApplyAsInt(a1, a2, a3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	/** Converts to non-throwing variant (if required). */
	@Nonnull
	default LTieCharFunction<T> nestingTieCharFunc() {
		return this;
	}

	/** Converts to non-throwing variant that will propagate checked exception as it would be unchecked - there is no exception wrapping involved (at least not here). */
	default LTieCharFunction<T> shovingTieCharFunc() {
		return this;
	}

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LTieCharFunction for method references. */
	@FunctionalInterface
	interface LObjCharIntToIntFunc<T> extends LTieCharFunction<T> {

		int doApplyAsIntObjCharInt(T a1, char a3, int a2);

		@Override
		default int doApplyAsIntX(T a1, int a2, char a3) {
			return this.doApplyAsIntObjCharInt(a1, a3, a2);
		}
	}

	/** Permutation of LTieCharFunction for method references. */
	@FunctionalInterface
	interface LIntObjCharToIntFunc<T> extends LTieCharFunction<T> {

		int doApplyAsIntIntObjChar(int a2, T a1, char a3);

		@Override
		default int doApplyAsIntX(T a1, int a2, char a3) {
			return this.doApplyAsIntIntObjChar(a2, a1, a3);
		}
	}

	/** Permutation of LTieCharFunction for method references. */
	@FunctionalInterface
	interface LIntCharObjToIntFunc<T> extends LTieCharFunction<T> {

		int doApplyAsIntIntCharObj(int a2, char a3, T a1);

		@Override
		default int doApplyAsIntX(T a1, int a2, char a3) {
			return this.doApplyAsIntIntCharObj(a2, a3, a1);
		}
	}

	/** Permutation of LTieCharFunction for method references. */
	@FunctionalInterface
	interface LCharObjIntToIntFunc<T> extends LTieCharFunction<T> {

		int doApplyAsIntCharObjInt(char a3, T a1, int a2);

		@Override
		default int doApplyAsIntX(T a1, int a2, char a3) {
			return this.doApplyAsIntCharObjInt(a3, a1, a2);
		}
	}

	/** Permutation of LTieCharFunction for method references. */
	@FunctionalInterface
	interface LCharIntObjToIntFunc<T> extends LTieCharFunction<T> {

		int doApplyAsIntCharIntObj(char a3, int a2, T a1);

		@Override
		default int doApplyAsIntX(T a1, int a2, char a3) {
			return this.doApplyAsIntCharIntObj(a3, a2, a1);
		}
	}

	// </editor-fold>

	/** Does nothing (LTieCharFunction) Function */
	public static <T> int produceInt(T a1, int a2, char a3) {
		return Function4U.defaultInteger;
	}

	/** Does nothing (LTieCharFunction.LObjCharIntToIntFunc) Function */
	public static <T> int produceInt(T a1, char a3, int a2) {
		return Function4U.defaultInteger;
	}

	/** Does nothing (LTieCharFunction.LIntObjCharToIntFunc) Function */
	public static <T> int produceInt(int a2, T a1, char a3) {
		return Function4U.defaultInteger;
	}

	/** Does nothing (LTieCharFunction.LIntCharObjToIntFunc) Function */
	public static <T> int produceInt(int a2, char a3, T a1) {
		return Function4U.defaultInteger;
	}

	/** Does nothing (LTieCharFunction.LCharObjIntToIntFunc) Function */
	public static <T> int produceInt(char a3, T a1, int a2) {
		return Function4U.defaultInteger;
	}

	/** Does nothing (LTieCharFunction.LCharIntObjToIntFunc) Function */
	public static <T> int produceInt(char a3, int a2, T a1) {
		return Function4U.defaultInteger;
	}

	// TIE_CONSUMER_GEN: FOR, [SourcePurpose{arg=int sStart, type=CONST}, SourcePurpose{arg=int sEnd, type=CONST}, SourcePurpose{arg=int tStart, type=CONST},
	// SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=char a3, type=TIE_SOURCE}, SourcePurpose{arg=char a3, type=TIE_GEN_SUPPLIER}]
	default <SRC> int genericTieForEach(int sStart, int sEnd, int tStart, T trg1, SRC src3, OiFunction<SRC, aChar> srcAcc3) {
		return tieForEach(sStart, sEnd, tStart, trg1, src3, (LOiToCharFunction<SRC>) srcAcc3, this);

	}

	// TARGETED_INDEXED_FOR_EACH: FOR, [SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=char a3, type=IA}, SourcePurpose{arg=LTieCharFunction<? super
	// T> consumer, type=CONST}]
	public static <T, C3> T tiForEach(T trg1, IndexedRead<C3, aChar> ia3, C3 source3, LTieCharFunction<? super T> consumer) {

		tieForEach(trg1, ia3, source3, consumer);

		return trg1;

	}

	// TARGETED_INDEXED_FOR_EACH_NEW: FOR, [SourcePurpose{arg=T trg1, type=SIZE_FACTORY}, SourcePurpose{arg=char a3, type=IA},
	// SourcePurpose{arg=LTieCharFunction<? super T> consumer, type=CONST}]
	public static <T, C3> T ntiForEach(LIntFunction<T> trgFactory1, IndexedRead<C3, aChar> ia3, C3 source3, LTieCharFunction<? super T> consumer) {
		int size = ia3.size(source3);
		T trg1 = trgFactory1.doApply(size);
		tieForEach(0, size, 0, trg1, source3, ia3.getter(), consumer);
		return trg1;
	}

	// TIE_CONSUMER_SHORT: FOR, [SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=char a3, type=IA}, SourcePurpose{arg=LTieCharFunction<? super T>
	// consumer, type=CONST}]
	public static <T, C3> int tieForEach(T trg1, IndexedRead<C3, aChar> ia3, C3 source3, LTieCharFunction<? super T> consumer) {
		int size = ia3.size(source3);
		return tieForEach(0, size, 0, trg1, source3, ia3.getter(), consumer);
	}

	// TIE_CONSUMER: FOR, [SourcePurpose{arg=int sStart, type=CONST}, SourcePurpose{arg=int sEnd, type=CONST}, SourcePurpose{arg=int tStart, type=CONST},
	// SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=char a3, type=TIE_SOURCE}, SourcePurpose{arg=char a3, type=TIE_SUPPLIER},
	// SourcePurpose{arg=LTieCharFunction<? super T> consumer, type=CONST}]
	public static <T, SRC> int tieForEach(int sStart, int sEnd, int tStart, T trg1, SRC src3, LOiToCharFunction<SRC> srcAcc3, LTieCharFunction<? super T> consumer) {
		int tIndex = tStart;
		for (int sIndex = sStart; sIndex < sEnd; sIndex++) {
			char a3 = srcAcc3.doApplyAsChar(src3, sIndex);
			tIndex += consumer.doApplyAsInt(trg1, tIndex, a3);
		}
		return tIndex - tStart;

	}

	// TIE_CONSUMER2: FOR, [SourcePurpose{arg=int sStart, type=CONST}, SourcePurpose{arg=int tStart, type=CONST}, SourcePurpose{arg=T trg1, type=CONST},
	// SourcePurpose{arg=char a3, type=TIE_SOURCE}, SourcePurpose{arg=char a3, type=TE_PREDICATE}, SourcePurpose{arg=char a3, type=TE_SUPPLIER},
	// SourcePurpose{arg=LTieCharFunction<? super T> consumer, type=CONST}]
	public static <T, SRC> int tieForEach(int sStart, int tStart, T trg1, SRC src3, LPredicate<SRC> srcTest3, LToCharFunction<SRC> srcAcc3, LTieCharFunction<? super T> consumer) {
		int tIndex = tStart;
		for (; srcTest3.doTest(src3); tIndex++) {
			char a3 = srcAcc3.doApplyAsChar(src3);
			tIndex += consumer.doApplyAsInt(trg1, tIndex, a3);
		}
		return tIndex - sStart;

	}

	// TIE_CONSUMER2_SHORT: WHILE, [SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=char a3, type=SA}, SourcePurpose{arg=LTieCharFunction<? super T>
	// consumer, type=CONST}]
	public static <T, C3, I3> int tieIterate(T trg1, SequentialRead<C3, I3, aChar> sa3, C3 source3, LTieCharFunction<? super T> consumer) {
		LFunction<C3, I3> toIntermediate = sa3.adapter();
		return tieForEach(0, 0, trg1, toIntermediate.doApply(source3), sa3.tester(), sa3.getter(), consumer);
	}

	// TARGETED_INDEXED_ITERATE: WHILE, [SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=char a3, type=SA}, SourcePurpose{arg=LTieCharFunction<? super
	// T> consumer, type=CONST}]
	public static <T, C3, I3> T tiIterate(T trg1, SequentialRead<C3, I3, aChar> sa3, C3 source3, LTieCharFunction<? super T> consumer) {

		tieIterate(trg1, sa3, source3, consumer);

		return trg1;

	}

	// TARGETED_INDEXED_ITERATE_NEW: WHILE, [SourcePurpose{arg=T trg1, type=SUPPLIER}, SourcePurpose{arg=char a3, type=SA}, SourcePurpose{arg=LTieCharFunction<?
	// super T> consumer, type=CONST}]
	public static <T, C3, I3> T ntiIterate(LSupplier<T> source1, SequentialRead<C3, I3, aChar> sa3, C3 source3, LTieCharFunction<? super T> consumer) {
		T trg1 = source1.doGet();
		tieIterate(trg1, sa3, source3, consumer);

		return trg1;

	}

	// MAP: FOR, [SourcePurpose{arg=T a1, type=IA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=char a3, type=IA}, SourcePurpose{arg=LIntConsumer
	// consumer, type=CONST}]
	default <C1, C2, C3> void forEach(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, IndexedRead<C3, aChar> ia3, C3 source3, LIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToCharFunction<Object> oiFunc3 = (LOiToCharFunction) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			T a1 = oiFunc1.doApply(source1, i);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			char a3 = oiFunc3.doApplyAsChar(source3, i);
			consumer.doAccept(this.doApplyAsInt(a1, a2, a3));
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T a1, type=SA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=char a3, type=IA}, SourcePurpose{arg=LIntConsumer
	// consumer, type=CONST}]
	default <C1, I1, C2, C3> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, IndexedRead<C3, aChar> ia3, C3 source3, LIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToCharFunction<Object> oiFunc3 = (LOiToCharFunction) ia3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size) {
			T a1 = nextFunc1.doApply(iterator1);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			char a3 = oiFunc3.doApplyAsChar(source3, i);
			consumer.doAccept(this.doApplyAsInt(a1, a2, a3));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T a1, type=IA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=char a3, type=IA}, SourcePurpose{arg=LIntConsumer
	// consumer, type=CONST}]
	default <C1, C2, I2, C3> void iterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, IndexedRead<C3, aChar> ia3, C3 source3, LIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToCharFunction<Object> oiFunc3 = (LOiToCharFunction) ia3.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2)) {
			T a1 = oiFunc1.doApply(source1, i);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			char a3 = oiFunc3.doApplyAsChar(source3, i);
			consumer.doAccept(this.doApplyAsInt(a1, a2, a3));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T a1, type=SA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=char a3, type=IA}, SourcePurpose{arg=LIntConsumer
	// consumer, type=CONST}]
	default <C1, I1, C2, I2, C3> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, IndexedRead<C3, aChar> ia3, C3 source3, LIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		int size = ia3.size(source3);
		LOiToCharFunction<Object> oiFunc3 = (LOiToCharFunction) ia3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2) && i < size) {
			T a1 = nextFunc1.doApply(iterator1);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			char a3 = oiFunc3.doApplyAsChar(source3, i);
			consumer.doAccept(this.doApplyAsInt(a1, a2, a3));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T a1, type=IA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=char a3, type=SA}, SourcePurpose{arg=LIntConsumer
	// consumer, type=CONST}]
	default <C1, C2, C3, I3> void iterate(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, SequentialRead<C3, I3, aChar> sa3, C3 source3, LIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToCharFunction<Object> nextFunc3 = (LToCharFunction) sa3.getter();
		int i = 0;
		while (i < size && testFunc3.doTest(iterator3)) {
			T a1 = oiFunc1.doApply(source1, i);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			char a3 = nextFunc3.doApplyAsChar(iterator3);
			consumer.doAccept(this.doApplyAsInt(a1, a2, a3));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T a1, type=SA}, SourcePurpose{arg=int a2, type=IA}, SourcePurpose{arg=char a3, type=SA}, SourcePurpose{arg=LIntConsumer
	// consumer, type=CONST}]
	default <C1, I1, C2, C3, I3> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, SequentialRead<C3, I3, aChar> sa3, C3 source3, LIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.getter();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToCharFunction<Object> nextFunc3 = (LToCharFunction) sa3.getter();
		int i = 0;
		while (testFunc1.doTest(iterator1) && i < size && testFunc3.doTest(iterator3)) {
			T a1 = nextFunc1.doApply(iterator1);
			int a2 = oiFunc2.doApplyAsInt(source2, i);
			char a3 = nextFunc3.doApplyAsChar(iterator3);
			consumer.doAccept(this.doApplyAsInt(a1, a2, a3));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T a1, type=IA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=char a3, type=SA}, SourcePurpose{arg=LIntConsumer
	// consumer, type=CONST}]
	default <C1, C2, I2, C3, I3> void iterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, SequentialRead<C3, I3, aChar> sa3, C3 source3, LIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToCharFunction<Object> nextFunc3 = (LToCharFunction) sa3.getter();
		int i = 0;
		while (i < size && testFunc2.doTest(iterator2) && testFunc3.doTest(iterator3)) {
			T a1 = oiFunc1.doApply(source1, i);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			char a3 = nextFunc3.doApplyAsChar(iterator3);
			consumer.doAccept(this.doApplyAsInt(a1, a2, a3));
			i++;
		}
	}

	// MAP: WHILE, [SourcePurpose{arg=T a1, type=SA}, SourcePurpose{arg=int a2, type=SA}, SourcePurpose{arg=char a3, type=SA}, SourcePurpose{arg=LIntConsumer
	// consumer, type=CONST}]
	default <C1, I1, C2, I2, C3, I3> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, SequentialRead<C3, I3, aChar> sa3, C3 source3, LIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).doApply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).doApply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).doApply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToCharFunction<Object> nextFunc3 = (LToCharFunction) sa3.getter();
		while (testFunc1.doTest(iterator1) && testFunc2.doTest(iterator2) && testFunc3.doTest(iterator3)) {
			T a1 = nextFunc1.doApply(iterator1);
			int a2 = nextFunc2.doApplyAsInt(iterator2);
			char a3 = nextFunc3.doApplyAsChar(iterator3);
			consumer.doAccept(this.doApplyAsInt(a1, a2, a3));
		}
	}

}
