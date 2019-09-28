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
 * Non-throwing functional interface (lambda) LPredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 1): T a
 *
 * Co-domain: boolean
 *
 * Special case of function that corresponds to expressions like (iterator) -> Iterator::next
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LPredicate<T> extends Predicate<T>, MetaPredicate, MetaInterface.NonThrowing, OFunction<T, aBool>, Codomain<aBool>, Domain1<a<T>> { // NOSONAR

	String DESCRIPTION = "LPredicate: boolean test(T a)";

	// boolean test(T a) ;
	default boolean test(T a) {
		// return nestingTest(a);
		try {
			return this.testX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call test(T a)
	 */
	boolean testX(T a) throws Throwable;

	default boolean tupleTest(LSingle<T> args) {
		return test(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default boolean handlingTest(T a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.testX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LPredicate<T> handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> handlingTest(a, handling);
	}

	default boolean test(T a, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.testX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LPredicate<T> trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return a -> test(a, exF, newMessage, messageParams);
	}

	default boolean test(T a, @Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.testX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LPredicate<T> trying(@Nonnull ExWF<RuntimeException> exF) {
		return a -> test(a, exF);
	}

	default boolean testThen(T a, @Nonnull LPredicate<Throwable> handler) {
		try {
			return this.testX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.test(e);
		}
	}

	default LPredicate<T> tryingThen(@Nonnull LPredicate<Throwable> handler) {
		return a -> testThen(a, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingTest(T a) {
		try {
			return this.testX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default boolean shovingTest(T a) {
		try {
			return this.testX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static <T> boolean handlingTest(T a, LPredicate<T> func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingTest(a, handling);
	}

	static <T> boolean tryTest(T a, LPredicate<T> func) {
		Null.nonNullArg(func, "func");
		return func.nestingTest(a);
	}

	static <T> boolean tryTest(T a, LPredicate<T> func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.test(a, exF, newMessage, messageParams);
	}

	static <T> boolean tryTest(T a, LPredicate<T> func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.test(a, exF);
	}

	static <T> boolean tryTestThen(T a, LPredicate<T> func, @Nonnull LPredicate<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.testThen(a, handler);
	}

	default boolean failSafeTest(T a, @Nonnull LPredicate<T> failSafe) {
		try {
			return test(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.test(a);
		}
	}

	static <T> boolean failSafeTest(T a, LPredicate<T> func, @Nonnull LPredicate<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.test(a);
		} else {
			return func.failSafeTest(a, failSafe);
		}
	}

	static <T> LPredicate<T> failSafe(LPredicate<T> func, @Nonnull LPredicate<T> failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeTest(a, func, failSafe);
	}

	default boolean doIf(T a, LAction action) {
		Null.nonNullArg(action, "action");
		if (test(a)) {
			action.execute();
			return true;
		} else {
			return false;
		}
	}

	static <T> boolean doIf(T a, @Nonnull LPredicate<T> predicate, @Nonnull LAction action) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.doIf(a, action);
	}

	static <T> boolean doIf(T a, @Nonnull LPredicate<T> predicate, @Nonnull LConsumer<? super T> consumer) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.doIf(a, consumer);
	}

	default boolean doIf(T a, @Nonnull LConsumer<? super T> consumer) {
		Null.nonNullArg(consumer, "consumer");
		if (test(a)) {
			consumer.accept(a);
			return true;
		} else {
			return false;
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullTest(T a) {
		return test(a);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(T a) {
		return test(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LPredicate.DESCRIPTION;
	}

	public default <V> boolean doIf(V a1, T a2, @Nonnull LBiConsumer<V, ? super T> consumer) {
		Null.nonNullArg(consumer, "consumer");
		if (test(a2)) {
			consumer.accept(a1, a2);
			return true;
		} else {
			return false;
		}
	}

	/** 2 */
	public default <V> int doIf(V a1, T a2, @Nonnull LToIntBiFunction<V, ? super T> consumer) {
		Null.nonNullArg(consumer, "consumer");
		if (test(a2)) {
			return consumer.applyAsInt(a1, a2);
		} else {
			return 0;
		}
	}

	public default <V> boolean doIf(V a1, T a2, int a3, @Nonnull LBiObjIntConsumer<? super V, ? super T> consumer) {
		Null.nonNullArg(consumer, "consumer");
		if (test(a2)) {
			consumer.accept(a1, a2, a3);
			return true;
		} else {
			return false;
		}
	}

	public default <V> boolean doIf(V a1, int a2, T a3, @Nonnull LTieConsumer<? super V, ? super T> consumer) {
		Null.nonNullArg(consumer, "consumer");
		if (test(a3)) {
			consumer.accept(a1, a2, a3);
			return true;
		} else {
			return false;
		}
	}

	public default <V> int doIf(V a1, int a2, T a3, @Nonnull LTieFunction<? super V, ? super T> consumer) {
		Null.nonNullArg(consumer, "consumer");
		if (test(a3)) {
			return consumer.applyAsInt(a1, a2, a3);
		} else {
			return 0;
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTo(int min_i, int max_i, T a, @Nonnull LPredicate<T> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i <= max_i; i++) {
				func.test(a);
			}
		} else {
			for (int i = min_i; i >= max_i; i--) {
				func.test(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void fromTill(int min_i, int max_i, T a, @Nonnull LPredicate<T> func) {
		Null.nonNullArg(func, "func");
		if (min_i <= max_i) {
			for (int i = min_i; i < max_i; i++) {
				func.test(a);
			}
		} else {
			for (int i = min_i; i > max_i; i--) {
				func.test(a);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static <T> void times(int max_i, T a, @Nonnull LPredicate<T> func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a, func);
	}

	/** Extract and apply function. */
	public static <M, K, V> boolean from(@Nonnull M container, LBiFunction<M, K, V> extractor, K key, @Nonnull LPredicate<V> function) {
		Null.nonNullArg(container, "container");
		Null.nonNullArg(function, "function");
		V value = extractor.apply(container, key);

		if (value != null) {
			return function.test(value);
		}

		return false;
	}

	/** Cast that removes generics. */
	public default LPredicate untyped() {
		return this;
	}

	/** Cast that replace generics. */
	public default <V2> LPredicate<V2> cast() {
		return untyped();
	}

	/** Cast that replace generics. */
	public static <V2, T> LPredicate<V2> cast(LPredicate<T> function) {
		return (LPredicate) function;
	}

	/** Change function to consumer that ignores output. */
	public default LConsumer<T> toConsumer() {
		return this::test;
	}

	/** Calls domain consumer before main function. */
	public default LPredicate<T> beforeDo(@Nonnull LConsumer<T> before) {
		Null.nonNullArg(before, "before");
		return (T a) -> {
			before.accept(a);
			return test(a);
		};
	}

	/** Calls codomain consumer after main function. */
	public default LPredicate<T> afterDo(@Nonnull LBoolConsumer after) {
		Null.nonNullArg(after, "after");
		return (T a) -> {
			final boolean retval = test(a);
			after.accept(retval);
			return retval;
		};
	}

	/** Throws new exception if condition is met. */
	public static <T> T throwIf(T a, @Nonnull LPredicate<T> pred, @Nonnull ExMF<RuntimeException> factory, @Nonnull String newMessage, @Nonnull Object... messageParams) {
		if (pred.test(a)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
		return a;
	}

	/** Throws new exception if condition is not met. */
	public static <T> T throwIfNot(T a, @Nonnull LPredicate<T> pred, @Nonnull ExMF<RuntimeException> factory, @Nonnull String newMessage, @Nonnull Object... messageParams) {
		if (!pred.test(a)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
		return a;
	}

	/** Throws new exception if condition is met. */
	public static <T> T throwIf(T a, @Nonnull LPredicate<T> pred, @Nonnull ExMF<RuntimeException> factory, @Nonnull String newMessage) {
		if (pred.test(a)) {
			throw Handling.create(factory, newMessage);
		}
		return a;
	}

	/** Throws new exception if condition is not met. */
	public static <T> T throwIfNot(T a, @Nonnull LPredicate<T> pred, @Nonnull ExMF<RuntimeException> factory, @Nonnull String newMessage) {
		if (!pred.test(a)) {
			throw Handling.create(factory, newMessage);
		}
		return a;
	}

	/**
	* Throws new exception if condition is met.
	* Message will be formatted with predicate arguments.
	*/
	public static <T> T throwIf$(T a, @Nonnull LPredicate<T> pred, @Nonnull ExMF<RuntimeException> factory, @Nonnull String newMessage) {
		if (pred.test(a)) {
			throw Handling.create(factory, newMessage, a);
		}
		return a;
	}

	/**
	* Throws new exception if condition is not met.
	* Message will be formatted with predicate arguments.
	*/
	public static <T> T throwIfNot$(T a, @Nonnull LPredicate<T> pred, @Nonnull ExMF<RuntimeException> factory, @Nonnull String newMessage) {
		if (!pred.test(a)) {
			throw Handling.create(factory, newMessage, a);
		}
		return a;
	}

	/** Captures arguments but delays the evaluation. */
	default LBoolSupplier capture(T a) {
		return () -> this.test(a);
	}

	/** Creates function that always returns the same value. */
	static <T> LPredicate<T> constant(boolean r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static <T> LPredicate<T> pred(final @Nonnull LPredicate<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	/** A completely inconvenient method in case lambda expression and generic arguments are ambiguous for the compiler. */
	@Nonnull
	static <T> LPredicate<T> pred(@Nullable Class<T> c1, final @Nonnull LPredicate<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static <T> LPredicate<T> recursive(final @Nonnull LFunction<LPredicate<T>, LPredicate<T>> selfLambda) {
		final LPredicateSingle<T> single = new LPredicateSingle();
		LPredicate<T> func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LPredicateSingle<T> implements LSingle<LPredicate<T>>, LPredicate<T> {
		private LPredicate<T> target = null;

		@Override
		public boolean testX(T a) throws Throwable {
			return target.testX(a);
		}

		@Override
		public LPredicate<T> value() {
			return target;
		}
	}

	@Nonnull
	static <T> LPredicate<T> predThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static <T> LPredicate<T> predThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce(message);
		};
	}

	static <T> boolean call(T a, final @Nonnull LPredicate<T> lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.test(a);
	}

	// <editor-fold desc="wrap">

	/** Wraps JRE instance. */
	@Nonnull
	static <T> LPredicate<T> wrap(final Predicate<T> other) {
		return other::test;
	}
	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as alwaysFalse). */
	@Nonnull
	static <T> LPredicate<T> safe() {
		return LPredicate::alwaysFalse;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LPredicate<T>> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static <T> LPredicate<T> safe(final @Nullable LPredicate<T> other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static <T> LSupplier<LPredicate<T>> safeSupplier(final @Nullable LSupplier<LPredicate<T>> supplier) {
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
	default LPredicate<T> negate() {
		return a -> !test(a);
	}

	@Nonnull
	static <T> LPredicate<T> not(@Nonnull LPredicate<T> pred) {
		Null.nonNullArg(pred, "pred");
		return pred.negate();
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LPredicate<T> and(@Nonnull LPredicate<? super T> other) {
		Null.nonNullArg(other, "other");
		return a -> test(a) && other.test(a);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LPredicate<T> or(@Nonnull LPredicate<? super T> other) {
		Null.nonNullArg(other, "other");
		return a -> test(a) || other.test(a);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LPredicate<T> xor(@Nonnull LPredicate<? super T> other) {
		Null.nonNullArg(other, "other");
		return a -> test(a) ^ other.test(a);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static <T> LPredicate<T> isEqual(T target) {
		return (null == target) ? Objects::isNull : object -> object.equals(target);
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LPredicate<V> compose(@Nonnull final LFunction<? super V, ? extends T> before) {
		Null.nonNullArg(before, "before");
		return v -> this.test(before.apply(v));
	}

	public static <V, T> LPredicate<V> composed(@Nonnull final LFunction<? super V, ? extends T> before, LPredicate<T> after) {
		return after.compose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LFunction<T, V> boolToFunc(@Nonnull LBoolFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.apply(this.test(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToByteFunction<T> boolToToByteFunc(@Nonnull LBoolToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsByte(this.test(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToSrtFunction<T> boolToToSrtFunc(@Nonnull LBoolToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsSrt(this.test(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToIntFunction<T> boolToToIntFunc(@Nonnull LBoolToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsInt(this.test(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToLongFunction<T> boolToToLongFunc(@Nonnull LBoolToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsLong(this.test(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToFltFunction<T> boolToToFltFunc(@Nonnull LBoolToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsFlt(this.test(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToDblFunction<T> boolToToDblFunc(@Nonnull LBoolToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsDbl(this.test(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LToCharFunction<T> boolToToCharFunc(@Nonnull LBoolToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsChar(this.test(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LPredicate<T> boolToPred(@Nonnull LLogicalOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.apply(this.test(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	// >>> LPredicate<T>

	/** Returns TRUE. */
	public static <T> boolean alwaysTrue(T a) {
		return true;
	}

	/** Returns FALSE. */
	public static <T> boolean alwaysFalse(T a) {
		return false;
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C0> void filterForEach(IndexedRead<C0, a<T>> ia, C0 source, LConsumer<? super T> consumer) {
		int size = ia.size(source);
		LOiFunction<Object, T> oiFunc0 = (LOiFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			T a = oiFunc0.apply(source, i);
			doIf(a, consumer);
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C0, I0> void filterIterate(SequentialRead<C0, I0, a<T>> sa, C0 source, LConsumer<? super T> consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).apply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LFunction<Object, T> nextFunc0 = (LFunction) sa.supplier();
		while (testFunc0.test(iterator0)) {
			T a = nextFunc0.apply(iterator0);
			doIf(a, consumer);
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with index) if predicate test passes. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns number of iterations that element (or tuple) was accepter by predicate.
	*/
	default <V, C0> int targetedForEach(V v, IndexedRead<C0, a<T>> ia, C0 source, LBiObjIntConsumer<V, T> consumer) {
		int size = ia.size(source);
		LOiFunction<Object, T> oiFunc0 = (LOiFunction) ia.getter();
		int acceptedIndex = 0;
		int i = 0;
		for (; i < size; i++) {
			T a = oiFunc0.apply(source, i);
			acceptedIndex += doIf(v, a, acceptedIndex, consumer) ? 1 : 0;
		}
		return acceptedIndex;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with index) if predicate test passes. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns number of iterations that element (or tuple) was accepter by predicate.
	*/
	default <V, C0, I0> int targetedIterate(V v, SequentialRead<C0, I0, a<T>> sa, C0 source, LBiObjIntConsumer<V, T> consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).apply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LFunction<Object, T> nextFunc0 = (LFunction) sa.supplier();
		int acceptedIndex = 0;
		int i = 0;
		while (testFunc0.test(iterator0)) {
			T a = nextFunc0.apply(iterator0);
			acceptedIndex += doIf(v, a, acceptedIndex, consumer) ? 1 : 0;
			i++;
		}
		return acceptedIndex;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with index) if predicate test passes. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns number of iterations that element (or tuple) was accepter by predicate.
	*/
	default <V, C0> int tieForEach(V v, IndexedRead<C0, a<T>> ia, C0 source, LTieConsumer<V, T> consumer) {
		int size = ia.size(source);
		LOiFunction<Object, T> oiFunc0 = (LOiFunction) ia.getter();
		int acceptedIndex = 0;
		int i = 0;
		for (; i < size; i++) {
			T a = oiFunc0.apply(source, i);
			acceptedIndex += doIf(v, acceptedIndex, a, consumer) ? 1 : 0;
		}
		return acceptedIndex;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with index) if predicate test passes. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns number of iterations that element (or tuple) was accepter by predicate.
	*/
	default <V, C0, I0> int tieIterate(V v, SequentialRead<C0, I0, a<T>> sa, C0 source, LTieConsumer<V, T> consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).apply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LFunction<Object, T> nextFunc0 = (LFunction) sa.supplier();
		int acceptedIndex = 0;
		int i = 0;
		while (testFunc0.test(iterator0)) {
			T a = nextFunc0.apply(iterator0);
			acceptedIndex += doIf(v, acceptedIndex, a, consumer) ? 1 : 0;
			i++;
		}
		return acceptedIndex;

	}

}
