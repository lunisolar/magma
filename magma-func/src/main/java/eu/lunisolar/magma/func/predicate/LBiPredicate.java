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

package eu.lunisolar.magma.func.predicate;

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
 * Non-throwing functional interface (lambda) LBiPredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 2): T1 a1,T2 a2
 *
 * Co-domain: boolean
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiPredicate<T1, T2> extends BiPredicate<T1, T2>, MetaPredicate, MetaInterface.NonThrowing, Codomain<aBool>, Domain2<a<T1>, a<T2>> { // NOSONAR

	String DESCRIPTION = "LBiPredicate: boolean test(T1 a1,T2 a2)";

	// boolean test(T1 a1,T2 a2) ;
	default boolean test(T1 a1, T2 a2) {
		// return nestingTest(a1,a2);
		try {
			return this.testX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call test(T1 a1,T2 a2)
	 */
	boolean testX(T1 a1, T2 a2) throws Throwable;

	default boolean tupleTest(LPair<T1, T2> args) {
		return test(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default boolean handlingTest(T1 a1, T2 a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.testX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LBiPredicate<T1, T2> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2) -> handlingTest(a1, a2, handling);
	}

	default boolean test(T1 a1, T2 a2, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.testX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LBiPredicate<T1, T2> trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return (a1, a2) -> test(a1, a2, exF, newMessage, messageParams);
	}

	default boolean test(T1 a1, T2 a2, @Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.testX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LBiPredicate<T1, T2> trying(@Nonnull ExWF<RuntimeException> exF) {
		return (a1, a2) -> test(a1, a2, exF);
	}

	default boolean testThen(T1 a1, T2 a2, @Nonnull LPredicate<Throwable> handler) {
		try {
			return this.testX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.test(e);
		}
	}

	default LBiPredicate<T1, T2> tryingThen(@Nonnull LPredicate<Throwable> handler) {
		return (a1, a2) -> testThen(a1, a2, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingTest(T1 a1, T2 a2) {
		try {
			return this.testX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default boolean shovingTest(T1 a1, T2 a2) {
		try {
			return this.testX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T1, T2> boolean handlingTest(T1 a1, T2 a2, LBiPredicate<T1, T2> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingTest(a1, a2, handling);
	}

	static <T1, T2> boolean tryTest(T1 a1, T2 a2, LBiPredicate<T1, T2> func) {
		Null.nonNullArg(func, "func");
		return func.nestingTest(a1, a2);
	}

	static <T1, T2> boolean tryTest(T1 a1, T2 a2, LBiPredicate<T1, T2> func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, exF, newMessage, messageParams);
	}

	static <T1, T2> boolean tryTest(T1 a1, T2 a2, LBiPredicate<T1, T2> func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, exF);
	}

	static <T1, T2> boolean tryTestThen(T1 a1, T2 a2, LBiPredicate<T1, T2> func, @Nonnull LPredicate<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.testThen(a1, a2, handler);
	}

	default boolean failSafeTest(T1 a1, T2 a2, @Nonnull LBiPredicate<T1, T2> failSafe) {
		try {
			return test(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.test(a1, a2);
		}
	}

	static <T1, T2> boolean failSafeTest(T1 a1, T2 a2, LBiPredicate<T1, T2> func, @Nonnull LBiPredicate<T1, T2> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.test(a1, a2);
		} else {
			return func.failSafeTest(a1, a2, failSafe);
		}
	}

	static <T1, T2> LBiPredicate<T1, T2> failSafe(LBiPredicate<T1, T2> func, @Nonnull LBiPredicate<T1, T2> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeTest(a1, a2, func, failSafe);
	}

	default boolean doIf(T1 a1, T2 a2, LAction action) {
		Null.nonNullArg(action, "action");
		if (test(a1, a2)) {
			action.execute();
			return true;
		} else {
			return false;
		}
	}

	static <T1, T2> boolean doIf(T1 a1, T2 a2, @Nonnull LBiPredicate<T1, T2> predicate, @Nonnull LAction action) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.doIf(a1, a2, action);
	}

	static <T1, T2> boolean doIf(T1 a1, T2 a2, @Nonnull LBiPredicate<T1, T2> predicate, @Nonnull LBiConsumer<? super T1, ? super T2> consumer) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.doIf(a1, a2, consumer);
	}

	default boolean doIf(T1 a1, T2 a2, @Nonnull LBiConsumer<? super T1, ? super T2> consumer) {
		Null.nonNullArg(consumer, "consumer");
		if (test(a1, a2)) {
			consumer.accept(a1, a2);
			return true;
		} else {
			return false;
		}
	}

	static <T1, T2> void throwIf(T1 a1, T2 a2, LBiPredicate<T1, T2> pred, ExMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		if (pred.test(a1, a2)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
	}

	static <T1, T2> void throwIfNot(T1 a1, T2 a2, LBiPredicate<T1, T2> pred, ExMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		if (!pred.test(a1, a2)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullTest(T1 a1, T2 a2) {
		return test(a1, a2);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(T1 a1, T2 a2) {
		return test(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiPredicate.DESCRIPTION;
	}

	public default <V> boolean doIf(V a1, T1 a2, T2 a3, LTriConsumer<V, ? super T1, ? super T2> consumer) {
		if (test(a2, a3)) {
			consumer.accept(a1, a2, a3);
			return true;
		} else {
			return false;
		}
	}

	/** 2 */
	public default <V> int doIf(V a1, T1 a2, T2 a3, LToIntTriFunction<V, ? super T1, ? super T2> consumer) {
		if (test(a2, a3)) {
			return consumer.applyAsInt(a1, a2, a3);
		} else {
			return 0;
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void fromTo(int min_i, int max_i, T1 a1, T2 a2, LBiPredicate<T1, T2> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.test(a1, a2);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.test(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void fromTill(int min_i, int max_i, T1 a1, T2 a2, LBiPredicate<T1, T2> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.test(a1, a2);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.test(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void times(int max_i, T1 a1, T2 a2, LBiPredicate<T1, T2> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, func);
	}

	/** Extract and apply function. */
	public static <M, K, V, T2> boolean from(M container, LBiFunction<M, K, V> extractor, K key, T2 a2, LBiPredicate<V, T2> function) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.test(value, a2);
		}

		return false;
	}

	public default LPredicate<T2> lShrink(LFunction<T2, T1> left) {
		return a2 -> test(left.apply(a2), a2);
	}

	public default LPredicate<T2> lShrinkc(T1 a1) {
		return a2 -> test(a1, a2);
	}

	public static <T2, T1> LPredicate<T2> lShrinked(LFunction<T2, T1> left, LBiPredicate<T1, T2> func) {
		return func.lShrink(left);
	}

	public static <T2, T1> LPredicate<T2> lShrinkedc(T1 a1, LBiPredicate<T1, T2> func) {
		return func.lShrinkc(a1);
	}

	public default LPredicate<T1> rShrink(LFunction<T1, T2> right) {
		return a1 -> test(a1, right.apply(a1));
	}

	public default LPredicate<T1> rShrinkc(T2 a2) {
		return a1 -> test(a1, a2);
	}

	public static <T1, T2> LPredicate<T1> rShrinked(LFunction<T1, T2> right, LBiPredicate<T1, T2> func) {
		return func.rShrink(right);
	}

	public static <T1, T2> LPredicate<T1> rShrinkedc(T2 a2, LBiPredicate<T1, T2> func) {
		return func.rShrinkc(a2);
	}

	/**  */
	public static <T1, T2> LBiPredicate<T1, T2> uncurry(LFunction<T1, LPredicate<T2>> func) {
		return (T1 a1, T2 a2) -> func.apply(a1).test(a2);
	}

	/** Cast that removes generics. */
	public default LBiPredicate untyped() {
		return this;
	}

	/** Cast that replace generics. */
	public default <V2, V3> LBiPredicate<V2, V3> cast() {
		return untyped();
	}

	/** Cast that replace generics. */
	public static <V2, V3, T1, T2> LBiPredicate<V2, V3> cast(LBiPredicate<T1, T2> function) {
		return (LBiPredicate) function;
	}

	/** Captures arguments but delays the evaluation. */
	default LBoolSupplier capture(T1 a1, T2 a2) {
		return () -> this.test(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static <T1, T2> LBiPredicate<T1, T2> constant(boolean r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T1, T2> LBiPredicate<T1, T2> test1st(@Nonnull LPredicate<T1> func) {
		return (a1, a2) -> func.test(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T1, T2> LBiPredicate<T1, T2> test2nd(@Nonnull LPredicate<T2> func) {
		return (a1, a2) -> func.test(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2> LBiPredicate<T1, T2> biPred(final @Nonnull LBiPredicate<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T1, T2> LBiPredicate<T1, T2> recursive(final @Nonnull LFunction<LBiPredicate<T1, T2>, LBiPredicate<T1, T2>> selfLambda) {
		final LBiPredicateSingle<T1, T2> single = new LBiPredicateSingle();
		LBiPredicate<T1, T2> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LBiPredicateSingle<T1, T2> implements LSingle<LBiPredicate<T1, T2>>, LBiPredicate<T1, T2> {
		private LBiPredicate<T1, T2> target = null;

		@Override
		public boolean testX(T1 a1, T2 a2) throws Throwable {
			return target.testX(a1, a2);
		}

		@Override
		public LBiPredicate<T1, T2> value() {
			return target;
		}
	}

	@Nonnull
	static <T1, T2> LBiPredicate<T1, T2> biPredThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T1, T2> LBiPredicate<T1, T2> biPredThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1> LObj1Obj0Pred<T2, T1> obj1Obj0Pred(final @Nonnull LObj1Obj0Pred<T2, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T1, T2> boolean call(T1 a1, T2 a2, final @Nonnull LBiPredicate<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.test(a1, a2);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <T1, T2> LBiPredicate<T1, T2> wrap(final BiPredicate<T1, T2> other) {
		return other::test;
	}
	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as alwaysFalse). */
	@Nonnull
	static <T1, T2> LBiPredicate<T1, T2> safe() {
		return LBiPredicate::alwaysFalse;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T1, T2> LSupplier<LBiPredicate<T1, T2>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T1, T2> LBiPredicate<T1, T2> safe(final @Nullable LBiPredicate<T1, T2> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T1, T2> LSupplier<LBiPredicate<T1, T2>> safeSupplier(final @Nullable LSupplier<LBiPredicate<T1, T2>> supplier) {
		if (supplier == null) {
			return safeSupplier();
		} else {
			return supplier;
		}
	}

	// </editor-fold>
	// <editor-fold desc="predicate">

	/**
	 * Returns a predicate that represents the logical negation of this predicate.
	 *
	 * @see {@link java.util.function.Predicate#negate}
	 */
	@Nonnull
	default LBiPredicate<T1, T2> negate() {
		return (a1, a2) -> !test(a1, a2);
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LBiPredicate<T1, T2> and(@Nonnull LBiPredicate<? super T1, ? super T2> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2) -> test(a1, a2) && other.test(a1, a2);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LBiPredicate<T1, T2> or(@Nonnull LBiPredicate<? super T1, ? super T2> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2) -> test(a1, a2) || other.test(a1, a2);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LBiPredicate<T1, T2> xor(@Nonnull LBiPredicate<? super T1, ? super T2> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2) -> test(a1, a2) ^ other.test(a1, a2);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static <T1, T2> LBiPredicate<T1, T2> isEqual(T1 v1, T2 v2) {
		return (a1, a2) -> (a1 == null ? v1 == null : a1.equals(v1)) && (a2 == null ? v2 == null : a2.equals(v2));
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiPredicate<V1, V2> compose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.test(before1.apply(v1), before2.apply(v2));
	}

	public static <V1, V2, T1, T2> LBiPredicate<V1, V2> composed(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, LBiPredicate<T1, T2> after) {
		return after.compose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiFunction<T1, T2, V> boolToBiFunc(@Nonnull LBoolFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.apply(this.test(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToByteBiFunction<T1, T2> boolToToByteBiFunc(@Nonnull LBoolToByteFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsByte(this.test(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToSrtBiFunction<T1, T2> boolToToSrtBiFunc(@Nonnull LBoolToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsSrt(this.test(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToIntBiFunction<T1, T2> boolToToIntBiFunc(@Nonnull LBoolToIntFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsInt(this.test(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToLongBiFunction<T1, T2> boolToToLongBiFunc(@Nonnull LBoolToLongFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsLong(this.test(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToFltBiFunction<T1, T2> boolToToFltBiFunc(@Nonnull LBoolToFltFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsFlt(this.test(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToDblBiFunction<T1, T2> boolToToDblBiFunc(@Nonnull LBoolToDblFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsDbl(this.test(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToCharBiFunction<T1, T2> boolToToCharBiFunc(@Nonnull LBoolToCharFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.applyAsChar(this.test(a1, a2));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiPredicate<T1, T2> boolToBiPred(@Nonnull LLogicalOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.apply(this.test(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LBiPredicate for method references. */
	@FunctionalInterface
	interface LObj1Obj0Pred<T2, T1> extends LBiPredicate<T1, T2> {

		boolean testObj1Obj0(T2 a2, T1 a1);

		@Override
		default boolean testX(T1 a1, T2 a2) {
			return this.testObj1Obj0(a2, a1);
		}
	}

	// </editor-fold>

	// >>> LBiPredicate<T1,T2>

	/** Returns TRUE. */
	public static <T1, T2> boolean alwaysTrue(T1 a1, T2 a2) {
		return true;
	}

	/** Returns FALSE. */
	public static <T1, T2> boolean alwaysFalse(T1 a1, T2 a2) {
		return false;
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2> void filterForEach(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, LBiConsumer<? super T1, ? super T2> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = oiFunc2.apply(source2, i);
			doIf(a1, a2, consumer);
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2> void filterIterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, LBiConsumer<? super T1, ? super T2> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = oiFunc2.apply(source2, i);
			doIf(a1, a2, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2> void filterIterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, LBiConsumer<? super T1, ? super T2> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = nextFunc2.apply(iterator2);
			doIf(a1, a2, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C1, I1, C2, I2> void filterIterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, LBiConsumer<? super T1, ? super T2> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2)) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = nextFunc2.apply(iterator2);
			doIf(a1, a2, consumer);
		}
	}

}
