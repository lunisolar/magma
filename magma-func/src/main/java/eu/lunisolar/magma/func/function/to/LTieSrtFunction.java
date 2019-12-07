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
 * Non-throwing functional interface (lambda) LTieSrtFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 3): T a1,int a2,short a3
 *
 * Co-domain: int
 *
 * Special case of function that corresponds to TIE consumer with return integer value.
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LTieSrtFunction<T> extends MetaFunction, MetaInterface.NonThrowing, TieFunction<T, aShort>, Codomain<aInt>, Domain3<a<T>, aInt, aShort> { // NOSONAR

	String DESCRIPTION = "LTieSrtFunction: int applyAsInt(T a1,int a2,short a3)";

	// int applyAsInt(T a1,int a2,short a3) ;
	default int applyAsInt(T a1, int a2, short a3) {
		// return nestingApplyAsInt(a1,a2,a3);
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsInt(T a1,int a2,short a3)
	 */
	int applyAsIntX(T a1, int a2, short a3) throws Throwable;

	default int tupleApplyAsInt(LObjIntSrtTriple<T> args) {
		return applyAsInt(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions according to the instructions. */
	default int handlingApplyAsInt(T a1, int a2, short a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LTieSrtFunction<T> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> handlingApplyAsInt(a1, a2, a3, handling);
	}

	default int applyAsInt(T a1, int a2, short a3, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LTieSrtFunction<T> trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return (a1, a2, a3) -> applyAsInt(a1, a2, a3, exF, newMessage, messageParams);
	}

	default int applyAsInt(T a1, int a2, short a3, @Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LTieSrtFunction<T> trying(@Nonnull ExWF<RuntimeException> exF) {
		return (a1, a2, a3) -> applyAsInt(a1, a2, a3, exF);
	}

	default int applyAsIntThen(T a1, int a2, short a3, @Nonnull LToIntFunction<Throwable> handler) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsInt(e);
		}
	}

	default LTieSrtFunction<T> tryingThen(@Nonnull LToIntFunction<Throwable> handler) {
		return (a1, a2, a3) -> applyAsIntThen(a1, a2, a3, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default int nestingApplyAsInt(T a1, int a2, short a3) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default int shovingApplyAsInt(T a1, int a2, short a3) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> int handlingApplyAsInt(T a1, int a2, short a3, LTieSrtFunction<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsInt(a1, a2, a3, handling);
	}

	static <T> int tryApplyAsInt(T a1, int a2, short a3, LTieSrtFunction<T> func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsInt(a1, a2, a3);
	}

	static <T> int tryApplyAsInt(T a1, int a2, short a3, LTieSrtFunction<T> func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.applyAsInt(a1, a2, a3, exF, newMessage, messageParams);
	}

	static <T> int tryApplyAsInt(T a1, int a2, short a3, LTieSrtFunction<T> func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.applyAsInt(a1, a2, a3, exF);
	}

	static <T> int tryApplyAsIntThen(T a1, int a2, short a3, LTieSrtFunction<T> func, @Nonnull LToIntFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsIntThen(a1, a2, a3, handler);
	}

	default int failSafeApplyAsInt(T a1, int a2, short a3, @Nonnull LTieSrtFunction<T> failSafe) {
		try {
			return applyAsInt(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.applyAsInt(a1, a2, a3);
		}
	}

	static <T> int failSafeApplyAsInt(T a1, int a2, short a3, LTieSrtFunction<T> func, @Nonnull LTieSrtFunction<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.applyAsInt(a1, a2, a3);
		} else {
			return func.failSafeApplyAsInt(a1, a2, a3, failSafe);
		}
	}

	static <T> LTieSrtFunction<T> failSafe(LTieSrtFunction<T> func, @Nonnull LTieSrtFunction<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeApplyAsInt(a1, a2, a3, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default int nonNullApplyAsInt(T a1, int a2, short a3) {
		return applyAsInt(a1, a2, a3);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LTieSrtFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_a2, int max_a2, T a1, short a3, @Nonnull LTieSrtFunction<T> func) {
		Null.nonNullArg(func, "func");
		if (min_a2 <= max_a2) {
			for (int a2 = min_a2; a2 <= max_a2; a2++) {
				func.applyAsInt(a1, a2, a3);
			}
		} else {
			for (int a2 = min_a2; a2 >= max_a2; a2--) {
				func.applyAsInt(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTill(int min_a2, int max_a2, T a1, short a3, @Nonnull LTieSrtFunction<T> func) {
		Null.nonNullArg(func, "func");
		if (min_a2 <= max_a2) {
			for (int a2 = min_a2; a2 < max_a2; a2++) {
				func.applyAsInt(a1, a2, a3);
			}
		} else {
			for (int a2 = min_a2; a2 > max_a2; a2--) {
				func.applyAsInt(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void times(int max_a2, T a1, short a3, @Nonnull LTieSrtFunction<T> func) {
		if (max_a2 < 0)
			return;
		fromTill(0, max_a2, a1, a3, func);
	}

	/** Extract and apply function. */
	public static <M, K, V> int from(@Nonnull M container, LBiFunction<M, K, V> extractor, K key, int a2, short a3, @Nonnull LTieSrtFunction<V> function, int orElse) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.applyAsInt(value, a2, a3);
		}

		return orElse;
	}

	public default LOiToIntFunction<T> rShrink(@Nonnull LOiToSrtFunction<T> right) {
		Null.nonNullArg(right, "right");
		return (a1, a2) -> applyAsInt(a1, a2, right.applyAsSrt(a1, a2));
	}

	public default LOiToIntFunction<T> rShrink_(short a3) {
		return (a1, a2) -> applyAsInt(a1, a2, a3);
	}

	public static <T> LOiToIntFunction<T> rShrunken(@Nonnull LOiToSrtFunction<T> right, @Nonnull LTieSrtFunction<T> func) {
		Null.nonNullArg(right, "right");
		Null.nonNullArg(func, "func");
		return func.rShrink(right);
	}

	public static <T> LOiToIntFunction<T> rShrunken_(short a3, @Nonnull LTieSrtFunction<T> func) {
		Null.nonNullArg(func, "func");
		return func.rShrink_(a3);
	}

	/**  */
	public static <T> LTieSrtFunction<T> uncurry(@Nonnull LFunction<T, LIntFunction<LSrtToIntFunction>> func) {
		Null.nonNullArg(func, "func");
		return (T a1, int a2, short a3) -> func.apply(a1).apply(a2).applyAsInt(a3);
	}

	/** Cast that removes generics. */
	public default LTieSrtFunction untyped() {
		return this;
	}

	/** Cast that replace generics. */
	public default <V2> LTieSrtFunction<V2> cast() {
		return untyped();
	}

	/** Cast that replace generics. */
	public static <V2, T> LTieSrtFunction<V2> cast(LTieSrtFunction<T> function) {
		return (LTieSrtFunction) function;
	}

	/** Change function to consumer that ignores output. */
	public default LTieSrtConsumer<T> toConsumer() {
		return this::applyAsInt;
	}

	/** Calls domain consumer before main function. */
	public default LTieSrtFunction<T> beforeDo(@Nonnull LTieSrtConsumer<T> before) {
		Null.nonNullArg(before, "before");
		return (T a1, int a2, short a3) -> {
			before.accept(a1, a2, a3);
			return applyAsInt(a1, a2, a3);
		};
	}

	/** Calls codomain consumer after main function. */
	public default LTieSrtFunction<T> afterDo(@Nonnull LIntConsumer after) {
		Null.nonNullArg(after, "after");
		return (T a1, int a2, short a3) -> {
			final int retval = applyAsInt(a1, a2, a3);
			after.accept(retval);
			return retval;
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LIntSupplier capture(T a1, int a2, short a3) {
		return () -> this.applyAsInt(a1, a2, a3);
	}

	/** Creates function that always returns the same value. */
	static <T> LTieSrtFunction<T> constant(int r) {
		return (a1, a2, a3) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T> LTieSrtFunction<T> apply1stAsInt(@Nonnull LToIntFunction<T> func) {
		return (a1, a2, a3) -> func.applyAsInt(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T> LTieSrtFunction<T> apply2ndAsInt(@Nonnull LIntUnaryOperator func) {
		return (a1, a2, a3) -> func.applyAsInt(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T> LTieSrtFunction<T> apply3rdAsInt(@Nonnull LSrtToIntFunction func) {
		return (a1, a2, a3) -> func.applyAsInt(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LTieSrtFunction<T> tieSrtFunc(final @Nonnull LTieSrtFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** A completely inconvenient method in case lambda expression and generic arguments are ambiguous for the compiler. */
	@Nonnull
	static <T> LTieSrtFunction<T> tieSrtFunc(@Nullable Class<T> c1, final @Nonnull LTieSrtFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T> LTieSrtFunction<T> recursive(final @Nonnull LFunction<LTieSrtFunction<T>, LTieSrtFunction<T>> selfLambda) {
		final LTieSrtFunctionSingle<T> single = new LTieSrtFunctionSingle();
		LTieSrtFunction<T> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LTieSrtFunctionSingle<T> implements LTieSrtFunction<T> {
		private LTieSrtFunction<T> target = null;

		@Override
		public int applyAsIntX(T a1, int a2, short a3) throws Throwable {
			return target.applyAsIntX(a1, a2, a3);
		}

	}

	@Nonnull
	static <T> LTieSrtFunction<T> tieSrtFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T> LTieSrtFunction<T> tieSrtFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LTieSrtFunction.LObjSrtIntToIntFunc<T> objSrtIntToIntFunc(final @Nonnull LTieSrtFunction.LObjSrtIntToIntFunc<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LTieSrtFunction.LIntObjSrtToIntFunc<T> intObjSrtToIntFunc(final @Nonnull LTieSrtFunction.LIntObjSrtToIntFunc<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LTieSrtFunction.LIntSrtObjToIntFunc<T> intSrtObjToIntFunc(final @Nonnull LTieSrtFunction.LIntSrtObjToIntFunc<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LTieSrtFunction.LSrtObjIntToIntFunc<T> srtObjIntToIntFunc(final @Nonnull LTieSrtFunction.LSrtObjIntToIntFunc<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LTieSrtFunction.LSrtIntObjToIntFunc<T> srtIntObjToIntFunc(final @Nonnull LTieSrtFunction.LSrtIntObjToIntFunc<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T> int call(T a1, int a2, short a3, final @Nonnull LTieSrtFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsInt(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as doNothing). */
	@Nonnull
	static <T> LTieSrtFunction<T> safe() {
		return LTieSrtFunction::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LTieSrtFunction<T>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T> LTieSrtFunction<T> safe(final @Nullable LTieSrtFunction<T> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LTieSrtFunction<T>> safeSupplier(final @Nullable LSupplier<LTieSrtFunction<T>> supplier) {
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
	default <V1> LTieSrtFunction<V1> compose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2, @Nonnull final LSrtUnaryOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.applyAsInt(before1.apply(v1), before2.applyAsInt(v2), before3.applyAsSrt(v3));
	}

	public static <V1, T> LTieSrtFunction<V1> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2, @Nonnull final LSrtUnaryOperator before3, LTieSrtFunction<T> after) {
		return after.compose(before1, before2, before3);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LToIntTriFunction<V1, V2, V3> tieSrtFuncCompose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2, @Nonnull final LToSrtFunction<? super V3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.applyAsInt(before1.apply(v1), before2.applyAsInt(v2), before3.applyAsSrt(v3));
	}

	public static <V1, V2, V3, T> LToIntTriFunction<V1, V2, V3> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2, @Nonnull final LToSrtFunction<? super V3> before3,
			LTieSrtFunction<T> after) {
		return after.tieSrtFuncCompose(before1, before2, before3);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LObjIntSrtFunction<T, V> then(@Nonnull LIntFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.apply(this.applyAsInt(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LTieSrtFunction<T> thenToInt(@Nonnull LIntUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.applyAsInt(this.applyAsInt(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LObjIntSrtPredicate<T> thenToBool(@Nonnull LIntPredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.test(this.applyAsInt(a1, a2, a3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LTieSrtFunction for method references. */
	@FunctionalInterface
	interface LObjSrtIntToIntFunc<T> extends LTieSrtFunction<T> {

		/**
		 * Implement this, but call applyAsInt(T a1,int a2,short a3)
		 */
		default int applyAsIntX(T a1, int a2, short a3) {
			return this.applyAsIntObjSrtInt(a1, a3, a2);
		}

		// int applyAsIntObjSrtInt(T a1,short a3,int a2) ;
		default int applyAsIntObjSrtInt(T a1, short a3, int a2) {
			// return nestingApplyAsIntObjSrtInt(a1,a3,a2);
			try {
				return this.applyAsIntObjSrtIntX(a1, a3, a2);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyAsIntObjSrtInt(T a1,short a3,int a2)
		 */
		int applyAsIntObjSrtIntX(T a1, short a3, int a2) throws Throwable;
	}

	/** Permutation of LTieSrtFunction for method references. */
	@FunctionalInterface
	interface LIntObjSrtToIntFunc<T> extends LTieSrtFunction<T> {

		/**
		 * Implement this, but call applyAsIntObjSrtInt(T a1,short a3,int a2)
		 */
		default int applyAsIntX(T a1, int a2, short a3) {
			return this.applyAsIntIntObjSrt(a2, a1, a3);
		}

		// int applyAsIntIntObjSrt(int a2,T a1,short a3) ;
		default int applyAsIntIntObjSrt(int a2, T a1, short a3) {
			// return nestingApplyAsIntIntObjSrt(a2,a1,a3);
			try {
				return this.applyAsIntIntObjSrtX(a2, a1, a3);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyAsIntIntObjSrt(int a2,T a1,short a3)
		 */
		int applyAsIntIntObjSrtX(int a2, T a1, short a3) throws Throwable;
	}

	/** Permutation of LTieSrtFunction for method references. */
	@FunctionalInterface
	interface LIntSrtObjToIntFunc<T> extends LTieSrtFunction<T> {

		/**
		 * Implement this, but call applyAsIntIntObjSrt(int a2,T a1,short a3)
		 */
		default int applyAsIntX(T a1, int a2, short a3) {
			return this.applyAsIntIntSrtObj(a2, a3, a1);
		}

		// int applyAsIntIntSrtObj(int a2,short a3,T a1) ;
		default int applyAsIntIntSrtObj(int a2, short a3, T a1) {
			// return nestingApplyAsIntIntSrtObj(a2,a3,a1);
			try {
				return this.applyAsIntIntSrtObjX(a2, a3, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyAsIntIntSrtObj(int a2,short a3,T a1)
		 */
		int applyAsIntIntSrtObjX(int a2, short a3, T a1) throws Throwable;
	}

	/** Permutation of LTieSrtFunction for method references. */
	@FunctionalInterface
	interface LSrtObjIntToIntFunc<T> extends LTieSrtFunction<T> {

		/**
		 * Implement this, but call applyAsIntIntSrtObj(int a2,short a3,T a1)
		 */
		default int applyAsIntX(T a1, int a2, short a3) {
			return this.applyAsIntSrtObjInt(a3, a1, a2);
		}

		// int applyAsIntSrtObjInt(short a3,T a1,int a2) ;
		default int applyAsIntSrtObjInt(short a3, T a1, int a2) {
			// return nestingApplyAsIntSrtObjInt(a3,a1,a2);
			try {
				return this.applyAsIntSrtObjIntX(a3, a1, a2);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyAsIntSrtObjInt(short a3,T a1,int a2)
		 */
		int applyAsIntSrtObjIntX(short a3, T a1, int a2) throws Throwable;
	}

	/** Permutation of LTieSrtFunction for method references. */
	@FunctionalInterface
	interface LSrtIntObjToIntFunc<T> extends LTieSrtFunction<T> {

		/**
		 * Implement this, but call applyAsIntSrtObjInt(short a3,T a1,int a2)
		 */
		default int applyAsIntX(T a1, int a2, short a3) {
			return this.applyAsIntSrtIntObj(a3, a2, a1);
		}

		// int applyAsIntSrtIntObj(short a3,int a2,T a1) ;
		default int applyAsIntSrtIntObj(short a3, int a2, T a1) {
			// return nestingApplyAsIntSrtIntObj(a3,a2,a1);
			try {
				return this.applyAsIntSrtIntObjX(a3, a2, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyAsIntSrtIntObj(short a3,int a2,T a1)
		 */
		int applyAsIntSrtIntObjX(short a3, int a2, T a1) throws Throwable;
	}

	// </editor-fold>

	/** Does nothing (LTieSrtFunction) Function */
	public static <T> int doNothing(T a1, int a2, short a3) {
		return Function4U.defaultInteger;
	}

	/** Does nothing (LTieSrtFunction.LObjSrtIntToIntFunc) Function */
	public static <T> int doNothing(T a1, short a3, int a2) {
		return Function4U.defaultInteger;
	}

	/** Does nothing (LTieSrtFunction.LIntObjSrtToIntFunc) Function */
	public static <T> int doNothing(int a2, T a1, short a3) {
		return Function4U.defaultInteger;
	}

	/** Does nothing (LTieSrtFunction.LIntSrtObjToIntFunc) Function */
	public static <T> int doNothing(int a2, short a3, T a1) {
		return Function4U.defaultInteger;
	}

	/** Does nothing (LTieSrtFunction.LSrtObjIntToIntFunc) Function */
	public static <T> int doNothing(short a3, T a1, int a2) {
		return Function4U.defaultInteger;
	}

	/** Does nothing (LTieSrtFunction.LSrtIntObjToIntFunc) Function */
	public static <T> int doNothing(short a3, int a2, T a1) {
		return Function4U.defaultInteger;
	}

	/** ***ITERATION:    TIE_CONSUMER_GEN:  FOR, [SourcePurpose{arg=int sStart, type=CONST}, SourcePurpose{arg=int sEnd, type=CONST}, SourcePurpose{arg=int tStart, type=CONST}, SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=short a3, type=TIE_SOURCE}, SourcePurpose{arg=short a3, type=TIE_GEN_SUPPLIER}] */
	default <SRC> int genericTieForEach(int sStart, int sEnd, int tStart, T trg1, SRC src3, OiFunction<SRC, aShort> srcAcc3) {
		return tieForEach(sStart, sEnd, tStart, trg1, src3, (LOiToSrtFunction<SRC>) srcAcc3, this);

	}

	/** ***ITERATION:    TARGETED_INDEXED_FOR_EACH:  FOR, [SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=short a3, type=IA}, SourcePurpose{arg=LTieSrtFunction<? super T> consumer, type=CONST}] */
	public static <T, C3> T tiForEach(T trg1, IndexedRead<C3, aShort> ia3, C3 source3, LTieSrtFunction<? super T> consumer) {

		tieForEach(trg1, ia3, source3, consumer);

		return trg1;

	}

	/** ***ITERATION:    TARGETED_INDEXED_FOR_EACH_NEW:  FOR, [SourcePurpose{arg=T trg1, type=SIZE_FACTORY}, SourcePurpose{arg=short a3, type=IA}, SourcePurpose{arg=LTieSrtFunction<? super T> consumer, type=CONST}] */
	public static <T, C3> T ntiForEach(LIntFunction<T> trgFactory1, IndexedRead<C3, aShort> ia3, C3 source3, LTieSrtFunction<? super T> consumer) {
		int size = ia3.size(source3);
		T trg1 = trgFactory1.apply(size);
		tieForEach(0, size, 0, trg1, source3, ia3.getter(), consumer);
		return trg1;
	}

	/** ***ITERATION:    TIE_CONSUMER_SHORT:  FOR, [SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=short a3, type=IA}, SourcePurpose{arg=LTieSrtFunction<? super T> consumer, type=CONST}] */
	public static <T, C3> int tieForEach(T trg1, IndexedRead<C3, aShort> ia3, C3 source3, LTieSrtFunction<? super T> consumer) {
		int size = ia3.size(source3);
		return tieForEach(0, size, 0, trg1, source3, ia3.getter(), consumer);
	}

	/** ***ITERATION:    TIE_CONSUMER:  FOR, [SourcePurpose{arg=int sStart, type=CONST}, SourcePurpose{arg=int sEnd, type=CONST}, SourcePurpose{arg=int tStart, type=CONST}, SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=short a3, type=TIE_SOURCE}, SourcePurpose{arg=short a3, type=TIE_SUPPLIER}, SourcePurpose{arg=LTieSrtFunction<? super T> consumer, type=CONST}] */
	public static <T, SRC> int tieForEach(int sStart, int sEnd, int tStart, T trg1, SRC src3, LOiToSrtFunction<SRC> srcAcc3, LTieSrtFunction<? super T> consumer) {
		int tIndex = tStart;
		for (int sIndex = sStart; sIndex < sEnd; sIndex++) {
			short a3 = srcAcc3.applyAsSrt(src3, sIndex);
			tIndex += consumer.applyAsInt(trg1, tIndex, a3);
		}
		return tIndex - tStart;

	}

	/** ***ITERATION:    TIE_CONSUMER2_GEN:  FOR, [SourcePurpose{arg=int sStart, type=CONST}, SourcePurpose{arg=int tStart, type=CONST}, SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=short a3, type=TIE_SOURCE}, SourcePurpose{arg=short a3, type=TE_GEN_PREDICATE}, SourcePurpose{arg=short a3, type=TE_GEN_SUPPLIER}] */
	public default <SRC> int genericTieForEach(int sStart, int tStart, T trg1, SRC src3, OFunction<SRC, aBool> srcTest3, OFunction<SRC, aShort> srcAcc3) {
		return tieForEach(sStart, tStart, trg1, src3, (LPredicate<SRC>) srcTest3, (LToSrtFunction<SRC>) srcAcc3, this);

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with TIE: 'target', index, element). First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns increment count based on consumer function
	*/
	public static <T, SRC> int tieForEach(int sStart, int tStart, T trg1, SRC src3, LPredicate<SRC> srcTest3, LToSrtFunction<SRC> srcAcc3, LTieSrtFunction<? super T> consumer) {
		int tIndex = tStart;
		for (; srcTest3.test(src3); tIndex++) {
			short a3 = srcAcc3.applyAsSrt(src3);
			tIndex += consumer.applyAsInt(trg1, tIndex, a3);
		}
		return tIndex - sStart;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with TIE: 'target', index, element). First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns increment count based on consumer function
	*/
	public static <T, C3, I3> int tieIterate(T trg1, SequentialRead<C3, I3, aShort> sa3, C3 source3, LTieSrtFunction<? super T> consumer) {
		LFunction<C3, I3> toIntermediate = sa3.adapter();
		return tieForEach(0, 0, trg1, toIntermediate.apply(source3), sa3.tester(), sa3.supplier(), consumer);
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with TIE: 'target', index, element). First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns 'target' object
	*/
	public static <T, C3, I3> T tiIterate(T trg1, SequentialRead<C3, I3, aShort> sa3, C3 source3, LTieSrtFunction<? super T> consumer) {

		tieIterate(trg1, sa3, source3, consumer);

		return trg1;

	}

	/** ***ITERATION:    TARGETED_INDEXED_ITERATE_NEW:  WHILE, [SourcePurpose{arg=T trg1, type=SUPPLIER}, SourcePurpose{arg=short a3, type=SA}, SourcePurpose{arg=LTieSrtFunction<? super T> consumer, type=CONST}] */
	public static <T, C3, I3> T ntiIterate(LSupplier<T> source1, SequentialRead<C3, I3, aShort> sa3, C3 source3, LTieSrtFunction<? super T> consumer) {
		T trg1 = source1.get();
		tieIterate(trg1, sa3, source3, consumer);

		return trg1;

	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, C3> void forEach(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, IndexedRead<C3, aShort> ia3, C3 source3, LIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToSrtFunction<Object> oiFunc3 = (LOiToSrtFunction) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = oiFunc2.applyAsInt(source2, i);
			short a3 = oiFunc3.applyAsSrt(source3, i);
			consumer.accept(this.applyAsInt(a1, a2, a3));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, C3> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, IndexedRead<C3, aShort> ia3, C3 source3, LIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToSrtFunction<Object> oiFunc3 = (LOiToSrtFunction) ia3.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = oiFunc2.applyAsInt(source2, i);
			short a3 = oiFunc3.applyAsSrt(source3, i);
			consumer.accept(this.applyAsInt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2, C3> void iterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, IndexedRead<C3, aShort> ia3, C3 source3, LIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		size = Integer.min(size, ia3.size(source3));
		LOiToSrtFunction<Object> oiFunc3 = (LOiToSrtFunction) ia3.getter();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = nextFunc2.applyAsInt(iterator2);
			short a3 = oiFunc3.applyAsSrt(source3, i);
			consumer.accept(this.applyAsInt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, I2, C3> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, IndexedRead<C3, aShort> ia3, C3 source3, LIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		int size = ia3.size(source3);
		LOiToSrtFunction<Object> oiFunc3 = (LOiToSrtFunction) ia3.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && i < size) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = nextFunc2.applyAsInt(iterator2);
			short a3 = oiFunc3.applyAsSrt(source3, i);
			consumer.accept(this.applyAsInt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, C3, I3> void iterate(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, SequentialRead<C3, I3, aShort> sa3, C3 source3, LIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToSrtFunction<Object> nextFunc3 = (LToSrtFunction) sa3.supplier();
		int i = 0;
		while (i < size && testFunc3.test(iterator3)) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = oiFunc2.applyAsInt(source2, i);
			short a3 = nextFunc3.applyAsSrt(iterator3);
			consumer.accept(this.applyAsInt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, C3, I3> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, SequentialRead<C3, I3, aShort> sa3, C3 source3, LIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToSrtFunction<Object> nextFunc3 = (LToSrtFunction) sa3.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size && testFunc3.test(iterator3)) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = oiFunc2.applyAsInt(source2, i);
			short a3 = nextFunc3.applyAsSrt(iterator3);
			consumer.accept(this.applyAsInt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2, C3, I3> void iterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, SequentialRead<C3, I3, aShort> sa3, C3 source3, LIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToSrtFunction<Object> nextFunc3 = (LToSrtFunction) sa3.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = nextFunc2.applyAsInt(iterator2);
			short a3 = nextFunc3.applyAsSrt(iterator3);
			consumer.accept(this.applyAsInt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C1, I1, C2, I2, C3, I3> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, SequentialRead<C3, I3, aShort> sa3, C3 source3, LIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToSrtFunction<Object> nextFunc3 = (LToSrtFunction) sa3.supplier();
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = nextFunc2.applyAsInt(iterator2);
			short a3 = nextFunc3.applyAsSrt(iterator3);
			consumer.accept(this.applyAsInt(a1, a2, a3));
		}
	}

}
