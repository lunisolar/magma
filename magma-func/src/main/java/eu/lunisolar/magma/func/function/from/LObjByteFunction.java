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
 * Non-throwing functional interface (lambda) LObjByteFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 2): T a1,byte a2
 *
 * Co-domain: R
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LObjByteFunction<T, R> extends MetaFunction, MetaInterface.NonThrowing, Codomain<a<R>>, Domain2<a<T>, aByte> { // NOSONAR

	String DESCRIPTION = "LObjByteFunction: R apply(T a1,byte a2)";

	@Nullable
	// R apply(T a1,byte a2) ;
	default R apply(T a1, byte a2) {
		// return nestingApply(a1,a2);
		try {
			return this.applyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call apply(T a1,byte a2)
	 */
	R applyX(T a1, byte a2) throws Throwable;

	default R tupleApply(LObjBytePair<T> args) {
		return apply(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default R handlingApply(T a1, byte a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LObjByteFunction<T, R> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2) -> handlingApply(a1, a2, handling);
	}

	default R apply(T a1, byte a2, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage) {
		try {
			return this.applyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage);
		}
	}

	default R apply(T a1, byte a2, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, param1);
		}
	}

	default R apply(T a1, byte a2, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, param1, param2);
		}
	}

	default R apply(T a1, byte a2, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, param1, param2, param3);
		}
	}

	default LObjByteFunction<T, R> trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage) {
		return (a1, a2) -> apply(a1, a2, exF, newMessage);
	}

	default LObjByteFunction<T, R> trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2) -> apply(a1, a2, exF, newMessage, param1);
	}

	default LObjByteFunction<T, R> trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2) -> apply(a1, a2, exF, newMessage, param1, param1);
	}

	default LObjByteFunction<T, R> trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2) -> apply(a1, a2, exF, newMessage, param1, param2, param3);
	}

	default R apply(T a1, byte a2, @Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.applyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LObjByteFunction<T, R> trying(@Nonnull ExWF<RuntimeException> exF) {
		return (a1, a2) -> apply(a1, a2, exF);
	}

	default R applyThen(T a1, byte a2, @Nonnull LFunction<Throwable, R> handler) {
		try {
			return this.applyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.apply(e);
		}
	}

	default LObjByteFunction<T, R> tryingThen(@Nonnull LFunction<Throwable, R> handler) {
		return (a1, a2) -> applyThen(a1, a2, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default R nestingApply(T a1, byte a2) {
		try {
			return this.applyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default R shovingApply(T a1, byte a2) {
		try {
			return this.applyX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T, R> R handlingApply(T a1, byte a2, LObjByteFunction<T, R> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApply(a1, a2, handling);
	}

	static <T, R> R tryApply(T a1, byte a2, LObjByteFunction<T, R> func) {
		Null.nonNullArg(func, "func");
		return func.nestingApply(a1, a2);
	}

	static <T, R> R tryApply(T a1, byte a2, LObjByteFunction<T, R> func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, exF, newMessage);
	}

	static <T, R> R tryApply(T a1, byte a2, LObjByteFunction<T, R> func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, exF, newMessage, param1);
	}

	static <T, R> R tryApply(T a1, byte a2, LObjByteFunction<T, R> func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, exF, newMessage, param1, param2);
	}

	static <T, R> R tryApply(T a1, byte a2, LObjByteFunction<T, R> func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, exF, newMessage, param1, param2, param3);
	}

	static <T, R> R tryApply(T a1, byte a2, LObjByteFunction<T, R> func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, exF);
	}

	static <T, R> R tryApplyThen(T a1, byte a2, LObjByteFunction<T, R> func, @Nonnull LFunction<Throwable, R> handler) {
		Null.nonNullArg(func, "func");
		return func.applyThen(a1, a2, handler);
	}

	default R failSafeApply(T a1, byte a2, @Nonnull LObjByteFunction<T, R> failSafe) {
		try {
			return apply(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.apply(a1, a2);
		}
	}

	static <T, R> R failSafeApply(T a1, byte a2, LObjByteFunction<T, R> func, @Nonnull LObjByteFunction<T, R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.apply(a1, a2);
		} else {
			return func.failSafeApply(a1, a2, failSafe);
		}
	}

	static <T, R> LObjByteFunction<T, R> failSafe(LObjByteFunction<T, R> func, @Nonnull LObjByteFunction<T, R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeApply(a1, a2, func, failSafe);
	}

	LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullApply() method cannot be null (" + DESCRIPTION + ").";

	/** Function call that ensures the result is not null */
	@Nonnull
	default R nonNullApply(T a1, byte a2) {
		return Null.nonNull(apply(a1, a2), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LObjByteFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T, R> void fromTo(int min_i, int max_i, T a1, byte a2, @Nonnull LObjByteFunction<T, R> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.apply(a1, a2);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.apply(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T, R> void fromTill(int min_i, int max_i, T a1, byte a2, @Nonnull LObjByteFunction<T, R> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.apply(a1, a2);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.apply(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T, R> void times(int max_i, T a1, byte a2, @Nonnull LObjByteFunction<T, R> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, func);
	}

	/** Extract and apply function. */
	public static <R, M, K, V> R from(@Nonnull M container, LBiFunction<M, K, V> extractor, K key, byte a2, @Nonnull LObjByteFunction<V, R> function) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.apply(value, a2);
		}

		return null;
	}

	default LByteFunction<R> lShrink(@Nonnull LByteFunction<T> left) {
		Null.nonNullArg(left, "left");
		return a2 -> apply(left.apply(a2), a2);
	}

	default LByteFunction<R> lShrink_(T a1) {
		return a2 -> apply(a1, a2);
	}

	public static <R, T> LByteFunction<R> lShrunken(@Nonnull LByteFunction<T> left, @Nonnull LObjByteFunction<T, R> func) {
		Null.nonNullArg(left, "left");
		Null.nonNullArg(func, "func");
		return func.lShrink(left);
	}

	public static <R, T> LByteFunction<R> lShrunken_(T a1, @Nonnull LObjByteFunction<T, R> func) {
		Null.nonNullArg(func, "func");
		return func.lShrink_(a1);
	}

	default LFunction<T, R> rShrink(@Nonnull LToByteFunction<T> right) {
		Null.nonNullArg(right, "right");
		return a1 -> apply(a1, right.applyAsByte(a1));
	}

	default LFunction<T, R> rShrink_(byte a2) {
		return a1 -> apply(a1, a2);
	}

	public static <T, R> LFunction<T, R> rShrunken(@Nonnull LToByteFunction<T> right, @Nonnull LObjByteFunction<T, R> func) {
		Null.nonNullArg(right, "right");
		Null.nonNullArg(func, "func");
		return func.rShrink(right);
	}

	public static <T, R> LFunction<T, R> rShrunken_(byte a2, @Nonnull LObjByteFunction<T, R> func) {
		Null.nonNullArg(func, "func");
		return func.rShrink_(a2);
	}

	/**  */
	public static <T, R> LObjByteFunction<T, R> uncurry(@Nonnull LFunction<T, LByteFunction<R>> func) {
		Null.nonNullArg(func, "func");
		return (T a1, byte a2) -> func.apply(a1).apply(a2);
	}

	/** Cast that removes generics. */
	default LObjByteFunction untyped() {
		return this;
	}

	/** Cast that replace generics. */
	default <V2, V3> LObjByteFunction<V2, V3> cast() {
		return untyped();
	}

	/** Cast that replace generics. */
	public static <V2, V3, T, R> LObjByteFunction<V2, V3> cast(LObjByteFunction<T, R> function) {
		return (LObjByteFunction) function;
	}

	/** Change function to consumer that ignores output. */
	default LObjByteConsumer<T> toConsumer() {
		return this::apply;
	}

	/** Calls domain consumer before main function. */
	default LObjByteFunction<T, R> beforeDo(@Nonnull LObjByteConsumer<T> before) {
		Null.nonNullArg(before, "before");
		return (T a1, byte a2) -> {
			before.accept(a1, a2);
			return apply(a1, a2);
		};
	}

	/** Calls codomain consumer after main function. */
	default LObjByteFunction<T, R> afterDo(@Nonnull LConsumer<R> after) {
		Null.nonNullArg(after, "after");
		return (T a1, byte a2) -> {
			final R retval = apply(a1, a2);
			after.accept(retval);
			return retval;
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplier<R> capture(T a1, byte a2) {
		return () -> this.apply(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static <T, R> LObjByteFunction<T, R> constant(R r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T, R> LObjByteFunction<T, R> apply1st(@Nonnull LFunction<T, R> func) {
		return (a1, a2) -> func.apply(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T, R> LObjByteFunction<T, R> apply2nd(@Nonnull LByteFunction<R> func) {
		return (a1, a2) -> func.apply(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, R> LObjByteFunction<T, R> objByteFunc(final @Nonnull LObjByteFunction<T, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** A completely inconvenient method in case lambda expression and generic arguments are ambiguous for the compiler. */
	@Nonnull
	static <T, R> LObjByteFunction<T, R> objByteFunc(@Nullable Class<T> c1, @Nullable Class<R> c2, final @Nonnull LObjByteFunction<T, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	final class S<T, R> implements LObjByteFunction<T, R> {
		private LObjByteFunction<T, R> target = null;
		@Override
		public R applyX(T a1, byte a2) throws Throwable {
			return target.applyX(a1, a2);
		}
	}

	@Nonnull
	static <T, R> LObjByteFunction<T, R> recursive(final @Nonnull LFunction<LObjByteFunction<T, R>, LObjByteFunction<T, R>> selfLambda) {
		final S<T, R> single = new S();
		LObjByteFunction<T, R> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	/**
	 * Memento of a function, initialized with value from it.
	 */
	public static <T, R> M<T, R> mementoOf(T a1, byte a2, LObjByteFunction<T, R> function) {
		var initialValue = function.apply(a1, a2);
		return initializedMementoOf(initialValue, function);
	}

	/**
	 * Memento of a function, initialized with argument value.
	 */
	public static <T, R> M<T, R> initializedMementoOf(R initialValue, LObjByteFunction<T, R> function) {
		return memento(initialValue, function, (x1, x2) -> x2);
	}

	public static <T, R> M<T, R> deltaOf(T a1, byte a2, LObjByteFunction<T, R> function, LBinaryOperator<R> deltaFunction) {
		var initialValue = function.apply(a1, a2);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	/**
	 * Delta of a function result, initialized with argument value.
	 */
	public static <T, R> M<T, R> initializedDeltaOf(R initialValue, LObjByteFunction<T, R> function, LBinaryOperator<R> deltaFunction) {
		return memento(initialValue, function, deltaFunction);
	}

	/**
	 * Creates function that remembers previous result of itself and applies a memento-function on it an current result of base function.
	 * Basically, provided that calls and arguments (if applicable) represents progression of some sort, makes possible to apply functions like MAX. MIN, DELTA on the result of the base function.
	 */
	public static <T, R> M<T, R> memento(R initialValue, LObjByteFunction<T, R> baseFunction, LBinaryOperator<R> mementoFunction) {
		return new M(initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not ). Very short name is intended to be used with parent (LObjByteFunction.D)
	 */
	final class M<T, R> implements LObjByteFunction<T, R> {

		private R lastValue;
		private final LBinaryOperator<R> mementoFunction;
		private final LObjByteFunction<T, R> baseFunction;

		private M(R lastValue, LObjByteFunction<T, R> baseFunction, LBinaryOperator<R> mementoFunction) {
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
			this.baseFunction = baseFunction;
		}

		@Override
		public R applyX(T a1, byte a2) throws Throwable {
			R x2 = baseFunction.applyX(a1, a2);
			R x1 = lastValue;
			return lastValue = mementoFunction.apply(x1, x2);
		}

		public R lastValue() {
			return lastValue;
		};
	}

	@Nonnull
	static <T, R> LObjByteFunction<T, R> objByteFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T, R> LObjByteFunction<T, R> objByteFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T, R> LObjByteFunction.LByteObjFunc<T, R> byteObjFunc(final @Nonnull LObjByteFunction.LByteObjFunc<T, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T, R> R call(T a1, byte a2, final @Nonnull LObjByteFunction<T, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.apply(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as doNothing). */
	@Nonnull
	static <T, R> LObjByteFunction<T, R> safe() {
		return LObjByteFunction::doNothing;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T, R> LSupplier<LObjByteFunction<T, R>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T, R> LObjByteFunction<T, R> safe(final @Nullable LObjByteFunction<T, R> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T, R> LSupplier<LObjByteFunction<T, R>> safeSupplier(final @Nullable LSupplier<LObjByteFunction<T, R>> supplier) {
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
	default <V1> LObjByteFunction<V1, R> compose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LByteUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.apply(before1.apply(v1), before2.applyAsByte(v2));
	}

	public static <V1, T, R> LObjByteFunction<V1, R> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LByteUnaryOperator before2, LObjByteFunction<T, R> after) {
		return after.compose(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiFunction<V1, V2, R> objByteFuncCompose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToByteFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.apply(before1.apply(v1), before2.applyAsByte(v2));
	}

	public static <V1, V2, T, R> LBiFunction<V1, V2, R> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToByteFunction<? super V2> before2, LObjByteFunction<T, R> after) {
		return after.objByteFuncCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LObjByteFunction<T, V> then(@Nonnull LFunction<? super R, ? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.apply(this.apply(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LObjByteConsumer<T> thenConsume(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.accept(this.apply(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LObjBytePredicate<T> thenToBool(@Nonnull LPredicate<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.test(this.apply(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LObjByteFunction<T, R> nonNullable() {
		return this::nonNullApply;
	}

	// <editor-fold desc="interface variants">

	/** Permutation of LObjByteFunction for method references. */
	@FunctionalInterface
	interface LByteObjFunc<T, R> extends LObjByteFunction<T, R> {

		/**
		 * Implement this, but call apply(T a1,byte a2)
		 */
		default R applyX(T a1, byte a2) {
			return this.applyByteObj(a2, a1);
		}

		@Nullable
		// R applyByteObj(byte a2,T a1) ;
		default R applyByteObj(byte a2, T a1) {
			// return nestingApplyByteObj(a2,a1);
			try {
				return this.applyByteObjX(a2, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyByteObj(byte a2,T a1)
		 */
		R applyByteObjX(byte a2, T a1) throws Throwable;
	}

	// </editor-fold>

	/** Does nothing (LObjByteFunction) Function */
	public static <T, R> R doNothing(T a1, byte a2) {
		return (R) Function4U.defaultObject;
	}

	/** Does nothing (LObjByteFunction.LByteObjFunc) Function */
	public static <T, R> R doNothing(byte a2, T a1) {
		return (R) Function4U.defaultObject;
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2> void forEach(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aByte> ia2, C2 source2, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToByteFunction<Object> oiFunc2 = (LOiToByteFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			T a1 = oiFunc1.apply(source1, i);
			byte a2 = oiFunc2.applyAsByte(source2, i);
			consumer.accept(this.apply(a1, a2));
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aByte> ia2, C2 source2, LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToByteFunction<Object> oiFunc2 = (LOiToByteFunction) ia2.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			T a1 = nextFunc1.apply(iterator1);
			byte a2 = oiFunc2.applyAsByte(source2, i);
			consumer.accept(this.apply(a1, a2));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2> void iterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aByte> sa2, C2 source2, LConsumer<? super R> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToByteFunction<Object> nextFunc2 = (LToByteFunction) sa2.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			T a1 = oiFunc1.apply(source1, i);
			byte a2 = nextFunc2.applyAsByte(iterator2);
			consumer.accept(this.apply(a1, a2));
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the function and passes the result to consumer.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C1, I1, C2, I2> void iterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aByte> sa2, C2 source2, LConsumer<? super R> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToByteFunction<Object> nextFunc2 = (LToByteFunction) sa2.supplier();
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2)) {
			T a1 = nextFunc1.apply(iterator1);
			byte a2 = nextFunc2.applyAsByte(iterator2);
			consumer.accept(this.apply(a1, a2));
		}
	}

}
