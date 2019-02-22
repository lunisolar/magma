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
 * Non-throwing functional interface (lambda) LTieBoolFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 3): T a1,int a2,boolean a3
 *
 * Co-domain: int
 *
 * Special case of function that corresponds to TIE consumer with return integer value.
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LTieBoolFunction<T> extends MetaFunction, MetaInterface.NonThrowing, TieFunction<T, aBool>, Codomain<aInt>, Domain3<a<T>, aInt, aBool> { // NOSONAR

	String DESCRIPTION = "LTieBoolFunction: int applyAsInt(T a1,int a2,boolean a3)";

	// int applyAsInt(T a1,int a2,boolean a3) ;
	default int applyAsInt(T a1, int a2, boolean a3) {
		// return nestingApplyAsInt(a1,a2,a3);
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsInt(T a1,int a2,boolean a3)
	 */
	int applyAsIntX(T a1, int a2, boolean a3) throws Throwable;

	default int tupleApplyAsInt(LObjIntBoolTriple<T> args) {
		return applyAsInt(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions according to the instructions. */
	default int handlingApplyAsInt(T a1, int a2, boolean a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LTieBoolFunction<T> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> handlingApplyAsInt(a1, a2, a3, handling);
	}

	default int applyAsInt(T a1, int a2, boolean a3, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LTieBoolFunction<T> trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return (a1, a2, a3) -> applyAsInt(a1, a2, a3, exF, newMessage, messageParams);
	}

	default int applyAsInt(T a1, int a2, boolean a3, @Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LTieBoolFunction<T> trying(@Nonnull ExWF<RuntimeException> exF) {
		return (a1, a2, a3) -> applyAsInt(a1, a2, a3, exF);
	}

	default int applyAsIntThen(T a1, int a2, boolean a3, @Nonnull LToIntFunction<Throwable> handler) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsInt(e);
		}
	}

	default LTieBoolFunction<T> tryingThen(@Nonnull LToIntFunction<Throwable> handler) {
		return (a1, a2, a3) -> applyAsIntThen(a1, a2, a3, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default int nestingApplyAsInt(T a1, int a2, boolean a3) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default int shovingApplyAsInt(T a1, int a2, boolean a3) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> int handlingApplyAsInt(T a1, int a2, boolean a3, LTieBoolFunction<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsInt(a1, a2, a3, handling);
	}

	static <T> int tryApplyAsInt(T a1, int a2, boolean a3, LTieBoolFunction<T> func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsInt(a1, a2, a3);
	}

	static <T> int tryApplyAsInt(T a1, int a2, boolean a3, LTieBoolFunction<T> func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.applyAsInt(a1, a2, a3, exF, newMessage, messageParams);
	}

	static <T> int tryApplyAsInt(T a1, int a2, boolean a3, LTieBoolFunction<T> func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.applyAsInt(a1, a2, a3, exF);
	}

	static <T> int tryApplyAsIntThen(T a1, int a2, boolean a3, LTieBoolFunction<T> func, @Nonnull LToIntFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsIntThen(a1, a2, a3, handler);
	}

	default int failSafeApplyAsInt(T a1, int a2, boolean a3, @Nonnull LTieBoolFunction<T> failSafe) {
		try {
			return applyAsInt(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.applyAsInt(a1, a2, a3);
		}
	}

	static <T> int failSafeApplyAsInt(T a1, int a2, boolean a3, LTieBoolFunction<T> func, @Nonnull LTieBoolFunction<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.applyAsInt(a1, a2, a3);
		} else {
			return func.failSafeApplyAsInt(a1, a2, a3, failSafe);
		}
	}

	static <T> LTieBoolFunction<T> failSafe(LTieBoolFunction<T> func, @Nonnull LTieBoolFunction<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeApplyAsInt(a1, a2, a3, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default int nonNullApplyAsInt(T a1, int a2, boolean a3) {
		return applyAsInt(a1, a2, a3);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LTieBoolFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_a2, int max_a2, T a1, boolean a3, LTieBoolFunction<T> func) {
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
	public static <T> void fromTill(int min_a2, int max_a2, T a1, boolean a3, LTieBoolFunction<T> func) {
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
	public static <T> void times(int max_a2, T a1, boolean a3, LTieBoolFunction<T> func) {
		if (max_a2 < 0)
			return;
		fromTill(0, max_a2, a1, a3, func);
	}

	/**  */
	public static <T> LTieBoolFunction<T> uncurry(LFunction<T, LIntFunction<LBoolToIntFunction>> func) {
		return (T a1, int a2, boolean a3) -> func.apply(a1).apply(a2).applyAsInt(a3);
	}

	/** Cast that removes generics. */
	public default LTieBoolFunction untyped() {
		return this;
	}

	/** Cast that replace generics. */
	public default <V2> LTieBoolFunction<V2> cast() {
		return untyped();
	}

	/** Cast that replace generics. */
	public static <V2, T> LTieBoolFunction<V2> cast(LTieBoolFunction<T> function) {
		return (LTieBoolFunction) function;
	}

	/** Captures arguments but delays the evaluation. */
	default LIntSupplier capture(T a1, int a2, boolean a3) {
		return () -> this.applyAsInt(a1, a2, a3);
	}

	/** Creates function that always returns the same value. */
	static <T> LTieBoolFunction<T> constant(int r) {
		return (a1, a2, a3) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T> LTieBoolFunction<T> apply1stAsInt(@Nonnull LToIntFunction<T> func) {
		return (a1, a2, a3) -> func.applyAsInt(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T> LTieBoolFunction<T> apply2ndAsInt(@Nonnull LIntUnaryOperator func) {
		return (a1, a2, a3) -> func.applyAsInt(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T> LTieBoolFunction<T> apply3rdAsInt(@Nonnull LBoolToIntFunction func) {
		return (a1, a2, a3) -> func.applyAsInt(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LTieBoolFunction<T> tieBoolFunc(final @Nonnull LTieBoolFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T> LTieBoolFunction<T> recursive(final @Nonnull LFunction<LTieBoolFunction<T>, LTieBoolFunction<T>> selfLambda) {
		final LTieBoolFunctionSingle<T> single = new LTieBoolFunctionSingle();
		LTieBoolFunction<T> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LTieBoolFunctionSingle<T> implements LSingle<LTieBoolFunction<T>>, LTieBoolFunction<T> {
		private LTieBoolFunction<T> target = null;

		@Override
		public int applyAsIntX(T a1, int a2, boolean a3) throws Throwable {
			return target.applyAsIntX(a1, a2, a3);
		}

		@Override
		public LTieBoolFunction<T> value() {
			return target;
		}
	}

	@Nonnull
	static <T> LTieBoolFunction<T> tieBoolFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T> LTieBoolFunction<T> tieBoolFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LObjBoolIntToIntFunc<T> objBoolIntToIntFunc(final @Nonnull LObjBoolIntToIntFunc<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LIntObjBoolToIntFunc<T> intObjBoolToIntFunc(final @Nonnull LIntObjBoolToIntFunc<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LIntBoolObjToIntFunc<T> intBoolObjToIntFunc(final @Nonnull LIntBoolObjToIntFunc<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LBoolObjIntToIntFunc<T> boolObjIntToIntFunc(final @Nonnull LBoolObjIntToIntFunc<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LBoolIntObjToIntFunc<T> boolIntObjToIntFunc(final @Nonnull LBoolIntObjToIntFunc<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T> int call(T a1, int a2, boolean a3, final @Nonnull LTieBoolFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsInt(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceInt). */
	@Nonnull
	static <T> LTieBoolFunction<T> safe() {
		return LTieBoolFunction::produceInt;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LTieBoolFunction<T>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T> LTieBoolFunction<T> safe(final @Nullable LTieBoolFunction<T> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LTieBoolFunction<T>> safeSupplier(final @Nullable LSupplier<LTieBoolFunction<T>> supplier) {
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
	default <V1> LTieBoolFunction<V1> compose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2, @Nonnull final LLogicalOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.applyAsInt(before1.apply(v1), before2.applyAsInt(v2), before3.apply(v3));
	}

	public static <V1, T> LTieBoolFunction<V1> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2, @Nonnull final LLogicalOperator before3, LTieBoolFunction<T> after) {
		return after.compose(before1, before2, before3);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LToIntTriFunction<V1, V2, V3> tieBoolFuncCompose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2, @Nonnull final LPredicate<? super V3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.applyAsInt(before1.apply(v1), before2.applyAsInt(v2), before3.test(v3));
	}

	public static <V1, V2, V3, T> LToIntTriFunction<V1, V2, V3> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2, @Nonnull final LPredicate<? super V3> before3, LTieBoolFunction<T> after) {
		return after.tieBoolFuncCompose(before1, before2, before3);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LObjIntBoolFunction<T, V> then(@Nonnull LIntFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.apply(this.applyAsInt(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LTieBoolFunction<T> thenToInt(@Nonnull LIntUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.applyAsInt(this.applyAsInt(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LObjIntBoolPredicate<T> thenToBool(@Nonnull LIntPredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.test(this.applyAsInt(a1, a2, a3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LTieBoolFunction for method references. */
	@FunctionalInterface
	interface LObjBoolIntToIntFunc<T> extends LTieBoolFunction<T> {

		int applyAsIntObjBoolInt(T a1, boolean a3, int a2);

		@Override
		default int applyAsIntX(T a1, int a2, boolean a3) {
			return this.applyAsIntObjBoolInt(a1, a3, a2);
		}
	}

	/** Permutation of LTieBoolFunction for method references. */
	@FunctionalInterface
	interface LIntObjBoolToIntFunc<T> extends LTieBoolFunction<T> {

		int applyAsIntIntObjBool(int a2, T a1, boolean a3);

		@Override
		default int applyAsIntX(T a1, int a2, boolean a3) {
			return this.applyAsIntIntObjBool(a2, a1, a3);
		}
	}

	/** Permutation of LTieBoolFunction for method references. */
	@FunctionalInterface
	interface LIntBoolObjToIntFunc<T> extends LTieBoolFunction<T> {

		int applyAsIntIntBoolObj(int a2, boolean a3, T a1);

		@Override
		default int applyAsIntX(T a1, int a2, boolean a3) {
			return this.applyAsIntIntBoolObj(a2, a3, a1);
		}
	}

	/** Permutation of LTieBoolFunction for method references. */
	@FunctionalInterface
	interface LBoolObjIntToIntFunc<T> extends LTieBoolFunction<T> {

		int applyAsIntBoolObjInt(boolean a3, T a1, int a2);

		@Override
		default int applyAsIntX(T a1, int a2, boolean a3) {
			return this.applyAsIntBoolObjInt(a3, a1, a2);
		}
	}

	/** Permutation of LTieBoolFunction for method references. */
	@FunctionalInterface
	interface LBoolIntObjToIntFunc<T> extends LTieBoolFunction<T> {

		int applyAsIntBoolIntObj(boolean a3, int a2, T a1);

		@Override
		default int applyAsIntX(T a1, int a2, boolean a3) {
			return this.applyAsIntBoolIntObj(a3, a2, a1);
		}
	}

	// </editor-fold>

	/** Does nothing (LTieBoolFunction) Function */
	public static <T> int produceInt(T a1, int a2, boolean a3) {
		return Function4U.defaultInteger;
	}

	/** Does nothing (LTieBoolFunction.LObjBoolIntToIntFunc) Function */
	public static <T> int produceInt(T a1, boolean a3, int a2) {
		return Function4U.defaultInteger;
	}

	/** Does nothing (LTieBoolFunction.LIntObjBoolToIntFunc) Function */
	public static <T> int produceInt(int a2, T a1, boolean a3) {
		return Function4U.defaultInteger;
	}

	/** Does nothing (LTieBoolFunction.LIntBoolObjToIntFunc) Function */
	public static <T> int produceInt(int a2, boolean a3, T a1) {
		return Function4U.defaultInteger;
	}

	/** Does nothing (LTieBoolFunction.LBoolObjIntToIntFunc) Function */
	public static <T> int produceInt(boolean a3, T a1, int a2) {
		return Function4U.defaultInteger;
	}

	/** Does nothing (LTieBoolFunction.LBoolIntObjToIntFunc) Function */
	public static <T> int produceInt(boolean a3, int a2, T a1) {
		return Function4U.defaultInteger;
	}

	/** ***ITERATION:    TIE_CONSUMER_GEN:  FOR, [SourcePurpose{arg=int sStart, type=CONST}, SourcePurpose{arg=int sEnd, type=CONST}, SourcePurpose{arg=int tStart, type=CONST}, SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=boolean a3, type=TIE_SOURCE}, SourcePurpose{arg=boolean a3, type=TIE_GEN_SUPPLIER}] */
	default <SRC> int genericTieForEach(int sStart, int sEnd, int tStart, T trg1, SRC src3, OiFunction<SRC, aBool> srcAcc3) {
		return tieForEach(sStart, sEnd, tStart, trg1, src3, (LObjIntPredicate<SRC>) srcAcc3, this);

	}

	/** ***ITERATION:    TARGETED_INDEXED_FOR_EACH:  FOR, [SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=boolean a3, type=IA}, SourcePurpose{arg=LTieBoolFunction<? super T> consumer, type=CONST}] */
	public static <T, C3> T tiForEach(T trg1, IndexedRead<C3, aBool> ia3, C3 source3, LTieBoolFunction<? super T> consumer) {

		tieForEach(trg1, ia3, source3, consumer);

		return trg1;

	}

	/** ***ITERATION:    TARGETED_INDEXED_FOR_EACH_NEW:  FOR, [SourcePurpose{arg=T trg1, type=SIZE_FACTORY}, SourcePurpose{arg=boolean a3, type=IA}, SourcePurpose{arg=LTieBoolFunction<? super T> consumer, type=CONST}] */
	public static <T, C3> T ntiForEach(LIntFunction<T> trgFactory1, IndexedRead<C3, aBool> ia3, C3 source3, LTieBoolFunction<? super T> consumer) {
		int size = ia3.size(source3);
		T trg1 = trgFactory1.apply(size);
		tieForEach(0, size, 0, trg1, source3, ia3.getter(), consumer);
		return trg1;
	}

	/** ***ITERATION:    TIE_CONSUMER_SHORT:  FOR, [SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=boolean a3, type=IA}, SourcePurpose{arg=LTieBoolFunction<? super T> consumer, type=CONST}] */
	public static <T, C3> int tieForEach(T trg1, IndexedRead<C3, aBool> ia3, C3 source3, LTieBoolFunction<? super T> consumer) {
		int size = ia3.size(source3);
		return tieForEach(0, size, 0, trg1, source3, ia3.getter(), consumer);
	}

	/** ***ITERATION:    TIE_CONSUMER:  FOR, [SourcePurpose{arg=int sStart, type=CONST}, SourcePurpose{arg=int sEnd, type=CONST}, SourcePurpose{arg=int tStart, type=CONST}, SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=boolean a3, type=TIE_SOURCE}, SourcePurpose{arg=boolean a3, type=TIE_SUPPLIER}, SourcePurpose{arg=LTieBoolFunction<? super T> consumer, type=CONST}] */
	public static <T, SRC> int tieForEach(int sStart, int sEnd, int tStart, T trg1, SRC src3, LObjIntPredicate<SRC> srcAcc3, LTieBoolFunction<? super T> consumer) {
		int tIndex = tStart;
		for (int sIndex = sStart; sIndex < sEnd; sIndex++) {
			boolean a3 = srcAcc3.test(src3, sIndex);
			tIndex += consumer.applyAsInt(trg1, tIndex, a3);
		}
		return tIndex - tStart;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with TIE: 'target', index, element). First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns increment count based on consumer function
	*/
	public static <T, SRC> int tieForEach(int sStart, int tStart, T trg1, SRC src3, LPredicate<SRC> srcTest3, LPredicate<SRC> srcAcc3, LTieBoolFunction<? super T> consumer) {
		int tIndex = tStart;
		for (; srcTest3.test(src3); tIndex++) {
			boolean a3 = srcAcc3.test(src3);
			tIndex += consumer.applyAsInt(trg1, tIndex, a3);
		}
		return tIndex - sStart;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with TIE: 'target', index, element). First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns increment count based on consumer function
	*/
	public static <T, C3, I3> int tieIterate(T trg1, SequentialRead<C3, I3, aBool> sa3, C3 source3, LTieBoolFunction<? super T> consumer) {
		LFunction<C3, I3> toIntermediate = sa3.adapter();
		return tieForEach(0, 0, trg1, toIntermediate.apply(source3), sa3.tester(), sa3.supplier(), consumer);
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with TIE: 'target', index, element). First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns 'target' object
	*/
	public static <T, C3, I3> T tiIterate(T trg1, SequentialRead<C3, I3, aBool> sa3, C3 source3, LTieBoolFunction<? super T> consumer) {

		tieIterate(trg1, sa3, source3, consumer);

		return trg1;

	}

	/** ***ITERATION:    TARGETED_INDEXED_ITERATE_NEW:  WHILE, [SourcePurpose{arg=T trg1, type=SUPPLIER}, SourcePurpose{arg=boolean a3, type=SA}, SourcePurpose{arg=LTieBoolFunction<? super T> consumer, type=CONST}] */
	public static <T, C3, I3> T ntiIterate(LSupplier<T> source1, SequentialRead<C3, I3, aBool> sa3, C3 source3, LTieBoolFunction<? super T> consumer) {
		T trg1 = source1.get();
		tieIterate(trg1, sa3, source3, consumer);

		return trg1;

	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, C3> void forEach(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, IndexedRead<C3, aBool> ia3, C3 source3, LIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LObjIntPredicate<Object> oiFunc3 = (LObjIntPredicate) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = oiFunc2.applyAsInt(source2, i);
			boolean a3 = oiFunc3.test(source3, i);
			consumer.accept(this.applyAsInt(a1, a2, a3));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, C3> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, IndexedRead<C3, aBool> ia3, C3 source3, LIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LObjIntPredicate<Object> oiFunc3 = (LObjIntPredicate) ia3.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = oiFunc2.applyAsInt(source2, i);
			boolean a3 = oiFunc3.test(source3, i);
			consumer.accept(this.applyAsInt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2, C3> void iterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, IndexedRead<C3, aBool> ia3, C3 source3, LIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		size = Integer.min(size, ia3.size(source3));
		LObjIntPredicate<Object> oiFunc3 = (LObjIntPredicate) ia3.getter();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = nextFunc2.applyAsInt(iterator2);
			boolean a3 = oiFunc3.test(source3, i);
			consumer.accept(this.applyAsInt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, I2, C3> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, IndexedRead<C3, aBool> ia3, C3 source3, LIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		int size = ia3.size(source3);
		LObjIntPredicate<Object> oiFunc3 = (LObjIntPredicate) ia3.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && i < size) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = nextFunc2.applyAsInt(iterator2);
			boolean a3 = oiFunc3.test(source3, i);
			consumer.accept(this.applyAsInt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, C3, I3> void iterate(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, SequentialRead<C3, I3, aBool> sa3, C3 source3, LIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LPredicate<Object> nextFunc3 = (LPredicate) sa3.supplier();
		int i = 0;
		while (i < size && testFunc3.test(iterator3)) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = oiFunc2.applyAsInt(source2, i);
			boolean a3 = nextFunc3.test(iterator3);
			consumer.accept(this.applyAsInt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, C3, I3> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, SequentialRead<C3, I3, aBool> sa3, C3 source3, LIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LPredicate<Object> nextFunc3 = (LPredicate) sa3.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size && testFunc3.test(iterator3)) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = oiFunc2.applyAsInt(source2, i);
			boolean a3 = nextFunc3.test(iterator3);
			consumer.accept(this.applyAsInt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2, C3, I3> void iterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, SequentialRead<C3, I3, aBool> sa3, C3 source3, LIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LPredicate<Object> nextFunc3 = (LPredicate) sa3.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = nextFunc2.applyAsInt(iterator2);
			boolean a3 = nextFunc3.test(iterator3);
			consumer.accept(this.applyAsInt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C1, I1, C2, I2, C3, I3> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, SequentialRead<C3, I3, aBool> sa3, C3 source3, LIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LPredicate<Object> nextFunc3 = (LPredicate) sa3.supplier();
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = nextFunc2.applyAsInt(iterator2);
			boolean a3 = nextFunc3.test(iterator3);
			consumer.accept(this.applyAsInt(a1, a2, a3));
		}
	}

}
