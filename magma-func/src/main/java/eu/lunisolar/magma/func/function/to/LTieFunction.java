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
 * Non-throwing functional interface (lambda) LTieFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 3): T1 a1,int a2,T2 a3
 *
 * Co-domain: int
 *
 * Special case of function that corresponds to TIE consumer with return integer value.
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LTieFunction<T1, T2> extends MetaFunction, MetaInterface.NonThrowing, TieFunction<T1, a<T2>>, Codomain<aInt>, Domain3<a<T1>, aInt, a<T2>> { // NOSONAR

	String DESCRIPTION = "LTieFunction: int applyAsInt(T1 a1,int a2,T2 a3)";

	// int applyAsInt(T1 a1,int a2,T2 a3) ;
	default int applyAsInt(T1 a1, int a2, T2 a3) {
		// return nestingApplyAsInt(a1,a2,a3);
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsInt(T1 a1,int a2,T2 a3)
	 */
	int applyAsIntX(T1 a1, int a2, T2 a3) throws Throwable;

	default int tupleApplyAsInt(LObjIntObjTriple<T1, T2> args) {
		return applyAsInt(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions according to the instructions. */
	default int handlingApplyAsInt(T1 a1, int a2, T2 a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LTieFunction<T1, T2> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> handlingApplyAsInt(a1, a2, a3, handling);
	}

	default int applyAsInt(T1 a1, int a2, T2 a3, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LTieFunction<T1, T2> trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return (a1, a2, a3) -> applyAsInt(a1, a2, a3, exF, newMessage, messageParams);
	}

	default int applyAsInt(T1 a1, int a2, T2 a3, @Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LTieFunction<T1, T2> trying(@Nonnull ExWF<RuntimeException> exF) {
		return (a1, a2, a3) -> applyAsInt(a1, a2, a3, exF);
	}

	default int applyAsIntThen(T1 a1, int a2, T2 a3, @Nonnull LToIntFunction<Throwable> handler) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsInt(e);
		}
	}

	default LTieFunction<T1, T2> tryingThen(@Nonnull LToIntFunction<Throwable> handler) {
		return (a1, a2, a3) -> applyAsIntThen(a1, a2, a3, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default int nestingApplyAsInt(T1 a1, int a2, T2 a3) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default int shovingApplyAsInt(T1 a1, int a2, T2 a3) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T1, T2> int handlingApplyAsInt(T1 a1, int a2, T2 a3, LTieFunction<T1, T2> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsInt(a1, a2, a3, handling);
	}

	static <T1, T2> int tryApplyAsInt(T1 a1, int a2, T2 a3, LTieFunction<T1, T2> func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsInt(a1, a2, a3);
	}

	static <T1, T2> int tryApplyAsInt(T1 a1, int a2, T2 a3, LTieFunction<T1, T2> func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.applyAsInt(a1, a2, a3, exF, newMessage, messageParams);
	}

	static <T1, T2> int tryApplyAsInt(T1 a1, int a2, T2 a3, LTieFunction<T1, T2> func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.applyAsInt(a1, a2, a3, exF);
	}

	static <T1, T2> int tryApplyAsIntThen(T1 a1, int a2, T2 a3, LTieFunction<T1, T2> func, @Nonnull LToIntFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsIntThen(a1, a2, a3, handler);
	}

	default int failSafeApplyAsInt(T1 a1, int a2, T2 a3, @Nonnull LTieFunction<T1, T2> failSafe) {
		try {
			return applyAsInt(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.applyAsInt(a1, a2, a3);
		}
	}

	static <T1, T2> int failSafeApplyAsInt(T1 a1, int a2, T2 a3, LTieFunction<T1, T2> func, @Nonnull LTieFunction<T1, T2> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.applyAsInt(a1, a2, a3);
		} else {
			return func.failSafeApplyAsInt(a1, a2, a3, failSafe);
		}
	}

	static <T1, T2> LTieFunction<T1, T2> failSafe(LTieFunction<T1, T2> func, @Nonnull LTieFunction<T1, T2> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeApplyAsInt(a1, a2, a3, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default int nonNullApplyAsInt(T1 a1, int a2, T2 a3) {
		return applyAsInt(a1, a2, a3);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LTieFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void fromTo(int min_a2, int max_a2, T1 a1, T2 a3, @Nonnull LTieFunction<T1, T2> func) {
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
	public static <T1, T2> void fromTill(int min_a2, int max_a2, T1 a1, T2 a3, @Nonnull LTieFunction<T1, T2> func) {
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
	public static <T1, T2> void times(int max_a2, T1 a1, T2 a3, @Nonnull LTieFunction<T1, T2> func) {
		if (max_a2 < 0)
			return;
		fromTill(0, max_a2, a1, a3, func);
	}

	/** Extract and apply function. */
	public static <M, K, V, T2> int from(@Nonnull M container, LBiFunction<M, K, V> extractor, K key, int a2, T2 a3, @Nonnull LTieFunction<V, T2> function, int orElse) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.applyAsInt(value, a2, a3);
		}

		return orElse;
	}

	public default LOiToIntFunction<T1> rShrink(@Nonnull LOiFunction<T1, T2> right) {
		Null.nonNullArg(right, "right");
		return (a1, a2) -> applyAsInt(a1, a2, right.apply(a1, a2));
	}

	public default LOiToIntFunction<T1> rShrink_(T2 a3) {
		return (a1, a2) -> applyAsInt(a1, a2, a3);
	}

	public static <T1, T2> LOiToIntFunction<T1> rShrunken(@Nonnull LOiFunction<T1, T2> right, @Nonnull LTieFunction<T1, T2> func) {
		Null.nonNullArg(right, "right");
		Null.nonNullArg(func, "func");
		return func.rShrink(right);
	}

	public static <T1, T2> LOiToIntFunction<T1> rShrunken_(T2 a3, @Nonnull LTieFunction<T1, T2> func) {
		Null.nonNullArg(func, "func");
		return func.rShrink_(a3);
	}

	/**  */
	public static <T1, T2> LTieFunction<T1, T2> uncurry(@Nonnull LFunction<T1, LIntFunction<LToIntFunction<T2>>> func) {
		Null.nonNullArg(func, "func");
		return (T1 a1, int a2, T2 a3) -> func.apply(a1).apply(a2).applyAsInt(a3);
	}

	/** Cast that removes generics. */
	public default LTieFunction untyped() {
		return this;
	}

	/** Cast that replace generics. */
	public default <V2, V3> LTieFunction<V2, V3> cast() {
		return untyped();
	}

	/** Cast that replace generics. */
	public static <V2, V3, T1, T2> LTieFunction<V2, V3> cast(LTieFunction<T1, T2> function) {
		return (LTieFunction) function;
	}

	/** Change function to consumer that ignores output. */
	public default LTieConsumer<T1, T2> toConsumer() {
		return this::applyAsInt;
	}

	/** Calls domain consumer before main function. */
	public default LTieFunction<T1, T2> beforeDo(@Nonnull LTieConsumer<T1, T2> before) {
		Null.nonNullArg(before, "before");
		return (T1 a1, int a2, T2 a3) -> {
			before.accept(a1, a2, a3);
			return applyAsInt(a1, a2, a3);
		};
	}

	/** Calls codomain consumer after main function. */
	public default LTieFunction<T1, T2> afterDo(@Nonnull LIntConsumer after) {
		Null.nonNullArg(after, "after");
		return (T1 a1, int a2, T2 a3) -> {
			final int retval = applyAsInt(a1, a2, a3);
			after.accept(retval);
			return retval;
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LIntSupplier capture(T1 a1, int a2, T2 a3) {
		return () -> this.applyAsInt(a1, a2, a3);
	}

	/** Creates function that always returns the same value. */
	static <T1, T2> LTieFunction<T1, T2> constant(int r) {
		return (a1, a2, a3) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T1, T2> LTieFunction<T1, T2> apply1stAsInt(@Nonnull LToIntFunction<T1> func) {
		return (a1, a2, a3) -> func.applyAsInt(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T1, T2> LTieFunction<T1, T2> apply2ndAsInt(@Nonnull LIntUnaryOperator func) {
		return (a1, a2, a3) -> func.applyAsInt(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T1, T2> LTieFunction<T1, T2> apply3rdAsInt(@Nonnull LToIntFunction<T2> func) {
		return (a1, a2, a3) -> func.applyAsInt(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2> LTieFunction<T1, T2> tieFunc(final @Nonnull LTieFunction<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** A completely inconvenient method in case lambda expression and generic arguments are ambiguous for the compiler. */
	@Nonnull
	static <T1, T2> LTieFunction<T1, T2> tieFunc(@Nullable Class<T1> c1, @Nullable Class<T2> c2, final @Nonnull LTieFunction<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T1, T2> LTieFunction<T1, T2> recursive(final @Nonnull LFunction<LTieFunction<T1, T2>, LTieFunction<T1, T2>> selfLambda) {
		final LTieFunctionSingle<T1, T2> single = new LTieFunctionSingle();
		LTieFunction<T1, T2> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LTieFunctionSingle<T1, T2> implements LSingle<LTieFunction<T1, T2>>, LTieFunction<T1, T2> {
		private LTieFunction<T1, T2> target = null;

		@Override
		public int applyAsIntX(T1 a1, int a2, T2 a3) throws Throwable {
			return target.applyAsIntX(a1, a2, a3);
		}

		@Override
		public LTieFunction<T1, T2> value() {
			return target;
		}
	}

	@Nonnull
	static <T1, T2> LTieFunction<T1, T2> tieFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T1, T2> LTieFunction<T1, T2> tieFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2> LTieFunction.LObj0Obj2Int1ToIntFunc<T1, T2> obj0Obj2Int1ToIntFunc(final @Nonnull LTieFunction.LObj0Obj2Int1ToIntFunc<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2> LTieFunction.LInt1BiObj2ToIntFunc<T1, T2> int1BiObj2ToIntFunc(final @Nonnull LTieFunction.LInt1BiObj2ToIntFunc<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1> LTieFunction.LInt1Obj2Obj0ToIntFunc<T2, T1> int1Obj2Obj0ToIntFunc(final @Nonnull LTieFunction.LInt1Obj2Obj0ToIntFunc<T2, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1> LTieFunction.LObj2Obj0Int1ToIntFunc<T2, T1> obj2Obj0Int1ToIntFunc(final @Nonnull LTieFunction.LObj2Obj0Int1ToIntFunc<T2, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1> LTieFunction.LObj2Int1Obj0ToIntFunc<T2, T1> obj2Int1Obj0ToIntFunc(final @Nonnull LTieFunction.LObj2Int1Obj0ToIntFunc<T2, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T1, T2> int call(T1 a1, int a2, T2 a3, final @Nonnull LTieFunction<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsInt(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as doNothing). */
	@Nonnull
	static <T1, T2> LTieFunction<T1, T2> safe() {
		return LTieFunction::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T1, T2> LSupplier<LTieFunction<T1, T2>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T1, T2> LTieFunction<T1, T2> safe(final @Nullable LTieFunction<T1, T2> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T1, T2> LSupplier<LTieFunction<T1, T2>> safeSupplier(final @Nullable LSupplier<LTieFunction<T1, T2>> supplier) {
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
	default <V1, V3> LTieFunction<V1, V3> compose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LIntUnaryOperator before2, @Nonnull final LFunction<? super V3, ? extends T2> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.applyAsInt(before1.apply(v1), before2.applyAsInt(v2), before3.apply(v3));
	}

	public static <V1, V3, T1, T2> LTieFunction<V1, V3> composed(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LIntUnaryOperator before2, @Nonnull final LFunction<? super V3, ? extends T2> before3, LTieFunction<T1, T2> after) {
		return after.compose(before1, before2, before3);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LToIntTriFunction<V1, V2, V3> tieFuncCompose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LToIntFunction<? super V2> before2, @Nonnull final LFunction<? super V3, ? extends T2> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.applyAsInt(before1.apply(v1), before2.applyAsInt(v2), before3.apply(v3));
	}

	public static <V1, V2, V3, T1, T2> LToIntTriFunction<V1, V2, V3> composed(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LToIntFunction<? super V2> before2, @Nonnull final LFunction<? super V3, ? extends T2> before3,
			LTieFunction<T1, T2> after) {
		return after.tieFuncCompose(before1, before2, before3);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LObjIntObjFunction<T1, T2, V> then(@Nonnull LIntFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.apply(this.applyAsInt(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LTieFunction<T1, T2> thenToInt(@Nonnull LIntUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.applyAsInt(this.applyAsInt(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LObjIntObjPredicate<T1, T2> thenToBool(@Nonnull LIntPredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.test(this.applyAsInt(a1, a2, a3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LTieFunction for method references. */
	@FunctionalInterface
	interface LObj0Obj2Int1ToIntFunc<T1, T2> extends LTieFunction<T1, T2> {

		/**
		 * Implement this, but call applyAsInt(T1 a1,int a2,T2 a3)
		 */
		default int applyAsIntX(T1 a1, int a2, T2 a3) {
			return this.applyAsIntObj0Obj2Int1(a1, a3, a2);
		}

		// int applyAsIntObj0Obj2Int1(T1 a1,T2 a3,int a2) ;
		default int applyAsIntObj0Obj2Int1(T1 a1, T2 a3, int a2) {
			// return nestingApplyAsIntObj0Obj2Int1(a1,a3,a2);
			try {
				return this.applyAsIntObj0Obj2Int1X(a1, a3, a2);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyAsIntObj0Obj2Int1(T1 a1,T2 a3,int a2)
		 */
		int applyAsIntObj0Obj2Int1X(T1 a1, T2 a3, int a2) throws Throwable;
	}

	/** Permutation of LTieFunction for method references. */
	@FunctionalInterface
	interface LInt1BiObj2ToIntFunc<T1, T2> extends LTieFunction<T1, T2> {

		/**
		 * Implement this, but call applyAsIntObj0Obj2Int1(T1 a1,T2 a3,int a2)
		 */
		default int applyAsIntX(T1 a1, int a2, T2 a3) {
			return this.applyAsIntInt1BiObj2(a2, a1, a3);
		}

		// int applyAsIntInt1BiObj2(int a2,T1 a1,T2 a3) ;
		default int applyAsIntInt1BiObj2(int a2, T1 a1, T2 a3) {
			// return nestingApplyAsIntInt1BiObj2(a2,a1,a3);
			try {
				return this.applyAsIntInt1BiObj2X(a2, a1, a3);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyAsIntInt1BiObj2(int a2,T1 a1,T2 a3)
		 */
		int applyAsIntInt1BiObj2X(int a2, T1 a1, T2 a3) throws Throwable;
	}

	/** Permutation of LTieFunction for method references. */
	@FunctionalInterface
	interface LInt1Obj2Obj0ToIntFunc<T2, T1> extends LTieFunction<T1, T2> {

		/**
		 * Implement this, but call applyAsIntInt1BiObj2(int a2,T1 a1,T2 a3)
		 */
		default int applyAsIntX(T1 a1, int a2, T2 a3) {
			return this.applyAsIntInt1Obj2Obj0(a2, a3, a1);
		}

		// int applyAsIntInt1Obj2Obj0(int a2,T2 a3,T1 a1) ;
		default int applyAsIntInt1Obj2Obj0(int a2, T2 a3, T1 a1) {
			// return nestingApplyAsIntInt1Obj2Obj0(a2,a3,a1);
			try {
				return this.applyAsIntInt1Obj2Obj0X(a2, a3, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyAsIntInt1Obj2Obj0(int a2,T2 a3,T1 a1)
		 */
		int applyAsIntInt1Obj2Obj0X(int a2, T2 a3, T1 a1) throws Throwable;
	}

	/** Permutation of LTieFunction for method references. */
	@FunctionalInterface
	interface LObj2Obj0Int1ToIntFunc<T2, T1> extends LTieFunction<T1, T2> {

		/**
		 * Implement this, but call applyAsIntInt1Obj2Obj0(int a2,T2 a3,T1 a1)
		 */
		default int applyAsIntX(T1 a1, int a2, T2 a3) {
			return this.applyAsIntObj2Obj0Int1(a3, a1, a2);
		}

		// int applyAsIntObj2Obj0Int1(T2 a3,T1 a1,int a2) ;
		default int applyAsIntObj2Obj0Int1(T2 a3, T1 a1, int a2) {
			// return nestingApplyAsIntObj2Obj0Int1(a3,a1,a2);
			try {
				return this.applyAsIntObj2Obj0Int1X(a3, a1, a2);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyAsIntObj2Obj0Int1(T2 a3,T1 a1,int a2)
		 */
		int applyAsIntObj2Obj0Int1X(T2 a3, T1 a1, int a2) throws Throwable;
	}

	/** Permutation of LTieFunction for method references. */
	@FunctionalInterface
	interface LObj2Int1Obj0ToIntFunc<T2, T1> extends LTieFunction<T1, T2> {

		/**
		 * Implement this, but call applyAsIntObj2Obj0Int1(T2 a3,T1 a1,int a2)
		 */
		default int applyAsIntX(T1 a1, int a2, T2 a3) {
			return this.applyAsIntObj2Int1Obj0(a3, a2, a1);
		}

		// int applyAsIntObj2Int1Obj0(T2 a3,int a2,T1 a1) ;
		default int applyAsIntObj2Int1Obj0(T2 a3, int a2, T1 a1) {
			// return nestingApplyAsIntObj2Int1Obj0(a3,a2,a1);
			try {
				return this.applyAsIntObj2Int1Obj0X(a3, a2, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyAsIntObj2Int1Obj0(T2 a3,int a2,T1 a1)
		 */
		int applyAsIntObj2Int1Obj0X(T2 a3, int a2, T1 a1) throws Throwable;
	}

	// </editor-fold>

	/** Does nothing (LTieFunction) Function */
	public static <T1, T2> int doNothing(T1 a1, int a2, T2 a3) {
		return Function4U.defaultInteger;
	}

	/** Does nothing (LTieFunction.LObj0Obj2Int1ToIntFunc) Function */
	public static <T1, T2> int doNothing(T1 a1, T2 a3, int a2) {
		return Function4U.defaultInteger;
	}

	/** Does nothing (LTieFunction.LInt1BiObj2ToIntFunc) Function */
	public static <T1, T2> int doNothing(int a2, T1 a1, T2 a3) {
		return Function4U.defaultInteger;
	}

	/** ***ITERATION:    TIE_CONSUMER_GEN:  FOR, [SourcePurpose{arg=int sStart, type=CONST}, SourcePurpose{arg=int sEnd, type=CONST}, SourcePurpose{arg=int tStart, type=CONST}, SourcePurpose{arg=T1 trg1, type=CONST}, SourcePurpose{arg=T2 a3, type=TIE_SOURCE}, SourcePurpose{arg=T2 a3, type=TIE_GEN_SUPPLIER}] */
	default <SRC> int genericTieForEach(int sStart, int sEnd, int tStart, T1 trg1, SRC src3, OiFunction<SRC, a<T2>> srcAcc3) {
		return tieForEach(sStart, sEnd, tStart, trg1, src3, (LOiFunction<SRC, T2>) srcAcc3, this);

	}

	/** ***ITERATION:    TARGETED_INDEXED_FOR_EACH:  FOR, [SourcePurpose{arg=T1 trg1, type=CONST}, SourcePurpose{arg=T2 a3, type=IA}, SourcePurpose{arg=LTieFunction<? super T1,? super T2> consumer, type=CONST}] */
	public static <T1, C3, T2> T1 tiForEach(T1 trg1, IndexedRead<C3, a<T2>> ia3, C3 source3, LTieFunction<? super T1, ? super T2> consumer) {

		tieForEach(trg1, ia3, source3, consumer);

		return trg1;

	}

	/** ***ITERATION:    TARGETED_INDEXED_FOR_EACH_NEW:  FOR, [SourcePurpose{arg=T1 trg1, type=SIZE_FACTORY}, SourcePurpose{arg=T2 a3, type=IA}, SourcePurpose{arg=LTieFunction<? super T1,? super T2> consumer, type=CONST}] */
	public static <T1, C3, T2> T1 ntiForEach(LIntFunction<T1> trgFactory1, IndexedRead<C3, a<T2>> ia3, C3 source3, LTieFunction<? super T1, ? super T2> consumer) {
		int size = ia3.size(source3);
		T1 trg1 = trgFactory1.apply(size);
		tieForEach(0, size, 0, trg1, source3, ia3.getter(), consumer);
		return trg1;
	}

	/** ***ITERATION:    TIE_CONSUMER_SHORT:  FOR, [SourcePurpose{arg=T1 trg1, type=CONST}, SourcePurpose{arg=T2 a3, type=IA}, SourcePurpose{arg=LTieFunction<? super T1,? super T2> consumer, type=CONST}] */
	public static <T1, C3, T2> int tieForEach(T1 trg1, IndexedRead<C3, a<T2>> ia3, C3 source3, LTieFunction<? super T1, ? super T2> consumer) {
		int size = ia3.size(source3);
		return tieForEach(0, size, 0, trg1, source3, ia3.getter(), consumer);
	}

	/** ***ITERATION:    TIE_CONSUMER:  FOR, [SourcePurpose{arg=int sStart, type=CONST}, SourcePurpose{arg=int sEnd, type=CONST}, SourcePurpose{arg=int tStart, type=CONST}, SourcePurpose{arg=T1 trg1, type=CONST}, SourcePurpose{arg=T2 a3, type=TIE_SOURCE}, SourcePurpose{arg=T2 a3, type=TIE_SUPPLIER}, SourcePurpose{arg=LTieFunction<? super T1,? super T2> consumer, type=CONST}] */
	public static <T1, SRC, T2> int tieForEach(int sStart, int sEnd, int tStart, T1 trg1, SRC src3, LOiFunction<SRC, T2> srcAcc3, LTieFunction<? super T1, ? super T2> consumer) {
		int tIndex = tStart;
		for (int sIndex = sStart; sIndex < sEnd; sIndex++) {
			T2 a3 = srcAcc3.apply(src3, sIndex);
			tIndex += consumer.applyAsInt(trg1, tIndex, a3);
		}
		return tIndex - tStart;

	}

	/** ***ITERATION:    TIE_CONSUMER2_GEN:  FOR, [SourcePurpose{arg=int sStart, type=CONST}, SourcePurpose{arg=int tStart, type=CONST}, SourcePurpose{arg=T1 trg1, type=CONST}, SourcePurpose{arg=T2 a3, type=TIE_SOURCE}, SourcePurpose{arg=T2 a3, type=TE_GEN_PREDICATE}, SourcePurpose{arg=T2 a3, type=TE_GEN_SUPPLIER}] */
	public default <SRC> int genericTieForEach(int sStart, int tStart, T1 trg1, SRC src3, OFunction<SRC, aBool> srcTest3, OFunction<SRC, a<T2>> srcAcc3) {
		return tieForEach(sStart, tStart, trg1, src3, (LPredicate<SRC>) srcTest3, (LFunction<SRC, T2>) srcAcc3, this);

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with TIE: 'target', index, element). First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns increment count based on consumer function
	*/
	public static <T1, SRC, T2> int tieForEach(int sStart, int tStart, T1 trg1, SRC src3, LPredicate<SRC> srcTest3, LFunction<SRC, T2> srcAcc3, LTieFunction<? super T1, ? super T2> consumer) {
		int tIndex = tStart;
		for (; srcTest3.test(src3); tIndex++) {
			T2 a3 = srcAcc3.apply(src3);
			tIndex += consumer.applyAsInt(trg1, tIndex, a3);
		}
		return tIndex - sStart;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with TIE: 'target', index, element). First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns increment count based on consumer function
	*/
	public static <T1, C3, I3, T2> int tieIterate(T1 trg1, SequentialRead<C3, I3, a<T2>> sa3, C3 source3, LTieFunction<? super T1, ? super T2> consumer) {
		LFunction<C3, I3> toIntermediate = sa3.adapter();
		return tieForEach(0, 0, trg1, toIntermediate.apply(source3), sa3.tester(), sa3.supplier(), consumer);
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with TIE: 'target', index, element). First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns 'target' object
	*/
	public static <T1, C3, I3, T2> T1 tiIterate(T1 trg1, SequentialRead<C3, I3, a<T2>> sa3, C3 source3, LTieFunction<? super T1, ? super T2> consumer) {

		tieIterate(trg1, sa3, source3, consumer);

		return trg1;

	}

	/** ***ITERATION:    TARGETED_INDEXED_ITERATE_NEW:  WHILE, [SourcePurpose{arg=T1 trg1, type=SUPPLIER}, SourcePurpose{arg=T2 a3, type=SA}, SourcePurpose{arg=LTieFunction<? super T1,? super T2> consumer, type=CONST}] */
	public static <T1, C3, I3, T2> T1 ntiIterate(LSupplier<T1> source1, SequentialRead<C3, I3, a<T2>> sa3, C3 source3, LTieFunction<? super T1, ? super T2> consumer) {
		T1 trg1 = source1.get();
		tieIterate(trg1, sa3, source3, consumer);

		return trg1;

	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, C3> void forEach(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, IndexedRead<C3, a<T2>> ia3, C3 source3, LIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T2> oiFunc3 = (LOiFunction) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			T1 a1 = oiFunc1.apply(source1, i);
			int a2 = oiFunc2.applyAsInt(source2, i);
			T2 a3 = oiFunc3.apply(source3, i);
			consumer.accept(this.applyAsInt(a1, a2, a3));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, C3> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, IndexedRead<C3, a<T2>> ia3, C3 source3, LIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T2> oiFunc3 = (LOiFunction) ia3.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			T1 a1 = nextFunc1.apply(iterator1);
			int a2 = oiFunc2.applyAsInt(source2, i);
			T2 a3 = oiFunc3.apply(source3, i);
			consumer.accept(this.applyAsInt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2, C3> void iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, IndexedRead<C3, a<T2>> ia3, C3 source3, LIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T2> oiFunc3 = (LOiFunction) ia3.getter();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			T1 a1 = oiFunc1.apply(source1, i);
			int a2 = nextFunc2.applyAsInt(iterator2);
			T2 a3 = oiFunc3.apply(source3, i);
			consumer.accept(this.applyAsInt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, I2, C3> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, IndexedRead<C3, a<T2>> ia3, C3 source3, LIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		int size = ia3.size(source3);
		LOiFunction<Object, T2> oiFunc3 = (LOiFunction) ia3.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && i < size) {
			T1 a1 = nextFunc1.apply(iterator1);
			int a2 = nextFunc2.applyAsInt(iterator2);
			T2 a3 = oiFunc3.apply(source3, i);
			consumer.accept(this.applyAsInt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, C3, I3> void iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, SequentialRead<C3, I3, a<T2>> sa3, C3 source3, LIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T2> nextFunc3 = (LFunction) sa3.supplier();
		int i = 0;
		while (i < size && testFunc3.test(iterator3)) {
			T1 a1 = oiFunc1.apply(source1, i);
			int a2 = oiFunc2.applyAsInt(source2, i);
			T2 a3 = nextFunc3.apply(iterator3);
			consumer.accept(this.applyAsInt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, C3, I3> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, SequentialRead<C3, I3, a<T2>> sa3, C3 source3, LIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T2> nextFunc3 = (LFunction) sa3.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size && testFunc3.test(iterator3)) {
			T1 a1 = nextFunc1.apply(iterator1);
			int a2 = oiFunc2.applyAsInt(source2, i);
			T2 a3 = nextFunc3.apply(iterator3);
			consumer.accept(this.applyAsInt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2, C3, I3> void iterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, SequentialRead<C3, I3, a<T2>> sa3, C3 source3, LIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T2> nextFunc3 = (LFunction) sa3.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			T1 a1 = oiFunc1.apply(source1, i);
			int a2 = nextFunc2.applyAsInt(iterator2);
			T2 a3 = nextFunc3.apply(iterator3);
			consumer.accept(this.applyAsInt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C1, I1, C2, I2, C3, I3> void iterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, SequentialRead<C3, I3, a<T2>> sa3, C3 source3, LIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T2> nextFunc3 = (LFunction) sa3.supplier();
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			T1 a1 = nextFunc1.apply(iterator1);
			int a2 = nextFunc2.applyAsInt(iterator2);
			T2 a3 = nextFunc3.apply(iterator3);
			consumer.accept(this.applyAsInt(a1, a2, a3));
		}
	}

}
