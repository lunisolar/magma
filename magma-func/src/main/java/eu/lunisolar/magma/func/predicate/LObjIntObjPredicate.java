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
 * Non-throwing functional interface (lambda) LObjIntObjPredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 3): T1 a1,int a2,T2 a3
 *
 * Co-domain: boolean
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LObjIntObjPredicate<T1, T2> extends MetaPredicate, MetaInterface.NonThrowing, Codomain<aBool>, Domain3<a<T1>, aInt, a<T2>> { // NOSONAR

	String DESCRIPTION = "LObjIntObjPredicate: boolean test(T1 a1,int a2,T2 a3)";

	// boolean test(T1 a1,int a2,T2 a3) ;
	default boolean test(T1 a1, int a2, T2 a3) {
		// return nestingTest(a1,a2,a3);
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call test(T1 a1,int a2,T2 a3)
	 */
	boolean testX(T1 a1, int a2, T2 a3) throws Throwable;

	default boolean tupleTest(LObjIntObjTriple<T1, T2> args) {
		return test(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions according to the instructions. */
	default boolean handlingTest(T1 a1, int a2, T2 a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LObjIntObjPredicate<T1, T2> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> handlingTest(a1, a2, a3, handling);
	}

	default boolean test(T1 a1, int a2, T2 a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage);
		}
	}

	default boolean test(T1 a1, int a2, T2 a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1);
		}
	}

	default boolean test(T1 a1, int a2, T2 a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2);
		}
	}

	default boolean test(T1 a1, int a2, T2 a3, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory, newMessage, param1, param2, param3);
		}
	}

	default LObjIntObjPredicate<T1, T2> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		return (a1, a2, a3) -> test(a1, a2, a3, factory, newMessage);
	}

	default LObjIntObjPredicate<T1, T2> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2, a3) -> test(a1, a2, a3, factory, newMessage, param1);
	}

	default LObjIntObjPredicate<T1, T2> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2, a3) -> test(a1, a2, a3, factory, newMessage, param1, param1);
	}

	default LObjIntObjPredicate<T1, T2> trying(@Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2, a3) -> test(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	default boolean test(T1 a1, int a2, T2 a3, @Nonnull ExWF<RuntimeException> factory) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, factory);
		}
	}

	default LObjIntObjPredicate<T1, T2> trying(@Nonnull ExWF<RuntimeException> factory) {
		return (a1, a2, a3) -> test(a1, a2, a3, factory);
	}

	default boolean testThen(T1 a1, int a2, T2 a3, @Nonnull LPredicate<Throwable> handler) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.test(e);
		}
	}

	default LObjIntObjPredicate<T1, T2> tryingThen(@Nonnull LPredicate<Throwable> handler) {
		return (a1, a2, a3) -> testThen(a1, a2, a3, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingTest(T1 a1, int a2, T2 a3) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default boolean shovingTest(T1 a1, int a2, T2 a3) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T1, T2> boolean shovingTest(T1 a1, int a2, T2 a3, LObjIntObjPredicate<T1, T2> func) {
		Null.nonNullArg(func, "func");
		return func.shovingTest(a1, a2, a3);
	}

	static <T1, T2> boolean handlingTest(T1 a1, int a2, T2 a3, LObjIntObjPredicate<T1, T2> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingTest(a1, a2, a3, handling);
	}

	static <T1, T2> boolean tryTest(T1 a1, int a2, T2 a3, LObjIntObjPredicate<T1, T2> func) {
		Null.nonNullArg(func, "func");
		return func.nestingTest(a1, a2, a3);
	}

	static <T1, T2> boolean tryTest(T1 a1, int a2, T2 a3, LObjIntObjPredicate<T1, T2> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, factory, newMessage);
	}

	static <T1, T2> boolean tryTest(T1 a1, int a2, T2 a3, LObjIntObjPredicate<T1, T2> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, factory, newMessage, param1);
	}

	static <T1, T2> boolean tryTest(T1 a1, int a2, T2 a3, LObjIntObjPredicate<T1, T2> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, factory, newMessage, param1, param2);
	}

	static <T1, T2> boolean tryTest(T1 a1, int a2, T2 a3, LObjIntObjPredicate<T1, T2> func, @Nonnull ExWMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, factory, newMessage, param1, param2, param3);
	}

	static <T1, T2> boolean tryTest(T1 a1, int a2, T2 a3, LObjIntObjPredicate<T1, T2> func, @Nonnull ExWF<RuntimeException> factory) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, factory);
	}

	static <T1, T2> boolean tryTestThen(T1 a1, int a2, T2 a3, LObjIntObjPredicate<T1, T2> func, @Nonnull LPredicate<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.testThen(a1, a2, a3, handler);
	}

	default boolean failSafeTest(T1 a1, int a2, T2 a3, @Nonnull LObjIntObjPredicate<T1, T2> failSafe) {
		try {
			return test(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.test(a1, a2, a3);
		}
	}

	static <T1, T2> boolean failSafeTest(T1 a1, int a2, T2 a3, LObjIntObjPredicate<T1, T2> func, @Nonnull LObjIntObjPredicate<T1, T2> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.test(a1, a2, a3);
		} else {
			return func.failSafeTest(a1, a2, a3, failSafe);
		}
	}

	static <T1, T2> LObjIntObjPredicate<T1, T2> failSafe(LObjIntObjPredicate<T1, T2> func, @Nonnull LObjIntObjPredicate<T1, T2> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeTest(a1, a2, a3, func, failSafe);
	}

	default boolean doIf(T1 a1, int a2, T2 a3, LAction action) {
		Null.nonNullArg(action, "action");
		if (test(a1, a2, a3)) {
			action.execute();
			return true;
		} else {
			return false;
		}
	}

	static <T1, T2> boolean doIf(T1 a1, int a2, T2 a3, @Nonnull LObjIntObjPredicate<T1, T2> predicate, @Nonnull LAction action) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.doIf(a1, a2, a3, action);
	}

	static <T1, T2> boolean doIf(T1 a1, int a2, T2 a3, @Nonnull LObjIntObjPredicate<T1, T2> predicate, @Nonnull LTieConsumer<? super T1, ? super T2> consumer) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.doIf(a1, a2, a3, consumer);
	}

	default boolean doIf(T1 a1, int a2, T2 a3, @Nonnull LTieConsumer<? super T1, ? super T2> consumer) {
		Null.nonNullArg(consumer, "consumer");
		if (test(a1, a2, a3)) {
			consumer.accept(a1, a2, a3);
			return true;
		} else {
			return false;
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullTest(T1 a1, int a2, T2 a3) {
		return test(a1, a2, a3);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(T1 a1, int a2, T2 a3) {
		return test(a1, a2, a3);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LObjIntObjPredicate.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void fromTo(int min_a2, int max_a2, T1 a1, T2 a3, @Nonnull LObjIntObjPredicate<T1, T2> func) {
		Null.nonNullArg(func, "func");
		if (min_a2 <= max_a2) {
			for (int a2 = min_a2; a2 <= max_a2; a2++) {
				func.test(a1, a2, a3);
			}
		} else {
			for (int a2 = min_a2; a2 >= max_a2; a2--) {
				func.test(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void fromTill(int min_a2, int max_a2, T1 a1, T2 a3, @Nonnull LObjIntObjPredicate<T1, T2> func) {
		Null.nonNullArg(func, "func");
		if (min_a2 <= max_a2) {
			for (int a2 = min_a2; a2 < max_a2; a2++) {
				func.test(a1, a2, a3);
			}
		} else {
			for (int a2 = min_a2; a2 > max_a2; a2--) {
				func.test(a1, a2, a3);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void times(int max_a2, T1 a1, T2 a3, @Nonnull LObjIntObjPredicate<T1, T2> func) {
		if (max_a2 < 0)
			return;
		fromTill(0, max_a2, a1, a3, func);
	}

	/** Extract and apply function. */
	public static <M, K, V, T2> boolean from(@Nonnull M container, LBiFunction<M, K, V> extractor, K key, int a2, T2 a3, @Nonnull LObjIntObjPredicate<V, T2> function) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.test(value, a2, a3);
		}

		return false;
	}

	default LObjIntPredicate<T1> rShrink(@Nonnull LOiFunction<T1, T2> right) {
		Null.nonNullArg(right, "right");
		return (a1, a2) -> test(a1, a2, right.apply(a1, a2));
	}

	default LObjIntPredicate<T1> rShrink_(T2 a3) {
		return (a1, a2) -> test(a1, a2, a3);
	}

	public static <T1, T2> LObjIntPredicate<T1> rShrunken(@Nonnull LOiFunction<T1, T2> right, @Nonnull LObjIntObjPredicate<T1, T2> func) {
		Null.nonNullArg(right, "right");
		Null.nonNullArg(func, "func");
		return func.rShrink(right);
	}

	public static <T1, T2> LObjIntPredicate<T1> rShrunken_(T2 a3, @Nonnull LObjIntObjPredicate<T1, T2> func) {
		Null.nonNullArg(func, "func");
		return func.rShrink_(a3);
	}

	/**  */
	public static <T1, T2> LObjIntObjPredicate<T1, T2> uncurry(@Nonnull LFunction<T1, LIntFunction<LPredicate<T2>>> func) {
		Null.nonNullArg(func, "func");
		return (T1 a1, int a2, T2 a3) -> func.apply(a1).apply(a2).test(a3);
	}

	/** Cast that removes generics. */
	default LObjIntObjPredicate untyped() {
		return this;
	}

	/** Cast that replace generics. */
	default <V2, V3> LObjIntObjPredicate<V2, V3> cast() {
		return untyped();
	}

	/** Cast that replace generics. */
	public static <V2, V3> LObjIntObjPredicate<V2, V3> cast(LObjIntObjPredicate<?, ?> function) {
		return (LObjIntObjPredicate) function;
	}

	/** Change function to consumer that ignores output. */
	default LTieConsumer<T1, T2> toConsumer() {
		return this::test;
	}

	/** Calls domain consumer before main function. */
	default LObjIntObjPredicate<T1, T2> beforeDo(@Nonnull LTieConsumer<T1, T2> before) {
		Null.nonNullArg(before, "before");
		return (T1 a1, int a2, T2 a3) -> {
			before.accept(a1, a2, a3);
			return test(a1, a2, a3);
		};
	}

	/** Calls codomain consumer after main function. */
	default LObjIntObjPredicate<T1, T2> afterDo(@Nonnull LBoolConsumer after) {
		Null.nonNullArg(after, "after");
		return (T1 a1, int a2, T2 a3) -> {
			final boolean retval = test(a1, a2, a3);
			after.accept(retval);
			return retval;
		};
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, X extends Throwable> T1 throwIf(T1 a1, int a2, T2 a3, @Nonnull LObjIntObjPredicate<? super T1, ? super T2> pred, @Nonnull ExMF<X> factory, @Nonnull LObjIntObjFunction<? super T1, ? super T2, ? extends String> msgFunc) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, msgFunc.apply(a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, X extends Throwable> T1 throwIf(T1 a1, int a2, T2 a3, @Nonnull LObjIntObjPredicate<? super T1, ? super T2> pred, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(msg, a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, X extends Throwable> T1 throwIf(T1 a1, int a2, T2 a3, @Nonnull LObjIntObjPredicate<? super T1, ? super T2> pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, X extends Throwable> T1 throwIf(T1 a1, int a2, T2 a3, @Nonnull LObjIntObjPredicate<? super T1, ? super T2> pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, X extends Throwable> T1 throwIf(T1 a1, int a2, T2 a3, @Nonnull LObjIntObjPredicate<? super T1, ? super T2> pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2,
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
	public static <T1, T2, X extends Throwable> T1 throwIfNot(T1 a1, int a2, T2 a3, @Nonnull LObjIntObjPredicate<? super T1, ? super T2> pred, @Nonnull ExMF<X> factory, @Nonnull LObjIntObjFunction<? super T1, ? super T2, ? extends String> msgFunc)
			throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, msgFunc.apply(a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, X extends Throwable> T1 throwIfNot(T1 a1, int a2, T2 a3, @Nonnull LObjIntObjPredicate<? super T1, ? super T2> pred, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(msg, a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, X extends Throwable> T1 throwIfNot(T1 a1, int a2, T2 a3, @Nonnull LObjIntObjPredicate<? super T1, ? super T2> pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, X extends Throwable> T1 throwIfNot(T1 a1, int a2, T2 a3, @Nonnull LObjIntObjPredicate<? super T1, ? super T2> pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, X extends Throwable> T1 throwIfNot(T1 a1, int a2, T2 a3, @Nonnull LObjIntObjPredicate<? super T1, ? super T2> pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2,
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
	public static <T1, T2, X extends Throwable> T1 throwIf(T1 a1, int a2, T2 a3, @Nonnull LObjIntObjPredicate<? super T1, ? super T2> pred, @Nonnull ExF<X> noArgFactory) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(noArgFactory, "noArgFactory");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(noArgFactory);
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, X extends Throwable> T1 throwIfNot(T1 a1, int a2, T2 a3, @Nonnull LObjIntObjPredicate<? super T1, ? super T2> pred, @Nonnull ExF<X> noArgFactory) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(noArgFactory, "noArgFactory");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(noArgFactory);
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, X extends Throwable> T1 throwIf(T1 a1, @Nonnull LObjIntObjPredicate<? super T1, ? super T2> pred, int a2, T2 a3, @Nonnull ExMF<X> factory, @Nonnull LObjIntObjFunction<? super T1, ? super T2, ? extends String> msgFunc) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, msgFunc.apply(a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, X extends Throwable> T1 throwIf(T1 a1, @Nonnull LObjIntObjPredicate<? super T1, ? super T2> pred, int a2, T2 a3, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(msg, a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, X extends Throwable> T1 throwIf(T1 a1, @Nonnull LObjIntObjPredicate<? super T1, ? super T2> pred, int a2, T2 a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, X extends Throwable> T1 throwIf(T1 a1, @Nonnull LObjIntObjPredicate<? super T1, ? super T2> pred, int a2, T2 a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2, X extends Throwable> T1 throwIf(T1 a1, @Nonnull LObjIntObjPredicate<? super T1, ? super T2> pred, int a2, T2 a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2,
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
	public static <T1, T2, X extends Throwable> T1 throwIfNot(T1 a1, @Nonnull LObjIntObjPredicate<? super T1, ? super T2> pred, int a2, T2 a3, @Nonnull ExMF<X> factory, @Nonnull LObjIntObjFunction<? super T1, ? super T2, ? extends String> msgFunc)
			throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, msgFunc.apply(a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, X extends Throwable> T1 throwIfNot(T1 a1, @Nonnull LObjIntObjPredicate<? super T1, ? super T2> pred, int a2, T2 a3, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(msg, a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, X extends Throwable> T1 throwIfNot(T1 a1, @Nonnull LObjIntObjPredicate<? super T1, ? super T2> pred, int a2, T2 a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, X extends Throwable> T1 throwIfNot(T1 a1, @Nonnull LObjIntObjPredicate<? super T1, ? super T2> pred, int a2, T2 a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, X extends Throwable> T1 throwIfNot(T1 a1, @Nonnull LObjIntObjPredicate<? super T1, ? super T2> pred, int a2, T2 a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2,
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
	public static <T1, T2, X extends Throwable> T1 throwIf(T1 a1, @Nonnull LObjIntObjPredicate<? super T1, ? super T2> pred, int a2, T2 a3, @Nonnull ExF<X> noArgFactory) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(noArgFactory, "noArgFactory");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(noArgFactory);
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T1, T2, X extends Throwable> T1 throwIfNot(T1 a1, @Nonnull LObjIntObjPredicate<? super T1, ? super T2> pred, int a2, T2 a3, @Nonnull ExF<X> noArgFactory) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(noArgFactory, "noArgFactory");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(noArgFactory);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, X extends Throwable> T1 throwIfNotEx(T1 a1, int a2, T2 a3, @Nonnull LObjIntObjFunction<? super T1, ? super T2, ? extends String> specialPredicate, @Nonnull ExMF<X> factory) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		var m = specialPredicate.apply(a1, a2, a3);
		if (m != null) {
			throw Handling.create(factory, m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, X extends Throwable> T1 throwIfNotEx(T1 a1, int a2, T2 a3, @Nonnull LObjIntObjFunction<? super T1, ? super T2, ? extends String> specialPredicate, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
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
	public static <T1, T2, X extends Throwable> T1 throwIfNotEx(T1 a1, int a2, T2 a3, @Nonnull LObjIntObjFunction<? super T1, ? super T2, ? extends String> specialPredicate, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1)
			throws X {
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
	public static <T1, T2, X extends Throwable> T1 throwIfNotEx(T1 a1, int a2, T2 a3, @Nonnull LObjIntObjFunction<? super T1, ? super T2, ? extends String> specialPredicate, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1,
			@Nullable Object param2) throws X {
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
	public static <T1, T2, X extends Throwable> T1 throwIfNotEx(T1 a1, int a2, T2 a3, @Nonnull LObjIntObjFunction<? super T1, ? super T2, ? extends String> specialPredicate, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1,
			@Nullable Object param2, @Nullable Object param3) throws X {
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
	public static <T1, T2, X extends Throwable> T1 throwIfNotEx(T1 a1, @Nonnull LObjIntObjFunction<? super T1, ? super T2, ? extends String> specialPredicate, int a2, T2 a3, @Nonnull ExMF<X> factory) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		var m = specialPredicate.apply(a1, a2, a3);
		if (m != null) {
			throw Handling.create(factory, m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T1, T2, X extends Throwable> T1 throwIfNotEx(T1 a1, @Nonnull LObjIntObjFunction<? super T1, ? super T2, ? extends String> specialPredicate, int a2, T2 a3, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
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
	public static <T1, T2, X extends Throwable> T1 throwIfNotEx(T1 a1, @Nonnull LObjIntObjFunction<? super T1, ? super T2, ? extends String> specialPredicate, int a2, T2 a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1)
			throws X {
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
	public static <T1, T2, X extends Throwable> T1 throwIfNotEx(T1 a1, @Nonnull LObjIntObjFunction<? super T1, ? super T2, ? extends String> specialPredicate, int a2, T2 a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1,
			@Nullable Object param2) throws X {
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
	public static <T1, T2, X extends Throwable> T1 throwIfNotEx(T1 a1, @Nonnull LObjIntObjFunction<? super T1, ? super T2, ? extends String> specialPredicate, int a2, T2 a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1,
			@Nullable Object param2, @Nullable Object param3) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		var m = specialPredicate.apply(a1, a2, a3);
		if (m != null) {
			throw Handling.create(factory, String.format(message, param1, param2, param3) + ' ' + m);
		}
		return a1;
	}

	/** Captures arguments but delays the evaluation. */
	default LBoolSupplier capture(T1 a1, int a2, T2 a3) {
		return () -> this.test(a1, a2, a3);
	}

	/** Creates function that always returns the same value. */
	static <T1, T2> LObjIntObjPredicate<T1, T2> constant(boolean r) {
		return (a1, a2, a3) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T1, T2> LObjIntObjPredicate<T1, T2> test1st(@Nonnull LPredicate<T1> func) {
		return (a1, a2, a3) -> func.test(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T1, T2> LObjIntObjPredicate<T1, T2> test2nd(@Nonnull LIntPredicate func) {
		return (a1, a2, a3) -> func.test(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T1, T2> LObjIntObjPredicate<T1, T2> test3rd(@Nonnull LPredicate<T2> func) {
		return (a1, a2, a3) -> func.test(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2> LObjIntObjPredicate<T1, T2> objIntObjPred(final @Nonnull LObjIntObjPredicate<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** A completely inconvenient method in case lambda expression and generic arguments are ambiguous for the compiler. */
	@Nonnull
	static <T1, T2> LObjIntObjPredicate<T1, T2> objIntObjPred(@Nullable Class<T1> c1, @Nullable Class<T2> c2, final @Nonnull LObjIntObjPredicate<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	final class S<T1, T2> implements LObjIntObjPredicate<T1, T2> {
		private LObjIntObjPredicate<T1, T2> target = null;
		@Override
		public boolean testX(T1 a1, int a2, T2 a3) throws Throwable {
			return target.testX(a1, a2, a3);
		}
	}

	@Nonnull
	static <T1, T2> LObjIntObjPredicate<T1, T2> recursive(final @Nonnull LFunction<LObjIntObjPredicate<T1, T2>, LObjIntObjPredicate<T1, T2>> selfLambda) {
		final S<T1, T2> single = new S();
		LObjIntObjPredicate<T1, T2> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	public static <T1, T2> M<T1, T2> mementoOf(T1 a1, int a2, T2 a3, LObjIntObjPredicate<T1, T2> function) {
		var initialValue = function.test(a1, a2, a3);
		return initializedMementoOf(initialValue, function);
	}

	public static <T1, T2> M<T1, T2> initializedMementoOf(boolean initialValue, LObjIntObjPredicate<T1, T2> function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static <T1, T2> M<T1, T2> deltaOf(T1 a1, int a2, T2 a3, LObjIntObjPredicate<T1, T2> function, LLogicalBinaryOperator deltaFunction) {
		var initialValue = function.test(a1, a2, a3);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static <T1, T2> M<T1, T2> deltaOf(T1 a1, int a2, T2 a3, LObjIntObjPredicate<T1, T2> function) {
		var initialValue = function.test(a1, a2, a3);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> x1 != x2);
	}

	public static <T1, T2> M<T1, T2> initializedDeltaOf(boolean initialValue, LObjIntObjPredicate<T1, T2> function, LLogicalBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.apply(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.apply(x1, x2));
	}

	public static <T1, T2> M<T1, T2> memento(boolean initialBaseValue, boolean initialValue, LObjIntObjPredicate<T1, T2> baseFunction, LLogicalTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LObjIntObjPredicate.M)
	 */
	@NotThreadSafe
	final class M<T1, T2> implements LObjIntObjPredicate<T1, T2> {

		private final LObjIntObjPredicate<T1, T2> baseFunction;
		private boolean lastBaseValue;
		private boolean lastValue;
		private final LLogicalTernaryOperator mementoFunction;

		private M(boolean lastBaseValue, boolean lastValue, LObjIntObjPredicate<T1, T2> baseFunction, LLogicalTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public boolean testX(T1 a1, int a2, T2 a3) throws Throwable {
			boolean x1 = lastBaseValue;
			boolean x2 = lastBaseValue = baseFunction.testX(a1, a2, a3);

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
	static <T1, T2> LObjIntObjPredicate<T1, T2> objIntObjPredThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T1, T2> LObjIntObjPredicate<T1, T2> objIntObjPredThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2> LObjIntObjPredicate.LInt1BiObj2Pred<T1, T2> int1BiObj2Pred(final @Nonnull LObjIntObjPredicate.LInt1BiObj2Pred<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1> LObjIntObjPredicate.LInt1Obj2Obj0Pred<T2, T1> int1Obj2Obj0Pred(final @Nonnull LObjIntObjPredicate.LInt1Obj2Obj0Pred<T2, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1> LObjIntObjPredicate.LObj2Int1Obj0Pred<T2, T1> obj2Int1Obj0Pred(final @Nonnull LObjIntObjPredicate.LObj2Int1Obj0Pred<T2, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T1, T2> boolean call(T1 a1, int a2, T2 a3, final @Nonnull LObjIntObjPredicate<T1, T2> lambda) {
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
	default LObjIntObjPredicate<T1, T2> negate() {
		return (a1, a2, a3) -> !test(a1, a2, a3);
	}

	@Nonnull
	static <T1, T2> LObjIntObjPredicate<T1, T2> not(@Nonnull LObjIntObjPredicate<T1, T2> pred) {
		Null.nonNullArg(pred, "pred");
		return pred.negate();
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LObjIntObjPredicate<T1, T2> and(@Nonnull LObjIntObjPredicate<? super T1, ? super T2> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> test(a1, a2, a3) && other.test(a1, a2, a3);
	}

	@Nonnull
	public static <T1, T2> LObjIntObjPredicate<T1, T2> and(@Nonnull LObjIntObjPredicate<? super T1, ? super T2>... predicates) {
		Null.nonNullArg(predicates, "predicates");
		return (a1, a2, a3) -> {
			for (LObjIntObjPredicate<? super T1, ? super T2> p : predicates) {
				if (!p.test(a1, a2, a3)) {
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
	default LObjIntObjPredicate<T1, T2> or(@Nonnull LObjIntObjPredicate<? super T1, ? super T2> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> test(a1, a2, a3) || other.test(a1, a2, a3);
	}

	@Nonnull
	public static <T1, T2> LObjIntObjPredicate<T1, T2> or(@Nonnull LObjIntObjPredicate<? super T1, ? super T2>... predicates) {
		Null.nonNullArg(predicates, "predicates");
		return (a1, a2, a3) -> {
			for (LObjIntObjPredicate<? super T1, ? super T2> p : predicates) {
				if (p.test(a1, a2, a3)) {
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
	default LObjIntObjPredicate<T1, T2> xor(@Nonnull LObjIntObjPredicate<? super T1, ? super T2> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> test(a1, a2, a3) ^ other.test(a1, a2, a3);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static <T1, T2> LObjIntObjPredicate<T1, T2> isEqual(T1 v1, int v2, T2 v3) {
		return (a1, a2, a3) -> (a1 == null ? v1 == null : a1.equals(v1)) && (a2 == v2) && (a3 == null ? v3 == null : a3.equals(v3));
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V3> LObjIntObjPredicate<V1, V3> compose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LIntUnaryOperator before2, @Nonnull final LFunction<? super V3, ? extends T2> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.test(before1.apply(v1), before2.applyAsInt(v2), before3.apply(v3));
	}

	public static <V1, V3, T1, T2> LObjIntObjPredicate<V1, V3> composed(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LIntUnaryOperator before2, @Nonnull final LFunction<? super V3, ? extends T2> before3,
			LObjIntObjPredicate<T1, T2> after) {
		return after.compose(before1, before2, before3);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriPredicate<V1, V2, V3> objIntObjPredCompose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LToIntFunction<? super V2> before2, @Nonnull final LFunction<? super V3, ? extends T2> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.test(before1.apply(v1), before2.applyAsInt(v2), before3.apply(v3));
	}

	public static <V1, V2, V3, T1, T2> LTriPredicate<V1, V2, V3> composed(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LToIntFunction<? super V2> before2, @Nonnull final LFunction<? super V3, ? extends T2> before3,
			LObjIntObjPredicate<T1, T2> after) {
		return after.objIntObjPredCompose(before1, before2, before3);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LObjIntObjFunction<T1, T2, V> boolToObjIntObjFunc(@Nonnull LBoolFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.apply(this.test(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LTieFunction<T1, T2> boolToTieFunc(@Nonnull LBoolToIntFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.applyAsInt(this.test(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LObjIntObjPredicate<T1, T2> boolToObjIntObjPred(@Nonnull LLogicalOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.apply(this.test(a1, a2, a3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LObjIntObjPredicate for method references. */
	@FunctionalInterface
	interface LInt1BiObj2Pred<T1, T2> extends LObjIntObjPredicate<T1, T2> {

		/**
		 * Implement this, but call test(T1 a1,int a2,T2 a3)
		 */
		default boolean testX(T1 a1, int a2, T2 a3) {
			return this.testInt1BiObj2(a2, a1, a3);
		}

		// boolean testInt1BiObj2(int a2,T1 a1,T2 a3) ;
		default boolean testInt1BiObj2(int a2, T1 a1, T2 a3) {
			// return nestingTestInt1BiObj2(a2,a1,a3);
			try {
				return this.testInt1BiObj2X(a2, a1, a3);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call testInt1BiObj2(int a2,T1 a1,T2 a3)
		 */
		boolean testInt1BiObj2X(int a2, T1 a1, T2 a3) throws Throwable;
	}

	/** Permutation of LObjIntObjPredicate for method references. */
	@FunctionalInterface
	interface LInt1Obj2Obj0Pred<T2, T1> extends LObjIntObjPredicate<T1, T2> {

		/**
		 * Implement this, but call testInt1BiObj2(int a2,T1 a1,T2 a3)
		 */
		default boolean testX(T1 a1, int a2, T2 a3) {
			return this.testInt1Obj2Obj0(a2, a3, a1);
		}

		// boolean testInt1Obj2Obj0(int a2,T2 a3,T1 a1) ;
		default boolean testInt1Obj2Obj0(int a2, T2 a3, T1 a1) {
			// return nestingTestInt1Obj2Obj0(a2,a3,a1);
			try {
				return this.testInt1Obj2Obj0X(a2, a3, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call testInt1Obj2Obj0(int a2,T2 a3,T1 a1)
		 */
		boolean testInt1Obj2Obj0X(int a2, T2 a3, T1 a1) throws Throwable;
	}

	/** Permutation of LObjIntObjPredicate for method references. */
	@FunctionalInterface
	interface LObj2Int1Obj0Pred<T2, T1> extends LObjIntObjPredicate<T1, T2> {

		/**
		 * Implement this, but call testInt1Obj2Obj0(int a2,T2 a3,T1 a1)
		 */
		default boolean testX(T1 a1, int a2, T2 a3) {
			return this.testObj2Int1Obj0(a3, a2, a1);
		}

		// boolean testObj2Int1Obj0(T2 a3,int a2,T1 a1) ;
		default boolean testObj2Int1Obj0(T2 a3, int a2, T1 a1) {
			// return nestingTestObj2Int1Obj0(a3,a2,a1);
			try {
				return this.testObj2Int1Obj0X(a3, a2, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call testObj2Int1Obj0(T2 a3,int a2,T1 a1)
		 */
		boolean testObj2Int1Obj0X(T2 a3, int a2, T1 a1) throws Throwable;
	}

	// </editor-fold>

	// >>> LObjIntObjPredicate<T1,T2>

	/** Returns TRUE. */
	public static <T1, T2> boolean alwaysTrue(T1 a1, int a2, T2 a3) {
		return true;
	}

	/** Returns FALSE. */
	public static <T1, T2> boolean alwaysFalse(T1 a1, int a2, T2 a3) {
		return false;
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, C3> void filterForEach(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, IndexedRead<C3, a<T2>> ia3, C3 source3, LTieConsumer<? super T1, ? super T2> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T2> oiFunc3 = (LOiFunction) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			T1 a1 = oiFunc1.apply(source1, i);
			int a2 = oiFunc2.applyAsInt(source2, i);
			T2 a3 = oiFunc3.apply(source3, i);
			doIf(a1, a2, a3, consumer);
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, C3> void filterIterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, IndexedRead<C3, a<T2>> ia3, C3 source3, LTieConsumer<? super T1, ? super T2> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T2> oiFunc3 = (LOiFunction) ia3.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			T1 a1 = nextFunc1.apply(iterator1);
			int a2 = oiFunc2.applyAsInt(source2, i);
			T2 a3 = oiFunc3.apply(source3, i);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2, C3> void filterIterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, IndexedRead<C3, a<T2>> ia3, C3 source3, LTieConsumer<? super T1, ? super T2> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		size = Integer.min(size, ia3.size(source3));
		LOiFunction<Object, T2> oiFunc3 = (LOiFunction) ia3.getter();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			T1 a1 = oiFunc1.apply(source1, i);
			int a2 = nextFunc2.applyAsInt(iterator2);
			T2 a3 = oiFunc3.apply(source3, i);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, I2, C3> void filterIterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, IndexedRead<C3, a<T2>> ia3, C3 source3, LTieConsumer<? super T1, ? super T2> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		int size = ia3.size(source3);
		LOiFunction<Object, T2> oiFunc3 = (LOiFunction) ia3.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && i < size) {
			T1 a1 = nextFunc1.apply(iterator1);
			int a2 = nextFunc2.applyAsInt(iterator2);
			T2 a3 = oiFunc3.apply(source3, i);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, C3, I3> void filterIterate(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, SequentialRead<C3, I3, a<T2>> sa3, C3 source3, LTieConsumer<? super T1, ? super T2> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T2> nextFunc3 = (LFunction) sa3.supplier();
		int i = 0;
		while (i < size && testFunc3.test(iterator3)) {
			T1 a1 = oiFunc1.apply(source1, i);
			int a2 = oiFunc2.applyAsInt(source2, i);
			T2 a3 = nextFunc3.apply(iterator3);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, C3, I3> void filterIterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, SequentialRead<C3, I3, a<T2>> sa3, C3 source3, LTieConsumer<? super T1, ? super T2> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T2> nextFunc3 = (LFunction) sa3.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size && testFunc3.test(iterator3)) {
			T1 a1 = nextFunc1.apply(iterator1);
			int a2 = oiFunc2.applyAsInt(source2, i);
			T2 a3 = nextFunc3.apply(iterator3);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2, C3, I3> void filterIterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, SequentialRead<C3, I3, a<T2>> sa3, C3 source3, LTieConsumer<? super T1, ? super T2> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T2> nextFunc3 = (LFunction) sa3.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			T1 a1 = oiFunc1.apply(source1, i);
			int a2 = nextFunc2.applyAsInt(iterator2);
			T2 a3 = nextFunc3.apply(iterator3);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C1, I1, C2, I2, C3, I3> void filterIterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, SequentialRead<C3, I3, a<T2>> sa3, C3 source3, LTieConsumer<? super T1, ? super T2> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LFunction<Object, T2> nextFunc3 = (LFunction) sa3.supplier();
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			T1 a1 = nextFunc1.apply(iterator1);
			int a2 = nextFunc2.applyAsInt(iterator2);
			T2 a3 = nextFunc3.apply(iterator3);
			doIf(a1, a2, a3, consumer);
		}
	}

}
