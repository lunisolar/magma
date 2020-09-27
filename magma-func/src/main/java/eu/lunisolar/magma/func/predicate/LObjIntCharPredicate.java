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
 * Non-throwing functional interface (lambda) LObjIntCharPredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 3): T a1,int a2,char a3
 *
 * Co-domain: boolean
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LObjIntCharPredicate<T> extends MetaPredicate, MetaInterface.NonThrowing, Codomain<aBool>, Domain3<a<T>, aInt, aChar> { // NOSONAR

	String DESCRIPTION = "LObjIntCharPredicate: boolean test(T a1,int a2,char a3)";

	// boolean test(T a1,int a2,char a3) ;
	default boolean test(T a1, int a2, char a3) {
		// return nestingTest(a1,a2,a3);
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call test(T a1,int a2,char a3)
	 */
	boolean testX(T a1, int a2, char a3) throws Throwable;

	default boolean tupleTest(LObjIntCharTriple<T> args) {
		return test(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions according to the instructions. */
	default boolean handlingTest(T a1, int a2, char a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LObjIntCharPredicate<T> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> handlingTest(a1, a2, a3, handling);
	}

	default boolean test(T a1, int a2, char a3, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage);
		}
	}

	default boolean test(T a1, int a2, char a3, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, param1);
		}
	}

	default boolean test(T a1, int a2, char a3, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, param1, param2);
		}
	}

	default boolean test(T a1, int a2, char a3, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, param1, param2, param3);
		}
	}

	default LObjIntCharPredicate<T> trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage) {
		return (a1, a2, a3) -> test(a1, a2, a3, exF, newMessage);
	}

	default LObjIntCharPredicate<T> trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1) {
		return (a1, a2, a3) -> test(a1, a2, a3, exF, newMessage, param1);
	}

	default LObjIntCharPredicate<T> trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		return (a1, a2, a3) -> test(a1, a2, a3, exF, newMessage, param1, param1);
	}

	default LObjIntCharPredicate<T> trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		return (a1, a2, a3) -> test(a1, a2, a3, exF, newMessage, param1, param2, param3);
	}

	default boolean test(T a1, int a2, char a3, @Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LObjIntCharPredicate<T> trying(@Nonnull ExWF<RuntimeException> exF) {
		return (a1, a2, a3) -> test(a1, a2, a3, exF);
	}

	default boolean testThen(T a1, int a2, char a3, @Nonnull LPredicate<Throwable> handler) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.test(e);
		}
	}

	default LObjIntCharPredicate<T> tryingThen(@Nonnull LPredicate<Throwable> handler) {
		return (a1, a2, a3) -> testThen(a1, a2, a3, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingTest(T a1, int a2, char a3) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default boolean shovingTest(T a1, int a2, char a3) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> boolean handlingTest(T a1, int a2, char a3, LObjIntCharPredicate<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingTest(a1, a2, a3, handling);
	}

	static <T> boolean tryTest(T a1, int a2, char a3, LObjIntCharPredicate<T> func) {
		Null.nonNullArg(func, "func");
		return func.nestingTest(a1, a2, a3);
	}

	static <T> boolean tryTest(T a1, int a2, char a3, LObjIntCharPredicate<T> func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, exF, newMessage);
	}

	static <T> boolean tryTest(T a1, int a2, char a3, LObjIntCharPredicate<T> func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, exF, newMessage, param1);
	}

	static <T> boolean tryTest(T a1, int a2, char a3, LObjIntCharPredicate<T> func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, exF, newMessage, param1, param2);
	}

	static <T> boolean tryTest(T a1, int a2, char a3, LObjIntCharPredicate<T> func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, exF, newMessage, param1, param2, param3);
	}

	static <T> boolean tryTest(T a1, int a2, char a3, LObjIntCharPredicate<T> func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, exF);
	}

	static <T> boolean tryTestThen(T a1, int a2, char a3, LObjIntCharPredicate<T> func, @Nonnull LPredicate<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.testThen(a1, a2, a3, handler);
	}

	default boolean failSafeTest(T a1, int a2, char a3, @Nonnull LObjIntCharPredicate<T> failSafe) {
		try {
			return test(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.test(a1, a2, a3);
		}
	}

	static <T> boolean failSafeTest(T a1, int a2, char a3, LObjIntCharPredicate<T> func, @Nonnull LObjIntCharPredicate<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.test(a1, a2, a3);
		} else {
			return func.failSafeTest(a1, a2, a3, failSafe);
		}
	}

	static <T> LObjIntCharPredicate<T> failSafe(LObjIntCharPredicate<T> func, @Nonnull LObjIntCharPredicate<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeTest(a1, a2, a3, func, failSafe);
	}

	default boolean doIf(T a1, int a2, char a3, LAction action) {
		Null.nonNullArg(action, "action");
		if (test(a1, a2, a3)) {
			action.execute();
			return true;
		} else {
			return false;
		}
	}

	static <T> boolean doIf(T a1, int a2, char a3, @Nonnull LObjIntCharPredicate<T> predicate, @Nonnull LAction action) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.doIf(a1, a2, a3, action);
	}

	static <T> boolean doIf(T a1, int a2, char a3, @Nonnull LObjIntCharPredicate<T> predicate, @Nonnull LTieCharConsumer<? super T> consumer) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.doIf(a1, a2, a3, consumer);
	}

	default boolean doIf(T a1, int a2, char a3, @Nonnull LTieCharConsumer<? super T> consumer) {
		Null.nonNullArg(consumer, "consumer");
		if (test(a1, a2, a3)) {
			consumer.accept(a1, a2, a3);
			return true;
		} else {
			return false;
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullTest(T a1, int a2, char a3) {
		return test(a1, a2, a3);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(T a1, int a2, char a3) {
		return test(a1, a2, a3);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LObjIntCharPredicate.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_a2, int max_a2, T a1, char a3, @Nonnull LObjIntCharPredicate<T> func) {
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
	public static <T> void fromTill(int min_a2, int max_a2, T a1, char a3, @Nonnull LObjIntCharPredicate<T> func) {
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
	public static <T> void times(int max_a2, T a1, char a3, @Nonnull LObjIntCharPredicate<T> func) {
		if (max_a2 < 0)
			return;
		fromTill(0, max_a2, a1, a3, func);
	}

	/** Extract and apply function. */
	public static <M, K, V> boolean from(@Nonnull M container, LBiFunction<M, K, V> extractor, K key, int a2, char a3, @Nonnull LObjIntCharPredicate<V> function) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.test(value, a2, a3);
		}

		return false;
	}

	default LObjIntPredicate<T> rShrink(@Nonnull LOiToCharFunction<T> right) {
		Null.nonNullArg(right, "right");
		return (a1, a2) -> test(a1, a2, right.applyAsChar(a1, a2));
	}

	default LObjIntPredicate<T> rShrink_(char a3) {
		return (a1, a2) -> test(a1, a2, a3);
	}

	public static <T> LObjIntPredicate<T> rShrunken(@Nonnull LOiToCharFunction<T> right, @Nonnull LObjIntCharPredicate<T> func) {
		Null.nonNullArg(right, "right");
		Null.nonNullArg(func, "func");
		return func.rShrink(right);
	}

	public static <T> LObjIntPredicate<T> rShrunken_(char a3, @Nonnull LObjIntCharPredicate<T> func) {
		Null.nonNullArg(func, "func");
		return func.rShrink_(a3);
	}

	/**  */
	public static <T> LObjIntCharPredicate<T> uncurry(@Nonnull LFunction<T, LIntFunction<LCharPredicate>> func) {
		Null.nonNullArg(func, "func");
		return (T a1, int a2, char a3) -> func.apply(a1).apply(a2).test(a3);
	}

	/** Cast that removes generics. */
	default LObjIntCharPredicate untyped() {
		return this;
	}

	/** Cast that replace generics. */
	default <V2> LObjIntCharPredicate<V2> cast() {
		return untyped();
	}

	/** Cast that replace generics. */
	public static <V2> LObjIntCharPredicate<V2> cast(LObjIntCharPredicate<?> function) {
		return (LObjIntCharPredicate) function;
	}

	/** Change function to consumer that ignores output. */
	default LTieCharConsumer<T> toConsumer() {
		return this::test;
	}

	/** Calls domain consumer before main function. */
	default LObjIntCharPredicate<T> beforeDo(@Nonnull LTieCharConsumer<T> before) {
		Null.nonNullArg(before, "before");
		return (T a1, int a2, char a3) -> {
			before.accept(a1, a2, a3);
			return test(a1, a2, a3);
		};
	}

	/** Calls codomain consumer after main function. */
	default LObjIntCharPredicate<T> afterDo(@Nonnull LBoolConsumer after) {
		Null.nonNullArg(after, "after");
		return (T a1, int a2, char a3) -> {
			final boolean retval = test(a1, a2, a3);
			after.accept(retval);
			return retval;
		};
	}

	/** Throws new exception if condition is met. */
	public static <T, X extends Throwable> T throwIf(T a1, int a2, char a3, @Nonnull LObjIntCharPredicate<? super T> pred, @Nonnull ExMF<X> factory, @Nonnull LObjIntCharFunction<? super T, ? extends String> msgFunc) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, msgFunc.apply(a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T, X extends Throwable> T throwIf(T a1, int a2, char a3, @Nonnull LObjIntCharPredicate<? super T> pred, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(msg, a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T, X extends Throwable> T throwIf(T a1, int a2, char a3, @Nonnull LObjIntCharPredicate<? super T> pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T, X extends Throwable> T throwIf(T a1, int a2, char a3, @Nonnull LObjIntCharPredicate<? super T> pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T, X extends Throwable> T throwIf(T a1, int a2, char a3, @Nonnull LObjIntCharPredicate<? super T> pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3)
			throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2, param3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T, X extends Throwable> T throwIfNot(T a1, int a2, char a3, @Nonnull LObjIntCharPredicate<? super T> pred, @Nonnull ExMF<X> factory, @Nonnull LObjIntCharFunction<? super T, ? extends String> msgFunc) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, msgFunc.apply(a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T, X extends Throwable> T throwIfNot(T a1, int a2, char a3, @Nonnull LObjIntCharPredicate<? super T> pred, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(msg, a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T, X extends Throwable> T throwIfNot(T a1, int a2, char a3, @Nonnull LObjIntCharPredicate<? super T> pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T, X extends Throwable> T throwIfNot(T a1, int a2, char a3, @Nonnull LObjIntCharPredicate<? super T> pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T, X extends Throwable> T throwIfNot(T a1, int a2, char a3, @Nonnull LObjIntCharPredicate<? super T> pred, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3)
			throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2, param3));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T, X extends Throwable> T throwIf(T a1, @Nonnull LObjIntCharPredicate<? super T> pred, int a2, char a3, @Nonnull ExMF<X> factory, @Nonnull LObjIntCharFunction<? super T, ? extends String> msgFunc) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, msgFunc.apply(a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T, X extends Throwable> T throwIf(T a1, @Nonnull LObjIntCharPredicate<? super T> pred, int a2, char a3, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(msg, a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T, X extends Throwable> T throwIf(T a1, @Nonnull LObjIntCharPredicate<? super T> pred, int a2, char a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T, X extends Throwable> T throwIf(T a1, @Nonnull LObjIntCharPredicate<? super T> pred, int a2, char a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T, X extends Throwable> T throwIf(T a1, @Nonnull LObjIntCharPredicate<? super T> pred, int a2, char a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3)
			throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2, param3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T, X extends Throwable> T throwIfNot(T a1, @Nonnull LObjIntCharPredicate<? super T> pred, int a2, char a3, @Nonnull ExMF<X> factory, @Nonnull LObjIntCharFunction<? super T, ? extends String> msgFunc) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msgFunc, "msgFunc");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, msgFunc.apply(a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T, X extends Throwable> T throwIfNot(T a1, @Nonnull LObjIntCharPredicate<? super T> pred, int a2, char a3, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(msg, "msg");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(msg, a1, a2, a3));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T, X extends Throwable> T throwIfNot(T a1, @Nonnull LObjIntCharPredicate<? super T> pred, int a2, char a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T, X extends Throwable> T throwIfNot(T a1, @Nonnull LObjIntCharPredicate<? super T> pred, int a2, char a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2) throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2));
		}
		return a1;
	}

	/** Throws new exception if condition is NOT met. */
	public static <T, X extends Throwable> T throwIfNot(T a1, @Nonnull LObjIntCharPredicate<? super T> pred, int a2, char a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1, @Nullable Object param2, @Nullable Object param3)
			throws X {
		Null.nonNullArg(pred, "pred");
		Null.nonNullArg(factory, "factory");
		Null.nonNullArg(message, "message");
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, String.format(message, param1, param2, param3));
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T, X extends Throwable> T throwIfNotEx(T a1, int a2, char a3, @Nonnull LObjIntCharFunction<? super T, ? extends String> specialPredicate, @Nonnull ExMF<X> factory) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		var m = specialPredicate.apply(a1, a2, a3);
		if (m != null) {
			throw Handling.create(factory, m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T, X extends Throwable> T throwIfNotEx(T a1, int a2, char a3, @Nonnull LObjIntCharFunction<? super T, ? extends String> specialPredicate, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
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
	public static <T, X extends Throwable> T throwIfNotEx(T a1, int a2, char a3, @Nonnull LObjIntCharFunction<? super T, ? extends String> specialPredicate, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
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
	public static <T, X extends Throwable> T throwIfNotEx(T a1, int a2, char a3, @Nonnull LObjIntCharFunction<? super T, ? extends String> specialPredicate, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1,
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
	public static <T, X extends Throwable> T throwIfNotEx(T a1, int a2, char a3, @Nonnull LObjIntCharFunction<? super T, ? extends String> specialPredicate, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1,
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
	public static <T, X extends Throwable> T throwIfNotEx(T a1, @Nonnull LObjIntCharFunction<? super T, ? extends String> specialPredicate, int a2, char a3, @Nonnull ExMF<X> factory) throws X {
		Null.nonNullArg(specialPredicate, "specialPredicate");
		Null.nonNullArg(factory, "factory");
		var m = specialPredicate.apply(a1, a2, a3);
		if (m != null) {
			throw Handling.create(factory, m);
		}
		return a1;
	}

	/** Throws new exception if condition is not met (non null message is returned by 'predicate') */
	public static <T, X extends Throwable> T throwIfNotEx(T a1, @Nonnull LObjIntCharFunction<? super T, ? extends String> specialPredicate, int a2, char a3, @Nonnull ExMF<X> factory, @Nonnull String msg) throws X {
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
	public static <T, X extends Throwable> T throwIfNotEx(T a1, @Nonnull LObjIntCharFunction<? super T, ? extends String> specialPredicate, int a2, char a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1) throws X {
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
	public static <T, X extends Throwable> T throwIfNotEx(T a1, @Nonnull LObjIntCharFunction<? super T, ? extends String> specialPredicate, int a2, char a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1,
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
	public static <T, X extends Throwable> T throwIfNotEx(T a1, @Nonnull LObjIntCharFunction<? super T, ? extends String> specialPredicate, int a2, char a3, @Nonnull ExMF<X> factory, @Nonnull String message, @Nullable Object param1,
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
	default LBoolSupplier capture(T a1, int a2, char a3) {
		return () -> this.test(a1, a2, a3);
	}

	/** Creates function that always returns the same value. */
	static <T> LObjIntCharPredicate<T> constant(boolean r) {
		return (a1, a2, a3) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T> LObjIntCharPredicate<T> test1st(@Nonnull LPredicate<T> func) {
		return (a1, a2, a3) -> func.test(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T> LObjIntCharPredicate<T> test2nd(@Nonnull LIntPredicate func) {
		return (a1, a2, a3) -> func.test(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T> LObjIntCharPredicate<T> test3rd(@Nonnull LCharPredicate func) {
		return (a1, a2, a3) -> func.test(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LObjIntCharPredicate<T> objIntCharPred(final @Nonnull LObjIntCharPredicate<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** A completely inconvenient method in case lambda expression and generic arguments are ambiguous for the compiler. */
	@Nonnull
	static <T> LObjIntCharPredicate<T> objIntCharPred(@Nullable Class<T> c1, final @Nonnull LObjIntCharPredicate<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	final class S<T> implements LObjIntCharPredicate<T> {
		private LObjIntCharPredicate<T> target = null;
		@Override
		public boolean testX(T a1, int a2, char a3) throws Throwable {
			return target.testX(a1, a2, a3);
		}
	}

	@Nonnull
	static <T> LObjIntCharPredicate<T> recursive(final @Nonnull LFunction<LObjIntCharPredicate<T>, LObjIntCharPredicate<T>> selfLambda) {
		final S<T> single = new S();
		LObjIntCharPredicate<T> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	public static <T> M<T> mementoOf(T a1, int a2, char a3, LObjIntCharPredicate<T> function) {
		var initialValue = function.test(a1, a2, a3);
		return initializedMementoOf(initialValue, function);
	}

	public static <T> M<T> initializedMementoOf(boolean initialValue, LObjIntCharPredicate<T> function) {
		return memento(initialValue, initialValue, function, (m, x1, x2) -> x2);
	}

	public static <T> M<T> deltaOf(T a1, int a2, char a3, LObjIntCharPredicate<T> function, LLogicalBinaryOperator deltaFunction) {
		var initialValue = function.test(a1, a2, a3);
		return initializedDeltaOf(initialValue, function, deltaFunction);
	}

	public static <T> M<T> deltaOf(T a1, int a2, char a3, LObjIntCharPredicate<T> function) {
		var initialValue = function.test(a1, a2, a3);
		return initializedDeltaOf(initialValue, function, (x1, x2) -> x1 != x2);
	}

	public static <T> M<T> initializedDeltaOf(boolean initialValue, LObjIntCharPredicate<T> function, LLogicalBinaryOperator deltaFunction) {
		return memento(initialValue, deltaFunction.apply(initialValue, initialValue), function, (m, x1, x2) -> deltaFunction.apply(x1, x2));
	}

	public static <T> M<T> memento(boolean initialBaseValue, boolean initialValue, LObjIntCharPredicate<T> baseFunction, LLogicalTernaryOperator mementoFunction) {
		return new M(initialBaseValue, initialValue, baseFunction, mementoFunction);
	}

	/**
	 * Implementation that allows to create derivative functions (do not confuse it with math concepts). Very short name is intended to be used with parent (LObjIntCharPredicate.M)
	 */
	@NotThreadSafe
	final class M<T> implements LObjIntCharPredicate<T> {

		private final LObjIntCharPredicate<T> baseFunction;
		private boolean lastBaseValue;
		private boolean lastValue;
		private final LLogicalTernaryOperator mementoFunction;

		private M(boolean lastBaseValue, boolean lastValue, LObjIntCharPredicate<T> baseFunction, LLogicalTernaryOperator mementoFunction) {
			this.baseFunction = baseFunction;
			this.lastBaseValue = lastBaseValue;
			this.lastValue = lastValue;
			this.mementoFunction = mementoFunction;
		}

		@Override
		public boolean testX(T a1, int a2, char a3) throws Throwable {
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
	static <T> LObjIntCharPredicate<T> objIntCharPredThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T> LObjIntCharPredicate<T> objIntCharPredThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LObjIntCharPredicate.LObjCharIntPred<T> objCharIntPred(final @Nonnull LObjIntCharPredicate.LObjCharIntPred<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LObjIntCharPredicate.LIntObjCharPred<T> intObjCharPred(final @Nonnull LObjIntCharPredicate.LIntObjCharPred<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LObjIntCharPredicate.LIntCharObjPred<T> intCharObjPred(final @Nonnull LObjIntCharPredicate.LIntCharObjPred<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LObjIntCharPredicate.LCharObjIntPred<T> charObjIntPred(final @Nonnull LObjIntCharPredicate.LCharObjIntPred<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LObjIntCharPredicate.LCharIntObjPred<T> charIntObjPred(final @Nonnull LObjIntCharPredicate.LCharIntObjPred<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T> boolean call(T a1, int a2, char a3, final @Nonnull LObjIntCharPredicate<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.test(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as alwaysFalse). */
	@Nonnull
	static <T> LObjIntCharPredicate<T> safe() {
		return LObjIntCharPredicate::alwaysFalse;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LObjIntCharPredicate<T>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T> LObjIntCharPredicate<T> safe(final @Nullable LObjIntCharPredicate<T> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LObjIntCharPredicate<T>> safeSupplier(final @Nullable LSupplier<LObjIntCharPredicate<T>> supplier) {
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
	default LObjIntCharPredicate<T> negate() {
		return (a1, a2, a3) -> !test(a1, a2, a3);
	}

	@Nonnull
	static <T> LObjIntCharPredicate<T> not(@Nonnull LObjIntCharPredicate<T> pred) {
		Null.nonNullArg(pred, "pred");
		return pred.negate();
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LObjIntCharPredicate<T> and(@Nonnull LObjIntCharPredicate<? super T> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> test(a1, a2, a3) && other.test(a1, a2, a3);
	}

	@Nonnull
	public static <T> LObjIntCharPredicate<T> and(@Nonnull LObjIntCharPredicate<? super T>... predicates) {
		Null.nonNullArg(predicates, "predicates");
		return (a1, a2, a3) -> {
			for (LObjIntCharPredicate<? super T> p : predicates) {
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
	default LObjIntCharPredicate<T> or(@Nonnull LObjIntCharPredicate<? super T> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> test(a1, a2, a3) || other.test(a1, a2, a3);
	}

	@Nonnull
	public static <T> LObjIntCharPredicate<T> or(@Nonnull LObjIntCharPredicate<? super T>... predicates) {
		Null.nonNullArg(predicates, "predicates");
		return (a1, a2, a3) -> {
			for (LObjIntCharPredicate<? super T> p : predicates) {
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
	default LObjIntCharPredicate<T> xor(@Nonnull LObjIntCharPredicate<? super T> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> test(a1, a2, a3) ^ other.test(a1, a2, a3);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static <T> LObjIntCharPredicate<T> isEqual(T v1, int v2, char v3) {
		return (a1, a2, a3) -> (a1 == null ? v1 == null : a1.equals(v1)) && (a2 == v2) && (a3 == v3);
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1> LObjIntCharPredicate<V1> compose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2, @Nonnull final LCharUnaryOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.test(before1.apply(v1), before2.applyAsInt(v2), before3.applyAsChar(v3));
	}

	public static <V1, T> LObjIntCharPredicate<V1> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2, @Nonnull final LCharUnaryOperator before3, LObjIntCharPredicate<T> after) {
		return after.compose(before1, before2, before3);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriPredicate<V1, V2, V3> objIntCharPredCompose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2, @Nonnull final LToCharFunction<? super V3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.test(before1.apply(v1), before2.applyAsInt(v2), before3.applyAsChar(v3));
	}

	public static <V1, V2, V3, T> LTriPredicate<V1, V2, V3> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2, @Nonnull final LToCharFunction<? super V3> before3,
			LObjIntCharPredicate<T> after) {
		return after.objIntCharPredCompose(before1, before2, before3);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LObjIntCharFunction<T, V> boolToObjIntCharFunc(@Nonnull LBoolFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.apply(this.test(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LTieCharFunction<T> boolToTieCharFunc(@Nonnull LBoolToIntFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.applyAsInt(this.test(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LObjIntCharPredicate<T> boolToObjIntCharPred(@Nonnull LLogicalOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.apply(this.test(a1, a2, a3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LObjIntCharPredicate for method references. */
	@FunctionalInterface
	interface LObjCharIntPred<T> extends LObjIntCharPredicate<T> {

		/**
		 * Implement this, but call test(T a1,int a2,char a3)
		 */
		default boolean testX(T a1, int a2, char a3) {
			return this.testObjCharInt(a1, a3, a2);
		}

		// boolean testObjCharInt(T a1,char a3,int a2) ;
		default boolean testObjCharInt(T a1, char a3, int a2) {
			// return nestingTestObjCharInt(a1,a3,a2);
			try {
				return this.testObjCharIntX(a1, a3, a2);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call testObjCharInt(T a1,char a3,int a2)
		 */
		boolean testObjCharIntX(T a1, char a3, int a2) throws Throwable;
	}

	/** Permutation of LObjIntCharPredicate for method references. */
	@FunctionalInterface
	interface LIntObjCharPred<T> extends LObjIntCharPredicate<T> {

		/**
		 * Implement this, but call testObjCharInt(T a1,char a3,int a2)
		 */
		default boolean testX(T a1, int a2, char a3) {
			return this.testIntObjChar(a2, a1, a3);
		}

		// boolean testIntObjChar(int a2,T a1,char a3) ;
		default boolean testIntObjChar(int a2, T a1, char a3) {
			// return nestingTestIntObjChar(a2,a1,a3);
			try {
				return this.testIntObjCharX(a2, a1, a3);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call testIntObjChar(int a2,T a1,char a3)
		 */
		boolean testIntObjCharX(int a2, T a1, char a3) throws Throwable;
	}

	/** Permutation of LObjIntCharPredicate for method references. */
	@FunctionalInterface
	interface LIntCharObjPred<T> extends LObjIntCharPredicate<T> {

		/**
		 * Implement this, but call testIntObjChar(int a2,T a1,char a3)
		 */
		default boolean testX(T a1, int a2, char a3) {
			return this.testIntCharObj(a2, a3, a1);
		}

		// boolean testIntCharObj(int a2,char a3,T a1) ;
		default boolean testIntCharObj(int a2, char a3, T a1) {
			// return nestingTestIntCharObj(a2,a3,a1);
			try {
				return this.testIntCharObjX(a2, a3, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call testIntCharObj(int a2,char a3,T a1)
		 */
		boolean testIntCharObjX(int a2, char a3, T a1) throws Throwable;
	}

	/** Permutation of LObjIntCharPredicate for method references. */
	@FunctionalInterface
	interface LCharObjIntPred<T> extends LObjIntCharPredicate<T> {

		/**
		 * Implement this, but call testIntCharObj(int a2,char a3,T a1)
		 */
		default boolean testX(T a1, int a2, char a3) {
			return this.testCharObjInt(a3, a1, a2);
		}

		// boolean testCharObjInt(char a3,T a1,int a2) ;
		default boolean testCharObjInt(char a3, T a1, int a2) {
			// return nestingTestCharObjInt(a3,a1,a2);
			try {
				return this.testCharObjIntX(a3, a1, a2);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call testCharObjInt(char a3,T a1,int a2)
		 */
		boolean testCharObjIntX(char a3, T a1, int a2) throws Throwable;
	}

	/** Permutation of LObjIntCharPredicate for method references. */
	@FunctionalInterface
	interface LCharIntObjPred<T> extends LObjIntCharPredicate<T> {

		/**
		 * Implement this, but call testCharObjInt(char a3,T a1,int a2)
		 */
		default boolean testX(T a1, int a2, char a3) {
			return this.testCharIntObj(a3, a2, a1);
		}

		// boolean testCharIntObj(char a3,int a2,T a1) ;
		default boolean testCharIntObj(char a3, int a2, T a1) {
			// return nestingTestCharIntObj(a3,a2,a1);
			try {
				return this.testCharIntObjX(a3, a2, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call testCharIntObj(char a3,int a2,T a1)
		 */
		boolean testCharIntObjX(char a3, int a2, T a1) throws Throwable;
	}

	// </editor-fold>

	// >>> LObjIntCharPredicate<T>

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(T a1, int a2, char a3) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(T a1, int a2, char a3) {
		return false;
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, C3> void filterForEach(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, IndexedRead<C3, aChar> ia3, C3 source3, LTieCharConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToCharFunction<Object> oiFunc3 = (LOiToCharFunction) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = oiFunc2.applyAsInt(source2, i);
			char a3 = oiFunc3.applyAsChar(source3, i);
			doIf(a1, a2, a3, consumer);
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, C3> void filterIterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, IndexedRead<C3, aChar> ia3, C3 source3, LTieCharConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToCharFunction<Object> oiFunc3 = (LOiToCharFunction) ia3.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = oiFunc2.applyAsInt(source2, i);
			char a3 = oiFunc3.applyAsChar(source3, i);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2, C3> void filterIterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, IndexedRead<C3, aChar> ia3, C3 source3, LTieCharConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		size = Integer.min(size, ia3.size(source3));
		LOiToCharFunction<Object> oiFunc3 = (LOiToCharFunction) ia3.getter();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = nextFunc2.applyAsInt(iterator2);
			char a3 = oiFunc3.applyAsChar(source3, i);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, I2, C3> void filterIterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, IndexedRead<C3, aChar> ia3, C3 source3, LTieCharConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		int size = ia3.size(source3);
		LOiToCharFunction<Object> oiFunc3 = (LOiToCharFunction) ia3.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && i < size) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = nextFunc2.applyAsInt(iterator2);
			char a3 = oiFunc3.applyAsChar(source3, i);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, C3, I3> void filterIterate(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, SequentialRead<C3, I3, aChar> sa3, C3 source3, LTieCharConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToCharFunction<Object> nextFunc3 = (LToCharFunction) sa3.supplier();
		int i = 0;
		while (i < size && testFunc3.test(iterator3)) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = oiFunc2.applyAsInt(source2, i);
			char a3 = nextFunc3.applyAsChar(iterator3);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, C3, I3> void filterIterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, SequentialRead<C3, I3, aChar> sa3, C3 source3, LTieCharConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToCharFunction<Object> nextFunc3 = (LToCharFunction) sa3.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size && testFunc3.test(iterator3)) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = oiFunc2.applyAsInt(source2, i);
			char a3 = nextFunc3.applyAsChar(iterator3);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2, C3, I3> void filterIterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, SequentialRead<C3, I3, aChar> sa3, C3 source3, LTieCharConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToCharFunction<Object> nextFunc3 = (LToCharFunction) sa3.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = nextFunc2.applyAsInt(iterator2);
			char a3 = nextFunc3.applyAsChar(iterator3);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C1, I1, C2, I2, C3, I3> void filterIterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, SequentialRead<C3, I3, aChar> sa3, C3 source3, LTieCharConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToCharFunction<Object> nextFunc3 = (LToCharFunction) sa3.supplier();
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = nextFunc2.applyAsInt(iterator2);
			char a3 = nextFunc3.applyAsChar(iterator3);
			doIf(a1, a2, a3, consumer);
		}
	}

}
