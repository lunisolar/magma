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
 * Non-throwing functional interface (lambda) LQuadPredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 4): T1 a1,T2 a2,T3 a3,T4 a4
 *
 * Co-domain: boolean
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LQuadPredicate<T1, T2, T3, T4> extends MetaPredicate, MetaInterface.NonThrowing, Codomain<aBool>, Domain4<a<T1>, a<T2>, a<T3>, a<T4>> { // NOSONAR

	String DESCRIPTION = "LQuadPredicate: boolean test(T1 a1,T2 a2,T3 a3,T4 a4)";

	// boolean test(T1 a1,T2 a2,T3 a3,T4 a4) ;
	default boolean test(T1 a1, T2 a2, T3 a3, T4 a4) {
		// return nestingTest(a1,a2,a3,a4);
		try {
			return this.testX(a1, a2, a3, a4);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call test(T1 a1,T2 a2,T3 a3,T4 a4)
	 */
	boolean testX(T1 a1, T2 a2, T3 a3, T4 a4) throws Throwable;

	default boolean tupleTest(LQuad<T1, T2, T3, T4> args) {
		return test(args.first(), args.second(), args.third(), args.fourth());
	}

	/** Function call that handles exceptions according to the instructions. */
	default boolean handlingTest(T1 a1, T2 a2, T3 a3, T4 a4, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.testX(a1, a2, a3, a4);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LQuadPredicate<T1, T2, T3, T4> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3, a4) -> handlingTest(a1, a2, a3, a4, handling);
	}

	default boolean test(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage) {
		try {
			return this.testX(a1, a2, a3, a4);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage);
		}
	}

	default boolean test(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.testX(a1, a2, a3, a4);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, param1);
		}
	}

	default boolean test(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.testX(a1, a2, a3, a4);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, param1, param2);
		}
	}

	default boolean test(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.testX(a1, a2, a3, a4);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, param1, param2, param3);
		}
	}

	default LQuadPredicate<T1, T2, T3, T4> trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage) {
		return (a1, a2, a3, a4) -> test(a1, a2, a3, a4, exF, newMessage);
	}

	default LQuadPredicate<T1, T2, T3, T4> trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2, a3, a4) -> test(a1, a2, a3, a4, exF, newMessage, param1);
	}

	default LQuadPredicate<T1, T2, T3, T4> trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2, a3, a4) -> test(a1, a2, a3, a4, exF, newMessage, param1, param1);
	}

	default LQuadPredicate<T1, T2, T3, T4> trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2, a3, a4) -> test(a1, a2, a3, a4, exF, newMessage, param1, param2, param3);
	}

	default boolean test(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.testX(a1, a2, a3, a4);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LQuadPredicate<T1, T2, T3, T4> trying(@Nonnull ExWF<RuntimeException> exF) {
		return (a1, a2, a3, a4) -> test(a1, a2, a3, a4, exF);
	}

	default boolean testThen(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LPredicate<Throwable> handler) {
		try {
			return this.testX(a1, a2, a3, a4);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.test(e);
		}
	}

	default LQuadPredicate<T1, T2, T3, T4> tryingThen(@Nonnull LPredicate<Throwable> handler) {
		return (a1, a2, a3, a4) -> testThen(a1, a2, a3, a4, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingTest(T1 a1, T2 a2, T3 a3, T4 a4) {
		try {
			return this.testX(a1, a2, a3, a4);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default boolean shovingTest(T1 a1, T2 a2, T3 a3, T4 a4) {
		try {
			return this.testX(a1, a2, a3, a4);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T1, T2, T3, T4> boolean handlingTest(T1 a1, T2 a2, T3 a3, T4 a4, LQuadPredicate<T1, T2, T3, T4> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingTest(a1, a2, a3, a4, handling);
	}

	static <T1, T2, T3, T4> boolean tryTest(T1 a1, T2 a2, T3 a3, T4 a4, LQuadPredicate<T1, T2, T3, T4> func) {
		Null.nonNullArg(func, "func");
		return func.nestingTest(a1, a2, a3, a4);
	}

	static <T1, T2, T3, T4> boolean tryTest(T1 a1, T2 a2, T3 a3, T4 a4, LQuadPredicate<T1, T2, T3, T4> func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, a4, exF, newMessage);
	}

	static <T1, T2, T3, T4> boolean tryTest(T1 a1, T2 a2, T3 a3, T4 a4, LQuadPredicate<T1, T2, T3, T4> func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, a4, exF, newMessage, param1);
	}

	static <T1, T2, T3, T4> boolean tryTest(T1 a1, T2 a2, T3 a3, T4 a4, LQuadPredicate<T1, T2, T3, T4> func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, a4, exF, newMessage, param1, param2);
	}

	static <T1, T2, T3, T4> boolean tryTest(T1 a1, T2 a2, T3 a3, T4 a4, LQuadPredicate<T1, T2, T3, T4> func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, a4, exF, newMessage, param1, param2, param3);
	}

	static <T1, T2, T3, T4> boolean tryTest(T1 a1, T2 a2, T3 a3, T4 a4, LQuadPredicate<T1, T2, T3, T4> func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, a4, exF);
	}

	static <T1, T2, T3, T4> boolean tryTestThen(T1 a1, T2 a2, T3 a3, T4 a4, LQuadPredicate<T1, T2, T3, T4> func, @Nonnull LPredicate<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.testThen(a1, a2, a3, a4, handler);
	}

	default boolean failSafeTest(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadPredicate<T1, T2, T3, T4> failSafe) {
		try {
			return test(a1, a2, a3, a4);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.test(a1, a2, a3, a4);
		}
	}

	static <T1, T2, T3, T4> boolean failSafeTest(T1 a1, T2 a2, T3 a3, T4 a4, LQuadPredicate<T1, T2, T3, T4> func, @Nonnull LQuadPredicate<T1, T2, T3, T4> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.test(a1, a2, a3, a4);
		} else {
			return func.failSafeTest(a1, a2, a3, a4, failSafe);
		}
	}

	static <T1, T2, T3, T4> LQuadPredicate<T1, T2, T3, T4> failSafe(LQuadPredicate<T1, T2, T3, T4> func, @Nonnull LQuadPredicate<T1, T2, T3, T4> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3, a4) -> failSafeTest(a1, a2, a3, a4, func, failSafe);
	}

	default boolean doIf(T1 a1, T2 a2, T3 a3, T4 a4, LAction action) {
		Null.nonNullArg(action, "action");
		if (test(a1, a2, a3, a4)) {
			action.execute();
			return true;
		} else {
			return false;
		}
	}

	static <T1, T2, T3, T4> boolean doIf(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadPredicate<T1, T2, T3, T4> predicate, @Nonnull LAction action) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.doIf(a1, a2, a3, a4, action);
	}

	static <T1, T2, T3, T4> boolean doIf(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadPredicate<T1, T2, T3, T4> predicate, @Nonnull LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.doIf(a1, a2, a3, a4, consumer);
	}

	default boolean doIf(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
		Null.nonNullArg(consumer, "consumer");
		if (test(a1, a2, a3, a4)) {
			consumer.accept(a1, a2, a3, a4);
			return true;
		} else {
			return false;
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullTest(T1 a1, T2 a2, T3 a3, T4 a4) {
		return test(a1, a2, a3, a4);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(T1 a1, T2 a2, T3 a3, T4 a4) {
		return test(a1, a2, a3, a4);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LQuadPredicate.DESCRIPTION;
	}

	default <V> boolean doIf(V a1, T1 a2, T2 a3, T3 a4, T4 a5, @Nonnull LQuintConsumer<V, ? super T1, ? super T2, ? super T3, ? super T4> consumer) {
		Null.nonNullArg(consumer, "consumer");
		if (test(a2, a3, a4, a5)) {
			consumer.accept(a1, a2, a3, a4, a5);
			return true;
		} else {
			return false;
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, T3, T4> void fromTo(int min_i, int max_i, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadPredicate<T1, T2, T3, T4> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.test(a1, a2, a3, a4);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.test(a1, a2, a3, a4);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, T3, T4> void fromTill(int min_i, int max_i, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadPredicate<T1, T2, T3, T4> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.test(a1, a2, a3, a4);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.test(a1, a2, a3, a4);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, T3, T4> void times(int max_i, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadPredicate<T1, T2, T3, T4> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, a3, a4, func);
	}

	/** Extract and apply function. */
	public static <M, K, V, T2, T3, T4> boolean from(@Nonnull M container, LBiFunction<M, K, V> extractor, K key, T2 a2, T3 a3, T4 a4, @Nonnull LQuadPredicate<V, T2, T3, T4> function) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.test(value, a2, a3, a4);
		}

		return false;
	}

	default LTriPredicate<T2, T3, T4> lShrink(@Nonnull LTriFunction<T2, T3, T4, T1> left) {
		Null.nonNullArg(left, "left");
		return (a2, a3, a4) -> test(left.apply(a2, a3, a4), a2, a3, a4);
	}

	default LTriPredicate<T2, T3, T4> lShrink_(T1 a1) {
		return (a2, a3, a4) -> test(a1, a2, a3, a4);
	}

	public static <T2, T3, T4, T1> LTriPredicate<T2, T3, T4> lShrunken(@Nonnull LTriFunction<T2, T3, T4, T1> left, @Nonnull LQuadPredicate<T1, T2, T3, T4> func) {
		Null.nonNullArg(left, "left");
		Null.nonNullArg(func, "func");
		return func.lShrink(left);
	}

	public static <T2, T3, T4, T1> LTriPredicate<T2, T3, T4> lShrunken_(T1 a1, @Nonnull LQuadPredicate<T1, T2, T3, T4> func) {
		Null.nonNullArg(func, "func");
		return func.lShrink_(a1);
	}

	default LTriPredicate<T1, T2, T3> rShrink(@Nonnull LTriFunction<T1, T2, T3, T4> right) {
		Null.nonNullArg(right, "right");
		return (a1, a2, a3) -> test(a1, a2, a3, right.apply(a1, a2, a3));
	}

	default LTriPredicate<T1, T2, T3> rShrink_(T4 a4) {
		return (a1, a2, a3) -> test(a1, a2, a3, a4);
	}

	public static <T1, T2, T3, T4> LTriPredicate<T1, T2, T3> rShrunken(@Nonnull LTriFunction<T1, T2, T3, T4> right, @Nonnull LQuadPredicate<T1, T2, T3, T4> func) {
		Null.nonNullArg(right, "right");
		Null.nonNullArg(func, "func");
		return func.rShrink(right);
	}

	public static <T1, T2, T3, T4> LTriPredicate<T1, T2, T3> rShrunken_(T4 a4, @Nonnull LQuadPredicate<T1, T2, T3, T4> func) {
		Null.nonNullArg(func, "func");
		return func.rShrink_(a4);
	}

	/**  */
	public static <T1, T2, T3, T4> LQuadPredicate<T1, T2, T3, T4> uncurry(@Nonnull LFunction<T1, LFunction<T2, LFunction<T3, LPredicate<T4>>>> func) {
		Null.nonNullArg(func, "func");
		return (T1 a1, T2 a2, T3 a3, T4 a4) -> func.apply(a1).apply(a2).apply(a3).test(a4);
	}

	/** Cast that removes generics. */
	default LQuadPredicate untyped() {
		return this;
	}

	/** Cast that replace generics. */
	default <V2, V3, V4, V5> LQuadPredicate<V2, V3, V4, V5> cast() {
		return untyped();
	}

	/** Cast that replace generics. */
	public static <V2, V3, V4, V5> LQuadPredicate<V2, V3, V4, V5> cast(LQuadPredicate<?, ?, ?, ?> function) {
		return (LQuadPredicate) function;
	}

	/** Change function to consumer that ignores output. */
	default LQuadConsumer<T1, T2, T3, T4> toConsumer() {
		return this::test;
	}

	/** Calls domain consumer before main function. */
	default LQuadPredicate<T1, T2, T3, T4> beforeDo(@Nonnull LQuadConsumer<T1, T2, T3, T4> before) {
		Null.nonNullArg(before, "before");
		return (T1 a1, T2 a2, T3 a3, T4 a4) -> {
			before.accept(a1, a2, a3, a4);
			return test(a1, a2, a3, a4);
		};
	}

	/** Calls codomain consumer after main function. */
	default LQuadPredicate<T1, T2, T3, T4> afterDo(@Nonnull LBoolConsumer after) {
		Null.nonNullArg(after, "after");
		return (T1 a1, T2 a2, T3 a3, T4 a4) -> {
			final boolean retval = test(a1, a2, a3, a4);
			after.accept(retval);
			return retval;
		};
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIf(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadPredicate<? super T1, ? super T2, ? super T3, ? super T4> pred, @Nonnull ExMF<X> factory,
			@Nonnull LQuadFunction<? super T1, ? super T2, ? super T3, ? super T4, ? extends String> msgFunc) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (pred.test(a1, a2, a3, a4)) {
			throw Handling.create(factory, msgFunc.apply(a1, a2, a3, a4));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIf(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadPredicate<? super T1, ? super T2, ? super T3, ? super T4> pred, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (pred.test(a1, a2, a3, a4)) {
			throw Handling.create(factory, String.format(msg, a1, a2, a3, a4));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIf(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadPredicate<? super T1, ? super T2, ? super T3, ? super T4> pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1)
			throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2, a3, a4)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIf(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadPredicate<? super T1, ? super T2, ? super T3, ? super T4> pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1,
			@Nullable Object param2) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2, a3, a4)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIf(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadPredicate<? super T1, ? super T2, ? super T3, ? super T4> pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1,
			@Nullable Object param2, @Nullable Object param3) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2, a3, a4)) {
			throw Handling.create(factory, String.format(message, param1, param2, param3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIfNot(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadPredicate<? super T1, ? super T2, ? super T3, ? super T4> pred, @Nonnull ExMF<X> factory,
			@Nonnull LQuadFunction<? super T1, ? super T2, ? super T3, ? super T4, ? extends String> msgFunc) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!pred.test(a1, a2, a3, a4)) {
			throw Handling.create(factory, msgFunc.apply(a1, a2, a3, a4));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIfNot(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadPredicate<? super T1, ? super T2, ? super T3, ? super T4> pred, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (!pred.test(a1, a2, a3, a4)) {
			throw Handling.create(factory, String.format(msg, a1, a2, a3, a4));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIfNot(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadPredicate<? super T1, ? super T2, ? super T3, ? super T4> pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1)
			throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2, a3, a4)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIfNot(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadPredicate<? super T1, ? super T2, ? super T3, ? super T4> pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1,
			@Nullable Object param2) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2, a3, a4)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIfNot(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadPredicate<? super T1, ? super T2, ? super T3, ? super T4> pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1,
			@Nullable Object param2, @Nullable Object param3) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2, a3, a4)) {
			throw Handling.create(factory, String.format(message, param1, param2, param3));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIf(T1 a1, @Nonnull LQuadPredicate<? super T1, ? super T2, ? super T3, ? super T4> pred, T2 a2, T3 a3, T4 a4, @Nonnull ExMF<X> factory,
			@Nonnull LQuadFunction<? super T1, ? super T2, ? super T3, ? super T4, ? extends String> msgFunc) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (pred.test(a1, a2, a3, a4)) {
			throw Handling.create(factory, msgFunc.apply(a1, a2, a3, a4));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIf(T1 a1, @Nonnull LQuadPredicate<? super T1, ? super T2, ? super T3, ? super T4> pred, T2 a2, T3 a3, T4 a4, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (pred.test(a1, a2, a3, a4)) {
			throw Handling.create(factory, String.format(msg, a1, a2, a3, a4));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIf(T1 a1, @Nonnull LQuadPredicate<? super T1, ? super T2, ? super T3, ? super T4> pred, T2 a2, T3 a3, T4 a4, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1)
			throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2, a3, a4)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIf(T1 a1, @Nonnull LQuadPredicate<? super T1, ? super T2, ? super T3, ? super T4> pred, T2 a2, T3 a3, T4 a4, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1,
			@Nullable Object param2) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2, a3, a4)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIf(T1 a1, @Nonnull LQuadPredicate<? super T1, ? super T2, ? super T3, ? super T4> pred, T2 a2, T3 a3, T4 a4, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1,
			@Nullable Object param2, @Nullable Object param3) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2, a3, a4)) {
			throw Handling.create(factory, String.format(message, param1, param2, param3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIfNot(T1 a1, @Nonnull LQuadPredicate<? super T1, ? super T2, ? super T3, ? super T4> pred, T2 a2, T3 a3, T4 a4, @Nonnull ExMF<X> factory,
			@Nonnull LQuadFunction<? super T1, ? super T2, ? super T3, ? super T4, ? extends String> msgFunc) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!pred.test(a1, a2, a3, a4)) {
			throw Handling.create(factory, msgFunc.apply(a1, a2, a3, a4));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIfNot(T1 a1, @Nonnull LQuadPredicate<? super T1, ? super T2, ? super T3, ? super T4> pred, T2 a2, T3 a3, T4 a4, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (!pred.test(a1, a2, a3, a4)) {
			throw Handling.create(factory, String.format(msg, a1, a2, a3, a4));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIfNot(T1 a1, @Nonnull LQuadPredicate<? super T1, ? super T2, ? super T3, ? super T4> pred, T2 a2, T3 a3, T4 a4, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1)
			throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2, a3, a4)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIfNot(T1 a1, @Nonnull LQuadPredicate<? super T1, ? super T2, ? super T3, ? super T4> pred, T2 a2, T3 a3, T4 a4, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1,
			@Nullable Object param2) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2, a3, a4)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIfNot(T1 a1, @Nonnull LQuadPredicate<? super T1, ? super T2, ? super T3, ? super T4> pred, T2 a2, T3 a3, T4 a4, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1,
			@Nullable Object param2, @Nullable Object param3) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2, a3, a4)) {
			throw Handling.create(factory, String.format(message, param1, param2, param3));
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIfNotEx(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadFunction<? super T1, ? super T2, ? super T3, ? super T4, ? extends String> specialPredicate, @Nonnull ExMF<X> factory) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		var m = specialPredicate.apply(a1, a2, a3, a4);
		if (m != null) {
			throw Handling.create(factory, m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIfNotEx(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadFunction<? super T1, ? super T2, ? super T3, ? super T4, ? extends String> specialPredicate, @Nonnull ExMF<X> factory, @Nonnull String msg)
			throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		var m = specialPredicate.apply(a1, a2, a3, a4);
		if (m != null) {
			throw Handling.create(factory, String.format(msg, a1, a2, a3, a4) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIfNotEx(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadFunction<? super T1, ? super T2, ? super T3, ? super T4, ? extends String> specialPredicate, @Nonnull ExMF<X> factory,
			@Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		var m = specialPredicate.apply(a1, a2, a3, a4);
		if (m != null) {
			throw Handling.create(factory, String.format(message, param1) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIfNotEx(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadFunction<? super T1, ? super T2, ? super T3, ? super T4, ? extends String> specialPredicate, @Nonnull ExMF<X> factory,
			@Nonnull String message, @Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		var m = specialPredicate.apply(a1, a2, a3, a4);
		if (m != null) {
			throw Handling.create(factory, String.format(message, param1, param2) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIfNotEx(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadFunction<? super T1, ? super T2, ? super T3, ? super T4, ? extends String> specialPredicate, @Nonnull ExMF<X> factory,
			@Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		var m = specialPredicate.apply(a1, a2, a3, a4);
		if (m != null) {
			throw Handling.create(factory, String.format(message, param1, param2, param3) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIfNotEx(T1 a1, @Nonnull LQuadFunction<? super T1, ? super T2, ? super T3, ? super T4, ? extends String> specialPredicate, T2 a2, T3 a3, T4 a4, @Nonnull ExMF<X> factory) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		var m = specialPredicate.apply(a1, a2, a3, a4);
		if (m != null) {
			throw Handling.create(factory, m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIfNotEx(T1 a1, @Nonnull LQuadFunction<? super T1, ? super T2, ? super T3, ? super T4, ? extends String> specialPredicate, T2 a2, T3 a3, T4 a4, @Nonnull ExMF<X> factory, @Nonnull String msg)
			throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		var m = specialPredicate.apply(a1, a2, a3, a4);
		if (m != null) {
			throw Handling.create(factory, String.format(msg, a1, a2, a3, a4) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIfNotEx(T1 a1, @Nonnull LQuadFunction<? super T1, ? super T2, ? super T3, ? super T4, ? extends String> specialPredicate, T2 a2, T3 a3, T4 a4, @Nonnull ExMF<X> factory,
			@Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		var m = specialPredicate.apply(a1, a2, a3, a4);
		if (m != null) {
			throw Handling.create(factory, String.format(message, param1) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIfNotEx(T1 a1, @Nonnull LQuadFunction<? super T1, ? super T2, ? super T3, ? super T4, ? extends String> specialPredicate, T2 a2, T3 a3, T4 a4, @Nonnull ExMF<X> factory,
			@Nonnull String message, @Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		var m = specialPredicate.apply(a1, a2, a3, a4);
		if (m != null) {
			throw Handling.create(factory, String.format(message, param1, param2) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIfNotEx(T1 a1, @Nonnull LQuadFunction<? super T1, ? super T2, ? super T3, ? super T4, ? extends String> specialPredicate, T2 a2, T3 a3, T4 a4, @Nonnull ExMF<X> factory,
			@Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		var m = specialPredicate.apply(a1, a2, a3, a4);
		if (m != null) {
			throw Handling.create(factory, String.format(message, param1, param2, param3) + ' ' + m);
		}
		return a1;
	}

	/** Captures arguments but delays the evaluation. */
	default LBoolSupplier capture(T1 a1, T2 a2, T3 a3, T4 a4) {
		return () -> this.test(a1, a2, a3, a4);
	}

	/** Creates function that always returns the same value. */
	static <T1, T2, T3, T4> LQuadPredicate<T1, T2, T3, T4> constant(boolean r) {
		return (a1, a2, a3, a4) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T1, T2, T3, T4> LQuadPredicate<T1, T2, T3, T4> test1st(@Nonnull LPredicate<T1> func) {
		return (a1, a2, a3, a4) -> func.test(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T1, T2, T3, T4> LQuadPredicate<T1, T2, T3, T4> test2nd(@Nonnull LPredicate<T2> func) {
		return (a1, a2, a3, a4) -> func.test(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T1, T2, T3, T4> LQuadPredicate<T1, T2, T3, T4> test3rd(@Nonnull LPredicate<T3> func) {
		return (a1, a2, a3, a4) -> func.test(a3);
	}

	/** Captures single parameter function into this interface where only 4th parameter will be used. */
	@Nonnull
	static <T1, T2, T3, T4> LQuadPredicate<T1, T2, T3, T4> test4th(@Nonnull LPredicate<T4> func) {
		return (a1, a2, a3, a4) -> func.test(a4);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, T3, T4> LQuadPredicate<T1, T2, T3, T4> quadPred(final @Nonnull LQuadPredicate<T1, T2, T3, T4> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** A completely inconvenient method in case lambda expression and generic arguments are ambiguous for the compiler. */
	@Nonnull
	static <T1, T2, T3, T4> LQuadPredicate<T1, T2, T3, T4> quadPred(@Nullable Class<T1> c1, @Nullable Class<T2> c2, @Nullable Class<T3> c3, @Nullable Class<T4> c4, final @Nonnull LQuadPredicate<T1, T2, T3, T4> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	final class S<T1, T2, T3, T4> implements LQuadPredicate<T1, T2, T3, T4> {
		private LQuadPredicate<T1, T2, T3, T4> target = null;
		@Override
		public boolean testX(T1 a1, T2 a2, T3 a3, T4 a4) throws Throwable {
			return target.testX(a1, a2, a3, a4);
		}
	}

	@Nonnull
	static <T1, T2, T3, T4> LQuadPredicate<T1, T2, T3, T4> recursive(final @Nonnull LFunction<LQuadPredicate<T1, T2, T3, T4>, LQuadPredicate<T1, T2, T3, T4>> selfLambda) {
		final S<T1, T2, T3, T4> single = new S();
		LQuadPredicate<T1, T2, T3, T4> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	public static <T1, T2, T3, T4> M<T1, T2, T3, T4> mementoOf(T1 a1, T2 a2, T3 a3, T4 a4, LQuadPredicate<T1, T2, T3, T4> function) {
		var initialValue = function.test(a1, a2, a3, a4);
		return initializedMementoOf(initialValue, function);
	}

	public static <T1, T2, T3, T4> M<T1, T2, T3, T4> initializedMementoOf(boolean initialValue, LQuadPredicate<T1, T2, T3, T4> function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static <T1, T2, T3, T4> M<T1, T2, T3, T4> deltaOf(T1 a1, T2 a2, T3 a3, T4 a4, LQuadPredicate<T1, T2, T3, T4> function, LLogicalBinaryOperator deltaFunction) {
		var initialValue = function.test(a1, a2, a3, a4);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static <T1, T2, T3, T4> M<T1, T2, T3, T4> deltaOf(T1 a1, T2 a2, T3 a3, T4 a4, LQuadPredicate<T1, T2, T3, T4> function) {
		var initialValue = function.test(a1, a2, a3, a4);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> x1 != x2);
	}

	public static <T1, T2, T3, T4> M<T1, T2, T3, T4> initializedDeltaOf(boolean initialValue, LQuadPredicate<T1, T2, T3, T4> function, LLogicalBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.apply(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.apply(x1, x2));
	}

	public static <T1, T2, T3, T4> M<T1, T2, T3, T4> memento(boolean initialBaseValue, boolean initialValue, LQuadPredicate<T1, T2, T3, T4> baseFunction, LLogicalTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LQuadPredicate.M)
	 */
	final class M<T1, T2, T3, T4> implements LQuadPredicate<T1, T2, T3, T4> {

		private final LQuadPredicate<T1, T2, T3, T4> baseFunction;
		private boolean lastBaseValue;
		private boolean lastValue;
		private final LLogicalTernaryOperator mementoFunction;

		private M(boolean lastBaseValue, boolean lastValue, LQuadPredicate<T1, T2, T3, T4> baseFunction, LLogicalTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public boolean testX(T1 a1, T2 a2, T3 a3, T4 a4) throws Throwable {
			boolean x1 = lastBaseValue;
			boolean x2 = lastBaseValue = baseFunction.testX(a1, a2, a3, a4);

			return lastValue = mementoFunction.apply(lastValue, x1, x2);
		}

		public boolean lastValue() {
			return lastValue;
		};

		public boolean lastBaseValue() {
			return lastBaseValue;
		};
	}

	@Nonnull
	static <T1, T2, T3, T4> LQuadPredicate<T1, T2, T3, T4> quadPredThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3, a4) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T1, T2, T3, T4> LQuadPredicate<T1, T2, T3, T4> quadPredThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3, a4) -> {
			throw exF.produce(message);
		};
	}

	static <T1, T2, T3, T4> boolean call(T1 a1, T2 a2, T3 a3, T4 a4, final @Nonnull LQuadPredicate<T1, T2, T3, T4> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.test(a1, a2, a3, a4);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as alwaysFalse). */
	@Nonnull
	static <T1, T2, T3, T4> LQuadPredicate<T1, T2, T3, T4> safe() {
		return LQuadPredicate::alwaysFalse;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T1, T2, T3, T4> LSupplier<LQuadPredicate<T1, T2, T3, T4>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T1, T2, T3, T4> LQuadPredicate<T1, T2, T3, T4> safe(final @Nullable LQuadPredicate<T1, T2, T3, T4> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T1, T2, T3, T4> LSupplier<LQuadPredicate<T1, T2, T3, T4>> safeSupplier(final @Nullable LSupplier<LQuadPredicate<T1, T2, T3, T4>> supplier) {
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
	default LQuadPredicate<T1, T2, T3, T4> negate() {
		return (a1, a2, a3, a4) -> !test(a1, a2, a3, a4);
	}

	@Nonnull
	static <T1, T2, T3, T4> LQuadPredicate<T1, T2, T3, T4> not(@Nonnull LQuadPredicate<T1, T2, T3, T4> pred) {
		Null.nonNullArg(pred, "pred");
		return pred.negate();
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LQuadPredicate<T1, T2, T3, T4> and(@Nonnull LQuadPredicate<? super T1, ? super T2, ? super T3, ? super T4> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3, a4) -> test(a1, a2, a3, a4) && other.test(a1, a2, a3, a4);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LQuadPredicate<T1, T2, T3, T4> or(@Nonnull LQuadPredicate<? super T1, ? super T2, ? super T3, ? super T4> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3, a4) -> test(a1, a2, a3, a4) || other.test(a1, a2, a3, a4);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LQuadPredicate<T1, T2, T3, T4> xor(@Nonnull LQuadPredicate<? super T1, ? super T2, ? super T3, ? super T4> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3, a4) -> test(a1, a2, a3, a4) ^ other.test(a1, a2, a3, a4);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static <T1, T2, T3, T4> LQuadPredicate<T1, T2, T3, T4> isEqual(T1 v1, T2 v2, T3 v3, T4 v4) {
		return (a1, a2, a3, a4) -> (a1 == null ? v1 == null : a1.equals(v1)) && (a2 == null ? v2 == null : a2.equals(v2)) && (a3 == null ? v3 == null : a3.equals(v3)) && (a4 == null ? v4 == null : a4.equals(v4));
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3, V4> LQuadPredicate<V1, V2, V3, V4> compose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LFunction<? super V3, ? extends T3> before3,
			@Nonnull final LFunction<? super V4, ? extends T4> before4) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		Null.nonNullArg(before4, "before4");
		return (v1, v2, v3, v4) -> this.test(before1.apply(v1), before2.apply(v2), before3.apply(v3), before4.apply(v4));
	}

	public static <V1, V2, V3, V4, T1, T2, T3, T4> LQuadPredicate<V1, V2, V3, V4> composed(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2,
			@Nonnull final LFunction<? super V3, ? extends T3> before3, @Nonnull final LFunction<? super V4, ? extends T4> before4, LQuadPredicate<T1, T2, T3, T4> after) {
		return after.compose(before1, before2, before3, before4);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LQuadFunction<T1, T2, T3, T4, V> boolToQuadFunc(@Nonnull LBoolFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3, a4) -> after.apply(this.test(a1, a2, a3, a4));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	// >>> LQuadPredicate<T1,T2,T3,T4>

	/** Returns TRUE. */
	public static <T1, T2, T3, T4> boolean alwaysTrue(T1 a1, T2 a2, T3 a3, T4 a4) {
		return true;
	}

	/** Returns FALSE. */
	public static <T1, T2, T3, T4> boolean alwaysFalse(T1 a1, T2 a2, T3 a3, T4 a4) {
		return false;
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, C3, C4> void filterForEach(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4,
			LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		int i = 0;
		for (; i < size; i++) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = oiFunc4.apply(source4, i);
			doIf(a1, a2, a3, a4, consumer);
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, C3, C4> void filterIterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4,
			LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = oiFunc4.apply(source4, i);
			doIf(a1, a2, a3, a4, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2, C3, C4> void filterIterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4,
			LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = oiFunc4.apply(source4, i);
			doIf(a1, a2, a3, a4, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, I2, C3, C4> void filterIterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4,
			LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		int size = ia3.size(source3);
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && i < size) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = oiFunc4.apply(source4, i);
			doIf(a1, a2, a3, a4, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, C3, I3, C4> void filterIterate(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4,
			LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		int i = 0;
		while (i < size && testFunc3.test(iterator3)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = oiFunc4.apply(source4, i);
			doIf(a1, a2, a3, a4, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, C3, I3, C4> void filterIterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4,
			LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size && testFunc3.test(iterator3)) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = oiFunc4.apply(source4, i);
			doIf(a1, a2, a3, a4, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2, C3, I3, C4> void filterIterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4,
			LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		size = Integer.min(size, ia4.size(source4));
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		int i = 0;
		while (i < size && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = oiFunc4.apply(source4, i);
			doIf(a1, a2, a3, a4, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, I2, C3, I3, C4> void filterIterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, IndexedRead<C4, a<T4>> ia4, C4 source4,
			LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		int size = ia4.size(source4);
		LOiFunction<Object, T4> oiFunc4 = (LOiFunction) ia4.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && testFunc3.test(iterator3) && i < size) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = oiFunc4.apply(source4, i);
			doIf(a1, a2, a3, a4, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, C3, C4, I4> void filterIterate(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4, C4 source4,
			LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		int i = 0;
		while (i < size && testFunc4.test(iterator4)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = nextFunc4.apply(iterator4);
			doIf(a1, a2, a3, a4, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, C3, C4, I4> void filterIterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4, C4 source4,
			LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size && testFunc4.test(iterator4)) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = nextFunc4.apply(iterator4);
			doIf(a1, a2, a3, a4, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2, C3, C4, I4> void filterIterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4, C4 source4,
			LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2) && testFunc4.test(iterator4)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = nextFunc4.apply(iterator4);
			doIf(a1, a2, a3, a4, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, I2, C3, C4, I4> void filterIterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, a<T3>> ia3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4, C4 source4,
			LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		int size = ia3.size(source3);
		LOiFunction<Object, T3> oiFunc3 = (LOiFunction) ia3.getter();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && i < size && testFunc4.test(iterator4)) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = oiFunc3.apply(source3, i);
			T4 a4 = nextFunc4.apply(iterator4);
			doIf(a1, a2, a3, a4, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, C3, I3, C4, I4> void filterIterate(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4, C4 source4,
			LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		int i = 0;
		while (i < size && testFunc3.test(iterator3) && testFunc4.test(iterator4)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = nextFunc4.apply(iterator4);
			doIf(a1, a2, a3, a4, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, C3, I3, C4, I4> void filterIterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4, C4 source4,
			LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size && testFunc3.test(iterator3) && testFunc4.test(iterator4)) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = oiFunc2.apply(source2, i);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = nextFunc4.apply(iterator4);
			doIf(a1, a2, a3, a4, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2, C3, I3, C4, I4> void filterIterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4, C4 source4,
			LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2) && testFunc3.test(iterator3) && testFunc4.test(iterator4)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = nextFunc4.apply(iterator4);
			doIf(a1, a2, a3, a4, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C1, I1, C2, I2, C3, I3, C4, I4> void filterIterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, a<T3>> sa3, C3 source3, SequentialRead<C4, I4, a<T4>> sa4, C4 source4,
			LQuadConsumer<? super T1, ? super T2, ? super T3, ? super T4> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T3> nextFunc3 = (LFunction) sa3.supplier();
		Object iterator4 = ((LFunction) sa4.adapter()).apply(source4);
		LPredicate<Object> testFunc4 = (LPredicate) sa4.tester();
		LFunction<Object, T4> nextFunc4 = (LFunction) sa4.supplier();
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && testFunc3.test(iterator3) && testFunc4.test(iterator4)) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = nextFunc2.apply(iterator2);
			T3 a3 = nextFunc3.apply(iterator3);
			T4 a4 = nextFunc4.apply(iterator4);
			doIf(a1, a2, a3, a4, consumer);
		}
	}

}
