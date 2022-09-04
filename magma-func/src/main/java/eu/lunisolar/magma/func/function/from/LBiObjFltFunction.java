/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2022 Lunisolar (http://lunisolar.eu/).
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
 * Non-throwing functional interface (lambda) LBiObjFltFunction for Java 8.
 *
 * Type: function
 *
 * Domain (lvl: 3): T1 a1,T2 a2,float a3
 *
 * Co-domain: R
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiObjFltFunction<T1, T2, R> extends MetaFunction, MetaInterface.NonThrowing, Codomain<a<R>>, Domain3<a<T1>, a<T2>, aFloat> { // NOSONAR

	String DESCRIPTION = "LBiObjFltFunction: R apply(T1 a1,T2 a2,float a3)";

	@Nullable
	// R apply(T1 a1,T2 a2,float a3) ;
	default R apply(T1 a1, T2 a2, float a3) {
		// return nestingApply(a1,a2,a3);
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call apply(T1 a1,T2 a2,float a3)
	 */
	R applyX(T1 a1, T2 a2, float a3) throws Throwable;

	default R tupleApply(LBiObjFltTriple<T1, T2> args) {
		return apply(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions according to the instructions. */
	default R handlingApply(T1 a1, T2 a2, float a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LBiObjFltFunction<T1, T2, R> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> handlingApply(a1, a2, a3, handling);
	}

	default R apply(T1 a1, T2 a2, float a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default R apply(T1 a1, T2 a2, float a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default R apply(T1 a1, T2 a2, float a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default R apply(T1 a1, T2 a2, float a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LBiObjFltFunction<T1, T2, R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2, a3) -> apply(a1, a2, a3, factory, newMessage);
	}

	default LBiObjFltFunction<T1, T2, R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2, a3) -> apply(a1, a2, a3, factory, newMessage, param1);
	}

	default LBiObjFltFunction<T1, T2, R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2, a3) -> apply(a1, a2, a3, factory, newMessage, param1, param1);
	}

	default LBiObjFltFunction<T1, T2, R> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2, a3) -> apply(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	default R apply(T1 a1, T2 a2, float a3, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LBiObjFltFunction<T1, T2, R> trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2, a3) -> apply(a1, a2, a3, factory);
	}

	default R applyThen(T1 a1, T2 a2, float a3, @Nonnull LFunction<Throwable, R> handler) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.apply(e);
		}
	}

	default LBiObjFltFunction<T1, T2, R> tryingThen(@Nonnull LFunction<Throwable, R> handler) {
		return (a1, a2, a3) -> applyThen(a1, a2, a3, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default R nestingApply(T1 a1, T2 a2, float a3) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default R shovingApply(T1 a1, T2 a2, float a3) {
		try {
			return this.applyX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T1, T2, R> R shovingApply(T1 a1, T2 a2, float a3, LBiObjFltFunction<T1, T2, R> func) {
		Null.nonNullArg(func, "func");
		return func.shovingApply(a1, a2, a3);
	}

	static <T1, T2, R> R handlingApply(T1 a1, T2 a2, float a3, LBiObjFltFunction<T1, T2, R> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingApply(a1, a2, a3, handling);
	}

	static <T1, T2, R> R tryApply(T1 a1, T2 a2, float a3, LBiObjFltFunction<T1, T2, R> func) {
		Null.nonNullArg(func, "func");
		return func.nestingApply(a1, a2, a3);
	}

	static <T1, T2, R> R tryApply(T1 a1, T2 a2, float a3, LBiObjFltFunction<T1, T2, R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, factory, newMessage);
	}

	static <T1, T2, R> R tryApply(T1 a1, T2 a2, float a3, LBiObjFltFunction<T1, T2, R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, factory, newMessage, param1);
	}

	static <T1, T2, R> R tryApply(T1 a1, T2 a2, float a3, LBiObjFltFunction<T1, T2, R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, factory, newMessage, param1, param2);
	}

	static <T1, T2, R> R tryApply(T1 a1, T2 a2, float a3, LBiObjFltFunction<T1, T2, R> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	static <T1, T2, R> R tryApply(T1 a1, T2 a2, float a3, LBiObjFltFunction<T1, T2, R> func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.apply(a1, a2, a3, factory);
	}

	static <T1, T2, R> R tryApplyThen(T1 a1, T2 a2, float a3, LBiObjFltFunction<T1, T2, R> func, @Nonnull LFunction<Throwable, R> handler) {
		Null.nonNullArg(func, "func");
		return func.applyThen(a1, a2, a3, handler);
	}

	default R failSafeApply(T1 a1, T2 a2, float a3, @Nonnull LBiObjFltFunction<T1, T2, R> failSafe) {
		try {
			return apply(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.apply(a1, a2, a3);
		}
	}

	static <T1, T2, R> R failSafeApply(T1 a1, T2 a2, float a3, LBiObjFltFunction<T1, T2, R> func, @Nonnull LBiObjFltFunction<T1, T2, R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.apply(a1, a2, a3);
		} else {
			return func.failSafeApply(a1, a2, a3, failSafe);
		}
	}

	static <T1, T2, R> LBiObjFltFunction<T1, T2, R> failSafe(LBiObjFltFunction<T1, T2, R> func, @Nonnull LBiObjFltFunction<T1, T2, R> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeApply(a1, a2, a3, func, failSafe);
	}

	LSupplier<String> NULL_VALUE_MESSAGE_SUPPLIER = () -> "Evaluated value by nonNullApply() method cannot be null (" + DESCRIPTION + ").";

	/** Function call that ensures the result is not null */
	@Nonnull
	default R nonNullApply(T1 a1, T2 a2, float a3) {
		return Null.nonNull(apply(a1, a2, a3), NULL_VALUE_MESSAGE_SUPPLIER);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiObjFltFunction.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, R> void fromTo(int min_i, int max_i, T1 a1, T2 a2, float a3, @Nonnull LBiObjFltFunction<T1, T2, R> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.apply(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.apply(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, R> void fromTill(int min_i, int max_i, T1 a1, T2 a2, float a3, @Nonnull LBiObjFltFunction<T1, T2, R> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.apply(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.apply(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, R> void times(int max_i, T1 a1, T2 a2, float a3, @Nonnull LBiObjFltFunction<T1, T2, R> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, a3, func);
	}

	/** Extract and apply function. */
	public static <R, M, K, V, T2> R from(@Nonnull M container, LBiFunction<M, K, V> extractor, K key, T2 a2, float a3, @Nonnull LBiObjFltFunction<V, T2, R> function) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.apply(value, a2, a3);
		}

		return null;
	}

	default LObjFltFunction<T2, R> lShrink(@Nonnull LObjFltFunction<T2, T1> left) {
		Null.nonNullArg(left, "left");
		return (a2, a3) -> apply(left.apply(a2, a3), a2, a3);
	}

	default LObjFltFunction<T2, R> lShrink_(T1 a1) {
		return (a2, a3) -> apply(a1, a2, a3);
	}

	public static <T2, R, T1> LObjFltFunction<T2, R> lShrunken(@Nonnull LObjFltFunction<T2, T1> left, @Nonnull LBiObjFltFunction<T1, T2, R> func) {
		Null.nonNullArg(left, "left");
		Null.nonNullArg(func, "func");
		return func.lShrink(left);
	}

	public static <T2, R, T1> LObjFltFunction<T2, R> lShrunken_(T1 a1, @Nonnull LBiObjFltFunction<T1, T2, R> func) {
		Null.nonNullArg(func, "func");
		return func.lShrink_(a1);
	}

	default LBiFunction<T1, T2, R> rShrink(@Nonnull LToFltBiFunction<T1, T2> right) {
		Null.nonNullArg(right, "right");
		return (a1, a2) -> apply(a1, a2, right.applyAsFlt(a1, a2));
	}

	default LBiFunction<T1, T2, R> rShrink_(float a3) {
		return (a1, a2) -> apply(a1, a2, a3);
	}

	public static <T1, T2, R> LBiFunction<T1, T2, R> rShrunken(@Nonnull LToFltBiFunction<T1, T2> right, @Nonnull LBiObjFltFunction<T1, T2, R> func) {
		Null.nonNullArg(right, "right");
		Null.nonNullArg(func, "func");
		return func.rShrink(right);
	}

	public static <T1, T2, R> LBiFunction<T1, T2, R> rShrunken_(float a3, @Nonnull LBiObjFltFunction<T1, T2, R> func) {
		Null.nonNullArg(func, "func");
		return func.rShrink_(a3);
	}

	/**  */
	public static <T1, T2, R> LBiObjFltFunction<T1, T2, R> uncurry(@Nonnull LFunction<T1, LFunction<T2, LFltFunction<R>>> func) {
		Null.nonNullArg(func, "func");
		return (T1 a1, T2 a2, float a3) -> func.apply(a1).apply(a2).apply(a3);
	}

	/** Cast that removes generics. */
	default LBiObjFltFunction untyped() {
		return this;
	}

	/** Cast that replace generics. */
	default <V2, V3, V4> LBiObjFltFunction<V2, V3, V4> cast() {
		return untyped();
	}

	/** Cast that replace generics. */
	public static <V2, V3, V4> LBiObjFltFunction<V2, V3, V4> cast(LBiObjFltFunction<?, ?, ?> function) {
		return (LBiObjFltFunction) function;
	}

	/** Change function to consumer that ignores output. */
	default LBiObjFltConsumer<T1, T2> toConsumer() {
		return this::apply;
	}

	/** Calls domain consumer before main function. */
	default LBiObjFltFunction<T1, T2, R> beforeDo(@Nonnull LBiObjFltConsumer<T1, T2> before) {
		Null.nonNullArg(before, "before");
		return (T1 a1, T2 a2, float a3) -> {
			before.accept(a1, a2, a3);
			return apply(a1, a2, a3);
		};
	}

	/** Calls codomain consumer after main function. */
	default LBiObjFltFunction<T1, T2, R> afterDo(@Nonnull LConsumer<R> after) {
		Null.nonNullArg(after, "after");
		return (T1 a1, T2 a2, float a3) -> {
			final R retval = apply(a1, a2, a3);
			after.accept(retval);
			return retval;
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LSupplier<R> capture(T1 a1, T2 a2, float a3) {
		return () -> this.apply(a1, a2, a3);
	}

	/** Creates function that always returns the same value. */
	static <T1, T2, R> LBiObjFltFunction<T1, T2, R> constant(R r) {
		return (a1, a2, a3) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T1, T2, R> LBiObjFltFunction<T1, T2, R> apply1st(@Nonnull LFunction<T1, R> func) {
		return (a1, a2, a3) -> func.apply(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T1, T2, R> LBiObjFltFunction<T1, T2, R> apply2nd(@Nonnull LFunction<T2, R> func) {
		return (a1, a2, a3) -> func.apply(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T1, T2, R> LBiObjFltFunction<T1, T2, R> apply3rd(@Nonnull LFltFunction<R> func) {
		return (a1, a2, a3) -> func.apply(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, R> LBiObjFltFunction<T1, T2, R> biObjFltFunc(final @Nonnull LBiObjFltFunction<T1, T2, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** A completely inconvenient method in case lambda expression and generic arguments are ambiguous for the compiler. */
	@Nonnull
	static <T1, T2, R> LBiObjFltFunction<T1, T2, R> biObjFltFunc(@Nullable Class<T1> c1, @Nullable Class<T2> c2, @Nullable Class<R> c3, final @Nonnull LBiObjFltFunction<T1, T2, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	final class S<T1, T2, R> implements LBiObjFltFunction<T1, T2, R> {
		private LBiObjFltFunction<T1, T2, R> target = null;
		@Override
		public R applyX(T1 a1, T2 a2, float a3) throws Throwable {
			return target.applyX(a1, a2, a3);
		}
	}

	@Nonnull
	static <T1, T2, R> LBiObjFltFunction<T1, T2, R> recursive(final @Nonnull LFunction<LBiObjFltFunction<T1, T2, R>, LBiObjFltFunction<T1, T2, R>> selfLambda) {
		final S<T1, T2, R> single = new S();
		LBiObjFltFunction<T1, T2, R> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	public static <T1, T2, R> M<T1, T2, R> mementoOf(T1 a1, T2 a2, float a3, LBiObjFltFunction<T1, T2, R> function) {
		var initialValue = function.apply(a1, a2, a3);
		return initializedMementoOf(initialValue, function);
	}

	public static <T1, T2, R> M<T1, T2, R> initializedMementoOf(R initialValue, LBiObjFltFunction<T1, T2, R> function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static <T1, T2, R> M<T1, T2, R> deltaOf(T1 a1, T2 a2, float a3, LBiObjFltFunction<T1, T2, R> function, LBinaryOperator<R> deltaFunction) {
		var initialValue = function.apply(a1, a2, a3);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static <T1, T2, R> M<T1, T2, R> initializedDeltaOf(R initialValue, LBiObjFltFunction<T1, T2, R> function, LBinaryOperator<R> deltaFunction) {
		return memento(initialValue, deltaFunction.apply(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.apply(x1, x2));
	}

	public static <T1, T2, R> M<T1, T2, R> memento(R initialBaseValue, R initialValue, LBiObjFltFunction<T1, T2, R> baseFunction, LTernaryOperator<R> mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LBiObjFltFunction.M)
	 */
	@NotThreadSafe
	final class M<T1, T2, R> implements LBiObjFltFunction<T1, T2, R> {

		private final LBiObjFltFunction<T1, T2, R> baseFunction;
		private R lastBaseValue;
		private R lastValue;
		private final LTernaryOperator<R> mementoFunction;

		private M(R lastBaseValue, R lastValue, LBiObjFltFunction<T1, T2, R> baseFunction, LTernaryOperator<R> mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public R applyX(T1 a1, T2 a2, float a3) throws Throwable {
			R x1 = lastBaseValue;
			R x2 = lastBaseValue = baseFunction.applyX(a1, a2, a3);

			return lastValue = mementoFunction.apply(lastValue, x1, x2);
		}

		public R lastValue() {
			return lastValue;
		};

		public R lastBaseValue() {
			return lastBaseValue;
		};
	}

	@Nonnull
	static <T1, T2, R> LBiObjFltFunction<T1, T2, R> biObjFltFuncThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T1, T2, R> LBiObjFltFunction<T1, T2, R> biObjFltFuncThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, R> LBiObjFltFunction.LObj0Flt2Obj1Func<T1, T2, R> obj0Flt2Obj1Func(final @Nonnull LBiObjFltFunction.LObj0Flt2Obj1Func<T1, T2, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1, R> LBiObjFltFunction.LObj1Obj0Flt2Func<T2, T1, R> obj1Obj0Flt2Func(final @Nonnull LBiObjFltFunction.LObj1Obj0Flt2Func<T2, T1, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1, R> LBiObjFltFunction.LObj1Flt2Obj0Func<T2, T1, R> obj1Flt2Obj0Func(final @Nonnull LBiObjFltFunction.LObj1Flt2Obj0Func<T2, T1, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, R> LBiObjFltFunction.LFlt2Obj0Obj1Func<T1, T2, R> flt2Obj0Obj1Func(final @Nonnull LBiObjFltFunction.LFlt2Obj0Obj1Func<T1, T2, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1, R> LBiObjFltFunction.LFlt2Obj1Obj0Func<T2, T1, R> flt2Obj1Obj0Func(final @Nonnull LBiObjFltFunction.LFlt2Obj1Obj0Func<T2, T1, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T1, T2, R> R call(T1 a1, T2 a2, float a3, final @Nonnull LBiObjFltFunction<T1, T2, R> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.apply(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiObjFltFunction<V1, V2, R> compose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LFltUnaryOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.apply(before1.apply(v1), before2.apply(v2), before3.applyAsFlt(v3));
	}

	public static <V1, V2, T1, T2, R> LBiObjFltFunction<V1, V2, R> composed(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LFltUnaryOperator before3,
			LBiObjFltFunction<T1, T2, R> after) {
		return after.compose(before1, before2, before3);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriFunction<V1, V2, V3, R> biObjFltFuncCompose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LToFltFunction<? super V3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.apply(before1.apply(v1), before2.apply(v2), before3.applyAsFlt(v3));
	}

	public static <V1, V2, V3, T1, T2, R> LTriFunction<V1, V2, V3, R> composed(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LToFltFunction<? super V3> before3,
			LBiObjFltFunction<T1, T2, R> after) {
		return after.biObjFltFuncCompose(before1, before2, before3);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiObjFltFunction<T1, T2, V> then(@Nonnull LFunction<? super R, ? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.apply(this.apply(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiObjFltConsumer<T1, T2> thenConsume(@Nonnull LConsumer<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.accept(this.apply(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiObjFltPredicate<T1, T2> thenToBool(@Nonnull LPredicate<? super R> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.test(this.apply(a1, a2, a3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	/** Converts to function that makes sure that the result is not null. */
	@Nonnull
	default LBiObjFltFunction<T1, T2, R> nonNullable() {
		return this::nonNullApply;
	}

	// <editor-fold desc="interface variants">

	/** Permutation of LBiObjFltFunction for method references. */
	@FunctionalInterface
	interface LObj0Flt2Obj1Func<T1, T2, R> extends LBiObjFltFunction<T1, T2, R> {

		/**
		 * Implement this, but call apply(T1 a1,T2 a2,float a3)
		 */
		default R applyX(T1 a1, T2 a2, float a3) {
			return this.applyObj0Flt2Obj1(a1, a3, a2);
		}

		@Nullable
		// R applyObj0Flt2Obj1(T1 a1,float a3,T2 a2) ;
		default R applyObj0Flt2Obj1(T1 a1, float a3, T2 a2) {
			// return nestingApplyObj0Flt2Obj1(a1,a3,a2);
			try {
				return this.applyObj0Flt2Obj1X(a1, a3, a2);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyObj0Flt2Obj1(T1 a1,float a3,T2 a2)
		 */
		R applyObj0Flt2Obj1X(T1 a1, float a3, T2 a2) throws Throwable;
	}

	/** Permutation of LBiObjFltFunction for method references. */
	@FunctionalInterface
	interface LObj1Obj0Flt2Func<T2, T1, R> extends LBiObjFltFunction<T1, T2, R> {

		/**
		 * Implement this, but call applyObj0Flt2Obj1(T1 a1,float a3,T2 a2)
		 */
		default R applyX(T1 a1, T2 a2, float a3) {
			return this.applyObj1Obj0Flt2(a2, a1, a3);
		}

		@Nullable
		// R applyObj1Obj0Flt2(T2 a2,T1 a1,float a3) ;
		default R applyObj1Obj0Flt2(T2 a2, T1 a1, float a3) {
			// return nestingApplyObj1Obj0Flt2(a2,a1,a3);
			try {
				return this.applyObj1Obj0Flt2X(a2, a1, a3);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyObj1Obj0Flt2(T2 a2,T1 a1,float a3)
		 */
		R applyObj1Obj0Flt2X(T2 a2, T1 a1, float a3) throws Throwable;
	}

	/** Permutation of LBiObjFltFunction for method references. */
	@FunctionalInterface
	interface LObj1Flt2Obj0Func<T2, T1, R> extends LBiObjFltFunction<T1, T2, R> {

		/**
		 * Implement this, but call applyObj1Obj0Flt2(T2 a2,T1 a1,float a3)
		 */
		default R applyX(T1 a1, T2 a2, float a3) {
			return this.applyObj1Flt2Obj0(a2, a3, a1);
		}

		@Nullable
		// R applyObj1Flt2Obj0(T2 a2,float a3,T1 a1) ;
		default R applyObj1Flt2Obj0(T2 a2, float a3, T1 a1) {
			// return nestingApplyObj1Flt2Obj0(a2,a3,a1);
			try {
				return this.applyObj1Flt2Obj0X(a2, a3, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyObj1Flt2Obj0(T2 a2,float a3,T1 a1)
		 */
		R applyObj1Flt2Obj0X(T2 a2, float a3, T1 a1) throws Throwable;
	}

	/** Permutation of LBiObjFltFunction for method references. */
	@FunctionalInterface
	interface LFlt2Obj0Obj1Func<T1, T2, R> extends LBiObjFltFunction<T1, T2, R> {

		/**
		 * Implement this, but call applyObj1Flt2Obj0(T2 a2,float a3,T1 a1)
		 */
		default R applyX(T1 a1, T2 a2, float a3) {
			return this.applyFlt2Obj0Obj1(a3, a1, a2);
		}

		@Nullable
		// R applyFlt2Obj0Obj1(float a3,T1 a1,T2 a2) ;
		default R applyFlt2Obj0Obj1(float a3, T1 a1, T2 a2) {
			// return nestingApplyFlt2Obj0Obj1(a3,a1,a2);
			try {
				return this.applyFlt2Obj0Obj1X(a3, a1, a2);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyFlt2Obj0Obj1(float a3,T1 a1,T2 a2)
		 */
		R applyFlt2Obj0Obj1X(float a3, T1 a1, T2 a2) throws Throwable;
	}

	/** Permutation of LBiObjFltFunction for method references. */
	@FunctionalInterface
	interface LFlt2Obj1Obj0Func<T2, T1, R> extends LBiObjFltFunction<T1, T2, R> {

		/**
		 * Implement this, but call applyFlt2Obj0Obj1(float a3,T1 a1,T2 a2)
		 */
		default R applyX(T1 a1, T2 a2, float a3) {
			return this.applyFlt2Obj1Obj0(a3, a2, a1);
		}

		@Nullable
		// R applyFlt2Obj1Obj0(float a3,T2 a2,T1 a1) ;
		default R applyFlt2Obj1Obj0(float a3, T2 a2, T1 a1) {
			// return nestingApplyFlt2Obj1Obj0(a3,a2,a1);
			try {
				return this.applyFlt2Obj1Obj0X(a3, a2, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call applyFlt2Obj1Obj0(float a3,T2 a2,T1 a1)
		 */
		R applyFlt2Obj1Obj0X(float a3, T2 a2, T1 a1) throws Throwable;
	}

	// </editor-fold>

	/** Does nothing (LBiObjFltFunction) Function */
	public static <T1, T2, R> R doNothing(T1 a1, T2 a2, float a3) {
		return (R) Function4U.defaultObject;
	}

	/** Does nothing (LBiObjFltFunction.LObj0Flt2Obj1Func) Function */
	public static <T1, T2, R> R doNothing(T1 a1, float a3, T2 a2) {
		return (R) Function4U.defaultObject;
	}

	/** Does nothing (LBiObjFltFunction.LFlt2Obj0Obj1Func) Function */
	public static <T1, T2, R> R doNothing(float a3, T1 a1, T2 a2) {
		return (R) Function4U.defaultObject;
	}

}
