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
public interface LBiPredicate<T1, T2> extends BiPredicate<T1, T2>, MetaPredicate, MetaInterface.NonThrowing, Codomain<aBool>, Domain2<a<T1>, a<T2>> { //NOSONAR

	String DESCRIPTION = "LBiPredicate: boolean test(T1 a1,T2 a2)";

	default boolean test(T1 a1, T2 a2) {
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
		} catch (Throwable e) { //NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LBiPredicate<T1, T2> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2) -> handlingTest(a1, a2, handling);
	}

	default boolean test(T1 a1, T2 a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.testX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default boolean test(T1 a1, T2 a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.testX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default boolean test(T1 a1, T2 a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.testX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default boolean test(T1 a1, T2 a2, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.testX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LBiPredicate<T1, T2> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2) -> test(a1, a2, factory, newMessage);
	}

	default LBiPredicate<T1, T2> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2) -> test(a1, a2, factory, newMessage, param1);
	}

	default LBiPredicate<T1, T2> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2) -> test(a1, a2, factory, newMessage, param1, param1);
	}

	default LBiPredicate<T1, T2> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2) -> test(a1, a2, factory, newMessage, param1, param2, param3);
	}

	default boolean test(T1 a1, T2 a2, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.testX(a1, a2);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LBiPredicate<T1, T2> trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2) -> test(a1, a2, factory);
	}

	default boolean testThen(T1 a1, T2 a2, @Nonnull LPredicate<Throwable> handler) {
		try {
			return this.testX(a1, a2);
		} catch (Throwable e) { //NOSONAR
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
		} catch (Throwable e) { //NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T1, T2> boolean shovingTest(T1 a1, T2 a2, LBiPredicate<T1, T2> func) {
		Null.nonNullArg(func, "func");
		return func.shovingTest(a1, a2);
	}

	static <T1, T2> boolean handlingTest(T1 a1, T2 a2, LBiPredicate<T1, T2> func, HandlingInstructions<Throwable, RuntimeException> handling) { //<-
		Null.nonNullArg(func, "func");
		return func.handlingTest(a1, a2, handling);
	}

	static <T1, T2> boolean tryTest(T1 a1, T2 a2, LBiPredicate<T1, T2> func) {
		Null.nonNullArg(func, "func");
		return func.nestingTest(a1, a2);
	}

	static <T1, T2> boolean tryTest(T1 a1, T2 a2, LBiPredicate<T1, T2> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, factory, newMessage);
	}

	static <T1, T2> boolean tryTest(T1 a1, T2 a2, LBiPredicate<T1, T2> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, factory, newMessage, param1);
	}

	static <T1, T2> boolean tryTest(T1 a1, T2 a2, LBiPredicate<T1, T2> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, factory, newMessage, param1, param2);
	}

	static <T1, T2> boolean tryTest(T1 a1, T2 a2, LBiPredicate<T1, T2> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, factory, newMessage, param1, param2, param3);
	}

	static <T1, T2> boolean tryTest(T1 a1, T2 a2, LBiPredicate<T1, T2> func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, factory);
	}

	static <T1, T2> boolean tryTestThen(T1 a1, T2 a2, LBiPredicate<T1, T2> func, @Nonnull LPredicate<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.testThen(a1, a2, handler);
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

	default <V> boolean doIf(V a1, T1 a2, T2 a3, @Nonnull LTriConsumer<V, ? super T1, ? super T2> consumer) {
		Null.nonNullArg(consumer, "consumer");
		if (test(a2, a3)) {
			consumer.accept(a1, a2, a3);
			return true;
		} else {
			return false;
		}
	}

	/** 2 */
	default <V> int doIf(V a1, T1 a2, T2 a3, @Nonnull LToIntTriFunction<V, ? super T1, ? super T2> consumer) {
		Null.nonNullArg(consumer, "consumer");
		if (test(a2, a3)) {
			return consumer.applyAsInt(a1, a2, a3);
		} else {
			return 0;
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void fromTo(int min_i, int max_i, T1 a1, T2 a2, @Nonnull LBiPredicate<T1, T2> func) {
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
	public static <T1, T2> void fromTill(int min_i, int max_i, T1 a1, T2 a2, @Nonnull LBiPredicate<T1, T2> func) {
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
	public static <T1, T2> void times(int max_i, T1 a1, T2 a2, @Nonnull LBiPredicate<T1, T2> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, func);
	}

	/** Extract and apply function. */
	public static <M, K, V, T2> boolean from(@Nonnull M container, LBiFunction<M, K, V> extractor, K key, T2 a2, @Nonnull LBiPredicate<V, T2> function) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.test(value, a2);
		}

		return false;
	}

	default LPredicate<T2> _with(T1 a1) {
		return a2 -> test(a1, a2);
	}

	default LPredicate<T1> with(T2 a2) {
		return a1 -> test(a1, a2);
	}

	/**  */
	public static <T1, T2> LBiPredicate<T1, T2> uncurry(@Nonnull LFunction<T1, LPredicate<T2>> func) {
		Null.nonNullArg(func, "func");
		return (T1 a1, T2 a2) -> func.apply(a1).test(a2);
	}

	/** Change function to one that ignores output. */
	default LBiConsumer<T1, T2> toConsumer() {
		return this::test;
	}

	/** Calls domain consumer before main function. */
	default LBiPredicate<T1, T2> beforeDo(@Nonnull LBiConsumer<T1, T2> before) {
		Null.nonNullArg(before, "before");
		return (T1 a1, T2 a2) -> {
			before.accept(a1, a2);
			return test(a1, a2);
		};
	}

	/** Calls codomain consumer after main function. */
	default LBiPredicate<T1, T2> afterDo(@Nonnull LBoolConsumer after) {
		Null.nonNullArg(after, "after");
		return (T1 a1, T2 a2) -> {
			final boolean retval = test(a1, a2);
			after.accept(retval);
			return retval;
		};
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, X extends Throwable> T1 throwIf(T1 a1, T2 a2, @Nonnull LBiPredicate<? super T1, ? super T2> pred, @Nonnull ExMF<X> factory, @Nonnull LBiFunction<? super T1, ? super T2, ? extends String> msgFunc) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (pred.test(a1, a2)) {
			throw Handling.create(factory, msgFunc.apply(a1, a2));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, X extends Throwable> T1 throwIf(T1 a1, T2 a2, @Nonnull LBiPredicate<? super T1, ? super T2> pred, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (pred.test(a1, a2)) {
			throw Handling.create(factory, String.format(msg, a1, a2));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, X extends Throwable> T1 throwIf(T1 a1, T2 a2, @Nonnull LBiPredicate<? super T1, ? super T2> pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, X extends Throwable> T1 throwIf(T1 a1, T2 a2, @Nonnull LBiPredicate<? super T1, ? super T2> pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, X extends Throwable> T1 throwIf(T1 a1, T2 a2, @Nonnull LBiPredicate<? super T1, ? super T2> pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3)
			throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2)) {
			throw Handling.create(factory, String.format(message, param1, param2, param3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, X extends Throwable> T1 throwIfNot(T1 a1, T2 a2, @Nonnull LBiPredicate<? super T1, ? super T2> pred, @Nonnull ExMF<X> factory, @Nonnull LBiFunction<? super T1, ? super T2, ? extends String> msgFunc) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!pred.test(a1, a2)) {
			throw Handling.create(factory, msgFunc.apply(a1, a2));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, X extends Throwable> T1 throwIfNot(T1 a1, T2 a2, @Nonnull LBiPredicate<? super T1, ? super T2> pred, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (!pred.test(a1, a2)) {
			throw Handling.create(factory, String.format(msg, a1, a2));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, X extends Throwable> T1 throwIfNot(T1 a1, T2 a2, @Nonnull LBiPredicate<? super T1, ? super T2> pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, X extends Throwable> T1 throwIfNot(T1 a1, T2 a2, @Nonnull LBiPredicate<? super T1, ? super T2> pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, X extends Throwable> T1 throwIfNot(T1 a1, T2 a2, @Nonnull LBiPredicate<? super T1, ? super T2> pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3)
			throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2)) {
			throw Handling.create(factory, String.format(message, param1, param2, param3));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, X extends Throwable> T1 throwIf(T1 a1, T2 a2, @Nonnull LBiPredicate<? super T1, ? super T2> pred, @Nonnull ExF<X> noArgFactory) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(noArgFactory, "noArgFactory");
		if (pred.test(a1, a2)) {
			throw Handling.create(noArgFactory);
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, X extends Throwable> T1 throwIfNot(T1 a1, T2 a2, @Nonnull LBiPredicate<? super T1, ? super T2> pred, @Nonnull ExF<X> noArgFactory) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(noArgFactory, "noArgFactory");
		if (!pred.test(a1, a2)) {
			throw Handling.create(noArgFactory);
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, X extends Throwable> T1 throwIf(T1 a1, @Nonnull LBiPredicate<? super T1, ? super T2> pred, T2 a2, @Nonnull ExMF<X> factory, @Nonnull LBiFunction<? super T1, ? super T2, ? extends String> msgFunc) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (pred.test(a1, a2)) {
			throw Handling.create(factory, msgFunc.apply(a1, a2));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, X extends Throwable> T1 throwIf(T1 a1, @Nonnull LBiPredicate<? super T1, ? super T2> pred, T2 a2, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (pred.test(a1, a2)) {
			throw Handling.create(factory, String.format(msg, a1, a2));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, X extends Throwable> T1 throwIf(T1 a1, @Nonnull LBiPredicate<? super T1, ? super T2> pred, T2 a2, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, X extends Throwable> T1 throwIf(T1 a1, @Nonnull LBiPredicate<? super T1, ? super T2> pred, T2 a2, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, X extends Throwable> T1 throwIf(T1 a1, @Nonnull LBiPredicate<? super T1, ? super T2> pred, T2 a2, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3)
			throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2)) {
			throw Handling.create(factory, String.format(message, param1, param2, param3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, X extends Throwable> T1 throwIfNot(T1 a1, @Nonnull LBiPredicate<? super T1, ? super T2> pred, T2 a2, @Nonnull ExMF<X> factory, @Nonnull LBiFunction<? super T1, ? super T2, ? extends String> msgFunc) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!pred.test(a1, a2)) {
			throw Handling.create(factory, msgFunc.apply(a1, a2));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, X extends Throwable> T1 throwIfNot(T1 a1, @Nonnull LBiPredicate<? super T1, ? super T2> pred, T2 a2, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (!pred.test(a1, a2)) {
			throw Handling.create(factory, String.format(msg, a1, a2));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, X extends Throwable> T1 throwIfNot(T1 a1, @Nonnull LBiPredicate<? super T1, ? super T2> pred, T2 a2, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, X extends Throwable> T1 throwIfNot(T1 a1, @Nonnull LBiPredicate<? super T1, ? super T2> pred, T2 a2, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, X extends Throwable> T1 throwIfNot(T1 a1, @Nonnull LBiPredicate<? super T1, ? super T2> pred, T2 a2, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3)
			throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2)) {
			throw Handling.create(factory, String.format(message, param1, param2, param3));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, X extends Throwable> T1 throwIf(T1 a1, @Nonnull LBiPredicate<? super T1, ? super T2> pred, T2 a2, @Nonnull ExF<X> noArgFactory) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(noArgFactory, "noArgFactory");
		if (pred.test(a1, a2)) {
			throw Handling.create(noArgFactory);
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, X extends Throwable> T1 throwIfNot(T1 a1, @Nonnull LBiPredicate<? super T1, ? super T2> pred, T2 a2, @Nonnull ExF<X> noArgFactory) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(noArgFactory, "noArgFactory");
		if (!pred.test(a1, a2)) {
			throw Handling.create(noArgFactory);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, X extends Throwable> T1 throwIfNotEx(T1 a1, T2 a2, @Nonnull LBiFunction<? super T1, ? super T2, ? extends String> specialPredicate, @Nonnull ExMF<X> factory) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		var m = specialPredicate.apply(a1, a2);
		if (m != null) {
			throw Handling.create(factory, m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, X extends Throwable> T1 throwIfNotEx(T1 a1, T2 a2, @Nonnull LBiFunction<? super T1, ? super T2, ? extends String> specialPredicate, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		var m = specialPredicate.apply(a1, a2);
		if (m != null) {
			throw Handling.create(factory, String.format(msg, a1, a2) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, X extends Throwable> T1 throwIfNotEx(T1 a1, T2 a2, @Nonnull LBiFunction<? super T1, ? super T2, ? extends String> specialPredicate, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		var m = specialPredicate.apply(a1, a2);
		if (m != null) {
			throw Handling.create(factory, String.format(message, param1) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, X extends Throwable> T1 throwIfNotEx(T1 a1, T2 a2, @Nonnull LBiFunction<? super T1, ? super T2, ? extends String> specialPredicate, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1,
			@Nullable Object param2) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		var m = specialPredicate.apply(a1, a2);
		if (m != null) {
			throw Handling.create(factory, String.format(message, param1, param2) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, X extends Throwable> T1 throwIfNotEx(T1 a1, T2 a2, @Nonnull LBiFunction<? super T1, ? super T2, ? extends String> specialPredicate, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1,
			@Nullable Object param2, @Nullable Object param3) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		var m = specialPredicate.apply(a1, a2);
		if (m != null) {
			throw Handling.create(factory, String.format(message, param1, param2, param3) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, X extends Throwable> T1 throwIfNotEx(T1 a1, @Nonnull LBiFunction<? super T1, ? super T2, ? extends String> specialPredicate, T2 a2, @Nonnull ExMF<X> factory) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		var m = specialPredicate.apply(a1, a2);
		if (m != null) {
			throw Handling.create(factory, m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, X extends Throwable> T1 throwIfNotEx(T1 a1, @Nonnull LBiFunction<? super T1, ? super T2, ? extends String> specialPredicate, T2 a2, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		var m = specialPredicate.apply(a1, a2);
		if (m != null) {
			throw Handling.create(factory, String.format(msg, a1, a2) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, X extends Throwable> T1 throwIfNotEx(T1 a1, @Nonnull LBiFunction<? super T1, ? super T2, ? extends String> specialPredicate, T2 a2, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		var m = specialPredicate.apply(a1, a2);
		if (m != null) {
			throw Handling.create(factory, String.format(message, param1) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, X extends Throwable> T1 throwIfNotEx(T1 a1, @Nonnull LBiFunction<? super T1, ? super T2, ? extends String> specialPredicate, T2 a2, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1,
			@Nullable Object param2) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		var m = specialPredicate.apply(a1, a2);
		if (m != null) {
			throw Handling.create(factory, String.format(message, param1, param2) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, X extends Throwable> T1 throwIfNotEx(T1 a1, @Nonnull LBiFunction<? super T1, ? super T2, ? extends String> specialPredicate, T2 a2, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1,
			@Nullable Object param2, @Nullable Object param3) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		var m = specialPredicate.apply(a1, a2);
		if (m != null) {
			throw Handling.create(factory, String.format(message, param1, param2, param3) + ' ' + m);
		}
		return a1;
	}

	//<editor-fold desc="CallContext">

	@Nonnull
	static <T1, T2> LBiPredicate<T1, T2> biPred(@Nullable CallContext c1, final @Nonnull LBiPredicate<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.withCC(c1);
	}

	default @Nonnull LBiPredicate<T1, T2> withCC(@Nullable CallContext c1) {
		return (a1, a2) -> LBiPredicate.shovingTest(c1, a1, a2, this);
	}

	static <T1, T2> boolean nestingTest(@Nullable CallContext c1, T1 a1, T2 a2, @Nonnull LBiPredicate<T1, T2> function) {
		Null.nonNullArg(function, "function");
		try {
			return testX(c1, a1, a2, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static <T1, T2> boolean shovingTest(@Nullable CallContext c1, T1 a1, T2 a2, @Nonnull LBiPredicate<T1, T2> function) {
		Null.nonNullArg(function, "function");
		try {
			return testX(c1, a1, a2, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static <T1, T2> boolean testX(@Nullable CallContext c1, T1 a1, T2 a2, @Nonnull LBiPredicate<T1, T2> function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContexts.tryInit(last, c1); // try { c1?.start() ...

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;
		Object retval = null;
		if (primary == null) {
			try {
				retval = function.shovingTest(a1, a2);
			} catch (Throwable e) {
				primary = e;
			}
		}

		primary = CallContexts.tryFinish(primary, c1, s1); // } finally { c1?.end(...) }

		if (primary != null) {
			throw Handling.throwIt(primary);
		}
		return (boolean) retval;
	}

	static <T1, T2> CompletableFuture<Boolean> asyncTest(@Nonnull AsyncCallContext async, @Nullable CallContext c1, T1 a1, T2 a2, @Nonnull LBiPredicate<T1, T2> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<Boolean> future = new CompletableFuture<>();
		//noinspection unchecked,rawtypes
		return (CompletableFuture) async.call_(() -> {
			return LBiPredicate.testX(c1, a1, a2, function);

		});
	}

	static <T1, T2> boolean nestingTest(@Nullable CallContext c1, @Nullable CallContext c2, T1 a1, T2 a2, @Nonnull LBiPredicate<T1, T2> function) {
		Null.nonNullArg(function, "function");
		try {
			return testX(c1, c2, a1, a2, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static <T1, T2> boolean shovingTest(@Nullable CallContext c1, @Nullable CallContext c2, T1 a1, T2 a2, @Nonnull LBiPredicate<T1, T2> function) {
		Null.nonNullArg(function, "function");
		try {
			return testX(c1, c2, a1, a2, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static <T1, T2> boolean testX(@Nullable CallContext c1, @Nullable CallContext c2, T1 a1, T2 a2, @Nonnull LBiPredicate<T1, T2> function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContexts.tryInit(last, c1); // try { c1?.start() ...
		final Object s2 = last = CallContexts.tryInit(last, c2); // try { c2?.start() ...

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;
		Object retval = null;
		if (primary == null) {
			try {
				retval = function.shovingTest(a1, a2);
			} catch (Throwable e) {
				primary = e;
			}
		}

		primary = CallContexts.tryFinish(primary, c2, s2); // } finally { c2?.end(...) }
		primary = CallContexts.tryFinish(primary, c1, s1); // } finally { c1?.end(...) }

		if (primary != null) {
			throw Handling.throwIt(primary);
		}
		return (boolean) retval;
	}

	static <T1, T2> CompletableFuture<Boolean> asyncTest(@Nonnull AsyncCallContext async, @Nullable CallContext c1, @Nullable CallContext c2, T1 a1, T2 a2, @Nonnull LBiPredicate<T1, T2> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<Boolean> future = new CompletableFuture<>();
		//noinspection unchecked,rawtypes
		return (CompletableFuture) async.call_(() -> {
			return LBiPredicate.testX(c1, c2, a1, a2, function);

		});
	}

	static <T1, T2> boolean nestingTest(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, T1 a1, T2 a2, @Nonnull LBiPredicate<T1, T2> function) {
		Null.nonNullArg(function, "function");
		try {
			return testX(c1, c2, c3, a1, a2, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static <T1, T2> boolean shovingTest(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, T1 a1, T2 a2, @Nonnull LBiPredicate<T1, T2> function) {
		Null.nonNullArg(function, "function");
		try {
			return testX(c1, c2, c3, a1, a2, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static <T1, T2> boolean testX(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, T1 a1, T2 a2, @Nonnull LBiPredicate<T1, T2> function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContexts.tryInit(last, c1); // try { c1?.start() ...
		final Object s2 = last = CallContexts.tryInit(last, c2); // try { c2?.start() ...
		final Object s3 = last = CallContexts.tryInit(last, c3); // try { c3?.start() ...

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;
		Object retval = null;
		if (primary == null) {
			try {
				retval = function.shovingTest(a1, a2);
			} catch (Throwable e) {
				primary = e;
			}
		}

		primary = CallContexts.tryFinish(primary, c3, s3); // } finally { c3?.end(...) }
		primary = CallContexts.tryFinish(primary, c2, s2); // } finally { c2?.end(...) }
		primary = CallContexts.tryFinish(primary, c1, s1); // } finally { c1?.end(...) }

		if (primary != null) {
			throw Handling.throwIt(primary);
		}
		return (boolean) retval;
	}

	static <T1, T2> CompletableFuture<Boolean> asyncTest(@Nonnull AsyncCallContext async, @Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, T1 a1, T2 a2, @Nonnull LBiPredicate<T1, T2> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<Boolean> future = new CompletableFuture<>();
		//noinspection unchecked,rawtypes
		return (CompletableFuture) async.call_(() -> {
			return LBiPredicate.testX(c1, c2, c3, a1, a2, function);

		});
	}

	static <T1, T2> boolean nestingTest(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, T1 a1, T2 a2, @Nonnull LBiPredicate<T1, T2> function) {
		Null.nonNullArg(function, "function");
		try {
			return testX(c1, c2, c3, c4, a1, a2, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static <T1, T2> boolean shovingTest(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, T1 a1, T2 a2, @Nonnull LBiPredicate<T1, T2> function) {
		Null.nonNullArg(function, "function");
		try {
			return testX(c1, c2, c3, c4, a1, a2, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static <T1, T2> boolean testX(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, T1 a1, T2 a2, @Nonnull LBiPredicate<T1, T2> function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContexts.tryInit(last, c1); // try { c1?.start() ...
		final Object s2 = last = CallContexts.tryInit(last, c2); // try { c2?.start() ...
		final Object s3 = last = CallContexts.tryInit(last, c3); // try { c3?.start() ...
		final Object s4 = last = CallContexts.tryInit(last, c4); // try { c4?.start() ...

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;
		Object retval = null;
		if (primary == null) {
			try {
				retval = function.shovingTest(a1, a2);
			} catch (Throwable e) {
				primary = e;
			}
		}

		primary = CallContexts.tryFinish(primary, c4, s4); // } finally { c4?.end(...) }
		primary = CallContexts.tryFinish(primary, c3, s3); // } finally { c3?.end(...) }
		primary = CallContexts.tryFinish(primary, c2, s2); // } finally { c2?.end(...) }
		primary = CallContexts.tryFinish(primary, c1, s1); // } finally { c1?.end(...) }

		if (primary != null) {
			throw Handling.throwIt(primary);
		}
		return (boolean) retval;
	}

	static <T1, T2> CompletableFuture<Boolean> asyncTest(@Nonnull AsyncCallContext async, @Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, T1 a1, T2 a2, @Nonnull LBiPredicate<T1, T2> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<Boolean> future = new CompletableFuture<>();
		//noinspection unchecked,rawtypes
		return (CompletableFuture) async.call_(() -> {
			return LBiPredicate.testX(c1, c2, c3, c4, a1, a2, function);

		});
	}

	static <T1, T2> CompletableFuture<Boolean> asyncTest(@Nonnull AsyncCallContext async, T1 a1, T2 a2, @Nonnull LBiPredicate<T1, T2> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<Boolean> future = new CompletableFuture<>();
		//noinspection unchecked,rawtypes
		return (CompletableFuture) async.call_(() -> {
			return function.testX(a1, a2);

		});
	}

	//</editor-fold>

	/** Creates function that always returns the same value. */
	static <T1, T2> LBiPredicate<T1, T2> constant(boolean r) {
		return (a1, a2) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2> LBiPredicate<T1, T2> biPred(final @Nonnull LBiPredicate<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S<T1, T2> implements LBiPredicate<T1, T2> {
		private LBiPredicate<T1, T2> target = null;
		@Override
		public boolean testX(T1 a1, T2 a2) throws Throwable {
			return target.testX(a1, a2);
		}
	}

	@Nonnull
	static <T1, T2> LBiPredicate<T1, T2> recursive(final @Nonnull LFunction<LBiPredicate<T1, T2>, LBiPredicate<T1, T2>> selfLambda) {
		final S<T1, T2> single = new S();
		LBiPredicate<T1, T2> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static <T1, T2> M<T1, T2> mementoOf(T1 a1, T2 a2, LBiPredicate<T1, T2> function) {
		var initialValue = function.test(a1, a2);
		return initializedMementoOf(initialValue, function);
	}

	public static <T1, T2> M<T1, T2> initializedMementoOf(boolean initialValue, LBiPredicate<T1, T2> function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static <T1, T2> M<T1, T2> deltaOf(T1 a1, T2 a2, LBiPredicate<T1, T2> function, LLogicalBinaryOperator deltaFunction) {
		var initialValue = function.test(a1, a2);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static <T1, T2> M<T1, T2> deltaOf(T1 a1, T2 a2, LBiPredicate<T1, T2> function) {
		var initialValue = function.test(a1, a2);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> x1 != x2);
	}

	public static <T1, T2> M<T1, T2> initializedDeltaOf(boolean initialValue, LBiPredicate<T1, T2> function, LLogicalBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.apply(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.apply(x1, x2));
	}

	public static <T1, T2> M<T1, T2> memento(boolean initialBaseValue, boolean initialValue, LBiPredicate<T1, T2> baseFunction, LLogicalTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LBiPredicate.M)
	 */
	@NotThreadSafe
	final class M<T1, T2> implements LBiPredicate<T1, T2> {

		private final LBiPredicate<T1, T2> baseFunction;
		private boolean lastBaseValue;
		private boolean lastValue;
		private final LLogicalTernaryOperator mementoFunction;

		private M(boolean lastBaseValue, boolean lastValue, LBiPredicate<T1, T2> baseFunction, LLogicalTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public boolean testX(T1 a1, T2 a2) throws Throwable {
			boolean x1 = lastBaseValue;
			boolean x2 = lastBaseValue = baseFunction.testX(a1, a2);

			return lastValue = mementoFunction.applyX(lastValue, x1, x2);
		}

		public boolean currentTest(T1 a1, T2 a2) {
			boolean x1 = lastBaseValue;
			boolean x2 = baseFunction.test(a1, a2);

			return mementoFunction.apply(lastValue, x1, x2);
		}

		public boolean lastValue() {
			return lastValue;
		}

		public boolean lastBaseValue() {
			return lastBaseValue;
		}

		public boolean currentBaseValue(T1 a1, T2 a2) {
			return baseFunction.test(a1, a2);
		}
	}

	// </editor-fold>

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

	@Nonnull
	static <T1, T2> LBiPredicate<T1, T2> not(@Nonnull LBiPredicate<T1, T2> pred) {
		Null.nonNullArg(pred, "pred");
		return pred.negate();
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

	@Nonnull
	public static <T1, T2> LBiPredicate<T1, T2> and(@Nonnull LBiPredicate<? super T1, ? super T2>... predicates) {
		Null.nonNullArg(predicates, "predicates");
		return (a1, a2) -> !any(false, a1, a2, predicates);
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

	@Nonnull
	public static <T1, T2> LBiPredicate<T1, T2> or(@Nonnull LBiPredicate<? super T1, ? super T2>... predicates) {
		Null.nonNullArg(predicates, "predicates");
		return (a1, a2) -> any(true, a1, a2, predicates);
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

	public static <T1, T2> boolean any(boolean expected, T1 a1, T2 a2, @Nonnull Collection<? extends LBiPredicate<? super T1, ? super T2>> predicates) {
		return any(expected, a1, a2, Null.nonNullArg(predicates, "predicates").iterator());
	}

	public static <T1, T2> boolean any(boolean expected, T1 a1, T2 a2, @Nonnull Stream<? extends LBiPredicate<? super T1, ? super T2>> predicates) {
		return any(expected, a1, a2, Null.nonNullArg(predicates, "predicates").iterator());
	}

	public static <T1, T2> boolean any(boolean expected, T1 a1, T2 a2, @Nonnull Iterator<? extends LBiPredicate<? super T1, ? super T2>> predicates) {
		Null.nonNullArg(predicates, "predicates");
		for (var it = predicates; it.hasNext();) {
			var pred = it.next();
			Null.nonNullArg(pred, "pred");
			if (expected == pred.test(a1, a2)) {
				return true;
			}
		}
		return false;
	}

	public static <T1, T2> boolean any(boolean expected, T1 a1, T2 a2, @Nonnull LBiPredicate<? super T1, ? super T2>... predicates) {
		Null.nonNullArg(predicates, "predicates");
		for (var pred : predicates) {
			Null.nonNullArg(pred, "pred");
			if (expected == pred.test(a1, a2)) {
				return true;
			}
		}
		return false;
	}

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiPredicate<V1, V2> compose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.test(before1.apply(v1), before2.apply(v2));
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

	default LBiPredicate<T1, T2> shoving() {

		return new LBiPredicate<T1, T2>() {

			public boolean test(T1 a1, T2 a2) {
				try {
					return this.testX(a1, a2);
				} catch (Throwable e) { // NOSONAR
					Handling.handleErrors(e);
					throw Handling.throwIt(e);
				}
			}

			public boolean testX(T1 a1, T2 a2) throws Throwable {
				return LBiPredicate.this.testX(a1, a2);
			}

		};
	}

	// <editor-fold desc="variant conversions">

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

}
