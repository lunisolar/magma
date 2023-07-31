/*
 * This file is part of "lunisolar-magma".
 *
 * (C) Copyright 2014-2023 Lunisolar (http://lunisolar.eu/).
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
import java.util.stream.Stream; // NOSONAR

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
 * Non-throwing functional interface (lambda) LQuintPredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 5): T1 a1,T2 a2,T3 a3,T4 a4,T5 a5
 *
 * Co-domain: boolean
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LQuintPredicate<T1, T2, T3, T4, T5> extends MetaPredicate, MetaInterface.NonThrowing, Codomain<aBool>, Domain5<a<T1>, a<T2>, a<T3>, a<T4>, a<T5>> { // NOSONAR

	String DESCRIPTION = "LQuintPredicate: boolean test(T1 a1,T2 a2,T3 a3,T4 a4,T5 a5)";

	// boolean test(T1 a1,T2 a2,T3 a3,T4 a4,T5 a5) ;
	default boolean test(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5) {
		// return nestingTest(a1,a2,a3,a4,a5);
		try {
			return this.testX(a1, a2, a3, a4, a5);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call test(T1 a1,T2 a2,T3 a3,T4 a4,T5 a5)
	 */
	boolean testX(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5) throws Throwable;

	default boolean tupleTest(LQuint<T1, T2, T3, T4, T5> args) {
		return test(args.first(), args.second(), args.third(), args.fourth(), args.fifth());
	}

	/** Function call that handles exceptions according to the instructions. */
	default boolean handlingTest(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.testX(a1, a2, a3, a4, a5);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LQuintPredicate<T1, T2, T3, T4, T5> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3, a4, a5) -> handlingTest(a1, a2, a3, a4, a5, handling);
	}

	default boolean test(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.testX(a1, a2, a3, a4, a5);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default boolean test(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.testX(a1, a2, a3, a4, a5);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default boolean test(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.testX(a1, a2, a3, a4, a5);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default boolean test(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.testX(a1, a2, a3, a4, a5);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LQuintPredicate<T1, T2, T3, T4, T5> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2, a3, a4, a5) -> test(a1, a2, a3, a4, a5, factory, newMessage);
	}

	default LQuintPredicate<T1, T2, T3, T4, T5> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2, a3, a4, a5) -> test(a1, a2, a3, a4, a5, factory, newMessage, param1);
	}

	default LQuintPredicate<T1, T2, T3, T4, T5> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2, a3, a4, a5) -> test(a1, a2, a3, a4, a5, factory, newMessage, param1, param1);
	}

	default LQuintPredicate<T1, T2, T3, T4, T5> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2, a3, a4, a5) -> test(a1, a2, a3, a4, a5, factory, newMessage, param1, param2, param3);
	}

	default boolean test(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.testX(a1, a2, a3, a4, a5);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LQuintPredicate<T1, T2, T3, T4, T5> trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2, a3, a4, a5) -> test(a1, a2, a3, a4, a5, factory);
	}

	default boolean testThen(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LPredicate<Throwable> handler) {
		try {
			return this.testX(a1, a2, a3, a4, a5);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.test(e);
		}
	}

	default LQuintPredicate<T1, T2, T3, T4, T5> tryingThen(@Nonnull LPredicate<Throwable> handler) {
		return (a1, a2, a3, a4, a5) -> testThen(a1, a2, a3, a4, a5, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingTest(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5) {
		try {
			return this.testX(a1, a2, a3, a4, a5);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default boolean shovingTest(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5) {
		try {
			return this.testX(a1, a2, a3, a4, a5);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T1, T2, T3, T4, T5> boolean shovingTest(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, LQuintPredicate<T1, T2, T3, T4, T5> func) {
		Null.nonNullArg(func, "func");
		return func.shovingTest(a1, a2, a3, a4, a5);
	}

	static <T1, T2, T3, T4, T5> boolean handlingTest(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, LQuintPredicate<T1, T2, T3, T4, T5> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingTest(a1, a2, a3, a4, a5, handling);
	}

	static <T1, T2, T3, T4, T5> boolean tryTest(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, LQuintPredicate<T1, T2, T3, T4, T5> func) {
		Null.nonNullArg(func, "func");
		return func.nestingTest(a1, a2, a3, a4, a5);
	}

	static <T1, T2, T3, T4, T5> boolean tryTest(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, LQuintPredicate<T1, T2, T3, T4, T5> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, a4, a5, factory, newMessage);
	}

	static <T1, T2, T3, T4, T5> boolean tryTest(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, LQuintPredicate<T1, T2, T3, T4, T5> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, a4, a5, factory, newMessage, param1);
	}

	static <T1, T2, T3, T4, T5> boolean tryTest(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, LQuintPredicate<T1, T2, T3, T4, T5> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, a4, a5, factory, newMessage, param1, param2);
	}

	static <T1, T2, T3, T4, T5> boolean tryTest(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, LQuintPredicate<T1, T2, T3, T4, T5> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2,
			@Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, a4, a5, factory, newMessage, param1, param2, param3);
	}

	static <T1, T2, T3, T4, T5> boolean tryTest(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, LQuintPredicate<T1, T2, T3, T4, T5> func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, a4, a5, factory);
	}

	static <T1, T2, T3, T4, T5> boolean tryTestThen(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, LQuintPredicate<T1, T2, T3, T4, T5> func, @Nonnull LPredicate<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.testThen(a1, a2, a3, a4, a5, handler);
	}

	default boolean doIf(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, LAction action) {
		Null.nonNullArg(action, "action");
		if (test(a1, a2, a3, a4, a5)) {
			action.execute();
			return true;
		} else {
			return false;
		}
	}

	static <T1, T2, T3, T4, T5> boolean doIf(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintPredicate<T1, T2, T3, T4, T5> predicate, @Nonnull LAction action) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.doIf(a1, a2, a3, a4, a5, action);
	}

	static <T1, T2, T3, T4, T5> boolean doIf(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintPredicate<T1, T2, T3, T4, T5> predicate, @Nonnull LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.doIf(a1, a2, a3, a4, a5, consumer);
	}

	default boolean doIf(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintConsumer<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> consumer) {
		Null.nonNullArg(consumer, "consumer");
		if (test(a1, a2, a3, a4, a5)) {
			consumer.accept(a1, a2, a3, a4, a5);
			return true;
		} else {
			return false;
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullTest(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5) {
		return test(a1, a2, a3, a4, a5);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5) {
		return test(a1, a2, a3, a4, a5);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LQuintPredicate.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, T3, T4, T5> void fromTo(int min_i, int max_i, T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintPredicate<T1, T2, T3, T4, T5> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.test(a1, a2, a3, a4, a5);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.test(a1, a2, a3, a4, a5);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, T3, T4, T5> void fromTill(int min_i, int max_i, T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintPredicate<T1, T2, T3, T4, T5> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.test(a1, a2, a3, a4, a5);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.test(a1, a2, a3, a4, a5);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, T3, T4, T5> void times(int max_i, T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintPredicate<T1, T2, T3, T4, T5> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, a3, a4, a5, func);
	}

	/** Extract and apply function. */
	public static <M, K, V, T2, T3, T4, T5> boolean from(@Nonnull M container, LBiFunction<M, K, V> extractor, K key, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintPredicate<V, T2, T3, T4, T5> function) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.test(value, a2, a3, a4, a5);
		}

		return false;
	}

	/**  */
	public static <T1, T2, T3, T4, T5> LQuintPredicate<T1, T2, T3, T4, T5> uncurry(@Nonnull LFunction<T1, LFunction<T2, LFunction<T3, LFunction<T4, LPredicate<T5>>>>> func) {
		Null.nonNullArg(func, "func");
		return (T1 a1, T2 a2, T3 a3, T4 a4, T5 a5) -> func.apply(a1).apply(a2).apply(a3).apply(a4).test(a5);
	}

	/** Change function to one that ignores output. */
	default LQuintConsumer<T1, T2, T3, T4, T5> toConsumer() {
		return this::test;
	}

	/** Calls domain consumer before main function. */
	default LQuintPredicate<T1, T2, T3, T4, T5> beforeDo(@Nonnull LQuintConsumer<T1, T2, T3, T4, T5> before) {
		Null.nonNullArg(before, "before");
		return (T1 a1, T2 a2, T3 a3, T4 a4, T5 a5) -> {
			before.accept(a1, a2, a3, a4, a5);
			return test(a1, a2, a3, a4, a5);
		};
	}

	/** Calls codomain consumer after main function. */
	default LQuintPredicate<T1, T2, T3, T4, T5> afterDo(@Nonnull LBoolConsumer after) {
		Null.nonNullArg(after, "after");
		return (T1 a1, T2 a2, T3 a3, T4 a4, T5 a5) -> {
			final boolean retval = test(a1, a2, a3, a4, a5);
			after.accept(retval);
			return retval;
		};
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, T3, T4, T5, X extends Throwable> T1 throwIf(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintPredicate<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> pred, @Nonnull ExMF<X> factory,
			@Nonnull LQuintFunction<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends String> msgFunc) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (pred.test(a1, a2, a3, a4, a5)) {
			throw Handling.create(factory, msgFunc.apply(a1, a2, a3, a4, a5));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, T3, T4, T5, X extends Throwable> T1 throwIf(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintPredicate<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> pred, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (pred.test(a1, a2, a3, a4, a5)) {
			throw Handling.create(factory, String.format(msg, a1, a2, a3, a4, a5));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, T3, T4, T5, X extends Throwable> T1 throwIf(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintPredicate<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> pred, @Nonnull ExMF<X> factory, @Nonnull String message,
			@Nullable Object param1) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2, a3, a4, a5)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, T3, T4, T5, X extends Throwable> T1 throwIf(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintPredicate<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> pred, @Nonnull ExMF<X> factory, @Nonnull String message,
			@Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2, a3, a4, a5)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, T3, T4, T5, X extends Throwable> T1 throwIf(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintPredicate<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> pred, @Nonnull ExMF<X> factory, @Nonnull String message,
			@Nullable Object param1, @Nullable Object param2, @Nullable Object param3) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2, a3, a4, a5)) {
			throw Handling.create(factory, String.format(message, param1, param2, param3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, T3, T4, T5, X extends Throwable> T1 throwIfNot(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintPredicate<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> pred, @Nonnull ExMF<X> factory,
			@Nonnull LQuintFunction<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends String> msgFunc) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!pred.test(a1, a2, a3, a4, a5)) {
			throw Handling.create(factory, msgFunc.apply(a1, a2, a3, a4, a5));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, T3, T4, T5, X extends Throwable> T1 throwIfNot(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintPredicate<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> pred, @Nonnull ExMF<X> factory, @Nonnull String msg)
			throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (!pred.test(a1, a2, a3, a4, a5)) {
			throw Handling.create(factory, String.format(msg, a1, a2, a3, a4, a5));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, T3, T4, T5, X extends Throwable> T1 throwIfNot(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintPredicate<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> pred, @Nonnull ExMF<X> factory, @Nonnull String message,
			@Nullable Object param1) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2, a3, a4, a5)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, T3, T4, T5, X extends Throwable> T1 throwIfNot(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintPredicate<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> pred, @Nonnull ExMF<X> factory, @Nonnull String message,
			@Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2, a3, a4, a5)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, T3, T4, T5, X extends Throwable> T1 throwIfNot(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintPredicate<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> pred, @Nonnull ExMF<X> factory, @Nonnull String message,
			@Nullable Object param1, @Nullable Object param2, @Nullable Object param3) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2, a3, a4, a5)) {
			throw Handling.create(factory, String.format(message, param1, param2, param3));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, T3, T4, T5, X extends Throwable> T1 throwIf(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintPredicate<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> pred, @Nonnull ExF<X> noArgFactory) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(noArgFactory, "noArgFactory");
		if (pred.test(a1, a2, a3, a4, a5)) {
			throw Handling.create(noArgFactory);
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, T3, T4, T5, X extends Throwable> T1 throwIfNot(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintPredicate<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> pred, @Nonnull ExF<X> noArgFactory) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(noArgFactory, "noArgFactory");
		if (!pred.test(a1, a2, a3, a4, a5)) {
			throw Handling.create(noArgFactory);
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, T3, T4, T5, X extends Throwable> T1 throwIf(T1 a1, @Nonnull LQuintPredicate<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> pred, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull ExMF<X> factory,
			@Nonnull LQuintFunction<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends String> msgFunc) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (pred.test(a1, a2, a3, a4, a5)) {
			throw Handling.create(factory, msgFunc.apply(a1, a2, a3, a4, a5));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, T3, T4, T5, X extends Throwable> T1 throwIf(T1 a1, @Nonnull LQuintPredicate<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> pred, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (pred.test(a1, a2, a3, a4, a5)) {
			throw Handling.create(factory, String.format(msg, a1, a2, a3, a4, a5));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, T3, T4, T5, X extends Throwable> T1 throwIf(T1 a1, @Nonnull LQuintPredicate<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> pred, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull ExMF<X> factory, @Nonnull String message,
			@Nullable Object param1) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2, a3, a4, a5)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, T3, T4, T5, X extends Throwable> T1 throwIf(T1 a1, @Nonnull LQuintPredicate<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> pred, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull ExMF<X> factory, @Nonnull String message,
			@Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2, a3, a4, a5)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, T3, T4, T5, X extends Throwable> T1 throwIf(T1 a1, @Nonnull LQuintPredicate<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> pred, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull ExMF<X> factory, @Nonnull String message,
			@Nullable Object param1, @Nullable Object param2, @Nullable Object param3) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2, a3, a4, a5)) {
			throw Handling.create(factory, String.format(message, param1, param2, param3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, T3, T4, T5, X extends Throwable> T1 throwIfNot(T1 a1, @Nonnull LQuintPredicate<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> pred, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull ExMF<X> factory,
			@Nonnull LQuintFunction<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends String> msgFunc) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!pred.test(a1, a2, a3, a4, a5)) {
			throw Handling.create(factory, msgFunc.apply(a1, a2, a3, a4, a5));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, T3, T4, T5, X extends Throwable> T1 throwIfNot(T1 a1, @Nonnull LQuintPredicate<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> pred, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull ExMF<X> factory, @Nonnull String msg)
			throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (!pred.test(a1, a2, a3, a4, a5)) {
			throw Handling.create(factory, String.format(msg, a1, a2, a3, a4, a5));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, T3, T4, T5, X extends Throwable> T1 throwIfNot(T1 a1, @Nonnull LQuintPredicate<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> pred, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull ExMF<X> factory, @Nonnull String message,
			@Nullable Object param1) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2, a3, a4, a5)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, T3, T4, T5, X extends Throwable> T1 throwIfNot(T1 a1, @Nonnull LQuintPredicate<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> pred, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull ExMF<X> factory, @Nonnull String message,
			@Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2, a3, a4, a5)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, T3, T4, T5, X extends Throwable> T1 throwIfNot(T1 a1, @Nonnull LQuintPredicate<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> pred, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull ExMF<X> factory, @Nonnull String message,
			@Nullable Object param1, @Nullable Object param2, @Nullable Object param3) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2, a3, a4, a5)) {
			throw Handling.create(factory, String.format(message, param1, param2, param3));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, T3, T4, T5, X extends Throwable> T1 throwIf(T1 a1, @Nonnull LQuintPredicate<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> pred, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull ExF<X> noArgFactory) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(noArgFactory, "noArgFactory");
		if (pred.test(a1, a2, a3, a4, a5)) {
			throw Handling.create(noArgFactory);
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, T3, T4, T5, X extends Throwable> T1 throwIfNot(T1 a1, @Nonnull LQuintPredicate<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> pred, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull ExF<X> noArgFactory) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(noArgFactory, "noArgFactory");
		if (!pred.test(a1, a2, a3, a4, a5)) {
			throw Handling.create(noArgFactory);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, T3, T4, T5, X extends Throwable> T1 throwIfNotEx(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintFunction<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends String> specialPredicate, @Nonnull ExMF<X> factory)
			throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		var m = specialPredicate.apply(a1, a2, a3, a4, a5);
		if (m != null) {
			throw Handling.create(factory, m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, T3, T4, T5, X extends Throwable> T1 throwIfNotEx(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintFunction<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends String> specialPredicate,
			@Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		var m = specialPredicate.apply(a1, a2, a3, a4, a5);
		if (m != null) {
			throw Handling.create(factory, String.format(msg, a1, a2, a3, a4, a5) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, T3, T4, T5, X extends Throwable> T1 throwIfNotEx(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintFunction<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends String> specialPredicate,
			@Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		var m = specialPredicate.apply(a1, a2, a3, a4, a5);
		if (m != null) {
			throw Handling.create(factory, String.format(message, param1) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, T3, T4, T5, X extends Throwable> T1 throwIfNotEx(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintFunction<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends String> specialPredicate,
			@Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		var m = specialPredicate.apply(a1, a2, a3, a4, a5);
		if (m != null) {
			throw Handling.create(factory, String.format(message, param1, param2) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, T3, T4, T5, X extends Throwable> T1 throwIfNotEx(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintFunction<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends String> specialPredicate,
			@Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		var m = specialPredicate.apply(a1, a2, a3, a4, a5);
		if (m != null) {
			throw Handling.create(factory, String.format(message, param1, param2, param3) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, T3, T4, T5, X extends Throwable> T1 throwIfNotEx(T1 a1, @Nonnull LQuintFunction<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends String> specialPredicate, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull ExMF<X> factory)
			throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		var m = specialPredicate.apply(a1, a2, a3, a4, a5);
		if (m != null) {
			throw Handling.create(factory, m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, T3, T4, T5, X extends Throwable> T1 throwIfNotEx(T1 a1, @Nonnull LQuintFunction<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends String> specialPredicate, T2 a2, T3 a3, T4 a4, T5 a5,
			@Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		var m = specialPredicate.apply(a1, a2, a3, a4, a5);
		if (m != null) {
			throw Handling.create(factory, String.format(msg, a1, a2, a3, a4, a5) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, T3, T4, T5, X extends Throwable> T1 throwIfNotEx(T1 a1, @Nonnull LQuintFunction<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends String> specialPredicate, T2 a2, T3 a3, T4 a4, T5 a5,
			@Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		var m = specialPredicate.apply(a1, a2, a3, a4, a5);
		if (m != null) {
			throw Handling.create(factory, String.format(message, param1) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, T3, T4, T5, X extends Throwable> T1 throwIfNotEx(T1 a1, @Nonnull LQuintFunction<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends String> specialPredicate, T2 a2, T3 a3, T4 a4, T5 a5,
			@Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		var m = specialPredicate.apply(a1, a2, a3, a4, a5);
		if (m != null) {
			throw Handling.create(factory, String.format(message, param1, param2) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, T3, T4, T5, X extends Throwable> T1 throwIfNotEx(T1 a1, @Nonnull LQuintFunction<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends String> specialPredicate, T2 a2, T3 a3, T4 a4, T5 a5,
			@Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		var m = specialPredicate.apply(a1, a2, a3, a4, a5);
		if (m != null) {
			throw Handling.create(factory, String.format(message, param1, param2, param3) + ' ' + m);
		}
		return a1;
	}

	// <editor-fold desc="CallContext">

	static <T1, T2, T3, T4, T5> boolean nestingTest(@Nullable CallContext c1, T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintPredicate<T1, T2, T3, T4, T5> function) {
		Null.nonNullArg(function, "function");
		try {
			return testX(c1, a1, a2, a3, a4, a5, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static <T1, T2, T3, T4, T5> boolean shovingTest(@Nullable CallContext c1, T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintPredicate<T1, T2, T3, T4, T5> function) {
		Null.nonNullArg(function, "function");
		try {
			return testX(c1, a1, a2, a3, a4, a5, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static <T1, T2, T3, T4, T5> boolean testX(@Nullable CallContext c1, T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintPredicate<T1, T2, T3, T4, T5> function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContext.tryInit(last, c1);

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;
		Object retval = null;
		if (primary == null) {
			try {
				retval = function.shovingTest(a1, a2, a3, a4, a5);
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

	static <T1, T2, T3, T4, T5> CompletableFuture<Boolean> asyncTest(@Nonnull AsyncCallContext async, @Nullable CallContext c1, T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintPredicate<T1, T2, T3, T4, T5> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<Boolean> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					var v = LQuintPredicate.testX(c1, a1, a2, a3, a4, a5, function);
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

	static <T1, T2, T3, T4, T5> boolean nestingTest(@Nullable CallContext c1, @Nullable CallContext c2, T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintPredicate<T1, T2, T3, T4, T5> function) {
		Null.nonNullArg(function, "function");
		try {
			return testX(c1, c2, a1, a2, a3, a4, a5, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static <T1, T2, T3, T4, T5> boolean shovingTest(@Nullable CallContext c1, @Nullable CallContext c2, T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintPredicate<T1, T2, T3, T4, T5> function) {
		Null.nonNullArg(function, "function");
		try {
			return testX(c1, c2, a1, a2, a3, a4, a5, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static <T1, T2, T3, T4, T5> boolean testX(@Nullable CallContext c1, @Nullable CallContext c2, T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintPredicate<T1, T2, T3, T4, T5> function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContext.tryInit(last, c1);
		final Object s2 = last = CallContext.tryInit(last, c2);

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;
		Object retval = null;
		if (primary == null) {
			try {
				retval = function.shovingTest(a1, a2, a3, a4, a5);
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

	static <T1, T2, T3, T4, T5> CompletableFuture<Boolean> asyncTest(@Nonnull AsyncCallContext async, @Nullable CallContext c1, @Nullable CallContext c2, T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintPredicate<T1, T2, T3, T4, T5> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<Boolean> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					var v = LQuintPredicate.testX(c1, c2, a1, a2, a3, a4, a5, function);
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

	static <T1, T2, T3, T4, T5> boolean nestingTest(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintPredicate<T1, T2, T3, T4, T5> function) {
		Null.nonNullArg(function, "function");
		try {
			return testX(c1, c2, c3, a1, a2, a3, a4, a5, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static <T1, T2, T3, T4, T5> boolean shovingTest(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintPredicate<T1, T2, T3, T4, T5> function) {
		Null.nonNullArg(function, "function");
		try {
			return testX(c1, c2, c3, a1, a2, a3, a4, a5, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static <T1, T2, T3, T4, T5> boolean testX(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintPredicate<T1, T2, T3, T4, T5> function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContext.tryInit(last, c1);
		final Object s2 = last = CallContext.tryInit(last, c2);
		final Object s3 = last = CallContext.tryInit(last, c3);

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;
		Object retval = null;
		if (primary == null) {
			try {
				retval = function.shovingTest(a1, a2, a3, a4, a5);
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

	static <T1, T2, T3, T4, T5> CompletableFuture<Boolean> asyncTest(@Nonnull AsyncCallContext async, @Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, T1 a1, T2 a2, T3 a3, T4 a4, T5 a5,
			@Nonnull LQuintPredicate<T1, T2, T3, T4, T5> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<Boolean> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					var v = LQuintPredicate.testX(c1, c2, c3, a1, a2, a3, a4, a5, function);
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

	static <T1, T2, T3, T4, T5> boolean nestingTest(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintPredicate<T1, T2, T3, T4, T5> function) {
		Null.nonNullArg(function, "function");
		try {
			return testX(c1, c2, c3, c4, a1, a2, a3, a4, a5, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static <T1, T2, T3, T4, T5> boolean shovingTest(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintPredicate<T1, T2, T3, T4, T5> function) {
		Null.nonNullArg(function, "function");
		try {
			return testX(c1, c2, c3, c4, a1, a2, a3, a4, a5, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static <T1, T2, T3, T4, T5> boolean testX(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintPredicate<T1, T2, T3, T4, T5> function)
			throws Throwable {
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
				retval = function.shovingTest(a1, a2, a3, a4, a5);
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

	static <T1, T2, T3, T4, T5> CompletableFuture<Boolean> asyncTest(@Nonnull AsyncCallContext async, @Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, T1 a1, T2 a2, T3 a3, T4 a4, T5 a5,
			@Nonnull LQuintPredicate<T1, T2, T3, T4, T5> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<Boolean> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					var v = LQuintPredicate.testX(c1, c2, c3, c4, a1, a2, a3, a4, a5, function);
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

	static <T1, T2, T3, T4, T5> CompletableFuture<Boolean> asyncTest(@Nonnull AsyncCallContext async, T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintPredicate<T1, T2, T3, T4, T5> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<Boolean> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					var v = function.testX(a1, a2, a3, a4, a5);
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
	static <T1, T2, T3, T4, T5> LQuintPredicate<T1, T2, T3, T4, T5> constant(boolean r) {
		return (a1, a2, a3, a4, a5) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, T3, T4, T5> LQuintPredicate<T1, T2, T3, T4, T5> quintPred(final @Nonnull LQuintPredicate<T1, T2, T3, T4, T5> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T1, T2, T3, T4, T5> LQuintPredicate<T1, T2, T3, T4, T5> quintPredThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3, a4, a5) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T1, T2, T3, T4, T5> LQuintPredicate<T1, T2, T3, T4, T5> quintPredThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3, a4, a5) -> {
			throw exF.produce(message);
		};
	}

	static <T1, T2, T3, T4, T5> boolean call(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, final @Nonnull LQuintPredicate<T1, T2, T3, T4, T5> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.test(a1, a2, a3, a4, a5);
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
	default LQuintPredicate<T1, T2, T3, T4, T5> negate() {
		return (a1, a2, a3, a4, a5) -> !test(a1, a2, a3, a4, a5);
	}

	@Nonnull
	static <T1, T2, T3, T4, T5> LQuintPredicate<T1, T2, T3, T4, T5> not(@Nonnull LQuintPredicate<T1, T2, T3, T4, T5> pred) {
		Null.nonNullArg(pred, "pred");
		return pred.negate();
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LQuintPredicate<T1, T2, T3, T4, T5> and(@Nonnull LQuintPredicate<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3, a4, a5) -> test(a1, a2, a3, a4, a5) && other.test(a1, a2, a3, a4, a5);
	}

	@Nonnull
	public static <T1, T2, T3, T4, T5> LQuintPredicate<T1, T2, T3, T4, T5> and(@Nonnull LQuintPredicate<? super T1, ? super T2, ? super T3, ? super T4, ? super T5>... predicates) {
		Null.nonNullArg(predicates, "predicates");
		return (a1, a2, a3, a4, a5) -> !any(false, a1, a2, a3, a4, a5, predicates);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LQuintPredicate<T1, T2, T3, T4, T5> or(@Nonnull LQuintPredicate<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3, a4, a5) -> test(a1, a2, a3, a4, a5) || other.test(a1, a2, a3, a4, a5);
	}

	@Nonnull
	public static <T1, T2, T3, T4, T5> LQuintPredicate<T1, T2, T3, T4, T5> or(@Nonnull LQuintPredicate<? super T1, ? super T2, ? super T3, ? super T4, ? super T5>... predicates) {
		Null.nonNullArg(predicates, "predicates");
		return (a1, a2, a3, a4, a5) -> any(true, a1, a2, a3, a4, a5, predicates);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LQuintPredicate<T1, T2, T3, T4, T5> xor(@Nonnull LQuintPredicate<? super T1, ? super T2, ? super T3, ? super T4, ? super T5> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3, a4, a5) -> test(a1, a2, a3, a4, a5) ^ other.test(a1, a2, a3, a4, a5);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static <T1, T2, T3, T4, T5> LQuintPredicate<T1, T2, T3, T4, T5> isEqual(T1 v1, T2 v2, T3 v3, T4 v4, T5 v5) {
		return (a1, a2, a3, a4, a5) -> (a1 == null ? v1 == null : a1.equals(v1)) && (a2 == null ? v2 == null : a2.equals(v2)) && (a3 == null ? v3 == null : a3.equals(v3)) && (a4 == null ? v4 == null : a4.equals(v4))
				&& (a5 == null ? v5 == null : a5.equals(v5));
	}

	// </editor-fold>

	public static <T1, T2, T3, T4, T5> boolean any(boolean expected, T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull Collection<? extends LQuintPredicate<? super T1, ? super T2, ? super T3, ? super T4, ? super T5>> predicates) {
		return any(expected, a1, a2, a3, a4, a5, Null.nonNullArg(predicates, "predicates").iterator());
	}

	public static <T1, T2, T3, T4, T5> boolean any(boolean expected, T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull Stream<? extends LQuintPredicate<? super T1, ? super T2, ? super T3, ? super T4, ? super T5>> predicates) {
		return any(expected, a1, a2, a3, a4, a5, Null.nonNullArg(predicates, "predicates").iterator());
	}

	public static <T1, T2, T3, T4, T5> boolean any(boolean expected, T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull Iterator<? extends LQuintPredicate<? super T1, ? super T2, ? super T3, ? super T4, ? super T5>> predicates) {
		Null.nonNullArg(predicates, "predicates");
		for (var it = predicates; it.hasNext();) {
			var pred = it.next();
			Null.nonNullArg(pred, "pred");
			if (expected == pred.test(a1, a2, a3, a4, a5)) {
				return true;
			}
		}
		return false;
	}

	public static <T1, T2, T3, T4, T5> boolean any(boolean expected, T1 a1, T2 a2, T3 a3, T4 a4, T5 a5, @Nonnull LQuintPredicate<? super T1, ? super T2, ? super T3, ? super T4, ? super T5>... predicates) {
		Null.nonNullArg(predicates, "predicates");
		for (var pred : predicates) {
			Null.nonNullArg(pred, "pred");
			if (expected == pred.test(a1, a2, a3, a4, a5)) {
				return true;
			}
		}
		return false;
	}

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3, V4, V5> LQuintPredicate<V1, V2, V3, V4, V5> compose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2,
			@Nonnull final LFunction<? super V3, ? extends T3> before3, @Nonnull final LFunction<? super V4, ? extends T4> before4, @Nonnull final LFunction<? super V5, ? extends T5> before5) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		Null.nonNullArg(before4, "before4");
		Null.nonNullArg(before5, "before5");
		return (v1, v2, v3, v4, v5) -> this.test(before1.apply(v1), before2.apply(v2), before3.apply(v3), before4.apply(v4), before5.apply(v5));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LQuintFunction<T1, T2, T3, T4, T5, V> boolToQuintFunc(@Nonnull LBoolFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3, a4, a5) -> after.apply(this.test(a1, a2, a3, a4, a5));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	// >>> LQuintPredicate<T1,T2,T3,T4,T5>

	/** Returns TRUE. */
	public static <T1, T2, T3, T4, T5> boolean alwaysTrue(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5) {
		return true;
	}

	/** Returns FALSE. */
	public static <T1, T2, T3, T4, T5> boolean alwaysFalse(T1 a1, T2 a2, T3 a3, T4 a4, T5 a5) {
		return false;
	}

}
