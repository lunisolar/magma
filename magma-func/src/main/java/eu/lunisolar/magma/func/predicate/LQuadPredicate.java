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

package eu.lunisolar.magma.func.predicate;

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

	default boolean test(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.testX(a1, a2, a3, a4);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default boolean test(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.testX(a1, a2, a3, a4);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default boolean test(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.testX(a1, a2, a3, a4);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default boolean test(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.testX(a1, a2, a3, a4);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LQuadPredicate<T1, T2, T3, T4> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2, a3, a4) -> test(a1, a2, a3, a4, factory, newMessage);
	}

	default LQuadPredicate<T1, T2, T3, T4> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2, a3, a4) -> test(a1, a2, a3, a4, factory, newMessage, param1);
	}

	default LQuadPredicate<T1, T2, T3, T4> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2, a3, a4) -> test(a1, a2, a3, a4, factory, newMessage, param1, param1);
	}

	default LQuadPredicate<T1, T2, T3, T4> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2, a3, a4) -> test(a1, a2, a3, a4, factory, newMessage, param1, param2, param3);
	}

	default boolean test(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.testX(a1, a2, a3, a4);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LQuadPredicate<T1, T2, T3, T4> trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2, a3, a4) -> test(a1, a2, a3, a4, factory);
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

	static <T1, T2, T3, T4> boolean shovingTest(T1 a1, T2 a2, T3 a3, T4 a4, LQuadPredicate<T1, T2, T3, T4> func) {
		Null.nonNullArg(func, "func");
		return func.shovingTest(a1, a2, a3, a4);
	}

	static <T1, T2, T3, T4> boolean handlingTest(T1 a1, T2 a2, T3 a3, T4 a4, LQuadPredicate<T1, T2, T3, T4> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingTest(a1, a2, a3, a4, handling);
	}

	static <T1, T2, T3, T4> boolean tryTest(T1 a1, T2 a2, T3 a3, T4 a4, LQuadPredicate<T1, T2, T3, T4> func) {
		Null.nonNullArg(func, "func");
		return func.nestingTest(a1, a2, a3, a4);
	}

	static <T1, T2, T3, T4> boolean tryTest(T1 a1, T2 a2, T3 a3, T4 a4, LQuadPredicate<T1, T2, T3, T4> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, a4, factory, newMessage);
	}

	static <T1, T2, T3, T4> boolean tryTest(T1 a1, T2 a2, T3 a3, T4 a4, LQuadPredicate<T1, T2, T3, T4> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, a4, factory, newMessage, param1);
	}

	static <T1, T2, T3, T4> boolean tryTest(T1 a1, T2 a2, T3 a3, T4 a4, LQuadPredicate<T1, T2, T3, T4> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, a4, factory, newMessage, param1, param2);
	}

	static <T1, T2, T3, T4> boolean tryTest(T1 a1, T2 a2, T3 a3, T4 a4, LQuadPredicate<T1, T2, T3, T4> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, a4, factory, newMessage, param1, param2, param3);
	}

	static <T1, T2, T3, T4> boolean tryTest(T1 a1, T2 a2, T3 a3, T4 a4, LQuadPredicate<T1, T2, T3, T4> func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, a4, factory);
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

	/**  */
	public static <T1, T2, T3, T4> LQuadPredicate<T1, T2, T3, T4> uncurry(@Nonnull LFunction<T1, LFunction<T2, LFunction<T3, LPredicate<T4>>>> func) {
		Null.nonNullArg(func, "func");
		return (T1 a1, T2 a2, T3 a3, T4 a4) -> func.apply(a1).apply(a2).apply(a3).test(a4);
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
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIf(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadPredicate<? super T1, ? super T2, ? super T3, ? super T4> pred, @Nonnull ExF<X> noArgFactory) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(noArgFactory, "noArgFactory");
		if (pred.test(a1, a2, a3, a4)) {
			throw Handling.create(noArgFactory);
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIfNot(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadPredicate<? super T1, ? super T2, ? super T3, ? super T4> pred, @Nonnull ExF<X> noArgFactory) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(noArgFactory, "noArgFactory");
		if (!pred.test(a1, a2, a3, a4)) {
			throw Handling.create(noArgFactory);
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

	/** Throws new exception if condition is met. */
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIf(T1 a1, @Nonnull LQuadPredicate<? super T1, ? super T2, ? super T3, ? super T4> pred, T2 a2, T3 a3, T4 a4, @Nonnull ExF<X> noArgFactory) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(noArgFactory, "noArgFactory");
		if (pred.test(a1, a2, a3, a4)) {
			throw Handling.create(noArgFactory);
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIfNot(T1 a1, @Nonnull LQuadPredicate<? super T1, ? super T2, ? super T3, ? super T4> pred, T2 a2, T3 a3, T4 a4, @Nonnull ExF<X> noArgFactory) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(noArgFactory, "noArgFactory");
		if (!pred.test(a1, a2, a3, a4)) {
			throw Handling.create(noArgFactory);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIfNot$(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadFunction<? super T1, ? super T2, ? super T3, ? super T4, ? extends String> specialPredicate, @Nonnull ExMF<X> factory) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		var m = specialPredicate.apply(a1, a2, a3, a4);
		if (m != null) {
			throw Handling.create(factory, m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIfNot$(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadFunction<? super T1, ? super T2, ? super T3, ? super T4, ? extends String> specialPredicate, @Nonnull ExMF<X> factory, @Nonnull String msg)
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
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIfNot$(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadFunction<? super T1, ? super T2, ? super T3, ? super T4, ? extends String> specialPredicate, @Nonnull ExMF<X> factory,
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
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIfNot$(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadFunction<? super T1, ? super T2, ? super T3, ? super T4, ? extends String> specialPredicate, @Nonnull ExMF<X> factory,
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
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIfNot$(T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadFunction<? super T1, ? super T2, ? super T3, ? super T4, ? extends String> specialPredicate, @Nonnull ExMF<X> factory,
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
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIfNot$(T1 a1, @Nonnull LQuadFunction<? super T1, ? super T2, ? super T3, ? super T4, ? extends String> specialPredicate, T2 a2, T3 a3, T4 a4, @Nonnull ExMF<X> factory) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		var m = specialPredicate.apply(a1, a2, a3, a4);
		if (m != null) {
			throw Handling.create(factory, m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIfNot$(T1 a1, @Nonnull LQuadFunction<? super T1, ? super T2, ? super T3, ? super T4, ? extends String> specialPredicate, T2 a2, T3 a3, T4 a4, @Nonnull ExMF<X> factory, @Nonnull String msg)
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
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIfNot$(T1 a1, @Nonnull LQuadFunction<? super T1, ? super T2, ? super T3, ? super T4, ? extends String> specialPredicate, T2 a2, T3 a3, T4 a4, @Nonnull ExMF<X> factory,
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
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIfNot$(T1 a1, @Nonnull LQuadFunction<? super T1, ? super T2, ? super T3, ? super T4, ? extends String> specialPredicate, T2 a2, T3 a3, T4 a4, @Nonnull ExMF<X> factory,
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
	public static <T1, T2, T3, T4, X extends Throwable> T1 throwIfNot$(T1 a1, @Nonnull LQuadFunction<? super T1, ? super T2, ? super T3, ? super T4, ? extends String> specialPredicate, T2 a2, T3 a3, T4 a4, @Nonnull ExMF<X> factory,
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

	// <editor-fold desc="CallContext">

	static <T1, T2, T3, T4> boolean nestingTest(@Nullable CallContext c1, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadPredicate<T1, T2, T3, T4> function) {
		Null.nonNullArg(function, "function");
		try {
			return testX(c1, a1, a2, a3, a4, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static <T1, T2, T3, T4> boolean shovingTest(@Nullable CallContext c1, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadPredicate<T1, T2, T3, T4> function) {
		Null.nonNullArg(function, "function");
		try {
			return testX(c1, a1, a2, a3, a4, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static <T1, T2, T3, T4> boolean testX(@Nullable CallContext c1, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadPredicate<T1, T2, T3, T4> function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContext.tryInit(last, c1);

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;
		Object retval = null;
		if (primary == null) {
			try {
				retval = function.shovingTest(a1, a2, a3, a4);
			} catch (Throwable e) {
				primary = e;
			}
		}

		primary = CallContext.tryFinish(primary, c1, s1);

		if (primary != null) {
			throw Handling.throwIt(primary);
		}
		return (boolean) retval;
	}

	static <T1, T2, T3, T4> CompletableFuture<Boolean> asyncTest(@Nonnull AsyncCallContext async, @Nullable CallContext c1, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadPredicate<T1, T2, T3, T4> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<Boolean> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					var v = LQuadPredicate.testX(c1, a1, a2, a3, a4, function);
					future.complete(v);
				} catch (Throwable e) {
					Handling.handleErrors(e);
					future.completeExceptionally(e);
				}
			});
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
		return future;
	}

	static <T1, T2, T3, T4> boolean nestingTest(@Nullable CallContext c1, @Nullable CallContext c2, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadPredicate<T1, T2, T3, T4> function) {
		Null.nonNullArg(function, "function");
		try {
			return testX(c1, c2, a1, a2, a3, a4, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static <T1, T2, T3, T4> boolean shovingTest(@Nullable CallContext c1, @Nullable CallContext c2, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadPredicate<T1, T2, T3, T4> function) {
		Null.nonNullArg(function, "function");
		try {
			return testX(c1, c2, a1, a2, a3, a4, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static <T1, T2, T3, T4> boolean testX(@Nullable CallContext c1, @Nullable CallContext c2, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadPredicate<T1, T2, T3, T4> function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContext.tryInit(last, c1);
		final Object s2 = last = CallContext.tryInit(last, c2);

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;
		Object retval = null;
		if (primary == null) {
			try {
				retval = function.shovingTest(a1, a2, a3, a4);
			} catch (Throwable e) {
				primary = e;
			}
		}

		primary = CallContext.tryFinish(primary, c2, s2);
		primary = CallContext.tryFinish(primary, c1, s1);

		if (primary != null) {
			throw Handling.throwIt(primary);
		}
		return (boolean) retval;
	}

	static <T1, T2, T3, T4> CompletableFuture<Boolean> asyncTest(@Nonnull AsyncCallContext async, @Nullable CallContext c1, @Nullable CallContext c2, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadPredicate<T1, T2, T3, T4> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<Boolean> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					var v = LQuadPredicate.testX(c1, c2, a1, a2, a3, a4, function);
					future.complete(v);
				} catch (Throwable e) {
					Handling.handleErrors(e);
					future.completeExceptionally(e);
				}
			});
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
		return future;
	}

	static <T1, T2, T3, T4> boolean nestingTest(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadPredicate<T1, T2, T3, T4> function) {
		Null.nonNullArg(function, "function");
		try {
			return testX(c1, c2, c3, a1, a2, a3, a4, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static <T1, T2, T3, T4> boolean shovingTest(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadPredicate<T1, T2, T3, T4> function) {
		Null.nonNullArg(function, "function");
		try {
			return testX(c1, c2, c3, a1, a2, a3, a4, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static <T1, T2, T3, T4> boolean testX(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadPredicate<T1, T2, T3, T4> function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContext.tryInit(last, c1);
		final Object s2 = last = CallContext.tryInit(last, c2);
		final Object s3 = last = CallContext.tryInit(last, c3);

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;
		Object retval = null;
		if (primary == null) {
			try {
				retval = function.shovingTest(a1, a2, a3, a4);
			} catch (Throwable e) {
				primary = e;
			}
		}

		primary = CallContext.tryFinish(primary, c3, s3);
		primary = CallContext.tryFinish(primary, c2, s2);
		primary = CallContext.tryFinish(primary, c1, s1);

		if (primary != null) {
			throw Handling.throwIt(primary);
		}
		return (boolean) retval;
	}

	static <T1, T2, T3, T4> CompletableFuture<Boolean> asyncTest(@Nonnull AsyncCallContext async, @Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadPredicate<T1, T2, T3, T4> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<Boolean> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					var v = LQuadPredicate.testX(c1, c2, c3, a1, a2, a3, a4, function);
					future.complete(v);
				} catch (Throwable e) {
					Handling.handleErrors(e);
					future.completeExceptionally(e);
				}
			});
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
		return future;
	}

	static <T1, T2, T3, T4> boolean nestingTest(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadPredicate<T1, T2, T3, T4> function) {
		Null.nonNullArg(function, "function");
		try {
			return testX(c1, c2, c3, c4, a1, a2, a3, a4, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static <T1, T2, T3, T4> boolean shovingTest(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadPredicate<T1, T2, T3, T4> function) {
		Null.nonNullArg(function, "function");
		try {
			return testX(c1, c2, c3, c4, a1, a2, a3, a4, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static <T1, T2, T3, T4> boolean testX(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadPredicate<T1, T2, T3, T4> function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContext.tryInit(last, c1);
		final Object s2 = last = CallContext.tryInit(last, c2);
		final Object s3 = last = CallContext.tryInit(last, c3);
		final Object s4 = last = CallContext.tryInit(last, c4);

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;
		Object retval = null;
		if (primary == null) {
			try {
				retval = function.shovingTest(a1, a2, a3, a4);
			} catch (Throwable e) {
				primary = e;
			}
		}

		primary = CallContext.tryFinish(primary, c4, s4);
		primary = CallContext.tryFinish(primary, c3, s3);
		primary = CallContext.tryFinish(primary, c2, s2);
		primary = CallContext.tryFinish(primary, c1, s1);

		if (primary != null) {
			throw Handling.throwIt(primary);
		}
		return (boolean) retval;
	}

	static <T1, T2, T3, T4> CompletableFuture<Boolean> asyncTest(@Nonnull AsyncCallContext async, @Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, T1 a1, T2 a2, T3 a3, T4 a4,
			@Nonnull LQuadPredicate<T1, T2, T3, T4> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<Boolean> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					var v = LQuadPredicate.testX(c1, c2, c3, c4, a1, a2, a3, a4, function);
					future.complete(v);
				} catch (Throwable e) {
					Handling.handleErrors(e);
					future.completeExceptionally(e);
				}
			});
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
		return future;
	}

	static <T1, T2, T3, T4> CompletableFuture<Boolean> asyncTest(@Nonnull AsyncCallContext async, T1 a1, T2 a2, T3 a3, T4 a4, @Nonnull LQuadPredicate<T1, T2, T3, T4> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<Boolean> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					var v = function.testX(a1, a2, a3, a4);
					future.complete(v);
				} catch (Throwable e) {
					Handling.handleErrors(e);
					future.completeExceptionally(e);
				}
			});
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
		return future;
	}

	// </editor-fold>

	/** Creates function that always returns the same value. */
	static <T1, T2, T3, T4> LQuadPredicate<T1, T2, T3, T4> constant(boolean r) {
		return (a1, a2, a3, a4) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, T3, T4> LQuadPredicate<T1, T2, T3, T4> quadPred(final @Nonnull LQuadPredicate<T1, T2, T3, T4> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
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

	@Nonnull
	public static <T1, T2, T3, T4> LQuadPredicate<T1, T2, T3, T4> and(@Nonnull LQuadPredicate<? super T1, ? super T2, ? super T3, ? super T4>... predicates) {
		Null.nonNullArg(predicates, "predicates");
		return (a1, a2, a3, a4) -> {
			for (LQuadPredicate<? super T1, ? super T2, ? super T3, ? super T4> p : predicates) {
				if (!p.test(a1, a2, a3, a4)) {
					return false;
				}
			}
			return true;
		};
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

	@Nonnull
	public static <T1, T2, T3, T4> LQuadPredicate<T1, T2, T3, T4> or(@Nonnull LQuadPredicate<? super T1, ? super T2, ? super T3, ? super T4>... predicates) {
		Null.nonNullArg(predicates, "predicates");
		return (a1, a2, a3, a4) -> {
			for (LQuadPredicate<? super T1, ? super T2, ? super T3, ? super T4> p : predicates) {
				if (p.test(a1, a2, a3, a4)) {
					return true;
				}
			}
			return false;
		};
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

}
