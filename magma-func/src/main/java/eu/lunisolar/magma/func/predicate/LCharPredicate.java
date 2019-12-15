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
 * Non-throwing functional interface (lambda) LCharPredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 1): char a
 *
 * Co-domain: boolean
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LCharPredicate extends MetaPredicate, MetaInterface.NonThrowing, Codomain<aBool>, Domain1<aChar> { // NOSONAR

	String DESCRIPTION = "LCharPredicate: boolean test(char a)";

	// boolean test(char a) ;
	default boolean test(char a) {
		// return nestingTest(a);
		try {
			return this.testX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call test(char a)
	 */
	boolean testX(char a) throws Throwable;

	default boolean tupleTest(LCharSingle args) {
		return test(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default boolean handlingTest(char a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.testX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LCharPredicate handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> handlingTest(a, handling);
	}

	default boolean test(char a, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.testX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LCharPredicate trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return a -> test(a, exF, newMessage, messageParams);
	}

	default boolean test(char a, @Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.testX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LCharPredicate trying(@Nonnull ExWF<RuntimeException> exF) {
		return a -> test(a, exF);
	}

	default boolean testThen(char a, @Nonnull LPredicate<Throwable> handler) {
		try {
			return this.testX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.test(e);
		}
	}

	default LCharPredicate tryingThen(@Nonnull LPredicate<Throwable> handler) {
		return a -> testThen(a, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingTest(char a) {
		try {
			return this.testX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default boolean shovingTest(char a) {
		try {
			return this.testX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static boolean handlingTest(char a, LCharPredicate func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingTest(a, handling);
	}

	static boolean tryTest(char a, LCharPredicate func) {
		Null.nonNullArg(func, "func");
		return func.nestingTest(a);
	}

	static boolean tryTest(char a, LCharPredicate func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.test(a, exF, newMessage, messageParams);
	}

	static boolean tryTest(char a, LCharPredicate func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.test(a, exF);
	}

	static boolean tryTestThen(char a, LCharPredicate func, @Nonnull LPredicate<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.testThen(a, handler);
	}

	default boolean failSafeTest(char a, @Nonnull LCharPredicate failSafe) {
		try {
			return test(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.test(a);
		}
	}

	static boolean failSafeTest(char a, LCharPredicate func, @Nonnull LCharPredicate failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.test(a);
		} else {
			return func.failSafeTest(a, failSafe);
		}
	}

	static LCharPredicate failSafe(LCharPredicate func, @Nonnull LCharPredicate failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeTest(a, func, failSafe);
	}

	default boolean doIf(char a, LAction action) {
		Null.nonNullArg(action, "action");
		if (test(a)) {
			action.execute();
			return true;
		} else {
			return false;
		}
	}

	static boolean doIf(char a, @Nonnull LCharPredicate predicate, @Nonnull LAction action) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.doIf(a, action);
	}

	static boolean doIf(char a, @Nonnull LCharPredicate predicate, @Nonnull LCharConsumer consumer) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.doIf(a, consumer);
	}

	default boolean doIf(char a, @Nonnull LCharConsumer consumer) {
		Null.nonNullArg(consumer, "consumer");
		if (test(a)) {
			consumer.accept(a);
			return true;
		} else {
			return false;
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullTest(char a) {
		return test(a);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(char a) {
		return test(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LCharPredicate.DESCRIPTION;
	}

	public default <V> boolean doIf(V a1, char a2, @Nonnull LObjCharConsumer<V> consumer) {
		Null.nonNullArg(consumer, "consumer");
		if (test(a2)) {
			consumer.accept(a1, a2);
			return true;
		} else {
			return false;
		}
	}

	public default <V> boolean doIf(V a1, int a2, char a3, @Nonnull LTieCharConsumer<? super V> consumer) {
		Null.nonNullArg(consumer, "consumer");
		if (test(a3)) {
			consumer.accept(a1, a2, a3);
			return true;
		} else {
			return false;
		}
	}

	public default <V> int doIf(V a1, int a2, char a3, @Nonnull LTieCharFunction<? super V> consumer) {
		Null.nonNullArg(consumer, "consumer");
		if (test(a3)) {
			return consumer.applyAsInt(a1, a2, a3);
		} else {
			return 0;
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, char a, @Nonnull LCharPredicate func) {
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
	public static void fromTill(int min_i, int max_i, char a, @Nonnull LCharPredicate func) {
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
	public static void times(int max_i, char a, @Nonnull LCharPredicate func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a, func);
	}

	/** Change function to consumer that ignores output. */
	public default LCharConsumer toConsumer() {
		return this::test;
	}

	/** Calls domain consumer before main function. */
	public default LCharPredicate beforeDo(@Nonnull LCharConsumer before) {
		Null.nonNullArg(before, "before");
		return (char a) -> {
			before.accept(a);
			return test(a);
		};
	}

	/** Calls codomain consumer after main function. */
	public default LCharPredicate afterDo(@Nonnull LBoolConsumer after) {
		Null.nonNullArg(after, "after");
		return (char a) -> {
			final boolean retval = test(a);
			after.accept(retval);
			return retval;
		};
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> char throwIf(char a, @Nonnull LCharPredicate pred, @Nonnull ExMF<X> factory, @Nonnull String newMessage, @Nonnull Object... messageParams) throws X {
		if (pred.test(a)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
		return a;
	}

	/** Throws new exception if condition is not met. */
	public static <X extends Throwable> char throwIfNot(char a, @Nonnull LCharPredicate pred, @Nonnull ExMF<X> factory, @Nonnull String newMessage, @Nonnull Object... messageParams) throws X {
		if (!pred.test(a)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
		return a;
	}

	/** Throws new exception if condition is met. */
	public static <X extends Throwable> char throwIf(char a, @Nonnull LCharPredicate pred, @Nonnull ExMF<X> factory, @Nonnull String newMessage) throws X {
		if (pred.test(a)) {
			throw Handling.create(factory, newMessage);
		}
		return a;
	}

	/** Throws new exception if condition is not met. */
	public static <X extends Throwable> char throwIfNot(char a, @Nonnull LCharPredicate pred, @Nonnull ExMF<X> factory, @Nonnull String newMessage) throws X {
		if (!pred.test(a)) {
			throw Handling.create(factory, newMessage);
		}
		return a;
	}

	/**
	* Throws new exception if condition is met.
	* Message will be formatted with predicate arguments.
	*/
	public static <X extends Throwable> char throwIf$(char a, @Nonnull LCharPredicate pred, @Nonnull ExMF<X> factory, @Nonnull String newMessage) throws X {
		if (pred.test(a)) {
			throw Handling.create(factory, newMessage, a);
		}
		return a;
	}

	/**
	* Throws new exception if condition is not met.
	* Message will be formatted with predicate arguments.
	*/
	public static <X extends Throwable> char throwIfNot$(char a, @Nonnull LCharPredicate pred, @Nonnull ExMF<X> factory, @Nonnull String newMessage) throws X {
		if (!pred.test(a)) {
			throw Handling.create(factory, newMessage, a);
		}
		return a;
	}

	/** Captures arguments but delays the evaluation. */
	default LBoolSupplier capture(char a) {
		return () -> this.test(a);
	}

	/** Creates function that always returns the same value. */
	static LCharPredicate constant(boolean r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LCharPredicate charPred(final @Nonnull LCharPredicate lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LCharPredicate recursive(final @Nonnull LFunction<LCharPredicate, LCharPredicate> selfLambda) {
		final LCharPredicateSingle single = new LCharPredicateSingle();
		LCharPredicate func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LCharPredicateSingle implements LCharPredicate {
		private LCharPredicate target = null;

		@Override
		public boolean testX(char a) throws Throwable {
			return target.testX(a);
		}

	}

	@Nonnull
	static LCharPredicate charPredThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LCharPredicate charPredThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce(message);
		};
	}

	static boolean call(char a, final @Nonnull LCharPredicate lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.test(a);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as alwaysFalse). */
	@Nonnull
	static LCharPredicate safe() {
		return LCharPredicate::alwaysFalse;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LCharPredicate> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LCharPredicate safe(final @Nullable LCharPredicate other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LCharPredicate> safeSupplier(final @Nullable LSupplier<LCharPredicate> supplier) {
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
	default LCharPredicate negate() {
		return a -> !test(a);
	}

	@Nonnull
	static LCharPredicate not(@Nonnull LCharPredicate pred) {
		Null.nonNullArg(pred, "pred");
		return pred.negate();
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LCharPredicate and(@Nonnull LCharPredicate other) {
		Null.nonNullArg(other, "other");
		return a -> test(a) && other.test(a);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LCharPredicate or(@Nonnull LCharPredicate other) {
		Null.nonNullArg(other, "other");
		return a -> test(a) || other.test(a);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LCharPredicate xor(@Nonnull LCharPredicate other) {
		Null.nonNullArg(other, "other");
		return a -> test(a) ^ other.test(a);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static LCharPredicate isEqual(char target) {
		return a -> a == target;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LCharPredicate compose(@Nonnull final LCharUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.test(before.applyAsChar(v));
	}

	public static LCharPredicate composed(@Nonnull final LCharUnaryOperator before, LCharPredicate after) {
		return after.compose(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LPredicate<V> charPredCompose(@Nonnull final LToCharFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.test(before.applyAsChar(v));
	}

	public static <V> LPredicate<V> composed(@Nonnull final LToCharFunction<? super V> before, LCharPredicate after) {
		return after.charPredCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LCharFunction<V> boolToCharFunc(@Nonnull LBoolFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.apply(this.test(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToByteFunction boolToCharToByteFunc(@Nonnull LBoolToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsByte(this.test(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToSrtFunction boolToCharToSrtFunc(@Nonnull LBoolToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsSrt(this.test(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToIntFunction boolToCharToIntFunc(@Nonnull LBoolToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsInt(this.test(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToLongFunction boolToCharToLongFunc(@Nonnull LBoolToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsLong(this.test(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToFltFunction boolToCharToFltFunc(@Nonnull LBoolToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsFlt(this.test(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharToDblFunction boolToCharToDblFunc(@Nonnull LBoolToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsDbl(this.test(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharUnaryOperator boolToCharUnaryOp(@Nonnull LBoolToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsChar(this.test(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LCharPredicate boolToCharPred(@Nonnull LLogicalOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.apply(this.test(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	// >>> LCharPredicate

	/** Returns TRUE. */
	public static boolean alwaysTrue(char a) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(char a) {
		return false;
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C0> void filterForEach(IndexedRead<C0, aChar> ia, C0 source, LCharConsumer consumer) {
		int size = ia.size(source);
		LOiToCharFunction<Object> oiFunc0 = (LOiToCharFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			char a = oiFunc0.applyAsChar(source, i);
			doIf(a, consumer);
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C0, I0> void filterIterate(SequentialRead<C0, I0, aChar> sa, C0 source, LCharConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).apply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToCharFunction<Object> nextFunc0 = (LToCharFunction) sa.supplier();
		while (testFunc0.test(iterator0)) {
			char a = nextFunc0.applyAsChar(iterator0);
			doIf(a, consumer);
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with index) if predicate test passes. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns number of iterations that element (or tuple) was accepter by predicate.
	*/
	default <V, C0> int tieForEach(V v, IndexedRead<C0, aChar> ia, C0 source, LTieCharConsumer<V> consumer) {
		int size = ia.size(source);
		LOiToCharFunction<Object> oiFunc0 = (LOiToCharFunction) ia.getter();
		int acceptedIndex = 0;
		int i = 0;
		for (; i < size; i++) {
			char a = oiFunc0.applyAsChar(source, i);
			acceptedIndex += doIf(v, acceptedIndex, a, consumer) ? 1 : 0;
		}
		return acceptedIndex;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with index) if predicate test passes. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns number of iterations that element (or tuple) was accepter by predicate.
	*/
	default <V, C0, I0> int tieIterate(V v, SequentialRead<C0, I0, aChar> sa, C0 source, LTieCharConsumer<V> consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).apply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToCharFunction<Object> nextFunc0 = (LToCharFunction) sa.supplier();
		int acceptedIndex = 0;
		int i = 0;
		while (testFunc0.test(iterator0)) {
			char a = nextFunc0.applyAsChar(iterator0);
			acceptedIndex += doIf(v, acceptedIndex, a, consumer) ? 1 : 0;
			i++;
		}
		return acceptedIndex;

	}

}
