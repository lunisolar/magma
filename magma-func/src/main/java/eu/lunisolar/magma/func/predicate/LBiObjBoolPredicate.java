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
 * Non-throwing functional interface (lambda) LBiObjBoolPredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 3): T1 a1,T2 a2,boolean a3
 *
 * Co-domain: boolean
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBiObjBoolPredicate<T1, T2> extends MetaPredicate, MetaInterface.NonThrowing, Codomain<aBool>, Domain3<a<T1>, a<T2>, aBool> { // NOSONAR

	String DESCRIPTION = "LBiObjBoolPredicate: boolean test(T1 a1,T2 a2,boolean a3)";

	// boolean test(T1 a1,T2 a2,boolean a3) ;
	default boolean test(T1 a1, T2 a2, boolean a3) {
		// return nestingTest(a1,a2,a3);
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call test(T1 a1,T2 a2,boolean a3)
	 */
	boolean testX(T1 a1, T2 a2, boolean a3) throws Throwable;

	default boolean tupleTest(LBiObjBoolTriple<T1, T2> args) {
		return test(args.first(), args.second(), args.third());
	}

	/** Function call that handles exceptions according to the instructions. */
	default boolean handlingTest(T1 a1, T2 a2, boolean a3, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LBiObjBoolPredicate<T1, T2> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2, a3) -> handlingTest(a1, a2, a3, handling);
	}

	default boolean test(T1 a1, T2 a2, boolean a3, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LBiObjBoolPredicate<T1, T2> trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return (a1, a2, a3) -> test(a1, a2, a3, exF, newMessage, messageParams);
	}

	default boolean test(T1 a1, T2 a2, boolean a3, @Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LBiObjBoolPredicate<T1, T2> trying(@Nonnull ExWF<RuntimeException> exF) {
		return (a1, a2, a3) -> test(a1, a2, a3, exF);
	}

	default boolean testThen(T1 a1, T2 a2, boolean a3, @Nonnull LPredicate<Throwable> handler) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.test(e);
		}
	}

	default LBiObjBoolPredicate<T1, T2> tryingThen(@Nonnull LPredicate<Throwable> handler) {
		return (a1, a2, a3) -> testThen(a1, a2, a3, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingTest(T1 a1, T2 a2, boolean a3) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default boolean shovingTest(T1 a1, T2 a2, boolean a3) {
		try {
			return this.testX(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T1, T2> boolean handlingTest(T1 a1, T2 a2, boolean a3, LBiObjBoolPredicate<T1, T2> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingTest(a1, a2, a3, handling);
	}

	static <T1, T2> boolean tryTest(T1 a1, T2 a2, boolean a3, LBiObjBoolPredicate<T1, T2> func) {
		Null.nonNullArg(func, "func");
		return func.nestingTest(a1, a2, a3);
	}

	static <T1, T2> boolean tryTest(T1 a1, T2 a2, boolean a3, LBiObjBoolPredicate<T1, T2> func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, exF, newMessage, messageParams);
	}

	static <T1, T2> boolean tryTest(T1 a1, T2 a2, boolean a3, LBiObjBoolPredicate<T1, T2> func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, a3, exF);
	}

	static <T1, T2> boolean tryTestThen(T1 a1, T2 a2, boolean a3, LBiObjBoolPredicate<T1, T2> func, @Nonnull LPredicate<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.testThen(a1, a2, a3, handler);
	}

	default boolean failSafeTest(T1 a1, T2 a2, boolean a3, @Nonnull LBiObjBoolPredicate<T1, T2> failSafe) {
		try {
			return test(a1, a2, a3);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.test(a1, a2, a3);
		}
	}

	static <T1, T2> boolean failSafeTest(T1 a1, T2 a2, boolean a3, LBiObjBoolPredicate<T1, T2> func, @Nonnull LBiObjBoolPredicate<T1, T2> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.test(a1, a2, a3);
		} else {
			return func.failSafeTest(a1, a2, a3, failSafe);
		}
	}

	static <T1, T2> LBiObjBoolPredicate<T1, T2> failSafe(LBiObjBoolPredicate<T1, T2> func, @Nonnull LBiObjBoolPredicate<T1, T2> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2, a3) -> failSafeTest(a1, a2, a3, func, failSafe);
	}

	default boolean doIf(T1 a1, T2 a2, boolean a3, LAction action) {
		Null.nonNullArg(action, "action");
		if (test(a1, a2, a3)) {
			action.execute();
			return true;
		} else {
			return false;
		}
	}

	static <T1, T2> boolean doIf(T1 a1, T2 a2, boolean a3, @Nonnull LBiObjBoolPredicate<T1, T2> predicate, @Nonnull LAction action) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.doIf(a1, a2, a3, action);
	}

	static <T1, T2> boolean doIf(T1 a1, T2 a2, boolean a3, @Nonnull LBiObjBoolPredicate<T1, T2> predicate, @Nonnull LBiObjBoolConsumer<? super T1, ? super T2> consumer) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.doIf(a1, a2, a3, consumer);
	}

	default boolean doIf(T1 a1, T2 a2, boolean a3, @Nonnull LBiObjBoolConsumer<? super T1, ? super T2> consumer) {
		Null.nonNullArg(consumer, "consumer");
		if (test(a1, a2, a3)) {
			consumer.accept(a1, a2, a3);
			return true;
		} else {
			return false;
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullTest(T1 a1, T2 a2, boolean a3) {
		return test(a1, a2, a3);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(T1 a1, T2 a2, boolean a3) {
		return test(a1, a2, a3);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBiObjBoolPredicate.DESCRIPTION;
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T1, T2> void fromTo(int min_i, int max_i, T1 a1, T2 a2, boolean a3, @Nonnull LBiObjBoolPredicate<T1, T2> func) {
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
	public static <T1, T2> void fromTill(int min_i, int max_i, T1 a1, T2 a2, boolean a3, @Nonnull LBiObjBoolPredicate<T1, T2> func) {
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
	public static <T1, T2> void times(int max_i, T1 a1, T2 a2, boolean a3, @Nonnull LBiObjBoolPredicate<T1, T2> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a1, a2, a3, func);
	}

	/** Extract and apply function. */
	public static <M, K, V, T2> boolean from(@Nonnull M container, LBiFunction<M, K, V> extractor, K key, T2 a2, boolean a3, @Nonnull LBiObjBoolPredicate<V, T2> function) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.test(value, a2, a3);
		}

		return false;
	}

	public default LObjBoolPredicate<T2> lShrink(@Nonnull LObjBoolFunction<T2, T1> left) {
		Null.nonNullArg(left, "left");
		return (a2, a3) -> test(left.apply(a2, a3), a2, a3);
	}

	public default LObjBoolPredicate<T2> lShrink_(T1 a1) {
		return (a2, a3) -> test(a1, a2, a3);
	}

	public static <T2, T1> LObjBoolPredicate<T2> lShrunken(@Nonnull LObjBoolFunction<T2, T1> left, @Nonnull LBiObjBoolPredicate<T1, T2> func) {
		Null.nonNullArg(left, "left");
		Null.nonNullArg(func, "func");
		return func.lShrink(left);
	}

	public static <T2, T1> LObjBoolPredicate<T2> lShrunken_(T1 a1, @Nonnull LBiObjBoolPredicate<T1, T2> func) {
		Null.nonNullArg(func, "func");
		return func.lShrink_(a1);
	}

	public default LBiPredicate<T1, T2> rShrink(@Nonnull LBiPredicate<T1, T2> right) {
		Null.nonNullArg(right, "right");
		return (a1, a2) -> test(a1, a2, right.test(a1, a2));
	}

	public default LBiPredicate<T1, T2> rShrink_(boolean a3) {
		return (a1, a2) -> test(a1, a2, a3);
	}

	public static <T1, T2> LBiPredicate<T1, T2> rShrunken(@Nonnull LBiPredicate<T1, T2> right, @Nonnull LBiObjBoolPredicate<T1, T2> func) {
		Null.nonNullArg(right, "right");
		Null.nonNullArg(func, "func");
		return func.rShrink(right);
	}

	public static <T1, T2> LBiPredicate<T1, T2> rShrunken_(boolean a3, @Nonnull LBiObjBoolPredicate<T1, T2> func) {
		Null.nonNullArg(func, "func");
		return func.rShrink_(a3);
	}

	/**  */
	public static <T1, T2> LBiObjBoolPredicate<T1, T2> uncurry(@Nonnull LFunction<T1, LFunction<T2, LLogicalOperator>> func) {
		Null.nonNullArg(func, "func");
		return (T1 a1, T2 a2, boolean a3) -> func.apply(a1).apply(a2).apply(a3);
	}

	/** Cast that removes generics. */
	public default LBiObjBoolPredicate untyped() {
		return this;
	}

	/** Cast that replace generics. */
	public default <V2, V3> LBiObjBoolPredicate<V2, V3> cast() {
		return untyped();
	}

	/** Cast that replace generics. */
	public static <V2, V3, T1, T2> LBiObjBoolPredicate<V2, V3> cast(LBiObjBoolPredicate<T1, T2> function) {
		return (LBiObjBoolPredicate) function;
	}

	/** Change function to consumer that ignores output. */
	public default LBiObjBoolConsumer<T1, T2> toConsumer() {
		return this::test;
	}

	/** Calls domain consumer before main function. */
	public default LBiObjBoolPredicate<T1, T2> beforeDo(@Nonnull LBiObjBoolConsumer<T1, T2> before) {
		Null.nonNullArg(before, "before");
		return (T1 a1, T2 a2, boolean a3) -> {
			before.accept(a1, a2, a3);
			return test(a1, a2, a3);
		};
	}

	/** Calls codomain consumer after main function. */
	public default LBiObjBoolPredicate<T1, T2> afterDo(@Nonnull LBoolConsumer after) {
		Null.nonNullArg(after, "after");
		return (T1 a1, T2 a2, boolean a3) -> {
			final boolean retval = test(a1, a2, a3);
			after.accept(retval);
			return retval;
		};
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2> T1 throwIf(T1 a1, T2 a2, boolean a3, @Nonnull LBiObjBoolPredicate<T1, T2> pred, @Nonnull ExMF<RuntimeException> factory, @Nonnull String newMessage, @Nonnull Object... messageParams) {
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2> T1 throwIf(T1 a1, @Nonnull LBiObjBoolPredicate<T1, T2> pred, T2 a2, boolean a3, @Nonnull ExMF<RuntimeException> factory, @Nonnull String newMessage, @Nonnull Object... messageParams) {
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
		return a1;
	}

	/** Throws new exception if condition is not met. */
	public static <T1, T2> T1 throwIfNot(T1 a1, T2 a2, boolean a3, @Nonnull LBiObjBoolPredicate<T1, T2> pred, @Nonnull ExMF<RuntimeException> factory, @Nonnull String newMessage, @Nonnull Object... messageParams) {
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
		return a1;
	}

	/** Throws new exception if condition is not met. */
	public static <T1, T2> T1 throwIfNot(T1 a1, @Nonnull LBiObjBoolPredicate<T1, T2> pred, T2 a2, boolean a3, @Nonnull ExMF<RuntimeException> factory, @Nonnull String newMessage, @Nonnull Object... messageParams) {
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2> T1 throwIf(T1 a1, T2 a2, boolean a3, @Nonnull LBiObjBoolPredicate<T1, T2> pred, @Nonnull ExMF<RuntimeException> factory, @Nonnull String newMessage) {
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, newMessage);
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <T1, T2> T1 throwIf(T1 a1, @Nonnull LBiObjBoolPredicate<T1, T2> pred, T2 a2, boolean a3, @Nonnull ExMF<RuntimeException> factory, @Nonnull String newMessage) {
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, newMessage);
		}
		return a1;
	}

	/** Throws new exception if condition is not met. */
	public static <T1, T2> T1 throwIfNot(T1 a1, T2 a2, boolean a3, @Nonnull LBiObjBoolPredicate<T1, T2> pred, @Nonnull ExMF<RuntimeException> factory, @Nonnull String newMessage) {
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, newMessage);
		}
		return a1;
	}

	/** Throws new exception if condition is not met. */
	public static <T1, T2> T1 throwIfNot(T1 a1, @Nonnull LBiObjBoolPredicate<T1, T2> pred, T2 a2, boolean a3, @Nonnull ExMF<RuntimeException> factory, @Nonnull String newMessage) {
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, newMessage);
		}
		return a1;
	}

	/**
	* Throws new exception if condition is met.
	* Message will be formatted with predicate arguments.
	*/
	public static <T1, T2> T1 throwIf$(T1 a1, T2 a2, boolean a3, @Nonnull LBiObjBoolPredicate<T1, T2> pred, @Nonnull ExMF<RuntimeException> factory, @Nonnull String newMessage) {
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, newMessage, a1, a2, a3);
		}
		return a1;
	}

	/**
	* Throws new exception if condition is met.
	* Message will be formatted with predicate arguments.
	*/
	public static <T1, T2> T1 throwIf$(T1 a1, @Nonnull LBiObjBoolPredicate<T1, T2> pred, T2 a2, boolean a3, @Nonnull ExMF<RuntimeException> factory, @Nonnull String newMessage) {
		if (pred.test(a1, a2, a3)) {
			throw Handling.create(factory, newMessage, a1, a2, a3);
		}
		return a1;
	}

	/**
	* Throws new exception if condition is not met.
	* Message will be formatted with predicate arguments.
	*/
	public static <T1, T2> T1 throwIfNot$(T1 a1, T2 a2, boolean a3, @Nonnull LBiObjBoolPredicate<T1, T2> pred, @Nonnull ExMF<RuntimeException> factory, @Nonnull String newMessage) {
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, newMessage, a1, a2, a3);
		}
		return a1;
	}

	/**
	* Throws new exception if condition is not met.
	* Message will be formatted with predicate arguments.
	*/
	public static <T1, T2> T1 throwIfNot$(T1 a1, @Nonnull LBiObjBoolPredicate<T1, T2> pred, T2 a2, boolean a3, @Nonnull ExMF<RuntimeException> factory, @Nonnull String newMessage) {
		if (!pred.test(a1, a2, a3)) {
			throw Handling.create(factory, newMessage, a1, a2, a3);
		}
		return a1;
	}

	/** Captures arguments but delays the evaluation. */
	default LBoolSupplier capture(T1 a1, T2 a2, boolean a3) {
		return () -> this.test(a1, a2, a3);
	}

	/** Creates function that always returns the same value. */
	static <T1, T2> LBiObjBoolPredicate<T1, T2> constant(boolean r) {
		return (a1, a2, a3) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static <T1, T2> LBiObjBoolPredicate<T1, T2> test1st(@Nonnull LPredicate<T1> func) {
		return (a1, a2, a3) -> func.test(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static <T1, T2> LBiObjBoolPredicate<T1, T2> test2nd(@Nonnull LPredicate<T2> func) {
		return (a1, a2, a3) -> func.test(a2);
	}

	/** Captures single parameter function into this interface where only 3rd parameter will be used. */
	@Nonnull
	static <T1, T2> LBiObjBoolPredicate<T1, T2> test3rd(@Nonnull LLogicalOperator func) {
		return (a1, a2, a3) -> func.apply(a3);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2> LBiObjBoolPredicate<T1, T2> biObjBoolPred(final @Nonnull LBiObjBoolPredicate<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** A completely inconvenient method in case lambda expression and generic arguments are ambiguous for the compiler. */
	@Nonnull
	static <T1, T2> LBiObjBoolPredicate<T1, T2> biObjBoolPred(@Nullable Class<T1> c1, @Nullable Class<T2> c2, final @Nonnull LBiObjBoolPredicate<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T1, T2> LBiObjBoolPredicate<T1, T2> recursive(final @Nonnull LFunction<LBiObjBoolPredicate<T1, T2>, LBiObjBoolPredicate<T1, T2>> selfLambda) {
		final LBiObjBoolPredicateSingle<T1, T2> single = new LBiObjBoolPredicateSingle();
		LBiObjBoolPredicate<T1, T2> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LBiObjBoolPredicateSingle<T1, T2> implements LSingle<LBiObjBoolPredicate<T1, T2>>, LBiObjBoolPredicate<T1, T2> {
		private LBiObjBoolPredicate<T1, T2> target = null;

		@Override
		public boolean testX(T1 a1, T2 a2, boolean a3) throws Throwable {
			return target.testX(a1, a2, a3);
		}

		@Override
		public LBiObjBoolPredicate<T1, T2> value() {
			return target;
		}
	}

	@Nonnull
	static <T1, T2> LBiObjBoolPredicate<T1, T2> biObjBoolPredThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T1, T2> LBiObjBoolPredicate<T1, T2> biObjBoolPredThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2, a3) -> {
			throw exF.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2> LObj0Bool2Obj1Pred<T1, T2> obj0Bool2Obj1Pred(final @Nonnull LObj0Bool2Obj1Pred<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1> LObj1Obj0Bool2Pred<T2, T1> obj1Obj0Bool2Pred(final @Nonnull LObj1Obj0Bool2Pred<T2, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1> LObj1Bool2Obj0Pred<T2, T1> obj1Bool2Obj0Pred(final @Nonnull LObj1Bool2Obj0Pred<T2, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T1, T2> LBool2Obj0Obj1Pred<T1, T2> bool2Obj0Obj1Pred(final @Nonnull LBool2Obj0Obj1Pred<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T2, T1> LBool2Obj1Obj0Pred<T2, T1> bool2Obj1Obj0Pred(final @Nonnull LBool2Obj1Obj0Pred<T2, T1> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static <T1, T2> boolean call(T1 a1, T2 a2, boolean a3, final @Nonnull LBiObjBoolPredicate<T1, T2> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.test(a1, a2, a3);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as alwaysFalse). */
	@Nonnull
	static <T1, T2> LBiObjBoolPredicate<T1, T2> safe() {
		return LBiObjBoolPredicate::alwaysFalse;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T1, T2> LSupplier<LBiObjBoolPredicate<T1, T2>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T1, T2> LBiObjBoolPredicate<T1, T2> safe(final @Nullable LBiObjBoolPredicate<T1, T2> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T1, T2> LSupplier<LBiObjBoolPredicate<T1, T2>> safeSupplier(final @Nullable LSupplier<LBiObjBoolPredicate<T1, T2>> supplier) {
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
	default LBiObjBoolPredicate<T1, T2> negate() {
		return (a1, a2, a3) -> !test(a1, a2, a3);
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LBiObjBoolPredicate<T1, T2> and(@Nonnull LBiObjBoolPredicate<? super T1, ? super T2> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> test(a1, a2, a3) && other.test(a1, a2, a3);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LBiObjBoolPredicate<T1, T2> or(@Nonnull LBiObjBoolPredicate<? super T1, ? super T2> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> test(a1, a2, a3) || other.test(a1, a2, a3);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LBiObjBoolPredicate<T1, T2> xor(@Nonnull LBiObjBoolPredicate<? super T1, ? super T2> other) {
		Null.nonNullArg(other, "other");
		return (a1, a2, a3) -> test(a1, a2, a3) ^ other.test(a1, a2, a3);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static <T1, T2> LBiObjBoolPredicate<T1, T2> isEqual(T1 v1, T2 v2, boolean v3) {
		return (a1, a2, a3) -> (a1 == null ? v1 == null : a1.equals(v1)) && (a2 == null ? v2 == null : a2.equals(v2)) && (a3 == v3);
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiObjBoolPredicate<V1, V2> compose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LLogicalOperator before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.test(before1.apply(v1), before2.apply(v2), before3.apply(v3));
	}

	public static <V1, V2, T1, T2> LBiObjBoolPredicate<V1, V2> composed(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LLogicalOperator before3,
			LBiObjBoolPredicate<T1, T2> after) {
		return after.compose(before1, before2, before3);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2, V3> LTriPredicate<V1, V2, V3> biObjBoolPredCompose(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LPredicate<? super V3> before3) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		Null.nonNullArg(before3, "before3");
		return (v1, v2, v3) -> this.test(before1.apply(v1), before2.apply(v2), before3.test(v3));
	}

	public static <V1, V2, V3, T1, T2> LTriPredicate<V1, V2, V3> composed(@Nonnull final LFunction<? super V1, ? extends T1> before1, @Nonnull final LFunction<? super V2, ? extends T2> before2, @Nonnull final LPredicate<? super V3> before3,
			LBiObjBoolPredicate<T1, T2> after) {
		return after.biObjBoolPredCompose(before1, before2, before3);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LBiObjBoolFunction<T1, T2, V> boolToBiObjBoolFunc(@Nonnull LBoolFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.apply(this.test(a1, a2, a3));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBiObjBoolPredicate<T1, T2> boolToBiObjBoolPred(@Nonnull LLogicalOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2, a3) -> after.apply(this.test(a1, a2, a3));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LBiObjBoolPredicate for method references. */
	@FunctionalInterface
	interface LObj0Bool2Obj1Pred<T1, T2> extends LBiObjBoolPredicate<T1, T2> {

		boolean testObj0Bool2Obj1(T1 a1, boolean a3, T2 a2);

		@Override
		default boolean testX(T1 a1, T2 a2, boolean a3) {
			return this.testObj0Bool2Obj1(a1, a3, a2);
		}
	}

	/** Permutation of LBiObjBoolPredicate for method references. */
	@FunctionalInterface
	interface LObj1Obj0Bool2Pred<T2, T1> extends LBiObjBoolPredicate<T1, T2> {

		boolean testObj1Obj0Bool2(T2 a2, T1 a1, boolean a3);

		@Override
		default boolean testX(T1 a1, T2 a2, boolean a3) {
			return this.testObj1Obj0Bool2(a2, a1, a3);
		}
	}

	/** Permutation of LBiObjBoolPredicate for method references. */
	@FunctionalInterface
	interface LObj1Bool2Obj0Pred<T2, T1> extends LBiObjBoolPredicate<T1, T2> {

		boolean testObj1Bool2Obj0(T2 a2, boolean a3, T1 a1);

		@Override
		default boolean testX(T1 a1, T2 a2, boolean a3) {
			return this.testObj1Bool2Obj0(a2, a3, a1);
		}
	}

	/** Permutation of LBiObjBoolPredicate for method references. */
	@FunctionalInterface
	interface LBool2Obj0Obj1Pred<T1, T2> extends LBiObjBoolPredicate<T1, T2> {

		boolean testBool2Obj0Obj1(boolean a3, T1 a1, T2 a2);

		@Override
		default boolean testX(T1 a1, T2 a2, boolean a3) {
			return this.testBool2Obj0Obj1(a3, a1, a2);
		}
	}

	/** Permutation of LBiObjBoolPredicate for method references. */
	@FunctionalInterface
	interface LBool2Obj1Obj0Pred<T2, T1> extends LBiObjBoolPredicate<T1, T2> {

		boolean testBool2Obj1Obj0(boolean a3, T2 a2, T1 a1);

		@Override
		default boolean testX(T1 a1, T2 a2, boolean a3) {
			return this.testBool2Obj1Obj0(a3, a2, a1);
		}
	}

	// </editor-fold>

	// >>> LBiObjBoolPredicate<T1,T2>

	/** Returns TRUE. */
	public static <T1, T2> boolean alwaysTrue(T1 a1, T2 a2, boolean a3) {
		return true;
	}

	/** Returns FALSE. */
	public static <T1, T2> boolean alwaysFalse(T1 a1, T2 a2, boolean a3) {
		return false;
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, C3> void filterForEach(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, aBool> ia3, C3 source3, LBiObjBoolConsumer<? super T1, ? super T2> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LObjIntPredicate<Object> oiFunc3 = (LObjIntPredicate) ia3.getter();
		int i = 0;
		for (; i < size; i++) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = oiFunc2.apply(source2, i);
			boolean a3 = oiFunc3.test(source3, i);
			doIf(a1, a2, a3, consumer);
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, C3> void filterIterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, IndexedRead<C3, aBool> ia3, C3 source3, LBiObjBoolConsumer<? super T1, ? super T2> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		size = Integer.min(size, ia3.size(source3));
		LObjIntPredicate<Object> oiFunc3 = (LObjIntPredicate) ia3.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = oiFunc2.apply(source2, i);
			boolean a3 = oiFunc3.test(source3, i);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2, C3> void filterIterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, aBool> ia3, C3 source3, LBiObjBoolConsumer<? super T1, ? super T2> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		size = Integer.min(size, ia3.size(source3));
		LObjIntPredicate<Object> oiFunc3 = (LObjIntPredicate) ia3.getter();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = nextFunc2.apply(iterator2);
			boolean a3 = oiFunc3.test(source3, i);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, I2, C3> void filterIterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, IndexedRead<C3, aBool> ia3, C3 source3, LBiObjBoolConsumer<? super T1, ? super T2> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		int size = ia3.size(source3);
		LObjIntPredicate<Object> oiFunc3 = (LObjIntPredicate) ia3.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && i < size) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = nextFunc2.apply(iterator2);
			boolean a3 = oiFunc3.test(source3, i);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, C3, I3> void filterIterate(IndexedRead<C1, a<T1>> ia1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, aBool> sa3, C3 source3, LBiObjBoolConsumer<? super T1, ? super T2> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LPredicate<Object> nextFunc3 = (LPredicate) sa3.supplier();
		int i = 0;
		while (i < size && testFunc3.test(iterator3)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = oiFunc2.apply(source2, i);
			boolean a3 = nextFunc3.test(iterator3);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2, C3, I3> void filterIterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, IndexedRead<C2, a<T2>> ia2, C2 source2, SequentialRead<C3, I3, aBool> sa3, C3 source3, LBiObjBoolConsumer<? super T1, ? super T2> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiFunction<Object, T2> oiFunc2 = (LOiFunction) ia2.getter();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LPredicate<Object> nextFunc3 = (LPredicate) sa3.supplier();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size && testFunc3.test(iterator3)) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = oiFunc2.apply(source2, i);
			boolean a3 = nextFunc3.test(iterator3);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2, C3, I3> void filterIterate(IndexedRead<C1, a<T1>> ia1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, aBool> sa3, C3 source3, LBiObjBoolConsumer<? super T1, ? super T2> consumer) {
		int size = ia1.size(source1);
		LOiFunction<Object, T1> oiFunc1 = (LOiFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LPredicate<Object> nextFunc3 = (LPredicate) sa3.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			T1 a1 = oiFunc1.apply(source1, i);
			T2 a2 = nextFunc2.apply(iterator2);
			boolean a3 = nextFunc3.test(iterator3);
			doIf(a1, a2, a3, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C1, I1, C2, I2, C3, I3> void filterIterate(SequentialRead<C1, I1, a<T1>> sa1, C1 source1, SequentialRead<C2, I2, a<T2>> sa2, C2 source2, SequentialRead<C3, I3, aBool> sa3, C3 source3, LBiObjBoolConsumer<? super T1, ? super T2> consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LFunction<Object, T1> nextFunc1 = (LFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LFunction<Object, T2> nextFunc2 = (LFunction) sa2.supplier();
		Object iterator3 = ((LFunction) sa3.adapter()).apply(source3);
		LPredicate<Object> testFunc3 = (LPredicate) sa3.tester();
		LPredicate<Object> nextFunc3 = (LPredicate) sa3.supplier();
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2) && testFunc3.test(iterator3)) {
			T1 a1 = nextFunc1.apply(iterator1);
			T2 a2 = nextFunc2.apply(iterator2);
			boolean a3 = nextFunc3.test(iterator3);
			doIf(a1, a2, a3, consumer);
		}
	}

}
