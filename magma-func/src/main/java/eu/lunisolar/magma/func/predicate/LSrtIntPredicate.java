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
 * Non-throwing functional interface (lambda) LSrtIntPredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 2): short a1,int a2
 *
 * Co-domain: boolean
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LSrtIntPredicate extends MetaPredicate, MetaInterface.NonThrowing, Codomain<aBool>, Domain2<aShort, aInt> { // NOSONAR

	String DESCRIPTION = "LSrtIntPredicate: boolean test(short a1,int a2)";

	// boolean test(short a1,int a2) ;
	default boolean test(short a1, int a2) {
		// return nestingTest(a1,a2);
		try {
			return this.testX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call test(short a1,int a2)
	 */
	boolean testX(short a1, int a2) throws Throwable;

	default boolean tupleTest(LSrtIntPair args) {
		return test(args.first(), args.second());
	}

	/** Function call that handles exceptions according to the instructions. */
	default boolean handlingTest(short a1, int a2, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.testX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LSrtIntPredicate handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return (a1, a2) -> handlingTest(a1, a2, handling);
	}

	default boolean test(short a1, int a2, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.testX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LSrtIntPredicate trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return (a1, a2) -> test(a1, a2, exF, newMessage, messageParams);
	}

	default boolean test(short a1, int a2, @Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.testX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LSrtIntPredicate trying(@Nonnull ExWF<RuntimeException> exF) {
		return (a1, a2) -> test(a1, a2, exF);
	}

	default boolean testThen(short a1, int a2, @Nonnull LPredicate<Throwable> handler) {
		try {
			return this.testX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.test(e);
		}
	}

	default LSrtIntPredicate tryingThen(@Nonnull LPredicate<Throwable> handler) {
		return (a1, a2) -> testThen(a1, a2, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingTest(short a1, int a2) {
		try {
			return this.testX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default boolean shovingTest(short a1, int a2) {
		try {
			return this.testX(a1, a2);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static boolean handlingTest(short a1, int a2, LSrtIntPredicate func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingTest(a1, a2, handling);
	}

	static boolean tryTest(short a1, int a2, LSrtIntPredicate func) {
		Null.nonNullArg(func, "func");
		return func.nestingTest(a1, a2);
	}

	static boolean tryTest(short a1, int a2, LSrtIntPredicate func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, exF, newMessage, messageParams);
	}

	static boolean tryTest(short a1, int a2, LSrtIntPredicate func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.test(a1, a2, exF);
	}

	static boolean tryTestThen(short a1, int a2, LSrtIntPredicate func, @Nonnull LPredicate<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.testThen(a1, a2, handler);
	}

	default boolean failSafeTest(short a1, int a2, @Nonnull LSrtIntPredicate failSafe) {
		try {
			return test(a1, a2);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.test(a1, a2);
		}
	}

	static boolean failSafeTest(short a1, int a2, LSrtIntPredicate func, @Nonnull LSrtIntPredicate failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.test(a1, a2);
		} else {
			return func.failSafeTest(a1, a2, failSafe);
		}
	}

	static LSrtIntPredicate failSafe(LSrtIntPredicate func, @Nonnull LSrtIntPredicate failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return (a1, a2) -> failSafeTest(a1, a2, func, failSafe);
	}

	default boolean doIf(short a1, int a2, LAction action) {
		Null.nonNullArg(action, "action");
		if (test(a1, a2)) {
			action.execute();
			return true;
		} else {
			return false;
		}
	}

	static boolean doIf(short a1, int a2, @Nonnull LSrtIntPredicate predicate, @Nonnull LAction action) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.doIf(a1, a2, action);
	}

	static boolean doIf(short a1, int a2, @Nonnull LSrtIntPredicate predicate, @Nonnull LSrtIntConsumer consumer) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.doIf(a1, a2, consumer);
	}

	default boolean doIf(short a1, int a2, @Nonnull LSrtIntConsumer consumer) {
		Null.nonNullArg(consumer, "consumer");
		if (test(a1, a2)) {
			consumer.accept(a1, a2);
			return true;
		} else {
			return false;
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullTest(short a1, int a2) {
		return test(a1, a2);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(short a1, int a2) {
		return test(a1, a2);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LSrtIntPredicate.DESCRIPTION;
	}

	public default <V> boolean doIf(V a1, int a2, short a3, @Nonnull LTieSrtConsumer<? super V> consumer) {
		Null.nonNullArg(consumer, "consumer");
		if (test(a3, a2)) {
			consumer.accept(a1, a2, a3);
			return true;
		} else {
			return false;
		}
	}

	public default <V> int doIf(V a1, int a2, short a3, @Nonnull LTieSrtFunction<? super V> consumer) {
		Null.nonNullArg(consumer, "consumer");
		if (test(a3, a2)) {
			return consumer.applyAsInt(a1, a2, a3);
		} else {
			return 0;
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_a2, int max_a2, short a1, @Nonnull LSrtIntPredicate func) {
		Null.nonNullArg(func, "func");
		if (min_a2 <= max_a2) {
			for (int a2 = min_a2; a2 <= max_a2; a2++) {
				func.test(a1, a2);
			}
		} else {
			for (int a2 = min_a2; a2 >= max_a2; a2--) {
				func.test(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTill(int min_a2, int max_a2, short a1, @Nonnull LSrtIntPredicate func) {
		Null.nonNullArg(func, "func");
		if (min_a2 <= max_a2) {
			for (int a2 = min_a2; a2 < max_a2; a2++) {
				func.test(a1, a2);
			}
		} else {
			for (int a2 = min_a2; a2 > max_a2; a2--) {
				func.test(a1, a2);
			}
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void times(int max_a2, short a1, @Nonnull LSrtIntPredicate func) {
		if (max_a2 < 0)
			return;
		fromTill(0, max_a2, a1, func);
	}

	public default LIntPredicate lShrink(@Nonnull LIntToSrtFunction left) {
		Null.nonNullArg(left, "left");
		return a2 -> test(left.applyAsSrt(a2), a2);
	}

	public default LIntPredicate lShrink_(short a1) {
		return a2 -> test(a1, a2);
	}

	public static LIntPredicate lShrunken(@Nonnull LIntToSrtFunction left, @Nonnull LSrtIntPredicate func) {
		Null.nonNullArg(left, "left");
		Null.nonNullArg(func, "func");
		return func.lShrink(left);
	}

	public static LIntPredicate lShrunken_(short a1, @Nonnull LSrtIntPredicate func) {
		Null.nonNullArg(func, "func");
		return func.lShrink_(a1);
	}

	public default LSrtPredicate rShrink(@Nonnull LSrtToIntFunction right) {
		Null.nonNullArg(right, "right");
		return a1 -> test(a1, right.applyAsInt(a1));
	}

	public default LSrtPredicate rShrink_(int a2) {
		return a1 -> test(a1, a2);
	}

	public static LSrtPredicate rShrunken(@Nonnull LSrtToIntFunction right, @Nonnull LSrtIntPredicate func) {
		Null.nonNullArg(right, "right");
		Null.nonNullArg(func, "func");
		return func.rShrink(right);
	}

	public static LSrtPredicate rShrunken_(int a2, @Nonnull LSrtIntPredicate func) {
		Null.nonNullArg(func, "func");
		return func.rShrink_(a2);
	}

	/**  */
	public static LSrtIntPredicate uncurry(@Nonnull LSrtFunction<LIntPredicate> func) {
		Null.nonNullArg(func, "func");
		return (short a1, int a2) -> func.apply(a1).test(a2);
	}

	/** Change function to consumer that ignores output. */
	public default LSrtIntConsumer toConsumer() {
		return this::test;
	}

	/** Calls domain consumer before main function. */
	public default LSrtIntPredicate beforeDo(@Nonnull LSrtIntConsumer before) {
		Null.nonNullArg(before, "before");
		return (short a1, int a2) -> {
			before.accept(a1, a2);
			return test(a1, a2);
		};
	}

	/** Calls codomain consumer after main function. */
	public default LSrtIntPredicate afterDo(@Nonnull LBoolConsumer after) {
		Null.nonNullArg(after, "after");
		return (short a1, int a2) -> {
			final boolean retval = test(a1, a2);
			after.accept(retval);
			return retval;
		};
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> short throwIf(short a1, int a2, @Nonnull LSrtIntPredicate pred, @Nonnull ExMF<X> factory, @Nonnull String newMessage, @Nonnull Object... messageParams) throws X {
		if (pred.test(a1, a2)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> short throwIf(short a1, @Nonnull LSrtIntPredicate pred, int a2, @Nonnull ExMF<X> factory, @Nonnull String newMessage, @Nonnull Object... messageParams) throws X {
		if (pred.test(a1, a2)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
		return a1;
	}

	/** Throws new exception if condition is not met. */
	public static <X extends Throwable> short throwIfNot(short a1, int a2, @Nonnull LSrtIntPredicate pred, @Nonnull ExMF<X> factory, @Nonnull String newMessage, @Nonnull Object... messageParams) throws X {
		if (!pred.test(a1, a2)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
		return a1;
	}

	/** Throws new exception if condition is not met. */
	public static <X extends Throwable> short throwIfNot(short a1, @Nonnull LSrtIntPredicate pred, int a2, @Nonnull ExMF<X> factory, @Nonnull String newMessage, @Nonnull Object... messageParams) throws X {
		if (!pred.test(a1, a2)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> short throwIf(short a1, int a2, @Nonnull LSrtIntPredicate pred, @Nonnull ExMF<X> factory, @Nonnull String newMessage) throws X {
		if (pred.test(a1, a2)) {
			throw Handling.create(factory, newMessage);
		}
		return a1;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> short throwIf(short a1, @Nonnull LSrtIntPredicate pred, int a2, @Nonnull ExMF<X> factory, @Nonnull String newMessage) throws X {
		if (pred.test(a1, a2)) {
			throw Handling.create(factory, newMessage);
		}
		return a1;
	}

	/** Throws new exception if condition is not met. */
	public static <X extends Throwable> short throwIfNot(short a1, int a2, @Nonnull LSrtIntPredicate pred, @Nonnull ExMF<X> factory, @Nonnull String newMessage) throws X {
		if (!pred.test(a1, a2)) {
			throw Handling.create(factory, newMessage);
		}
		return a1;
	}

	/** Throws new exception if condition is not met. */
	public static <X extends Throwable> short throwIfNot(short a1, @Nonnull LSrtIntPredicate pred, int a2, @Nonnull ExMF<X> factory, @Nonnull String newMessage) throws X {
		if (!pred.test(a1, a2)) {
			throw Handling.create(factory, newMessage);
		}
		return a1;
	}

	/**
	* Throws new exception if condition is met.
	* Message will be formatted with predicate arguments.
	*/
	public static <X extends Throwable> short throwIf$(short a1, int a2, @Nonnull LSrtIntPredicate pred, @Nonnull ExMF<X> factory, @Nonnull String newMessage) throws X {
		if (pred.test(a1, a2)) {
			throw Handling.create(factory, newMessage, a1, a2);
		}
		return a1;
	}

	/**
	* Throws new exception if condition is met.
	* Message will be formatted with predicate arguments.
	*/
	public static <X extends Throwable> short throwIf$(short a1, @Nonnull LSrtIntPredicate pred, int a2, @Nonnull ExMF<X> factory, @Nonnull String newMessage) throws X {
		if (pred.test(a1, a2)) {
			throw Handling.create(factory, newMessage, a1, a2);
		}
		return a1;
	}

	/**
	* Throws new exception if condition is not met.
	* Message will be formatted with predicate arguments.
	*/
	public static <X extends Throwable> short throwIfNot$(short a1, int a2, @Nonnull LSrtIntPredicate pred, @Nonnull ExMF<X> factory, @Nonnull String newMessage) throws X {
		if (!pred.test(a1, a2)) {
			throw Handling.create(factory, newMessage, a1, a2);
		}
		return a1;
	}

	/**
	* Throws new exception if condition is not met.
	* Message will be formatted with predicate arguments.
	*/
	public static <X extends Throwable> short throwIfNot$(short a1, @Nonnull LSrtIntPredicate pred, int a2, @Nonnull ExMF<X> factory, @Nonnull String newMessage) throws X {
		if (!pred.test(a1, a2)) {
			throw Handling.create(factory, newMessage, a1, a2);
		}
		return a1;
	}

	/** Captures arguments but delays the evaluation. */
	default LBoolSupplier capture(short a1, int a2) {
		return () -> this.test(a1, a2);
	}

	/** Creates function that always returns the same value. */
	static LSrtIntPredicate constant(boolean r) {
		return (a1, a2) -> r;
	}

	/** Captures single parameter function into this interface where only 1st parameter will be used. */
	@Nonnull
	static LSrtIntPredicate test1st(@Nonnull LSrtPredicate func) {
		return (a1, a2) -> func.test(a1);
	}

	/** Captures single parameter function into this interface where only 2nd parameter will be used. */
	@Nonnull
	static LSrtIntPredicate test2nd(@Nonnull LIntPredicate func) {
		return (a1, a2) -> func.test(a2);
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LSrtIntPredicate srtIntPred(final @Nonnull LSrtIntPredicate lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LSrtIntPredicate recursive(final @Nonnull LFunction<LSrtIntPredicate, LSrtIntPredicate> selfLambda) {
		final LSrtIntPredicateSingle single = new LSrtIntPredicateSingle();
		LSrtIntPredicate func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LSrtIntPredicateSingle implements LSrtIntPredicate {
		private LSrtIntPredicate target = null;

		@Override
		public boolean testX(short a1, int a2) throws Throwable {
			return target.testX(a1, a2);
		}

	}

	@Nonnull
	static LSrtIntPredicate srtIntPredThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LSrtIntPredicate srtIntPredThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return (a1, a2) -> {
			throw exF.produce(message);
		};
	}

	// <editor-fold desc="wrap variants">

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LSrtIntPredicate.LIntSrtPred intSrtPred(final @Nonnull LSrtIntPredicate.LIntSrtPred lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	// </editor-fold>

	static boolean call(short a1, int a2, final @Nonnull LSrtIntPredicate lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.test(a1, a2);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as alwaysFalse). */
	@Nonnull
	static LSrtIntPredicate safe() {
		return LSrtIntPredicate::alwaysFalse;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LSrtIntPredicate> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LSrtIntPredicate safe(final @Nullable LSrtIntPredicate other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LSrtIntPredicate> safeSupplier(final @Nullable LSupplier<LSrtIntPredicate> supplier) {
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
	default LSrtIntPredicate negate() {
		return (a1, a2) -> !test(a1, a2);
	}

	@Nonnull
	static LSrtIntPredicate not(@Nonnull LSrtIntPredicate pred) {
		Null.nonNullArg(pred, "pred");
		return pred.negate();
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LSrtIntPredicate and(@Nonnull LSrtIntPredicate other) {
		Null.nonNullArg(other, "other");
		return (a1, a2) -> test(a1, a2) && other.test(a1, a2);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LSrtIntPredicate or(@Nonnull LSrtIntPredicate other) {
		Null.nonNullArg(other, "other");
		return (a1, a2) -> test(a1, a2) || other.test(a1, a2);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LSrtIntPredicate xor(@Nonnull LSrtIntPredicate other) {
		Null.nonNullArg(other, "other");
		return (a1, a2) -> test(a1, a2) ^ other.test(a1, a2);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static LSrtIntPredicate isEqual(short v1, int v2) {
		return (a1, a2) -> (a1 == v1) && (a2 == v2);
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LSrtIntPredicate compose(@Nonnull final LSrtUnaryOperator before1, @Nonnull final LIntUnaryOperator before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.test(before1.applyAsSrt(v1), before2.applyAsInt(v2));
	}

	public static LSrtIntPredicate composed(@Nonnull final LSrtUnaryOperator before1, @Nonnull final LIntUnaryOperator before2, LSrtIntPredicate after) {
		return after.compose(before1, before2);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V1, V2> LBiPredicate<V1, V2> srtIntPredCompose(@Nonnull final LToSrtFunction<? super V1> before1, @Nonnull final LToIntFunction<? super V2> before2) {
		Null.nonNullArg(before1, "before1");
		Null.nonNullArg(before2, "before2");
		return (v1, v2) -> this.test(before1.applyAsSrt(v1), before2.applyAsInt(v2));
	}

	public static <V1, V2> LBiPredicate<V1, V2> composed(@Nonnull final LToSrtFunction<? super V1> before1, @Nonnull final LToIntFunction<? super V2> before2, LSrtIntPredicate after) {
		return after.srtIntPredCompose(before1, before2);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default LSrtIntPredicate boolToSrtIntPred(@Nonnull LLogicalOperator after) {
		Null.nonNullArg(after, "after");
		return (a1, a2) -> after.apply(this.test(a1, a2));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	// <editor-fold desc="interface variants">

	/** Permutation of LSrtIntPredicate for method references. */
	@FunctionalInterface
	interface LIntSrtPred extends LSrtIntPredicate {

		/**
		 * Implement this, but call test(short a1,int a2)
		 */
		default boolean testX(short a1, int a2) {
			return this.testIntSrt(a2, a1);
		}

		// boolean testIntSrt(int a2,short a1) ;
		default boolean testIntSrt(int a2, short a1) {
			// return nestingTestIntSrt(a2,a1);
			try {
				return this.testIntSrtX(a2, a1);
			} catch (Throwable e) { // NOSONAR
				throw Handling.nestCheckedAndThrow(e);
			}
		}

		/**
		 * Implement this, but call testIntSrt(int a2,short a1)
		 */
		boolean testIntSrtX(int a2, short a1) throws Throwable;
	}

	// </editor-fold>

	// >>> LSrtIntPredicate

	/** Returns TRUE. */
	public static boolean alwaysTrue(short a1, int a2) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(short a1, int a2) {
		return false;
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2> void filterForEach(IndexedRead<C1, aShort> ia1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, LSrtIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiToSrtFunction<Object> oiFunc1 = (LOiToSrtFunction) ia1.getter();
		size = Integer.min(size, ia2.size(source2));
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		int i = 0;
		for (; i < size; i++) {
			short a1 = oiFunc1.applyAsSrt(source1, i);
			int a2 = oiFunc2.applyAsInt(source2, i);
			doIf(a1, a2, consumer);
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, I1, C2> void filterIterate(SequentialRead<C1, I1, aShort> sa1, C1 source1, IndexedRead<C2, aInt> ia2, C2 source2, LSrtIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToSrtFunction<Object> nextFunc1 = (LToSrtFunction) sa1.supplier();
		int size = ia2.size(source2);
		LOiToIntFunction<Object> oiFunc2 = (LOiToIntFunction) ia2.getter();
		int i = 0;
		while (testFunc1.test(iterator1) && i < size) {
			short a1 = nextFunc1.applyAsSrt(iterator1);
			int a2 = oiFunc2.applyAsInt(source2, i);
			doIf(a1, a2, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C1, C2, I2> void filterIterate(IndexedRead<C1, aShort> ia1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, LSrtIntConsumer consumer) {
		int size = ia1.size(source1);
		LOiToSrtFunction<Object> oiFunc1 = (LOiToSrtFunction) ia1.getter();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		int i = 0;
		while (i < size && testFunc2.test(iterator2)) {
			short a1 = oiFunc1.applyAsSrt(source1, i);
			int a2 = nextFunc2.applyAsInt(iterator2);
			doIf(a1, a2, consumer);
			i++;
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C1, I1, C2, I2> void filterIterate(SequentialRead<C1, I1, aShort> sa1, C1 source1, SequentialRead<C2, I2, aInt> sa2, C2 source2, LSrtIntConsumer consumer) {
		Object iterator1 = ((LFunction) sa1.adapter()).apply(source1);
		LPredicate<Object> testFunc1 = (LPredicate) sa1.tester();
		LToSrtFunction<Object> nextFunc1 = (LToSrtFunction) sa1.supplier();
		Object iterator2 = ((LFunction) sa2.adapter()).apply(source2);
		LPredicate<Object> testFunc2 = (LPredicate) sa2.tester();
		LToIntFunction<Object> nextFunc2 = (LToIntFunction) sa2.supplier();
		while (testFunc1.test(iterator1) && testFunc2.test(iterator2)) {
			short a1 = nextFunc1.applyAsSrt(iterator1);
			int a2 = nextFunc2.applyAsInt(iterator2);
			doIf(a1, a2, consumer);
		}
	}

}
