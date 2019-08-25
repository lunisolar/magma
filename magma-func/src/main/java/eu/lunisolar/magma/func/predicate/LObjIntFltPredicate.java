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
 * Non-throwing functional interface (lambda) LObjIntFltPredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 3): T a1,int a2,float a3
 *
 * Co-domain: boolean
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LObjIntFltPredicate<T> extends MetaPredicate, MetaInterface.NonThrowing, Codomain<aBool>, Domain3<a<T>, aInt, aFloat> { // NOSONAR

	String DESCRIPTION = "LObjIntFltPredicate: boolean test(T a1,int a2,float a3)";

	// boolean test(T a1,int a2,float a3) ;
	default boolean test(T a1, int a2, float a3) {
		// return nestingTest(a1,a2,a3);
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call test(T a1,int a2,float a3)
	 */
	boolean testX(T a1, int a2, float a3) throws Throwable;

	default boolean tupleTest(LObjIntFltTriple<T> args) {
		return test(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions according to the instructions. */
	default boolean handlingTest(T a1, int a2, float a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LObjIntFltPredicate<T> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> handlingTest(a1, a2, a3, handling);
	}

	default boolean test(T a1, int a2, float a3, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LObjIntFltPredicate<T> trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return (a1, a2, a3) -> test(a1, a2, a3, exF, newMessage, messageParams);
	}

	default boolean test(T a1, int a2, float a3, @Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LObjIntFltPredicate<T> trying(@Nonnull ExWF<RuntimeException> exF) {
		return (a1, a2, a3) -> test(a1, a2, a3, exF);
	}

	default boolean testThen(T a1, int a2, float a3, @Nonnull LPredicate<Throwable> handler) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.test(e);
		}
	}

	default LObjIntFltPredicate<T> tryingThen(@Nonnull LPredicate<Throwable> handler) {
		return (a1, a2, a3) -> testThen(a1, a2, a3, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingTest(T a1, int a2, float a3) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default boolean shovingTest(T a1, int a2, float a3) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> boolean handlingTest(T a1, int a2, float a3, LObjIntFltPredicate<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingTest(a1, a2, a3, handling);
	}

	static <T> boolean tryTest(T a1, int a2, float a3, LObjIntFltPredicate<T> func) {
		Null.nonNullArg(func, "func");
		return func.nestingTest(a1, a2, a3);
	}

	static <T> boolean tryTest(T a1, int a2, float a3, LObjIntFltPredicate<T> func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, exF, newMessage, messageParams);
	}

	static <T> boolean tryTest(T a1, int a2, float a3, LObjIntFltPredicate<T> func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, exF);
	}

	static <T> boolean tryTestThen(T a1, int a2, float a3, LObjIntFltPredicate<T> func, @Nonnull LPredicate<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.testThen(a1, a2, a3, handler);
	}

	default boolean failSafeTest(T a1, int a2, float a3, @Nonnull LObjIntFltPredicate<T> failSafe) {
		try {
			return test(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.test(a1, a2, a3);
		}
	}

	static <T> boolean failSafeTest(T a1, int a2, float a3, LObjIntFltPredicate<T> func, @Nonnull LObjIntFltPredicate<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.test(a1, a2, a3);
		} else {
			return func.failSafeTest(a1, a2, a3, failSafe);
		}
	}

	static <T> LObjIntFltPredicate<T> failSafe(LObjIntFltPredicate<T> func, @Nonnull LObjIntFltPredicate<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeTest(a1, a2, a3, func, failSafe);
	}

	default boolean doIf(T a1, int a2, float a3, LAction action) {
		Null.nonNullArg(action, "action");
		if (test(a1, a2, a3)) {
			action.execute();
			return true;
		} else {
			return false;
		}
	}

	static <T> boolean doIf(T a1, int a2, float a3, @Nonnull LObjIntFltPredicate<T> predicate, @Nonnull LAction action) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.doIf(a1, a2, a3, action);
	}

	static <T> boolean doIf(T a1, int a2, float a3, @Nonnull LObjIntFltPredicate<T> predicate, @Nonnull LTieFltConsumer<? super T> consumer) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.doIf(a1, a2, a3, consumer);
	}

	default boolean doIf(T a1, int a2, float a3, @Nonnull LTieFltConsumer<? super T> consumer) {
		Null.nonNullArg(consumer, "consumer");
		if (test(a1, a2, a3)) {
			consumer.accept(a1, a2, a3);
			return true;
		} else {
			return false;
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullTest(T a1, int a2, float a3) {
		return test(a1, a2, a3);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(T a1, int a2, float a3) {
		return test(a1, a2, a3);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LObjIntFltPredicate.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_a2, int max_a2, T a1, float a3, @Nonnull LObjIntFltPredicate<T> func) {
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
	public static <T> void fromTill(int min_a2, int max_a2, T a1, float a3, @Nonnull LObjIntFltPredicate<T> func) {
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
	public static <T> void times(int max_a2, T a1, float a3, @Nonnull LObjIntFltPredicate<T> func) {
		if (max_a2 < 0)
			return;
		fromTill(0, max_a2, a1, a3, func);
	}

	/** Extract and apply function. */
	public static <M, K, V> boolean from(@Nonnull M container, LBiFunction<M, K, V> extractor, K key, int a2, float a3, @Nonnull LObjIntFltPredicate<V> function) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.test(value, a2, a3);
		}

		return false;
	}

	/**  */
	public static <T> LObjIntFltPredicate<T> uncurry(@Nonnull LFunction<T, LIntFunction<LFltPredicate>> func) {
		Null.nonNullArg(func, "func");
		return (T a1, int a2, float a3) -> func.apply(a1).apply(a2).test(a3);
	}

	/** Cast that removes generics. */
	public default LObjIntFltPredicate untyped() {
		return this;
	}

	/** Cast that replace generics. */
	public default <V2> LObjIntFltPredicate<V2> cast() {
		return untyped();
	}

	/** Cast that replace generics. */
	public static <V2, T> LObjIntFltPredicate<V2> cast(LObjIntFltPredicate<T> function) {
		return (LObjIntFltPredicate) function;
	}

	/** Change function to consumer that ignores output. */
	public default LTieFltConsumer<T> toConsumer() {
		return this::test;
	}

	/** Calls domain consumer before main function. */
	public default LObjIntFltPredicate<T> beforeDo(@Nonnull LTieFltConsumer<T> before) {
		Null.nonNullArg(before, "before");
		return (T a1, int a2, float a3) -> {
			before.accept(a1, a2, a3);
			return test(a1, a2, a3);
		};
	}

	/** Calls codomain consumer after main function. */
	public default LObjIntFltPredicate<T> afterDo(@Nonnull LBoolConsumer after) {
		Null.nonNullArg(after, "after");
		return (T a1, int a2, float a3) -> {
			final boolean retval = test(a1, a2, a3);
			after.accept(retval);
			return retval;
		};
	}

	/** Throws new exception if condition is met. */
	public static <T> T throwIf(T a1, int a2, float a3, @Nonnull LObjIntFltPredicate<T> pred, @Nonnull ExMF<RuntimeException> factory, @Nonnull String newMessage, @Nonnull Object... messageParams) {
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T> T throwIf(T a1, @Nonnull LObjIntFltPredicate<T> pred, int a2, float a3, @Nonnull ExMF<RuntimeException> factory, @Nonnull String newMessage, @Nonnull Object... messageParams) {
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
		return a1;
	}

	/** Throws new exception if condition is not met. */
	public static <T> T throwIfNot(T a1, int a2, float a3, @Nonnull LObjIntFltPredicate<T> pred, @Nonnull ExMF<RuntimeException> factory, @Nonnull String newMessage, @Nonnull Object... messageParams) {
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
		return a1;
	}

	/** Throws new exception if condition is not met. */
	public static <T> T throwIfNot(T a1, @Nonnull LObjIntFltPredicate<T> pred, int a2, float a3, @Nonnull ExMF<RuntimeException> factory, @Nonnull String newMessage, @Nonnull Object... messageParams) {
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T> T throwIf(T a1, int a2, float a3, @Nonnull LObjIntFltPredicate<T> pred, @Nonnull ExMF<RuntimeException> factory, @Nonnull String newMessage) {
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, newMessage);
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T> T throwIf(T a1, @Nonnull LObjIntFltPredicate<T> pred, int a2, float a3, @Nonnull ExMF<RuntimeException> factory, @Nonnull String newMessage) {
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, newMessage);
		}
		return a1;
	}

	/** Throws new exception if condition is not met. */
	public static <T> T throwIfNot(T a1, int a2, float a3, @Nonnull LObjIntFltPredicate<T> pred, @Nonnull ExMF<RuntimeException> factory, @Nonnull String newMessage) {
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, newMessage);
		}
		return a1;
	}

	/** Throws new exception if condition is not met. */
	public static <T> T throwIfNot(T a1, @Nonnull LObjIntFltPredicate<T> pred, int a2, float a3, @Nonnull ExMF<RuntimeException> factory, @Nonnull String newMessage) {
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, newMessage);
		}
		return a1;
	}

	/**
	* Throws new exception if condition is met.
	* Message will be formatted with predicate arguments.
	*/
	public static <T> T throwIf$(T a1, int a2, float a3, @Nonnull LObjIntFltPredicate<T> pred, @Nonnull ExMF<RuntimeException> factory, @Nonnull String newMessage) {
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, newMessage, a1, a2, a3);
		}
		return a1;
	}

	/**
	* Throws new exception if condition is met.
	* Message will be formatted with predicate arguments.
	*/
	public static <T> T throwIf$(T a1, @Nonnull LObjIntFltPredicate<T> pred, int a2, float a3, @Nonnull ExMF<RuntimeException> factory, @Nonnull String newMessage) {
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, newMessage, a1, a2, a3);
		}
		return a1;
	}

	/**
	* Throws new exception if condition is not met.
	* Message will be formatted with predicate arguments.
	*/
	public static <T> T throwIfNot$(T a1, int a2, float a3, @Nonnull LObjIntFltPredicate<T> pred, @Nonnull ExMF<RuntimeException> factory, @Nonnull String newMessage) {
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, newMessage, a1, a2, a3);
		}
		return a1;
	}

	/**
	* Throws new exception if condition is not met.
	* Message will be formatted with predicate arguments.
	*/
	public static <T> T throwIfNot$(T a1, @Nonnull LObjIntFltPredicate<T> pred, int a2, float a3, @Nonnull ExMF<RuntimeException> factory, @Nonnull String newMessage) {
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, newMessage, a1, a2, a3);
		}
		return a1;
	}

	/** Captures arguments but delays the evaluation. */
	default LBoolSupplier capture(T a1, int a2, float a3) {
		return () -> this.test(a1, a2, a3);
	}

	/** Creates function that always returns the same value. */
	static <T> LObjIntFltPredicate<T> constant(boolean r) {
		return (a1, a2, a3) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T> LObjIntFltPredicate<T> test1st(@Nonnull LPredicate<T> func) {
		return (a1, a2, a3) -> func.test(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T> LObjIntFltPredicate<T> test2nd(@Nonnull LIntPredicate func) {
		return (a1, a2, a3) -> func.test(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T> LObjIntFltPredicate<T> test3rd(@Nonnull LFltPredicate func) {
		return (a1, a2, a3) -> func.test(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LObjIntFltPredicate<T> objIntFltPred(final @Nonnull LObjIntFltPredicate<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** A completely inconvenient method in case lambda expression and generic arguments are ambiguous for the compiler. */
	@Nonnull
	static <T> LObjIntFltPredicate<T> objIntFltPred(@Nullable Class<T> c1, final @Nonnull LObjIntFltPredicate<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T> LObjIntFltPredicate<T> recursive(final @Nonnull LFunction<LObjIntFltPredicate<T>, LObjIntFltPredicate<T>> selfLambda) {
		final LObjIntFltPredicateSingle<T> single = new LObjIntFltPredicateSingle();
		LObjIntFltPredicate<T> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LObjIntFltPredicateSingle<T> implements LSingle<LObjIntFltPredicate<T>>, LObjIntFltPredicate<T> {
		private LObjIntFltPredicate<T> target = null;

		@Override
		public boolean testX(T a1, int a2, float a3) throws Throwable {
			return target.testX(a1, a2, a3);
		}

		@Override
		public LObjIntFltPredicate<T> value() {
			return target;
		}
	}

	@Nonnull
	static <T> LObjIntFltPredicate<T> objIntFltPredThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T> LObjIntFltPredicate<T> objIntFltPredThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LObjFltIntPred<T> objFltIntPred(final @Nonnull LObjFltIntPred<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LIntObjFltPred<T> intObjFltPred(final @Nonnull LIntObjFltPred<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LIntFltObjPred<T> intFltObjPred(final @Nonnull LIntFltObjPred<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LFltObjIntPred<T> fltObjIntPred(final @Nonnull LFltObjIntPred<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LFltIntObjPred<T> fltIntObjPred(final @Nonnull LFltIntObjPred<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T> boolean call(T a1, int a2, float a3, final @Nonnull LObjIntFltPredicate<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.test(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as alwaysFalse). */
	@Nonnull
	static <T> LObjIntFltPredicate<T> safe() {
		return LObjIntFltPredicate::alwaysFalse;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LObjIntFltPredicate<T>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T> LObjIntFltPredicate<T> safe(final @Nullable LObjIntFltPredicate<T> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LObjIntFltPredicate<T>> safeSupplier(final @Nullable LSupplier<LObjIntFltPredicate<T>> supplier) {
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
	default LObjIntFltPredicate<T> negate() {
		return (a1, a2, a3) -> !test(a1, a2, a3);
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LObjIntFltPredicate<T> and(@Nonnull LObjIntFltPredicate<? super T> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> test(a1, a2, a3) && other.test(a1, a2, a3);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LObjIntFltPredicate<T> or(@Nonnull LObjIntFltPredicate<? super T> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> test(a1, a2, a3) || other.test(a1, a2, a3);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LObjIntFltPredicate<T> xor(@Nonnull LObjIntFltPredicate<? super T> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> test(a1, a2, a3) ^ other.test(a1, a2, a3);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static <T> LObjIntFltPredicate<T> isEqual(T v1, int v2, float v3) {
		return (a1, a2, a3) -> (a1 == null ? v1 == null : a1.equals(v1)) && (a2 == v2) && (a3 == v3);
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1> LObjIntFltPredicate<V1> compose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2, @Nonnull final LFltUnaryOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.test(before1.apply(v1), before2.applyAsInt(v2), before3.applyAsFlt(v3));
	}

	public static <V1, T> LObjIntFltPredicate<V1> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LIntUnaryOperator before2, @Nonnull final LFltUnaryOperator before3, LObjIntFltPredicate<T> after) {
		return after.compose(before1, before2, before3);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriPredicate<V1, V2, V3> objIntFltPredCompose(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2, @Nonnull final LToFltFunction<? super V3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.test(before1.apply(v1), before2.applyAsInt(v2), before3.applyAsFlt(v3));
	}

	public static <V1, V2, V3, T> LTriPredicate<V1, V2, V3> composed(@Nonnull final LFunction<? super V1, ? extends T> before1, @Nonnull final LToIntFunction<? super V2> before2, @Nonnull final LToFltFunction<? super V3> before3,
			LObjIntFltPredicate<T> after) {
		return after.objIntFltPredCompose(before1, before2, before3);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LObjIntFltFunction<T, V> boolToObjIntFltFunc(@Nonnull LBoolFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.apply(this.test(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LTieFltFunction<T> boolToTieFltFunc(@Nonnull LBoolToIntFunction after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.applyAsInt(this.test(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LObjIntFltPredicate<T> boolToObjIntFltPred(@Nonnull LLogicalOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.apply(this.test(a1, a2, a3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LObjIntFltPredicate for method references. */
	@FunctionalInterface
	interface LObjFltIntPred<T> extends LObjIntFltPredicate<T> {

		boolean testObjFltInt(T a1, float a3, int a2);

		@Override
		default boolean testX(T a1, int a2, float a3) {
			return this.testObjFltInt(a1, a3, a2);
		}
	}

	/** Permutation of LObjIntFltPredicate for method references. */
	@FunctionalInterface
	interface LIntObjFltPred<T> extends LObjIntFltPredicate<T> {

		boolean testIntObjFlt(int a2, T a1, float a3);

		@Override
		default boolean testX(T a1, int a2, float a3) {
			return this.testIntObjFlt(a2, a1, a3);
		}
	}

	/** Permutation of LObjIntFltPredicate for method references. */
	@FunctionalInterface
	interface LIntFltObjPred<T> extends LObjIntFltPredicate<T> {

		boolean testIntFltObj(int a2, float a3, T a1);

		@Override
		default boolean testX(T a1, int a2, float a3) {
			return this.testIntFltObj(a2, a3, a1);
		}
	}

	/** Permutation of LObjIntFltPredicate for method references. */
	@FunctionalInterface
	interface LFltObjIntPred<T> extends LObjIntFltPredicate<T> {

		boolean testFltObjInt(float a3, T a1, int a2);

		@Override
		default boolean testX(T a1, int a2, float a3) {
			return this.testFltObjInt(a3, a1, a2);
		}
	}

	/** Permutation of LObjIntFltPredicate for method references. */
	@FunctionalInterface
	interface LFltIntObjPred<T> extends LObjIntFltPredicate<T> {

		boolean testFltIntObj(float a3, int a2, T a1);

		@Override
		default boolean testX(T a1, int a2, float a3) {
			return this.testFltIntObj(a3, a2, a1);
		}
	}

	// </editor-fold>

	// >>> LObjIntFltPredicate<T>

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(T a1, int a2, float a3) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(T a1, int a2, float a3) {
		return false;
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, C3> void filterForEach(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, IndexedRead<C3, aFloat> ia3, C3 source3, LTieFltConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToFltFunction<Object> oiFunc3 = (LOiToFltFunction) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = oiFunc2.applyAsInt(source2, i);
			float a3 = oiFunc3.applyAsFlt(source3, i);
			doIf(a1, a2, a3, consumer);
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, C3> void filterIterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, IndexedRead<C3, aFloat> ia3, C3 source3, LTieFltConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LOiToFltFunction<Object> oiFunc3 = (LOiToFltFunction) ia3.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = oiFunc2.applyAsInt(source2, i);
			float a3 = oiFunc3.applyAsFlt(source3, i);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2, C3> void filterIterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, IndexedRead<C3, aFloat> ia3, C3 source3, LTieFltConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		size = Integer.min(size, ia3.size(source3));
		LOiToFltFunction<Object> oiFunc3 = (LOiToFltFunction) ia3.getter();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = nextFunc2.applyAsInt(iterator2);
			float a3 = oiFunc3.applyAsFlt(source3, i);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, I2, C3> void filterIterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, IndexedRead<C3, aFloat> ia3, C3 source3, LTieFltConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		int size = ia3.size(source3);
		LOiToFltFunction<Object> oiFunc3 = (LOiToFltFunction) ia3.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && i < size) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = nextFunc2.applyAsInt(iterator2);
			float a3 = oiFunc3.applyAsFlt(source3, i);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, C3, I3> void filterIterate(IndexedRead<C1, a<T>> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, SequentialRead<C3, I3, aFloat> sa3, C3 source3, LTieFltConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToFltFunction<Object> nextFunc3 = (LToFltFunction) sa3.supplier();
		int i = 0;
		while (i < size && testFunc3.test(iterator3)) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = oiFunc2.applyAsInt(source2, i);
			float a3 = nextFunc3.applyAsFlt(iterator3);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, C3, I3> void filterIterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, SequentialRead<C3, I3, aFloat> sa3, C3 source3, LTieFltConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToFltFunction<Object> nextFunc3 = (LToFltFunction) sa3.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size && testFunc3.test(iterator3)) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = oiFunc2.applyAsInt(source2, i);
			float a3 = nextFunc3.applyAsFlt(iterator3);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2, C3, I3> void filterIterate(IndexedRead<C1, a<T>> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, SequentialRead<C3, I3, aFloat> sa3, C3 source3, LTieFltConsumer<? super T> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToFltFunction<Object> nextFunc3 = (LToFltFunction) sa3.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			T a1 = oiFunc1.apply(source1, i);
			int a2 = nextFunc2.applyAsInt(iterator2);
			float a3 = nextFunc3.applyAsFlt(iterator3);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C1, I1, C2, I2, C3, I3> void filterIterate(SequentialRead<C1, I1, a<T>> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, SequentialRead<C3, I3, aFloat> sa3, C3 source3, LTieFltConsumer<? super T> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LToFltFunction<Object> nextFunc3 = (LToFltFunction) sa3.supplier();
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			T a1 = nextFunc1.apply(iterator1);
			int a2 = nextFunc2.applyAsInt(iterator2);
			float a3 = nextFunc3.applyAsFlt(iterator3);
			doIf(a1, a2, a3, consumer);
		}
	}

}
