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
 * Non-throwing functional interface (lambda) LTriPredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 3): T1 a1,T2 a2,T3 a3
 *
 * Co-domain: boolean
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LTriPredicate<T1, T2, T3> extends MetaPredicate, MetaInterface.NonThrowing, Codomain<aBool>, Domain3<a<T1>, a<T2>, a<T3>> { //NOSONAR

	String DESCRIPTION = "LTriPredicate: boolean test(T1 a1,T2 a2,T3 a3)";

	default boolean test(T1 a1, T2 a2, T3 a3) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call test(T1 a1,T2 a2,T3 a3)
	 */
	boolean testX(T1 a1, T2 a2, T3 a3) throws Throwable;

	default boolean tupleTest(LTriple<T1, T2, T3> args) {
		return test(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions according to the instructions. */
	default boolean handlingTest(T1 a1, T2 a2, T3 a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LTriPredicate<T1, T2, T3> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> handlingTest(a1, a2, a3, handling);
	}

	default boolean test(T1 a1, T2 a2, T3 a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default boolean test(T1 a1, T2 a2, T3 a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default boolean test(T1 a1, T2 a2, T3 a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default boolean test(T1 a1, T2 a2, T3 a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LTriPredicate<T1, T2, T3> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2, a3) -> test(a1, a2, a3, factory, newMessage);
	}

	default LTriPredicate<T1, T2, T3> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2, a3) -> test(a1, a2, a3, factory, newMessage, param1);
	}

	default LTriPredicate<T1, T2, T3> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2, a3) -> test(a1, a2, a3, factory, newMessage, param1, param1);
	}

	default LTriPredicate<T1, T2, T3> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2, a3) -> test(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	default boolean test(T1 a1, T2 a2, T3 a3, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LTriPredicate<T1, T2, T3> trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2, a3) -> test(a1, a2, a3, factory);
	}

	default boolean testThen(T1 a1, T2 a2, T3 a3, @Nonnull LPredicate<Throwable> handler) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			Handling.handleErrors(e);
			return handler.test(e);
		}
	}

	default LTriPredicate<T1, T2, T3> tryingThen(@Nonnull LPredicate<Throwable> handler) {
		return (a1, a2, a3) -> testThen(a1, a2, a3, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingTest(T1 a1, T2 a2, T3 a3) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default boolean shovingTest(T1 a1, T2 a2, T3 a3) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { //NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T1, T2, T3> boolean shovingTest(T1 a1, T2 a2, T3 a3, LTriPredicate<T1, T2, T3> func) {
		Null.nonNullArg(func, "func");
		return func.shovingTest(a1, a2, a3);
	}

	static <T1, T2, T3> boolean handlingTest(T1 a1, T2 a2, T3 a3, LTriPredicate<T1, T2, T3> func, HandlingInstructions<Throwable, RuntimeException> handling) { //<-
		Null.nonNullArg(func, "func");
		return func.handlingTest(a1, a2, a3, handling);
	}

	static <T1, T2, T3> boolean tryTest(T1 a1, T2 a2, T3 a3, LTriPredicate<T1, T2, T3> func) {
		Null.nonNullArg(func, "func");
		return func.nestingTest(a1, a2, a3);
	}

	static <T1, T2, T3> boolean tryTest(T1 a1, T2 a2, T3 a3, LTriPredicate<T1, T2, T3> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, factory, newMessage);
	}

	static <T1, T2, T3> boolean tryTest(T1 a1, T2 a2, T3 a3, LTriPredicate<T1, T2, T3> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, factory, newMessage, param1);
	}

	static <T1, T2, T3> boolean tryTest(T1 a1, T2 a2, T3 a3, LTriPredicate<T1, T2, T3> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, factory, newMessage, param1, param2);
	}

	static <T1, T2, T3> boolean tryTest(T1 a1, T2 a2, T3 a3, LTriPredicate<T1, T2, T3> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	static <T1, T2, T3> boolean tryTest(T1 a1, T2 a2, T3 a3, LTriPredicate<T1, T2, T3> func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, factory);
	}

	static <T1, T2, T3> boolean tryTestThen(T1 a1, T2 a2, T3 a3, LTriPredicate<T1, T2, T3> func, @Nonnull LPredicate<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.testThen(a1, a2, a3, handler);
	}

	default boolean doIf(T1 a1, T2 a2, T3 a3, LAction action) {
		Null.nonNullArg(action, "action");
		if (test(a1, a2, a3)) {
			action.execute();
			return true;
		} else {
			return false;
		}
	}

	static <T1, T2, T3> boolean doIf(T1 a1, T2 a2, T3 a3, @Nonnull LTriPredicate<T1, T2, T3> predicate, @Nonnull LAction action) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.doIf(a1, a2, a3, action);
	}

	static <T1, T2, T3> boolean doIf(T1 a1, T2 a2, T3 a3, @Nonnull LTriPredicate<T1, T2, T3> predicate, @Nonnull LTriConsumer<? super T1, ? super T2, ? super T3> consumer) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.doIf(a1, a2, a3, consumer);
	}

	default boolean doIf(T1 a1, T2 a2, T3 a3, @Nonnull LTriConsumer<? super T1, ? super T2, ? super T3> consumer) {
		Null.nonNullArg(consumer, "consumer");
		if (test(a1, a2, a3)) {
			consumer.accept(a1, a2, a3);
			return true;
		} else {
			return false;
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullTest(T1 a1, T2 a2, T3 a3) {
		return test(a1, a2, a3);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(T1 a1, T2 a2, T3 a3) {
		return test(a1, a2, a3);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LTriPredicate.DESCRIPTION;
	}

	default <V> boolean doIf(V a1, T1 a2, T2 a3, T3 a4, @Nonnull LQuadConsumer<V, ? super T1, ? super T2, ? super T3> consumer) {
		Null.nonNullArg(consumer, "consumer");
		if (test(a2, a3, a4)) {
			consumer.accept(a1, a2, a3, a4);
			return true;
		} else {
			return false;
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, T3> void fromTo(int min_i, int max_i, T1 a1, T2 a2, T3 a3, @Nonnull LTriPredicate<T1, T2, T3> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.test(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.test(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, T3> void fromTill(int min_i, int max_i, T1 a1, T2 a2, T3 a3, @Nonnull LTriPredicate<T1, T2, T3> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.test(a1, a2, a3);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.test(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2, T3> void times(int max_i, T1 a1, T2 a2, T3 a3, @Nonnull LTriPredicate<T1, T2, T3> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, a3, func);
	}

	/** Extract and apply function. */
	public static <M, K, V, T2, T3> boolean from(@Nonnull M container, LBiFunction<M, K, V> extractor, K key, T2 a2, T3 a3, @Nonnull LTriPredicate<V, T2, T3> function) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.test(value, a2, a3);
		}

		return false;
	}

	default LBiPredicate<T2, T3> _with(T1 a1) {
		return (a2, a3) -> test(a1, a2, a3);
	}

	default LBiPredicate<T1, T2> with(T3 a3) {
		return (a1, a2) -> test(a1, a2, a3);
	}

	default LPredicate<T3> _with(T1 a1, T2 a2) {
		return a3 -> test(a1, a2, a3);
	}

	default LPredicate<T1> with(T2 a2, T3 a3) {
		return a1 -> test(a1, a2, a3);
	}

	/**  */
	public static <T1, T2, T3> LTriPredicate<T1, T2, T3> uncurry(@Nonnull LFunction<T1, LFunction<T2, LPredicate<T3>>> func) {
		Null.nonNullArg(func, "func");
		return (T1 a1, T2 a2, T3 a3) -> func.apply(a1).apply(a2).test(a3);
	}

	/** Change function to one that ignores output. */
	default LTriConsumer<T1, T2, T3> toConsumer() {
		return this::test;
	}

	/** Calls domain consumer before main function. */
	default LTriPredicate<T1, T2, T3> beforeDo(@Nonnull LTriConsumer<T1, T2, T3> before) {
		Null.nonNullArg(before, "before");
		return (T1 a1, T2 a2, T3 a3) -> {
			before.accept(a1, a2, a3);
			return test(a1, a2, a3);
		};
	}

	/** Calls codomain consumer after main function. */
	default LTriPredicate<T1, T2, T3> afterDo(@Nonnull LBoolConsumer after) {
		Null.nonNullArg(after, "after");
		return (T1 a1, T2 a2, T3 a3) -> {
			final boolean retval = test(a1, a2, a3);
			after.accept(retval);
			return retval;
		};
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, T3, X extends Throwable> T1 throwIf(T1 a1, T2 a2, T3 a3, @Nonnull LTriPredicate<? super T1, ? super T2, ? super T3> pred, @Nonnull ExMF<X> factory,
			@Nonnull LTriFunction<? super T1, ? super T2, ? super T3, ? extends String> msgFunc) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, msgFunc.apply(a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, T3, X extends Throwable> T1 throwIf(T1 a1, T2 a2, T3 a3, @Nonnull LTriPredicate<? super T1, ? super T2, ? super T3> pred, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(msg, a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, T3, X extends Throwable> T1 throwIf(T1 a1, T2 a2, T3 a3, @Nonnull LTriPredicate<? super T1, ? super T2, ? super T3> pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, T3, X extends Throwable> T1 throwIf(T1 a1, T2 a2, T3 a3, @Nonnull LTriPredicate<? super T1, ? super T2, ? super T3> pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2)
			throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, T3, X extends Throwable> T1 throwIf(T1 a1, T2 a2, T3 a3, @Nonnull LTriPredicate<? super T1, ? super T2, ? super T3> pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2,
			@Nullable Object param3) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2, param3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, T3, X extends Throwable> T1 throwIfNot(T1 a1, T2 a2, T3 a3, @Nonnull LTriPredicate<? super T1, ? super T2, ? super T3> pred, @Nonnull ExMF<X> factory,
			@Nonnull LTriFunction<? super T1, ? super T2, ? super T3, ? extends String> msgFunc) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, msgFunc.apply(a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, T3, X extends Throwable> T1 throwIfNot(T1 a1, T2 a2, T3 a3, @Nonnull LTriPredicate<? super T1, ? super T2, ? super T3> pred, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(msg, a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, T3, X extends Throwable> T1 throwIfNot(T1 a1, T2 a2, T3 a3, @Nonnull LTriPredicate<? super T1, ? super T2, ? super T3> pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, T3, X extends Throwable> T1 throwIfNot(T1 a1, T2 a2, T3 a3, @Nonnull LTriPredicate<? super T1, ? super T2, ? super T3> pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2)
			throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, T3, X extends Throwable> T1 throwIfNot(T1 a1, T2 a2, T3 a3, @Nonnull LTriPredicate<? super T1, ? super T2, ? super T3> pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2,
			@Nullable Object param3) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2, param3));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, T3, X extends Throwable> T1 throwIf(T1 a1, T2 a2, T3 a3, @Nonnull LTriPredicate<? super T1, ? super T2, ? super T3> pred, @Nonnull ExF<X> noArgFactory) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(noArgFactory, "noArgFactory");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(noArgFactory);
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, T3, X extends Throwable> T1 throwIfNot(T1 a1, T2 a2, T3 a3, @Nonnull LTriPredicate<? super T1, ? super T2, ? super T3> pred, @Nonnull ExF<X> noArgFactory) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(noArgFactory, "noArgFactory");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(noArgFactory);
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, T3, X extends Throwable> T1 throwIf(T1 a1, @Nonnull LTriPredicate<? super T1, ? super T2, ? super T3> pred, T2 a2, T3 a3, @Nonnull ExMF<X> factory,
			@Nonnull LTriFunction<? super T1, ? super T2, ? super T3, ? extends String> msgFunc) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, msgFunc.apply(a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, T3, X extends Throwable> T1 throwIf(T1 a1, @Nonnull LTriPredicate<? super T1, ? super T2, ? super T3> pred, T2 a2, T3 a3, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(msg, a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, T3, X extends Throwable> T1 throwIf(T1 a1, @Nonnull LTriPredicate<? super T1, ? super T2, ? super T3> pred, T2 a2, T3 a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, T3, X extends Throwable> T1 throwIf(T1 a1, @Nonnull LTriPredicate<? super T1, ? super T2, ? super T3> pred, T2 a2, T3 a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2)
			throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, T3, X extends Throwable> T1 throwIf(T1 a1, @Nonnull LTriPredicate<? super T1, ? super T2, ? super T3> pred, T2 a2, T3 a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2,
			@Nullable Object param3) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2, param3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, T3, X extends Throwable> T1 throwIfNot(T1 a1, @Nonnull LTriPredicate<? super T1, ? super T2, ? super T3> pred, T2 a2, T3 a3, @Nonnull ExMF<X> factory,
			@Nonnull LTriFunction<? super T1, ? super T2, ? super T3, ? extends String> msgFunc) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, msgFunc.apply(a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, T3, X extends Throwable> T1 throwIfNot(T1 a1, @Nonnull LTriPredicate<? super T1, ? super T2, ? super T3> pred, T2 a2, T3 a3, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(msg, a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, T3, X extends Throwable> T1 throwIfNot(T1 a1, @Nonnull LTriPredicate<? super T1, ? super T2, ? super T3> pred, T2 a2, T3 a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, T3, X extends Throwable> T1 throwIfNot(T1 a1, @Nonnull LTriPredicate<? super T1, ? super T2, ? super T3> pred, T2 a2, T3 a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2)
			throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, T3, X extends Throwable> T1 throwIfNot(T1 a1, @Nonnull LTriPredicate<? super T1, ? super T2, ? super T3> pred, T2 a2, T3 a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2,
			@Nullable Object param3) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2, param3));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, T3, X extends Throwable> T1 throwIf(T1 a1, @Nonnull LTriPredicate<? super T1, ? super T2, ? super T3> pred, T2 a2, T3 a3, @Nonnull ExF<X> noArgFactory) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(noArgFactory, "noArgFactory");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(noArgFactory);
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, T3, X extends Throwable> T1 throwIfNot(T1 a1, @Nonnull LTriPredicate<? super T1, ? super T2, ? super T3> pred, T2 a2, T3 a3, @Nonnull ExF<X> noArgFactory) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(noArgFactory, "noArgFactory");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(noArgFactory);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, T3, X extends Throwable> T1 throwIfNotEx(T1 a1, T2 a2, T3 a3, @Nonnull LTriFunction<? super T1, ? super T2, ? super T3, ? extends String> specialPredicate, @Nonnull ExMF<X> factory) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		var m = specialPredicate.apply(a1, a2, a3);
		if (m != null) {
			throw Handling.create(factory, m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, T3, X extends Throwable> T1 throwIfNotEx(T1 a1, T2 a2, T3 a3, @Nonnull LTriFunction<? super T1, ? super T2, ? super T3, ? extends String> specialPredicate, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		var m = specialPredicate.apply(a1, a2, a3);
		if (m != null) {
			throw Handling.create(factory, String.format(msg, a1, a2, a3) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, T3, X extends Throwable> T1 throwIfNotEx(T1 a1, T2 a2, T3 a3, @Nonnull LTriFunction<? super T1, ? super T2, ? super T3, ? extends String> specialPredicate, @Nonnull ExMF<X> factory, @Nonnull String message,
			@Nullable Object param1) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		var m = specialPredicate.apply(a1, a2, a3);
		if (m != null) {
			throw Handling.create(factory, String.format(message, param1) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, T3, X extends Throwable> T1 throwIfNotEx(T1 a1, T2 a2, T3 a3, @Nonnull LTriFunction<? super T1, ? super T2, ? super T3, ? extends String> specialPredicate, @Nonnull ExMF<X> factory, @Nonnull String message,
			@Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		var m = specialPredicate.apply(a1, a2, a3);
		if (m != null) {
			throw Handling.create(factory, String.format(message, param1, param2) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, T3, X extends Throwable> T1 throwIfNotEx(T1 a1, T2 a2, T3 a3, @Nonnull LTriFunction<? super T1, ? super T2, ? super T3, ? extends String> specialPredicate, @Nonnull ExMF<X> factory, @Nonnull String message,
			@Nullable Object param1, @Nullable Object param2, @Nullable Object param3) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		var m = specialPredicate.apply(a1, a2, a3);
		if (m != null) {
			throw Handling.create(factory, String.format(message, param1, param2, param3) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, T3, X extends Throwable> T1 throwIfNotEx(T1 a1, @Nonnull LTriFunction<? super T1, ? super T2, ? super T3, ? extends String> specialPredicate, T2 a2, T3 a3, @Nonnull ExMF<X> factory) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		var m = specialPredicate.apply(a1, a2, a3);
		if (m != null) {
			throw Handling.create(factory, m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, T3, X extends Throwable> T1 throwIfNotEx(T1 a1, @Nonnull LTriFunction<? super T1, ? super T2, ? super T3, ? extends String> specialPredicate, T2 a2, T3 a3, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		var m = specialPredicate.apply(a1, a2, a3);
		if (m != null) {
			throw Handling.create(factory, String.format(msg, a1, a2, a3) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, T3, X extends Throwable> T1 throwIfNotEx(T1 a1, @Nonnull LTriFunction<? super T1, ? super T2, ? super T3, ? extends String> specialPredicate, T2 a2, T3 a3, @Nonnull ExMF<X> factory, @Nonnull String message,
			@Nullable Object param1) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		var m = specialPredicate.apply(a1, a2, a3);
		if (m != null) {
			throw Handling.create(factory, String.format(message, param1) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, T3, X extends Throwable> T1 throwIfNotEx(T1 a1, @Nonnull LTriFunction<? super T1, ? super T2, ? super T3, ? extends String> specialPredicate, T2 a2, T3 a3, @Nonnull ExMF<X> factory, @Nonnull String message,
			@Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		var m = specialPredicate.apply(a1, a2, a3);
		if (m != null) {
			throw Handling.create(factory, String.format(message, param1, param2) + ' ' + m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, T3, X extends Throwable> T1 throwIfNotEx(T1 a1, @Nonnull LTriFunction<? super T1, ? super T2, ? super T3, ? extends String> specialPredicate, T2 a2, T3 a3, @Nonnull ExMF<X> factory, @Nonnull String message,
			@Nullable Object param1, @Nullable Object param2, @Nullable Object param3) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		var m = specialPredicate.apply(a1, a2, a3);
		if (m != null) {
			throw Handling.create(factory, String.format(message, param1, param2, param3) + ' ' + m);
		}
		return a1;
	}

	//<editor-fold desc="CallContext">

	@Nonnull
	static <T1, T2, T3> LTriPredicate<T1, T2, T3> triPred(@Nullable CallContext c1, final @Nonnull LTriPredicate<T1, T2, T3> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.withCC(c1);
	}

	default @Nonnull LTriPredicate<T1, T2, T3> withCC(@Nullable CallContext c1) {
		return (a1, a2, a3) -> LTriPredicate.shovingTest(c1, a1, a2, a3, this);
	}

	static <T1, T2, T3> boolean nestingTest(@Nullable CallContext c1, T1 a1, T2 a2, T3 a3, @Nonnull LTriPredicate<T1, T2, T3> function) {
		Null.nonNullArg(function, "function");
		try {
			return testX(c1, a1, a2, a3, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static <T1, T2, T3> boolean shovingTest(@Nullable CallContext c1, T1 a1, T2 a2, T3 a3, @Nonnull LTriPredicate<T1, T2, T3> function) {
		Null.nonNullArg(function, "function");
		try {
			return testX(c1, a1, a2, a3, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static <T1, T2, T3> boolean testX(@Nullable CallContext c1, T1 a1, T2 a2, T3 a3, @Nonnull LTriPredicate<T1, T2, T3> function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContexts.tryInit(last, c1); // try { c1?.start() ...

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;
		Object retval = null;
		if (primary == null) {
			try {
				retval = function.shovingTest(a1, a2, a3);
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

	static <T1, T2, T3> CompletableFuture<Boolean> asyncTest(@Nonnull AsyncCallContext async, @Nullable CallContext c1, T1 a1, T2 a2, T3 a3, @Nonnull LTriPredicate<T1, T2, T3> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<Boolean> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					var v = LTriPredicate.testX(c1, a1, a2, a3, function);
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

	static <T1, T2, T3> boolean nestingTest(@Nullable CallContext c1, @Nullable CallContext c2, T1 a1, T2 a2, T3 a3, @Nonnull LTriPredicate<T1, T2, T3> function) {
		Null.nonNullArg(function, "function");
		try {
			return testX(c1, c2, a1, a2, a3, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static <T1, T2, T3> boolean shovingTest(@Nullable CallContext c1, @Nullable CallContext c2, T1 a1, T2 a2, T3 a3, @Nonnull LTriPredicate<T1, T2, T3> function) {
		Null.nonNullArg(function, "function");
		try {
			return testX(c1, c2, a1, a2, a3, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static <T1, T2, T3> boolean testX(@Nullable CallContext c1, @Nullable CallContext c2, T1 a1, T2 a2, T3 a3, @Nonnull LTriPredicate<T1, T2, T3> function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContexts.tryInit(last, c1); // try { c1?.start() ...
		final Object s2 = last = CallContexts.tryInit(last, c2); // try { c2?.start() ...

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;
		Object retval = null;
		if (primary == null) {
			try {
				retval = function.shovingTest(a1, a2, a3);
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

	static <T1, T2, T3> CompletableFuture<Boolean> asyncTest(@Nonnull AsyncCallContext async, @Nullable CallContext c1, @Nullable CallContext c2, T1 a1, T2 a2, T3 a3, @Nonnull LTriPredicate<T1, T2, T3> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<Boolean> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					var v = LTriPredicate.testX(c1, c2, a1, a2, a3, function);
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

	static <T1, T2, T3> boolean nestingTest(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, T1 a1, T2 a2, T3 a3, @Nonnull LTriPredicate<T1, T2, T3> function) {
		Null.nonNullArg(function, "function");
		try {
			return testX(c1, c2, c3, a1, a2, a3, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static <T1, T2, T3> boolean shovingTest(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, T1 a1, T2 a2, T3 a3, @Nonnull LTriPredicate<T1, T2, T3> function) {
		Null.nonNullArg(function, "function");
		try {
			return testX(c1, c2, c3, a1, a2, a3, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static <T1, T2, T3> boolean testX(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, T1 a1, T2 a2, T3 a3, @Nonnull LTriPredicate<T1, T2, T3> function) throws Throwable {
		Null.nonNullArg(function, "function");

		Object last = null;
		final Object s1 = last = CallContexts.tryInit(last, c1); // try { c1?.start() ...
		final Object s2 = last = CallContexts.tryInit(last, c2); // try { c2?.start() ...
		final Object s3 = last = CallContexts.tryInit(last, c3); // try { c3?.start() ...

		Throwable primary = (last instanceof Throwable) ? (Throwable) last : null;
		Object retval = null;
		if (primary == null) {
			try {
				retval = function.shovingTest(a1, a2, a3);
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

	static <T1, T2, T3> CompletableFuture<Boolean> asyncTest(@Nonnull AsyncCallContext async, @Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, T1 a1, T2 a2, T3 a3, @Nonnull LTriPredicate<T1, T2, T3> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<Boolean> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					var v = LTriPredicate.testX(c1, c2, c3, a1, a2, a3, function);
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

	static <T1, T2, T3> boolean nestingTest(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, T1 a1, T2 a2, T3 a3, @Nonnull LTriPredicate<T1, T2, T3> function) {
		Null.nonNullArg(function, "function");
		try {
			return testX(c1, c2, c3, c4, a1, a2, a3, function);
		} catch (Throwable e) {
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	static <T1, T2, T3> boolean shovingTest(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, T1 a1, T2 a2, T3 a3, @Nonnull LTriPredicate<T1, T2, T3> function) {
		Null.nonNullArg(function, "function");
		try {
			return testX(c1, c2, c3, c4, a1, a2, a3, function);
		} catch (Throwable e) {
			throw Handling.throwIt(e);
		}
	}

	static <T1, T2, T3> boolean testX(@Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, T1 a1, T2 a2, T3 a3, @Nonnull LTriPredicate<T1, T2, T3> function) throws Throwable {
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
				retval = function.shovingTest(a1, a2, a3);
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

	static <T1, T2, T3> CompletableFuture<Boolean> asyncTest(@Nonnull AsyncCallContext async, @Nullable CallContext c1, @Nullable CallContext c2, @Nullable CallContext c3, @Nullable CallContext c4, T1 a1, T2 a2, T3 a3,
			@Nonnull LTriPredicate<T1, T2, T3> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<Boolean> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					var v = LTriPredicate.testX(c1, c2, c3, c4, a1, a2, a3, function);
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

	static <T1, T2, T3> CompletableFuture<Boolean> asyncTest(@Nonnull AsyncCallContext async, T1 a1, T2 a2, T3 a3, @Nonnull LTriPredicate<T1, T2, T3> function) {
		Null.nonNullArg(async, "async");
		Null.nonNullArg(function, "function");
		CompletableFuture<Boolean> future = new CompletableFuture<>();
		try {
			async.call(() -> {
				try {
					var v = function.testX(a1, a2, a3);
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

	//</editor-fold>

	/** Creates function that always returns the same value. */
	static <T1, T2, T3> LTriPredicate<T1, T2, T3> constant(boolean r) {
		return (a1, a2, a3) -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2, T3> LTriPredicate<T1, T2, T3> triPred(final @Nonnull LTriPredicate<T1, T2, T3> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// <editor-fold desc="recursive">

	final class S<T1, T2, T3> implements LTriPredicate<T1, T2, T3> {
		private LTriPredicate<T1, T2, T3> target = null;
		@Override
		public boolean testX(T1 a1, T2 a2, T3 a3) throws Throwable {
			return target.testX(a1, a2, a3);
		}
	}

	@Nonnull
	static <T1, T2, T3> LTriPredicate<T1, T2, T3> recursive(final @Nonnull LFunction<LTriPredicate<T1, T2, T3>, LTriPredicate<T1, T2, T3>> selfLambda) {
		final S<T1, T2, T3> single = new S();
		LTriPredicate<T1, T2, T3> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	// </editor-fold>

	// <editor-fold desc="memento">

	public static <T1, T2, T3> M<T1, T2, T3> mementoOf(T1 a1, T2 a2, T3 a3, LTriPredicate<T1, T2, T3> function) {
		var initialValue = function.test(a1, a2, a3);
		return initializedMementoOf(initialValue, function);
	}

	public static <T1, T2, T3> M<T1, T2, T3> initializedMementoOf(boolean initialValue, LTriPredicate<T1, T2, T3> function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static <T1, T2, T3> M<T1, T2, T3> deltaOf(T1 a1, T2 a2, T3 a3, LTriPredicate<T1, T2, T3> function, LLogicalBinaryOperator deltaFunction) {
		var initialValue = function.test(a1, a2, a3);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static <T1, T2, T3> M<T1, T2, T3> deltaOf(T1 a1, T2 a2, T3 a3, LTriPredicate<T1, T2, T3> function) {
		var initialValue = function.test(a1, a2, a3);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> x1 != x2);
	}

	public static <T1, T2, T3> M<T1, T2, T3> initializedDeltaOf(boolean initialValue, LTriPredicate<T1, T2, T3> function, LLogicalBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.apply(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.apply(x1, x2));
	}

	public static <T1, T2, T3> M<T1, T2, T3> memento(boolean initialBaseValue, boolean initialValue, LTriPredicate<T1, T2, T3> baseFunction, LLogicalTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LTriPredicate.M)
	 */
	@NotThreadSafe
	final class M<T1, T2, T3> implements LTriPredicate<T1, T2, T3> {

		private final LTriPredicate<T1, T2, T3> baseFunction;
		private boolean lastBaseValue;
		private boolean lastValue;
		private final LLogicalTernaryOperator mementoFunction;

		private M(boolean lastBaseValue, boolean lastValue, LTriPredicate<T1, T2, T3> baseFunction, LLogicalTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public boolean testX(T1 a1, T2 a2, T3 a3) throws Throwable {
			boolean x1 = lastBaseValue;
			boolean x2 = lastBaseValue = baseFunction.testX(a1, a2, a3);

			return lastValue = mementoFunction.applyX(lastValue, x1, x2);
		}

		public boolean currentTest(T1 a1, T2 a2, T3 a3) {
			boolean x1 = lastBaseValue;
			boolean x2 = baseFunction.test(a1, a2, a3);

			return mementoFunction.apply(lastValue, x1, x2);
		}

		public boolean lastValue() {
			return lastValue;
		}

		public boolean lastBaseValue() {
			return lastBaseValue;
		}

		public boolean currentBaseValue(T1 a1, T2 a2, T3 a3) {
			return baseFunction.test(a1, a2, a3);
		}
	}

	// </editor-fold>

	@Nonnull
	static <T1, T2, T3> LTriPredicate<T1, T2, T3> triPredThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T1, T2, T3> LTriPredicate<T1, T2, T3> triPredThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce(message);
		};
	}

	static <T1, T2, T3> boolean call(T1 a1, T2 a2, T3 a3, final @Nonnull LTriPredicate<T1, T2, T3> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.test(a1, a2, a3);
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
	default LTriPredicate<T1, T2, T3> negate() {
		return (a1, a2, a3) -> !test(a1, a2, a3);
	}

	@Nonnull
	static <T1, T2, T3> LTriPredicate<T1, T2, T3> not(@Nonnull LTriPredicate<T1, T2, T3> pred) {
		Null.nonNullArg(pred, "pred");
		return pred.negate();
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LTriPredicate<T1, T2, T3> and(@Nonnull LTriPredicate<? super T1, ? super T2, ? super T3> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> test(a1, a2, a3) && other.test(a1, a2, a3);
	}

	@Nonnull
	public static <T1, T2, T3> LTriPredicate<T1, T2, T3> and(@Nonnull LTriPredicate<? super T1, ? super T2, ? super T3>... predicates) {
		Null.nonNullArg(predicates, "predicates");
		return (a1, a2, a3) -> !any(false, a1, a2, a3, predicates);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LTriPredicate<T1, T2, T3> or(@Nonnull LTriPredicate<? super T1, ? super T2, ? super T3> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> test(a1, a2, a3) || other.test(a1, a2, a3);
	}

	@Nonnull
	public static <T1, T2, T3> LTriPredicate<T1, T2, T3> or(@Nonnull LTriPredicate<? super T1, ? super T2, ? super T3>... predicates) {
		Null.nonNullArg(predicates, "predicates");
		return (a1, a2, a3) -> any(true, a1, a2, a3, predicates);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LTriPredicate<T1, T2, T3> xor(@Nonnull LTriPredicate<? super T1, ? super T2, ? super T3> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> test(a1, a2, a3) ^ other.test(a1, a2, a3);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static <T1, T2, T3> LTriPredicate<T1, T2, T3> isEqual(T1 v1, T2 v2, T3 v3) {
		return (a1, a2, a3) -> (a1 == null ? v1 == null : a1.equals(v1)) && (a2 == null ? v2 == null : a2.equals(v2)) && (a3 == null ? v3 == null : a3.equals(v3));
	}

	// </editor-fold>

	public static <T1, T2, T3> boolean any(boolean expected, T1 a1, T2 a2, T3 a3, @Nonnull Collection<? extends LTriPredicate<? super T1, ? super T2, ? super T3>> predicates) {
		return any(expected, a1, a2, a3, Null.nonNullArg(predicates, "predicates").iterator());
	}

	public static <T1, T2, T3> boolean any(boolean expected, T1 a1, T2 a2, T3 a3, @Nonnull Stream<? extends LTriPredicate<? super T1, ? super T2, ? super T3>> predicates) {
		return any(expected, a1, a2, a3, Null.nonNullArg(predicates, "predicates").iterator());
	}

	public static <T1, T2, T3> boolean any(boolean expected, T1 a1, T2 a2, T3 a3, @Nonnull Iterator<? extends LTriPredicate<? super T1, ? super T2, ? super T3>> predicates) {
		Null.nonNullArg(predicates, "predicates");
		for (var it = predicates; it.hasNext();) {
			var pred = it.next();
			Null.nonNullArg(pred, "pred");
			if (expected == pred.test(a1, a2, a3)) {
				return true;
			}
		}
		return false;
	}

	public static <T1, T2, T3> boolean any(boolean expected, T1 a1, T2 a2, T3 a3, @Nonnull LTriPredicate<? super T1, ? super T2, ? super T3>... predicates) {
		Null.nonNullArg(predicates, "predicates");
		for (var pred : predicates) {
			Null.nonNullArg(pred, "pred");
			if (expected == pred.test(a1, a2, a3)) {
				return true;
			}
		}
		return false;
	}

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriPredicate<V1, V2, V3> compose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LFunction<? super V3, ? extends T3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.test(before1.apply(v1), before2.apply(v2), before3.apply(v3));
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LTriFunction<T1, T2, T3, V> boolToTriFunc(@Nonnull LBoolFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.apply(this.test(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToIntTriFunction<T1, T2, T3> boolToToIntTriFunc(@Nonnull LBoolToIntFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.applyAsInt(this.test(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LTriPredicate<T1, T2, T3> boolToTriPred(@Nonnull LLogicalOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.apply(this.test(a1, a2, a3));
	}

	// </editor-fold>

	default LTriPredicate<T1, T2, T3> shoving() {

		return new LTriPredicate<T1, T2, T3>() {

			public boolean test(T1 a1, T2 a2, T3 a3) {
				try {
					return this.testX(a1, a2, a3);
				} catch (Throwable e) { // NOSONAR
					Handling.handleErrors(e);
					throw Handling.throwIt(e);
				}
			}

			public boolean testX(T1 a1, T2 a2, T3 a3) throws Throwable {
				return LTriPredicate.this.testX(a1, a2, a3);
			}

		};
	}

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	// >>> LTriPredicate<T1,T2,T3>

	/** Returns TRUE. */
	public static <T1, T2, T3> boolean alwaysTrue(T1 a1, T2 a2, T3 a3) {
		return true;
	}

	/** Returns FALSE. */
	public static <T1, T2, T3> boolean alwaysFalse(T1 a1, T2 a2, T3 a3) {
		return false;
	}

}
