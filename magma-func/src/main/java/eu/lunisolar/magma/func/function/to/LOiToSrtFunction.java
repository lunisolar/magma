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
import javax.annotation.concurrent.NotThreadSafe; // NOSONAR
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

import java.util.concurrent.*; // NOSONAR
import java.util.function.*; // NOSONAR
import java.util.*; // NOSONAR
import java.lang.reflect.*; // NOSONAR

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
 * Non-throwing functional interface (lambda) LOiToSrtFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 2): T a1,int a2
 *
 * Co-domain: short
 *
 * Special case of function that corresponds to expressions like (list, index) -> List::get
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LOiToSrtFunction<T> extends MetaFunction, MetaInterface.NonThrowing, OiFunction<T, aShort>, Codomain<aShort>, Domain2<a<T>, aInt> { // NOSONAR

	String DESCRIPTION = "LOiToSrtFunction: short applyAsSrt(T a1,int a2)";

	// short applyAsSrt(T a1,int a2) ;
	default short applyAsSrt(T a1, int a2) {
		// return nestingApplyAsSrt(a1,a2);
		try {
			return this.applyAsSrtX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call applyAsSrt(T a1,int a2)
	 */
	short applyAsSrtX(T a1, int a2) throws Throwable;

	default short tupleApplyAsSrt(LObjIntPair<T> args) {
		return applyAsSrt(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default short handlingApplyAsSrt(T a1, int a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyAsSrtX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LOiToSrtFunction<T> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2) -> handlingApplyAsSrt(a1, a2, handling);
	}

	default short applyAsSrt(T a1, int a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyAsSrtX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default short applyAsSrt(T a1, int a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyAsSrtX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default short applyAsSrt(T a1, int a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyAsSrtX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default short applyAsSrt(T a1, int a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyAsSrtX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LOiToSrtFunction<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2) -> applyAsSrt(a1, a2, factory, newMessage);
	}

	default LOiToSrtFunction<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2) -> applyAsSrt(a1, a2, factory, newMessage, param1);
	}

	default LOiToSrtFunction<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2) -> applyAsSrt(a1, a2, factory, newMessage, param1, param1);
	}

	default LOiToSrtFunction<T> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2) -> applyAsSrt(a1, a2, factory, newMessage, param1, param2, param3);
	}

	default short applyAsSrt(T a1, int a2, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyAsSrtX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LOiToSrtFunction<T> trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2) -> applyAsSrt(a1, a2, factory);
	}

	default short applyAsSrtThen(T a1, int a2, @Nonnull LToSrtFunction<Throwable> handler) {
		try {
			return this.applyAsSrtX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.applyAsSrt(e);
		}
	}

	default LOiToSrtFunction<T> tryingThen(@Nonnull LToSrtFunction<Throwable> handler) {
		return (a1, a2) -> applyAsSrtThen(a1, a2, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default short nestingApplyAsSrt(T a1, int a2) {
		try {
			return this.applyAsSrtX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default short shovingApplyAsSrt(T a1, int a2) {
		try {
			return this.applyAsSrtX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> short shovingApplyAsSrt(T a1, int a2, LOiToSrtFunction<T> func) {
		Null.nonNullArg(func, "func");
		return func.shovingApplyAsSrt(a1, a2);
	}

	static <T> short handlingApplyAsSrt(T a1, int a2, LOiToSrtFunction<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApplyAsSrt(a1, a2, handling);
	}

	static <T> short tryApplyAsSrt(T a1, int a2, LOiToSrtFunction<T> func) {
		Null.nonNullArg(func, "func");
		return func.nestingApplyAsSrt(a1, a2);
	}

	static <T> short tryApplyAsSrt(T a1, int a2, LOiToSrtFunction<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrt(a1, a2, factory, newMessage);
	}

	static <T> short tryApplyAsSrt(T a1, int a2, LOiToSrtFunction<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrt(a1, a2, factory, newMessage, param1);
	}

	static <T> short tryApplyAsSrt(T a1, int a2, LOiToSrtFunction<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrt(a1, a2, factory, newMessage, param1, param2);
	}

	static <T> short tryApplyAsSrt(T a1, int a2, LOiToSrtFunction<T> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrt(a1, a2, factory, newMessage, param1, param2, param3);
	}

	static <T> short tryApplyAsSrt(T a1, int a2, LOiToSrtFunction<T> func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrt(a1, a2, factory);
	}

	static <T> short tryApplyAsSrtThen(T a1, int a2, LOiToSrtFunction<T> func, @Nonnull LToSrtFunction<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.applyAsSrtThen(a1, a2, handler);
	}

	default short failSafeApplyAsSrt(T a1, int a2, @Nonnull LOiToSrtFunction<T> failSafe) {
		try {
			return applyAsSrt(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.applyAsSrt(a1, a2);
		}
	}

	static <T> short failSafeApplyAsSrt(T a1, int a2, LOiToSrtFunction<T> func, @Nonnull LOiToSrtFunction<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.applyAsSrt(a1, a2);
		} else {
			return func.failSafeApplyAsSrt(a1, a2, failSafe);
		}
	}

	static <T> LOiToSrtFunction<T> failSafe(LOiToSrtFunction<T> func, @Nonnull LOiToSrtFunction<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeApplyAsSrt(a1, a2, func, failSafe);
	}

	/** Just to mirror the method: Ensures the result is not null */
	default short nonNullApplyAsSrt(T a1, int a2) {
		return applyAsSrt(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LOiToSrtFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_a2, int max_a2, T a1, @Nonnull LOiToSrtFunction<T> func) {
		Null.nonNullArg(func, "func");
		if (min_a2 <= max_a2) {
			for (int a2 = min_a2; a2 <= max_a2; a2++) {
				func.applyAsSrt(a1, a2);
			}
		} else {
			for (int a2 = min_a2; a2 >= max_a2; a2--) {
				func.applyAsSrt(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTill(int min_a2, int max_a2, T a1, @Nonnull LOiToSrtFunction<T> func) {
		Null.nonNullArg(func, "func");
		if (min_a2 <= max_a2) {
			for (int a2 = min_a2; a2 < max_a2; a2++) {
				func.applyAsSrt(a1, a2);
			}
		} else {
			for (int a2 = min_a2; a2 > max_a2; a2--) {
				func.applyAsSrt(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void times(int max_a2, T a1, @Nonnull LOiToSrtFunction<T> func) {
		if (max_a2 < 0)
			return;
		fromTill(0, max_a2, a1, func);
	}

	/** Extract and apply function. */
	public static <M, K, V> short from(@Nonnull M container, LBiFunction<M, K, V> extractor, K key, int a2, @Nonnull LOiToSrtFunction<V> function, short orElse) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.applyAsSrt(value, a2);
		}

		return orElse;
	}

	default LIntToSrtFunction lShrink(@Nonnull LIntFunction<T> left) {
		Null.nonNullArg(left, "left");
		return a2 -> applyAsSrt(left.apply(a2), a2);
	}

	default LIntToSrtFunction lShrink_(T a1) {
		return a2 -> applyAsSrt(a1, a2);
	}

	public static <T> LIntToSrtFunction lShrunken(@Nonnull LIntFunction<T> left, @Nonnull LOiToSrtFunction<T> func) {
		Null.nonNullArg(left, "left");
		Null.nonNullArg(func, "func");
		return func.lShrink(left);
	}

	public static <T> LIntToSrtFunction lShrunken_(T a1, @Nonnull LOiToSrtFunction<T> func) {
		Null.nonNullArg(func, "func");
		return func.lShrink_(a1);
	}

	default LToSrtFunction<T> rShrink(@Nonnull LToIntFunction<T> right) {
		Null.nonNullArg(right, "right");
		return a1 -> applyAsSrt(a1, right.applyAsInt(a1));
	}

	default LToSrtFunction<T> rShrink_(int a2) {
		return a1 -> applyAsSrt(a1, a2);
	}

	public static <T> LToSrtFunction<T> rShrunken(@Nonnull LToIntFunction<T> right, @Nonnull LOiToSrtFunction<T> func) {
		Null.nonNullArg(right, "right");
		Null.nonNullArg(func, "func");
		return func.rShrink(right);
	}

	public static <T> LToSrtFunction<T> rShrunken_(int a2, @Nonnull LOiToSrtFunction<T> func) {
		Null.nonNullArg(func, "func");
		return func.rShrink_(a2);
	}

	/**  */
	public static <T> LOiToSrtFunction<T> uncurry(@Nonnull LFunction<T, LIntToSrtFunction> func) {
		Null.nonNullArg(func, "func");
		return (T a1, int a2) -> func.apply(a1).applyAsSrt(a2);
	}

	/** Cast that removes generics. */
	default LOiToSrtFunction untyped() {
		return this;
	}

	/** Cast that replace generics. */
	default <V2> LOiToSrtFunction<V2> cast() {
		return untyped();
	}

	/** Cast that replace generics. */
	public static <V2> LOiToSrtFunction<V2> cast(LOiToSrtFunction<?> function) {
		return (LOiToSrtFunction) function;
	}

	/** Change function to consumer that ignores output. */
	default LObjIntConsumer<T> toConsumer() {
		return this::applyAsSrt;
	}

	/** Calls domain consumer before main function. */
	default LOiToSrtFunction<T> beforeDo(@Nonnull LObjIntConsumer<T> before) {
		Null.nonNullArg(before, "before");
		return (T a1, int a2) -> {
			before.accept(a1, a2);
			return applyAsSrt(a1, a2);
		};
	}

	/** Calls codomain consumer after main function. */
	default LOiToSrtFunction<T> afterDo(@Nonnull LSrtConsumer after) {
		Null.nonNullArg(after, "after");
		return (T a1, int a2) -> {
			final short retval = applyAsSrt(a1, a2);
			after.accept(retval);
			return retval;
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LSrtSupplier capture(T a1, int a2) {
		return () -> this.applyAsSrt(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static <T> LOiToSrtFunction<T> constant(short r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T> LOiToSrtFunction<T> apply1stAsSrt(@Nonnull LToSrtFunction<T> func) {
		return (a1, a2) -> func.applyAsSrt(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T> LOiToSrtFunction<T> apply2ndAsSrt(@Nonnull LIntToSrtFunction func) {
		return (a1, a2) -> func.applyAsSrt(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LOiToSrtFunction<T> oiToSrtFunc(final @Nonnull LOiToSrtFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** A completely inconvenient method in case lambda expression and generic arguments are ambiguous for the compiler. */
	@Nonnull
	static <T> LOiToSrtFunction<T> oiToSrtFunc(@Nullable Class<T> c1, final @Nonnull LOiToSrtFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	final class S<T> implements LOiToSrtFunction<T> {
		private LOiToSrtFunction<T> target = null;
		@Override
		public short applyAsSrtX(T a1, int a2) throws Throwable {
			return target.applyAsSrtX(a1, a2);
		}
	}

	@Nonnull
	static <T> LOiToSrtFunction<T> recursive(final @Nonnull LFunction<LOiToSrtFunction<T>, LOiToSrtFunction<T>> selfLambda) {
		final S<T> single = new S();
		LOiToSrtFunction<T> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	public static <T> M<T> mementoOf(T a1, int a2, LOiToSrtFunction<T> function) {
		var initialValue = function.applyAsSrt(a1, a2);
		return initializedMementoOf(initialValue, function);
	}

	public static <T> M<T> initializedMementoOf(short initialValue, LOiToSrtFunction<T> function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static <T> M<T> deltaOf(T a1, int a2, LOiToSrtFunction<T> function, LSrtBinaryOperator deltaFunction) {
		var initialValue = function.applyAsSrt(a1, a2);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static <T> M<T> deltaOf(T a1, int a2, LOiToSrtFunction<T> function) {
		var initialValue = function.applyAsSrt(a1, a2);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> (short) (x2 - x1));
	}

	public static <T> M<T> initializedDeltaOf(short initialValue, LOiToSrtFunction<T> function, LSrtBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.applyAsSrt(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.applyAsSrt(x1, x2));
	}

	public static <T> M<T> memento(short initialBaseValue, short initialValue, LOiToSrtFunction<T> baseFunction, LSrtTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LOiToSrtFunction.M)
	 */
	@NotThreadSafe
	final class M<T> implements LOiToSrtFunction<T> {

		private final LOiToSrtFunction<T> baseFunction;
		private short lastBaseValue;
		private short lastValue;
		private final LSrtTernaryOperator mementoFunction;

		private M(short lastBaseValue, short lastValue, LOiToSrtFunction<T> baseFunction, LSrtTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public short applyAsSrtX(T a1, int a2) throws Throwable {
			short x1 = lastBaseValue;
			short x2 = lastBaseValue = baseFunction.applyAsSrtX(a1, a2);

			return lastValue = mementoFunction.applyAsSrt(lastValue, x1, x2);
		}

		public short lastValue() {
			return lastValue;
		};

		public short lastBaseValue() {
			return lastBaseValue;
		};
	}

	@Nonnull
	static <T> LOiToSrtFunction<T> oiToSrtFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T> LOiToSrtFunction<T> oiToSrtFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LOiToSrtFunction.LIntObjToSrtFunc<T> intObjToSrtFunc(final @Nonnull LOiToSrtFunction.LIntObjToSrtFunc<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T> short call(T a1, int a2, final @Nonnull LOiToSrtFunction<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.applyAsSrt(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1> LOiToSrtFunction<V1> compose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.applyAsSrt(before1.apply(v1), before2.applyAsInt(v2));
	}

	public static <V1, T> LOiToSrtFunction<V1> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2, LOiToSrtFunction<T> after) {
		return after.compose(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LToSrtBiFunction<V1, V2> oiToSrtFuncCompose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.applyAsSrt(before1.apply(v1), before2.applyAsInt(v2));
	}

	public static <V1, V2, T> LToSrtBiFunction<V1, V2> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2, LOiToSrtFunction<T> after) {
		return after.oiToSrtFuncCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LOiFunction<T, V> then(@Nonnull LSrtFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.apply(this.applyAsSrt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToByteFunction<T> thenToByte(@Nonnull LSrtToByteFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsByte(this.applyAsSrt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToSrtFunction<T> thenToSrt(@Nonnull LSrtUnaryOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsSrt(this.applyAsSrt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToIntFunction<T> thenToInt(@Nonnull LSrtToIntFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsInt(this.applyAsSrt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToLongFunction<T> thenToLong(@Nonnull LSrtToLongFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsLong(this.applyAsSrt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToFltFunction<T> thenToFlt(@Nonnull LSrtToFltFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsFlt(this.applyAsSrt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToDblFunction<T> thenToDbl(@Nonnull LSrtToDblFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsDbl(this.applyAsSrt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LOiToCharFunction<T> thenToChar(@Nonnull LSrtToCharFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsChar(this.applyAsSrt(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LObjIntPredicate<T> thenToBool(@Nonnull LSrtPredicate after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.test(this.applyAsSrt(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LOiToSrtFunction for method references. */
	@FunctionalInterface
	interface LIntObjToSrtFunc<T> extends LOiToSrtFunction<T> {

		/**
		 * Implement this, but call applyAsSrt(T a1,int a2)
		 */
		default short applyAsSrtX(T a1, int a2) {
			return this.applyAsSrtIntObj(a2, a1);
		}

		// short applyAsSrtIntObj(int a2,T a1) ;
		default short applyAsSrtIntObj(int a2, T a1) {
			// return nestingApplyAsSrtIntObj(a2,a1);
			try {
				return this.applyAsSrtIntObjX(a2, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyAsSrtIntObj(int a2,T a1)
		 */
		short applyAsSrtIntObjX(int a2, T a1) throws Throwable;
	}

	// </editor-fold>

	/** Does nothing (LOiToSrtFunction) Function */
	public static <T> short doNothing(T a1, int a2) {
		return Function4U.defaultShort;
	}

	/** Does nothing (LOiToSrtFunction.LIntObjToSrtFunc) Function */
	public static <T> short doNothing(int a2, T a1) {
		return Function4U.defaultShort;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2> void forEach(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, LSrtConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = oiFunc2.applyAsInt(source2, i);
			consumer.accept(this.applyAsSrt(a1, a2));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, LSrtConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = oiFunc2.applyAsInt(source2, i);
			consumer.accept(this.applyAsSrt(a1, a2));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2> void iterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, LSrtConsumer consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = nextFunc2.applyAsInt(iterator2);
			consumer.accept(this.applyAsSrt(a1, a2));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C1, I1, C2, I2> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, LSrtConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2)) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = nextFunc2.applyAsInt(iterator2);
			consumer.accept(this.applyAsSrt(a1, a2));
		}
	}

}
