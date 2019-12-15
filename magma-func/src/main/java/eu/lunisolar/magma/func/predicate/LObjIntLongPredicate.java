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
 * Non-throwing functional interface (lambda) LObjIntLongPredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 3): T a1,int a2,long a3
 *
 * Co-domain: boolean
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LObjIntLongPredicate<T> extends MetaPredicate, MetaInterface.NonThrowing, Codomain<aBool>, Domain3<a<T>, aInt, aLong> { // NOSONAR

	String DESCRIPTION = "LObjIntLongPredicate: boolean test(T a1,int a2,long a3)";

	// boolean test(T a1,int a2,long a3) ;
	default boolean test(T a1, int a2, long a3) {
		// return nestingTest(a1,a2,a3);
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call test(T a1,int a2,long a3)
	 */
	boolean testX(T a1, int a2, long a3) throws Throwable;

	default boolean tupleTest(LObjIntLongTriple<T> args) {
		return test(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions according to the instructions. */
	default boolean handlingTest(T a1, int a2, long a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LObjIntLongPredicate<T> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> handlingTest(a1, a2, a3, handling);
	}

	default boolean test(T a1, int a2, long a3, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LObjIntLongPredicate<T> trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return (a1, a2, a3) -> test(a1, a2, a3, exF, newMessage, messageParams);
	}

	default boolean test(T a1, int a2, long a3, @Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LObjIntLongPredicate<T> trying(@Nonnull ExWF<RuntimeException> exF) {
		return (a1, a2, a3) -> test(a1, a2, a3, exF);
	}

	default boolean testThen(T a1, int a2, long a3, @Nonnull LPredicate<Throwable> handler) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.test(e);
		}
	}

	default LObjIntLongPredicate<T> tryingThen(@Nonnull LPredicate<Throwable> handler) {
		return (a1, a2, a3) -> testThen(a1, a2, a3, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingTest(T a1, int a2, long a3) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default boolean shovingTest(T a1, int a2, long a3) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> boolean handlingTest(T a1, int a2, long a3, LObjIntLongPredicate<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingTest(a1, a2, a3, handling);
	}

	static <T> boolean tryTest(T a1, int a2, long a3, LObjIntLongPredicate<T> func) {
		Null.nonNullArg(func, "func");
		return func.nestingTest(a1, a2, a3);
	}

	static <T> boolean tryTest(T a1, int a2, long a3, LObjIntLongPredicate<T> func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, exF, newMessage, messageParams);
	}

	static <T> boolean tryTest(T a1, int a2, long a3, LObjIntLongPredicate<T> func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, exF);
	}

	static <T> boolean tryTestThen(T a1, int a2, long a3, LObjIntLongPredicate<T> func, @Nonnull LPredicate<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.testThen(a1, a2, a3, handler);
	}

	default boolean failSafeTest(T a1, int a2, long a3, @Nonnull LObjIntLongPredicate<T> failSafe) {
		try {
			return test(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.test(a1, a2, a3);
		}
	}

	static <T> boolean failSafeTest(T a1, int a2, long a3, LObjIntLongPredicate<T> func, @Nonnull LObjIntLongPredicate<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.test(a1, a2, a3);
		} else {
			return func.failSafeTest(a1, a2, a3, failSafe);
		}
	}

	static <T> LObjIntLongPredicate<T> failSafe(LObjIntLongPredicate<T> func, @Nonnull LObjIntLongPredicate<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeTest(a1, a2, a3, func, failSafe);
	}

	default boolean doIf(T a1, int a2, long a3, LAction action) {
		Null.nonNullArg(action, "action");
		if (test(a1, a2, a3)) {
			action.execute();
			return true;
		} else {
			return false;
		}
	}

	static <T> boolean doIf(T a1, int a2, long a3, @Nonnull LObjIntLongPredicate<T> predicate, @Nonnull LAction action) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.doIf(a1, a2, a3, action);
	}

	static <T> boolean doIf(T a1, int a2, long a3, @Nonnull LObjIntLongPredicate<T> predicate, @Nonnull LTieLongConsumer<? super T> consumer) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.doIf(a1, a2, a3, consumer);
	}

	default boolean doIf(T a1, int a2, long a3, @Nonnull LTieLongConsumer<? super T> consumer) {
		Null.nonNullArg(consumer, "consumer");
		if (test(a1, a2, a3)) {
			consumer.accept(a1, a2, a3);
			return true;
		} else {
			return false;
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullTest(T a1, int a2, long a3) {
		return test(a1, a2, a3);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(T a1, int a2, long a3) {
		return test(a1, a2, a3);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LObjIntLongPredicate.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_i, int max_i, T a1, int a2, long a3, @Nonnull LObjIntLongPredicate<T> func) {
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
	public static <T> void fromTill(int min_i, int max_i, T a1, int a2, long a3, @Nonnull LObjIntLongPredicate<T> func) {
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
	public static <T> void times(int max_i, T a1, int a2, long a3, @Nonnull LObjIntLongPredicate<T> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, a3, func);
	}

	/** Extract and apply function. */
	public static <M, K, V> boolean from(@Nonnull M container, LBiFunction<M, K, V> extractor, K key, int a2, long a3, @Nonnull LObjIntLongPredicate<V> function) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.test(value, a2, a3);
		}

		return false;
	}

	public default LObjIntPredicate<T> rShrink(@Nonnull LOiToLongFunction<T> right) {
		Null.nonNullArg(right, "right");
		return (a1, a2) -> test(a1, a2, right.applyAsLong(a1, a2));
	}

	public default LObjIntPredicate<T> rShrink_(long a3) {
		return (a1, a2) -> test(a1, a2, a3);
	}

	public static <T> LObjIntPredicate<T> rShrunken(@Nonnull LOiToLongFunction<T> right, @Nonnull LObjIntLongPredicate<T> func) {
		Null.nonNullArg(right, "right");
		Null.nonNullArg(func, "func");
		return func.rShrink(right);
	}

	public static <T> LObjIntPredicate<T> rShrunken_(long a3, @Nonnull LObjIntLongPredicate<T> func) {
		Null.nonNullArg(func, "func");
		return func.rShrink_(a3);
	}

	/**  */
	public static <T> LObjIntLongPredicate<T> uncurry(@Nonnull LFunction<T, LIntFunction<LLongPredicate>> func) {
		Null.nonNullArg(func, "func");
		return (T a1, int a2, long a3) -> func.apply(a1).apply(a2).test(a3);
	}

	/** Cast that removes generics. */
	public default LObjIntLongPredicate untyped() {
		return this;
	}

	/** Cast that replace generics. */
	public default <V2> LObjIntLongPredicate<V2> cast() {
		return untyped();
	}

	/** Cast that replace generics. */
	public static <V2, T> LObjIntLongPredicate<V2> cast(LObjIntLongPredicate<T> function) {
		return (LObjIntLongPredicate) function;
	}

	/** Change function to consumer that ignores output. */
	public default LTieLongConsumer<T> toConsumer() {
		return this::test;
	}

	/** Calls domain consumer before main function. */
	public default LObjIntLongPredicate<T> beforeDo(@Nonnull LTieLongConsumer<T> before) {
		Null.nonNullArg(before, "before");
		return (T a1, int a2, long a3) -> {
			before.accept(a1, a2, a3);
			return test(a1, a2, a3);
		};
	}

	/** Calls codomain consumer after main function. */
	public default LObjIntLongPredicate<T> afterDo(@Nonnull LBoolConsumer after) {
		Null.nonNullArg(after, "after");
		return (T a1, int a2, long a3) -> {
			final boolean retval = test(a1, a2, a3);
			after.accept(retval);
			return retval;
		};
	}

	/** Throws new exception if condition is met. */
	public static <T, X extends Throwable> T throwIf(T a1, int a2, long a3, @Nonnull LObjIntLongPredicate<T> pred, @Nonnull ExMF<X> factory, @Nonnull String newMessage, @Nonnull Object... messageParams) throws X {
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T, X extends Throwable> T throwIf(T a1, @Nonnull LObjIntLongPredicate<T> pred, int a2, long a3, @Nonnull ExMF<X> factory, @Nonnull String newMessage, @Nonnull Object... messageParams) throws X {
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
		return a1;
	}

	/** Throws new exception if condition is not met. */
	public static <T, X extends Throwable> T throwIfNot(T a1, int a2, long a3, @Nonnull LObjIntLongPredicate<T> pred, @Nonnull ExMF<X> factory, @Nonnull String newMessage, @Nonnull Object... messageParams) throws X {
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
		return a1;
	}

	/** Throws new exception if condition is not met. */
	public static <T, X extends Throwable> T throwIfNot(T a1, @Nonnull LObjIntLongPredicate<T> pred, int a2, long a3, @Nonnull ExMF<X> factory, @Nonnull String newMessage, @Nonnull Object... messageParams) throws X {
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T, X extends Throwable> T throwIf(T a1, int a2, long a3, @Nonnull LObjIntLongPredicate<T> pred, @Nonnull ExMF<X> factory, @Nonnull String newMessage) throws X {
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, newMessage);
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T, X extends Throwable> T throwIf(T a1, @Nonnull LObjIntLongPredicate<T> pred, int a2, long a3, @Nonnull ExMF<X> factory, @Nonnull String newMessage) throws X {
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, newMessage);
		}
		return a1;
	}

	/** Throws new exception if condition is not met. */
	public static <T, X extends Throwable> T throwIfNot(T a1, int a2, long a3, @Nonnull LObjIntLongPredicate<T> pred, @Nonnull ExMF<X> factory, @Nonnull String newMessage) throws X {
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, newMessage);
		}
		return a1;
	}

	/** Throws new exception if condition is not met. */
	public static <T, X extends Throwable> T throwIfNot(T a1, @Nonnull LObjIntLongPredicate<T> pred, int a2, long a3, @Nonnull ExMF<X> factory, @Nonnull String newMessage) throws X {
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, newMessage);
		}
		return a1;
	}

	/**
	* Throws new exception if condition is met.
	* Message will be formatted with predicate arguments.
	*/
	public static <T, X extends Throwable> T throwIf$(T a1, int a2, long a3, @Nonnull LObjIntLongPredicate<T> pred, @Nonnull ExMF<X> factory, @Nonnull String newMessage) throws X {
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, newMessage, a1, a2, a3);
		}
		return a1;
	}

	/**
	* Throws new exception if condition is met.
	* Message will be formatted with predicate arguments.
	*/
	public static <T, X extends Throwable> T throwIf$(T a1, @Nonnull LObjIntLongPredicate<T> pred, int a2, long a3, @Nonnull ExMF<X> factory, @Nonnull String newMessage) throws X {
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, newMessage, a1, a2, a3);
		}
		return a1;
	}

	/**
	* Throws new exception if condition is not met.
	* Message will be formatted with predicate arguments.
	*/
	public static <T, X extends Throwable> T throwIfNot$(T a1, int a2, long a3, @Nonnull LObjIntLongPredicate<T> pred, @Nonnull ExMF<X> factory, @Nonnull String newMessage) throws X {
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, newMessage, a1, a2, a3);
		}
		return a1;
	}

	/**
	* Throws new exception if condition is not met.
	* Message will be formatted with predicate arguments.
	*/
	public static <T, X extends Throwable> T throwIfNot$(T a1, @Nonnull LObjIntLongPredicate<T> pred, int a2, long a3, @Nonnull ExMF<X> factory, @Nonnull String newMessage) throws X {
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, newMessage, a1, a2, a3);
		}
		return a1;
	}

	/** Captures arguments but delays the evaluation. */
	default LBoolSupplier capture(T a1, int a2, long a3) {
		return () -> this.test(a1, a2, a3);
	}

	/** Creates function that always returns the same value. */
	static <T> LObjIntLongPredicate<T> constant(boolean r) {
		return (a1, a2, a3) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T> LObjIntLongPredicate<T> test1st(@Nonnull LPredicate<T> func) {
		return (a1, a2, a3) -> func.test(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T> LObjIntLongPredicate<T> test2nd(@Nonnull LIntPredicate func) {
		return (a1, a2, a3) -> func.test(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T> LObjIntLongPredicate<T> test3rd(@Nonnull LLongPredicate func) {
		return (a1, a2, a3) -> func.test(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LObjIntLongPredicate<T> objIntLongPred(final @Nonnull LObjIntLongPredicate<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** A completely inconvenient method in case lambda expression and generic arguments are ambiguous for the compiler. */
	@Nonnull
	static <T> LObjIntLongPredicate<T> objIntLongPred(@Nullable Class<T> c1, final @Nonnull LObjIntLongPredicate<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T> LObjIntLongPredicate<T> recursive(final @Nonnull LFunction<LObjIntLongPredicate<T>, LObjIntLongPredicate<T>> selfLambda) {
		final LObjIntLongPredicateSingle<T> single = new LObjIntLongPredicateSingle();
		LObjIntLongPredicate<T> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LObjIntLongPredicateSingle<T> implements LObjIntLongPredicate<T> {
		private LObjIntLongPredicate<T> target = null;

		@Override
		public boolean testX(T a1, int a2, long a3) throws Throwable {
			return target.testX(a1, a2, a3);
		}

	}

	@Nonnull
	static <T> LObjIntLongPredicate<T> objIntLongPredThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T> LObjIntLongPredicate<T> objIntLongPredThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LObjIntLongPredicate.LObjLongIntPred<T> objLongIntPred(final @Nonnull LObjIntLongPredicate.LObjLongIntPred<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LObjIntLongPredicate.LIntObjLongPred<T> intObjLongPred(final @Nonnull LObjIntLongPredicate.LIntObjLongPred<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LObjIntLongPredicate.LIntLongObjPred<T> intLongObjPred(final @Nonnull LObjIntLongPredicate.LIntLongObjPred<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LObjIntLongPredicate.LLongObjIntPred<T> longObjIntPred(final @Nonnull LObjIntLongPredicate.LLongObjIntPred<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LObjIntLongPredicate.LLongIntObjPred<T> longIntObjPred(final @Nonnull LObjIntLongPredicate.LLongIntObjPred<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T> boolean call(T a1, int a2, long a3, final @Nonnull LObjIntLongPredicate<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.test(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as alwaysFalse). */
	@Nonnull
	static <T> LObjIntLongPredicate<T> safe() {
		return LObjIntLongPredicate::alwaysFalse;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LObjIntLongPredicate<T>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T> LObjIntLongPredicate<T> safe(final @Nullable LObjIntLongPredicate<T> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LObjIntLongPredicate<T>> safeSupplier(final @Nullable LSupplier<LObjIntLongPredicate<T>> supplier) {
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
	default LObjIntLongPredicate<T> negate() {
		return (a1, a2, a3) -> !test(a1, a2, a3);
	}

	@Nonnull
	static <T> LObjIntLongPredicate<T> not(@Nonnull LObjIntLongPredicate<T> pred) {
		Null.nonNullArg(pred, "pred");
		return pred.negate();
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LObjIntLongPredicate<T> and(@Nonnull LObjIntLongPredicate<? super T> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> test(a1, a2, a3) && other.test(a1, a2, a3);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LObjIntLongPredicate<T> or(@Nonnull LObjIntLongPredicate<? super T> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> test(a1, a2, a3) || other.test(a1, a2, a3);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LObjIntLongPredicate<T> xor(@Nonnull LObjIntLongPredicate<? super T> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> test(a1, a2, a3) ^ other.test(a1, a2, a3);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static <T> LObjIntLongPredicate<T> isEqual(T v1, int v2, long v3) {
		return (a1, a2, a3) -> (a1 == null ? v1 == null : a1.equals(v1)) && (a2 == v2) && (a3 == v3);
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1> LObjIntLongPredicate<V1> compose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2, @Nonnull final LLongUnaryOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.test(before1.apply(v1), before2.applyAsInt(v2), before3.applyAsLong(v3));
	}

	public static <V1, T> LObjIntLongPredicate<V1> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2, @Nonnull final LLongUnaryOperator before3, LObjIntLongPredicate<T> after) {
		return after.compose(before1, before2, before3);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriPredicate<V1, V2, V3> objIntLongPredCompose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2, @Nonnull final LToLongFunction<? super V3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.test(before1.apply(v1), before2.applyAsInt(v2), before3.applyAsLong(v3));
	}

	public static <V1, V2, V3, T> LTriPredicate<V1, V2, V3> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2, @Nonnull final LToLongFunction<? super V3> before3,
			LObjIntLongPredicate<T> after) {
		return after.objIntLongPredCompose(before1, before2, before3);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LObjIntLongFunction<T, V> boolToObjIntLongFunc(@Nonnull LBoolFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.apply(this.test(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LTieLongFunction<T> boolToTieLongFunc(@Nonnull LBoolToIntFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.applyAsInt(this.test(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LObjIntLongPredicate<T> boolToObjIntLongPred(@Nonnull LLogicalOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.apply(this.test(a1, a2, a3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LObjIntLongPredicate for method references. */
	@FunctionalInterface
	interface LObjLongIntPred<T> extends LObjIntLongPredicate<T> {

		/**
		 * Implement this, but call test(T a1,int a2,long a3)
		 */
		default boolean testX(T a1, int a2, long a3) {
			return this.testObjLongInt(a1, a3, a2);
		}

		// boolean testObjLongInt(T a1,long a3,int a2) ;
		default boolean testObjLongInt(T a1, long a3, int a2) {
			// return nestingTestObjLongInt(a1,a3,a2);
			try {
				return this.testObjLongIntX(a1, a3, a2);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call testObjLongInt(T a1,long a3,int a2)
		 */
		boolean testObjLongIntX(T a1, long a3, int a2) throws Throwable;
	}

	/** Permutation of LObjIntLongPredicate for method references. */
	@FunctionalInterface
	interface LIntObjLongPred<T> extends LObjIntLongPredicate<T> {

		/**
		 * Implement this, but call testObjLongInt(T a1,long a3,int a2)
		 */
		default boolean testX(T a1, int a2, long a3) {
			return this.testIntObjLong(a2, a1, a3);
		}

		// boolean testIntObjLong(int a2,T a1,long a3) ;
		default boolean testIntObjLong(int a2, T a1, long a3) {
			// return nestingTestIntObjLong(a2,a1,a3);
			try {
				return this.testIntObjLongX(a2, a1, a3);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call testIntObjLong(int a2,T a1,long a3)
		 */
		boolean testIntObjLongX(int a2, T a1, long a3) throws Throwable;
	}

	/** Permutation of LObjIntLongPredicate for method references. */
	@FunctionalInterface
	interface LIntLongObjPred<T> extends LObjIntLongPredicate<T> {

		/**
		 * Implement this, but call testIntObjLong(int a2,T a1,long a3)
		 */
		default boolean testX(T a1, int a2, long a3) {
			return this.testIntLongObj(a2, a3, a1);
		}

		// boolean testIntLongObj(int a2,long a3,T a1) ;
		default boolean testIntLongObj(int a2, long a3, T a1) {
			// return nestingTestIntLongObj(a2,a3,a1);
			try {
				return this.testIntLongObjX(a2, a3, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call testIntLongObj(int a2,long a3,T a1)
		 */
		boolean testIntLongObjX(int a2, long a3, T a1) throws Throwable;
	}

	/** Permutation of LObjIntLongPredicate for method references. */
	@FunctionalInterface
	interface LLongObjIntPred<T> extends LObjIntLongPredicate<T> {

		/**
		 * Implement this, but call testIntLongObj(int a2,long a3,T a1)
		 */
		default boolean testX(T a1, int a2, long a3) {
			return this.testLongObjInt(a3, a1, a2);
		}

		// boolean testLongObjInt(long a3,T a1,int a2) ;
		default boolean testLongObjInt(long a3, T a1, int a2) {
			// return nestingTestLongObjInt(a3,a1,a2);
			try {
				return this.testLongObjIntX(a3, a1, a2);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call testLongObjInt(long a3,T a1,int a2)
		 */
		boolean testLongObjIntX(long a3, T a1, int a2) throws Throwable;
	}

	/** Permutation of LObjIntLongPredicate for method references. */
	@FunctionalInterface
	interface LLongIntObjPred<T> extends LObjIntLongPredicate<T> {

		/**
		 * Implement this, but call testLongObjInt(long a3,T a1,int a2)
		 */
		default boolean testX(T a1, int a2, long a3) {
			return this.testLongIntObj(a3, a2, a1);
		}

		// boolean testLongIntObj(long a3,int a2,T a1) ;
		default boolean testLongIntObj(long a3, int a2, T a1) {
			// return nestingTestLongIntObj(a3,a2,a1);
			try {
				return this.testLongIntObjX(a3, a2, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call testLongIntObj(long a3,int a2,T a1)
		 */
		boolean testLongIntObjX(long a3, int a2, T a1) throws Throwable;
	}

	// </editor-fold>

	// >>> LObjIntLongPredicate<T>

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(T a1, int a2, long a3) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(T a1, int a2, long a3) {
		return false;
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, C3> void filterForEach(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, IndexedRead<C3, aLong> ia3, C3 source3, LTieLongConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToLongFunction<Object> oiFunc3 = (LOiToLongFunction) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = oiFunc2.applyAsInt(source2, i);
			long a3 = oiFunc3.applyAsLong(source3, i);
			doIf(a1, a2, a3, consumer);
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, C3> void filterIterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, IndexedRead<C3, aLong> ia3, C3 source3, LTieLongConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToLongFunction<Object> oiFunc3 = (LOiToLongFunction) ia3.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = oiFunc2.applyAsInt(source2, i);
			long a3 = oiFunc3.applyAsLong(source3, i);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2, C3> void filterIterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, IndexedRead<C3, aLong> ia3, C3 source3, LTieLongConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		size = Integer.min(size, ia3.size(source3));
		LOiToLongFunction<Object> oiFunc3 = (LOiToLongFunction) ia3.getter();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = nextFunc2.applyAsInt(iterator2);
			long a3 = oiFunc3.applyAsLong(source3, i);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, I2, C3> void filterIterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, IndexedRead<C3, aLong> ia3, C3 source3, LTieLongConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		int size = ia3.size(source3);
		LOiToLongFunction<Object> oiFunc3 = (LOiToLongFunction) ia3.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && i < size) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = nextFunc2.applyAsInt(iterator2);
			long a3 = oiFunc3.applyAsLong(source3, i);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, C3, I3> void filterIterate(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, SequentialRead<C3, I3, aLong> sa3, C3 source3, LTieLongConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToLongFunction<Object> nextFunc3 = (LToLongFunction) sa3.supplier();
		int i = 0;
		while (i < size && testFunc3.test(iterator3)) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = oiFunc2.applyAsInt(source2, i);
			long a3 = nextFunc3.applyAsLong(iterator3);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, C3, I3> void filterIterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, SequentialRead<C3, I3, aLong> sa3, C3 source3, LTieLongConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToLongFunction<Object> nextFunc3 = (LToLongFunction) sa3.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size && testFunc3.test(iterator3)) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = oiFunc2.applyAsInt(source2, i);
			long a3 = nextFunc3.applyAsLong(iterator3);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2, C3, I3> void filterIterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, SequentialRead<C3, I3, aLong> sa3, C3 source3, LTieLongConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToLongFunction<Object> nextFunc3 = (LToLongFunction) sa3.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = nextFunc2.applyAsInt(iterator2);
			long a3 = nextFunc3.applyAsLong(iterator3);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C1, I1, C2, I2, C3, I3> void filterIterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, SequentialRead<C3, I3, aLong> sa3, C3 source3, LTieLongConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToLongFunction<Object> nextFunc3 = (LToLongFunction) sa3.supplier();
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = nextFunc2.applyAsInt(iterator2);
			long a3 = nextFunc3.applyAsLong(iterator3);
			doIf(a1, a2, a3, consumer);
		}
	}

}
