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
 * Non-throwing functional interface (lambda) LTieDblFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 3): T a1,int a2,double a3
 *
 * Co-domain: int
 *
 * Special case of function that corresponds to TIE consumer with return integer value.
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LTieDblFunction<T> extends MetaFunction, MetaInterface.NonThrowing, TieFunction<T, aDouble>, Codomain<aInt>, Domain3<a<T>, aInt, aDouble> { // NOSONAR

	String DESCRIPTION = "LTieDblFunction: int applyAsInt(T a1,int a2,double a3)";

	// int applyAsInt(T a1,int a2,double a3) ;
	default int applyAsInt(T a1, int a2, double a3) {
		// return nestingApplyAsInt(a1,a2,a3);
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsInt(T a1,int a2,double a3)
	 */
	int applyAsIntX(T a1, int a2, double a3) throws Throwable;

	default int tupleApplyAsInt(LObjIntDblTriple<T> args) {
		return applyAsInt(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions according to the instructions. */
	default int handlingApplyAsInt(T a1, int a2, double a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LTieDblFunction<T> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> handlingApplyAsInt(a1, a2, a3, handling);
	}

	default int applyAsInt(T a1, int a2, double a3, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LTieDblFunction<T> trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return (a1, a2, a3) -> applyAsInt(a1, a2, a3, exF, newMessage, messageParams);
	}

	default int applyAsInt(T a1, int a2, double a3, @Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LTieDblFunction<T> trying(@Nonnull ExWF<RuntimeException> exF) {
		return (a1, a2, a3) -> applyAsInt(a1, a2, a3, exF);
	}

	default int applyAsIntThen(T a1, int a2, double a3, @Nonnull LToIntFunction<Throwable> handler) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsInt(e);
		}
	}

	default LTieDblFunction<T> tryingThen(@Nonnull LToIntFunction<Throwable> handler) {
		return (a1, a2, a3) -> applyAsIntThen(a1, a2, a3, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default int nestingApplyAsInt(T a1, int a2, double a3) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default int shovingApplyAsInt(T a1, int a2, double a3) {
		try {
			return this.applyAsIntX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> int handlingApplyAsInt(T a1, int a2, double a3, LTieDblFunction<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsInt(a1, a2, a3, handling);
	}

	static <T> int tryApplyAsInt(T a1, int a2, double a3, LTieDblFunction<T> func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsInt(a1, a2, a3);
	}

	static <T> int tryApplyAsInt(T a1, int a2, double a3, LTieDblFunction<T> func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.applyAsInt(a1, a2, a3, exF, newMessage, messageParams);
	}

	static <T> int tryApplyAsInt(T a1, int a2, double a3, LTieDblFunction<T> func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.applyAsInt(a1, a2, a3, exF);
	}

	static <T> int tryApplyAsIntThen(T a1, int a2, double a3, LTieDblFunction<T> func, @Nonnull LToIntFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsIntThen(a1, a2, a3, handler);
	}

	default int failSafeApplyAsInt(T a1, int a2, double a3, @Nonnull LTieDblFunction<T> failSafe) {
		try {
			return applyAsInt(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.applyAsInt(a1, a2, a3);
		}
	}

	static <T> int failSafeApplyAsInt(T a1, int a2, double a3, LTieDblFunction<T> func, @Nonnull LTieDblFunction<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.applyAsInt(a1, a2, a3);
		} else {
			return func.failSafeApplyAsInt(a1, a2, a3, failSafe);
		}
	}

	static <T> LTieDblFunction<T> failSafe(LTieDblFunction<T> func, @Nonnull LTieDblFunction<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeApplyAsInt(a1, a2, a3, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default int nonNullApplyAsInt(T a1, int a2, double a3) {
		return applyAsInt(a1, a2, a3);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LTieDblFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_a2, int max_a2, T a1, double a3, LTieDblFunction<T> func) {
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
	public static <T> void fromTill(int min_a2, int max_a2, T a1, double a3, LTieDblFunction<T> func) {
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
	public static <T> void times(int max_a2, T a1, double a3, LTieDblFunction<T> func) {
		if (max_a2 < 0)
			return;
		fromTill(0, max_a2, a1, a3, func);
	}

	/** Extract and apply function. */
	public static <M, K, V> int from(M container, LBiFunction<M, K, V> extractor, K key, int a2, double a3, LTieDblFunction<V> function, int orElse) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.applyAsInt(value, a2, a3);
		}

		return orElse;
	}

	/**  */
	public static <T> LTieDblFunction<T> uncurry(LFunction<T, LIntFunction<LDblToIntFunction>> func) {
		return (T a1, int a2, double a3) -> func.apply(a1).apply(a2).applyAsInt(a3);
	}

	/** Cast that removes generics. */
	public default LTieDblFunction untyped() {
		return this;
	}

	/** Cast that replace generics. */
	public default <V2> LTieDblFunction<V2> cast() {
		return untyped();
	}

	/** Cast that replace generics. */
	public static <V2, T> LTieDblFunction<V2> cast(LTieDblFunction<T> function) {
		return (LTieDblFunction) function;
	}

	/** Change function to consumer that ignores output. */
	public default LTieDblConsumer<T> toConsumer() {
		return this::applyAsInt;
	}

	/** Captures arguments but delays the evaluation. */
	default LIntSupplier capture(T a1, int a2, double a3) {
		return () -> this.applyAsInt(a1, a2, a3);
	}

	/** Creates function that always returns the same value. */
	static <T> LTieDblFunction<T> constant(int r) {
		return (a1, a2, a3) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T> LTieDblFunction<T> apply1stAsInt(@Nonnull LToIntFunction<T> func) {
		return (a1, a2, a3) -> func.applyAsInt(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T> LTieDblFunction<T> apply2ndAsInt(@Nonnull LIntUnaryOperator func) {
		return (a1, a2, a3) -> func.applyAsInt(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T> LTieDblFunction<T> apply3rdAsInt(@Nonnull LDblToIntFunction func) {
		return (a1, a2, a3) -> func.applyAsInt(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LTieDblFunction<T> tieDblFunc(final @Nonnull LTieDblFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T> LTieDblFunction<T> recursive(final @Nonnull LFunction<LTieDblFunction<T>, LTieDblFunction<T>> selfLambda) {
		final LTieDblFunctionSingle<T> single = new LTieDblFunctionSingle();
		LTieDblFunction<T> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LTieDblFunctionSingle<T> implements LSingle<LTieDblFunction<T>>, LTieDblFunction<T> {
		private LTieDblFunction<T> target = null;

		@Override
		public int applyAsIntX(T a1, int a2, double a3) throws Throwable {
			return target.applyAsIntX(a1, a2, a3);
		}

		@Override
		public LTieDblFunction<T> value() {
			return target;
		}
	}

	@Nonnull
	static <T> LTieDblFunction<T> tieDblFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T> LTieDblFunction<T> tieDblFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LObjDblIntToIntFunc<T> objDblIntToIntFunc(final @Nonnull LObjDblIntToIntFunc<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LIntObjDblToIntFunc<T> intObjDblToIntFunc(final @Nonnull LIntObjDblToIntFunc<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LIntDblObjToIntFunc<T> intDblObjToIntFunc(final @Nonnull LIntDblObjToIntFunc<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LDblObjIntToIntFunc<T> dblObjIntToIntFunc(final @Nonnull LDblObjIntToIntFunc<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LDblIntObjToIntFunc<T> dblIntObjToIntFunc(final @Nonnull LDblIntObjToIntFunc<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T> int call(T a1, int a2, double a3, final @Nonnull LTieDblFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsInt(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as produceInt). */
	@Nonnull
	static <T> LTieDblFunction<T> safe() {
		return LTieDblFunction::produceInt;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LTieDblFunction<T>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T> LTieDblFunction<T> safe(final @Nullable LTieDblFunction<T> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LTieDblFunction<T>> safeSupplier(final @Nullable LSupplier<LTieDblFunction<T>> supplier) {
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
	default <V1> LTieDblFunction<V1> compose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2, @Nonnull final LDblUnaryOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.applyAsInt(before1.apply(v1), before2.applyAsInt(v2), before3.applyAsDbl(v3));
	}

	public static <V1, T> LTieDblFunction<V1> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2, @Nonnull final LDblUnaryOperator before3, LTieDblFunction<T> after) {
		return after.compose(before1, before2, before3);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LToIntTriFunction<V1, V2, V3> tieDblFuncCompose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2, @Nonnull final LToDblFunction<? super V3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.applyAsInt(before1.apply(v1), before2.applyAsInt(v2), before3.applyAsDbl(v3));
	}

	public static <V1, V2, V3, T> LToIntTriFunction<V1, V2, V3> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2, @Nonnull final LToDblFunction<? super V3> before3,
			LTieDblFunction<T> after) {
		return after.tieDblFuncCompose(before1, before2, before3);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LObjIntDblFunction<T, V> then(@Nonnull LIntFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.apply(this.applyAsInt(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LTieDblFunction<T> thenToInt(@Nonnull LIntUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.applyAsInt(this.applyAsInt(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LObjIntDblPredicate<T> thenToBool(@Nonnull LIntPredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.test(this.applyAsInt(a1, a2, a3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LTieDblFunction for method references. */
	@FunctionalInterface
	interface LObjDblIntToIntFunc<T> extends LTieDblFunction<T> {

		int applyAsIntObjDblInt(T a1, double a3, int a2);

		@Override
		default int applyAsIntX(T a1, int a2, double a3) {
			return this.applyAsIntObjDblInt(a1, a3, a2);
		}
	}

	/** Permutation of LTieDblFunction for method references. */
	@FunctionalInterface
	interface LIntObjDblToIntFunc<T> extends LTieDblFunction<T> {

		int applyAsIntIntObjDbl(int a2, T a1, double a3);

		@Override
		default int applyAsIntX(T a1, int a2, double a3) {
			return this.applyAsIntIntObjDbl(a2, a1, a3);
		}
	}

	/** Permutation of LTieDblFunction for method references. */
	@FunctionalInterface
	interface LIntDblObjToIntFunc<T> extends LTieDblFunction<T> {

		int applyAsIntIntDblObj(int a2, double a3, T a1);

		@Override
		default int applyAsIntX(T a1, int a2, double a3) {
			return this.applyAsIntIntDblObj(a2, a3, a1);
		}
	}

	/** Permutation of LTieDblFunction for method references. */
	@FunctionalInterface
	interface LDblObjIntToIntFunc<T> extends LTieDblFunction<T> {

		int applyAsIntDblObjInt(double a3, T a1, int a2);

		@Override
		default int applyAsIntX(T a1, int a2, double a3) {
			return this.applyAsIntDblObjInt(a3, a1, a2);
		}
	}

	/** Permutation of LTieDblFunction for method references. */
	@FunctionalInterface
	interface LDblIntObjToIntFunc<T> extends LTieDblFunction<T> {

		int applyAsIntDblIntObj(double a3, int a2, T a1);

		@Override
		default int applyAsIntX(T a1, int a2, double a3) {
			return this.applyAsIntDblIntObj(a3, a2, a1);
		}
	}

	// </editor-fold>

	/** Does nothing (LTieDblFunction) Function */
	public static <T> int produceInt(T a1, int a2, double a3) {
		return Function4U.defaultInteger;
	}

	/** Does nothing (LTieDblFunction.LObjDblIntToIntFunc) Function */
	public static <T> int produceInt(T a1, double a3, int a2) {
		return Function4U.defaultInteger;
	}

	/** Does nothing (LTieDblFunction.LIntObjDblToIntFunc) Function */
	public static <T> int produceInt(int a2, T a1, double a3) {
		return Function4U.defaultInteger;
	}

	/** Does nothing (LTieDblFunction.LIntDblObjToIntFunc) Function */
	public static <T> int produceInt(int a2, double a3, T a1) {
		return Function4U.defaultInteger;
	}

	/** Does nothing (LTieDblFunction.LDblObjIntToIntFunc) Function */
	public static <T> int produceInt(double a3, T a1, int a2) {
		return Function4U.defaultInteger;
	}

	/** Does nothing (LTieDblFunction.LDblIntObjToIntFunc) Function */
	public static <T> int produceInt(double a3, int a2, T a1) {
		return Function4U.defaultInteger;
	}

	/** ***ITERATION:    TIE_CONSUMER_GEN:  FOR, [SourcePurpose{arg=int sStart, type=CONST}, SourcePurpose{arg=int sEnd, type=CONST}, SourcePurpose{arg=int tStart, type=CONST}, SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=double a3, type=TIE_SOURCE}, SourcePurpose{arg=double a3, type=TIE_GEN_SUPPLIER}] */
	default <SRC> int genericTieForEach(int sStart, int sEnd, int tStart, T trg1, SRC src3, OiFunction<SRC, aDouble> srcAcc3) {
		return tieForEach(sStart, sEnd, tStart, trg1, src3, (LOiToDblFunction<SRC>) srcAcc3, this);

	}

	/** ***ITERATION:    TARGETED_INDEXED_FOR_EACH:  FOR, [SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=double a3, type=IA}, SourcePurpose{arg=LTieDblFunction<? super T> consumer, type=CONST}] */
	public static <T, C3> T tiForEach(T trg1, IndexedRead<C3, aDouble> ia3, C3 source3, LTieDblFunction<? super T> consumer) {

		tieForEach(trg1, ia3, source3, consumer);

		return trg1;

	}

	/** ***ITERATION:    TARGETED_INDEXED_FOR_EACH_NEW:  FOR, [SourcePurpose{arg=T trg1, type=SIZE_FACTORY}, SourcePurpose{arg=double a3, type=IA}, SourcePurpose{arg=LTieDblFunction<? super T> consumer, type=CONST}] */
	public static <T, C3> T ntiForEach(LIntFunction<T> trgFactory1, IndexedRead<C3, aDouble> ia3, C3 source3, LTieDblFunction<? super T> consumer) {
		int size = ia3.size(source3);
		T trg1 = trgFactory1.apply(size);
		tieForEach(0, size, 0, trg1, source3, ia3.getter(), consumer);
		return trg1;
	}

	/** ***ITERATION:    TIE_CONSUMER_SHORT:  FOR, [SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=double a3, type=IA}, SourcePurpose{arg=LTieDblFunction<? super T> consumer, type=CONST}] */
	public static <T, C3> int tieForEach(T trg1, IndexedRead<C3, aDouble> ia3, C3 source3, LTieDblFunction<? super T> consumer) {
		int size = ia3.size(source3);
		return tieForEach(0, size, 0, trg1, source3, ia3.getter(), consumer);
	}

	/** ***ITERATION:    TIE_CONSUMER:  FOR, [SourcePurpose{arg=int sStart, type=CONST}, SourcePurpose{arg=int sEnd, type=CONST}, SourcePurpose{arg=int tStart, type=CONST}, SourcePurpose{arg=T trg1, type=CONST}, SourcePurpose{arg=double a3, type=TIE_SOURCE}, SourcePurpose{arg=double a3, type=TIE_SUPPLIER}, SourcePurpose{arg=LTieDblFunction<? super T> consumer, type=CONST}] */
	public static <T, SRC> int tieForEach(int sStart, int sEnd, int tStart, T trg1, SRC src3, LOiToDblFunction<SRC> srcAcc3, LTieDblFunction<? super T> consumer) {
		int tIndex = tStart;
		for (int sIndex = sStart; sIndex < sEnd; sIndex++) {
			double a3 = srcAcc3.applyAsDbl(src3, sIndex);
			tIndex += consumer.applyAsInt(trg1, tIndex, a3);
		}
		return tIndex - tStart;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with TIE: 'target', index, element). First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns increment count based on consumer function
	*/
	public static <T, SRC> int tieForEach(int sStart, int tStart, T trg1, SRC src3, LPredicate<SRC> srcTest3, LToDblFunction<SRC> srcAcc3, LTieDblFunction<? super T> consumer) {
		int tIndex = tStart;
		for (; srcTest3.test(src3); tIndex++) {
			double a3 = srcAcc3.applyAsDbl(src3);
			tIndex += consumer.applyAsInt(trg1, tIndex, a3);
		}
		return tIndex - sStart;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with TIE: 'target', index, element). First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns increment count based on consumer function
	*/
	public static <T, C3, I3> int tieIterate(T trg1, SequentialRead<C3, I3, aDouble> sa3, C3 source3, LTieDblFunction<? super T> consumer) {
		LFunction<C3, I3> toIntermediate = sa3.adapter();
		return tieForEach(0, 0, trg1, toIntermediate.apply(source3), sa3.tester(), sa3.supplier(), consumer);
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with TIE: 'target', index, element). First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns 'target' object
	*/
	public static <T, C3, I3> T tiIterate(T trg1, SequentialRead<C3, I3, aDouble> sa3, C3 source3, LTieDblFunction<? super T> consumer) {

		tieIterate(trg1, sa3, source3, consumer);

		return trg1;

	}

	/** ***ITERATION:    TARGETED_INDEXED_ITERATE_NEW:  WHILE, [SourcePurpose{arg=T trg1, type=SUPPLIER}, SourcePurpose{arg=double a3, type=SA}, SourcePurpose{arg=LTieDblFunction<? super T> consumer, type=CONST}] */
	public static <T, C3, I3> T ntiIterate(LSupplier<T> source1, SequentialRead<C3, I3, aDouble> sa3, C3 source3, LTieDblFunction<? super T> consumer) {
		T trg1 = source1.get();
		tieIterate(trg1, sa3, source3, consumer);

		return trg1;

	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, C3> void forEach(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, IndexedRead<C3, aDouble> ia3, C3 source3, LIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToDblFunction<Object> oiFunc3 = (LOiToDblFunction) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = oiFunc2.applyAsInt(source2, i);
			double a3 = oiFunc3.applyAsDbl(source3, i);
			consumer.accept(this.applyAsInt(a1, a2, a3));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, C3> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, IndexedRead<C3, aDouble> ia3, C3 source3, LIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToDblFunction<Object> oiFunc3 = (LOiToDblFunction) ia3.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = oiFunc2.applyAsInt(source2, i);
			double a3 = oiFunc3.applyAsDbl(source3, i);
			consumer.accept(this.applyAsInt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2, C3> void iterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, IndexedRead<C3, aDouble> ia3, C3 source3, LIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		size = Integer.min(size, ia3.size(source3));
		LOiToDblFunction<Object> oiFunc3 = (LOiToDblFunction) ia3.getter();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = nextFunc2.applyAsInt(iterator2);
			double a3 = oiFunc3.applyAsDbl(source3, i);
			consumer.accept(this.applyAsInt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, I2, C3> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, IndexedRead<C3, aDouble> ia3, C3 source3, LIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		int size = ia3.size(source3);
		LOiToDblFunction<Object> oiFunc3 = (LOiToDblFunction) ia3.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && i < size) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = nextFunc2.applyAsInt(iterator2);
			double a3 = oiFunc3.applyAsDbl(source3, i);
			consumer.accept(this.applyAsInt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, C3, I3> void iterate(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, SequentialRead<C3, I3, aDouble> sa3, C3 source3, LIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToDblFunction<Object> nextFunc3 = (LToDblFunction) sa3.supplier();
		int i = 0;
		while (i < size && testFunc3.test(iterator3)) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = oiFunc2.applyAsInt(source2, i);
			double a3 = nextFunc3.applyAsDbl(iterator3);
			consumer.accept(this.applyAsInt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, C3, I3> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, SequentialRead<C3, I3, aDouble> sa3, C3 source3, LIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToDblFunction<Object> nextFunc3 = (LToDblFunction) sa3.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size && testFunc3.test(iterator3)) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = oiFunc2.applyAsInt(source2, i);
			double a3 = nextFunc3.applyAsDbl(iterator3);
			consumer.accept(this.applyAsInt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2, C3, I3> void iterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, SequentialRead<C3, I3, aDouble> sa3, C3 source3, LIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToDblFunction<Object> nextFunc3 = (LToDblFunction) sa3.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = nextFunc2.applyAsInt(iterator2);
			double a3 = nextFunc3.applyAsDbl(iterator3);
			consumer.accept(this.applyAsInt(a1, a2, a3));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C1, I1, C2, I2, C3, I3> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, SequentialRead<C3, I3, aDouble> sa3, C3 source3, LIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToDblFunction<Object> nextFunc3 = (LToDblFunction) sa3.supplier();
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = nextFunc2.applyAsInt(iterator2);
			double a3 = nextFunc3.applyAsDbl(iterator3);
			consumer.accept(this.applyAsInt(a1, a2, a3));
		}
	}

}