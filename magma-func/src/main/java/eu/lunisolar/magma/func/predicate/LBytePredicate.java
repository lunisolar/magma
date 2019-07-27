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
 * Non-throwing functional interface (lambda) LBytePredicate for Java 8.
 *
 * Type: predicate
 *
 * Domain (lvl: 1): byte a
 *
 * Co-domain: boolean
 *
 */
@FunctionalInterface
@SuppressWarnings("UnusedDeclaration")
public interface LBytePredicate extends MetaPredicate, MetaInterface.NonThrowing, Codomain<aBool>, Domain1<aByte> { // NOSONAR

	String DESCRIPTION = "LBytePredicate: boolean test(byte a)";

	// boolean test(byte a) ;
	default boolean test(byte a) {
		// return nestingTest(a);
		try {
			return this.testX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/**
	 * Implement this, but call test(byte a)
	 */
	boolean testX(byte a) throws Throwable;

	default boolean tupleTest(LByteSingle args) {
		return test(args.value());
	}

	/** Function call that handles exceptions according to the instructions. */
	default boolean handlingTest(byte a, HandlingInstructions<Throwable, RuntimeException> handling) {
		try {
			return this.testX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handler.handleOrNest(e, handling);
		}
	}

	default LBytePredicate handling(HandlingInstructions<Throwable, RuntimeException> handling) {
		return a -> handlingTest(a, handling);
	}

	default boolean test(byte a, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		try {
			return this.testX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF, newMessage, messageParams);
		}
	}

	default LBytePredicate trying(@Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		return a -> test(a, exF, newMessage, messageParams);
	}

	default boolean test(byte a, @Nonnull ExWF<RuntimeException> exF) {
		try {
			return this.testX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.wrap(e, exF);
		}
	}

	default LBytePredicate trying(@Nonnull ExWF<RuntimeException> exF) {
		return a -> test(a, exF);
	}

	default boolean testThen(byte a, @Nonnull LPredicate<Throwable> handler) {
		try {
			return this.testX(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return handler.test(e);
		}
	}

	default LBytePredicate tryingThen(@Nonnull LPredicate<Throwable> handler) {
		return a -> testThen(a, handler);
	}

	/** Function call that handles exceptions by always nesting checked exceptions and propagating the others as is. */
	default boolean nestingTest(byte a) {
		try {
			return this.testX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.nestCheckedAndThrow(e);
		}
	}

	/** Function call that handles exceptions by always propagating them as is, even when they are undeclared checked ones. */
	default boolean shovingTest(byte a) {
		try {
			return this.testX(a);
		} catch (Throwable e) { // NOSONAR
			throw Handling.shoveIt(e);
		}
	}

	static boolean handlingTest(byte a, LBytePredicate func, HandlingInstructions<Throwable, RuntimeException> handling) { // <-
		Null.nonNullArg(func, "func");
		return func.handlingTest(a, handling);
	}

	static boolean tryTest(byte a, LBytePredicate func) {
		Null.nonNullArg(func, "func");
		return func.nestingTest(a);
	}

	static boolean tryTest(byte a, LBytePredicate func, @Nonnull ExWMF<RuntimeException> exF, @Nonnull String newMessage, @Nullable Object... messageParams) {
		Null.nonNullArg(func, "func");
		return func.test(a, exF, newMessage, messageParams);
	}

	static boolean tryTest(byte a, LBytePredicate func, @Nonnull ExWF<RuntimeException> exF) {
		Null.nonNullArg(func, "func");
		return func.test(a, exF);
	}

	static boolean tryTestThen(byte a, LBytePredicate func, @Nonnull LPredicate<Throwable> handler) {
		Null.nonNullArg(func, "func");
		return func.testThen(a, handler);
	}

	default boolean failSafeTest(byte a, @Nonnull LBytePredicate failSafe) {
		try {
			return test(a);
		} catch (Throwable e) { // NOSONAR
			Handling.handleErrors(e);
			return failSafe.test(a);
		}
	}

	static boolean failSafeTest(byte a, LBytePredicate func, @Nonnull LBytePredicate failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		if (func == null) {
			return failSafe.test(a);
		} else {
			return func.failSafeTest(a, failSafe);
		}
	}

	static LBytePredicate failSafe(LBytePredicate func, @Nonnull LBytePredicate failSafe) {
		Null.nonNullArg(failSafe, "failSafe");
		return a -> failSafeTest(a, func, failSafe);
	}

	default boolean doIf(byte a, LAction action) {
		Null.nonNullArg(action, "action");
		if (test(a)) {
			action.execute();
			return true;
		} else {
			return false;
		}
	}

	static boolean doIf(byte a, @Nonnull LBytePredicate predicate, @Nonnull LAction action) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.doIf(a, action);
	}

	static boolean doIf(byte a, @Nonnull LBytePredicate predicate, @Nonnull LByteConsumer consumer) {
		Null.nonNullArg(predicate, "predicate");
		return predicate.doIf(a, consumer);
	}

	default boolean doIf(byte a, @Nonnull LByteConsumer consumer) {
		Null.nonNullArg(consumer, "consumer");
		if (test(a)) {
			consumer.accept(a);
			return true;
		} else {
			return false;
		}
	}

	static void throwIf(byte a, LBytePredicate pred, ExMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		if (pred.test(a)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
	}

	static void throwIfNot(byte a, LBytePredicate pred, ExMF<RuntimeException> factory, @Nonnull String newMessage, @Nullable Object... messageParams) {
		if (!pred.test(a)) {
			throw Handling.create(factory, newMessage, messageParams);
		}
	}

	/** Just to mirror the method: Ensures the result is not null */
	default boolean nonNullTest(byte a) {
		return test(a);
	}

	/** For convenience, where "test()" makes things more confusing than "applyAsBoolean()". */

	default boolean doApplyAsBoolean(byte a) {
		return test(a);
	}

	/** Returns description of the functional interface. */
	@Nonnull
	default String functionalInterfaceDescription() {
		return LBytePredicate.DESCRIPTION;
	}

	public default <V> boolean doIf(V a1, byte a2, @Nonnull LObjByteConsumer<V> consumer) {
		Null.nonNullArg(consumer, "consumer");
		if (test(a2)) {
			consumer.accept(a1, a2);
			return true;
		} else {
			return false;
		}
	}

	public default <V> boolean doIf(V a1, int a2, byte a3, @Nonnull LTieByteConsumer<? super V> consumer) {
		Null.nonNullArg(consumer, "consumer");
		if (test(a3)) {
			consumer.accept(a1, a2, a3);
			return true;
		} else {
			return false;
		}
	}

	public default <V> int doIf(V a1, int a2, byte a3, @Nonnull LTieByteFunction<? super V> consumer) {
		Null.nonNullArg(consumer, "consumer");
		if (test(a3)) {
			return consumer.applyAsInt(a1, a2, a3);
		} else {
			return 0;
		}
	}

	/** From-To. Intended to be used with non-capturing lambda. */
	public static void fromTo(int min_i, int max_i, byte a, @Nonnull LBytePredicate func) {
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
	public static void fromTill(int min_i, int max_i, byte a, @Nonnull LBytePredicate func) {
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
	public static void times(int max_i, byte a, @Nonnull LBytePredicate func) {
		if (max_i < 0)
			return;
		fromTill(0, max_i, a, func);
	}

	/** Change function to consumer that ignores output. */
	public default LByteConsumer toConsumer() {
		return this::test;
	}

	/** Calls domain consumer before main function. */
	public default LBytePredicate before(@Nonnull LByteConsumer before) {
		Null.nonNullArg(before, "before");
		return (byte a) -> {
			before.accept(a);
			return test(a);
		};
	}

	/** Calls codomain consumer after main function. */
	public default LBytePredicate after(@Nonnull LBoolConsumer after) {
		Null.nonNullArg(after, "after");
		return (byte a) -> {
			final boolean retval = test(a);
			after.accept(retval);
			return retval;
		};
	}

	/** Captures arguments but delays the evaluation. */
	default LBoolSupplier capture(byte a) {
		return () -> this.test(a);
	}

	/** Creates function that always returns the same value. */
	static LBytePredicate constant(boolean r) {
		return a -> r;
	}

	/** Convenient method in case lambda expression is ambiguous for the compiler (that might happen for overloaded methods accepting different interfaces). */
	@Nonnull
	static LBytePredicate bytePred(final @Nonnull LBytePredicate lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda;
	}

	@Nonnull
	static LBytePredicate recursive(final @Nonnull LFunction<LBytePredicate, LBytePredicate> selfLambda) {
		final LBytePredicateSingle single = new LBytePredicateSingle();
		LBytePredicate func = selfLambda.apply(single);
		single.target = func;
		return func;
	}

	final class LBytePredicateSingle implements LSingle<LBytePredicate>, LBytePredicate {
		private LBytePredicate target = null;

		@Override
		public boolean testX(byte a) throws Throwable {
			return target.testX(a);
		}

		@Override
		public LBytePredicate value() {
			return target;
		}
	}

	@Nonnull
	static LBytePredicate bytePredThrowing(final @Nonnull ExF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce();
		};
	}

	@Nonnull
	static LBytePredicate bytePredThrowing(final String message, final @Nonnull ExMF<Throwable> exF) {
		Null.nonNullArg(exF, "exF");
		return a -> {
			throw exF.produce(message);
		};
	}

	static boolean call(byte a, final @Nonnull LBytePredicate lambda) {
		Null.nonNullArg(lambda, "lambda");
		return lambda.test(a);
	}

	// <editor-fold desc="wrap">

	// </editor-fold>

	// <editor-fold desc="safe">

	/** Safe instance. That always returns the same value (as alwaysFalse). */
	@Nonnull
	static LBytePredicate safe() {
		return LBytePredicate::alwaysFalse;
	}

	/** Safe instance supplier. Returns supplier of safe() instance. */
	@Nonnull
	static LSupplier<LBytePredicate> safeSupplier() {
		return () -> safe();
	}

	/** Safe wrapping. Either argument function is returned (if it is not null) or safe() instance. */
	@Nonnull
	static LBytePredicate safe(final @Nullable LBytePredicate other) {
		if (other == null) {
			return safe();
		} else {
			return other;
		}
	}

	/** Safe supplier. Either argument supplier is returned (if it is not null) or supplier of safe() instance. */
	@Nonnull
	static LSupplier<LBytePredicate> safeSupplier(final @Nullable LSupplier<LBytePredicate> supplier) {
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
	default LBytePredicate negate() {
		return a -> !test(a);
	}

	/**
	 * Returns a predicate that represents the logical AND of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#and()}
	 */
	@Nonnull
	default LBytePredicate and(@Nonnull LBytePredicate other) {
		Null.nonNullArg(other, "other");
		return a -> test(a) && other.test(a);
	}

	/**
	 * Returns a predicate that represents the logical OR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LBytePredicate or(@Nonnull LBytePredicate other) {
		Null.nonNullArg(other, "other");
		return a -> test(a) || other.test(a);
	}

	/**
	 * Returns a predicate that represents the logical XOR of evaluation of this predicate and the argument one.
	 * @see {@link java.util.function.Predicate#or}
	 */
	@Nonnull
	default LBytePredicate xor(@Nonnull LBytePredicate other) {
		Null.nonNullArg(other, "other");
		return a -> test(a) ^ other.test(a);
	}

	/**
	 * Creates predicate that evaluates if an object is equal with the argument one.
	 * @see {@link java.util.function.Predicate#isEqual()
	 */
	@Nonnull
	static LBytePredicate isEqual(byte target) {
		return a -> a == target;
	}

	// </editor-fold>

	// <editor-fold desc="compose (functional)">

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default LBytePredicate compose(@Nonnull final LByteUnaryOperator before) {
		Null.nonNullArg(before, "before");
		return v -> this.test(before.applyAsByte(v));
	}

	public static LBytePredicate composed(@Nonnull final LByteUnaryOperator before, LBytePredicate after) {
		return after.compose(before);
	}

	/** Allows to manipulate the domain of the function. */
	@Nonnull
	default <V> LPredicate<V> bytePredCompose(@Nonnull final LToByteFunction<? super V> before) {
		Null.nonNullArg(before, "before");
		return v -> this.test(before.applyAsByte(v));
	}

	public static <V> LPredicate<V> composed(@Nonnull final LToByteFunction<? super V> before, LBytePredicate after) {
		return after.bytePredCompose(before);
	}

	// </editor-fold>

	// <editor-fold desc="then (functional)">

	/** Combines two functions together in a order. */
	@Nonnull
	default <V> LByteFunction<V> boolToByteFunc(@Nonnull LBoolFunction<? extends V> after) {
		Null.nonNullArg(after, "after");
		return a -> after.apply(this.test(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteUnaryOperator boolToByteUnaryOp(@Nonnull LBoolToByteFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsByte(this.test(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToSrtFunction boolToByteToSrtFunc(@Nonnull LBoolToSrtFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsSrt(this.test(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToIntFunction boolToByteToIntFunc(@Nonnull LBoolToIntFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsInt(this.test(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToLongFunction boolToByteToLongFunc(@Nonnull LBoolToLongFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsLong(this.test(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToFltFunction boolToByteToFltFunc(@Nonnull LBoolToFltFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsFlt(this.test(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToDblFunction boolToByteToDblFunc(@Nonnull LBoolToDblFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsDbl(this.test(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LByteToCharFunction boolToByteToCharFunc(@Nonnull LBoolToCharFunction after) {
		Null.nonNullArg(after, "after");
		return a -> after.applyAsChar(this.test(a));
	}

	/** Combines two functions together in a order. */
	@Nonnull
	default LBytePredicate boolToBytePred(@Nonnull LLogicalOperator after) {
		Null.nonNullArg(after, "after");
		return a -> after.apply(this.test(a));
	}

	// </editor-fold>

	// <editor-fold desc="variant conversions">

	// </editor-fold>

	// >>> LBytePredicate

	/** Returns TRUE. */
	public static boolean alwaysTrue(byte a) {
		return true;
	}

	/** Returns FALSE. */
	public static boolean alwaysFalse(byte a) {
		return false;
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	*/
	default <C0> void filterForEach(IndexedRead<C0, aByte> ia, C0 source, LByteConsumer consumer) {
		int size = ia.size(source);
		LOiToByteFunction<Object> oiFunc0 = (LOiToByteFunction) ia.getter();
		int i = 0;
		for (; i < size; i++) {
			byte a = oiFunc0.applyAsByte(source, i);
			doIf(a, consumer);
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer if predicate test passes.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	*/
	default <C0, I0> void filterIterate(SequentialRead<C0, I0, aByte> sa, C0 source, LByteConsumer consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).apply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToByteFunction<Object> nextFunc0 = (LToByteFunction) sa.supplier();
		while (testFunc0.test(iterator0)) {
			byte a = nextFunc0.applyAsByte(iterator0);
			doIf(a, consumer);
		}
	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with index) if predicate test passes. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method is not expected.
	* @returns number of iterations that element (or tuple) was accepter by predicate.
	*/
	default <V, C0> int tieForEach(V v, IndexedRead<C0, aByte> ia, C0 source, LTieByteConsumer<V> consumer) {
		int size = ia.size(source);
		LOiToByteFunction<Object> oiFunc0 = (LOiToByteFunction) ia.getter();
		int acceptedIndex = 0;
		int i = 0;
		for (; i < size; i++) {
			byte a = oiFunc0.applyAsByte(source, i);
			acceptedIndex += doIf(v, acceptedIndex, a, consumer) ? 1 : 0;
		}
		return acceptedIndex;

	}

	/**
	* For each element (or tuple) from arguments, calls the consumer (with index) if predicate test passes. First argument is designated as 'target' object.
	* Thread safety, fail-fast, fail-safety of this method depends highly on the arguments.
	* @returns number of iterations that element (or tuple) was accepter by predicate.
	*/
	default <V, C0, I0> int tieIterate(V v, SequentialRead<C0, I0, aByte> sa, C0 source, LTieByteConsumer<V> consumer) {
		Object iterator0 = ((LFunction) sa.adapter()).apply(source);
		LPredicate<Object> testFunc0 = (LPredicate) sa.tester();
		LToByteFunction<Object> nextFunc0 = (LToByteFunction) sa.supplier();
		int acceptedIndex = 0;
		int i = 0;
		while (testFunc0.test(iterator0)) {
			byte a = nextFunc0.applyAsByte(iterator0);
			acceptedIndex += doIf(v, acceptedIndex, a, consumer) ? 1 : 0;
			i++;
		}
		return acceptedIndex;

	}

}
